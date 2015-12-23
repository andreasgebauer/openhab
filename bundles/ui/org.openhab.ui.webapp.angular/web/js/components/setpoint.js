angular.module("app.directives")

.directive('setpoint', function($templateRequest, $compile, $animate, $interpolate) {
	
	return({
		controller: "SetpointController",
		link: link,
		restrict: "AE",
		replace: true,
		template: 	'<div> \
						<img ng-src="{{widget.icon}}" width=29 height=29 class="iFull" />\
						<span class="options">\
							<img class="down" src="/images/_down.png" />\
							<img class="up" src="/images/_up.png" />\
						</span>\
						<label style="{{widget.labelStyle}}">{{widget.label}}</label>\
						<span class="value" style="{{widget.valueStyle}}" class="{{widget.styleClass}}">{{widget.formattedValue}}</span> \
					</div>'
	});


	function link( scope, element, attributes, controller ) {

		var down = element.find("img.down");
		var up = element.find("img.up");

		down.on("touchstart mousedown", function(){
			controller.decreaseState();
		});

		up.on("touchstart mousedown", function(){
			controller.increaseState();
		});
	}
});


angular.module("app.controllers")

.controller('SetpointController', function($scope, $element, commandService, updateService, widgetDataProcessor) {

	var widget = $scope.widget;

	var changeState = function(item, state) {
		commandService.update(item, state);
	};
	
	this.increaseState = function() {
		var newState = new Big(widget.value).plus(new Big(widget.step))
		changeState(widget.item, newState.toString());
	};

	this.decreaseState = function() {
		var newState = new Big(widget.value).minus(new Big(widget.step))
		changeState(widget.item, newState.toString());
	};

	updateService.attach($scope, $element);

	widgetDataProcessor.process($scope.widget);

});

