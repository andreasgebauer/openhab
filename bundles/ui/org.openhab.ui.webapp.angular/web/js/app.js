var sitemapUrl = "/angular/sitemap/";
var commandUrl = "/angular/cmd/";
var chartDataUrl = "/angular/chartdata/";
var websocketUrl = "/angular/websocket/";
var imageUrl = "/angular/images/";
var transformUrl = "/angular/transform/";

var app = angular.module("app", [ 'app.controllers', 'app.directives', 'app.services', 'ngAnimate', 'minicolors' ]);

app.config(function(webSocketProvider) {
	webSocketProvider.setWebSocketURL('ws://' + window.location.host + websocketUrl);
});


window.openhab = {};
window.openhab.isValueDefined = function (value) {
	return value != 'Uninitialized' && value != 'Undefined';

}