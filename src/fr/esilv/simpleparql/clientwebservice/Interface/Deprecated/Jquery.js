/*** Web Service Access ***/
/*** version 2015-07-23 ***/


// Global variables

// en local
//var uri = "ws://localhost:4500";

// en production
var uri = "ws://uep.labs.esilv.fr:80";

var compt;
var test = true;
var debugLevels = { // DEBUG < TRACE < INFO < WARNING < ERROR < FATAL
	DEBUG: 1,
	TRACE: 2,
	INFO: 3,
	WARNING: 4,
	ERROR: 5,
	FATAL: 6,
	NONE: 99
}
var debugLevel = debugLevels.DEBUG;

var ws_socket = null;


$(document).ready(function(){
	connect();

	$('#message').focus();

	$('#query_form').submit(function(){
		compt = 1;
		sendMessage();

	});

	$('#clear').click(function(){
		clearScreen();
	});
});


function writeInformation(level, msg) {
	$('#wait').empty();
	if( debugLevel <= level )
	{
		l = "";
		switch(level)
		{
			case debugLevels.DEBUG:
				l = "[DEBUG]";
				break;
			case debugLevels.TRACE:
				 l = "[TRACE]";
				 break;
			case debugLevels.INFO:
				 l = "[INFO]";
				 break;
			case debugLevels.WARNING:
				l = "[WARNING]";
				break;
			case debugLevels.ERROR:
				l = "[ERROR]";
				break;
			case debugLevels.FATAL:
				l = "[FATAL]";
				break;
		}

		var screen = $('#information');
		screen.append('<p>'+ l + ' ' + msg +'</p>');
	}
}

function writeIntoResults(element) {
	$('#wait').empty();
	var screen = $('#query_results');
	screen.append(element);
}

function clearInformation(){
	$('#information').empty();
}

function clearResults(){
     	$('#query_results').empty();
}

function clearScreen(){
	clearInformation();
	clearResults();
}



function connect(){


	if( ws_socket != null )
	{
		ws_socket.close();
	}

	ws_socket = new WebSocket(uri);

	ws_socket.onopen = function(){
		//writeInformation(debugLevels.TRACE , 'Connection opened on the Web-service ' + uri);
	}

	ws_socket.onerror = function(){
		writeInformation(debugLevels.WARNING , 'Remote SPARQL server(s) are unreachable! (Please, try again later.)');
	}

	ws_socket.onclose = function(){
		//writeInformation(debugLevels.TRACE , 'Connection closed with the Web-service ' + uri);
		//writeInformation(debugLevels.ERROR ,'Woops! Connection closed. Please reload the page.');
	}

	// On received message
	ws_socket.onmessage = function(e){

		// TODO fonctionnne ?
		//var compt = 0;

		var obj = jQuery.parseJSON( e.data );

// 		if( obj.hasOwnProperty('toto') ) {  }

		if ( obj.AnswerType === 'Result' ) // result from a (SimplePARQL or SPARQL) query
		{
			writeIntoResults('<br> <B> <span class="results_header">'
				+ '<em>Set of resulats #' + compt + '</em>'
				+ ' (from endpoint ' + href(obj.Endpoint) + ')'
				+ '</span> </B> <br> <br>');
			compt++;

			var nodeTable = document.createElement("table");

			var nodeTR1 = document.createElement("tr");

			// 1Ã¨re ligne du tableau du tableau HTML

			var nbColomns = 0;

			//for( var i = 0 ; i < obj.Variables.length ; i++) { // ???
			for(var key in obj.Variables) {

				var variableName = obj.Variables[key];
				if ( typeof(variableName) === 'string'){
					var nodeTH = document.createElement("th");
					nodeTH.innerHTML = variableName;
					nodeTR1.appendChild( nodeTH );

				}

				else if ( typeof(variableName) === 'object'){
					var nodeTH = document.createElement("th");
					var tempvar = "";
					for( var key01 in variableName ) {

						tempvar = tempvar.concat(" ");
						tempvar = tempvar.concat(variableName[key01]);

					}
					nodeTH.innerHTML = tempvar;
				      	nodeTR1.appendChild( nodeTH );

				}
				nbColomns++;
			}
			nodeTable.appendChild( nodeTR1 );

			// autres lignes du tableau HTML

			for ( var key in obj.Results) {		 // pour chaque triplet
				var result = obj.Results[key];
				var nodeTR = document.createElement("tr");

				for ( var keyResulat in result) {		// pour chaque Ã©lÃ©ment du triplet (sujet,prÃ©dicat, objet)
					var tripleElement = result[keyResulat];

					if ( typeof(tripleElement) === 'string'){    // verifier si l'Ã©lÃ©ment est un string ou composÃ© de variables temporaires
						var nodeTD = document.createElement("td");
 						nodeTD.innerHTML = href(tripleElement);
 						nodeTR.appendChild( nodeTD );

 					}
 					else if ( typeof(tripleElement) === 'object'){
						var nodeTD = document.createElement("td");
						var tempvar = "";
						var compteur = 0;

						for( var key01 in tripleElement ) {
						       if ( compteur === 1){
								 tempvar = tempvar.concat(" ");
								 tempvar = tempvar.concat('<b>because has label</b>');
								 tempvar = tempvar.concat(href(tripleElement[key01]));
						       }
						       else
						       {
								  tempvar = tempvar.concat(" ");
								  tempvar = tempvar.concat(href(tripleElement[key01]));
						       }
						compteur ++;

						}
 						nodeTD.innerHTML = tempvar;
 						nodeTR.appendChild( nodeTD );
					}

				}
				nodeTable.appendChild( nodeTR );
			}

			 writeIntoResults( nodeTable );


		}
		else if( obj.AnswerType === 'WarningOrErreur') {

		        writeIntoResults('<br> <b> <span class="results_header">'
					      + '<em>Information </em>'
					      + '</span> </b> <br>');

			writeIntoResults('<span class="results_header">'
					+ ' Error: timeout, or the endpoint '
					+ href(obj.Endpoint)
					+ ' is unreachable. '
					+ '</span>  <br> <br>');

		}
 	}
}

function close(){
	writeInformation("[DEBUG] ws_socket fermÃ©e");
}

function abort(){
	ws_socket.send("abort");
}
function href(element){

	if (element.indexOf("http") === 1){
		element = '<a href='+ element+'>' + element + '</a>';
	}
	return element;


}


function sendMessage(){

    var Query   = $("#Query").val();
	var BASES   = $("#list_bases").val();
	var PREFIX  = $("#prefix").val();
	var SELECT  = $("#select").val();
	var WHERE   = $("#where").val();
	var FILTER  = $("#filter").val();
	var OPTIONAL= $("#optional").val();
	var LIMIT   = $("#limit").val();
	var EXTPREFIX = $("#list_prifix").val();



	if (Query == null){

		if(BASES == null) alert( "Please select only one base" );

		if(WHERE == '' || SELECT=='')
		{
			alert( "Please complete the fields : Select, Where" );
		}
		else
		{
			var tab= new Array();
			tab = WHERE.split("\n");
			var str = "";
			var nb = tab.length;


			for (i = 0; i < nb; i++) {

				var elt = tab[i].trim();  // ne pas oublier trim pour bien enlever \n !!!

				if( elt.length !==0 ) {

					if( i !== nb -1 )
					{
						elt = elt +"\EOL";
					}

					str = str + elt;
				}

			}

			var res = str.trim();
			var tab = new Array();
			tab = res.split("\EOL");
			var Line = tab.join(" ");
			Line = Line.replace("  ", " ");
			var T = new Array();
			var Indice = 0;
			var IndiceLine = 0;
			T[Indice] = "";

			while (IndiceLine < Line.length)
			{
				Temp = Line[IndiceLine];

				if (Temp === '"')
				{
					T[Indice] =  T[Indice] + Temp;
					IndiceLine =IndiceLine + 1;
					Temp = Line [IndiceLine];

					while (Temp!=='"')
					{
						T[Indice] =  T[Indice] + Temp;
						IndiceLine = IndiceLine + 1;
						Temp = Line [IndiceLine];
					}

					T[Indice] = T[Indice] + Temp;

				}
				else if (Temp === '(')
				{
					T[Indice] =  T[Indice]+ Temp;
					IndiceLine ++;
					Temp = Line [IndiceLine];

					while (Temp!=')')
					{
						T[Indice] =  T[Indice]+ Temp;
						IndiceLine =IndiceLine + 1;
						Temp = Line [IndiceLine];

					}

					T[Indice] =  T[Indice]+ Temp;

				}
				else if (Temp ===' ')
				{
					Indice = Indice + 1;
					T[Indice] = null;
				}

				else
				{
					T[Indice] =  T[Indice]+ Temp;
				}

				IndiceLine = IndiceLine + 1;

			}


			if( (T.length % 3 != 0) && (T.length != 1) )
			{
				  writeInformation(debugLevels.WARNING , "The triple must be composed of one part (subject) or three parts (subject, predicat, and complement) which together consiste a sentence") ;
			}
			else
			{
				if (T.length == 1 ){

					WHERE0="";
					WHERE0 =  T[0]   +' '
							+ "?Property"  + ' '
							+ "?Value" ;

					var message= JSON.stringify ({
					QueryType:	"SimpleparqlQuery",
					ListOfBases:	BASES,
					Prefix:  	PREFIX,
					Select:  	SELECT,
					Where:   	WHERE0,
					Filter: 	FILTER,
					Optional:	OPTIONAL,
					Limit:  	LIMIT,
					ExtendPrefix:	EXTPREFIX
					});

					//writeInformation(debugLevels.DEBUG , 'Msg send: ___'+ message + '___');
					//writeInformation(debugLevels.DEBUG , 'Msg size : '+ message.length);

					ws_socket.send(message);

				}

				else if (T.length % 3 === 0 ){

					var message = JSON.stringify ({
						QueryType:	"SimpleparqlQuery",
						ListOfBases:BASES,
						Prefix:  	PREFIX,
						Select:  	SELECT,
						Where:   	WHERE,
						Filter: 	FILTER,
						Optional:	OPTIONAL,
						Limit:  	LIMIT,
						ExtendPrefix:	EXTPREFIX
						});

					ws_socket.send(message);
				}

				if( debugLevel == debugLevels.DEBUG ) {
					clearResults();
				}
				else {
					clearScreen();
				}
			}
		}
	}
	else
	{
		if(BASES == null)
		{
			alert( "Please select only one base" );
		}
		var message= JSON.stringify ({
						QueryType:	"SparqlQuery",
						ListOfBases:	BASES,
						Query : 	Query,
						});

		writeIntoResults(message);
		ws_socket.send(message);

		if( debugLevel == debugLevels.DEBUG )
		{
			clearResults();
		}
		else
		{
			clearScreen();
		}



	}

}
