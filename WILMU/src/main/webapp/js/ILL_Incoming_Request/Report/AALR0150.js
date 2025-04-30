$(document).ready(function() {
	////// table setup
	$('#reportTable').DataTable( {
		destroy: true,
		searching: false,
		bLengthChange: false,
		autoWidth: false,
		responsive: true,
	});
	
	$('#requestName').prop('checked', true);
	$('#input-startDate, #input-endDate').attr('disabled', 'disabled');
	
	///////////////////// input-startDate set Current Date  //////////////////
	$('#input-startDate').datepicker({
		format: "dd/mm/yyyy",
		todayBtn: true,
		autoclose: true,
		todayHighlight: true,
	});
	
	///////////////////// input-endDate set Current Date  ////////////////////
	$('#input-endDate').datepicker({
		format: "dd/mm/yyyy",
		todayBtn: true,
		autoclose: true,
		todayHighlight: true,
	});

	////////////// radio button on click  ////////////////////
	$('input[type=radio][name=selectOption]').click(function() {
		var chkBoxSearchCateria = $("input[name='selectOption']:checked").val();
		switch(chkBoxSearchCateria) {
		  case 'requestName':
			$('#requestorName-text').removeAttr('disabled'); // ENABLE REQUESTOR NAME
			$('#input-startDate, #input-endDate').val('').attr('disabled', 'disabled'); // DISABLE DATE AND CLEAR THE DATE INPUT
			$('#requestorName-text').prop('selectedIndex',0); // TO RESET SELEC OPTION
		    break;
		  case 'requestDate':
			$('#input-startDate, #input-endDate').removeAttr('disabled'); // ENABLE DATE
			$('#requestorName-text').attr('disabled', 'disabled');  // DISABLE REQUESTOR NAME
		    break;
		 case undefined:
		    break;
		} 
	});
	
	
	//////////////////////////////////////////////////////////////////////////
	////////////////////////btn Reterive Click ///////////////////////////////
	//////////////////////////////////////////////////////////////////////////
	$('#Reterive').click(function(){
		var selectOption = $('input[type=radio][name=selectOption]:checked').val();
		var requestorName = $('#requestorName-text').val().toUpperCase();
		var startSentDate = moment($("#input-startDate").val(), 'DD/MM/YYYY').format("YYYYMMDD");
		var endSentDate = moment($("#input-endDate").val(), 'DD/MM/YYYY').format("YYYYMMDD");
		var sDate = '';
		var eDate = '';
		
		var startSentDate = $("#input-startDate").val();
		if(startSentDate == ''){
			startSentDate = '';
			sDate = ''
		}else{
			startSentDate = moment($("#input-startDate").val(), 'DD/MM/YYYY').format("YYYYMMDD");
			sDate = $("#input-startDate").val();
		}
		
		var endSentDate = $("#input-endDate").val();
		if(endSentDate == ''){
			endSentDate = '';
			eDate = ''
		}else{
			endSentDate = moment($("#input-endDate").val(), 'DD/MM/YYYY').format("YYYYMMDD");
			eDate = $("#input-endDate").val();
		}
		
		var title = ''
		if(selectOption === 'requestDate'){
			title = 'WILMU Incoming Request Report from '+ sDate +" until "+ eDate
		}
		if(selectOption === 'requestName'){
			title = 'WILMU Incoming Request Report '+requestorName
		}
		
		// result display
		$('#reportTable').DataTable({
			dom: 'Bfrtip',
			info:     false,
			buttons: [
						{
					  		extend: 'excelHtml5',
					        filename: 'WILMU_Incoming Request Report',
					        title: title,
					 	},
						{
					    	text: '<i class="fa fa-file-pdf-o"></i> PDF',
							extend: 'pdfHtml5',
							filename: 'WILMU_Incoming Request Report',
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
												doc.content[1].table.body[i][6].alignment = 'left';
												doc.content[1].table.body[i][7].alignment = 'left';
												doc.content[1].table.body[i][8].alignment = 'left';
												doc.content[1].table.body[i][9].alignment = 'center';
												doc.content[1].table.body[i][10].alignment = 'center';
												doc.content[1].table.body[i][11].alignment = 'center';
												doc.content[1].table.body[i][12].alignment = 'center';
												doc.content[1].table.body[i][13].alignment = 'left';
								           };
											//Remove the title created by datatTables  
											doc.content.splice(0, 1, {
						                        text: [
						                                   { text: title + '\n',bold:true,fontSize:13,alignment: 'center'}			                        ],
	 					                        margin: [0, 0, 0, 20],
						                    });
											doc.pageMargins = [ 20, 20, 20, 20 ]; //left, top, right, bottom
					
											doc['footer']=(function(page, pages) {
												return {
													columns: [
														{
															alignment: 'left',
															text: ['\t\t\t\t  ', moment(new Date()).format("DD/MM/YYYY") +'\t' +moment(new Date()).format("hh:mm:ss A")]
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
		                	filename: 'WILMU_Incoming Request Report',
							charset: "utf-8",
							bom : "true",
							customize: function(doc){
										var split_csv = doc.split("\n");
										$.each(split_csv.slice(1), function (index, csv_row) {
											var csv_cell_array = csv_row.split('","');
											csv_cell_array[1].replace("", (index+1));
										});
										return title +"\n\n" + doc;
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
		    	url: "getIncomingRequestIll",
		        data : {selectOption: selectOption,
						requestorName: requestorName,
						startSentDate: startSentDate,
						endSentDate: endSentDate,
						},
		        type: "GET",
		        dataSrc: function (json) {
		            var return_data = new Array();
					var i = 1
					$.each( json, function( key, value ) {
						return_data.push({
							'No' : i,
							'Request No' : value.requestNo,
							'Control No' : value.controlNo,
							'Title' : value.title,
							'Accession No' : value.accessionNo,
							'Dt Request' : value.dateRequest,
							'Dt Sent' : value.dateSent,
							'Dt Billed' : value.dateBilled,
							'Dt Due' : value.dateDue,
							'Service' : value.serviceAmount,
							'Handling' : value.handlingAmount,
							'Discount' : value.discountAmount,
							'Total' : value.totalAmount,
							'Status' : value.status,
			            })
					i++
					});
		            return return_data;
		          },
		     },
			columns: [
				{ "data": "No" },
				{ "data": "Request No" },
				{ "data": "Control No" },
				{ "data": "Title" },
				{ "data": "Accession No" },
				{ "data": "Dt Request" },
			    { "data": "Dt Sent" },
				{ "data": "Dt Billed" },
				{ "data": "Dt Due" },
				{ "data": "Service" },
				{ "data": "Handling" },
			    { "data": "Discount" },
				{ "data": "Total" },
				{ "data": "Status" },
			  ],
			footerCallback: function ( row, data, start, end, display ) {
		            var api = this.api()

		            // total Service header amount over all pages
		            var totalService = api.column(9).data().reduce(function (a, b){
		                    return Number(a) + Number(b);
		                }, 0 );

		            // total Handling header amount over all pages
		            var totalHandling = api.column(10).data().reduce(function (a, b){
		                    return Number(a) + Number(b);
		                }, 0 );

		            // total Discount header amount over all pages
		            var totalDiscount = api.column(11).data().reduce(function (a, b){
		                    return Number(a) + Number(b);
		                }, 0 );

		            // total Sum amount header over all pages
		            var totalSum = api.column(12).data().reduce(function (a, b){
		                    return Number(a) + Number(b);
		                }, 0 );
					
					$("#totalService, #totalHandling, #totalDiscount, #totalSum").empty();
					
					$("#totalService").append(totalService.toFixed(2));
					$("#totalHandling").append(totalHandling.toFixed(2));
					$("#totalDiscount").append(totalDiscount.toFixed(2));
					$("#totalSum").append(totalSum.toFixed(2));
		        }
    		});
	})
});