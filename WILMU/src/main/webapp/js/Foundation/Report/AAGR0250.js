$(document).ready(function() {
	
	var today = new Date();  
	//////////////////////////////////////////////////////////////////////////
	////////////////////////btn Reterive Click ///////////////////////////////
	//////////////////////////////////////////////////////////////////////////
	$('#Reterive').click(function(){

		
		$('#fndreportTable').dataTable().fnClearTable();
		
		var startSentDate = moment($("#input-startDate").val(), 'DD/MM/YYYY').format("YYYYMMDD");
		var endSentDate = moment($("#input-endDate").val(), 'DD/MM/YYYY').format("YYYYMMDD");
		var sDate, eDate;
		
		var startSentDate = $("#input-startDate").val();
		if(startSentDate == ''){
			startSentDate = '';
			sDate = '';
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
			eDate =  " until " +$("#input-endDate").val();
		}

		
		var module = $("input[name='chkBoxModule']:checked").val();

		var moduldesc =$("input[name='chkBoxModule']:checked").data('name');


		/////result display
		var t = $('#fndreportTable').DataTable( {
			dom: 'Bfrtip',
            buttons: [
						{
					  		extend: 'excelHtml5',
					        filename: 'WILMU_FinancialReport',
					        title: 'Financial Report For '+moduldesc +' : ' +sDate  +eDate,
					                
					 	},
						{
					    	text: '<i class="fa fa-file-pdf-o"></i> PDF',
							extend: 'pdfHtml5',
							filename: 'WILMU_FinancialReport',
							charset: "utf-8",
							bom : "true",
							orientation: 'landscape', //landscape
							pageSize: 'A4', //A3 , A5 , A6 , legal , letter
							footer: true,
							exportOptions: {
								search: 'applied',
								order: 'applied'
							},
							customize: function (doc) {
								//doc.defaultStyle.font = 'Arial';
								//doc.styles.tableHeader.alignment = 'left';
								
								var rowCount = doc.content[1].table.body.length;
						           for (i = 0; i < rowCount; i++) {
										doc.content[1].table.body[i][0].alignment = 'left';
						               	doc.content[1].table.body[i][1].alignment = 'left';
										doc.content[1].table.body[i][2].alignment = 'left';
										doc.content[1].table.body[i][3].alignment = 'left';
										doc.content[1].table.body[i][4].alignment = 'left';
										doc.content[1].table.body[i][5].alignment = 'left';
										doc.content[1].table.body[i][6].alignment = 'right';
										doc.content[1].table.body[i][7].alignment = 'right';
										doc.content[1].table.body[i][8].alignment = 'right';
										doc.content[1].table.body[i][9].alignment = 'left';
										doc.content[1].table.body[i][10].alignment = 'left';
										doc.content[1].table.body[i][11].alignment = 'left';

						           };
								
							 	//doc.content[1].table.widths = [ '8%',  '12%', '60%', '20%']; 
							

											//Remove the title created by datatTables  
											doc.content.splice(0, 1, {
						                        text: [
						                                   { text: 'Financial Report For '+moduldesc +' : ' +sDate  +eDate+ ' \n',bold:true,fontSize:13,alignment: 'center'},					                        ],
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
		                	filename: 'WILMU_FinancialReport',
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
										
										return 'Financial Report For '+moduldesc +' : ' +sDate  +eDate +"\n\n" + doc;
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
			footer: true,
		    ajax: {
		    	url: "ResultFinancialReport",
		        type: "GET",
				data : {startSentDate : startSentDate, endSentDate : endSentDate, module : module},
				/*start_time: new Date().getTime(),
				    complete: function(data) {
				        alert('This request took '+(new Date().getTime() - this.start_time)+' ms');
				},*/
		        dataSrc: function (json) {
		            var return_data = new Array();
		            
		            for(var i=0;i< json.length; i++){
			
					
					  var getYear =  json[i].Column5;
					  
						if(getYear != ""){
							getYear = moment(json[i].Column5, 'DD/MM/YYYY').format("YYYY");
						}

						
		              return_data.push({
		                'No': (i+1),
		                'Payment Date' : json[i].Column1,
		                'Vendor' : json[i].Column2,
		                'Invoice No' : json[i].Column3,
		                'Invoice Date' : json[i].Column4,
						'Order Year' : getYear,
						'Amount' : json[i].Column6,
						'Ref Amount' : json[i].Column7,
						'Total Amount' : json[i].Column8,
						'Folio No' : json[i].Column9,
						'Voucher No' : json[i].Column10,
						'Voucher Date' : json[i].Column11,
		            })
		            }
					
		            return return_data;
		          },
		     },//This is the end of the AJAX object from the example above
		     	columns    : [
					{'data': 'No'},
					{'data': 'Payment Date', type: 'date-uk', targets: 0,},
					{'data': 'Vendor'},
					{'data': 'Invoice No'},
					{'data': 'Invoice Date', type: 'date-uk', targets: 0,},
					{'data': 'Order Year'},
					{'data': 'Amount', className: "text-right", render: $.fn.dataTable.render.number(',', '.', 2, '')},
					{'data': 'Ref Amount', className: "text-right", render: $.fn.dataTable.render.number(',', '.', 2, '')},
					{'data': 'Total Amount', className: "text-right", render: $.fn.dataTable.render.number(',', '.', 2, '')},
					{'data': 'Folio No'},
					{'data': 'Voucher No'},
					{'data': 'Voucher Date', type: 'date-uk', targets: 0,},
				],
    	});
		
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