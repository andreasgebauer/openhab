
angular.module('app.directives')

.directive('selection', function(widgetLinker) {
	return {
		template : '<a ng-style="{{widget.labelStyle}}" class="iRadio" value="autoback"> \
						<img ng-src="{{widget.icon}}" width=29 height=29 class="iFull" />{{widget.label}}	{{widget.rows}} \
					</a>',
		restrict : 'AE',
		replace : true,
		link : function postLink($scope, element, attrs, ctrl) {
			widgetLinker.link($scope, element);
			
			element.on("click", function(){
				ctrl.showSelections();
			});

		},
		controller: "SelectionController"
	};
})

angular.module('app.controllers')

.controller("SelectionController", function($scope, $log, $location){
    $log.debug("SelectionController constructing");

	var widget = $scope.widget;

	this.showSelections = function() {

		var clone = angular.extend({}, widget);
		clone.initialValue = clone.value;

		$scope.setViewRoot(clone);

		$scope.$apply(function(){
			$location.path("_Selection");
		});

	}
});

