$(document).ready(function() {
	
	
	//////////////////////////////////////////////////////////////////////////
	//////////////////////////setup page//////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////
	///////////////////// input-startDate set Current Date  //////////////////
	var today = new Date();  
	$('#input-startDate').datepicker({
		//startDate : today,
		format: "dd/mm/yyyy",
		todayBtn: true,
		autoclose: true,
		todayHighlight: true,
	});
	
	///////////////////// input-endDate set Current Date  ////////////////////
	
	$('#input-endDate').val(moment(today).format("DD/MM/YYYY"));
	$('#input-endDate').datepicker({
		format: "dd/mm/yyyy",
		todayBtn: true,
		autoclose: true,
		todayHighlight: true,
	});
	$('#deftEnq').DataTable( {
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
		
		$('#deftEnq2').dataTable().fnClearTable();
		
		var startSentDate = moment($("#input-startDate").val(), 'DD/MM/YYYY').format("YYYYMMDD");
		var endSentDate = moment($("#input-endDate").val(), 'DD/MM/YYYY').format("YYYYMMDD");
		var sDate, eDate;
		
		var startSentDate = $("#input-startDate").val();
		if(startSentDate == ''){
			startSentDate = '';
			sDate = "";
		}else{
			startSentDate = moment($("#input-startDate").val(), 'DD/MM/YYYY').format("YYYYMMDD");
			sDate = "from " +$("#input-startDate").val();
		}
		
		var endSentDate = $("#input-endDate").val();
		if(endSentDate == ''){
			endSentDate = '';
			eDate = '';
		}else{
			endSentDate = moment($("#input-endDate").val(), 'DD/MM/YYYY').format("YYYYMMDD");
			eDate =  $("#input-endDate").val();
		}
	
	$('#deftEnq').DataTable( {
		dom: 'Bfrtip',
            buttons: [
                /*'excel', 'pdf', 'print'*/
						{
					  		extend: 'excelHtml5',
					        filename: 'WILMU_DefaulterEnquiry',
					        title: "Defaulter Enquiry " +sDate + " until " +eDate,

			   
					 	},
						{
					    	text: '<i class="fa fa-file-pdf-o"></i> PDF',
							extend: 'pdfHtml5',
							filename: 'WILMU_DefaulterEnquiry',
							charset: "utf-8",
							bom : "true",
							exportData : {decodeEntities:true},
							orientation: 'landscape', //portrait
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
								
								// Add header rows to the table (1 row in this case)
							    doc.content[1].table.headerRows = 1;
							
							    // Set the width of each column (adjust the values as needed)
							    doc.content[1].table.widths = ['5%', '20%', '50%', '15%', '10%'];
							
							    // Customize the table header style
							    doc.content[1].table.body[0][0].alignment = 'left'; // Align the first column header to the left
							    doc.content[1].table.body[0][1].alignment = 'left';
							    doc.content[1].table.body[0][2].alignment = 'left';
							    doc.content[1].table.body[0][3].alignment = 'left';
							    doc.content[1].table.body[0][4].alignment = 'left';
					

											//Remove the title created by datatTables
											doc.content.splice(0, 1, {
						                        text: [
						                                   { text: 'Defaulter Enquiry '+sDate+ ' until '+eDate+ ' \n',bold:true,fontSize:13,alignment: 'center'},
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
		                	filename: 'WILMU_DefaulterEnquiry',
		                	title: 'Defaulter Enquiry ',
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
										
										return "Defaulter Enquiry " +sDate + " until "+ eDate +"\n\n" + doc;
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
		//info: false,
		order: [[ 3, 'asc' ]],
	    ajax: {
	    	url: "resultDeftEnq",
	    	 data : {startSentDate : startSentDate, endSentDate : endSentDate
						},
	        type: "GET",
	        dataSrc: function (json) {
	            var return_data = new Array();

	            for(var i=0;i< json.length; i++){

	              return_data.push({
					'No': (i+1),
	                'Patron ID': json[i].PatronID,
	                'Patron Name' : json[i].PatronName, 
	                'Notice Type' : json[i].NoticeType,
	                'Accession No' : json[i].AccessionNo,
	              })
	            }
				
	            return return_data;
	          },
	     },//This is the end of the AJAX object from the example above
	     	columns    : [
				{'data': 'No'},
				{'data': 'Patron ID'},
				{'data': 'Patron Name'},
				{'data': 'Notice Type', className: "text-center"},
				{'data': 'Accession No', className: "text-center"},
			],
	});
	
	});
	
	//reload table
	$('#refresh').click(function(){
		  $('#deftEnq').DataTable().ajax.reload();
	});
	
});