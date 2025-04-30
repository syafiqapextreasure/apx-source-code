$(document).ready(function() {
	////// table setup
	$('#reportTable').DataTable({
		destroy: true,
		searching: false,
		bLengthChange: false,
		autoWidth: false,
		responsive: true,
	});

	$('#branch').multiselect("disable");
	$('#branch').multiselect({
		allSelectedText: 'All',
		maxHeight: 200,
		includeSelectAllOption: true
	});

	$('input[type=checkbox][name=chkBoxBranchCateria]').click(function() {
		$("#branch").multiselect("clearSelection");
		if ($('#branchSelect').prop("checked") == true) { // checkbox is CHECKED
			$('#branch').multiselect("enable");
		} else if ($('#branchSelect').prop("checked") != true) { // checkbox is not CHECKED 
			$('#branch').multiselect("disable");
		}
	})

	///////////////////// input-startDate set Current Date  //////////////////
	$('#input-endDate').val(moment(new Date()).format("DD/MM/YYYY"));
	$('#input-startDate').datepicker({
		format: "dd/mm/yyyy",
		todayBtn: true,
		autoclose: true,
		todayHighlight: true,
	});

	///////////////////// input-endDate set Current Date  ////////////////////
	$('#input-endDate').datepicker({
		format: "dd/mm/yyyy",
		todayBtn: true,
		autoclose: true,
		todayHighlight: true,
	});

	////////////// branchSelect checkbox on click  ////////////////////
	$("#input-endDate,#input-startDate, #branchSelect, #branch").on('input change', function() {
		checkDataFormat()
	});

	function checkDataFormat() {
		$('#Reterive').prop('disabled', true);
		if (($('#input-endDate').val().length > 0 || $('#input-startDate').val().length > 0) && $('#branchSelect').prop("checked") == false) {
			$('#Reterive').prop('disabled', false);
		}
		if (($('#input-endDate').val().length > 0 || $('#input-startDate').val().length > 0) && $('#branchSelect').prop("checked") == true) {
			//$('#Reterive').prop('disabled', true);
			if ($("#branch").val().length > 0) {
				$('#Reterive').prop('disabled', false);
			}
		}
	}


	//////////////////////////////////////////////////////////////////////////
	////////////////////////btn Reterive Click ///////////////////////////////
	//////////////////////////////////////////////////////////////////////////
	$('#Reterive').click(function() {
		var startSentDate = moment($("#input-startDate").val(), 'DD/MM/YYYY').format("YYYYMMDD");
		var endSentDate = moment($("#input-endDate").val(), 'DD/MM/YYYY').format("YYYYMMDD");
		var sDate = '';
		var eDate = '';

		var startSentDate = $("#input-startDate").val();
		if (startSentDate == '') {
			startSentDate = '';
			sDate = ''
		} else {
			startSentDate = moment($("#input-startDate").val(), 'DD/MM/YYYY').format("YYYYMMDD");
			sDate = $("#input-startDate").val();
		}

		var endSentDate = $("#input-endDate").val();
		if (endSentDate == '') {
			endSentDate = '';
			eDate = ''
		} else {
			endSentDate = moment($("#input-endDate").val(), 'DD/MM/YYYY').format("YYYYMMDD");
			eDate = $("#input-endDate").val();
		}

		var title = ''
		if (startSentDate != '' && endSentDate == '') {
			title = 'Reservation Date Report from ' + sDate
		} if (startSentDate == '' && endSentDate != '') {
			title = 'Reservation Date Report until ' + eDate
		} if (startSentDate != '' && endSentDate != '') {
			title = 'Reservation Date Report from ' + sDate + ' to ' + eDate
		}

		var checkBranchSelect = 'N';
		var branchSelectData = '';

		if ($('#branchSelect').prop("checked") == true) { // checkbox is CHECKED for branch
			checkBranchSelect = 'Y';
			branchSelectData = JSON.stringify($("#branch").val());
		}


		// result display
		$('#reportTable').DataTable({
			dom: 'Bfrtip',
			info: false,
			buttons: [
				{
					extend: 'excelHtml5',
					filename: 'WILMU_Reservation Scrutiny',
					title: title,
				},
				{
					text: '<i class="fa fa-file-pdf-o"></i> PDF',
					extend: 'pdfHtml5',
					filename: 'WILMU_Reservation Scrutiny',
					charset: "utf-8",
					bom: "true",
					orientation: 'landscape', //portrait
					pageSize: 'A4', //A3 , A5 , A6 , legal , letter
					footer: true,
					exportOptions: {
						search: 'applied',
						order: 'applied'
					},
					customize: function(doc) {
						//Remove the title created by datatTables  
						doc.content.splice(0, 1, {
							text: [
								{ text: title + '\n', bold: true, fontSize: 13, alignment: 'center' }],
							margin: [0, 0, 0, 20],
						});
						doc.pageMargins = [20, 20, 20, 20]; //left, top, right, bottom

						doc['footer'] = (function(page, pages) {
							return {
								columns: [
									{
										alignment: 'left',
										text: ['\t\t\t\t  ', moment(new Date()).format("DD/MM/YYYY") + '\t' + moment(new Date()).format("hh:mm:ss A")]
									},
									{
										alignment: 'right',
										text: ['page ', { text: page.toString() }, ' of ', { text: pages.toString() }]
									}
								],
								margin: 20
							}
						});
					},
				},
				{
					extend: 'csv',
					filename: 'WILMU_Reservation Scrutiny',
					charset: "utf-8",
					bom: "true",
					customize: function(doc) {
						var split_csv = doc.split("\n");
						$.each(split_csv.slice(1), function(index, csv_row) {
							var csv_cell_array = csv_row.split('","');
							csv_cell_array[1].replace("", (index + 1));
						});
						return title + "\n\n" + doc;
					}
				},
			],
			destroy: true,
			searching: true,
			bLengthChange: false,
			autoWidth: false,
			responsive: true,
			processing: true,
			paging: true,
			language: {
				loadingRecords: "Please wait - loading...",
				processing: "Please wait formatting in progress..."
			},
			deferRender: true,
			footer: true,
			ajax: {
				url: "getReservationScrutinyReport",
				data: {
					startSentDate: startSentDate,
					endSentDate: endSentDate,
					checkBranchSelect: checkBranchSelect,
					branchSelectData: branchSelectData
				},
				type: "GET",
				dataSrc: function(json) {
					var return_data = new Array();
					$.each(json, function(key, value) {
						return_data.push({
							'Patron': value.patronName,
							'Status': value.status,
							'Reserved Date': value.reserveDate,
							'Reserved Time': value.reserveTime,
							'Priority': value.priority,
							'Title': value.title,
							'Pickup Branch': value.pickupBranch
						})
					});
					return return_data;
				},
			},
			columns: [
				{ "data": "Patron" },
				{ "data": "Status" },
				{ "data": "Reserved Date" },
				{ "data": "Reserved Time" },
				{ "data": "Priority" },
				{ "data": "Title" },
				{ "data": "Pickup Branch" }
			],
		});
	})
});