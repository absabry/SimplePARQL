var ws = require ("nodejs-websocket");
var net = require('net');
var serverPort = 1234;
var clientPort = 2222;
var host = "localhost";

function handleConnection (conn) {
     conn.on ("close", function (s) {
        console.log("Connection closed");
     });
	 conn.on ("text", function (s) {
            var client = new net.Socket();
            client.setEncoding('utf8');
            client.connect(clientPort, host, function() {
                console.log('Connected to Java Server!');
                client.write(s);
                console.log("Message sent to Java server");
            });

            var msg='';
            client.on('data', function (data) {
                msg += data.toString();
                if(msg.length > 3 && data.substring(data.length-4)=="null"){
                    var message = msg.substring(0,msg.length-4); //to delete the null of the message
                    client.destroy();
                    conn.sendText(message);
                    console.log("Message sent to client!");
                }
            });

            client.on('close', function() {
                console.log('Connection to Java Server closed!');
            });

            client.on('error', function(ex) {
              console.log("handled error");
              conn.sendText(JSON.stringify({'error':ex}));
            });
		});
	}

ws.createServer(handleConnection).listen(serverPort);