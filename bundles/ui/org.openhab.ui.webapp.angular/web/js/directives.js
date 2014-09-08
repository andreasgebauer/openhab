'use strict';

var app = angular.module('app');

app.directive('chart', function($interval, $log) {
	return {
		template : '<div></div>',
		scope : {
			chart : '=',
			id : '@'
		},
		restrict : 'E',
		replace : true,
		link : function postLink($scope, element, attrs) {
			var lineChart = new google.visualization.LineChart(element[0]);
			var view = null;

			var widgetScope = $scope.$parent.$parent;
			$scope.widget = widgetScope.widget;
			
			function draw(table, viewData) {

				if (!view) {
					view = new google.visualization.DataView(table);
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

				lineChart.draw(view, chartOptions);
			}
			
			var update = function(newValue, oldValue) {
				if (newValue !== oldValue) {
					if ($scope.widget.data) {
						var data = $scope.widget.data.table;
						var viewData = $scope.widget.data.viewData;

						if (data && viewData) {
							//$log.debug("Data updated");
							draw(data, viewData);
						}
					}
				}
			};
			
			var stop;
			$scope.draw = function() {
				if ( angular.isDefined(stop) ) return;

				var interval = $scope.widget.period / 800;
			  	stop = $interval(function() {
				  	var widget = $scope.widget;
				  
					if (widget.data) {
						var data = widget.data.table;
						
						var collected = [];
						var notCollected = data.getNumberOfColumns() - 1;
						for(var row = data.getNumberOfRows() -1; row >= 0 && notCollected > 0; row--){
							for(var col=1; col < data.getNumberOfColumns(); col++){
								if(!collected[col]){
									var val = data.getValue(row, col);
									if(val != null && !isNaN(val)){
										collected[col] = val;
										notCollected--;
									}
								}								
							}
						}
						
						for(var i = 0; i < widget.items.length; i++){
							var itemName = widget.items[i];
							var value = collected[data.getColumnIndex(itemName)];
							var update = {
								period: widget.period,
								id: itemName,
								values: [
									{value: value}
								]
							};
							// fake update to keep the chart scrolling
							widget.processUpdate(update);
						}
					}
			  }, interval);
			};
			
			$scope.stopDraw = function() {
			  if (angular.isDefined(stop)) {
				$interval.cancel(stop);
				stop = undefined;
			  }
			};

			$scope.$on('$destroy', function() {
			  // Make sure that the interval is destroyed too
			  $scope.stopDraw();
			});
			
			$scope.draw();

			widgetScope.$watch(function() {
				if ($scope.widget.data) {
					return $scope.widget.data.counter;
				}
			}, update, false);
		}
	};

});


app.directive('smartchart', function($interval, dateFilter, $log) {
	return {
		template : '<div></div>',
		scope : {
			chart : '=',
			id : '@'
		},
		restrict : 'E',
		replace : true,
		link : function postLink($scope, element, attrs) {
			
			var lineChart = new google.visualization.LineChart(element[0]);
			var view = null;

			var widgetScope = $scope.$parent.$parent.$parent;
			$scope.widget = widgetScope.widget;
			
			function draw(table, viewData) {

				if (!view) {
					view = new google.visualization.DataView(table);
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

				lineChart.draw(view, chartOptions);
			}
			
			var update = function(newValue, oldValue) {
				if (newValue !== oldValue) {
					$log.debug("redraw")
					if ($scope.widget.data) {
						var data = $scope.widget.data.table;
						var viewData = $scope.widget.data.viewData;

						if (data && viewData) {
							//$log.debug("Data updated");
							draw(data, viewData);
						}
					}
				}
			};
			
			var stop;
			$scope.draw = function() {
				if ( angular.isDefined(stop) ) return;

				var interval = $scope.widget.period / 800;
			  	stop = $interval(function() {
				  	var widget = $scope.widget;
				  
					if (widget.data) {
						var data = widget.data.table;
						
						var collected = [];
						var notCollected = data.getNumberOfColumns() - 1;
						for(var row = data.getNumberOfRows() -1; row >= 0 && notCollected > 0; row--){
							for(var col=1; col < data.getNumberOfColumns(); col++){
								if(!collected[col]){
									var val = data.getValue(row, col);
									if(val != null && !isNaN(val)){
										collected[col] = val;
										notCollected--;
									}
								}								
							}
						}
						
						for(var i = 0; i < widget.items.length; i++){
							var itemName = widget.items[i];
							var value = collected[data.getColumnIndex(itemName)];
							var update = {
								period: widget.period,
								id: itemName,
								values: [
									{value: value}
								]
							};
							// fake update to keep the chart scrolling
							widget.processUpdate(update);
						}
					}
			  }, interval);
			};
			
			$scope.stopDraw = function() {
			  if (angular.isDefined(stop)) {
				$interval.cancel(stop);
				stop = undefined;
			  }
			};

			$scope.$on('$destroy', function() {
			  // Make sure that the interval is destroyed too
			  $scope.stopDraw();
			});
			
			$scope.draw();

			widgetScope.$watch(function() {
				if ($scope.widget.data) {
					return $scope.widget.data.counter;
				}
			}, update, false);

			
			
		}
	};
});