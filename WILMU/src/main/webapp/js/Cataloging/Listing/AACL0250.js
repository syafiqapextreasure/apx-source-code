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
	
	var allSelectedDatabaseType = false;
	$('#databaseType').multiselect({
		allSelectedText: 'All',
		maxHeight: 200,
		includeSelectAllOption: true,
		numberDisplayed: 1,
		onSelectAll: function() {
			allSelectedDatabaseType = true;
		},
		onChange: function() {
			allSelectedDatabaseType = false;
		}
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
			if (responseJson != null) {
				$.each(responseJson, function(key, value) {
					$(".lblName").text(value['Name']);
					$('#Reterive').prop('disabled', false);
				});
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
		var getDBselection = JSON.stringify($('#databaseType').val())

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


		/////control number range
		var chkRange = $('#chkBoxRangeControlNo:checked').val();
		var rangeFirstVal;
		var rangeLastVal;

		if (chkRange == "range") {
			rangeFirstVal = $('input[name=startInputCtrlno]').val();
			rangeLastVal = $('input[name=endInputCtrlno]').val();
		} else {
			rangeFirstVal = "";
			rangeLastVal = "";
		}


		//Vendor AND Patron Id
		var chkBoxSearchCateria = $('#chkBoxSearchCateria:checked').val();

		var patronID;
		if (chkBoxSearchCateria == "patrID") {
			patronID = $("#lblPatronID").val();
		} else {
			patronID = 0;
		}

		//////view mode
		var viewFormat = $("input[name='viewFormat']:checked").val();

		/*var numberofcolumn; 
		
		if(viewFormat == 'MARC'){
			var numberofcolumn = 
		}*/

		/////result display
		var t = $('#catreportTable').DataTable({
			dom: 'Bfrtip',
			paging: false,
			info: false,
			buttons: [
				/*{
						extend: 'excelHtml5',
					filename: 'WILMU_CatEditListing',
					title: 'Database = '+getDBselection +', Catalog date ' +sDate + eDate,
						    
				  },*/
				/*{
					text: '<i class="fa fa-file-pdf-o"></i> PDF',
					extend: 'pdfHtml5',
					filename: 'WILMU_CatEditListing',
					orientation: 'portrait', //portrait
					pageSize: 'A4', //A3 , A5 , A6 , legal , letter
					footer: true,
					exportOptions: {
						 columns: [1,6,7,8,9,10],
						//grouped_array_index: [ 1,2 ] 
						 grouped_array_index: [1]
					},
					customize: function (doc) {
						
						doc.styles.tableHeader.alignment = 'left';*/

				/*var rowCount = doc.content[1].table.body.length;
				   for (i = 0; i < rowCount; i++) {
						doc.content[1].table.body[i][1].alignment = 'left';
						doc.content[1].table.body[i][2].alignment = 'left';
						doc.content[1].table.body[i][3].alignment = 'left';
						doc.content[1].table.body[i][4].alignment = 'left';
						doc.content[1].table.body[i][5].alignment = 'left';
						doc.content[1].table.body[i][6].alignment = 'left';
						doc.content[1].table.body[i][7].alignment = 'right';
				   };*/

				///doc.content[1].table.widths = [ '3%', '15%', '10%', '15%', '23%', '20%', '10%'];


				//Remove the title created by datatTables  
				/*doc.content.splice(0, 1, {
					text: [
							   { text: 'Database = '+getDBselection +', Catalog date ' +sDate + eDate + '\n',bold:true,fontSize:13,alignment: 'center'}						                        ],
					margin: [0, 0, 0, 12],
				});*/

				/*doc['footer']=(function(page, pages) {
					return {
						columns: [
							{
								alignment: 'left',
								text: ['\t\t\t\t  ', moment(today).format("DD/MM/YYYY") +'\t' +moment(today).format("hh:mm:ss A")]
							},
							{
								alignment: 'right',
								text: ['page ', { text: page.toString() },	' of ',	{ text: pages.toString() }]
							}
						],
						margin: 20
					}
				});
		}, 

},*/
				{
					//text: '<i class="fa fa-file-pdf-o"></i> PDF', extend: "pdf", className: "fred"
					//extend: 'pdfHtml5', className: "RequestedItemStatus",
					//filename: 'WILMU_AcqRequestedItemStatus',
					text: "Print",
					className: 'Print',
					action: function(e, dt, node, config) {
						var pageTitle = 'Catalog Edit List',
							stylesheet = '//maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css',
							win = window.open('', 'Print', '');
						win.document.write('<html><head><title>' + pageTitle + '</title>' +
							'<link rel="stylesheet" href="' + stylesheet + '">' +
							'</head><body>' +
							'<br> <b>Database = ' + getDBselection + ', ' + sDate + eDate +
							'</b><br><br>' +
							'<table border="1"' + $('#catreportTable')[0].outerHTML + '</table></body></html>')
						win.document.close();
						win.print();
						win.close();
						return false;
					}
				},

				/*{
			extend: 'csv',
			filename: 'WILMU_CatEditListing',
			//title: 'Orders Report By ',
			charset: "utf-8",
			bom : "true",
			customize: function(doc){
						
						var split_csv = doc.split("\n");
						//alert(split_csv)
						$.each(split_csv.slice(1), function (index, csv_row) {
							var csv_cell_array = csv_row.split('","');
							csv_cell_array[1].replace("", (index+1));
						});
						
						return 'Database = '+getDBselection +', Catalog date ' +sDate + eDate +"\n\n" + doc;
					 }
			},*/
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
				url: "ResultCatEditListing",
				type: "GET",
				data: {
					getDBselection: getDBselection,
					dateSelection: dateSelection,
					startSentDate: startSentDate, endSentDate: endSentDate,
					allbranch: allbranch, brnch: JSON.stringify(brnch),
					rangeFirstVal: rangeFirstVal, rangeLastVal: rangeLastVal,
					patronID: patronID, viewFormat: viewFormat
				},
				/*start_time: new Date().getTime(),
					complete: function(data) {
						alert('This request took '+(new Date().getTime() - this.start_time)+' ms');
				},*/
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
							/*'No': (i+1),*/
							'Branch': "Branch : " + json[i].Column1,
							'Control No': count + ". Control No : " + json[i].Column2 + ",&emsp; Database : " + json[i].Column3
								+ ",&emsp; Catalog Date : " + json[i].Column4
								+ ",&emsp; Index Date : " + json[i].Column5
								+ ",&emsp; Catalog Officer : " + json[i].Column6
								+ ",&emsp; Index Officer : " + json[i].Column7,
							'Database': json[i].Column3,
							'Catalog Date': json[i].Column4,
							'Index Date': json[i].Column5,
							'Catalog Officer': json[i].Column6,
							'Index Officer': json[i].Column7,
							'Tag': json[i].Column8,
							'Description': json[i].Column9,
							'Indicator 1': json[i].Column10,
							'Indicator 2': json[i].Column11,
							'Data': json[i].Column12,
						})
					}
					return return_data;
				},
			},//This is the end of the AJAX object from the example above
			columns: [
				/*{'data': 'No'},*/
				{ 'data': 'Branch', visible: false },
				{ 'data': 'Control No', visible: false },
				{ 'data': 'Database', visible: false },
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
			/*responsive: {
				details: {
					renderer: function(api, rowIdx, columns) {
						var data = $.map(columns, function(col, i) {
							alert(col.hidden+"dfs");
							alert(col.data+"dfs2");
							alert(col.rowIndex+"dfs3");

							if (col.hidden) {
								if (col.data) {
									return '<tr data-dt-row="' + col.rowIndex + '" data-dt-column="' + col.columnIndex + '">' +
										'<td><b>' + col.title + ':' + '</b></td> ' +
										'<td>' + col.data + '</td>' +
										'</tr>'
								}
							}
						}).join('');
						return data ? $('<table/>').append(data) : false;
					}
				}
			},*/

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
								/*+'<tr class="group"><td><b> Control No : ' + rowData['Control No'] +'</b></td>'
								   +'<td><b> Database : ' + rowData['Database'] +'</b></td>'
								   +'<td><b> Catalog Date : ' + rowData['Catalog Date'] +'</b></td>'
								   +'<td><b> Index Date : ' + rowData['Index Date'] +'</b></td>'
								   +'<td><b> Officer : ' + rowData['Officer'] +'</b></td>'
								   +'<td></td>'*/
								/*+'<tr class="group"><td><b> Branch : ' + rowData['Branch'] +'</b></td>'
								+'<tr class="group"><td><b> Control No : ' + rowData['Control No'] +'</b></td>'*/
							);
							last = group;
						}
					});
				}

				/*api.column(0, {page:'current'} ).data().each( function ( group, i ) {
						   if ( last !== group ) {
						   var rowData = api.row(i).data();
							   $(rows).eq( i ).before(
								   '<tr class="group"><td colspan="6"><b> Branch: ' + group +'</b></td></tr>'
								   +'<tr class="group"><td><b> Control No : ' + rowData['Control No'] +'</b></td>'
								   +'<td><b> Database : ' + rowData['Database'] +'</b></td>'
								   +'<td><b> Catalog Date : ' + rowData['Catalog Date'] +'</b></td>'
								   +'<td><b> Index Date : ' + rowData['Index Date'] +'</b></td>'
								   +'<td><b> Officer : ' + rowData['Officer'] +'</b></td>'
								   +'<td></td>'
								   +'</tr>'
								   //+'<tr class="group"><td colspan="11"><b> Branch : ' + rowData['Branch'] +'</b></td></tr>'
							   );
							   last = group;
						   }
					   });*/
			},
		});
		/*t.on('order.dt search.dt', function () {
					 t.column(0, {search:'applied', order:'applied'}).nodes().each( function (cell, i) {
						   cell.innerHTML = i+1;
						   t.cell(cell).invalidate('dom');
					 });
		}).draw();
		*/
		if (viewFormat == 'MARC') {
			//t.column( [6] ).visible( true );
			t.column([8]).visible(false);
		} else if (viewFormat == 'Linear') {
			//t.column( [6] ).visible( false );
			t.column([8]).visible(true);
		}
	});
});