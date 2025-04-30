$(document).ready(function() {
	$('#GL08STAT').focus();
	
	$("#GL08STAT").attr('maxlength','2');
	
	/*$('#GL08STAT').blur(function(event) {
		//alert("test");
		var id = $('#GL08STAT').val();
		//alert("test 2");
		$.get('CheckingStatus', {
			GL08STAT : id
		}, function(responseText) {
			//alert("test 3");
			$('#ajaxResponse').text(responseText);
		});
	});*/
	
	$('#statusForm').bootstrapValidator({
		 framework: 'bootstrap',
		 excluded: [':disabled'],
		 icon: {
	            valid: 'glyphicon glyphicon-ok',
	            invalid: 'glyphicon glyphicon-remove',
	            validating: 'glyphicon glyphicon-refresh'
	        },
	        fields: {
	        	GL08STAT: {
	                validators: {
	                    notEmpty: {
	                        message: 'The Patron Status is required'
	                    },
	                    remote: {
	                    	type: "GET",
	                        url: 'CheckingStatus',
	                        data: function(validator) {
	                            return {
	                            	GL08STAT: validator.getFieldElements('GL08STAT').val(),
	                            };
	                        },
	                        message: 'Patron Status already exist!',
	                    }
	                }
	            },
	            Description: {
	                validators: {
	                    notEmpty: {
	                        message: 'The Description is required'
	                    }
	                }
	            }  
	        }  
	    });
	
	$('body').on('hidden.bs.modal', '.modal', function () {
	     $(this).removeData('bs.modal');
	});
});

function toUppercase(){
	
	//alert("testing");
	var x = document.getElementById("GL08STAT");
	x.value = x.value.toUpperCase();
	var x = document.getElementById("GL08DESC");
	x.value = x.value.toUpperCase();
}


function addStat() {
	//alert("Masuk Javasript");
	var GL08STAT = $('#GL08STAT').val();
	var GL08DESC = $('#GL08DESC').val();
	var status = null;
	
	
	if (document.getElementById('GL08ACTION1').checked) {
		GL08ACTION1 = "Y";
	}else{
		GL08ACTION1 = "N";
	}
	    
	if (document.getElementById('GL08ACTION2').checked) {
		GL08ACTION2 = "Y";
	}else{
		GL08ACTION2 = "N";
	}
	
	if (document.getElementById('GL08ACTION3').checked) {
		GL08ACTION3 = "Y";
	}else{
		GL08ACTION3 = "N";
	}
	
	if (document.getElementById('GL08ACTION4').checked) {
		GL08ACTION4 = "Y";
	}else{
		GL08ACTION4 = "N";
	}
	
	if (document.getElementById('GL08ACTION5').checked) {
		GL08ACTION5 = "Y";
	}else{
		GL08ACTION5 = "N";
	}
	
	if (document.getElementById('GL08ACTION6').checked) {
		GL08ACTION6 = "Y";
	}else{
		GL08ACTION6 = "N";
	}
	
	if (document.getElementById('GL08ACTION7').checked) {
		GL08ACTION7 = "Y";
	}else{
		GL08ACTION7 = "N";
	}
	
	if (document.getElementById('GL08ACTION8').checked) {
		GL08ACTION8 = "Y";
	}else{
		GL08ACTION8 = "N";
	}
	
	if (document.getElementById('GL08ACTION9').checked) {
		GL08ACTION9 = "Y";
	}else{
		GL08ACTION9 = "N";
	}
	
	if (document.getElementById('GL08ACTION10').checked) {
		GL08ACTION10 = "Y";
	}else{
		GL08ACTION10 = "N";
	}
	
	if (document.getElementById('GL08ACTION11').checked) {
		GL08ACTION11 = "Y";
	}else{
		GL08ACTION11 = "N";
	}
	
	if (document.getElementById('GL08ACTION12').checked) {
		GL08ACTION12 = "Y";
	}else{
		GL08ACTION12 = "N";
	}
	
	if (document.getElementById('GL08ACTION13').checked) {
		GL08ACTION13 = "Y";
	}else{
		GL08ACTION13 = "N";
	}
	
	if (document.getElementById('GL08ACTION14').checked) {
		GL08ACTION14 = "Y";
	}else{
		GL08ACTION14 = "N";
	}
	
	if (document.getElementById('GL08ACTION15').checked) {
		GL08ACTION15 = "Y";
	}else{
		GL08ACTION15 = "N";
	}
	
	if (document.getElementById('GL08ACTION16').checked) {
		GL08ACTION16 = "Y";
	}else{
		GL08ACTION16 = "N";
	}
	
	if (document.getElementById('GL08ACTION17').checked) {
		GL08ACTION17 = "Y";
	}else{
		GL08ACTION17 = "N";
	}
	
	if (document.getElementById('GL08ACTION18').checked) {
		GL08ACTION18 = "Y";
	}else{
		GL08ACTION18 = "N";
	}
	
	if (document.getElementById('GL08ACTION19').checked) {
		GL08ACTION19 = "Y";
	}else{
		GL08ACTION19 = "N";
	}
	
	if (document.getElementById('GL08ACTION20').checked) {
		GL08ACTION20 = "Y";
	}else{
		GL08ACTION20 = "N";
	}
	

	$.get('Handler_AddStatus', {GL08STAT:GL08STAT, GL08DESC:GL08DESC, GL08ACTION1:GL08ACTION1,GL08ACTION2:GL08ACTION2,GL08ACTION3:GL08ACTION3,GL08ACTION4:GL08ACTION4,GL08ACTION5:GL08ACTION5,GL08ACTION6:GL08ACTION6
		,GL08ACTION7:GL08ACTION7,GL08ACTION8:GL08ACTION8,GL08ACTION9:GL08ACTION9,GL08ACTION10:GL08ACTION10,GL08ACTION11:GL08ACTION11
		,GL08ACTION12:GL08ACTION12,GL08ACTION13:GL08ACTION13,GL08ACTION14:GL08ACTION14,GL08ACTION15:GL08ACTION15,GL08ACTION16:GL08ACTION16,GL08ACTION17:GL08ACTION17
		,GL08ACTION18:GL08ACTION18,GL08ACTION19:GL08ACTION19,GL08ACTION20:GL08ACTION20}, function(responseText) {
		//alert("test 3");
		$('#ajaxResponse').text(responseText);
	});
		
	}
	

function update() {
	//alert("Update Patron Status");
	var GL08STAT = $('#GL08STAT').val();
	var GL08DESC = $('#GL08DESC').val();
	var status = null;

	if (document.getElementById('GL08ACTION1').checked) {
		GL08ACTION1 = "Y";
	}else{
		GL08ACTION1 = "N";
	}
	    
	if (document.getElementById('GL08ACTION2').checked) {
		GL08ACTION2 = "Y";
	}else{
		GL08ACTION2 = "N";
	}
	
	if (document.getElementById('GL08ACTION3').checked) {
		GL08ACTION3 = "Y";
	}else{
		GL08ACTION3 = "N";
	}
	
	if (document.getElementById('GL08ACTION4').checked) {
		GL08ACTION4 = "Y";
	}else{
		GL08ACTION4 = "N";
	}
	
	if (document.getElementById('GL08ACTION5').checked) {
		GL08ACTION5 = "Y";
	}else{
		GL08ACTION5 = "N";
	}
	
	if (document.getElementById('GL08ACTION6').checked) {
		GL08ACTION6 = "Y";
	}else{
		GL08ACTION6 = "N";
	}
	
	if (document.getElementById('GL08ACTION7').checked) {
		GL08ACTION7 = "Y";
	}else{
		GL08ACTION7 = "N";
	}
	
	if (document.getElementById('GL08ACTION8').checked) {
		GL08ACTION8 = "Y";
	}else{
		GL08ACTION8 = "N";
	}
	
	if (document.getElementById('GL08ACTION9').checked) {
		GL08ACTION9 = "Y";
	}else{
		GL08ACTION9 = "N";
	}
	
	if (document.getElementById('GL08ACTION10').checked) {
		GL08ACTION10 = "Y";
	}else{
		GL08ACTION10 = "N";
	}
	
	if (document.getElementById('GL08ACTION11').checked) {
		GL08ACTION11 = "Y";
	}else{
		GL08ACTION11 = "N";
	}
	
	if (document.getElementById('GL08ACTION12').checked) {
		GL08ACTION12 = "Y";
	}else{
		GL08ACTION12 = "N";
	}
	
	if (document.getElementById('GL08ACTION13').checked) {
		GL08ACTION13 = "Y";
	}else{
		GL08ACTION13 = "N";
	}
	
	if (document.getElementById('GL08ACTION14').checked) {
		GL08ACTION14 = "Y";
	}else{
		GL08ACTION14 = "N";
	}
	
	if (document.getElementById('GL08ACTION15').checked) {
		GL08ACTION15 = "Y";
	}else{
		GL08ACTION15 = "N";
	}
	
	if (document.getElementById('GL08ACTION16').checked) {
		GL08ACTION16 = "Y";
	}else{
		GL08ACTION16 = "N";
	}
	
	if (document.getElementById('GL08ACTION17').checked) {
		GL08ACTION17 = "Y";
	}else{
		GL08ACTION17 = "N";
	}
	
	if (document.getElementById('GL08ACTION18').checked) {
		GL08ACTION18 = "Y";
	}else{
		GL08ACTION18 = "N";
	}
	
	if (document.getElementById('GL08ACTION19').checked) {
		GL08ACTION19 = "Y";
	}else{
		GL08ACTION19 = "N";
	}
	
	if (document.getElementById('GL08ACTION20').checked) {
		GL08ACTION20 = "Y";
	}else{
		GL08ACTION20 = "N";
	}
	
	


	$.get('UpdatePatronStatus', {GL08STAT:GL08STAT, GL08DESC:GL08DESC, GL08ACTION1:GL08ACTION1,GL08ACTION2:GL08ACTION2,GL08ACTION3:GL08ACTION3,GL08ACTION4:GL08ACTION4,GL08ACTION5:GL08ACTION5,GL08ACTION6:GL08ACTION6
		,GL08ACTION7:GL08ACTION7,GL08ACTION8:GL08ACTION8,GL08ACTION9:GL08ACTION9,GL08ACTION10:GL08ACTION10,GL08ACTION11:GL08ACTION11
		,GL08ACTION12:GL08ACTION12,GL08ACTION13:GL08ACTION13,GL08ACTION14:GL08ACTION14,GL08ACTION15:GL08ACTION15,GL08ACTION16:GL08ACTION16,GL08ACTION17:GL08ACTION17
		,GL08ACTION18:GL08ACTION18,GL08ACTION19:GL08ACTION19,GL08ACTION20:GL08ACTION20}, function(responseText) {
		//alert("test 3");
		$('#ajaxResponse').text(responseText);
	});
		
	}

function toUppercase(){
	
	//alert("testing");
	var x = document.getElementById("GL08DESC");
	x.value = x.value.toUpperCase();
	
	
}
