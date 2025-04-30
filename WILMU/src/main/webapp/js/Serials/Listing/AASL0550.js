$(document).ready(function() {

	function messageBox(code, criteria, label){
			$.get("Global?type=Handler&name=Error_Page",{GL79ERRCODE : code,
				criteria : criteria,
			 	label : label},function(result){
			 		//alert(result);
					//swal('',result, 'info' );
					swal(result);
				
			});
	} 
	
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
	
	
	//$(".div1patrid").removeClass( "col-sm-3 col-md-3" ).addClass( "col-sm-2 col-md-2" ); 
	
	/////////////////--- EDITOR ------/////////
	
	////1) Hide selection criteria
	/////List all div   
	////.rangeDiv, .vendorSearch, .patronSearch, .orderModeDiv, .statusDiv, .acctDiv,
	////.deptDiv, .invstatDiv, .itemstatDiv, .recordselectionDiv, .claimstatDiv, .printerStat
	
	$(".statusDiv, .vendorSearch, .acctDiv, .itemstatDiv").hide();
	$(".recordselectionDiv, .claimstatDiv, .printerStat").hide();
	$(".dateDiv, .deptDiv").hide();

	
	////2) Change Label
	///Date Label
	//$(".datelabel").html("<b>Claim Date Range</b>");
	
	///Range Label
	//$(".rangeLabel").html("<b>Order Number Range</b>");
	
	///Status Label (Request/Order /Invoice /Gift)
	//$(".statusLabel").html("<b>Request Status</b>")

	

	
	////3) ... search
	
	
	
	////Patron
	$('.patronid').on('click',function(){
		var url = "Modal_PatrSearch";
		
		$.get(url,function(data){
			$("#Modal_PatrSearchContent").html(data);
		});
	});
	
	///keyup
	$("#lblPatronID").on("keypress keyup blur",function (e) {
		var id = $("#lblPatronID").val();

		$(".lblName").empty();
		////display vendor name
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
	
	
	//clear paron id keydown backspace
	$("#lblPatronID").keydown(function(e){ 
		var code = e.keyCode || e.which;
		if(code == '8' ||code == '46'){
			$("#.lblName").empty();
		}
		
		if(code == 13) {
		    e.preventDefault();
		    return false;
		 }
	});
	

	/////////////////--- EDITOR ------/////////
	
	
	//////////////////////////////////////////////////////////////////////////
	////////////////////////btn Reterive Click ///////////////////////////////
	//////////////////////////////////////////////////////////////////////////
	$('#Reterive').click(function(){
		
		$('#reportTable').dataTable().fnClearTable();
		
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
		
		
		
		//Vendor AND Patron Id
		var chkBoxSearchCateria = $('#chkBoxSearchCateria:checked').val();
		
		var patronID;
		if(chkBoxSearchCateria == "patrID"){
    		patronID = $("#lblPatronID").val();
    	}else{
    		patronID = 0;
    	}

		//////view mode
		var viewFormat = $("input[name='formatlabel']:checked").val();
		

		/////////////////--- EDITOR ------/////////		
		
		/////result display
		var t = $('#reportTable').DataTable( {
			dom: 'Bfrtip',
			paging: false,
			info:     false,
            buttons: [
						/*{
					  		extend: 'excelHtml5',
					        filename: 'WILMU_SerialEditListing',
					        title: 'Serials Edit Listing ' +sDate +eDate,
					                
					 	},*/
						/*{
					    	text: '<i class="fa fa-file-pdf-o"></i> PDF',
							extend: 'pdfHtml5',
							filename: 'WILMU_SerialEditListing',
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
								
								doc.styles.tableHeader.alignment = 'left';
								
								 var rowCount = doc.content[1].table.body.length;
						           for (i = 0; i < rowCount; i++) {
										doc.content[1].table.body[i][0].alignment = 'left';
										doc.content[1].table.body[i][1].alignment = 'left';
										doc.content[1].table.body[i][2].alignment = 'left';
										doc.content[1].table.body[i][3].alignment = 'left';
										doc.content[1].table.body[i][4].alignment = 'left';
						           };
								
							 	//doc.content[1].table.widths = [ '3%', '9%', '9%', '8%', '13%', '9%', '7%', '8%', '3%', '7%', '7%', '9%', '8%']; 

							 		doc.content.push( {
						                        //margin: [ 0, 0, 0, 12 ],
						                        alignment: 'right',
												bold:true,
								                   text: [
								                         	$('.divtotal').text(), 
								                        ]
	
											 } );

											//Remove the title created by datatTables  
											doc.content.splice(0, 1, {
						                        text: [
						                                   { text: 'Serials Edit Listing '+sDate +eDate+ '\n',bold:true,fontSize:13,alignment: 'center'}						                        ],
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
		                	filename: 'WILMU_SerialEditListing',
		                	//title: 'Received Items By ',
							charset: "utf-8",
							bom : "true",
							customize: function(doc){
										
										var split_csv = doc.split("\n");
										//alert(split_csv)
										$.each(split_csv.slice(1), function (index, csv_row) {
											var csv_cell_array = csv_row.split('","');
											csv_cell_array[1].replace("", (index+1));
										});
										
										return "Serials Edit Listing "+sDate + eDate +"\n\n" + doc;
									 }
             			},*/

						{
					    	//text: '<i class="fa fa-file-pdf-o"></i> PDF', extend: "pdf", className: "fred"
							//extend: 'pdfHtml5', className: "RequestedItemStatus",
							//filename: 'WILMU_AcqRequestedItemStatus',
							text: "Print",
							className : 'Print',
				                action: function ( e, dt, node, config ) {
									
									 var pageTitle = 'Print',
							            stylesheet = '//maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css',
							            win = window.open('', 'Print', '');
										win.document.write('<html><head><title>' + pageTitle + '</title>' +
							            '<link rel="stylesheet" href="' + stylesheet + '">' +
							            '</head><body>'+
										'<br> <b>Serials Edit Listing'+sDate + eDate+
										'</b><br><br>'+
										'<table border="1"' + $('#reportTable')[0].outerHTML + '</table></body></html>')
								        win.document.close();
								        win.print();
								        win.close();
								        return false;
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
		    	url: "ResultSeEditListing",
		        data : {dateSelection : dateSelection,
						startSentDate : startSentDate, endSentDate : endSentDate,
						patronID : patronID, viewFormat : viewFormat
						},
		        type: "GET",
		        dataSrc: function (json) {
		            var return_data = new Array();

					 var count = 1;
		            
		            for(var i=0;i< json.length; i++){

		            	

		            	if(i>0){

		            		if(json[i].Column1 != ""){
								if(json[i].Column1 != json[i-1].Column1){
								count++;
								
							}
		            		}
										
						}

		              return_data.push({
		                /*'No': (i+1),*/
		                'Control No' : json[i].Column1,//"No : " +count   +" BIBLIOGRAPHIC CONTROL NUMBER " +json[i].Column1,
		                'Tag' : json[i].Column2,
		                'Description' : json[i].Column3,
		                'Indi 1' : json[i].Column4,
 						'Indi 2' : json[i].Column5,
		                'Data' : json[i].Column6,
		                'Accession No' : json[i].Column7,
		                'Branch' : json[i].Column9,
 						'Location' : json[i].Column10,
		                'Item Category' : json[i].Column11,
		                'Status' : json[i].Column12,
		                'Matno' : json[i].Column8,
		            })
		            }
					
		            return return_data;
		          },
		     },//This is the end of the AJAX object from the example above
		     	columns    : [
					/*{'data': 'No'},*/
					{'data': 'Control No',visible: false},
					{'data': 'Tag'},
					{'data': 'Description'},
					{'data': 'Indi 1'},
					{'data': 'Indi 2'},
					{'data': 'Data'},
					{'data': 'Accession No'},
					{'data': 'Branch'},
					{'data': 'Location'},
					{'data': 'Item Category'},
					{'data': 'Status'},
					{'data': 'Matno',visible: false},
				],

						
				

		        drawCallback: function ( settings ) {
									
				var api = this.api();

			     var rows = api.rows({ page: 'current' }).nodes();
			     var last = null;
			     var columns = [0];
			 
			     for (c = 0; c < columns.length; c++) {
					 
			
			         api.column(c, { page: 'current' }).data().each(function (group, i) {

			             if (last !== group) {
			             	var rowData = api.row(i).data();

			                 $(rows).eq(i).before(
			                     '<tr class="group"><td colspan="9"><b>BIBLIOGRAPHIC CONTROL NUMBER : '+ group +'</b></td></tr>'
			          
			                 );
			 
			                 last = group;
			             }
			         });
			     }
			 
			 
 			},
    	});

		
		/*t.on('order.dt search.dt', function () {
			 t.column(0, {search:'applied', order:'applied'}).nodes().each( function (cell, i) {
				cell.innerHTML = i+1;
				t.cell(cell).invalidate('dom');
			 });
	   }).draw();*/

	   if(viewFormat == 'marc'){
			t.column( [1] ).visible( true );
			t.column( [2] ).visible( false );
			t.column( [3] ).visible( true );
			t.column( [4] ).visible( true );
		}else if(viewFormat == 'linear'){
			t.column( [1] ).visible( false );
			t.column( [2] ).visible( true );
			t.column( [3] ).visible( false );
			t.column( [4] ).visible( false );
		}
		
    	

    });

	
	
});