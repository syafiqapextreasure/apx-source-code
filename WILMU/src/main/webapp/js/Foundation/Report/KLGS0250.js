$(document).ready(function() {
	///////////////////// input-toDate set Current Date //////////////////
	$('#input-toDate').val(moment(new Date()).format("DD/MM/YYYY"));

	////// table setup
	$('#reportTable').DataTable({
		destroy: true,
		searching: false,
		bLengthChange: false,
		autoWidth: false,
		responsive: true,
	});

	var allSelected = false;
	$('#byStatus-select, #byBranch-select').multiselect({
		allSelectedText: 'All',
		maxHeight: 200,
		includeSelectAllOption: true,
		numberDisplayed: 1,
		onSelectAll: function() {
			allSelected = true;
		},
		onChange: function() {
			allSelected = false;
		}
	});

	$("#byStatus-select, #byBranch-select").multiselect("disable");
	$("#Reterive").attr('disabled', 'disabled');

	$("#statusCheck, #branchCheck").on('click', function() {
		$("#byStatus-select, #byBranch-select").multiselect("disable");

		if ($("#statusCheck").is(":checked") && $("#branchCheck").is(":not(:checked)")) {
			$("#byBranch-select").multiselect("clearSelection");
			$('#byBranch-select').multiselect("disable");
			$('#byStatus-select').multiselect("enable");
		}
		if ($("#branchCheck").is(":checked") && $("#statusCheck").is(":not(:checked)")) {
			$("#byStatus-select").multiselect("clearSelection");
			$('#byStatus-select').multiselect("disable");
			$('#byBranch-select').multiselect("enable");
		}
		if ($("#statusCheck").is(":checked") && $("#branchCheck").is(":checked")) {
			$('#byStatus-select, #byBranch-select').multiselect("enable");
		}
		checkFormatControl()
	});

	$("#input-fromDate, #input-toDate").on('input', function() {
		checkFormatControl()
	});

	$("#byStatus-select, #byBranch-select").on('change', function() {
		checkFormatControl()
	});

	function checkFormatControl() {
		if ($("#statusCheck").is(":not(:checked)") && $("#branchCheck").is(":not(:checked)")) {
			$("#byStatus-select, #byBranch-select").multiselect("clearSelection");
			if ($("#input-fromDate").val().length > 0 && $("#input-toDate").val().length > 0) {
				$('#Reterive').prop('disabled', false);
			} else {
				$('#Reterive').prop('disabled', true);
			}
		}
		if ($("#statusCheck").is(":checked") && $("#branchCheck").is(":not(:checked)")) {
			checkFormatControlDateInputSelect()
		}
		if ($("#branchCheck").is(":checked") && $("#statusCheck").is(":not(:checked)")) {
			checkFormatControlDateInputSelect()
		}
		if ($("#branchCheck").is(":checked") && $("#statusCheck").is(":checked")) {
			checkFormatControlDateInputSelect()
		}
	}

	function checkFormatControlDateInputSelect() {
		$('#Reterive').prop('disabled', true);
		if ($("#input-fromDate").val().length > 0 && $("#input-toDate").val().length > 0) {
			if ($("#statusCheck").is(":checked") && $("#branchCheck").is(":not(:checked)") && $("#byStatus-select").val().length > 0) {
				$('#Reterive').prop('disabled', false);
			}
			if ($("#branchCheck").is(":checked") && $("#statusCheck").is(":not(:checked)") && $("#byBranch-select").val().length > 0) {
				$('#Reterive').prop('disabled', false);
			}
			if ($("#branchCheck").is(":checked") && $("#statusCheck").is(":checked") && $("#byBranch-select").val().length > 0 && $("#byStatus-select").val().length > 0) {
				$('#Reterive').prop('disabled', false);
			}
		}
	}
	//////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////

	var t = $('#reportTable').DataTable({
		destroy: true,
		searching: false,
		bLengthChange: false,
		autoWidth: false,
		responsive: true,
	});


	// Formatting function for row details - modify as you need
	function format(d) {
		// `d` is the original data object for the row
		return '<table cellpadding="5" border = "1" cellspacing="0">' +
			'<thead>' +
			'<tr>' +
			'<th style="width:40%;">Patron</th>' +
			'<th style="width:15%; text-align: center;">Expiry Date</th>' +
			'<th style="width:15%; text-align: center;">Expired within date range</th>' +
			'<th style="width:15%; text-align: center;">Expired before date range</th>' +
			'<th style="width:15%; text-align: center;">Active</th>' +
			'</tr>' +
			'</thead>' +
			'<tbody>' +
			d +
			'</tbody>' +
			'</table>';
	}

	// Add event listener for opening and closing details
	$('#reportTable tbody').on('click', 'td.dt-control', function(e) {
		let tr = e.target.closest('tr');
		let row = t.row(tr);

		if (row.child.isShown()) {
			// This row is already open - close it
			row.child.hide();
			$(tr).removeClass('dt-hasChild');
		}
		else {
			// Open this row
			var startSentDate = moment($("#input-fromDate").val(), 'DD/MM/YYYY').format("YYYYMMDD");
			var endSentDate = moment($("#input-toDate").val(), 'DD/MM/YYYY').format("YYYYMMDD");
			var todayDate = moment(new Date(), 'DD/MM/YYYY').format("YYYYMMDD");
			var patronCategory = row.data().patronCategory.split("-").shift().trim();
			var stringTableData = ''
			var statusCheck = false;
			var branchCheck = false;

			var statusData = "";
			var branchData = "";


			if ($("#statusCheck").is(":checked")) {
				statusCheck = true;
				statusData = JSON.stringify($("#byStatus-select").val())
			}
			if ($("#branchCheck").is(":checked")) {
				branchCheck = true;
				branchData = JSON.stringify($("#byBranch-select").val())
			}

			$.get("getMembershipReportDetails", {
				startSentDate: startSentDate,
				endSentDate: endSentDate,
				todayDate: todayDate,
				patronCategory: patronCategory,
				statusCheck: statusCheck,
				branchCheck: branchCheck,
				statusData: statusData,
				branchData: branchData
			}).then(function(result) {
				var stringTableRow = ''
				for (var key in result) {
					stringTableRow = '<tr><td>' + result[key].patronDetails + '</td><td style="text-align: center;">' + result[key].expiryDate + '</td>'

					if (result[key].expiredWithinDate == 1) {
						stringTableRow = stringTableRow + '<td style="text-align: center;">X</td>'
					}
					if (result[key].expiredWithinDate == 0) {
						stringTableRow = stringTableRow + '<td></td>'
					}
					if (result[key].expiredBeforeDate == 1) {
						stringTableRow = stringTableRow + '<td style="text-align: center;">X</td>'
					}
					if (result[key].expiredBeforeDate == 0) {
						stringTableRow = stringTableRow + '<td></td>'
					}
					if (result[key].activePatron == 1) {
						stringTableRow = stringTableRow + '<td style="text-align: center;">X</td>'
					}
					if (result[key].activePatron == 0) {
						stringTableRow = stringTableRow + '<td></td>'
					}
					stringTableRow = stringTableRow + '</tr>'
					stringTableData = stringTableData + stringTableRow
				}
				row.child(format(stringTableData)).show();
				$(tr).addClass('dt-hasChild');
			});
		}
	});

	//////////////////////////////////////////////////////////////////////////
	////////////////////////btn Reterive Click ///////////////////////////////
	//////////////////////////////////////////////////////////////////////////
	$('#Reterive').click(function() {
		var startSentDate = moment($("#input-fromDate").val(), 'DD/MM/YYYY').format("YYYYMMDD");
		var endSentDate = moment($("#input-toDate").val(), 'DD/MM/YYYY').format("YYYYMMDD");
		var todayDate = moment(new Date(), 'DD/MM/YYYY').format("YYYYMMDD");
		var sDate = $("#input-fromDate").val();
		var eDate = $("#input-toDate").val();


        var startSentDateTemp = moment($("#input-fromDate").val(), 'DD/MM/YYYY').format("MM/DD/YYYY");
        var endSentDateTemp = moment($("#input-toDate").val(), 'DD/MM/YYYY').format("MM/DD/YYYY");
		var date1StartDate = new Date(startSentDateTemp).getTime();
		var date2EndDate = new Date(endSentDateTemp).getTime();
		var sTempDate = '';
		var eTempDate = '';

		if (date1StartDate > date2EndDate) {
			sTempDate = eDate;
			eTempDate = sDate;
		} if (date1StartDate < date2EndDate) {
			sTempDate = sDate;
			eTempDate = eDate;
		}

		var reportTitle = "Statistics date from " + sTempDate + " to " + eTempDate;
		var statusCheck = false;
		var branchCheck = false;
		var statusData = "";
		var branchData = "";


		if ($("#statusCheck").is(":checked")) {
			statusCheck = true;
			statusData = JSON.stringify($("#byStatus-select").val())
		}
		if ($("#branchCheck").is(":checked")) {
			branchCheck = true;
			branchData = JSON.stringify($("#byBranch-select").val())
		}

		t = $('#reportTable').DataTable({
			dom: 'Bfrtip',
			buttons: [
				{
					extend: 'excelHtml5',
					filename: 'WILMU_MembershipReport',
					title: "Membership Report " + reportTitle + "\n",
				},
				{
					text: '<i class="fa fa-file-pdf-o"></i> PDF',
					extend: 'pdfHtml5',
					filename: 'WILMU_MembershipReport',
					charset: "utf-8",
					bom: "true",
					orientation: 'portrait', //portrait
					pageSize: 'A4', //A3 , A5 , A6 , legal , letter
					footer: true,
					exportOptions: {
						search: 'applied',
						order: 'applied'
					},
					customize: function(doc) {
						doc.styles.tableHeader.alignment = 'left';
						var rowCount = doc.content[1].table.body.length;
						for (i = 0; i < rowCount; i++) {
							doc.content[1].table.body[i][0].alignment = 'left';
							doc.content[1].table.body[i][1].alignment = 'left';
							doc.content[1].table.body[i][2].alignment = 'right';
							doc.content[1].table.body[i][3].alignment = 'right';
							doc.content[1].table.body[i][4].alignment = 'right';
						};

						//Remove the title created by datatTables  
						doc.content.splice(0, 1, {
							text: [
								{ text: "Membership Report\n" + reportTitle + "\n", bold: true, fontSize: 13, alignment: 'center' }],
							margin: [0, 0, 0, 12],
						});

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
					filename: 'WILMU_MembershipReport',
					charset: "utf-8",
					bom: "true",
					customize: function(doc) {
						var split_csv = doc.split("\n");
						$.each(split_csv.slice(1), function(index, csv_row) {
							var csv_cell_array = csv_row.split('","');
							csv_cell_array[1].replace("", (index + 1));
						});
						return "Membership Report\n" + reportTitle + "\n" + doc;
					}
				},
			],
			destroy: true,
			searching: true,
			bLengthChange: false,
			autoWidth: false,
			responsive: true,
			paging: false,
			processing: true,
			language: {
				loadingRecords: "Please wait - loading...",
				processing: "Please wait formatting in progress..."
			},
			deferRender: true,
			footer: true,
			ajax: {
				url: "getMembershipReport",
				data: {
					startSentDate: startSentDate,
					endSentDate: endSentDate,
					todayDate: todayDate,
					statusCheck: statusCheck,
					branchCheck: branchCheck,
					statusData: statusData,
					branchData: branchData
				},
				type: "GET",
				dataSrc: function(json) {
					var return_data = new Array();
					for (var i = 0; i < json.length; i++) {
						return_data.push({
							'patronCategory': json[i].patronCategory,
							'totalPatron': json[i].totalPatron,
							'expiredWithinDate': json[i].expiredWithinDate,
							'expiredBeforeDate': json[i].expiredBeforeDate,
							'activePatron': json[i].activePatron
						})
					}
					
					$("#toDeleteTd").remove();
					$("#toColspanTd").attr('colspan',2);
					return return_data;
				},
			},//This is the end of the AJAX object from the example above
			columns: [
				{
					className: 'dt-control',
					orderable: false,
					data: null,
					defaultContent: ''
				},
				{ 'data': 'patronCategory' },
				{ 'data': 'totalPatron' },
				{ 'data': 'expiredWithinDate' },
				{ 'data': 'expiredBeforeDate' },
				{ 'data': 'activePatron' },
			],
			footerCallback: function(row, data, start, end, display) {
				let api = this.api();

				// Remove the formatting to get integer data for summation
				let intVal = function(i) {
					return typeof i === 'string'
						? i.replace(/[\$,]/g, '') * 1
						: typeof i === 'number'
							? i
							: 0;
				};

				// Total over all pages
				var totalAll = api
					.column(2)
					.data()
					.reduce((a, b) => intVal(a) + intVal(b), 0);
				var totalExpiredWithinDateRange = api
					.column(3)
					.data()
					.reduce((a, b) => intVal(a) + intVal(b), 0);
				var totalExpiredBeforeDateRange = api
					.column(4)
					.data()
					.reduce((a, b) => intVal(a) + intVal(b), 0);
				var totalActive = api
					.column(5)
					.data()
					.reduce((a, b) => intVal(a) + intVal(b), 0);


				// Update footer
				api.column(2).footer().innerHTML = totalAll;
				api.column(3).footer().innerHTML = totalExpiredWithinDateRange;
				api.column(4).footer().innerHTML = totalExpiredBeforeDateRange;
				api.column(5).footer().innerHTML = totalActive;
			}
		});
	});
});