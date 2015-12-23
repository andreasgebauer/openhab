'use strict';

angular.module('app.directives', ['app.controllers', 'window-events'])

.directive('root', function($templateRequest, $compile, $animate, $interpolate, commandService, updateService, color) {

	var template = function(type) {
		if(type == "group") {
			type = "frame";
		}
		return "pages/" + type + ".html";
	};


	var render = function(scope, element, parent, childScopes){
		var widget = scope.viewRoot;
		if(!widget) {
			return;
		}

		var after = element.find("div#children");

		var children;
		if(widget.type == "group" || widget.type == "colorpicker"){
			// for groups: wrap the single widget into a child.
			children = [widget];
		} else {
			children = widget.children;	
		}

		var itemsInserted = 0;
		angular.forEach(children, function(child, index){
			$templateRequest(template(child.type), true).then(function(response) {
				var childScope = scope.$new();
				childScopes.push(childScope);

				childScope.widget = child;
				var el = angular.element(response);

				if(child.type == "colorpicker"){
					childScope.colors = {
						inline: true,
						control: "wheel",
						style: "margin: 10px;",
						theme: "default",
						format: "rgb"
					};

					var updateInProgress = false;

					updateService.attach(childScope, el, function(data){
						var hex = color.hsvStrToHexStr(data.value);
						updateInProgress = true;
						widget.valueInput = hex;
						widget.initialValue = data.value;
						updateInProgress = false;
					});

					childScope.$watch("widget.valueInput", function(value, oldValue){
						if(value != oldValue) {
							var val = value;
							var oldVal = oldValue;
							if(value[0] == '#')
								val = value.substring(1);
							if(oldValue && oldValue[0] == '#')
								oldVal = oldValue.substring(1);
							if(val != oldVal) {
								console.info("HSB-Value: " + val);

								var r = parseInt("0x" + val.substring(0,2));
								var g = parseInt("0x" + val.substring(2,4));
								var b = parseInt("0x" + val.substring(4,6));

								var hsb = color.rgbToHsv(r,g,b);
								hsb[0] *= 360;
								hsb[1] *= 100;
								hsb[2] *= 100;

								var initial = color.hsvStrToHexStr(child.initialValue);

								if(initial != val){
									commandService.update(child.item, hsb[0]+","+hsb[1]+","+hsb[2]);
								}
							}
						}
					});
				}

				$compile(el)(childScope);

				el.insertAfter(after);
				after = el;

				// if all elements inserted
				if(++itemsInserted == children.length){
					element.addClass(scope.animationStyle);
					$animate.enter(element, parent);
				}
			});	
		});

	};

	return {
		template: '',
		restrict: 'E',
		replace: true,
		compile: function compile(element, attr) {
		  return {
			post: function postLink(scope, iElement, iAttrs, controller) { 
				var parentScope = scope.$parent;
				var original = iElement.clone();
				var previousElement, previousChildScopes = new Array();
				var parent = element.parent();


				scope.$watchCollection(iAttrs.swap, function(val, old){
					var newElement = element;
					if(previousElement) {
						newElement = original.clone();
						angular.forEach(previousChildScopes, function(previousChildScope, index){
							previousChildScope.$destroy();
						});
						previousChildScopes = new Array();
						$animate.leave(previousElement);
					}

					render(scope, newElement, parent, previousChildScopes);

					previousElement = newElement;

				});
			}
		  }
		}
	};
})


.directive('proxy', function ($parse, $injector, $controller, $compile) {
	return {
		replace: true,
		link: function (scope, element, attrs) {
			var nameGetter = $parse(attrs.proxy);
			var name = nameGetter(scope);
			var normalizedName = attrs.$normalize(name);
			var value = undefined;
			if (attrs.proxyValue) {
			  var valueGetter = $parse(attrs.proxyValue);
			  value = valueGetter(scope);
			}

			if (value !== undefined) {
				attrs[normalizedName] = value;
			}

			var el = angular.element("<div " + name +  "></div>");
			el = $compile(el)(scope);
			element.append(el);
		}
	}
})

		


;