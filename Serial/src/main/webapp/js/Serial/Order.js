/**
 * 
 */

function alertMessage(message, info, code, criteria, label){
	var url = "Error_Message?GL79ERRCODE="+code+"" + 
		"&criteria=" + criteria + "&label="+label+"";
	$.ajax({
			url: url,
			success: function(result) {
				//swal(message,result, info );
				//swal(message,result, info );
				swal(result.trim());
			}
		});
}

function processOrdering(){
	var action = "";
	var orderno = [];
	var total = "";
	var referenceNo = $("#reference").val();
	var selectedValue = $("#ordermode option:selected").val();
	var liferayLogin = $("#liferayLogin").val();
	

	if(selectedValue=="0"){
		alertMessage("", "info", "121", "", "");
	}else{
	if(document.getElementById("checkExist").checked){
		action = "IsUniqueReferenceNo";
	}
	
/*	
	 $("input:checkbox[name=orderlist]:checked").each(function () {
		 var order = "";
		 alert($(this).val());
			if(($(this).val()).includes("*")){
				order =  ($(this).val()).replace("*", "");
			}else{
				order = $(this).val();
			}
			
			total = $(this).attr("data-total");
			orderno.push(order);
     });
	 */
	

	$('#orderingList').DataTable().rows().nodes().to$().find(':has(:checkbox:checked)').each(function(){
		 var order = "";
		 var orderNO = $(this).closest('tr').find('td:eq(1)').text().trim(); 
		if((orderNO).includes("*")){
			order =  (orderNO).replace("*", "");
		}else{
			order = orderNO;
		}
		
		orderno.push(order);
        //alert(dataArr);
    });
	 	$.get("Handler_ProcessOrdering",{action:action, referenceNo:referenceNo, orderno:orderno.toString(), ordermode:selectedValue},function(data){
		if(data.trim()=="exist"){
			alertMessage("", "info", "116", "", "");
			//swal("", "Reference No exist", "")
			// alertMessage("", "", "116","", "");
		}else{
			
			$("#modalProcess").modal("hide");
			
			var vendor = $("#input-vendorCode").val();


			//16102019
			/*var url = "GeneratePreviewDocument?orderno=" + orderno.toString()
			+ "&referenceNo=" + referenceNo + "&ordermode=" + selectedValue
			+ "&liferayLogin=" +liferayLogin
			+ "&action=orderLetter";
			window.open(url).print();
			
			if(data.trim()=="Req_Exist"){
				if(!document.getElementById("notification").checked){
					var url = "GeneratePreviewDocument?orderno=" + orderno.toString()
					+ "&referenceNo=" + referenceNo
					+ "&action=notification";
					window.open(url);
				}
			}*/
			//11032020
			if(!document.getElementById("orderLetter").checked){
				var url = "GeneratePreviewDocument?orderno=" + orderno.toString()
				+ "&referenceNo=" + referenceNo + "&ordermode=" + selectedValue
				+ "&liferayLogin=" +liferayLogin  + "&vendor=" + vendor
				+ "&action=orderLetter";
				window.open(url).print();
			}
			else{
				
				$.get("GeneratePreviewDocument",{
		 			orderno : orderno.toString(),
		 			referenceNo : referenceNo,
		 			ordermode : selectedValue,
		 			liferayLogin : liferayLogin, 
		 			vendor : vendor,
		 			action : "orderLetter"
					}, 
			 	function(data,status){
					
			 	}).fail(function(data){
					swal("fail");
				})
			}
			
			if(data.trim()=="Req_Exist"){
				if(!document.getElementById("notification").checked){
					var url = "GeneratePreviewDocument?orderno=" + orderno.toString()
					+ "&referenceNo=" + referenceNo
					+ "&action=notification";
					window.open(url);
			}
				///25112019
				/*else{
					var url = "GeneratePreviewDocument?orderno=" + orderno.toString()
					+ "&referenceNo=" + referenceNo
					+ "&action=notification";
				}*/
			}
			
			
		$('#checkAll').attr('checked',false);
		swal("", "Successfully updated", "");
		
		var checked = $("input[name='optionsRadios']:checked").val()
		var action = "";
		var criteria = "";
		var vendor = "";
		var value = "";
		var value1 = "";
		 if($('input#inlineRadiocharge:checked').length > 0){
			 action = "vendor";
			 vendor = $(".CT03VEND").val();
		 }
		 var ordermode = $("#ordermode option:selected").val();
		 vendor = $(".CT03VEND").val();
		 if(checked =="all"){
			 criteria = "all";
		 }else{
			 criteria = "orderdate";
			 value = $("#orderDate1").val();
			 value1 = $("#orderDate2").val();
		 }
		 
		 $("#orderingList").DataTable().destroy();
		$.get("Table_Ordering",{action:action, criteria:checked, vendor:vendor, value:value, value1:value1, ordermode:ordermode},function(data){

			 $("#OrderList").html(data);
			 var order =  $('#orderingList').DataTable({
				    responsive: true,
				    order: [],
				    columnDefs: [ { orderable: false, targets: [0] ,'searchable': false} ]
				});
		});
			
			$('#checkAll').prop('checked', false);
			//alertMessage("Successful", "success", "005","updated", "@action");
		}
	});

	}
}

function process(){
	var total = 0.00;
	var referenceNo = $("#reference").val();

	if(document.getElementById("checkExist").checked){
		action = "IsUniqueReferenceNo";
	}
	
	$('#orderingList').DataTable().rows().nodes().to$().find(':has(:checkbox:checked)').each(function(){
		 var amt = $(this).closest('tr').find('td:eq(8)').text(); 
		 //amt = amt.replace('RM', '');
		total = parseFloat(total) + parseFloat(amt);
/*		if((orderNO).includes("*")){
			order =  (orderNO).replace("*", "");
		}else{
			order = orderNO;
		}
		
		orderno.push(order);*/
       //alert(dataArr);
   });
	 /*$("input:checkbox[name=orderlist]:checked").each(function () {
			total = parseFloat(total) + parseFloat($(this).attr("data-total"));
     });
	 */
	 
	 //Fund accounting
	 var vendor = $("#input-vendorCode").val();
	// processOrdering();
	 var selectedValue = $("#ordermode option:selected").val();
	
	 checkRefExist().done(function(validate){
			
	if(validate==true){	
	 if(selectedValue!="44"&&selectedValue!="60" ){
	 $.get("FundDistribution",{module:"AAAM0450", vendor:vendor, ref:referenceNo,totalOrder:total, desc:"ser:ordering"},function(data){
		if(data.trim()!="no_funddistribution"){
		 $("#fundDistribution").modal("show");
		 $("#fundContent").html(data);
		}else{
			processOrdering();
		}
		
	 });
	 }else{
		 processOrdering();
	 }}});
		
}

function generate(){
	$.get("Handler_GenerateNo",{functions:"SERPONO"},function(data){
		$("#reference").val(data.trim());
		if($("#reference").val()!=""){
			$('#save').removeAttr('disabled');
		}else{
			 $("#save").attr("disabled", "disabled"); 
		}
	});
	
	return false;
}

function previewOrder(){
	var orderno = [];

	 $("input:checkbox[name=orderlist]:checked").each(function () {
		 var order = "";
			if(($(this).val()).includes("*")){
				order =  ($(this).val()).replace("*", "");
			}else{
				order = $(this).val();
			}
			orderno.push(order);
     });
	 
	 var vendor = $("#input-vendorCode").val();
	 var letterid = "EQ40";
	 
		var url = "GenerateOrderList?letterId=" + letterid
		+ "&orderNo=" + orderno.toString() + "&vendor=" + vendor;
	// var url = "GeneratePreviewDocument?letterId = EQ40&orderNo=" + orderno.toString();
	window.open(url);
}

$("#processOrder").click(function () {
	var selectedValue = $("#ordermode option:selected").val();

	var selectedText = $("#ordermode option:selected").text();
	
	var skip = true;
	
	var orderno = [];
	
	$('#orderingList').DataTable().rows().nodes().to$().find(':has(:checkbox:checked)').each(function(){
		 var orderno = $(this).closest('tr').find('td:eq(1)').text(); 
		 if((orderno).includes("*") && selectedValue != "44"){
				skip = false;
			}else if(!(orderno).includes("*") && selectedValue == "44") {
				skip = false;
			}
   });
	/* $("input:checkbox[name=orderlist]:checked").each(function () {
		if(($(this).val()).includes("*") && selectedValue != "44"){
			skip = false;
		}else if(!($(this).val()).includes("*") && selectedValue == "44") {
			skip = false;
		}
     });*/
	
	
	if(selectedValue=="0"){
		$("#modalProcess").modal("hide");
		alertMessage("", "info", "121", "", "");
	}else{
		
		if(!skip){
			alertMessage("", "info", "122", selectedText,"@ordermode");
			$("#modalProcess").modal("hide");
		}else{
	
		$.get("Modal_Process",{mode:selectedText},function(data){
			$("#modalProcess").modal("show");
			$("#processContent").html(data);
		});
		}
	}
});

function preview(){
	 var selectedValue = $("#ordermode option:selected").text();
	 var selectedValue1 = $("#ordermode option:selected").val();
	 var liferayLogin = $("#liferayLogin").val();
	 var skip = true;

	 
	 if(selectedValue1==0){
		 swal("", "Please choose an order mode", "");
	 }else{
		 var orderno = [];
		 
		 $('#orderingList').DataTable().rows().nodes().to$().find(':has(:checkbox:checked)').each(function(){
			 var ordernos = $(this).closest('tr').find('td:eq(1)').text(); 

			 if((ordernos).includes("*") && selectedValue1 != "44"){
					skip = false;
				}else if(!(ordernos).includes("*") && selectedValue1 == "44") {
					skip = false;
				}
			 
			 
			 var order = "";
			 if((ordernos).includes("*")){
					order =  (ordernos).replace("*", "");
				}else{
					order = ordernos;
				}
				orderno.push(order);
	   });

		/* $("input:checkbox[name=orderlist]:checked").each(function () {
			 var order = "";
			 alert($(this).val());
				if(($(this).val()).includes("*")){
					order =  ($(this).val()).replace("*", "");
				}else{
					 alert("ssa");
					order = $(this).val();
				}
				orderno.push(order);
	     });
		 alert(order.toString());*/
		 var vendor = $("#input-vendorCode").val();
		 var letterid = "";
		//14102019
		 var referenceNo = "(PREVIEW)";
		 
		 if(selectedValue1=="20"){
			 letterid = "ES04";
		 }else if(selectedValue1=="40"){
			 letterid = "ES01";
		 }else if(selectedValue1=="44"){
			 letterid = "ES05";
		 }else if(selectedValue1=="60"){
			 letterid = "ES03";
		 }
		 
		 
		/*	var url = "GenerateOrderList?letterId=" + letterid
			+ "&orderNo=" + orderno.toString() + "&vendor=" + vendor + "&mode=" + selectedValue;
			alert(url);*/

			if(skip==false){
				alertMessage("", "info", "122", selectedValue,"@ordermode");
			}else{
		
				 /*var url = "GeneratePreviewDocument?orderno=" + orderno.toString()
					+ "&referenceNo=" + referenceNo + "&ordermode=" + selectedValue1
					+ "&action=orderLetter";*/
				
				//14102019
				 var url = "GeneratePreviewDocument?orderno=" + orderno.toString()
					+ "&referenceNo=" + referenceNo + "&ordermode=" + selectedValue1
					+ "&liferayLogin=" +liferayLogin
					+ "&action=orderLetter";
					
				// var url = "GeneratePreviewDocument?letterId = EQ40&orderNo=" + orderno.toString();
				window.open(url);
			}
	
	 }
	
}

function retrieveRecords(){

		var checked = $("input[name='optionsRadios']:checked").val()
		var action = "";
		var criteria = "";
		var vendor = "";
		var value = "";
		var value1 = "";
		 if($('input#inlineRadiocharge:checked').length > 0){
			 action = "vendor";
			 vendor = $(".CT03VEND").val();
		 }
		 var ordermode = $("#ordermode option:selected").val();
		 vendor = $(".CT03VEND").val();
		 if(checked =="all"){
			 criteria = "all";
		 }else if(checked == "ctrlno"){
			 criteria = "ctrlno";
			 value = $("#ctrlNo").val();
		 }else{
			 criteria = "orderdate";
			 value = $("#orderDate1").val();
			 value1 = $("#orderDate2").val();
		 }
		 
		 $("#orderingList").DataTable().destroy();
		
		$.get("Table_Ordering",{action:action, criteria:checked, vendor:vendor, value:value, value1:value1, ordermode:ordermode},function(data){

			 $("#OrderList").html(data);
			 order =  $('#orderingList').DataTable({
				    responsive: true,
				    order: [],
				    columnDefs: [ { orderable: false, targets: [0] ,'searchable': false} ]
				});
			 
			 ////////15092020
			 
			 $('#orderingList tbody').on('change', '#orderlist', function(){
					
				 //var el = $('#orderlist').get(0);
			      var totaltick = $('#orderingList :input[type="checkbox"]:checked').length;
			      
				   // If checkbox is not checked
				   if(!this.checked){
					  //$(".saveRetrieve").attr('disabled',true);
					   
					   if(totaltick > 1){
					    	  $("#viewOdrMaint").attr('disabled',true); 
					   }else{
						   $("#processOrder").attr('disabled',false);
						   $("#previewbtn").attr('disabled',false);
						   //$("#orderbtn").attr('disabled',false);
						   $("#viewOdrMaint").attr('disabled',false); 
					   }
				   }else{
					   if(totaltick > 1){
					    	  $("#viewOdrMaint").attr('disabled',true); 
					   }else{
						   $("#processOrder").attr('disabled',false);
						   $("#previewbtn").attr('disabled',false);
						   //$("#orderbtn").attr('disabled',false);
						   $("#viewOdrMaint").attr('disabled',false); 
					   }
					   
				   }
				});
		});
}

//show form bar
function viewDisplay(){
	$('#StatusTab, #FeedbackTab, #ErrorTab').show();
	$("#formdataOrderMain input").attr('disabled','disabled');
	$("#formdataOrderMain input[type=radio]").attr('disabled', true);
	$('#ordermodes, #source, #currency, #frequency').prop('disabled', 'disabled');
	$("#deatilRequest").hide();
	//$("#viewReqestor").show();
	//$("#addRequestor").hide();
	$("#save, #cancel").hide();
		$("#close").show();
		$("#searchCtrlNo, #searchvendor, #searchpatr").attr('disabled', 'disabled');
		$("#orderno").attr('disabled', 'disabled');
		$("#orderhideorView").show();
	//$('#addRequestor, #removeRequestor, #clearRequestor').attr('disabled', 'disabled');
	////... -> xbuat lagi
		
}

function getValue(){
	var rows = $('#orderingList').DataTable()
	   .rows({ 'search': 'applied' })
	   .nodes();
	if($('.orderlist:checked', rows).length>0){
		 $("#processOrder").removeAttr("disabled"); 
		 $("#previewbtn").removeAttr("disabled"); 
		 $("#orderbtn").removeAttr("disabled");  
		 $("#viewOdrMaint").removeAttr("disabled"); 
		 if($('.orderlist:checked').length>1){
				 $("#viewOdrMaint").attr("disabled", "disabled"); 
			}
	}else{
		 $("#processOrder").attr("disabled", "disabled"); 
		 $("#previewbtn").attr("disabled", "disabled"); 
		 $("#orderbtn").attr("disabled", "disabled"); 
		 $("#viewOdrMaint").attr("disabled", "disabled"); 
	}
}


function checkRefExist(){
	
	var referenceNo = $("#reference").val();
	var dfrd1 = $.Deferred();
	var validate = false;
	
	if(document.getElementById("checkExist").checked){
		action = "IsUniqueReferenceNo";
		$.get("Handler_ProcessOrdering",{action:action, referenceNo:referenceNo},function(data){
			if(data.trim()=="exist"){
					alertMessage("", "info", "116", "", "");
					validate = false;
					//swal("", "Reference No exist", "")
					// alertMessage("", "", "116","", "");
				}else{
					validate = true;
					dfrd1.resolve(validate);
				}

		});
	}else{
		validate = true;
		dfrd1.resolve(validate);
	}

	
	
	 return dfrd1.promise();
}

$( document ).ready(function() {
	
	$('body').on('hidden.bs.modal', '#vendorSearch', function () {
		  $(this).removeData('bs.modal');
		});
	
	
	$('#input-vendorCode').focusout(function(){
	//alert("fdsfz");
		 	if($(this).val()!=""){
		 		$("#retrievebtn").removeAttr("disabled"); 
			}else{
				 $("#retrievebtn").attr("disabled", "disabled"); 
			}
		$.get("SearchVendor",{action:"keyupVendorType", criteria:$(this).val(), type:"vendor"},function(data){
			$(".div-vendorName").text(data.trim());
		});
		 	
		 	/*$.get('SearchVendorName', {
				criteria : $(this).val()
		    	}, function(responseJson) {
					if(responseJson != null){
						$.each(responseJson, function(key,value) {
							$(".div-vendorName").text(value['Name']);
						});
					}
					
		    });*/
		
	});
	
	
	//****************************************** AREA FOR MODAL *****************************************//
	 
	 //////modal add, edit, view
	 var stored;
	 $('#modalOdrMaint').on('show.bs.modal', function (event) {
		 var orderno = [];
		var button = $(event.relatedTarget) // Button that triggered the modal
		var modal = $(this);
		var recipient = button.data('whatever'); // Extract info from data-* attributes
		
		 $("input:checkbox[name=orderlist]:checked").each(function () {
			 var order = "";
				if(($(this).val()).includes("*")){
					order =  ($(this).val()).replace("*", "");
				}else{
					order = $(this).val();
				}
				orderno.push(order);
	     });
		//var rowid = button.data('rowid');
		var rowid = orderno.toString();
		//var rowid = button.data('rowid');
		
		modal.find('.modal-title').text(recipient);
		
		switch(recipient){
			
			case state = 'View Record Order':
				viewDisplay();
				$("#div-vendorName, #div-ctrolNo, #namePatr, #dept").empty();
				$("#currency, #frequency").prop("selectedIndex",-1);
				
				////// getOrdermaintenance Particulars n Status part 1
				$.get('getOrdermaintenance', {
		  			rowid : rowid
				 	}, function(responseJson) {
					if(responseJson != null){
						$.each(responseJson, function(key,value) {
							//////////////xhidekn lagi ....
							////display vendor name
							$.get('getVendorName', {
					        	code : value['vendor']
							 	}, function(responseJson) {
								if(responseJson != null){
									$.each(responseJson, function(key,value) {
										$("#div-vendorName").text(value['vendorName']);
									});
								}
							});
							$("#orderno").val(value['orderNumber']);
							$("#createdon").text(value['createdate']);
							$("#input-contorlNo").val(value['ctrlno']);
							$("#div-ctrolNo").text(value['title']);
							$("#input-vendorCode2").val(value['vendor']);
							$("#currency").val(value['currency']);
							$("#frequency").val(value['frequency']);
							$("#volFrom").val(value['volume']);
							$("#volisfrom").val(value['volissue']);
							$("#startDate").val(value['startdate']);
							$("#stopDate").val(value['stopdate']);
							$("#div-exchange-Rate").val(value['exchangeRate']);
							$("#foreign-cost").val(value['fprice']);
							$("#local-cost").val(value['lprice']);
							$("#qty").val(value['qty']);
							$("#issue").val(value['issue']);
							
							$("#div-currentstatus").text(value['desccurrentStatus']);
							$("#div-invoicestatus").text(value['descinvStatus']);
							$("#div-refNo").val(value['refno']);
							$("#orderdate").val(value['oderDate']);
							$("#expectedDate").val(value['createdate']);
								
							$("#claims1").val(value['claim1']);
							$("#claims2").val(value['claim2']);
							$("#claims3").val(value['claim3']);
						});
					}
				});
				
				////get for cardex
				$('#cardex').DataTable( {
					destroy: true,
					searching: false,
					bLengthChange: false,
					info: false,
				    ajax: {
				        url: "getOrdermaintenancecardex?rowid="+rowid,
				        type: "GET",
				        dataSrc: function (json) {
				            var return_data = new Array();
				           
				            for(var i=0;i< json.length; i++){
				              return_data.push({
				                'Copy': json[i].ordercopies,
				                'Volume' : json[i].volume, 
				                'Issue' : json[i].volissue,
				                'Expected Date' : json[i].expectedDate,
				                'Received Date' : json[i].recDate, 
				                'First Claim' : json[i].claim1,
				                'Second Claim' : json[i].claim2,
				                'Third Claim' : json[i].claim3,
				              })
				            }
				            return return_data;
				          },
				     },//This is the end of the AJAX object from the example above
				     	columns    : [
							{'data': 'Copy'},
							{'data': 'Volume'},
							{'data': 'Issue'},
							{'data': 'Expected Date'},
							{'data': 'Received Date'},
							{'data': 'First Claim'},
							{'data': 'Second Claim'},
							{'data': 'Third Claim'}
						]
				});
				
				
				////// getOrdermaintenance Invoice/Payment Details
				$.get('getOrdermaintenanceInvoicePaymentDetails', {
		  			rowid : rowid
				 	}, function(responseJson) {
					if(responseJson != null){
						$.each(responseJson, function(key,value) {
						
							$("#invoiceNo").text(value['invno']);
							$("#invCurrency").text(value['currency'] +" ("+value['exchangeRate'] +")");
							$("#lamount").text(value['lprice']);
							$("#famount").text(value['fprice']);
							$("#invDate").text(value['invdate']);
							$("#paymentRef").text(value['refno']);
							$("#chequeNo").text(value['chequeNo']);
							$("#voucherNo").text(value['voucherNo']);
							$("#chequedate").text(value['chequeDate']);
							$("#voucherdate").text(value['voucherDate']);
						});
					}
				});
				
				////get for requestor  
				$('#requestor').DataTable( {
					destroy: true,
					searching: false,
					bLengthChange: false,
				    ajax: {
				        url: "getOrdermaintenance3?rowid="+rowid,
				        type: "GET",
				        dataSrc: function (json) {
				            var return_data = new Array();
				           
				            for(var i=0;i< json.length; i++){
				              return_data.push({
				                'Request No': json[i].reqno,
				                'Requestor' : json[i].requestor, 
				                'Name' : json[i].name,
				                'Date Requested' : json[i].dateRequestor,
				              })
				            }
				            return return_data;
				          },
				     },//This is the end of the AJAX object from the example above
				     	columns    : [
							{'data': 'Request No'},
							{'data': 'Requestor'},
							{'data': 'Name'},
							{'data': 'Date Requested'},
						],
						/*columnDefs: [
							{
						     	"targets": [ 5 ],
						     	"visible": false,
						     	"searchable": false
							},
						]*/
				});
				
				////get for feedback
				$('#feedback').DataTable( {
					destroy: true,
					searching: false,
					bLengthChange: false,
				    ajax: {
				        url: "getOrdermaintenance4?rowid="+rowid,
				        type: "GET",
				        dataSrc: function (json) {
				            var return_data = new Array();
				           
				            for(var i=0;i< json.length; i++){
				              return_data.push({
				            	  'Date': json[i].date,
					              'Feedback' : json[i].feedback, 
					              'Officer' : json[i].officer
				              })
				            }
				            return return_data;
				          },
				     },//This is the end of the AJAX object from the example above
				     	columns    : [
							{'data': 'Date'},
							{'data': 'Feedback'},
							{'data': 'Officer'}
						]
				});
				
				////get for errorInSupply
				$('#errorInSupply').DataTable( {
					destroy: true,
					searching: false,
					bLengthChange: false,
				    ajax: {
				        url: "getOrdermaintenance5?rowid="+rowid,
				        type: "GET",
				        dataSrc: function (json) {
				            var return_data = new Array();
				           
				            for(var i=0;i< json.length; i++){
				              return_data.push({
				                'Date': json[i].date,
				                'Feedback' : json[i].feedback, 
				                'Officer' : json[i].officer
				              })
				            }
				            return return_data;
				          },
				     },//This is the end of the AJAX object from the example above
				     	columns    : [
							{'data': 'Date'},
							{'data': 'Feedback'},
							{'data': 'Officer'}
						]
				});
				
				break;
		}
		
	 });
	 
	
	$("[title]").tooltip();
	//11032020
	/*var order = $('#orderingList').DataTable({
	    responsive: true,
	    order: [],
	    columnDefs: [ { orderable: false, targets: [0] ,'searchable': false} ]
	});*/
	
	$("input[name=optionsRadios]").change(function(){
		if($(this).val()=="ctrlno"){
			$("#ctrlNo").removeAttr("disabled"); 
			 $("#orderDate1").val("");
			 $("#orderDate2").val("");
		}else if($(this).val()!="ctrlno"){
			 $("#ctrlNo").attr("disabled", "disabled"); 
		}
		
		if($(this).val()=="orderdate"){
			 $("#ctrlNo").val("");
		 }else if($(this).val()=="all"){
			 $("#ctrlNo").val("");
			 $("#orderDate1").val("");
			 $("#orderDate2").val("");
		 }
	})
	
	/*$("#retrieveRecord").click(function () {
		var checked = $("input[name='optionsRadios']:checked").val()
		var action = "";
		var criteria = "";
		var vendor = "";
		var value = "";
		var value1 = "";
		 if($('input#inlineRadiocharge:checked').length > 0){
			 action = "vendor";
			 vendor = $(".CT03VEND").val();
		 }
		 var ordermode = $("#ordermode option:selected").val();
		 vendor = $(".CT03VEND").val();
		 if(checked =="all"){
			 criteria = "all";
		 }else if(checked == "ctrlno"){
			 criteria = "ctrlno";
			 value = $("#ctrlNo").val();
		 }else{
			 criteria = "orderdate";
			 value = $("#orderDate1").val();
			 value1 = $("#orderDate2").val();
		 }
		 
		 order.destroy();
		
		$.get("Table_Ordering",{action:action, criteria:checked, vendor:vendor, value:value, value1:value1, ordermode:ordermode},function(data){

			 $("#OrderList").html(data);
			 order = $("#orderingList").DataTable();
		});
	});*/
	
	
/*	 $('input[type=checkbox]').change(function() {
		   
		  if(this.checked != true){
		       alert('you need to be fluent in English to apply for the job '+$("#myname").val());
		     }
	 	}); */ 
	/*$(".orderlist").click(function(){
		alert("ssd");
		if($('.orderlist:checked').length>1){
			 $("#viewOdrMaint").attr("disabled", "disabled"); 
		}else{
			 $("#viewOdrMaint").removeAttr("disabled"); 
		}
	});
	*/
	$(".checkAll").change(function () {
		 var rows = $('#orderingList').DataTable().rows({ 'search': 'applied' }).nodes();
	/*	 var rows = $('#orderingList').find('tbody tr');
		  var checked = $(this).prop('checked');
		 $.each(rows, function() {
		      var checkbox = $($(this).find('td').eq(0)).find('input.orderlist').prop('checked', checked);
		      //$(".orderlist").prop('checked', $(this).prop('checked'));
		   });*/
		 
		 if(!this.checked){
	    	   $('input[type="checkbox"]', rows).prop('checked', false);
	       }else{
	    	   $('input[type="checkbox"]', rows).prop('checked', this.checked);
	       }
		 
		 
		 if($('.orderlist:checked').length>0){
			 $("#processOrder").removeAttr("disabled"); 
			 $("#previewbtn").removeAttr("disabled"); 
			 $("#orderbtn").removeAttr("disabled");  
			 $("#viewOdrMaint").removeAttr("disabled"); 
			 if($('.orderlist:checked').length>1){
					 $("#viewOdrMaint").attr("disabled", "disabled"); 
				}
		}else{
			 $("#processOrder").attr("disabled", "disabled"); 
			 $("#previewbtn").attr("disabled", "disabled"); 
			 $("#orderbtn").attr("disabled", "disabled"); 
			 $("#viewOdrMaint").attr("disabled", "disabled"); 
		}
		});
	
	$("#processOrder").click(function () {
		var selectedValue = $("#ordermode option:selected").val();

		var selectedText = $("#ordermode option:selected").text();
		
		var skip = true;
		
		var orderno = [];
		
		$('#orderingList').DataTable().rows().nodes().to$().find(':has(:checkbox:checked)').each(function(){
			 var orderno = $(this).closest('tr').find('td:eq(1)').text(); 
			 if((orderno).includes("*") && selectedValue != "44"){
					skip = false;
				}else if(!(orderno).includes("*") && selectedValue == "44") {
					skip = false;
				}
	   });
		/* $("input:checkbox[name=orderlist]:checked").each(function () {
			if(($(this).val()).includes("*") && selectedValue != "44"){
				skip = false;
			}else if(!($(this).val()).includes("*") && selectedValue == "44") {
				skip = false;
			}
	     });*/
		
		
		if(selectedValue=="0"){
			$("#modalProcess").modal("hide");
			alertMessage("", "info", "121", "", "");
		}else{
			
			if(!skip){
				alertMessage("", "info", "122", selectedText,"@ordermode");
				$("#modalProcess").modal("hide");
			}else{
		
			$.get("Modal_Process",{mode:selectedText},function(data){
				$("#modalProcess").modal("show");
				$("#processContent").html(data);
			});
			}
		}
	});
	
	$("#reference").focusout(function(){
		if($(this).val()!=""){
			$('#save').removeAttr('disabled');
		}else{
			 $("#save").attr("disabled", "disabled"); 
		}
	})

	
/*	function getFund(){
		var e = document.getElementById("ddlViewBy");
		var strUser = e.options[e.selectedIndex].value;
		alert(strUser);
		 $("#orderlist tbody").find("td:nth-child(2)").text(strUser);
		}*/

});
