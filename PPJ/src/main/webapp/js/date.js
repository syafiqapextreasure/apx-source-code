$(document).ready(function() {

	$.extend( jQuery.fn.dataTableExt.oSort, {
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
	});
	
	var today = new Date();  
	$('#tarikhtran').val(moment(today).format("DD/MM/YYYY"));
	$('#tarikhtran').datepicker({
		//startDate : today,
		//dateFormat : "dd/mm/yyyy",
		format: "dd/mm/yyyy",
		todayBtn: true,
		autoclose: true,
		todayHighlight: true,
		endDate: new Date(),
	}).on('changeDate', function (selected) {
		
         if($("#branch").val()  == '' || $('#tarikhtran').val() == ''){
			$("#Reterive").prop( "disabled", true);
		}else{
			$("#Reterive").prop( "disabled", false);
		}
    });
	
});

