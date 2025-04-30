$(document).ready(function() {
	////// table setup
	$('#listingreportTable').DataTable({
		destroy: true,
		searching: false,
		bLengthChange: false,
		autoWidth: false,
		responsive: true,
	});
	
	//////////////////////////////////////////////////////////////////////////
	////////////////////////btn Reterive Click ///////////////////////////////
	//////////////////////////////////////////////////////////////////////////
	$('#Reterive').click(function(){
		/////result display
		var t = $('#listingreportTable').DataTable({
			dom: 'Bfrtip',
			info:     false,
			buttons: [
						{
					  		extend: 'excelHtml5',
					        filename: 'WILMU_Room List',
					        title: 'WILMU Room List',
					                
					 	},
						{
					    	text: '<i class="fa fa-file-pdf-o"></i> PDF',
							extend: 'pdfHtml5',
							filename: 'WILMU_Room List',
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
										doc.content[1].table.body[i][2].alignment = 'left';
										doc.content[1].table.body[i][3].alignment = 'left';
										doc.content[1].table.body[i][4].alignment = 'left';
										doc.content[1].table.body[i][5].alignment = 'left';
										doc.content[1].table.body[i][6].alignment = 'left';
										doc.content[1].table.body[i][7].alignment = 'left';
						           };
							//doc.content[1].table.widths = [ '5%', '10%', '40%', '10%', '10%', '10%'];
											//Remove the title created by datatTables  
											doc.content.splice(0, 1, {
						                        text: [
						                                   { text: 'WILMU Room List',bold:true,fontSize:13,alignment: 'center'}						                        ],
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
		                	filename: 'WILMU_Room List',
							charset: "utf-8",
							bom : "true",
							customize: function(doc){
										var split_csv = doc.split("\n");
										$.each(split_csv.slice(1), function (index, csv_row) {
											var csv_cell_array = csv_row.split('","');
											csv_cell_array[1].replace("", (index+1));
										});
										return "WILMU Room List" + doc;
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
		    	url: "getRoomList",
		        data : {},
		        type: "GET",
		        dataSrc: function (json) {
		            var return_data = new Array();
					var i = 0
					$.each( json, function( key, value ) {
						return_data.push({
			                'No': (i+1),
			                'Room ID' : value.roomId,
							'Capacity' : value.capacity,
							'Book By Multiday' : value.bookByMultiDay,
			                'Price Per Day' : value.pricePerDay,
							'Price Half Day' : value.priceHalfDay,
							'Price Per Hour' : value.pricePerHour,
			                'Admin email' : value.email
			            })
					i++
					});
		            return return_data;
		          },
		     },
			columns: [
				{ "data": "No" },
				{ "data": "Room ID" },
				{ "data": "Capacity" },
				{ "data": "Book By Multiday" },
			    { "data": "Price Per Day" },
				{ "data": "Price Half Day" },
				{ "data": "Price Per Hour" },
				{ "data": "Admin email" }
			  ]
    		});
	})
});