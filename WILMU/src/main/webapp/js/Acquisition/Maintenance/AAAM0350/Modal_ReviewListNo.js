/**
 * 
 */
$(document).ready(function() {
/////Review List Modal Validation
	$("#reviewNo").attr('maxlength','10');
	
	///////generate review list number
    $('.generateReviewNo').on('click', function () {	
    	$.get("Global?type=Handler&name=GenerateNo",{functions:"SERRVWNO",action:"ReviewListNo"},function(data){
    		$("#reviewNo").val(data.trim());
    		$("#reviewNo").focus();
    	});
    });
    
    /////////////// VALIDATE REVIEW NO
    $('#reviewList').bootstrapValidator({
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
        	reviewNo: {
        		trigger: 'blur',
                validators: {
                    notEmpty: {
                        message: 'This field is required'
                    },
                    remote: {
                        type: "GET",
                        url: 'validateReviewListNo',
                        data: function(validator) {
                            return {
                            	reviewNo: validator.getFieldElements('reviewNo').val(),
                            };
                        },
                        message: 'No Already exist!',
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
         ///$form.serialize();
         
         
         //alert(reviewNo);
         //alert($("#skip").prop('checked') == true);
         var id = $("#liferayLogin").val();
         var reviewNo = $("#reviewNo").val();
         var reviewNopurAuthor = $("#reviewNopurAuthor").val();
         //alert(reviewNopurAuthor);
         //alert(reviewNo);
         
         var dataArr = [];
     	 $('#newreqtable').DataTable().rows().nodes().to$().find(':has(:checkbox:checked)').each(function(){
     		
     		var req = $(this).closest('tr').find('td:eq(1)').text();//dataArr.push($(this).closest('tr').find('td:eq(1)').text());  
     		var indexval  = $('#newreqtable').DataTable().rows({ search: 'applied'}).nodes().to$().index($(this).closest('tr'));
     		$('#newreqtable').DataTable().row(indexval).remove().draw();
     		var obj = {};
  	    	obj.req = req;
  	    	obj.indexval = indexval;
  	    	dataArr.push(obj);
         });
     	//alert(JSON.stringify(dataArr));
     	
     	/////////////////////get index 
     	var indexArray = [];
 	 	
 	 	/////////////////////get reqno 
 	 	var reqArray = [];
	 	   	
 	 	for (var i = 0; i < dataArr.length; i++) {
 	 		var code = dataArr[i]['req'];
 	 		var obj = {};
  	    	obj.code = code;
  	    	reqArray.push(obj);
 	 		/*reqArray.push(code);*/
 	 	}
 	 	//alert(reqArray); 

        $.post("updateReviewList", { 
        	 	reqno : JSON.stringify(reqArray),
        	 	reviewNo : reviewNo
			 }, 
			 function(data,status){
			 	if($("#skip").prop('checked') == true){
			 		 $('#ReviewListNumber').modal('hide');
			 	}else{
			 		var action = "print";
			 		var url = "Global?type=Letter&name=GenerateLetterAcq&reqno=" + reviewNopurAuthor
	    			+ "&id=" + id
	    			+ "&letterId=" + "EQ41"
	    			+ "&action=" + action;
			 		window.open(url).print();
					//window.open(url);
			 	}
			 	
			 	setTimeout(function(){
		        	 $("#ReviewListNumber").removeClass("in");
		        	  $(".modal-backdrop").remove();
		        	  $('body').removeClass('modal-open');
		        	  $('body').css('padding-right', '');
		        	  $("#ReviewListNumber").hide();
		        	  
		 		}, 1000);
			 
			 }
		     ).fail(function(data){
		    	 swal("fail");
		     }).success(function(data){
		 });
         
         
         
     	 
	});

});