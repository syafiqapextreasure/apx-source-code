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
$("[title]").tooltip();


function remainValue(){
	var element;
	$('input:checkbox[name=checkedValue]').each(function() 
			{    
			    if(!$(this).is(':checked')){
			    	var element = "." + $(this).val();
			    	
			    	   if($(element).is("input[type=text]")) {
					    	$(element).val('');
					    	$(".CT03DOCNO").val('');
					    	if(element==".CT03MATNO"){
					    		$(".title").html("");
					    	}
					    	if(element==".CT03VEND"){
					    		$(".div-vendorName").html("");
					    	}
					    	return true;
					    }
					    
					    if(element==".CT03INVDATE"){
					    	$('#CT03INVDATE').val("").datepicker("update");
					    	
					    }
					    
					    if($(element).is("select")) {
					    	$(element).prop('selectedIndex',0);
					    	var element1 = element + "1";
					    	$(element1).prop('selectedIndex',0);
					    	//selectBox();
					    	/*equalLocation1();
					    	equalCategory1();
					    	equalCondition1();
					    	equalSMD1();
					    	equalForex1();*/
					    }
			    }
			});
	/*$('input[name=checkedValue]:not(:checked)').each(function() {
		   //alert(this.value); 
		   //var element = "#"+this.value;
		
		 element = "."+this.value;
	
		    if($(element).is("input[type=text]")) {
		    	alert("clear");
		    	$(element).val('');
		    	$(".CT03DOCNO").val('');
		    	if(element==".CT03MATNO"){
		    		$(".title").html("");
		    	}
		    	if(element==".CT03VEND"){
		    		$(".div-vendorName").html("");
		    	}
		    	return true;
		    }
		    
		    if(element==".CT03INVDATE"){
		    	$('#CT03INVDATE').val("").datepicker("update");
		    	
		    }
		    
		    if($(element).is("select")) {
		    	$(element).prop('selectedIndex',0);
		    	var element1 = element + "1";
		    	$(element1).prop('selectedIndex',0);
		    	//selectBox();
		    	equalLocation1();
		    	equalCategory1();
		    	equalCondition1();
		    	equalSMD1();
		    	equalForex1();
		    }
		});
	
	alert(element + "s");*/
}

 	$('#btn-update').click(function(){

 		var CT03DOCNO = $('#editCT03DOCNO').val();
 		var CT03MATNO = $('#editCT03MATNO').val();
 		var CT03LOCA = $('#editCT03LOCA').val();
 		var CT03ICAT = $('#editCT03ICAT').val();
 		var CT03COND = $('#editCT03COND').val();
 		var CT03SMD = $('#editCT03SMD').val();
 		var copyType = $("input[name=copyType1]:checked").val();
 		
 		if(CT03SMD!="0"&&CT03DOCNO.length>0&&CT03MATNO.length>0&&CT03LOCA!="0"&&
 				CT03ICAT!="0"&&CT03COND!="0"){
 		
   		var url = "Handler_EditAccMaint?CT03DOCNO=" + CT03DOCNO +
   				"&CT05SRAW=" + $('#editCT05SRAW').val() +
   				"&CT03MATNO=" + CT03MATNO +
   				"&CT03LOCA=" + CT03LOCA + 
   				"&CT03ICAT=" + CT03ICAT +
   				"&CT03VEND=" + $('#editCT03VEND').val()+
   				"&CT03COND=" + CT03COND +
   				"&CT03INVOICE=" +$('#editCT03INVOICE').val() +
   				"&CT03SMD=" + CT03SMD +
   				"&CT03INVDATE=" + $('#CT03EINVDATE').val().trim() +
   				"&currency=" + $('#editcurrency').val() +
   				"&CT03COPYNO=" + $('#editCT03COPYNO').val() +
   				"&CT03VOL=" + $('#editCT03VOL').val() +
   				"&CT03RATE=" + $('#editCT03RATE').val().trim() +
   				"&foreignCost=" + $('#editforeignCost').val().trim() +
   				"&localCost=" + $('#editlocalCost').val().trim() +
   				"&sCost=" + $('#editsCost').val().trim() +
   				"&hCost=" + $('#edithCost').val().trim() +
   				"&copyType=" + copyType ;
  
				url += "&onthefly=";
					if ($('#editonthefly').is(':checked')) {url += "Y"}else{url += "N"};
				
   		$.ajax({
   			url: url,
   			success: function(result) {
  				var updateRecord = result.trim();

  				if(updateRecord == "ok"){
  				
  					$('#editAccMaint').modal('hide');
  					$('body').removeClass('modal-open');
  					$('.modal-backdrop').remove();
  					$.get("Table_SearchByAccNo",{txtMatNo : $('.CT03DOCNO').val()},function(data){
  						$(".accession_list").html(data);
  						 alertMessage("Successful", "success", "005","updated", "@action");
  					});
  					//reloadRecord();
  				}else{
  					 alertMessage("", "info", "019",null, null);
  				}
  			}
   		});
 		}else{
			 alertMessage("", "info", "034",null, null);
		}
   	});
 	
$('#add-submit').click(function(){
/*alert( $('#CT03DOCNO').val()+$('#CT03MATNO').val()+$('#CT03LOCA').val()+$('#CT03ICAT').val()+
		 $('#CT03VEND').val()+ $('#CT03COND').val()+ $('#CT03SMD').val()+$('#CT03INVDATE').val()+
		 $('#currency').val()+$('#CT03COPYNO').val()+$('#CT03VOL').val()+$('#CT03RATE').val()+
		 $('#foreignCost').val()+$('#localCost').val()+$('#sCost').val()+$('#hCost').val());*/
	var CT03DOCNO = $('#CT03DOCNO').val();
	
	if(CT03DOCNO.length<10){
		swal('Warning', "Invalid accession no." , "warning");
	}else{
	var CT03MATNO = $('#CT03MATNO').val();
	var CT03LOCA = $('#CT03LOCA').val();
	var CT03ICAT = $('#CT03ICAT').val();
	var CT03COND = $('#CT03COND').val();
	var CT03SMD = $('#CT03SMD').val();
	var copyType = $("input[name=copyType]:checked").val();
	
	if(CT03SMD!="0"&&CT03DOCNO.length>0&&CT03MATNO.length>0&&CT03LOCA!="0"&&
				CT03ICAT!="0"&&CT03COND!="0"){
		
		var url = "Handler_AddNewAccMaint?CT03DOCNO=" + CT03DOCNO+
			"&CT03MATNO=" + CT03MATNO +
			"&CT03LOCA=" + CT03LOCA + 
			"&CT03ICAT=" + CT03ICAT +
			"&CT03VEND=" + $('#CT03VEND').val()+
			"&CT03COND=" + CT03COND +
			"&CT03INVOICE=" + $('.CT03INVOICE').val() +
			"&CT03SMD=" + CT03SMD +
			"&CT03INVDATE=" + $('#CT03INVDATE').val() +
			"&currency=" + $('#currency').val() +
			"&CT03COPYNO=" + $('#CT03COPYNO').val() +
			"&CT03VOL=" + $('#CT03VOL').val() +
			"&CT03RATE=" + $('#CT03RATE').val() +
			"&foreignCost=" + $('.foreignCost').val() +
			"&localCost=" + $('.localCost').val() +
			"&sCost=" + $('.sCost').val() +
			"&hCost=" + $('.hCost').val()+
			"&copyType=" + copyType;
		url += "&onthefly=";
			if ($('#onthefly').is(':checked')) {url += "Y"}else{url += "N"};
			
			
	$.ajax({
			url: url,
			success: function(result) {
				var updateRecord = result.trim();
				if(updateRecord == "ok"){
					//showPopupMsg("Update successful.","success");
					alertMessage("Successful", "success", "005","added", "@action");
					$("#CT03DOCNO").val('');
					 //$("#title").text('');
					//$('#addAccMaint').modal('hide');
					remainValue();

			    	/*equalCategory1();
			    	equalCondition1();
			    	equalSMD1();
			    	equalForex1();*/
					//reloadRecord();
				}else if(updateRecord == "error"){
					 alertMessage("", "info", "019",null, null);
				}
			}
		});
		}else{
			 alertMessage("", "info", "034",null, null);
		}
	}
	});
//Keyin the template change to uppercase
	$('.CT03VEND').keyup(function(){
	    this.value = this.value.toUpperCase();
	});
	
$(".CT03VEND").focusout(function(){
	var vendor_details = $(this).val();
	$.get("SearchVendor",{criteria:vendor_details,action:"keyupVendor"},function(data_vendor){

		$(".div-vendorName").html(data_vendor)
		//document.getElementById('div-vendorName').innerHTML = data_vendor;
	   });	
	
});
	
	/*$("#CT03DOCNO").focusout(function(){
		
		if($(this).val().length<10){
			//swal('Warning', "Invalid accession no." , "warning");
			 $("#btn-update").prop('disabled', true);
			 $("#add-submit").prop('disabled', true);
		}else{
			 $("#btn-update").prop('disabled', false);
			 $("#add-submit").prop('disabled', false);
		}
	});*/


	
	$(".CT03MATNO").focusout(function(){
		var matno = $(this).val();

		$.get("SearchVendor",{criteria:matno,action:"keyupCtrlNo"},function(title){

			if(title.trim()=="Error"){
				$(".title").html("<font style='color:red'>Invalid control no.</font>");
				 $("#add-submit").prop('disabled', true);
			}else{
				$(".title").html(title);
				 $("#add-submit").prop('disabled', false);
			}
			//alert(title);
			/*if(title.trim()!=null && title.trim()!=""){
				$(".title").html(title);
			}else{
				$(".title").html("Invalid control no.");
			}*/
		   });	
		
	});
	
/*	 $('.modal').on('show.bs.modal', function(e)
			 { 
		 alert("test");
			 	 $(this).removeData();
			 }) */
	
	
	$("#addAccMaint").on("show.bs.modal", function(){
		 $(this).removeData();
	});
	
	$("#editAccMaintenance").on('show.bs.modal', function(){
		 $(this).removeData();
	});
	
	$("#viewAccMaintenance").on('show.bs.modal', function(){
		 $(this).removeData();
	});
	
	$(".modal").on('hide.bs.modal', function(){
		 $(this).removeData();
	});

$(".CT03LOCA").change(function(){
	$(".CT03LOCA1").val($(this).val());
});

$(".CT03LOCA1").change(function(){
	$(".CT03LOCA").val($(this).val());
})

$(".CT03ICAT").change(function(){
	$(".CT03ICAT1").val($(this).val());CT03ICAT1
});

$(".CT03ICAT1").change(function(){
	$(".CT03ICAT").val($(this).val());CT03ICAT1
});

$(".CT03SMD").change(function(){
	$(".CT03SMD1").val($(this).val());
})

$(".CT03SMD1").change(function(){
	$(".CT03SMD").val($(this).val());
})

$(".CT03COND").change(function(){
	$(".CT03COND1").val($(this).val());
})

$(".CT03COND1").change(function(){
	$(".CT03COND").val($(this).val());
})

$(".currency").change(function(){
	$(".currency1").val($(this).val());
	$(".CT03RATE").val($(this).find(':selected').attr('data-id'));
	var result = $(".CT03RATE").val()*$(".foreignCost").val();
	$(".localCost").val(result.toFixed(2));
	var result1 = $(".CT03RATE").val()*$("#editforeignCost").val();
	$("#editlocalCost").val(result1.toFixed(2));
})

$(".currency1").change(function(){
	$(".currency").val($(this).val());
	$(".CT03RATE").val($(this).find(':selected').attr('data-id'));
	var result = $(".CT03RATE").val()*$(".foreignCost").val();
	$(".localCost").val(result.toFixed(2));
	var result1 = $(".CT03RATE").val()*$("#editforeignCost").val();
	$("#editlocalCost").val(result1.toFixed(2));
})
/*function equalLocation(){
	//document.getElementById("GL03LOCA1").value = document.getElementById("CT03LOCA").value;
	$(".CT03LOCA1").val($(".CT03LOCA").val());
}*/

/*function equalLocation1(){
	//document.getElementById("CT03LOCA").value = document.getElementById("GL03LOCA1").value;
	$(".CT03LOCA").val($(".CT03LOCA1").val());
}
*/
/*function equalSMD(){
	//document.getElementById("CT03SMD1").value = document.getElementById("CT03SMD").value;
	$(".CT03SMD1").val($(".CT03SMD").val());
}
function equalSMD1(){
	//document.getElementById("CT03SMD").value = document.getElementById("CT03SMD1").value;
	$(".CT03SMD").val($(".CT03SMD1").val());
}
*/
/*function equalCondition(){
	//document.getElementById("CT03COND1").value = document.getElementById("CT03COND").value;
	$(".CT03COND1").val($(".CT03COND").val());
}
function equalCondition1(){
	//document.getElementById("CT03COND").value = document.getElementById("CT03COND1").value;
	$(".CT03COND").val($(".CT03COND1").val());
}*/
function equalCategory(){
	//document.getElementById("CT03ICAT1").value = document.getElementById("CT03ICAT").value;
	$(".CT03ICAT1").val($(".CT03ICAT").val());
}
function equalCategory1(){
	//document.getElementById("CT03ICAT").value = document.getElementById("CT03ICAT1").value;
	$(".CT03ICAT").val(	$(".CT03ICAT1").val());
}
/*function equalForex(currency){
	//document.getElementById("currency1").value = document.getElementById("currency").value;
	$(".currency1").val($(".currency").val());
	$("#CT03RATE").val(currency.options[currency.selectedIndex].getAttribute('data-id'));
	updateLocalCost();	
}
function equalForex1(currency){
	//document.getElementById("currency").value = document.getElementById("currency1").value;
	$(".currency").val($(".currency1").val());
	$("#CT03RATE").val(currency.options[currency.selectedIndex].getAttribute('data-id'));
	updateLocalCost();
}
*/

$(".foreignCost").keyup(function(){
	var result = $(".CT03RATE").val()*$(this).val();
	$(".localCost").val(result.toFixed(2));
});

$("#editforeignCost").keyup(function(){
	var result = $(".CT03RATE").val()*$(this).val();
	$("#editlocalCost").val(result.toFixed(2));
});

$(".localCost").keyup(function(){
	var result = $(this).val()/$(".CT03RATE").val();
	$(".foreignCost").val(result.toFixed(2));
});

$("#editlocalCost").keyup(function(){
	var result = $(this).val()/$(".CT03RATE").val();
	$("#editforeignCost").val(result.toFixed(2));
});
/*function updateLocalCost(){
	var result = $("#CT03RATE").val()*$("#foreignCost").val();
	$("#localCost").val(result.toFixed(2));
}

function updateForeignCost(){
	var result = $("#localCost").val()/$("#CT03RATE").val();
	$("#foreignCost").val(result.toFixed(2));
}*/

function updateBothCost(){
	updateLocalCost();
}

$(document).ready(function() {

	 var sPageURL = decodeURIComponent(window.parent.location),
     sURLVariables = sPageURL.split('?');
	 var varArray = (sURLVariables[1]).split("="); //eg. index.html?msg=1
		if((varArray[3]!=null)&& varArray[3]!=""){
			$.get("Table_CTResultMaster",{controlNoInput : varArray[3]},function(data){
				$(".accession_list").html(data);
			});
		}
		 
	$('.div-result').DataTable({
	    responsive: true
	});


	/*
	$("#addAccMaint").on("hidden.bs.modal", function(){
		$('input[name="addAccessionMaint"]:not(:checked)').each(function() {
			   //alert(this.value); 
			   var element = "#"+this.value;
			    if($(element).is("input[type=text]")) {
			    	$(element).val('');
			    	$("#CT03DOCNO").val('');
			    	if(element=="#CT03MATNO"){
			    		$("#title").html("");
			    	}
			    	return true;
			    }
			    
			    if($(element).is("select")) {
			    	$(element).prop('selectedIndex',0);
			    }
			});
	});
*/

	  $.get("template",{MODULE:"Cataloging/03_Accession_Maintenance",ACTION:"MainPage.jsp"},function(data_title){
	   });
	initDate();
	defaultDate();
	init();
	//Open accession modal

/*$('#currency').change(function(){

	 Generate URL 
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
*/
function init(){
	initDate();
	defaultDate();
}
$('#CT03INVDATE').datepicker({
	format : "d/m/yyyy",
	autoclose : true
});	




});

/*$('.modal').on('hidden.bs.modal', function(){
    $(this).find('form')[0].reset();
});*/

$(function() {
    $('input[name="daterange"]').daterangepicker();
});


$('.panel-collapse').on('shown.bs.collapse', function (e) {
var tableIdToUpdate = $(e.currentTarget).find('.panel-body').find('.table')[0].id;
$('#' + tableIdToUpdate).DataTable().columns.adjust().responsive.recalc();
});

function selectBox(){
	
	$(".GL03LOCA1").val($(".GL03LOCA").val());
	$(".CT03ICAT1").val($(".CT03ICAT").val());
	$(".CT03COND1").val($(".CT03COND").val());
	$(".CT03SMD1").val($(".CT03SMD").val());
	$(".currency1").val($(".currency").val());

	
}

function generateAcc(){
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
}


function updateAccessionNo(result) {
	var accNo = parseInt(result);
	var StringaccNo = accNo.toString();
	var n = StringaccNo.length;
	var count = 10;
	for(var i=n;i<count;i++){
    //$('#AccNo').val(accNo);
    accNo= "0" + accNo;
   
	}
	$("#CT03DOCNO").val(accNo);
}

function deleteDOC(accNo){

	swal({   
		title: 'Are you sure want to delete?',
		text: 'The record will be removed from the template',
		type: 'warning',   showCancelButton: true,
		confirmButtonColor: '#3085d6', 
		cancelButtonColor: '#d33',
		confirmButtonText: 'Yes, delete it!',
		cancelButtonText: 'No, cancel !',
		confirmButtonClass: 'confirm-class',
		cancelButtonClass: 'cancel-class',
		closeOnConfirm: false,
		closeOnCancel: true 
		}).then(function() {
	
			 var deleteUrl = "Handler_DeleteAccMaint?accNo="+accNo;
				swal( 'Deleted!', accNo+ ' record has been deleted.','success');  
			      $.ajax({
			 			// Title
			 			url: deleteUrl,
			 			success: function(result) {
			 				var status = result.replace(/\s+/g, '');
			 				if(status == "ok"){
			 					$.get("Table_SearchByAccNo",{txtMatNo : $('#CT03DOCNO').val()},function(data){
			  						$(".accession_list").html(data);
			  					});
			 					showPopupMsg('Data deleted succesfully.', 'success');
			 				}else{
			 					/*swal(
									      'Cancelled',
									      'Accession No.:'+accNo+' status is circulated :)',
									      'error'
									    );*/
			 					swal(
									      'Cancelled',
									      "Only record with 'Final Processing' status can be deleted",
									      'error'
									    );
			 				}
			 				//updateTableWithLastQuery();
			 			}
			 		});
					},function(dismiss) {
						  if (dismiss === 'cancel') {
						    swal(
						      'Cancelled',
						      'Accession No. is not deleted :)',
						      'error'
						    );
						  }
						})

}

function deleteMATNO(id){

	var matno = $(id).attr('data-id');
	var accNo = $(id).attr('id');
	alert(matno);
	alert(accNo);
	swal({   
		title: 'Are you sure want to delete?',
		text: 'The record will be removed from the template',
		type: 'warning',   showCancelButton: true,
		confirmButtonColor: '#3085d6', 
		cancelButtonColor: '#d33',
		confirmButtonText: 'Yes, delete it!',
		cancelButtonText: 'No, cancel !',
		confirmButtonClass: 'confirm-class',
		cancelButtonClass: 'cancel-class',
		closeOnConfirm: false,
		closeOnCancel: true 
		}).then(function() {
	
			 var deleteUrl = "Handler_DeleteAccMaint?accNo="+accNo;
				swal( 'Deleted!', accNo+ ' record has been deleted.','success');  
			      $.ajax({
			 			// Title
			 			url: deleteUrl,
			 			success: function(result) {
			 				var status = result.replace(/\s+/g, '');
			 				if(status == "ok"){
			 					$.get("Table_CTResultMaster",{controlNoInput : matno},function(data){
			 						$(".accession_list").html(data);
			 					});
			 					showPopupMsg('Data deleted succesfully.', 'success');
			 				}else{
			 					/*swal(
									      'Cancelled',
									      'Accession No.:'+accNo+' status is circulated :)',
									      'error'
									    );*/
			 					swal(
									      'Cancelled',
									      "Only record with 'Final Processing' status can be deleted",
									      'error'
									    );
			 				}
			 				//updateTableWithLastQuery();
			 			}
			 		});
					},function(dismiss) {
						  if (dismiss === 'cancel') {
						    swal(
						      'Cancelled',
						      'Accession No. is not deleted :)',
						      'error'
						    );
						  }
						})

}

/*$("#addAccMaint").on("hidden.bs.modal", function(){
	 var allVals = [];

	 $("input:checkbox").each(function(){
		    var $this = $(this);

		    if(!$this.is(":checked")){
		    	if ( $(this).is("input:text") ){
		    		$("."+$(this).val()).val();
		    	}else{
		    		 $("."+$(this).val()).val('0');
		    	}
		    	
		    	 
		    }
	 });
	 $('[name=addAccessionMaint]').each(function() {
		 if($this.is(":checked")){
		        someObj.fruitsGranted.push($this.attr("id"));
		    }else{
		        someObj.fruitsDenied.push($this.attr("id"));
		    }
		 $("#"+$(this).val()).val('0');
	  // allVals.push($(this).val());
	 });
	
	 if ($('.fastRelease').is(':checked')) {
         alert('You have Checked it');
     } else {
         alert('You Un-Checked it');
     }

});*/

