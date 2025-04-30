$(document).ready(function() {
	
	//////////////////////////////////////////////////////////////////////////
	//////////////////////////setup page//////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////

	///////////////////// input-startDate set Current Date  //////////////////
	var today = new Date();  
	/*$('#input-startDate').datepicker({
		//startDate : today,
		format: "dd/mm/yyyy",
		todayBtn: true,
		autoclose: true,
		todayHighlight: true,
	});*/
	
	
	///////////////////// input-endDate set Current Date  ////////////////////
	
	/*$('#input-endDate').val(moment(today).format("DD/MM/YYYY"));
	$('#input-endDate').datepicker({
		format: "dd/mm/yyyy",
		todayBtn: true,
		autoclose: true,
		todayHighlight: true,
	});*/
	
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
		
		var expReassign = $("input[name='viewBy']:checked").val();
		var viewBy;
		var titleHeader;
		
		switch(expReassign) {
		  case 'iCate':
			viewBy = "C";
			titleHeader = "Item Category";
		    break;
		  case 'locaOrBranch':
			viewBy = "L";
			titleHeader = "Location";
		    break;
		}
			
		
		
		/////result display
		var t = $('#reportTable').DataTable( {
			dom: 'Bfrtip',
            buttons: [
                /*'excel', 'pdf', 'print'*/
						{
					  		extend: 'excelHtml5',
					        filename: 'WILMU_ExpiredReaasignment',
					        title: 'View By '+titleHeader,

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
							filename: 'WILMU_ExpiredReaasignment',
							orientation: 'landscape', //portrait
							
							pageSize: 'A4', //A3 , A5 , A6 , legal , letter
							footer: true,
							//messageTop: 'Income Details by Transaction Type' ,
							//messageBottom: "lllllll",
							exportData : {decodeEntities:true},
							exportOptions: {
								//columns: ':visible',
								/*search: 'applied',
								order: 'applied',*/
								charset: "utf-8",
                        		bom: "true",
	                              format: {
	                                 /*body: function ( data, row, column, node ) {
	                                      // Strip $ from salary column to make it numeric

											
	                                      if(column===2){
												alert(data);
	                                          data = "\u0012" + data;
											alert("qqq"+data);
	                                      }
	
	                                      return data;
	                                  } */
	                              }
                          	},
							
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
						                                   { text: 'View By ' +titleHeader+ '\n',bold:true,fontSize:13,alignment: 'center'},

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
		                	filename: 'WILMU_ExpiredReaasignment',
		                	title: 'Weeding Listing ',
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
										
										return "View By " +titleHeader + "\n\n" + doc;
									
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
		    	url: "ResultExpiredReassignmentListing",
		        data : {viewBy : viewBy
						},
		        type: "GET",
		        dataSrc: function (json) {
		            var return_data = new Array();
		            
		            for(var i=0;i< json.length; i++){
			
					var callno = json[i].Column2;
					
					if(callno!=undefined){
						if(callno.charAt(0)=='*'){
							callno = callno.slice(1);
							callno = "<i>"+callno+"</i>";
						}	
					}else{
						callno = "";
					}
			
		              return_data.push({
		                'No': (i+1),
		                'Accession No' : json[i].Column1,
		                'Call No' : callno,
		                'Original' : json[i].Column3,
		                'Reassigned' : json[i].Column4,
						'Start' : json[i].Column5,
						'Stop' : json[i].Column6,
						'Reassigned for' : json[i].Column7,
						'Reassigned by' : json[i].Column8,
		            })
		            }
					
		            return return_data;
		          },
		     },//This is the end of the AJAX object from the example above
		     	columns    : [
					{'data': 'No'},
					{'data': 'Accession No'},
					{'data': 'Call No'},
					{'data': 'Original'},
					{'data': 'Reassigned'},
					{'data': 'Start'},
					{'data': 'Stop'},
					{'data': 'Reassigned for'},
					{'data': 'Reassigned by'},

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