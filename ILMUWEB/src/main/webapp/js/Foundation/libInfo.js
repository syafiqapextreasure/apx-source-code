$(document).ready(function() {
	
	$('#libInfoForm').bootstrapValidator({
		 framework: 'bootstrap',
		 excluded: [':disabled'],
		 icon: {
	            valid: 'glyphicon glyphicon-ok',
	            invalid: 'glyphicon glyphicon-remove',
	            validating: 'glyphicon glyphicon-refresh'
	        },
	        fields: {
	        	GL28LIBSYMBOL: {
	                validators: {
	                    notEmpty: {
	                        message: 'The Library Symbol is required'
	                    }
	                }
	            }  
	        }  
	    });
	
	/*$("input[name=GL11FAX]").keypress(function (e) {
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
	
	$("input[name=GL11POSCODE], input[name=GL11STAFF]").keypress(function (e) {
        if (e.which != 8 && e.which != 0 && (e.which < 48 || e.which > 57)) {
            return false;
        }
	});*/
	
});

/*function saveLibInfo() {
	
	var LibraryName = $('#GL28NAME').val();
	var OrganizationName = $("#GL28ORGNAME").val();
	var LibrarySymbol = $("#GL28LIBSYMBOL").val();
	var BranchCode = $("#GL28BRNC").val();
	var Address1 = $("#GL28ADD1").val();
	var Address2 = $("#GL28ADD2").val();
	var Address3 = $("#GL28ADD3").val();
	var Postcode = $("#GL28POSCODE").val();
	var Town = $("#GL28TOWN").val();
	var Telephone = $("#GL28TEL").val();
	var Fax = $("#GL28FAX").val();
	var HeadLibrarian = $
	
	$.get('Handler_SaveLibInfo', {}, function(responseText) {
		 swal("Successful","The department code successfully added.", "success" );
			$('.swal2-confirm').click(function(){
				location.reload(); 
			});
	});
	
}*/