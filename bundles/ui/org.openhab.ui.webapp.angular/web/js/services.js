'use strict';

var services = angular.module('app.services', [ 'sprintf' ]);

services.factory('formatter', function(parser, transformService, $log, $q) {
	var formatter = {};
  
	var format = function(valuePattern,  value){
		if(typeof value == "number") {
			//return sprintf(valuePattern,  value);
		}
		return mdgw.format(valuePattern,  value);
	};

  	formatter.getFormattedValue = function(value, valuePattern){
		var rxTransform = /^(.*)\((.*)?\):(.*)$/gm;
		var rxTransformResult = rxTransform.exec(valuePattern);
		if(rxTransformResult != null){
			var transformType = rxTransformResult[1];
			var transformParam = rxTransformResult[2];
			valuePattern = rxTransformResult[3];
			var formattedValue = format(valuePattern, value);
			$log.debug("formatValue: need to transform: " + transformType + " " + transformParam + " " + formattedValue);
			return $q(function(resolve, reject) {
				transformService.transform(transformType, transformParam, formattedValue)
				.then(function(transformed){
						resolve(transformed.data);
				});
			}); 
			
		} else {
			//$log.debug("formatValue: pattern '" + valuePattern + "' value: " + value);
			return $q(function(resolve, reject) {
				resolve(format(valuePattern,  value));
			});
		}
  	};

  	formatter.sliceLabelAndValuePattern = function(labelPattern){
		var rx = /^(.*)? \[(.*)\]$/gm;
		var result = rx.exec(labelPattern);
		return result;
  	}
  
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
})



services.factory("color", function(){

	return {
			/**
		 * Converts an HSL color value to RGB. Conversion formula
		 * adapted from http://en.wikipedia.org/wiki/HSL_color_space.
		 * Assumes h, s, and l are contained in the set [0, 1] and
		 * returns r, g, and b in the set [0, 255].
		 *
		 * @param   Number  h       The hue
		 * @param   Number  s       The saturation
		 * @param   Number  l       The lightness
		 * @return  Array           The RGB representation
		 */
		hslToRgb: function hslToRgb(h, s, l){
			var r, g, b;

			if(s == 0){
				r = g = b = l; // achromatic
			}else{
				var hue2rgb = function hue2rgb(p, q, t){
					if(t < 0) t += 1;
					if(t > 1) t -= 1;
					if(t < 1/6) return p + (q - p) * 6 * t;
					if(t < 1/2) return q;
					if(t < 2/3) return p + (q - p) * (2/3 - t) * 6;
					return p;
				}

				var q = l < 0.5 ? l * (1 + s) : l + s - l * s;
				var p = 2 * l - q;
				r = hue2rgb(p, q, h + 1/3);
				g = hue2rgb(p, q, h);
				b = hue2rgb(p, q, h - 1/3);
			}

			return [Math.round(r * 255), Math.round(g * 255), Math.round(b * 255)];
		},

		/**
		 * Converts an RGB color value to HSL. Conversion formula
		 * adapted from http://en.wikipedia.org/wiki/HSL_color_space.
		 * Assumes r, g, and b are contained in the set [0, 255] and
		 * returns h, s, and l in the set [0, 1].
		 *
		 * @param   Number  r       The red color value
		 * @param   Number  g       The green color value
		 * @param   Number  b       The blue color value
		 * @return  Array           The HSL representation
		 */
		rgbToHsl: function rgbToHsl(r, g, b){
			r /= 255, g /= 255, b /= 255;
			var max = Math.max(r, g, b), min = Math.min(r, g, b);
			var h, s, l = (max + min) / 2;

			if(max == min){
				h = s = 0; // achromatic
			}else{
				var d = max - min;
				s = l > 0.5 ? d / (2 - max - min) : d / (max + min);
				switch(max){
					case r: h = (g - b) / d + (g < b ? 6 : 0); break;
					case g: h = (b - r) / d + 2; break;
					case b: h = (r - g) / d + 4; break;
				}
				h /= 6;
			}

			return [h, s, l];
		},


		/**
		 * Converts an RGB color value to HSV. Conversion formula
		 * adapted from http://en.wikipedia.org/wiki/HSV_color_space.
		 * Assumes r, g, and b are contained in the set [0, 255] and
		 * returns h, s, and v in the set [0, 1].
		 *
		 * @param   Number  r       The red color value
		 * @param   Number  g       The green color value
		 * @param   Number  b       The blue color value
		 * @return  Array           The HSV representation
		 */
		rgbToHsv: function rgbToHsv(r, g, b){
			r = r/255, g = g/255, b = b/255;
			var max = Math.max(r, g, b), min = Math.min(r, g, b);
			var h, s, v = max;

			var d = max - min;
			s = max == 0 ? 0 : d / max;

			if(max == min){
				h = 0; // achromatic
			}else{
				switch(max){
					case r: h = (g - b) / d + (g < b ? 6 : 0); break;
					case g: h = (b - r) / d + 2; break;
					case b: h = (r - g) / d + 4; break;
				}
				h /= 6;
			}

			return [h, s, v];
		},

		/**
		 * Converts an HSV color value to RGB. Conversion formula
		 * adapted from http://en.wikipedia.org/wiki/HSV_color_space.
		 * Assumes h, s, and v are contained in the set [0, 1] and
		 * returns r, g, and b in the set [0, 255].
		 *
		 * @param   Number  h       The hue
		 * @param   Number  s       The saturation
		 * @param   Number  v       The value
		 * @return  Array           The RGB representation
		 */
		hsvToRgb: function hsvToRgb(h, s, v){
			var r, g, b;

			var i = Math.floor(h * 6);
			var f = h * 6 - i;
			var p = v * (1 - s);
			var q = v * (1 - f * s);
			var t = v * (1 - (1 - f) * s);

			switch(i % 6){
				case 0: r = v, g = t, b = p; break;
				case 1: r = q, g = v, b = p; break;
				case 2: r = p, g = v, b = t; break;
				case 3: r = p, g = q, b = v; break;
				case 4: r = t, g = p, b = v; break;
				case 5: r = v, g = p, b = q; break;
			}

			return [r * 255, g * 255, b * 255];
		},


		hsvStrToHexStr: function(hsvString) {
			if(hsvString == "Uninitialized") {
				return hsvString;
			}

			var vals = hsvString.split(",");
			var h = new Big(vals[0]).div(360);
			var s = new Big(vals[1]).div(100);
			var b = new Big(vals[2]).div(100);
			var hsb = h + "," + s + "," + b;

			var rgb = this.hsvToRgb(parseFloat(h), parseFloat(s), parseFloat(b));

			var hex = mdgw.format("%02x", rgb[0]) + mdgw.format("%02x", rgb[1]) + mdgw.format("%02x", rgb[2]);
			return hex;
		}

	};

})


;

