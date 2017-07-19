
var PORT = 1234;
var HOST= 'localhost';
var urlMySocket = "ws://"+HOST+":"+PORT+"/";
var mySocket;
var jsonResults;
var SIMPLEPARQL_QUERY_ASSISTED;
var SIMPLEPARQL_QUERY;

$("#query_form").submit(function( event ) {
     event.preventDefault();
     var queryInformations;
      if ($('#assist').is(':checked')) {
         queryInformations = createQueryFromAssisted();
     } else {
        queryInformations = createQueryFromNoAssisted();
     }
     SIMPLEPARQL_QUERY = queryInformations.query;

     callSocket(queryInformations, function (s) {
            jsonResults = JSON.parse(s);
            result("#query_results",jsonResults);
            $('#wait').empty();
     });
});

$("#clear").click(function(){
    if(mySocket != undefined){
         //mySocket.close();
         // does not work yet
    }
});

$("#download").click(function() {
  if(jsonResults != undefined){
      $("<a />", {
        "download": Date() + ".json",
        "href" : "data:application/json," + encodeURIComponent(JSON.stringify(jsonResults,null,4))
      }).appendTo("body")
      .click(function() {
         $(this).remove()
      })[0].click()
  }
  else{
    alert("You don't have any result, please run the query first!");
  }
});

$("#download-XML").click(function() {
    if(jsonResults != undefined){
          $("<a />", {
            "download": Date() + ".xml",
            "href" : "data:application/json," + encodeURIComponent(json2xml(jsonResults))
          }).appendTo("body")
          .click(function() {
             $(this).remove()
          })[0].click();
    }
    else{
        alert("You don't have any result, please run the query first!");
    }
});

function json2xml(json) {
    var c = document.createElement("root");
    var t = function (v) {
        return {}.toString.call(v).split(' ')[1].slice(0, -1).toLowerCase();
    };
    var f = function (f, c, json, s) {
        c.setAttribute("type", t(json));
        if (t(json) != "array" && t(json) != "object") {
            if (t(json) != "null") {
                c.appendChild(document.createTextNode(json));
            }
        } else {
            for (var k in json) {
                var v = json[k];
                if (k == "__type" && t(json) == "object") {
                    c.setAttribute("__type", v);
                } else {
                    if (t(v) == "object") {
                        var ch = c.appendChild(document.createElementNS(null, s ? "item" : k));
                        f(f, ch, v);
                    } else if (t(v) == "array") {
                        var ch = c.appendChild(document.createElementNS(null, s ? "item" : k));
                        f(f, ch, v, true);
                    } else {
                        var va = document.createElementNS(null, s ? "item" : k);
                        if (t(v) != "null") {
                            va.appendChild(document.createTextNode(v));
                        }
                        var ch = c.appendChild(va);
                        ch.setAttribute("type", t(v));
                    }
                }
            }
        }
    };
    f(f, c, json, t(json) == "array");
    return formatXml(c.outerHTML);
}

function formatXml(xml) {
    var formatted = '';
    var reg = /(>)(<)(\/*)/g; /* this comment is added to escape the /* in the regex expression not useful at all*/
    xml = xml.replace(reg, '$1\r\n$2$3');
    var pad = 0;
    jQuery.each(xml.split('\r\n'), function(index, node) {
        var indent = 0;
        if (node.match( /.+<\/\w[^>]*>$/ )) {
            indent = 0;
        } else if (node.match( /^<\/\w/ )) {
            if (pad != 0) {
                pad -= 1;
            }
        } else if (node.match( /^<\w[^>]*[^\/]>.*$/ )) {
            indent = 1;
        } else {
            indent = 0;
        }

        var padding = '';
        for (var i = 0; i < pad; i++) {
            padding += '  ';
        }

        formatted += padding + node + '\r\n';
        pad += indent;
    });
    return formatted;
}



/* ---------------- Get the POST fields and manipulate it before creating the socket ---------------------------*/

// create array when it's NOT assisted form
function createQueryFromAssisted(){
     var bases = $("#list_bases").data("kendoMultiSelect").value();
     var select = $('#select').val();
     var where = $('#where').val();
     var filter = $('#filter').val();
     var optional = $('#optional').val();
     var limit = $('#limit').val();
     var prefixes = $("#list_prefixes").data("kendoMultiSelect").value();
     var newprefix = $('#newprefix').val();
     newprefix = cleanArray(newprefix.split("\n"));
     prefixes = prefixes.concat(newprefix);
     var allPrefixes = '';
     for(var i=0;i<prefixes.length;i++){
        allPrefixes += ' PREFIX ' + prefixes[i];
     }
     // keep this infomrmations, to be used when we replace the truc in the original query
      SIMPLEPARQL_QUERY_ASSISTED= {
         'select':select,
         'where':where,
         'filter':filter,
         'optional':optional,
         }
     var query= allPrefixes
               + ' SELECT ' + select
               + ' WHERE {' + where
               + (filter.trim() != ''? ' FILTER(' + filter +')' : '')
               + (optional.trim() != ''? ' OPTIONAL('+ optional +')':'')
               + '}'
               + (limit.trim() != ''? ' LIMIT ' + limit:'');
     var queryInformations={
                  "bases" : bases,
                  "query":query
     }
     return queryInformations;
}

// create array when it's assisted form
function createQueryFromNoAssisted(){
      var bases = $("#list_bases").data("kendoMultiSelect").value();
      var query= $('#query').val();
      var queryInformations={
              "bases" : bases,
              "query":query
      }
      return queryInformations;
}

// get cleaned array (without empty elements)
function cleanArray(array){
    var tmp=[]
    for(var i=0;i<array.length;i++){
       if(array[i].trim() != ''){
         tmp.push(array[i]);
       }
    }
    return tmp;
}

/* ---------------- The webscoket ---------------------------*/

// main websocket
function callSocket (s, callback) {
    var whenstart = new Date ();
    mySocket = new WebSocket (urlMySocket)
    mySocket.onopen = function (evt) {
        mySocket.send(JSON.stringify(s)+"\n");
        };
    mySocket.onmessage = function (evt) {
        $("#idWebSocketTime").text ("The call to the server took " + secondsSince (whenstart) + " secs to perform this query.");
        callback (evt.data);
        mySocket.close ();
        };
    mySocket.onerror = function (event) {
        var result = $('<h3></h3>').addClass('error')
                    .text("There was an error with your websocket. [Maybe the webservice isn't connected to the server..?]");
        $("#query_results").append(result);
        $('#wait').empty();
    };
    mySocket.onclose = function(event){
         // detect when the socket is closing
        //console.log("Socket closed");
    }
}

// compute the seconds since launching
function secondsSince (when) {
    var now = new Date ();
    when = new Date (when);
    return ((now - when) / 1000);
}

/* ---------------- Build the result tags and elements ---------------------------*/
// check if string is JSON
function isJSON (something) {
    if (typeof something != 'string')
        something = JSON.stringify(something);

    try {
        JSON.parse(something);
        return true;
    } catch (e) {
        return false;
    }
}

function result(query_div,json){
    $(query_div).empty(); // clear the div

    if(json.hasOwnProperty('error')){
        addError(query_div,json.error);
        return;
    }

    addBase(query_div,json.base);

    for (var i=0; i <json.result.length;i++){
        addQuery(query_div,json.result[i].query,i,json.base.api);
        var table = $('<table></table>');
        addTitle(table,json,i);
        addResults(table,json,i);
        $(query_div).append(table);
        $(query_div).append('<hr>');
        $(query_div).append('<br/>');
    }
}

// add error (if exsits) to the html page
function addError(query_div,error){
    var errorTag = $('<h3></h3>').addClass('error');
    if(isJSON(error)){
        errorTag.multiline("Connection refused to the server. [Maybe the server isn't turned on..?] \n" + JSON.stringify(error));
    }
    else{
        errorTag.text(json.error);
    }
    $(query_div).append(errorTag);
}

// add base to the html page
function addBase(query_div,base){
    var baseTag=$('<p></p>')
            .text('Results for the base ')
            .append('<b>'+base.name+'</b>')
            .append(' You can found it here: ');
            addURIElement(baseTag,base.link);
            $(query_div).append(baseTag);
}

// add query to the html page
function addQuery(query_div,query,i,apiBase){
    var header = $('<div></div>').addClass('query_header').attr('title',query.substring(0,50)+'...\nClick on the arrow to see more');
    var textBesidesTheImage = $('<h5></h5>').text('Generated query n°'+(i+1)).css('display','inline');
    var imageCollapse =  $('<img></img>').addClass('showQuery_img')
                          .attr('src','images/down-arrow.png').addClass('pointer')
                          .click(function () {
                            $(this).parent().next().slideToggle(500);
                          });

    header.append(textBesidesTheImage);
    header.append(imageCollapse);

    var queryTag= $("<p></p>").addClass('query')
        .multiline(reformatQuery(query));
        addLogoToQuery(queryTag,apiBase);
    $(query_div).append(header);
    $(query_div).append(queryTag);
}

// add the logo to the query to execute it in dbpedia
function addLogoToQuery(queryTag,apiBase){

    var div= $('<div></div>')
    var openBase = $('<img></img>').addClass('pointer').attr('title','Click to use me in the base!');
    openBase.attr('src', 'images/useme.png');
    $(openBase).on( "click", function() {
        copyToClipboard($(this).parent().parent().text()); // to get the query text from the <p> tag (this.parent is <div>, and then it's <p>)
        alert("Just past the query in the new form!");
        window.open(base);
    })
    var seeResult = $('<img></img>').addClass('pointer').attr('title','Click to see my results in the base!');
        seeResult.attr('src', 'images/result.png');
        $(seeResult).on( "click", function() {
            var tempQuery = $(this).parent().parent().text();
            console.log(apiBase);
            window.open(encodeURI(apiBase+tempQuery));
        });
    div.append(openBase);
    div.append(seeResult);
    queryTag.append(div);
}

function copyToClipboard(text) {
  var element = $('<textarea>').appendTo('body').val(text).select();
  var successful = document.execCommand('copy');
  var msg = successful ? 'Text copied to clipboard!' : 'Unsuccessful copy of text to clipboard';
  console.log(msg);
  element.remove();
}

// add the th of the table
function addTitle(table,json,i){
    var row= $('<tr></tr>');
    for(var j=0; j<json.result[i].variables.length; j++){
        if(!isTrucVariables(json.result[i].variables[j])){
            var title = $('<th></th>').text(json.result[i].variables[j]);
            row.append(title);
        }
    }
    var imageCollapse =  $('<img></img>').addClass('showQuery_img')
                          .attr('src','images/down-arrow.png').addClass('pointer')
                          .click(function () {
                            $(this).parent().next().slideToggle(500);
                          });
    //row.append(imageCollapse);
    table.append(row);
}

// add results to the table created dynamically
function addResults(table,json,i){
    for(var j=0;j<json.result[i].results.length;j++){
        var row= $('<tr></tr>');
        for(var k=0;k<json.result[i].results[j].length;k++){
            var element=$('<td></td>');
            if(json.result[i].results[j][k] instanceof Array){ // when the variable is temporary, and it's not a real variable
                addURIElement(element,json.result[i].results[j][k][1].Result,'value');
                addPropretyOrLabel(element,json.result[i].results[j][k]);
                addLogoToTrucCell(element,json);
                row.append(element);
            }
            else{
               if(isSelectedVariable(json.result[i].variables,json.result[i].results[j][k].Variable)){
                  addURIElement(element,json.result[i].results[j][k].Result);
                  row.append(element);
               }
            }
        }
        table.append(row);
    }
}

// add the logo for coping the value to query
function addLogoToTrucCell(element){
      var img = $('<img></img>').addClass('pointer');
      img.attr({'src':'images/grab.png','title':'Click to use me!'});
      $(img).on( "click", function() {
        var $td =$(this).parent();
        var $th = $td.closest('table').find('th').eq($td.index());
        replaceTruc($th.text(),$td.find('.value').text())
        resetCellsImages($td);
        img.attr('src', 'images/after_grab.png');
      })
      .mousedown(function(){
         $(this).attr('src','images/grabbed.png' );
      })
      .mouseup(function(){
        $(this).attr('src','images/grab.png' );
      })
      element.addClass('truc_in_table');
      element.append(img);
}

// get all table results, and reset their image if it's in the same column as $td
function resetCellsImages($td){
    $('#query_results table').each(function(){
        $(this).find('td').each(function() {
            var colIndex=$(this).index();
            if(colIndex == $td.index()){
                $(this).find('img').attr('src', 'images/grab.png');
            }
        });
    });
}

// replace the truc on clicking the logo in the ceil
function replaceTruc(trucName, trucFound){
        if(isUrl(trucFound)){
            trucFound = "<"+trucFound+">";
        }
        if ($('#assist').is(':checked')) {
             var select = SIMPLEPARQL_QUERY_ASSISTED.select;
             elementToTextArea('select',select.replace(trucName,trucFound));
             var where = SIMPLEPARQL_QUERY_ASSISTED.where;
             elementToTextArea('where',where.replace(trucName,trucFound));
             var filter = SIMPLEPARQL_QUERY_ASSISTED.filter;
             elementToTextArea('filter',filter.replace(trucName,trucFound));
             var optional = SIMPLEPARQL_QUERY_ASSISTED.optional;
             elementToTextArea('optional',optional.replace(trucName,trucFound));
        }
        else{
            var query = SIMPLEPARQL_QUERY;
            var index = query.indexOf(trucName);
            var foundInQuery = getWord(index,trucName.length,query); // the truc found before adding the limiter " or
            if(index != -1 ){
                var beforechar = query.charAt(index-1);
                var afterchar = query.charAt(index+trucName.length);
                // add it to trucName to be removed when we will replace it
                if(beforechar != ' '){
                    trucName= beforechar + trucName;
                }
                if(afterchar != ' ' && afterchar !='.'){
                    trucName = trucName + afterchar;
                }
                if(!isUrl(foundInQuery)){
                    query = query.replace(trucName,trucFound); // replace the word
                    queryToTextArea(query);
                }
            }
        }
}

// get word givin the index (maybe in the middle of the word)
function getWord(index,endindex,query){
    var word = query.substring(index,index+endindex);
    var indexBefore=index;
    var indexAfter=index + endindex;
    var charBefore= query.charAt(indexBefore-1);
    while(charBefore.trim() != ''){
        word = charBefore +word;
        indexBefore = indexBefore-1;
        charBefore = query.charAt(indexBefore);
    }
    var charAfter= query.charAt(indexAfter);
    while(charAfter.trim() != '' && charAfter!= '.' ){
        word = word+charAfter;
        indexAfter = indexAfter+1;
        charAfter = query.charAt(indexAfter);
    }
    return word;
}

// add proprety tmp_var_1 tmp_var_2 OR label
function addPropretyOrLabel(element,arrayOfResult){
    if(arrayOfResult[2] != undefined){
      if(arrayOfResult[2].Position == "LABEL"){
         element.append( $('<b></b>').text(' has for label '));
         addURIElement(element,arrayOfResult[2].Result);
      }
      else if(arrayOfResult[2].Position == "TMP1"){
        element.append( $('<b></b>').text(' has for the proprety '));
        addURIElement(element,arrayOfResult[2].Result);
        element.append( $('<b></b>').text(' the value '));
        addURIElement(element,arrayOfResult[3].Result);
      }
    }
}

// check if it's one of selected variable
function isSelectedVariable(variables,variable){
    for(var i=0;i<variables.length;i++){
        if(variables[i] == variable){
            return true;
        }
    }
    return false;
}

// check if it's temp variable
function isTrucVariables(variable){
    trucVariables = ["SimplePARQL_","tmp_var1_","tmp_var2_","label_"]
    for(var i=0;i<trucVariables.length;i++){
        if(trucVariables[i] == variable.substring(0,variable.length-1)){
            return true;
        }
    }
    return false;
}

// add text with multiline in the tags
$.fn.multiline = function(text){
    this.text(text);
    this.html(this.html().replace(/\n/g,'<br/>'));
    return this;
}

// add text when it's an URL
function addURIElement(element,text,classAdded){
     classAdded = classAdded || '';
     if (isUrl(text)){
       var a = $('<a></a>').attr('href',text).text(text);
       if(classAdded !=''){
        a.addClass(classAdded);
       }
       element.append(a);
    }
    else{
        element.append(text);
    }
}

// check if text is url (basic form)
function isUrl(s) { return s.toLowerCase().startsWith('http') || s.toLowerCase().startsWith('<http');}

