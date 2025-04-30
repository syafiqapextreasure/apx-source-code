$(document).ready(function() {
	
	$("#GL54SUBJSTA").focus();
	
	/*$('#GL54SUBJSTA').blur(function(event) {
		//alert("test");
		var id = $('#GL54SUBJSTA').val();
		//alert("test 2");
		$.get('CheckingGeneral', {
			GL54SUBJSTA : id
		}, function(responseText) {
			//alert("test 3");
			$('#ajaxResponse').text(responseText);
		});
	});*/
	
	$('#genSubForm').bootstrapValidator({
		 framework: 'bootstrap',
		 excluded: [':disabled'],
		 icon: {
	            valid: 'glyphicon glyphicon-ok',
	            invalid: 'glyphicon glyphicon-remove',
	            validating: 'glyphicon glyphicon-refresh'
	        },
	        fields: {
	        	GL54SUBJSTA: {
	                validators: {
	                    notEmpty: {
	                        message: 'The Subject Start is required'
	                    },
	                    /*remote: {
	                    	type: "GET",
	                        url: 'CheckingGeneral',
	                        data: function(validator) {
	                            return {
	                            	GL54SUBJSTA: validator.getFieldElements('GL54SUBJSTA').val(),
	                            };
	                        },
	                        message: 'Subject Start already exist!',
	                    }*/
	                }
	            },
	            GL54SUBJEND: {
	                validators: {
	                    notEmpty: {
	                        message: 'The Subject End is required'
	                    },
	                    remote: {
	                    	type: "GET",
	                        url: 'CheckingGeneral',
	                        data: function(validator) {
	                            return {
	                            	GL54SUBJSTA: validator.getFieldElements('GL54SUBJSTA').val(),
	                            	GL54SUBJEND: validator.getFieldElements('GL54SUBJEND').val(),
	                            };
	                        },
	                        message: 'Subject Start already exist!',
	                    }
	                }
	            },
	            GL54MARK: {
	                validators: {
	                    notEmpty: {
	                        message: 'The Join Character is required'
	                    }
	                }
	            },
	            GL54DESC: {
	                validators: {
	                    notEmpty: {
	                        message: 'The Description is required'
	                    }
	                }
	            }  
	        }  
	    });
	
	$("#GL54SUBJSTA, #GL54SUBJEND").attr('maxlength','6');
	$("#GL54MARK").attr('maxlength','1');
	$("#GL54DESC").attr('maxlength','50');
	
	$('body').on('hidden.bs.modal', '.modal', function () {
	     $(this).removeData('bs.modal');
	});
});

/*$(document).ready(function() {
	$('#GL54SUBJEND').blur(function(event) {
		//alert("test");
		var id = $('#GL54SUBJEND').val();
		//alert("test 2");
		$.get('CheckingGeneral', {
			GL54SUBJEND : id
		}, function(responseText) {
			//alert("test 3");
			$('#ajaxResponse2').text(responseText);
		});
	});
});*/



function toUppercase(){
	
	//alert("testing");
	var x = document.getElementById("GL54SUBJSTA");
	x.value = x.value.toUpperCase();
	var x = document.getElementById("GL54SUBJEND");
	x.value = x.value.toUpperCase();
	var x = document.getElementById("GL54DESC");
	x.value = x.value.toUpperCase();
}

function addGenSubj() {
	//alert("Masuk Javasript");
	
	var GL54SUBJSTA = $('#GL54SUBJSTA').val();
	var GL54SUBJEND = $('#GL54SUBJEND').val();
	var GL54MARK = $('#GL54MARK').val();
	var GL54DESC = $('#GL54DESC').val();
	
	$.get('Handler_AddGenSubj', {GL54SUBJSTA:GL54SUBJSTA, GL54SUBJEND:GL54SUBJEND, GL54MARK:GL54MARK,GL54DESC:GL54DESC}, function(responseText) {
		//alert("test 3");
		$('#ajaxResponse').text(responseText);
	});
		
	}

function updateGenSubj() {
	//alert("Masuk Javasript");
	
	var GL54SUBJSTA = $('#GL54SUBJSTA').val();
	var GL54SUBJEND = $('#GL54SUBJEND').val();
	var GL54MARK = $('#GL54MARK').val();
	var GL54DESC = $('#GL54DESC').val();
	
	$.get('UpdateGeneralSubj', {GL54SUBJSTA:GL54SUBJSTA, GL54SUBJEND:GL54SUBJEND, GL54MARK:GL54MARK,GL54DESC:GL54DESC}, function(responseText) {
		//alert("test 3");
		$('#ajaxResponse').text(responseText);
	});
		
	}

