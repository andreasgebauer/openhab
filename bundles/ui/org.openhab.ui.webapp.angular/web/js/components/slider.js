angular.module("app.directives")

.directive('slider', function($templateRequest, $compile, $animate, $interpolate) {
	return({
		controller: "SliderController",
		link: postLink,
		restrict: "A",
		replace: true,
		template: '<div> \
					<img ng-src="{{widget.icon}}" width=29 height=29 class="iFull" /> \
					<span class="options"> \
						<img class="down" src="/images/_down.png"/> \
						<img class="up" src="/images/_up.png"/> \
					</span> \
					<label style="{{widget.labelStyle}}">{{widget.label}}</label> \
					<span class="value {{widget.styleClass}}">{{widget.value}}</span> \
				</div>'
	});

	function postLink( scope, element, attributes, ctrl ) {

		element.find("img.up").on("touchstart mousedown", function(){
			ctrl.repeatedRequest("INCREASE");
		}).on("mouseup", function(){
			ctrl.stopRepeatedRequest("INCREASE");
		});

		element.find("img.down").on("touchstart mousedown", function(){
			ctrl.repeatedRequest("DECREASE");
		}).on("mouseup", function(){
			ctrl.stopRepeatedRequest("DECREASE");
		});
	}
});


angular.module("app.controllers")

.controller('SliderController', function($scope, $element, commandService, updateService, widgetDataProcessor) {
	var widget = $scope.widget;

	this.repeatedRequest = function(cmd) {
		commandService.update(widget.item, cmd);
	}

	this.stopRepeatedRequest = function(cmd) {
		$log.debug("stopRepeatedRequest invoked: " + widget.item + ": " + cmd);
	}

	updateService.attach($scope, $element);

	widgetDataProcessor.process($scope.widget);

});

