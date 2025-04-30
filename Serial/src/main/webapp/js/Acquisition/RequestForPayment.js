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

function accept(){
	
	var print = [];
	var orderno = [];
	var referno = [];
	var inv = [];
	var printForeignAmt = "N";
	var vendor = "";
	var grpList = "N";
	var checked = $("input[name='optionsRadios']:checked").val();
	var textValue = "";
	var value = "";
	var value1 = "";
	var vendor = "";

	  $("tr.record").each(function () {
		    var printStat = $(this).find('.print').text();
		    print.push(printStat);
		  });
	
	  if(document.getElementById("inlineRadiocharge").checked) {
			vendor = $(".CT03VEND").val();
		} 
	
	  if(document.getElementById("groupdetails").checked){
		  $('#orderingList tbody tr.record').each(function() {
			  if( $(this).find('.print').text() =="Y"){
			    var order = $(this).find("td").eq(3).text();
			    orderno.push( "'"+order+"'");
			  }
			});
		  	grpList = "Y";
		}else{
			 $('#orderingList tbody tr.record').each(function() {
				    var order = $(this).find("td").eq(4).text();
				/*   if(order=="DIS"||order=="HC"||order=="SC"){
					   order = "'"+order+"'";
				   }*/
				    orderno.push( "'"+order+"'");
				});
			 print = [];
		}
	
	  var payment = $("#paymentreference").val();
		
	  $.get("Handler_AcceptPayment",{print:print, printForeignAmt:printForeignAmt, vendor:vendor, orderno:orderno.toString(),
		 							payment:payment,grpList:grpList},function(message){

		  if(message.trim()=='120'){
				alertMessage("", "info", "120", "", "");
			}else if(message.trim()=='success'){
				//alertMessage("", "info", "005", "@action", "");
				alertMessage("Successful", "success", "005","processed", "@action");
				retrieveRecord();
				 if (checked==("referenceno")) {
				    	textValue = $("#referenceno").val();
				    }else if (checked==("invoiceno")) {
				    	textValue = $("#invoiceno").val();
				    	alert(textValue);
				    }else if (checked==("orderno")){
				    	textValue = $("#orderno").val();
				    }else {
				    	checked="none";
				    	 value = $("#invoicedate1").val();
						 value1 = $("#invoicedate2").val();
				    }
				  
				  var url = "GeneratePreview?criteria=" + checked
					+ "&vendor=" + vendor
					+ "&textValue=" + textValue
					+ "&startDate=" +value
					+ "&stopDate=" +value1
					+ "&order=" +orderno.toString()
					+ "&ref=" + $("#paymentreference").val(); 
				// var url = "GeneratePreview?order=" + orderno.toString();
				 
				if(document.getElementById("groupdetails").checked){
					url += "&sLetterID=EQ32&groupList=Y";
				}else{
					url += "&sLetterID=EQ12&groupList=N";
				}
				
				window.open(url);
				document.getElementById('paymentreference').value = '';
				document.getElementById("current_form").reset();
				$(".div-vendorName").text("");
				 //$("#paymentreference").val("");
			}else {
				swal("", "Unable to update Transaction", "");
			}
		})
	/*var table = document.getElementById("orderingList"); 
	var totalRows = document.getElementById("orderingList").rows.length;
	var totalCol = 3; // enter the number of columns in the table minus 1 (first column is 0 not 1)
	//To display all values
	for (var x = 0; x <= totalRows; x++)
	  {

	    alert(table.rows[x].cells[1].innerHTML);

	  }*/
}


function preview(){
	

	var checked = $("input[name='optionsRadios']:checked").val();
	var textValue = "";
	var value = "";
	var value1 = "";
	var vendor = "";
	var groupList = "N";
	var orderno = [];
	
	
	if(document.getElementById("inlineRadiocharge").checked) {
		vendor = $(".CT03VEND").val();
		 value = $("#invoicedate1").val();
		 value1 = $("#invoicedate2").val();
	} 
		
	 if (checked==("referenceno")) {
	    	textValue = $("#referenceno").val();
	    }else if (checked==("invoiceno")) {
	    	textValue = $("#invoiceno").val();
	    	alert(textValue);
	    }else if (checked==("orderno")){
	    	textValue = $("#orderno").val();
	    }/*else {
	    	alert("inside");
	    	checked="none";
	    	 value = $("#invoicedate1").val();
			 value1 = $("#invoicedate2").val();
	    }*/
	 
	 if(document.getElementById("groupdetails").checked){
		  $('#orderingList tbody tr.record').each(function() {
			  if( $(this).find('.print').text() =="Y"){
			    var order = $(this).find("td").eq(3).text();
			    orderno.push( "'"+order+"'");
			  }
			});
		}else{
			 $('#orderingList tbody tr.record').each(function() {
				    var order = $(this).find("td").eq(4).text();
				    alert(order);
				    orderno.push( "'"+order+"'");
				});
		}
	
	 
	 
		var url = "GeneratePreview?criteria=" + checked
		+ "&vendor=" + vendor
		+ "&textValue=" + textValue
		+ "&startDate=" +value
		+ "&stopDate=" +value1
		+ "&order=" +orderno.toString()
		+ "&ref=" + $("#paymentreference").val(); 
		if(document.getElementById("groupdetails").checked){
			url += "&sLetterID=EQ32&groupList=Y";
		}else{
			url += "&sLetterID=EQ12&groupList=N";
		}
		
	window.open(url);
}


function groupDetails(){
	if(document.getElementById("groupdetails").checked){
		$(".groupList").show();
		$(".requestList").hide();
		//$("#orderingList").hide();
		//$("#groupDetails_wrapper").show();
		// $('#orderingList_wrapper').hide();
	}else{
		$(".groupList").hide();
		$(".requestList").show();
		//$("#groupDetails").hide();
		//$("#groupDetails_wrapper").hide();
		 //$('#orderingList_wrapper').show();
		//$("#orderingList").show();
	}

	
}

//Retrieve Record
function retrieveRecord(){
	var checked = $("input[name='optionsRadios']:checked").val();
	var textValue = "";
	var value = "";
	var value1 = "";
	var vendor = "";
	var groupList = "N";
	
	if(document.getElementById("inlineRadiocharge").checked) {
		vendor = $(".CT03VEND").val();
		 value = $("#invoicedate1").val();
		 value1 = $("#invoicedate2").val();
	} 
		
	if(document.getElementById("groupdetails").checked){
		groupList = "Y";
	}
	 if (checked==("referenceno")) {
	    	textValue = $("#referenceno").val();
	    }else if (checked==("invoiceno")) {
	    	textValue = $("#invoiceno").val();
	    	alert(textValue);
	    }else if (checked==("orderno")){
	    	textValue = $("#orderno").val();
	    }/*else {
	    	alert("inside");
	    	checked="none";
	    	 value = $("#invoicedate1").val();
			 value1 = $("#invoicedate2").val();
	    }*/
	 loader("#requestpaymentList");
	$.get("Table_RequestForPayment",{criteria:checked, vendor:vendor, textValue:textValue, startDate:value, 
		stopDate:value1, groupList:groupList},function(data){
			
			$("#requestpaymentList").html(data.trim());
			document.getElementById("groupdetails").disabled = true;
			
			$("#printForeignAmt:not(:checked)").each(function() {
				var foreignPrice = "table .foreignPrice";
				var currency = ".currency";
				  $(currency).hide();
				 $(foreignPrice).hide();
			});
			
	});
}


$( document ).ready(function() {
	$(".groupList").hide();
	
	$('#input-vendorCode').focusout(function(){
		$.get("SearchVendor",{action:"keyupVendor", criteria:$(this).val()},function(data){
			$(".div-vendorName").text(data.trim());
		});
	});
	
	 
	
	$("[title]").tooltip();
	$('#orderingList').DataTable({
	    responsive: true,
	    "ordering": false
	});
	

	$("#btn_addrow").click(function(){
	   var html = $(".row_1").html();
	   $("tbody").append(html);
	});
	
	$("#printForeignAmt").click(function(){
	    var foreignPrice = "table .foreignPrice";
	    var currency = ".currency";
	    $(foreignPrice).toggle();
	    $(currency).toggle();
	});
	
	$("#printForeignAmt:not(:checked)").each(function() {
		var foreignPrice = "table .foreignPrice";
		var currency = ".currency";
		  $(currency).hide();
		 $(foreignPrice).hide();
	});
	
	  $('#orderingList tbody').on( 'click', 'td.print', function () {
		  if($(this).text()=="N"){
			  $(this).text("Y");
		  }else if($(this).text()=="Y"){
			  $(this).text("N");
		  }
		} )
		
		$("#paymentreference").keyup(function(){
				  
		 if ($(this).val() != null &&$(this).val() != '') {  
			 $('#acceptbtn').removeAttr('disabled');
				$('#previewbtn').removeAttr('disabled');
		 }else{
			 $("#acceptbtn").attr("disabled", "disabled"); 
			 $("#previewbtn").attr("disabled", "disabled"); 
		}	

		});
	  
	  $("#invoiceno").keyup(function(){
		  $("input[name=optionsRadios][value='invoiceno']").prop("checked",true);
		  $("#orderno").val("");
		  $("#referenceno").val("");
		/*  $("#orderno").val("");
		  $("#referenceno").val("");
		  if($(this).val().trim()==""){
			  $("#chkinvoice").attr("checked", false);
		  }else{
			  alert("sd");
			  $("#chkinvoice").attr("checked", true);
			  $("#chkorder").attr("checked", false);
			  $("#chkreference").attr("checked", false);
		  }
	*/
		});
	  
	  $("#orderno").keyup(function(){
		  $("input[name=optionsRadios][value='orderno']").prop("checked",true);
		  $("#invoiceno").val("");
		  $("#referenceno").val("");
		/*  $("#invoiceno").val("");
		  $("#referenceno").val("");
		  $("#chkinvoice").attr("checked", false);
		  $("#chkreference").attr("checked", false);
		  
		  if($(this).val().trim()==""){
			  $("#chkorder").attr("checked", false);
		  }else{
			  alert("sd1");
			 // $("#chkinvoice").attr("checked", false);
			  $("#chkorder").attr("checked", true);
			 // $("#chkreference").attr("checked", false);
		  }*/
	
		});
	  

	  $("#referenceno").keyup(function(){
		  $("input[name=optionsRadios][value='referenceno']").prop("checked",true);
		  $("#invoiceno").val("");
		  $("#orderno").val("");
		/*  $("#invoiceno").val("");
		  $("#orderno").val("");
		  if($(this).val().trim()==""){
			  $("#chkreference").attr("checked", false);
		  }else{
			  alert("sd2");
			  $("#chkinvoice").attr("checked", false);
			  $("#chkorder").attr("checked", false);
			  $("#chkreference").attr("checked", true);
		  }*/
	
		});

	  $("input[name=optionsRadios]").change(function(){
			 if($(this).val()=="referenceno"){
				 $("#invoiceno").val("");
				 $("#orderno").val("");
			 }else if($(this).val()=="orderno"){
				 $("#invoiceno").val("");
				 $("#referenceno").val("");
			 }else if($(this).val()=="invoiceno"){
				  $("#orderno").val("");
				  $("#referenceno").val("");
			 }
		
	});
	  
	  $("#refresh").click(function(){
			location.reload();
		});
	  
	  $('#vendorSearch').on('hidden.bs.modal', function(e)
		{ 
		   $(this).removeData();
		}) ;

});
