$(document).ready(function() {
	$('#input-accessionNo, #input-contorlNo').attr('maxLength',"10");
	$('#input-accessionNo').prop('disabled', true);
	
	$('input:radio[name=radioOption]').filter('[value="controlNo"]').attr('checked', true);
	
	$('#input-title').css({"padding":"5px", "width":"40vw","height":"15vh"});
	$('#input-title').prop('disabled', true);
	
	$('input[type=radio]').change(function() {
		  var inputRadioType =  $(this).attr('id')

	      if(inputRadioType == 'controlNo') {
			$('#input-accessionNo').prop('disabled', true);
			$('#input-contorlNo').prop('disabled', false);
			$('#input-accessionNo').val('')	
			$('input:radio[name=radioOption]').filter('[value="controlNo"]').attr('checked', true);
	      }else if(inputRadioType == 'accessionNo'){
			$('#input-contorlNo').prop('disabled', true);
			$('#input-accessionNo').prop('disabled', false);
			$('#input-contorlNo').val('')	
			$('input:radio[name=radioOption]').filter('[value="accessionNo"]').attr('checked', true);
	      }
	 });

	 $("#input-contorlNo").on("input", function(){
        $('#input-accessionNo').val('')	
		$('#input-accessionNo').prop('disabled', true);
    });
	 $("#input-accessionNo").on("input", function(){
        $('#input-contorlNo').val('')
		$('#input-contorlNo').prop('disabled', true);	
    });

	// table setup
	var t = $('#cirreportTable').DataTable( {
		destroy: true,
		searching: false,
		bLengthChange: false,
		autoWidth: false,
		responsive: true,
	});
	
	
	// when reterive
	$('#Reterive').click(function(){
		var inputRadioTypeChecked = $('input[name=radioOption]:checked','#fndReportnListing').val()
		var inputNo = new Array()
		$('input[type="text"].test').each(function () {
			inputNo.push({
				inputType : inputRadioTypeChecked,
				inputNo : $(this).val()
			})
		});
		
		var objectData = inputNo.filter(function(obj) {return obj.inputNo.length !== 0})
		var matNoAfterClick = ''
		
		var t = $('#cirreportTable').DataTable({
			dom: 'Bfrtip',
            buttons: [
						{
					  		extend: 'excelHtml5',
					        filename: 'WILMU_LoanHistoryEnquiry',
					        title: 'Loan History Enquiry',          
					 	},
						{
					    	text: '<i class="fa fa-file-pdf-o"></i> PDF',
							extend: 'pdfHtml5',
							filename: 'WILMU_LoanHistoryEnquiry',
							charset: "utf8",
							bom : "true",
							orientation: 'landscape', // portrait
							pageSize: 'A4', // A3 , A5 , A6 , legal , letter
							footer: true,
							exportOptions: {
								stripHtml: true,
							},
							customize: function (doc) {
											doc.styles.tableHeader.alignment = 'left';
											// Remove the title created by datatTables  
											doc.content.splice(0, 1);
											
											doc['header']=(function(page, pages) {
												return {
													columns: [
														{
															text: 'Loan History Enquiry \n',
															bold:true,
															fontSize:13,
															alignment: 'center'
														}
													],
													margin: 20
												}
											});
											doc['footer']=(function(page, pages) {
												return {
													columns: [
														{
															alignment: 'left',
															text: ['\t\t\t\t  ', moment().format("DD/MM/YYYY") +'\t' +moment().format("hh:mm:ss A")]
														},
														{
															alignment: 'right',
															text: ['page ', { text: page.toString() },	' of ',	{ text: pages.toString() }]
														}
													],
													margin: 20
												}
											});
									}
					    },
               			{
		                	extend: 'csv',
		                	filename: 'WILMU_LoanHistoryEnquiry',
							charset: "utf-8",
							bom : "true",
							customize: function(doc){
										var split_csv = doc.split("\n");
										$.each(split_csv.slice(1), function (index, csv_row) {
											var csv_cell_array = csv_row.split('","');
											csv_cell_array[1].replace("", (index+1));
										});
										return "Loan History Enquiry \n\n" + doc;
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
             loadingRecords : "Please wait - loading...",
             processing :     "Please wait formatting in progress..."
        	},
			deferRender: true,
			  order: [[0, 'asc']],      
			footer: true,
		    ajax: {
		    	url: "resultCirLoanHistory",
		        data : {inputType :  objectData[0].inputType,
						inputNo : objectData[0].inputNo
						},
		        type: "GET",
				async: false,
		        dataSrc: function (json) {
					if ( json.length < 1) {
						$("#cirreportTable_processing").css({"display": "none"});
					}
			
					matNoAfterClick = json[0].MatNo
		            var return_data = new Array();
		            for(var i=0;i< json.length; i++){
		              return_data.push({
						'Accession No': json[i].DocNo,
		                'Patron ID' : json[i].PatronId,
		                'Borrow Date' : json[i].BorrowDate,
		                'Due Date' : json[i].DueDate,
		                'Return Date' : json[i].ReturnDate,
						'Charge Officer' : json[i].ChargeOfficer,
						'Discharge Officer' : json[i].DischargeOfficer,
		              })
		            }
		            return return_data;
		          },
		     },// This is the end of the AJAX object from the example above
		     	columns    : [		
					{'data': 'Accession No'},
					{'data': 'Patron ID'},
					{'data': 'Borrow Date'},
					{'data': 'Due Date'},
					{'data': 'Return Date'},
					{'data': 'Charge Officer'},
					{'data': 'Discharge Officer'},	
				],
    	});

		if ( objectData[0].inputType == "controlNo") {
			t.column( [0] ).visible( true );
			$.get('getTitle', {	
				inputNo : objectData[0].inputNo
	       	}, function(responseJson) {
	           if (responseJson != null) {
					$("#input-title").val(responseJson).prop('disabled', 'disabled');
	        	}
	      	});
		} else if ( objectData[0].inputType == 'accessionNo') {
			t.column([0]).visible( false );
			$.get('GetTagDetail', {	
	             accno: objectData[0].inputNo,
	             tag: "245",
	       	}, function(responseJson) {
	           if (responseJson != null) {
	           	 	$("#input-title").val(responseJson.slice(4)).prop('disabled', 'disabled');
				 	$("#input-contorlNo").val(matNoAfterClick).prop('disabled', 'disabled');
	        	}
	      	});
		}
	});
});