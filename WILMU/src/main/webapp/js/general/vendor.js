$(document).ready(function() {
	/////setting display
	///action All, bind, supp, pub
	var vendorSelection = $(".vendorSelection").val();
	
	$("#input-vendorCode").attr('maxlength','4');
	switch(vendorSelection) {
		  case 'm':
			$("<label />", {
			  id : "labelvendorId",
			  insertAfter: ".vendordisplay", // (or use appendTo: to insert into it)
			  append: [' Vendor'] // include our $input and also some text description
			});
		    break;	
		  case 'c':
			var $input = $('<input />', {
			  class : "form-check-input",
			  type : "checkbox",
			  name : "chkBoxSearchCateria",
			  id : "chkBoxSearchCateria",
			  value : "vendCode"
			});
			
			$("<label />", {
			  insertAfter: ".vendordisplay", // (or use appendTo: to insert into it)
			  append: [$input, ' Vendor'] // include our $input and also some text description
			});
		    break;
		  case 'r':
			var $input = $('<input />', {
			  class : "form-check-input",
			  type : "radio",
			  name : "chkBoxSearchCateria",
			  id : "chkBoxSearchCateria",
			  value : "vendCode"
			});
			
			$("<label />", {
			  class : "form-check-input",
			  insertAfter: ".vendordisplay", // (or use appendTo: to insert into it)
			  append: [$input, ' Vendor'] // include our $input and also some text description
			});
		    break;
		}  
		
		
		////checkbox
		$("input[type=checkbox][name=chkBoxSearchCateria]").on('change', function () {
			var self = $(this);
			switch ($(this).val()) {
			 case 'vendCode':
				if (self.is(":checked")) {
					$('#Reterive').prop('disabled', true);
					$("#input-vendorCode").prop( "disabled", false);
					$('.vendorCode').prop('disabled', false);
					
			    } else {
					$('#Reterive').prop('disabled', false);
					$("#input-vendorCode").prop( "disabled", true);
					$('.vendorCode').prop('disabled', true);
					$("#input-vendorCode").val('');
					$(".div-vendorName").empty();
					$("#input-vendorCode").css("border", "");
			
					if($("#acqStat").val() == ""){
						$('#Reterive').prop('disabled', true);
					}
			    }
			}
		    
		});
	
		///30092021
		/*$("#input-vendorCode").keyup(function(e){
			var vendorcode = $("#input-vendorCode").val();
			if(vendorcode.length >=1){
				$('#Reterive').prop('disabled', false);
			}else{
				$('#Reterive').prop('disabled', true);
			}
		});*/
		


});