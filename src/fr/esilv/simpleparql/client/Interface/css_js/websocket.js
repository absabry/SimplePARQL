
var PORT = 1234;
var HOST= 'localhost';
var urlMySocket = "ws://"+HOST+":"+PORT+"/";
var mySocket;
var SIMPLEPARQL_QUERY_ASSISTED; // keep those informations for replacing trucs correctly
var SIMPLEPARQL_QUERY; // keep those informations for replacing trucs correctly
var dictionnaryTrucs={}; // handle replacing trucs
var pages = ["FIRST","SECOND","THIRD"]; // handle pages displayed
var page_counter = 0; // handles pages displayed
var queryInformations; // to change pages, when we click on next or previous.
var haveResult = false;

// submit the form and request the Java server
$("#query_form").submit(function( event ) {
     event.preventDefault();
     if (event.originalEvent !== undefined) { // the first time to execute the query, not previous or next page
         dictionnaryTrucs={};
         if ($('.not_assisted_form').is(":visible")) {
              if($('#query').val().toLowerCase().indexOf('limit') == -1){ // check for the limit keyword in the query
                  if(confirm('Are you sure you want to proceed this query without any limit?')){
                     queryInformations = createQueryFromNoAssisted();
                  }
                  else{ // stop submitting form
                      return false;
                  }
               }
               else{
                    queryInformations = createQueryFromNoAssisted();
               }
         }else {
             if($('#limit').val().trim() ==''){ // check for the limit field
                if(confirm('Are you sure you want to proceed this query without any limit?')){
                     queryInformations = createQueryFromAssisted();
                }
                else{ // stop submitting form
                    return false;
                }
             }
             else{
                queryInformations = createQueryFromAssisted();
             }
         }
         $('#clear').trigger('click');
     }
     queryInformations.page=pages[page_counter];
     waitFunction();

    var output_list = $("#output_list").data("kendoMultiSelect").value();
     callSocket(queryInformations, function (s) {
            for(var i in output_list){
                 getResults(output_list[i],s);
            }
            $('#wait').empty();
     });
});

// stop the socket and the web socket when clicking on clear!
$("#clear").click(function(){
    clearFunction();
    page_counter=0;
    haveResult=false;
    if(mySocket != undefined && mySocket.readyState != mySocket.CLOSED && mySocket.readyState != mySocket.CLOSING ){
         mySocket.send('stop');
         mySocket.close();
    }
});

$("#stop").click(function(){
    if(mySocket != undefined && mySocket.readyState != mySocket.CLOSED && mySocket.readyState != mySocket.CLOSING ){
         mySocket.send('stop');
         mySocket.close();
    }
    $('#wait').empty();
});

// get the next page results
$("#nextpage").click(function(){
    page_counter= page_counter+1;
    $(this).hide();
    $("#previouspage").hide();
    $('#query_form').trigger('submit');
});

// get the previous page results
$("#previouspage").click(function(){
    page_counter= page_counter-1;
    $(this).hide();
    $("#nextpage").hide();
    $('#query_form').trigger('submit');
});

/* ---------------- Get the POST fields and manipulate it before creating the socket ---------------------------*/

// create JSON request when it's in the assisted form
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
     SIMPLEPARQL_QUERY_ASSISTED = {
         'select':select,
         'where':where,
         'filter':filter,
         'optional':optional
      }
     var query = allPrefixes
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

// create JSON request when it's the not assisted form
function createQueryFromNoAssisted(){
     var query = $('#query').val();
     // add escape before the closing accolades if it does not exist..
     // some bugs are generated when this escape isn't here...
     var escapeBefore= true;
     for(var i=0; i< query.length; i++){
        if(query[i]=='}'){
            if(query[i-1] != ' ' && query[i-1] != "." && query[i-1] !="\n"){
                escapeBefore=false;
                break;
            }
        }
     }
     // add the escape if needed
     if(!escapeBefore){
        $('#query').val(query.replace("}", " }"));
     }
     SIMPLEPARQL_QUERY = $('#query').val();

      return {
              "bases" : $("#list_bases").data("kendoMultiSelect").value(),
              "query":  $('#query').val(),
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

/* ---------------------------------------------------- The webscoket ----------------------------------------------------------------------*/

// create the websocket
function callSocket (s, callback) {
    var html = $("#output_list").data("kendoMultiSelect").value().indexOf("HTML") >= 0;  // HTML is checked or not
    $query_div = "#query_results"; // result's div, if html is checked
    var counter = {};  // should be an object to be passed by ref in javascript functions.
    counter.value=-1;  // to count the queries that have found at leasr ONE result
    var allBases = []; // final json
    var currentBase; // json object representing the current base
    var baseIndex = -1; //current base index, to create the final json object
    var whenstart = new Date(); // to compute server time to give all response
    var msg=''; // message concatened till the protocol end (Iend,Eend,etc...)
    mySocket = new WebSocket (urlMySocket)

    mySocket.onopen = function (evt) { // send client's request to the web service
        mySocket.send(JSON.stringify(s)+"\n");
    };

    mySocket.onmessage = function (evt) {
        msg += evt.data;
        if(msg.length > 3){
            switch(evt.data.substring(evt.data.length-4)){
                case "Iend": // Informations part of this [new] base
                     console.log("have result informations " + haveResult)
                     baseIndex +=1;
                     if(counter.value != -1){ // to not check the first time we visit this bloc..!
                       noResultInPreviousBase(counter); // no results found in the previous base... Will be used also in the last event, to handle the last base
                     }
                     console.log("have result informations " + haveResult)
                     counter.value = 1; // counter is back to 1 for the beggining of the new base
                     currentBase = {}; // intialize a new json object to this base
                     var message = msg.substring(0,msg.length-4); //to delete the end message
                     currentBase =  JSON.parse(message); // informations got, will be the first items of the current base object
                     currentBase.result  = []; // initialize the results
                     allBases.push(currentBase); // add the current base to the final object
                     console.log("One message has just been received! [Informations part]");
                     if(html){
                        informationToInterface(msg,currentBase);
                     }
                     msg ='';
                    break;
                case "Eend": // Error part of this base
                     var message = msg.substring(0,msg.length-4); //to delete the end message
                     console.log("One message has just been received! [Error part]");
                     currentBase = JSON.parse(message);
                     allBases[baseIndex] = currentBase
                     if (html){ // add an error if one occurs instead of results
                        addError($query_div,currentBase.error);
                     }
                     msg ='';
                    break;
                case "Rend": // Results part of this base
                    var message = msg.substring(0,msg.length-4); //to delete the end message
                    console.log("One message has just been received! [Part from result]");
                    result =  JSON.parse(message) // get ONE SPARQL query generated.
                    currentBase.result.push(result);
                    if(html){
                        queryResultToInterface(result,msg,currentBase,counter); // append results to the interface
                    }
                    msg='';
                    break;
                default:
                    break;
            }
        }
        if(evt.data=="null"){ // after sending all datas
            console.log("have result " + haveResult)
            noResultInPreviousBase(counter); // no results found in the last base... Was handled after each base in the informations part!
            console.log("have result " + haveResult)
            console.log("Datas received! We'll close connection with the web serivce.");
            console.log(allBases);
            $('#wait').empty();
            if(!haveResult){
                $("#nextpage").hide(); // keep it? the first page have no result for all bases, so we don't need the rest.
                var $noresultAtALL = $('<h3></h3>').addClass("cente error")
                                    .text("No results found in ANY base you've selected. Please change your SimplePARQL query, or choose another bases.");
                $($query_div).append($noresultAtALL);
            }
            callback (allBases); // save in JSON or XML format
            mySocket.close ();
        }
    };

    mySocket.onerror = function (event) {
        var result = $('<h3></h3>').addClass('error')
                    .text("There was an error with your websocket. [Maybe the webservice isn't connected to the server..?]");
        $($query_div).append(result);
        $('#wait').empty();
    };

    mySocket.onclose = function(event){
        // detect when the socket is closing
        $("#idWebSocketTime").text ("The call to the server took " + secondsSince (whenstart) + " secs to perform this query.");
        console.log("Socket closed");
    }
}

// send informations of the base to the interface
function informationToInterface(msg, currentBase){
    if(!currentBase.isSPARQL){
            if(page_counter == 2){ $("#nextpage").hide();}
            else{$("#nextpage").show();}
            if(page_counter == 0){$("#previouspage").hide();}
            else{ $("#previouspage").show();}
    }
    addBase($query_div,currentBase.base);
}

//send query results to the interface
function queryResultToInterface(result,msg,currentBase, counter){
    if(result.results.length != 0){ // no result ==> no table
       addQuery($query_div,result.query,counter.value,currentBase.base);
       // two tables are created to handle the table's toogle
       var $div_title = $('<div></div>').addClass('title');// table containing the titles (ths)
       addTitle($div_title,result);
       var $div_results=$('<div></div>').addClass('results');// table containing the results (tds)
       addResults($div_results,result);
       $($query_div).append($div_title);
       $($query_div).append($div_results);
       $($query_div).append('<hr>');
       $($query_div).append('<br/>');
       counter.value++;
    }
}

// handle when we dont find any results in one base...
function noResultInPreviousBase(counter){
    if(counter.value == 1){
        var $noresult = $('<h3></h3>').addClass("center")
                    .text("No results found in this base.");
        $($query_div).append($noresult);
    }
    else{
        haveResult = true; // to hide the next button, if we dont have any result in the page (all bases included)!
    }
}


// compute the seconds since launching the websocket
function secondsSince (when) {
    var now = new Date ();
    when = new Date (when);
    return ((now - when) / 1000);
}


/*--------------------------------------------------Builds results in different formats ------------------------------------------*/

// get results of formats we ask for (JSON, XML or HTML)
function getResults(format,jsonResults){
    if(format == 'XML'){
        $("<a />", {
          "download": Date() + ".xml",
          "href" : "data:application/json," + encodeURIComponent(jsonToXmlConverter(jsonResults))
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
}

// convert JSON to XML
function jsonToXmlConverter(json) {
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

// well formated XML file
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


/* --------------------------------------- Build the result tags and elements for the HTML file -------------------------------------------------------------*/

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

// add error (if there is one) to the html page
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
            addURIElement($base,base.plateforme);
            $($query_div).append($base);
}

// add query to the html page
function addQuery($query_div,query,counter,base){
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
    addLogoToQuery($query,base);
    $($query_div).append(header);
    $($query_div).append($query);
}

// toggle the query when we click in the arrow
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
function addLogoToQuery($query,base){

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
            window.open(base.plateforme);
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
    $div.append($openBase);

    // if this base contains any API, we'll add it to the interface to prove it to the user
    if(base.api != null){
        var $seeResult = $('<img></img>').addClass('pointer tooltip').attr('title','Click to see my results in the base!');
            $seeResult.attr('src', 'images/result.png');
            $($seeResult).on( "click", function() {
                var tempQuery = $(this).parent().parent().clone().find('br').prepend(' ').end().text();;
                window.open(base.api+encodeURIComponent(tempQuery));
            });
            $div.append($seeResult);
    }

    $query.append($div);
}

// copy file to clipboard and get the result of this copy
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
        var $title = $('<th></th>');
        if(typeof result.variables[j] == 'object' ){ // it's a SimplePARQL truc
            $title.text(result.variables[j].name);
            dictionnaryTrucs[result.variables[j].name]=result.variables[j];
            dictionnaryTrucs[result.variables[j].name].used = getOriginalTruc(result.variables[j].name);
        }
        else{ // it's a SPARQL variable
            $title.text(result.variables[j]);
        }

        // add the arrow to the last title (to be able to collapse and expand table results)
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
                var currentResult = result.results[j][k][1].SPARQLResult;
                addURIElement(element,currentResult,'value');
                addPropretyOrLabel(element,result.results[j][k]);
                if(isUrl(currentResult)){ // if it's a label and not URI, we dont add the logo to the cell
                    addLogoToTrucCell(element,result.variables);
                }
                $row.append(element);
            }
            else{
               if(isSelectedVariable(result.variables,result.results[j][k].Variable)){
                  addURIElement(element,result.results[j][k].SPARQLResult);
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
function addLogoToTrucCell(element,titles){
      var img = $('<img></img>').addClass('pointer tooltip');
      img.attr({'src':'images/grab.png','title':'Click to use me!'});
      $(img).on( "click", function() {
        var $td =$(this).parent();
        var $th = $td.closest('.results').prev().find('th').eq($td.index());
        var value = $td.find('.value').text();
        if(isUrl(value)){value = "<"+value+">";}

        if(dictionnaryTrucs[$th.text()].used == value) { // already clicked, we'll go back to the Truc
            dictionnaryTrucs[$th.text()].used = getOriginalTruc($th.text());
            replaceTrucs();
            resetCellsImages($td);
        }
        else{
            //Truc clicked, should replace it with new value
            dictionnaryTrucs[$th.text()].used = value;
            replaceTrucs();
            resetCellsImages($td);
            img.attr('src', 'images/after_grab.png');
        }
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

function getOriginalTruc(text){
    var originalText = text;
    if(dictionnaryTrucs[text].type =="STRING"){
        originalText = "\""+originalText+"\"";
    }
    else if(dictionnaryTrucs[text].type =="SEVERALWORDS"){
        originalText = "/"+originalText+"/";
    }
    if(dictionnaryTrucs[text].lang != null){
        originalText = originalText+"@"+dictionnaryTrucs[text].lang;
    }
    return originalText;
}

/*----------------------------------- Replace trucs from the query with the URL result to the HTML results---------------------------------*/


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
    if ($('.not_assisted_form').is(":visible")) {
        replaceToTextArea(SIMPLEPARQL_QUERY,'query');
        console.log($("#query").val());
    }
    else{
         replaceToTextArea(SIMPLEPARQL_QUERY_ASSISTED.select,'select');
         replaceToTextArea(SIMPLEPARQL_QUERY_ASSISTED.where,'where');
         //replaceToTextArea(SIMPLEPARQL_QUERY_ASSISTED.filter,'filter'); // not used now, the trucs cannot be in the filter field, but will be soon
         replaceToTextArea(SIMPLEPARQL_QUERY_ASSISTED.optional,'optional');
    }
}

// put replaced text to the text area
function replaceToTextArea(original,id){
    var query = original;
    if(query.trim() !=''){
        for (var truc in dictionnaryTrucs) {
            query = replaceElement(query,truc,dictionnaryTrucs[truc].used);
        }
        elementToTextArea(id,query);
    }
}

// replace element, with the corresponding one
function replaceElement(query,trucName,trucFound){
    var index = query.indexOf(trucName);
    var foundInQuery = getWord(index,trucName.length,query); // the truc found before adding the limiter " or /
    if(!isUrl(foundInQuery) && index !=-1){
        return query.replaceAll(foundInQuery,trucFound); // replace the word
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
    var indexBefore=index-1;
    var indexAfter= index + endindex;
    var charBefore= query.charAt(indexBefore);
    while(charBefore.trim() != ''){
        word = charBefore + word;
        indexBefore = indexBefore-1;
        charBefore = query.charAt(indexBefore);
    }
    var charAfter=query.charAt(indexAfter);
    while(charAfter.trim() != '' && charAfter!= '.' ){
    // after the truc we can found a point, space or closing accolades
        word = word+charAfter;
        indexAfter = indexAfter+1;
        charAfter = query.charAt(indexAfter);
    }
    return word;
}

/*----------------------------------- Build table results---------------------------------*/
// add proprety tmp_var_1 tmp_var_2 OR label
function addPropretyOrLabel(element,arrayOfResult){
    if(arrayOfResult[2] != undefined){
      if(arrayOfResult[2].Position == "LABEL"){
         element.append( $('<b></b>').text(' has for label '));
         addURIElement(element,arrayOfResult[2].SPARQLResult);
      }
      else if(arrayOfResult[2].Position == "TMP1"){
        element.append( $('<b></b>').text(' has for the proprety '));
        addURIElement(element,arrayOfResult[2].SPARQLResult);
        element.append( $('<b></b>').text(' the value '));
        addURIElement(element,arrayOfResult[3].SPARQLResult);
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
function isUrl(s) {
if (s==null){
 return false;
}
return s.toLowerCase().startsWith('http') || s.toLowerCase().startsWith('<http');
}

// replace truc in the textarea (assisted and not_assisted mode)
function elementToTextArea(id,text){
    if(id == 'query'){
        text = reformatQuery(text);
    }
    document.getElementById(id).value = text;
}


