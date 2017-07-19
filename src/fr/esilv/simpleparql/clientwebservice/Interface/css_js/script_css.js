
$("#send").kendoButton({enable: true});

$("#download").kendoButton({enable: true});

$("#download-XML").kendoButton({enable: true});

$("#clear").kendoButton({enable: true});

$('#assist').on('change', function () {
    if ($('#assist').is(':checked')) {
        $(document).trigger('assisted');
    } else {
        $(document).trigger('not_assisted');
    }
});

$(window).load(function() {
      if ($('#assist').is(':checked')) {
          $(document).trigger('assisted');
      } else {
          $(document).trigger('not_assisted');
      }
});

$(document).on('assisted', function() {
    $('#query_bloc').removeClass().addClass('assisted');
    $('.not_assisted_form').hide();
    $(".assisted_form").each(function(){
        $(this).show();
    });
});

$(document).on('not_assisted', function() {
     $('#query_bloc').removeClass().addClass('not_assisted');
      $(".assisted_form").each(function(){
             $(this).hide();
     });
     $('.not_assisted_form').show();
});

// used to toggle the prefixe
function toggleForm(elt,id){
	var filter = document.getElementById(elt);
	var img = document.getElementById(id);

	if(filter.style.display == 'block')
	{
		filter.style.display = 'none';
		img.src = 'images/plus.png';
	}
	else
	{
		filter.style.display = 'block';
		img.src = 'images/minus.png';
	}
}

// used to toggle filter, optional and limit
function toggleForm(elt){
	var filter = document.getElementById(elt);

	if(filter.style.display == 'block'){
		filter.style.display = 'none';
	}
	else{
		filter.style.display = 'block';
	}
}

// when the user waits for the query results
function waitFunction(){
    var parentOfImg = document.getElementById('wait');
    if($("#wait > img").length == 0){
    	var img = document.createElement("img");
    	img.src = "images/processing.gif";
    	parentOfImg.appendChild(img);
    }
    $('#query_results').empty();
    $('#idWebSocketTime').empty();
}

function clearFunction(){
    $('#wait').empty();
    $('#query_results').empty();
    $('#idWebSocketTime').empty();
}

/*--------------------- reformat the query to be formatted ----------------------*/

function reformatQuery(query){
    var result='';
    var brokeLine=false;
    var myArray = query.split(" ");
    for(var i=0;i<myArray.length;i++){
       if(isEscapeBefore(myArray[i]) && !brokeLine){
           result+= "\n" ;
           brokeLine = true;
       }
       else{
            brokeLine = false;
       }
       result += myArray[i] + " ";
       if(isEscapeAfter(myArray[i])){
            result+= "\n" ;
            brokeLine = true;
       }
    }
    return deleteFirstSpacesOrBreak(result);
}

function deleteFirstSpacesOrBreak(text){
    while(text[0] == "\n" || text[0]==" "){
        text=text.substring(1,text.length-1);
    }
    return text;
}

function isEscapeBefore(word){
    var myArray=["PREFIX","SELECT","FILTER","LIMIT","WHERE","}"]; // broke line before them!
    for(var i=0 ; i<myArray.length;i++){
        if(myArray[i].toLowerCase()==word.toLowerCase()){
            return true;
        }
    }
    return false;
}

function isEscapeAfter(word){
    var myArray=[".","{"]; // broke line after them!
    for(var i=0 ; i<myArray.length;i++){
        if(myArray[i].toLowerCase()==word.toLowerCase()){
            return true;
        }
        if(myArray[i].toLowerCase()== word.toLowerCase().slice(-1)){
            return true;
        }
    }
    return false;
}

// send the query to the text area
function queryToTextArea(query){ document.getElementById('query').value = reformatQuery(query);}

function elementToTextArea(id,text){ document.getElementById(id).value = text;}