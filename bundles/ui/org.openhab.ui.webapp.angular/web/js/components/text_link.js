angular.module("app.directives")

.directive('textLink', function($templateRequest, $compile, $animate, $interpolate) {
	
	// Return the directive configuration.
	return({
		controller: "LinkController",
		link: link,
		replace: true,
		restrict: "A",
		template: 	'<a> \
						<img ng-src="{{widget.icon}}" width=29 height=29 />{{widget.label}} \
						<span class="value" style="{{widget.valueStyle}}" class="{{widget.styleClass}}">{{widget.formattedValue}}</span> \
					</a>'
	});


	// I bind the JavaScript events to the scope.
	function link( scope, element, attributes, controller ) {

		element.on("click", function(){

			element.parent().addClass("__sel");

			controller.load(scope.widget);
		});

	}
});

angular.module("app.controllers")

.controller("LinkController", function($scope, $element, $timeout, $location, updateService, widgetDataProcessor){

	this.load = function(widget) {
		$timeout(function() {
			$location.path(widget.id);
		});
	};

	updateService.attach($scope, $element);

	widgetDataProcessor.process($scope.widget);

});
