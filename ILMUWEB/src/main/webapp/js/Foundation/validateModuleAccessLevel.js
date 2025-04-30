$(document).ready(function(){
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
	
	$('#formdataModuleAccessLevel').bootstrapValidator({
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
        	inputGroupID: {
                validators: {
                    notEmpty: {
                        message: 'Please select Group ID'
                    },
                }
            },
            inputModuleName: {
                validators: {
                    notEmpty: {
                        message: 'Please select Module Name'
                    }
                }
            },
            inputAccessLevel: {
                validators: {
                    notEmpty: {
                        message: 'Access Level is required'
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
     	 var GroupID = $("#inputGroupID").val();
     	 var ModuleName = $("#inputModuleName").val();
     	 var AccessLevel = $("#inputAccessLevel").val();
     	 
     	 /*var date = moment($(".daterecTransactionCodet").text(), 'DD/MM/YYYY').format("YYYYMMDD");
     	 var userID = $(".recbyTransactionCode").text();*/
     	 
     	//getcheked chekbox
 		var getforretain = $('input[name="Retain"]:checkbox:checked').map(function(){
 			return $(this).val();
 		});
 		
 		var t = $('#TableModuleAccessLevel').DataTable({
     		destroy: true,
			searching: false,
			bLengthChange: false,
			columnDefs: [{ targets: [1, 2], className: 'text-center' },
                           { targets: [4], className: 'text-right' }],
 		});
     	 
     	switch(title){
     		case state = 'Add New':
     			/*$('#formdataTransactionCode').data('bootstrapValidator').resetForm();
     			$('#formdataTransactionCode').trigger('reset');*/
     			$.get("Handler_AddAndEditAccessLevel",{
     				GroupID : GroupID, 
     				ModuleName : ModuleName,
     				AccessLevel : AccessLevel,
     				action : "ADD"
     				},
     				
     				function(message){
     					var status = message.replace(/\s+/g, '');

     					if (status == "ok") {
     						messageBox("005","Add","@action");
     						$('#TableModuleAccessLevel').dataTable().fnClearTable();
     						
     						if(getforretain.get().length == '0'){
    		 	        		$("#modalModuleAccessLevel").find('form').trigger('reset');
    		 	        		$("#inputGroupID, #inputModuleName").prop("selectedIndex",-1);
    		 	            }else{
    		 	            	var element;
    		 	         		$('input:checkbox[name=Retain]').each(function() {    
    		 	        			    if(!$(this).is(':checked')){
    		 	        			    	var element = "#" + $(this).val();
    		 	        			    	//alert(element);

    		 	        			    	
    		 	        			    	if(element=="#inputAccessLevel"){
    		 	 	           			    	$('input[type=text][name=inputAccessLevel]').val('');
    		 		 	   					}
    		 	 	           			
    		 	 	           			if($(element).is("select")) {
    		 	 	           				$(element).prop('selectedIndex',-1);
    		 	        					}
    		 	        			    }
    		 	        		});
    		 	            }
     						
     						///
     						$.get("GetEditViewModuleAccessLevel",
     						 {rowid : GroupID, rowid2 : ModuleName},
     						 function(json){
    			    			 var return_data = new Array();
    			    			 
    			    	            for(var i=0;i< json.length; i++){
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
    			    	            	t.row.add( [
    			    	            	         i+1,
    			    	            	         json[i].GroupID, 
    			    	            	         modulecode, 
    			    	            	         moduledesc,
    			    	            	         json[i].AcessLevel,
    			    	            	         '<button id="View" class="btn btn-primary btn-xs" data-toggle="modal" data-target="#modalModuleAccessLevel" data-whatever="View" title="View" data-rowid="'+json[i].GroupID+'" data-rowid2="'+modulecode+'"><span class="glyphicon glyphicon-eye-open"></span></button> <button id="Edit" class="btn btn-info btn-xs" data-toggle="modal" data-target="#modalModuleAccessLevel" data-whatever="Edit" title="Edit" data-rowid="'+json[i].GroupID+'" data-rowid2="'+modulecode+'"><span class="glyphicon glyphicon-pencil"></span></button> <button id="Delete" class="btn btn-dull btn-xs Delete" data-rowid="'+json[i].GroupID+'" data-rowid2="'+modulecode+'"><span class="glyphicon glyphicon-trash" title="Delete" ></span></button>',
    										] ).draw( false );
    			    	            }
    			    		 });
    						//$('#TableModuleAccessLevel').DataTable().ajax.reload();
     					}else{
     						messageBox("133","","");
     						//swal("Record already exists.");
     					}
     			});
     			break;
     		case state = 'Edit':
     			$.get("Handler_AddAndEditAccessLevel",{
     				GroupID : GroupID, 
	     			ModuleName : ModuleName,
	     			AccessLevel : AccessLevel,
					action : "EDIT",
     				},
     				function(message){
     					var status = message.replace(/\s+/g, '');
     					if (status == "ok") {
     						messageBox("005","Edit","@action");
     						$("#closeModalAdd").click();
    						//$('#TableModuleAccessLevel').DataTable().ajax.reload();
     						$('#TableModuleAccessLevel').dataTable().fnClearTable();
    						
    						///
     						$.get("GetEditViewModuleAccessLevel",
     						 {rowid : GroupID, rowid2 : ModuleName},
     						 function(json){
    			    			 var return_data = new Array();
    			    			 
    			    	            for(var i=0;i< json.length; i++){
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
    			    	            	t.row.add( [
    			    	            	         i+1,
    			    	            	         json[i].GroupID, 
    			    	            	         modulecode, 
    			    	            	         moduledesc,
    			    	            	         json[i].AcessLevel,
    			    	            	         '<button id="View" class="btn btn-primary btn-xs" data-toggle="modal" data-target="#modalModuleAccessLevel" data-whatever="View" title="View" data-rowid="'+json[i].GroupID+'" data-rowid2="'+modulecode+'"><span class="glyphicon glyphicon-eye-open"></span></button> <button id="Edit" class="btn btn-info btn-xs" data-toggle="modal" data-target="#modalModuleAccessLevel" data-whatever="Edit" title="Edit" data-rowid="'+json[i].GroupID+'" data-rowid2="'+modulecode+'"><span class="glyphicon glyphicon-pencil"></span></button> <button id="Delete" class="btn btn-dull btn-xs Delete" data-rowid="'+json[i].GroupID+'" data-rowid2="'+modulecode+'"><span class="glyphicon glyphicon-trash" title="Delete" ></span></button>',
    										] ).draw( false );
    			    	            }
    			    		 });
    						
     					}else{
     						swal("Unsuccessfully");
     					}
     			});
     			break;
     	}
	});
});