$(document).ready(function() {
	///////////////////// set to be multiselect  //////////////////
	$('#byBranch-select, #bySMD-select, #byItemCategory-select').multiselect({
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

	// table setup
	$('#reportTable').DataTable({
		destroy: true,
		searching: false,
		bLengthChange: false,
		autoWidth: false,
		responsive: true,
	});

	$("#Reterive").attr('disabled', 'disabled');
	$("#byBranch").prop("checked", true);
	$("#bySMD-select, #byItemCategory-select").multiselect("disable");
	var nameSelection = 'List by Branch';
	var selectClickedHistory;

	$("input[type=radio][name=selectOption]").on('click', function() {
		var chkBoxSearchCateria = $("input[type=radio][name=selectOption]:checked").val();
		$("#Reterive").attr('disabled', 'disabled');
		$('#byBranch-select, #bySMD-select, #byItemCategory-select').multiselect('clearSelection');
		switch (chkBoxSearchCateria) {
			case 'byBranch':
				nameSelection = 'List by Branch';
				$('#byBranch-select').multiselect("enable");
				$('#bySMD-select, #byItemCategory-select').multiselect("disable");
				break;
			case 'bySMD':
				nameSelection = 'List by SMD';
				$('#bySMD-select').multiselect("enable");
				$('#byBranch-select, #byItemCategory-select').multiselect("disable");
				break;
			case 'byItemCategory':
				nameSelection = 'List by Item Category';
				$('#byItemCategory-select').multiselect("enable");
				$('#byBranch-select, #bySMD-select').multiselect("disable");
				break;
		}
	})

	$('#vendorCode-text').attr('disabled', 'disabled');  // DISABLE VENDOR CODE SELECTION
	$("input[type=checkbox][name=selectVendorCode]").on('click', function() {
		$('#vendorCode-text').attr('disabled', 'disabled');
		if ($("#byVendorCode").prop("checked") == true) {
			$('#vendorCode-text').removeAttr('disabled');
		}
	})

	////////////// select option data onchange and click  ////////////////////
	$('#byBranch-select, #bySMD-select, #byItemCategory-select').on('change click', function(e) {
		selectClickedHistory = e.target.id
		checkConditionEnable()
	});

	////////////// input-fromDate and input-toDate onchange  ////////////////////
	$('#input-fromDate, #input-toDate').on('change input', function() {
		checkConditionEnable()
	});

	function checkConditionEnable() {
		$("#Reterive").attr('disabled', 'disabled');
		if ($("select[name=" + selectClickedHistory + "]").val().length > 0 && ($('#input-fromDate').val().length > 0 || $('#input-toDate').val().length > 0)) {
			$("#Reterive").removeAttr('disabled');
		}
	}


	//////////////////////////////////////////////////////////////////////////
	////////////////////////btn Reterive Click ///////////////////////////////
	//////////////////////////////////////////////////////////////////////////
	$('#Reterive').click(function() {
		var selectOption = $("input[type=radio][name=selectOption]:checked").val();
		var dataSelect = $("select[name=" + selectClickedHistory + "]").val()
		var startSentDate = moment($("#input-fromDate").val(), 'DD/MM/YYYY').format("YYYYMMDD");
		var endSentDate = moment($("#input-toDate").val(), 'DD/MM/YYYY').format("YYYYMMDD");
		var selectVendorCode = $("input[type=checkbox][name=selectVendorCode]:checked").val();
		var vendorCode = $("select[name=vendorCode-text]").val();
		
		var title = 'WILMU Accession Processing Timeframe \n Created Date ';
		
		if ($("#byVendorCode").prop("checked") == true) {
			selectVendorCode = "byVendorCode"
		}
		if ($("#byVendorCode").prop("checked") == false) {
			selectVendorCode = "notbyVendorCode"
		}

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
		title = title + '\n ' + nameSelection

		/////result display for not Branch					
		$('#reportTable').DataTable({
			dom: 'Bfrtip',
			info: false,
			buttons: [
				{
					extend: 'excelHtml5',
					filename: 'WILMU_Accession Processing Timeframe',
					title: title,
				},
				{
					text: '<i class="fa fa-file-pdf-o"></i> PDF',
					extend: 'pdfHtml5',
					filename: 'WILMU_Accession Processing Timeframe',
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
							doc.content[1].table.body[i][8].alignment = 'left';
							doc.content[1].table.body[i][9].alignment = 'center';
							doc.content[1].table.body[i][10].alignment = 'left';
						};
						
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
					filename: 'WILMU_Accession Processing Timeframe',
					charset: "utf-8",
					bom: "true",
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
				url: "getAccessionProcessingTimeframe",
				data: {
					startSentDate: startSentDate,
					endSentDate: endSentDate,
					selectOption: selectOption,
					dataSelect: JSON.stringify(dataSelect),
					selectVendorCode: selectVendorCode,
					vendorCode: vendorCode
				},
				type: "GET",
				dataSrc: function(json) {
					var return_data = new Array();
					$.each(json, function(_key, value) {
						return_data.push({
							'Accession No': value.accessionNo,
							'Control No': value.controlNo,
							'SMD': value.smd,
							'Item Category': value.itemCategory,
							'Branch': value.branch,
							'Vendor': value.vendor,
							'Date Created': value.dateCreated,
							'Date Modified': value.dateModified,
							'Date Released': value.dateReleased,
							'Processing Days': value.processingDay,
							'Title Days': value.title,
						})
					});
					return return_data;
				},
			},
			columns: [
				{ "data": "Accession No" },
				{ "data": "Control No" },
				{ "data": "SMD" },
				{ "data": "Item Category" },
				{ "data": "Branch" },
				{ "data": "Vendor" },
				{ "data": "Date Created" },
				{ "data": "Date Modified" },
				{ "data": "Date Released" },
				{ "data": "Processing Days" },
				{ "data": "Title Days" },
			],
		});
	})
});