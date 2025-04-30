$(document).ready(function() {

	var getFNAME = $("#FNAME").val();
	
	$("#CODE").focus();
	
	/*$('#CODE').blur(function(event) {

		var id = $('#CODE').val();
		var value = $('#FCODE').val();

		$.get('CheckingCode', {
			CODE : id,
			FCODE : value
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
	
	 $('#codeForm').bootstrapValidator({
		 framework: 'bootstrap',
		 excluded: [':disabled'],
		 icon: {
	            valid: 'glyphicon glyphicon-ok',
	            invalid: 'glyphicon glyphicon-remove',
	            validating: 'glyphicon glyphicon-refresh'
	        },
	        fields: {
	        	CODE: {
	                validators: {
	                    notEmpty: {
	                        message: 'The Code is required'
	                    },
	                    remote: {
	                    	type: "GET",
	                        url: 'CheckingCode',
	                        data: function(validator) {
	                            return {
	                            	CODE: validator.getFieldElements('CODE').val(),
	                    			FCODE : validator.getFieldElements('FCODE').val(),
	                            };
	                        },
	                        message: 'Code already exist!',
	                    }
	                }
	            },
	            DESCRIPTION: {
	                validators: {
	                    notEmpty: {
	                        message: 'The Description is required'
	                    }
	                }
	            }  
	        }  
	    });

	 //alert(getFNAME);
	if(getFNAME == 'Condition Code'){
		$("#CODE").attr('maxlength','2');
		$("#DESCRIPTION").attr('maxlength','50');
	}
	
	if(getFNAME == 'Bibliographic Source Code'){
		$("#CODE").attr('maxlength','3');
		$("#DESCRIPTION").attr('maxlength','50');
	}
	
	if(getFNAME == 'Designation Code'){ //DESIGNATION CODE
		$("#CODE").attr('maxlength','20');
		$("#DESCRIPTION").attr('maxlength','50');
	}
	
	if(getFNAME == 'Document Status'){
		$("#CODE").attr('maxlength','1');
		$("#DESCRIPTION").attr('maxlength','50');
	}
	
	if(getFNAME == 'Error In Supply Code'){
		$("#CODE").attr('maxlength','4');
		$("#DESCRIPTION").attr('maxlength','50');
	}
	
	/*if(getFNAME == 'MAIL TYPE'){
		$("#CODE").attr('maxlength','2');
		$("#DESCRIPTION").attr('maxlength','50');
	}*/
	
	if(getFNAME == 'MARC Type'){
		$("#CODE").attr('maxlength','2');
		$("#DESCRIPTION").attr('maxlength','50');
	}
	
	if(getFNAME == 'Name Title'){
		$("#CODE").attr('maxlength','4');
		$("#DESCRIPTION").attr('maxlength','50');
	}
	
	if(getFNAME == 'Race Code'){
		$("#CODE").attr('maxlength','1');
		$("#DESCRIPTION").attr('maxlength','50');
	}
	
	if(getFNAME == 'RELIGION CODE'){
		$("#CODE").attr('maxlength','20');
		$("#DESCRIPTION").attr('maxlength','50');
	}
	
	if(getFNAME == 'RELIGION CODE'){
		$("#CODE").attr('maxlength','20');
		$("#DESCRIPTION").attr('maxlength','50');
	}
	
	if(getFNAME == 'Academic Subject'){
		$("#CODE").attr('maxlength','10');
		$("#DESCRIPTION").attr('maxlength','70');
	}
	
	/*if(getFNAME == 'SEMESTER CODE'){
		$("#CODE").attr('maxlength','2');
		$("#DESCRIPTION").attr('maxlength','50');
	}*/
	
	
	$('body').on('hidden.bs.modal', '.modal', function () {
	     $(this).removeData('bs.modal');
	});
	
	///msg after add and edit
	if($("#msgAdd").val() == "Success"){
		//alert("Success");
		//messageBox("005","Add","@action");
		swal("Successfully Add");
	}else if($("#msgAdd").val() == "Success Update"){
		swal("Successfully Update");
	}
	
	//16062019
	/*$("#editCode").click(function(){
		alert("iii");
		var val = $("#GL14PATR").val();
		$.get("Modal_EditCode?GL14PATR="+GL14PATR,function(data){
			$("#editContent").html(data);
		});
		$.get("Modal_EditCode?value=A",function(data){
			$("#editContent").html(data);
		})
	});*/
	/*$('#editModal').on('show.bs.modal', function (event) {
		alert("test");
		var value = button.data('value');
		alert(value);
	});*/
	
	
	
	
	

	

	/*$('#CODE').blur(function(event) {
		//alert("test");
		var id = $('#CODE').val();
		var value = $('#FCODE').val();
		//alert("test 2");
		$.get('CheckingCode', {
			CODE : id,
			FCODE : value
		}, function(responseText) {
			//alert("test 3");
			$('#ajaxResponse').text(responseText);
		});
	});*/
});

function toUppercase(){
	
	//alert("testing");
	var x = document.getElementById("CODE");
	x.value = x.value.toUpperCase();
	var x = document.getElementById("DESCRIPTION");
	x.value = x.value.toUpperCase();
}

function addCodeTable() {
	//alert("Masuk Javasript");
	 
	var FCODE = $('#FCODE').val();
	var FNAME = $('#FNAME').val();
	var CODE = $('#CODE').val();
	var DESCRIPTION = $('#DESCRIPTION').val();
	

	$.get('Handler_AddCodeTable', {FCODE:FCODE, FNAME:FNAME, CODE:CODE,DESCRIPTION:DESCRIPTION}, function(responseText) {
		//alert("test 3");
		$('#ajaxResponse').text(responseText);
	});
		
}

function updateCode() {
	//alert("Update Code");
	var FCODE = $('#FCODE').val();
	var FNAME = $('#FNAME').val();
	var CODE = $('#CODE').val();
	var DESCRIPTION = $('#DESCRIPTION').val();

	$.get('UpdateCode', {FCODE:FCODE, FNAME:FNAME, CODE:CODE,DESCRIPTION:DESCRIPTION}, function(responseText) {
		//alert("test 3");
		$('#ajaxResponse').text(responseText);
	});
		
}







