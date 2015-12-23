
angular.module('app.directives')

.directive('chart', function($timeout, $log, $rootScope) {
	return {
		template : '<div>\
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
								<div class="chart-status row col-md-12 col-xs-12">\
									<div class="chart-label"></div>\
								</div> \
							</div> \
						</div> \
						<div class="ng-chart"></div> \
					</div>',
		restrict : 'AE',
		replace : true,
		link : function postLink($scope, element, attrs, ctrl) {

			//$timeout(function(){
				$log.debug("Linking chart " + $scope.widget.id);

				var chartDiv = angular.element(".ng-chart", element);
				$scope.lineChart = new google.visualization.LineChart(chartDiv[0]);

				$scope.status = angular.element(".chart-status", element);
				$scope.statusLabel = angular.element(".chart-label", $scope.status);

				var reloadButton = angular.element(".ng-reload", element);
				$scope.reloadButton = reloadButton;

				ctrl.init($scope.widget, {});
			//}, 500);

		},
		controller: "ChartController"
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
});


angular.module('app.controllers')

.controller("ChartController", function($scope, $log, $http, $timeout, webSocket){
    $log.debug("Chart Controller constructing");

    var view = null;
    var that = this;
    var widget = null;
    var counter = 0;

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
        $log.debug("Forcing data update for " + $scope.widget.item);
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
            setTimeout(function(){
                $scope.status.hide();
            }, 1000);
        });
    };

    var updateChart = function(newValue, oldValue) {
        if (newValue !== oldValue) {
            if (widget.data) {
                //$log.debug("Drawing chart for " + $scope.item);
                var table = widget.data.table;
                var viewData = widget.data.viewData;

                if (table && viewData) {
                    //$log.debug("Data updated");
                    that.draw(table, viewData, newValue);
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

    // processes a single item for the given widget (graph) sent from websocket
    var processItemUpdate = function (item, widget){
        $log.info("Received Update for " + widget.item);
        // update chart status
        var statusLabel = $scope.statusLabel;
        var status = $scope.status;

		clearTimeout($scope.hideTimeout);
		statusLabel.html("Received update");
		status.show();

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

		updateChart(++counter);
    };

    this.init = function(widgetP) {
        $log.debug("Chart Controller initializing");
        widget = widgetP;
        if(angular.isUndefined(widget.data)) {
            widget.data = {
                viewData : {
                    begin : new Date().getTime() - widget.period,
                    end : new Date().getTime()
                },
            };
            initTable(widget);
        }

        updateData();

        webSocket.subscribe(this.processUpdate);

        $scope.reloadButton.on('mousedown', function(event) {
            updateData();
        });

    };

    this.draw = function (table, viewData, cnt) {
        $log.debug("Drawing chart for " + $scope.widget.item);

        var start = new Date().getTime();

        if(!$scope.view) {
            $scope.view = new google.visualization.DataView(table);
        }

        var height = 250;
        var chartOptions = {
            legend : {
                position : 'in',
                maxLines : 1,
                alignment : 'center'
            },
            interpolateNulls : true,
            chartArea : {
                top: 0,
                width: '100%',
                height : height
            },
            vAxis : {
                textPosition : 'in'
            },
            hAxis : {
                textPosition : 'in',
                viewWindow : {
                    min : new Date(viewData.end),
                    max : new Date(viewData.begin)
                }
            },
            lineWidth : 1,
            height : height
        };

        $scope.lineChart.draw($scope.view, chartOptions);

        $log.debug("Drawing chart for " + $scope.widget.item +" done in " + (new Date().getTime() - start) +"ms");

		if(counter == cnt) {
			$scope.status.hide();
		}
    };

    // processes a single item update sent via websocket
    this.processUpdate = function(item) {
        for(var i = 0; i < widget.items.length; i++) {
            if(widget.items[i] == item.id) {
                processItemUpdate(item, widget);
            }
        }
    };


    // returns the row index for the timestamp given as millis since 01-01-1970
    var getRowIndex = function(timestamp, table, tsToRowMap) {

        if(!angular.isUndefined(tsToRowMap[timestamp])){
            return tsToRowMap[timestamp];
        }

        var date = getDateFromTimestamp(timestamp);

        var index = table.addRow();
        table.setCell(index, 0, date);

        tsToRowMap[timestamp] = index;

        return index;
    };

    var processItemValues = function (item, table, tsToRowMap) {
        var colIndex = getColumnIndex(item, table);

        var rowAdded = false;
        var minTS = Math.max, maxTS = 0;

        for (var j = 0; j < item.values.length; j++) {
            var value = item.values[j];
            table.setCell(getRowIndex(value.timestamp, table, tsToRowMap), colIndex, parseFloat(value.value));
            rowAdded = true;
        }
        return rowAdded;
    };

    // process the chart data received from (chart) data servlet
    this.processChartData = function(data) {
        $log.debug("Processing chart data for " + $scope.widget.item);

        var table = widget.data.table;

        var tsToRowMap = {};

        for(var k=0; k < data.length; k++){
            var item = data[k];

            if (widget.period == item.period || !item.period) {
                processItemValues(item, widget.data.table, tsToRowMap);

                // the watched binding will receive an update now
                // widget.data.counter++;
            }
        }

        table.sort([ {
            column : 0,
            desc : false
        } ]);

        updateChart(++counter);
    };

    this.narrow = function(factor) {
        this.scale(factor);
    };

    this.expand = function(factor) {
        this.scale(factor);
    };

    this.scale = function(factor) {
        $scope.widget.period *= factor;
        updateData();
    };

    // update data when chart is shown
    $scope.$on('windowShow', function(broadcastEvent, browserEvent) {
        updateData();
    });

    $scope.$on('$destroy', function(){
        webSocket.unsubscribe(that.processUpdate);
    });

    $scope.scale = function(value){
        $scope.$evalAsync(function(){
			that.expand(eval(value));
		});
    };
});

