$(document).ready(function() {
	
	$('#docStatus').DataTable( {
		destroy: true,
		searching: false,
		bLengthChange: false,
	    ajax: {
	        url: "getDocStatus",
	        type: "GET",
	        dataSrc: function (json) {
	            var return_data = new Array();
	           
	            for(var i=0;i< json.length; i++){
	              return_data.push({
	                'Status': json[i].Code,
	                'Description' : json[i].Description, 
	                'Action' : '<button id="View" class="btn btn-primary btn-xs" data-toggle="modal" data-target="#modalDocStatus" data-whatever="View" title="View" data-rowid="'+json[i].Code+'"><span class="glyphicon glyphicon-eye-open"></span></button> <button id="Edit" class="btn btn-info btn-xs" data-toggle="modal" data-target="#modalDocStatus" data-whatever="Edit" title="Edit" data-rowid="'+json[i].Code+'"><span class="glyphicon glyphicon-pencil"></span></button> <button id="Delete" class="btn btn-dull btn-xs Delete" data-OrderNo="'+json[i].Code+'"><span class="glyphicon glyphicon-trash" title="Delete" ></span></button>'
	              })
	            }
	            return return_data;
	          },
	     },//This is the end of the AJAX object from the example above
	     	columns    : [
				{'data': 'Status'},
				{'data': 'Description'},
				{'data': 'Action'}
			]
	});
	
	///modal add, edit, view
	$('#modalDocStatus').on('show.bs.modal', function (event) {
		  var button = $(event.relatedTarget) // Button that triggered the modal
		  var modal = $(this);
		  var recipient = button.data('whatever'); // Extract info from data-* attributes
		  var rowid = button.data('rowid');

		  //alert(rowid);
		  modal.find('.modal-title').text(recipient);
		  
		  
			
		  switch(recipient){
		  	case state = 'Add':
		  		enable();
			  	$(".chgto").empty();
		  		$(".chgVal").empty();
			  	$.get('getDocStatus', function(responseJson) {
					if(responseJson != null){
						$.each(responseJson, function(key,value) {
							var checkbox = '<div class="checkbox"><label><input type="checkbox" name="chgval" value="'+value["Code"]+'">'+value["Code"]+','+value["Description"]+'</lable></div>';    
							$('.chgVal').append(checkbox);
						});
					}
				});
	
			  	 $(document).on('click',"input[name=chgval]",function () {        
			  		var allVals = [];
	
		            $('input[name=chgval]:checked').each(function () {
		                allVals.push($(this).val());
	
		            });
		            var newallVals = allVals.join('');
		         	$(".chgto").text(newallVals);
		         	$("#chgto3").val(newallVals);
			     });
				
	  			break;
		  	case state = 'Edit':
		  		enable();
			  	$(".chgto").empty();
	  			$(".chgValTick").empty();
	  			$("input[name=documentStatus]").attr('disabled', true);
	  			$.get('getDocStatusNotIn', {
		  			rowid : rowid
			  		},function(responseJson) {
					if(responseJson != null){
						$.each(responseJson, function(key,value) {
							var checkbox = '<div class="checkbox"><label><input type="checkbox" name="chgval" value="'+value["Code"]+'">'+value["Code"]+','+value["Description"]+'</lable></div>';    
							$('.chgValTick').append(checkbox);
						});
					}
				});
		  		
			  	$.get('getDocStatus2', {
		  			rowid : rowid
				 	}, function(responseJson) {
					if(responseJson != null){
						$.each(responseJson, function(key,value) {
							$("#documentStatus").val(value['Code']);
							$("#desc").val(value['Description']);
							$(".chgto").text(value['ChgTo']);
							$("#chgto3").val(value['ChgTo']);
							
							setTimeout(function () {
								var a = $("#chgto3").val();
								var b = a.split('');
								$(".chgValTick").find('[value=' + b.join('], [value=') + ']').prop("checked", true);
			                 }, 500);
							
							/*var getValChgto2 = value['ChgTo'];
							var getValChgto3 = getValChgto2.split('');
							
							$.each(getValChgto3, function (index, value) {
								alert(index +"getValChgto5" +value.toString() + "Z");
								$("#chgto4").val(index + value);
							});*/
							
							/*$.each(getValChgto3, function (index, value) {
								//alert("getValChgto5" +value.toString() + " Z");
								//alert(value.toString() +"##")
								 $('input[name="chgval"][value="' + value.toString() + '"]').prop("checked", true);
							});*/
							
							
	
						});
					}
				});
			  	
			  	$(document).on('click',"input[name=chgval]",function () {        
			  		var allVals = [];
	
		            $('input[name=chgval]:checked').each(function () {
		                allVals.push($(this).val());
	
		            });
		            var newallVals = allVals.join('');
		         	$(".chgto").text(newallVals);
		         	$("#chgto3").val(newallVals);
			     });
		  		break;
		  	case state = 'View':
		  		disable();
		  	
			  	$(".chgto").empty();
	  			$(".chgValTick").empty();
		  		$.get('getDocStatusNotIn', {
		  			rowid : rowid
			  		},function(responseJson) {
					if(responseJson != null){
						$.each(responseJson, function(key,value) {
							/*var checkbox = '<input type="checkbox" value="'+value["Code"]+'">'+value["Code"]+','+value["Description"]+' disabled';    
							$('.chgValTick').append(checkbox);*/
							var checkbox = '<div class="checkbox"><label><input type="checkbox" name="chgval" disabled value="'+value["Code"]+'">'+value["Code"]+','+value["Description"]+'</lable></div>';
							$('.chgValTick').append(checkbox);
						});
					}
				});
	  		
			  	$.get('getDocStatus2', {
		  			rowid : rowid
				 	}, function(responseJson) {
					if(responseJson != null){
						$.each(responseJson, function(key,value) {
							$("#documentStatus").val(value['Code']);
							$("#desc").val(value['Description']);
							$(".chgto").text(value['ChgTo']);
							$("#chgto3").val(value['ChgTo']);
							
							/*var getValChgto5 = value['ChgTo'];
							
							var getValChgto6 = getValChgto5.split('');*/
							
							 setTimeout(function () {
								var a = $("#chgto3").val();
								var b = a.split('');
								$(".chgValTick").find('[value=' + b.join('], [value=') + ']').prop("checked", true);
			                 }, 500);

							/*$(".chgValTick").find('[value=' + getValChgto6.join('], [value=') + ']').prop("checked", true);*/
							//$("#list").find('[value=' + getValChgto6.join('], [value=') + ']').prop("checked", true);
							/*$.each(getValChgto6, function (index, value) {
								//alert("getValChgto5" +value.toString() + " Z");
								//alert(value.toString() +"##")
								$('input[name="chgval"][value="'+value.toString()+'"]').prop("checked", true);
							});*/
						});
					}
				});
			  	break;
		  }	
	});
	
	//clear all input fields in bootstrap modal when close modal #modalAdd
	$('#modalDocStatus').on('hidden.bs.modal', function () {
	    $(this).find('form').trigger('reset');
	    $('#formdata').data('bootstrapValidator').resetForm();
		//$(this).removeData('bs.modal');
		 //$('#formdata').bootstrapValidator('resetForm', true);
	});
	
	
	//// function disable
	function disable(){
		$("input").attr('disabled','disabled');
		$("#save, #cancel").hide();
  		$("#close").show();
	}
	
	//// function enable
	function enable(){
		$("input").removeAttr('disabled');
		$("#save, #cancel").show();
  		$("#close").hide();
	}
	
	///max length
	$("#documentStatus").attr('maxlength','1');
	$("#desc").attr('maxlength','20');
	
//////////////////////////VALIDATION/////////////////////////////////////
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
		        	},1500);
		       	}
		});
	}
	
	$('#formdata').bootstrapValidator({
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
        	documentStatus: {
                validators: {
                    notEmpty: {
                        message: 'The Document Status is required'
                    },
                    remote: {
                        type: "GET",
                        url: 'checkingdocumentStatus',
                        data: function(validator) {
                            return {
                            	documentStatus: validator.getFieldElements('documentStatus').val(),
                            };
                        },
                        message: 'Document Status already exist!',
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
     	 
     	 var documentStatus = $('input[name="documentStatus"]').val();
     	 var desc = $('input[name="desc"]').val();
     	 var chgto = $('input[name="chgto3"]').val();
     	 alert(chgto)
     	 
     	switch(title){
	        case state = 'Add':
	        	$.post("addDocStatus", { 
	        		documentStatus : documentStatus,
	        		desc : desc,
	        		chgto : chgto
	 			}, 
		 	    function(data,status){
		 	    	//messageBox("005","","");
	 				swal("Successfully Added");
		 	    	$("#closeModalAdd").click();
		 	    	$('#docStatus').dataTable().fnClearTable();
		 	    	
		 	    	$('#docStatus').DataTable( {
		 	   		destroy: true,
		 	   		searching: false,
		 	   		bLengthChange: false,
		 	   	    ajax: {
		 	   	        url: "getDocStatus",
		 	   	        type: "GET",
		 	   	        dataSrc: function (json) {
		 	   	            var return_data = new Array();
		 	   	           
		 	   	            for(var i=0;i< json.length; i++){
		 	   	              return_data.push({
		 	   	                'Status': json[i].Code,
		 	   	                'Description' : json[i].Description, 
		 	   	                'Action' : '<button id="View" class="btn btn-primary btn-xs" data-toggle="modal" data-target="#modalDocStatus" data-whatever="View" title="View" data-rowid="'+json[i].Code+'"><span class="glyphicon glyphicon-eye-open"></span></button> <button id="Edit" class="btn btn-info btn-xs" data-toggle="modal" data-target="#modalDocStatus" data-whatever="Edit" title="Edit" data-rowid="'+json[i].Code+'"><span class="glyphicon glyphicon-pencil"></span></button> <button id="Delete" class="btn btn-dull btn-xs Delete" data-OrderNo="'+json[i].Code+'"><span class="glyphicon glyphicon-trash" title="Delete" ></span></button>'
		 	   	              })
		 	   	            }
		 	   	            return return_data;
		 	   	          },
		 	   	     },//This is the end of the AJAX object from the example above
		 	   	     	columns    : [
		 	   				{'data': 'Status'},
		 	   				{'data': 'Description'},
		 	   				{'data': 'Action'}
		 	   			]
		 	   	});
		 	    	
		 	    }).fail(function(data){
					//swal("fail");
				})/*.success(function(data){
				})*/;
		  		break;
	        case state = 'Edit':
	        	
	        	$("#documentStatus").attr('disabled', false);
	        	var documentStatus2 = $('input[name="documentStatus"]').val();
	
	        $.post("editDocStatus", { 
	        	documentStatus : documentStatus2,
        		desc : desc,
        		chgto : chgto
 			}, 
	 	    function(data,status){
	 	    	//messageBox("005","","@Edit");
 				swal("Successfully edit");
	 	    	$("#closeModalAdd").click();
	 	    	$('#docStatus').dataTable().fnClearTable();
	 	    	
	 	    	$('#docStatus').DataTable( {
		 	   		destroy: true,
		 	   		searching: false,
		 	   		bLengthChange: false,
		 	   	    ajax: {
		 	   	        url: "getDocStatus",
		 	   	        type: "GET",
		 	   	        dataSrc: function (json) {
		 	   	            var return_data = new Array();
		 	   	           
		 	   	            for(var i=0;i< json.length; i++){
		 	   	              return_data.push({
		 	   	                'Status': json[i].Code,
		 	   	                'Description' : json[i].Description, 
		 	   	                'Action' : '<button id="View" class="btn btn-primary btn-xs" data-toggle="modal" data-target="#modalDocStatus" data-whatever="View" title="View" data-rowid="'+json[i].Code+'"><span class="glyphicon glyphicon-eye-open"></span></button> <button id="Edit" class="btn btn-info btn-xs" data-toggle="modal" data-target="#modalDocStatus" data-whatever="Edit" title="Edit" data-rowid="'+json[i].Code+'"><span class="glyphicon glyphicon-pencil"></span></button> <button id="Delete" class="btn btn-dull btn-xs Delete" data-OrderNo="'+json[i].Code+'"><span class="glyphicon glyphicon-trash" title="Delete" ></span></button>'
		 	   	              })
		 	   	            }
		 	   	            return return_data;
		 	   	          },
		 	   	     },//This is the end of the AJAX object from the example above
		 	   	     	columns    : [
		 	   				{'data': 'Status'},
		 	   				{'data': 'Description'},
		 	   				{'data': 'Action'}
		 	   			]
		 	   	});
	 	    	
	 	    }).fail(function(data){
				//swal("fail");
			})/*.success(function(data){
			})*/;
		 	//$('#OdrMaint2').DataTable().row( $(this).parents('tr') ).remove().draw();
	 		break;
     	}
     
     	$("#closeModalAdd").click();
        

	});
	
	///delete
	$('#docStatus tbody').on( 'click', '#Delete', function () {
		var deleteDoc = $(this).attr('data-OrderNo');
		
		//function deletedocstatus(deleteDoc) {
			// alert("Test");

			swal({
				// title: 'Do you wish to delete this record?',
				text : 'Do you wish to delete this record?',
				//type : 'warning',
				showCancelButton : true,
				confirmButtonColor : '#3085d6',
				cancelButtonColor : '#d33',
				confirmButtonText : 'Yes',
				cancelButtonText : 'No',
				confirmButtonClass : 'confirm-class',
				cancelButtonClass : 'cancel-class',
				closeOnConfirm : false,
				closeOnCancel : false
			})
					.then(
							function() {
								var deleteUrl = "Handler_DocStaus?deleteDoc="
										+ deleteDoc;

								$
										.ajax({
											url : deleteUrl,
											success : function(result) {
												var status = result.replace(/\s+/g, '');
												if (status == "ok") {
													swal(
															'Successfully Deleted!',
															'The record has been successfully removed.');
													$('.swal2-confirm').click(
															function() {
																$('#docStatus').DataTable( {
														 	   		destroy: true,
														 	   		searching: false,
														 	   		bLengthChange: false,
														 	   	    ajax: {
														 	   	        url: "getDocStatus",
														 	   	        type: "GET",
														 	   	        dataSrc: function (json) {
														 	   	            var return_data = new Array();
														 	   	           
														 	   	            for(var i=0;i< json.length; i++){
														 	   	              return_data.push({
														 	   	                'Status': json[i].Code,
														 	   	                'Description' : json[i].Description, 
														 	   	                'Action' : '<button id="View" class="btn btn-primary btn-xs" data-toggle="modal" data-target="#modalDocStatus" data-whatever="View" title="View" data-rowid="'+json[i].Code+'"><span class="glyphicon glyphicon-eye-open"></span></button> <button id="Edit" class="btn btn-info btn-xs" data-toggle="modal" data-target="#modalDocStatus" data-whatever="Edit" title="Edit" data-rowid="'+json[i].Code+'"><span class="glyphicon glyphicon-pencil"></span></button> <button id="Delete" class="btn btn-dull btn-xs Delete" data-OrderNo="'+json[i].Code+'"><span class="glyphicon glyphicon-trash" title="Delete" ></span></button>'
														 	   	              })
														 	   	            }
														 	   	            return return_data;
														 	   	          },
														 	   	     },//This is the end of the AJAX object from the example above
														 	   	     	columns    : [
														 	   				{'data': 'Status'},
														 	   				{'data': 'Description'},
														 	   				{'data': 'Action'}
														 	   			]
														 	   	});
																/*$('#OdrMaint2').DataTable().row( $(this).parents('tr') ).remove().draw();*/
																//location.reload();
																//location.replace("template?MODULE=Foundation/05_DepartmentCode&ACTION=DeptTable.jsp");
															});

												} else {
													swal(
															'Not Deleted',
															'This record cannot be deleted.');
												}
											}
										});
							}, function(dismiss) {
								if (dismiss == 'cancel') {
									swal('', 'Cancelled');

								}
							})
		//}
		
		//$('#OdrMaint2').DataTable().row( $(this).parents('tr') ).remove().draw();
	});
});