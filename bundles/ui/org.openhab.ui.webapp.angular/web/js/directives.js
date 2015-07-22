'use strict';

var app = angular.module('app.directives', ['app.controllers']);

app.directive('chart', function($interval, $log, $rootScope) {
	return {
		template : '<div><div ng-transclude></div><div class="ng-chart"></div></div>',
		scope : {
			widgetData: '@',
			item : '@',
			id : '@',
			widget: '='
		},
		restrict : 'E',
		replace : true,
		transclude: true,
		link : function postLink($scope, element, attrs, ctrl) {
			//$scope.widget = $scope.$parent.$parent.widget;
			console.log($scope.widget);

			//$rootScope.getWidgets($scope.widget.item);

			var chartDiv = angular.element(".ng-chart", element);
			$scope.lineChart = new google.visualization.LineChart(chartDiv[0]);

			ctrl.init($scope.widget, {});


		},
		controller: function($scope, $log, $http, $interval, webSocket){
			console.log("Chart Controller initializing");

			var view = null;
			var that = this;
			var widget = null;

			var initTable = function (widget, forceNew) {
				
				if(forceNew || widget.data.table == null) {
					widget.data.table = new google.visualization.DataTable();
					widget.data.table.addColumn('datetime', "Datum", "Datum");

					widget.items.sort();
					var now = new Date().getTime();

					var begin = now - widget.period;
					var end = now;


					angular.forEach(widget.items, function(el){
						widget.data.table.addColumn('number', el, el);
					});

					widget.data.timestampToRowIndex = {};
				}
			};

			var updateData = function(timespan) {
				console.log("updating data");
				var now = new Date().getTime();
				var ts = timespan || {
					begin : now - widget.period,
					end : now
				};
				widget.data.viewData.begin = ts.begin;
				widget.data.viewData.end = ts.end;

				var pendingChartData = {};
				for (var i = 0; i < widget.items.length; i++) {
					var itemName = widget.items[i];
					if (!pendingChartData[itemName]) {
						pendingChartData[itemName] = new Array;
					}
					pendingChartData[itemName].push(ts);
				}

				$http.post(chartDataUrl, pendingChartData)
				.success(function(data) {
					that.processChartData(data);
				})
				.error(function() {
					$log.error("Command was not successful");
				});
			};

			var updateChart = function(newValue, oldValue) {
				if (newValue !== oldValue) {
					if (widget.data) {
						console.log("Updating chart");
						var data = widget.data.table;
						var viewData = widget.data.viewData;

						if (data && viewData) {
							//$log.debug("Data updated");
							that.draw(data, viewData);
						}
					}
				}
			};

			var getColumnIndex = function(item, table) {
				var colIndex = table.getColumnIndex(item.id);
				if (colIndex == -1) {
					colIndex = table.addColumn('number', item.id, item.id);
				}
				return colIndex;
			};
			
			var getRowIndex = function(value, widget) {
				var table = widget.data.table;
				var timestamp = (value.timestamp ? new Date(value.timestamp) : new Date());
				if (timestamp > widget.data.viewData.end) {
					widget.data.viewData.end = timestamp.getTime();
				}
				
				var index = widget.data.timestampToRowIndex[timestamp.getTime()];
				if (angular.isUndefined(index)) {
					index = table.addRow();
					// update the lookup table
					widget.data.timestampToRowIndex[timestamp] = index;
					table.setCell(index, 0, timestamp);
				}
				return index;
			};
			
			var processItemValues = function (item, widget) {
				var table = widget.data.table;
				
				var colIndex = getColumnIndex(item,table);

				var rowAdded = false;
				var minTS = Math.max, maxTS = 0;

				for (var j = 0; j < item.values.length; j++) {
					var value = item.values[j];
					
					var timestamp = (value.timestamp ? new Date(value.timestamp) : new Date());
					var val = parseFloat(value.value);
					
					var index = widget.data.timestampToRowIndex[timestamp.getTime()];
					if (angular.isUndefined(index)) {
						index = table.addRow();
						table.setCell(index, 0, timestamp);
						rowAdded = true;
					}
					
					table.setCell(index, colIndex, val);
				}
				return rowAdded;
			};
			
			var processItemUpdate = function (item, widget){
				var table = widget.data.table;
				var colIndex = getColumnIndex(item, table);

				var val = parseFloat(item.value);
				var index = getRowIndex(item, widget);

				table.setCell(index, colIndex, val);

				var timespan = widget.data.viewData.end - widget.data.viewData.begin;
				if (item.timestamp > widget.data.viewData.end) {
					widget.data.viewData.end = item.timestamp;
					widget.data.viewData.begin += timespan;
				}

				widget.data.counter++;
			};

			this.init = function(widgetP) {
				widget = widgetP;
				if(angular.isUndefined(widget.data)) {
					widget.data = {
						timestampToRowIndex : {},
						counter : 0,
						viewData : {
							begin : new Date().getTime() - widget.period,
							end : new Date().getTime(),
							max : widget.period,
							id : widget.id,
							item : widget.item,
							label : widget.label
						},
					};
					initTable(widget);
					updateData();

				} else {
					updateChart(true);
				}

				webSocket.subscribe(this.processUpdate);

				$scope.$watch(function() {
					if (widget.data) {
						return widget.data.counter;
					}
				}, updateChart, false);

			};

			this.draw = function (table, viewData) {
				console.log("Drawing chart");
				var start = new Date().getTime();

				view = new google.visualization.DataView(table);

				var max = new Date(viewData.end);
				var min = new Date(viewData.begin);

				var height = 300;
				var chartOptions = {
					legend : {
						position : 'bottom',
						maxLines : 1,
						alignment : 'center'
					},
					//title : viewData.label,
					interpolateNulls : true,
					chartArea : {
						width : '93%',
						height : height-40
					},
					vAxis : {
						textPosition : 'in'
					},
					hAxis : {
						textPosition : 'in',
						viewWindow : {
							min : min,
							max : max
						}
					},
					lineWidth : 1,
					height : height
				};

				$scope.lineChart.draw(view, chartOptions);
				//$scope.lineChart.draw();

				var took = new Date().getTime() - start;
				console.log("Drawing chart done in " + took +"ms");

			};
			
			this.processUpdate = function(item) {
				for(var i = 0; i < widget.items.length; i++) {
					if(widget.items[i] == item.id) {
						processItemUpdate(item, widget);
					}
				}
			};
			
			this.processChartData = function(data) {
				//initTable(widget);
				console.log("Processing chart data");

				var table = widget.data.table;
				
				// delete all rows
				widget.data.table.removeRows(0, table.getNumberOfRows() - 1);
				widget.data.timestampToRowIndex = {};

				for(var k=0; k < data.length; k++){
					var item = data[k];

					if (widget.period == item.period || !item.period) {
						if (processItemValues(item, widget)) {
							table.sort([ {
								column : 0,
								desc : false
							} ]);

							for (var i = 0; i < table.getNumberOfRows(); i++) {
								widget.data.timestampToRowIndex[table.getValue(i, 0).getTime()] = i;
							}
						}

						// the watched binding will receive an update now
						widget.data.counter++;
					}
				}
			};

			this.narrow = function(value) {
				var begin = $scope.widget.data.viewData.begin;
				var end = $scope.widget.data.viewData.end;
				var timespan = end - begin;
				var decrease = value * timespan / 100;

				updateData({begin: begin + decrease, end: end});
			};

			this.expand = function(value) {
				var timespan = $scope.widget.data.viewData.end - $scope.widget.data.viewData.begin;
				var increase = value * timespan / 100;

				updateData({begin: $scope.widget.data.viewData.begin - increase, end: $scope.widget.data.viewData.end});
			};
		
		}
	};
})


.directive('scale', function($interval, $log) {
	return {
		template:"<button ng-transclude></button>",
		scope : {
			narrow : '@',
			expand : '@'
		},
		require: '^chart',
		restrict : 'A',
		transclude: true,
		replace: true,
		link : function postLink($scope, element, attrs, chartCtrl) {
			element.on('mousedown', function(event) {
				// Prevent default dragging of selected content
				//event.preventDefault();

				if(attrs.expand) {
					chartCtrl.expand(eval(attrs.expand));
				} else if(attrs.narrow) {
					chartCtrl.narrow(eval(attrs.narrow));
				}
			});
		}
	};
})
		


;