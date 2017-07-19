var net = require('net');
var sleep = require('system-sleep');

var client = net.connect(1234, 'localhost');
client.setEncoding('utf8');

setInterval(function() {
    console.log("Writing....");
    response={
        "query":"i'm here too",
        "base":["hello you"]
    }
    client.write(JSON.stringify(response)+"\n");
}, 20000);
