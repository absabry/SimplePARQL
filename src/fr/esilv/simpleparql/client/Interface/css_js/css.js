
/*-------------------------------------------------- Kendu buttons -----------------------------------------------*/
$("#send").kendoButton({enable: true});

$("#download").kendoButton({enable: true});

$("#download-XML").kendoButton({enable: true});

$("#clear").kendoButton({enable: true});

/*-------------------------------------------------loading events --------------------------------------------------------*/

// on toggle assisted mode buttton
$(document).ready(function(){
    $('#assisted_toggler').click(function(){
        clearFunction();
        $(this).toggleClass('off');
        if($(this).hasClass('off')){
            $(this).text("OFF");
            $(document).trigger('not_assisted');
        }
        else{
         $(this).text("ON");
         $(document).trigger('assisted');
        }
    });
});
// on load, if he's reloading after assisted mode, it should be kept there
$(window).load(function() {
      if ($('#assisted_toggler').hasClass('off')) {
          $(document).trigger('not_assisted');
      } else {
          $(document).trigger('assisted');
      }
});

// fire the assisted mode
$(document).on('assisted', function() {
    $('#query_bloc').removeClass().addClass('assisted');
    $('.not_assisted_form').hide();
    $(".assisted_form").each(function(){
        $(this).show();
    });
});

// fire the not assisted mode
$(document).on('not_assisted', function() {
     $('#query_bloc').removeClass().addClass('not_assisted');
      $(".assisted_form").each(function(){
             $(this).hide();
     });
     $('.not_assisted_form').show();
});

// used to toggle filter, optional and limit
function toggleForm(id){
	if($('#'+id).css('display') == 'block'){
		$('#'+id).css('display','none');
		$('#'+id+'_img').attr('src','images/plus.png');
	}
	else{
        $('#'+id).css('display','block');
        $('#'+id+'_img').attr('src','images/minus.png');
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
    //$("#query_results").animate({ scrollTop: $('#query_results').prop("scrollHeight")}, 1000);
    $("html, body").animate({ scrollTop: $(document).height() }, 500);
}

//clear the already displayed results
function clearFunction(){
    $('#wait').empty();
    $('#query_results').empty();
    $('#idWebSocketTime').empty();
    $("#nextpage").hide();
    $("#previouspage").hide();
    $("#query_results").empty();
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


/* ---------------------------------------------- kendo multi select ------------------------------------------- */
$("#list_bases").kendoMultiSelect({
    autoClose: false,
    dataSource: {
    data: [
        {value: "Dbpedia", Name:"DBpedia (v)"},
        {value: "Utpl_Linked_Open_Data", Name:"UTPL Linked Open Data (v)"},
        {value: "National_Library_of_Francel", Name:"National Library of France (v)"},
        {value: "Linked_Stat", Name:"Linked Stat (v)"},
        {value: "European_Council_Council_of_the_European_Union", Name:"European Council Council of the European Union"},
        {value: "oxford", Name:"Oxford"},
        {value: "European_Central_Bank", Name:"European Central Bank"},
        {value: "DisGeNet", Name:"DisGeNet (v)" },
        {value: "HDP", Name:"HealthData.gov Platform - HDP"}
    ]
  },
  dataTextField: "Name",
  dataValueField: "value"
});

$("#list_prefixes").kendoMultiSelect({
    autoClose: false,
    dataSource: {
        data: [
            {value: "xhv:<http://www.w3.org/1999/xhtml/vocab#>"},
            {value: "xhtml:<http://www.w3.org/1999/xhtml#>"},
            {value: "xhtmlvocab:<http://www.w3.org/1999/xhtml/vocab/>"},
            {value: "log:<http://www.w3.org/2000/10/swap/log#>"},
            {value: "math:<http://www.w3.org/2000/10/swap/math#>"},
            {value: "con:<http://www.w3.org/2000/10/swap/pim/contact#>"},
            {value: "contact:<http://www.w3.org/2000/10/swap/pim/contact#>"},
            {value: "list:<http://www.w3.org/2000/10/swap/list#>"},
            {value: "string:<http://www.w3.org/2000/10/swap/string#>"},
            {value: "crypto:<http://www.w3.org/2000/10/swap/crypto#>"},
            {value: "mf:<http://www.w3.org/2001/sw/DataAccess/tests/test-manifest#>"},
            {value: "xsd:<http://www.w3.org/2001/XMLSchema#>"},
            {value: "xs:<http://www.w3.org/2001/XMLSchema#>"},
            {value: "ical:<http://www.w3.org/2002/12/cal/ical#>"},
            {value: "cal:<http://www.w3.org/2002/12/cal/ical#>"},
            {value: "xf:<http://www.w3.org/2002/xforms/>"},
            {value: "p3p:<http://www.w3.org/2002/01/p3prdfv1#>"},
            {value: "xforms:<http://www.w3.org/2002/xforms/>"},
            {value: "bookmark:<http://www.w3.org/2002/01/bookmark#>"},
            {value: "geo:<http://www.w3.org/2003/01/geo/wgs84_pos#>"},
            {value: "vs:<http://www.w3.org/2003/06/sw-vocab-error/ns#>"},
            {value: "swrl:<http://www.w3.org/2003/11/swrl#>"},
            {value: "swrlb:<http://www.w3.org/2003/11/swrlb#>"},
            {value: "exif:<http://www.w3.org/2003/12/exif/ns#>"},
            {value: "grddl:<http://www.w3.org/2003/g/data-view#>"},
            {value: "wgs:<http://www.w3.org/2003/01/geo/wgs84_pos#>"},
            {value: "owl:<http://www.w3.org/2002/07/owl#>"},
            {value: "dc:<http://purl.org/dc/elements/1.1>"},
            {value: "dbp:<http://dbpedia.org/property>"},
            {value: "ps:<https://w3id.org/payswarm#>"},
            {value: "sec:<https://w3id.org/security#>"},
            {value: "sawsdl:<http://www.w3.org/ns/sawsdl#>"},
            {value: "rdfa:<http://www.w3.org/ns/rdfa#>"},
            {value: "ldp:<http://www.w3.org/ns/ldp#>"},
            {value: "acl:<http://www.w3.org/ns/auth/acl#>"},
            {value: "oa:&lt;http://www.w3.org/ns/oa#>"},
            {value: "adms:<http://www.w3.org/ns/adms#>"},
            {value: "rsa:<http://www.w3.org/ns/auth/rsa#>"},
            {value: "cert:<http://www.w3.org/ns/auth/cert#>"},
            {value: "ma:<http://www.w3.org/ns/ma-ont#>"},
            {value: "rr:<http://www.w3.org/ns/r2rml#>"},
            {value: "earl:<http://www.w3.org/ns/earl#>"},
            {value: "dcat:<http://www.w3.org/ns/dcat#>"},
            {value: "sd:<http://www.w3.org/ns/sparql-service-description#>"},
            {value: "md:<http://www.w3.org/ns/md#>"},
            {value: "prov:<http://www.w3.org/ns/prov#>"},
            {value: "org:<http://www.w3.org/ns/org>"},
            {value: "gldp:<http://www.w3.org/ns/people#>"},
            {value: "http:<http://www.w3.org/2011/http#>"},
            {value: "cnt:<http://www.w3.org/2011/content#>"},
            {value: "ptr:<http://www.w3.org/2009/pointers#>"},
            {value: "ttl:<http://www.w3.org/2008/turtle#>"},
            {value: "skosxl:<http://www.w3.org/2008/05/skos-xl#>"},
            {value: "soft:<http://www.w3.org/2007/uwa/context/software.owl#>"},
            {value: "push:<http://www.w3.org/2007/uwa/context/push.owl#>"},
            {value: "common:<http://www.w3.org/2007/uwa/context/common.owl#>"},
            {value: "httph:<http://www.w3.org/2007/ont/httph#>"},
            {value: "dcn:<http://www.w3.org/2007/uwa/context/deliverycontext.owl#>"},
            {value: "rif:<http://www.w3.org/2007/rif#>"},
            {value: "web:<http://www.w3.org/2007/uwa/context/web.owl#>"},
            {value: "wdr:<http://www.w3.org/2007/05/powder#>"},
            {value: "hard:<http://www.w3.org/2007/uwa/context/hardware.owl#>"},
            {value: "net:<http://www.w3.org/2007/uwa/context/network.owl#>"},
            {value: "wdrs:<http://www.w3.org/2007/05/powder-s#>"},
            {value: "java:<http://www.w3.org/2007/uwa/context/java.owl#>"},
            {value: "loc:<http://www.w3.org/2007/uwa/context/location.owl#>"},
            {value: "link:<http://www.w3.org/2006/link#>"},
            {value: "wn20schema:<http://www.w3.org/2006/03/wn/wn20/schema/>"},
            {value: "time:<http://www.w3.org/2006/time#>"},
            {value: "tzont:<http://www.w3.org/2006/timezone#>"},
            {value: "gen:<http://www.w3.org/2006/gen/ont#>"},
            {value: "vcard:<http://www.w3.org/2006/vcard/ns#>"},
            {value: "wairole:<http://www.w3.org/2005/01/wai-rdf/GUIRoleTaxonomy#>"},
            {value: "fn:<http://www.w3.org/2005/xpath-functions#>"},
            {value: "states:<http://www.w3.org/2005/07/aaa#>"},
            {value: "imreg:<http://www.w3.org/2004/02/image-regions#>"},
            {value: "swp:<http://www.w3.org/2004/03/trix/swp-2/>"},
            {value: "fresnel:<http://www.w3.org/2004/09/fresnel#>"},
            {value: "rdfg:<http://www.w3.org/2004/03/trix/rdfg-1/>"},
            {value: "skos:<http://www.w3.org/2004/02/skos/core#>"}
        ]
      },
      dataTextField: "value",
      dataValueField: "value"
    });

$("#output_list").kendoMultiSelect({
    autoClose: false,
    dataSource: {
    data: [
        {value:"HTML"},
        {value:"JSON"},
        {value:"XML"}
    ]
  },
  dataTextField: "value",
  dataValueField: "value"
});

$("#list_bases").getKendoMultiSelect().value(["Dbpedia"]);
$("#output_list").getKendoMultiSelect().value(["HTML"]);


/* ------------------------- tooltips style --------------------------------------*/
$(document).on('mouseover', '.tooltip', function(event) {
    $('.tooltip').tooltipster({
        animation: 'fade',
        delay: 500,
        distance : 2
    });
   $(this).css('cursor','pointer');
});


/* _------------------------------- versions constants -------------------------------- */

// set up versions
$('#antlr-version').html(ANTLR);
$('#jena-version').html(JENA);
