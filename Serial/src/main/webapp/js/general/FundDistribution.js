/**
 * 
 */


	$("#btn_addrow").click(function(){
		var newClass =  parseInt(document.getElementById("orderList").rows.length);

	/*	var className = $('tr:last').attr('class');
		alert(className);
		var classes = className.split("_");
		var newClass = parseInt(classes[1])+1;*/
		   var html = "<tr class='row_"+newClass+"'>" + $(".row_1").html() + "</tr>";
		   $("#orderList tbody").append(html);
		   $(".row_" + newClass).find("td:nth-child(2)").text("");
		   $(".row_" + newClass).find(".amount").prop('disabled', true);
		  
		   
		  /* var height = $('#orderList').height();
		   if(height>200){
			   //$("#orderList tbody").css("overflow-y", "auto");
			   $("#orderList tbody").addClass('scrollContent');
		   }*/
		});
	
	//Delete order row
	function removeOrder(deleteRow){
		 var i = deleteRow.parentNode.parentNode.rowIndex;
		 document.getElementById("orderList").deleteRow(i);
		 
		 checkTotal();		
	} 
	
	function checkTotal(){
		 var total = 0.00;
			
		 $("#orderList tbody tr").each(function () {
			 var amount = $(this).closest("tr").find(".amount").val();
			 total = total + parseFloat(amount);
	     });
		 
		 $("#orderAmt").text(parseFloat(total).toFixed(2));
		 
		 if(parseFloat(total).toFixed(2)==parseFloat($("#totalOrder").text()).toFixed(2)){
			 $('#saveFund').prop('disabled', false);
			 
		 }else{
			 $('#saveFund').prop('disabled', true);
		 }
	}

	function getfund(check){
		 $(check).closest("tr").find("td:nth-child(2)").text($(check).val());
		 
		 if($(check).val()==0){
			 $(check).closest("tr").find(".amount").prop('disabled', true);
		 }else{
			 $(check).closest("tr").find(".amount").prop('disabled', false);
		 }
	}
	
	function checkbalance(amt){
		
			
			var code =  $(amt).closest("tr").find(".funds option:selected").text();
			var amount =  $(amt).closest("tr").find(".amount").val();

			if(amount!=""){
				$('#btn_addrow').prop('disabled', false);
			var total = 0.00;
			
			 $("#orderList tbody tr").each(function () {
				 var amount = $(this).closest("tr").find(".amount").val();
				 total = total + parseFloat(amount);
		     });
			 
			 $("#orderAmt").text(parseFloat(total).toFixed(2));

			 if(parseFloat(total).toFixed(2)==parseFloat($("#totalOrder").text()).toFixed(2)){

				 $('#saveFund').prop('disabled', false);
				 //$('#"saveFund"').removeAttr('disabled');
				 
			 }else{
				 $('#saveFund').prop('disabled', true);
				// $('#"saveFund"').attr('disabled','disabled');
			 }
			$.get("Handler_CheckBalance",{code:code,amount:amount,action:"check"},function(data){
				if(data.trim()=="fail"){
					swal({   
						title: "Fund allocation will be exceeded. Proceed anyways?",
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
						/* 	var vendor = $("#vendor").val();
							var ref = $("#ref").val();
							$.get("Handler_CheckBalance",{code:code,amount:parseFloat(total).toFixed(2),action:"insert",
															vendor:vendor,ref:ref,txcd:"200"},function(data){
								
							}); */
						},function(dismiss) {
						  if (dismiss === 'cancel') {
							  $("#fundDistribution").modal("hide");
						  }
						})
				}
			});
			}else{
				$('#btn_addrow').prop('disabled', true);
			}

	}
	
/*	function proceed(){
		//alert("ssda");
		var code = [];
		var amount = [];
		var vendor =  $("#vendor").val();
		alert(vendor);
		var desc =  $("#desc").val();
		alert(desc);
		var ref = $("#ref").val();
		alert(ref);
		 $("#orderList tbody tr").each(function () {
			  var amount = $(this).closest("tr").find(".amount").val();
			 total = total + parseInt(amount); 
			code.push($(this).closest("tr").find(".funds option:selected").text());
			 amount.push($(this).closest("tr").find(".amount").val());
	     });
		 
		 var module =  $("#module").val();
		 alert(module);
		if(module=="AAAM0450"){
			$.get("Handler_CheckBalance",{code:code,amount:amount,action:"insert",
				vendor:vendor,ref:ref,txcd:"200",total:code.length, desc:desc},function(data){
					
					if(data.trim()=="success"){
						processOrdering();
						 $("#fundDistribution").modal("hide");
					}
			});
		}else if(module=="AAAM1150"){
			alert("sdsdsd");
			$.get("Handler_CheckBalance",{code:code,amount:amount,action:"insert",
				vendor:vendor,ref:ref,txcd:"600",total:code.length, desc:desc},function(data){
					
					if(data.trim()=="success"){
						processRecord();
						 $("#fundDistribution").modal("hide");
					}
			});
		}
	}
	*/
	
	function proceed(){
		var code = [];
		var amount = [];
		var vendor =  $("#vendor").val();
		var desc =  $("#desc").val();
		var ref = $("#ref").val();
		var orderno = [];
		 $("#orderList tbody tr").each(function () {
			/*  var amount = $(this).closest("tr").find(".amount").val();
			 total = total + parseInt(amount); */
			code.push($(this).closest("tr").find(".funds option:selected").text());
			 amount.push($(this).closest("tr").find(".amount").val());
	     });

		 /*$("input:checkbox[name=orderlist]:checked").each(function () {
			 var order = "";
				if(($(this).val()).includes("*")){
					order =  ($(this).val()).replace("*", "");
				}else{
					order = $(this).val();
				}
				orderno.push(order);
	     });*/
		 
		//29092020
		 $('#orderingList').DataTable().rows().nodes().to$().find(':has(:checkbox:checked)').each(function(){
			 var order = "";
			 var orderNO = $(this).closest('tr').find('td:eq(1)').text(); 
			if((orderNO).includes("*")){
				order =  "'" + (orderNO).replace("*", "").trim() + "'";
			}else{
				order = orderNO;
			}
			orderno.push(order);
	     });
		 
		 
		 var module =  $("#module").val();
		if(module=="AAAM0450"){
			$.get("Handler_CheckBalance",{code:code,amount:amount,action:"insert",
				vendor:vendor,ref:ref,txcd:"200",total:code.length, desc:desc, orderno:orderno, totalorder:orderno.length},function(data){
					
					if(data.trim()=="success"){
						processOrdering();
						 $("#fundDistribution").modal("hide");
					/*	 $("input:checkbox[name=orderlist]:checked").each(function () {
							 var order = "";
								if(($(this).val()).includes("*")){
									order =  ($(this).val()).replace("*", "");
								}else{
									order = $(this).val();
								}
								orderno.push(order);
					     });
						 alert(code.toString() + code.length);
						$.get("Handler_InsertCode",{ref:ref, txcd:"200", code: code, amount:amount, orderno:orderno, total:code.length, totalOrder:orderno.length},function(data){
							if(data.trim()=="success"){
								processOrdering();
								 $("#fundDistribution").modal("hide");
							}
						});*/
					}
			});
		}else if(module=="AAAM1150"){
			$.get("Handler_CheckBalance",{code:code,amount:amount,action:"insert",
				vendor:vendor,ref:ref,txcd:"600",total:code.length, desc:desc, orderno:orderno, totalorder:orderno.length},function(data){
				
					if(data.trim()=="success"){
						//$.get("Handler_InsertCode",{ref:ref, txcd:"600", code: code, amount:amount, orderno:orderno, total:orderno.length},function(data){
							processRecord();
							 $("#fundDistribution").modal("hide");
						//})
					}
			});
		}
	}