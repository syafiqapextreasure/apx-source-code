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
	
	$("#startInput2, #endInput2").prop( "disabled", true);
	$("#startInput2, #endInput2").attr('maxlength','10');
	
	/////////////////--- EDITOR ------/////////
	
	////1) Hide selection criteria
	/////List all div   
	////.rangeDiv, .vendorSearch, .patronSearch, .orderModeDiv, .statusDiv, .acctDiv,
	////.deptDiv, .invstatDiv, .itemstatDiv, .recordselectionDiv, .claimstatDiv, .printerStat
	
	$(".patronSearch, .statusDiv, .acctDiv, .itemstatDiv, .invstatDiv").hide();
	$(".deptDiv, .recordselectionDiv, .claimstatDiv, .printerStat").hide();
	
	////2) Change Label
	///Date Label
	$(".datelabel").html("<b>Statistsic Report Date Range</b>");
	
	///Range Label
	$(".rangeLabel").html("<b>Order No Range</b>");
	
	///Status Label (ISSUUE OR ORDER)
	//$(".statusLabel").html("<b>Issue Status</b>")
	
	
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
	
	
	///patron id
	/*$("#lblPatronID").keyup(function(e){
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
	
	////a) Order No range
	$("#chkBoxRange").on('change', function () {
		var self = $(this);
	    if (self.is(":checked")) {
			$("#startInput, #endInput").prop( "disabled", false);
			$('#Reterive').prop('disabled', true);
			
			
	    } else {
			$("#startInput, #endInput").prop( "disabled", true);
			$('#Reterive').prop('disabled', false);
			$("#startInput, #endInput").val("");
	    }
	});
	
	////a2) invoice no range
	/*$("#chkBoxRangeInvoice").on('change', function () {
		var self = $(this);
	    if (self.is(":checked")) {
			$("#startInput2, #endInput2").prop( "disabled", false);
			$('#Reterive').prop('disabled', true);
			
			
	    } else {
			$("#startInput2, #endInput2").prop( "disabled", true);
			$('#Reterive').prop('disabled', false);
			$("#startInput2, #endInput2").val("");
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
	
	////c) checkbox multiselect status (ISSUE OR ORDER)
	/*$("#chkBoxStatus").on('change', function () {
	    var self = $(this);
	    if (self.is(":checked")) {
			$('#seStat').multiselect("enable");
			$('#Reterive').prop('disabled', true);
			$('#seStat').change(function() {
				if($("#seStat").val() != ""){
					$('#Reterive').prop('disabled', false);
				}else{
					$('#seStat').prop('disabled', true);
				}
			});	
	    } else {
			$('#seStat').multiselect("disable");
			$('#Reterive').prop('disabled', false);
			$("#seStat").multiselect("clearSelection")
 			$("#seStat").multiselect( 'refresh' ); 
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
					$('#seStat').prop('disabled', true);
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

	
	////f) checkbox multiselect inv status
	/*$("#chkBoxInvStatus").on('change', function () {
	    var self = $(this);
	    if (self.is(":checked")) {
			$('#seinvstat').multiselect("enable");
			$('#Reterive').prop('disabled', true);
			$('#seinvstat').change(function() {
				if($("#seinvstat").val() != ""){
					$('#Reterive').prop('disabled', false);
				}else{
					$('#Reterive').prop('disabled', true);
				}
			});
	    } else {
			$('#seinvstat').multiselect("disable");
			$('#Reterive').prop('disabled', false);
			$("#seinvstat").multiselect("clearSelection")
 			$("#seinvstat").multiselect( 'refresh' ); 
	    }
	});*/

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
		
		//1 Invoice Range
		/*var chkRange2 = $('#chkBoxRangeInvoice:checked').val();
		var rangeFirstVal2; 
		var rangeLastVal2;
		
		if(chkRange2 == "range2"){
    		rangeFirstVal2 = $('input[name=startInput2]').val();
			rangeLastVal2 = $('input[name=endInput2]').val();
    	}else{
    		rangeFirstVal2 = '';
			rangeLastVal2 = '';
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
		
	
		//3 ordermode
		var orderMode = $("#ordermode").val(); 
		if(orderMode != ""){
		}else{
			orderMode = 0;
		}
		
		/*//4 status
		var seStat = $("#seStat").val(); 
		if(seStat != ""){
		}else{
			seStat = 0;  
		}
		
		//5 account
		var account = $("#acct").val(); 
		if(account != ""){
		}else{
			account = 0;
		}
		
		//6 department
		var department = $("#dept").val(); 
		if(department != ""){
		}else{
			department = 0;
		}
		*/
		//7 Invoice Status
		/*var seinvstat = $("#seinvstat").val(); 
		if(seinvstat != ""){
		}else{
			seinvstat = 0;
		}*/
		
		//8 Record Selection
		/*var rbrecorselecion = $("input[name='rbRrcdSelection']:checked").val();
		var recordselecion = $("input[name='radioRecordselection']:checked").val();
		
		if(rbrecorselecion==undefined){
			recorselecion = 0;
		}*/

		//9 Claim Status
		/*var rbclaimstatus = $("input[name='rbClaimStat']:checked").val();
		var claimstatus = $("input[name='radioclaimstat']:checked").val();
		
		if(rbclaimstatus==undefined){
			claimstatus = 0;
		}*/
		

		/////////////////--- EDITOR ------/////////		
		
		/////result display
		var t = $('#reportTable').DataTable( {
			dom: 'Bfrtip',
            buttons: [
						{
					  		extend: 'excelHtml5',
					        filename: 'WILMU_SerSubsDueForRenewal',
					        title: 'Subscriptions Due For Renewal Report ' +sDate +eDate,
					                
					 	},
						{
					    	text: '<i class="fa fa-file-pdf-o"></i> PDF',
							extend: 'pdfHtml5',
							filename: 'WILMU_SerSubsDueForRenewal',
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
										doc.content[1].table.body[i][6].alignment = 'right';
						               	//doc.content[1].table.body[i][10].alignment = 'right';
						           };
								
							 	//doc.content[1].table.widths = [ '4%', '10%', '10%', '10%', '10%', '10%', '10%', '16%', '10%', '10%']; 
							
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
						                                   { text: 'Subscriptions Due For Renewal Report '+sDate +eDate+ '\n',bold:true,fontSize:13,alignment: 'center'}						                        ],
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
		                	filename: 'WILMU_SerSubsDueForRenewal',
		                	//title: 'Subscriptions Due For Renewal Report By ',
							customize: function(doc){
										
										var split_csv = doc.split("\n");
										//alert(split_csv)
										$.each(split_csv.slice(1), function (index, csv_row) {
											var csv_cell_array = csv_row.split('","');
											csv_cell_array[1].replace("", (index+1));
										});
										
										return "Subscriptions Due For Renewal Report "+sDate + eDate +"\n\n" + doc;
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
		    	url: "ResultSubsDueForRenewReport",
		        data : {currencyFormat :  currencyFormat, 
						startSentDate : startSentDate, endSentDate : endSentDate, 
						brnch : JSON.stringify(brnch), allbranch : allbranch,
						//// Editer/////
						rangeFirstVal : rangeFirstVal, rangeLastVal : rangeLastVal,
						//rangeFirstVal2 : rangeFirstVal2, rangeLastVal2 : rangeLastVal2,
						vendor : vendor, //patronID : patronID,
						//orderMode : JSON.stringify(orderMode),
						//seStat : JSON.stringify(seStat),
						//account : JSON.stringify(account),
						//department : JSON.stringify(department),
						//seinvstat : JSON.stringify(seinvstat),
						//rbrecorselecion : rbrecorselecion, claimstatus : claimstatus,
						//// Editer///// 


						},
		        type: "GET",
		        dataSrc: function (json) {
		            var return_data = new Array();
		            
		            for(var i=0;i< json.length; i++){

		              return_data.push({
		                'No': (i+1),
		                'Title' : json[i].Column1,
		                'Previous Order No' : json[i].Column2,
		                'Vendor' : json[i].Column3,
		                'Start Date' : json[i].Column4,
						'Stop Date' : json[i].Column5,
						'Order Amount(RM)' : json[i].Column6,
		            })
		            }
					
		            return return_data;
		          },
		     },//This is the end of the AJAX object from the example above
		     	columns    : [
					{'data': 'No'},
					{'data': 'Title'},
					{'data': 'Previous Order No'},
					{'data': 'Vendor'},
					{'data': 'Start Date', type: 'date-uk', targets: 0,},
					{'data': 'Stop Date', type: 'date-uk', targets: 0,},
					{'data': 'Order Amount(RM)', className: "text-right", render: $.fn.dataTable.render.number(',', '.', currencyFormat, '')},
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

 					var intVal2 = function ( i ) {

				
		                return typeof i === 'string' ?

							
							

							//when copy only
							/////i.substring((i.indexOf('('))).replace(/[^0-9.]/g, '')*1 :
							/// when set and copy
							((i.substring(0, i.indexOf('set'))+1).charAt(0)) * (i.substring((i.indexOf('('))).replace(/[^0-9.]/g, ''))*1:
					
							//////i*1 :
		                    // i.replace(/[^0-9.]/g, '')*1 :
		                    typeof i === 'number' ?
		                        i : 0;
		            };
		 
					///charge
		            // Total over all pages
		            totalLocalPrice = api
		                .column( 6 )
		                .data()
		                .reduce( function (a, b) {
		                    return intVal(a) + intVal(b);
		                }, 0 );


		 
		            // Update footer
		           $( api.column( 6 ).footer() ).html(
		                numFormat(totalLocalPrice)
		            );

					$("#allTotalAmount").text(" "+numFormat(totalLocalPrice));

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

function newFunction(i) {
    typeof i === 'number' ?
        i : 0;
}
