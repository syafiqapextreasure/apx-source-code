/**
 * 
 */

function updateVendor(){
	
	var orderno = [];
	var ordernoList = [];
	var vendorCode = $("#vendorCode").val();
	
	if(!vendorCode.length){
		$('#cancelOrderList').DataTable().rows().nodes().to$().find(':has(:checkbox:checked)').each(function(){
			 vendorCode = $(this).closest('tr').find('td:eq(2)').text(); 
	    });
	}
	
	var checkedValues = $("[name='selectedValue[]']:checked").map(function(){
			orderno.push($(this).val());
    }).get();
	
	$("[name='selectedValue[]']:not(:checked)").map(function(){
		ordernoList.push($(this).val());
	}).get();
	
	 $.get("Handler_Resubmit",{orderno:orderno, total:orderno.length, vendor:vendorCode},function(status){
		 $.get("Table_CancelOrderList",{orderno:ordernoList.toString(), action:"reload"},function(data){
			 $("#resubmitVendor").modal("hide");
			 swal('', 'Record successfully resubmitted', '');
				$("#cancel_orderList tbody").html(data);
			});
	 });
}
//Change to new vendor
function resubmit(){
	var orderno = [];
	var checkedValues = $("[name='selectedValue[]']:checked").map(function(){
			orderno.push($(this).val());
    }).get();
	
	
	swal({   
		text: 'Resubmit selected order?',
		showCancelButton: true,
		confirmButtonColor: '#3085d6', 
		cancelButtonColor: '#d33',
		confirmButtonText: 'Yes, resubmit!',
		cancelButtonText: 'No, cancel !',
		confirmButtonClass: 'confirm-class',
		cancelButtonClass: 'cancel-class',
		closeOnConfirm: false,
		closeOnCancel: true 
		}).then(function() {
			swal({   
				text: 'Do you want to assign a new vendor?',
				showCancelButton: true,
				confirmButtonColor: '#3085d6', 
				cancelButtonColor: '#d33',
				confirmButtonText: 'Yes',
				cancelButtonText: 'No',
				confirmButtonClass: 'confirm-class',
				cancelButtonClass: 'cancel-class',
				closeOnConfirm: false,
				closeOnCancel: true 
				}).then(function() {
					$("#resubmitVendor").modal("show");
				},function(dismiss) {
					$("#resubmitVendor").modal("hide");
					updateVendor();
				})
		},function(dismiss) {

		})
		
	
}

//Purge Record
function deleteRecord(){
	var orderno = [];
	var ordernoList = [];
	var checkedValues = $("[name='selectedValue[]']:checked").map(function(){
			orderno.push($(this).val());
    }).get();
	
	$("[name='selectedValue[]']:not(:checked)").map(function(){
		ordernoList.push($(this).val());
}).get();
	for (var j = 0; j < orderno.length; j++) {
		var order = orderno[j];
		swal({   
			title: 'Purge record ' +order + ' from database?',
			showCancelButton: true,
			confirmButtonColor: '#3085d6', 
			cancelButtonColor: '#d33',
			confirmButtonText: 'Yes, delete it!',
			cancelButtonText: 'No, cancel !',
			confirmButtonClass: 'confirm-class',
			cancelButtonClass: 'cancel-class',
			closeOnConfirm: false,
			closeOnCancel: true 
			}).then(function() {
				alert(order);
				 $.get("Handler_DeleteRecord",{orderno:order},function(status){
					
					 var message = status.split(",");
			 				if(message[0].trim() == "Successful"){
			 					localStorage.setItem("ctrlno",  message[1].trim());
			 					$.get("Table_CancelOrderList",{orderno:ordernoList.toString(), action:"reload"},function(data){
			 					
			 							$("#cancel_orderList tbody").html(data);
			 						});
			 					swal('', 'Record successfully deleted', '');
			 					
			 					swal({   
									title: "Do you like to delete BO?",
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
									
											$("#displayBO").modal("show");
								
										$.get("Modal_BORecord",{CT03DOCNO:localStorage.getItem('ctrlno'),action:"deleteBO"},function(list){
											
											$("#BORecord").html(list);
											  
										  });
												},function(dismiss) {
													  if (dismiss === 'cancel') {
														  $("#CT03DOCNO").val("");
														  return false;
													  }
													})
			 				}else{
			 					swal(
									      'Cancelled',
									      'Record is not deleted',
									      'error'
									    );
			 				}
			 				//updateTableWithLastQuery();
			 			})
				
			},function(dismiss) {
			 if (dismiss === 'cancel') {
				swal(
				     'Cancelled',
				     'Record is not deleted',
				     'error'
				    );
				  }
				})
	}

	
	
}

function searchAcq(){
	var criteria = $( 'input[name="searchMethod"]:checked' ).val();
	var checkboxArray = [];
	var vendor = "";
	var cancelstartdate, cancelenddate = "";
	var orderstartdate, orderenddate = "";
	var feedback = "";
	var orderno = "";
	var reference = "";
	var checkedValues = $("[name='searchMethod']:checked").map(function(){
		if($(this).val()=="vendor"){
			checkboxArray.push($(this).val());
			vendor= $("#input-vendorCode").val();
		}
		if($(this).val()=="ordercanceldate"){
			checkboxArray.push($(this).val());
			cancelstartdate=$("#input-cancellationStartDate").val();
			cancelenddate = $("#input-cancellationEndDate").val();
		}
		if($(this).val()=="orderdate"){
			checkboxArray.push($(this).val());
			orderstartdate=$("#input-startDate").val();
			orderenddate = $("#input-endDate").val();
		}
		
		if($(this).val()=="feedbacktype"){
			checkboxArray.push($(this).val());
			feedback=$("#select-feedbackType").val();
		}
		
		if($(this).val()=="orderno"){
			checkboxArray.push($(this).val());
			orderno=$("#input-orderNo").val();
		}
		
		if($(this).val()=="referenceno"){
			checkboxArray.push($(this).val());
			reference=$("#input-referenceNo").val();
		}
		
    }).get();

	 $("#cancelOrderList").DataTable().destroy();
		

	$.get("Table_CancelOrderList",{action:"new",checkboxArray:checkboxArray.toString(), vendor:vendor,cancelstartdate:cancelstartdate, cancelenddate:cancelenddate,
		orderstartdate: orderstartdate, orderenddate:orderenddate, feedback:feedback, orderno:orderno, reference:reference},function(data){
		/*	if(data.trim()==""){
				$("#resubmit").attr("disabled", "disabled");
				$("#btn_add").attr("disabled", "disabled");
			}else{
				$("#resubmit").removeAttr("disabled");
				$("#btn_add").removeAttr("disabled");
			}*/
			$("#cancel_orderList tbody").html(data);
			 order =  $('#cancelOrderList').DataTable({
				    responsive: true,
				    order: [],
				    columnDefs: [ { orderable: false, targets: [0] ,'searchable': false} ]
				});
			$("#searchCancelOrder").modal("hide");
		});
}

/*$('body').on('hidden.bs.modal', '#vendorSearch', function () {
	  $(this).removeData('bs.modal');
	});*/

$('#searchCancelOrder').on('hidden.bs.modal', function() {
	  $(this).removeData('bs.modal');
	$(".div-vendorName").text("");
});

$('#resubmitVendor').on('hidden.bs.modal', function() {
	$(this).removeData('bs.modal');
	$("#vendorCode").val("");
	$(".div-vendorName").text("");
});


function getResubmit(){
	if ($("input[class=spineChk]:checked").length > 0) {
		$("#resubmit").removeAttr("disabled");
		$("#btn_add").removeAttr("disabled");
	}else{
		$("#resubmit").attr("disabled", "disabled");
		$("#btn_add").attr("disabled", "disabled");
	}
}

$("input[name=searchMethod]").change(function(){
	if ($("input[name=searchMethod]:checked").length > 0) {
		$(".vendor_submit").removeAttr("disabled");
	}else{
		$(".vendor_submit").attr("disabled", "disabled");
	}

});

$('#input-vendorCode, #vendorCode').focusout(function(){
	$.get("SearchVendor",{action:"keyupVendor", criteria:$(this).val()},function(data){
		 $("input[name=searchMethod][value='vendor']").prop("checked",true);
		$(".div-vendorName").text(data.trim());
		
		  if ($("input[name=searchMethod]:checked").length > 0) {
				$(".vendor_submit").removeAttr("disabled");
			}else{
				$(".vendor_submit").attr("disabled", "disabled");
			}
	});
});

$( document ).ready(function() {
	  $("#input-vendorCode").keyup(function(){
		  
		  if ($(this).val() != null &&$(this).val() != '') {  
			  $("input[name=searchMethod][value='vendor']").prop("checked",true);
		 }else{
			  $("input[name=searchMethod][value='vendor']").prop("checked",false);
		 }	
		  
		  if ($("input[name=searchMethod]:checked").length > 0) {
				$(".vendor_submit").removeAttr("disabled");
			}else{
				$(".vendor_submit").attr("disabled", "disabled");
			}
		});
	  
  $("#input-cancellationStartDate, #input-cancellationEndDate").focusout(function(){
		  
		  if ($(this).val() != null &&$(this).val() != '') {  
			  $("input[name=searchMethod][value='ordercanceldate']").prop("checked",true);
		 }else{
			  $("input[name=searchMethod][value='ordercanceldate']").prop("checked",false);
		 }	
		  
		  if ($("input[name=searchMethod]:checked").length > 0) {
				$(".vendor_submit").removeAttr("disabled");
			}else{
				$(".vendor_submit").attr("disabled", "disabled");
			}
		});
  
  $("#input-startDate, #input-endDate").focusout(function(){
	  
	  if ($(this).val() != null &&$(this).val() != '') {  
		  $("input[name=searchMethod][value='orderdate']").prop("checked",true);
	 }else{
		  $("input[name=searchMethod][value='orderdate']").prop("checked",false);
	 }	
	  
	  if ($("input[name=searchMethod]:checked").length > 0) {
			$(".vendor_submit").removeAttr("disabled");
		}else{
			$(".vendor_submit").attr("disabled", "disabled");
		}
	});
  
  $("#input-orderNo").keyup(function(){
	  
	  if ($(this).val() != null &&$(this).val() != '') {  
		  $("input[name=searchMethod][value='orderno']").prop("checked",true);
	 }else{
		  $("input[name=searchMethod][value='orderno']").prop("checked",false);
	 }	
	  
	  if ($("input[name=searchMethod]:checked").length > 0) {
			$(".vendor_submit").removeAttr("disabled");
		}else{
			$(".vendor_submit").attr("disabled", "disabled");
		}
	});
  
  $("#input-referenceNo").keyup(function(){
	  
	  if ($(this).val() != null &&$(this).val() != '') {  
		  $("input[name=searchMethod][value='referenceno']").prop("checked",true);
	 }else{
		  $("input[name=searchMethod][value='referenceno']").prop("checked",false);
	 }	
	  
	  if ($("input[name=searchMethod]:checked").length > 0) {
			$(".vendor_submit").removeAttr("disabled");
		}else{
			$(".vendor_submit").attr("disabled", "disabled");
		}
	});
  
 $("#select-feedbackType").change(function(){
	  
	  if ($(this).val() != '') {  
		  $("input[name=searchMethod][value='feedbacktype']").prop("checked",true);
	 }else{
		  $("input[name=searchMethod][value='feedbacktype']").prop("checked",false);
	 }	
	  
	  if ($("input[name=searchMethod]:checked").length > 0) {
			$(".vendor_submit").removeAttr("disabled");
		}else{
			$(".vendor_submit").attr("disabled", "disabled");
		}
	});
});