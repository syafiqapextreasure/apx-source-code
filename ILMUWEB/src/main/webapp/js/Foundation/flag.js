$(document).ready(function() {
	
	///////////////////// Set Current Date  ///////////////////////////////////
	var today = new Date(); 
	var liferayLogin = $("#liferayLogin").val();
	$('input[type="text"]').attr('autocomplete', 'off');
	
	//MAX LENGTH
	$("#inputFunction").attr('maxlength','15');
	$("#inputDescription").attr('maxlength','100');
	$("#inputValue").attr('maxlength','50');
	
	////LOAD TABLE 
	$('#flagTable').DataTable( {
		destroy: true,
		searching: false,
		bLengthChange: false,
		autoWidth: false,
		//info: false,
	    ajax: {
	        url: "LoadTableFlag",
	        type: "GET",
	        dataSrc: function (json) {
	            var return_data = new Array();

	            for(var i=0;i< json.length; i++){
	              return_data.push({
	                'Function': json[i].Function,
	                'Description' : json[i].Description, 
	                'Value' : json[i].Value,
	                'Action' : '<button id="View" class="btn btn-primary btn-xs" data-toggle="modal" data-target="#modalFlag" data-whatever="View" title="View" data-rowid="'+json[i].Function+'"><span class="glyphicon glyphicon-eye-open"></span></button> <button id="Edit" class="btn btn-info btn-xs" data-toggle="modal" data-target="#modalFlag" data-whatever="Edit" title="Edit" data-rowid="'+json[i].Function+'"><span class="glyphicon glyphicon-pencil"></span></button> <button id="Delete" class="btn btn-dull btn-xs Delete" data-id="'+json[i].Function+'"><span class="glyphicon glyphicon-trash" title="Delete" ></span></button>',
	              })
	            }
	            return return_data;
	          },
	     },//This is the end of the AJAX object from the example above
	     	columns    : [
				{'data': 'Function'},
				{'data': 'Description'},
				{'data': 'Value'},
				{'data': 'Action'},
			],			
	});
	
	//****************************************** AREA FOR MODAL *****************************************//
	$('#modalFlag').on('show.bs.modal', function (event) {
		var button = $(event.relatedTarget) // Button that triggered the modal
		var modal = $(this);
		var recipient = button.data('whatever'); // Extract info from data-* attributes
		var rowid = button.data('rowid');

		modal.find('.modal-title').text(recipient);

		switch(recipient){
	  	case state = 'Add':
	  		enable();
	  		$("#inputFunction").focus();	
	  		break;
	  	case state = 'Edit':
	  		enable();
	  		$("#inputFunction").prop("disabled", true);
	  		$.get('GetEditViewFlag', {
	  			rowid : rowid
			 	}, function(responseJson) {
				if(responseJson != null){
					$.each(responseJson, function(key,value) {	
						$.each(responseJson, function(key,value) {	
							$("#inputFunction").val(value['Function']);
							$("#inputDescription").val(value['Description']);
							$("#inputValue").val(value['Value']);
						});					
					});
				}
			});
	  		break;
	  	case state = 'View':
	  		disable();
		  	$.get('GetEditViewFlag', {
	  			rowid : rowid
			 	}, function(responseJson) {
				if(responseJson != null){
					$.each(responseJson, function(key,value) {	
						$("#inputFunction").val(value['Function']);
						$("#inputDescription").val(value['Description']);
						$("#inputValue").val(value['Value']);
					});
				}
			});
	  		break;
	  }
	});
	//***************************************END AREA FOR MODAL *****************************************//
	
	//***************************************************************************************************//
	//clear all input fields in bootstrap modal when close modal #modalAdd
	$('#modalFlag').on('hidden.bs.modal', function () {
	    $(this).find('form').trigger('reset');
	    $('#formdataFlag').data('bootstrapValidator').resetForm();
	});
	
	//clear all input fields in bootstrap modal when close modal #modalAdd
	$('#searchFlag').on('hidden.bs.modal', function () {
	    $(this).find('form').trigger('reset');
	});
	
	///open modal
	$('#searchFlag').on('shown.bs.modal', function () {
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
	}
	
	////////////function disable at form 
	function disable(){
		$("input").attr('disabled','disabled');
  		$("#save, #cancel").hide();
  		$("#close").show();
	}
	//***********************************END AREA FOR FUNCTION *****************************************//
	
	//************************************ DELETE ******************************************************//
	$('#flagTable tbody').on( 'click', '#Delete', function () {
		var deleteFlag = $(this).attr('data-id');
		//alert(deleteFlag);

		var index = $('#flagTable').DataTable().rows({ search: 'applied'})
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
					url : "Handler_DeleteFlag",
					data: { code: deleteFlag},
						success : function(result) {
							var status = result.replace(/\s+/g, '');
							if (status == "ok") {
								//messageBox("005","Deleted","@action");
								swal('Successfully Deleted');
								/*swal('Successfully Deleted!',
									'The record has been successfully removed.');*/
								$('.swal2-confirm').click(
									function() {
										$('#flagTable').DataTable().row(index).remove().draw();
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
		$('#flagTable').dataTable().fnClearTable();
		
		var input_criteria = $('#input-criteria').val();
		var search_type = $("input[name='searchSelection']:checked").val();
		
		$('#flagTable').DataTable( {
			destroy: true,
			searching: false,
			bLengthChange: false,
			autoWidth: false,
			//info: false,
		    ajax: {
		    	url: "resultSearchFlag",
		        data : {input_criteria : input_criteria, search_type : search_type},
		        type: "GET",
		        dataSrc: function (json) {
		            var return_data = new Array();

		            for(var i=0;i< json.length; i++){
		            	return_data.push({
			                'Function': json[i].Function,
			                'Description' : json[i].Description, 
			                'Value' : json[i].Value,
			                'Action' : '<button id="View" class="btn btn-primary btn-xs" data-toggle="modal" data-target="#modalFlag" data-whatever="View" title="View" data-rowid="'+json[i].Function+'"><span class="glyphicon glyphicon-eye-open"></span></button> <button id="Edit" class="btn btn-info btn-xs" data-toggle="modal" data-target="#modalFlag" data-whatever="Edit" title="Edit" data-rowid="'+json[i].Function+'"><span class="glyphicon glyphicon-pencil"></span></button> <button id="Delete" class="btn btn-dull btn-xs Delete" data-id="'+json[i].Function+'"><span class="glyphicon glyphicon-trash" title="Delete" ></span></button>',
			           })
		            }
		            return return_data;
		          },
		     },//This is the end of the AJAX object from the example above
		     	columns    : [
						{'data': 'Function'},
						{'data': 'Description'},
						{'data': 'Value'},
						{'data': 'Action'},
				],
		});

		$("#closesearchFlag").click();  

	});
	//***********************************END AREA FOR SEARCH *******************************************//
});