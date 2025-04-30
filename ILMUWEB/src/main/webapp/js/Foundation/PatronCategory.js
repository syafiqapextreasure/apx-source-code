$(document).ready(function() {
	/*$('#GL07CATE').blur(function(event) {
		//alert("test");
		var id = $('#GL07CATE').val();
		//alert("test 2");
		$.get('CheckingPatCate', {
			GL07CATE : id
		}, function(responseText) {
			//alert("test 3");
			$('#ajaxResponse').text(responseText);
		});
	});*/

	$('#GL07RENEWGRC').keyup(function(e)
                                {
	  if (/\D/g.test(this.value))
	  {
	    // Filter non-digits from input value.
	    this.value = this.value.replace(/\D/g, '');
	  }
	});


	$('#GL07RENEWFEE').keypress(function(event) {
      if ((event.which != 46 || $(this).val().indexOf('.') != -1) && (event.which < 48 || event.which > 57)) {
        event.preventDefault();
      }
    });

	
	 $('#patCatForm').bootstrapValidator({
		 framework: 'bootstrap',
		 excluded: [':disabled'],
		 icon: {
	            valid: 'glyphicon glyphicon-ok',
	            invalid: 'glyphicon glyphicon-remove',
	            validating: 'glyphicon glyphicon-refresh'
	        },
	        fields: {
	        	GL07CATE: {
	                validators: {
	                    notEmpty: {
	                        message: 'The Patron Category is required'
	                    },
	                    remote: {
	                    	type: "GET",
	                        url: 'CheckingPatCate',
	                        data: function(validator) {
	                            return {
	                            	GL07CATE: validator.getFieldElements('GL07CATE').val(),
	                            };
	                        },
	                        message: 'Patron Category already exist!',
	                    }
	                }
	            },
	            GL07DESC: {
	                validators: {
	                    notEmpty: {
	                        message: 'The Description is required'
	                    }
	                }
	            },
	            GL07MAXACCT: {
	                validators: {
	                    notEmpty: {
	                        message: 'The Maximum Patron Account Allowed is required'
	                    }
	                }
	            }  
	        }  
	    });
	 
	 $("input[name=GL07CATE]").attr('maxlength','3');
	 $("input[name=GL07DESC]").attr('maxlength','50');
	
	$('body').on('hidden.bs.modal', '.modal', function () {
	     $(this).removeData('bs.modal');
	});
	
	radioBtn = $("input[name=Overdue]").val();
	//alert(radioBtn + " radioBtn");
	switch(radioBtn){
    case "Y":
    	$("input[name=GL07ALLOWOVD][value='Y']").prop("checked",true);
   	 break;
    default:
    	$("input[name=GL07ALLOWOVD][value='N']").prop("checked",true);
	}
	
	radioBtn2 = $("input[name=Reserve]").val();
	//alert(radioBtn2 + " radioBtn2");
	switch(radioBtn2){
    case "Y":
    	$("input[name=GL07ALLOWRSV][value='Y']").prop("checked",true);
   	 break;
    default:
    	$("input[name=GL07ALLOWRSV][value='N']").prop("checked",true);
	}
	
	 $('#subplan').change(function() {
	        if (!$(this).is(':checked')) {
	        	//alert("qwe");
	        	$('#GL07MAXACCT').prop("disabled", true); 
	        	$('#GL07MAXACCT').val("");
	        }else{
	        	//alert("qwe2");
	        	$('#GL07MAXACCT').prop("disabled", false); 
	        }
	    });

});

/*$(document).ready(function () {
	   $("#subplan").click(function () {
		  if($(this).is(":checked")){
	      $('#GL07MAXACCT').attr("disabled", false);
		  }
		  else{
			  $('#GL07MAXACCT').attr("disabled", true);
		  }
	   });
});*/

function toUppercase(){
	
	//alert("testing");
	var x = document.getElementById("GL07CATE");
	x.value = x.value.toUpperCase();
	var x = document.getElementById("GL07DESC");
	x.value = x.value.toUpperCase();
	
	
}


function addPatCate() {
	//alert("Masuk Javasript");
	var GL07CATE = $('#GL07CATE').val();
	var GL07DESC = $('#GL07DESC').val();
	
	if($('#GL07ELIG').val()==null){
		var GL07ELIG = "0";
		
	}else{
		var GL07ELIG = $('#GL07ELIG').val();
	}
	
	if($('#GL07MAXFINE').val()==null){
		var GL07MAXFINE = "0";
	}else{
		var GL07MAXFINE = $('#GL07MAXFINE').val();
	}
	
	if($('#GL07FINELIMIT').val()==null){
		var GL07FINELIMIT = "0";
	}else{
		var GL07FINELIMIT = $('#GL07FINELIMIT').val();
	}
	
	if($('#GL07ILLOUT').val()==null){
		var GL07ILLOUT = "0";
	}else{
		var GL07ILLOUT = $('#GL07ILLOUT').val();
	}
	
	if($('#GL07MAXRESV').val()==null){
		var GL07MAXRESV = "0";
	}else{
		var GL07MAXRESV = $('#GL07MAXRESV').val();
	}
	
	
	if (document.getElementById('GL07ALLOWOVD').checked) {
		GL07ALLOWOVD = "Y";
	}else {
		GL07ALLOWOVD = "N";
	}
	
	if (document.getElementById('GL07ALLOWRSV').checked) {
		GL07ALLOWRSV = "Y";
	}else {
		GL07ALLOWRSV = "N";
	}
	
	if (document.getElementById('subplan').checked) {
		$('#GL07MAXACCT').attr("disabled", false);
		GL07MAXACCT = $('#GL07MAXACCT').val();
	}
	else{
	  $('#GL07MAXACCT').attr("disabled", true);
	  var GL07MAXACCT ="0";
	}

		  
	
	if (document.getElementById('GL07POPDB').checked) {
		GL07POPDB = "Y";
	}else{
		GL07POPDB = "N";
	}
	
	if (document.getElementById('GL07RATER').checked) {
		GL07RATER = "Y";
	}else{
		GL07RATER = "N";
	}
	
	if (document.getElementById('GL07EMAIL').checked) {
		GL07EMAIL = "Y";
	}else{
		GL07EMAIL = "N";
	}
	
	if (document.getElementById('GL07MODEM').checked) {
		GL07MODEM = "Y";
	}else{
		GL07MODEM = "N";
	}
	
	if (document.getElementById('GL07SCHAR').checked) {
		GL07SCHAR = "Y";
	}else{
		GL07SCHAR = "N";
	}
	
	if (document.getElementById('GL07BRFORC').checked) {
		GL07BRFORC = "Y";
	}else{
		GL07BRFORC = "N";
	}
	
	if (document.getElementById('GL07ARTXN').checked) {
		GL07ARTXN = "Y";
	}else{
		GL07ARTXN = "N";
	}
	
	if (document.getElementById('GL07DCFORC').checked) {
		GL07DCFORC = "Y";
	}else{
		GL07DCFORC = "N";
	}
	

	$.get('Handler_AddPatCate', {GL07CATE:GL07CATE, GL07DESC:GL07DESC, 
		GL07ELIG:GL07ELIG,GL07MAXFINE:GL07MAXFINE,GL07FINELIMIT:GL07FINELIMIT,
		GL07ILLOUT:GL07ILLOUT, GL07MAXRESV:GL07MAXRESV, GL07ALLOWOVD:GL07ALLOWOVD,
		GL07ALLOWRSV:GL07ALLOWRSV, GL07MAXACCT:GL07MAXACCT,GL07POPDB:GL07POPDB,
		GL07RATER:GL07RATER,GL07EMAIL:GL07EMAIL,GL07MODEM:GL07MODEM,GL07SCHAR:GL07SCHAR,
		GL07BRFORC:GL07BRFORC,GL07ARTXN:GL07ARTXN,GL07DCFORC:GL07DCFORC}, function(responseText) {
		//alert("test 3");
		$('#ajaxResponse').text(responseText);
	});
		
	}
	

function updatePatCat() {
	var GL07CATE = $('#GL07CATE').val();
	var GL07DESC = $('#GL07DESC').val();
	
	if($('#GL07ELIG').val()==null){
		var GL07ELIG = "0";
		
	}else{
		var GL07ELIG = $('#GL07ELIG').val();
	}
	
	if($('#GL07MAXFINE').val()==null){
		var GL07MAXFINE = "0";
	}else{
		var GL07MAXFINE = $('#GL07MAXFINE').val();
	}
	
	if($('#GL07FINELIMIT').val()==null){
		var GL07FINELIMIT = "0";
	}else{
		var GL07FINELIMIT = $('#GL07FINELIMIT').val();
	}
	
	if($('#GL07ILLOUT').val()==null){
		var GL07ILLOUT = "0";
	}else{
		var GL07ILLOUT = $('#GL07ILLOUT').val();
	}
	
	if($('#GL07MAXRESV').val()==null){
		var GL07MAXRESV = "0";
	}else{
		var GL07MAXRESV = $('#GL07MAXRESV').val();
	}
	
	
	if (document.getElementById('GL07ALLOWOVD').checked) {
		GL07ALLOWOVD = "Y";
	}else {
		GL07ALLOWOVD = "N";
	}
	
	if (document.getElementById('GL07ALLOWRSV').checked) {
		GL07ALLOWRSV = "Y";
	}else {
		GL07ALLOWRSV = "N";
	}
	
	if (document.getElementById('subplan').checked) {
		$('#GL07MAXACCT').attr("disabled", false);
		GL07MAXACCT = $('#GL07MAXACCT').val();
	}
	else{
	  $('#GL07MAXACCT').attr("disabled", true);
	  var GL07MAXACCT ="0";
	}

		  
	
	if (document.getElementById('GL07POPDB').checked) {
		GL07POPDB = "Y";
	}else{
		GL07POPDB = "N";
	}
	
	if (document.getElementById('GL07RATER').checked) {
		GL07RATER = "Y";
	}else{
		GL07RATER = "N";
	}
	
	if (document.getElementById('GL07EMAIL').checked) {
		GL07EMAIL = "Y";
	}else{
		GL07EMAIL = "N";
	}
	
	if (document.getElementById('GL07MODEM').checked) {
		GL07MODEM = "Y";
	}else{
		GL07MODEM = "N";
	}
	
	if (document.getElementById('GL07SCHAR').checked) {
		GL07SCHAR = "Y";
	}else{
		GL07SCHAR = "N";
	}
	
	if (document.getElementById('GL07BRFORC').checked) {
		GL07BRFORC = "Y";
	}else{
		GL07BRFORC = "N";
	}
	
	if (document.getElementById('GL07ARTXN').checked) {
		GL07ARTXN = "Y";
	}else{
		GL07ARTXN = "N";
	}
	
	if (document.getElementById('GL07DCFORC').checked) {
		GL07DCFORC = "Y";
	}else{
		GL07DCFORC = "N";
	}


	$.get('UpdatePatCate', {GL07CATE:GL07CATE, GL07DESC:GL07DESC, 
		GL07ELIG:GL07ELIG,GL07MAXFINE:GL07MAXFINE,GL07FINELIMIT:GL07FINELIMIT,
		GL07ILLOUT:GL07ILLOUT, GL07MAXRESV:GL07MAXRESV, GL07ALLOWOVD:GL07ALLOWOVD,
		GL07ALLOWRSV:GL07ALLOWRSV, GL07MAXACCT:GL07MAXACCT,GL07POPDB:GL07POPDB,
		GL07RATER:GL07RATER,GL07EMAIL:GL07EMAIL,GL07MODEM:GL07MODEM,GL07SCHAR:GL07SCHAR,
		GL07BRFORC:GL07BRFORC,GL07ARTXN:GL07ARTXN,GL07DCFORC:GL07DCFORC}, function(responseText) {
		//alert("test 3");
		$('#ajaxResponse').text(responseText);
	});
		
	}
