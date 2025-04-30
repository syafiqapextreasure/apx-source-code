$(document).ready(function() {

	///////////////////// Set Current Date  ///////////////////////////////////
	var today = new Date(); 
	var liferayLogin = $("#liferayLogin").val();
	$('input[type="text"]').attr('autocomplete', 'off');
	
	//MAX LENGTH
	$("#inputModuleName, #inputAccessLevel").attr('maxlength','2');
	$("#inputMenuIndex").attr('maxlength','3');
	$("#inputMenuDescription").attr('maxlength','40');
	$("#inputProgramID").attr('maxlength','12');
	
	///input number only
	$("#inputMenuIndex").on("keypress keyup blur",function (event) {    
        $(this).val($(this).val().replace(/[^\d].+/, ""));
         if ((event.which < 48 || event.which > 57)) {
             event.preventDefault();
         }
     });
	
	////LOAD TABLE 
	$('#MenuTable').DataTable( {
		destroy: true,
		searching: false,
		bLengthChange: false,
		autoWidth: false,
		//info: false,
	    ajax: {
	        url: "LoadTableMenu",
	        type: "GET",
	        dataSrc: function (json) {
	            var return_data = new Array();

	            for(var i=0;i< json.length; i++){
	              return_data.push({
	                'Module': json[i].Module,
	                'Root' : json[i].Root, 
	                'Index' : json[i].Index,
	                'Description': json[i].Description,
	                'Program ID' : json[i].ProgramID, 
	                'Access Level' : json[i].AccessLevel,
	                'Action' : '<button id="View" class="btn btn-primary btn-xs" data-toggle="modal" data-target="#modalMenu" data-whatever="View" title="View Menu" data-rowid="'+json[i].Module+'" data-rowid2="'+json[i].Root+'" data-rowid3="'+json[i].Index+'"><span class="glyphicon glyphicon-eye-open"></span></button> <button id="Edit" class="btn btn-info btn-xs" data-toggle="modal" data-target="#modalMenu" data-whatever="Edit" title="Edit Menu" data-rowid="'+json[i].Module+'" data-rowid2="'+json[i].Root+'" data-rowid3="'+json[i].Index+'"><span class="glyphicon glyphicon-pencil"></span></button> <button id="Delete" class="btn btn-dull btn-xs Delete" data-rowid="'+json[i].Module+'" data-rowid2="'+json[i].Root+'" data-rowid3="'+json[i].Index+'"><span class="glyphicon glyphicon-trash" title="Delete" ></span></button>',
	              })
	            }
	            return return_data;
	          },
	     },//This is the end of the AJAX object from the example above
	     	columns    : [
				{'data': 'Module'},
				{'data': 'Root'},
				{'data': 'Index'},
				{'data': 'Description'},
				{'data': 'Program ID'},
				{'data': 'Access Level', className: "text-center"},
				{'data': 'Action'},
			],
	});
	
	//****************************************** AREA FOR MODAL *****************************************//
	$('#modalMenu').on('show.bs.modal', function (event) {
		var button = $(event.relatedTarget) // Button that triggered the modal
		var modal = $(this);
		var recipient = button.data('whatever'); // Extract info from data-* attributes
		var rowid = button.data('rowid');
		var rowid2 = button.data('rowid2');
		var rowid3 = button.data('rowid3');

		modal.find('.modal-title').text(recipient);

		switch(recipient){
	  	case state = 'Add':
	  		enable();
	  		$("#inputModuleName").focus();
	  		$("#RootName").prop("selectedIndex",-1); 
	  		/*$('.daterec').text(moment(today).format("DD/MM/YYYY"));
	  		$(".recby").text(liferayLogin);	*/	
	  		break;
	  	case state = 'Edit':
	  		enable();
	  		$("#inputModuleName").prop("disabled", true);
		  	$.get('GetEditViewMenu', {
	  			rowid : rowid,
	  			rowid2 : rowid2,
	  			rowid3 : rowid3,
			}, function(responseJson) {
				if(responseJson != null){
					$.each(responseJson, function(key,value) {	
						$("#inputModuleName").val(value['Module']);
						$("#RootName").val(value['Root']);
						$("#inputMenuIndex").val(value['Index']);
						$("#inputMenuDescription").val(value['Description']);
						$("#inputProgramID").val(value['ProgramID']);
						$("#inputAccessLevel").val(value['AccessLevel']);
						
						$("#hidemodule").val(value['Module']);
						$("#hiderootname").val(value['Root']);
						$("#hideindex").val(value['Index']);

						/*$(".daterec").text(value['daterec']);
						$(".recby").text(value['recby']);	*/					
					});
				}
			});
	  		break;
	  	case state = 'View':
	  		disable();
		  	$.get('GetEditViewMenu', {
	  			rowid : rowid,
	  			rowid2 : rowid2,
	  			rowid3 : rowid3,
			}, function(responseJson) {
				if(responseJson != null){
					$.each(responseJson, function(key,value) {	
						$("#inputModuleName").val(value['Module']);
						$("#RootName").val(value['Root']);
						$("#inputMenuIndex").val(value['Index']);
						$("#inputMenuDescription").val(value['Description']);
						$("#inputProgramID").val(value['ProgramID']);
						$("#inputAccessLevel").val(value['AccessLevel']);
						/*$(".daterec").text(value['daterec']);
						$(".recby").text(value['recby']);	*/					
					});
				}
			});
	  		break;
	  }
	});
	//***************************************END AREA FOR MODAL *****************************************//
	
	//***************************************************************************************************//
	//clear all input fields in bootstrap modal when close modal #modalAdd
	$('#modalMenu').on('hidden.bs.modal', function () {
	    $(this).find('form').trigger('reset');
	    $('#formdataMenu').data('bootstrapValidator').resetForm();
	});
	
	//clear all input fields in bootstrap modal when close modal #modalAdd
	$('#searchMenu').on('hidden.bs.modal', function () {
	    $(this).find('form').trigger('reset');
	});
	
	///open modal
	$('#searchMenu').on('shown.bs.modal', function () {
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
  		$("#RootName").prop("disabled", false); 
	}
	
	////////////function disable at form 
	function disable(){
		$("input").attr('disabled','disabled');
  		$("#save, #cancel").hide();
  		$("#close").show();
  		$("#RootName").prop("disabled", true); 
	}
	
	////when root onchange
	$("#RootName").change(function(){

        var getModule = $("#inputModuleName").val();
        var getRoot = $("#RootName option:selected").val();  

        $.get('getTotalIndex', {
        	getModule : getModule,
        	getRoot : getRoot
        	}, function(responseJson) {
    			if(responseJson != null){
    				$.each(responseJson, function(key,value) {
    					
    					var indexval = value['Index'];
    					
    					if(indexval == " "){
    						indexval = "0";
    					}
    					var convertIndex = parseInt(indexval);
    					var finalIndex = convertIndex + 1;
    					
    					$("#inputMenuIndex").val(finalIndex);
    				});
    			}
    			
        });
	});
	
	////when menu index onchange
	$("#inputMenuIndex").change(function(){

        var getMenu= $("#inputMenuIndex").val();
        var getModule = $("#inputModuleName").val();
        var getRoot = $("#RootName option:selected").val();  

        $.get('CheckMenurecord', {
        	getModule : getModule,
        	getRoot : getRoot,
        	getMenu : getMenu
        	}, function(responseJson) {
    			if(responseJson != null){
    				$.each(responseJson, function(key,value) {
    					
    					var exsit = value['Index']; 
    					if(exsit >= 1){
    						swal("Record already exists");
    						$("#inputModuleName, #inputMenuIndex").val("");
    						$("#RootName").prop("selectedIndex",-1); 
    						$('#formdataMenu').data('bootstrapValidator').resetForm();
    					}
    				});
    			}
    			
        });
	});
	
	//***********************************END AREA FOR FUNCTION *****************************************//
	
	//************************************ DELETE ******************************************************//
	$('#MenuTable tbody').on( 'click', '#Delete', function () {
		var module = $(this).attr('data-rowid');
		var name = $(this).attr('data-rowid2');
		var idx = $(this).attr('data-rowid3');
		//alert(module);

		var index = $('#MenuTable').DataTable().rows({ search: 'applied'})
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
					url : "Handler_DeleteMenu",
					data: { module: module,
							name : name,
							idx : idx
					},
						success : function(result) {
							var status = result.replace(/\s+/g, '');
							if (status == "ok") {
								swal("Successfully Deleted");
								//messageBox("005","Deleted","@action");
								/*swal('Successfully Deleted!',
									'The record has been successfully removed.');*/
								$('.swal2-confirm').click(
									function() {
										$('#MenuTable').DataTable().row(index).remove().draw();
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
		$('#MenuTable').dataTable().fnClearTable();
		
		var input_criteria = $('#input-criteria').val();
		var search_type = $("input[name='searchSelection']:checked").val();
		
		$('#MenuTable').DataTable( {
			destroy: true,
			searching: false,
			bLengthChange: false,
			autoWidth: false,
			//info: false,
		    ajax: {
		    	url: "resultSearchMenu",
		        data : {input_criteria : input_criteria, search_type : search_type},
		        type: "GET",
		        dataSrc: function (json) {
		            var return_data = new Array();

		            for(var i=0;i< json.length; i++){
		            	return_data.push({
			                'Module': json[i].Module,
			                'Root' : json[i].Root, 
			                'Index' : json[i].Index,
			                'Description': json[i].Description,
			                'Program ID' : json[i].ProgramID, 
			                'Access Level' : json[i].AccessLevel,
			                'Action' : '<button id="View" class="btn btn-primary btn-xs" data-toggle="modal" data-target="#modalMenu" data-whatever="View" title="View Menu" data-rowid="'+json[i].Module+'" data-rowid2="'+json[i].Root+'" data-rowid3="'+json[i].Index+'"><span class="glyphicon glyphicon-eye-open"></span></button> <button id="Edit" class="btn btn-info btn-xs" data-toggle="modal" data-target="#modalMenu" data-whatever="Edit" title="Edit Menu" data-rowid="'+json[i].Module+'" data-rowid2="'+json[i].Root+'" data-rowid3="'+json[i].Index+'"><span class="glyphicon glyphicon-pencil"></span></button> <button id="Delete" class="btn btn-dull btn-xs Delete" data-rowid="'+json[i].Module+'" data-rowid2="'+json[i].Root+'" data-rowid3="'+json[i].Index+'"><span class="glyphicon glyphicon-trash" title="Delete" ></span></button>',
			            })
		            }
		            return return_data;
		          },
		     },//This is the end of the AJAX object from the example above
		     	columns    : [
					{'data': 'Module'},
					{'data': 'Root'},
					{'data': 'Index'},
					{'data': 'Description'},
					{'data': 'Program ID'},
					{'data': 'Access Level', className: "text-center"},
					{'data': 'Action'},
				],
		});

		$("#closesearchMenu").click();  

	});
	//***********************************END AREA FOR SEARCH *******************************************//
	
	
});