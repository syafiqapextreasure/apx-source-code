$(document).ready(function() {
	
	var today = new Date();  
	
	var allSelected = false;
	$('#branch').multiselect({
    	allSelectedText: 'All',
   		maxHeight: 200,
   		includeSelectAllOption: true,
		numberDisplayed: 1,
		onSelectAll: function(){
		    allSelected = true;
		  },
		  onChange: function(){
		    allSelected = false;
		  }
	}); 
	
	$(".div1vend").removeClass( "col-sm-3 col-md-3" ).addClass( "col-sm-2 col-md-2" );
	
	//////////////////////////////////////////////////////////////////////////
	////////////////////////btn Reterive Click ///////////////////////////////
	//////////////////////////////////////////////////////////////////////////
	
	$('#Reterive').click(function(){
		$('#acqEnquiryTable').dataTable().fnClearTable();
		
		var currencyFormat = $("#setupCurrency").val();
		
		var dateSelection = $('input[name="dateSelection"]:checked').val();
		var dateSelectionLabel = $(".dateselectioinlabel").text();
		
		var startSentDate = moment($("#input-startDate").val(), 'DD/MM/YYYY').format("YYYYMMDD");
		var endSentDate = moment($("#input-endDate").val(), 'DD/MM/YYYY').format("YYYYMMDD");
		var sDate, eDate;
		
		var startSentDate = $("#input-startDate").val();
		if(startSentDate == ''){
			startSentDate = '';
			sDate = dateSelectionLabel + '';
		}else{
			startSentDate = moment($("#input-startDate").val(), 'DD/MM/YYYY').format("YYYYMMDD");
			sDate = dateSelectionLabel +" from " +$("#input-startDate").val();
		}
		
		var endSentDate = $("#input-endDate").val();
		if(endSentDate == ''){
			endSentDate = '';
			eDate = '';
		}else{
			endSentDate = moment($("#input-endDate").val(), 'DD/MM/YYYY').format("YYYYMMDD");
			eDate =  " until " +$("#input-endDate").val();
		}
		
		
		
		var brnch = $("#branch").val(); 
		var allbranch; 
	
		if(brnch != ""){
			if(allSelected){
				allbranch = "Y";
			  }else{
				allbranch = "N";
			    //alert("No.");
			  }
		}else{
			brnch = 0;
			allbranch = "N";
		}
		
		var chkRange = $('#chkBoxRange:checked').val();
		var rangeFirstVal; 
		var rangeLastVal;
		
		if(chkRange == "range"){
    		rangeFirstVal = $('input[name=startInput]').val();
			rangeLastVal = $('input[name=endInput]').val();
    	}else{
    		rangeFirstVal = '';
			rangeLastVal = '';
    	}
		
		var chkBoxSearchCateria = $("input[name='chkBoxSearchCateria']:checked").val();
		var vendor;

		switch(chkBoxSearchCateria) {
			
			case 'vendCode':
					vendor = $("#input-vendorCode").val();  
					break;
			case undefined:
					vendor = 0;
		    		break;	
		}
		
		var status = $("#acqStat").val(); 
		if(status != ""){
		}else{
			status = 0;  
		}
		
		var department = $("#dept").val(); 
		if(department != ""){
		}else{
			department = 0;
		}
		
		
		/////result display
		var t = $('#acqEnquiryTable').DataTable( {
			dom: 'Bfrtip',
            buttons: [
						{
					  		extend: 'excelHtml5',
					        filename: 'WILMU_AcqEnquiryOrderDetails',
					        title: 'Order Details Enquiry ' +sDate +eDate,
					                
					 	},
						{
					    	text: '<i class="fa fa-file-pdf-o"></i> PDF',
							extend: 'pdfHtml5',
							filename: 'WILMU_AcqEnquiryOrderDetails',
							charset: "utf8",
							bom : "true",
							orientation: 'landscape', //portrait
							pageSize: 'A4', //A3 , A5 , A6 , legal , letter
							footer: true,
							exportOptions: {
								stripHtml: true,
								/*search: 'applied',
								order: 'applied'*/
								/*format: {
                                 body: function ( data, row, column, node ) {
                                      // Strip $ from salary column to make it numeric
                                     const arabic = /[\u0600-\u06FF]/;

                        if (arabic.test(data)) {
                            return data.split(' ').reverse().join(' ');
                        }
                        return data;
                                  } 
                              }*/

							},
							customize: function (doc) {
								
						
								
								//doc.defaultStyle.font = "arial";
								//doc.defaultStyle.font-family = 'Arial';
								doc.styles.tableHeader.alignment = 'left';
						
								var rowCount = doc.content[1].table.body.length;
						           for (i = 0; i < rowCount; i++) {
										doc.content[1].table.body[i][8].alignment = 'right';
						               	doc.content[1].table.body[i][9].alignment = 'right';
										doc.content[1].table.body[i][11].alignment = 'right';
						               	doc.content[1].table.body[i][12].alignment = 'right';
						           };
								
							 	//doc.content[1].table.widths = [ '8%', '55%', '12%', '12%', '12%']; 

											//Remove the title created by datatTables  
											doc.content.splice(0, 1, {
						                        text: [
						                                   { text: 'Order Details Enquiry '+sDate +eDate+ '\n',bold:true,fontSize:13,alignment: 'center'}						                        ],
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
		                	filename: 'WILMU_AcqEnquiryOrderDetails',
		                	//title: 'Circulation Activity By ',
							charset: "utf-8",
							bom : "true",
							customize: function(doc){
										
										var split_csv = doc.split("\n");
										//alert(split_csv)
										$.each(split_csv.slice(1), function (index, csv_row) {
											var csv_cell_array = csv_row.split('","');
											csv_cell_array[1].replace("", (index+1));
										});
										
										return "Order Details Enquiry "+sDate + eDate +"\n\n" + doc;
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
		    	url: "ResultAcqEnquiryOrderDetails",
		        data : {currencyFormat :  currencyFormat, dateSelection : dateSelection,
						startSentDate : startSentDate, endSentDate : endSentDate, brnch : JSON.stringify(brnch),
						vendor : vendor,
						rangeFirstVal : rangeFirstVal, rangeLastVal : rangeLastVal,
						status : JSON.stringify(status),
						department : JSON.stringify(department), allbranch : allbranch
						},
		        type: "GET",

		        dataSrc: function (json) {
		            var return_data = new Array();
		            
		            for(var i=0;i< json.length; i++){

		              return_data.push({
		                'No': (i+1),
						'Branch' : json[i].Branch,
		                'Order No' : json[i].OrderNo,
		                'Order Date' : json[i].OrderDate,
		                'Expected Date' : json[i].ExpectedDate,
		                'Title' : json[i].Title,
						'Status' : json[i].Status,
						'Vendor' : json[i].Vendor,
						'Copies/Sets' : json[i].CopiesSets,
						'Received' : json[i].Received,
						'Currency' : json[i].Currency,
						'Foreign Cost' : json[i].ForeignCost,
						'Local Cost' : json[i].LocalCost,
						'Invoice Status' : json[i].InvoiceStatus,
		            })
		            }
					
		            return return_data;
		          },
		     },//This is the end of the AJAX object from the example above
		     	columns    : [
					{'data': 'No'},
					{'data': 'Branch', visible: false},
					{'data': 'Order No'},
					{'data': 'Order Date', type: 'date-uk', targets: 0,},
					{'data': 'Expected Date', type: 'date-uk', targets: 0,},
					{'data': 'Title'},
					{'data': 'Status'},
					{'data': 'Vendor'},
					{'data': 'Copies/Sets', className: "text-right"},
					{'data': 'Received', className: "text-right"},
					{'data': 'Currency'},
					{'data': 'Foreign Cost', className: "text-right", render: $.fn.dataTable.render.number(',', '.', currencyFormat, '')},
					{'data': 'Local Cost', className: "text-right", render: $.fn.dataTable.render.number(',', '.', currencyFormat, '')},
					{'data': 'Invoice Status'},		
				],
				drawCallback: function ( settings ) {
		            var api = this.api();
		            var rows = api.rows( {page:'current'} ).nodes();
		            var last=null;
		 
		            api.column(1, {page:'current'} ).data().each( function ( group, i ) {
		                if ( last !== group ) {
		                    $(rows).eq( i ).before(
		                        '<tr class="group"><td colspan="11">'+group+'</td></tr>'
		                    );
		 
		                    last = group;
		                }
		            } );
		        }
    	});
		
		/*t.on('order.dt search.dt', function () {
			 t.column(0, {search:'applied', order:'applied'}).nodes().each( function (cell, i) {
				cell.innerHTML = i+1;
				//t.cell(cell).invalidate('dom');
			 });
	   }).draw();*/
		
    	

    });
	
});