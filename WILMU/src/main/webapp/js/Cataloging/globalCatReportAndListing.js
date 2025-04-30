$(document).ready(function() {
	
	$(".div1patrid").removeClass( "col-sm-3 col-md-3" ).addClass( "col-sm-2 col-md-2" );
	$("#lblPatronID").prop( "disabled", true);
	
	$('.patronid').prop('disabled', true);
	$(".patronid").removeAttr('href'); 
	
	//////table setup
	$('#catreportTable, #catreportTableUndefined').DataTable( {
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
                    minDate.setDate(minDate.getDate());
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
                    minDate.setDate(minDate.getDate() );
                    $('#input-startDate').datepicker('setEndDate', minDate);
    });


	$('#smd, #icat, #loca, #dept, #doctatus, #branch, #cattype').multiselect({
    	allSelectedText: 'All',
   		maxHeight: 200,
   		includeSelectAllOption: true,
		numberDisplayed: 1,
	});
	
	$("#smd, #icat, #loca, #dept, #doctatus,  #cattype").multiselect("disable");
	
	$("#startInputCtrlno, #endInputCtrlno, #createdby").prop( "disabled", true);
	$("#startInputAcc, #endInputAcc, #startInputCallno, #endInputCallno, #inputtitle").prop( "disabled", true);
	
});