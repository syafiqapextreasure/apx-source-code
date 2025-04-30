$(document).ready(function() {
	$('#statusType').multiselect({
   		maxHeight: 200
	});
	
	////// table setup
	$('#listingreportTable').DataTable({
		destroy: true,
		searching: false,
		bLengthChange: false,
		autoWidth: false,
		responsive: true,
	});
	
	///////////////////// input-startDate set Current Date  //////////////////
	$('#input-startDate').datepicker({
		format: "dd/mm/yyyy",
		todayBtn: true,
		autoclose: true,
		todayHighlight: true,
	});
	
	///////////////////// input-endDate set Current Date  ////////////////////
	var today = new Date();  
	$('#input-endDate').val(moment(today).format("DD/MM/YYYY"));
	$('#input-endDate').datepicker({
		format: "dd/mm/yyyy",
		todayBtn: true,
		autoclose: true,
		todayHighlight: true,
	});
	
	//////////////////////////////////////////////////////////////////////////
	////////////////////////btn Reterive Click ///////////////////////////////
	//////////////////////////////////////////////////////////////////////////
	$('#Reterive').click(function(){
		var hasStatus = 0
		if($("#statusType").val().length > 0){
			hasStatus = 1
		} else if($("#statusType").val().length == 0){
			hasStatus = 0
		}
		
		var startSentDate = moment($("#input-startDate").val(), 'DD/MM/YYYY').format("YYYYMMDD");
		var endSentDate = moment($("#input-endDate").val(), 'DD/MM/YYYY').format("YYYYMMDD");
		var sDate, eDate;
		
		var startSentDate = $("#input-startDate").val();
		if(startSentDate == ''){
			startSentDate = '';
			sDate = "";
		}else{
			startSentDate = moment($("#input-startDate").val(), 'DD/MM/YYYY').format("YYYYMMDD");
			sDate = $("#input-startDate").val();
		}
		
		var endSentDate = $("#input-endDate").val();
		if(endSentDate == ''){
			endSentDate = '';
		}else{
			endSentDate = moment($("#input-endDate").val(), 'DD/MM/YYYY').format("YYYYMMDD");
			eDate =  $("#input-endDate").val();
		}
		
		/////result display
		var t = $('#listingreportTable').DataTable({
			dom: 'Bfrtip',
			info:     false,
			buttons: [
						{
					  		extend: 'excelHtml5',
					        filename: 'WILMU_Payment Detail List',
					        title: 'WILMU Payment Detail List ' + sDate +" until "+ eDate,
					                
					 	},
						{
					    	text: '<i class="fa fa-file-pdf-o"></i> PDF',
							extend: 'pdfHtml5',
							filename: 'WILMU_Payment Detail List',
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
								var rowCount = doc.content[1].table.body.length;
						           for (i = 0; i < rowCount; i++) {
										doc.content[1].table.body[i][0].alignment = 'left';
										doc.content[1].table.body[i][1].alignment = 'left';
										doc.content[1].table.body[i][2].alignment = 'left';
										doc.content[1].table.body[i][3].alignment = 'left';
										doc.content[1].table.body[i][4].alignment = 'left';
										doc.content[1].table.body[i][5].alignment = 'left';
						           };
											//Remove the title created by datatTables  
											doc.content.splice(0, 1, {
						                        text: [
						                                   { text: 'WILMU Payment Detail List from '+sDate + " until " +eDate+ '\n',bold:true,fontSize:13,alignment: 'center'}						                        ],
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
		                	filename: 'WILMU_Payment Detail List',
							charset: "utf-8",
							bom : "true",
							customize: function(doc){
										var split_csv = doc.split("\n");
										$.each(split_csv.slice(1), function (index, csv_row) {
											var csv_cell_array = csv_row.split('","');
											csv_cell_array[1].replace("", (index+1));
										});
										return "WILMU Payment Detail List from "+sDate + " until " + eDate +"\n\n" + doc;
									 }
             			},
            ],
			destroy: true,
			searching: true,
			bLengthChange: false,
			autoWidth: false,
			responsive: true,
			processing: true,
			paging: true,
			language: {
             loadingRecords : "Please wait - loading...",
             processing :     "Please wait formatting in progress..."
        	},
			deferRender: true,
			footer: true,
			ajax: {
		    	url: "getPaymentDetailList",
		        data : {
						startSentDate : startSentDate,
						endSentDate : endSentDate,
						hasStatus : hasStatus,
						statusType : JSON.stringify($("#statusType").val()),
					   },
		        type: "GET",
		        dataSrc: function (json) {
		            var return_data = new Array();
					var i = 0
					$.each( json, function( key, value ) {
						return_data.push({
			                'No': (i+1),
			                'Room ID' : value.roomId,
							'Room Title' : value.title,
							'Room Description' : value.descr,
							'Total Amount' : value.totalAmount,
							'Status' : value.status,
			            })
					i++
					});
		            return return_data;
		          },
		     },
			columns: [
				{ "data": "No" },
				{ "data": "Room ID" },
				{ "data": "Room Title" },
				{ "data": "Room Description" },
				{ "data": "Total Amount" },
				{ "data": "Status" }
			  ]
    		});
	})
});