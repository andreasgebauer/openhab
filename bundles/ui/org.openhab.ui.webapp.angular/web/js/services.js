'use strict';

var services = angular.module('app.services', [ 'sprintf' ]);

services.factory('formatter', function(parser, transformService, $log, $q) {
	var formatter = {};
  
  	formatter.getFormattedValue = function(value, valuePattern){
		var rxTransform = /^(.*)\((.*)?\):(.*)$/gm;
		var rxTransformResult = rxTransform.exec(valuePattern);
		if(rxTransformResult != null){
			var transformType = rxTransformResult[1];
			var transformParam = rxTransformResult[2];
			valuePattern = rxTransformResult[3];
			var formattedValue = mdgw.format(valuePattern,  value);
			$log.debug("formatValue: need to transform: " + transformType + " " + transformParam + " " + formattedValue);
			return $q(function(resolve, reject) {
				transformService.transform(transformType, transformParam, formattedValue)
				.then(function(transformed){
						resolve(transformed.data);
				});
			}); 
			
		} else {
			$log.debug("formatValue: pattern '" + valuePattern + "' value: " + value);
			return $q(function(resolve, reject) {
				resolve(mdgw.format(valuePattern,  value));
			});
		}
  	};
  
	// formats a value
	formatter.formatValue = function(widget, data) {
		if(angular.isDefined(data) && angular.isDefined(data.value)) {
			if(openhab.isValueDefined(data.value) && widget.labelPattern) {
				var labelPattern = widget.labelPattern;
				var rx = /^(.*)? \[(.*)\]$/gm;
				var result = rx.exec(labelPattern);
				if(result != null){
					var valuePattern = result[2];
					var value = parser.parseValue(data);
					formatter.getFormattedValue(value, valuePattern).then(function(formattedValue){
						widget.formattedValue = formattedValue;
					});
				}
			} else {
				widget.formattedValue = "-";
			}
		}
	};
  
	return formatter;
});

services.factory('parser', function() {
	var parser = {};
	
	parser.parseValue = function(value){
		if (value.valueType == "datetime") {
			return new Date(value.value);
		}
		return value.value;
	};
	
	return parser;
});
