$(document).ready(function() {

	//////////////////////////////////////////////////////////////////////////
	//////////////////////////setup page//////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////
	
	$(".div1vend").removeClass( "col-sm-3 col-md-3" ).addClass( "col-sm-2 col-md-2" );
	$(".div-vendorName1").removeClass( "col-sm-3 col-md-3" ).addClass( "col-sm-2 col-md-2" );
	$(".div1patrid").removeClass( "col-sm-3 col-md-3" ).addClass( "col-sm-2 col-md-2" );
	
	$(".div1control").removeClass( "col-sm-3 col-md-3" ).addClass( "col-sm-2 col-md-2" );

	///////input maxlenght
	$("#startInput, #endInput").attr('maxlength','10');
	
	//table
	//////table setup
	$('#reportTable').DataTable( {
		destroy: true,
		searching: false,
		bLengthChange: false,
		autoWidth: false,
		responsive: true,
	});
	
	jQuery.extend( jQuery.fn.dataTableExt.oSort, {
	    "date-uk-pre": function ( a ) {
	        if (a == null || a == "") {
	            return 0;
	        }
	        var ukDatea = a.split('/');
	        return (ukDatea[2] + ukDatea[1] + ukDatea[0]) * 1;
	    },
	 
	    "date-uk-asc": function ( a, b ) {
	        return ((a < b) ? -1 : ((a > b) ? 1 : 0));
	    },
	 
	    "date-uk-desc": function ( a, b ) {
	        return ((a < b) ? 1 : ((a > b) ? -1 : 0));
	    }
	} );
	
	
	///////////////////// input-startDate set Current Date  //////////////////
	var today = new Date();  
	$('#input-startDate').datepicker({
		//startDate : today,
		//dateFormat : "dd/mm/yyyy",
		format: "dd/mm/yyyy",
		todayBtn: true,
		autoclose: true,
		todayHighlight: true,	
		endDate: new Date(),
		}).on('changeDate', function (selected) {
                    var minDate = new Date(selected.date);
                    minDate.setDate(minDate.getDate() + 1);
                    $('#input-endDate').datepicker('setStartDate', minDate);
	});
	
	///////////////////// input-endDate set Current Date  ////////////////////
	$('#input-endDate').val(moment(today).format("DD/MM/YYYY"));
	$('#input-endDate').datepicker({
		format: "dd/mm/yyyy",
		todayBtn: true,
		autoclose: true,
		todayHighlight: true,
     	endDate: new Date(),
		 }).on('changeDate', function (selected) {
                    var minDate = new Date(selected.date);
                    minDate.setDate(minDate.getDate() - 1);
                    $('#input-startDate').datepicker('setEndDate', minDate);
    });
	
	//////////////range key in number only//////////////////////////////////////
	$("#startInput, #endInput").on("keypress keyup blur",function (event) {    
        $(this).val($(this).val().replace(/[^\d].+/, ""));
         if ((event.which < 48 || event.which > 57)) {
             event.preventDefault();
         }
    });
	
	$('#ordermode, #seStat, #acct, #dept, #seinvstat, #seitemstat').multiselect({
    	allSelectedText: 'All',
   		maxHeight: 200,
   		includeSelectAllOption: true,
		numberDisplayed: 1,
	});
	
	$(" #ordermode, #seStat, #acct, #dept, #seinvstat, #seitemstat, #seordertype").multiselect("disable");
	
	$("#startInput, #endInput").prop( "disabled", true);
	$("#lblPatronID, #input-vendorCode, #input-contorlNo").prop( "disabled", true);
	$('input[name=radioclaimstat]').attr("disabled",true);
	//$('input[name=radioRecordselection]').attr("disabled",true);
	$('input[name=rbClaimStat]').attr("disabled",true);
	$('input:radio[name="rbRrcdSelection"]').prop('checked', true);
	$("input[name=radioRecordselection][value=F]").prop('checked', true);
	
	
	$('.vendorCode, .patronid, .scontrolno').prop('disabled', true);
	$(".vendorCode, .patronid, .scontrolno").removeAttr('href');  
	
	
	
	 $.get('ResultGlflag2', {
		 	id : "CURRENCYFORMAT",
		 	}, function(responseJson) {
			$("#setupCurrency").val(responseJson);
	 });
	
});