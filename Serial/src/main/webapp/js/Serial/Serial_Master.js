$(document).ready(function() {
	$("[title]").tooltip();
	
	var serial = $('#tableSerial').DataTable({
 	    responsive: true,
 	   destroy: true,
 	});
	

	$('body').on('hidden.bs.modal', '#editPeriodicalsMaster, #viewPeriodicalsMaster, #addNewSerialMaster, #vendorSearch', function () {
		$(this).removeData('bs.modal');
		});
	
/*	  $.get("template",{MODULE:"Circulation/01_Charging",ACTION:"Charging.jsp"},function(data_title){
	   });
	  */
	// Get record from ShowAllPeriodicalsMaster.jsp
	$('#btn_showAll').click(function(){
		showAll();
	});
	
	   $("#button-generateAccession").click(
			   function(){
			$("td.GL14NAME").html("test");
			$("td.GL14CATE").html("test");
			$("td.GL14STAT").html("test");
			$("td.GL14MEMDATE").html("test");
			$("td.GL14EXPDATE").html("test");
	   });
	   
	   ////onblur requestor
	   $("#requestor").blur(function(e){
		   $.get("SearchRequestor",{criteria:document.getElementById('requestor').value,search_type:"reqID",action:"blur"},function(data){
	   			//alert(data.trim() + "dataaaa");
	   			//$("#div-requestorName").html(data);
	   			if(data.trim() != ""){
	   				$("#div-requestorName").text(data);
	   				$('#btn-submit').prop('disabled', false);
	   			}else{
	   				$("#div-requestorName").empty();
	   				swal("Invalid user id");
	   				$('#btn-submit').prop('disabled', true);
	   			}
		   });
	   });	  
});

//Add serial master record
/*function addRecord(){
		alert("w");
		var controlNumber = $("#controlNo").val();
		var vendorCode = $("#vendor").val();
		var publisherRate = $("#pubRate").val();
		var foreignPrice = $("#fPrice").val();
		var localPrice = $("#lPrice").val();
		var currency = $('#currency').val();
		alert($('#smd').val());
		
		var url = "Handler_AddNewSerialMaster?controlNo=" + $('#controlNo').val() +
		"&smd=" + $('#smd').val() +
		"&language=" + $('#language').val() +
		"&department=" + $('#department').val() +
		"&frequency=" + $('#frequency').val() +
		"&vendor=" + $('#vendor').val() +
		"&binder=" + $('#binder').val() +
		"&publisher=" + $('#publisher').val() +
		"&bibliographicSource=" + $('#bibliographicSource').val() +
		//"&renewalAlert=" + $('#renewalAlert').val() +
		"&serialMode=" + $('#serialMode').val() +
		"&requestor=" + $('#requestor').val() +
		"&currency=" + $('#currency').val() +
		"&pubRate=" + $('#pubRate').val() +
		"&fPrice=" + $('#fPrice').val() +
		"&lPrice=" + $('#lPrice').val();
		
		url += "&renewalAlert=";
		if($('#renewalAlert').val() == "") {url += "0"}else{url += $('#renewalAlert').val()};	
	url += "&cumIndex=";
		if ($('#cumIndex').is(':checked')) {url += "Y"}else{url += "N"};
	url += "&contPage=";
		if ($('#contPage').is(':checked')) {url += "Y"}else{url += "N"};
	url += "&stndOrder=";
		if ($('#stndOrder').is(':checked')) {url += "Y"}else{url += "N"};
	url += "&bindTreatment=";
		if ($('#bindTreatment').is(':checked')) {url += "Y"}else{url += "N"};
	url += "&subjHeading=";
		if ($('#subjHeading').is(':checked')) {url += "Y"}else{url += "N"};
	url += "&irsIndexing=";
		if ($('#irsIndexing').is(':checked')) {url += "Y"}else{url += "N"};
	url += "&ttlPage=";
		if ($('#ttlPage').is(':checked')) {url += "Y"}else{url += "N"};
	url += "&routing=";
		if ($('#routing').is(':checked')) {url += "Y"}else{url += "N"};
		alert("ds1");
		
		if(currency == "-" || controlNumber == "" || vendorCode ==  "" || publisherRate == "" || foreignPrice == "" || localPrice == ""){
			alert("ds");
			swal('',"Please fill up the necessary details.","");
		}else
		{
			$.ajax({
	   			url: url,
	   			success: function(result) {
	   				var isDuplicate =result.trim();
	   				if(isDuplicate == "error"){
	   				alert("ds2");
	   					swal('',"Duplicate control number.","");
	   				}else{
	   				$("#addNewSerialMaster").modal("hide");
	   				swal('',"Insert successful.","");
	   					showAll();
	   				}
	   			}
	   		});
		}			
	}*/


function deleteRecordShowAll(controlNo){


	swal({   
		title: 'Remove this record from the list?',
		showCancelButton: true,
		confirmButtonColor: '#3085d6', 
		cancelButtonColor: '#d33',
		confirmButtonText: 'Yes',
		cancelButtonText: 'No',
		confirmButtonClass: 'confirm-class',
		cancelButtonClass: 'cancel-class',
		closeOnConfirm: false,
		closeOnCancel: false 
		}).then(function() {
			var url = "Handler_DeleteSerialMaster?controlNo=" + controlNo;

			$.ajax({
	  			url: url,
	  			success: function(result) {
	  				var deleteRecord =result.trim();
	  				if(deleteRecord == "ok"){
	  				  swal(
								'', controlNo+ ' has been deleted.',''
								);
	  					if($("#txt-searchAll").val() === "yes"){
	  						showAll();
	  					}else{
	  						window.location = window.location.href;
	  					}
	  				}else{
	  					 swal('',"Record could not be deleted. Record exist in at least 1 order.","");
	  				}
	  			}
	  		});
					},function(dismiss) {
						  if (dismiss === 'cancel') {
						    swal(
						      'Cancelled'
						    );
						  }
						})
}

function showAll()
{
	$("#txt-searchAll").val("yes");
	loader("#serialdata");
	 $("#tableSerial").DataTable().destroy();
	$.get("Table_AllSerialMaster",{},function(data){
	
		$("#serialdata").html(data);
		 serial =  $('#tableSerial').DataTable({
			    responsive: true
			});
	});	
}


$(".CT03MATNO").blur(function(event){

	var matno = $(this).val();

	$.get("SearchVendor",{criteria:matno,action:"keyupCtrlNo"},function(title){

		if(title.trim()=="Error"){
			$(".title").html("<font style='color:red'>Invalid control no.</font>");
			 $("#add-submit").prop('disabled', true);
		}else{
			$(".title").html(title);
			 $("#add-submit").prop('disabled', false);
		}
	   });	

});

$("#controlNo").focusout(function(event){
	var matno = $(this).val();

	$.get("SearchVendor",{criteria:matno,action:"keyupCtrlNo"},function(title){

		if(title.trim()=="Error"){
			$(".title").html("<font style='color:red'>Invalid control no.</font>");
			 $("#btn-submit").prop('disabled', true);
		}else{
			$(".title").html(title);
			 $("#btn-submit").prop('disabled', false);
		}
	   });	
});

$(".CT03VEND").blur(function(event){
	var vendor_details = $(this).val();
	/*$.get("SearchVendorName",{criteria:vendor_details,action:"keyupVendor", type: "vendor"},function(data_vendor){
		alert(data_vendor['Name'] +"hgjg");
		$(".div-vendorName").html(data_vendor)
		//document.getElementById('div-vendorName').innerHTML = data_vendor;
	   });*/	
	
	$.get('SearchVendorName', {
		criteria : vendor_details
    	}, function(responseJson) {
			if(responseJson != null){
				$.each(responseJson, function(key,value) {
					$(".div-vendorName").text(value['Name']);
				});
			}
			
    });
});

$(".binder").focusout(function(event){
	var vendor_details = $(this).val();
	$.get("SearchVendor",{criteria:vendor_details,action:"keyupVendor",type: "binder"},function(data_vendor){
		
		$(".div-binderName").html(data_vendor)
		//document.getElementById('div-vendorName').innerHTML = data_vendor;
	   });		
});

$(".publisher").focusout(function(event){
	var vendor_details = $(this).val();
	$.get("SearchVendor",{criteria:vendor_details,action:"keyupVendor",type: "publisher"},function(data_vendor){

		$(".div-publisherName").html(data_vendor)
		//document.getElementById('div-vendorName').innerHTML = data_vendor;
	   });		
});


$(".currency").change(function(){
	//$(".currency1").val($(this).val());
	$(".CT03RATE").val($(this).find(':selected').attr('data-pub'));
	var result = $(".CT03RATE").val()*$(".foreignCost").val();
	$(".localCost").val(result.toFixed(2));
/*	var result1 = $(".CT03RATE").val()*$("#editforeignCost").val();
	$("#editlocalCost").val(result1.toFixed(2));*/
})

/*$(".currency1").change(function(){
	$(".currency").val($(this).val());
	$(".CT03RATE").val($(this).find(':selected').attr('data-id'));
	var result = $(".CT03RATE").val()*$(".foreignCost").val();
	$(".localCost").val(result.toFixed(2));
	var result1 = $(".CT03RATE").val()*$("#editforeignCost").val();
	$("#editlocalCost").val(result1.toFixed(2));
})*/
/*function showAll()
{
	alert("ssds");
	$("#txt-searchAll").val("yes");
	loader("#div-result");
	$.get("Table_AllSerialMaster",{},function(data){
		alert("ssds11");
		$("#div-result").html(data);
	});	
}*/