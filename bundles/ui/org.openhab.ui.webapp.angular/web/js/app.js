var app = angular.module("app", [ 'appControllers', 'ngAnimate', 'ui.bootstrap' ]);

app.config(function(webSocketProvider) {
	
	webSocketProvider.setWebSocketURL('ws://' + window.location.host + '/angular/websocket');
});