$(document).ready(function() {
	
	var today = new Date(); 
	
	
		//////table setup
	$('#reportTableSummary, #reportTableFull').DataTable( {
		destroy: true,
		searching: false,
		bLengthChange: false,
		autoWidth: false,
		responsive: true,
	});
	
	
	/////////////////--- EDITOR ------/////////
	
	////1) Hide selection criteria
	$(".branchDiv, .rangeDiv, .acctDiv").hide();
	$(".deptDiv, .invstatDiv, .itemstatDiv,  .claimstatDiv, .printerStat, .recordselectionDiv").hide();
	
	
	////2) Change Label
	///Date Label
	$(".datelabel").html("<b>Create Order Date Range</b>");
	
	
	$("#lblPatronID").prop("disabled", false);
	$('#Reterive').prop('disabled', true);
	
	
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
	
	
	//////////////////////////////////////////////////////////////////////////
	////////////////////////btn Reterive Click ///////////////////////////////
	//////////////////////////////////////////////////////////////////////////
	$('#Reterive').click(function(){
		$('#reportTableSummary, #reportTableFull').dataTable().fnClearTable();
		
		var currencyFormat = $("#setupCurrency").val();
		
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
		
		patronID = $("#lblPatronID").val();
		
		
		$('#reportTableFull').DataTable( {
			dom: 'Bfrtip',
            buttons: [
						{
					  		extend: 'excelHtml5',
					        filename: 'WILMU_AcquisitionStatusReportOfOrderByVendor',
					        title: 'New Order Entry By Officer ' +sDate +eDate,          
					 	},
						{
					    	text: '<i class="fa fa-file-pdf-o"></i> PDF',
							extend: 'pdfHtml5',
							filename: 'WILMU_AcquisitionStatusReportOfOrderByVendor',
							charset: "utf-8",
							bom : "true",
							orientation: 'landscape', //portrait
							pageSize: 'A4', //A3 , A5 , A6 , legal , letter
							footer: true,
							customize: function (doc) {
								
								doc.styles.tableHeader.alignment = 'left';
								
								 var rowCount = doc.content[1].table.body.length;
						          /* for (i = 0; i < rowCount; i++) {
										doc.content[1].table.body[i][5].alignment = 'right';
										doc.content[1].table.body[i][8].alignment = 'right';
						               //	doc.content[1].table.body[i][10].alignment = 'right';
						           };*/
								
							 	//doc.content[1].table.widths = [ '3%', '6%', '12%', '6%', '12%', '8%', '8%', '12%', '12%', '8%', '6%', '7%'];

											//Remove the title created by datatTables  
											doc.content.splice(0, 1, {
						                        text: [
						                                   { text: 'New Order Entry By Officer '+sDate +eDate+ '\n',bold:true,fontSize:13,alignment: 'center'}						                        ],
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
		                	filename: 'WILMU_AcquisitionStatusReportOfOrderByVendor',
							charset: "utf-8",
							bom : "true",
							customize: function(doc){
										
										var split_csv = doc.split("\n");
										//alert(split_csv)
										$.each(split_csv.slice(1), function (index, csv_row) {
											var csv_cell_array = csv_row.split('","');
											csv_cell_array[1].replace("", (index+1));
										});
										
										return "Acquisition Status Report Of Order By Vendor "+sDate + eDate +"\n\n" + doc;
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
		    	url: "ResultNewOrderEntryByOfficer",
		        data : {startSentDate : startSentDate, endSentDate : endSentDate, 
						patronID : patronID
						},
		        type: "GET",
		        dataSrc: function (json) {
		            var return_data = new Array();
		            
		            for(var i=0;i< json.length; i++){

		              return_data.push({
		                'No': (i+1),
		                'Date' : json[i].Column1,
						'Order No' : json[i].Column2,
						'Reference No' : json[i].Column3,
		                'Control No' : json[i].Column4,
		                'Title' : json[i].Column5,
		                'Status' : json[i].Column6
		            })
		            }
					
		            return return_data;
		          },
		     },//This is the end of the AJAX object from the example above
		     	columns    : [
					{'data': 'No'},
					{'data': 'Date'},
					{'data': 'Order No'},
					{'data': 'Reference No'},
					{'data': 'Control No'},		
					{'data': 'Title'},
					{'data': 'Status'},
				],
    	});
		
	});
	
});