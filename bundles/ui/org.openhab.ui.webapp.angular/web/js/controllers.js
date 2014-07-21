var url = "/angular/sitemap/";
var commandUrl = "/angular/cmd/";

var appControllers = angular.module('appControllers', ['windowEventBroadcasts', 'ngAnimate']);


	// process an item being shown
	var process = function(element, pendingChartData) {
		//$log.debug("Processing element " + JSON.stringify(element));
		if (element.children) {
			for (var int = 0; int < element.children.length; int++) {
				var child = element.children[int];
				// don't follow links
				if(!/_link$/.test(child.type)){
					process(child, pendingChartData);
				}
			}
		}

		// add function to process update data (from websocket)
		element.processUpdate = function(item) {
			//$log.debug("processing update for " + item.id);
			
			var widget = this;
			var value = item.values[0].value;
			var iconPrefix = item.icon != "none" ? item.icon : widget.type;

			if(item.label.indexOf('[') != -1){
				widget.label = item.label.substring(0, item.label.indexOf('['));
				widget.value = item.label.substring(item.label.indexOf('[') +  1, item.label.indexOf(']'));
			}

			if (widget.type === "text" || widget.type === "text_link") {
				// or simply widget.value = value.toLowerCase();
				widget.icon = item.icon;
			} else {
				if(widget.type === "switch"){
					if(value == "ON"){
						widget.value = true;
					} else {
						widget.value = false;
					}

				} else if(widget.type === "slider"){
					// try to find an image with xhr

					widget.value = value;
					widget.icon = widget.icon.substring(0, widget.icon.indexOf('-') +  1) + value.toLowerCase();

					widget.icon = iconPrefix + "-" + widget.value.toLowerCase();

				} else {
					$log.info("Unhandled data for: " + JSON.stringify(widget));
				}

				if(!angular.isUndefined(widget.value)) {
					if(iconPrefix.indexOf("-") == -1) {
						if(typeof widget.value === "string") {
							widget.icon = iconPrefix + "-" + widget.value.toLowerCase();
						} else if(typeof value === "string") {
							widget.icon = iconPrefix + "-" + value.toLowerCase();
						}
					}else{
						widget.icon = iconPrefix;
					}
				}

			}
		};

		if(element.type === "chart" || element.type === "smartchart"){
			if(element.items){
				//element.data = { counter: 0 };
				for(var i = 0; i < element.items.length; i++){
					var item = element.items[i];
					if(!pendingChartData[item]){
						pendingChartData[item] = new Array;
					}
					var now = new Date().getTime();
					pendingChartData[item].push(
					{
						begin: now - element.period,
						end: now
					});
				}
			}
			
			if (!element.data) {
				element.data = {
					timestampToRowIndex: {},
					counter : 0,
					viewData: {
						begin : new Date().getTime() - element.period,
						end : new Date().getTime(),
						max : element.period,
						id : element.id,
						item : element.item,
						label : element.label
					}
				};
			}

			
			element.processUpdate = function(item) {
				var widget = this;

				if (widget.period == item.period || !item.period) {

					//$log.debug("Update for " + item.id);

					var table;
					if(widget.data.table){
						//table = new google.visualization.DataTable(widget.data.table.toJSON());
						table = widget.data.table;
					}else{
						table = widget.data.table = new google.visualization.DataTable();
						if (table.getColumnIndex("Datum") == -1) {
							table.addColumn('datetime', "Datum", "Datum");

							widget.items.sort();
							for(var i = 0; i < widget.items.length; i++){
								var itemName = widget.items[i];
								table.addColumn('number', itemName, itemName);
							}
						}
					}

					var colIndex = table.getColumnIndex(item.id);
					if (colIndex == -1) {
						colIndex = table.addColumn('number', item.id, item.id);
					}

					var rowAdded = false;
					for(var j = 0; j< item.values.length; j++){
						var value = item.values[j];

						var timestamp = (value.timestamp ? new Date(value.timestamp) : new Date());
						var val = parseFloat(value.value);


						var existingRowIndex = widget.data.timestampToRowIndex[timestamp.getTime()];

						var index;
						if(existingRowIndex != undefined){
							index = existingRowIndex;
						}else{
							index = table.addRow();
							table.setCell(index, 0, timestamp);
							rowAdded = true;
						}

						table.setCell(index, colIndex, val);
					}

					if(rowAdded){
						table.sort([{column: 0, desc: false}]);
						
						for(var i=0; i<table.getNumberOfRows(); i++) {
							widget.data.timestampToRowIndex[table.getValue(i, 0).getTime()] = i;
						}
						
					}

					widget.data.table = table;

					// the watched binding will receive an update now
					//$log.debug(widget.data.counter++);
					widget.data.counter++;
				}
			};
		}
	};


appControllers.controller('HomeController', function($scope, sitemap, $log, $location, $http, webSocket, $interval) {

	$scope.show = false;


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

		var setupModel = function(data, widgetId) {
			$log.debug("Sitemap loaded: " + data.label);
			data.id = widgetId;

			var oldSitemap = $scope.sitemap;
			if(!angular.isUndefined(oldSitemap)) {
				// merge the two sitemap partials if possible
				
				var getWidget = function(root, id) {
					for (var int = 0; root.children && int < root.children.length; int++) {
						var child = root.children[int];
						if(child.id == id){
							return child;
						}
						if(child.children) {
							var candidate = getWidget(child, id);
							if(!angular.isUndefined(candidate)) {
								return candidate;
							}
						}
					}
				}
				
				var widget = getWidget(oldSitemap, data.id);
				if(angular.isUndefined(widget)){
					widget = getWidget(data, oldSitemap.id);
					if(!angular.isUndefined(widget)){
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
			$scope.show = true;

			$scope.viewItem = sitemap.pageItem($scope.sitemap, widgetId);
			if(angular.isUndefined($scope.viewItem)) {
				$scope.viewItem = $scope.sitemap;
			}

			var pendingChartData = {};

			process($scope.viewItem, pendingChartData);

			webSocket.send(pendingChartData);

		};

		if (angular.isUndefined($scope.sitemap)) {
			sitemap.fetch("default", widgetId, setupModel);
		} else {
			$scope.viewItem.selected = false;
			$scope.viewItem = sitemap.pageItem($scope.sitemap, widgetId);

			if(!angular.isUndefined($scope.nav)) {
				$scope.nav.back.visible = sitemap.location !== "";
				//$scope.nav.back.hef = data.parentId ? "#" + data.parentId : "#/";

				$scope.nav.home.visible = sitemap.location !== "";
				if(!angular.isUndefined($scope.viewItem)) {
					$scope.nav.title.text = $scope.viewItem.label;
				}
			}
			
			if(angular.isUndefined($scope.viewItem) || angular.isUndefined($scope.viewItem.children)) {
				// we need to load the sitemap data for the page shown
				sitemap.fetch("default", widgetId, setupModel);
			} else {
				var pendingChartData = {};

				process($scope.viewItem, pendingChartData);

				webSocket.send(pendingChartData);
			}
		}
	});


	var getWidgets = function(root, id) {
		if(angular.isUndefined(root)) {
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
			var widgets = getWidgets($scope.sitemap, item.id);
			if (!widgets || widgets.length == 0 || !item.values) {
				return;
			}

			for (var int = 0; int < widgets.length; int++) {
				var widget = widgets[int];
				if(!angular.isUndefined(widget.processUpdate)) {
					widget.processUpdate(item);
				}
			}
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
	
	// sets the location for the widget given
	$scope.load = function(widget) {
		widget.selected = true;
		$scope.viewItem = widget;
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


appControllers.controller('ChartCtrl', function($scope, $log, $http, webSocket, $interval) {
	$scope.initDate = new Date('2016-15-20');
	$scope.formats = ['dd-MMMM-yyyy', 'yyyy/MM/dd', 'dd.MM.yyyy', 'shortDate'];
	$scope.format = $scope.formats[0];

	$scope.widget = $scope.$parent.$parent.widget;

  // Disable weekend selection
  $scope.disabled = function(date, mode) {
    return ( mode === 'day' && ( date.getDay() === 0 || date.getDay() === 6 ) );
  };

  $scope.toggleMin = function() {
    $scope.minDate = $scope.minDate ? null : new Date();
  };
  $scope.toggleMin();


  $scope.dateOptions = {
    formatYear: 'yy',
    startingDay: 1
  };

	$scope.open = function($event) {
		$event.preventDefault();
		$event.stopPropagation();
		$scope.opened = true;
	};
	
	var updateWidget = function(newBegin){
		var pendingChartData = {};
		if(newBegin < $scope.widget.data.viewData.begin) {
			angular.forEach($scope.widget.items, function(key){
				pendingChartData[key] = new Array;
				pendingChartData[key].push({
						begin: newBegin,
						end: $scope.widget.data.viewData.begin -1
				});
			});
		} else {
			// force update
			$scope.widget.data.counter++;
		}
		$scope.widget.data.viewData.begin = newBegin;
		
		//process($scope.widget, pendingChartData);
		webSocket.send(pendingChartData);
	}
	
	$scope.narrow = function(value) {
		var timespan = $scope.widget.data.viewData.end - $scope.widget.data.viewData.begin;
		var decrease = value * timespan / 100;
		
		updateWidget($scope.widget.data.viewData.begin + decrease);
	};

	$scope.expand = function(value) {
		var timespan = $scope.widget.data.viewData.end - $scope.widget.data.viewData.begin;
		var increase = value * timespan / 100;

		updateWidget($scope.widget.data.viewData.begin - increase);
	};

});

