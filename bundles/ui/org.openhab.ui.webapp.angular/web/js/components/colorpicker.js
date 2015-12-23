
angular.module('app.directives')

.directive('colorpicker', function(updateService) {
	return {
		template : '<div> \
						<img ng-src="{{widget.icon}}" width=29 height=29 class="iFull" /> \
						<span class="options"> \
							<img class="down" src="/images/_down.png" /> \
							<img class="colorWheel" src="/images/colorwheel.png" width=29 height=29 border=0 />\
							<img class="up" src="/images/_up.png" /> \
						</span> \
						<label style="{{widget.labelStyle}}">{{widget.label}}</label> \
					</div>',
		restrict : 'AE',
		replace : true,
		link : function postLink($scope, element, attrs, ctrl) {

			updateService.attach($scope, element);

			var down = element.find(".down");
			var up = element.find(".up");
			var wheel = element.find(".colorWheel");

			down.on("touchstart mousedown", function() {
				ctrl.off();
			});

			down.on("mouseup", function() {
				ctrl.stopRepeatedRequest("DECREASE");
			});

			up.on("touchstart mousedown", function() {
				ctrl.on();
			});

			up.on("mouseup", function() {
				ctrl.stopRepeatedRequest("INCREASE");
			});

			wheel.on("click", function() {
				ctrl.showColorPicker();
			});

		},
		controller: "ColorpickerController"
	};
})

angular.module('app.controllers')

.controller("ColorpickerController", function($scope, $log, $location, commandService, color, widgetDataProcessor){
    $log.debug("Colorpicker Controller constructing");

	var widget = $scope.widget;

	widgetDataProcessor.process(widget);

	this.off = function() {
		commandService.update(widget.item, "OFF");
	};

	this.on = function() {
		commandService.update(widget.item, "ON");
	};

	this.repeatedRequest = function(cmd){
		
	};

	this.stopRepeatedRequest = function(cmd){
		
	};

	this.showColorPicker = function() {

		var clone = angular.extend({}, widget);
		clone.initialValue = clone.value;
		if(clone.value == "Uninitialized") {
			clone.value = "000000";
		} else {

			var hex = color.hsvStrToHexStr(clone.value);
			clone.valueInput = hex;
		}

		$scope.setViewRoot(clone);

		$scope.$apply(function(){
			$location.path("_Colorpicker");
		});

	}
});

