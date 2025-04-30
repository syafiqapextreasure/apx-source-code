$(document).ready(function() {
	$("#GL39CODE").focus();
	
	/*$('#GL39CODE').blur(function(event) {

		var id = $('#GL39CODE').val();

		$.get('CheckingVendor', {
			GL39CODE : id
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
	
	$('#vendorForm').bootstrapValidator({
		 framework: 'bootstrap',
		 excluded: [':disabled'],
		 icon: {
	            valid: 'glyphicon glyphicon-ok',
	            invalid: 'glyphicon glyphicon-remove',
	            validating: 'glyphicon glyphicon-refresh'
	        },
	        fields: {
	        	GL39CODE: {
	                validators: {
	                    notEmpty: {
	                        message: 'The Vendor Code is required'
	                    },
	                    remote: {
	                    	type: "GET",
	                        url: 'CheckingVendor',
	                        data: function(validator) {
	                            return {
	                            	GL39CODE: validator.getFieldElements('GL39CODE').val(),
	                            };
	                        },
	                        message: 'Vendor Code already exist!',
	                    }
	                }
	            },
	            GL39NAME: {
	                validators: {
	                    notEmpty: {
	                        message: 'The Vendor Name is required'
	                    }
	                }
	            }  
	        }  
	    });
		
	$("input[name=GL39FAX]").keypress(function (e) {
        if (e.which != 8 && e.which != 0 && (e.which < 48 || e.which > 57)) {
            return false;
        }
		  var curchr = this.value.length;
        var curval = $(this).val();
        if (curchr == 2) {
            $(this).val(curval + "-");
        }
        $(this).attr('maxlength', '12');
	});
	
	$("input[name=GL39TELNO]").keypress(function (e) {
        if (e.which != 8 && e.which != 0 && (e.which < 48 || e.which > 57)) {
            return false;
        }
        $(this).attr('maxlength', '14');
	});
	/*$('#GL39CODE').blur(function(event) {
		//alert("test");
		var id = $('#GL39CODE').val();
		//alert("test 2");
		$.get('CheckingVendor', {
			GL39CODE : id
		}, function(responseText) {
			//alert("test 3");
			$('#ajaxResponse').text(responseText);
		});
	});*/
	
	$("input[name=GL39CODE]").attr('maxlength','4');
	$("input[name=GL39NAME]").attr('maxlength','70');
	
	$('body').on('hidden.bs.modal', '.modal', function () {
	     $(this).removeData('bs.modal');
	});
	
	
});

function toUppercase(){
	
	//alert("testing");
	var x = document.getElementById("GL39CODE");
	x.value = x.value.toUpperCase();
	var x = document.getElementById("GL39NAME");
	x.value = x.value.toUpperCase();
	
}

document.getElementById("GL39PCODE").addEventListener("keypress", myFunction);
document.getElementById("GL39TELNO").addEventListener("keypress", myFunction);
document.getElementById("GL39FAX").addEventListener("keypress", myFunction);
document.getElementById("GL39ACCNO").addEventListener("keypress", myFunction);

function CompareDate() {
    
	 var GL39CBDATE = $('#GL39CBDATE').val(); //Year, Month, Date
	 var GL39CEDATE = $('#GL39CEDATE').val(); //Year, Month, Date
	 
	 //alert(GL14MEMDATE);
	 //alert(GL14EXPDATE);
	 
	 var smallDateArr = Array();
	 var largeDateArr = Array(); 
	 
	    smallDateArr = GL39CBDATE.split("/");
	    largeDateArr = GL39CEDATE.split("/"); 
	    
	    //alert(smallDateArr);
		//alert(largeDateArr);
	    
	    var smallDt = smallDateArr[0];
	    var smallMt = smallDateArr[1];
	    var smallYr = smallDateArr[2];  
	    var largeDt = largeDateArr[0];
	    var largeMt = largeDateArr[1];
	    var largeYr = largeDateArr[2];
	    
	    if(smallYr>largeYr) 
	    	
	    	swal({   
				text: 'The Contract End should be greater than Contract Begin',
				//type: 'warning',   
				showCancelButton: false,
				
				}).then(function(dismiss) {
					
					if (dismiss === 'cancel') {
					    swal(
					      'Cancelled',
					      'Your imaginary file is safe :)'//,
					      //'error'
					    );
					  }
					})
					
	else if(smallYr<=largeYr && smallMt>largeMt)
		
		swal({   
			text: 'The Contract End should be greater than Contract Begin',
			type: 'warning',   showCancelButton: false,
			
			}).then(function(dismiss) {
				
				if (dismiss === 'cancel') {
				    swal(
				      'Cancelled',
				      'Your imaginary file is safe :)'//,
				     // 'error'
				    );
				  }
				})
				else if(smallYr<=largeYr && smallMt>largeMt && smallDt>largeDt)
		
		swal({   
			text: 'The Contract End should be greater than Contract Begin',
			type: 'warning',   showCancelButton: false,
			
			}).then(function(dismiss) {
				
				if (dismiss === 'cancel') {
				    swal(
				      'Cancelled',
				      'Your imaginary file is safe :)',
				      'error'
				    );
				  }
				})
				
	else if(smallYr<=largeYr && smallMt==largeMt && smallDt>largeDt)
		swal({   
			text: 'The Contract End should be greater than Contract Begin',
			type: 'warning',   showCancelButton: false,
			
			}).then(function(dismiss) {
				
				if (dismiss === 'cancel') {
				    swal(
				      'Cancelled',
				      'Your imaginary file is safe :)',
				      'error'
				    );
				  }
				})
	
	    
	    
}

function myFunction() {
	 var regex = new RegExp("[0-9]+");
	    var key = String.fromCharCode(!event.charCode ? event.which : event.charCode);
	    if (!regex.test(key)) {
	       event.preventDefault();
	       //alert("Please insert number only.");
	       return false;
	       
	    }
}

function addVendor() {
	//alert("Masuk Javasript");
	var GL39CODE = $('#GL39CODE').val();
	var GL39NAME = $('#GL39NAME').val();
	var GL39ADD1 = $('#GL39ADD1').val();
	var GL39ADD2 = $('#GL39ADD2').val();
	var GL39ADD3 = $('#GL39ADD3').val();
	var GL39TELNO = $('#GL39TELNO').val();
	var GL39FAX = $('#GL39FAX').val();
	var GL39PERINC = $('#GL39PERINC').val();
	var GL39DESIG = $('#GL39DESIG').val();
	var GL39CONTNO = $('#GL39CONTNO').val();
	var GL39CBDATE = $('#GL39CBDATE').val();
	var GL39CEDATE = $('#GL39CEDATE').val();
	var GL39REMARK = $('#GL39REMARK').val();
	var GL39ACCNO = $('#GL39ACCNO').val();
	var GL39PCODE = $('#GL39PCODE').val();
	var GL39IPADD = $('#GL39IPADD').val();
	var GL39USERNAME = $('#GL39USERNAME').val();
	var GL39PASSWORD = $('#GL39PASSWORD').val();
	var GL39EMAIL = $('#GL39EMAIL').val();
	var GL39BANK = $('#GL39BANK').val();
	
	if (document.getElementById('GL39BINDER').checked) {
		GL39BINDER = "Y";
	}else {
		GL39BINDER = "N";
	}
	
	if (document.getElementById('GL39PUB').checked) {
		GL39PUB = "Y";
	}else {
		GL39PUB = "N";
	}
	
	if (document.getElementById('GL39SUPPLIER').checked) {
		GL39SUPPLIER = "Y";
	}else {
		GL39SUPPLIER = "N";
	}
	
	if (document.getElementById('GL39INDI').checked) {
		GL39INDI = "Y";
	}else {
		GL39INDI = "N";
	}

	$.get('Handler_AddVendor', {GL39CODE:GL39CODE,GL39NAME:GL39NAME,GL39ADD1:GL39ADD1, GL39ADD2:GL39ADD2, GL39ADD3:GL39ADD3, GL39TELNO:GL39TELNO, GL39FAX:GL39FAX, GL39PERINC:GL39PERINC, GL39DESIG:GL39DESIG, GL39CONTNO:GL39CONTNO, GL39CBDATE:GL39CBDATE, GL39CEDATE:GL39CEDATE, GL39REMARK:GL39REMARK, GL39ACCNO:GL39ACCNO, GL39PCODE:GL39PCODE, GL39IPADD:GL39IPADD, GL39BINDER:GL39BINDER, GL39SUPPLIER:GL39SUPPLIER,
		GL39PUB:GL39PUB, GL39INDI:GL39INDI, GL39USERNAME:GL39USERNAME, GL39PASSWORD:GL39PASSWORD, GL39EMAIL:GL39EMAIL, GL39BANK:GL39BANK}, function(responseText) {
		//alert("test 3");
		$('#ajaxResponse').text(responseText);
	});
		
	}
	

function updateVendor() {
	//alert("Updattettetet");
	var GL39CODE = $('#GL39CODE').val();
	var GL39NAME = $('#GL39NAME').val();
	var GL39ADD1 = $('#GL39ADD1').val();
	var GL39ADD2 = $('#GL39ADD2').val();
	var GL39ADD3 = $('#GL39ADD3').val();
	var GL39TELNO = $('#GL39TELNO').val();
	var GL39FAX = $('#GL39FAX').val();
	var GL39PERINC = $('#GL39PERINC').val();
	var GL39DESIG = $('#GL39DESIG').val();
	var GL39CONTNO = $('#GL39CONTNO').val();
	var GL39CBDATE = $('#GL39CBDATE').val();
	var GL39CEDATE = $('#GL39CEDATE').val();
	var GL39REMARK = $('#GL39REMARK').val();
	var GL39ACCNO = $('#GL39ACCNO').val();
	var GL39PCODE = $('#GL39PCODE').val();
	var GL39IPADD = $('#GL39IPADD').val();
	var GL39USERNAME = $('#GL39USERNAME').val();
	var GL39PASSWORD = $('#GL39PASSWORD').val();
	var GL39EMAIL = $('#GL39EMAIL').val();
	var GL39BANK = $('#GL39BANK').val();
	
	if (document.getElementById('GL39BINDER').checked) {
		GL39BINDER = "Y";
	}else {
		GL39BINDER = "N";
	}
	
	if (document.getElementById('GL39PUB').checked) {
		GL39PUB = "Y";
	}else {
		GL39PUB = "N";
	}
	
	if (document.getElementById('GL39SUPPLIER').checked) {
		GL39SUPPLIER = "Y";
	}else {
		GL39SUPPLIER = "N";
	}
	
	if (document.getElementById('GL39INDI').checked) {
		GL39INDI = "Y";
	}else {
		GL39INDI = "N";
	}

	$.get('UpdateVendor', {GL39CODE:GL39CODE,GL39NAME:GL39NAME,GL39ADD1:GL39ADD1, GL39ADD2:GL39ADD2, GL39ADD3:GL39ADD3, GL39TELNO:GL39TELNO, GL39FAX:GL39FAX, GL39PERINC:GL39PERINC, GL39DESIG:GL39DESIG, GL39CONTNO:GL39CONTNO, GL39CBDATE:GL39CBDATE, GL39CEDATE:GL39CEDATE, GL39REMARK:GL39REMARK, GL39ACCNO:GL39ACCNO, GL39PCODE:GL39PCODE, GL39IPADD:GL39IPADD, GL39BINDER:GL39BINDER, GL39SUPPLIER:GL39SUPPLIER,
		GL39PUB:GL39PUB, GL39INDI:GL39INDI, GL39USERNAME:GL39USERNAME, GL39PASSWORD:GL39PASSWORD, GL39EMAIL:GL39EMAIL, GL39BANK:GL39BANK}, function(responseText) {
		//alert("test 3");
			alert(responseText +"responseText");
		$('#ajaxResponse').text(responseText);
	});
		
	}


