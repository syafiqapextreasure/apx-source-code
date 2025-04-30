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
	$('#formdataVendorFeedback').bootstrapValidator({
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
            inputfeddcode: {
                validators: {
                    notEmpty: {
                        message: 'Feedback Code is required'
                    },
                    remote: {
                    	type: "GET",
                        url: 'CheckinginputVendorFeedback',
                        data: function(validator) {
                            return {
                            	inputfeddcode: validator.getFieldElements('inputfeddcode').val(),
                            };
                        },
                        message: 'Feedback Code already exists. Please enter a unique Feedback Code',
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
     	 
     	 var codeVendor = $("#inputfeddcode").val();
     	 var Description = $("#inputDescription").val();
     	 var Notify;
     	 var Display;
     	 
     	if($(".inputNotify").is(':checked')){
     		Notify = 'Y';
     	} else {
     		Notify = 'N';
     	}
     	
     	if($(".inputDisplay").is(':checked')){
     		Display = 'Y';
     	} else {
     		Display = 'N';
     	}
     	 
     	 /*var date = moment($(".daterecTransactionCodet").text(), 'DD/MM/YYYY').format("YYYYMMDD");
     	 var userID = $(".recbyTransactionCode").text();*/
     	 
     	switch(title){
     		case state = 'Add':
     			$('#formdataVendorFeedback').data('bootstrapValidator').resetForm();
     			$('#formdataVendorFeedback').trigger('reset');
     			$.get("HandlerAddEditVendorFeedback",{
     				codeVendor : codeVendor, 
     				Description : Description,
     				Notify : Notify, 
     				Display : Display,
	     			/*date : date,
	     			userID : userID,*/
					action : "ADD",
     				},
     				
     				function(message){
     					var status = message.replace(/\s+/g, '');
     					if (status == "ok") {
     						messageBox("005","Add Vendor Feedback","@action");
    						$('#VendorFeedbackTable').DataTable().ajax.reload();
    						$("#closeModalAdd").click();
     					}else{
     						swal("Unsuccessfully");
     					}
     			});
     			break;
     		case state = 'Edit':
     			$.get("HandlerAddEditVendorFeedback",{
     				codeVendor : codeVendor, 
     				Description : Description,
     				Notify : Notify, 
     				Display : Display,
					action : "EDIT",
     				},
     				
     				function(message){
     					var status = message.replace(/\s+/g, '');
     					if (status == "ok") {
     						messageBox("005","Edit Vendor Feedback","@action");
     						$("#closeModalAdd").click();
    						$('#VendorFeedbackTable').DataTable().ajax.reload();
     					}else{
     						swal("Unsuccessfully");
     					}
     			});
     			break;
     	}
	});
	//*******************************************************************************************//
	
});