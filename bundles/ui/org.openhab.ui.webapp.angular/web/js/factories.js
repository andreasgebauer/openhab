var appModule = angular.module('app.factories', []);

appModule.factory('sitemap', function($http, $log) {
	$log.debug("Initializing Sitemap-Factory");

	var forEachChild = function(root, callback) {
		for (var int = 0; root.children && int < root.children.length; int++) {
			callback(root.children[int]);
		}
	};

	return {
		fetch: function(sitemap, widget, callback, errorCallback) {
			$log.info("Fetching sitemap '" + sitemap + "', widget: '" + widget + "'");
			$http.get(sitemapUrl, {
				params : {
					sitemap : sitemap,
					w : widget
				}
			}).success(function(data) {
				data.id = widget;
				$log.debug("Sitemap for widget " + data.id + " fetched: " + data.label);
				callback(data);
			}).error(function(e) {
				errorCallback(e);
			});
		},
		/*
		 * get the widget for the widget id starting at root
		 * will traverse all children recursively to find the widget
		 */
		getWidget: function(root, id, ignoreRoot) {
			//$log.debug("getWidget: " + id);
			if(!ignoreRoot && root.id == id) {
				return root;
			}
			for (var int = 0; root.children && int < root.children.length; int++) {
				var child = root.children[int];
				if (child.id == id) {
					return child;
				}
				if (child.children) {
					var candidate = this.getWidget(child, id, true);
					if (!angular.isUndefined(candidate)) {
						return candidate;
					}
				}
			}
		},
		// get all widgets for the item id given
		getWidgets: function(root, id) {
			//$log.debug("getWidgets: " + id);
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
		},
		pageItem: function(sitemap, widgetId){
			if(angular.isDefined(sitemap)) {
				return this.getWidget(sitemap, widgetId);
			}
		}
	};
} )

.factory("commandService", function($log, $http) {
	var commandService = {
		update : function(widget, state) {
			$log.debug("commandService: update invoked: " + widget + ": " + state);
	
			var params = {};
			params[widget] = state;
	
			$http.get(commandUrl, {
				params : params
			}).success(function(data) {
				$log.debug("commandService: received: " + data);
			}).error(function() {
				$log.error("Command was not successful");
			});	
		}};
	return commandService;
})

.factory("transformService", function($log, $http){
	return {
		transform : function(transformType, transformParam, formattedValue) {
			return $http.get(transformUrl + transformType + "/" + transformParam + "/" + formattedValue);
		}
	};
})

.factory("iconService", function($log, $http, $q) {
	var getState = function(w) {
		return w;
	};
	var iconExists = function(filename) {
		return $http.get(imageUrl + filename + ".png");
	};
	return {
		getIcon : function(w, value) {
			return $q(function(resolve, reject){

				$log.debug("iconService: getIcon invoked: " + w.type + ": " + value);

				var icon = w.type;
				if(w.icon!=null && w.icon != "none") {
					icon = w.icon;
				} else {
					var itemName = w.item;
					//if(itemName!=null) {
					//	iconExists(itemName).success(function(){
					//		resolve(icon);
					//	});
					//}
				}

				$log.debug("iconService: icon: " + icon);

				var resOrErr = function (icon, err) {
					iconExists(icon)
					.then(function(){
						$log.debug("iconService: resolving because of success");
						resolve(icon);
					},err);				
				};

				var otherwise = function(icon){
					resOrErr(icon, function(){
						if(icon.indexOf("-") < 0) {
							$log.debug("iconService: resolving because no '-' found");
							resolve(icon);
						}
						iconExists(icon.substring(0, icon.indexOf("-")))
						.then(function(data) {
							resolve(icon);
							$log.debug("iconService: resolving because of success with substring " + JSON.stringify(data));
						}, function() {
							resolve("none");
						});
					});
				}


				// now add the state, if the string does not already contain a state
				// information
				if(icon.indexOf("-") < 0) {
					if(w.type == "percent") {
						// we do a special treatment for percent types; we try to find the icon of the biggest value
						// that is still smaller or equal to the current state. 
						// Example: if there are icons *-0.png, *-50.png and *-100.png, we choose *-0.png, if the state
						// is 40, and *-50.png, if the state is 70.
						var iconState = value;
						var testIcon = function (){
							test = icon + "-" + (iconState--);

							resOrErr(test, function() {
								 if(iconState>=0){
									testIcon();
								 } else {
									// TODO
									$log.debug("iconService: no icon found by state");
								 }
							});
						};
						testIcon();
					} else {
						// for all other types, just add the string representation of the state
						icon += "-" + value;
						otherwise(icon);
					}
				} else {
					otherwise(icon);
				}
			});
		}
	};
})


.factory("widgetDataProcessor", function($log, formatter, sitemap) {
	var processor = {};

	processor.process = function(widget) {
		$log.debug("process: " + widget.id + ": " + (widget.item ? widget.item : '<no item>')  + " (" + widget.type + ")");

		if(angular.isUndefined(widget.originalIcon)){
			widget.originalIcon = widget.icon;
		}
		widget.icon = imageUrl + sitemap.name +"/" + widget.id + "?state=" + encodeURIComponent(widget.value);

		if(angular.isDefined(widget.type) && widget.type != "frame") {
			//$log.debug("Formatter: " + widget.item);
			//if(angular.isUndefined(widget.formattedValue)) {
				formatter.formatValue(widget, widget);
			//} else {
			//	$log.warn("Formatter: formatted value already defined: " + widget.formattedValue);
			//}
		}
	};

	return processor;
})

.factory("updateService", function($log, $timeout, webSocket, formatter, parser, sitemap) {

	var update = {};


	var styleUpdate = function (widget, element) {
		if(element != undefined){

			var valueElement = element.find(".value");

			if(valueElement.length == 0){
				console.warn("value element not found");
				return;
			}

			valueElement.addClass("red");

			setTimeout(function() {
				valueElement.addClass("inherit");
				setTimeout(function() {
					valueElement.removeClass("red inherit");
				}, 1000);
			}, 1000);

			return;
		}

		console.warn("old fashioned style update for " + widget.item);

		widget.styleClass = "red";
		$timeout(function() {
			widget.styleClass = "inherit";
			$timeout(function() {
				widget.styleClass = "";
			}, 1000);
		}, 1000);	
	};

	update.attach = function(scope, element, callback){
			var widget = scope.widget;

			var cbFacade = function(data){
				if(data.id == widget.item) {

					console.debug("Received update " + JSON.stringify(data) + " for " + widget.item);

					styleUpdate(widget, element);
						
					scope.$apply(function() {

						// update the widget value
						widget.value = parser.parseValue(data);
						formatter.formatValue(widget, data);

						widget.icon = imageUrl + sitemap.name + "/" + widget.id + "?state=" + encodeURIComponent(widget.value);

						// and finally call the callback
						if(callback){
							callback(data);
						}
					})
				}
			};

			webSocket.subscribe(cbFacade);

			scope.$on("$destroy", function(){
				webSocket.unsubscribe(cbFacade);
			});
		};	

	return update;
})


.factory("widgetLinker", function(updateService, widgetDataProcessor){
	var linker = {};
	linker.link = function ( scope, element, attributes ) {
		updateService.attach(scope, element);
		widgetDataProcessor.process(scope);
	}
	return linker;
})

;