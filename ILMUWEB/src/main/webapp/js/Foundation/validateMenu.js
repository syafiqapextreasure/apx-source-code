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
	
	//*******************************************************************************************//
	$('#formdataMenu').bootstrapValidator({
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
        	inputModuleName: {
                validators: {
                    notEmpty: {
                        message: 'Module Name is required'
                    },
                }
            },
            RootName: {
                validators: {
                    notEmpty: {
                        message: 'Please select Root Name'
                    },
                }
            },
            inputMenuIndex: {
                validators: {
                    notEmpty: {
                        message: 'Menu Index is required'
                    }
                }
            },
            inputProgramID: {
                validators: {
                    notEmpty: {
                        message: 'Program ID is required'
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
     	 
     	 var ModuleName = $("#inputModuleName").val();
     	 var RootName = $("#RootName").val();
     	 var MenuIndex = $("#inputMenuIndex").val();
     	 var MenuDescription = $("#inputMenuDescription").val();
     	 var ProgramID = $("#inputProgramID").val();
     	 var AccessLevel = $("#inputAccessLevel").val();

     	 /*var date = moment($(".daterecTransactionCodet").text(), 'DD/MM/YYYY').format("YYYYMMDD");
     	 var userID = $(".recbyTransactionCode").text();*/
     	 
     	switch(title){
     		case state = 'Add':
     			$('#formdataMenu').data('bootstrapValidator').resetForm();
     			$('#formdataMenu').trigger('reset');
     			$.get("HandlerAddEditMenu",{
     				ModuleName : ModuleName, 
     				RootName : RootName,
     				MenuIndex : MenuIndex, 
	     			MenuDescription : MenuDescription,
	     			ProgramID :  ProgramID,
	     			AccessLevel : AccessLevel,
	     			/*userID : userID,
	     			date : date,*/
					action : "ADD",
     				},
     				
     				function(message){
     					var status = message.replace(/\s+/g, '');
     					if (status == "ok") {
     						messageBox("005","Add Menu","@action");
    						$('#MenuTable').DataTable().ajax.reload();
    						$("#closeModalAdd").click();
     					}else{
     						swal("Unsuccessfully");
     					}
     			});
     			break;
     		case state = 'Edit':
     			
     			var hidemodule = $("#hidemodule").val();
     			var hiderootname = $("#hiderootname").val();
     			var hideindex = $("#hideindex").val();
				
     			$.get("HandlerAddEditMenu",{
     				ModuleName : ModuleName, 
     				RootName : RootName,
     				MenuIndex : MenuIndex, 
	     			MenuDescription : MenuDescription,
	     			ProgramID :  ProgramID,
	     			AccessLevel : AccessLevel,
	     			hidemodule : hidemodule, 
	     			hiderootname : hiderootname,
	     			hideindex : hideindex,
					action : "EDIT",
     				},
     				
     				function(message){
     					var status = message.replace(/\s+/g, '');
     					if (status == "ok") {
     						messageBox("005","Edit Menu","@action");
     						$("#closeModalAdd").click();
    						$('#MenuTable').DataTable().ajax.reload();
     					}else{
     						swal("Unsuccessfully");
     					}
     			});
     			break;
     	}
	});
	//*******************************************************************************************//
	
});