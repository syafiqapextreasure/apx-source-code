$(document).ready(function() {
	
	//////////////////////////////////////////////////////////////////////////
	//////////////////////////setup page//////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////
	
	$('#Reterive').prop('disabled', true);
	$("#mode, #acct").prop("selectedIndex",-1);
	//$("input[name=chkBoxSearchCateria][value=orderNo]").prop('checked', true);
	
	$("#input-startDate, #input-endDate, #acct, #mode").prop('disabled', true);
	$("#input-startDoc, #input-endDoc, #input-vendorCode, .vendorCode").prop('disabled', true);
	
	
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
	
	//////////////radio button on change
	$('input[type=radio][name=chkBoxSearchCateria]').on('change', function() {
		switch ($(this).val()) {
		    case 'orderNo':
				$("#input-startDoc, #input-endDoc").prop('disabled', false);
				
				$("#input-startDate, #input-endDate, #acct, #mode").prop('disabled', true);
				$("#input-vendorCode, .vendorCode").prop('disabled', true);
	
		      break;
		    case 'orderMode':
				$("#mode, #acct").prop("selectedIndex",-1);
				$("#mode").prop('disabled', false);
			
				$("#input-startDate, #input-endDate, #acct").prop('disabled', true);
				$("#input-startDoc, #input-endDoc, #input-vendorCode, .vendorCode").prop('disabled', true);
	
		      break;
			case 'acct':
				$("#mode, #acct").prop("selectedIndex",-1);
				$("#acct").prop('disabled', false);
				
				$("#input-startDate, #input-endDate, #mode").prop('disabled', true);
				$("#input-startDoc, #input-endDoc, #input-vendorCode, .vendorCode").prop('disabled', true);
	
		      break;
			/*case 'orderDate':
			
				$("#input-startDate, #input-endDate").prop('disabled', false);
				
				$("#acct, #mode").prop('disabled', true);
				$("#input-startDoc, #input-endDoc, #input-vendorCode, .vendorCode").prop('disabled', true);
	
		      break;*/	
		  }
	});
	
	////////Branch mandatary
	/*$('#branch').change(function() {
		if($("#branch").val() != ""){
			$('#Reterive').prop('disabled', false);
		}else{
			$('#Reterive').prop('disabled', true);
		}
	 });*/

	///on click searc patron 
	$('.patronid').on('click',function(){
		var url = "Modal_PatrSearch";
		
		//alert(url);
		$.get(url,function(data){
			$("#Modal_PatrSearchContent").html(data);
		});
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
		
		var startSentDate = moment($("#input-startDate").val(), 'DD/MM/YYYY').format("YYYYMMDD");
		var endSentDate = moment($("#input-endDate").val(), 'DD/MM/YYYY').format("YYYYMMDD");
		
		var startSentDate = $("#input-startDate").val();
		if(startSentDate == ''){
			startSentDate = '';
		}else{
			startSentDate = moment($("#input-startDate").val(), 'DD/MM/YYYY').format("YYYYMMDD");
		}
		
		var endSentDate = $("#input-endDate").val();
		if(endSentDate == ''){
			endSentDate = '';
		}else{
			endSentDate = moment($("#input-endDate").val(), 'DD/MM/YYYY').format("YYYYMMDD");
		}
		
		/*var brnch = $("#branch").val(); 
		if(brnch != ""){
		}else{
			brnch = 0;
		}*/
		
		/*var head_item = $('#reportTable').DataTable().columns(1).header();
		var titleHeader;*/
		
		
		var chkBoxSearchCateria = $("input[name='chkBoxSearchCateria']:checked").val();
		var searchCateriaval;
		switch(chkBoxSearchCateria) {
		  case 'patrID':
			searchCateriaval = $('#lblPatronID').val();
			/*titleHeader = "Membership Category";
			$(head_item ).html(titleHeader);*/
		    // code block
		    break;	
		  case 'pateCate':
			searchCateriaval = $('#patronCate').val();
			/*titleHeader = "Membership Category";
			$(head_item ).html(titleHeader);*/
		    // code block
		    break;
		  case 'smd':
			searchCateriaval = $('#smd').val();
			/*titleHeader = "SMD";
			$(head_item ).html(titleHeader);*/
			//$(head_item ).html('SMD header');
		    // code block
		    break;
		  case 'itemcate':
			searchCateriaval = $('#icat').val();
			/*titleHeader = "Item Category";
			$(head_item ).html(titleHeader);*/
		    // code block
		    break;
		  case 'loca':
			searchCateriaval = $('#loca').val();
			/*titleHeader = "Item Location";
			$(head_item ).html(titleHeader);*/
		    // code block
		    break;
		 case undefined:
			searchCateriaval = "";
		    // code block
		    break;
		    // code block
		}  
		
		/*alert(chkBoxSearchCateria +"chkBoxSearchCateria");
		alert(JSON.stringify(searchCateriaval) +"searchCateriaval");*/
		
		// Setup - add a text input to each footer cell
/*    $('#reportTable thead tr:eq(1) th').each( function () {
        var title = $(this).text();
        $(this).html( '<input type="text" placeholder="Search '+title+'" class="column_search" />' );
    } );
*/
		/////result display
		var t = $('#reportTable').DataTable( {
			dom: 'Bfrtip',
            buttons: [
                /*'excel', 'pdf', 'print'*/
						{
					  		extend: 'excelHtml5',
					        filename: 'WILMU_OrdersReport',
					        title: 'Orders Report ',

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
							filename: 'WILMU_OrdersReport',
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
								
								//doc.content[1].table.widths = Array(doc.content[1].table.body[0].length + 1).join('*').split('');
							//	doc.content[1].table.widths = [30,100,50,50,50];
							//doc.content[1].table.body[0][3].alignment = 'right';
							 doc.content[1].table.widths = [ '3%', '9%', '16%', '9%', '9%','9%','9%','5%','5%','5%', '5%', '5%', '6%', '6%']; 
							 //doc.content[1].table.body[0][5].alignment = 'right';
						
						


											//Remove the title created by datatTables
											doc.content.splice(0, 1, {
						                        text: [
						                                   { text: 'Orders Report '+' \n',bold:true,fontSize:13,alignment: 'center'},
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
		                	filename: 'WILMU_OrdersReport',
		                	title: 'Orders Report ',
							customize: function(doc){
										
										var split_csv = doc.split("\n");
										//alert(split_csv)
										$.each(split_csv.slice(1), function (index, csv_row) {
											var csv_cell_array = csv_row.split('","');
											csv_cell_array[1].replace("", (index+1));
										});
										///alert(csv_cell_array[1]);
										//alert(csv_cell_array[1]);
										
										return "Orders Report " +"\n\n" + doc;
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
			/*language: {
		        "processing": "Loading. Please wait..."
		    },*/
			language: {
             loadingRecords : "Please wait - loading...",
             processing :     "Please wait formatting in progress"
        	},
			/*language: {
                    processing: '<i class="fa fa-spinner fa-spin" style="font-size:24px;color:rgb(75, 183, 245);"></i>'
            },*/
			//serverSide: true,
			deferRender: true,
			footer: true,
		    ajax: {
		    	url: "ResultOrdersReport",
		        data : {startSentDate : startSentDate, endSentDate : endSentDate,
						chkBoxSearchCateria : chkBoxSearchCateria, searchCateriaval : JSON.stringify(searchCateriaval)
						},
		        type: "GET",
		        dataSrc: function (json) {
		            var return_data = new Array();
		            
		            for(var i=0;i< json.length; i++){

					/* var callno = json[i].Column3;
					
					if(callno!=undefined){
						if(callno.charAt(0)=='*'){
							callno = callno.slice(1);
							callno = "<i>"+callno+"</i>";
						}	
					}else{
						callno = "";
					}
 */

					var title = json[i].Column4;
					
					if(title!=undefined){
						if(title.charAt(0)=='*'){
							title = title.slice(1);
							title = "<i>"+title+"</i>";
						}	
					}else{
						title = "";
					}
					
					var author = json[i].Column6;
					
					if(callno!=undefined){
						if(author.charAt(0)=='*'){
							author = author.slice(1);
							author = "<i>"+author+"</i>";
						}	
					}else{
						author = "";
					}


					var isbn = json[i].Column5;
					
					if(isbn!=undefined){
						if(isbn.charAt(0)=='*'){
							isbn = isbn.slice(1);
							isbn = "<i>"+isbn+"</i>";
						}	
					}else{
						isbn = "";
					}		
					
					/*if(title.charAt(0)=='*'){
						title = title.slice(1);
						title = "<i>"+title+"</i>";
						//title.css('font-style', 'italic');
					}else{
						//title.css('font-style', 'normal');
					}*/
					
		              return_data.push({
		                'No': (i+1),
		                'Order No' : json[i].Column1,
		                'Order Date' : json[i].Column2,
		                'Vendor' : json[i].Column3,
						'Title' : title,
						'ISBN' : isbn,
						'Author' : athor,
						'Quantity' : json[i].Column7,
		                'Unit Price' : json[i].Column8,
						'Total Price' : json[i].Column9,
                        'Local Price' : json[i].Column10,
		            })
		            }
					
		            return return_data;
		          },
		     },//This is the end of the AJAX object from the example above
		     	columns    : [
					{'data': 'No'},
					{'data': 'Order No'},
					{'data': 'Order Date'},
					{'data': 'Vendor'},
					{'data': 'Title'},
					{'data': 'ISBN'},
					{'data': 'Quantity'},
					{'data': 'Author'},
					{'data': 'Unit Price'},
					{'data': 'Total Price'},
					{'data': 'Local Price'},					
										
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