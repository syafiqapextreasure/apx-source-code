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
	//alert("process");
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
		 var orderNO = $(this).closest('tr').find('td:eq(1)').text(); 
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
			if(!document.getElementById("orderLetter").checked){
				//alert("sdsdsd1");
				var url = "GeneratePreviewDocument?orderno=" + orderno.toString()
				+ "&referenceNo=" + referenceNo + "&ordermode=" + selectedValue
				+ "&action=orderLetter";
				alert(url);
				window.open(url);
			}
			
			if(data.trim()=="Req_Exist"){
				if(!document.getElementById("notification").checked){
					var url = "GeneratePreviewDocument?orderno=" + orderno.toString()
					+ "&referenceNo=" + referenceNo
					+ "&action=notification";
					window.open(url);
				}
			}
			
		swal("", "Successfully updated", "");
			retrieveRecord();
			//alertMessage("Successful", "success", "005","updated", "@action");
		}
	});

	}
}

function process(){
	var total = 0.00;
	var referenceNo = $("#reference").val();

	
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
	
	 if(selectedValue!="44"&&selectedValue!="60" ){
	 $.get("FundDistribution",{module:"AAAM0450", vendor:vendor, ref:referenceNo,totalOrder:total, desc:"Acq : Ordering"},function(data){
		if(data.trim()!="no_funddistribution"){
		 $("#fundDistribution").modal("show");
		 $("#fundContent").html(data);
		}else{
			processOrdering();
		}
		
	 });
	 }else{
		 processOrdering();
	 }
		
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

function preview(){

	 var selectedValue = $("#ordermode option:selected").text();
	 var selectedValue1 = $("#ordermode option:selected").val();
	 var liferayLogin = $("#liferayLogin").val();
	 
	 if(selectedValue==0){
		 swal("", "Please choose an order mode", "");
	 }else{
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
		 var letterid;
		 
		 //var referenceNo = $("#reference").val();
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
		 
		 //14102019
		 var url = "GeneratePreviewDocument?orderno=" + orderno.toString()
			+ "&referenceNo=" + referenceNo + "&ordermode=" + selectedValue1
			+ "&liferayLogin=" +liferayLogin
			+ "&action=orderLetter";
		 
		/* var url = "GeneratePreviewDocument?orderno=" + orderno.toString()
			+ "&referenceNo=" + referenceNo + "&ordermode=" + selectedValue1
			+ "&action=orderLetter";*/
			
		// var url = "GeneratePreviewDocument?letterId = EQ40&orderNo=" + orderno.toString();
		window.open(url);
	 }
	
}

function retrieveRecord(){
	alert("sddsss");
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
	$('#ordermodes, #source, #currency, #subj').prop('disabled', 'disabled');
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

/*function getValue(){

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
}*/

$( document ).ready(function() {
	
	$('body').on('hidden.bs.modal', '#vendorSearch', function () {
		  $(this).removeData('bs.modal');
		});
	
	
	$('#input-vendorCode').focusout(function(){
		if($(this).val()!=""){
		 		 $("#retrievebtn").removeAttr("disabled"); 
			}else{
				 $("#retrievebtn").attr("disabled", "disabled"); 
			}

		 	$.get("SearchVendor",{action:"keyupVendor", criteria:$(this).val()},function(data){
				$(".div-vendorName").text(data.trim());
			});
	});
	
	
	//****************************************** AREA FOR MODAL *****************************************//
	 
	 //////modal add, edit, view
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
		modal.find('.modal-title').text(recipient);
		switch(recipient){
			case state = 'View Record Order':
				//viewDisplay();
				$("#div-vendorName, #div-ctrolNo, #namePatr, #dept").empty();
				$("#ordermodes, #source, #currency").prop("selectedIndex",-1);
				 viewDisplay();
				////// getOrdermaintenance Particulars n Status part 1
				$.get('getOrdermaintenance', {
		  			rowid : rowid
				 	}, function(responseJson) {
					if(responseJson != null){
						$.each(responseJson, function(key,value) {
							//////////////xhidekn lagi ....
							////display vendor name
						/*	$.get('getVendorName', {
					        	code : value['vendor']
							 	}, function(responseJson) {
								if(responseJson != null){
									$.each(responseJson, function(key,value) {
										$("#div-vendorName").text(value['vendorName']);
									});
								}
							});*/
							
							/// when COPIES ////
							if(value['orderSet'] == '0'){
								$('input[name=quantity][value=copies]').attr('checked', true);
					        	$('input[name=quantity][value=set]').attr('checked', false);
								$("#copies").val(value['ordercopies']);
								$("#set").val('');
								$("#copiesSet").val('');
							}else{
								$('input[name=quantity][value=set]').attr('checked', true); 
								$('input[name=quantity][value=copies]').attr('checked', false); 
								$("#set").val(value['orderSet']);
								$("#copiesSet").val(value['ordercopies']);
								$("#copies").val('');
							}
							$("#orderno").val(value['orderNumber']);
							$("#input-contorlNo").val(value['ctrlno']);
							$("#div-ctrolNo").text(value['title']);
							$("#vendorcode").val(value['vendor']);
							$("#ordermodes").val(value['ordermode']);
							$("#source").val(value['source']);
							$("#currency").val(value['currency']);
							$("#subj").val(value['subj']);
							$("#div-exchange-Rate").val(value['exchangeRate']);
							$("#foreign-amount").val(value['fprice']);
							$("#local-amount").val(value['lprice']);
							
							
							$("#div-status").text(value['currentStatusDesc']);
							$("#div-refNo").val(value['refno']);
							$("#orderdate").val(value['oderDate']);
							$("#expectedDate").val(value['expectedDate']);
							$("#claims1").val(value['claim1']);
							$("#claims2").val(value['claim2']);
							$("#claims3").val(value['claim3']);
						});
					}
				});
				
				////// getOrdermaintenance Status part 2
				$('#status').DataTable( {
					destroy: true,
					searching: false,
					bLengthChange: false,
				    ajax: {
				        url: "getOrdermaintenance2?rowid="+rowid,
				        type: "GET",
				        dataSrc: function (json) {
				            var return_data = new Array();
				           
				            for(var i=0;i< json.length; i++){
				              return_data.push({
				                'Accession Number': json[i].accno,
				                'Branch' : json[i].branch, 
				                'Location' : json[i].locadesc,
				                'Item Category' : json[i].itemCategory,
				                'Condition' : json[i].condition, 
				                'SMD' : json[i].smd
				              })
				            }
				            return return_data;
				          },
				     },//This is the end of the AJAX object from the example above
				     	columns    : [
							{'data': 'Accession Number'},
							{'data': 'Branch'},
							{'data': 'Location'},
							{'data': 'Item Category'},
							{'data': 'Condition'},
							{'data': 'SMD'}
						]
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
				                'Department' : json[i].department,
				                'Date Requested' : json[i].dateRequestor,
				                '': ''
				              })
				            }
				            return return_data;
				          },
				     },//This is the end of the AJAX object from the example above
				     	columns    : [
							{'data': 'Request No'},
							{'data': 'Requestor'},
							{'data': 'Name'},
							{'data': 'Department'},
							{'data': 'Date Requested'},
							{'data': ''}
						],
						columnDefs: [
							{
						     	"targets": [ 5 ],
						     	"visible": false,
						     	"searchable": false
							},
						]
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
	var order = $('#orderingList').DataTable({
	    responsive: true,
	    order: [],
	    columnDefs: [ { orderable: false, targets: [0] ,'searchable': false} ]
	});
	
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
			 //$("#orderbtn").removeAttr("disabled");  
			 $("#viewOdrMaint").removeAttr("disabled"); 
			 if($('.orderlist:checked').length>1){
					 $("#viewOdrMaint").attr("disabled", "disabled"); 
				}
		}else{
			 $("#processOrder").attr("disabled", "disabled"); 
			 $("#previewbtn").attr("disabled", "disabled"); 
			 //$("#orderbtn").attr("disabled", "disabled"); 
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
