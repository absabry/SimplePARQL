
var PORT = 1234;
var HOST= 'localhost';
var urlMySocket = "ws://"+HOST+":"+PORT+"/";
var mySocket;
var SIMPLEPARQL_QUERY_ASSISTED;
var SIMPLEPARQL_QUERY;
var dictionnaryTrucs={};

$("#query_form").submit(function( event ) {
     event.preventDefault();
     $('#clear').trigger('click');
     waitFunction();
     dictionnaryTrucs={};
     var queryInformations;
      if ($('#assist').is(':checked')) {
         queryInformations = createQueryFromAssisted();
     } else {
        queryInformations = createQueryFromNoAssisted();
     }
     SIMPLEPARQL_QUERY = queryInformations.query;


    var download_list = $("#download_list").data("kendoMultiSelect").value();
     callSocket(queryInformations, function (s) {
            var jsonResults = JSON.parse(s);
            if(jsonResults != undefined){
                for(var i in download_list){
                     download_display(download_list[i],jsonResults);
                }
            }
            $('#wait').empty();
     });
});

// not working yet, we should stop the socket and the web socket when clicking on clear!
$("#clear").click(function(){
    clearFunction();
    if(mySocket != undefined && mySocket.readyState != mySocket.CLOSED && mySocket.readyState != mySocket.CLOSING ){
         mySocket.send('stop');
         mySocket.close();
    }
});


// download files directly
function download_display(format,jsonResults){
    if(format == 'XML'){
        $("<a />", {
          "download": Date() + ".xml",
          "href" : "data:application/json," + encodeURIComponent(json2xml(jsonResults))
        }).appendTo("body")
        .click(function() {
           $(this).remove()
        })[0].click();
    }
    else if(format =='JSON'){
        $("<a />", {
          "download": Date() + ".json",
          "href" : "data:application/json," + encodeURIComponent(JSON.stringify(jsonResults,null,4))
        }).appendTo("body")
        .click(function() {
           $(this).remove()
        })[0].click()
    }
    else if(format == 'HTML'){
        result("#query_results",jsonResults);
    }
}


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
     var allPrefixes = '';
     for(var i=0;i<prefixes.length;i++){
        allPrefixes += ' PREFIX ' + prefixes[i];
     }
     // keep this infomrmations, to be used when we replace the truc in the original query
      SIMPLEPARQL_QUERY_ASSISTED= {
         'select':select,
         'where':where,
         'filter':filter,
         'optional':optional
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
                  "query":query,
                  "timeout" : $('#timeout').val()
     }
     return queryInformations;
}

// create array when it's assisted form
function createQueryFromNoAssisted(){
      return {
              "bases" : $("#list_bases").data("kendoMultiSelect").value(),
              "query":$('#query').val(),
              "timeout" : $('#timeout').val()
      }
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
    var whenstart = new Date();
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
        console.log("Socket closed");
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

function result($query_div,json){
    $($query_div).empty(); // clear the div

    if(json.hasOwnProperty('error')){
        addError($query_div,json.error);
        return;
    }

    addBase($query_div,json.base);

    var counter=1;
    for (var i=0; i <json.result.length;i++){
        if(json.result[i].results.length != 0){
            addQuery($query_div,json.result[i].query,counter,json.base.link,json.base.api);
            var $div_title = $('<div></div>').addClass('title');
            addTitle($div_title,json.result[i]);
            var $div_results=$('<div></div>').addClass('results');
            addResults($div_results,json.result[i]);
            $($query_div).append($div_title);
            $($query_div).append($div_results);
            $($query_div).append('<hr>');
            $($query_div).append('<br/>');
            counter++;
        }
    }
}

// add error (if exsits) to the html page
function addError($query_div,error){
    var $error = $('<h3></h3>').addClass('error');
    if(isJSON(error)){
        $error.multiline("Connection refused to the server. [Maybe the server isn't turned on..?] \n" + JSON.stringify(error));
    }
    else{
        $error.text(error);
    }
    $($query_div).append($error);
}

// add base to the html page
function addBase($query_div,base){
    var $base=$('<p></p>')
            .text('Results for the base ')
            .append('<b>'+base.name+'</b>')
            .append(' You can found it here: ');
            addURIElement($base,base.link);
            $($query_div).append($base);
}

// add query to the html page
function addQuery($query_div,query,counter,base,apiBase){
    var header = $('<div></div>').addClass('query_header tooltip').attr('title',query.substring(0,50)+'...\nClick on the arrow to see more.');
    var textBesidesTheImage = $('<h5></h5>').text('SPARQL query n°'+counter).css('display','inline');
    var imageCollapse =  $('<img></img>').addClass('collapse pointer')
                          .attr('src','images/down-arrow.png')
                          .click(function () {
                            var $toggled = $(this).parent().next();
                            $toggled.slideToggle(500,toggleQuery($(this),$toggled.is( ":visible" ),300));
                          });
    header.append(textBesidesTheImage);
    header.append(imageCollapse);

    var $query= $("<p></p>").addClass('query')
        .multiline(reformatQuery(query));
    addLogoToQuery($query,base,apiBase);
    $($query_div).append(header);
    $($query_div).append($query);
}

function toggleQuery($img,visible){
    setTimeout(function() {
       if(visible){
            $img.attr('src','images/down-arrow.png');
       }
       else{
            $img.attr('src','images/up-arrow.png');
       }
    }, 300);
}

// add the logo to the query to execute it in the api
function addLogoToQuery($query,base,apiBase){

    var $div= $('<div></div>');
    var $openBase = $('<img></img>').addClass('pointer tooltip');
    $openBase.attr('src', 'images/copy_me.png');
    $openBase.tooltipster({
        content: 'Click to use me in the base!',
        autoClose: 'false',
    });
    $($openBase).on( "click", function() {
        var msg = copyToClipboard($(this).parent().parent()); // to get the query text from the <p> tag (this.parent is <div>, and then it's <p>)
        setTimeout(function() {
            window.open(base);
        }, 1000);
        $(this).tooltipster('content', msg);
    })
    .mousedown(function(){
             $(this).attr('src','images/copy_me_pressed.png' );
    })
    .mouseup(function(){
        $(this).attr('src','images/copy_me.png' );
     })
    .mouseout(function(){
             $(this).tooltipster('content', 'Click to use me in the base!');
     })

    var $seeResult = $('<img></img>').addClass('pointer tooltip').attr('title','Click to see my results in the base!');
        $seeResult.attr('src', 'images/result.png');
        $($seeResult).on( "click", function() {
            var tempQuery = $(this).parent().parent().clone().find('br').prepend(' ').end().text();;
            window.open(apiBase+encodeURIComponent(tempQuery));
        });
    $div.append($openBase);
    $div.append($seeResult);
    $query.append($div);
}

function copyToClipboard($parent) {
  var filtredText = $($parent).clone().find('br').prepend('\r\n').end().text();  // trasnform <br> tag to \r\n tag!
  var element = $('<textarea>').appendTo('body').val(filtredText).select();
  var successful = document.execCommand('copy');
  var msg = successful ? 'Text copied to clipboard!' : 'Unsuccessful copy of text to clipboard';
  element.remove();
  return msg;
}

// add the th of the table
function addTitle($div,result){
    var $table=$('<table></table>');
    var $thead=$('<thead></thead>');
    var $row= $('<tr></tr>');
    for(var j=0; j<result.variables.length; j++){
        var $title = $('<th></th>').text(result.variables[j]);
        // add the arrow to the last title (to be able to collapse and expand results)
        if(j == result.variables.length -1){
            var imageCollapse =  $('<img></img>').addClass('collapse tooltip')
                                  .attr({'src':'images/arrow-up.png','title':'Show results.'}).addClass('pointer')
                                  .click(function () {
                                        var $toggled = $(this).closest('.title').next('.results');
                                        $toggled.slideToggle(1200,toggleTable($(this),$toggled.is( ":visible" )));
                                  });
            $title.append(imageCollapse);
        }
        $row.append($title);
    }
    $thead.append($row);
    $table.append($thead);
    $div.append($table);
}

// change image when slideToggle after animation time.
function toggleTable($img,visible){
    setTimeout(function() {
       if(visible){
            $img.attr('src','images/arrow-table.png');
       }
       else{
            $img.attr('src','images/arrow-up.png');
       }
    }, 800);
}

// add results to the table created dynamically
function addResults($div,result){
    var $table = $('<table></table>');
    var $tbody = $('<tbody></tbody>');
    for(var j=0;j<result.results.length;j++){
        var $row= $('<tr></tr>');
        for(var k=0;k<result.results[j].length;k++){
            var element=$('<td></td>');
            if(result.results[j][k] instanceof Array){ // when the variable is temporary, and it's not a real variable
                var currentResult = result.results[j][k][1].Result;
                addURIElement(element,currentResult,'value');
                addPropretyOrLabel(element,result.results[j][k]);
                if(isUrl(currentResult)){ // if it's a label and not URI, we dont add the logo to the cell
                    addLogoToTrucCell(element);
                }
                $row.append(element);
            }
            else{
               if(isSelectedVariable(result.variables,result.results[j][k].Variable)){
                  addURIElement(element,result.results[j][k].Result);
                  $row.append(element);
               }
            }
        }
        $tbody.append($row);
        $table.append($tbody);
        $div.append($table);
    }
}

// add the logo for coping the value to query
function addLogoToTrucCell(element){
      var img = $('<img></img>').addClass('pointer tooltip');
      img.attr({'src':'images/grab.png','title':'Click to use me!'});
      $(img).on( "click", function() {
        var $td =$(this).parent();
        var $th = $td.closest('.results').prev().find('th').eq($td.index());
        var value = $td.find('.value').text();
        if(isUrl(value)){value = "<"+value+">";}
        dictionnaryTrucs[$th.text()] = value;
        replaceTrucs();
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



/*----------------------------------- Replace trucs from the query with the URL result---------------------------------*/


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
function replaceTrucs(){
    if ($('#assist').is(':checked')) {
         (SIMPLEPARQL_QUERY_ASSISTED.select,'select');
         replaceToTextArea(SIMPLEPARQL_QUERY_ASSISTED.where,'where');
         replaceToTextArea(SIMPLEPARQL_QUERY_ASSISTED.filter,'filter');
         replaceToTextArea(SIMPLEPARQL_QUERY_ASSISTED.optional,'optional');
    }
    else{
        replaceToTextArea(SIMPLEPARQL_QUERY,'query');
    }
}

// put replaced text to the text area
function replaceToTextArea(original,id){
    var query = original;
    for (var truc in dictionnaryTrucs) {
        query = replaceElement(query,truc,dictionnaryTrucs[truc]);
    }
    elementToTextArea(id,query);
}

// replace element, with the corresponding one
function replaceElement(query,trucName,trucFound){
    var index = query.indexOf(trucName);
    var foundInQuery = getWord(index,trucName.length,query); // the truc found before adding the limiter " or
    if(!isUrl(foundInQuery) && index !=-1){
        return query.replaceAll(wordToBeReplaced(query,foundInQuery,index,trucName),trucFound); // replace the word
    }
    return query;
}

String.prototype.replaceAll = function (find, replace) {
    var str = this;
    return str.replace(new RegExp(find, 'g'), replace);
};

// get word givin the index (maybe in the middle of the word)
function getWord(index,endindex,query){
    var word = query.substring(index,index+endindex);
    var indexBefore=index;
    var indexAfter=index + endindex;
    var charBefore= query.charAt(indexBefore-1);
    while(charBefore.trim() != ''){
        word = charBefore + word;
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

// clean word that will be replaced
function wordToBeReplaced(query,foundInQuery,index,trucName){
     var beforechar = query.charAt(index-1);
     var afterchar = query.charAt(index+trucName.length);
     if(beforechar != ' '){
        trucName= beforechar + trucName;
     }
     if(afterchar != ' ' && afterchar !='.'){
        trucName = trucName + afterchar;
     }
     return trucName;
}

/*----------------------------------- Build table results---------------------------------*/
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

// replace truc of old query with new values in the ASSISTED MODE
function elementToTextArea(id,text){
    if(id == 'query'){
        text = reformatQuery(text);
    }
    document.getElementById(id).value = text;
}
