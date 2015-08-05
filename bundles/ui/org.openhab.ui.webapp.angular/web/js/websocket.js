'use strict';

var app = angular.module('app');

app.provider('webSocket', function() {

	var webSocketURL;
	var webSocketObject; // for testing only

	return {
		$get : function($q, $log) {
			var opened = false;
			var closed = false;
			var closeForced = false;

			if (!webSocketURL && !webSocketObject) {
				throw 'WebSocket URL is not defined';
			}
			
			var callbacks = jQuery.Callbacks();
			var socket;

			function onopen(){
				$log.debug("onopen");
				opened = true;
				closed = false;
			}

			function onmessage(e) {
				$log.debug("received message " + e.data);
				var data = JSON.parse(e.data);
				callbacks.fire(data);
			}

			function onclose(m){
				$log.debug("onclose");
				closed = true;
				opened = false;

				if(!closeForced) {
					// try to reconnect
					webSocket.reconnect();
				}
			}

			var webSocket = {

				subscribe : function(callback) {
					callbacks.add(callback);
				},
				
				unsubscribe : function(callback) {
					callbacks.remove(callback);
				},
				
				close : function(){
					$log.debug("close");
					//callbacks.disable();
					socket.close();
					closeForced = true;
				},
				
				init: function(){
					$log.debug("init");
					socket = !webSocketObject ? new WebSocket(webSocketURL) : webSocketObject;
					socket.onopen = onopen;
					socket.onmessage = onmessage;
					socket.onclose = onclose;

					closeForced = false;
				},

				reconnect: function() {
					this.init();
				},

				isClosed : function() {
					return closed;
				},

				isOpen : function() {
					return opened;
				}
			};

			return webSocket;
		},

		setWebSocketURL : function(wsURL) {
			webSocketURL = wsURL;
		},

		setWebSocketObject : function(wsObject) {
			webSocketObject = wsObject;
		}
	};
});