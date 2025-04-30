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


	////////Location
	$("#chkBoxLoca").on('change', function() {
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

	////////Catalog Type
	$("#chkBoxCattype").on('change', function() {
		var self = $(this);
		if (self.is(":checked")) {
			$('#cattype').multiselect("enable");
			$('#Reterive').prop('disabled', true);
			$('#cattype').change(function() {
				if ($("#cattype").val() != "") {
					$('#Reterive').prop('disabled', false);
				} else {
					$('#Reterive').prop('disabled', true);
				}
			});
		} else {
			$('#cattype').multiselect("disable");
			$('#Reterive').prop('disabled', false);
			$("#cattype").multiselect("clearSelection")
			$("#cattype").multiselect('refresh');
		}
	});


	//////////////////////////////////////////////////////////////////////////
	////////////////////////btn Reterive Click ///////////////////////////////
	//////////////////////////////////////////////////////////////////////////
	$('#Reterive').click(function() {

		$('#catreportTable').dataTable().fnClearTable();


		///date selection
		var dateSelection = $('input[name="dateSelection"]:checked').val();
		var dateSelectionLabel = $("input[name='dateSelection']:checked").data('name')

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
		var brnch = $("#branch").val();
		var allbranch;

		if (brnch != "") {
			if (allSelected) {
				allbranch = "Y";
			} else {
				allbranch = "N";
				//alert("No.");
			}
		} else {
			brnch = 0;
			allbranch = "N";
		}

		////location
		var loca = $("#loca").val();
		if (loca != "") {
		} else {
			loca = 0;
		}

		////cattype
		var cattype = $("#cattype").val();
		if (cattype != "") {
		} else {
			cattype = 0;
		}


		///display all class no
		var chkBoxdisplay = $('#chkBoxdisplay:checked').val();
		if (chkBoxdisplay == undefined) {
			chkBoxdisplay = 0;
		}

		///include unindex
		var chkBoxincunindex = $('#chkBoxincunindex:checked').val();
		if (chkBoxincunindex == undefined) {
			chkBoxincunindex = 0;
		}

		/////result display
		var t = $('#catreportTable').DataTable({
			dom: 'Bfrtip',
			buttons: [
				{
					extend: 'excelHtml5',
					filename: 'WILMU_CatHoldingsReport',
					title: 'Statistics Preview ' + sDate + eDate,

				},
				{
					text: '<i class="fa fa-file-pdf-o"></i> PDF',
					extend: 'pdfHtml5',
					filename: 'WILMU_CatHoldingsReport',
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

						///doc.styles.tableHeader.alignment = 'left';

						var rowCount = doc.content[1].table.body.length;
						for (i = 0; i < rowCount; i++) {
							doc.content[1].table.body[i][0].alignment = 'left';
							doc.content[1].table.body[i][1].alignment = 'left';
							doc.content[1].table.body[i][2].alignment = 'left';
							doc.content[1].table.body[i][3].alignment = 'right';
							doc.content[1].table.body[i][4].alignment = 'right';
						};

						///doc.content[1].table.widths = [ '4%', '10%', '14%', '12%', '18%', '12%', '10%', '10%', '10%']; 

						/*doc.content.push( {
											//margin: [ 0, 0, 0, 12 ],
											alignment: 'right',
											bold:true,
											   text: [
														  $('.divtotal').text(), 
													]
	
										 } );*/

						//Remove the title created by datatTables  
						doc.content.splice(0, 1, {
							text: [
								{ text: 'Statistics Preview ' + sDate + eDate + '\n', bold: true, fontSize: 13, alignment: 'center' }],
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
					filename: 'WILMU_CatHoldingsReport',
					charset: "utf-8",
					bom: "true",
					customize: function(doc) {

						var split_csv = doc.split("\n");
						//alert(split_csv)
						$.each(split_csv.slice(1), function(index, csv_row) {
							var csv_cell_array = csv_row.split('","');
							csv_cell_array[1].replace("", (index + 1));
						});

						return "Statistics Preview " + sDate + eDate + "\n\n" + doc;
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
				url: "ResultCatHoldingsReport",
				data: {
					dateSelection: dateSelection,
					startSentDate: startSentDate, endSentDate: endSentDate,
					allbranch: allbranch, brnch: JSON.stringify(brnch),
					loca: JSON.stringify(loca),
					cattype: JSON.stringify(cattype),
					chkBoxdisplay: chkBoxdisplay,
					chkBoxincunindex: chkBoxincunindex,
				},
				type: "GET",
				dataSrc: function(json) {
					var return_data = new Array();

					for (var i = 0; i < json.length; i++) {

						return_data.push({
							'No': (i + 1),
							'Class No Range': json[i].Column1,
							'Description': json[i].Column2,
							'Title': json[i].Column3,
							'Copies': json[i].Column4,
						})
					}
					return return_data;
				},
			},//This is the end of the AJAX object from the example above
			columns: [
				{ 'data': 'No' },
				{ 'data': 'Class No Range' },
				{ 'data': 'Description' },
				{ 'data': 'Title', className: "text-right" },
				{ 'data': 'Copies', className: "text-right" },
			],
		});
	});
});