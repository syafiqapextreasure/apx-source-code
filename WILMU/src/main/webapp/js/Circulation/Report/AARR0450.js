$(document).ready(function() {	
	$('.div1patrid').removeClass("col-sm-3 col-md-3").addClass("col-sm-2 col-md-2");
	$(".patronid").removeAttr('href');  
	$(".removeHeaderForTable").empty() 
	
	
	// to display Modal_PatrSearchContent
	$('.patronid').on('click',function(){
		var url = "Modal_PatrSearch";
		$.get(url,function(data){
			$("#Modal_PatrSearchContent").html(data);
		});
	});
	
	function clearTable(){
		$("#patronId, #patronName, #groupId, #category, #status, #lostItem, #collectionFine, #noOfSuspension, #membershipDate, #expiryDate, #borrowToDate, #borrowToYear, #lastBorrow, #lastReturn, #branch").empty();
	}
	
	// to retrieve data from the search process
	$("#lblPatronID").on("keypress keyup blur",function (e) {
		var id = $("#lblPatronID").val();
		clearTable()
		$(".lblName").empty();
		
		// display vendor name
		$.get('GetPatronName', {
        	id : id
		 	}, function(responseJson) {
			if(responseJson != null){
				$.each(responseJson, function(key,value) {
					$(".lblName").text(value['Name']);
					$('#Reterive').prop('disabled', false);
				});
			}
		});
	});
	

	// datatable setup
	$('#reportTable').DataTable( {
		destroy: true,
		searching: false,
		bLengthChange: false,
		autoWidth: false,
		responsive: true,
		paging: false,
		footer: false,
		bSort : false
	});
	
	$('#Reterive').click(function(){
		$.get('getPatronStatusReport', {id : $('#lblPatronID').val()}, 
		function(responseJson) {
			if(responseJson != null){
				$("#patronId").text(responseJson.GL14PATR)
				$("#patronName").text(responseJson.GL14NAME)
				$("#groupId").text(responseJson.GL14GRID)
				$("#category").text(responseJson.GL14CATE)
				$("#status").text(responseJson.GL14STAT)
				$("#lostItem").text(responseJson.GL14LOSTBOK)
				$("#collectionFine").text(responseJson.COLLECTION)
				$("#noOfSuspension").text(responseJson.GL14SUSPEND)
				$("#membershipDate").text(responseJson.GL14MEMDATE)
				$("#expiryDate").text(responseJson.GL14EXPDATE)
				$("#borrowToDate").text(responseJson.BTD)
				$("#borrowToYear").text(responseJson.BTY)
				$("#lastBorrow").text(responseJson.LAST_BORROW)
				$("#lastReturn").text(responseJson.LAST_RETURN)
				$("#branch").text(responseJson.GL14BRNC)
				$("#outstandingFines").text(responseJson.OUTSTANDING_FINE)
				
				var t = $('#reportTable').DataTable({
							dom: 'Bfrtip',
				            buttons: [
				                /*'excel', 'pdf', 'print'*/
										{
									  		extend: 'excelHtml5',
									        filename: 'WILMU_PatronStatusReport',
									        title: 'Patron Status Report',
											exportOptions: {
												search: 'applied',
												order: 'applied',
												rows: ':visible'
											},    
									 	},
										{
											text: '<i class="fa fa-file-pdf-o"></i> PDF',
											extend: 'pdfHtml5',
											filename: 'WILMU_PatronStatusReport',
											charset: "utf-8",
											bom : "true",
											orientation: 'portrait', //portrait
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
										           };
											doc.content[1].table.body[0][0].text = ''
											doc.content[1].table.body[0][1].text = ''
											doc.content[1].table.widths = [ '40%', '60%'];
															//Remove the title created by datatTables
															doc.content.splice(0, 1, {
										                        text: [
										                                   { text: 'Patron Status Report \n',bold:true,fontSize:13,alignment: 'center'},
										                        ],
										                        margin: [0, 0, 0, 12],
										                    });
									
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
						                	filename: 'WILMU_PatronStatusReport',
						                	title: 'Patron Status Report',
											charset: "utf-8",
											bom : "true",
											exportOptions: {
												//columns: ':visible',
												search: 'applied',
												order: 'applied',
												rows: ':visible'
											},
											customize: function(doc){
														var split_csv = doc.split("\n");
														$.each(split_csv.slice(1), function (index, csv_row) {
															var csv_cell_array = csv_row.split('","');
															csv_cell_array[1].replace("", (index+1));
														});
														return "Patron Status Report \n\n" + doc;
													 }
				             			},
				            ],
							destroy: true,
							searching: true,
							bLengthChange: false,
							autoWidth: false,
							responsive: true,
							processing: false,
							paging:   false,
							bInfo : false,
							bSort : false,
							language: {
				             loadingRecords : "Please wait - loading...",
				             processing :     "Please wait formatting in progress..."
				        	},
							deferRender: true,
				});
			}
		})
    });
});