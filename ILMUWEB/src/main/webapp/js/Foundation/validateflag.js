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
	$('#formdataFlag').bootstrapValidator({
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
        	inputFunction: {
                validators: {
                    notEmpty: {
                        message: 'Function is required'
                    },
                    remote: {
                    	type: "GET",
                        url: 'CheckinginputFlag',
                        data: function(validator) {
                            return {
                            	inputFunction: validator.getFieldElements('inputFunction').val(),
                            };
                        },
                        message: 'Function already exists. Please enter a unique Function',
                    }
                }
            },
            inputDescription: {
                validators: {
                    notEmpty: {
                        message: 'Description is required'
                    }
                }
            },
            inputValue: {
                validators: {
                    notEmpty: {
                        message: 'Value is required'
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
     	 
     	 var inputFunction = $("#inputFunction").val();
     	 var description = $("#inputDescription").val();
     	 var value = $("#inputValue").val();

     	 
     	switch(title){
     		case state = 'Add':
     			$('#formdataFlag').data('bootstrapValidator').resetForm();
     			$('#formdataFlag').trigger('reset');
     			$.get("HandlerAddEditFlag",{
     				inputFunction : inputFunction, 
     				description : description,
     				value : value, 
					action : "ADD",
     				},
     				
     				function(message){
     					var status = message.replace(/\s+/g, '');
     					if (status == "ok") {
     						messageBox("005","Add Flag","@action");
    						$('#flagTable').DataTable().ajax.reload();
    						$("#closeModalAdd").click();
     					}else{
     						swal("Unsuccessfully");
     					}
     			});
     			break;
     		case state = 'Edit':
     			$.get("HandlerAddEditFlag",{
     				inputFunction : inputFunction, 
     				description : description,
     				value : value, 
					action : "EDIT",
     				},
     				
     				function(message){
     					var status = message.replace(/\s+/g, '');
     					if (status == "ok") {
     						messageBox("005","Edit Flag","@action");
     						$("#closeModalAdd").click();
    						$('#flagTable').DataTable().ajax.reload();
     					}else{
     						swal("Unsuccessfully");
     					}
     			});
     			break;
     	}
	});
	//*******************************************************************************************//
	
});