$(document).ready(function() {
	$("#GL11DEPT").focus();
	
	/*$('#GL11DEPT').blur(function(event) {

		var id = $('#GL11DEPT').val();

		$.get('CheckingDeptCode', {
			GL11DEPT : id
		}, function(responseText) {
			if(responseText != null){
				if(responseText==''){
					$("#Add").removeAttr("disabled");
					$("#ajaxResponse").empty();
				}else{
					$('#ajaxResponse').text(responseText);
					$("#Add").attr("disabled", "disabled");
				}
			}
		});
	});*/
	
	$('#deptForm').bootstrapValidator({
		 framework: 'bootstrap',
		 excluded: [':disabled'],
		 icon: {
	            valid: 'glyphicon glyphicon-ok',
	            invalid: 'glyphicon glyphicon-remove',
	            validating: 'glyphicon glyphicon-refresh'
	        },
	        fields: {
	        	GL11DEPT: {
	                validators: {
	                    notEmpty: {
	                        message: 'The Department Code is required'
	                    },
	                    remote: {
	                    	type: "GET",
	                        url: 'CheckingDeptCode',
	                        data: function(validator) {
	                            return {
	                            	GL11DEPT: validator.getFieldElements('GL11DEPT').val(),
	                            };
	                        },
	                        message: 'Department Code already exist!',
	                    }
	                }
	            },
	            GL11NAME: {
	                validators: {
	                    notEmpty: {
	                        message: 'The Department Name is required'
	                    }
	                }
	            },
	            GL11ADD1: {
	                validators: {
	                    notEmpty: {
	                        message: 'The Address is required'
	                    }
	                }
	            }
	        }  
	    });
	
	$("input[name=GL11DEPT]").attr('maxlength','5');
	$("input[name=GL11NAME]").attr('maxlength','50');
	$("input[name=GL11ADD1], input[name=GL11ADD2], input[name=GL11ADD3]").attr('maxlength','100');
	$("input[name=GL11POSCODE]").attr('maxlength','5');
	$("input[name=GL11TEL]").attr('maxlength','14');
	
	$("input[name=GL11FAX]").keypress(function (e) {
        if (e.which != 8 && e.which != 0 && (e.which < 48 || e.which > 57)) {
            return false;
        }
		  var curchr = this.value.length;
        var curval = $(this).val();
        if (curchr == 2) {
            $(this).val(curval + "-");
        }
        $(this).attr('maxlength', '12');
	});
	
	$("input[name=GL11POSCODE], input[name=GL11STAFF]").keypress(function (e) {
        if (e.which != 8 && e.which != 0 && (e.which < 48 || e.which > 57)) {
            return false;
        }
	});
	
	$('body').on('hidden.bs.modal', '.modal', function () {
	     $(this).removeData('bs.modal');
	});
});

/*function CheckIfExist(){
	
	var id = $('#GL11DEPT').val();
	
	
	$.get('CheckingDeptCode', {
		GL11DEPT : id
	}, function(responseText) {
		
		$('#ajaxResponse').text(responseText);
		
		var test = responseText.trim()=="Record already exist!";
		var GL11NAME= $('#GL11NAME').val();
		if(test || GL11NAME == ""){
			
			$('.sendButton').attr('disabled', true);
			
		}else{
			
			$('.sendButton').attr('disabled', false);
		}
	});
	
}*/


function toUppercase(){
	
	//alert("testing");
	var x = document.getElementById("GL11DEPT");
	x.value = x.value.toUpperCase();
	var x = document.getElementById("GL11NAME");
	x.value = x.value.toUpperCase();
}

function CheckName(){
	
	var GL11NAME= $('#GL11NAME').val();
	
	
	if(GL11NAME == ""){
		//alert("test test");
		$('.sendButton').attr('disabled', true);
	}
	else{
		
		//alert("test test");
		$('.sendButton').attr('disabled', false);
			
			
	}
}



function addDeptCode() {
	//alert("Masuk Javasript");
	
	var GL11DEPT = $('#GL11DEPT').val();
	var GL11NAME = $('#GL11NAME').val();
	var GL11ADD1 = $('#GL11ADD1').val();
	var GL11ADD2 = $('#GL11ADD2').val();
	var GL11ADD3 = $('#GL11ADD3').val();
	var GL11POSCODE = $('#GL11POSCODE').val();
	var GL11TOWN = $('#GL11TOWN').val();
	var GL11TEL = $('#GL11TEL').val();
	var GL11HEAD = $('#GL11HEAD').val();
	var GL11STAFF = $('#GL11STAFF').val();
	var GL11FAX = $('#GL11FAX').val();
	
	
		$.get('Handler_AddDept', {GL11DEPT:GL11DEPT, GL11NAME:GL11NAME, GL11ADD1:GL11ADD1, GL11ADD2:GL11ADD2, GL11ADD3:GL11ADD3, GL11POSCODE:GL11POSCODE, GL11TOWN:GL11TOWN, GL11TEL:GL11TEL, GL11HEAD:GL11HEAD, GL11STAFF:GL11STAFF, GL11FAX:GL11FAX}, function(responseText) {
			 swal("Successful","The department code successfully added.", "success" );
				$('.swal2-confirm').click(function(){
					location.reload(); 
		        });
		 
		 $("#addModal").modal("hide");
		});
		
    
	
	}

function updateDeptCode() {
	//alert("Masuk Javasript");
	
	var GL11DEPT = $('#GL11DEPT').val();
	var GL11NAME = $('#GL11NAME').val();
	var GL11ADD1 = $('#GL11ADD1').val();
	var GL11ADD2 = $('#GL11ADD2').val();
	var GL11ADD3 = $('#GL11ADD3').val();
	var GL11POSCODE = $('#GL11POSCODE').val();
	var GL11TOWN = $('#GL11TOWN').val();
	var GL11TEL = $('#GL11TEL').val();
	var GL11HEAD = $('#GL11HEAD').val();
	var GL11STAFF = $('#GL11STAFF').val();
	var GL11FAX = $('#GL11FAX').val();
	
	$.get('UpdateDeptCode', {GL11DEPT:GL11DEPT, GL11NAME:GL11NAME, GL11ADD1:GL11ADD1, GL11ADD2:GL11ADD2, GL11ADD3:GL11ADD3, GL11POSCODE:GL11POSCODE, GL11TOWN:GL11TOWN, GL11TEL:GL11TEL, GL11HEAD:GL11HEAD, GL11STAFF:GL11STAFF, GL11FAX:GL11FAX}, function(responseText) {
		 /*swal("Successful","The record successfully edited.");
			$('.swal2-confirm').click(function(){
				location.reload(); 
				
	 });*/
		$('#ajaxResponse').text(responseText);
	 //$("#editModal").modal("hide");
	});
}


