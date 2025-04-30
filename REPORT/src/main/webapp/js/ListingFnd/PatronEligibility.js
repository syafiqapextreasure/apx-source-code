$(document).ready(function() {
	
	//////////////////////////////////////////////////////////////////////////
	//////////////////////////setup page//////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////
	var today = new Date();
	
	$('#patronCate').multiselect({
    	allSelectedText: 'All',
   		maxHeight: 200,
   		includeSelectAllOption: true
	});
	
	$("#patronCate").multiselect("disable");
	

	
	//////////////radio button on change
	$('input[type=checkbox][name=chkBoxSearchCateria]').on('change', function() {
	
		switch ($(this).val()) {
			case 'pateCate':
				$('#patronCate').multiselect("enable");
			  	//$("#icat").multiselect("disable");
				$('#Reterive').prop('disabled', true);
				$('#patronCate').change(function() {
					if($("#smd").val() != ""){
						$('#Reterive').prop('disabled', false);
					}else{
						$('#Reterive').prop('disabled', true);
					}
				});
		      break;	

		  }
	});

	
	//////table setup
	$('#reportTable').DataTable( {
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

		$('#reportTable').dataTable().fnClearTable();
		

		
		var patronCate = $("#patronCate").val(); 
		if(patronCate != ""){
		}else{
			patronCate = 0;
		}
		

		var titleHeader;
		
		
		var chkBoxSearchCateria = $("input[name='chkBoxSearchCateria']:checked").val();
		var searchCateriaval;

		switch(chkBoxSearchCateria) {
		  case 'pateCate':
			searchCateriaval = $('#patronCate').val();
			titleHeader = "Patron Category";
			/*$(head_item ).html(titleHeader);*/
		    // code block
		    break;
		 case undefined:
			searchCateriaval = "";
		    // code block
		    break;
		    // code block
		} 
		
		

		
		
		/////result display
		var t = $('#reportTable').DataTable( {
			dom: 'Bfrtip',
            buttons: [
                /*'excel', 'pdf', 'print'*/
						{
					  		extend: 'excelHtml5',
					        filename: 'WILMU_PatronEligibility',
					        title: 'Patron Eligibility Listing',

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
							filename: 'WILMU_PatronEligibility',
							orientation: 'landscape', //portrait
							
							pageSize: 'A4', //A3 , A5 , A6 , legal , letter
							footer: true,
							//messageTop: 'Income Details by Transaction Type' ,
							//messageBottom: "lllllll",
							
							
							/*exportOptions: {
					            format: {
					                body: function ( data, row, column, node ) {
					                    // Strip $ from salary column to make it numeric
					                    return column === 2 ?
					                        data.replace( /[$,]/g, '' ) :
					                        data;
					                }
					            }
					        },*/
							customize: function (doc) {
								
								/* var rowCount = doc.content[1].table.body.length;
						           for (i = 0; i < rowCount; i++) {
										doc.content[1].table.body[i][2].alignment = 'right';
						               	doc.content[1].table.body[i][3].alignment = 'right';
										doc.content[1].table.body[i][4].alignment = 'right';
						           };*/
								
								//doc.content[1].table.widths = Array(doc.content[1].table.body[0].length + 1).join('*').split('');
							//	doc.content[1].table.widths = [30,100,50,50,50];
							//doc.content[1].table.body[0][3].alignment = 'right';
							 //doc.content[1].table.widths = [ '8%', '55%', '12%', '12%', '12%']; 
							 //doc.content[1].table.body[0][5].alignment = 'right';
						
						



											
											//Remove the title created by datatTables
											doc.content.splice(0, 1, {
						                        text: [
						                                   { text: 'Patron Eligibility Listing',bold:true,fontSize:13,alignment: 'center'},

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
		                	filename: 'WILMU_PatronEligibility',
		                	title: 'Patron Eligibility Listing',
							charset: "utf-8",
							bom : "true",
							customize: function(doc){
										
										var split_csv = doc.split("\n");
										//alert(split_csv)
										$.each(split_csv.slice(1), function (index, csv_row) {
											var csv_cell_array = csv_row.split('","');
											csv_cell_array[1].replace("", (index+1));
										});
										///alert(csv_cell_array[1]);
										//alert(csv_cell_array[1]);
										
										return "Patron Eligibility Listing  \n\n" + doc;
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
			  //emptyTable: "No data available in table", //
              loadingRecords : "Please wait - loading...",
              processing :     "\n\nPlease wait formatting in progress...",
			  //zeroRecords: "No matching records found"
        	},
			/*language: {
                    processing: '<i class="fa fa-spinner fa-spin" style="font-size:24px;color:rgb(75, 183, 245);"></i>'
            },*/
			//serverSide: true,
			deferRender: true,
			footer: true,
		    ajax: {
		    	url: "ResultPatronEligibilityListing",
		        data : {patronCate : JSON.stringify(patronCate),
						chkBoxSearchCateria : chkBoxSearchCateria, searchCateriaval : JSON.stringify(searchCateriaval)
						},
		        type: "GET",
		        dataSrc: function (json) {
		            var return_data = new Array();
		            
		            for(var i=0;i< json.length; i++){
	
		              return_data.push({
		                'No': (i+1),
		                'Patron Category' : json[i].Column1,
		                'Item Category' : json[i].Column2,
		                'SMD' : json[i].Column3,
		                'Branch' : json[i].Column4,
						'Loan Period' : json[i].Column5,
						'Period Type' : json[i].Column6,
						'Max Item Permitted' : json[i].Column7,
						'Max No Renewal Items' : json[i].Column8,
		            })
		            }
					
		            return return_data;
		          },
		     },//This is the end of the AJAX object from the example above
		     	columns    : [
					{'data': 'No'},
					{'data': 'Patron Category'},
					{'data': 'Item Category'},
					{'data': 'SMD'},
					{'data': 'Branch'},
					{'data': 'Loan Period'},
					{'data': 'Period Type'},
					{'data': 'Max Item Permitted',},
					{'data': 'Max No Renewal Items',},

				],
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