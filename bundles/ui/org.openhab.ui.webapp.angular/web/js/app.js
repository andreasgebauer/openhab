var app = angular.module("app", [ 'app.controllers', 'app.directives', 'ngAnimate', 'ui.bootstrap' ]);

app.config(function(webSocketProvider) {
	
	webSocketProvider.setWebSocketURL('ws://' + window.location.host + '/angular/websocket');
});