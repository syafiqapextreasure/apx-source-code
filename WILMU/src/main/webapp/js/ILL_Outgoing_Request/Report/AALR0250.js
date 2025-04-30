$(document).ready(function() {
	////// table setup
	$('#reportTable').DataTable({
		destroy: true,
		searching: false,
		bLengthChange: false,
		autoWidth: false,
		responsive: true,
	});

	$('#requestName').prop('checked', true);
	$('#input-startDate, #input-endDate').attr('disabled', 'disabled');

	///////////////////// input-startDate set Current Date  //////////////////
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

	////////////// radio button on click  ////////////////////
	$('input[type=radio][name=selectOption]').click(function() {
		var chkBoxSearchCateria = $("input[name='selectOption']:checked").val();
		switch (chkBoxSearchCateria) {
			case 'requestName':
				$('#requestorName-text').removeAttr('disabled'); // ENABLE REQUESTOR NAME
				$('#input-startDate, #input-endDate').val('').attr('disabled', 'disabled'); // DISABLE DATE AND CLEAR THE DATE INPUT
				$('#requestorName-text').prop('selectedIndex', 0); // TO RESET SELEC OPTION
				break;
			case 'requestDate':
				$('#input-startDate, #input-endDate').removeAttr('disabled'); // ENABLE DATE
				$('#requestorName-text').attr('disabled', 'disabled');  // DISABLE REQUESTOR NAME
				break;
			case undefined:
				break;
		}
	});


	//////////////////////////////////////////////////////////////////////////
	////////////////////////btn Reterive Click ///////////////////////////////
	//////////////////////////////////////////////////////////////////////////
	$('#Reterive').click(function() {
		var selectOption = $('input[type=radio][name=selectOption]:checked').val();
		var requestorName = $('#requestorName-text').val().toUpperCase();
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
		if (selectOption === 'requestDate') {
			title = 'WILMU Outgoing Request Report from ' + sDate + " until " + eDate
		}
		if (selectOption === 'requestName') {
			title = 'WILMU Outgoing Request Report ' + requestorName
		}

		// result display
		$('#reportTable').DataTable({
			dom: 'Bfrtip',
			info: false,
			buttons: [
				{
					extend: 'excelHtml5',
					filename: 'WILMU_Outgoing Request Report',
					title: title,
				},
				{
					text: '<i class="fa fa-file-pdf-o"></i> PDF',
					extend: 'pdfHtml5',
					filename: 'WILMU_Outgoing Request Report',
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
					filename: 'WILMU_Outgoing Request Report',
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
				url: "getOutcomingRequestIll",
				data: {
					selectOption: selectOption,
					requestorName: requestorName,
					startSentDate: startSentDate,
					endSentDate: endSentDate,
				},
				type: "GET",
				dataSrc: function(json) {
					var return_data = new Array();
					var i = 1
					$.each(json, function(key, value) {
						return_data.push({
							'No': i,
							'Request No': value.requestNo,
							'Requestor': value.requestorName,
							'Title': value.title,
							'Control No': value.controlNo,
							'Accession No': value.accessionNo,
							'Dt Request': value.dateRequest,
							'Dt Expected': value.dateExpected,
							'Dt Received': value.dateReceived,
							'Dt Returned': value.dateReturned,
							'Lending Library': value.lendingLibrary,
							'Contact Person': value.contactPerson,
						})
						i++
					});
					return return_data;
				},
			},
			columns: [
				{ "data": "No" },
				{ "data": "Request No" },
				{ "data": "Requestor" },
				{ "data": "Title" },
				{ "data": "Control No" },
				{ "data": "Accession No" },
				{ "data": "Dt Request" },
				{ "data": "Dt Expected" },
				{ "data": "Dt Received" },
				{ "data": "Dt Returned" },
				{ "data": "Lending Library" },
				{ "data": "Contact Person" },
			],
		});
	})
});