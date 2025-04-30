$(document).ready(function() {
	
	$("#GL71BRNC").focus();
	
	/*$('#GL71BRNC').blur(function(event) {

		var id = $('#GL71BRNC').val();

		$.get('CheckingBranch', {
			GL71BRNC : id
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
	
	 $('#branchForm').bootstrapValidator({
		 framework: 'bootstrap',
		 excluded: [':disabled'],
		 icon: {
	            valid: 'glyphicon glyphicon-ok',
	            invalid: 'glyphicon glyphicon-remove',
	            validating: 'glyphicon glyphicon-refresh'
	        },
	        fields: {
	            GL71BRNC: {
	                validators: {
	                    notEmpty: {
	                        message: 'The Branch Code is required'
	                    },
	                    remote: {
	                    	type: "GET",
	                        url: 'CheckingBranch',
	                        data: function(validator) {
	                            return {
	                            	GL71BRNC: validator.getFieldElements('GL71BRNC').val(),
	                            };
	                        },
	                        message: 'Branch Code already exist!',
	                    }
	                }
	            },
	            GL71DESC: {
	                validators: {
	                    notEmpty: {
	                        message: 'The Description is required'
	                    }
	                }
	            }  
	        }  
	    });
	 
	

	$("input[name=GL71DESC], input[name=GL71ADD1], input[name=GL71ADD2], input[name=GL71ADD3]").attr('maxlength','50');
	$("input[name=GL71DISPLAY]").attr('maxlength','20');
	
	$("input[name=GL71POSCODE]").keypress(function (e) {
        if (e.which != 8 && e.which != 0 && (e.which < 48 || e.which > 57)) {
            return false;
        }
	});
	
	$('body').on('hidden.bs.modal', '.modal', function () {
	     $(this).removeData('bs.modal');
	});
	
	
});
function addForm(){
	alert("eeee");
	var GL71BRNC = $('#GL71BRNC').val();
	var GL71DESC = $('#GL71DESC').val();
	var GL71DISPLAY = $('#GL71DISPLAY').val();
	var GL71ADD1 = $('#GL71ADD1').val();
	var GL71ADD2 = $('#GL71ADD2').val();
	var GL71ADD3 = $('#GL71ADD3').val();
	var GL71POSCODE = $('#GL71POSCODE').val();
	var GL71TOWN = $('#GL71TOWN').val();
	
	if(GL71BRNC == '' || GL71DESC == ''){
	/* $('#branchForm').bootstrapValidator({
		 icon: {
	            valid: 'glyphicon glyphicon-ok',
	            invalid: 'glyphicon glyphicon-remove',
	            validating: 'glyphicon glyphicon-refresh'
	        },
	        fields: {
	            GL71BRNC: {
	                validators: {
	                    notEmpty: {
	                        message: 'The Branch Code is required'
	                    }
	                }
	            },
	            GL71DESC: {
	                validators: {
	                    notEmpty: {
	                        message: 'The Description is required'
	                    }
	                }
	            }  
	        }  
	    });*/
	 
	}else{
		$.get('Handler_AddBranch', {GL71BRNC:GL71BRNC, GL71DESC:GL71DESC, GL71DISPLAY:GL71DISPLAY,GL71ADD1:GL71ADD1, GL71ADD2:GL71ADD2,GL71ADD3:GL71ADD3,GL71POSCODE:GL71POSCODE,GL71TOWN:GL71TOWN}, function(responseText) {
			alert("test 3");
			$('#ajaxResponse').text(responseText);
		});
	}
}

function toUppercase(){
	
	//alert("testing");
	var x = document.getElementById("GL71BRNC");
	x.value = x.value.toUpperCase();
	var x = document.getElementById("GL71DESC");
	x.value = x.value.toUpperCase();
	var x = document.getElementById("GL71DISPLAY");
	x.value = x.value.toUpperCase();
}

function addBranch() {
	//alert("Masuk Javasript");
	
	var GL71BRNC = $('#GL71BRNC').val();
	var GL71DESC = $('#GL71DESC').val();
	var GL71DISPLAY = $('#GL71DISPLAY').val();
	var GL71ADD1 = $('#GL71ADD1').val();
	var GL71ADD2 = $('#GL71ADD2').val();
	var GL71ADD3 = $('#GL71ADD3').val();
	var GL71POSCODE = $('#GL71POSCODE').val();
	var GL71TOWN = $('#GL71TOWN').val();
	
	
	$.get('Handler_AddBranch', {GL71BRNC:GL71BRNC, GL71DESC:GL71DESC, GL71DISPLAY:GL71DISPLAY,GL71ADD1:GL71ADD1, GL71ADD2:GL71ADD2,GL71ADD3:GL71ADD3,GL71POSCODE:GL71POSCODE,GL71TOWN:GL71TOWN}, function(responseText) {
		//alert("test 3");
		$('#ajaxResponse').text(responseText);
	});
		
	}

function updateBranch() {
	alert("Masuk Javasript");
	
	var GL71BRNC = $('#GL71BRNC').val();
	var GL71DESC = $('#GL71DESC').val();
	var GL71DISPLAY = $('#GL71DISPLAY').val();
	var GL71ADD1 = $('#GL71ADD1').val();
	var GL71ADD2 = $('#GL71ADD2').val();
	var GL71ADD3 = $('#GL71ADD3').val();
	var GL71POSCODE = $('#GL71POSCODE').val();
	var GL71TOWN = $('#GL71TOWN').val();
	var phonenoedit = $('#phonenoedit').val();
	var emailedit = $('#emailedit').val();
	
	
	$.get('UpdateBranchCode', {GL71BRNC:GL71BRNC, GL71DESC:GL71DESC, GL71DISPLAY:GL71DISPLAY,GL71ADD1:GL71ADD1, GL71ADD2:GL71ADD2,GL71ADD3:GL71ADD3,GL71POSCODE:GL71POSCODE,GL71TOWN:GL71TOWN,
								phonenoedit:phonenoedit, emailedit : emailedit}, function(responseText) {
		//alert("test 3");
		$('#ajaxResponse').text(responseText);
	});
		
	}

