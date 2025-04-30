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
	$('#formdatagroupId').bootstrapValidator({
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
        	inputgroupId: {
                validators: {
                    notEmpty: {
                        message: 'Group Id is required'
                    },
                    remote: {
                    	type: "GET",
                        url: 'CheckinginputgroupId',
                        data: function(validator) {
                            return {
                            	inputgroupId: validator.getFieldElements('inputgroupId').val(),
                            };
                        },
                        message: 'Group Id already exists. Please enter a unique Group Id',
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
     	 
     	 var GroupId = $("#inputgroupId").val();
     	 var GroupName = $("#inputGroupName").val();
     	 var AccessLevel = $("#inputAccessLevel").val();
     	
     	 
     	 /*var date = moment($(".daterecTransactionCodet").text(), 'DD/MM/YYYY').format("YYYYMMDD");
     	 var userID = $(".recbyTransactionCode").text();*/
     	 
     	switch(title){
     		case state = 'Add':
     			$('#formdatagroupId').data('bootstrapValidator').resetForm();
     			$('#formdatagroupId').trigger('reset');
     			$.get("HandlerAddEditGroupId",{
     				GroupId : GroupId, 
     				GroupName : GroupName,
     				AccessLevel : AccessLevel, 
	     			/*date : date,
	     			userID : userID,*/
					action : "ADD",
     				},
     				
     				function(message){
     					var status = message.replace(/\s+/g, '');
     					if (status == "ok") {
     						messageBox("005","Add Group Id","@action");
    						$('#groupIdTable').DataTable().ajax.reload();
    						$("#closeModalAdd").click();
     					}else{
     						swal("Unsuccessfully");
     					}
     			});
     			break;
     		case state = 'Edit':
     			$.get("HandlerAddEditGroupId",{
     				GroupId : GroupId, 
     				GroupName : GroupName,
     				AccessLevel : AccessLevel, 
					action : "EDIT",
     				},
     				
     				function(message){
     					var status = message.replace(/\s+/g, '');
     					if (status == "ok") {
     						messageBox("005","Edit Group Id","@action");
     						$("#closeModalAdd").click();
    						$('#groupIdTable').DataTable().ajax.reload();
     					}else{
     						swal("Unsuccessfully");
     					}
     			});
     			break;
     	}
	});
	//*******************************************************************************************//
	
});