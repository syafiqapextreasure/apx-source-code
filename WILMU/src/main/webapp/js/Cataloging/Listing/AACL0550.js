$(document).ready(function() {
	
	var today = new Date();  
	
	$("#Reterive").prop( "disabled", true);
	
	
	$("#inputDesc").on("keypress keyup blur",function () {
		
		/*e.preventDefault();
		e.stopImmediatePropagation();*/
		
		
		if($(this).val().length >= 1) {
         $("#Reterive").prop( "disabled", false);
	    } else {
	       $("#Reterive").prop( "disabled", true);
	    }

	});
	
	
	//////////////////////////////////////////////////////////////////////////
	////////////////////////btn Reterive Click ///////////////////////////////
	//////////////////////////////////////////////////////////////////////////
	$('#Reterive').click(function(){

		$('#catreportTable').dataTable().fnClearTable();
		
		var queryby = $('#queryby').val();
		var inputDesc = $('input[name=inputDesc]').val();
		
		var querybyname =  $("#queryby option:selected").attr('name');
		

		
		var t = $('#catreportTable').DataTable( {
			dom: 'Bfrtip',
            buttons: [
						{
					  		extend: 'excelHtml5',
					        filename: 'WILMU_CatTermListing',
					        title: 'Query By '+querybyname +' = ' +inputDesc,
					                
					 	},
						{
					    	text: '<i class="fa fa-file-pdf-o"></i> PDF',
							extend: 'pdfHtml5',
							filename: 'WILMU_CatTermListing',
							orientation: 'portrait', //portrait
							pageSize: 'A4', //A3 , A5 , A6 , legal , letter
							footer: true,
							customize: function (doc) {
								
								//doc.styles.tableHeader.alignment = 'left';
								
								var rowCount = doc.content[1].table.body.length;
						           for (i = 0; i < rowCount; i++) {
										doc.content[1].table.body[i][0].alignment = 'left';
										doc.content[1].table.body[i][1].alignment = 'right';
										doc.content[1].table.body[i][2].alignment = 'left';
										//doc.content[1].table.body[i][4].alignment = 'left';
						           };
								
							 	doc.content[1].table.widths = [ '25%', '15%', '60%'];


											//Remove the title created by datatTables  
											doc.content.splice(0, 1, {
						                        text: [
						                                   { text: 'Query By '+querybyname +' = ' +inputDesc + '\n',bold:true,fontSize:13,alignment: 'center'}						                        ],
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
		                	filename: 'WILMU_CatTermListing',
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
										
										return 'Query By '+querybyname +' = ' +inputDesc +"\n\n" + doc;
									 }
             			},
            ],
			select: true,
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
			footer: true,
		    ajax: {
		    	url: "ResultCatTermListing",
		        type: "GET",
				data : {queryby : queryby, inputDesc : inputDesc
				},
				/*start_time: new Date().getTime(),
				    complete: function(data) {
				        alert('This request took '+(new Date().getTime() - this.start_time)+' ms');
				},*/
		        dataSrc: function (json) {
		            var return_data = new Array();
		            
		            for(var i=0;i< json.length; i++){
			
					return_data.push({
		               		/*'No': '',*/
			                'Authority Status' : json[i].Column1,
			                'Hits' : json[i].Column2,
			                'Description' : json[i].Column3,
							
		            	})
		            }
					
		            return return_data;
		          },
		     },//This is the end of the AJAX object from the example above
		     	columns    : [
					/*{'data': ''},*/
					{'data': 'Authority Status'},
					{'data': 'Hits', className: "text-right"},
					{'data': 'Description'},
				],
    	});



		/*t.on('order.dt search.dt', function () {
				     t.column(1, {search:'applied', order:'applied'}).nodes().each( function (cell, i) {
				           cell.innerHTML = i+1;
				           t.cell(cell).invalidate('dom');
				     });
		}).draw();*/

		
	});
	
	
	
	
});