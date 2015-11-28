'use strict';

var directives = angular.module('app.directives', ['app.controllers', 'window-events']);

directives.directive('chart', function($timeout, $log, $rootScope) {
	return {
		template : '<div> \
						<div> \
							<div class="row" style="position: relative;"> \
								<div class="col-md-6 col-xs-8"> \
									<button scale="2">---</button> \
									<button scale="1.5">--</button> \
									<button scale="1.25">-</button> \
									<button scale="0.8">+</button> \
									<button scale="0.66666666666">++</button> \
									<button scale="0.5">+++</button> \
								</div> \
								<div class="col-md-6 col-xs-4" style="text-align:right"> \
									<button class="ng-reload">reload</button>\
								</div> \
								<div class="ng-chart-status row col-md-12 col-xs-12" style="text-align: center;position: absolute;left: 15px;right: 15px;top: 0;bottom: 0; display: none;">\
									<div class="chart-label" style="position: absolute;left: 10px;right: 10px;bottom: 0px; top: 0px; opacity: 0.5;background-color: white; padding: 2pt;"></div>\
								</div> \
							</div> \
						</div> \
						<div class="ng-chart"></div> \
					</div>',
		scope : {
			item : '@',
			id : '@',
			widget: '='
		},
		restrict : 'E',
		replace : true,
		transclude: true,
		link : function postLink($scope, element, attrs, ctrl) {
			$log.debug("Linking chart " + $scope.id);
		
			var chartDiv = angular.element(".ng-chart", element);
			$scope.lineChart = new google.visualization.LineChart(chartDiv[0]);
			
			$scope.status = angular.element(".ng-chart-status", element);
			$scope.statusLabel = angular.element(".chart-label", $scope.status);
			
			var reloadButton = angular.element(".ng-reload", element);
			$scope.reloadButton = reloadButton;

			//debugger;
			//$scope.$evalAsync(function(){
			ctrl.init($scope.widget, {});
			//});

		},
		controller: function($scope, $log, $http, $timeout, webSocket){
			$log.debug("Chart Controller initializing");

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
				}
			};

			var updateData = function(timespan) {
				$log.debug("Forcing data update for " + $scope.item);
				$scope.statusLabel.html("Updating data");
				$scope.status.show();

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

				// delete all rows
				widget.data.table.removeRows(0, widget.data.table.getNumberOfRows() - 1);

				$http.post(chartDataUrl, pendingChartData)
				.success(function(data) {
					$scope.statusLabel.html("Data received");
					that.processChartData(data);
				})
				.error(function() {
					$log.error("Command was not successful for " + $scope.item);
					$scope.statusLabel.html("error");
					$timeout(function(){
						$scope.status.hide();
					}, 1000);
				});
			};

			var updateChart = function(newValue, oldValue) {
				if (newValue !== oldValue) {
					if (widget.data) {
						//$log.debug("Drawing chart for " + $scope.item);
						var data = widget.data.table;
						var viewData = widget.data.viewData;
						//debugger;

						if (data && viewData) {
							//$log.debug("Data updated");
							$timeout(function() {
								that.draw(data, viewData);
							});
						}
					}
				}
			};

			// returns the column index for the item given
			var getColumnIndex = function(item, table) {
				var colIndex = table.getColumnIndex(item.id);
				if (colIndex == -1) {
					colIndex = table.addColumn('number', item.id, item.id);
				}
				return colIndex;
			};

			// returns a date instance for the timestamp given as millis since 01-01-1970
			var getDateFromTimestamp = function(timestamp){
				return (timestamp ? new Date(timestamp) : new Date());
			};
			
			// returns the row index for the timestamp given as millis since 01-01-1970
			var getRowIndex = function(timestamp, table) {
				var date = getDateFromTimestamp(timestamp);

				var index = table.addRow();
				table.setCell(index, 0, date);
				
				return index;
			};
			

			var processItemValues = function (item, table) {
				var colIndex = getColumnIndex(item, table);

				var rowAdded = false;
				var minTS = Math.max, maxTS = 0;

				for (var j = 0; j < item.values.length; j++) {
					var value = item.values[j];
					
					table.setCell(getRowIndex(value.timestamp, table), colIndex, parseFloat(value.value));
					rowAdded = true;
				}
				return rowAdded;
			};
			
			var processItemUpdate = function (item, widget){
				$log.info("Received Update for " + $scope.item);
				// update chart status
				var statusLabel = $scope.statusLabel;
				var status = $scope.status;
				$scope.$evalAsync(function(){
					statusLabel.html("Received update");
					status.show();
				});

				var table = widget.data.table;
				var colIndex = getColumnIndex(item, table);

				var val = parseFloat(item.value);
				var date = getDateFromTimestamp(item.timestamp);

				var index = table.addRow();
				table.setCell(index, 0, date);
				table.setCell(index, colIndex, val);

				// "scroll" forward
				if (item.timestamp > widget.data.viewData.end) {
					widget.data.viewData.end = item.timestamp;
					widget.data.viewData.begin = item.timestamp - widget.period;
				}

				widget.data.counter++;
			};

			this.init = function(widgetP) {
				widget = widgetP;
				if(angular.isUndefined(widget.data)) {
					widget.data = {
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

				$scope.reloadButton.on('mousedown', function(event) {
					updateData();
				});

			};

			this.draw = function (table, viewData) {
				$log.debug("Drawing chart for " + $scope.item);
				
				var start = new Date().getTime();

				if(!$scope.view) {
					$scope.view = new google.visualization.DataView(table);
				}

				var max = new Date(viewData.end);
				var min = new Date(viewData.begin);

				var height = 300;
				var chartOptions = {
					legend : {
						position : 'bottom',
						maxLines : 1,
						alignment : 'center'
					},
					title : viewData.label,
					interpolateNulls : true,
					chartArea : {
						width : '100%',
						height : height//-40
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

				$scope.lineChart.draw($scope.view, chartOptions);
				//$scope.lineChart.draw();

				var took = new Date().getTime() - start;
				$log.debug("Drawing chart for " + $scope.item +" done in " + took +"ms");

				$scope.$evalAsync(function(){
					$scope.statusLabel.html("Chart drawn");
					$timeout(function(){
						$scope.status.hide();
					}, 200);
				});
			};
			
			this.processUpdate = function(item) {
				for(var i = 0; i < widget.items.length; i++) {
					if(widget.items[i] == item.id) {
						processItemUpdate(item, widget);
					}
				}
			};
			
			this.processChartData = function(data) {
				$log.debug("Processing chart data for " + $scope.item);

				var table = widget.data.table;
				
				for(var k=0; k < data.length; k++){
					var item = data[k];

					if (widget.period == item.period || !item.period) {
						if (processItemValues(item, widget.data.table)) {
							//table.sort([ {
							//	column : 0,
							//	desc : false
							//} ]);
						}

						// the watched binding will receive an update now
						widget.data.counter++;
					}
				}
			};

			this.narrow = function(factor) {
				this.scale(factor);
			};

			this.expand = function(factor) {
				this.scale(factor);
			};
			
			this.scale = function(factor) {
				widget.period *= factor;
				updateData();
			};
		
			// update data when chart is shown
			$scope.$on('windowShow', function(broadcastEvent, browserEvent) {
				updateData();
			});

			$scope.$on('$destroy', function(){
				webSocket.unsubscribe(that.processUpdate);
			});
		}
	};
})


.directive('scale', function($log) {
	return {
		template:"<button ng-transclude></button>",
		scope : {
			scale : '@',
		},
		require: '^chart',
		restrict : 'A',
		transclude: true,
		replace: true,
		link : function postLink($scope, element, attrs, chartCtrl) {
			element.on('mousedown', function(event) {
				// Prevent default dragging of selected content
				//event.preventDefault();
				$scope.$evalAsync(function(){
					chartCtrl.expand(eval(attrs.scale));
				});
			});
		}
	};
})
		


;