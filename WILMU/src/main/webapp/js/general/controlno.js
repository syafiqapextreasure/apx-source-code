$(document).ready(function() {
	
	/////setting display
	
	///action All, bind, supp, pub
	

	var vendorSelection = $(".controlSelection").val();
	
	$("#input-contorlNo").attr('maxlength','10');
	
	switch(vendorSelection) {
		  case 'm':
			$("<label />", {
			  id : "labelcontrolno",
			  insertAfter: ".controlnodisplay", // (or use appendTo: to insert into it)
			  append: [' Control No'] // include our $input and also some text description
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
			  insertAfter: ".controlnodisplay", // (or use appendTo: to insert into it)
			  append: [$input, ' Control No'] // include our $input and also some text description
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
			  insertAfter: ".controlnodisplay", // (or use appendTo: to insert into it)
			  append: [$input, ' Control No'] // include our $input and also some text description
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
					$("#input-contorlNo").prop( "disabled", false);
					$('.scontrolno').prop('disabled', false);
					
			    } else {
					$('#Reterive').prop('disabled', false);
					$("#input-contorlNo").prop( "disabled", true);
					$('.scontrolno').prop('disabled', true);
					$("#input-contorlNo").val('');
					$(".div-ctrolNo").empty();
					$("#input-contorlNo").css("border", "");
			
					if($("#acqStat").val() == ""){
						$('#Reterive').prop('disabled', true);
					}
			    }
			}
		    
		});
	
		///30092021
		/*$("#input-contorlNo").keyup(function(e){
			var scontrolno = $("#input-contorlNo").val();
			if(scontrolno.length >=1){
				$('#Reterive').prop('disabled', false);
			}else{
				$('#Reterive').prop('disabled', true);
			}
		});*/
		


});