$(document).ready(function() {
	
	var today = new Date();  
	
	/////////////////--- EDITOR ------/////////
	
	////1) Hide selection criteria
	/////List all div   
	////.rangeDiv, .vendorSearch, .patronSearch, .orderModeDiv, .statusDiv, .acctDiv,
	////.deptDiv, .invstatDiv, .itemstatDiv, .recordselectionDiv, .claimstatDiv, .printerStat
	
	$(".patronSearch, .statusDiv, .acctDiv, .itemstatDiv").hide();
	$(".recordselectionDiv, .printerStat").hide();

	
	////2) Change Label
	///Date Label
	$(".datelabel").html("<b>Claim Date Range</b>");
	
	///Range Label
	$(".rangeLabel").html("<b>Order Number Range</b>");
	
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
	});*/
	
	
	///4) Checkbox tick or not
	
	////a) range
	$("#chkBoxRange").on('change', function () {
		var self = $(this);
	    if (self.is(":checked")) {
			$("#startInput, #endInput").prop( "disabled", false);
			$('#Reterive').prop('disabled', true);
			
			
	    } else {
			$("#startInput, #endInput").prop( "disabled", true);
			$('#Reterive').prop('disabled', false);
			$("#startInput, #endInput").val("");
			$("#startInput, #endInput").css("border", "");
	    }
	});
	
	$("#startInput, #endInput").keyup(function(e){
		var startInput = $("#startInput").val();
		var endInput = $("#endInput").val();
		if(startInput.length >=1 || endInput.length >=1){
			$('#Reterive').prop('disabled', false);
		}else{
			$('#Reterive').prop('disabled', true);
		}
	});
	

	////b) checkbox multiselect ordermode
	$("#chkBoxOrderMode").on('change', function () {
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
	});
	
	
	
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
	/*)$("#chkBoxAcct").on('change', function () {
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
	$("#chkBoxDepartment").on('change', function () {
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
	});

	
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
	$("#chkBoxInvStatus").on('change', function () {
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
	});
	
	///5) Radio tick or not
	////a) when radio button Record Selectionu is click
	$('input[type=radio][name=rbRrcdSelection]').on('change', function() {
		switch ($(this).val()) {
			 case 'rcdselection':
				$('#Reterive').prop('disabled', true);
				$('input[name=radioRecordselection]').attr("disabled",false);
		      break;
		}
	});
	
	
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
	});*/
	
	
	////b) when radio button Claim Status click
	$('input[type=radio][name=rbClaimStat]').on('change', function() {
		switch ($(this).val()) {
			 case 'claimstat':
				$('#Reterive').prop('disabled', true);
				$('input[name=radioclaimstat]').attr("disabled",false);
		      break;
		}
	});
	
	///when radio option Claim Status is choose
	$('input[type=radio][name=radioclaimstat]').on('change', function() {
		
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
	});
	
	/////////////////--- EDITOR ------/////////
	
	
	//////////////////////////////////////////////////////////////////////////
	////////////////////////btn Reterive Click ///////////////////////////////
	//////////////////////////////////////////////////////////////////////////
	$('#Reterive').click(function(){
		$('#reportTable').dataTable().fnClearTable();
		
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
		
		
		
		var brnch = $("#branch").val(); 
		if(brnch != ""){
		}else{
			brnch = 0;
		}
		
		
		/////////////////--- EDITOR ------/////////
		//1 Range
		var chkRange = $('#chkBoxRange:checked').val();
		var rangeFirstVal; 
		var rangeLastVal;
		
		if(chkRange == "range"){
    		rangeFirstVal = $('input[name=startInput]').val();
			rangeLastVal = $('input[name=endInput]').val();
    	}else{
    		rangeFirstVal = '';
			rangeLastVal = '';
    	}

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
		
	
		//3 ordermode
		var orderMode = $("#ordermode").val(); 
		if(orderMode != ""){
		}else{
			orderMode = 0;
		}
		
		//4 status
		/*var status = $("#acqStat").val(); 
		if(status != ""){
		}else{
			status = 0;  
		}
		
		//5 account
		var account = $("#acct").val(); 
		if(account != ""){
		}else{
			account = 0;
		}*/
		
		//6 department
		var department = $("#dept").val(); 
		if(department != ""){
		}else{
			department = 0;
		}
		
		//7 Invoice Status
		var acqinvstat = $("#acqinvstat").val(); 
		if(acqinvstat != ""){
		}else{
			acqinvstat = 0;
		}
		
		//8 Item Status
		/*var acqitemstat = $("#acqitemstat").val(); 
		if(acqitemstat != ""){
		}else{
			acqitemstat = 0;
		}*/
		
		
		//9 Record Selection
		/*var rbrecorselecion = $("input[name='rbRrcdSelection']:checked").val();
		var recorselecion = $("input[name='radioRecordselection']:checked").val();
		
		if(rbrecorselecion==undefined){
			recorselecion = 0;
		}*/
		
		
		//10 Claim Status
		var rbclaimstatus = $("input[name='rbClaimStat']:checked").val();
		var claimstatus = $("input[name='radioclaimstat']:checked").val();
		
		if(rbclaimstatus==undefined){
			claimstatus = 0;
		}
		
		///unprinted
		/*var chkBoxAcqPrint = $("input[name='chkBoxAcqPrint']:checked").val();
		
		
		
		switch(chkBoxAcqPrint) {
			
			case 'acqprint':
					chkBoxAcqPrint = "Y";  
					break;
			case undefined:
					chkBoxAcqPrint = "N";
		    		break;	
		}*/
		
		

		/////////////////--- EDITOR ------/////////		
		
		/////result display
		var t = $('#reportTable').DataTable( {
			dom: 'Bfrtip',
            buttons: [
						{
					  		extend: 'excelHtml5',
					        filename: 'WILMU_AcqClaimsReport',
					        title: 'Claims Report ' +sDate +eDate,
					                
					 	},
						{
					    	text: '<i class="fa fa-file-pdf-o"></i> PDF',
							extend: 'pdfHtml5',
							filename: 'WILMU_AcqClaimsReport',
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
								
								doc.styles.tableHeader.alignment = 'left';
								
								 var rowCount = doc.content[1].table.body.length;
						           for (i = 0; i < rowCount; i++) {
										doc.content[1].table.body[i][9].alignment = 'right';
						               	doc.content[1].table.body[i][10].alignment = 'right';
						           };
								
							 	//doc.content[1].table.widths = [ '8%', '55%', '12%', '12%', '12%']; 

											//Remove the title created by datatTables  
											doc.content.splice(0, 1, {
						                        text: [
						                                   { text: 'Claims Report '+sDate +eDate+ '\n',bold:true,fontSize:13,alignment: 'center'}						                        ],
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
		                	filename: 'WILMU_AcqClaimsReport',
							charset: "utf-8",
							bom : "true",
		                	//title: 'Circulation Activity By ',
							customize: function(doc){
										
										var split_csv = doc.split("\n");
										//alert(split_csv)
										$.each(split_csv.slice(1), function (index, csv_row) {
											var csv_cell_array = csv_row.split('","');
											csv_cell_array[1].replace("", (index+1));
										});
										
										return "Claims Report "+sDate + eDate +"\n\n" + doc;
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
		    	url: "ResultAcqClaimReport",
		        data : {currencyFormat :  currencyFormat, 
						startSentDate : startSentDate, endSentDate : endSentDate, brnch : JSON.stringify(brnch),
						//// Editer/////
						rangeFirstVal : rangeFirstVal, rangeLastVal : rangeLastVal,
						vendor : vendor, //patronID : patronID,
						orderMode : JSON.stringify(orderMode),
						//status : JSON.stringify(status),
						//account : JSON.stringify(account),
						department : JSON.stringify(department),
						acqinvstat : JSON.stringify(acqinvstat),
						//acqitemstat : JSON.stringify(acqitemstat),
						//recorselecion : recorselecion, 
						claimstatus : claimstatus,
						//chkBoxAcqPrint : chkBoxAcqPrint
						//// Editer///// 
						},
		        type: "GET",
		        dataSrc: function (json) {
		            var return_data = new Array();
		            
		            for(var i=0;i< json.length; i++){

		              return_data.push({
		                'No': (i+1),
		                'Order No' : json[i].Column1,
		                'Order Date' : json[i].Column2,
		                'Vendor' : json[i].Column3,
		                'Source' : json[i].Column4,
						'Title' : json[i].Column5,
						'ISBN' : json[i].Column6,
						'Claim Type' : json[i].Column7,
						'Claim Date' : json[i].Column8,
						'Quantity' : json[i].Column9,
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
					{'data': 'Source'},
					{'data': 'Title'},
					{'data': 'ISBN'},
					{'data': 'Claim Type'},
					{'data': 'Claim Date'},
					{'data': 'Quantity', className: "text-right"},
					{'data': 'Local Price', className: "text-right", render: $.fn.dataTable.render.number(',', '.', currencyFormat, '')},	
				],
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
		            totalLocalPrice = api
		                .column( 10 )
		                .data()
		                .reduce( function (a, b) {
		                    return intVal(a) + intVal(b);
		                }, 0 );
		 
		 
		            // Update footer
		           $( api.column( 10 ).footer() ).html(
		                numFormat(totalLocalPrice)
		            );
		        }
    	});
		
		/*t.on('order.dt search.dt', function () {
			 t.column(0, {search:'applied', order:'applied'}).nodes().each( function (cell, i) {
				cell.innerHTML = i+1;
				t.cell(cell).invalidate('dom');
			 });
	   }).draw();*/
		
    	

    });

	
	
});