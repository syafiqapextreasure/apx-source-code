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
		    case 'mm':
			$("<label />", {
			  id : "labelPatrId",
			  insertAfter: ".patriddisplay", // (or use appendTo: to insert into it)
			  append: ['ID Pengguna'] // include our $input and also some text description
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
			  append: [$input, ' Patron ID'],// include our $input and also some text description
			  //class:"labelPatronId",
			});
		    break;
		    case 'cOffiId':
			var $input = $('<input />', {
			  class : "form-check-input",
			  type : "checkbox",
			  name : "chkBoxSearchCateria",
			  id : "chkBoxSearchCateria",
			  value : "patrID"
			});
			
			$("<label />", {
			  insertAfter: ".patriddisplay", // (or use appendTo: to insert into it)
			  append: [$input, ' Officer ID'],// include our $input and also some text description
			  //class:"labelPatronId",
			});
		    break;
			case 'cReqId':
			var $input = $('<input />', {
			  class : "form-check-input",
			  type : "checkbox",
			  name : "chkBoxSearchCateria",
			  id : "chkBoxSearchCateria",
			  value : "patrID"
			});
			
			$("<label />", {
			  insertAfter: ".patriddisplay", // (or use appendTo: to insert into it)
			  append: [$input, ' Requestor ID'],// include our $input and also some text description
			  //class:"labelPatronId",
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
			$(".vendorCode, .patronid").removeAttr('href');
		    break;
		    case 'libPatronId':
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
			  append: [$input, ' Lib Patron ID'] // include our $input and also some text description
			});
			
			$("#lblPatronID, .patronid").prop( "disabled", true );
			$(".vendorCode, .patronid").removeAttr('href');
		    break;

		}  
		
		
		////check radio button checked or not
		/*
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
		});*/
		
		////checkbox
		$("input[type=checkbox][name=chkBoxSearchCateria]").on('change', function () {
			var self = $(this);
			
			switch ($(this).val()) {
			 case 'patrID':
				if (self.is(":checked")) {
					$('#Reterive').prop('disabled', true);
					$("#lblPatronID").prop( "disabled", false);
					$('.patronid').prop('disabled', false);
			    } else {
					$('#Reterive').prop('disabled', false);
					$("#lblPatronID").prop( "disabled", true);
					$('.patronid').prop('disabled', true);
					$(".patronid").removeAttr('href');
					$("#lblPatronID").val('');
					$(".lblName").empty();
					if($("#acqStat").val() == ""){
						$('#Reterive').prop('disabled', true);
					}
			    }
			}
		    
		});
		
		
		////radiobutton
		$("input[type=radio][name=chkBoxSearchCateria]").on('change', function () {
			var self = $(this);
			
			switch ($(this).val()) {
			 case 'patrID':
				if (self.is(":checked")) {
					$('#Reterive').prop('disabled', true);
					$("#lblPatronID").prop( "disabled", false);
					$('.patronid').prop('disabled', false);
					 $("#inputreservid, #inputemailbooking").prop( "disabled", true );
					$("#inputreservid, #lblPatronID").val("");
					
			    } else {
					$('#Reterive').prop('disabled', false);
					$("#lblPatronID").prop( "disabled", true);
					$('.patronid').prop('disabled', true);
					$(".patronid").removeAttr('href');
					$("#lblPatronID").val('');
					$(".lblName").empty();
					if($("#acqStat").val() == ""){
						$('#Reterive').prop('disabled', true);
					}
			    }
			}
		    
		});
	
		
		$("#lblPatronID").keyup(function(e){
			var patron = $("#lblPatronID").val();
			if(patron.length > 1){
				$('#Reterive').prop('disabled', false);
			}else{
				$('#Reterive').prop('disabled', true);
			}
		});
		
		
		
		

});