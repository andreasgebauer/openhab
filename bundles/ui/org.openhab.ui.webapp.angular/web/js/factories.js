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
		// get the widget for the widget id given
		getWidget: function(root, id) {
			//$log.debug("getWidget: " + id);
			for (var int = 0; root.children && int < root.children.length; int++) {
				var child = root.children[int];
				if (child.id == id) {
					return child;
				}
				if (child.children) {
					var candidate = this.getWidget(child, id);
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
			// returns the widget for the widget id
			var getWidget = function(root, widgetId) {
				if(root.id == widgetId) {
					return root;
				}
				if(angular.isDefined(root.children)) {
					for(var i=0; i<root.children.length; i++){
						var child = root.children[i];
						if(child.id == widgetId){
							return child;
						} else {
							var candidate = getWidget(child, widgetId);
							if(candidate != null){
								return candidate;
							}
						}
					}
				}
			}
			
			if(angular.isDefined(sitemap)) {
				return getWidget(sitemap, widgetId);
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
					//var itemName = w.item;
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

// I get a rough estimate of the number of watchers on the page. This assumes 
// that the entire page is inside the same AngularJS application. 
.factory("getWatchCount", function() {

		// I return the count of watchers on the current page.
		function getWatchCount() {

			var total = 0;

			// AngularJS denotes new scopes in the HTML markup by appending the
			// class "ng-scope" to appropriate elements. As such, rather than 
			// attempting to navigate the hierarchical Scope tree, we can simply
			// query the DOM for the individual scopes. Then, we can pluck the 
			// watcher-count from each scope.
			// --
			// NOTE: Ordinarily, it would be a HUGE SIN for an AngularJS service
			// to access the DOM (Document Object Model). But, in this case,
			// we're not really building a true AngularJS service, so we can 
			// break the rules a bit.
			angular.element( ".ng-scope" ).each(
				function ngScopeIterator() {

					// Get the scope associated with this element node.
					var scope = $( this ).scope();

					// The $$watchers value starts out as NULL. 
					total += scope.$$watchers
						? scope.$$watchers.length
						: 0
					;

				}
			);
			
			return( total );

		}

		// For convenience, let's serialize the above method and convert it to 
		// a bookmarklet that can easily be run on ANY AngularJS page. 
		getWatchCount.bookmarklet = ( 
			"javascript:alert('Watchers:'+(" + 
			getWatchCount.toString()
				.replace( /\/\/.*/g, " " )
				.replace( /\s+/g, " " ) +
			")());void(0);" 
		);

		return( getWatchCount );

	}
)


;