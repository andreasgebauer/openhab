
var appControllers = angular.module('app.controllers', [ 'app.factories', 'windowEventBroadcasts', 'ngAnimate', 'sprintf' ]);

appControllers.controller('HomeController', function($scope, sitemap, $log, $location, commandService, iconService, transformService, webSocket, $timeout, getWatchCount) {

	webSocket.init();

	$scope.stayConnected = false;
	$scope.showLoader = false;

	var that = this;

	var parseValue = function(value){
		if (value.valueType == "datetime") {
			return new Date(value.value);
		}
		return value.value;
	};

	var formatValue = function(widget, data) {
		if(angular.isDefined(data) && angular.isDefined(data.value) && data.value != 'Uninitialized' && widget.labelPattern) {
			var labelPattern = widget.labelPattern;
			var rx = /^(.*)? \[(.*)\]$/gm;
			var result = rx.exec(labelPattern);
			if(result != null){
				var valuePattern = result[2];
				var value = parseValue(data);

				var rxTransform = /^(.*)\((.*)?\):(.*)$/gm;
				var rxTransformResult = rxTransform.exec(valuePattern);
				if(rxTransformResult != null){
					var transformType = rxTransformResult[1];
					var transformParam = rxTransformResult[2];
					valuePattern = rxTransformResult[3];
					var formattedValue = mdgw.format(valuePattern,  value);
					$log.debug("formatValue: need to transform: " + transformType + " " + transformParam + " " + formattedValue);
					transformService.transform(transformType, transformParam, formattedValue)
					.then(function(transformed){
						widget.formattedValue = transformed.data;
					});
				} else {
					$log.debug("formatValue: pattern '" + valuePattern + "' value: " + value);
					widget.formattedValue = mdgw.format(valuePattern,  value);
				}
			}
		}
	};

	var styleUpdate = function (widget) {
		widget.styleClass = "red";
		$timeout(function() {
			widget.styleClass = "inherit";
			$timeout(function() {
				widget.styleClass = "";
			}, 1000);
		}, 1000);	
	};

	var processUpdate = function(widget, item) {

		styleUpdate(widget);

		$log.debug("Processing update for widget " + widget.id);
		
		var value = parseValue(item);
		var iconPrefix = item.icon != "none" ? item.icon : widget.type;

		formatValue(widget, item);
		widget.icon = imageUrl + $scope.sitemapName + "/" + widget.id + "?state=" + encodeURIComponent(value);
		$log.debug("Setting icon to " + widget.icon);
		//iconService.getIcon(widget, value)
		//.then(function(icon) {
		//	$log.debug("processUpdate: resolved icon " + icon);
		//	widget.icon = icon;
		//});

		if (widget.type === "text" || widget.type === "text_link") {
			widget.value = value
		} else if (widget.type === "switch") {
			widget.value = value;
		} else if (widget.type === "slider" || widget.type == "setpoint") {
			if(value instanceof String) {
				value = value.toLowerCase();
			}
			widget.value = value;
		} else if (widget.type === "chart") {
			// nothing to do
		} else {
			$log.warn("Unhandled data for: " + JSON.stringify(widget));
		}
	};

	// process a widget being shown
	var process = function(widget, isRoot) {
		$log.debug("process: " + widget.id + ": " + widget.item  + " (" + widget.type + ")");

		if(angular.isUndefined(widget.originalIcon)){
			widget.originalIcon = widget.icon;
		}
		widget.icon = imageUrl + $scope.sitemapName +"/" + widget.id + "?state=" + encodeURIComponent(widget.value);

		var formatValue_ = function (element) {
			$log.debug("Formatter: " + element.item);
			if(angular.isUndefined(element.formattedValue)) {
				formatValue(element, element);
			} else {
				$log.debug("Formatter: formatted value already defined: " + element.formattedValue);
			}
		};

		if(widget.type != "frame" && angular.isDefined(widget.type)) {
			formatValue_(widget);
		}

		// don't follow links
		if (/_link$/.test(widget.type) && !isRoot) {
			return;
		}

		if (widget.children) {
			for (var int = 0; int < widget.children.length; int++) {
				process(widget.children[int], false);
			}
		}
	};

	var setupModel = function(widgetId, data) {
		data.id = widgetId;
		//debugger;

		var oldSitemap = $scope.sitemap;
		if (angular.isDefined(oldSitemap)) {
			// merge the two sitemap partials if possible
			var widget = sitemap.getWidget(oldSitemap, data.id);
			if (angular.isUndefined(widget)) {
				widget = sitemap.getWidget(data, oldSitemap.id);
				if (angular.isDefined(widget)) {
					// old sitemap appended as children
					// in case of navigating a level up
					widget.parentId = oldSitemap.parentId;
					widget.children = oldSitemap.children;
					$scope.sitemap = data;
				}
			} else {
				// new sitemap appended as children of old one
				// in case of navigating by clicking an item (level down)
				widget.parentId = data.parentId;
				widget.children = data.children;
			}
		} else {
			$scope.sitemap = data;
		}

		$scope.nav = {
			back : {
				text : "Back",
				href : data.parentId ? "#" + data.parentId : "#/",
				visible : sitemap.location !== ""
			},
			home : {
				text : "Home",
				visible : sitemap.location !== ""
			},
			title : {
				text : data.label,
				visible : true
			}
		};

		$scope.viewItem = sitemap.pageItem($scope.sitemap, data.id);
		if (angular.isUndefined($scope.viewItem)) {
			$scope.viewItem = $scope.sitemap;
		}

		process($scope.viewItem, true);
	};

	var fetchSitemap = function(widgetId, setupModel) {
		//$scope.showLoader = true;
		sitemap.fetch($scope.sitemapName, widgetId, 
		function(data) {
			setupModel(widgetId, data);
			$scope.showLoader = false;
		},
		function(){
			$scope.showLoader = false;
		});
	};

	// watch change of the location
	$scope.$watch(function() {
		return $location.path();
	}, function(path, oldPath) {
		if (oldPath === path && sitemap.location !== undefined) {
			return;
		}
		$log.debug("location path changed to '" + path + "'");

		sitemap.lastLocation = sitemap.location;
		sitemap.location = path === "/" ? "" : path;
		$scope.sitemapName = "default";

		var widgetId = path.substring(1);

		if (angular.isUndefined($scope.sitemap)) {
			fetchSitemap(widgetId, setupModel);
		} else {

			var viewItem = sitemap.pageItem($scope.sitemap, widgetId);
			//$scope.viewItem = sitemap.pageItem($scope.sitemap, widgetId);

			if (angular.isUndefined(viewItem) || angular.isUndefined(viewItem.children)) {
				// we need to load the sitemap data for the page shown
				fetchSitemap(widgetId, setupModel);
			} else {
				
				if(angular.isUndefined(viewItem)){
					fetchSitemap(widgetId, setupModel);
				} else {

					// sitemap already fetched
					$scope.showLoader = false;
					viewItem.selected = false;
					process(viewItem, true);
					$scope.viewItem = viewItem;
				}
			}

			if (angular.isDefined($scope.nav)) {
				// show back and home when not showing root
				$scope.nav.home.visible = $scope.nav.back.visible = sitemap.location !== "";

				if (angular.isDefined(viewItem)) {
					$scope.nav.back.href = viewItem.parentId ? "#" + viewItem.parentId : "#/";
					$scope.nav.title.text = viewItem.label;
					if (angular.isDefined(viewItem.formattedValue)) {
						$scope.nav.title.text += " " + viewItem.formattedValue;
					}
				}
			}
		}
	});

	// handles data from the websocket
	this.handleUpdate = function(item) {
		$scope.$apply(function () {
			// get the widgets which should handle the update
			var widgets = sitemap.getWidgets($scope.sitemap, item.id);
			if (!widgets || widgets.length == 0 || angular.isUndefined(item.value)) {
				return;
			}

			// update the data for each widget
			angular.forEach(widgets, function(widget){
				processUpdate(widget, item);
			});
		});
	};

	webSocket.subscribe(this.handleUpdate);

	this.reconnect = function() {
		if (webSocket.isClosed()) {
			webSocket.reconnect();
			$log.info("Reconnected");
			fetchSitemap($scope.viewItem.id, function(widgetId, data){

				var mergeWidgetData = function(root) {
					for (var int = 0; root.children && int < root.children.length; int++) {
						var child = root.children[int];
						var widget = sitemap.getWidget(data, child.id);
						if(widget) {
							styleUpdate(child);
							child.value = widget.value;
							child.icon = widget.icon;
							child.label = widget.label;
							formatValue(child, child);
							mergeWidgetData(child);
						}
					}
				}
				mergeWidgetData($scope.viewItem);
			});
		}
	};

	this.disconnect = function() {
		if (!$scope.stayConnected) {
			//webSocket.unsubscribe(that.handleUpdate);
			webSocket.close();
			$log.info("Disconnected");
		}
	};

	// event handling
	$scope.$on('$windowFocus', function(broadcastEvent, browserEvent) {
		// works when entering window
		that.reconnect();
	});

	// event handling
	$scope.$on('$windowShow', function(broadcastEvent, browserEvent) {
		console.log("onShow");
		that.reconnect();
	});

	$scope.$on('$windowBlur', function(broadcastEvent, browserEvent) {
		// works when leaving window
		that.disconnect();
	});

	$scope.$on('$windowHide', function(broadcastEvent, browserEvent) {
		console.log("onHide");
		that.disconnect();
	});

	// scope functions

	$scope.getWidgets = sitemap.getWidgets();

	// I hold the current count of watchers in the current page. This extends
	// beyond the current scope, and will hold the count for all scopes on 
	// the entire page.
	$scope.watchCount = 0;

	// I hold the bookmarkletized version of the function to provide a take-
	// away feature that can be used on any AngularJS page.
	$scope.bookmarklet = getWatchCount.bookmarklet;


	// Every time the digest runs, it's possible that we'll change the number
	// of $watch() bindings on the current page. 
	$scope.$watch(
		function watchCountExpression() {
			return( getWatchCount() );
		},
		function handleWatchCountChange( newValue ) {
			$scope.watchCount = newValue;
		}
	);

	$scope.changeState = function(widget, state) {
		commandService.update(widget, state);
	};

	$scope.repeatedRequest = function(item, cmd, frequency, switch_) {
		$log.debug("repeatedRequest invoked: " + item + ": " + cmd);

		$scope.changeState(item, cmd);
	};

	$scope.stopRepeatedRequest = function(item, cmd) {
		$log.debug("stopRepeatedRequest invoked: " + item + ": " + cmd);
	};

});

appControllers.controller('ButtonController', function($scope, $log, commandService) {
	//$log.debug("[BTN] Init");
	$scope.changeState = function(item, state) {
		$log.debug("[BTN] changeState invoked: " + item + ": " + state);
		commandService.update(item, state);
	};
	
	this.changeState = $scope.changeState;
});


appControllers.controller("LinkController", function($scope, $log, $timeout, $location){
	//$log.debug("[LNK] Init");
	// sets the widget to show
	this.load = function(widget) {
		widget.selected = true;

		$timeout(function() {
			//$scope.viewItem = widget;
			$location.path(widget.id);
			widget.selected = false;
			//$scope.showLoader = true;
		});
	};
});