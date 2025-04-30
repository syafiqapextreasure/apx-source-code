/**
 * 
 */

$(document).ready(function() {
	
	/////Call error message page
	function messageBox(code, criteria, label){
	      var url = "Error_Page?GL79ERRCODE="+code+"" +
	      "&criteria=" + criteria + "&label="+label+"";
	      //alert(url);
	      $.ajax({
	            url: url,
	            success: function(result) {
	                 //swal(result); //, 'warning' 
	            	setTimeout(function(){
	            		swal(result);
	        	     },700);
	            }
	      });
	}
	
	//////////////////////  function loader //////////////////////////////////////
	function loader(target_id) {
		$(target_id).load('Loading');
	}
	
	/////////////////////  CLICK button-vendorSearch /////////////////////////////
	$("#button-vendorSearch").click(function(){
		
		$.get("Modal_Vendor" ,function(data){
			$("#modal_vendorSearchContent").html(data);
		});
	});
	
	///////////////////// function checkbox  ///////////////////////////////////	
	$(':checkbox').on('change',function(){
		// var th = $(this), name = th.prop('name'); 
		var idcheckbox = $(this).attr("id");
		//alert(idcheckbox);
		
		switch(idcheckbox){
	        case "checkbox-vendorCode":
	        	$("#input-vendorCode").focus();
	       	 	break;
	        case "checkbox-date":
	        	$("#input-startDate").focus();
	       	 	break;
	        case "checkbox-orderNo":
	        	$("#input-orderNo").focus();
	       	 	break;
	        case "checkbox-referenceNo":
	        	$("#input-referenceNo").focus();
	       	 	break;
	        default:
        }
	});
	
	///////////////////// input-startDate set Current Date  ///////////////////////////////////
	var today = new Date();  
	$('#input-startDate').datepicker({
		//startDate : today,
		format: "dd/mm/yyyy",
		todayBtn: true,
		autoclose: true,
		todayHighlight: true,
	});
	
	///////////////////// input-endDate set Current Date  ///////////////////////////////////
	
	$('#input-endDate').val(moment(today).format("DD/MM/YYYY"));
	$('#input-endDate').datepicker({
		format: "dd/mm/yyyy",
		todayBtn: true,
		autoclose: true,
		todayHighlight: true,
	});
	
	///////button-claim1, button-claim2, button-claim3, button-unclaimed, button-retrieveAll///
	
	$('#button-claim1, #button-claim2, #button-claim3, #button-unclaimed, #button-retrieveAll').click(function(e){
		var getButtonIdClick = e.currentTarget.id;
		
		/* Generate URL */
		var url = "SearchOrderMngt?";
		
		/* Append URL */
		// Append action
		var params = "btnClick=" + getButtonIdClick;
		
		var inputVendorCode = $("#input-vendorCode").val();
		var inputStartDate = $("#input-startDate").val();
		var inputEndDate = $("#input-endDate").val();
		var inputOrderNo = $("#input-orderNo").val();
		var inputReferenceNo = $("#input-referenceNo").val();

		//alert($(':checkbox:checked').length);
		
		$(':checkbox:checked').each(function(){        
	        var values = $(this).attr("id");
	        
	        ///// Vendor
	        if(values=="checkbox-vendorCode"){
	        	params += "&vendorCode=" + inputVendorCode;
	        }
	        
	        ///// Date
	        if(values=="checkbox-date"){
	        	var newStartDate = moment(inputStartDate,'DD/MM/YYYY').format('YYYYMMDD');
	        	var newEndDate = moment(inputEndDate,'DD/MM/YYYY').format('YYYYMMDD');
	        	
	        	if(inputStartDate != "") {
	        		params += "&startDate=" + newStartDate;
	        	}
	        	
	        	if(inputEndDate != "") {
	        		params += "&endDate=" + newEndDate;
	        	}
	        }
	        
	        ///// Order No
	        if(values=="checkbox-orderNo"){
	        	params += "&orderNo=" + inputOrderNo;
	        }
	        
	        ///// Reference No
	        if(values=="checkbox-referenceNo"){
	        	//alert("Reference No");
	        	params += "&referenceNo=" + inputReferenceNo;
	        }	        
	    });
		//$('#result').collapse("show");
		$('#result').collapse("show");
		loader("#table-order");
		/*loader("#search-result");
		alert("sd");*/
		$.get(url + params,function(data){
			
			/* Replace content with loader */
			/*replaceLoader('search-result');*/
			
			/* Scroll to search result */
			/*animateScroll('panel-result');*/
			//loader("#search-result");
			//$('#result').collapse("show");
			$("#table-order").html(data);
		});
		//alert(url + params + " kkk");
	})

 
	function loader(target_id) {
		$(target_id).load('Loading');
	}

	//////////////////////////////////////////TABEL table-order ////////////////////////////////////////////
	/*$('#table-order').DataTable({
		
	});*/
	
	//////////////////////////// vendor //////////////////////////////////////////////////////////
	$( "#input-vendorCode" ).keyup(function() {
		var vendorCode = $("#input-vendorCode").val();
		//$('#checkbox-vendorCode').attr('checked', true);
		
		$("#div-vendorName").empty();
		
		if(vendorCode == ""){
			//$("#button-retrievenewreq").attr('disabled',false);
			$('#checkbox-vendorCode').prop('checked', false);
			$("#input-vendorCode").css("border", "");
		}else{
		////display vendor name
		$.get('getVendorName', {
        	code : vendorCode
		 	}, function(responseJson) {
		 	if(responseJson==''){
		 		//$("#button-retrievenewreq").attr('disabled',true);
		 		$("#input-vendorCode").css("border", "solid red");
		 		$('#checkbox-vendorCode').prop('checked', false);
		 	}else{
		 	
				$.each(responseJson, function(key,value) {
					$("#div-vendorName").text(value['vendorName']);
					//$("#button-retrievenewreq").attr('disabled',false);
					$('#checkbox-vendorCode').prop('checked', true);
					$("#input-vendorCode").css("border", "");
				});
			}
		});
		}
	});
	
	////////////////////////////Order Date //////////////////////////////////////////////////////////
	/*$( "#input-startDate" ).keyup(function() {
		$('#checkbox-date').attr('checked', true);
	});
	
	$( "#input-startDate" ).focusout(function() {
		$('#checkbox-date').attr('checked', true);
	});*/
	$( "#input-startDate" ).bind("keyup focusout", function () {
		$('#checkbox-date').attr('checked', true);
	});
	
	////////////////////////////orderNo //////////////////////////////////////////////////////////
	$( "#input-orderNo" ).keyup(function() {
		$('#checkbox-orderNo').attr('checked', true);
	});
	
	////////////////////////////Reference Number //////////////////////////////////////////////////////////
	$( "#input-referenceNo" ).keyup(function() {
		$('#checkbox-referenceNo').attr('checked', true);
	});
	
	//////////REFERESH CLICK ////////////////////////////////////////////////////////
	$("#button-refresh").click(function(e){
		$('input[type=text]').val('');  
		$("#div-vendorName").empty();
		$('input[name=checkbox]').prop('checked', false);
		$('#tableOrder').dataTable().fnClearTable();
		$(".button-cancellation, .button-errorInSupply, .button-vendorFeedback").hide();
	});
	

        
});
