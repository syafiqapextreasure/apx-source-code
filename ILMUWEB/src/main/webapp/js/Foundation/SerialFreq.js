$(document).ready(function() {
	
	$("#GL49FREQ").focus();
	
	/*$('#GL49FREQ').blur(function(event) {
		//alert("test");
		var id = $('#GL49FREQ').val();
		//alert("test 2");
		$.get('CheckingSerial', {
			GL49FREQ : id
		}, function(responseText) {
			//alert("test 3");
			$('#ajaxResponse').text(responseText);
		});
	});*/
	
	$('#serialFreqForm').bootstrapValidator({
		 framework: 'bootstrap',
		 excluded: [':disabled'],
		 icon: {
	            valid: 'glyphicon glyphicon-ok',
	            invalid: 'glyphicon glyphicon-remove',
	            validating: 'glyphicon glyphicon-refresh'
	        },
	        fields: {
	        	GL49FREQ: {
	                validators: {
	                    notEmpty: {
	                        message: 'The Frequency Code is required'
	                    },
	                    remote: {
	                    	type: "GET",
	                        url: 'CheckingSerial',
	                        data: function(validator) {
	                            return {
	                            	GL49FREQ: validator.getFieldElements('GL49FREQ').val(),
	                            };
	                        },
	                        message: 'Frequency Code already exist!',
	                    }
	                }
	            },
	            GL49TIME: {
	                validators: {
	                    notEmpty: {
	                        message: 'The Time Span is required'
	                    }
	                }
	            },  
	            GL49ALERT: {
	                validators: {
	                    notEmpty: {
	                        message: 'The Renewal Alert is required'
	                    }
	                }
	            },
	            GL49PTYPE: {
	                validators: {
	                    notEmpty: {
	                        message: 'Please select Period Type'
	                    }
	                }
	            },
	        }  
	    });
	
	$("input[name=GL49TIME], input[name=GL49ALERT]").attr('maxlength','5');
	
	$('body').on('hidden.bs.modal', '.modal', function () {
	     $(this).removeData('bs.modal');
	});
});

document.getElementById("GL49TIME").addEventListener("keypress", myFunction);
document.getElementById("GL49ALERT").addEventListener("keypress", myFunction);

function myFunction() {
	 var regex = new RegExp("[0-9]+");
	    var key = String.fromCharCode(!event.charCode ? event.which : event.charCode);
	    if (!regex.test(key)) {
	       event.preventDefault();
	       return false;
	    }
}

function toUppercase(){
	
	//alert("testing");
	var x = document.getElementById("GL49FREQ");
	x.value = x.value.toUpperCase();
	var x = document.getElementById("GL49DESC");
	x.value = x.value.toUpperCase();
	
}


function addSerial() {
	//alert("Masuk Javasript");
	var GL49FREQ = $('#GL49FREQ').val();
	var GL49DESC = $('#GL49DESC').val();
	var GL49TIME = $('#GL49TIME').val();
	var GL49ALERT = $('#GL49ALERT').val();
	var GL49PTYPE = $('#GL49PTYPE').val();
	

	$.get('Handler_AddSerial', {GL49FREQ:GL49FREQ, GL49DESC:GL49DESC, GL49TIME:GL49TIME, GL49ALERT:GL49ALERT,GL49PTYPE:GL49PTYPE}, function(responseText) {
		//alert("test 3");
		$('#ajaxResponse').text(responseText);
	});
		
	}
	

function updateSerial() {
	//alert("Update Patron Status");
	var GL49FREQ = $('#GL49FREQ').val();
	var GL49DESC = $('#GL49DESC').val();
	var GL49TIME = $('#GL49TIME').val();
	var GL49ALERT = $('#GL49ALERT').val();
	var GL49PTYPE = $('#GL49PTYPE').val();

	
	$.get('UpdateSerial', {GL49FREQ:GL49FREQ, GL49DESC:GL49DESC, GL49TIME:GL49TIME, GL49ALERT:GL49ALERT,GL49PTYPE:GL49PTYPE}, function(responseText) {
		//alert("test 3");
		$('#ajaxResponse').text(responseText);
	});
		
	}
