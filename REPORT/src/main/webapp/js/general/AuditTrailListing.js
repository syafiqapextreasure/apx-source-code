$(document).ready(function() {
	
	//////////////////////////////////////////////////////////////////////////
	//////////////////////////setup page//////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////
	
	$(".datelabel").html("<b>Charge Date Range</b>");
	
	$('#Reterive').prop('disabled', true);
	
	$('#smd, #branch, #icat').multiselect({
    	allSelectedText: 'All',
   		maxHeight: 200,
   		includeSelectAllOption: true
	});
	
	$("#smd, #branch, #icat").multiselect("disable");
	
	///////////////////// input-startDate set Current Date  //////////////////
	var today = new Date();  
	$('#input-startDate').datepicker({
		//startDate : today,
		format: "dd/mm/yyyy",
		todayBtn: true,
		autoclose: true,
		todayHighlight: true,
	});
	
	 $("#cirFreq").prop('min',1);
	 $("#cirFreq").val("10");
	
	///////////////////// input-endDate set Current Date  ////////////////////
	
	$('#input-endDate').val(moment(today).format("DD/MM/YYYY"));
	$('#input-endDate').datepicker({
		format: "dd/mm/yyyy",
		todayBtn: true,
		autoclose: true,
		todayHighlight: true,
	});
	
	
	//////////////cirFreq key in number only//////////////////////////////////////
	$("#cirFreq").on("keypress keyup blur",function (event) {    
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
			  	$("#smd, #icat").multiselect("disable");
				$('#Reterive').prop('disabled', true);
					$('#branch').change(function() {
						if($("#branch").val() != "" && $("#cirFreq").val().length >=1){
							$('#Reterive').prop('disabled', false);
						}else{
							$('#Reterive').prop('disabled', true);
						}
					 });
		      break;
		    case 'itemcate':
				$('#icat').multiselect("enable");
			  	$("#smd, #branch").multiselect("disable");
				$('#Reterive').prop('disabled', true);
					$('#icat').change(function() {
						if($("#icat").val() != ""  && $("#cirFreq").val().length >=1){
							$('#Reterive').prop('disabled', false);
						}else{
							$('#Reterive').prop('disabled', true);
						}
					});
		      break;
			case 'smd':
				$('#smd').multiselect("enable");
			  	$("#branch, #icat").multiselect("disable");
				$('#Reterive').prop('disabled', true);
				$('#smd').change(function() {
					if($("#smd").val() != ""  && $("#cirFreq").val().length >=1){
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
		
		var freq = $("#cirFreq").val();


		var chkBoxSearchCateria = $("input[name='chkBoxSearchCateria']:checked").val();
		var searchCateriaval;
		var searchCateriatext;
		
		switch(chkBoxSearchCateria) {
		  case 'branch':
			searchCateriaval = $('#branch').val();
			searchCateriatext = $("#branch option:selected").text();
		    break;
		  case 'smd':
			searchCateriaval = $('#smd').val();
			searchCateriatext = $("#smd option:selected").text();
		    break;
		  case 'itemcate':
			searchCateriaval = $('#icat').val();
			searchCateriatext = $("#icat option:selected").text();
		    break;
		 case undefined:
			searchCateriaval = "";
		    break;
		}  
		
		
		/////result display
		var t = $('#reportTable').DataTable( {
			dom: 'Bfrtip',
            buttons: [
                /*'excel', 'pdf', 'print'*/
						{
					  		extend: 'excelHtml5',
					        filename: 'WILMU_AuditTrailListing',
					        title: 'Statistic Date From '+startSentDate +" to "
									+endSentDate +", " +searchCateriatext,

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
							filename: 'WILMU_AuditTrailListing',
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
								
								 var rowCount = doc.content[1].table.body.length;
						           for (i = 0; i < rowCount; i++) {
										doc.content[1].table.body[i][2].alignment = 'right';
						               	doc.content[1].table.body[i][3].alignment = 'right';
										doc.content[1].table.body[i][4].alignment = 'right';
						           };
								
								//doc.content[1].table.widths = Array(doc.content[1].table.body[0].length + 1).join('*').split('');
							//	doc.content[1].table.widths = [30,100,50,50,50];
							//doc.content[1].table.body[0][3].alignment = 'right';
							 //doc.content[1].table.widths = [ '8%', '55%', '12%', '12%', '12%']; 
							 //doc.content[1].table.body[0][5].alignment = 'right';
						
						


											//Remove the title created by datatTables
											doc.content.splice(0, 1, {
						                        text: [
						                                   { text: 'Statistic Date From  '+startSentDate+' to' + endSentDate +', ' +searchCateriatext +'\n',bold:true,fontSize:13,alignment: 'center'},

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
		                	filename: 'WILMU_AuditTrailListing',
		                	title: 'Audit Trail Listing ',
							customize: function(doc){
										
										var split_csv = doc.split("\n");
										//alert(split_csv)
										$.each(split_csv.slice(1), function (index, csv_row) {
											var csv_cell_array = csv_row.split('","');
											csv_cell_array[1].replace("", (index+1));
										});
										///alert(csv_cell_array[1]);
										//alert(csv_cell_array[1]);
										
										return "Statistic Date From "+startSentDate + " to " +endSentDate + "," 
										+searchCateriatext +"\n\n" + doc;
									
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
		    	url: "ResultAuditTrailListing",
		        data : {startSentDate : startSentDate, endSentDate : endSentDate, 
						chkBoxSearchCateria : chkBoxSearchCateria, searchCateriaval : JSON.stringify(searchCateriaval)
						},
		        type: "GET",
		        dataSrc: function (json) {
		            var return_data = new Array();
		            
		            for(var i=0;i< json.length; i++){
			
					var callno = json[i].Column5;
					
					if(callno!=undefined){
						if(callno.charAt(0)=='*'){
							callno = callno.slice(1);
							callno = "<i>"+callno+"</i>";
						}	
					}
					/*if(callno.charAt(0)=='*'){
						callno = callno.slice(1);
						callno = "<i>"+callno+"</i>";
					}else{
						if(callno==undefined){
							callno = "";
						}
					}*/
					
					var title = json[i].Column6;
							
					if(title.charAt(0)=='*'){
						title = title.slice(1);
						title = "<i>"+title+"</i>";
						//title.css('font-style', 'italic');
					}else{
						//title.css('font-style', 'normal');
					}

		              return_data.push({
		                'No': (i+1),
		                'Date' : json[i].Column1,
		                'Time' : json[i].Column2,
		                'Activity Code' : json[i].Column3,
		                'Reference' : json[i].Column4,
						'Patron ID' : json[i].Column5,
						'Patron Name' : json[i].Column6,
						'Terminal ID' : json[i].Column7,
		            })
		            }
					
		            return return_data;
		          },
		     },//This is the end of the AJAX object from the example above
		     	columns    : [
					{'data': 'No'},
					{'data': 'Date'},
					{'data': 'Time'},
					{'data': 'Activity Code'},
					{'data': 'Reference'},
					{'data': 'Patron ID'},
					{'data': 'Patron Name'},
					{'data': 'Terminal ID'},					

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