
var appControllers = angular.module('app.controllers', [ 'app.factories', 'windowEventBroadcasts', 'ngAnimate', 'sprintf' ]);

appControllers.controller('HomeController', function($scope, sitemap, $log, $location, $http, webSocket, $interval, $timeout, getWatchCount) {

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
		// $log.debug("processing update for " + item.id);

		var value = parseValue(item);
		var iconPrefix = item.icon != "none" ? item.icon : widget.type;

		widget.formattedValue = formatted(widget, item);

		if (widget.type === "text" || widget.type === "text_link") {
			// or simply widget.value = value.toLowerCase();
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

	var processWidget = function (element) {

		if(angular.isUndefined(element.formattedValue)) {
			element.formattedValue = formatted(element, element);
		}

	//	if(element.processUpdate) {
	//		return;
	//	}
	//
	//	if(!element.item){
	//		return;
	//	}

		//console.debug("Registering processUpdate() at " + element.item);

		// add function to process update data (from websocket)
		//element.processUpdate = processUpdate;
	};

	// process an item being shown
	var process = function(element) {
		// console.debug("Processing element " + JSON.stringify(element));
		if (element.children) {
			for (var int = 0; int < element.children.length; int++) {
				var child = element.children[int];
				// don't follow links
				if (!/_link$/.test(child.type)) {
					process(child);
				}

				processWidget(child);
			}
		}

		processWidget(element);
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

		var setupModel = function(data) {
			$log.debug("Sitemap for widget " + data.id + " loaded: " + data.label);
			data.id = widgetId;
			//debugger;

			var oldSitemap = $scope.sitemap;
			if (angular.isDefined(oldSitemap)) {
				// merge the two sitemap partials if possible

				var getWidget = function(root, id) {
					for (var int = 0; root.children && int < root.children.length; int++) {
						var child = root.children[int];
						if (child.id == id) {
							return child;
						}
						if (child.children) {
							var candidate = getWidget(child, id);
							if (!angular.isUndefined(candidate)) {
								return candidate;
							}
						}
					}
				};

				var widget = getWidget(oldSitemap, data.id);
				if (angular.isUndefined(widget)) {
					widget = getWidget(data, oldSitemap.id);
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

			$timeout(function(){
				$scope.showLoader = false;
			});
		};
		
		var fetchSitemap = function(widgetId, setupModel) {
			//$scope.showLoader = true;
			sitemap.fetch("default", widgetId, setupModel, function(){
				$scope.showLoader = false;
			});
		};

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
					$scope.showLoader = false;

					viewItem.selected = false;
					process(viewItem);

					$timeout(function(){
						$scope.viewItem = viewItem;
					});
					//$timeout(function(){
					//});
				}
			}

			if (angular.isDefined($scope.nav)) {
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

	this.getWidgets = function(root, id) {
		if (angular.isUndefined(root)) {
			return;
		}
		var addIfNotExistent = function(arr, el) {
			var found = false;
			for (var j = 0; j < arr.length; j++) {
				var el2 = arr[j];
				if (el2 === el) {
					found = true;
					break;
				}
			}
			if (!found) {
				arr.push(el);
			}
		};

		var items = new Array;
		for (var int = 0; root.children && int < root.children.length; int++) {
			var widget = root.children[int];
			if (widget.item === id) {
				addIfNotExistent(items, widget);
			}
			if (widget.children) {
				var it = this.getWidgets(widget, id);
				if (it) {
					for (var j = 0; j < it.length; j++) {
						addIfNotExistent(items, it[j]);
					}
				}
			}
			if (widget.items) {
				for (var j = 0; j < widget.items.length; j++) {
					if (widget.items[j] === id) {
						addIfNotExistent(items, widget);
					}
				}
			}
		}
		return items;
	};

	this.handleUpdate = function(item) {
		$scope.$apply(function () {
			// console.debug("Received " + item.id);
			var widgets = that.getWidgets($scope.sitemap, item.id);
			if (!widgets || widgets.length == 0 || angular.isUndefined(item.value)) {
				//console.debug("No widget found for " + item.id);
				return;
			}

			// console.debug(widgets.length + " widgets found for " + item.id);
			angular.forEach(widgets, function(widget){
				//console.log("red: " + widget.item)
				widget.styleClass = "red";
				var w = widget;
				$timeout(function() {
					//console.log("whi: " + w.item)
					w.styleClass = "inherit";
					$timeout(function() {
						//console.log("whi: " + w.item)
						w.styleClass = "";
					}, 3000);
				}, 2000);
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

	// sets the widget to show
	$scope.load = function(widget) {
		widget.selected = true;

		$timeout(function() {
			//$scope.viewItem = widget;
			$location.path(widget.id);
			widget.selected = false;
			//$scope.showLoader = true;
		});
	};

	$scope.getWidgets = this.getWidgets();

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
		$log.debug("changeState invoked: " + widget + ": " + state);

		var params = {};
		params[widget] = state;

		$http.get(commandUrl, {
			params : params
		}).success(function(data) {
		}).error(function() {
			$log.error("Command was not successful");
		});
	};

	$scope.repeatedRequest = function(item, cmd, frequency, switch_) {
		$log.debug("repeatedRequest invoked: " + item + ": " + cmd);

		$scope.changeState(item, cmd);
	};

	$scope.stopRepeatedRequest = function(item, cmd) {
		$log.debug("stopRepeatedRequest invoked: " + item + ": " + cmd);
	};

});

