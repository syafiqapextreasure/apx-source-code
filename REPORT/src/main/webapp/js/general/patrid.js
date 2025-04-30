$(document).ready(function() {
	
	/////setting display

	var patrSelection = $(".patrSelection").val();
	
	$("#lblPatronID").attr('maxlength','12');
	
	switch(patrSelection) {
		  case 'm':
			$("<label />", {
			  id : "labelPatrId",
			  insertAfter: ".patriddisplay", // (or use appendTo: to insert into it)
			  append: ['Patron ID'] // include our $input and also some text description
			});
		    break;	
		  case 'c':
			var $input = $('<input />', {
			  class : "form-check-input",
			  type : "checkbox",
			  name : "chkBoxSearchCateria",
			  id : "chkBoxSearchCateria",
			  value : "patrID"
			});
			
			$("<label />", {
			  insertAfter: ".patriddisplay", // (or use appendTo: to insert into it)
			  append: [$input, ' Patron ID'] // include our $input and also some text description
			});
		    break;
		  case 'r':
			var $input = $('<input />', {
			  class : "form-check-input",
			  type : "radio",
			  name : "chkBoxSearchCateria",
			  id : "chkBoxSearchCateria",
			  value : "patrID"
			});
			
			$("<label />", {
			  class : "form-check-input",
			  insertAfter: ".patriddisplay", // (or use appendTo: to insert into it)
			  append: [$input, ' Patron ID'] // include our $input and also some text description
			});
			
			$("#lblPatronID, .patronid").prop( "disabled", true );
		    break;
		}  
		
		
		////check radio button checked or not
		
		$('input:radio[name="chkBoxSearchCateria"]').change(function(){
		    if($(this).val() == 'patrID'){
		       $('#Reterive').prop('disabled', false);
		    }
		});
		
		////checked radio button
		$('input[type=radio][name=chkBoxSearchCateria]').on('change', function() {
			//alert("af"+$(this).val());
			switch ($(this).val()) {
			 case 'patrID':
				$("#lblPatronID, .patronid").prop( "disabled", false );
				$(':radio:not(:checked)').each(function(){  
					
					var value = $(this).val();
					
					if(value == 'itemcate'){
						$('#icat').multiselect("disable");
					}else if(value == 'pateCate'){
						$('#patronCate').multiselect("disable");
					}else{
						$("#"+value).multiselect("disable");
					}
					
					
				});
		      break;
			 default:

				$("#lblPatronID, .patronid").prop( "disabled", true );
			}
		});
		
		//.multiselect("disable");
		/*
						$(':radio:not(:checked)').each(function(){  
				   alert($(this).val());
				});
		var chkBoxSearchCateria = $("input[name='chkBoxSearchCateria']:checked").val();
		alert(chkBoxSearchCateria+"chkBoxSearchCateria");*/
		/*if($("input:radio[name='yourRadioName']").is(":checked")) {
	    	//its checked
	  	}*/
		
		

});