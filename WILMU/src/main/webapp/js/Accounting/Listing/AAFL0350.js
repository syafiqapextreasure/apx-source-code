$(document).ready(function() {
	////// table setup
	$('#reportTable').DataTable({
		destroy: true,
		searching: false,
		bLengthChange: false,
		autoWidth: false,
		responsive: true,
	});
	
	$("#Reterive").attr('disabled', 'disabled');
	
	function checkConditionInputFormatting(){
		if($("#input-fromCode").val().length > 0 || $("#input-toCode").val().length > 0){
			$('#Reterive').removeAttr('disabled');
		}
		else{
			$("#Reterive").attr('disabled', 'disabled');
		}
	}
	
	$("#input-fromCode, #input-toCode").on('change input', function() {
		checkConditionInputFormatting()
	})	
	
	
	//////////////////////////////////////////////////////////////////////////
	////////////////////////btn Reterive Click ///////////////////////////////
	//////////////////////////////////////////////////////////////////////////
	$('#Reterive').click(function(){
		var inputFromCode = $("#input-fromCode").val();
		var inputToCode = $("#input-toCode").val();
		
		var title = '';
		
		if(inputFromCode == '' && inputToCode != ''){
			title = 'WILMU Fund Chart Listing until ' + inputToCode;
		}
		if(inputFromCode != '' && inputToCode == ''){
			title = 'WILMU Fund Chart Listing from ' + inputFromCode;
		}
		if(inputFromCode != '' && inputToCode != ''){
			$.ajax({
			    type : "get",
			    url : "getCountStatusFundChartList",
			    data : {inputFromCode : inputFromCode,
						inputToCode : inputToCode
					    },
			    success : function(data) {
					if(data == 0){
						title = 'WILMU Fund Chart Listing from ' + inputToCode + ' until ' + inputFromCode;
					}
					if(data != 0){
						title = 'WILMU Fund Chart Listing from ' + inputFromCode + ' until ' + inputToCode;
					}
			    }
			});	
		}
		
		/////result display
		var t = $('#reportTable').DataTable({
			dom: 'Bfrtip',
			info:     false,
			buttons: [
						{
					  		extend: 'excelHtml5',
					        filename: 'WILMU_Fund Chart List',
					        title: title,
					 	},
						{
					    	text: '<i class="fa fa-file-pdf-o"></i> PDF',
							extend: 'pdfHtml5',
							filename: 'WILMU_Fund Chart List',
							charset: "utf-8",
							bom : "true",
							orientation: 'landscape', //portrait
							pageSize: 'A4', //A3 , A5 , A6 , legal , letter
							footer: true,
							exportOptions: {
								search: 'applied',
								order: 'applied'
							},
							customize: function (doc) {
								var rowCount = doc.content[1].table.body.length;
								for (i = 0; i < rowCount; i++) {
										doc.content[1].table.body[i][0].alignment = 'left';
										doc.content[1].table.body[i][1].alignment = 'left';
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
						                                   { text: title + '\n',bold:true,fontSize:13,alignment: 'center'}						                        ],
						                        margin: [0, 0, 0, 20],
						                    });
											doc.pageMargins = [ 20, 20, 20, 20 ]; //left, top, right, bottom
					
											doc['footer']=(function(page, pages) {
												return {
													columns: [
														{
															alignment: 'left',
															text: ['\t\t\t\t  ', moment(new Date()).format("DD/MM/YYYY") +'\t' +moment(new Date()).format("hh:mm:ss A")]
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
					    },
					
               			{
		                	extend: 'csv',
		                	filename: 'WILMU_Fund Chart List',
							charset: "utf-8",
							bom : "true",
							customize: function(doc){
										var split_csv = doc.split("\n");
										$.each(split_csv.slice(1), function (index, csv_row) {
											var csv_cell_array = csv_row.split('","');
											csv_cell_array[1].replace("", (index+1));
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
             loadingRecords : "Please wait - loading...",
             processing :     "Please wait formatting in progress..."
        	},
			deferRender: true,
			footer: true,
			ajax: {
		    	url: "getFundChartList",
		        data : {
						inputFromCode : inputFromCode,
						inputToCode : inputToCode
					   },
		        type: "GET",
		        dataSrc: function (json) {
		            var return_data = new Array();
					$.each( json, function( key, value ) {
						return_data.push({
			                'Fund Code' : value.fundCode,
							'Description' : value.description,
							'Allocation' : value.allocation,
							'Increment' : value.increment,
							'Decrement' : value.decrement,
			                'Encumbrance' : value.encumbrance,
			                'Commitment' : value.commitment,
			                'Liability' : value.liability,
							'Payment' : value.payment
			            })
					});
		            return return_data;
		          },
		     },
			columns: [
				{ "data": "Fund Code" },
				{ "data": "Description" },
				{ "data": "Allocation" },
				{ "data": "Increment" },
				{ "data": "Decrement" },
				{ "data": "Encumbrance" },
			    { "data": "Commitment" },
				{ "data": "Liability" },
			    { "data": "Payment" }
			  ],
			footerCallback: function ( row, data, start, end, display ) {
		            var api = this.api()
					
		            // total Allocation header amount over all pages
		            var totalAllocation = api.column(2).data().reduce(function (a, b){
		                    return Number(a) + Number(b);
		                }, 0 );

		            // total Increment header amount over all pages
		            var totalIncrement = api.column(3).data().reduce(function (a, b){
		                    return Number(a) + Number(b);
		                }, 0 );

		            // total Decrement header amount over all pages
		            var totalDecrement = api.column(4).data().reduce(function (a, b){
		                    return Number(a) + Number(b);
		                }, 0 );

		            // total Encumbrance amount header over all pages
		            var totalEncumbrance = api.column(5).data().reduce(function (a, b){
		                    return Number(a) + Number(b);
		                }, 0 );

		            // total Commitment amount header over all pages
		            var totalCommitment = api.column(6).data().reduce(function (a, b){
		                    return Number(a) + Number(b);
		                }, 0 );

		            // total Liability amount header over all pages
		            var totalLiability = api.column(7).data().reduce(function (a, b){
		                    return Number(a) + Number(b);
		                }, 0 );

		            // total Payment amount header over all pages
		            var totalPayment = api.column(8).data().reduce(function (a, b){
		                    return Number(a) + Number(b);
		                }, 0 );
					
					$("#totalAllocation, #totalIncrement, #totalDecrement, #totalEncumbrance").empty();
					$("#totalCommitment, #totalLiability, #totalPayment").empty();
					
					$("#totalAllocation").append(totalAllocation.toFixed(2));
					$("#totalIncrement").append(totalIncrement.toFixed(2));
					$("#totalDecrement").append(totalDecrement.toFixed(2));
					$("#totalEncumbrance").append(totalEncumbrance.toFixed(2));
					$("#totalCommitment").append(totalCommitment.toFixed(2));
					$("#totalLiability").append(totalLiability.toFixed(2));
					$("#totalPayment").append(totalPayment.toFixed(2));
		        }
    		});
	})
});