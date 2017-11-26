var ws = require ("nodejs-websocket");
var net = require('net');
var serverPort = 1234;
var clientPort = 2222;
var host = "localhost";

function handleConnection (conn) {
     console.log('\n\n\n')
     console.log("---------------------------------------------------------------------");
     console.log("New connection" );
     console.log("---------------------------------------------------------------------");
     var client = new net.Socket();
     client.setEncoding('utf8');

     conn.on("close", function (s) {
        console.log("Connection closed");
     });

	 conn.on ("text", function (s) {
            if(s =='stop'){
                console.log('Connection aborted!!');
                client.destroy();
            }
            else{
                client.connect(clientPort, host, function() {
                    console.log('Connected to Java Server!');
                    client.write(s);
                    console.log("Message sent to Java server");
                });
            }
		});


	 var msg='';
	 /* NEW ONE */
     client.on('data', function (data) {
         msg += data.toString();
         if(msg.length > 3 && (data.substring(data.length-4)=="Iend"
             || data.substring(data.length-4)=="Eend"|| data.substring(data.length-4)=="Rend")){
             console.log("One message has just been sent to client!");
             console.log('['+data.substring(data.length-4,data.length)+']')
             conn.sendText(msg);
             msg='';
         }
         if(msg=="null"){
             conn.sendText(msg);
             console.log("Message is fully sent to the client (widgets messages). We'll close connection between the client and the server right now.");
             client.destroy();
         }
     });

     client.on('close', function() {
         console.log('Client of Java Server disconnected!');
         conn.close();
     });

     client.on('error', function(ex) {
       console.log("handled error...");
       conn.sendText(JSON.stringify({'error':ex})+"Eend"); //should keep the same protocol used to the server, to be handled as an error.
     });
	}

ws.createServer(handleConnection).listen(serverPort);