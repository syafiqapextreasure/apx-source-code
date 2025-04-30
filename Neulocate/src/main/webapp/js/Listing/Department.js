$(document).ready(function() {
	
	//////////////////////////////////////////////////////////////////////////
	//////////////////////////setup page//////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////
	
	///$('#Reterive').prop('disabled', true);
	var today = new Date();

	//////table setup
	$('#foundationTable').DataTable( {
		destroy: true,
		searching: false,
		bLengthChange: false,
		autoWidth: false,
		responsive: true,
	});
	


	//////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////
	
	//////////////////////////////////////////////////////////////////////////
	////////////////////////btn Reterive Click ///////////////////////////////
	//////////////////////////////////////////////////////////////////////////
	$('#Reterive').click(function(){
		$('#foundationTable').dataTable().fnClearTable();
		
		
		/////result display
		var t = $('#foundationTable').DataTable( {
			dom: 'Bfrtip',
			buttons: [
				 /*'excel', 'pdf', 'print'*/
				{
					extend: 'excelHtml5',
					filename: 'WILMU_DepartmentListing',
					title: 'Department Listing  ',

					 //messageTop: 'Minimum Borrowing = ' ,
					 /*	customize: function( xlsx ) {
						var sheet = xlsx.xl.worksheets['sheet1.xml'];
					// $('row:first c', sheet).attr( 's', '42' );
 					$('c[r=B3] t', sheet).text( 'Custom text' );
					}*/
					                
				},
				{
					    	text: '<i class="fa fa-file-pdf-o"></i> PDF',
							extend: 'pdfHtml5',
							filename: 'WILMU_DepartmentListing',
							orientation: ' landscape', //portrait
							pageSize: 'A4', //A3 , A5 , A6 , legal , letter
							footer: true,
							//messageTop: 'Income Details by Transaction Type' ,
							//messageBottom: "lllllll",
							exportOptions: {
								//columns: ':visible',
								search: 'applied',
								order: 'applied'
							},
							customize: function (doc) {
								
								 /*var rowCount = doc.content[1].table.body.length;
						           for (i = 0; i < rowCount; i++) {
										doc.content[1].table.body[i][2].alignment = 'right';
						               	doc.content[1].table.body[i][3].alignment = 'right';
										doc.content[1].table.body[i][4].alignment = 'right';
						           };*/
								
						
							//doc.content[1].table.widths = [ '6%', '8%', '20%', '15%', '15%', '15%', '15%', '15%', '6%']; 
						
						

											//Remove the title created by datatTables
											doc.content.splice(0, 1, {
						                        text: [
						                                   { text: 'Department Listing  \n',bold:true,fontSize:13,alignment: 'center'},

						                                   /////{ text: $("#libname").val() +'\n',bold:true,fontSize:13,alignment: 'center'},
						                        ],
						                        margin: [0, 0, 0, 12],
						                    });
					
											doc['footer']=(function(page, pages) {
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
					    },
					
               			{
		                	extend: 'csv',
		                	filename: 'WILMU_DepartmentListing',
		                	//title: 'Circulation Activity By ',
							customize: function(doc){
										
										var split_csv = doc.split("\n");
										//alert(split_csv)
										$.each(split_csv.slice(1), function (index, csv_row) {
											var csv_cell_array = csv_row.split('","');
											csv_cell_array[1].replace("", (index+1));
										});
										///alert(csv_cell_array[1]); 
										//alert(csv_cell_array[1]);
										
										return "Department Listing  \n\n" + doc;
										//var split_csv = doc.split("\n");
										
						
										 
									 }
             			},
			],
			destroy: true,
			searching: true,
			bLengthChange: false,
			autoWidth: false,
			responsive: true,
			processing: true,
			deferRender: true,
			language: {
             loadingRecords : "Please wait - loading...",
              processing :     "Please wait formatting in progress..."
        	},
			footer: true,
			/*language: {
        processing: "Loading. Please wait..."
    },*/
		    ajax: {
		    	url: "ResultDepartmentListing",
		        /*data : {startSentDate : startSentDate, endSentDate : endSentDate, 
						patrStatValue : JSON.stringify(patrStatValue), patrStat : patrStat,
						patrCateValue : JSON.stringify(patrCateValue), patrCate : patrCate,
						brnch : JSON.stringify(brnch)
						},*/
		        type: "GET",
		        dataSrc: function (json) {
		            var return_data = new Array();
		            
		            for(var i=0;i< json.length; i++){
			
		              return_data.push({
		                'No': (i+1),
		                'Department Code' : json[i].Id,
		                'Department Name' : json[i].Desc,
						'Address-1' : json[i].Column1,
		                'Address-2' : json[i].Column2,
						'Address-3' : json[i].Column3,
						'Tel No' : json[i].Column4,
						'Fax No' : json[i].Column5,	
						'No of staff' : json[i].Column6,							
		            })
		            }
					
		            return return_data;
		          },
		     },//This is the end of the AJAX object from the example above
		     	columns    : [
					{'data': 'No'},
					{'data': 'Department Code'},
					{'data': 'Department Name'},
					{'data': 'Address-1'},
					{'data': 'Address-2'},
					{'data': 'Address-3'},
					{'data': 'Tel No'},
					{'data': 'Fax No'},
                    {'data': 'No of staff'},				
				],
				//order: [[ 1, 'asc' ]],
				
    } );



		
		/*t.on('order.dt search.dt', function () {
			 t.column(0, {search:'applied', order:'applied'}).nodes().each( function (cell, i) {
				cell.innerHTML = i+1;
				t.cell(cell).invalidate('dom');
			 });
	   }).draw();*/
    	

    	
    });
	//////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////
	
});