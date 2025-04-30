$(document).ready(function() {

	///////////////////// Set Current Date  ///////////////////////////////////
	var today = new Date(); 
	var liferayLogin = $("#liferayLogin").val();
	$('input[type="text"]').attr('autocomplete', 'off');
	
	//MAX LENGTH
	$("#inputgroupId").attr('maxlength','2');
	$("#inputGroupName").attr('maxlength','40');
	$("#inputAccessLevel").attr('maxlength','4');
	
	///input number only
	$("#inputAccessLevel").on("keypress keyup blur",function (event) {    
        $(this).val($(this).val().replace(/[^\d].+/, ""));
         if ((event.which < 48 || event.which > 57)) {
             event.preventDefault();
         }
     });
	
	////LOAD TABLE 
	$('#groupIdTable').DataTable( {
		destroy: true,
		searching: false,
		bLengthChange: false,
		autoWidth: false,
		//info: false,
	    ajax: {
	        url: "LoadTableGroupId",
	        type: "GET",
	        dataSrc: function (json) {
	            var return_data = new Array();

	            for(var i=0;i< json.length; i++){
	              return_data.push({
	                'Group Id': json[i].groupid,
	                'Group Name' : json[i].groupname, 
	                'Access Level' : json[i].accesslevel,
	                'Action' : '<button id="View" class="btn btn-primary btn-xs" data-toggle="modal" data-target="#modalgroupId" data-whatever="View" title="View Group Id" data-rowid="'+json[i].groupid+'"><span class="glyphicon glyphicon-eye-open"></span></button> <button id="Edit" class="btn btn-info btn-xs" data-toggle="modal" data-target="#modalgroupId" data-whatever="Edit" title="Edit Group Id" data-rowid="'+json[i].groupid+'"><span class="glyphicon glyphicon-pencil"></span></button> <button id="Delete" class="btn btn-dull btn-xs Delete" data-id="'+json[i].groupid+'"><span class="glyphicon glyphicon-trash" title="Delete" ></span></button>',
	              })
	            }
	            return return_data;
	          },
	     },//This is the end of the AJAX object from the example above
	     	columns    : [
				{'data': 'Group Id'},
				{'data': 'Group Name'},
				{'data': 'Access Level'},
				{'data': 'Action'},
			],
	});
	
	//****************************************** AREA FOR MODAL *****************************************//
	$('#modalgroupId').on('show.bs.modal', function (event) {
		var button = $(event.relatedTarget) // Button that triggered the modal
		var modal = $(this);
		var recipient = button.data('whatever'); // Extract info from data-* attributes
		var rowid = button.data('rowid');

		modal.find('.modal-title').text(recipient);

		switch(recipient){
	  	case state = 'Add':
	  		enable();
	  		$("#inputgroupId").focus();
	  		/*$('.daterec').text(moment(today).format("DD/MM/YYYY"));
	  		$(".recby").text(liferayLogin);	*/	
	  		break;
	  	case state = 'Edit':
	  		enable();
	  		$("#inputgroupId").prop("disabled", true);
	  		$.get('GetEditViewGroupId', {
	  			rowid : rowid
			 	}, function(responseJson) {
				if(responseJson != null){
					$.each(responseJson, function(key,value) {	
						$("#inputgroupId").val(value['groupid']);
						$("#inputGroupName").val(value['groupname']);
						$("#inputAccessLevel").val(value['accesslevel']);
						/*$(".daterec").text(value['daterec']);
						$(".recby").text(value['recby']);*/						
					});
				}
			});
	  		break;
	  	case state = 'View':
	  		disable();
		  	$.get('GetEditViewGroupId', {
	  			rowid : rowid
			 	}, function(responseJson) {
				if(responseJson != null){
					$.each(responseJson, function(key,value) {	
						$("#inputgroupId").val(value['groupid']);
						$("#inputGroupName").val(value['groupname']);
						$("#inputAccessLevel").val(value['accesslevel']);
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
	$('#modalgroupId').on('hidden.bs.modal', function () {
	    $(this).find('form').trigger('reset');
	    $('#formdatagroupId').data('bootstrapValidator').resetForm();
	});
	
	//clear all input fields in bootstrap modal when close modal #modalAdd
	$('#searchgroupId').on('hidden.bs.modal', function () {
	    $(this).find('form').trigger('reset');
	});
	
	///open modal
	$('#searchgroupId').on('shown.bs.modal', function () {
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
	$('#groupIdTable tbody').on( 'click', '#Delete', function () {
		var deleteGroupID = $(this).attr('data-id');
		//alert(deleteGroupID);

		var index = $('#groupIdTable').DataTable().rows({ search: 'applied'})
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
					url : "Handler_DeleteGroupID",
					data: { code: deleteGroupID},
						success : function(result) {
							var status = result.replace(/\s+/g, '');
							if (status == "ok") {
								swal("Successfully Deleted");
								//messageBox("005","Deleted","@action");
								/*swal('Successfully Deleted!',
									'The record has been successfully removed.');*/
								$('.swal2-confirm').click(
									function() {
										$('#groupIdTable').DataTable().row(index).remove().draw();
										//location.reload();
								});
							}else {
								//messageBox("131","","@action");
								swal("This record cannot be deleted");
								/*swal(
									'Not Deleted','This record cannot be deleted.');*/
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
		$('#groupIdTable').dataTable().fnClearTable();
		
		var input_criteria = $('#input-criteria').val();
		var search_type = $("input[name='searchSelection']:checked").val();
		
		$('#groupIdTable').DataTable( {
			destroy: true,
			searching: false,
			bLengthChange: false,
			autoWidth: false,
			//info: false,
		    ajax: {
		    	url: "resultSearchGroupID",
		        data : {input_criteria : input_criteria, search_type : search_type},
		        type: "GET",
		        dataSrc: function (json) {
		            var return_data = new Array();

		            for(var i=0;i< json.length; i++){
		            	return_data.push({
			                'Group Id': json[i].groupid,
			                'Group Name' : json[i].groupname, 
			                'Access Level' : json[i].accesslevel,
			                'Action' : '<button id="View" class="btn btn-primary btn-xs" data-toggle="modal" data-target="#modalgroupId" data-whatever="View" title="View Group Id" data-rowid="'+json[i].groupid+'"><span class="glyphicon glyphicon-eye-open"></span></button> <button id="Edit" class="btn btn-info btn-xs" data-toggle="modal" data-target="#modalgroupId" data-whatever="Edit" title="Edit Group Id" data-rowid="'+json[i].groupid+'"><span class="glyphicon glyphicon-pencil"></span></button> <button id="Delete" class="btn btn-dull btn-xs Delete" data-id="'+json[i].groupid+'"><span class="glyphicon glyphicon-trash" title="Delete" ></span></button>',
			              })
		            }
		            return return_data;
		          },
		     },//This is the end of the AJAX object from the example above
		     	columns    : [
					{'data': 'Group Id'},
					{'data': 'Group Name'},
					{'data': 'Access Level'},
					{'data': 'Action'},
				],
		});

		$("#closesearchgroupId").click();  

	});
	//***********************************END AREA FOR SEARCH *******************************************//
	
	
});