

var app = angular.module("app.directives")

.directive('buttons', function(widgetLinker) {
	
	return({
		link: widgetLinker.link,
		restrict: "AE",
		template: '	<img ng-src="{{widget.icon}}" width=29 height=29 class="iFull" /> \
					<label style="{{widget.labelStyle}}">{{widget.label}}</label> \
					<filler ng-repeat="mapping in widget.mappings.slice().reverse()" button ng-style="{{widget.labelStyle}}"></filler>'
	});

});

