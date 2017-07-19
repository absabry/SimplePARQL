var net = require('net');
var sleep = require('system-sleep');

var client = net.connect(1234, 'localhost');
client.setEncoding('utf8');
setInterval(function() {
  console.log("Writing....");
  client.write('First client says : Hello from node.js\n');
}, 5000);
