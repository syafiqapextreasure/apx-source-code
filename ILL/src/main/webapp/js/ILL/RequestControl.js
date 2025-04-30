$(document).ready(function() {

	///////////////////// Set Current Date  ///////////////////////////////////
	var today = new Date(); 
	var liferayLogin = $("#liferayLogin").val();

	///////////////////// Set ReqCtrl_table  ////////////////////////////////
	$('#ReqCtrl_table').DataTable( {
		destroy: true,
		searching: false,
		bLengthChange: false,
		autoWidth: false,
		responsive: true,
	});
	
	///////////////////// input-startDate set Current Date  ///////////////////////////////////
	$('#dexpect').datepicker({
		//startDate : today,
		format: "dd/mm/yyyy",
		todayBtn: true,
		autoclose: true,
		todayHighlight: true,
	});

	///////////////////// Set text autocomplete off////////////////////////////
	$('input[type="text"]').attr('autocomplete', 'off');

	$("#txtPatron, #txtLibraryID").attr('maxlength','12');
	$("#txtDeliveryType, #txtTitle, #txtAuthor, #txtPlaceOfPublication, #txtPublisher, #txtRequestorRemark").attr('maxlength','225');
	$("#txtIsbn, #txtYearOfPublication, #txtEdition, #txtVolume, #txtIssue, #txtPageNumber").attr('maxlength','100');

	//////////////////////////////Clear All Modal /////////////////////////////
	$('#searchReqCtrl').on('hidden.bs.modal', function () {
		$(this).find('form').trigger('reset');
		//$('input[name="searchSelection"][value="reqno"]').prop('checked', true);
		$("#datepickerDate").hide();
		$("input[name=input-criteria]").show();
	});

	$('#modalReqCtrl').on('hidden.bs.modal', function () {
		$(this).find('form').trigger('reset');
		$('#formdataReqCtrl').data('bootstrapValidator').resetForm();
		$(".lblRequestNo, .lblStatus, .lblDateRequested, .lblPatronName, .lblRequestorName").empty();
	});

	//////////////////////////////when search modal open///////////////////////
	$('#searchReqCtrl').on('shown.bs.modal', function () {

		$("input").prop("disabled", false);
		$("input[type=radio]").attr('disabled', false);
		$("#input-criteria").focus();

		$("#datepickerDate").hide();
		$("input[name=input-criteria]").attr('maxlength','10');

		$('#endDate').val(moment(today).format("DD/MM/YYYY"));
		$('#input-Date, #endDate').datepicker({
			format: "dd/mm/yyyy",
			todayBtn: true,
			autoclose: true,
			todayHighlight: true,
		});


		var value = $('input[name=searchSelection]:checked').val();

		$('input[name=searchSelection]').change(function(){
			var value = $('input[name=searchSelection]:checked').val();

			$("input[name=input-criteria]").val('');
			$("#datepickerDate").hide();
			$("input[name=input-criteria]").show();

			if(value == "reqno"){
				$("#datepickerDate").hide();
				$("input[name=input-criteria]").show();
				$("input[name=input-criteria]").attr('maxlength','10');
			}else if(value == "title"){
				$("#datepickerDate").hide();
				$("input[name=input-criteria]").show();
				$("input[name=input-criteria]").attr('maxlength','255');
			}else if(value == "reqID"){
				$("#datepickerDate").hide();
				$("input[name=input-criteria]").show();
				$("input[name=input-criteria]").attr('maxlength','12');
			}else if(value == "dateReq"){
				$("#datepickerDate").show();
				$("input[name=input-criteria]").hide();
			}else if(value == "llID"){
				$("#datepickerDate").hide();
				$("input[name=input-criteria]").show();
				$("input[name=input-criteria]").attr('maxlength','12');
			}
		});

	});

	//***************************************AREA FOR SEARCH ********************************************//
	$('#search').click(function(){
		$('#ReqCtrl_table').dataTable().fnClearTable();

		var input_criteria = $('#input-criteria').val();
		var search_type = $("input[name='searchSelection']:checked").val();
		var inputDate = moment($("#input-Date").val(), 'DD/MM/YYYY').format("YYYYMMDD");
		var endinputDate = moment($("#endDate").val(), 'DD/MM/YYYY').format("YYYYMMDD"); 

		$('#ReqCtrl_table').DataTable( {
			destroy: true,
			searching: false,
			bLengthChange: false,
			autoWidth: false,
			responsive: true,
			processing: true,
			//info: false,
			ajax: {
				url: "ResultSearch",
				data : {input_criteria : input_criteria, search_type : search_type, inputDate : inputDate,
					endinputDate : endinputDate},
					type: "GET",
					dataSrc: function (json) {
						var return_data = new Array();

						for(var i=0;i< json.length; i++){

							var stat = GetStatus(json[i].status);

							return_data.push({
								'No': (i+1),
								'Reference No': json[i].requestNo,
								'Requestor' : json[i].requestor, 
								'ISBN' : json[i].isbn,
								'Title' : json[i].title,
								'Date Request' : json[i].dateReq,
								'Status' : stat,
								'Action' : '<button id="View" class="btn btn-primary btn-xs" data-toggle="modal" data-target="#modalReqCtrl" data-whatever="View Request Control" title="View Request Control" data-rowid="'+json[i].requestNo+'"><span class="glyphicon glyphicon-eye-open"></span></button> <button id="Edit" class="btn btn-info btn-xs" data-toggle="modal" data-target="#modalReqCtrl" data-whatever="Edit Request Control" title="Edit Request Control" data-rowid="'+json[i].requestNo+'"><span class="glyphicon glyphicon-pencil"></span></button> <button id="Delete" class="btn btn-dull btn-xs Delete" data-id="'+json[i].requestNo+'"><span class="glyphicon glyphicon-trash" title="Delete Request Control" ></span></button>',
							})
						}
						return return_data;

					},
			},//This is the end of the AJAX object from the example above
			columns    : [
			              {'data': 'No'},
			              {'data': 'Reference No'},
			              {'data': 'Requestor'},
			              {'data': 'ISBN'},
			              {'data': 'Title'},
			              {'data': 'Date Request'},
			              {'data': 'Status'},
			              {'data': 'Action'},
			              ],
			              drawCallback: function( settings ) {
			            	  $('#ReqCtrl_table tbody tr').each(function() {
			            		  var Cell = $(this).find('td:eq(6)');
			            		  //alert(Cell.text());
			            		  debugger;
			            		  if((Cell.text() == 'USER REQUEST')){
			            			  $(this).find('#Edit').prop("disabled", false);
			            			  $(this).find('#Delete').prop("disabled", false);
			            		  }else{
			            			  $(this).find('#Edit').prop("disabled", true);
			            			  $(this).find('#Delete').prop("disabled", true);
			            		  }
			            	  });
			              }
		});

		$("#closesearchReqCtrl").click();  

	});
	//***********************************END AREA FOR SEARCH ********************************************//


	///////////////////////////////////////////////////////////////////////////////////////////////////////

	function GetStatus(vsStatus){

		var tempGetStatus;

		switch (vsStatus){
		case "00":
			tempGetStatus = "USER REQUEST";
			break;
		case "01":
			tempGetStatus = "REJECTED";
			break;
		case "02":
			tempGetStatus = "IN PROGRESS";
			break;
		case "03":
			tempGetStatus = "RECEIVED";
			break;
		case "04":
			tempGetStatus = "RETURNED";
			break;
		}

		return tempGetStatus;
	}


	/////////////////////txtPatron
	$("#txtPatron").blur(function(e){

		var reqid = $("#txtPatron").val();

		setTimeout(function(){
			$.get('GetCheckLibExist', {
				code : reqid,
			}, function(responseJson) {

				if(responseJson==''){
					messageBox("154","",""); 
					$("#txtPatron").val("");
					$(".lblRequestorName").empty();
				}else{
					$.each(responseJson, function(key,value) {
						$(".lblPatronName").text(value['lblLibName']);
					});
				}
			});
		},800);
	});
	
	/////////////////////txtLibraryID
	$("#txtLibraryID").blur(function(e){

		var libId = $("#txtLibraryID").val();

		setTimeout(function(){
			$.get('GetCheckLibExist', {
				code : libId,
			}, function(responseJson) {


				if(responseJson==''){
					messageBox("154","",""); 
					$("#txtLibraryID").val("");
					$(".lblLibName").empty();
				}else{
					$.each(responseJson, function(key,value) {
						
						var cateGroup = value['sLLBorrowerCategory'];
						var cate = value['sPatronCat'];
						var expdate = value['sExpDate'];
						
						if(cateGroup != cate){
							messageBox("151","",""); 
							$("#txtLibraryID").val("");
							$(".lblLibName").empty();
						}else{
							if(moment(moment(today, 'DD-MM-YYYY')).format('YYYY-MM-DD') > moment(moment(expdate, 'DD-MM-YYYY')).format('YYYY-MM-DD')){
								messageBox("163","","");
								$("#txtLibraryID").val("");
								$(".lblLibName").empty();
							}else{
								$(".lblLibName").text(value['lblLibName']);
							}
						}
					});
				}
			});
		},800);
	});

	
	//****************************************** AREA FOR MODAL *****************************************//
	$('#modalReqCtrl').on('show.bs.modal', function (event) {
		var button = $(event.relatedTarget) // Button that triggered the modal
		var modal = $(this);
		var recipient = button.data('whatever'); // Extract info from data-* attributes
		var rowid = button.data('rowid');

		modal.find('.modal-title').text(recipient);
		
		///$("#cboDocumentType").prop("selectedIndex",-1);
		
		switch(recipient){
	  	case state = 'Add Request Control':
	  			enable();
	  			$("#txtVolume, #txtIssue, #txtPageNumber").prop("disabled", true);
	  			$(".lblStatus").text("New Request");
	  			$('.lblDateRequested').text(moment(today).format("DD/MM/YYYY"));
	  		break;
	  	case state = 'Edit Request Control':
	  		enable();
		  	$.get('GetEditViewReqCtrl', {
	  			rowid : rowid
			 	}, function(responseJson) {
				if(responseJson != null){
					$.each(responseJson, function(key,value) {
						$(".lblRequestNo").text(value['sRequestNo']);
						
						var status = value['status']
						status = GetStatus(status);
						$(".lblStatus").text(status);
						
						$(".lblDateRequested").text(value['dateReq']);
						$("#txtPatron").val(value['requestor']);
						
						$.get('GetCheckLibExist', {
							code : value['requestor'],
						}, function(responseJson) {
							$.each(responseJson, function(key,value) {
								$(".lblPatronName").text(value['lblLibName']);
							});
						});
						
						$("#txtLibraryID").val(value['sLendingLibraryId']);
						$.get('GetCheckLibExist', {
							code : value['sLendingLibraryId'],
						}, function(responseJson) {
							$.each(responseJson, function(key,value) {
								$(".lblLibName").text(value['lblLibName']);
							});
						});
						
						$("#cboDocumentType").val(value['sDocumentType']);
						
						var valueDocumentType = value['sDocumentType'];
						if(valueDocumentType=='01' || valueDocumentType == '02'){
							$("#txtVolume, #txtIssue, #txtPageNumber").prop("disabled", true); 
						}else if(valueDocumentType=='03' || valueDocumentType == '04'){
							$("#txtVolume, #txtIssue, #txtPageNumber").prop("disabled", false); 
						}
						
						
						var expdate = value['sDateExpected'];
						if(expdate == "-"){
							expdate = "";
						}
						
						$("#dexpect").val(expdate);
						$("#txtDeliveryType").val(value['sDeliveryType']);
						$("#txtTitle").val(value['title']);
						$("#txtIsbn").val(value['sISBN']);
						$("#txtAuthor").val(value['sAuthor']);
						$("#txtPlaceOfPublication").val(value['sPlaceOfPublication']);
						$("#txtPublisher").val(value['sPublisher']);
						$("#txtYearOfPublication").val(value['sYearOfPublication']);
						$("#txtEdition").val(value['sEdition']);
						$("#txtRequestorRemark").val(value['sRequestorRemark']);
						$("#txtVolume").val(value['sVolume']);
						$("#txtIssue").val(value['sIssue']);
						$("#txtPageNumber").val(value['sPageNumber']);
					});
				}
			});
	  		break;
	  	case state = 'View Request Control':
	   			disable();
			  	$.get('GetEditViewReqCtrl', {
		  			rowid : rowid
				 	}, function(responseJson) {
					if(responseJson != null){
						$.each(responseJson, function(key,value) {
							$(".lblRequestNo").text(value['sRequestNo']);
							
							var status = value['status']
							status = GetStatus(status);
							$(".lblStatus").text(status);
							
							$(".lblDateRequested").text(value['dateReq']);
							$("#txtPatron").val(value['requestor']);
							
							$.get('GetCheckLibExist', {
								code : value['requestor'],
							}, function(responseJson) {
								$.each(responseJson, function(key,value) {
									$(".lblPatronName").text(value['lblLibName']);
								});
							});
							
							$("#txtLibraryID").val(value['sLendingLibraryId']);
							$.get('GetCheckLibExist', {
								code : value['sLendingLibraryId'],
							}, function(responseJson) {
								$.each(responseJson, function(key,value) {
									$(".lblLibName").text(value['lblLibName']);
								});
							});
							
							$("#cboDocumentType").val(value['sDocumentType']);
							
							var expdate = value['sDateExpected'];
							if(expdate == "-"){
								expdate = "";
							}
							$("#dexpect").val(expdate);
							$("#txtDeliveryType").val(value['sDeliveryType']);
							$("#txtTitle").val(value['title']);
							$("#txtIsbn").val(value['sISBN']);
							$("#txtAuthor").val(value['sAuthor']);
							$("#txtPlaceOfPublication").val(value['sPlaceOfPublication']);
							$("#txtPublisher").val(value['sPublisher']);
							$("#txtYearOfPublication").val(value['sYearOfPublication']);
							$("#txtEdition").val(value['sEdition']);
							$("#txtRequestorRemark").val(value['sRequestorRemark']);
							$("#txtVolume").val(value['sVolume']);
							$("#txtIssue").val(value['sIssue']);
							$("#txtPageNumber").val(value['sPageNumber']);
						});
					}
				});
	  			
	  		break;
		}
	});
	//***************************************END AREA FOR MODAL *****************************************//
	
	
	function disable(){
		$("input").attr('disabled','disabled');
  		$("#save, #cancel, #previewbtn").hide();
  		$("#close").show();
  		$("#searchReqID, #searchLibID").attr('disabled', true);
  		$("textarea").prop('disabled', true);
  		$('#cboDocumentType').prop('disabled', 'disabled');
	}
	
	//////////////////////////////Enable///////////////////////////////////////
	function enable(){
		$("input").prop("disabled", false);
  		$("#save, #cancel").show();
  		$("#close").hide();
  		$("#searchReqID, #searchLibID").attr('disabled', false);
  		$("textarea").prop('disabled', false);
  		$('#cboDocumentType').prop('disabled', false);
  		$("#cboDocumentType").prop("selectedIndex",-1);
	}
	
	$("#cboDocumentType").change(function() {
		
		var value = $(this).val(); 
		
		if(value=='01' || value == '02'){
			$("#txtVolume, #txtIssue, #txtPageNumber").prop("disabled", true); 
		}else if(value=='03' || value == '04'){
			$("#txtVolume, #txtIssue, #txtPageNumber").prop("disabled", false); 
		}
		
	});
	
	//***************************************AREA FOR DELETE ********************************************//
	/*function messageBox(code, criteria, label){
		var url = "Error_Message?GL79ERRCODE="+code+"" + 
			"&criteria=" + criteria + "&label="+label+"";
		$.ajax({
				url: url,
				success: function(result) {
					//swal('',result, 'info' );
					swal(result);
				}
			});
	}*/
	
	$('#ReqCtrl_table tbody').on( 'click', '#Delete', function () {
		var deleteReqCtrl = $(this).attr('data-id');
		//alert(deleteFundAccountChar);

		var index = $('#ReqCtrl_table').DataTable().rows({ search: 'applied'})
		.nodes().to$().index($(this).closest('tr'));
		//alert(index);
		
		swal({
			text: "Are you sure want to delete?",
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
					url : "Handler_DeleteReqCtrl",
					data: { id: deleteReqCtrl,
							loginid : liferayLogin},
						success : function(result) {
							var status = result.replace(/\s+/g, '');
							if (status == "ok") {
								messageBox("005","Deleted","@action");
								/*swal('Successfully Deleted!',
									'The record has been successfully removed.');*/
								$('.swal2-confirm').click(
									function() {
										$('#ReqCtrl_table').DataTable().row(index).remove().draw();
										//location.reload();
								});
							}else{
	     						swal("Unsuccessfully");
	     					}
						}
					});
			}, function(dismiss) {
				if (dismiss == 'cancel') {
					swal('', 'Cancelled');
				}
		})
	});
	//***********************************END AREA FOR DELETE ********************************************//
	


});