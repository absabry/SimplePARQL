
var urlMySocket = "ws://localhost:1234/";

function callSocket (s, callback) {
    var whenstart = new Date ();
    var mySocket = new WebSocket (urlMySocket);
    mySocket.onopen = function (evt) {
        mySocket.send(JSON.stringify(s)+"\n");
        };
    mySocket.onmessage = function (evt) {
        $("#idWebSocketTime").text ("The call to the server took " + secondsSince (whenstart) + " secs to perform this query.");
        callback (evt.data);
        mySocket.close ();
        };
}

function secondsSince (when) {
    var now = new Date ();
    when = new Date (when);
    return ((now - when) / 1000);
}

function createQueryFromAssisted(){
     var select = $('#select').val();
     var where = $('#where').val();
     var filter = $('#filter').val();
     var optional = $('#optional').val();
     var limit = $('#limit').val();
     return 'SELECT ' + select
           + 'WHERE{' + where
           + 'FILTER(' + filter +')'
           + 'OPTIONAL('+ optional +')'
           + '}' 'LIMIT' + limit;
}

 $( "#query_form" ).submit(function( event ) {
     event.preventDefault();
     var bases = $("#list_bases").data("kendoMultiSelect").value();
     var prefixes = $("#list_prefixes").data("kendoMultiSelect").value();
     var newprefix = $('#newprefix').val();
     var select = $('#select').val();
     var where = $('#where').val();
     var filter = $('#filter').val();
     var optional = $('#optional').val();
     var limit = $('#limit').val();

     newprefix = cleanArray(newprefix.split("\n"));// split the text area each break line
     var query= $('#query').val();
     var queryInformations={
             "bases" : bases,
             "prefixes": prefixes.concat(newprefix),
             "query":query
     }
     console.log(queryInformations);
     /*
     callSocket(queryInformations, function (s) {
            var json = JSON.parse(s);
            result("#query_results",json);
            $('#wait').empty();
     });
     */
});


function cleanArray(array){
    var tmp=[]
    for(var i=0;i<array.length;i++){
       if(array[i].trim() != ''){
         tmp.push(array[i]);
       }
    }
    return tmp;
}

function result(query_div,json){
    $( query_div ).empty(); // clear the div

    if(json.hasOwnProperty('error')){
        var error = $('<h3></h3>').addClass('error').text(json.error);
         $(query_div).append(error);
         return;
    }

    var base=$('<p></p>').text('Results for the base ').append('<b>'+json.base+'</b>');
    $(query_div).append(base);

    for (var i=0; i <json.response.length;i++){
        var query= $("<p></p>").addClass('query pointer').multiline(reformatQuery(json.response[i].query))
            .on( "click", function() {
              queryToTextArea($(this).text());
            });
        $(query_div).append(query);

        var table = $('<table></table>');
        addTitle(table,json,i);
        addResults(table,json,i);
        $(query_div).append(table);
        $(query_div).append('<hr>');
        $(query_div).append('<br/>');
    }
}

function addTitle(table,json,i){
    var row= $('<tr></tr>');
    for(var j=0; j<json.response[i].variables.length; j++){
        var title = $('<th></th>').text(json.response[i].variables[j]);
        row.append(title);
    }
    table.append(row);
}

function addResults(table,json,i){
    for(var j=0;j<json.response[i].results.length;j++){
        var row= $('<tr></tr>');
        for(var k=0;k<json.response[i].results[j].length;k++){
            var element=$('<td></td>')
            addURIElement(element,json.response[i].results[j][k][1].Result);
             if(json.response[i].results[j][k][2] != undefined){
                  if(json.response[i].results[j][k][2].Position == "LABEL"){
                     element.append( $('<b></b>').text(' has for label '));
                     addURIElement(element,json.response[i].results[j][k][2].Result);
                  }
                  else if(json.response[i].results[j][k][2].Position == "TMP1"){
                    element.append( $('<b></b>').text(' has for the proprety '));
                    addURIElement(element,json.response[i].results[j][k][2].Result);
                    element.append( $('<b></b>').text(' the value '));
                    addURIElement(element,json.response[i].results[j][k][3].Result);
                }
             }
             row.append(element);
        }
        table.append(row);
    }
}

// add text with multiline in the tags
$.fn.multiline = function(text){
    this.text(text);
    this.html(this.html().replace(/\n/g,'<br/>'));
    return this;
}

function addURIElement(element,text){
     if (isUrl(text)){
       var a = $('<a></a>').attr('href',text).text(text);
       element.append(a);
    }
    else{
        element.append(text);
    }
}

function isUrl(s) {
   return s.toLowerCase().startsWith('http');
}