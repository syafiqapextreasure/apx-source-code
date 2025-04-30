$(document).ready(function() {	
	////// table setup //////
	$('#reportTable').DataTable({
		destroy: true,
		searching: false,
		bLengthChange: false,
		autoWidth: false,
		responsive: true,
		paging: false
	});
	
	$("#Reterive").attr('disabled', 'disabled');

	///////////////////// input-endDate set Current Date  ////////////////////
	$('#input-endDate').val(moment(new Date()).format("DD/MM/YYYY"));
	$('#input-startDate').datepicker({
		format: "dd/mm/yyyy",
		todayBtn: true,
		autoclose: true,
		todayHighlight: true,
	});
	$('#input-endDate').datepicker({
		format: "dd/mm/yyyy",
		todayBtn: true,
		autoclose: true,
		todayHighlight: true,
	});
	
	$('#input-startDate, #input-endDate').on('change', function(){
		if($('#input-startDate').val().length > 0 && $('#input-endDate').val().length > 0){
			$("#Reterive").removeAttr('disabled');
		}
		if($('#input-startDate').val().length == 0 && $('#input-endDate').val().length > 0){
			$("#Reterive").attr('disabled', 'disabled');
		}
		if($('#input-startDate').val().length > 0 && $('#input-endDate').val().length == 0){
			$("#Reterive").attr('disabled', 'disabled');
		}
		if($('#input-startDate').val().length == 0 && $('#input-endDate').val().length == 0){
			$("#Reterive").attr('disabled', 'disabled');
		}
	})
	
	$('#input-startDate, #input-endDate').on('input', function(){
		if($('#input-startDate').val().length > 0 && $('#input-endDate').val().length > 0){
			$("#Reterive").removeAttr('disabled');
		}
		if($('#input-startDate').val().length == 0 && $('#input-endDate').val().length > 0){
			$("#Reterive").attr('disabled', 'disabled');
		}
		if($('#input-startDate').val().length > 0 && $('#input-endDate').val().length == 0){
			$("#Reterive").attr('disabled', 'disabled');
		}
		if($('#input-startDate').val().length == 0 && $('#input-endDate').val().length == 0){
			$("#Reterive").attr('disabled', 'disabled');
		}
	})
	

	//////////////////////////////////////////////////////////////////////////
	////////////////////////btn Reterive Click ///////////////////////////////
	//////////////////////////////////////////////////////////////////////////
	$('#Reterive').click(function(){
		
		/////result display
		var t = $('#reportTable').DataTable({
			dom: 'Bfrtip',
			info:     false,

			destroy: true,
			searching: true,
			bLengthChange: false,
			autoWidth: false,
			responsive: true,
			processing: true,
			paging: true,
			language: {
             loadingRecords : "Please wait - loading...",
             processing :     "Please wait formatting in progress..."
        	},
			deferRender: true,
			footer: true,
			ajax: {
		    	url: "getFineInformation",
		        data : {},
		        type: "GET",
		        dataSrc: function (json) {
		            var return_data = new Array();
					var i = 0
					$.each( json, function( key, value ) {
						return_data.push({
			                'Patron ID' : value.id,
							'Patron Name' : value.name,
							'Patron Category' : value.category,
			                'Course' : value.course,
			                'Department' : value.department,
			                'Amount' : value.amount
			            })
					i++
					});
		            return return_data;
		          },
		     },
			columns: [
				{ "data": "No" },
				{ "data": "Patron ID" },
				{ "data": "Patron Name" },
				{ "data": "Patron Category" },
			    { "data": "Course" },
				{ "data": "Department" },
			    { "data": "Amount" }
			  ]
    		});
	})


});