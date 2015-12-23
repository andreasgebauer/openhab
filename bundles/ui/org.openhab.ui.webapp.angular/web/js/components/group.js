

var app = angular.module("app.directives")

.directive('group', function($templateRequest, $compile, $animate, $interpolate) {
	
	return({
		controller: "LinkController",
		link: link,
		restrict: "A",
		replace: true,
		template: '	<a href="javascript:void(0);"> \
						<img ng-src="{{widget.icon}}" width=29 height=29 ng-style="{{widget.labelStyle}}" />{{widget.label}} \
					</a>'
	});

	function link( scope, element, attributes, controller ) {
		element.on("click", function(){
			controller.load(scope.widget);
		});
	}
});

