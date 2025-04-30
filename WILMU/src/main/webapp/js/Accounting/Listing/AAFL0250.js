$(document).ready(function() {
	///////////////////// set to be multiselect  //////////////////
	$('#byBranch-select, #byLocation-select, #bySMD-select, #byItemCategory-select').multiselect({
		allSelectedText: 'All',
		maxHeight: 200,
		includeSelectAllOption: true
	});

	///////////////////// input-toDate set Current Date  //////////////////
	$('#input-toDate').val(moment(new Date()).format("DD/MM/YYYY"));

	$('#input-fromDate, #input-toDate').datepicker({
		format: "dd/mm/yyyy",
		todayBtn: true,
		autoclose: true,
		todayHighlight: true,
	});

	// table setup for Branch only
	$('#reportTableBranch').DataTable({
		destroy: true,
		searching: false,
		bLengthChange: false,
		autoWidth: false,
		responsive: true,
	});

	// table setup not Branch only
	$('#reportTableNotBranch').DataTable({
		destroy: true,
		searching: false,
		bLengthChange: false,
		autoWidth: false,
		responsive: true,
	});

	$('#reportTableNotBranch_wrapper').css({ "display": "none" })

	$("#Reterive").attr('disabled', 'disabled');
	$("#byBranch").prop("checked", true);
	$("#byLocation-select, #bySMD-select, #byItemCategory-select").multiselect("disable");
	var nameSelection;
	var selectClickedHistory;

	$("input[type=radio][name=selectOption]").on('click', function() {
		var chkBoxSearchCateria = $("input[type=radio][name=selectOption]:checked").val();
		$("#Reterive").attr('disabled', 'disabled');
		$('#byBranch-select, #byLocation-select, #bySMD-select, #byItemCategory-select').multiselect('clearSelection');
		switch (chkBoxSearchCateria) {
			case 'byBranch':
				nameSelection = 'List by Branch';
				$('#byBranch-select').multiselect("enable");
				$('#byLocation-select, #bySMD-select, #byItemCategory-select').multiselect("disable");
				break;
			case 'byLocation':
				nameSelection = 'List by Location';
				$('#byLocation-select').multiselect("enable");
				$('#byBranch-select, #bySMD-select, #byItemCategory-select').multiselect("disable");
				break;
			case 'bySMD':
				nameSelection = 'List by SMD';
				$('#bySMD-select').multiselect("enable");
				$('#byBranch-select, #byLocation-select, #byItemCategory-select').multiselect("disable");
				break;
			case 'byItemCategory':
				nameSelection = 'List by Item Category';
				$('#byItemCategory-select').multiselect("enable");
				$('#byBranch-select, #byLocation-select, #bySMD-select').multiselect("disable");
				break;
		}

		if (chkBoxSearchCateria == 'byBranch') {
			$('#reportTableNotBranch_wrapper').css({ "display": "none" })
			$('#reportTableBranch_wrapper').css({ "display": "block" })
		}
		if (chkBoxSearchCateria != 'byBranch') {
			$('#reportTableBranch_wrapper').css({ "display": "none" })
			$('#reportTableNotBranch_wrapper').css({ "display": "block" })
		}
	})

	////////////// select option data onchange and click  ////////////////////
	$('#byBranch-select, #byLocation-select, #bySMD-select, #byItemCategory-select').on('change click', function(e) {
		selectClickedHistory = e.target.id
		checkConditionEnable()
	});

	////////////// input-fromDate and input-toDate onchange  ////////////////////
	$('#input-fromDate, #input-toDate').on('change input', function() {
		checkConditionEnable()
	});

	function checkConditionEnable() {
		$("#Reterive").attr('disabled', 'disabled');
		if (selectClickedHistory != undefined) {
			if ($("select[name=" + selectClickedHistory + "]").val().length > 0 && ($('#input-fromDate').val().length > 0 || $('#input-toDate').val().length > 0)) {
				$("#Reterive").removeAttr('disabled');
			}
		}
	}

	var return_data_GroupBy = new Array();
	var return_data_Branch = new Array();


	//////////////////////////////////////////////////////////////////////////
	////////////////////////btn Reterive Click ///////////////////////////////
	//////////////////////////////////////////////////////////////////////////
	$('#Reterive').click(function() {
		var selectOption = $("input[type=radio][name=selectOption]:checked").val();
		var dataSelect = $("select[name=" + selectClickedHistory + "]").val()
		var startSentDate = moment($("#input-fromDate").val(), 'DD/MM/YYYY').format("YYYYMMDD");
		var endSentDate = moment($("#input-toDate").val(), 'DD/MM/YYYY').format("YYYYMMDD");
		var title = 'WILMU Holding / Assets Listing\nCreated Date ';

		if ($("#input-fromDate").val().length == 0 && $("#input-toDate").val().length > 0) {
			title = title + 'until ' + endSentDate;
			startSentDate = '';
		}
		if ($("#input-fromDate").val().length > 0 && $("#input-toDate").val().length == 0) {
			title = title + 'from ' + startSentDate;
			endSentDate = '';
		}
		if ($("#input-fromDate").val().length > 0 && $("#input-toDate").val().length > 0) {
			title = title + 'from ' + $("#input-fromDate").val() + ' until ' + $("#input-toDate").val();
		}

		var chkBoxSearchCateria = $("input[type=radio][name=selectOption]:checked").val();
		switch (chkBoxSearchCateria) {
			case 'byBranch':
				nameSelection = 'List by Branch'
				title = title + '\n' + nameSelection;
				break;
			case 'byLocation':
				nameSelection = 'List by Location'
				title = title + '\n' + nameSelection;
				break;
			case 'bySMD':
				nameSelection = 'List by SMD';
				title = title + '\n' + nameSelection;
				break;
			case 'byItemCategory':
				nameSelection = 'List by Item Category';
				title = title + '\n' + nameSelection;
				break;
		}

		if (selectOption == 'byBranch') {
			/////result display for Branch
			var t = $('#reportTableBranch').DataTable({
				dom: 'Bfrtip',
				info: false,
				buttons: [
					{
						extend: 'excelHtml5',
						filename: 'WILMU_Holding Assets Listing',
						title: title,
					},
					{
						text: '<i class="fa fa-file-pdf-o"></i> PDF',
						action: function() {
							var dd = {
								pageOrientation: 'landscape', // Add this property to specify landscape orientation
								content: [
									{ text: title + '\n', style: 'title', alignment: 'center' },
									{
										style: 'tableExample',
										color: '#444',
										table: {
											widths: ['20%', '20%', '20%', '20%', '20%'],
											body: [
												[
													{ text: 'Code', style: 'tableHeader', alignment: 'center', color: 'white' },
													{ text: 'Description', style: 'tableHeader', alignment: 'center', color: 'white' },
													{ text: 'Titles', style: 'tableHeader', alignment: 'center', color: 'white' },
													{ text: 'Copies', style: 'tableHeader', alignment: 'center', color: 'white' },
													{ text: 'Amount', style: 'tableHeader', alignment: 'center', color: 'white' }],
											]
										}
									},
								],
								footer: function(currentPage, pageCount) {
									return {
										columns: [
											{
												alignment: 'left',
												text: ['\t\t\t\t  ', moment(new Date()).format("DD/MM/YYYY") + '\t' + moment(new Date()).format("hh:mm:ss A")],
												margin: [20, 0, 0, 0] // Adjust the left margin here
											},
											{
												alignment: 'right',
												text: [`Page ${currentPage.toString()} of ${pageCount.toString()}`],
												margin: [0, 0, 20, 0] // Adjust the right margin here
											}
										],
									}
								},
								styles: {
									tableExample: {
										margin: [0, 5, 0, 15]
									},
									tableHeader: {
										fillColor: '#2d4154', // Color for header cells
										bold: true
									},
									tableBodyGroupBranchName: {
										fillColor: '#bfcddb' // Color for group cells
									},
									tableBodyTotal: {
										fillColor: '#c0c0c0' // Color for total cells
									}
								},
							}

							var setCodeGroupBy = new Set(return_data_GroupBy.map(item => item.codeGroupBy));
							var setBranch = new Set(return_data_Branch.map(item => item.Branch));

							var newArrCodeGroupBy = [...setCodeGroupBy];
							var newArrBranch = [...setBranch];
							var tableData = t.data().toArray();

							var countTitle = 0;
							var countCopies = 0;
							var countAmount = 0;

							for (i = 0; i < newArrCodeGroupBy.length; i++) {// for number of group by in CASE SQL statement
								dd.content[1].table.body.push(
									[{ colSpan: 5, text: 'Branch : ' + newArrBranch[i], style: 'tableBodyGroupBranchName' }]
								)
								for (j = 0; j < tableData.length; j++) { // to check if same number of group by or not
									if (tableData[j].CodeByGroup == newArrCodeGroupBy[i]) {
										countTitle = parseFloat(countTitle) + parseFloat(tableData[j].Titles);
										countCopies = parseFloat(countCopies) + parseFloat(tableData[j].Copies);
										countAmount = parseFloat(countAmount) + parseFloat(tableData[j].Amount);

										dd.content[1].table.body.push(
											[
												{ text: tableData[j].Code },
												{ text: tableData[j].Description },
												{ text: tableData[j].Titles, alignment: 'center' },
												{ text: tableData[j].Copies, alignment: 'center' },
												{ text: tableData[j].Amount, alignment: 'right' }
											]
										);
									}
								};
								dd.content[1].table.body.push(
									[
										{ text: 'Total', bold: true, colSpan: 2, style: 'tableBodyOdd', border: [true, true, false, true], style: 'tableBodyTotal' },
										{ text: '', style: 'tableBodyOdd' },
										{ text: countTitle, bold: true, style: 'tableBodyOdd', alignment: 'center', border: [false, true, false, true], style: 'tableBodyTotal' },
										{ text: countCopies, bold: true, style: 'tableBodyOdd', alignment: 'center', border: [false, true, false, true], style: 'tableBodyTotal' },
										{ text: countAmount.toFixed(2), bold: true, style: 'tableBodyOdd', alignment: 'right', border: [false, true, true, true], style: 'tableBodyTotal' }
									]
								)
								countTitle = 0;
								countCopies = 0;
								countAmount = 0;
							};

							// Generate the PDF
							pdfMake.createPdf(dd).download('WILMU_Holding Assets Listing.pdf');
						}
					},
					{
						extend: 'csv',
						filename: 'WILMU_Holding Assets Listing',
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
					url: "getHoldingAssetsList",
					data: {
						startSentDate: startSentDate,
						endSentDate: endSentDate,
						selectOption: selectOption,
						dataSelect: JSON.stringify(dataSelect)
					},
					type: "GET",
					dataSrc: function(json) {
						var return_data = new Array();
						$.each(json, function(key, value) {
							return_data.push({
								'Branch': value.branch,
								'Code': value.code,
								'Description': value.desc,
								'Titles': value.title,
								'Copies': value.copy,
								'Amount': value.amount,
								'CodeByGroup': value.specialCode,
							})

							return_data_GroupBy.push({
								'codeGroupBy': value.specialCode
							})

							return_data_Branch.push({
								'Branch': value.branch
							})
						});
						return return_data;
					},
				},
				columns: [
					{ "data": "Branch" },
					{ "data": "Code" },
					{ "data": "Description" },
					{ "data": "Titles" },
					{ "data": "Copies" },
					{ "data": "Amount" },
					{ "data": "CodeByGroup" },
				],
				columnDefs: [{ visible: false, targets: 0 }, { visible: false, targets: 6 }], // columnDefs: [{ visible: false, targets: 0 }, { visible: false, targets: 1 }],
				drawCallback: function(settings) {
					var api = this.api();
					var rows = api.rows({ page: 'current' }).nodes();
					var last = null;

					api.column(0, { page: 'current' }).data().each(function(group, i) {
						if (last !== group) {
							$(rows).eq(i).before('<tr class="group"><td colspan="6">Branch : ' + group + '</td></tr>');
							last = group;
						}
					});
				},
			});
		}
		if (selectOption != 'byBranch') {
			/////result display for not Branch
			var t = $('#reportTableNotBranch').DataTable({
				dom: 'Bfrtip',
				info: false,
				buttons: [
					{
						extend: 'excelHtml5',
						filename: 'WILMU_Holding Assets Listing',
						title: title,
					},
					{
						text: '<i class="fa fa-file-pdf-o"></i> PDF',
						extend: 'pdfHtml5',
						filename: 'WILMU_Holding Assets Listing',
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
								doc.content[1].table.body[i][2].alignment = 'center';
								doc.content[1].table.body[i][3].alignment = 'center';
								doc.content[1].table.body[i][4].alignment = 'center';
							};

							doc.content[1].table.widths = Array(doc.content[1].table.body[0].length + 1).join('*').split('');
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
						filename: 'WILMU_Holding Assets Listing',
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
					url: "getHoldingAssetsList",
					data: {
						startSentDate: startSentDate,
						endSentDate: endSentDate,
						selectOption: selectOption,
						dataSelect: JSON.stringify(dataSelect)
					},
					type: "GET",
					dataSrc: function(json) {
						var return_data = new Array();
						$.each(json, function(key, value) {
							return_data.push({
								'Code': value.code,
								'Description': value.desc,
								'Titles': value.title,
								'Copies': value.copy,
								'Amount': value.amount
							})
						});
						return return_data;
					},
				},
				columns: [
					{ "data": "Code" },
					{ "data": "Description" },
					{ "data": "Titles" },
					{ "data": "Copies" },
					{ "data": "Amount" },
				],
				footerCallback: function(row, data, start, end, display) {
					var api = this.api()

					// total Title header amount over all pages
					var totalTitle = api.column(2).data().reduce(function(a, b) {
						return Number(a) + Number(b);
					}, 0);

					// total Copy header amount over all pages
					var totalCopy = api.column(3).data().reduce(function(a, b) {
						return Number(a) + Number(b);
					}, 0);

					// total Amount header amount over all pages
					var totalAmount = api.column(4).data().reduce(function(a, b) {
						return Number(a) + Number(b);
					}, 0);

					$("#totalTitle").empty();
					$("#totalCopy").empty();
					$("#totalAmount").empty();

					$("#totalTitle").append(totalTitle);
					$("#totalCopy").append(totalCopy);
					$("#totalAmount").append(totalAmount.toFixed(2));
				},
			});
		}
	})
});