$(document).ready(function() {
	
	//////////////////////////////////////////////////////////////////////////
	//////////////////////////setup page//////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////
	
	$(".datelabel").html("<b>Weed Date Range</b>");
	
	//$('#Reterive').prop('disabled', true);
	
	$(".patronCategory").hide();
	
	$('#smd, #branch, #icat, #loca').multiselect({
    	allSelectedText: 'All',
   		maxHeight: 200,
   		includeSelectAllOption: true
	});
	
	$("#smd, #icat").multiselect("disable");
	
	
	
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
	
	
	//////////////WeedingListing key in number only//////////////////////////////////////
	$("#WeedingListing").on("keypress keyup blur",function (event) {    
        $(this).val($(this).val().replace(/[^\d].+/, ""));
         if ((event.which < 48 || event.which > 57)) {
             event.preventDefault();
         }
    });
	
	//////////////radio button on change
	$('input[type=radio][name=chkBoxSearchCateria]').on('change', function() {

		switch ($(this).val()) {
		    case 'branch':
				$('#branch').multiselect("enable");
			  	$('#Reterive').prop('disabled', false);
		      break;
		    case 'itemcate':
				$('#icat').multiselect("enable");
			  	$("#smd").multiselect("disable");
				$('#Reterive').prop('disabled', true);
					$('#icat').change(function() {
						if($("#icat").val() != ""){
							$('#Reterive').prop('disabled', false);
						}else{
							$('#Reterive').prop('disabled', true);
						}
					});
		      break;
			case 'smd':
				$('#smd').multiselect("enable");
			  	$("#icat").multiselect("disable");
				$('#Reterive').prop('disabled', true);
				$('#smd').change(function() {
					if($("#smd").val() != ""){
						$('#Reterive').prop('disabled', false);
					}else{
						$('#Reterive').prop('disabled', true);
					}
				});
		      break;	
			case 'loca':
				$('#loca').multiselect("enable");
			  	$("#patronCate, #icat, #smd").multiselect("disable");
				$('#Reterive').prop('disabled', true);
				$('#loca').change(function() {
					if($("#loca").val() != ""){
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
			eDate =  $("#input-endDate").val();
		}
		
		var brnch = $("#branch").val(); 
		if(brnch != ""){
		}else{
			brnch = 0;
		}
		

		var titleHeader;
		
		
		var chkBoxSearchCateria = $("input[name='chkBoxSearchCateria']:checked").val();
		var searchCateriaval;

		switch(chkBoxSearchCateria) {
		  case 'patrID':
			searchCateriaval = $('#lblPatronID').val();
			titleHeader = "Patron Id";
			/*$(head_item ).html(titleHeader);*/
		    // code block
		    break;	
		  case 'pateCate':
			searchCateriaval = $('#patronCate').val();
			titleHeader = "Patron Category";
			/*$(head_item ).html(titleHeader);*/
		    // code block
		    break;
		  case 'smd':
			searchCateriaval = $('#smd').val();
			titleHeader = "SMD";
			/*$(head_item ).html(titleHeader);*/
			//$(head_item ).html('SMD header');
		    // code block
		    break;
		  case 'itemcate':
			searchCateriaval = $('#icat').val();
			titleHeader = "Item Category";
			/*$(head_item ).html(titleHeader);*/
		    // code block
		    break;
		  case 'loca':
			searchCateriaval = $('#loca').val();
			titleHeader = "Item Location";
			/*$(head_item ).html(titleHeader);*/
		    // code block
		    break;
		 case undefined:
			searchCateriaval = "";
		    // code block
		    break;
		    // code block
		} 
		
		
		var collectionType = $("input[name='collectionType']:checked").val();
		var type;
		
		switch(collectionType) {
		  case 'mono':
			type = "M";
		    break;
		  case 'irs':
			type = "I";
		    break;
		}
		
		
		
		/////result display
		var t = $('#reportTable').DataTable( {
			dom: 'Bfrtip',
            buttons: [
                /*'excel', 'pdf', 'print'*/
						{
					  		extend: 'excelHtml5',
					        filename: 'WILMU_WeedingListing',
					        title: 'Weeding By '+titleHeader +sDate + " until " +eDate,

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
							filename: 'WILMU_WeedingListing',
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
	                                 body: function ( data, row, column, node ) {
	                                      // Strip $ from salary column to make it numeric

											
	                                      if(column===2){
												/*alert(data);
	                                          data = "\u0012" + data;
											alert("qqq"+data);*/
	                                      }
	
	                                      return data;
	                                  } 
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
						                                   { text: 'Weeding By  '+titleHeader +sDate+ ' until '+eDate+ '\n',bold:true,fontSize:13,alignment: 'center'},

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
		                	filename: 'WILMU_WeedingListing',
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
										
										return "Weeding By " +sDate + " until "+ eDate +"\n\n" + doc;
									
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
		    	url: "ResultWeedingListing",
		        data : {startSentDate : startSentDate, endSentDate : endSentDate,  brnch : JSON.stringify(brnch),
						chkBoxSearchCateria : chkBoxSearchCateria, searchCateriaval : JSON.stringify(searchCateriaval),
						type : type
						},
		        type: "GET",
		        dataSrc: function (json) {
		            var return_data = new Array();
		            
		            for(var i=0;i< json.length; i++){
			
		              return_data.push({
		                'No': (i+1),
		                'Accession No' : json[i].Column1,
		                'Title' : json[i].Column2,
		                'Reason' : json[i].Column3,
		                'Weed Date' : json[i].Column4,
						'Item Cate' : json[i].Column5,
						'SMD' : json[i].Column6,
						'Branch' : json[i].Column7,
						'Location' : json[i].Column8,
		            })
		            }
					
		            return return_data;
		          },
		     },//This is the end of the AJAX object from the example above
		     	columns    : [
					{'data': 'No'},
					{'data': 'Accession No'},
					{'data': 'Title'},
					{'data': 'Reason'},
					{'data': 'Weed Date'},
					{'data': 'Item Cate'},
					{'data': 'SMD'},
					{'data': 'Branch',},
					{'data': 'Location',},

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