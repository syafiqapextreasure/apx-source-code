$(document).ready(function() {
	$("#fileNameInput, #branchSelect").attr('disabled', 'disabled');
	
	$("#exportfile").hide();

	////// table setup
	$('#reportTable').DataTable({
		destroy: true,
		searching: false,
		bLengthChange: false,
		autoWidth: false,
		responsive: true,
	});

	///////////////////// input-endDate set Current Date  ////////////////////
	$('#input-valueDate').val(moment(new Date()).format("DD/MM/YYYY"));
	$('#input-valueDate').datepicker({
		format: "dd/mm/yyyy",
		todayBtn: true,
		autoclose: true,
		todayHighlight: true,
	});

	// Handling both 'input' and 'change' events
	$("#input-valueDate, #fileNameInput, #branchSelect").on("input change", function() {
		checkConditionPartForInputFormatting()
	});


	// Handling 'change' events
	$("#chckBranch, #chckFilename").on("change", function() {
		// Detecting if the checkbox is checked
		if ($('#chckBranch').is(':checked')) { // branch checkbox is checked
			$('#branchSelect').removeAttr("disabled");
		}
		if (!$('#chckBranch').is(':checked')) { // branch checkbox is not checked
			$('#branchSelect').attr('disabled', 'disabled');
		}
		if ($('#chckFilename').is(':checked')) { // file name checkbox is checked
			$('#fileNameInput').prop('disabled', false);
		}
		if (!$('#chckFilename').is(':checked')) { // file name checkbox is not checked
			$('#fileNameInput').attr('disabled', 'disabled');
			$('#fileNameInput').val('');
		}
		checkConditionPartForInputFormatting()
	});


	function checkConditionPartForInputFormatting() {
		$('#Reterive').attr('disabled', 'disabled');

		// checkbox branch is not checked and checkbox file name is not checked and date has input
		if ($("#chckBranch").prop("checked") == false && $("#chckFilename").prop("checked") == false && $("#input-valueDate").val().length > 0) {
			$('#Reterive').prop('disabled', false);
		}

		// checkbox branch is checked and checkbox file name is not checked and date has input and branch selection has input
		if ($("#chckBranch").prop("checked") == true && $("#chckFilename").prop("checked") == false && $("#input-valueDate").val().length > 0 && $("#branchSelect").val().length > 0) {
			$('#Reterive').prop('disabled', false);
		}

		// checkbox branch is not checked and checkbox file name is checked and date has input and file name selection has input
		if ($("#chckBranch").prop("checked") == false && $("#chckFilename").prop("checked") == true && $("#input-valueDate").val().length > 0 && $("#fileNameInput").val().length > 0) {
			$('#Reterive').prop('disabled', false);
		}

		// checkbox branch is checked and checkbox file name is checked and date has input and file name selection has input
		if ($("#chckBranch").prop("checked") == true && $("#chckFilename").prop("checked") == true && $("#input-valueDate").val().length > 0 && $("#fileNameInput").val().length > 0 && $("#branchSelect").val().length > 0) {
			$('#Reterive').prop('disabled', false);
		}
	}
	
	
	// Export as CSV programmatically
        $('#exportfile').click(function() {
            //table.button('.buttons-csv').trigger();
            exportTableAsText();
        });
        
        
        
        function exportTableAsText() {
		    var uniqueFileNames = {};
		
		    $('#reportTable tbody tr').each(function() {
		        var fileName = $(this).find('td:first-child').text().trim(); // Get the value of the first cell in the current row (column 0)
		        if (!(fileName in uniqueFileNames)) {
		            uniqueFileNames[fileName] = [];
		        }
		
		        var $cells = $(this).find('td');
		        // Start from index 1 to skip the first column
		        for (var i = 2; i < $cells.length; i++) {
		            uniqueFileNames[fileName].push($cells.eq(i).text().trim()); // Store cell data
		        }
		    });
		
		    // Iterate through unique file names and create separate text files
		    for (var fileName in uniqueFileNames) {
		        var tableData = uniqueFileNames[fileName].join('\n') + '\n';
		
		        // Create a Blob containing the table data
		        var blob = new Blob([tableData], { type: 'text/plain' });
		
		        // Create a link element to download the Blob as a file
		        var link = document.createElement('a');
		        link.href = window.URL.createObjectURL(blob);
		        link.download = fileName + '.txt'; // Append .txt extension to the filename
		
		        // Append the link to the document and trigger a click to start the download
		        document.body.appendChild(link);
		        link.click();
		
		        // Cleanup
		        document.body.removeChild(link);
		    }
		}

        
        
      
        /*function exportTableAsText() {
	
			var fileName = $('#reportTable tbody tr:first-child td:first-child').text(); // Get the value of the first cell in the first row 
			
            var tableData = '';
            
            $('#reportTable tbody tr').each(function() {
                var $cells = $(this).find('td');
                // Start from index 1 to skip the first column
                for (var i = 1; i < $cells.length; i++) {
                    tableData += $cells.eq(i).text() + '\n'; // Separate columns by tab
                }
                tableData += ''; // Newline for each row
            });

            // Create a Blob containing the table data
            var blob = new Blob([tableData], { type: 'text/plain' });

            // Create a link element to download the Blob as a file
            var link = document.createElement('a');
            link.href = window.URL.createObjectURL(blob);
            link.download = fileName;

            // Append the link to the document and trigger a click to start the download
            document.body.appendChild(link);
            link.click();

            // Cleanup
            document.body.removeChild(link);
        }*/
        
    /*function exportTableAsText() {
            var tableData = '';
            $('#reportTable tbody tr').each(function() {
                $(this).find('td').each(function() {
                    tableData += $(this).text() + '\n'; // Separate columns by tab
                });
                tableData += '\n'; // Newline for each row
            });

            // Create a Blob containing the table data
            var blob = new Blob([tableData], { type: 'text/plain' });

            // Create a link element to download the Blob as a file
            var link = document.createElement('a');
            link.href = window.URL.createObjectURL(blob);
            link.download = 'table_data.txt';

            // Append the link to the document and trigger a click to start the download
            document.body.appendChild(link);
            link.click();

            // Cleanup
            document.body.removeChild(link);
        }*/


	//////////////////////////////////////////////////////////////////////////
	////////////////////////btn Reterive Click ///////////////////////////////
	//////////////////////////////////////////////////////////////////////////
	$('#Reterive').click(function() {
		 $("#exportfile").show();
		  
		var sentDate = moment($("#input-valueDate").val(), 'DD/MM/YYYY').format("YYYYMMDD");
		var branchFlag = 'N';
		var branchVal = '';
		var filenameFlag = 'N';
		var filenameVal = '';

		if ($('#chckBranch').is(':checked')) { // branch checkbox is checked
			branchFlag = 'Y';
			branchVal = $('#branchSelect').val()
		}
		if ($('#chckFilename').is(':checked')) { // file name checkbox is checked
			filenameFlag = 'Y';
			filenameVal = $("#fileNameInput").val()
		}
		
		var title = 'Fines Outstanding Listing';


		/////result display
		var t = $('#reportTable').DataTable({
			dom: 'Bfrtip',
			info: false,
			buttons: [
				/*{
					extend: 'excelHtml5',
					filename: 'WILMU_' + title,
					title: title + ' ' + sentDate,
				},
				{
					text: '<i class="fa fa-file-pdf-o"></i> PDF',
					extend: 'pdfHtml5',
					filename: 'WILMU_' + title,
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
							doc.content[1].table.body[i][6].alignment = 'right';
						};
						doc.content[1].table.widths = ['5%', '10%', '40%', '10%', '10%', '10%', '15%'];

						//Remove the title created by datatTables  
						doc.content.splice(0, 1, {
							text: [
								{ text: 'Fines Outstanding Listing ' + sentDate + '\n', bold: true, fontSize: 13, alignment: 'center' }],
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
					filename: 'WILMU_' + title,
					charset: "utf-8",
					bom: "true",
					customize: function(doc) {
						var split_csv = doc.split("\n");
						$.each(split_csv.slice(1), function(index, csv_row) {
							var csv_cell_array = csv_row.split('","');
							csv_cell_array[1].replace("", (index + 1));
						});
						return title + " " + sentDate + "\n\n" + doc;
					}
				},*/
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
				url: "getBapiListingPpj",
				data: {
					sentDate: sentDate,
					branchFlag: branchFlag,
					branchVal: branchVal,
					filenameFlag: filenameFlag,
					filenameVal: filenameVal
				},
				type: "GET",
				dataSrc: function(json) {
					var return_data = new Array();
					$.each(json, function(key, value) {
						return_data.push({
							'File name': value.fileName,
							'Time': value.time,
							'Line 1': value.line1,
							'Line 2': value.line2,
							'Line 3': value.line3,
						})
					});
					return return_data;
				},
			},
			columns: [
				{ "data": "File name" },
				{ "data": "Time" },
				{ "data": "Line 1" },
				{ "data": "Line 2" },
				{ "data": "Line 3" }
			],
		});
	})
});