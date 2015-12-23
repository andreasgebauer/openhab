

var app = angular.module("app.directives")

.directive('switch', function($templateRequest, $compile, $animate, $interpolate) {
	
	return({
		controller: "SwitchController",
		link: link,
		restrict: "A",
		replace: true,
		template: '<div> \
						<b class="iToggle {{cssClass}}" title="{{widget.item}}">&nbsp;<b style="{{alignB}}: -1px;"></b> \
						<i style="{{alignI}}: 0px;">{{label}}</i></b> \
						<input type="checkbox" id="{{widget.item}}" class="iToggle" title="I|O" {{widget.checked}} /> \
						<img ng-src="{{widget.icon}}" width=29 height=29 class="iFull" /> \
						<label style="{{widget.labelStyle}}">{{widget.label}}</label> \
					</div>'
	});

	function link( scope, element, attributes, controller ) {
		var toggle = element.find("b.iToggle");
		toggle.on("click", function(){
			controller.toggle();
		});
	}
});


angular.module("app.controllers")

.controller('SwitchController', function($scope, $element, commandService, updateService, widgetDataProcessor) {
	var widget = $scope.widget;

	this.toggle = function() {
		commandService.update(widget.item, "TOGGLE");
	};

	var setWidgetValues = function(widget) {
		if(widget.value == "ON") {
			$scope.alignI = "left";
			$scope.alignB = "right";
			$scope.cssClass = "__sel";
			$scope.label = "I";
		} else if(widget.value == "OFF"){
			$scope.alignI = "right";
			$scope.alignB = "left";
			$scope.cssClass = "";
			$scope.label = "O";
		} else {
			//TODO undefined state
		}
	};

	setWidgetValues(widget);

	updateService.attach($scope, $element, function handleSwitchUpdate(data){
		setWidgetValues(widget);
	});

	widgetDataProcessor.process($scope.widget);
});

