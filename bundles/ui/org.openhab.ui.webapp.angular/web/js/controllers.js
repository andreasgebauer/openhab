
var appControllers = angular.module('app.controllers', [ 'app.factories', 'windowEventBroadcasts', 'ngAnimate', 'sprintf' ]);

appControllers.controller('HomeController', function($scope, sitemap, $log, $location, commandService, webSocket, $interval, $timeout, getWatchCount) {

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

	var formatted = function(widget, data) {
		if(angular.isDefined(data.value) && data.value != 'Uninitialized' && widget.valuePattern) {
			return mdgw.format(widget.valuePattern,  parseValue(data));
		}
	};

	var processUpdate = function(widget, item) {
		var value = parseValue(item);
		var iconPrefix = item.icon != "none" ? item.icon : widget.type;

		widget.formattedValue = formatted(widget, item);

		if (widget.type === "text" || widget.type === "text_link") {
			widget.icon = item.icon;
		} else {
			if (widget.type === "switch") {
				widget.value = value;
			} else if (widget.type === "slider") {
				widget.value = value;
				if(value instanceof String) {
					value = value.toLowerCase();
				}
				widget.icon = iconPrefix + "-" + value;
			} else if (widget.type === "chart") {
				// nothing to do
			} else {
				$log.warn("Unhandled data for: " + JSON.stringify(widget));
			}

			if (angular.isDefined(widget.value)) {
				if (iconPrefix.indexOf("-") == -1) {
					if (typeof widget.value === "string") {
						widget.icon = iconPrefix + "-" + widget.value.toLowerCase();
					} else if (typeof value === "string") {
						widget.icon = iconPrefix + "-" + value.toLowerCase();
					}
				} else {
					widget.icon = iconPrefix;
				}
			}
		}
	};

	// process a widget being shown
	var process = function(widget) {
		var formatValue= function (element) {
			
			if(angular.isUndefined(element.formattedValue)) {
				element.formattedValue = formatted(element, element);
			}
		};
		if (widget.children) {
			for (var int = 0; int < widget.children.length; int++) {
				var child = widget.children[int];
				// don't follow links
				if (!/_link$/.test(child.type)) {
					process(child);
				}

				formatValue(child);
			}
		}
		formatValue(widget);
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

		process($scope.viewItem);

		$scope.showLoader = false;
	};

	var fetchSitemap = function(widgetId, setupModel) {
		//$scope.showLoader = true;
		sitemap.fetch("default", widgetId, 
		function(data) {
			setupModel(widgetId, data);	
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
					process(viewItem);
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

	this.styleUpdate = function (widget) {
		widget.styleClass = "red";
		$timeout(function() {
			widget.styleClass = "inherit";
			$timeout(function() {
				widget.styleClass = "";
			}, 1000);
		}, 1000);	
	};

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
				that.styleUpdate(widget);
				processUpdate(widget, item);
			});
		});
	};

	webSocket.subscribe(this.handleUpdate);

	this.reconnect = function() {
		if (webSocket.isClosed()) {
			webSocket.reconnect();
			//webSocket.subscribe(that.handleUpdate);
			$log.info("Reconnected");
			fetchSitemap($scope.viewItem.id, function(widgetId, data){

				var procWidget = function(root) {
					for (var int = 0; root.children && int < root.children.length; int++) {
						var child = root.children[int];
						var widget = sitemap.getWidget(data, child.id);
						if(widget) {
							that.styleUpdate(child);
							child.value = widget.value;
							child.icon = widget.icon;
							child.label = widget.label;
							child.formattedValue = widget.formattedValue;
							procWidget(child);
						}
					}
				}
				procWidget($scope.viewItem);
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
	$log.debug("[BTN] Init");
	$scope.changeState = function(item, state) {
		$log.debug("[BTN] changeState invoked: " + item + ": " + state);
		commandService.update(item, state);
	};
	
	this.changeState = $scope.changeState;
});


appControllers.controller("LinkController", function($scope, $log, $timeout, $location){
	$log.debug("[LNK] Init");
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