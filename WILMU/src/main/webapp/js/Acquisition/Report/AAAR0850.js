$(document).ready(function() {
	
	
	jQuery.extend( jQuery.fn.dataTableExt.oSort, {
	    "date-uk-pre": function ( a ) {
	        if (a == null || a == "") {
	            return 0;
	        }
	        var ukDatea = a.split('/');
	        return (ukDatea[2] + ukDatea[1] + ukDatea[0]) * 1;
	    },
	 
	    "date-uk-asc": function ( a, b ) {
	        return ((a < b) ? -1 : ((a > b) ? 1 : 0));
	    },
	 
	    "date-uk-desc": function ( a, b ) {
	        return ((a < b) ? 1 : ((a > b) ? -1 : 0));
	    }
	} );
	
	
	///////////////////// input-startDate set Current Date  //////////////////
	var today = new Date();  
	$('#input-startDate').datepicker({
		//startDate : today,
		//dateFormat : "dd/mm/yyyy",
		format: "dd/mm/yyyy",
		todayBtn: true,
		autoclose: true,
		todayHighlight: true,	
		endDate: new Date(),
		}).on('changeDate', function (selected) {
                    var minDate = new Date(selected.date);
                    minDate.setDate(minDate.getDate() + 1);
                    $('#input-endDate').datepicker('setStartDate', minDate);
	});
	
	///////////////////// input-endDate set Current Date  ////////////////////
	$('#input-endDate').val(moment(today).format("DD/MM/YYYY"));
	$('#input-endDate').datepicker({
		format: "dd/mm/yyyy",
		todayBtn: true,
		autoclose: true,
		todayHighlight: true,
     	endDate: new Date(),
		 }).on('changeDate', function (selected) {
                    var minDate = new Date(selected.date);
                    minDate.setDate(minDate.getDate() - 1);
                    $('#input-startDate').datepicker('setEndDate', minDate);
    });


	//////table setup
	$('#acqtabl').DataTable( {
		destroy: true,
		searching: false,
		bLengthChange: false,
		autoWidth: false,
		responsive: true,
	});
	
	$('#subj').multiselect({
    	allSelectedText: 'All',
   		maxHeight: 200,
   		includeSelectAllOption: true,
		numberDisplayed: 1,
	});
	
	/*$('#actCode').change(function() {
		if($("#actCode").val() != ""){
			$('#Reterive').prop('disabled', false);
		}else{
			$('#Reterive').prop('disabled', true);
		}
	});*/
	
	
	//////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////
	
	//////////////////////////////////////////////////////////////////////////
	////////////////////////btn Reterive Click ///////////////////////////////
	//////////////////////////////////////////////////////////////////////////
	$('#Reterive').click(function(){

		
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
		
		var subj = $("#subj").val(); 
		if(subj != ""){
		}else{
			subj = 0;
		}
		

		
		var t = $('#acqtabl').DataTable( {
			dom: 'Bfrtip',
            buttons: [
						{
					  		extend: 'excelHtml5',
					        filename: 'WILMU_Subj',
					        title: 'Order Maintenance date range ' +sDate +eDate,
					                
					 	},
						{
					    	text: '<i class="fa fa-file-pdf-o"></i> PDF',
							extend: 'pdfHtml5',
							filename: 'WILMU_Subj',
							charset: "utf-8",
							bom : "true",
							orientation: 'portrait', //landscape
							pageSize: 'A4', //A3 , A5 , A6 , legal , letter
							footer: true,
							exportOptions: {
								search: 'applied',
								order: 'applied'
							},
							customize: function (doc) {
								//doc.defaultStyle.font = 'Arial';
								doc.styles.tableHeader.alignment = 'left';
								
								var rowCount = doc.content[1].table.body.length;
						           for (i = 0; i < rowCount; i++) {
										doc.content[1].table.body[i][0].alignment = 'left';
										doc.content[1].table.body[i][1].alignment = 'left';
										doc.content[1].table.body[i][2].alignment = 'left';
										doc.content[1].table.body[i][2].alignment = 'right';
						           };
								
							 	doc.content[1].table.widths = [ '7%', '14%', '55%', '20%']; 
							

											//Remove the title created by datatTables  
											doc.content.splice(0, 1, {
						                        text: [
						                                   { text: 'Order Maintenance date range' +sDate +eDate+ '\n',bold:true,fontSize:13,alignment: 'center'}						                        ],
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
		                	filename: 'WILMU_Subj',
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
										
										return "Order Maintenance date range" +sDate +eDate +"\n\n" + doc;
									 }
             			},
            ],
			destroy: true,
			searching: false,
			bLengthChange: false,
			autoWidth: false,
			responsive: true,
			processing: true,
			language: {
             loadingRecords : "Please wait - loading...",
             processing :     "Please wait formatting in progress..."
        	},
		    ajax: {
		    	url: "ResultOMReport",
				data : {startSentDate : startSentDate, endSentDate : endSentDate,  
						subj : JSON.stringify(subj),
						},
		        type: "GET",
				/*start_time: new Date().getTime(),
				    complete: function(data) {
				        alert('This request took '+(new Date().getTime() - this.start_time)+' ms');
				},*/
		        dataSrc: function (json) {
		            var return_data = new Array();
		            
		            for(var i=0;i< json.length; i++){

		              return_data.push({
		                'No': (i+1),
		                'Code' : json[i].Column1,
		                'Description' : json[i].Column2,
		                'Total' : json[i].Column3,
		            })
		            }
					
		            return return_data;
		          },
		     },//This is the end of the AJAX object from the example above
		     	columns    : [
					{'data': 'No'},
					{'data': 'Code'},
					{'data': 'Description'},
					{'data': 'Total'},
				],
    	});
	});
	
});
	