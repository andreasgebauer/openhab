angular.module("app.directives")
.directive('image', function(widgetLinker) {
	
	return({
		controller: "ImageController",
		link: link,
		restrict: "A",
		replace: true,
		template: '<div>\
						<center>\
							<img style="padding: 10px; width: 90%" {{widget.refresh}} />\
						</center>\
					</div>'
	});

	
	function link($scope, $element, $args, ctrl){
		widgetLinker.link($scope, $element);

		var widget = $scope.widget;

		var iframe = $element.find("img");

		iframe.attr("src", widget.url);
	}

});

angular.module("app.controllers")
.controller("ImageController", function($scope){

	
});