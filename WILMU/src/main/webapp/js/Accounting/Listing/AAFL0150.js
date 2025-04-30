$(document).ready(function() {
	////// table setup
	$('#reportTable').DataTable({
		destroy: true,
		searching: false,
		bLengthChange: false,
		autoWidth: false,
		responsive: true,
	});

	$("#Reterive, #input-fromInput, #input-toInput").attr('disabled', 'disabled');
	$("input[type=radio][name=transaction-type]").attr('disabled', 'disabled');
	$("#transaction-no").prop("checked", true);

	function checkForTransactionCodeCondition() {
		// this is for checking input -> Transaction Code from
		if ($("#input-fromCode").val().length > 0 && $("#input-toCode").val().length > 0) {
			$("#input-fromInput, #input-toInput").removeAttr('disabled');
			$("input[type=radio][name=transaction-type]").removeAttr('disabled');
		}
		else { // this is for checking input not filled in -> Transaction Code from
			$("#Reterive").attr('disabled', 'disabled');
			$("#Reterive, #input-fromInput, #input-toInput").attr('disabled', 'disabled');
			$("input[type=radio][name=transaction-type]").attr('disabled', 'disabled');
		}
	}

	$("#input-fromCode, #input-toCode").on('change input', function() {
		checkForTransactionCodeCondition()
	})

	$("#input-fromInput, #input-toInput").on('change input', function() {
		checkForInputTransaction()
	})

	function checkForInputTransaction() {
		if ($("#transaction-no").prop("checked") == true) {
			if ($("#input-fromInput").val().length > 0 && $("#input-toInput").val().length > 0) {
				$("#Reterive").removeAttr('disabled');
			}
			else {
				$("#Reterive").attr('disabled', 'disabled');
			}
		}
		if ($("#transaction-date").prop("checked") == true) {
			if ($("#input-fromInput").val().length > 0 || $("#input-toInput").val().length > 0) {
				$("#Reterive").removeAttr('disabled');
			}
			else {
				$("#Reterive").attr('disabled', 'disabled');
			}
		}
	}

	$("input[type=radio][name=transaction-type]").on('click', function() {
		$("#input-fromInput, #input-toInput").val('');

		if ($("#transaction-no").prop("checked") == true) {
			$("#Reterive").attr('disabled', 'disabled');
			$(".transaction-change").empty();
			$(".transaction-change").append('<label>Transaction No. from</label>');

			// to destroy the datepicker when change radio type
			$("#input-fromInput, #input-toInput").datepicker("destroy");
		}
		if ($("#transaction-date").prop("checked") == true) {
			$("#Reterive").removeAttr('disabled');
			$(".transaction-change").empty();
			$(".transaction-change").append('<label>Transaction Date from</label>');
			$('#input-toInput').val(moment(new Date()).format("DD/MM/YYYY"));

			///////////////////// input-endDate set Current Date  ////////////////////
			$('#input-fromInput, #input-toInput').datepicker({
				format: "dd/mm/yyyy",
				todayBtn: true,
				autoclose: true,
				todayHighlight: true,
			});
		}
	})


	//////////////////////////////////////////////////////////////////////////
	////////////////////////btn Reterive Click ///////////////////////////////
	//////////////////////////////////////////////////////////////////////////
	$('#Reterive').click(function() {
		var fromCode = $("#input-fromCode").val();
		var toCode = $("#input-toCode").val();
		var transactType = $('input[type=radio][name=transaction-type]:checked').val();
		var fromInput = '';
		var toInput = '';

		if (transactType === 'number') {
			fromInput = $("#input-fromInput").val();
			toInput = $("#input-toInput").val();
		}
		if (transactType === 'date') {
			fromInput = moment($("#input-fromInput").val(), 'DD/MM/YYYY').format("YYYYMMDD");
			toInput = moment($("#input-toInput").val(), 'DD/MM/YYYY').format("YYYYMMDD");
		}

		var title = 'WILMU Fund Transaction Listing\n';
		$.ajax({
			type: "get",
			url: "getCountStatusFundTransactionList",
			data: {
				fromCode: fromCode,
				toCode: toCode,
				transactType: transactType,
				fromInput: fromInput,
				toInput: toInput,
			},
			success: function(data) {
				var titleCode = title + 'Fund Transaction Code from ' + fromCode + ' until ' + toCode + '\n';

				if (data.map.flagFundExistFA02TXCD == 0) {
					titleCode = title + 'Fund Transaction Code from ' + toCode + ' until ' + fromCode + '\n';
				}

				if (transactType === 'number') {
					title = titleCode + 'Fund Transaction No. from ' + fromInput + ' until ' + toInput;
					if (data.map.flagFundExistFA02TXNO == 0) {
						title = titleCode + 'Fund Transaction No. from ' + toInput + ' until ' + fromInput;
					}
				}
				if (transactType === 'date') {
					title = titleCode + 'Fund Transaction Date from ' + fromInput + ' until ' + toInput;
					if (data.map.flagFundExistFA02DATE == 0) {
						title = titleCode + 'Fund Transaction Date from ' + toInput + ' until ' + fromInput;
					}
				}
			}
		});

		/////result display
		var t = $('#reportTable').DataTable({
			dom: 'Bfrtip',
			info: false,
			buttons: [
				{
					extend: 'excelHtml5',
					filename: 'WILMU_Fund Transaction List',
					title: title,
				},
				{
					text: '<i class="fa fa-file-pdf-o"></i> PDF',
					extend: 'pdfHtml5',
					filename: 'WILMU_Fund Transaction List',
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
							doc.content[1].table.body[i][0].alignment = 'center';
							doc.content[1].table.body[i][1].alignment = 'center';
							doc.content[1].table.body[i][2].alignment = 'center';
							doc.content[1].table.body[i][3].alignment = 'center';
							doc.content[1].table.body[i][4].alignment = 'center';
							doc.content[1].table.body[i][5].alignment = 'center';
							doc.content[1].table.body[i][6].alignment = 'center';
							doc.content[1].table.body[i][7].alignment = 'center';
							doc.content[1].table.body[i][8].alignment = 'center';
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
					filename: 'WILMU_Fund Transaction List',
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
				url: "getFundTransactionList",
				data: {
					fromCode: fromCode,
					toCode: toCode,
					transactType: transactType,
					fromInput: fromInput,
					toInput: toInput,
				},
				type: "GET",
				dataSrc: function(json) {
					var return_data = new Array();
					$.each(json, function(key, value) {
						return_data.push({
							'Trx Date': value.transactionDate,
							'Trx No': value.transactionNo,
							'Fund Code': value.fundCode,
							'Fund Desc': value.fundDesc,
							'Trx Code': value.transactionCode,
							'Trx Code Desc': value.transactionDesc,
							'Description': value.desc,
							'Amount': value.amount,
							'Reference': value.reference
						})
					});
					return return_data;
				},
			},
			columns: [
				{ "data": "Trx Date" },
				{ "data": "Trx No" },
				{ "data": "Fund Code" },
				{ "data": "Fund Desc" },
				{ "data": "Trx Code" },
				{ "data": "Trx Code Desc" },
				{ "data": "Description" },
				{ "data": "Amount" },
				{ "data": "Reference" }
			],
		});
	})
});