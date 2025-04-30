$(document).ready(function() {
	
	$('#TableModuleAccessLevel').DataTable( {
		destroy: true,
		searching: false,
		bLengthChange: false,
	});
	
	///////////////////// Set Current Date  ///////////////////////////////////
	var today = new Date(); 
	var liferayLogin = $("#liferayLogin").val();
	$('input[type="text"]').attr('autocomplete', 'off');
	
	//MAX LENGTH
	$("#inputAccessLevel").attr('maxlength','2');
	
	//****************************************** AREA FOR MODAL *****************************************//
	$('#modalModuleAccessLevel').on('show.bs.modal', function (event) {
		var button = $(event.relatedTarget) // Button that triggered the modal
		var modal = $(this);
		var recipient = button.data('whatever'); // Extract info from data-* attributes
		var rowid = button.data('rowid');
		var rowid2 = button.data('rowid2');

		modal.find('.modal-title').text(recipient);
		
		switch(recipient){
	  	case state = 'Add New':
	  		enable();
	  		/*$('.daterecModuleAccessLevel').text(moment(today).format("DD/MM/YYYY"));
	  		$(".recbyModuleAccessLevel").text(liferayLogin);*/
	  		$("#inputGroupID, #inputModuleName").prop("selectedIndex",-1); 
	  		break;
	  	case state = 'Edit':
	  		enable();
	  		$('#checkboxGroupID, #checkboxModuleName, #checkboxModuleName').prop('disabled', 'disabled');
	  		$("#inputGroupID, #inputModuleName").prop("disabled", true); 
		  	$.get('GetEditViewModuleAccessLevel',{rowid : rowid, rowid2 : rowid2},  function(responseJson) {
				if(responseJson != null){
					$.each(responseJson, function(key,value) {
						$("#inputGroupID").val(value['GroupID']);
						$("#inputModuleName").val(value['ModuleCode']);
						$("#inputAccessLevel").val(value['AcessLevel']);
					});
				}
			});
	  		break;
	  	case state = 'View':
	  		disable();
	  		$.get('GetEditViewModuleAccessLevel',{rowid : rowid, rowid2 : rowid2},  function(responseJson) {
				if(responseJson != null){
					$.each(responseJson, function(key,value) {
						$("#inputGroupID").val(value['GroupID']);
						$("#inputModuleName").val(value['ModuleCode']);
						$("#inputAccessLevel").val(value['AcessLevel']);
					});
				}
			});
	  		break;
	  }
	});
	//***************************************END AREA FOR MODAL *****************************************//
	
	//***************************************************************************************************//
	//clear all input fields in bootstrap modal when close modal #modalAdd
	$('#modalModuleAccessLevel').on('hidden.bs.modal', function () {
	    $(this).find('form').trigger('reset');
	    $('#formdataModuleAccessLevel').data('bootstrapValidator').resetForm();
	});
	
	//clear all input fields in bootstrap modal when close modal #modalAdd
	$('#searchModuleAccessLevel').on('hidden.bs.modal', function () {
	    $(this).find('form').trigger('reset');
	});
	
	///open modal
	$('#searchModuleAccessLevel').on('shown.bs.modal', function () {
		$("input").prop("disabled", false);
		$("input[type=radio]").attr('disabled', false);
		
		$("#search").prop("disabled",true);
		$("#input-criteria").focus();

		$('#input-criteria').on('keyup',function(){

		      var input = $(this).val();

		      if(input.length >= 1){
		    	  $("#search").prop("disabled",false);
		      }else{
		    	  $("#search").prop("disabled",true);
		      }

		});
	});
	//***************************************************************************************************//
	
	//***************************************AREA FOR FUNCTION *****************************************//
	///// function enable at form 
	function enable(){
		$("input").prop("disabled", false);
		$("#inputGroupID, #inputModuleName").prop("disabled", false); 
  		$("#save, #cancel").show();
  		$("#close").hide();
  		$('#checkboxGroupID, #checkboxModuleName, #checkboxModuleName').prop('disabled', false);
	}
	
	////////////function disable at form 
	function disable(){
		$("input").attr('disabled','disabled');
		$("#inputGroupID, #inputModuleName").prop("disabled", true); 	
  		$("#save, #cancel").hide();
  		$("#close").show();
  		$('#checkboxGroupID, #checkboxModuleName, #checkboxModuleName').prop('disabled', 'disabled');
	}
	
	///input number only
	$("#inputAccessLevel").on("keypress keyup blur",function (event) {    
        $(this).val($(this).val().replace(/[^\d].+/, ""));
         if ((event.which < 48 || event.which > 57)) {
             event.preventDefault();
         }
     });
	//***********************************END AREA FOR FUNCTION *****************************************//
	
	//***************************************AREA FOR SEARCH *******************************************//
	$('#search').click(function(){
		$('#TableModuleAccessLevel').dataTable().fnClearTable();
		
		var input_criteria = $('#input-criteria').val();
		var search_type = $("input[name='searchSelection']:checked").val();
		
		$('#TableModuleAccessLevel').DataTable( {
			destroy: true,
			searching: false,
			bLengthChange: false,
			//info: false,
		    ajax: {
		    	url: "resultSearchModuleAccessLevel",
		        data : {input_criteria : input_criteria, search_type : search_type},
		        type: "GET",
		        dataSrc: function (json) {
		            var return_data = new Array();

		            for(var i=0;i< json.length; i++){
		            	///case
		            	var modulecode =  json[i].ModuleCode;
		            	var moduledesc;
		            	
		            	switch(modulecode){
		        	  	case state = 'GL':
		        	  		moduledesc = "FOUNDATION";
		        	  		break;
		        	  	case state = 'CI':
		        	  		moduledesc = "CIRCULATION";
		        	  		break;
		        	  	case state = 'CT':
		        	  		moduledesc = "CATALOGING";
		        	  		break;
		        	  	case state = 'AU':
		        	  		moduledesc = "AUTHORITY";
		        	  		break;
		        	  	case state = 'OP':
		        	  		moduledesc = "INFOTRACK";
		        	  		break;
		        	  	case state = 'AC':
		        	  		moduledesc = "ACQUISITIONS";
		        	  		break;
		        	  	case state = 'FA':
		        	  		moduledesc = "FUND ACCOUNTING";
		        	  		break;
		        	  	case state = 'LO':
		        	  		moduledesc = "OUTGOING REQUEST";
		        	  		break;
		        	  	case state = 'IR':
		        	  		moduledesc = "IRS";
		        	  		break;
		        	  	case state = 'RE':
		        	  		moduledesc = "RECEIPTING";
		        	  		break;
		        	  	case state = 'SE':
		        	  		moduledesc = "SERIALS";
		        	  		break;
		        	  	case state = 'PS':
		        	  		moduledesc = "PARASORD";
		        	  		break;
		        	  	case state = 'BI':
		        	  		moduledesc = "BINDING";
		        	  		break;
		        	  	case state = 'LI':
		        	  		moduledesc = "INCOMING REQUEST";
		        	  		break;
		        	  	case state = 'ST':
		        	  		moduledesc = "STOCK TAKE";
		        	  		break;
		        	  	case state = 'RT':
		        	  		moduledesc = "ROUTING";
		        	  		break;
		        	  	case state = 'RC':
		        	  		moduledesc = "RESERVE COLLECTION";
		        	  		break;
		        	  	case state = 'DD':
		        	  		moduledesc = "DOCUMENT DELIVERY";
		        	  		break;
		        	  	case state = 'MM':
		        	  		moduledesc = "MEMBERSHIP";
		        	  		break;
		        	  	case state = 'LL':
		        	  		moduledesc = "INTER LIBRARY LOAN";
		        	  		break;
		        	  }		            	
		              return_data.push({
		                'No': i+1,
		                'Group ID' : json[i].GroupID, 
		                'Module Code' : modulecode, 
		                'Module Name' : moduledesc,
		                'Access Level' : json[i].AcessLevel,
		                'Action' : '<button id="View" class="btn btn-primary btn-xs" data-toggle="modal" data-target="#modalModuleAccessLevel" data-whatever="View" title="View" data-rowid="'+json[i].GroupID+'" data-rowid2="'+modulecode+'"><span class="glyphicon glyphicon-eye-open"></span></button> <button id="Edit" class="btn btn-info btn-xs" data-toggle="modal" data-target="#modalModuleAccessLevel" data-whatever="Edit" title="Edit" data-rowid="'+json[i].GroupID+'" data-rowid2="'+modulecode+'"><span class="glyphicon glyphicon-pencil"></span></button> <button id="Delete" class="btn btn-dull btn-xs Delete" data-rowid="'+json[i].GroupID+'" data-rowid2="'+modulecode+'"><span class="glyphicon glyphicon-trash" title="Delete" ></span></button>',
		              })
		            }
		            return return_data;
		          },
		     },//This is the end of the AJAX object from the example above
		     	columns    : [
					{'data': 'No'},
					{'data': 'Group ID', className: "text-center"},
					{'data': 'Module Code', className: "text-center"},
					{'data': 'Module Name'},
					{'data': 'Access Level', className: "text-right"},
					{'data': 'Action'},
				],
		});

		$("#closesearchModuleAccessLevel").click();  

	});
	//***********************************END AREA FOR SEARCH *******************************************//
	
	//************************************ DELETE ******************************************************//
	$('#TableModuleAccessLevel tbody').on( 'click', '#Delete', function () {
		var deleteAccess = $(this).attr('data-rowid');
		var deleteAccess2 = $(this).attr('data-rowid2');
		//alert(deleteFundAccountChar);

		var index = $('#TableModuleAccessLevel').DataTable().rows({ search: 'applied'})
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
					url : "Handler_DeleteAccessLEvel",
					data: { d1: deleteAccess, d2: deleteAccess2},
						success : function(result) {
							var status = result.replace(/\s+/g, '');
							if (status == "ok") {
								
								//messageBox("005","Deleted","@action");
								swal('Successfully Deleted');
								$('.swal2-confirm').click(
									function() {
										$('#TableModuleAccessLevel').DataTable().row(index).remove().draw();
										//location.reload();
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

});