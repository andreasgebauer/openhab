var appModule = angular.module('app');

appModule.factory('sitemap', function($http, $log, webSocket) {
	return {
		fetching : false,
		location : undefined,
		lastLocation : undefined,
		fetch : function(sitemap, widget, callback) {
			fetching = true;
			lastLocation = location;
			$log.info("Fetching sitemap '" + sitemap + "', widget:" + widget);
			$http.get(url, {
				params : {
					sitemap : sitemap,
					w : widget
				}
				
			}).success(function(data) {
				var pendingChartData = {};
				process = function(element, pendingChartData) {
					//$log.debug("Processing element " + JSON.stringify(element));
					if (element.children) {
						for (var int = 0; int < element.children.length; int++) {
							var child = element.children[int];
							process(child, pendingChartData);
						}
					}

					element.processUpdate = function(item) {
						// nothing to do; default implementation
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
								if(typeof widget.value === "string") {
									widget.icon = iconPrefix + "-" + widget.value.toLowerCase();
								} else if(typeof value === "string") {
									widget.icon = iconPrefix + "-" + value.toLowerCase();
								}
							}
							
						}
					};
					
					if(element.type === "chart"){
						if(element.items){
							//element.data = { counter: 0 };
							for(var i = 0; i < element.items.length; i++){
								var item = element.items[i];
								if(!pendingChartData[item]){
									pendingChartData[item] = new Array;
								}
								pendingChartData[item].push(element.period);
							}
						}
						element.processUpdate = function(item) {
							var widget = this;
							
							if (widget.period == item.period || !item.period) {
								if (!widget.data) {
									widget.data = {
										timestampToRowIndex: {},
										counter : 0,
										viewData: {
											max : widget.period,
											id : widget.id,
											item : widget.item,
											label : widget.label
										}
									};
								}

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
										widget.data.timestampToRowIndex[timestamp.getTime()] = index;
									}

									table.setCell(index, colIndex, val);
								}

								table.sort([{column: 0, desc: false}]);

								widget.data.table = table;

								// the watched binding will receive an update now
								//$log.debug(widget.data.counter++);
								widget.data.counter++;
							}
						};
					}
				};

				process(data, pendingChartData);

				webSocket.send(pendingChartData);
				
				callback(data);
				fetching = false;
			}).error(function() {
				alert("Unable to fetch sitemap");
				fetching = false;
			});
		}
	};
} );