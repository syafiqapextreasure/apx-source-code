$(document).ready(function() {

//	var username ="sysadmin";
	
	var liferayLogin = $("#liferayLogin").val();
	
/*	$('#dateRecorded').text(moment(todayDate).format("DD/MM/YYYY"));*/
	$("#recordedBy").text(liferayLogin);
	
	   $(".search").click(function(){
		   
		   var action = 'searchMembershipDetail';

			   var url = "Modal_PatrSearch?action=" + action;
			
			 $.get(url,function(data){
				
				 $("#searchPatrContent").html(data);
			   });
		   });
		
		$('#patronId').bind("enterKey",function(e){
			value = $("#patronId").val(); 
			var result='';
			$('#patronAddr').empty();
			$('#patronName').empty();
			$('#divNewExpDate').hide();
			$('#newExpDate').empty();
			$('#membershipDate').empty();
			$('#expiredDate').empty();
			$('#onloanCount').empty();
			$('#overdueCount').empty();
			$('#reservationCount').empty();
			$('#itemChargedToDate').empty();
			$('#lateReturnToDate').empty();
			$('#noOfItemLost').empty();
			$('#noOfSuspend').empty();
			$('#itemChargedYTD').empty();
			$('#lateReturnsYTD').empty();	
			$('#lastChargeDate').empty();
			$('#lastReturnDate').empty();
			$('#lastRenewDate').empty();
			$('#amountOutstanding').empty();
			$('#amountPaid').empty();
			$('#groupId').empty();
			$('#status').empty();
			$('#DOB').empty();
			$('#gender').empty();
			$('#dept').empty();
			$('#category').empty();
						
	        $.ajax({
	        	url: 'PatronStatus?patronId='+ $("#patronId").val(),
	        	async: false,
	        	success: function(data) {
	        		result = data;
	        		if(result != null) {
	 
	            		$('#patronIdinPS').val(value);
	    				$('#patronName').text(data[0].patronName);
	    				$('#patronAddr').append(data[0].addr1 +'\n'+ data[0].addr2+'\n'+ data[0].addr3+'\n'+ data[0].code+'\n'+ data[0].town);
	    				$('#membershipDate').text(data[0].memberDate);
	    				$('#expiredDate').text(data[0].expiredDate);
	    				$('#onloanCount').text(data[0].onloanItemCount);
						$('#overdueCount').text(data[0].onloanItemCount);
						$('#reservationCount').text(data[0].reservationCount);
						$('#itemChargedToDate').text(data[0].itemChargedToDate);
						$('#lateReturnToDate').text(data[0].lateReturnToDate);
						$('#noOfItemLost').text(data[0].noOfItemLost);
						$('#noOfSuspend').text(data[0].noOfSuspend);
						$('#itemChargedYTD').text(data[0].itemChargedYTD);
						$('#lateReturnsYTD').text(data[0].lateReturnsYTD);	
						$('#lastChargeDate').text(data[0].lastChargeDate);
						$('#lastReturnDate').text(data[0].lastReturnDate);
						$('#lastRenewDate').text(data[0].lastRenewDate);
						$('#amountOutstanding').text(data[0].amountOutstanding);
						$('#amountPaid').text(data[0].amountPaid);
						$('#groupId').text(data[0].groupId);
						$('#status').text(data[0].status);
						$('#DOB').text(data[0].DOB);
						$('#gender').text(data[0].gender);
						$('#dept').text(data[0].dept);
						$('#category').text(data[0].category);
	    				
						$('#renewMembershipBtn').prop('disabled', false);
	            	}
	        	}
	        })
	        
	    	if( (result !="") && (result[0].validateMembershipRenewal=="true")){
				
	            var return_data = new Array();
	           
	            $('#spanPatronName').text(result[0].patronId);
	            $('#spanPatronCate').text(result[0].patronName);
	            
	            $('#renewBtn').prop('disabled', false);
	            $('#receiptingBtn').prop('disabled', false);
	            $('#patronStatusBtn').prop('disabled', false);
	            $('#newBtn').prop('disabled', false);
	            $('#accessionNo').prop('disabled', false);
	            $('#retrieveAllBtn').prop('disabled', false);

	    	}else if(result ==""){    
	            
				swal({
					text: "Invalid Patron Id, please re-enter Id?",
		
				})
			}else if(result !="" && result[0].validateMembershipRenewal=="false"){

				swal({
					text: "Patron membership has expired",
		
				})
			}
		});		

    $('#patronId').keyup(function(e){
		if(e.keyCode == 13)
		{
		  $(this).trigger("enterKey");
		}
	});

    $('#patronId').keydown(function(e) {
        var code = e.keyCode || e.which;

        if (code === 9) {  
        	$(this).trigger("enterKey");
        }
    });
    
//	$('#patronSearchBtn').click(function(){
//		value = $("#patronId").val(); 
//		var result='';
//		$('#patronAddr').empty();
//		$('#newExpDate').empty();
//		
//		
//        $.ajax({
//        	url: 'PatronStatus?patronId='+ $("#patronId").val(),
//        	async: false,
//        	success: function(data) {
//        		result = data;
//        		if(result != null) {
// 
//            		$('#patronIdinPS').val(value);
//    				$('#patronName').text(data[0].patronName);
//    				$('#patronAddr').append(data[0].addr1 +'\n'+ data[0].addr2+'\n'+ data[0].addr3+'\n'+ data[0].code+'\n'+ data[0].town);
//    				$('#membershipDate').text(data[0].memberDate);
//    				$('#expiredDate').text(data[0].expiredDate);
//    				$('#recordedBy').text(data[0].recordedBy);
//    				$('#onloanCount').text(data[0].onloanItemCount);
//					$('#overdueCount').text(data[0].onloanItemCount);
//					$('#reservationCount').text(data[0].reservationCount);
//					$('#itemChargedToDate').text(data[0].itemChargedToDate);
//					$('#lateReturnToDate').text(data[0].lateReturnToDate);
//					$('#noOfItemLost').text(data[0].noOfItemLost);
//					$('#noOfSuspend').text(data[0].noOfSuspend);
//					$('#itemChargedYTD').text(data[0].itemChargedYTD);
//					$('#lateReturnsYTD').text(data[0].lateReturnsYTD);	
//					$('#lastChargeDate').text(data[0].lastChargeDate);
//					$('#lastReturnDate').text(data[0].lastReturnDate);
//					$('#lastRenewDate').text(data[0].lastRenewDate);
//					$('#amountOutstanding').text(data[0].amountOutstanding);
//					$('#amountPaid').text(data[0].amountPaid);
//					$('#groupId').text(data[0].groupId);
//					$('#status').text(data[0].status);
//					$('#DOB').text(data[0].DOB);
//					$('#gender').text(data[0].gender);
//					$('#dept').text(data[0].dept);
//					$('#category').text(data[0].category);
//    				
//					$('#renewMembershipBtn').prop('disabled', false);
//            	}
//        	}
//        })
//        
//    	if( (result !="") && (result[0].validateMembershipRenewal=="true")){
//			
//            var return_data = new Array();
//           
//            $('#spanPatronName').text(result[0].patronId);
//            $('#spanPatronCate').text(result[0].patronName);
//            
//            $('#renewBtn').prop('disabled', false);
//            $('#receiptingBtn').prop('disabled', false);
//            $('#patronStatusBtn').prop('disabled', false);
//            $('#newBtn').prop('disabled', false);
//            $('#accessionNo').prop('disabled', false);
//            $('#retrieveAllBtn').prop('disabled', false);
//
//    	}else if(result ==""){    
//            
//			swal({
//				text: "Invalid Patron Id, please re-enter Id?",
//	
//			})
//		}else if(result !="" && result[0].validateMembershipRenewal=="false"){
//
//			swal({
//				text: "Patron membership has expired",
//	
//			})
//		}else {
//		$('#patronSearchModal').modal('show');
//	}
////        });		
//	});
    
//    $.ajax({
//    	url: 'MemberPatronCateDesc',
//    	success: function(data) {
//    		
////    		$('#patronCategoryDesc1').empty();
////    		$('#patronCategoryDesc1').append($("<option />").val('').text('All'));
//			$.each(data, function(key, entry) {
//				$('#patronCategoryDesc1').append(
//						$("<option />").val(entry)
//								.text(entry));
//			})
//    	}
//    });
    
	$.ajax({
		
		url : 'MemberPatronCateDesc',
		beforeSend : function() {
    		$('#patronCategoryDesc1').empty();
    		$('#patronCategoryDesc1').append($("<option />").val('').text('All'));

		},
		success : function(data1) {
			if (data1) {
				var count1 = 0;

				$.each(data1, function(key, entry) {
					$('#patronCategoryDesc1').append(
							$("<option />").val(entry)
									.text(entry));
					count1++;
				})
			}
			$dropdown = $('#patronCategoryDesc1');
	//		$dropdown[0].selectedIndex = 0;
		}
	});
    
	$('#startSearch').click(function(){
		
		var result='';
		
		$.ajax({
			url: "PatronSearch",
			data : {searchBy: $('#searchBy').val(),searchText: $('#searchText').val(),filterBy: $('#patronCategoryDesc1').val(),sortedBy: $('#sortedBy').val()},
			type: "GET",
		    dataType: 'json',
		    async: false,
			success: function (data) {
			        result=data;
			}  
		})
		
		  if($.trim(result)){
	    	  
	    		$('#modal_table_searchPatron').dataTable().fnClearTable();
				
				$('#modal_table_searchPatron').DataTable({
					select: true,
					destroy: true,
					searching: false,
					bLengthChange: false,
					autoWidth: true,
					responsive: false,
					aaSorting: [],
					processing: true,
				    ajax: {
				    	url: "PatronSearch",
				        data : {searchBy: $('#searchBy').val(),searchText: $('#searchText').val(),filterBy: $('#patronCategoryDesc1').val(),sortedBy: $('#sortedBy').val()},
				        type: "GET",
				        dataSrc: function (data) {

				        	$.fn.dataTable.ext.errMode = 'none';
				        	
				            var return_data = new Array();

				            for( var i=0;i< data.length; i++) {
				            	var stat = data[i].status;
								return_data.push({
									'Patron ID': data[i].patronId,
									'Patron Name' : data[i].patronName,
								})
				            }
				            return return_data;
				          },
				     },
			     	columns    : [
						{'data': 'Patron ID'},
						{'data': 'Patron Name'},
					],
			        'dom': 'Rlfrtip',
//			        'colReorder': {
//			            'allowReorder': false
//			        },
//			        select: {
//			            style: 'single'
//			        }
				});
	      }	else{
	    	  swal("Empty recordset.");
	      }	
	});
	
	$('#renewMembershipBtn').click(function(){
		value = $("#patronId").val(); 
		var result='';

        $.ajax({
        	url: 'MembershipDetail?patronId='+ $("#patronId").val() + '&expDate=' + $("#expiredDate").text() ,
        	async:false,
			success : function(data) {

				result = data;
			 }
        });	
        
        	if (result[0].newExpiryDate != "null") {
					$('#renewMembershipModal').modal('show'); 
        			$('#newExpDate_modal').val(result[0].newExpiryDate);
        			$('#category_modal').val(result[0].category);
        			$('#fee_modal').val(result[0].fee);
		
				}else if (result[0].newExpiryDate == "null"){
						swal("Membership Renewal not allowed due to expired date more than " + result[0].checkDays + " days from current date.");
				}
	    });
	
    $(document).on('click dblclick', '#modal_table_searchPatron > tbody >tr', function() {

	    if ($(this).hasClass('selected')) {
            $(this).removeClass('selected');
        } else {
            $('#modal_table_searchPatron > tbody > tr.selected').removeClass('selected');
            $(this).addClass('selected');
        }
        
        var getDateFromCell= ($(this).text());

    });
    $(document).on("click", "#btn_submit", function(event){
    	
    	var criteria = $('#patronSearchInput').val();
    	var searchType =$(".text-value").data('span');
    	var cate_id = $('#cate_type').val();
    	var action = $("#action").val();
    	var sort = $("#patronSortedBy").val();
    	
    	loader("#display_vendor");
    	$.get("MemberSearchPatron",{criteria:criteria,search_type:searchType,cate_id:cate_id, action:action, sort:sort},function(data_vendor){
    	/*	$("#result_vendor").collapse('show');
    		$("#search_vendor").collapse('hide');*/
    		
    		$("#display_vendor").html(data_vendor);
    		$('#patr_result').DataTable();
    	   });
    });

    
	$('#patronSearchModal').on("hidden.bs.modal", function () { 
		
		var result='';
		
		var table = $('#modal_table_searchPatron').DataTable();
		
		var selectedRowValue= table.row('.selected').data();

		$.ajax({
	        	url: 'PatronStatus?patronId='+ selectedRowValue["Patron ID"],
	        	async: false,
	        	success: function(data) {
	        		if(data != null) {
	 
	            		$('#patronId').val(selectedRowValue["Patron ID"]);
	    				$('#patronName').text(data[0].patronName);
	    				$('#patronAddr').append(data[0].addr1 +'\n'+ data[0].addr2+'\n'+ data[0].addr3+'\n'+ data[0].code+'\n'+ data[0].town);
	    				$('#membershipDate').text(data[0].memberDate);
	    				$('#expiredDate').text(data[0].expiredDate);
	    	//			$('#recordedBy').text(data[0].recordedBy);
	    				$('#onloanCount').text(data[0].onloanItemCount);
						$('#overdueCount').text(data[0].onloanItemCount);
						$('#reservationCount').text(data[0].reservationCount);
						$('#itemChargedToDate').text(data[0].itemChargedToDate);
						$('#lateReturnToDate').text(data[0].lateReturnToDate);
						$('#noOfItemLost').text(data[0].noOfItemLost);
						$('#noOfSuspend').text(data[0].noOfSuspend);
						$('#itemChargedYTD').text(data[0].itemChargedYTD);
						$('#lateReturnsYTD').text(data[0].lateReturnsYTD);	
						$('#lastChargeDate').text(data[0].lastChargeDate);
						$('#lastReturnDate').text(data[0].lastReturnDate);
						$('#lastRenewDate').text(data[0].lastRenewDate);
						$('#amountOutstanding').text(data[0].amountOutstanding);
						$('#amountPaid').text(data[0].amountPaid);
						$('#groupId').text(data[0].groupId);
						$('#status').text(data[0].status);
						$('#DOB').text(data[0].DOB);
						$('#gender').text(data[0].gender);
						$('#dept').text(data[0].dept);
						$('#category').text(data[0].category);
	    				
						$('#renewMembershipBtn').prop('disabled', false);
		
						$('#modal_table_searchPatron').dataTable().fnClearTable();
	        	}
	        }
	    });
	});
	
	$('#updateMembershipBtn').click(function(){
		value = $("#patronId").val(); 
		var result = "";

		swal({
			text: "Are you sure want to renew membership?",
			showCancelButton : true,
			confirmButtonColor : '#3085d6',
			cancelButtonColor : '#d33',
			confirmButtonText : 'Yes',
			cancelButtonText : 'No',
			confirmButtonClass : 'confirm-class',
			cancelButtonClass : 'cancel-class',
			closeOnConfirm : false,
			closeOnCancel : false
		}).then(
			function() {
				$.ajax({
					url : "UpdateMembership",
					async: false,
					data: { patronId: $("#patronId").val(), expDate: $("#expiredDate").text(), newExpDate: $("#newExpDate_modal").val(), recordedBy: liferayLogin, fee: $("#fee_modal").val()},
						success : function(data) {

							result=data;

							if (data[0].updateStatus == "pass") {
								swal("Membership Renew Successfully ");
										
								$('#divNewExpDate').toggle();
								$('#newExpDate').html(result[0].newExpiryDate);	
							}else{
	     						swal("Membership failed to Renew due to expired date more than " + result[0].fee + " days from current date.");
	     					}
						}
					});
			}, function(dismiss) {
				if (dismiss == 'cancel') {
					swal('', 'Cancelled');
				}
	    });
	});
});

function appendData(GL14PATR){

	$(document).ready(function() {

	$("#patronId").val(GL14PATR);

//	setTimeout(function(){
//        $('#patronId')[0].focus();
//    }, 1000);
//	
    $.ajax({
    	url: 'PatronStatus?patronId='+ GL14PATR,
    	async: false,
    	success: function(data) {
    		result = data;
    		if(result != null) {

        	//	$('#patronIdinPS').val(value);
				$('#patronName').text(data[0].patronName);
				$('#patronAddr').append(data[0].addr1 +'\n'+ data[0].addr2+'\n'+ data[0].addr3+'\n'+ data[0].code+'\n'+ data[0].town);
				$('#membershipDate').text(data[0].memberDate);
				$('#expiredDate').text(data[0].expiredDate);
			//	$('#recordedBy').text(data[0].recordedBy);
				$('#onloanCount').text(data[0].onloanItemCount);
				$('#overdueCount').text(data[0].onloanItemCount);
				$('#reservationCount').text(data[0].reservationCount);
				$('#itemChargedToDate').text(data[0].itemChargedToDate);
				$('#lateReturnToDate').text(data[0].lateReturnToDate);
				$('#noOfItemLost').text(data[0].noOfItemLost);
				$('#noOfSuspend').text(data[0].noOfSuspend);
				$('#itemChargedYTD').text(data[0].itemChargedYTD);
				$('#lateReturnsYTD').text(data[0].lateReturnsYTD);	
				$('#lastChargeDate').text(data[0].lastChargeDate);
				$('#lastReturnDate').text(data[0].lastReturnDate);
				$('#lastRenewDate').text(data[0].lastRenewDate);
				$('#amountOutstanding').text(data[0].amountOutstanding);
				$('#amountPaid').text(data[0].amountPaid);
				$('#groupId').text(data[0].groupId);
				$('#status').text(data[0].status);
				$('#DOB').text(data[0].DOB);
				$('#gender').text(data[0].gender);
				$('#dept').text(data[0].dept);
				$('#category').text(data[0].category);
				
				$('#renewMembershipBtn').prop('disabled', false);
        	}
    	}
    })
	
}
	)};