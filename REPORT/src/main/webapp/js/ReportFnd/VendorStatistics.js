$(document).ready(function() {
	
	//////////////////////////////////////////////////////////////////////////
	//////////////////////////setup page//////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////
	
	/*$(".datelabel").html("<b>Vendor StatisticsDate Range</b>");
	$('#Reterive').prop('disabled', true);*/
	
	
	
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
	
	//////table setup
	$('#reportTable').DataTable( {
		destroy: true,
		searching: false,
		bLengthChange: false,
		autoWidth: false,
		responsive: true,
		paging:   false,
		bInfo : false,
		bSort : false,
	});
	
	$(".SER").hide();
	
	$('input[type=radio][name=chkBoxModule]').on('change', function() {
		
		$(".NumberOfClaimsSent, .NumberOfClaimsSentOrder, .NumberOfClaimsSentIssue").empty();
		$(".NumberOfCopiesCancelled, .NumberOfCopiesClaimed, .NumberOfCopiesOrdered").empty();
		$(".NumberOfCopiesReceived, .NumberOfTitleOrdered, .NumberOfTitleReceived").empty();
		$(".AmountOrdered, AmountInvoiced").empty();

		switch ($(this).val()) {
			case 'AC':
				$(".SER").hide();
				$(".ACQ").show();
				break;
			case 'SE':
				$(".SER").show();
				$(".ACQ").hide();
				break;
				
		}
		
	});
	
	
	///on click searc patron 
	$('.vendorCode').on('click',function(){
		var url = "Modal_Vendor?action=All";

		$.get(url,function(data){
			$("#modal_vendorSearchContent").html(data);
		});
	});
	
	///keyup
	$("#input-vendorCode").keyup(function(e){
		var id = $("#input-vendorCode").val();
		/*if(id.length==0){
			$('#Reterive').prop('disabled', true);
		}else{
			$('#Reterive').prop('disabled', false);
		}*/

		$(".div-vendorName").empty();
		////display vendor name
		$.get('GetVendorName', {
        	id : id,
			action : "All"
		 	}, function(responseJson) {
			if(responseJson != null){
				$.each(responseJson, function(key,value) {
					$(".div-vendorName").text(value['vendorName']);
				});
			}
		});
	});
	
	
	//clear title when paron id keydown backspace
	$("#input-vendorCode").keydown(function(e){ 
		var code = e.keyCode || e.which;
		if(code == '8' ||code == '46'){
			$(".div-vendorName").empty();
		}
		
		if(code == 13) {
	      e.preventDefault();
	      return false;
	    }
	});
	
	function ReplaceNumberWithCommas(yourNumber) {
	    //Seperates the components of the number
	    var components = yourNumber.toString().split(".");
	    //Comma-fies the first part
	    components [0] = components [0].replace(/\B(?=(\d{3})+(?!\d))/g, ",");
	    //Combines the two sections
	    return components.join(".");
	}
	


	//////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////
	
	//////////////////////////////////////////////////////////////////////////
	////////////////////////btn Reterive Click ///////////////////////////////
	//////////////////////////////////////////////////////////////////////////
	$('#Reterive').click(function(){
		//$('#reportTable').dataTable().fnClearTable();
		
		var startSentDate = moment($("#input-startDate").val(), 'DD/MM/YYYY').format("YYYYMMDD");
		var endSentDate = moment($("#input-endDate").val(), 'DD/MM/YYYY').format("YYYYMMDD");
		var sDate, eDate;
		
		var startSentDate = $("#input-startDate").val();
		if(startSentDate == ''){
			startSentDate = '';
			sDate = "";
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
		
		var module = $("input[name='chkBoxModule']:checked").val();
		var vendor = $('#input-vendorCode').val();
		
		
		$.get('ResultVendorStatistics', {
        	startSentDate : startSentDate, endSentDate : endSentDate, module : module, vendor : vendor 
		 	}, function(responseJson) {
			if(responseJson != null){
				$.each(responseJson, function(key,value) {
					///$(".AmountOrdered").html(value['Column1']);
					//$(".NumberOfClaimsSent")[0].innerHTML = value['Column1'];
					
					var mod = value['Column1'];
					if(mod == "ACQ"){
						$(".NumberOfClaimsSent")[0].innerHTML = value['Column2'];
						$(".NumberOfCopiesCancelled")[0].innerHTML = value['Column3'];
						$(".NumberOfCopiesClaimed")[0].innerHTML = value['Column4'];
						$(".NumberOfCopiesOrdered")[0].innerHTML = value['Column5'];
						$(".NumberOfCopiesReceived")[0].innerHTML = value['Column6'];
						$(".NumberOfTitleOrdered")[0].innerHTML = value['Column7'];
						$(".NumberOfTitleReceived")[0].innerHTML = value['Column8'];
						$(".AmountOrdered")[0].innerHTML = ReplaceNumberWithCommas(value['Column9']);
						$(".AmountInvoiced")[0].innerHTML = ReplaceNumberWithCommas(value['Column10']);
					}else if(mod == "SER"){
						$(".NumberOfClaimsSentOrder")[0].innerHTML = value['Column2'];
						$(".NumberOfClaimsSentIssue")[0].innerHTML = value['Column3'];
						$(".NumberOfCopiesCancelled")[0].innerHTML = value['Column4'];
						$(".NumberOfCopiesClaimed")[0].innerHTML = value['Column5'];
						$(".NumberOfCopiesOrdered")[0].innerHTML = value['Column6'];
						$(".NumberOfCopiesReceived")[0].innerHTML = value['Column7'];
						$(".NumberOfTitleOrdered")[0].innerHTML = value['Column8'];
						$(".NumberOfTitleReceived")[0].innerHTML = value['Column9'];
						$(".AmountOrdered")[0].innerHTML = ReplaceNumberWithCommas(value['Column10']);
						$(".AmountInvoiced")[0].innerHTML = ReplaceNumberWithCommas(value['Column11']);
					}
					
					
					var t = $('#reportTable').DataTable( {
							dom: 'Bfrtip',
				            buttons: [
				                /*'excel', 'pdf', 'print'*/
										{
									  		extend: 'excelHtml5',
									        filename: 'WILMU_VendorStatistics',
									        title: 'Vendor Statistics Report For '+module+'' +sDate + " until " +eDate,
											exportOptions: {
												//columns: ':visible',
												search: 'applied',
												order: 'applied',
												rows: ':visible'
												//filter: 'applied'
											},
				
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
											filename: 'WILMU_VendorStatistics',
											charset: "utf-8",
											bom : "true",
											exportData : {decodeEntities:true},
											orientation: 'portrait', //portrait landscape
											pageSize: 'A4', //A3 , A5 , A6 , legal , letter
											footer: true,
											
											//messageTop: 'Income Details by Transaction Type' ,
											//messageBottom: "lllllll",
											exportOptions: {
												//columns: ':visible',
												search: 'applied',
												order: 'applied',
												rows: ':visible'
												//filter: 'applied'
											},
											customize: function (doc) {
												
												 var rowCount = doc.content[1].table.body.length;
										           for (i = 0; i < rowCount; i++) {
														doc.content[1].table.body[i][1].alignment = 'right';
										           };
												
												//doc.content[1].table.widths = Array(doc.content[1].table.body[0].length + 1).join('*').split('');
											    //doc.content[1].table.widths = [30,50,50,50,50,50,50,50,50,50,50,50];
											//doc.content[1].table.body[0][3].alignment = 'right';
											//doc.content[1].table.widths = [ '8%', '55%', '12%', '12%', '12%'];
											doc.content[1].table.widths = [ '70%', '30%'];
											 //doc.content[1].table.widths = [ '8%', '55%', '12%', '12%', '12%'];
										   //doc.content[1].table.widths = [ '3%', '10%', '20%', '6%', '6%', '6%','6%','6%','6%','6%','9%','8%','8%'];  
											 //doc.content[1].table.body[0][5].alignment = 'right';
										
										
				
				
															//Remove the title created by datatTables
															doc.content.splice(0, 1, {
										                        text: [
										                                   { text: 'Vendor Statistics Report For '+module+''+sDate+ ' until '+eDate+ ' \n',bold:true,fontSize:13,alignment: 'center'},
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
						                	filename: 'WILMU_VendorStatistics',
						                	title: 'Vendor Statistics Report ',
											charset: "utf-8",
											bom : "true",
											exportOptions: {
												//columns: ':visible',
												search: 'applied',
												order: 'applied',
												rows: ':visible'
												//filter: 'applied'
											},
											customize: function(doc){
														
														var split_csv = doc.split("\n");
														//alert(split_csv)
														$.each(split_csv.slice(1), function (index, csv_row) {
															var csv_cell_array = csv_row.split('","');
															csv_cell_array[1].replace("", (index+1));
														});
														///alert(csv_cell_array[1]);
														//alert(csv_cell_array[1]);
														
														return "Vendor Statistics Report By For "+module+"" +sDate + " until "+ eDate +"\n\n" + doc;
														//var split_csv = doc.split("\n");
														 
													 }
				             			},
				            ],
							destroy: true,
							searching: true,
							bLengthChange: false,
							autoWidth: false,
							responsive: true,
							processing: false,
							paging:   false,
							bInfo : false,
							bSort : false,
							/*language: {
						        "processing": "Loading. Please wait..."
						    },*/
							/*language: {
				             loadingRecords : "Please wait - loading...",
				             processing :     "Please wait formatting in progress"
				        	},*/
							/*language: {
				                    processing: '<i class="fa fa-spinner fa-spin" style="font-size:24px;color:rgb(75, 183, 245);"></i>'
				            },*/
							//serverSide: true,
							//deferRender: true,
							//footer: false,
				
				    } );
				});
			}
		});
		
		/*setTimeout(function() { 
	       /////result display
		
	    }, 10000);*/
		
		
		
		
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