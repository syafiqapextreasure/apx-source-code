$(document).ready(function() {
	
	var today = new Date(); 
	var liferayLogin = $("#liferayLogin").val();
	$('input[type="text"]').attr('autocomplete', 'off');
	
	//MAX LENGTH
	$("#input-patrAttri").attr('maxlength','5');
	$("#input-desc").attr('maxlength','30');
	
	
	/////table for patron miscellaneous attributes
	$('#miscellaneous').DataTable( {
		destroy: true,
		searching: false,
		bLengthChange: false,
		//info: false,
	    ajax: {
	        url: "GetAllMiscellaneous",
	        type: "GET",
	        dataSrc: function (json) {
	            var return_data = new Array();

	            for(var i=0;i< json.length; i++){
	              return_data.push({
	                'Attribute': json[i].code,
	                'Description' : json[i].description, 
	                'Action' :  '<button id="View" class="btn btn-primary btn-xs" data-toggle="modal" data-target="#modalmiscellaneous" data-whatever="View" title="View" data-rowid="'+json[i].code+'"><span class="glyphicon glyphicon-eye-open"></span></button> <button id="Edit" class="btn btn-info btn-xs" data-toggle="modal" data-target="#modalmiscellaneous" data-whatever="Edit" title="Edit" data-rowid="'+json[i].code+'"><span class="glyphicon glyphicon-pencil"></span></button> <button id="Delete" class="btn btn-dull btn-xs DeleteOrder" data-id="'+json[i].code+'"><span class="glyphicon glyphicon-trash" title="Delete" ></span></button>',
	              })
	            }
	            return return_data;
	          },
	     },//This is the end of the AJAX object from the example above
	     	columns    : [
				{'data': 'Attribute'},
				{'data': 'Description'},
				{'data': 'Action'},
			]
	});
	
	//****************************************** AREA FOR MODAL *****************************************//
	/////// change model title
	$('#modalmiscellaneous').on('show.bs.modal', function (event) {
		  var button = $(event.relatedTarget) // Button that triggered the modal
		  var modal = $(this);
		  var recipient = button.data('whatever'); // Extract info from data-* attributes
		  var rowid = button.data('rowid');

		  modal.find('.modal-title').text(recipient);
		  
		  switch(recipient){
		  	case state = 'Add New':
		  		enable();
		  		$(".recby").text($("#liferayLogin").val());
		  		$('.daterec').text(moment(today).format("DD/MM/YYYY"));
		  		break;
		  	case state = 'Edit':
		  		enable();
		  		$("#input-patrAttri").prop("disabled", true);
			  	$.get('GetById', {
		  			rowid : rowid
				 	}, function(responseJson) {
					if(responseJson != null){
						$.each(responseJson, function(key,value) {
							$("#input-patrAttri").val(value['code']);
							$("#input-desc").val(value['description']);  
							$(".daterec").text(value['savedate']);
							$(".recby").text(value['createuser']);
						});
					}
				});
		  		break;
		  	case state = 'View':
		  		disable();
		  		$.get('GetById', {
		  			rowid : rowid
				 	}, function(responseJson) {
					if(responseJson != null){
						$.each(responseJson, function(key,value) {
							$("#input-patrAttri").val(value['code']);
							$("#input-desc").val(value['description']);  
							$(".daterec").text(value['savedate']);
							$(".recby").text(value['createuser']);
						});
					}
				});
		  		break;
		  }
	});
	
	//clear all input fields in bootstrap modal when close modal #modalAdd
	$('#modalmiscellaneous').on('hidden.bs.modal', function () {
	    $(this).find('form').trigger('reset');
	    $('#formdatamiscellaneous').data('bootstrapValidator').resetForm();
	});
	
	$('#searchmiscellaneous').on('hidden.bs.modal', function () {
		$(this).find('form').trigger('reset');
		//$('#selectAllAcc').prop('checked',false); 
		//$('#updAccStatus').dataTable().fnDestroy();
		//$('#updAccStatus').DataTable().ajax.reload();
	});
	//****************************************** AREA FOR MODAL *****************************************//
	
	//***************************************AREA FOR FUNCTION *****************************************//
	///// function enable at form 
	function enable(){
		$("input").prop("disabled", false);
  		$("#save, #cancel").show();
  		$("#close").hide();
	}
	
	//////////// function disable at form 
	function disable(){
		$("input").attr('disabled','disabled');
  		$("#save, #cancel").hide();
  		$("#close").show();
	}
	
	///////today date 
	var today = new Date();  
	//***************************************AREA FOR FUNCTION *****************************************//
	
	//*************************************AREA FOR VALIDATION *****************************************//
	//$('#table-issue').DataTable().ajax.reload();
	
	/////Call error message page
	function messageBox(code, criteria, label){
	      var url = "Error_Page?GL79ERRCODE="+code+"" +
	      "&criteria=" + criteria + "&label="+label+"";
	      //alert(url);
	      $.ajax({
	            url: url,
	            success: function(result) {
	                 //swal(result); //, 'warning' 
	            	setTimeout(function(){
	            		swal(result);
	        	     },700);
	            }
	      });
	}
	
	$('#formdatamiscellaneous').bootstrapValidator({
		framework: 'bootstrap',
		 excluded: [':disabled'],
		 icon: {
        // To use feedback icons, ensure that you use Bootstrap v3.1.0 or later
        //feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
        	inputpatrAttri: {
        		trigger: 'blur',
                validators: {
                    notEmpty: {
                        message: 'Patron Attribute is required'
                    },
                    remote: {
                        type: "GET",
                        url: 'CheckingpatrAttriID',
                        data: function(validator) {
                            return {
                            	inputpatrAttri: validator.getFieldElements('inputpatrAttri').val(),
                            };
                        },
                        message: 'Patron Attribute exist!',
                    }
                }
            },
            inputdesc: {
            	trigger: 'blur',
                validators: {
                    notEmpty: {
                        message: 'Description is required'
                    }
                }
            },
        }
    })
     .on('success.form.bv', function (e) {
    	 
    	// Prevent form submission
         e.preventDefault();

         // Get the form instance
         var $form = $(e.target);

         // Get the BootstrapValidator instance
         var bv = $form.data('bootstrapValidator');
        
         //get title
     	 var title = $("#modalName").text();

     	 var inputForm = $form.serialize();
     	 var patrAttri = $('input[name="inputpatrAttri"]').val();
     	 var oridesc = $('input[name="inputdesc"]').val();
     	 var desc = oridesc.replace(/[']/gi, "''");
     	 var daterec = moment($(".daterec").text(), 'DD/MM/YYYY').format("YYYYMMDD"); 
     	 var recby = $('.recby').text(); 
		 		 
		 switch(title){
		 	case state = 'Add New':
		 		$('#formdatamiscellaneous').data('bootstrapValidator').resetForm();

			 	$.get("Handler_SavePatrAttri",{
			 		patrAttri : patrAttri, 
			 		desc : desc,
			 		daterec : daterec,
			 		recby : recby}, 
		 	    function(data,status){
					//messageBox("005"," Add New","@action");
					$("#closeModalAdd").click();
					$('#miscellaneous').DataTable().ajax.reload();
		 	    }).fail(function(data){
					swal("fail");
				})
		 		break;
		 	case state = 'Edit':
		 		var reqno = $(".reqno").text();
		 		//alert(reqno + "reqno");
		 		$.get("Handler_EditPatrAttri",{
		 			patrAttri : patrAttri, 
			 		desc : desc},  
		 	    function(data,status){
 					//messageBox("005"," Edit","@action");
 			 	    $("#closeModalAdd").click();
 			 	    $('#miscellaneous').DataTable().ajax.reload();
		 	    }).fail(function(data){
					swal("fail");
				})
		 		break;
		 }
	});
	//*************************************AREA FOR VALIDATION *****************************************//
	
	//*****************************************AREA FOR DELETE *****************************************//
	$('#miscellaneous tbody').on( 'click', '#Delete', function () {
		var deleteid = $(this).attr('data-id');
		//alert(deleteid);
		
		var index = $('#miscellaneous').DataTable()
	       .rows({ search: 'applied'})
	       .nodes()
	       .to$()
	       .index($(this).closest('tr'));//$(this).closest('tr').index();
		//alert(index);
		swal({
			text: "Are you sure want to delete?",
	        showCancelButton: true,
	        confirmButtonColor: "#DD6B55",
	        confirmButtonText: "Yes",
	        cancelButtonText: "No"
	      }).then(
	        function(isConfirm) {
	          if (isConfirm) {
	        	  
	        	  $.ajax({
	    			  method: "GET",
	    			  url: "Handler_DeletePatrAttri",
	    			  data: { deleteid: deleteid}
	    			}).done(function( msg ) {
	    			    //messageBox("005"," Deleted","@action");
	    		})
	        	  setTimeout(function(){
						$('#miscellaneous').DataTable().row(index).remove().draw();
					}, 1000);
	    		
	          }
	     });
	});
	//*****************************************AREA FOR DELETE *****************************************//
	
	//*****************************************AREA FOR SEARCH *****************************************//
	$('#searchVal').click(function(){

		var input_criteria = $('#input-criteria').val();
		var search_type = $("input[name='searchSelection']:checked").val();
		
		//alert("input_criteria : " +input_criteria);
		//alert("search_type : " +search_type);
		
		$("#closeFindMode").click();
		
		$('#miscellaneous').DataTable( {
			responsive: true,
			scrollx: true,
			info: false,
			paging: true,
			ordering: false,
			destroy: true,
			searching:false,
			info: true,
			lengthChange: false,

		    ajax: {
		    	url: "Getsearchmiscellaneous",
			    data:{
			    	input_criteria : input_criteria,
			    	search_type : search_type
			    },
		        type: "GET",
		        dataSrc: function (json) {
		            var return_data = new Array();

		            	for(var i=0;i< json.length; i++){
				              return_data.push({
				            	  'Attribute': json[i].code,
					              'Description' : json[i].description, 
					              'Action' :  '<button id="View" class="btn btn-primary btn-xs" data-toggle="modal" data-target="#modalmiscellaneous" data-whatever="View" title="View" data-rowid="'+json[i].code+'"><span class="glyphicon glyphicon-eye-open"></span></button> <button id="Edit" class="btn btn-info btn-xs" data-toggle="modal" data-target="#modalmiscellaneous" data-whatever="Edit" title="Edit" data-rowid="'+json[i].code+'"><span class="glyphicon glyphicon-pencil"></span></button> <button id="Delete" class="btn btn-dull btn-xs DeleteOrder" data-id="'+json[i].code+'"><span class="glyphicon glyphicon-trash" title="Delete" ></span></button>',
				              })
				        }

		            return return_data;
		          },
		     },//This is the end of the AJAX object from the example above
		     	columns    : [
		 			{'data': 'Attribute'},
		 			{'data': 'Description'},
		 			{'data': 'Action'}
				],
		});
	});
	//*****************************************AREA FOR SEARCH *****************************************//
	
});