var appModule = angular.module('app');

appModule.factory('sitemap', function($http, $log) {
	return {
		fetch : function(sitemap, widget, callback) {
			$log.info("Fetching sitemap '" + sitemap + "', widget:" + widget);
			$http.get(url, {
				params : {
					sitemap : sitemap,
					w : widget
				}
			}).success(function(data) {
				callback(data, widget);
			}).error(function() {
				alert("Unable to fetch sitemap");
			});
		},
		pageItem: function(sitemap, widgetId){
			// returns the widget for the widget id
			var getWidget = function(root, widgetId) {
				if(root.id == widgetId) {
					return root;
				}
				if(!angular.isUndefined(root.children)) {
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
			
			if(!angular.isUndefined(sitemap)) {
				var pageItem = getWidget(sitemap, widgetId);
				return pageItem;
			}
		}
	};
} );