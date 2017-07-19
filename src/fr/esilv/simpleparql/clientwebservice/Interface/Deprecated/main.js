'use strict';
var express = require('express'),
bodyParser = require('body-parser'),
net = require('net'),
TIMEOUT=5*1000,
app=express();
// npm install net ejs express body-parser json-socket


app.use("/css_js",  express.static(__dirname + '\\css_js'));
app.use("/Views",  express.static(__dirname + '\\Views'));
app.use("/images",  express.static(__dirname + '\\images'));
app.set('view engine','ejs');

app.use(bodyParser.urlencoded({
    extended: true
}));
app.use(bodyParser.json());

app.get('/', function (req, res) {
    res.render("simpleparql",{result:null});
});

app.get('/sparql', function (req, res) {
    res.render('sparql');
});

app.get('/simpleparql',function(req,res){
    res.render("simpleparql",{result:null});
})
app.get('/about', function (req, res) {
    res.sendFile('Views/about.html', {root: __dirname })
});
app.post('/sparql', function (req, res) {
    postQuery('sparql',req,res);
});

app.post('/simpleparql', function (req, res) {
    postQuery('simpleparql',req,res);
});


function postQuery(plateform,req,res){

     var lastQuery={
            "newprefix" :req.body.newprefix,
            "bases" : req.body.list_bases,
            "prefixes": req.body.list_prefixes,
            "plateform": plateform,
            "query":req.body.Query
     }

    if(req.body.newprefix.trim() != ""){
        // split the text area each break line
        var newprefix = (req.body.newprefix).split("\r\n");
        // delete the first word PREFIX from the prefixes query if it exists
        for(var i=0;i<newprefix.length;i++){
            var parts = newprefix[i].split(' ');
            if(parts[0]=="PREFIX"){
                parts.shift();
            }
            newprefix[i] = parts.join(' ');
        }

        // add the newprefixes list to the pre defined prefixes
        if(req.body.list_prefixes != undefined){
            req.body.list_prefixes =  req.body.list_prefixes.concat(newprefix);
        }
        else if(req.body.list_prefixes == undefined){
             req.body.list_prefixes = newprefix;
        }
    }
    else if(req.body.list_prefixes == undefined){
        req.body.list_prefixes=[];
    }

    if(req.body.list_bases == undefined){
        req.body.list_bases=[];
    }

    for(var i=0;i<req.body.list_prefixes.length;i++){
         var parts = req.body.list_prefixes[i].split(':');
         parts[1]= "<"+parts[1];
         parts[2]= parts[2]+">";
         req.body.list_prefixes[i] = parts.join(':');
    }


    var response={
        "bases" : req.body.list_bases,
        "prefixes": req.body.list_prefixes,
        "plateform": plateform,
        "query":req.body.Query
    }

    var client = new net.Socket();
    client.setEncoding('utf8');
    client.connect(1234, 'localhost', function() {
        console.log('Connected');
        client.write(JSON.stringify(response)+"\n");
    });

    var msg='';
    client.on('data', function (data) {
        msg += data.toString();
        if(msg.length > 3 && data.substring(data.length-4)=="null"){
            var message = JSON.parse(msg.substring(0,msg.length-4)); //to delete the null of the message
            message.lastQuery = lastQuery;
            console.log(message);
            res.render(plateform,{result:message});
            client.destroy();
        }
    });

    client.on('close', function() {
        console.log('Connection closed');
    });
}
app.listen(1222);
console.log("Magic happens on the 1222 port!");
