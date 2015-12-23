angular.module("app.directives")

.directive('text', function(widgetLinker) {
	
	return({
		controller: "TextController",
		link: widgetLinker.link,
		restrict: "A",
		replace: true,
		template: ' <div> \
						<img ng-src="{{widget.icon}}" width=29 height=29 /> \
						<label style="{{widget.labelStyle}}">{{widget.label}}</label> \
						<span class="value" style="{{widget.valueStyle}}" class="{{widget.styleClass}}">{{widget.formattedValue}}</span> \
					</div>'
	});

});

angular.module("app.controllers")

.controller("TextController", function($scope, widgetDataProcessor){

	widgetDataProcessor.process($scope.widget);
});