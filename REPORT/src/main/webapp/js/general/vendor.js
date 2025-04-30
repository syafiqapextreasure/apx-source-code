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
		
		
		////check radio button checked or not
		
		$('input:radio[name="chkBoxSearchCateria"]').change(function(){
		    if($(this).val() == 'vendCode'){
				
		        $('#Reterive').prop('disabled', false);
		    }
		});
		
		/*case 'orderNo':
				$("#input-startDoc, #input-endDoc").prop('disabled', false);
				
				$("#input-startDate, #input-endDate, #acct, #mode").prop('disabled', true);
				$("#input-vendorCode, .vendorCode").prop('disabled', true);
	
		      break;
		    case 'orderMode':
				$("#mode, #acct").prop("selectedIndex",-1);
				$("#mode").prop('disabled', false);
			
				$("#input-startDate, #input-endDate, #acct").prop('disabled', true);
				$("#input-startDoc, #input-endDoc, #input-vendorCode, .vendorCode").prop('disabled', true);
	
		      break;
			case 'acct':
				$("#mode, #acct").prop("selectedIndex",-1);
				$("#acct").prop('disabled', false);
				
				$("#input-startDate, #input-endDate, #mode").prop('disabled', true);
				$("#input-startDoc, #input-endDoc, #input-vendorCode, .vendorCode").prop('disabled', true);
	
		      break;*/
		
		
		$('input[type=radio][name=chkBoxSearchCateria]').on('change', function() {
			
			alert("sdfds");
			switch ($(this).val()) {
			 case 'vendCode':
				$("#input-vendorCode, .vendorCode").prop('disabled', false);
				$(':radio:not(:checked)').each(function(){  
					
					var value = $(this).val();
					
					if(value == 'orderNo'){
						$("#input-startDate, #input-endDate, #acct, #mode").prop('disabled', true);
						$("#input-startDoc, #input-endDoc, #input-vendorCode, .vendorCode").prop('disabled', true);
					}/*else if(value == 'pateCate'){
						$('#patronCate').multiselect("disable");
					}else{
						$("#"+value).multiselect("disable");
					}*/
					
					
				});
		      break;
			 default:

				$("#input-vendorCode, .vendorCode").prop('disabled', true);
			}
		});
		
		

});