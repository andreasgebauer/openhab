
angular.module("app.directives")
.directive('button', function(updateService) {
	
	return({
		controller: "ButtonController",
		link: link,
		restrict: "A",
		replace: true,
		template: '<a href="javascript:void(0);" class="iButton {{cssClass}}" style="{{widget.labelStyle}}">{{mapping.label}}</a>'
	});

	function link( scope, element, attributes, controller ) {

		element.on("click", function(){
			controller.changeState();
		});

		updateService.attach(scope, element, function(data) {
			controller.setCssClass(data.value);
		});

	}
});

angular.module("app.controllers")
.controller('ButtonController', function($scope, $element, $log, commandService, updateService) {

	var widget = $scope.widget;
	var mapping = $scope.mapping;

	this.changeState = function() {
		commandService.update(widget.item, mapping.cmd);
	};
	
	this.setCssClass = function (value) {
		// active or inactive
		$scope.cssClass = "iB" + ((widget.mappings.length > 1 && value == mapping.cmd) ? 'Warn' : 'Action');
	}

	// active or inactive
	this.setCssClass(widget.value);
});
