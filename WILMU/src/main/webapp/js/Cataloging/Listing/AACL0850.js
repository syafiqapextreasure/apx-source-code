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

	$('#branch').multiselect({
		allSelectedText: 'All',
		maxHeight: 200,
		includeSelectAllOption: true,
		numberDisplayed: 1,
	});

	$('#branch').multiselect("disable");

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


	//////CONTROL NO
	$("#chkBoxRangeControlNo").on('change', function() {
		var self = $(this);
		if (self.is(":checked")) {
			$("#startInputCtrlno, #endInputCtrlno").prop("disabled", false);
			$('#Reterive').prop('disabled', true);
		} else {
			$("#startInputCtrlno, #endInputCtrlno").prop("disabled", true);
			$('#Reterive').prop('disabled', false);
			$("#startInputCtrlno, #endInputCtrlno").val("");
			$("#startInputCtrlno, #endInputCtrlno").css("border", "");
		}
	});

	/////validate control number
	$("#startInputCtrlno, #endInputCtrlno").focusout(function(e) {
		e.preventDefault();
		e.stopImmediatePropagation();

		if (parseFloat($("#startInputCtrlno").val()) > parseFloat($("#endInputCtrlno").val())) {
			$("#startInputCtrlno, #endInputCtrlno").css("border", "solid red");
			$("#Reterive").prop('disabled', true);
			messageBox("172", "", "");
			$("#chkBoxRange").focus();
		} else {
			$(".error").css("display", "none");
			$("#startInputCtrlno, #endInputCtrlno").css("border", "");
			$("#Reterive").prop('disabled', false);
		}
	});


	//////officer id
	////Patron
	$('.patronid').on('click', function() {
		var url = "Modal_PatrSearch";
		$.get(url, function(data) {
			$("#Modal_PatrSearchContent").html(data);
		});
	});

	///keyup
	$("#lblPatronID").on("keypress keyup blur", function(e) {
		var id = $("#lblPatronID").val();
		$(".lblName").empty();
		////display vendor name
		$.get('GetPatronName', {
			id: id
		}, function(responseJson) {
			$('#Reterive').prop('disabled', true);
			if (responseJson[0].Name.length > 1) {
				checkIdExist = responseJson[0].Name
				$(".lblName").text(responseJson[0].Name);
				$('#Reterive').prop('disabled', false);
			}
		});
	});


	//clear paron id keydown backspace
	$("#lblPatronID").keydown(function(e) {
		var code = e.keyCode || e.which;
		if (code == '8' || code == '46') {
			$("#.lblName").empty();
		}
		if (code == 13) {
			e.preventDefault();
			return false;
		}
	});


	//////////////////////////////////////////////////////////////////////////
	////////////////////////btn Reterive Click ///////////////////////////////
	//////////////////////////////////////////////////////////////////////////
	$('#Reterive').click(function() {
		$('#catreportTable').dataTable().fnClearTable();

		////database selection
		var cat = 0;
		var ser = 0;
		var acq = 0;

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

		////branch 
		var chkBrnch = $('#chkBoxBranch').is(":checked");
		var brnch = $("#branch").val();
		if (chkBrnch == true) {
			brnch = $("#branch").val()
			if (brnch.length == 0) {
				brnch = 0;
			}
		} else {
			brnch = 0;
		}

		/////control number range
		var chkRange = $('#chkBoxRangeControlNo').is(":checked");
		var rangeFirstVal;
		var rangeLastVal;

		if (chkRange == true) {
			rangeFirstVal = $('input[name=startInputCtrlno]').val();
			rangeLastVal = $('input[name=endInputCtrlno]').val();
		} else {
			rangeFirstVal = "";
			rangeLastVal = "";
		}


		//Vendor AND Patron Id
		var chkBoxSearchCateria = $('#chkBoxSearchCateria').is(":checked");

		var patronID;
		if (chkBoxSearchCateria == true) {
			patronID = $("#lblPatronID").val();
		} else {
			patronID = 0;
		}

		//////view mode
		var viewFormat = $("input[name='viewFormat']:checked").val();


		/////result display
		var t = $('#catreportTable').DataTable({
			dom: 'Bfrtip',
			paging: false,
			info: false,
			buttons: [
				{
					text: "Print",
					className: 'Print',
					action: function(e, dt, node, config) {
						var pageTitle = 'Buffer Edit List',
							stylesheet = '//maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css',
							win = window.open('', 'Print', '');
						win.document.write('<html><head><title>' + pageTitle + '</title>' +
							'<link rel="stylesheet" href="' + stylesheet + '">' +
							'</head><body>' +
							'<table border="1"' + $('#catreportTable')[0].outerHTML + '</table></body></html>')
						win.document.close();
						win.print();
						win.close();
						return false;
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
				url: "getBufferEditListing",
				type: "GET",
				data: {
					chkBrnch: chkBrnch,
					chkRange: chkRange,
					chkBoxSearchCateria: chkBoxSearchCateria,
					dateSelection: dateSelection,
					startSentDate: startSentDate,
					endSentDate: endSentDate,
					brnch: JSON.stringify(brnch),
					rangeFirstVal: rangeFirstVal,
					rangeLastVal: rangeLastVal,
					patronID: patronID,
					viewFormat: viewFormat
				},
				dataSrc: function(json) {
					var return_data = new Array();
					var count = 1;
					for (var i = 0; i < json.length; i++) {
						if (i > 0) {
							if (json[i].Column2 != json[i - 1].Column2) {
								count++;
							}
						}
						return_data.push({
							'Branch': "Branch : " + json[i].Column1,
							'Control No': count + ". Control No : " + json[i].Column2 + ",&emsp; Catalog Date : " + json[i].Column3
								+ ",&emsp; Index Date : " + json[i].Column4
								+ ",&emsp; Catalog Officer : " + json[i].Column5
								+ ",&emsp; Index Officer : " + json[i].Column6,
							'Catalog Date': json[i].Column3,
							'Index Date': json[i].Column4,
							'Catalog Officer': json[i].Column5,
							'Index Officer': json[i].Column6,
							'Tag': json[i].Column7,
							'Description': json[i].Column8,
							'Indicator 1': json[i].Column9,
							'Indicator 2': json[i].Column10,
							'Data': json[i].Column11
						})
					}
					return return_data;
				},
			},//This is the end of the AJAX object from the example above
			columns: [
				{ 'data': 'Branch', visible: false },
				{ 'data': 'Control No', visible: false },
				{ 'data': 'Catalog Date', type: 'date-uk', targets: 0, visible: false },
				{ 'data': 'Index Date', type: 'date-uk', targets: 0, visible: false },
				{ 'data': 'Catalog Officer', visible: false },
				{ 'data': 'Index Officer', visible: false },
				{ 'data': 'Tag' },
				{ 'data': 'Description' },
				{ 'data': 'Indicator 1' },
				{ 'data': 'Indicator 2' },
				{ 'data': 'Data' },
			],
			drawCallback: function(settings) {
				var api = this.api();
				var rows = api.rows({ page: 'current' }).nodes();
				var last = null;
				var columns = [1, 2];

				for (c = 0; c < columns.length; c++) {
					var colNo = columns[c];
					api.column(c, { page: 'current' }).data().each(function(group, i) {
						if (last !== group) {
							var rowData = api.row(i).data();
							$(rows).eq(i).before(
								'<tr class="group"><td colspan="7"><b>' + group + '</b></td></tr>'
							);
							last = group;
						}
					});
				}
			},
		});
		if (viewFormat == 'MARC') {
			t.column([7]).visible(false);
		} if (viewFormat == 'Linear') {
			t.column([7]).visible(true);
		}
	});
});