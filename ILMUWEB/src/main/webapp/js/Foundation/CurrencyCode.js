$(document).ready(function() {
	/*$('#GL24FOREX').blur(function(event) {
		//alert("test");
		var id = $('#GL24FOREX').val();
		//alert("test 2");
		$.get('CheckingCurrency', {
			GL24FOREX : id
		}, function(responseText) {
			//alert("test 3");
			$('#ajaxResponse').text(responseText);
		});
	});*/

	$("#GL24FOREX").focus();
	
	$("input[name=GL24FOREX]").attr('maxlength','3');
	$("input[name=GL24DESC]").attr('maxlength','50');
	
	$('body').on('hidden.bs.modal', '.modal', function () {
	     $(this).removeData('bs.modal');
	});
	
	/*$('#GL24FOREX').blur(function(event) {

		var id = $('#GL24FOREX').val();

		$.get('CheckingCurrency', {
			GL24FOREX : id
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
	
	$('#currencyForm').bootstrapValidator({
		 framework: 'bootstrap',
		 excluded: [':disabled'],
		 icon: {
	            valid: 'glyphicon glyphicon-ok',
	            invalid: 'glyphicon glyphicon-remove',
	            validating: 'glyphicon glyphicon-refresh'
	        },
	        fields: {
	        	GL24FOREX: {
	                validators: {
	                    notEmpty: {
	                        message: 'The Currency Code is required'
	                    },
	                    remote: {
	                    	type: "GET",
	                        url: 'CheckingCurrency',
	                        data: function(validator) {
	                            return {
	                            	GL24FOREX: validator.getFieldElements('GL24FOREX').val(),
	                            };
	                        },
	                        message: 'Currency Code already exist!',
	                    }
	                }
	            },
	            GL24DESC: {
	                validators: {
	                    notEmpty: {
	                        message: 'The Description is required'
	                    }
	                }
	            },
	            GL24PRATE: {
	                validators: {
	                    notEmpty: {
	                        message: 'The Publisher Rate is required'
	                    }
	                }
	            },
	            GL24BRATE: {
	                validators: {
	                    notEmpty: {
	                        message: 'The Bank Rate is required'
	                    }
	                }
	            } 
	        }  
	    });

});

function toUppercase(){
	
	//alert("testing");
	var x = document.getElementById("GL24FOREX");
	x.value = x.value.toUpperCase();
	var x = document.getElementById("GL24DESC");
	x.value = x.value.toUpperCase();
	
}


function addCurrency() {
	//alert("Masuk Javasript");
	var GL24FOREX = $('#GL24FOREX').val();
	var GL24DESC = $('#GL24DESC').val();
	var GL24PRATE = $('#GL24PRATE').val();
	var GL24BRATE = $('#GL24BRATE').val();
	var GL24PDATE = $('#GL24PDATE').val();
	var GL24BDATE = $('#GL24BDATE').val();
	

	$.get('Handler_AddCurrency', {GL24FOREX:GL24FOREX, GL24DESC:GL24DESC, GL24PRATE:GL24PRATE,GL24BRATE:GL24BRATE,GL24PDATE:GL24PDATE,GL24BDATE:GL24BDATE}, function(responseText) {
		 swal("Successful","The department code successfully added.", "success" );
			$('.swal2-confirm').click(function(){
				location.reload(); 
	        });
	 
	 $("#addModal").modal("hide");
	});
		
	}
	

function updateCurrency() {
	//alert("Masuk Javasript");
	var GL24FOREX = $('#GL24FOREX').val();
	var GL24DESC = $('#GL24DESC').val();
	var GL24PRATE = $('#GL24PRATE').val();
	var GL24BRATE = $('#GL24BRATE').val();
	var GL24PDATE = $('#GL24PDATE').val();
	var GL24BDATE = $('#GL24BDATE').val();
	

	$.get('UpdateCurrency', {GL24FOREX:GL24FOREX, GL24DESC:GL24DESC, GL24PRATE:GL24PRATE,GL24BRATE:GL24BRATE,GL24PDATE:GL24PDATE,GL24BDATE:GL24BDATE}, function(responseText) {
		 swal("Successful","The department code successfully added.", "success" );
			$('.swal2-confirm').click(function(){
				location.reload(); 
	        });
	 
	 $("#editModal").modal("hide");
	});
		
	}
