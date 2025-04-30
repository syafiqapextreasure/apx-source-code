/**
 * 
 */
	
	var text = [];  
	
//In use
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

function showDialog2(order) {
	 $("#prediction").removeClass("fade").modal("hide");
	 $("#prediction").addClass("fade").modal("show");
		 $("#prediction").modal("show");
		 $.get("Modal_Renew",{orderno:order},function(data){
			 $("#predictionContent").html(data);
			 $.get("Load_Cardex",{orderno:order},function(result){
				 $("#table-cardex").html(result);
				 });
			 });

}
//In use
function printRenew(){
	var selectedValue = $("#ordermode option:selected").val();
	var selectedText = $("#ordermode option:selected").text();
	var skip = true;
	var orderNO = [];
	
	if(selectedValue=="0"){
		alertMessage("", "info", "121", "", "");
		localstorage.clear();
	}else{
	$('#orderingList').DataTable().rows().nodes().to$().find(':has(:checkbox:checked)').each(function(){

		 var orderno = $(this).closest('tr').find('td:eq(1)').text(); 

		 if((orderno).includes("*") && selectedValue != "44"){
				skip = false;
			}else if(!(orderno).includes("*") && selectedValue == "44") {
				skip = false;
			}
		 
		 orderNO.push(orderno.trim());

		 if(!skip){

				alertMessage("", "info", "122", selectedText,"@ordermode");
				localstorage.clear();
			}else{
		
		 $("#prediction").modal("show");
		 $.get("Modal_Renew",{orderno:orderNO[0].trim()},function(data){
			 $("#predictionContent").html(data);
			 $.get("Load_Cardex",{orderno:orderNO[0].trim()},function(result){
				 $("#table-cardex").html(result);
				 $("#ordernoarray").val(order.toString());
				 });
			 });
		 
			}
		/* var orderNO = $(this).closest('tr').find('td:eq(1)').text(); 
		if((orderNO).includes("*")){
			order =  (orderNO).replace("*", "");
		}else{
			order = orderNO;
		}
		
		orderno.push(order);*/
       //alert(dataArr);
		
			/*	 $("#prediction").modal("show");
				 $.get("Modal_Renew",{orderno:orderno},function(data){
					 $("#predictionContent").html(data);
					 $.get("Load_Cardex",{orderno:orderno.trim()},function(result){
						 $("#table-cardex").html(result);
						 });
					 });*/
			
   });
	localStorage.setItem("issueOrder", orderNO.toString());
	localStorage.setItem("orderlist", orderNO.toString());
	/* localStorage.setItem("lastname", order.toString());
	 openModal(order.toString());*/
	}
	 
}

//In use
var test1 = "";
function processRenewal(){
	
	var firstVolume = $("#firstVolume").val();
	var pattern = $("#hidden-patternID").val();
	var firstPattern = $("#issuePattern").val();
	
	var localCost = $("#localCost").val();
	var foreignCost = $("#foreignCost").val();
	var exchangeRate = $("#exchangeRate").val();
	var startDate = $(".startDate").val();
	var noOfIssues = $(".numberOfIssues").val();
	var quantity = $(".copies").val();
	var freq = $('.frequency :selected').val();
	var order = $("#ordernoval").val();
	var getOrder = localStorage.getItem("orderlist");
	var vendor = $("#input-vendorCode").val();
	var matno = $("#matno").val();
	var currency = $('.currency :selected').val();
	var selectedValue = $("#ordermode option:selected").val();
	
	var numbersArray = getOrder.split(',');
	var orderArray = [];
	for(var i=0;i<numbersArray.length;i++){
		orderArray.push(numbersArray[i]);
	}

	orderArray.splice($.inArray(order, orderArray),1);
	localStorage.setItem("orderlist", orderArray.toString());

	if(vendor==""||quantity==""||quantity=="0"||noOfIssues==""||noOfIssues=="0"||firstPattern==""||firstVolume==""||
			startDate==""|| exchangeRate==""||exchangeRate=="0"||foreignCost==""||localCost=="")
		alert("Please fill in necessary details.");
	else
	{
		
		if(typeof orderArray[0] === "undefined"){
			$("#prediction").modal("hide");
			var selectedText = $("#ordermode option:selected").text();
			$("#modalProcess").modal("show");
			$.get("Modal_ProcessRenewal",{mode:selectedText},function(data){
				$("#processContents").html(data);
			});
			//text.push('{"id":"1","forex":"'+currency+'", "fcost" : "'+foreignCost+'", "lcost" : "'+localCost+'", "vendor" :"'+vendor+'", "prate":"'+exchangeRate+'"  }')
			//order = [];
		}else{
			//text.push('{"id":"1","forex":"'+currency+'", "fcost" : "'+foreignCost+'", "lcost" : "'+localCost+'", "vendor" :"'+vendor+'", "prate":"'+exchangeRate+'"  }')
			showDialog2(orderArray[0]);
		}

		var vollbl = [];
		var issulbl = [];
		var expdate = [];
		
		$('#patternList tbody tr').each(function(){
			vollbl.push( $(this).closest('tr').find('td:eq(1)').text());
			issulbl.push( $(this).closest('tr').find('td:eq(2)').text());
			expdate.push( $(this).closest('tr').find('td:eq(3)').text());
		})
		
		$.get("Handler_StoreIssuesList",{noOfIssues:noOfIssues, quantity:quantity,vollbl:vollbl,
				issulbl:issulbl,expdate:expdate, matno:matno, freq:freq, total:vollbl.length},
					function(message){
					var orderArray = localStorage.getItem('newOrder');
					orderArray = orderArray ? orderArray.split(',') : [];
					orderArray.push(message.trim());
					localStorage.setItem('newOrder', orderArray.toString());
						$.get("Handler_ProcessRenewal",{action:"AddRecord", orderno:message.trim(), 
													ordermode:selectedValue,currency:currency,prate:exchangeRate,fprice:foreignCost, 
													lprice:localCost, vendor:vendor},function(data){
														var datas = data.trim().split(",");
														localStorage.setItem('orders', datas);
							
						});
					});

		//localStorage.setItem("newOrder", order.toString());
	}

}

//16062020
/*function updateCardex()
{
	//Clear all
	$("table-cardex").html("");
	var data;
	
	var firstVolume = $("#firstVolume").val();
	var quantity = $("#quantity").val();
	var noOfIssues = $("#numberOfIssues").val();
	var startDate = $(".startDate").val();
	var pattern = $("#hidden-patternID").val();
	//var freq = $('#frequency:selected').val(); 
	var freq = $('.frequency :selected').val();
	
	if(pattern==""){
		$("#hidden-firstPattern").val($("#issuePattern").val());
		var pattern = $("#hidden-firstPattern").val();
		findPattern(pattern);
	}else{
		if(startDate != ""){
			calStopDate(startDate, freq, noOfIssues);
		}
	}
	
}
*/

//16062020
/*function findPattern(pattern){
	var url = "GetCardexPattern?pattern=" +pattern;

	var stopDate = null;
	$.ajax({
		url: url,
		success: function(result){
			if(result.trim()==0){
				$("#hidden-firstPattern").val("");
				$("#issuePattern").val("");
				swal("", "Pattern does not exist. Please select new pattern", "");
				 $(".swal2-modal").css('background-color', 'grey');
				 $(".swal2-modal").css('color', 'white');
			}else{
				$("#hidden-patternID").val(result.trim());
				updateCardex();
			}
		}
});
}*/


//16062020
/*function calStopDate(startDate, pattern, noOfIssues)
{
	var url = "ComputeStopDate?startDate=" +startDate+"&freq=" +pattern+ "&noOfIssues="+noOfIssues;

	var stopDate = null;
	$.ajax({
		url: url,
		success: function(result){
		
			$("#stopDate-disabled").val(result);
			generateTableCardex();
		}
	});
}*/

//16062020
/*//In use
$(".numberOfIssues").focusout(function(){
	updateCardex();
})

//In use
$(".copies").focusout(function(){
	//generateTableCardex();
	updateCardex();
})

//In use
$("#firstVolume").focusout(function(){
	//generateTableCardex();
	updateCardex();
})

//In use
$("#issuePattern").focusout(function(){
	//generateTableCardex();
	updateCardex();
})

//In use
$("#frequency").change(function(){
	//generateTableCardex();
	updateCardex();
})*/

//16062020
/*function getTableCardex(){
	var firstVolume = $("#firstVolume").val();
	var quantity = $(".copies").val();
	var noOfIssues = $(".numberOfIssues").val();
	var startDate = $(".startDate").val();
	var freq = $('.frequency :selected').val();
	

	if(firstVolume == "" || quantity == "" || noOfIssues == "" || startDate== "")
		alert("Please fill in necessary details.");
	else
	{
		var url = "Table_GenerateIssuesList?patternID=" +pattern+"&firstPattern=" +firstPattern+"&startDate="+startDate;
		url += "&quantity="+quantity+"&noOfIssues=" + noOfIssues+"&firstVolume="+firstVolume+"&freq="+freq;
		
   		$.ajax({
   			// Title
   			url: url,
   			success: function(result) {
   				$("#table-cardex").html(result);
   			}
   		});
	}
}*/

//16062020
//In use
/*function generateTableCardex(){

	var firstVolume = $("#firstVolume").val();
	var quantity = $(".copies").val();
	var noOfIssues = $(".numberOfIssues").val();
	var startDate = $(".startDate").val();
	var pattern = $("#hidden-patternID").val();
	var firstPattern = $("#hidden-firstPattern").val();
	var freq = $('.frequency :selected').val();
	var url = "Table_GenerateIssuesList?patternID=" +pattern+"&firstPattern=" +firstPattern+"&startDate="+startDate;
	url += "&quantity="+quantity+"&noOfIssues=" + noOfIssues+"&firstVolume="+firstVolume+"&freq="+freq;
	
	
	if(firstVolume == "" || quantity == "" || noOfIssues == "" || startDate== ""|| pattern=="" || firstPattern == "")
		alert("Please fill in necessary details.");
	else
	{
		var url = "Table_GenerateIssuesList?patternID=" +pattern+"&firstPattern=" +firstPattern+"&startDate="+startDate;
		url += "&quantity="+quantity+"&noOfIssues=" + noOfIssues+"&firstVolume="+firstVolume+"&freq="+freq;
		
   		$.ajax({
   			// Title
   			url: url,
   			success: function(result) {
   				$("#table-cardex").html(result);
   			}
   		});
	}
}*/

function process(){
	
	var order = [];
	$('#orderingList').DataTable().rows().nodes().to$().find(':has(:checkbox:checked)').each(function(){
		 var orderno = $(this).closest('tr').find('td:eq(1)').text(); 
		 order.push(orderno);
  });
	
	var url = "Handler_StoreIssue?order=" +order+"&firstPattern=" +firstPattern+"&startDate="+startDate;
	url += "&quantity="+quantity+"&noOfIssues=" + noOfIssues+"&firstVolume="+firstVolume+"&freq="+freq;
	
		$.ajax({
			// Title
			url: url,
			success: function(result) {
				$("#table-cardex").html(result);
			}
		});
}

//In use
function renew(){
	var total = 0.00;
	var referenceNo = $("#reference").val();

	
	$('#orderingList').DataTable().rows().nodes().to$().find(':has(:checkbox:checked)').each(function(){
		 var amt = $(this).closest('tr').find('td:eq(8)').text(); 
		 //amt = amt.replace('RM', '');
		total = parseFloat(total) + parseFloat(amt);
   });
	 //Fund accounting
	 var vendor = $("#input-vendorCode").val();
	// processOrdering();
	 var selectedValue = $("#ordermode option:selected").val();
	
/*	 if(selectedValue!="44"&&selectedValue!="60" ){
	 $.get("FundDistribution",{module:"AAAM0450", vendor:vendor, ref:referenceNo,totalOrder:total, desc:"acq:ordering"},function(data){
		if(data.trim()!="no_funddistribution"){
		 $("#fundDistribution").modal("show");
		 $("#fundContent").html(data);
		}else{
			executeRenewal();
		}
		
	 });
	 }else{*/
		 executeRenewal();
	 //}
		
}

//In use
function executeRenewal(){
	var action = "";
	var orderno = [];
	var total = "";
	var referenceNo = $("#reference").val();
	var selectedValue = $("#ordermode option:selected").val();

	if(selectedValue=="0"){
		alertMessage("", "info", "121", "", "");
	}else{
	if(document.getElementById("checkExist").checked){
		action = "IsUniqueReferenceNo";
	}

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
	
	var getOrder = localStorage.getItem("newOrder");
	var orders = localStorage.getItem("orders");
	$.get("Handler_ProcessRenewal",{action:"UpdateRef", referenceNo:referenceNo, orderno:getOrder, ordermode:selectedValue,  oldOrder:orderno, total:orderno.length},function(data){

		if(data.trim()=="exist"){
			alertMessage("", "info", "116", "", "");
		}else if(data.trim()=="Successful"){
			
			$("#modalProcess").modal("hide");
				var url = "GeneratePreviewDocument?orderno=" + orders
				+ "&referenceNo=" + referenceNo + "&ordermode=" + selectedValue
				+ "&action=orderLetter";

				window.open(url);
				localStorage.clear();
				swal("", "Successfully renewed", "");
				location.reload();
				retrieveRecord();
			
		
			/*if(!document.getElementById("orderLetter").checked){
				alert("sdsdsd1");
				var url = "GeneratePreviewDocument?orderno=" + orderno.toString()
				+ "&referenceNo=" + referenceNo + "&ordermode=" + selectedValue
				+ "&action=orderLetter";
				//alert(url);
				localstorage.clear();
				window.open(url);
			}*/
			
		/*	if(data.trim()=="Req_Exist"){
				if(!document.getElementById("notification").checked){
					var url = "GeneratePreviewDocument?orderno=" + orderno.toString()
					+ "&referenceNo=" + referenceNo
					+ "&action=notification";
					window.open(url);
				}
			}*/
			
		swal("", "Successfully updated", "");
			retrieveRecord();
			
			$('#checkAll').prop('checked', false);
		}
	});

	}
}

//In use
function generate(){
	$.get("Handler_GenerateNo",{functions:"SERPONO"},function(data){
		$("#reference").val(data.trim());
		if($("#reference").val()!=""){
			$('#renewBtn').removeAttr('disabled');
		}else{
			 $("#renewBtn").attr("disabled", "disabled"); 
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
	 var skip = true;
	 
	 if(selectedValue==0){
		 swal("", "Please choose an order mode", "");
	 }else{
		 var orderno = [];
		 
		 $('#orderingList').DataTable().rows().nodes().to$().find(':has(:checkbox:checked)').each(function(){
			 var ordernos = $(this).closest('tr').find('td:eq(1)').text(); 
			 if((ordernos).includes("*") && selectedValue != "44"){
					skip = false;
				}else if(!(ordernos).includes("*") && selectedValue == "44") {
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
		 var referenceNo = $("#reference").val();
		 /*if(selectedValue1=="20"){
			 letterid = "EQ03";
		 }else if(selectedValue1=="40"){
			 letterid = "EQ01";
		 }else if(selectedValue1=="44"){
			 letterid = "EQ04";
		 }else if(selectedValue1=="60"){
			 letterid = "EQ02";
		 }*/
		 
		 
		/*	var url = "GenerateOrderList?letterId=" + letterid
			+ "&orderNo=" + orderno.toString() + "&vendor=" + vendor + "&mode=" + selectedValue;
			alert(url);*/

			if(skip==false){
				alertMessage("", "info", "122", selectedValue,"@ordermode");
			}else{
		
				 var url = "GeneratePreviewDocument?orderno=" + orderno.toString()
					+ "&referenceNo=" + referenceNo + "&ordermode=" + selectedValue1
					+ "&action=orderLetter";
					
				// var url = "GeneratePreviewDocument?letterId = EQ40&orderNo=" + orderno.toString();
				window.open(url);
			}
	
	 }
	
}

//Retrieve list of renewal
function retrieveRecord(){
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
	
		 if(checked =="all"){
			 criteria = "all";
		 }else if(checked=="expiredate"){
			 criteria = "expiredate";
			 value = $("#ctrlno").val();
		 }else{
			 criteria = "expiredate";
			 value = $("#orderDate1").val();
			 value1 = $("#orderDate2").val();
		 }
		 
		 $("#orderingList").DataTable().destroy();
		$.get("Table_Renewal",{action:action, criteria:checked, vendor:vendor, value:value, value1:value1, ordermode:ordermode},function(data){
			 $("#OrderList").html(data);
			 order =  $('#orderingList').DataTable({
				    responsive: true,
				    order: [],
				    columnDefs: [ { orderable: false, targets: [0] ,'searchable': false} ]
				});
		});
}

//show form bar
function viewDisplay(){
	$('#StatusTab, #FeedbackTab, #ErrorTab').show();
	$("#formdataOrderMain input").attr('disabled','disabled');
	$("#formdataOrderMain input[type=radio]").attr('disabled', true);
	$('#ordermodes, #source, #currency').prop('disabled', 'disabled');
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
}

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
		$.get("SearchVendor",{action:"keyupVendor", criteria:$(this).val(), type:"vendor"},function(data){
			$(".div-vendorName").text(data.trim());
		});
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
	
	//In use
	$("#reference").focusout(function(){
		if($(this).val()!=""){
			$('#renewBtn').removeAttr('disabled');
		}else{
			 $("#renewBtn").attr("disabled", "disabled"); 
		}
	})
	
	
		//In use
	$("#cancelRenew").focusout(function(){
		var getOrder = localStorage.getItem("newOrder");
		$.get("Handler_DeleteRenewal",{orderno:getOrder},function(data){
			
			$("#modalProcess").modal("hide");
			localstorage.clear();
		});
			
	})

	
/*	function getFund(){
		var e = document.getElementById("ddlViewBy");
		var strUser = e.options[e.selectedIndex].value;
		alert(strUser);
		 $("#orderlist tbody").find("td:nth-child(2)").text(strUser);
		}*/
	//17062020
	
	
	 
	 
	/* $("#startDate").focusout(function (e) {
		 setTimeout(function(){
				var startDate = $("#startDate").val();
				var qty = parseInt($("#qty").val());
				var issue = parseInt($("#issue").val());
				var freqType = $("#freqType").val();
				var freqTime = parseInt($("#freqTime").val());
				
				endDate(startDate, qty, issue, freqType, freqTime);
				generateTableCardex();

			}, 1000);
	 });
	//16062020
	////onchange select frequency
	$('#frequency1').on('change', function(event, obj) {
		 var freq = $("#frequency1").val();
		 alert(freq + "dfgd");
		  //get frequency detail
		  $.get('GetFreqType', {
			  freq : freq
			 	}, function(responseJson) {
				if(responseJson != null){
					$.each(responseJson, function(key,value) {
						$("#freqTime").val(value['freqTime']);
						$("#freqType").val(value['freqType']);
					});
				}
			});
	});
	
	//issue
	$(".numberOfIssues").blur(function(){
		var startDate = $("#startDate").val();
		var qty = parseInt($("#copies").val());
		var issue = parseInt($(".numberOfIssues").val());
		var freqType = $("#freqType").val();
		var freqTime = parseInt($("#freqTime").val());
			
		generateTableCardex();
		endDate(startDate, qty, issue, freqType, freqTime);

	})
	
	function generateTableCardex(){
		//alert("here");
		var firstVolume = $("#firstVolume").val();
		var quantity = $("#copies").val();
		var noOfIssues = $("#numberOfIssues").val().trim();
		var startDate = $("#startDate").val();
		var pattern = $("#hidden-patternID").val();
		var firstPattern = $("#hidden-firstPattern").val();
		var freq = $('#frequency1').val();
		var freqType = $('#freqType').val();
		var freqTime = $('#freqTime').val();

		//var freqTime = parseInt($("#freqTime").val());
		
		//aleret(freqTime);
		
		var url = "Table_GenerateIssuesList?patternID=" +pattern+"&firstPattern=" +firstPattern+"&startDate="+startDate;
		url += "&quantity="+quantity+"&noOfIssues=" + noOfIssues+"&firstVolume="+firstVolume+"&freq="+freq+"&freqType="+freqType+"&freqTime="+freqTime;
		
		////alert("url" +url);
		
		var result = "NewFolder333".match(/[^\d]+|\d+/g);
		alert(result);
		
		////05082019
		//if(firstVolume == "" || quantity == "" || noOfIssues == "" || startDate== ""|| pattern=="" || firstPattern == "")
		if($("#volisfrom").val()==""){
			//alert($("#volisfrom").val() +"kl");
			alert("Please fill in necessary details.");
		}else
		{
			////alert("111");
			var url = "Table_GenerateIssuesList?patternID=" +pattern+"&firstPattern=" +firstPattern+"&startDate="+startDate;
			url += "&quantity="+quantity+"&noOfIssues=" + noOfIssues+"&firstVolume="+firstVolume+"&freq="+freq+"&freqType="+freqType+"&freqTime="+freqTime;
			//alert("222");
	   		$.ajax({
	   			// Title
	   			url: url,
	   			success: function(result) {
	   			
	   				$("#patternList").html(result);
	   				var quantity = $("#qty").val();
	   				$.get('GetDisplayLooseSheets', {
						  matno : $("#input-contorlNo").val()
						 	}, function(responseJson) {
							if(responseJson != null){
								$.each(responseJson, function(key,value) {
									for(var i=1;i<=quantity; i++){
										if(value['CUMIDX'] == "Y"){
											$('#table-cardex').append('<tr><td>'+i+'</td><td>Cum. Index</td><td>Cum. Index</td><td>'+$("#startDate").val()+'</td></tr>');
										}
										
										if(value['TITLPG'] == "Y"){
											$('#table-cardex').append('<tr><td>'+i+'</td><td>Title Page</td><td>Title Page</td><td>'+$("#startDate").val()+'</td></tr>');
										}
										
										if(value['CONTPG'] == "Y"){
											$('#table-cardex').append('<tr><td>'+i+'</td><td>Cont. Page</td><td>Cont. Page</td><td>'+$("#startDate").val()+'</td></tr>');
										}
									}
									
								});
							}
					});
	   				
	   			}
	   		});
	   		///alert("333");
		}
	}*/


});
