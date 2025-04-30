$(document).ready(function() {
	function messageBox(code, criteria, label) {
		$.get("Global?type=Handler&name=Error_Page", {
			GL79ERRCODE: code,
			criteria: criteria,
			label: label
		}, function(result) {
			swal(result);
		});
	}


	$('#databaseType').multiselect({
		allSelectedText: 'All',
		maxHeight: 200,
		includeSelectAllOption: true,
		numberDisplayed: 1,
	});

	//////Database
	$("#databaseType").on('change', function() {
		var numberNotChecked = $('#databaseType').val().length;
		if (numberNotChecked == 0) {
			$('#Reterive').prop('disabled', true);
		} else {
			$('#Reterive').prop('disabled', false);
		}
	});

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


	////////BRANCH
	$("#chkBoxBranch").on('change', function() {
		var self = $(this);
		if (self.is(":checked")) {
			$('#branch').multiselect("enable");
			$('#Reterive').prop('disabled', true);
			$('#branch').change(function() {
				if ($("#branch").val() != "") {
					$('#Reterive').prop('disabled', false);
				} else {
					$('#Reterive').prop('disabled', true);
				}
			});
		} else {
			$('#branch').multiselect("disable");
			$('#Reterive').prop('disabled', false);
			$("#branch").multiselect("clearSelection")
			$("#branch").multiselect('refresh');
		}
	});


	////////ACCESSION NUMBER
	$("#chkBoxRangeAcc").on('change', function() {
		var self = $(this);
		if (self.is(":checked")) {
			$("#startInputAcc, #endInputAcc").prop("disabled", false);
			$('#Reterive').prop('disabled', true);
		} else {
			$("#startInputAcc, #endInputAcc").prop("disabled", true);
			$('#Reterive').prop('disabled', false);
			$("#startInputAcc, #endInputAcc").val("");
			$("#startInputAcc, #endInputAcc").css("border", "");
		}
	});

	$("#startInputAcc, #endInputAcc").keyup(function(e) {
		e.preventDefault();
		var startInputAcc = $("#startInputAcc").val();
		var endInputAcc = $("#endInputAcc").val();
		if (startInputAcc.length >= 1 || endInputAcc.length >= 1) {
			$('#Reterive').prop('disabled', false);
		} else {
			$('#Reterive').prop('disabled', true);
		}
	});

	$("#startInputAcc, #endInputAcc").focusout(function(e) {
		e.preventDefault();
		e.stopImmediatePropagation();

		if (parseFloat($("#startInputAcc").val()) > parseFloat($("#endInputAcc").val())) {
			$("#startInputAcc, #endInputAcc").css("border", "solid red");
			$("#Reterive").prop('disabled', true);
			messageBox("172", "", "");
			$("#chkBoxRangeAcc").focus();
		} else {
			$(".error").css("display", "none");
			$("#startInputAcc, #endInputAcc").css("border", "");
			$("#Reterive").prop('disabled', false);
		}
	});


	//////////CALL NUMBER
	$("#chkBoxRangeCallno").on('change', function() {
		var self = $(this);
		if (self.is(":checked")) {
			$("#startInputCallno, #endInputCallno").prop("disabled", false);
			$('#Reterive').prop('disabled', true);
		} else {
			$("#startInputCallno, #endInputCallno").prop("disabled", true);
			$('#Reterive').prop('disabled', false);
			$("#startInputCallno, #endInputCallno").val("");
			$("#startInputCallno, #endInputCallno").css("border", "");
		}
	});

	$("#startInputCallno, #endInputCallno").keyup(function(e) {
		e.preventDefault();
		var startInputCallno = $("#startInputCallno").val();
		var endInputCallno = $("#endInputCallno").val();
		if (startInputCallno.length >= 1 || endInputCallno.length >= 1) {
			$('#Reterive').prop('disabled', false);
		} else {
			$('#Reterive').prop('disabled', true);
		}
	});

	$("#startInputCallno, #endInputCallno").focusout(function(e) {
		e.preventDefault();
		e.stopImmediatePropagation();

		if (parseFloat($("#startInputCallno").val()) > parseFloat($("#endInputAcc").val())) {
			$("#startInputCallno, #endInputCallno").css("border", "solid red");
			$("#Reterive").prop('disabled', true);
			messageBox("172", "", "");
			$("#chkBoxRangeCallno").focus();
		} else {
			$(".error").css("display", "none");
			$("#startInputCallno, #endInputCallno").css("border", "");
			$("#Reterive").prop('disabled', false);
		}
	});


	////////TITLE
	$("#chkBoxTitle").on('change', function() {
		var self = $(this);
		if (self.is(":checked")) {
			$("#inputtitle").prop("disabled", false);
			$('#Reterive').prop('disabled', true);
		} else {
			$("#inputtitle").prop("disabled", true);
			$('#Reterive').prop('disabled', false);
			$("#inputtitle").val("");
		}
	});

	$("#inputtitle").keyup(function(e) {
		e.preventDefault();
		var inputtitle = $("#inputtitle").val();
		if (inputtitle.length >= 1) {
			$('#Reterive').prop('disabled', false);
		} else {
			$('#Reterive').prop('disabled', true);
		}
	});



	//////////SMD
	$("#chkBoxsmd").on('change', function() {
		var self = $(this);
		if (self.is(":checked")) {
			$('#smd').multiselect("enable");
			$('#Reterive').prop('disabled', true);
			$('#smd').change(function() {
				if ($("#smd").val() != "") {
					$('#Reterive').prop('disabled', false);
				} else {
					$('#Reterive').prop('disabled', true);
				}
			});
		} else {
			$('#smd').multiselect("disable");
			$('#Reterive').prop('disabled', false);
			$("#smd").multiselect("clearSelection")
			$("#smd").multiselect('refresh');
		}
	});


	//////////Item Status
	$("#chkBoxIcat").on('change', function() {
		var self = $(this);
		if (self.is(":checked")) {
			$('#icat').multiselect("enable");
			$('#Reterive').prop('disabled', true);
			$('#icat').change(function() {
				if ($("#icat").val() != "") {
					$('#Reterive').prop('disabled', false);
				} else {
					$('#Reterive').prop('disabled', true);
				}
			});
		} else {
			$('#icat').multiselect("disable");
			$('#Reterive').prop('disabled', false);
			$("#icat").multiselect("clearSelection")
			$("#icat").multiselect('refresh');
		}
	});


	//////////Location
	$("#chkBoxlocation").on('change', function() {
		var self = $(this);
		if (self.is(":checked")) {
			$('#loca').multiselect("enable");
			$('#Reterive').prop('disabled', true);
			$('#loca').change(function() {
				if ($("#loca").val() != "") {
					$('#Reterive').prop('disabled', false);
				} else {
					$('#Reterive').prop('disabled', true);
				}
			});
		} else {
			$('#loca').multiselect("disable");
			$('#Reterive').prop('disabled', false);
			$("#loca").multiselect("clearSelection")
			$("#loca").multiselect('refresh');
		}
	});

	//////////Status
	$("#chkBoxdocStatus").on('change', function() {
		var self = $(this);
		if (self.is(":checked")) {
			$('#doctatus').multiselect("enable");
			$('#Reterive').prop('disabled', true);
			$('#doctatus').change(function() {
				if ($("#doctatus").val() != "") {
					$('#Reterive').prop('disabled', false);
				} else {
					$('#Reterive').prop('disabled', true);
				}
			});
		} else {
			$('#doctatus').multiselect("disable");
			$('#Reterive').prop('disabled', false);
			$("#doctatus").multiselect("clearSelection")
			$("#doctatus").multiselect('refresh');
		}
	});


	//////////////////////////////////////////////////////////////////////////
	////////////////////////btn Reterive Click ///////////////////////////////
	//////////////////////////////////////////////////////////////////////////
	$('#Reterive').click(function() {
		$('#catreportTable').dataTable().fnClearTable();

		///date selection
		var dateSelection = $('input[name="dateSelection"]:checked').val();
		var dateSelectionLabel = $("input[name='dateSelection']:checked").data('name');

		var startSentDate = moment($("#input-startDate").val(), 'DD/MM/YYYY').format("YYYYMMDD");
		var endSentDate = moment($("#input-endDate").val(), 'DD/MM/YYYY').format("YYYYMMDD");
		var sDate, eDate;

		var startSentDate = $("#input-startDate").val();
		if (startSentDate == '') {
			startSentDate = '';
			sDate = dateSelectionLabel + '';
		} else {
			startSentDate = moment($("#input-startDate").val(), 'DD/MM/YYYY').format("YYYYMMDD");
			sDate = dateSelectionLabel + " from " + $("#input-startDate").val();
		}

		var endSentDate = $("#input-endDate").val();
		if (endSentDate == '') {
			endSentDate = '';
			eDate = '';
		} else {
			endSentDate = moment($("#input-endDate").val(), 'DD/MM/YYYY').format("YYYYMMDD");
			eDate = " until " + $("#input-endDate").val();
		}

		////database selection		
		var getDBselection = JSON.stringify($('#databaseType').val())

		////branch
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


		////accno
		var chkBoxRangeAcc = $('#chkBoxRangeAcc:checked').val();
		var startInputAcc;
		var endInputAcc;

		if (chkBoxRangeAcc == "rangeAcc") {
			startInputAcc = $("#startInputAcc").val();
			endInputAcc = $("#endInputAcc").val();
		} else {
			startInputAcc = '';
			endInputAcc = '';
		}


		////callno
		var chkBoxRangeCallno = $('#chkBoxRangeCallno:checked').val();
		var startInputCallno;
		var endInputCallno;

		if (chkBoxRangeCallno == "rangeCallno") {
			startInputCallno = $("#startInputCallno").val();
			endInputCallno = $("#endInputCallno").val();
		} else {
			startInputCallno = '';
			endInputCallno = '';
		}

		///title
		var chkBoxTitle = $('#chkBoxTitle:checked').val();
		var inputtitle;

		if (chkBoxTitle == "iTilte") {
			inputtitle = $("#inputtitle").val();
		} else {
			inputtitle = 0;
		}

		//smd
		var smd = $("#smd").val();
		if (smd != "") {
		} else {
			smd = 0;
		}

		//icat
		var icat = $("#icat").val();
		if (icat != "") {
		} else {
			icat = 0;
		}

		//loca
		var loca = $("#loca").val();
		if (loca != "") {
		} else {
			loca = 0;
		}

		//Status
		var doctatus = $("#doctatus").val();
		if (doctatus != "") {
		} else {
			doctatus = 0;
		}

		var t = $('#catreportTable').DataTable({
			dom: 'Bfrtip',
			buttons: [
				{
					extend: 'excelHtml5',
					filename: 'WILMU_CatandHoldingList',
					title: 'Catalog and Holding List ' + sDate + eDate,
				},
				{
					text: '<i class="fa fa-file-pdf-o"></i> PDF',
					extend: 'pdfHtml5',
					filename: 'WILMU_CatandHoldingList',
					orientation: 'landscape', //portrait
					pageSize: 'A4', //A3 , A5 , A6 , legal , letter
					exportOptions: {
						columns: [0, 1, 2, 3, 4, 5, 8, 10, 11, 12, 13, 14, 15, 17],
					},
					footer: true,
					customize: function(doc) {
						doc.styles.tableHeader.alignment = 'left';
						doc.defaultStyle.fontSize = 8;

						//Remove the title created by datatTables  
						doc.content.splice(0, 1, {
							text: [
								{ text: 'Catalog and Holding List ' + sDate + eDate + '\n', bold: true, fontSize: 13, alignment: 'center' }],
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
					filename: 'WILMU_CatandHoldingList',
					charset: "utf-8",
					bom: "true",
					customize: function(doc) {
						var split_csv = doc.split("\n");
						$.each(split_csv.slice(1), function(index, csv_row) {
							var csv_cell_array = csv_row.split('","');
							csv_cell_array[1].replace("", (index + 1));
						});
						return 'Catalog and Holding List ' + sDate + eDate + "\n\n" + doc;
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
				url: "ResultCatandHoldingList",
				type: "GET",
				data: {
					getDBselection: getDBselection,
					dateSelection: dateSelection,
					startSentDate: startSentDate, endSentDate: endSentDate,
					allbranch: allbranch, brnch: JSON.stringify(brnch),
					startInputAcc: startInputAcc, endInputAcc: endInputAcc,
					startInputCallno: startInputCallno, endInputCallno: endInputCallno,
					inputtitle: inputtitle,
					smd: JSON.stringify(smd),
					icat: JSON.stringify(icat),
					loca: JSON.stringify(loca),
					doctatus: JSON.stringify(doctatus),
				},
				dataSrc: function(json) {
					var return_data = new Array();
					for (var i = 0; i < json.length; i++) {
						return_data.push({
							'No': (i + 1),
							'Control No': json[i].Column1,
							'Acc No': json[i].Column2,
							'Title': json[i].Column3,
							'Call No': json[i].Column4,
							'Author': json[i].Column5,
							'Edition': json[i].Column6,
							'Publisher': json[i].Column7,
							'ISBN': json[i].Column8,
							'Volume': json[i].Column9,
							'SMD': json[i].Column10,
							'ICAT': json[i].Column11,
							'Location': json[i].Column12,
							'Status': json[i].Column13,
							'Local Price': json[i].Column14,
							'Vendor': json[i].Column15,
							'Officer': json[i].Column16,
							'Branch': json[i].Column17,
							'Subject': json[i].Column17,
						})
					}
					return return_data;
				}, error: function(xhr, error, thrown) {
					// This function is called when there is an error (status code 500) from the server
					// You can display an error message to the user or take appropriate action based on the error.
					alert("An error occurred while loading data. Please try again later.");
				},
			},//This is the end of the AJAX object from the example above
			columns: [
				{ 'data': 'No' },
				{ 'data': 'Control No' },
				{ 'data': 'Acc No' },
				{ 'data': 'Title', },
				{ 'data': 'Call No', },
				{ 'data': 'Author' },
				{ 'data': 'Edition' },
				{ 'data': 'Publisher' },
				{ 'data': 'ISBN' },
				{ 'data': 'Volume' },
				{ 'data': 'SMD' },
				{ 'data': 'ICAT' },
				{ 'data': 'Location' },
				{ 'data': 'Status' },
				{ 'data': 'Local Price', className: "text-right" },
				{ 'data': 'Vendor' },
				{ 'data': 'Officer' },
				{ 'data': 'Branch' },
				{ 'data': 'Subject' },
			],
		});
	});
});