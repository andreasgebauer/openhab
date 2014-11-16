'use strict';

angular.module('app').provider('webSocket', function() {

	var webSocketURL;
	var webSocketObject; // for testing only

	return {
		$get : function($q, $log) {
			this.opened = false;
			this.closed = false;

			if (!webSocketURL && !webSocketObject) {
				throw 'WebSocket URL is not defined';
			}
			
			var setup = function() {
				var socket = !webSocketObject ? new WebSocket(webSocketURL) : webSocketObject;
				
				socket.onopen = function() {
					deferred.resolve();
				};

				socket.onmessage = function(e) {
					var data = JSON.parse(e.data);
					callbacks.fire(data);
				};

				socket.onclose = function(){
					$log.info("Websocket closed");
					socket.closed = true;
				};
				
				return socket;
			}
			
			var socket;
			var deferred = $q.defer();
			var callbacks = jQuery.Callbacks();

			return {
				closed: false,

				send : function(message) {
					var msg = JSON.stringify(message);

					deferred.promise.then(function() {
						if(socket.readyState == 3){
							socket = new WebSocket(webSocketURL);
						}	
						socket.send(msg);
					});
				},

				subscribe : function(callback) {
					callbacks.add(callback);
				},
				
				unsubscribe : function(callback) {
					callbacks.remove(callback);
				},
				
				close : function(){
					socket.close();
					this.closed = true;
				},
				
				init: function(){
					socket = setup();
					this.closed = false;
				},

				isClosed : function() {
					return this.closed;
				}
			};
		},

		setWebSocketURL : function(wsURL) {
			webSocketURL = wsURL;
		},

		setWebSocketObject : function(wsObject) {
			webSocketObject = wsObject;
		}
	};
});