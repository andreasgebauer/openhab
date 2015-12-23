
var appControllers = angular.module('app.controllers', [ 'app.factories', 'window-events', 'ngAnimate', 'sprintf' ]);

appControllers.controller('HomeController', function($scope, $log, $location, $timeout, $animate, sitemap, commandService, iconService, formatter, parser, webSocket) {

	webSocket.init();

	var colorPicker = "Colorpicker";

	$scope.stayConnected = false;
	$scope.showLoader = true;

	$scope.nav = {
					back : {},
					home : {},
					title : {}
				};

	$scope.viewSwap = {
		nr: 0
	};

	var that = this;

	var cacheSitemap = true;

	var updateTitleText = function(viewItem){
		$log.info("Update title text: " + viewItem.item);
		if(angular.isDefined(viewItem.labelPattern)){
			var patterns = formatter.sliceLabelAndValuePattern(viewItem.labelPattern);
			var valuePattern;
			if(patterns && patterns.length == 3){
				valuePattern = patterns[2];
			} else {
				valuePattern = viewItem.labelPattern;
			}
			 
			formatter.getFormattedValue(viewItem.value, valuePattern).then(function(formattedValue){
				var prefix = (valuePattern === viewItem.labelPattern) ? "" : viewItem.label + " ";
				$scope.nav.title.text = prefix + formattedValue.replace("[","").replace("]", "");
			});
		} else {
			$scope.nav.title.text = viewItem.label;
		}
	}

	var setupModel = function( data) {
		var oldSitemap = $scope.sitemap;
		if (cacheSitemap && angular.isDefined(oldSitemap)) {
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
			// first sitemap
			$scope.sitemap = data;
		}

		// update back button
		$scope.nav.back.text = "Back";
		$scope.nav.back.href = data.parentId ? "#" + data.parentId : "#/";
		$scope.nav.back.visible = sitemap.location !== "";

		// update home button
		$scope.nav.home.text = "Home";
		$scope.nav.home.visible = sitemap.location !== "";

		// update title button
		//$scope.nav.title.text = data.label;
		$scope.nav.title.visible = true;
		
		var viewRoot = sitemap.pageItem($scope.sitemap, data.id);

		viewRoot = angular.isUndefined(viewRoot) ? $scope.sitemap : viewRoot;

		//process(viewRoot, true);
		
		$scope.viewRoot = viewRoot;

		$scope.viewSwap.nr++;

		updateTitleText($scope.viewRoot);

		$scope.showLoader = false;

	};

	var fetchSitemap = function(widgetId, setupModel) {

		var success = function(data) {
			data.id = widgetId;
			setupModel(data);
		};

		sitemap.fetch($scope.sitemapName, widgetId, success,
		function() {
			//TODO show error
		});
	};

	// watch change of the location
	$scope.$watchCollection(
	function() {
		return $location.path();
	}, 
	function(path, oldPath) {
		if (oldPath === path && sitemap.location !== undefined) {
			return;
		}

		path = $location.path();

		$log.debug("location path changed to '" + path + "'");

		sitemap.lastLocation = sitemap.location;
		sitemap.location = path === "/" ? "" : path;
		if(!sitemap.name) {
			sitemap.name = $location.search().sitemap != undefined ? $location.search().sitemap : "default";
		}

		var setAnimationStyle = function(classToAdd){
			$scope.animationStyle = classToAdd;
			// add style class to viewRoot to enable animations
			var el = angular.element($(".viewRoot"));
			el.removeClass("slide-right slide-left pop-up").addClass($scope.animationStyle);

			// add style class to title to enable animations
			var el = angular.element($(".swap-title"));
			el.removeClass("slide-right slide-left pop-up").addClass($scope.animationStyle);
		}

		$scope.sitemapName = sitemap.name;
		if(angular.isDefined($scope.viewRoot)) {
			setAnimationStyle((oldPath.length > path.length) ? "slide-right" : "slide-left");

		}

		if(path == "/_" + colorPicker) {

			setAnimationStyle("slide-left");

			$scope.viewSwap.nr++;

			return;
		}




		var widgetId = path.substring(1);

		// cache sitemap?
		if(!cacheSitemap) {
			fetchSitemap(widgetId, setupModel);
			return;
		}

		if (angular.isUndefined($scope.sitemap)) {
			fetchSitemap(widgetId, setupModel);
		} else {

			var viewItem = sitemap.pageItem($scope.sitemap, widgetId);

			if (angular.isUndefined(viewItem) || angular.isUndefined(viewItem.children)) {
				// we need to load the sitemap data for the page shown
				fetchSitemap(widgetId, setupModel);
			} else {
				// sitemap already fetched
				$scope.showLoader = false;
				//process(viewItem, true);
				$scope.viewRoot = viewItem;

				$scope.viewSwap.nr++;

				// show back and home when not showing root
				$scope.nav.home.visible = $scope.nav.back.visible = sitemap.location !== "";
				if (angular.isDefined(viewItem)) {
					$scope.nav.back.href = viewItem.parentId ? "#" + viewItem.parentId : "#/";
					updateTitleText(viewItem);
				}

				// update data of existing widgets
				// this garantees that there's no stale data
				updateViewData();
			}
		}
	});

	
	// updates the widgets of the currently shown view
	var updateViewData = function(){

		var mergeWidgetData = function(root, data) {
			for (var int = 0; root.children && int < root.children.length; int++) {
				var child = root.children[int];
				var widget = sitemap.getWidget(data, child.id);
				if(widget) {
					child.value = widget.value;
					child.icon = widget.icon;
					child.label = widget.label;
					formatter.formatValue(child, child);
					mergeWidgetData(child, data);
				}
			}
		};


		fetchSitemap($scope.viewRoot.id, function(data){
			mergeWidgetData($scope.viewRoot, data);
		});
	}

	this.reconnect = function() {
		if (webSocket.isClosed()) {
			webSocket.reconnect();
			$log.info("Reconnected");
			updateViewData();
		}
	};

	this.disconnect = function() {
		if (!$scope.stayConnected) {
			//webSocket.unsubscribe(that.handleUpdate);
			webSocket.close();
			$log.info("Disconnected");
		}
	};

	$scope.$on('windowFocus', function(broadcastEvent, browserEvent) {
		// works when entering window
		that.reconnect();
	});

	$scope.$on('windowShow', function(broadcastEvent, browserEvent) {
		console.log("onShow");
		that.reconnect();
	});

	$scope.$on('windowBlur', function(broadcastEvent, browserEvent) {
		// works when leaving window
		that.disconnect();
	});

	$scope.$on('windowHide', function(broadcastEvent, browserEvent) {
		console.log("onHide");
		that.disconnect();
	});

	// scope functions
	$scope.repeatedRequest = function(item, cmd, frequency, switch_) {
		$log.debug("repeatedRequest invoked: " + item + ": " + cmd);

		$scope.changeState(item, cmd);
	};

	$scope.stopRepeatedRequest = function(item, cmd) {
		$log.debug("stopRepeatedRequest invoked: " + item + ": " + cmd);
	};

	$scope.setViewRoot = function(viewRoot) {
		$scope.oldViewRoot = $scope.viewRoot;
		$scope.viewRoot = viewRoot;
	};
});


