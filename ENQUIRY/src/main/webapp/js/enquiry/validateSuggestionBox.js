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
	
	$('#formdataSugg').bootstrapValidator({
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
        	txtReply: {
                validators: {
                    notEmpty: {
                        message: 'Formal Reply is required'
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
     	 //

     	$("#closeModalAdd").click();
     	
     	var lblDate = moment($(".lblDate").text(), "DD/MM/YYYY").format('YYYYMMDD');
     	var lblTime = moment($(".lblTime").text(), 'HH:mm:ss A').format('HHmmss')
     	var lblPatronID = $(".lblPatronID").text();
     	var txtReply = $("#txtReply").val();
     	
     	$.post("Handler_SaveReply",{
     			lblDate : lblDate,
     			lblTime : lblTime, 
     			lblPatronID : lblPatronID,
     			txtReply : txtReply, },
				function(message){
					var status = message.replace(/\s+/g, '');
					if (status == "ok") {
						messageBox("005","Reply","@action");
						$('#suggBox').dataTable().fnClearTable();
						setTimeout(function(){ 
							$('#suggBoxReterive').click();
	 		  			}, 1000);
					}else{
						swal("Unsuccessfully");
					}
				});
     	});
	
	
	
});