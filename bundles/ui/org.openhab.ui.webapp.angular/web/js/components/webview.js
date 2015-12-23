angular.module("app.directives")
.directive('webview', function(widgetLinker) {
	
	return({
		controller: "WebviewController",
		link: link,
		restrict: "A",
		replace: true,
		template: '<div>\
						<center>\
							<iframe style="padding:10px;width:90%"></iframe>\
						</center>\
					</div>'
	});

	function link($scope, $element, $args, ctrl){
		widgetLinker.link($scope, $element);

		var widget = $scope.widget;

		var iframe = $element.find("iframe");

		iframe.attr("src", widget.url);
		if(widget.height) {
			iframe.attr("height", widget.height);
		}
	}

});

angular.module("app.controllers")
.controller("WebviewController", function($scope){
	
	
});