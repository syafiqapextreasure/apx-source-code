$(document).ready(function(){
	//alert('masuk x ?');
	/////Call error message page
	function messageBox(code, criteria, label){
	      var url = "Error_Page?GL79ERRCODE="+code+"" +
	      "&criteria=" + criteria + "&label="+label+"";
	      //alert(url);
	      $.ajax({
	            url: url,
	            success: function(result) {
	                 swal(result); 
	            }
	      });
	}
	
	$('#formdataNote').bootstrapValidator({
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
        	notetitle: {
                validators: {
                    notEmpty: {
                        message: 'Title is required'
                    }
                }
            },
            note: {
            	//trigger: 'blur',
                validators: {
                    notEmpty: {
                        message: 'Annotation is required'
                    }
                }
            },
            viewprm: {
                validators: {
                    notEmpty: {
                        message: 'View Permission is required'
                    },
                }
            },
            editprm: {
                validators: {
                    notEmpty: {
                        message: 'Edit Permission is required'
                    },
                }
            },
        }
    })
     .on('success.form.bv', function (e) {
	
		//alert('masuk x success?');
    	 
    	 // Prevent form submission
         e.preventDefault();

         // Get the form instance
         var $form = $(e.target);

         // Get the BootstrapValidator instance
         var bv = $form.data('bootstrapValidator');
         
         //get title
     	 var title = $("#modalName").text();
     	 //
     	 var passval = $("#passval").val();
     	 var idnote = $("#idnote").val();
     	 var notetitle = $("#notetitle").val();
     	 var note = $("#note").val();
     	 var viewprm = $("#viewprm").val();
     	 var editprm = $("#editprm").val();
     	 var idnote = $("#idnote").val();
     	 var vendor = $("input[name='vendorView']:checked").val();
     	
     	if(idnote.length == 0){
     		/////////////save value
         	$.get("Handler_ViewNote",{
         			passval : passval, 
         			notetitle : notetitle,
         			note : note, 
         			viewprm : viewprm,
         			editprm : editprm, 
         			login : $("#liferayLogin").val(),
         			vendor : vendor,
    				},
    				function(message){
    					var status = message.replace(/\s+/g, '');
    					if (status == "ok") {
    						
    						$('#formdataNote').trigger('reset');
    					    $('#formdataNote').data('bootstrapValidator').resetForm();
    					    $("#viewprm, #editprm").prop("selectedIndex",-1);
    					    $('#notetitle').prop('disabled', true);
    						$('#note').prop('disabled', true);
    						$('#viewprm').prop('disabled', true);
    						$('#editprm').prop('disabled', true);
    						$('#vendorView').prop('disabled', true);
    						$('input[name=vendorView]').attr("disabled",true);
    						
    						$('#table-note').DataTable( {
    							destroy: true,
    							searching: false,
    							bLengthChange: false,
    							autoWidth: false,
    							responsive: true,
    							processing: true,
    							//info: false,
    						    ajax: {
    						    	url: "getViewNote",
    						        data : {patrId : passval},
    						        type: "GET",
    						        dataSrc: function (json) {
    						            var return_data = new Array();

    						            for(var i=0;i< json.length; i++){
    						            	
    						            	var view = json[i].viewpermission;
    						            	var edit = json[i].editpermission;

    						            	switch(view){
    						        	  	case state = '1':
    						        	  		view = "1 - Public";
    						        	  		break;
    						        	  	case state = '2':
    						        	  		view = "2 - Group";
    						        	  		break;
    						        	  	case state = '3':
    						        	  		view = "3 - Personal";
    						        	  		break;
    						        		}
    						            	
    						            	switch(edit){
    						        	  	case state = '1':
    						        	  		edit = "1 - Public";
    						        	  		break;
    						        	  	case state = '2':
    						        	  		edit = "2 - Group";
    						        	  		break;
    						        	  	case state = '3':
    						        	  		edit = "3 - Personal";
    						        	  		break;
    						        		}
    						            	
    						              return_data.push({
    						            	'ID': json[i].id,
    						                'Date': json[i].date,
    						                'Time' : json[i].time, 
    						                'Title' : json[i].title,
    						                'Author' : json[i].author,
    						                'View Permission' : view,
    						                'Edit Permission' : edit,
    						                'Action' : '<button id="ViewNote" class="btn btn-primary btn-xs ViewNote" title="View" data-rowid="'+json[i].id+'" data-author="'+json[i].author+'" data-viewprm="'+json[i].viewpermission+'"><span class="glyphicon glyphicon-eye-open"></span></button> <button id="EditNote" class="btn btn-info btn-xs EditNote" title="Edit" data-rowid="'+json[i].id+'" data-author="'+json[i].author+'" data-editprm="'+json[i].editpermission+'"><span class="glyphicon glyphicon-pencil"></span></button> <button id="DeleteNote" class="btn btn-dull btn-xs DeleteNote" data-id="'+json[i].id+'" data-author="'+json[i].author+'" data-editprm="'+json[i].editpermission+'"><span class="glyphicon glyphicon-trash" title="Delete" ></span></button>',
    						              })
    						            }
    						            return return_data;
    						          },
    						     },//This is the end of the AJAX object from the example above
    						     	columns    : [
    									{'data': 'ID'},
    									{'data': 'Date'},
    									{'data': 'Time'},
    									{'data': 'Title'},
    									{'data': 'Author'},
    									{'data': 'View Permission'},
    									{'data': 'Edit Permission'},
    									{'data': 'Action'},
    								],
    						});
    					}				
    			});
     	}
     	
     	else{
     		$.get("Handler_editnote",{
     			idnote : idnote, 
     			notetitle : notetitle,
     			note : note, 
     			viewprm : viewprm,
     			editprm : editprm, 
     			vendor : vendor,
				},
				function(message){
					var status = message.replace(/\s+/g, '');
					if (status == "ok") {
						
						$('#formdataNote').trigger('reset');
					    $('#formdataNote').data('bootstrapValidator').resetForm();
					    $("#viewprm, #editprm").prop("selectedIndex",-1);
					    
					    $('#notetitle').prop('disabled', true);
						$('#note').prop('disabled', true);
						$('#viewprm').prop('disabled', true);
						$('#editprm').prop('disabled', true);
						$('#vendorView').prop('disabled', true);
						$('input[name=vendorView]').attr("disabled",true);
						
						$('#table-note').DataTable( {
							destroy: true,
							searching: false,
							bLengthChange: false,
							autoWidth: false,
							responsive: true,
							processing: true,
							//info: false,
						    ajax: {
						    	url: "getViewNote",
						    	data : {patrId : passval},
						        type: "GET",
						        dataSrc: function (json) {
						            var return_data = new Array();

						            for(var i=0;i< json.length; i++){
						            	
						            	var view = json[i].viewpermission;
						            	var edit = json[i].editpermission;

						            	switch(view){
						        	  	case state = '1':
						        	  		view = "1 - Public";
						        	  		break;
						        	  	case state = '2':
						        	  		view = "2 - Group";
						        	  		break;
						        	  	case state = '3':
						        	  		view = "3 - Personal";
						        	  		break;
						        		}
						            	
						            	switch(edit){
						        	  	case state = '1':
						        	  		edit = "1 - Public";
						        	  		break;
						        	  	case state = '2':
						        	  		edit = "2 - Group";
						        	  		break;
						        	  	case state = '3':
						        	  		edit = "3 - Personal";
						        	  		break;
						        		}
						            	
						              return_data.push({
						            	'ID': json[i].id,
						                'Date': json[i].date,
						                'Time' : json[i].time, 
						                'Title' : json[i].title,
						                'Author' : json[i].author,
						                'View Permission' : view,
						                'Edit Permission' : edit,
						                'Action' : '<button id="ViewNote" class="btn btn-primary btn-xs ViewNote" title="View" data-rowid="'+json[i].id+'" data-author="'+json[i].author+'" data-viewprm="'+json[i].viewpermission+'"><span class="glyphicon glyphicon-eye-open"></span></button> <button id="EditNote" class="btn btn-info btn-xs EditNote" title="Edit" data-rowid="'+json[i].id+'" data-author="'+json[i].author+'" data-editprm="'+json[i].editpermission+'"><span class="glyphicon glyphicon-pencil"></span></button> <button id="DeleteNote" class="btn btn-dull btn-xs DeleteNote" data-id="'+json[i].id+'" data-author="'+json[i].author+'" data-editprm="'+json[i].editpermission+'"><span class="glyphicon glyphicon-trash" title="Delete" ></span></button>',
						              })
						            }
						            return return_data;
						          },
						     },//This is the end of the AJAX object from the example above
						     	columns    : [
									{'data': 'ID'},
									{'data': 'Date'},
									{'data': 'Time'},
									{'data': 'Title'},
									{'data': 'Author'},
									{'data': 'View Permission'},
									{'data': 'Edit Permission'},
									{'data': 'Action'},
								],
						});
					}				
			});
     	}
	});
	
	
	/////////////////////////DATE	
	/*$('#dateSent').on('changeDate show', function(e) {
        // Revalidate the date when user change it
        $('#formdataMonograph').bootstrapValidator('revalidateField', 'dsent');
	});*/
	
	
	
});