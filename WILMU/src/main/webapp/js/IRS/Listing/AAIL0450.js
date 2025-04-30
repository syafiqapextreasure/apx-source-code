$(document).ready(function() {
	////// table setup
	$('#reportTable').DataTable({
		destroy: true,
		searching: false,
		bLengthChange: false,
		autoWidth: false,
		responsive: true,
	});

	$('#allPatron').prop('checked', true);
	$('#input-patron1, #input-patron2').attr('disabled', 'disabled');

	////////////// radio button on click  ////////////////////
	$('input[type=radio][name=selectOption]').click(function() {
		var chkBoxSearchCateria = $("input[name='selectOption']:checked").val();
		switch (chkBoxSearchCateria) {
			case 'all':
				$('#input-patron1, #input-patron2').val('').attr('disabled', 'disabled'); // DISABLE DATE AND CLEAR THE DATE INPUT
				break;
			case 'range':
				$('#input-patron1, #input-patron2').removeAttr('disabled'); // ENABLE DATE
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
		var patron1 = $('#input-patron1').val().trim();
		var patron2 = $('#input-patron2').val().trim();
		var title = '';

		if (selectOption == 'all') {
			title = 'All patrons'
		}
		if (selectOption == 'range') {
			if (patron1 != '' && patron2 != '') {
				$.get('getCountUserPatronListing', {
					patron1: patron1,
					patron2: patron2
				}, function(responseJson) {
					if (responseJson != null) {
						title = 'Patron ID from ' + patron2 + ' to ' + patron1
						if (responseJson.count == 1) {
							title = 'Patron ID from ' + patron1 + ' to ' + patron2
						}
					}
				});
			}
			if (patron1 == '' && patron2 != '') {
				title = 'Patron ID until ' + patron2
			}
			if (patron1 != '' && patron2 == '') {
				title = 'Patron ID from ' + patron1
			}
		}


		// result display
		$('#reportTable').DataTable({
			dom: 'Bfrtip',
			info: false,
			buttons: [
				{
					extend: 'excelHtml5',
					filename: 'WILMU_Patron Profile Listing',
					title: title,
				},
				{
					text: '<i class="fa fa-file-pdf-o"></i> PDF',
					extend: 'pdfHtml5',
					filename: 'WILMU_Patron Profile Listing',
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
						var rowCount = doc.content[1].table.body.length;
						for (i = 0; i < rowCount; i++) {
							doc.content[1].table.body[i][0].alignment = 'left';
							doc.content[1].table.body[i][1].alignment = 'left';
							doc.content[1].table.body[i][2].alignment = 'left';
							doc.content[1].table.body[i][3].alignment = 'left';
							doc.content[1].table.body[i][4].alignment = 'left';
							doc.content[1].table.body[i][5].alignment = 'center';
						};
						doc.content[1].table.widths = ['5%', '15%', '20%', '20%', '20%', '20%'];
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
					filename: 'WILMU_Patron Profile Listing',
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
				url: "getPatronProfileListing",
				data: {
					selectOption: selectOption,
					patron1: patron1,
					patron2: patron2,
				},
				type: "GET",
				dataSrc: function(json) {
					var return_data = new Array();
					var i = 1
					$.each(json, function(key, value) {
						return_data.push({
							'No': i,
							'Patron ID': value.id,
							'Name': value.name,
							'Specialization': value.specializaton,
							'Descriptor': value.descriptor,
							'Hits': value.hits + ' hits'
						})
						i++
					});
					return return_data;
				},
			},
			columns: [
				{ "data": "No" },
				{ "data": "Patron ID" },
				{ "data": "Name" },
				{ "data": "Specialization" },
				{ "data": "Descriptor" },
				{ "data": "Hits" },
			],
		});
	})
});