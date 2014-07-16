var url = "/angular/sitemap/";
var commandUrl = "/angular/cmd/";

var appControllers = angular.module('appControllers', ['windowEventBroadcasts', 'ngAnimate']);

appControllers.controller('HomeController', function($scope, sitemap, $log, $location, $http, webSocket, $interval) {

	$scope.show = false;

	var setupModel = function(data) {
		$log.debug("Sitemap loaded: " + JSON.stringify(data));


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

		$scope.viewItem = data;

		$scope.show = true;

	};

	// watch change of the location
	$scope.$watch(function() {
		return $location.path();
	}, function(path, oldPath) {
		if (oldPath === path && sitemap.location !== undefined) {
			return;
		}
		// animate
		//$scope.show = false;
		//$scope.viewItem = null;
		
		$log.debug("location path changed to '" + path + "'");
		sitemap.lastLocation = sitemap.location;
		sitemap.location = path === "/" ? "" : path;

		var widget = path.substring(1);
		$scope.root = widget;

		if (!sitemap.fetching) {
			//$interval(function(){
			sitemap.fetch("default", widget, setupModel);
			// }, 500, 1);
		}
	});

	var getWidgets = function(root, id) {
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
				var it = getWidgets(widget, id);
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

	var handleUpdate = function(item) {
		$scope.$apply(function() {
			(function(item) {
				var widgets = getWidgets($scope.viewItem, item.id);
				if (!widgets || widgets.length == 0 || !item.values) {
					return;
				}

				for (var int = 0; int < widgets.length; int++) {
					widgets[int].processUpdate(item);
				}
			}(item));

		});
	};

	webSocket.subscribe(handleUpdate);

	reconnect = function(){
		webSocket.init();
		webSocket.subscribe(handleUpdate);
		$log.info("Reconnected");
	};

	disconnect = function(){
		webSocket.unsubscribe(handleUpdate);
		webSocket.close();
		$log.info("Disconnected");
	};

	// event handling
	$scope.$on('$windowFocus', function(broadcastEvent, browserEvent)  {
		console.log("onFocus");
		reconnect();
	});

	// event handling
	$scope.$on('$windowShow', function(broadcastEvent, browserEvent)  {
		console.log("onShow");
		reconnect();
	});

	$scope.$on('$windowBlur', function(broadcastEvent, browserEvent)  {
		console.log("onBlur");
		disconnect();			
	});

	$scope.$on('$windowHide', function(broadcastEvent, browserEvent)  {
		console.log("onHide");
		disconnect();			
	});
	
	$scope.$on("pagehide", function(event){
		console.log("onPageHide");
	});
	
	$scope.$on("pageshow", function(event){
		console.log("onPageShow");
	});



	// scope functions
	$scope.load = function(widget) {
		widget.selected = true;
		$location.path(widget.id);
	};

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

