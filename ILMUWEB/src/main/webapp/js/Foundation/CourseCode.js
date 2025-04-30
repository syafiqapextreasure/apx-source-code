$(document).ready(function() {


	$("#GL12COURSE").focus();
	
	/*$('#GL12COURSE').blur(function(event) {

		var id = $('#GL12COURSE').val();

		$.get('CheckingCourse', {
			GL12COURSE : id
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
	
	 $('#courseCodeForm').bootstrapValidator({
		 framework: 'bootstrap',
		 excluded: [':disabled'],
		 icon: {
	            valid: 'glyphicon glyphicon-ok',
	            invalid: 'glyphicon glyphicon-remove',
	            validating: 'glyphicon glyphicon-refresh'
	        },
	        fields: {
	        	GL12COURSE: {
	                validators: {
	                    notEmpty: {
	                        message: 'The Course Code is required'
	                    },
	                    remote: {
	                    	type: "GET",
	                        url: 'CheckingCourse',
	                        data: function(validator) {
	                            return {
	                            	GL12COURSE: validator.getFieldElements('GL12COURSE').val(),
	                            };
	                        },
	                        message: 'Branch Code already exist!',
	                    }
	                }
	            },
	            GL12DESC: {
	                validators: {
	                    notEmpty: {
	                        message: 'The Description is required'
	                    }
	                }
	            }  
	        }  
	    });

	$("input[name=GL12COURSE]").attr('maxlength','10');
	$("input[name=GL12DESC]").attr('maxlength','50');
	
	$('body').on('hidden.bs.modal', '.modal', function () {
	     $(this).removeData('bs.modal');
	});
	
	//////keyup Tutor
	$("input[name=GL12TUTOR]").blur(function(e){
		var tutor = $("input[name=GL12TUTOR]").val();
		//alert("tutor" +tutor);

		////display tutor name
		$.get('serGetDesc', {
			code : tutor,
			table : "patron"
		 	}, function(responseJson) {
			if(responseJson != null){
				if(responseJson==""){
					$("#btn-add").prop("disabled",true);
					$(".tutor").css("color","red");
					$("input[name=GL12TUTOR]").css("border", "1px solid red");
				}
				$.each(responseJson, function(key,value) {
					
					var tutorname = value['Desc'];
					
					$("#btn-add").prop("disabled",false);
					$("#patronName").val(value['Desc']);
					$(".tutor").css("color","");
					$("input[name=GL12TUTOR]").css("border", "");
					
					//$("#patronName").val(value['Desc']);
				});
			}
		});

	});
	
	//clear tutor keydown backspace
	$("input[name=GL12TUTOR]").keydown(function(e){ 
		var code = e.keyCode || e.which;
		if(code == '8' ||code == '46'){
			$("#patronName").val("");
		}
	});
	
	//$('#editModal').modal({backdrop: 'static', keyboard: false})  
});

function toUppercase(){
	
	//alert("testing");
	var x = document.getElementById("GL12COURSE");
	x.value = x.value.toUpperCase();
	var x = document.getElementById("GL12DESC");
	x.value = x.value.toUpperCase();
	
	
}


function addCourse() {

	var GL12COURSE = $('#GL12COURSE').val();
	var GL12DESC = $('#GL12DESC').val();

	/*if(GL12COURSE == '' || GL12DESC == ''){

		$('#courseCodeForm').bootstrapValidator({
        framework: 'bootstrap',
        // To use feedback icons, ensure that you use Bootstrap v3.1.0 or later
        excluded: [':disabled'],
        icon: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
        	GL12COURSE: {
                validators: {
                    notEmpty: {
                        message: 'The Course Code is required'
                    },
                }
            },
        	GL12DESC: {
                validators: {
                    notEmpty: {
                        message: 'The Description is required'
                    },
                }
            },
        }
    });

	}else{*/
		//alert("notnull");
		$.get('Handler_AddCourse', {GL12COURSE:GL12COURSE, GL12DESC:GL12DESC}, function(responseText) {
			//alert("test 3");
			$('#ajaxResponse').text(responseText);
		});
	//}
		
	}
	

function updateCourse() {
	//alert("Masuk Javasript");
	var GL12COURSE = $('#GL12COURSE').val();
	var GL12DESC = $('#GL12DESC').val();
	

	$.get('UpdateCourse', {GL12COURSE:GL12COURSE, GL12DESC:GL12DESC}, function(responseText) {
		//alert("test 3");
		$('#ajaxResponse').text(responseText);
	});
		
	}
