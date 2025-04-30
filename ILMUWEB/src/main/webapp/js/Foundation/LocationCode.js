$(document).ready(function() {
	$("#GL05BRNC").focus();
	
	/*$('#GL05LOCA').blur(function(event) {
		//alert("test");
		var id = $('#GL05LOCA').val();
		//alert("test 2");
		$.get('CheckingLocation', {
			GL05LOCA : id
		}, function(responseText) {
			//alert("test 3");
			$('#ajaxResponse').text(responseText);
		});
	});*/
	
	$('#locaForm').bootstrapValidator({
		 framework: 'bootstrap',
		 excluded: [':disabled'],
		 icon: {
	            valid: 'glyphicon glyphicon-ok',
	            invalid: 'glyphicon glyphicon-remove',
	            validating: 'glyphicon glyphicon-refresh'
	        },
	        fields: {
	        	GL05BRNC: {
	                validators: {
	                    notEmpty: {
	                        message: 'The Branch is required'
	                    }
	                }
	            },
	        	GL05LOCA: {
	                validators: {
	                    notEmpty: {
	                        message: 'The Location is required'
	                    },
	                    remote: {
	                    	type: "GET",
	                        url: 'CheckingLocation',
	                        data: function(validator) {
	                            return {
	                            	GL05LOCA: validator.getFieldElements('GL05LOCA').val(),
	                            };
	                        },
	                        message: 'Location already exist!',
	                    }
	                }
	            },
	            GL05DESC: {
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
	var x = document.getElementById("GL05LOCA");
	x.value = x.value.toUpperCase();
	var x = document.getElementById("GL05DESC");
	x.value = x.value.toUpperCase();
	var x = document.getElementById("GL05DISPLAY");
	x.value = x.value.toUpperCase();
	var x = document.getElementById("GL05SUBJECT");
	x.value = x.value.toUpperCase();
	
}


function addLocation() {
	
	var GL05BRNC = $('#GL05BRNC').val();
	var GL05LOCA = $('#GL05LOCA').val();
	var GL05DESC = $('#GL05DESC').val();
	var GL05DISPLAY = $('#GL05DISPLAY').val();
	var GL05SUBJECT = $('#GL05SUBJECT').val();
	var GL05MATCAP = $('#GL05MATCAP').val();
	var GL05LAYOUT = $('#GL05LAYOUT').val();
	var GL05IPADD = $('#GL05IPADD').val();
	var GL05NOSERVER = $('#GL05NOSERVER').val();
	var GL05LNPRT = $('#GL05LNPRT').val();
	var GL05NOTER = $('#GL05NOTER').val();
	var GL05NOPC = $('#GL05NOPC').val();
	var GL05LJPRT = $('#GL05LJPRT').val();
	var GL05MODEM = $('#GL05MODEM').val();
	var GL05DMPRT = $('#GL05DMPRT').val();
	
	
	if($('#GL05MATCAP').val()==null){
		document.getElementById('GL05MATCAP').value = " ";
	}
	
	if (document.getElementById('GL05MMEDIA').checked) {
		GL05MMEDIA = "Y";
	}else{
		GL05MMEDIA = "N";
	}
	
	if (document.getElementById('GL05SDI').checked) {
		GL05SDI = "Y";
	}else{
		GL05SDI = "N";
	}
	
	if (document.getElementById('GL05SDDS').checked) {
		GL05SDDS = "Y";
	}else{
		GL05SDDS = "N";
	}
	
	if (document.getElementById('GL05JARING').checked) {
		GL05JARING = "Y";
	}else{
		GL05JARING = "N";
	}
	
	if (document.getElementById('GL05CDROM').checked) {
		GL05CDROM = "Y";
	}else{
		GL05CDROM = "N";
	}
	
	if (document.getElementById('GL05IRL').checked) {
		GL05IRL = "Y";
	}else{
		GL05IRL = "N";
	}
	
	if (document.getElementById('GL05NST').checked) {
		GL05NST = "Y";
	}else{
		GL05NST = "N";
	}
	

	$.get('Handler_AddLocation', {GL05BRNC:GL05BRNC,GL05LOCA:GL05LOCA, GL05DESC:GL05DESC, GL05DISPLAY:GL05DISPLAY,GL05SUBJECT:GL05SUBJECT,GL05MATCAP:GL05MATCAP,GL05LAYOUT:GL05LAYOUT,GL05IPADD:GL05IPADD,GL05NOSERVER:GL05NOSERVER,GL05LNPRT:GL05LNPRT,GL05NOTER:GL05NOTER,GL05LJPRT:GL05LJPRT,GL05NOPC:GL05NOPC,GL05DMPRT:GL05DMPRT,GL05MODEM:GL05MODEM,GL05MMEDIA:GL05MMEDIA,GL05SDI:GL05SDI,GL05SDDS:GL05SDDS,GL05JARING:GL05JARING,GL05CDROM:GL05CDROM,GL05IRL:GL05IRL,GL05NST:GL05NST}, function(responseText) {
		swal({
		    title: "Success!",
		    text:  "You are now following",
		    type: "success",
		    timer: 6000,
		    showConfirmButton: false
		}), function(){
		    location.reload();
		}
	});
		
	}
	

function updateLocation() {
	//alert("update location");
	
	var GL05BRNC = $('#GL05BRNC').val();
	//alert(GL05BRNC);
	var GL05LOCA = $('#GL05LOCA').val();
	var GL05DESC = $('#GL05DESC').val();
	var GL05DISPLAY = $('#GL05DISPLAY').val();
	var GL05SUBJECT = $('#GL05SUBJECT').val();
	var GL05MATCAP = $('#GL05MATCAP').val();
	var GL05LAYOUT = $('#GL05LAYOUT').val();
	var GL05IPADD = $('#GL05IPADD').val();
	var GL05NOSERVER = 0;
	var GL05LNPRT = 0;
	var GL05NOTER = 0;
	var GL05NOPC = 0;
	var GL05LJPRT = 0;
	var GL05MODEM = 0;
	var GL05DMPRT = 0;
	
	if($('#GL05NOSERVER').val()!=null){
		GL05NOSERVER = $('#GL05NOSERVER').val();
	}
	
	if($('#GL05LNPRT').val()!=null){
		GL05LNPRT= $('#GL05LNPRT').val()
	}
	
	if($('#GL05NOTER').val()!=null){
		GL05NOTER = $('#GL05NOTER').val()
	}
	if($('#GL05NOPC').val()!=null){
		GL05NOPC = $('#GL05NOPC').val()
	}
	if($('#GL05LJPRT').val()!=null){
		GL05LJPRT = $('#GL05LJPRT').val()
	}
	
	if($('#GL05MODEM').val()!=null){
		GL05MODEM = $('#GL05MODEM').val()
	}
	if($('#GL05DMPRT').val()!=null){
		GL05DMPRT = $('#GL05DMPRT').val()
	}
	
	if (document.getElementById('GL05MMEDIA').checked) {
		GL05MMEDIA = "Y";
	}else{
		GL05MMEDIA = "N";
	}
	
	if (document.getElementById('GL05SDI').checked) {
		GL05SDI = "Y";
	}else{
		GL05SDI = "N";
	}
	
	if (document.getElementById('GL05SDDS').checked) {
		GL05SDDS = "Y";
	}else{
		GL05SDDS = "N";
	}
	
	if (document.getElementById('GL05JARING').checked) {
		GL05JARING = "Y";
	}else{
		GL05JARING = "N";
	}
	
	if (document.getElementById('GL05CDROM').checked) {
		GL05CDROM = "Y";
	}else{
		GL05CDROM = "N";
	}
	
	if (document.getElementById('GL05IRL').checked) {
		GL05IRL = "Y";
	}else{
		GL05IRL = "N";
	}
	
	if (document.getElementById('GL05NST').checked) {
		GL05NST = "Y";
	}else{
		GL05NST = "N";
	}
	

	$.get('UpdateLocation', {GL05BRNC:GL05BRNC,GL05LOCA:GL05LOCA, GL05DESC:GL05DESC, GL05DISPLAY:GL05DISPLAY,GL05SUBJECT:GL05SUBJECT,GL05MATCAP:GL05MATCAP,GL05LAYOUT:GL05LAYOUT,GL05IPADD:GL05IPADD,GL05NOSERVER:GL05NOSERVER,GL05LNPRT:GL05LNPRT,GL05NOTER:GL05NOTER,GL05LJPRT:GL05LJPRT,GL05NOPC:GL05NOPC,GL05DMPRT:GL05DMPRT,GL05MODEM:GL05MODEM,GL05MMEDIA:GL05MMEDIA,GL05SDI:GL05SDI,GL05SDDS:GL05SDDS,GL05JARING:GL05JARING,GL05CDROM:GL05CDROM,GL05IRL:GL05IRL,GL05NST:GL05NST}, function(responseText) {
		//alert("test 3");
		//$('#ajaxResponse').text(responseText);
	});
		
	}
