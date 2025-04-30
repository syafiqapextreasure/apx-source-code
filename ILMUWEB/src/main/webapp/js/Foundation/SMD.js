$(document).ready(function() {
	$("#GL47SMD").focus();
	
	/*$('#GL47SMD').blur(function(event) {

		var id = $('#GL47SMD').val();

		$.get('CheckingSMD', {
			GL47SMD : id
		}, function(responseText) {
			
			if(responseText != null){
				if(responseText==''){
					$("#addBtn").removeAttr("disabled");
					$("#ajaxResponse").empty();
				}else{
					$('#ajaxResponse').text(responseText);
					$("#addBtn").attr("disabled", "disabled");
				}
			}
			
			
		});
	});*/
	
	$('#smdForm').bootstrapValidator({
		 framework: 'bootstrap',
		 excluded: [':disabled'],
		 icon: {
	            valid: 'glyphicon glyphicon-ok',
	            invalid: 'glyphicon glyphicon-remove',
	            validating: 'glyphicon glyphicon-refresh'
	        },
	        fields: {
	        	GL47SMD: {
	                validators: {
	                    notEmpty: {
	                        message: 'The SMD is required'
	                    },
	                    remote: {
	                    	type: "GET",
	                        url: 'CheckingSMD',
	                        data: function(validator) {
	                            return {
	                            	GL47SMD: validator.getFieldElements('GL47SMD').val(),
	                            };
	                        },
	                        message: 'SMD already exist!',
	                    }
	                }
	            },
	            GL47DESC: {
	                validators: {
	                    notEmpty: {
	                        message: 'The Description is required'
	                    }
	                }
	            }  
	        }  
	    });
	
	/*$('#GL47SMD').blur(function(event) {
		//alert("test");
		var id = $('#GL47SMD').val();
		//alert("test 2");
		$.get('CheckingSMD', {
			GL47SMD : id
		}, function(responseText) {
			//alert("test 3");
			$('#ajaxResponse').text(responseText);
		});
	});*/
	
	$("input[name=GL47DESC]").attr('maxlength','50');
	$("input[name=GL47DISPLAY]").attr('maxlength','40');
	
	$('#GL47SMD').keyup(function(){
		$(this).val($(this).val().toUpperCase());
	});
	
/*	$('#GL47SMD').val($('#GL47SMD').val().toUpperCase());
	$('#GL47DESC').val($('#GL47DESC').val().toUpperCase());
	$('#GL47DISPLAY').val($('#GL47DISPLAY').val().toUpperCase());*/
	
	$('body').on('hidden.bs.modal', '.modal', function () {
	     $(this).removeData('bs.modal');
	});
});

/*function toUppercase(){
	
	//alert("testing");
	var x = document.getElementById("GL47SMD");
	x.value = x.value.toUpperCase();
	var x = document.getElementById("GL47DESC");
	x.value = x.value.toUpperCase();
	var x = document.getElementById("GL47DISPLAY");
	x.value = x.value.toUpperCase();
}*/














