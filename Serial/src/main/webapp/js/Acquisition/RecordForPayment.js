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

	var checked = $("input[name='optionsRadios']:checked").val();
	var vendor = "";
	var criteria = "";
	var text = "";
	var chequeno = $("#chequeno").val();
	var voucherno = $("#voucherno").val();
	var chequedate = $("#chequedate").val();
	var voucherdate = $("#voucherdate").val();
	var invoice = [];
	var total = 0.00;

	if (checked==("invoiceno")) {
		text = $("#invoiceno").val();
    }else if (checked==("referenceno")) {
    	text = $("#referenceno").val();
    }
	  if(document.getElementById("inlineRadiocharge").checked) {
			vendor = $(".CT03VEND").val();
		} 
	
	  
	 /* $('#orderingList tbody tr.record').each(function() {
		    var order = $(this).find("td").eq(2).text();
		    invoice.push("'" + order + "'");
		});*/
	  
	  var rows = $("#orderingList").dataTable().fnGetNodes();
      for(var i=0;i<rows.length;i++)
      {
    	  var order = $(rows[i]).find("td").eq(2).text();
    	   invoice.push("'" + order + "'");
    	   total = parseFloat(total) + parseFloat($(rows[i]).find("td").eq(3).text());
    	   //total.push("'" + order + "'");
      }
      
      $.get("Handler_AcceptRecord",{vendor:vendor, text:text, criteria:checked,chequeno:chequeno,voucherno:voucherno,
		  chequedate:chequedate, voucherdate:voucherdate,invoice:invoice.toString()},function(message){
			  if(message.trim()=='120'){
				alertMessage("", "info", "120", "", "");
			}else if(message.trim()=='success'){
				alertMessage("", "info", "005", "recorded", "@action");
				retrieveRecord();
				if(!document.getElementById("skipPrinting").checked){
				var url = "GeneratePreview?criteria=" + checked
				+ "&vendor=" + vendor
				+ "&textValue=" + text
				+ "&chequeno=" +chequeno
				+ "&voucherno=" +voucherno
				+ "&chequedate=" +chequedate
				+ "&voucherdate=" + voucherdate
				+ "&order=" + invoice.toString() 
				+ "&sLetterID=EQ13&action=accept";
				window.open(url);
				}
				
				$("#chequeno").val('');
				$("#voucherno").val('');
				$("#chequedate").val('');
				$("#voucherdate").val('');
     
      $.get("FundDistribution",{module:"AAAM1150", vendor:vendor, ref:chequeno,totalOrder:total, desc:"Acq : Record Payment"},function(data){
    	  //alert("sss");
  		if(data.trim()!="no_funddistribution"){
  		 $("#fundDistribution").modal("show");
  		 $("#fundContent").html(data);
  		}else{
  			processOrdering();
  		}
  		
  	 });
			}else {
				swal("", "Unable to update Transaction", "");
			}
		})
}

function preview(){
	
	var checked = $("input[name='optionsRadios']:checked").val();
	var vendor = "";
	var criteria = "";
	var text = "";
	var chequeno = $("#chequeno").val();
	var voucherno = $("#voucherno").val();
	var chequedate = $("#chequedate").val();
	var voucherdate = $("#voucherdate").val();
	var invoice = [];
	  /*$('#orderingList tbody tr.record').each(function() {
		    var order = $(this).find("td").eq(2).text();
		    invoice.push("'" + order + "'");
		});*/
	  
	  var rows = $("#orderingList").dataTable().fnGetNodes();
      for(var i=0;i<rows.length;i++)
      {
    	  var order = $(rows[i]).find("td").eq(2).text();
    	   invoice.push("'" + order + "'");
      }
	  
	if(document.getElementById("inlineRadiocharge").checked) {
		vendor = $(".CT03VEND").val();
	} 
		
	if (checked==("invoiceno")) {
		text = $("#invoiceno").val();
    }else if (checked==("referenceno")) {
    	text = $("#referenceno").val();
    }
	var url = "GeneratePreview?criteria=" + checked
				+ "&vendor=" + vendor
				+ "&textValue=" + text
				+ "&chequeno=" +chequeno
				+ "&voucherno=" +voucherno
				+ "&chequedate=" +chequedate
				+ "&voucherdate=" + voucherdate
				+ "&order=" + invoice.toString() 
				+ "&sLetterID=EQ13&action=preview";

	window.open(url);
}


//Retrieve Record
function retrieveRecord(){
	
	var checked = $("input[name='optionsRadios']:checked").val();
	var textValue = "";
	var vendor = "";
	var foreignAmt = "";
	
	if(document.getElementById("inlineRadiocharge").checked) {
		vendor = $(".CT03VEND").val();
	} 
		

	 if (checked==("invoiceno")) {
	    	textValue = $("#invoiceno").val();
	    }else if (checked==("referenceno")) {
	    	textValue = $("#referenceno").val();
	    }
/*	 
	 if(document.getElementById("printForeignAmt").checked){
		 foreignAmt = "Y";
	 }else{
		 foreignAmt = "N";
	 }*/
	 
	 $("#orderingList").DataTable().destroy();
	 
		$.get("Table_RecordPayment",{vendor:vendor, textValue:textValue, criteria:checked},function(data){
			$("#voucherno, #chequeno").removeAttr("disabled"); 
		
				$("#requestpaymentList").html(data.trim());
				$("#printForeignAmt:not(:checked)").each(function() {
					var foreignPrice = "table .foreignPrice";
					var currency = ".currency";
					  $(currency).hide();
					 $(foreignPrice).hide();
				});
				$("#chequeno").val('');
				$("#voucherno").val('');
				$("#chequedate").val('');
				$("#voucherdate").val('');
				order =  $('#orderingList').DataTable({
					responsive: true,
				    "ordering": false
				});
				
				
		/*$("#printForeignAmt:not(:checked)").each(function() {
					var foreignPrice = "table .foreignPrice";
					var currency = ".currency";
					  $(currency).hide();
					 $(foreignPrice).hide();
				});*/
				
		});
}


$( document ).ready(function() {
	$('#input-vendorCode').focusout(function(){
		$.get("SearchVendor",{action:"keyupVendor", criteria:$(this).val()},function(data){
			$(".div-vendorName").text(data.trim());
		});
	});
	
	 
	
	$("[title]").tooltip();
	var order = $('#orderingList').DataTable({
	    responsive: true,
	    "ordering": false
	});

	$("#chequeno").keyup(function(){
		
		 var rows = $("#orderingList").dataTable().fnGetNodes();
	        for(var i=0;i<rows.length;i++)
	        {
	            // Get HTML of 3rd column (for example)
	            $(rows[i]).find(".chequeno").text($(this).val());
	        }
		//$(".chequeno").text($(this).val());
		if($(this).val()==""||$("#voucherno").val()==""||$("#chequedate").val()==""||$("#voucherdate").val()==""){
			 $("#acceptbtn").attr("disabled", "disabled"); 
			 $("#previewbtn").attr("disabled", "disabled"); 
		}else{
			$('#acceptbtn').removeAttr('disabled');
			$('#previewbtn').removeAttr('disabled');
		}
	});
	
	$("#voucherno").keyup(function(){
		
		 var rows = $("#orderingList").dataTable().fnGetNodes();
	        for(var i=0;i<rows.length;i++)
	        {
	            // Get HTML of 3rd column (for example)
	            $(rows[i]).find(".voucherno").text($(this).val());
	        }
		//$(".voucherno").text($(this).val());
		
		if($(this).val()==""||$("#chequeno").val()==""||$("#chequedate").val()==""||$("#voucherdate").val()==""){
			 $("#acceptbtn").attr("disabled", "disabled"); 
			 $("#previewbtn").attr("disabled", "disabled"); 
		}else{
			$('#acceptbtn').removeAttr('disabled');
			$('#previewbtn').removeAttr('disabled');
		}
	});
	
	$("#chequedate").focusout(function(){
		
		 var rows = $("#orderingList").dataTable().fnGetNodes();
	        for(var i=0;i<rows.length;i++)
	        {
	            // Get HTML of 3rd column (for example)
	            $(rows[i]).find(".chequedate").text($(this).val());
	        }
		//$(".chequedate").text($(this).val());
		if($(this).val()==""||$("#chequeno").val()==""||$("#voucherno").val()==""||$("#voucherdate").val()==""){
			 $("#acceptbtn").attr("disabled", "disabled"); 
			 $("#previewbtn").attr("disabled", "disabled"); 
		}else{
			$('#acceptbtn').removeAttr('disabled');
			$('#previewbtn').removeAttr('disabled');
		}
	});
	
	$("#voucherdate").focusout(function(){
		
		 var rows = $("#orderingList").dataTable().fnGetNodes();
	        for(var i=0;i<rows.length;i++)
	        {
	            // Get HTML of 3rd column (for example)
	            $(rows[i]).find(".voucherdate").text($(this).val());
	        }
		//$(".voucherdate").text($(this).val());
		if($(this).val()==""||$("#chequeno").val()==""||$("#voucherno").val()==""||$("#chequedate").val()==""){
			 $("#acceptbtn").attr("disabled", "disabled"); 
			 $("#previewbtn").attr("disabled", "disabled"); 
		}else{
			$('#acceptbtn').removeAttr('disabled');
			$('#previewbtn').removeAttr('disabled');
		}
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
	

		  $("#invoiceno").keyup(function(){
			  $("input[name=optionsRadios][value='invoiceno']").prop("checked",true);
			  $("#referenceno").val("");
			});
	  
	  $("#referenceno").keyup(function(){
		  $("input[name=optionsRadios][value='referenceno']").prop("checked",true);
		  $("#invoiceno").val("");
		});
	  
	  $("#refresh").click(function(){
			location.reload();
		});
		  

});
