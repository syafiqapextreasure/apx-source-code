$(document).ready(function() {
	
	$("#GL10ICAT").focus();
	
	/*$('#GL10ICAT').blur(function(event) {

		var id = $('#GL10ICAT').val();

		$.get('CheckingItemCat', {
			GL10ICAT : id
		}, function(responseText) {
			
			if(responseText != null){
				if(responseText==''){
					$("#btn-add").removeAttr("disabled");
					$("#ajaxResponse").empty();
				}else{
					$('#ajaxResponse').text(responseText);
					$("#btn-add").attr("disabled", "disabled");
				}
			}
			
			
		});
	});*/
	
	$('#icatForm').bootstrapValidator({
		 framework: 'bootstrap',
		 excluded: [':disabled'],
		 icon: {
	            valid: 'glyphicon glyphicon-ok',
	            invalid: 'glyphicon glyphicon-remove',
	            validating: 'glyphicon glyphicon-refresh'
	        },
	        fields: {
	        	GL10ICAT: {
	                validators: {
	                    notEmpty: {
	                        message: 'The Item Category is required'
	                    },
	                    remote: {
	                    	type: "GET",
	                        url: 'CheckingItemCat',
	                        data: function(validator) {
	                            return {
	                            	GL10ICAT: validator.getFieldElements('GL10ICAT').val(),
	                            };
	                        },
	                        message: 'Item Category already exist!',
	                    }
	                }
	            },
	            GL10DESC: {
	                validators: {
	                    notEmpty: {
	                        message: 'The Description is required'
	                    }
	                }
	            }  
	        }  
	    });
	
	/*$('#GL10ICAT').blur(function(event) {
		//alert("test");
		var id = $('#GL10ICAT').val();
		//alert("test 2");
		$.get('CheckingItemCat', {
			GL10ICAT : id
		}, function(responseText) {
			//alert("test 3");
			$('#ajaxResponse').text(responseText);
		});
	});*/
	
	$("input[name=GL10ELIG]").keypress(function (e) {
        if (e.which != 8 && e.which != 0 && (e.which < 48 || e.which > 57)) {
            return false;
        }
	});
	
	$("input[name=GL10ICAT]").attr('maxlength','3');
	$("input[name=GL10DESC]").attr('maxlength','50');
	$("input[name=GL10DISPLAY]").attr('maxlength','20');
	
	$('body').on('hidden.bs.modal', '.modal', function () {
	     $(this).removeData('bs.modal');
	});
});

function toUppercase(){
	
	//alert("testing");
	var x = document.getElementById("GL10ICAT");
	x.value = x.value.toUpperCase();
	var x = document.getElementById("GL10DESC");
	x.value = x.value.toUpperCase();
	var x = document.getElementById("GL10DISPLAY");
	x.value = x.value.toUpperCase();
	
	
}


function addItemCat() {
	//alert("Masuk Javasript");
	var GL10ICAT = $('#GL10ICAT').val();
	var GL10DESC = $('#GL10DESC').val();
	var GL10DISPLAY = $('#GL10DISPLAY').val();
	var GL10ELIG = $('#GL10ELIG').val();
	
	if (document.getElementById('day').checked) {
		GL10UNIT = "D";
	}else if (document.getElementById('hour').checked) {
		GL10UNIT = "H";
	}
	
	
	if (document.getElementById('GL10RESERVEC').checked) {
		GL10RESERVEC = "Y";
	}else{
		GL10RESERVEC = "N";
	}
	

	$.get('Handler_AddItemCat', {GL10ICAT:GL10ICAT, GL10DESC:GL10DESC, GL10DISPLAY:GL10DISPLAY,GL10ELIG:GL10ELIG,GL10UNIT:GL10UNIT,GL10RESERVEC:GL10RESERVEC}, function(responseText) {
		//alert("test 3");
		$('#ajaxResponse').text(responseText);
	});
		
	}
	

function updateItemCat() {
	//alert("Update Patron Status");
	var GL10ICAT = $('#GL10ICAT').val();
	var GL10DESC = $('#GL10DESC').val();
	var GL10DISPLAY = $('#GL10DISPLAY').val();
	var GL10ELIG = $('#GL10ELIG').val();

	if (document.getElementById('day').checked) {
		GL10UNIT = "D";
	}else if (document.getElementById('hour').checked) {
		GL10UNIT = "H";
	}
	
	
	if (document.getElementById('GL10RESERVEC').checked) {
		GL10RESERVEC = "Y";
	}else{
		GL10RESERVEC = "N";
	}
	

	$.get('UpdateItemCat', {GL10ICAT:GL10ICAT, GL10DESC:GL10DESC, GL10DISPLAY:GL10DISPLAY,GL10ELIG:GL10ELIG,GL10UNIT:GL10UNIT,GL10RESERVEC:GL10RESERVEC}, function(responseText) {
		//alert("test 3");
		$('#ajaxResponse').text(responseText);
	});
		
	}
