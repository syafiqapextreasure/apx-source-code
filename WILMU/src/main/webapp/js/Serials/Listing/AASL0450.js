$(document).ready(function() {

	function messageBox(code, criteria, label){
			$.get("Global?type=Handler&name=Error_Page",{GL79ERRCODE : code,
				criteria : criteria,
			 	label : label},function(result){
			 		//alert(result);
					//swal('',result, 'info' );
					swal(result);
				
			});
	} 
	
	var today = new Date();  
	
	var allSelected = false;
	$('#branch').multiselect({
    	allSelectedText: 'All',
   		maxHeight: 200,
   		includeSelectAllOption: true,
		numberDisplayed: 1,
		onSelectAll: function(){
		    allSelected = true;
		  },
		  onChange: function(){
		    allSelected = false;
		  }
	}); 

	//$('#chkBoxStatus, #Reterive').prop( "disabled", true);
	$('#chkBoxStatus').prop('checked', true);
	$('#seStat').multiselect("enable");
	
	$('#seStat').change(function() {
		if($("#seStat").val() != ""){
			$('#Reterive').prop('disabled', false);
		}else{
			$('#Reterive').prop('disabled', true);
		}
	});
	
	$(".divreportTableSummary").hide();
	
	//////table setup
	/*$('#reportTableSummary, #reportTableFull').DataTable( {
		destroy: true,
		searching: false,
		bLengthChange: false,
		autoWidth: false,
		responsive: true,
	});*/

	/////////////////--- EDITOR ------/////////
	
	////1) Hide selection criteria
	/////List all div   
	////.rangeDiv, .vendorSearch, .patronSearch, .orderModeDiv, .statusDiv, .acctDiv,
	////.deptDiv, .invstatDiv, .itemstatDiv, .recordselectionDiv, .claimstatDiv, .printerStat
	
	$(".rangeDiv, .orderModeDiv, .acctDiv, .invstatDiv, .itemstatDiv, .deptDiv").hide();
	$(".claimstatDiv, .printerStat, .recordselectionDiv").hide();

	
	////2) Change Label
	///Date Label
	$(".datelabel").html("<b>Stop Date Range</b>");
	
	///Range Label
	$(".rangeLabel").html("<b>Request Number Range</b>");
	
	///Status Label (Request/Order /Invoice /Gift)
	$(".statusLabel").html("<b>Request Status</b>")
	
	
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
			$(".lblName").empty();
		}
		
		if(code == 13) {
		    e.preventDefault();
		    return false;
		 }
	});
	
	
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
	
	////c) checkbox multiselect status
	$("#chkBoxStatus").on('change', function () {
	    var self = $(this);
	    if (self.is(":checked")) {
			$('#seStat').multiselect("enable");
			$('#Reterive').prop('disabled', true);
			$('#seStat').change(function() {
				if($("#seStat").val() != ""){
					$('#Reterive').prop('disabled', false);
				}else{
					$('#Reterive').prop('disabled', true);
				}
			});	
	    } else {
			$('#seStat').multiselect("disable");
			$('#Reterive').prop('disabled', false);
			$("#seStat").multiselect("clearSelection")
 			$("#seStat").multiselect( 'refresh' ); 
	    }
	});
		
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

	/////validate range number   #startInput, #endInput
	$("#startInput, #endInput").focusout(function(e){
		e.preventDefault();
		e.stopImmediatePropagation();

	    if(parseFloat($("#startInput").val()) > parseFloat($("#endInput").val())){
			$("#startInput, #endInput").css("border", "solid red");
	        $("#Reterive").prop('disabled',true);
	
			messageBox("172","","");
			$("#chkBoxRange").focus();
	    } else {
	        $(".error").css("display","none");
			$("#startInput, #endInput").css("border", "");
	        $("#Reterive").prop('disabled',false);        
	    }
	    
	});
	
	///5) Radio tick or not

	
	////radio button selection
	/////var tableReport = $('#reportTable').DataTable();
 
	// Hide two columns
	

	$('input[type=radio][name=radioRecordselection]').on('change', function() {
		
		switch ($(this).val()) {
			 case 'F':
				//$('#reportTableSummary, #reportTableFull').dataTable().fnClearTable();
				$(".divreportTableSummary").hide();
				$(".divreportTableFull").show();
		      break;
			case 'S':
				//$('#reportTableSummary, #reportTableFull').dataTable().fnClearTable();
				$(".divreportTableFull").hide();
				$(".divreportTableSummary").show();
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
		
		
		var selection = [] ;
		$.each($("input[name='selectionendyear']:checked"), function(){            
                selection.push($(this).val());
            });
		selection = selection.join(", ");
		
		

		
		//var selection = $("input[name='selectionendyear']:checked").val();
		
		

		
		/////////////////--- EDITOR ------/////////	
		var numberofcolumn;
		var pageOrin;
		if(selection == '')	{
			numberofcolumn = "0,1,2,3,4,5,6,7";
			pageOrin = 'portrait'; 
		}else if(selection == 'D')	{
			numberofcolumn = "0,1,2,3,4,5,6,7,8,10,11,12,13,14";
			pageOrin = 'landscape'; 
		}else if(/*selection == 'D' ||*/ selection == 'D, V')	{
			numberofcolumn = "0,1,2,3,4,5,6,7,8,10,11,12,13,14,15";
			pageOrin = 'landscape'; 
		}else if(selection == 'V')	{
			numberofcolumn = "0,1,2,3,4,5,6,7,15";
			pageOrin = 'landscape'; 
		}
		
		


		var t = $('#reportTable').DataTable( {
			dom: 'Bfrtip',
            buttons: [
						{
					  		extend: 'excelHtml5',
					        filename: 'WILMU_SeEndYearList',
							exportOptions: {
								 columns: numberofcolumn,
							},
					        title: 'End of Year Serial Listing ' +sDate +eDate,
					                
					 	},
						{
					    	text: '<i class="fa fa-file-pdf-o"></i> PDF',
							extend: 'pdfHtml5',
							filename: 'WILMU_SeEndYearList',
							orientation: pageOrin, //portrait
							pageSize: 'A4', //A3 , A5 , A6 , legal , letter
							footer: true,
							exportOptions: {
								 columns: numberofcolumn,
							},
							customize: function (doc) {
								
								doc.styles.tableHeader.alignment = 'left';
								
								/* var rowCount = doc.content[1].table.body.length;
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
										doc.content[1].table.body[i][9].alignment = 'left';
										doc.content[1].table.body[i][10].alignment = 'left';
										doc.content[1].table.body[i][11].alignment = 'left';
										doc.content[1].table.body[i][12].alignment = 'left';
										doc.content[1].table.body[i][13].alignment = 'left';
										doc.content[1].table.body[i][14].alignment = 'left';
										doc.content[1].table.body[i][15].alignment = 'left';
						           };*/
								
							 	///doc.content[1].table.widths = [ '3%', '6%', '12%', '6%', '12%', '8%', '8%', '12%', '12%', '8%', '6%', '7%'];

							 	/*doc.content.push( {
						                        //margin: [ 0, 0, 0, 12 ],
						                        alignment: 'right',
												bold:true,
								                   text: [
								                         	$('.divtotal').text(), 
								                        ]
	
											 } );*/

											//Remove the title created by datatTables  
											doc.content.splice(0, 1, {
						                        text: [
						                                   { text: 'End of Year Serial Listing '+sDate +eDate+ '\n',bold:true,fontSize:13,alignment: 'center'}						                        ],
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
		                	filename: 'WILMU_SeEndYearList',
							exportOptions: {
							                    columns: numberofcolumn,
							},
							customize: function(doc){
										
										var split_csv = doc.split("\n");
										//alert(split_csv)
										$.each(split_csv.slice(1), function (index, csv_row) {
											var csv_cell_array = csv_row.split('","');
											csv_cell_array[1].replace("", (index+1));
										});
										
										return "End of Year Serial Listing "+sDate + eDate +"\n\n" + doc;
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
			    	"targets": [ ArrayOfInts ],
			       	"visible": false,
			        "searchable": false,
			    }
			],*/
			deferRender: true,
			footer: true,
		    ajax: {
		    	url: "ResultSeEndYearList",
		        data : {currencyFormat :  currencyFormat, 
						startSentDate : startSentDate, endSentDate : endSentDate, brnch : JSON.stringify(brnch),
						//// Editer/////
						rangeFirstVal : rangeFirstVal, rangeLastVal : rangeLastVal,
						vendor : vendor, patronID : patronID,
						//// Editer///// 
						allbranch : allbranch
						},
		        type: "GET",
		        dataSrc: function (json) {
		            var return_data = new Array();
		            
		            for(var i=0;i< json.length; i++){

		              return_data.push({
	                	'No': (i+1),
		                'Title' : json[i].Column1,
		                'Volume' : json[i].Column2,
		                'Issue' : json[i].Column3,
		                'Start Date' : json[i].Column4,
						'Stop Date' : json[i].Column5,
						'Frequency' : json[i].Column6,
						'Status' : json[i].Column7,
						'Issue No' : json[i].Column8,
						'Copy' : json[i].Column9,
						'Branch' :json[i].Column10,
						'Location' :json[i].Column11,
						'Item Category' :json[i].Column12,
						'Date' :json[i].Column13,
						'Status' :json[i].Column14,
						'Vendor' :json[i].Column15,
		            })
		            }
					
		            return return_data;
		          },
		     },//This is the end of the AJAX object from the example above
		     	columns    : [
					{'data': 'No'},
					{'data': 'Title'},
					{'data': 'Volume'},
					{'data': 'Issue'},
					{'data': 'Start Date', type: 'date-uk', targets: 0,},
					{'data': 'Stop Date', type: 'date-uk', targets: 0,},
					{'data': 'Frequency'},
					{'data': 'Status'},
					{'data': 'Issue No'},
					{'data': 'Copy'},
					{'data': 'Branch'},
					{'data': 'Location'},
					{'data': 'Item Category'},
					{'data': 'Date', type: 'date-uk', targets: 0,},
					{'data': 'Status'},
					{'data': 'Vendor'},
				],
			drawCallback: function ( settings ) {
				var api = this.api();
		        var rows = api.rows( {page:'current'} ).nodes();
		        var last=null;

				if(selection == 'D')	{
					api.column(1, {page:'current'} ).data().each( function ( group, i ) {
					 	
					     if (last !== group) {
						
					         var rowData = api.row(i).data();

				
					         $(rows).eq(i).before(
					         ///'<tr class="group"><td colspan="11"> Order No : ' + group + " - " + rowData['Vendor'] + '</td></tr>'
							'<tr class="group"><td><b>Title</b></td><td colspan="15"><b>'+group+'</b></td></td></tr>'
							+'<tr class="group"><td><b>Volume</b></td><td colspan="15"><b>'+rowData['Volume']+'</b></td></td></tr>'
							+'<tr class="group"><td><b>Issue</b></td><td colspan="15"><b>'+rowData['Issue']+'</b></td></td></tr>'
							+'<tr class="group"><td><b>Start Date</b></td><td colspan="15"><b>'+rowData['Start Date']+'</b></td></td></tr>'
							+'<tr class="group"><td><b>Stop Date</b></td><td colspan="15"><b>'+rowData['Stop Date']+'</b></td></td></tr>'
							+'<tr class="group"><td><b>Frequency</b></td><td colspan="15"><b>'+rowData['Frequency']+'</b></td></td></tr>'
							+'<tr class="group"><td><b>Status</b></td><td colspan="15"><b>'+rowData['Status']+'</b></td></td></tr>'
					     );
					         last = group;
					     }
					});
				}else if(selection == 'V' )	{
					api.column(15, {page:'current'} ).data().each( function ( group, i ) {
					 	
					     if (last !== group) {
						
					         var rowData = api.row(i).data();

				
					         $(rows).eq(i).before(
					         ///'<tr class="group"><td colspan="11"> Order No : ' + group + " - " + rowData['Vendor'] + '</td></tr>'
							'<tr class="group"><td><b>Vendor</b></td><td colspan="15"><b>'+group+'</b></td></td></tr>'
							
					     );
					         last = group;
					     }
					});
				}else if(selection == 'D, V')	{
					api.column(15, {page:'current'} ).data().each( function ( group, i ) {
					 	
					     if (last !== group) {
						
					         var rowData = api.row(i).data();

				
					         $(rows).eq(i).before(
					         ///'<tr class="group"><td colspan="11"> Order No : ' + group + " - " + rowData['Vendor'] + '</td></tr>'
							'<tr class="group"><td><b>Vendor</b></td><td colspan="15"><b>'+group+'</b></td></td></tr>'
							+'<tr class="group"><td><b>Title</b></td><td colspan="15"><b>'+rowData['Title']+'</b></td></td></tr>'
							+'<tr class="group"><td><b>Volume</b></td><td colspan="15"><b>'+rowData['Volume']+'</b></td></td></tr>'
							+'<tr class="group"><td><b>Issue</b></td><td colspan="15"><b>'+rowData['Issue']+'</b></td></td></tr>'
							+'<tr class="group"><td><b>Start Date</b></td><td colspan="15"><b>'+rowData['Start Date']+'</b></td></td></tr>'
							+'<tr class="group"><td><b>Stop Date</b></td><td colspan="15"><b>'+rowData['Stop Date']+'</b></td></td></tr>'
							+'<tr class="group"><td><b>Frequency</b></td><td colspan="15"><b>'+rowData['Frequency']+'</b></td></td></tr>'
							+'<tr class="group"><td><b>Status</b></td><td colspan="15"><b>'+rowData['Status']+'</b></td></td></tr>'
					     );
					         last = group;
					     }
					});
				}
				
			},
    	});


		if(selection == '' || selection == 'V' )	{
			t.column( [8] ).visible( false );
			t.column( [9] ).visible( false );
			t.column( [10] ).visible( false );
			t.column( [11] ).visible( false );
			t.column( [12] ).visible( false );
			t.column( [13] ).visible( false );
			t.column( [14] ).visible( false );
			t.column( [15] ).visible( false );
		}else if(selection == 'D')	{
			t.column( [1] ).visible( false );
			t.column( [2] ).visible( false );
			t.column( [3] ).visible( false );
			t.column( [4] ).visible( false );
			t.column( [5] ).visible( false );
			t.column( [6] ).visible( false );
			t.column( [7] ).visible( false );
			t.column( 15 ).visible( false );
		}else if(/*selection == 'V' || */selection == 'D, V')	{
			t.column( [1] ).visible( false );
			t.column( [2] ).visible( false );
			t.column( [3] ).visible( false );
			t.column( [4] ).visible( false );
			t.column( [5] ).visible( false );
			t.column( [6] ).visible( false );
			t.column( [7] ).visible( false );
			t.column( 15 ).visible( false );
		}

    });

	
	
});