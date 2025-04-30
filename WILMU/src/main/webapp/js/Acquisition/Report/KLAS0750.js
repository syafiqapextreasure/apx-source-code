$(document).ready(function() {
	
	var today = new Date(); 
	
	$(".divreportTableSummary").hide();
	
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
	/////List all div   
	////.rangeDiv, .vendorSearch, .patronSearch, .orderModeDiv, .statusDiv, .acctDiv,
	////.deptDiv, .invstatDiv, .itemstatDiv, .recordselectionDiv, .claimstatDiv, .printerStat
	
	
	$(".branchDiv, .rangeDiv, .acctDiv").hide();
	$(".deptDiv, .invstatDiv, .itemstatDiv,  .claimstatDiv, .printerStat").hide();
	
	//
	//$(".recordselectionDiv, .printerStat, .claimstatDiv").hide();

	
	////2) Change Label
	///Date Label
	$(".datelabel").html("<b>Transaction Date Range</b>");
	
	///Range Label
	//$(".rangeLabel").html("<b>Order Number Range</b>");
	
	///Status Label (Request/Order /Invoice /Gift)
	//$(".statusLabel").html("<b>Request Status</b>")
	
	
	////3) ... search
	
	////Vendor
	$('.vendorCode').on('click',function(){
		var url = "Modal_Vendor?action=All";

		$.get(url,function(data){
			$("#modal_vendorSearchContent").html(data);
		});
	});
	
	$("#input-vendorCode").on("keypress keyup blur",function (e) {
		var id = $("#input-vendorCode").val();

		$(".div-vendorName").empty();
		////display vendor name
		$.get('ResultVendorName', {
        	id : id,
			action : "All"
		 	}, function(responseJson) {
			if(responseJson != null){
				$.each(responseJson, function(key,value) {
					$(".div-vendorName").text(value['vendorName']);
					$('#Reterive').prop('disabled', false);
				});
			}
		});
	});
	
	
	//clear vendor keydown backspace
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
	
	////Patron
	/*$('.patronid').on('click',function(){
		var url = "Modal_PatrSearch";
		
		$.get(url,function(data){
			$("#Modal_PatrSearchContent").html(data);
		});
	});
	
	///keyup
	$("#lblPatronID").keyup(function(e){
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
	});*/
	
	
	///4) Checkbox tick or not
	
	////a) range
	/*$("#chkBoxRange").on('change', function () {
		var self = $(this);
	    if (self.is(":checked")) {
			$("#startInput, #endInput").prop( "disabled", false);
			$('#Reterive').prop('disabled', true);
			
			
	    } else {
			$("#startInput, #endInput").prop( "disabled", true);
			$('#Reterive').prop('disabled', false);
			$("#startInput, #endInput").val("");
	    }
	});*/
	
	/*$("#startInput, #endInput").keyup(function(e){
		var startInput = $("#startInput").val();
		var endInput = $("#endInput").val();
		if(startInput.length >=1 || endInput.length >=1){
			$('#Reterive').prop('disabled', false);
		}else{
			$('#Reterive').prop('disabled', true);
		}
	});*/
	

	////b) checkbox multiselect ordermode
	/*$("#chkBoxOrderMode").on('change', function () {
	    var self = $(this);
	    if (self.is(":checked")) {
			$('#ordermode').multiselect("enable");
			$('#Reterive').prop('disabled', true);
			$('#ordermode').change(function() {
				if($("#ordermode").val() != ""){
					$('#Reterive').prop('disabled', false);
				}else{
					$('#Reterive').prop('disabled', true);
				}
			});	
	    } else {
			$('#ordermode').multiselect("disable");
			$('#Reterive').prop('disabled', false);
			$("#ordermode").multiselect("clearSelection")
 			$("#ordermode").multiselect( 'refresh' ); 
	    }
	});*/
	
	
	
	////c) checkbox multiselect status
	/*$("#chkBoxStatus").on('change', function () {
	    var self = $(this);
	    if (self.is(":checked")) {
			$('#acqStat').multiselect("enable");
			$('#Reterive').prop('disabled', true);
			$('#acqStat').change(function() {
				if($("#acqStat").val() != ""){
					$('#Reterive').prop('disabled', false);
				}else{
					$('#Reterive').prop('disabled', true);
				}
			});	
	    } else {
			$('#acqStat').multiselect("disable");
			$('#Reterive').prop('disabled', false);
			$("#acqStat").multiselect("clearSelection")
 			$("#acqStat").multiselect( 'refresh' ); 
	    }
	});*/
	
	////d) checkbox multiselect account
	/*$("#chkBoxAcct").on('change', function () {
	    var self = $(this);
	    if (self.is(":checked")) {
			$('#acct').multiselect("enable");
			$('#Reterive').prop('disabled', true);
			$('#acct').change(function() {
				if($("#acct").val() != ""){
					$('#Reterive').prop('disabled', false);
				}else{
					$('#Reterive').prop('disabled', true);
				}
			});	
	    } else {
			$('#acct').multiselect("disable");
			$('#Reterive').prop('disabled', false);
			$("#acct").multiselect("clearSelection")
 			$("#acct").multiselect( 'refresh' ); 
	    }
	});*/
	
	////e)  checkbox multiselect department
	/*$("#chkBoxDepartment").on('change', function () {
	    var self = $(this);
	    if (self.is(":checked")) {
			$('#dept').multiselect("enable");
			$('#Reterive').prop('disabled', true);
			$('#dept').change(function() {
				if($("#dept").val() != ""){
					$('#Reterive').prop('disabled', false);
				}else{
					$('#Reterive').prop('disabled', true);
				}
			});
	    } else {
			$('#dept').multiselect("disable");
			$('#Reterive').prop('disabled', false);
			$("#dept").multiselect("clearSelection")
 			$("#dept").multiselect( 'refresh' ); 
	    }
	});*/

	
	////f) checkbox multiselect item status
	/*$("#chkBoxItemStatus").on('change', function () {
	    var self = $(this);
	    if (self.is(":checked")) {
			$('#acqitemstat').multiselect("enable");
			$('#Reterive').prop('disabled', true);
			$('#acqitemstat').change(function() {
				if($("#acqitemstat").val() != ""){
					$('#Reterive').prop('disabled', false);
				}else{
					$('#Reterive').prop('disabled', true);
				}
			});
	    } else {
			$('#acqitemstat').multiselect("disable");
			$('#Reterive').prop('disabled', false);
			$("#acqitemstat").multiselect("clearSelection")
 			$("#acqitemstat").multiselect( 'refresh' ); 
	    }
	});*/
	
	////g) checkbox multiselect inv status
	/*$("#chkBoxInvStatus").on('change', function () {
	    var self = $(this);
	    if (self.is(":checked")) {
			$('#acqinvstat').multiselect("enable");
			$('#Reterive').prop('disabled', true);
			$('#acqinvstat').change(function() {
				if($("#acqinvstat").val() != ""){
					$('#Reterive').prop('disabled', false);
				}else{
					$('#Reterive').prop('disabled', true);
				}
			});
	    } else {
			$('#acqinvstat').multiselect("disable");
			$('#Reterive').prop('disabled', false);
			$("#acqinvstat").multiselect("clearSelection")
 			$("#acqinvstat").multiselect( 'refresh' ); 
	    }
	});*/
	
	///5) Radio tick or not
	////a) when radio button Record Selectionu is click
	/*$('input[type=radio][name=rbRrcdSelection]').on('change', function() {
		switch ($(this).val()) {
			 case 'rcdselection':
				$('#Reterive').prop('disabled', true);
				$('input[name=radioRecordselection]').attr("disabled",false);
		      break;
		}
	});*/
	
	
	//// when radio option  Record Selection is choose
	/*$('input[type=radio][name=radioRecordselection]').on('change', function() {

		switch ($(this).val()) {
			 case 'F':
				$('#Reterive').prop('disabled', false);
		      break;
			case 'S':
				$('#Reterive').prop('disabled', false);
		      break;
		}
	});
	
	
	////b) when radio button Claim Status click
	$('input[type=radio][name=rbClaimStat]').on('change', function() {
		switch ($(this).val()) {
			 case 'claimstat':
				$('#Reterive').prop('disabled', true);
				$('input[name=radioclaimstat]').attr("disabled",false);
		      break;
		}
	});*/
	
	///when radio option Claim Status is choose
	/*$('input[type=radio][name=radioclaimstat]').on('change', function() {
		
		switch ($(this).val()) {
			 case 'C':
				$('#Reterive').prop('disabled', false);
		      break;
			case 'O':
				$('#Reterive').prop('disabled', false);
		      break;
			case 'U':
				$('#Reterive').prop('disabled', false);
		      break;
		}
	});*/
	
	$('input[type=radio][name=radioRecordselection]').on('change', function() {
		
		switch ($(this).val()) {
			 case 'F':
				$('#reportTableSummary, #reportTableFull').dataTable().fnClearTable();
				$(".divreportTableSummary").hide();
				$(".divreportTableFull").show();
				$("#allTotalQty").html(" 0"); 
				$(".buttons-html5").hide();
				//$('#reportTableSummary, #reportTableFull').dataTable().buttons( '.buttons-html5' ).remove();
		      break;
			case 'S':
				$('#reportTableSummary, #reportTableFull').dataTable().fnClearTable();
				$(".divreportTableFull").hide();
				$(".divreportTableSummary").show();
				$("#allTotalQty").html(" 0"); 
				$(".buttons-html5").hide();
		      break;
		}
	});
	
	/////////////////--- EDITOR ------/////////
	
	
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
		
		
		
		/*var brnch = $("#branch").val(); 
		var allbranch; 
	
		if(brnch != ""){
			if(allSelected){
				allbranch = "Y";
			  }else{
				allbranch = "N";
			    //alert("No.");
			  }
		}else{
			brnch = 0;
			allbranch = "N";
		}*/
		
		
		/////////////////--- EDITOR ------/////////
		//1 Range
		/*var chkRange = $('#chkBoxRange:checked').val();
		var rangeFirstVal; 
		var rangeLastVal;
		
		if(chkRange == "range"){
    		rangeFirstVal = $('input[name=startInput]').val();
			rangeLastVal = $('input[name=endInput]').val();
    	}else{
    		rangeFirstVal = '';
			rangeLastVal = '';
    	}*/

		//2 Vendor AND Patron Id
		var chkBoxSearchCateria = [];
		$.each($("input[name='chkBoxSearchCateria']:checked"), function(){            
                chkBoxSearchCateria.push($(this).val());
            });
		chkBoxSearchCateria = chkBoxSearchCateria.join(", ");
		
		var vendor;
		var patronID;
		//alert(chkBoxSearchCateria+"chkBoxSearchCateria");
		
		switch(chkBoxSearchCateria) {
		
			 case 'vendCode':
					vendor = $("#input-vendorCode").val();  
					patronID = 0;
		    break;	
			 case 'patrID':
					vendor = 0;
					patronID = $("#lblPatronID").val();

		    break;	
 			case 'vendCode, patrID':
				vendor = $("#input-vendorCode").val();
				patronID = $("#lblPatronID").val();
		    break;	
			case '':
				vendor = 0;
				patronID = 0;
		    break;	
			
		}

		var recordselection = $("input[name='radioRecordselection']:checked").val();
		
		if(recordselection=="F"){
			$('#reportTableFull').DataTable( {
			dom: 'Bfrtip',
            buttons: [
						{
					  		extend: 'excelHtml5',
					        filename: 'WILMU_AcquisitionStatusReportOfOrderByVendor',
					        title: 'Acquisition Status Report Of Order By Vendor ' +sDate +eDate,          
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
						           for (i = 0; i < rowCount; i++) {
										doc.content[1].table.body[i][5].alignment = 'right';
										doc.content[1].table.body[i][8].alignment = 'right';
						               //	doc.content[1].table.body[i][10].alignment = 'right';
						           };
								
							 	//doc.content[1].table.widths = [ '3%', '6%', '12%', '6%', '12%', '8%', '8%', '12%', '12%', '8%', '6%', '7%'];

											//Remove the title created by datatTables  
											doc.content.splice(0, 1, {
						                        text: [
						                                   { text: 'Acquisition Status Report Of Order By Vendor '+sDate +eDate+ '\n',bold:true,fontSize:13,alignment: 'center'}						                        ],
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
			/*columnDefs: [
				{
			    	"targets": [ targetVal.indexOf(1) ],
			       	"visible": false,
			        "searchable": false,
			    }
			],*/
			deferRender: true,
			footer: true,
		    ajax: {
		    	url: "ResultAcqStatusReportOfOrderByVendor",
		        data : {currencyFormat :  currencyFormat, 
						startSentDate : startSentDate, endSentDate : endSentDate, //brnch : JSON.stringify(brnch),
						//// Editer/////
						//rangeFirstVal : rangeFirstVal, rangeLastVal : rangeLastVal,
						vendor : vendor, //patronID : patronID,
						//status : JSON.stringify(status),
						//department : JSON.stringify(department),
						//recordselection : recordselection,
						//// Editer///// 
						//allbranch : allbranch
						recordselection : recordselection
						},
		        type: "GET",
		        dataSrc: function (json) {
		            var return_data = new Array();
		            
		            for(var i=0;i< json.length; i++){

		              return_data.push({
		                'No': (i+1),
						'Vendor' : json[i].Column1,
						'Order No' : json[i].Column2,
		                'Order Date' : json[i].Column3,
		                'Date Received' : json[i].Column4,
		                'Order Price' : json[i].Column5,
		                'Title' : json[i].Column6,
						'Invoice No' : json[i].Column7,
						'Qty Received' : json[i].Column8,
						'Status' : json[i].Column9,
		            })
		            }
					
		            return return_data;
		          },
		     },//This is the end of the AJAX object from the example above
		     	columns    : [
					{'data': 'No'},
					{'data': 'Vendor', visible: false},
					{'data': 'Order No'},
					{'data': 'Order Date'},
					{'data': 'Date Received'},
					{'data': 'Order Price', className: "text-right"},		
					{'data': 'Title'},
					{'data': 'Invoice No'},
					{'data': 'Qty Received', className: "text-right"},
					{'data': 'Status'},
					//{'data': 'Total', className: "text-right"},
				],
				drawCallback: function ( settings ) {
		            var api = this.api();
		            var rows = api.rows( {page:'current'} ).nodes();
		            var last=null;
		 
		            api.column(1, {page:'current'} ).data().each( function ( group, i ) {
		                if ( last !== group ) {
		                    $(rows).eq( i ).before(
		                        '<tr class="group"><td colspan="9">'+group+'</td></tr>'
		                    );
		 
		                    last = group;
		                }
		            } );
		        },
				footerCallback: function ( row, data, start, end, display ) {
		            var api = this.api(), data;

					var numFormat = $.fn.dataTable.render.number( '\,', '.', currencyFormat, '' ).display
		 
		            // Remove the formatting to get integer data for summation
		            var intVal = function ( i ) {
		                return typeof i === 'string' ?
		                    i.replace(/[\$,]/g, '')*1 :
		                    typeof i === 'number' ?
		                        i : 0;
		            };
		 
					///charge
		            // Total over all pages
		            totalQty = api
		                .column( 8 )
		                .data()
		                .reduce( function (a, b) {
		                    return intVal(a) + intVal(b);
		                }, 0 );
		 
		 
		            // Update footer
		           /*$( api.column( 10 ).footer() ).html(
		                numFormat(totalLocalPrice)
		            );*/
		 			/////////////////////////////////////////////////////////
					$("#allTotalQty").html(" "+totalQty); 

		        }
    	});
			
		}else if(recordselection=="S"){
			
			$('#reportTableSummary').DataTable( {
			dom: 'Bfrtip',
            buttons: [
						{
					  		extend: 'excelHtml5',
					        filename: 'WILMU_AcquisitionStatusReportOfOrderByVendor',
					        title: 'Acquisition Status Report Of Order By Vendor ' +sDate +eDate,          
					 	},
						{
					    	text: '<i class="fa fa-file-pdf-o"></i> PDF',
							extend: 'pdfHtml5',
							filename: 'WILMU_AcquisitionStatusReportOfOrderByVendor',
							charset: "utf-8",
							bom : "true",
							orientation: 'portrait', //portrait
							pageSize: 'A4', //A3 , A5 , A6 , legal , letter
							footer: true,
							customize: function (doc) {
								
								doc.styles.tableHeader.alignment = 'left';
								
								 var rowCount = doc.content[1].table.body.length;
						           for (i = 0; i < rowCount; i++) {
										doc.content[1].table.body[i][2].alignment = 'left';
										doc.content[1].table.body[i][3].alignment = 'right';
						               //	doc.content[1].table.body[i][10].alignment = 'right';
						           };
								
							 	//doc.content[1].table.widths = [ '3%', '6%', '12%', '6%', '12%', '8%', '8%', '12%', '12%', '8%', '6%', '7%'];

											//Remove the title created by datatTables  
											doc.content.splice(0, 1, {
						                        text: [
						                                   { text: 'Acquisition Status Report Of Order By Vendor '+sDate +eDate+ '\n',bold:true,fontSize:13,alignment: 'center'}						                        ],
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
			/*columnDefs: [
				{
			    	"targets": [ targetVal.indexOf(1) ],
			       	"visible": false,
			        "searchable": false,
			    }
			],*/
			
			deferRender: true,
			footer: true,
		    ajax: {
		    	url: "ResultAcqStatusReportOfOrderByVendor",
		        data : {currencyFormat :  currencyFormat, 
						startSentDate : startSentDate, endSentDate : endSentDate, //brnch : JSON.stringify(brnch),
						//// Editer/////
						//rangeFirstVal : rangeFirstVal, rangeLastVal : rangeLastVal,
						vendor : vendor, //patronID : patronID,
						//status : JSON.stringify(status),
						//department : JSON.stringify(department),
						//recordselection : recordselection,
						//// Editer///// 
						//allbranch : allbranch
						recordselection : recordselection
						},
		        type: "GET",
		        dataSrc: function (json) {
		            var return_data = new Array();
		            
		            for(var i=0;i< json.length; i++){

		              return_data.push({
						'Vendor' : json[i].Column1,
						'Total Received Item (Orders)' : json[i].Column2,
		                'Total Not Received Item (Orders)' : json[i].Column3,
		                'Total Local Payment (Invoices)' : json[i].Column4,
		                'Total Unpaid Local Amount (Invoice)' : json[i].Column5,
		            })
		            }
					
		            return return_data;
		          },
		     },//This is the end of the AJAX object from the example above
		     	columns    : [
					{'data': 'Vendor',},
					{'data': 'Total Received Item (Orders)', className: "text-right", render: $.fn.dataTable.render.number(',', '.', currencyFormat, '')},
					{'data': 'Total Not Received Item (Orders)', className: "text-right", render: $.fn.dataTable.render.number(',', '.', currencyFormat, '')},
					{'data': 'Total Local Payment (Invoices)', className: "text-right", render: $.fn.dataTable.render.number(',', '.', currencyFormat, '')},
					{'data': 'Total Unpaid Local Amount (Invoice)', className: "text-right", render: $.fn.dataTable.render.number(',', '.', currencyFormat, '')},
				],
				/*drawCallback: function ( settings ) {
		            var api = this.api();
		            var rows = api.rows( {page:'current'} ).nodes();
		            var last=null;
		 
		            api.column(0, {page:'current'} ).data().each( function ( group, i ) {
		                if ( last !== group ) {
		                    $(rows).eq( i ).before(
		                        '<tr class="group"><td colspan="3">'+group+'</td></tr>'
		                    );
		 
		                    last = group;
		                }
		            } );
		        },*/
    	});
			
		}

		/////////////////--- EDITOR ------/////////		
		
		
		
		/*t.on('order.dt search.dt', function () {
			 t.column(0, {search:'applied', order:'applied'}).nodes().each( function (cell, i) {
				cell.innerHTML = i+1;
				t.cell(cell).invalidate('dom');
			 });
	   }).draw();*/
		
    	

    });

	
	
});