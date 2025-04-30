$(document).ready(function() {
	
	///////////////////// Set Current Date  ///////////////////////////////////
	var today = new Date(); 
	var liferayLogin = $("#liferayLogin").val();
	$('input[type="text"]').attr('autocomplete', 'off');
	
	//MAX LENGTH
	$("#inputfeddcode").attr('maxlength','2');
	$("inputDescription").attr('maxlength','40');
	
	////LOAD TABLE 
	$('#VendorFeedbackTable').DataTable( {
		destroy: true,
		searching: false,
		bLengthChange: false,
		autoWidth: false,
		//info: false,
	    ajax: {
	        url: "LoadTableVendorFeedback",
	        type: "GET",
	        dataSrc: function (json) {
	            var return_data = new Array();

	            for(var i=0;i< json.length; i++){
	              return_data.push({
	                'Code': json[i].Code,
	                'Description' : json[i].Description, 
	                'Notify Requestor' : json[i].NotifyRequestor,
	                'OPAC Display' : json[i].OPACDisplay,
	                'Action' : '<button id="View" class="btn btn-primary btn-xs" data-toggle="modal" data-target="#modalVendorFeedback" data-whatever="View" title="View" data-rowid="'+json[i].Code+'"><span class="glyphicon glyphicon-eye-open"></span></button> <button id="Edit" class="btn btn-info btn-xs" data-toggle="modal" data-target="#modalVendorFeedback" data-whatever="Edit" title="Edit" data-rowid="'+json[i].Code+'"><span class="glyphicon glyphicon-pencil"></span></button> <button id="Delete" class="btn btn-dull btn-xs Delete" data-id="'+json[i].Code+'"><span class="glyphicon glyphicon-trash" title="Delete" ></span></button>',
	              })
	            }
	            return return_data;
	          },
	     },//This is the end of the AJAX object from the example above
	     	columns    : [
				{'data': 'Code'},
				{'data': 'Description'},
				{'data': 'Notify Requestor'},
				{'data': 'OPAC Display'},
				{'data': 'Action'},
			],			
	});
	
	//****************************************** AREA FOR MODAL *****************************************//
	$('#modalVendorFeedback').on('show.bs.modal', function (event) {
		var button = $(event.relatedTarget) // Button that triggered the modal
		var modal = $(this);
		var recipient = button.data('whatever'); // Extract info from data-* attributes
		var rowid = button.data('rowid');

		modal.find('.modal-title').text(recipient);

		switch(recipient){
	  	case state = 'Add':
	  		enable();
	  		$("#inputfeddcode").focus();
	  		/*$('.daterec').text(moment(today).format("DD/MM/YYYY"));
	  		$(".recby").text(liferayLogin);	*/	
	  		break;
	  	case state = 'Edit':
	  		enable();
	  		$("#inputfeddcode").prop("disabled", true);
	  		$.get('GetEditViewVendorFeedback', {
	  			rowid : rowid
			 	}, function(responseJson) {
				if(responseJson != null){
					$.each(responseJson, function(key,value) {	
						$("#inputfeddcode").val(value['Code']);
						$("#inputDescription").val(value['Description']);
						
						var Notify = value['NotifyRequestor'];
						var Display = value['OPACDisplay'];
						
						switch(Notify){
				  			case state = 'Y':
				  				$(".inputNotify").prop('checked', true);
				  			break;
						}
						
						switch(Display){
				  			case state = 'Y':
				  				$(".inputDisplay").prop('checked', true);
				  			break;
						}
						/*$(".daterec").text(value['daterec']);
						$(".recby").text(value['recby']);*/						
					});
				}
			});
	  		break;
	  	case state = 'View':
	  		disable();
		  	$.get('GetEditViewVendorFeedback', {
	  			rowid : rowid
			 	}, function(responseJson) {
				if(responseJson != null){
					$.each(responseJson, function(key,value) {	
						$("#inputfeddcode").val(value['Code']);
						$("#inputDescription").val(value['Description']);
						
						var Notify = value['NotifyRequestor'];
						var Display = value['OPACDisplay'];
						
						switch(Notify){
				  			case state = 'Y':
				  				$(".inputNotify").prop('checked', true);
				  			break;
						}
						
						switch(Display){
				  			case state = 'Y':
				  				$(".inputDisplay").prop('checked', true);
				  			break;
						}
						/*$(".daterec").text(value['daterec']);
						$(".recby").text(value['recby']);*/						
					});
				}
			});
	  		break;
	  }
	});
	//***************************************END AREA FOR MODAL *****************************************//
	
	//***************************************************************************************************//
	//clear all input fields in bootstrap modal when close modal #modalAdd
	$('#modalVendorFeedback').on('hidden.bs.modal', function () {
	    $(this).find('form').trigger('reset');
	    $('#formdataVendorFeedback').data('bootstrapValidator').resetForm();
	});
	
	//clear all input fields in bootstrap modal when close modal #modalAdd
	$('#searchVendorFeedback').on('hidden.bs.modal', function () {
	    $(this).find('form').trigger('reset');
	});
	
	///open modal
	$('#searchVendorFeedback').on('shown.bs.modal', function () {
		$("input").prop("disabled", false);
		$("input[type=radio]").attr('disabled', false);
		$("#input-criteria").focus();
	});
	//***************************************************************************************************//
	
	//***************************************AREA FOR FUNCTION *****************************************//
	///// function enable at form 
	function enable(){
		$("input").prop("disabled", false);
  		$("#save, #cancel").show();
  		$("#close").hide();
  		$(".inputcheckbox").prop("disabled", false);
	}
	
	////////////function disable at form 
	function disable(){
		$("input").attr('disabled','disabled');
  		$("#save, #cancel").hide();
  		$("#close").show();
  		$(".inputcheckbox").prop("disabled", false);
	}
	//***********************************END AREA FOR FUNCTION *****************************************//
	
	//************************************ DELETE ******************************************************//
	$('#VendorFeedbackTable tbody').on( 'click', '#Delete', function () {
		var deleteVendorFeedback = $(this).attr('data-id');
		//alert(deleteVendorFeedback);

		var index = $('#VendorFeedbackTable').DataTable().rows({ search: 'applied'})
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
					url : "Handler_DeleteVendorFeedback",
					data: { code: deleteVendorFeedback},
						success : function(result) {
							var status = result.replace(/\s+/g, '');
							if (status == "ok") {
								//messageBox("005","Deleted","@action");
								swal('Successfully Deleted');
								/*swal('Successfully Deleted!',
									'The record has been successfully removed.');*/
								$('.swal2-confirm').click(
									function() {
										$('#VendorFeedbackTable').DataTable().row(index).remove().draw();
								});
							}else {
								//messageBox("131","","@action");
								swal('Not Deleted');
							}
						}
					});
			}, function(dismiss) {
				if (dismiss == 'cancel') {
					swal('', 'Cancelled');
				}
		})
	});
	//********************************** END DELETE ****************************************************//
	
	//***************************************AREA FOR SEARCH *******************************************//
	$('#search').click(function(){
		$('#VendorFeedbackTable').dataTable().fnClearTable();
		
		var input_criteria = $('#input-criteria').val();
		var search_type = $("input[name='searchSelection']:checked").val();
		
		$('#VendorFeedbackTable').DataTable( {
			destroy: true,
			searching: false,
			bLengthChange: false,
			autoWidth: false,
			//info: false,
		    ajax: {
		    	url: "resultSearchVendorFeedback",
		        data : {input_criteria : input_criteria, search_type : search_type},
		        type: "GET",
		        dataSrc: function (json) {
		            var return_data = new Array();

		            for(var i=0;i< json.length; i++){
		            	return_data.push({
			                'Code': json[i].Code,
			                'Description' : json[i].Description, 
			                'Notify Requestor' : json[i].NotifyRequestor,
			                'OPAC Display' : json[i].OPACDisplay,
			                'Action' : '<button id="View" class="btn btn-primary btn-xs" data-toggle="modal" data-target="#modalVendorFeedback" data-whatever="View" title="View" data-rowid="'+json[i].Code+'"><span class="glyphicon glyphicon-eye-open"></span></button> <button id="Edit" class="btn btn-info btn-xs" data-toggle="modal" data-target="#modalVendorFeedback" data-whatever="Edit" title="Edit" data-rowid="'+json[i].Code+'"><span class="glyphicon glyphicon-pencil"></span></button> <button id="Delete" class="btn btn-dull btn-xs Delete" data-id="'+json[i].Code+'"><span class="glyphicon glyphicon-trash" title="Delete" ></span></button>',
			              })
		            }
		            return return_data;
		          },
		     },//This is the end of the AJAX object from the example above
		     	columns    : [
		     	              {'data': 'Code'},
		     	              {'data': 'Description'},
		     	              {'data': 'Notify Requestor'},
		     	              {'data': 'OPAC Display'},
		     	              {'data': 'Action'},
				],
		});

		$("#closesearchVendorFeedback").click();  

	});
	//***********************************END AREA FOR SEARCH *******************************************//
});