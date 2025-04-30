$(document).ready(function() {
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
	
	$("#lblPatronID").attr('maxLength', 12);	
	$("#lblPatronID, #input-startDate, #input-endDate, #transactCode, #Reterive").attr('disabled', 'disabled');	
	$('.patronid').prop('disabled', true);  
	
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
				});
			}
		});
	});
	
	$("#lblPatronID").on('input', function() {
		checkConditionPartForInputFormatting()
	})
	
	$('.lblName').on('DOMSubtreeModified', function(){
	  checkConditionPartForInputFormatting()
	});
	
	$("#input-startDate, #input-endDate, #transactCode").on('change', function() {
		checkConditionPartForInputFormatting()
	})
	
	$('input[type=checkbox][name=chkBoxSearchCateria]').click(function() {
		checkConditionPartForInputFormatting()
		checkConditionPartForDisableInput()
	})
	
	function checkConditionPartForInputFormatting(){
		if($("#patronId").prop("checked") == true && $("#dateRange").prop("checked") != true && $("#trxcCode").prop("checked") != true){ // patronId checkbox IS CHECKED ONLY
			$("#lblPatronID").removeAttr('disabled');
			$('.patronid').prop('disabled', false);  
			if($("#patronId").prop("checked") == true && $("#lblPatronID").val().length > 0){
				$('#Reterive').removeAttr('disabled');
			}else {
				$('#Reterive').attr('disabled', 'disabled');
			}
		}
		if($("#dateRange").prop("checked") == true && $("#patronId").prop("checked") != true && $("#trxcCode").prop("checked") != true){ // date checkbox IS CHECKED ONLY
			$("#input-startDate, #input-endDate").removeAttr('disabled');
			if($("#dateRange").prop("checked") == true && ($("#input-startDate").val().length > 0 || $("#input-endDate").val().length > 0)){
				$('#Reterive').removeAttr('disabled');
			}else {
				$('#Reterive').attr('disabled', 'disabled');
			}
		}
		if($("#trxcCode").prop("checked") == true && $("#dateRange").prop("checked") != true && $("#patronId").prop("checked") != true){ // transactionCode checkbox IS CHECKED ONLY
			$("#transactCode").removeAttr('disabled');
			if($("#trxcCode").prop("checked") == true && $("#transactCode").val().length > 0){
				$('#Reterive').removeAttr('disabled');
			}else {
				$('#Reterive').attr('disabled', 'disabled');
			}
		}
		if($("#patronId").prop("checked") == true && $("#dateRange").prop("checked") == true && $("#trxcCode").prop("checked") != true){ // patronId && date IS CHECKED ONLY
			$("#lblPatronID, #input-startDate, #input-endDate").removeAttr('disabled');
			$('.patronid').prop('disabled', false);
			if($("#patronId").prop("checked") == true && $("#lblPatronID").val().length > 0 && $("#dateRange").prop("checked") == true && ($("#input-startDate").val().length > 0 || $("#input-endDate").val().length > 0)){
				$('#Reterive').removeAttr('disabled');
			}else {
				$('#Reterive').attr('disabled', 'disabled');
			}
		}
		if($("#patronId").prop("checked") == true && $("#trxcCode").prop("checked") == true && $("#dateRange").prop("checked") != true){ // patronId && transactCode IS CHECKED ONLY
			$("#lblPatronID, #transactCode").removeAttr('disabled');
			$('.patronid').prop('disabled', false);
			if($("#patronId").prop("checked") == true && $("#lblPatronID").val().length > 0 && $("#trxcCode").prop("checked") == true && $("#transactCode").val().length > 0){
				$('#Reterive').removeAttr('disabled');
			}else {
				$('#Reterive').attr('disabled', 'disabled');
			}
		}
		if($("#patronId").prop("checked") == true && $("#dateRange").prop("checked") == true && $("#trxcCode").prop("checked") == true){ // patronId && date && transactCode IS CHECKED ONLY
			$("#lblPatronID, #input-startDate, #input-endDate, #transactCode").removeAttr('disabled');
			$('.patronid').prop('disabled', false);
			if($("#patronId").prop("checked") == true && $("#lblPatronID").val().length > 0 && $("#trxcCode").prop("checked") == true && $("#transactCode").val().length > 0 && $("#dateRange").prop("checked") == true && ($("#input-startDate").val().length > 0 || $("#input-endDate").val().length > 0)){
				$('#Reterive').removeAttr('disabled');
			}else {
				$('#Reterive').attr('disabled', 'disabled');
			}
		}
		if($("#trxcCode").prop("checked") == true && $("#dateRange").prop("checked") == true && $("#patronId").prop("checked") != true){ // date && transactCode IS CHECKED ONLY
			$("#input-startDate, #input-endDate, #transactCode").removeAttr('disabled');
			if($("#trxcCode").prop("checked") == true && $("#transactCode").val().length > 0 && $("#dateRange").prop("checked") == true && ($("#input-startDate").val().length > 0 || $("#input-endDate").val().length > 0)){
				$('#Reterive').removeAttr('disabled');
			}else {
				$('#Reterive').attr('disabled', 'disabled');
			}
		}
		if($("#patronId").prop("checked") != true && $("#dateRange").prop("checked") != true && $("#trxcCode").prop("checked") != true){ // patronId && date && transactCode IS NOT CHECKED ONLY
			$("#Reterive").attr('disabled', 'disabled');
		}
	}

	function checkConditionPartForDisableInput(){
		if($("#patronId").prop("checked") != true){
			$("#lblPatronID").attr('disabled', 'disabled');
			$('.patronid').prop('disabled', true);
		}
		if($("#dateRange").prop("checked") != true){
			$("#input-startDate, #input-endDate").attr('disabled', 'disabled');
		}
		if($("#trxcCode").prop("checked") != true){
			$("#transactCode").attr('disabled', 'disabled');
		}
	}
 
	$(".patronid").removeAttr('href'); 
	/// on click searc patron 	
	$('.patronid').on('click',function(){
		var url = "Modal_PatrSearch";
		$.get(url,function(data){
			$("#Modal_PatrSearchContent").html(data);
		});
	});

	////// table setup
	$('#reportTable').DataTable( {
		destroy: true,
		searching: false,
		bLengthChange: false,
		autoWidth: false,
		responsive: true,
	});
	
	// when reterive
	$('#Reterive').click(function(){
		var sDate, eDate;
		var startSentDate = $("#input-startDate").val();
		if(startSentDate == ''){
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
		
		var patronIdCheck = '';
		var patronIdValue = '';
		var dateRangeCheck = '';
		var trxcCheck = '';
		var trxcCheckValue = '';
		var paidTransactCheck = '';
		
		if($("#patronId").prop("checked") == true){
			patronIdCheck = 'Y'
			patronIdValue = $("#lblPatronID").val()
		} else {
			patronIdCheck = 'N'
		}
		if($("#dateRange").prop("checked") == true){
			dateRangeCheck= 'Y'
		} else {
			dateRangeCheck = 'N'
		}
		if($("#trxcCode").prop("checked") == true){
			trxcCheck = 'Y'
			trxcCheckValue = $("#transactCode").val()
		} else {
			trxcCheck = 'N'
		}
		if($("#paidTransaction").prop("checked") == true){
			paidTransactCheck = 'Y'
		} else {
			paidTransactCheck = 'N'
		}
		
		/////result display
		var t = $('#reportTable').DataTable({
			dom: 'Bfrtip',
			info:     false,
			buttons: [
						{
					  		extend: 'excelHtml5',
					        filename: 'WILMU_Receipting Transaction Listing',
					        title: 'Receipting Transaction Listing from ' + sDate +" until "+ eDate,
					                
					 	},
						{
					    	text: '<i class="fa fa-file-pdf-o"></i> PDF',
							extend: 'pdfHtml5',
							filename: 'WILMU_Receipting Transaction Listing',
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
									    doc.content[1].table.body[i][0].alignment = 'center';
										doc.content[1].table.body[i][1].alignment = 'center';
										doc.content[1].table.body[i][2].alignment = 'center';
										doc.content[1].table.body[i][3].alignment = 'center';
										doc.content[1].table.body[i][4].alignment = 'center';
										doc.content[1].table.body[i][5].alignment = 'left';
										doc.content[1].table.body[i][6].alignment = 'left';
										doc.content[1].table.body[i][7].alignment = 'right';
										doc.content[1].table.body[i][8].alignment = 'right';
										doc.content[1].table.body[i][9].alignment = 'right';
						               	doc.content[1].table.body[i][10].alignment = 'right';
						           };
								doc.content[1].table.widths = [ '5%', '9%', '9%', '10%', '10%', '10%', '10%', '10%', '10%', '9%', '9%'];
							
							doc.content.push( {
						                        alignment: 'right',
												bold:true,
								                   text: [
								                         	$('.divtotal').text(), 
								                        ]
											 });

											//Remove the title created by datatTables  
											doc.content.splice(0, 1, {
						                        text: [
						                                   { text: 'Receipting Transaction Listing from '+sDate + " until " +eDate+ '\n',bold:true,fontSize:13,alignment: 'center'}						                        ],
						                        margin: [0, 0, 0, 12],
						                    });
					
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
		                	filename: 'WILMU_Receipting Transaction Listing',
							charset: "utf-8",
							bom : "true",
							customize: function(doc){
										var split_csv = doc.split("\n");
										$.each(split_csv.slice(1), function (index, csv_row) {
											var csv_cell_array = csv_row.split('","');
											csv_cell_array[1].replace("", (index+1));
										});
										return "Receipting Transaction Listing from "+sDate + " until " + eDate +"\n\n" + doc;
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
		    	url: "getReceiptingTransactionList",
		        data : {patronIdCheck : patronIdCheck,
						patronIdValue : patronIdValue,
						trxcCheck : trxcCheck,
						trxcCheckValue : trxcCheckValue,
						paidTransactCheck : paidTransactCheck,
						dateRangeCheck : dateRangeCheck,
						startSentDate : startSentDate,
						endSentDate : endSentDate},
		        type: "GET",
		        dataSrc: function (json) {
		            var return_data = new Array();
					var i = 0
					$.each( json, function( key, value ) {
						return_data.push({
			                'No': (i+1),
			                'Trx No' : value.trxNo,
							'Date' : value.date,
							'Code' : value.code,
							'Desc' : value.desc,
			                'Patron ID' : value.patronId,
			                'Reference' : value.reference,
			                'Amount' : value.amount,
							'Paid Amount' : value.paidAmount,
							'Override' : value.overRide,
							'Balance' : value.balance,
			            })
					i++
					});
		            return return_data;
		          },
		     },
			columns: [
				{ "data": "No" },
				{ "data": "Trx No" },
				{ "data": "Date" },
				{ "data": "Code" },
				{ "data": "Desc" },
				{ "data": "Patron ID" },
				{ "data": "Reference" },
				{ "data": "Amount" },
			    { "data": "Paid Amount" },
				{ "data": "Override" },
			    { "data": "Balance" }
			  ],
			footerCallback: function ( row, data, start, end, display ) {
		            var api = this.api()

					// Total Amount over all pages
		            var totalAmount = api.column(7).data().reduce(function (a, b){
		                    return Number(a) + Number(b);
		                }, 0 );

					// Total Paid Amount over all pages
		            var totalPaid = api.column(8).data().reduce(function (a, b){
		                    return Number(a) + Number(b);
		                }, 0 );

					// Total Override over all pages
		            var totalOverride = api.column(9).data().reduce(function (a, b){
		                    return Number(a) + Number(b);
		                }, 0 );

		            // Total Balance over all pages
		            var totalBalance = api.column(10).data().reduce(function (a, b){
		                    return Number(a) + Number(b);
		                }, 0 );
					$("#allTotalAmount").text(totalAmount.toFixed(2));
					$("#allTotalPaidAmount").text(totalPaid.toFixed(2));
					$("#allTotalOverrideAmount").text(totalOverride.toFixed(2));
					$("#allTotalBalanceAmount").text(totalBalance.toFixed(2));
		        }
    		});
	});
});