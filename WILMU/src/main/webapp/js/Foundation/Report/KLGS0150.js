$(document).ready(function() {
	var today = new Date();

	var allSelected = false;
	$('#branch').multiselect({
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

	$('#patronStat, #patronCate').multiselect({
		allSelectedText: 'All',
		maxHeight: 200,
		includeSelectAllOption: true,
		numberDisplayed: 1,
	});

	function checkFormatControl() {
		if ($("#chkPatrStat").is(":checked") && $("#chkPatrCate").is(":not(:checked)")) {
			if ($("#patronStat").val().length > 0) {
				$('#Reterive').prop('disabled', false);
			} else {
				$('#Reterive').prop('disabled', true);
			}
		}
		if ($("#chkPatrCate").is(":checked") && $("#chkPatrStat").is(":not(:checked)")) {
			if ($("#patronCate").val().length > 0) {
				$('#Reterive').prop('disabled', false);
			} else {
				$('#Reterive').prop('disabled', true);
			}
		}
		if ($("#chkPatrCate").is(":checked") && $("#chkPatrStat").is(":checked")) {
			if ($("#patronCate").val().length > 0 && $("#patronStat").val().length > 0) {
				$('#Reterive').prop('disabled', false);
			} else {
				$('#Reterive').prop('disabled', true);
			}
		}
	}
	
	$("#patronStat, #patronCate").multiselect("disable");

	$("#chkPatrStat, #chkPatrCate").on('change', function() {
		if ($("#chkPatrStat").is(":checked") && $("#chkPatrCate").is(":not(:checked)")) {
			$("#patronCate").multiselect("clearSelection")
			$('#patronCate').multiselect("disable");
			$('#patronStat').multiselect("enable");
			$('#Reterive').prop('disabled', true);
		}
		if ($("#chkPatrCate").is(":checked") && $("#chkPatrStat").is(":not(:checked)")) {
			$("#patronStat").multiselect("clearSelection")
			$('#patronStat').multiselect("disable");
			$('#patronCate').multiselect("enable");
			$('#Reterive').prop('disabled', true);
		}
		if ($("#chkPatrCate").is(":not(:checked)") && $("#chkPatrStat").is(":not(:checked)")) {
			$("#patronCate, #patronStat").multiselect("clearSelection")
			$('#patronCate, #patronStat').multiselect("disable");
			$('#Reterive').prop('disabled', false);
		}
		if ($("#chkPatrCate").is(":checked") && $("#chkPatrStat").is(":checked")) {
			$('#patronCate, #patronStat').multiselect("enable");
			$('#Reterive').prop('disabled', true);
		}
		checkFormatControl()
	});

	$("#patronStat, #patronCate").on('change', function() {
		checkFormatControl()
	});

	//////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////

	//////////////////////////////////////////////////////////////////////////
	////////////////////////btn Reterive Click ///////////////////////////////
	//////////////////////////////////////////////////////////////////////////
	$('#Reterive').click(function() {
		$('#fndreportTable').dataTable().fnClearTable();
		var startSentDate = moment($("#input-startDate").val(), 'DD/MM/YYYY').format("YYYYMMDD");
		var endSentDate = moment($("#input-endDate").val(), 'DD/MM/YYYY').format("YYYYMMDD");
		var sDate, eDate;

		var startSentDate = $("#input-startDate").val();
		if (startSentDate == '') {
			startSentDate = '';
			sDate = '';
		} else {
			startSentDate = moment($("#input-startDate").val(), 'DD/MM/YYYY').format("YYYYMMDD");
			sDate = "from " + $("#input-startDate").val();
		}

		var endSentDate = $("#input-endDate").val();
		if (endSentDate == '') {
			endSentDate = '';
			eDate = '';
		} else {
			endSentDate = moment($("#input-endDate").val(), 'DD/MM/YYYY').format("YYYYMMDD");
			eDate = " until " + $("#input-endDate").val();
		}

		var brnch = $("#branch").val();
		var allbranch;

		if (brnch != "") {
			if (allSelected) {
				allbranch = "Y";
			} else {
				allbranch = "N";
			}
		} else {
			brnch = 0;
			allbranch = "N";
		}

		var chkExpiredMember = 'N'
		// Check if the checkbox for Include Expired Membership is checked
		if ($('#chkExpiredMember').is(':checked')) {
			chkExpiredMember = 'Y'
		}

		///patronStat
		var patronStat = $("#patronStat").val();
		if (patronStat != "") {
		} else {
			patronStat = 0;
		}

		///patronCate
		var patronCate = $("#patronCate").val();
		if (patronCate != "") {
		} else {
			patronCate = 0;
		}

		var t = $('#fndreportTable').DataTable({
			dom: 'Bfrtip',
			buttons: [
				{
					extend: 'excelHtml5',
					filename: 'WILMU_MembershipRegistration',
					title: 'Membership Registration ' + sDate + eDate,
				},
				{
					text: '<i class="fa fa-file-pdf-o"></i> PDF',
					extend: 'pdfHtml5',
					filename: 'WILMU_MembershipRegistration',
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

						//doc.content[1].table.widths = [ '4%', '10%', '10%', '10%', '15%', '10%', '15%', '10%', '8%', '8%']; 
						doc.content.push({
							//margin: [ 0, 0, 0, 12 ],
							alignment: 'right',
							bold: true,
							text: [
								$('.divtotal').text(),
							]
						});

						//Remove the title created by datatTables  
						doc.content.splice(0, 1, {
							text: [
								{ text: 'Membership Registration ' + sDate + eDate + '\n', bold: true, fontSize: 13, alignment: 'center' }],
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
					filename: 'WILMU_MembershipRegistration',
					charset: "utf-8",
					bom: "true",
					customize: function(doc) {
						var split_csv = doc.split("\n");
						$.each(split_csv.slice(1), function(index, csv_row) {
							var csv_cell_array = csv_row.split('","');
							csv_cell_array[1].replace("", (index + 1));
						});
						return "Membership Registration " + sDate + eDate + "\n\n" + doc;
					}
				},
			],
			destroy: true,
			searching: true,
			bLengthChange: false,
			autoWidth: false,
			responsive: true,
			processing: true,
			language: {
				loadingRecords: "Please wait - loading...",
				processing: "Please wait formatting in progress..."
			},
			deferRender: true,
			footer: true,
			ajax: {
				url: "ResultMembershipReqReport",
				data: {
					startSentDate: startSentDate, endSentDate: endSentDate, brnch: JSON.stringify(brnch),
					patronStat: JSON.stringify(patronStat),
					patronCate: JSON.stringify(patronCate),
					//// Editer///// 
					allbranch: allbranch,
					chkExpiredMember: chkExpiredMember
				},
				type: "GET",
				dataSrc: function(json) {
					var return_data = new Array();
					for (var i = 0; i < json.length; i++) {
						if (json[i].Column4 == "") {
							json[i].Column4 = "0.00";
						}
						if (json[i].Column5 == "") {
							json[i].Column5 = "0.00";
						}
						return_data.push({
							'No': (i + 1),
							'Patron Category': json[i].Column1 + " - " + json[i].Column2,
							'New Membership': json[i].Column3,
							'Registration Fee (RM)': json[i].Column4,
							'Deposit (RM)': json[i].Column5,
						})
					}
					return return_data;
				},
			},//This is the end of the AJAX object from the example above
			columns: [
				{ 'data': 'No' },
				{ 'data': 'Patron Category' },
				{ 'data': 'New Membership', className: "text-right" },
				{ 'data': 'Registration Fee (RM)', className: "text-right", render: $.fn.dataTable.render.number(',', '.', 2, '') },
				{ 'data': 'Deposit (RM)', className: "text-right", render: $.fn.dataTable.render.number(',', '.', 2, '') },
			],
			footerCallback: function(row, data, start, end, display) {
				var api = this.api(), data;
				var numFormat = $.fn.dataTable.render.number('\,', '.', 2, '').display

				// Remove the formatting to get integer data for summation
				var intVal = function(i) {
					return typeof i === 'string' ?
						i.replace(/[\$,]/g, '') * 1 :
						typeof i === 'number' ?
							i : 0;
				};

				///charge
				// Total over all pages
				total1 = api
					.column(2)
					.data()
					.reduce(function(a, b) {
						return intVal(a) + intVal(b);
					}, 0);

				// Total over this page
				/*pageTotal = api
					.column( 3, { page: 'current'} )
					.data()
					.reduce( function (a, b) {
						return intVal(a) + intVal(b);
					}, 0 );*/

				// Update footer
				$(api.column(2).footer()).html(total1);

				///discharge
				// Total over all pages
				total2 = api
					.column(3)
					.data()
					.reduce(function(a, b) {
						return intVal(a) + intVal(b);
					}, 0);

				// Total over this page
				/*pageTotal = api
					.column( 3, { page: 'current'} )
					.data()
					.reduce( function (a, b) {
						return intVal(a) + intVal(b);
					}, 0 );*/

				// Update footer
				$(api.column(3).footer()).html(numFormat(total2));

				///renewal
				// Total over all pages
				total3 = api
					.column(4)
					.data()
					.reduce(function(a, b) {
						return intVal(a) + intVal(b);
					}, 0);

				// Total over this page
				/*pageTotal = api
					.column( 3, { page: 'current'} )
					.data()
					.reduce( function (a, b) {
						return intVal(a) + intVal(b);
					}, 0 );*/

				// Update footer
				$(api.column(4).footer()).html(numFormat(total3));
			}
		});
		/*t.on('order.dt search.dt', function () {
			 t.column(0, {search:'applied', order:'applied'}).nodes().each( function (cell, i) {
				cell.innerHTML = i+1;
				t.cell(cell).invalidate('dom');
			 });
	   }).draw();*/
	});
});