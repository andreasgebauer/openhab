var appModule = angular.module('app.factories', []);

appModule.factory('sitemap', function($http, $log) {
	$log.debug("Initializing Sitemap-Factory")
	return {
		fetch : function(sitemap, widget, callback) {
			$log.info("Fetching sitemap '" + sitemap + "', widget: '" + widget + "'");
			$http.get(sitemapUrl, {
				params : {
					sitemap : sitemap,
					w : widget
				}
			}).success(function(data) {
				callback(data);
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
				var pageItem = getWidget(sitemap, widgetId);
				return pageItem;
			}
		}
	};
} )


// I get a rough estimate of the number of watchers on the page. This assumes 
// that the entire page is inside the same AngularJS application. 
.factory(
	"getWatchCount",
	function() {

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