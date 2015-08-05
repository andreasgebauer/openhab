var sitemapUrl = "/angular/sitemap/";
var commandUrl = "/angular/cmd/";
var chartDataUrl = "/angular/chartdata/";
var websocketUrl = "/angular/websocket/";

var app = angular.module("app", [ 'app.controllers', 'app.directives', 'ngAnimate' ]);

app.config(function(webSocketProvider) {
	
	webSocketProvider.setWebSocketURL('ws://' + window.location.host + websocketUrl);
});