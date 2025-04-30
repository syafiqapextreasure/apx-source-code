/**
 * 
 */

$(document).ready(function() {
	
	//Call error message page
	function messageBox(code, criteria, label){
	      var url = "Error_Page?GL79ERRCODE="+code+"" +
	      "&criteria=" + criteria + "&label="+label+"";
	      //alert(url);
	      $.ajax({
	            url: url,
	            success: function(result) {
	                  swal(result);
	            }
	      });
	}
	//////////////////////hide field////////////////////////////////////////////////////////////////////
	$("#set").hide();
	$(".vendCode").hide();
	
	///////////////////// GET TODAY DATE /////////////////////////////////////////////////////////
	var today = new Date();           
    var formattedtoday = moment(today).format("DD/MM/YYYY");
    //alert(formattedtoday);
	
	//////////////////////////////////////////TABEL table-receivingDetail //////////////

	var table = $('#table-receivingDetail').DataTable( {
        scrollX:        true,
        scrollCollapse: true,
        paging:         false,
        info: false,
        autoWidth:false,
        fixedColumns: true,
        searching: false,
        ordering: false,
        responsive: true,
        /*columnDefs: [
        	{ width: 200, targets: 0 },
        	{ width: 200, targets: 1 }
        ],*/
        //fixedColumns: true
    });
	
	///////////////////////////////focus////////////////////////////////////////////////
	$("#OrderNO").focus();
	
	///////////////////////////////readonly/////////////////////////////////////////////
	$("#CopyReceived").prop("readonly", true);
	$("#DateRec").attr("disabled", true);
	
	///////////////////////////////hide TBODY///////////////////////////////////////////
	$('#table-receivingDetail').find('tbody').hide();
	
	///////////////////////////////hide BUTTON /////////////////////////////////////////////
	$('#generate, #save, #discard, #note').hide();

	//////////////////////////////////////////OrderNO TAB OR ENTER ////////////////////
	$("#OrderNO").keydown(function(e){ 
		var code = e.keyCode || e.which;
		if(code == '9' || code == '13'){
			alert("sss");
			 $.get('checkOrderNo', {
				 OrderNO : $("#OrderNO").val()
			 	}, function(responseJson) {
				if(responseJson != null){
					$.each(responseJson, function(key,value) {
						alert(value['Count']);
						if(value['Count']=='0'){
							//alert("This Order does not exist");
							clear();
							messageBox("105","","");
						}else{
							$.get('displayReceivingDetails', {
								OrderNO : $("#OrderNO").val()
							 	}, function(responseJson) {
							 		alert(responseJson);
								if(responseJson==''){
									messageBox("106","","");
									//alert("Receiving process cannot be performed for this order");
								}else{
									//alert(value['contEnd']);
									$.each(responseJson, function(key,value) {
									var set, TotalFPrice, TotalLocalPrice;
								/*	
									var contcDate = value['contEnd'];
									alert(value['contEnd']);*/
									//if( (contcDate == " ") || (contcDate == "") ){
						
										/// when COPIES ////
										if(value['Set'] == '0' || value['Set'] == undefined){
											$("#set").hide();
											$("#copy").show();
											//alert("copies");
											
											set = "";
											TotalFPrice = value['Copies'] * value['FPrice'];
											////TotalLocalPrice = value['Copies'] * value['LocalPrice'];
											
											$(".Received").text(value['Received']);
											
											$("#CopyReceived").prop("readonly", false);
											$("#CopyReceived").focus();
											$("#Foreign").val($(".FPrice").text());
											$("#Local").val($(".LocalPrice").text());
											$('#DateReceived').attr('readonly',false).datepicker({
												//startDate : today, 
												format: "dd/mm/yyyy",
												todayBtn: true,
												autoclose: true,
												todayHighlight: true,
											});
										}
										/// WHEN SET
										else{
											//alert("set");
											$("#set").show();
											$("#copy").hide();
											set = value['Set'];
											TotalFPrice = value['Set'] * value['FPrice']; //value['Set'] * value['Copies'] * value['FPrice'];
											//////TotalLocalPrice = value['Set'] * value['LocalPrice']; //value['Set'] * value['Copies'] * value['LocalPrice'];
											$(".Received").text(value['SetsReceived']);
											////$(".Received").text(value['Received']);
											
											$("#SetReceived").focus();
											$("#CopyReceivedPerSet").prop("readonly", true);
											$("#DateRecSet").prop("readonly", true);
											
											
										}
										
										//// invoice date
										var outputInvoiceDate;
										if(moment(value.InvoiceDate).isValid() == false){
											outputInvoiceDate = value.InvoiceDate; 
										}else{
											outputInvoiceDate = moment(value.InvoiceDate).format("DD/MM/YYYY");
										}
										
										///// change to 4 decimal place ///
										var FPrice; 
										FPrice = parseFloat(value['FPrice']); 
										
										/*var LocalPrice;
										LocalPrice = parseFloat(value['LocalPrice']); */
										
										//01122020
										var LocalPrice;
										LocalPrice = value['PubRate'] * value['FPrice'];
										LocalPrice = parseFloat(LocalPrice); 
										
										TotalLocalPrice = TotalFPrice* value['PubRate'];
										
										
										
										
										///dislpay value
										$(".ISBN").text(value['ISBN']);
										$(".ControlNo").text(value['ControlNo']);
										$(".CallNo").text(value['CallNo']);
										$(".Title").text(value['Title']);
										$(".ReferenceNo").text(value['ReferenceNo']);
										$(".VendorR").text(value['Vendor']);
										//alert("lll"+$(".VendorR").text());
										$(".Mode").text(value['Mode']);
										$(".Status").text(value['Status']);
										$(".OrderDate").text(moment(value.OrderDate).format("DD/MM/YYYY"));
										$(".ExpectedDate").text(moment(value.ExpectedDate).format("DD/MM/YYYY"));
										$(".Status").text(value['Status']);
										$(".Set").text(set);
										$(".Copies").text(value['Copies']);
										
										$(".RecordedBy").text(value['RecordedBy']);
										$(".Currency").text(value['Currency']);
										$(".PubRate").text(value['PubRate']);
										$(".FPrice").text(FPrice.toFixed(4));
										$(".LocalPrice").text(LocalPrice.toFixed(4));
										$(".TotalFPrice").text(TotalFPrice.toFixed(4));
										$(".TotalLocalPrice").text(TotalLocalPrice.toFixed(4));
										$(".InvoiceNo").text(value['InvoiceNo']);
										$(".InvoiceDate").text(outputInvoiceDate);
										$("#note").show();
										
										$(".vendCode").text(value["VendorCode"]);
									/*}else{
										
										var contEnd = moment(value['contEnd']).format("YYYY-MM-DD");
										var currentdate = moment(today).format("YYYY-MM-DD");
										
										var checkEnd = moment(contEnd).isAfter(currentdate);
										
										if(checkEnd == true){
												/// when COPIES ////
											if(value['Set'] == '0' || value['Set'] == undefined){
												$("#set").hide();
												$("#copy").show();
												//alert("copies");
												
												set = "";
												TotalFPrice = value['Copies'] * value['FPrice'];
												////TotalLocalPrice = value['Copies'] * value['LocalPrice'];
												
												$(".Received").text(value['Received']);
												
												$("#CopyReceived").prop("readonly", false);
												$("#CopyReceived").focus();
												$("#Foreign").val($(".FPrice").text());
												$("#Local").val($(".LocalPrice").text());
												$('#DateReceived').attr('readonly',false).datepicker({
													//startDate : today, 
													format: "dd/mm/yyyy",
													todayBtn: true,
													autoclose: true,
													todayHighlight: true,
												});
											}
											/// WHEN SET
											else{
												//alert("set");
												$("#set").show();
												$("#copy").hide();
												set = value['Set'];
												TotalFPrice = value['Set'] * value['FPrice']; //value['Set'] * value['Copies'] * value['FPrice'];
												//////TotalLocalPrice = value['Set'] * value['LocalPrice']; //value['Set'] * value['Copies'] * value['LocalPrice'];
												$(".Received").text(value['SetsReceived']);
												////$(".Received").text(value['Received']);
												
												$("#SetReceived").focus();
												$("#CopyReceivedPerSet").prop("readonly", true);
												$("#DateRecSet").prop("readonly", true);
												
												
											}
											
											//// invoice date
											var outputInvoiceDate;
											if(moment(value.InvoiceDate).isValid() == false){
												outputInvoiceDate = value.InvoiceDate; 
											}else{
												outputInvoiceDate = moment(value.InvoiceDate).format("DD/MM/YYYY");
											}
											
											///// change to 4 decimal place ///
											var FPrice; 
											FPrice = parseFloat(value['FPrice']); 
											
											var LocalPrice;
											LocalPrice = parseFloat(value['LocalPrice']); 
											
											//01122020
											var LocalPrice;
											LocalPrice = value['PubRate'] * value['FPrice'];
											LocalPrice = parseFloat(LocalPrice); 
											
											TotalLocalPrice = TotalFPrice* value['PubRate'];
											
											
											
											
											///dislpay value
											$(".ISBN").text(value['ISBN']);
											$(".ControlNo").text(value['ControlNo']);
											$(".CallNo").text(value['CallNo']);
											$(".Title").text(value['Title']);
											$(".ReferenceNo").text(value['ReferenceNo']);
											$(".VendorR").text(value['Vendor']);
											//alert("lll"+$(".VendorR").text());
											$(".Mode").text(value['Mode']);
											$(".Status").text(value['Status']);
											$(".OrderDate").text(moment(value.OrderDate).format("DD/MM/YYYY"));
											$(".ExpectedDate").text(moment(value.ExpectedDate).format("DD/MM/YYYY"));
											$(".Status").text(value['Status']);
											$(".Set").text(set);
											$(".Copies").text(value['Copies']);
											
											$(".RecordedBy").text(value['RecordedBy']);
											$(".Currency").text(value['Currency']);
											$(".PubRate").text(value['PubRate']);
											$(".FPrice").text(FPrice.toFixed(4));
											$(".LocalPrice").text(LocalPrice.toFixed(4));
											$(".TotalFPrice").text(TotalFPrice.toFixed(4));
											$(".TotalLocalPrice").text(TotalLocalPrice.toFixed(4));
											$(".InvoiceNo").text(value['InvoiceNo']);
											$(".InvoiceDate").text(outputInvoiceDate);
											$("#note").show();
											
											$(".vendCode").text(value["VendorCode"]);
										}else{
											messageBox("169",moment(value['contEnd']).format("DD/MM/YYYY"),"@date");
										}
									}*/
										
									});
								}
							});
						}
					});
				}
			});
			 event.preventDefault();
		}else if(code == '8'){
			clear();
		}
	});
	
	////////////////////////////////////FOR DATEPICKER ///////////////////////////////////////
	/*$('#DateReceived').datepicker({
		//startDate : today, 
		format: "dd/mm/yyyy",
		todayBtn: true,
		autoclose: true,
		todayHighlight: true,
		//enableOnReadonly: false
	});
	
	$('#DateReceived').attr('readonly',true).datepicker("destroy");*/
		
	///////////////////////////////////////Copy Received input NUMBER ONLY ////////////////////
	$("#CopyReceived, #SetReceived, #CopyReceivedPerSet").keypress(function (e) {
	     //if the letter is not digit then display error and don't type anything
	     if (e.which != 8 && e.which != 0 && (e.which < 48 || e.which > 57)) {
	     	return false;
	     }
	});
	
	///////////////////////////// function CLEAR ////////////////////////////////////////////
	function clear(){
		$(".ISBN, .ControlNo, .CallNo, .Title, .ReferenceNo, .VendorR, .Mode").empty();
		$(".Status, .OrderDate, .ExpectedDate, .Set, .Copies, .Received").empty();
		$(".RecordedBy, .Currency, .PubRate, .FPrice,  .LocalPrice, .TotalFPrice").empty();
		$(".TotalLocalPrice, .InvoiceNo, .InvoiceDate").empty();
		$("#CopyReceived").val('');
		$("#CopyReceived").prop("readonly", true);
		$("#DateRec").attr("disabled", true);
		
		$("#set").hide();
		$("#copy").show();
		////for b tabble
		var firstElement = $('tbody > tr').first();
	    table.rows(firstElement.nextAll('tr')).remove().draw();
	    $("#Accession, #Volume, #Copy").val('');
	    $('#table-receivingDetail').find('tbody').hide();
	}
	
	///////////////////////////////////////CREATE TABLE ROW FOR COPY RECEIVED/////////////////////////////////
	$("#CopyReceived").keydown(function(e){ 
		
		var code = e.keyCode || e.which;
		if(code == '9' || code == '13'){

			//$('input[name="Retain"]:checkbox').prop('checked', this.checked);
			//$("input[name='Retain']").attr('checked', true);
			var qtyRec = $("#CopyReceived").val();
			var qty = $(".Copies").text();
			var rec = $(".Received").text();
			var expected = qty - rec;
			
			if(qtyRec == '0'){
				$("#generate").prop('disabled', true);
			}else{
				$("#generate").prop('disabled', false);
			}
			
			if(qtyRec > expected){
				swal({
					  text: "Order Quantity : "+qty+"<br> Received Quantity: "+rec+
					  "<br><br>Expected Quantity To Be Received: <b>"+expected+
					  "</b><br> Receiving Quantity: "+qtyRec
					  +"<br><br>The Receiving Quantity is more than Expected Quantity. DO you wish to continue?",
					  //type: "warning",
					  showCancelButton: true,
					  confirmButtonClass: "btn-danger",
					  confirmButtonText: "Yes",
					  cancelButtonText: "No",
					  closeOnConfirm: false,
					  closeOnCancel: false
					}).then(function(isConfirm) {
						$("#CopyReceived").prop("readonly", true);
						///////// for date //////
						$("#DateReceived").datepicker("setDate", formattedtoday);
						$("#DateRec").attr("disabled", false);
						generateTableCOPY();
					});
			}else{
				
				generateTableCOPY();
				$(".foreign").val($(".FPrice").text()).prop("readonly", false);
	       		$(".local").val($(".LocalPrice").text()).prop("readonly", false);
				
				$("#CopyReceived").prop("readonly", true);
				///////// for date //////
				$("#DateReceived").datepicker("setDate", formattedtoday);
				$("#DateRec").attr("disabled", false);
			}
		}
	});
	
	///////////////////////////// function generate table for COPY ////////////////////////////////////////////
	function generateTableCOPY(){
		$('#save, #discard, #generate').show();
		
		var numberROW = $("#CopyReceived").val();
		//alert(numberROW + " numberROW")
		$('#table-receivingDetail').find('tbody').show();  // --> SHOW TBODY
		//
		$(".foreign").val($(".FPrice").text()).prop("readonly", false);
   		$(".local").val($(".LocalPrice").text()).prop("readonly", false);
		
		var totalTr=($("#table-receivingDetail tr").length)-1;

	       if(numberROW > totalTr){
	           for(var i=0;i<numberROW-totalTr;i++){	        	   
	               var lastTR=$("#table-receivingDetail tr:last").html();
	               
	               var prevID = $("#table-receivingDetail input.accno").attr('id');
	               var acc = prevID.split("_");
	               $("#table-receivingDetail").append("<tr>");
	               $("#table-receivingDetail tr:last").html(lastTR);
	             
	               	///display row number
	               	$('#table-receivingDetail tbody tr').each(function(idx){
	    	       		$(".foreign").val($(".FPrice").text()).prop("readonly", false);
	    	       		$(".local").val($(".LocalPrice").text()).prop("readonly", false);
	    	       		
	    	       	//EDITED 11062019
	    	       		$(".accno").val('');
	    	       		/////for SMD
	    	       		if($.trim($('#retainSMD').val()) != ''){
	    	       			$("#SMD").val($('#retainSMD').val());
	    	       		}else{
	    	       			$(".SMD").prop("selectedIndex",-1);
	    	       		}
	    	       		
	    	       		/////for ItemCatgory
	    	       		if($.trim($('#retainitemcategory').val()) != ''){
	    	       			$("#ItemCatgory").val($('#retainitemcategory').val());
	    	       		}else{
	    	       			$(".ItemCatgory").prop("selectedIndex",-1);
	    	       		}
	    	       		
	    	       		/////for Loca
	    	       		if($.trim($('#retainlocation').val()) != ''){
	    	       			$("#Loca").val($('#retainlocation').val());
	    	       		}else{
	    	       			$(".Loca").prop("selectedIndex",-1);
	    	       		}
	    	       		
	    	       		/////for Con
	    	       		if($.trim($('#retaincondition').val()) != ''){
	    	       			$("#Con").val($('#retaincondition').val());
	    	       		}else{
	    	       			$(".Con").prop("selectedIndex",-1);
	    	       		}
	    	       		
	    	       		//$("#SMD, #ItemCatgory, #Loca, #Con").prop("selectedIndex",-1);
	    	       		
	   			   		$(this).children().first().html(idx + 1 + "<input type='hidden' class='count' value='"+idx+"'>");
	   				});
	           }
	           
	       }else{
	            for(var i=0;i<totalTr-numberROW;i++){
	                $("#table-receivingDetail tr:last").remove();
	            }
	       }
	       
	       /// for check user input ACCESSION value
	       $('.accno').blur(function () {
	    	      	   
	    	   var id = $(this).closest("#table-receivingDetail tbody tr").find('.accno').val();
	    	   var getNOCOPY  =$(this).closest("#table-receivingDetail tbody tr").find('#NOCOPY').text();
	    	   var NOCOPYindex = parseInt(getNOCOPY)-1;
	    	   
	    	   $.get('checkAccNo',{
				   id : id
					}, function(responseJson) {
				 		
				 		if(responseJson != null){
				 			$.each(responseJson, function(key,value) {
								if(value['Count'] >= '1'){
									$('#table-receivingDetail tbody tr:eq('+NOCOPYindex+') input.accno').val('');//.css("background-color", 'red');
									//alert("Accession already exits");
									//messageBox("124","",""); 
									swal("Accession already exits")
								}else if(value['Count'] == '0'){
									//alert("Accession can use");
								}
				 			});
				 		}
			   });
	       });
	}
	
	///////////////////////////////////////CREATE TABLE ROW FOR SET RECEIVED/////////////////////////////////
	$("#SetReceived").keydown(function(e){ 
		
		var code = e.keyCode || e.which;
		if(code == '9' || code == '13'){
			
			$("input[name='Retain']").attr('checked', true);
			
			setTimeout(function(){
				$("#CopyReceivedPerSet").focus();
			}, 1000);
			//
			/*$("#CopyReceivedPerSet").val($(".Copies").text());
			$("#CopyReceivedPerSet").prop("readonly", false);*/
			
			var qtySetRec = $("#SetReceived").val();
			var setOrder = $(".Set").text();
			var rec = $(".Received").text();
			var expected = setOrder - rec;
			
			if(qtySetRec == '0'){
				$("#generate").prop('disabled', true);
			}else{
				$("#generate").prop('disabled', false);
				//$("#CopyReceivedPerSet").focus();
			}
			
			if(qtySetRec > expected){
				swal({
					  text: "Order Quantity : "+setOrder+"<br> Received Quantity: "+rec+
					  "<br><br>Expected Quantity To Be Received: <b>"+expected+
					  "</b><br> Receiving Quantity: "+qtySetRec
					  +"<br><br>The Receiving Quantity is more than Expected Quantity. DO you wish to continue?",
					  //type: "warning",
					  showCancelButton: true,
					  confirmButtonClass: "btn-danger",
					  confirmButtonText: "Yes",
					  cancelButtonText: "No",
					  closeOnConfirm: false,
					  closeOnCancel: false
					}).then(function(isConfirm) {
						isComfirmSet();
					});
			}else{
				isComfirmSet();
			}
		}
	});
	
	/////////////////////////FUNCTION IS COMFIRM FOR SET //////////////////////////////////////
	function isComfirmSet(){
		$("#CopyReceivedPerSet").val($(".Copies").text());
		$("#CopyReceivedPerSet").prop("readonly", false);
		$("#CopyReceivedPerSet").focus();
	}
	
	///////////////////////////// function generate table for COPY ////////////////////////////////////////////
	$("#CopyReceivedPerSet").keydown(function(e){ 
		
		var code = e.keyCode || e.which;
		if(code == '9'){
			///////// for date //////
			$("#SetReceived").prop("readonly", true);
			$("#CopyReceivedPerSet").prop("readonly", true);
			///////// for date //////
			$('#DateReceivedSet').attr('readonly',false).datepicker({
				//startDate : today, 
				format: "dd/mm/yyyy",
				todayBtn: true,
				autoclose: true,
				todayHighlight: true,
			});
			$("#DateReceivedSet").datepicker("setDate", formattedtoday);
			$("#DateRecSet").prop("readonly", false);
			
			/////calculate foreign n local price /////
			var copy = $(".Copies").text();
			var FPrice = $(".FPrice").text();
			var LocalPrice = $(".LocalPrice").text();
			
			var pricePerCopyForFPrice = (FPrice/copy).toFixed(4);
			var pricePerCopyForLocalPrice = (LocalPrice/copy).toFixed(4);
			
			//generate table//
			$('#save, #discard, #generate').show();
			var setReceived = $("#SetReceived").val();
			var copyReceived = 	$("#CopyReceivedPerSet").val();
			var totalNumberOfRow = setReceived * copyReceived;
			
			$('#table-receivingDetail').find('tbody').show();  // --> SHOW TBODY
			//
			$(".foreign").val(pricePerCopyForFPrice).prop("readonly", false);
       		$(".local").val(pricePerCopyForLocalPrice).prop("readonly", false);
			var totalTr=($("#table-receivingDetail tr").length)-1;
			
			

		       if(totalNumberOfRow > totalTr){
		           for(var i=0;i<totalNumberOfRow-totalTr;i++){	        	   
		               var lastTR=$("#table-receivingDetail tr:last").html();
		               
		               
		               $("#table-receivingDetail").append("<tr>");
		               $("#table-receivingDetail tr:last").html(lastTR);
		               
		               	///display row number
		               	$('#table-receivingDetail tbody tr').each(function(idx){
		    	       		/*$(".foreign").val(pricePerCopyForFPrice);
		    	       		$(".local").val(pricePerCopyForLocalPrice);*/
		    	       		$(".foreign").val(pricePerCopyForFPrice).prop("readonly", false);
		    	       		$(".local").val(pricePerCopyForLocalPrice).prop("readonly", false);
		    	       		/////////////////////////////
		    	       		//EDITED 11062019
		    	       		$(".accno").val('');
		    	       		/////for SMD
		    	       		if($.trim($('#retainSMD').val()) != ''){
		    	       			$("#SMD").val($('#retainSMD').val());
		    	       		}else{
		    	       			$(".SMD").prop("selectedIndex",-1);
		    	       		}
		    	       		
		    	       		/////for ItemCatgory
		    	       		if($.trim($('#retainitemcategory').val()) != ''){
		    	       			$("#ItemCatgory").val($('#retainitemcategory').val());
		    	       		}else{
		    	       			$(".ItemCatgory").prop("selectedIndex",-1);
		    	       		}
		    	       		
		    	       		/////for Loca
		    	       		if($.trim($('#retainlocation').val()) != ''){
		    	       			$("#Loca").val($('#retainlocation').val());
		    	       		}else{
		    	       			$(".Loca").prop("selectedIndex",-1);
		    	       		}
		    	       		
		    	       		/////for Con
		    	       		if($.trim($('#retaincondition').val()) != ''){
		    	       			$("#Con").val($('#retaincondition').val());
		    	       		}else{
		    	       			$(".Con").prop("selectedIndex",-1);
		    	       		}
		    	       		
		    	       		//$("#SMD, #ItemCatgory, #Loca, #Con").prop("selectedIndex",-1);
		    	       		////////////////////////////
		   			   		$(this).children().first().html(idx + 1 + "<input type='hidden' class='count' value='"+idx+"'>");
		   				});
		               	
		               	
		           }
		           
		       }else{
		            for(var i=0;i<totalTr-totalNumberOfRow;i++){
		                $("#table-receivingDetail tr:last").remove();
		            }
		       }
		       
		       /// for check user input ACCESSION value
		       $('.accno').blur(function () {
    	      	   
		    	   var id = $(this).closest("#table-receivingDetail tbody tr").find('.accno').val();
		    	   var getNOCOPY  =$(this).closest("#table-receivingDetail tbody tr").find('#NOCOPY').text();
		    	   var NOCOPYindex = parseInt(getNOCOPY)-1;
		    	   
		    	   $.get('checkAccnoAcq',{
					   id : id
						}, function(responseJson) {
					 		
					 		if(responseJson != null){
					 			$.each(responseJson, function(key,value) {
									if(value['Count'] >= '1'){
										$('#table-receivingDetail tbody tr:eq('+NOCOPYindex+') input.accno').val('');//.css("background-color", 'red');
										//alert("Accession already exits");
										//messageBox("124","",""); 
										swal("Accession already exits")
									}else if(value['Count'] == '0'){
										//alert("Accession can use");
									}
					 			});
					 		}
				   });
		       });
		      
		}
	});
	
	
	
	////////////////////////FUNCTION Retain VALUE/////////////////////////////////////////
	
	function Retain(){
		
		var selectedRetain = new Array();
		
		$('input[name="Retain"]:checkbox:not(:checked)').each(function() {
			selectedRetain.push(this.value);
			
			if(this.value == 'smdcheck'){
				//$("#SMD").val('');
				$("#SMD").find('option').removeAttr("selected");
			}else if(this.value == 'itemcategorycheck'){
				$("#ItemCatgory").find('option').removeAttr("selected");
			}else if(this.value == 'locationcheck'){
				$("#Loca").find('option').removeAttr("selected");
			}else if(this.value == 'conditioncheck'){
				$("#Con").find('option').removeAttr("selected");
			}
		});
	}
	
	////////////////////////WHEN DISCARD CLICK /////////////////////////////////////////////
	$("#discard").click(function(){
		var getSetVal = $(".Set").text();
		
		if(getSetVal == ''){
			discardForCopy();
		}else if(getSetVal >= '1'){
			discardForSet ()
		}
		///Retain();
	});
	
	/////////////function set is empty value for copy clear when discard ///////////
	function discardForCopy (){
		var firstElement = $('tbody > tr').first();
	    table.rows(firstElement.nextAll('tr')).remove().draw();
	    $("#Accession, #Volume, #Copy").val('');
	    $('#table-receivingDetail').find('tbody').hide();
		$("#CopyReceived").val('');
		$("#save").hide();
		$("#CopyReceived").focus();
		$("#CopyReceived").prop("readonly",  false);
		///////// for date copies //////
		$('#DateReceived').attr('readonly',true).datepicker("destroy");
		$("#DateRec").val('');
		$("#DateRec").attr("disabled", true);
	}
	
	/////////////function set is empty value for set clear when discard ///////////
	function discardForSet (){
		var firstElement = $('tbody > tr').first();
	    table.rows(firstElement.nextAll('tr')).remove().draw();
	    $("#Accession, #Volume, #Copy").val('');
	    $('#table-receivingDetail').find('tbody').hide();
	    
	    $("#SetReceived, #CopyReceivedPerSet").val('');
	    $("#save").hide();
	    $("#SetReceived").focus();
	    $("#SetReceived").prop("readonly",  false);
	    
	    ///////// for date copies //////
		$('#DateReceivedSet').attr('readonly',true).datepicker("destroy");
		$("#DateRecSet").val('');
		$("#DateRecSet").prop("readonly", true);
		//$("#DateRecSet").attr("disabled", true);
	}
	
	////////////////////////FUNCTION Retain CHECKED true/////////////////////////////////////////
	$("#smdcheck, #itemcategorycheck, #locationcheck, #conditioncheck").prop( "checked", true );
	$(".smd").prop("selectedIndex",-1);
	$(".icat, #location, #condition").prop("selectedIndex",-1);

	
	 // code to read selected table row cell data (values).
    $("#table-receivingDetail").on('change','.smd',function(){
    	var row_index =  $(this).closest("tr").index();
    	if(row_index==0){
    		var getSMDVALUE = $(this).find(":selected").val();
    		$("#smd option[value='"+getSMDVALUE+"']").attr('selected', 'selected');
    		 $(this).find("option:selected").text(getSMDVALUE);
    	}else{
	    	var currentRow=$(this).closest("select.smd"); 
	    	var smd = currentRow.find(":selected").val();
	    	currentRow.find("option[value='"+smd+"']").attr('selected', 'selected');
	    	currentRow.find("option:selected").text(smd);
    	}
    });
    
    $("#table-receivingDetail").on('change','.icat',function(){
    	var row_index =  $(this).closest("tr").index();
    	if(row_index==0){
    		var getItemCatgoryVALUE = $(this).find(":selected").val();
    		$("#icat option[value='"+getItemCatgoryVALUE+"']").attr('selected', 'selected');
    		 $(this).find("option:selected").text(getItemCatgoryVALUE);
    	}else{
	    	var currentRow=$(this).closest("select.icat"); 
	    	var icat = currentRow.find(":selected").val();
	    	currentRow.find("option[value='"+icat+"']").attr('selected', 'selected');
	    	currentRow.find("option:selected").text(icat);
    	}
    });
    
    $("#table-receivingDetail").on('change','.location',function(){
    	var row_index =  $(this).closest("tr").index();
    	if(row_index==0){
    		var getLocaVALUE = $(this).find(":selected").val();
    		$("#location option[value='"+getLocaVALUE+"']").attr('selected', 'selected');
    		 $(this).find("option:selected").text(getLocaVALUE);
    	}else{
    	var currentRow=$(this).closest("select.location"); 
    	var loca = currentRow.find(":selected").val();
    	currentRow.find("option[value='"+loca+"']").attr('selected', 'selected');
    	currentRow.find("option:selected").text(loca);
    	}
    });
    
    $("#table-receivingDetail").on('change','.condition',function(){
    	var row_index =  $(this).closest("tr").index();
    	if(row_index==0){
    		var getConVALUE = $(this).find(":selected").val();
    		$("#condition option[value='"+getConVALUE+"']").attr('selected', 'selected');
    		 $(this).find("option:selected").text(getConVALUE);
    	}else{
    	var currentRow=$(this).closest("select.condition"); 
    	var condition = currentRow.find(":selected").val();
    	currentRow.find("option[value='"+condition+"']").attr('selected', 'selected');
    	currentRow.find("option:selected").text(condition);
    	}
    });


	
	
	
	////////////////////////WHEN GENERATE CLICK //////////////////////////////////////////
	$("#generate").click(function(){
		//alert("generate");
		var rowCount = $('#table-receivingDetail tr').length-1;
		//$.get("Global?type=Handler&name=GenerateNo",{functions:"ACCNO", action: "ReviewListNo"},function(data){
		$.get('generateAccNORec', {
			rowCount : rowCount
		 	}, function(jsonResponse) {
		 		var table = $("#table-receivingDetail");
		 		 $.each(jsonResponse, function(index, value) {
		 			/* alert("value = " + value);
		 			 alert("index = " + index);*/
		 			 
		 			 if(rowCount == 1){
		 				//alert('1');
		 				$("#Accession").val(value);
		 			 }else{
		 				//alert('2');
		 				$('#table-receivingDetail tbody tr').each(function(indexs) {
			 				if(index == $(this).find("td .count").val()){
			 					$(this).children().eq(1).html("<input type='text' maxlength='10' class='form-control accno' id='Accession' value='"+value+"'>");
			 				}
						});
		 			 }
		 		 });     
		 	});
	});
	
	////////////////////////WHEN SAVE CLICK ///////////////////////////////////////////////
	$("#save").click(function(){
		
		// check for empty value
		if(!checkEmptyValue()) {
			// Do not proceed if empty values detected
			alert('Empty values detected!');
			return false;
		}
		
		//alert("not");
		//var rowCount = $('#table-receivingDetail tr').length-1;
		var OrderNO = $("#OrderNO").val();
		var ControlNo = $(".ControlNo").text();
		var InvoiceNo = $(".InvoiceNo").text();
		var InvoiceDate = $(".InvoiceDate").text();
		var InvoiceDate2 = moment(InvoiceDate, 'DD/MM/YYYY').format("YYYYMMDD");
		var PubRate = $(".PubRate").text();
		var Currency = $(".Currency").text();
		var oriVendor = $(".VendorR").text();
		//alert("oriVendor" +oriVendor);
		var Vendor = oriVendor.replace(/[']/gi, "''");
		var Copies = $(".Copies").text();
		var Set = $(".Set").text();
		var Received = $(".Received").text();
		if(Received == ""){
			Received = "0";
		}
		var CopyReceived = $("#CopyReceived").val();
		var fullyComfirm;
		var CopyReceivedPerSet = $("#CopyReceivedPerSet").val();
		var SetReceived = $("#SetReceived").val();
		var Status = $(".Status").text();
		var officeId = $("#liferayLogin").val();
		var reservation;
		var arrival;
		var AckLetter;
		var vendorcode = $(".vendCode").text();
		
		////alert("Vendor ff" +Vendor);
		//alert("vendorcode" +vendorcode);
		
		if($("#reservation").prop('checked') == true){
			reservation = "Yes";
		}
		
		if($('#reservation').not(':checked').length){
			reservation = "Not";
		}else{
			reservation = "reservation";
		} 
		
		////////////////arrival for REQUESTOR
		if($("#requestor").prop('checked') == true){
			arrival = "print";
		}else{
			arrival = "no";
		}
		
		////////////////AckLetter for VENDOR
		if($("#vendor").prop('checked') == true){
			AckLetter = "print";
		}else{
			AckLetter = "no";
		}
		
		
		
		 var output = [];
		    $("#table-receivingDetail tbody tr").each(function() {
		        var obj = {};
		        obj.Accession = $("#Accession", this).val();
		        obj.SMD = $("#SMD", this).val();
		        obj.ItemCatgory = $("#ItemCatgory", this).val();
		        obj.Loca = $("#Loca", this).val();
		        obj.Con = $("#Con", this).val();
		        obj.foreign = $(".foreign", this).val();
		        obj.local = $(".local", this).val();
		        obj.Volume = $("#Volume", this).val();
		        obj.Copy = $("#Copy", this).val();
		        
		       
		        if(obj.Accession == ''){
					messageBox("108","Accession","@field");
					return false;
		        }else if(obj.SMD == ''){
					messageBox("108","SMD","@field");
				}else if(obj.ItemCatgory == ''){
					messageBox("108","Item Catgory","@field");
				}else if(obj.Loca == ''){
					messageBox("108","Location","@field");
				}else if(obj.Con == ''){
					messageBox("108","Condition","@field");
				}else{
					output.push(obj);
				}
		    });
		    
		    var isfullyreceived;
		    /*var iCopies = Copies;
	    	var iReceiving = CopyReceived;
	    	var iReceived = Received;
		    var iSets = Set;*/
		    var iCopies = parseInt(Copies);
	    	var iReceiving = parseInt(CopyReceived);
	    	var iReceived = parseInt(Received);
		    var iSets = parseInt(Set);
		    var iSetsReceiving  = parseInt(SetReceived);
		    ////////////////alert("reservation" +reservation);
		    

		    if(Set == ""){
		    	//alert("COPY");
		    	//alert("set is null");
		    	//alert("SET :" + (iReceiving + iReceived) >= iCopies);
		    	if( (iReceiving + iReceived) >= iCopies){
		    		//alert("SETT A");
		    		swal({
		    			text: "Click on yes button to comfirm that order has been full received",
		    	        showCancelButton: true,
		    	        confirmButtonColor: "#DD6B55",
		    	        confirmButtonText: "Yes",
		    	        cancelButtonText: "No"
		    	      }).then(
		    	        function(isConfirm) {
		    	          if (isConfirm) {
		    	        	  fullyComfirm = "Yes";
		    	        	  //swal(fullyComfirm+ ' : CONFIRMED');
		    	        	  getValueForSave(output, OrderNO, ControlNo, InvoiceNo, 
		    							InvoiceDate2, PubRate, Currency, Vendor, Copies, Set, 
		    							Received, CopyReceived, fullyComfirm, CopyReceivedPerSet, SetReceived,
		    							Status, officeId, reservation, arrival, AckLetter, vendorcode);
		    	          }
		    	        },
		    	        function() {
		    	        	fullyComfirm = "No";
		    	        	//swal(fullyComfirm +' : BACK');
		    	        	getValueForSave(output, OrderNO, ControlNo, InvoiceNo,  
	    							InvoiceDate2, PubRate, Currency, Vendor, Copies, Set, 
	    							Received, CopyReceived, fullyComfirm, CopyReceivedPerSet, SetReceived,
	    							Status, officeId, reservation, arrival, AckLetter, vendorcode);
		    	        }
		    	      );
		    	}else{
		    		//alert("SETT B");
		    		fullyComfirm = "0";
		    		getValueForSave(output, OrderNO, ControlNo, InvoiceNo, 
							InvoiceDate2, PubRate, Currency, Vendor, Copies, Set, 
							Received, CopyReceived, fullyComfirm, CopyReceivedPerSet, SetReceived,
							Status, officeId, reservation, arrival, AckLetter, vendorcode);
		    	}
		    }else{
		    	//alert("SET");
		    	//if((iReceiving + iReceived) >= iSets){
		    	alert((iSetsReceiving + iReceived) +"kln");
		    	alert("SET :" + (iSetsReceiving + iReceived) >= iSets);
		    	if((iSetsReceiving + iReceived) >= iSets){
		    		swal({
		    			text: "Click on yes button to comfirm that order has been full received",
		    	        showCancelButton: true,
		    	        confirmButtonColor: "#DD6B55",
		    	        confirmButtonText: "Yes",
		    	        cancelButtonText: "No"
		    	      }).then(
		    	        function(isConfirm) {
		    	          if (isConfirm) {
		    	        	  fullyComfirm = "Yes";
		    	        	  //swal(fullyComfirm+ ' : CONFIRMED');
		    	        	  getValueForSave(output, OrderNO, ControlNo, InvoiceNo,  
		    							InvoiceDate2, PubRate, Currency, Vendor, Copies, Set, 
		    							Received, CopyReceived, fullyComfirm, CopyReceivedPerSet, SetReceived,
		    							Status, officeId, reservation, arrival, AckLetter, vendorcode);
		    	          }
		    	        },
		    	        function() {
		    	        	fullyComfirm = "No";
		    	        	//swal(fullyComfirm +' : BACK');
		    	        	getValueForSave(output, OrderNO, ControlNo, InvoiceNo, 
	    							InvoiceDate2, PubRate, Currency, Vendor, Copies, Set, 
	    							Received, CopyReceived, fullyComfirm, CopyReceivedPerSet, SetReceived,
	    							Status, officeId, reservation, arrival, AckLetter, vendorcode);
		    	        }
		    	      );		    		
		    	}else{
		    		fullyComfirm = "0";
		    		getValueForSave(output, OrderNO, ControlNo, InvoiceNo,
							InvoiceDate2, PubRate, Currency, Vendor, Copies, Set, 
							Received, CopyReceived, fullyComfirm, CopyReceivedPerSet, SetReceived,
							Status, officeId, reservation, arrival, AckLetter, vendorcode);
		    	}
		    }

		    return false;
	});
	
	
	////////////////////////////// function getValue for save /////////////////////
	function getValueForSave(output, OrderNO, ControlNo, InvoiceNo,
			InvoiceDate2, PubRate, Currency, Vendor, Copies, Set, 
			Received, CopyReceived, fullyComfirm, CopyReceivedPerSet, SetReceived,
			Status, officeId, reservation, arrival, AckLetter, vendorcode){
		/*alert("kkk");
		alert("AckLetter" +AckLetter);
		alert("arrival" +arrival);*/
		/*if(arrival == "print"){
			$.get('GetRequestLetter', {
				orderno : OrderNO
					}, function(responseJson) {
						if(responseJson != null){

							$.each(responseJson, function(key,value) {
								var action = "arrival";

								var url = "GeneratePreviewDocumentreqauthorization?orderno=" +OrderNO   
									+ "&id=" + officeId
									+ "&requestor=" +value['Requestor']
									+ "&action=" + action;
									window.open(url); 
					});
				}
			});
		}*/
		//alert("vend" +Vendor);
		//alert("vendorcodevendorcode" +vendorcode);
		
		/*if(reservation == "reservation"){

			$.get('LoadRequestorREC', {
				orderno : OrderNO
					}, function(responseJson) {
						if(responseJson != null){

							$.each(responseJson, function(key,value) {
								var id = value['Code'];
								var name= value['Desc'];

								if(name="undefined"){
									swal("Unable to verify patron "+id+ " status.");
								}else{
									swal("Reservation created for" + id +" , "+name);
								}
								
							});
				}
			});
		}
		*/
		
		/*swal({
			text: "Click on yes button to comfirm that order has been full received",
	        showCancelButton: true,
	        confirmButtonColor: "#DD6B55",
	        confirmButtonText: "Yes",
	        cancelButtonText: "No"
	      }).then(
	        function(isConfirm) {
	          if (isConfirm) {
	        	  fullyComfirm = "Yes";
	        	  //swal(fullyComfirm+ ' : CONFIRMED');
	        	  getValueForSave(output, OrderNO, ControlNo, InvoiceNo,  
							InvoiceDate2, PubRate, Currency, Vendor, Copies, Set, 
							Received, CopyReceived, fullyComfirm, CopyReceivedPerSet, SetReceived,
							Status, officeId, reservation, arrival, AckLetter, vendorcode);
	          }
	        },
	        function() {
	        	fullyComfirm = "No";
	        	//swal(fullyComfirm +' : BACK');
	        	getValueForSave(output, OrderNO, ControlNo, InvoiceNo, 
						InvoiceDate2, PubRate, Currency, Vendor, Copies, Set, 
						Received, CopyReceived, fullyComfirm, CopyReceivedPerSet, SetReceived,
						Status, officeId, reservation, arrival, AckLetter, vendorcode);
	        }
	      );*/
		
		
		$.ajax({
			  method: "POST",
			  url: "insertReceiving",
			  data: {saveDetail: JSON.stringify(output), 
				   OrderNO: OrderNO,
				   ControlNo: ControlNo,
				   InvoiceNo:InvoiceNo,
				   InvoiceDate: InvoiceDate2,
				   PubRate: PubRate,
				   Currency: Currency,
				   Vendor: Vendor,
				   Copies: Copies,
				   Set: Set,
				   Received: Received,
				   CopyReceived: CopyReceived,
				   Statusupdate: fullyComfirm,
				   CopyReceivedPerSet : CopyReceivedPerSet,
				   SetReceived : SetReceived,
				   Status : Status,
				   officeId : officeId,
				   reservation : reservation},
			}).done(function( msg ) {
				
				if(arrival == "print"){
					$.get('GetRequestLetter', {
						orderno : OrderNO
							}, function(responseJson) {
								if(responseJson != null){

									$.each(responseJson, function(key,value) {
										var action = "arrival";
										//ADD THIS CopyReceived
										var url = "GeneratePreviewDocumentAcq1?orderno=" +OrderNO   
											+ "&id=" + officeId
											+ "&CopyReceived=" + CopyReceived
											+ "&requestor=" +value['Requestor']
											+ "&action=" + action;
											window.open(url); 
							});
						}
					});
				}
				
				if(AckLetter == "print"){
					var action = "AckLetter";

					var url = "GeneratePreviewDocumentAcq1?orderno=" +OrderNO   
						+ "&id=" + officeId
						+ "&CopyReceived=" + CopyReceived
						+ "&vendor=" + vendorcode
						+ "&action=" + action;
						window.open(url); 
				}
				
			    messageBox("005","received ","@action");
			    
			    //////CLEAR
			   $("#OrderNO").val("");
			    $(".ISBN, .ControlNo, .Title, .CallNo, .ReferenceNo, .Vendor, .Mode, .VendorR").empty();
			    $(".Status, .OrderDate, .ExpectedDate, .Copies, .Set, .Received").empty();
			    $(".RecordedBy, .Currency, .PubRate, .FPrice, .LocalPrice, .TotalFPrice").empty();
			    $(".TotalLocalPrice, .InvoiceNo, .InvoiceDate").empty();
			    
			    $("#set").hide();
			    $("#SetReceived, #CopyReceivedPerSet, #DateRecSet").val(""); //for set
			    $("#CopyReceived, #DateRec").val(""); //for copy
			    
			    $("#SetReceived").prop("readonly", false);
			    $("#CopyReceived").prop("readonly", true);
				$("#DateRec").attr("disabled", true);
				
				
				var firstElement = $('tbody > tr').first();
			    table.rows(firstElement.nextAll('tr')).remove().draw();
				///table.clear();
			    $(".accno, #Volume, #Copy, .foreign, .local").val('');
			    
			    $('#generate, #save, #discard, #note').hide();
			    $('#table-receivingDetail').find('tbody').hide(); 
			    /////$('#table-receivingDetail').find('tbody').hide(); 
			    //retain
			    
			    //Retain();
			    var element;
			    $('input:checkbox[name=Retain]').each(function() {    
       			    if($(this).is(':checked')){
       			    	var element = $(this).val();//"#" + $(this).val();
       			    	///alert(element);
       			    	
       			     var smdis, itemcategoryis, locationis, conditionis;
       			     
       			     $('#table-receivingDetail tr').each(function(row, tr){
       			    	smdis = $(tr).find('td:eq(2) #SMD').val();
       			    	itemcategoryis = $(tr).find('td:eq(3) #ItemCatgory').val();
       			    	locationis = $(tr).find('td:eq(4) #Loca').val();
       			    	conditionis = $(tr).find('td:eq(5) #Con').val();
       			     });
       			    	
       			     
       			    	switch(element) {
           			     case "smdcheck":
           			    	 $("#retainSMD").val(smdis);
           			    	//element = "#SMD";
           			         break;
           			     case "itemcategorycheck":
           			    	$("#retainitemcategory").val(itemcategoryis);
           			    	//element = "#ItemCatgory";
           			         break;
           			     case "locationcheck":
           			    	//element = "#Loca";
           			    	$("#retainlocation").val(locationis);
           			         break;
           			     case "conditioncheck":
           			    	//element = "#Con";
           			    	$("#retaincondition").val(conditionis);
           			         break;
           			     default:
      			    	}
       			    	
       			    	if($(element).is("select")) {
       			    		$(element).prop('selectedIndex',-1);
       					}
       			    }
			    });
			    
			    setTimeout(function(){
					//location.reload();
			    	$("#OrderNO").focus();
			    }, 1000);
		});
	}
	
	////////////////////////////// Check empty value///////////////////////////////
	function checkEmptyValue() {
		var result = true;

	    
	    $('#table-receivingDetail tr').each(function(row, tr){
	    	if ($(tr).find('td:eq(1) #Accession').val() === '') {
	    		$(tr).find('td:eq(1) #Accession').focus();
	    		messageBox("108","Accession","@field");
	    		result = false;
	    		return false;
	    	}else if ($(tr).find('td:eq(2) #SMD').val() === null) {
	    		$(tr).find('td:eq(2) #SMD').focus();
	    		messageBox("108","SMD","@field");
	    		result = false;
	    		return false;
	    	}else if ($(tr).find('td:eq(3) #ItemCatgory').val() === null) {
	    		$(tr).find('td:eq(3) #ItemCatgory').focus();
	    		messageBox("108","Item Catgory","@field");
	    		result = false;
	    		return false;
	    	}else if ($(tr).find('td:eq(4) #Loca').val() === null) {
	    		$(tr).find('td:eq(4) #Loca').focus();
	    		messageBox("108","Location","@field");
	    		result = false;
	    		return false;
	    	}else if ($(tr).find('td:eq(5) #Con').val() === null) {
	    		$(tr).find('td:eq(5) #Con').focus();
	    		messageBox("108","Condition","@field");
	    		result = false;
	    		return false;
	    	}		
	    });

	    return result;
	}
	
	///////////////////////////// function getListReceving //////////////////////////////////
	
	$("#getListReceving").click(function(){
		$.get("getListReceving" ,function(data){
			$("#getListRecevingContent").html(data);
		});
	});
	

	
});