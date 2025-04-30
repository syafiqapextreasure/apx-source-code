$(document).ready(function() {
	///////////////////// input-startDate set Current Date  //////////////////
	var today = new Date();
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

	$("#Reterive").attr('disabled', 'disabled');

	function checkConditionPartForInputFormatting() {
		if ($("#input-startDate").val().length > 0 || $("#input-endDate").val().length > 0) {
			$('#Reterive').removeAttr('disabled');
		}
		else {
			$("#Reterive").attr('disabled', 'disabled');
		}
	}

	$("#input-startDate, #input-endDate").on('change', function() {
		checkConditionPartForInputFormatting()
	})


	////// table setup
	$('#reportTable').DataTable({
		destroy: true,
		searching: false,
		bLengthChange: false,
		autoWidth: false,
		responsive: true,
	});


	//////////////////////////////////////////////////////////////////////////
	////////////////////////btn Reterive Click ///////////////////////////////
	//////////////////////////////////////////////////////////////////////////
	$('#Reterive').click(function() {
		var startSentDate = moment($("#input-startDate").val(), 'DD/MM/YYYY').format("YYYYMMDD");
		var endSentDate = moment($("#input-endDate").val(), 'DD/MM/YYYY').format("YYYYMMDD");
		var sDate, eDate;
		var title = ''

		var startSentDate = $("#input-startDate").val();
		if (startSentDate == '') {
			startSentDate = '';
			sDate = "";
		} else {
			startSentDate = moment($("#input-startDate").val(), 'DD/MM/YYYY').format("YYYYMMDD");
			sDate = $("#input-startDate").val();
		}

		var endSentDate = $("#input-endDate").val();
		if (endSentDate == '') {
			endSentDate = '';
		} else {
			endSentDate = moment($("#input-endDate").val(), 'DD/MM/YYYY').format("YYYYMMDD");
			eDate = $("#input-endDate").val();
		}

		title = 'Stop Date from ' + sDate + ' until ' + eDate

		if (endSentDate == '' && startSentDate != '') {
			title = 'Stop Date from ' + sDate
		}
		if (startSentDate == '' && endSentDate != '') {
			title = 'Stop Date until ' + eDate
		}


		/////result display
		var t = $('#reportTable').DataTable({
			dom: 'Bfrtip',
			info: false,
			buttons: [
				{
					extend: 'excelHtml5',
					filename: 'WILMU_Reserve Collection Listing',
					title: title,
				},
				{
					text: '<i class="fa fa-file-pdf-o"></i> PDF',
					extend: 'pdfHtml5',
					filename: 'WILMU_Reserve Collection Listing',
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
						var rowCount = doc.content[1].table.body.length;
						for (i = 0; i < rowCount; i++) {
							doc.content[1].table.body[i][0].alignment = 'left';
							doc.content[1].table.body[i][1].alignment = 'left';
							doc.content[1].table.body[i][2].alignment = 'left';
							doc.content[1].table.body[i][3].alignment = 'left';
							doc.content[1].table.body[i][4].alignment = 'left';
							doc.content[1].table.body[i][5].alignment = 'left';
							doc.content[1].table.body[i][6].alignment = 'left';
							doc.content[1].table.body[i][7].alignment = 'left';
						};
						
						//Remove the title created by datatTables  
						doc.content.splice(0, 1, {
							text: [
								{ text: title + '\n', bold: true, fontSize: 13, alignment: 'center' }],
							margin: [0, 0, 0, 12],
						});

						doc['footer'] = (function(page, pages) {
							return {
								columns: [
									{
										alignment: 'left',
										text: ['\t\t\t\t  ', moment(today).format("DD/MM/YYYY") + '\t' + moment(today).format("hh:mm:ss A")]
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
					filename: 'WILMU_Reserve Collection Listing',
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
				url: "getReserveCollectionListing",
				data: { startSentDate: startSentDate, endSentDate: endSentDate },
				type: "GET",
				dataSrc: function(json) {
					var return_data = new Array();
					var i = 0
					$.each(json, function(key, value) {
						return_data.push({
							'No': (i + 1),
							'Course': value.course,
							'Subject': value.subject,
							'Semester': value.semester,
							'Instructor': value.instructor,
							'Control No': value.controlNo,
							'Title': value.title,
							'Start Date': value.startDate,
							'Stop Date': value.endDate
						})
						i++
					});
					return return_data;
				},
			},
			columns: [
				{ "data": "No" },
				{ "data": "Course" },
				{ "data": "Subject" },
				{ "data": "Semester" },
				{ "data": "Instructor" },
				{ "data": "Control No" },
				{ "data": "Title" },
				{ "data": "Start Date" },
				{ "data": "Stop Date" }
			],
		});
	})
});