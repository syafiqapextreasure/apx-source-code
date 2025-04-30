$(document).ready(function() {
	//////table setup
	$('#fndreportTable').DataTable({
		destroy: true,
		searching: false,
		bLengthChange: false,
		autoWidth: false,
		responsive: true,
	});

	jQuery.extend(jQuery.fn.dataTableExt.oSort, {
		"date-uk-pre": function(a) {
			if (a == null || a == "") {
				return 0;
			}
			var ukDatea = a.split('/');
			return (ukDatea[2] + ukDatea[1] + ukDatea[0]) * 1;
		},
		"date-uk-asc": function(a, b) {
			return ((a < b) ? -1 : ((a > b) ? 1 : 0));
		},
		"date-uk-desc": function(a, b) {
			return ((a < b) ? 1 : ((a > b) ? -1 : 0));
		}
	});

	var today = new Date();
	///////////////////// input-startDate  ////////////////////
		$('#input-startDate').datepicker({
			format: "dd/mm/yyyy",
			todayBtn: true,
			autoclose: true,
			todayHighlight: true,
			endDate: new Date(),
		}).on('changeDate', function(selected) {
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
	}).on('changeDate', function(selected) {
		var minDate = new Date(selected.date);
		minDate.setDate(minDate.getDate() - 1);
		$('#input-startDate').datepicker('setEndDate', minDate);
	});
});