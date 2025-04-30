/**
 * 
 *//*

	var url = "";
    
    function loadCreditDebitRecord(){
    	replaceLoader("#div-result");
		var input_criteria = $('#input-criteria').val();
		var search_type = $("input[name='searchSelection']:checked").val();

		url = "Table_Record.jsp?criteria="+input_criteria+"&searchType="+search_type;
		
		$.get(url,function(data){
			$("#div-result").html(data);
		});	
		
		// Hide the search form
		$('#search-creditdebit').collapse("hide");
		$('#searchCreditDebit').modal('hide');
		return false;
    }
    
    function reloadCreditDebitRecord(){
    	replaceLoader("#div-result");

		$.get(url,function(data){
			$("#div-result").html(data);
		});	
		
		// Hide the search form
		$('#search-creditdebit').collapse("hide");
		$('#searchCreditDebit').modal('hide');
		return false;
    }

	$(document).ready(function() {
		//Div collapse
		$("#search-title").click(function(){
			$("#search_controlNum").collapse("show");
			$("#result_controlNum").collapse("hide");
		});
		
		$("#result-title").click(function(){
			$("#search_controlNum").collapse("hide");
			$("#result_controlNum").collapse("show");
		});
	 	
		$('#btn-submit-title').click(function(){	
			loadTag();
		}); 
	});
	
	// Update placeholder for vendor after changes in select
    function updatePlaceholder() {
    	$("#criteria").attr("placeholder", $("#search_type option:selected").text());
    	$("#criteria").val("");
    }

	// Send title info
	function send_title_info() {	
		// Hide the search form
		$('#search_controlNum').collapse("hide");
		// Show the result form
		$('#result_controlNum').collapse("show").height("auto");
		return false;
	}	
	function getValue(tagName){
		var strTagName =  tagName.options[tagName.selectedIndex].getAttribute('data-id');
		var strTabName =  tagName.options[tagName.selectedIndex].getAttribute('data');
		var strTag =  tagName.options[tagName.selectedIndex].getAttribute('value');
		document.getElementById("tagName").innerHTML = strTagName;
		document.getElementById("tabNameValue").value = strTabName;
		document.getElementById("tagValue").value = strTag;
		}

	var url = "";
	$(document).ready(function() {
	 	// Get record from ShowAllPeriodicalsMaster.jsp
		$('#btn_showAll').click(function(){
			showAll();
		});
	
		   
	});

	function radio(){
	var searchType = $("input[name='searchSelection']:checked").val();
		if(searchType == "selection"){
			
			$('input[type=radio]').not(':checked').each(function(){
	
			$( ".title" ).prop( "disabled", false );
			$( ".tags" ).prop( "disabled", true );
			$( ".ctrlNo" ).prop( "disabled", true );
			$( ".officerID" ).prop( "disabled", true );
			$( ".txtMatNo" ).prop( "disabled", true );
		});
		}
	  
		else if(searchType == "MatNo"){
			$('input[type=radio]').not(':checked').each(function(){
			$( ".title" ).prop( "disabled", true );
			$( ".tags" ).prop( "disabled", true );
			$( ".ctrlNo" ).prop( "disabled", true );
			$( ".officerID" ).prop( "disabled", true );
			$( ".txtMatNo" ).prop( "disabled", false );
			});
		}
		
		else if(searchType == "ctrlNo"){
			$('input[type=radio]').not(':checked').each(function(){
			$( ".title" ).prop( "disabled", true );
			$( ".tags" ).prop( "disabled", true );
			$( ".txtMatNo" ).prop( "disabled", true );
			$( ".officerID" ).prop( "disabled", true );
			$( ".ctrlNo" ).prop( "disabled", false );
			});
		}
		
		else if(searchType == "tag"){
			$('input[type=radio]').not(':checked').each(function(){
	
			$( ".title" ).prop( "disabled", true );
			$( ".ctrlNo" ).prop( "disabled", true );
			$( ".txtMatNo" ).prop( "disabled", true );
			$( ".officerID" ).prop( "disabled", true );
			$( ".tags" ).prop( "disabled", false );
			});
		}
		
		else if(searchType == "OfficerID"){
			$('input[type=radio]').not(':checked').each(function(){
	
			$( ".title" ).prop( "disabled", true );
			$( ".ctrlNo" ).prop( "disabled", true );
			$( ".txtMatNo" ).prop( "disabled", true );
			$( ".officerID" ).prop( "disabled", false );
			$( ".tags" ).prop( "disabled", true );
			});
		}
	} 
//Button submit to load data	document.getElementsByClassName
	function getURL(){
		var searchType = $("input[name='searchSelection']:checked").val();
		if(searchType == "selection"){
		
			var title = $('#criteria').val();
			var searchType = $('#search_type').val();
			
			url = "jsp/include/sharedV1/Modal_Selections.jsp?criteria="+title+"&search_type="+searchType;
			
			$.get(url,function(data_title){
				$("#display_title").html(data_title);
			});	
			$("#searchBtn").attr("href", url);
			$("#searchBtn").attr("data-target", "#selectionSearch");
	}
		else if(searchType == "MatNo"){
			var txtMatNo = $('#criteria').val();
			alert(txtMatNo);
			$.get("Table_SearchByAccNo",{txtMatNo : txtMatNo},function(data){
				alert(txtMatNo);
				$("#div-result").html(data);
				$(".collapse").collapse('show');
				$("#txt-searchAll").val("no");
			});
	}
		else if(searchType == "ctrlNo"){
			
			var title = $('#txtCtrlNo').val();
			var searchType = "ctrlNo";
			
			url = "jsp/include/sharedV1/Modal_ControlNumber.jsp?criteria="+title+"&search_type="+searchType;
			
			$.get(url,function(data_title){
				$("#display_title").html(data_title);
				$(".collapse").collapse('show');
			});	
			$("#searchBtn").attr("href", url);
			$("#searchBtn").attr("data-target", "#ctrlSearch");
	}
		
		else if(searchType == "tag"){
			//replaceLoader("#display_title");
			var criteria =  $('#tagValue').val();
			var searchType = $('#tabNameValue').val();
			var condition = $('#inputValue').val();
		
			url = "jsp/include/sharedV1/Modal_Tag.jsp?criteria="+criteria+"&search_type="+searchType+"&condition="+condition;
			$.get(url,function(data_title){
				$("#display_title").html(data_title);
			});	
			
			
			$("#searchBtn").attr("href", url);
			$("#searchBtn").attr("data-target", "#tagSearch");
	}
		document.getElementById("current_form").reset();

	}
		
		//url = "OrderTable.jsp?";
		
	
	function showAll()
	{
		$("#txt-searchAll").val("yes");
		//replaceLoader("#div-result");
		$.get("../../ICMS/jsp/modules/periodicals/01/Table_AllPeriodicalsMaster.jsp",{},function(data){
			$("#div-result").html(data);
		});	
	}

	function reloadRecord(){
		// Display loading bar
		//replaceLoader("#display_title");
		
		$.get(url,function(data_title){
			$("#display_title").html(data_title);
		});	
	}
		
	function deleteRecordShowAll(controlNo){
		var url = "ICMS/jsp/modules/periodicals/01/Handler_DeletePeriodicalsMaster.jsp?controlNo=" + controlNo;
		
		var result = confirm("You are about to delete one record?");
		if (result) {
			$.ajax({
	  			url: url,
	  			success: function(result) {
	  				var deleteRecord = clearSpace(result);
	  				if(deleteRecord == "ok"){
	  					showPopupMsg("Delete successful.","success");
	  					
	  					if($("#txt-searchAll").val() === "yes")
	  						showAll();
	  					else
	  						window.location = window.location.href;
	  				}else{
	  					showPopupMsg("Record could not be delete. Record exist in at least 1 order.","warning");
	  				}
	  			}
	  		});
		}
	}


	
	function deleteRecord(controlNo)
	{
		alert("ss");
		//var menuID = "CT";
		var deleteUrl = "jsp/modules/Cataloging/Accession_Maint/Handler_DeleteAccMaint.jsp?controlNo="+controlNo;
		var confirmDelete =$('#delete').modal('show');
		if(confirmDelete)
		{
			$.ajax({
	   			// Title
	   			url: deleteUrl,
	   			success: function(result) {
	   				var status = result.replace(/\s+/g, '');
	   				if(status == "ok"){
	   					showPopupMsg('Data deleted succesfully.', 'success');
	   				}else{
	   					showPopupMsg('Having issues deleting data. Please try again later.', 'warning');
	   				}
	   				//updateTableWithLastQuery();
	   			}
	   		});
		}
		
	}*/

function equalLocation(){
	document.getElementById("GL03LOCA1").value = document.getElementById("CT03LOCA").value;
}

function equalLocation1(){
	document.getElementById("CT03LOCA").value = document.getElementById("GL03LOCA1").value;
}

function equalSMD(){
	document.getElementById("CT03SMD1").value = document.getElementById("CT03SMD").value;
}
function equalSMD1(){
	document.getElementById("CT03SMD").value = document.getElementById("CT03SMD1").value;
}

function equalCondition(){
	document.getElementById("CT03COND1").value = document.getElementById("CT03COND").value;
}
function equalCondition1(){
	document.getElementById("CT03COND").value = document.getElementById("CT03COND1").value;
}
function equalCategory(){
	document.getElementById("CT03ICAT1").value = document.getElementById("CT03ICAT").value;
}
function equalCategory1(){
	document.getElementById("CT03ICAT").value = document.getElementById("CT03ICAT1").value;
}
function equalForex(){
	document.getElementById("currency1").value = document.getElementById("currency").value;
}
function equalForex1(){
	document.getElementById("currency").value = document.getElementById("currency1").value;
}

function updateLocalCost(){
	var result = $("#exchangeRate").val()*$("#foreignCost").val();
	$("#localCost").val(result.toFixed(2));
}

function updateForeignCost(){
	var result = $("#localCost").val()/$("#exchangeRate").val();
	$("#foreignCost").val(result.toFixed(2));
}

function updateBothCost(){
	updateLocalCost();
}

$(document).ready(function() {
	
	initDate();
	defaultDate();
	init();
	//Open accession modal
$('#button-generateAccession').click(function() {
	// Get current accesion number
	var accNo;
	$.ajax({
		// Title
		url: 'GetAccessionNo',
		success: function(result) {
			result = result.replace(/^\s+|\s+$/g, '');
			updateAccessionNo(result);
		}
	});
	
	function updateAccessionNo(result) {
		var accNo = parseInt(result);
		accNo += 1;
		var StringaccNo = accNo.toString();
		var n = StringaccNo.length;
		var count = 10;
		for(var i=n;i<count;i++){
	    //$('#AccNo').val(accNo);
	    accNo= "0" + accNo;
	   
		}
		$("#CT03DOCNO").val(accNo);
	}
});

$('#add-submit').click(function(){

		var url = "Handler_AddNewAccMaint?CT03DOCNO=" + $('#CT03DOCNO').val()+
			"&CT03MATNO=" + $('#CT03MATNO').val() +
			"&CT03LOCA=" + $('#CT03LOCA').val() + 
			"&CT03ICAT=" + $('#CT03ICAT').val() +
			"&CT03VEND=" + $('#vendor').val()+
			"&CT03COND=" + $('#CT03COND').val() +
			"&CT03INVOICE=" + $('#CT03INVOICE').val() +
			"&CT03SMD=" + $('#CT03SMD').val() +
			"&CT03INVDATE=" + $('#CT03INVDATE').val() +
			"&currency=" + $('#currency').val() +
			"&CT03COPYNO=" + $('#CT03COPYNO').val() +
			"&CT03VOL=" + $('#CT03VOL').val() +
			"&CT03RATE=" + $('#CT03RATE').val() +
			"&foreignCost=" + $('#foreignCost').val() +
			"&localCost=" + $('#localCost').val() +
			"&sCost=" + $('#sCost').val() +
			"&hCost=" + $('#hCost').val() ;
   		url += "&copyType=";
			if ($('#copyType').is(':checked')) {url += "Y"}else{url += "N"};
		url += "&onthefly=";
			if ($('#onthefly').is(':checked')) {url += "Y"}else{url += "N"};
			
	$.ajax({
			url: url,
			success: function(result) {
				reloadForm();
				var updateRecord = clearSpace(result);
				if(updateRecord == "ok"){
					//showPopupMsg("Update successful.","success");
					$('#addAccMaint').modal('hide');
				
					//reloadRecord();
				}else{
					showPopupMsg("Please fill up the necessary details.","warning");
				}
			}
		});
	});

$('#currency').change(function(){
	/* Generate URL */
	//var button = document.getElementById( "currency" );
var currency = $(this).find('option:selected').attr('data-id');
	//var currencies = currency.options[ currency.selectedIndex ].value;
		var url = "CurrencyRate?currency=" + currency;
		// Execute AJAX Update
		$.ajax({
			// Title
			url: url,
			success: function(result) {
				
				$("#exchangeRate").val(currency);
				$("#localCost").val("");
				$("#foreignCost").val("");
			}
		});
});


function reloadForm(){
	$('input[name="addAccessionMaint"]:not(:checked)').each(function() {
		   //alert(this.value); 
		   var element = "#"+this.value;
		    if($(element).is("input[type=text]")) {
		    	$(element).val('');
		    	return true;
		    }
		    
		    if($(element).is("select")) {
		    	$(element).prop('selectedIndex',0);
		    }
		});
}

function init(){
	initDate();
	defaultDate();
}
$('#CT03INVDATE').datepicker({
	format : "d/m/yyyy",
	autoclose : true
});	


});

$(function() {
	alert("ddd");
    $('input[name="daterange"]').daterangepicker();
});

