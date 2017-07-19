var net = require('net');

var client = net.connect(1234, 'localhost');
client.setEncoding('utf8');
setInterval(function() {
  console.log("Writing....")
  client.write('Clientbis says : I\'m another one mann!!!\n');
}, 100);
