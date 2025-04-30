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
	
	$.get('ResultGlflag2', {
		 	id : "CURRENCYFORMAT",
		 	}, function(responseJson) {
			$("#setupCurrency").val(responseJson);
	 }); 

	$("#startInput, #endInput").attr('maxlength','10');
	
	
	///////startup page
	$('#input-startDate, #input-startDate2').datepicker({
		//startDate : today,
		format: "dd/mm/yyyy",
		todayBtn: true,
		autoclose: true,
		todayHighlight: true,
		minDate: 0  
	});
	
	///////////////////// input-endDate set Current Date  ////////////////////
	$('#input-endDate, #input-endDate2').val(moment(today).format("DD/MM/YYYY"));
	$('#input-endDate, #input-endDate2').datepicker({
		format: "dd/mm/yyyy",
		todayBtn: true,
		autoclose: true,
		todayHighlight: true,
	});
	
	$("#startInput, #endInput").on("keypress keyup blur",function (event) {    
        $(this).val($(this).val().replace(/[^\d].+/, ""));
         if ((event.which < 48 || event.which > 57)) {
             event.preventDefault();
         }
    });

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
	
	
	$('#ordermode, #dept, #seinvstat, #seStat').multiselect({
    	allSelectedText: 'All',
   		maxHeight: 200,
   		includeSelectAllOption: true,
		numberDisplayed: 1,
	});
	
	$("#ordermode, #dept, #seinvstat, #seStat").multiselect("disable");
	
	
	$('#input-startDate, #input-endDate, #input-startDate2, #input-endDate2').prop('disabled', true);
	$("#startInput, #endInput").prop( "disabled", true);
	$("#input-vendorCode").prop( "disabled", false);	
	$('.vendorCode').prop('disabled', false);
	//$("#chkBoxOrderMode, #chkBoxDepartment, #chkBoxStatus, #chkBoxInvStatus").prop( "disabled", true);
	///$("#Reterive").prop( "disabled", true);
	//$('input[name=filterby]').attr("disabled",true);
	
	
	//////table setup
	$('#reportTableOrder, #reportTableInvoice').DataTable( {
		destroy: true,
		searching: false,
		bLengthChange: false,
		autoWidth: false,
		responsive: true,
	});
	
	
	$(".invstatDiv, .divTableInvoice").hide();
	$(".rangeLabel").html("<b> Order Number Range</b>");
	$(".datelabel").html("<b> Created Date Range</b>");
	//////////////////////////////////
	
	$('input[type=radio][name=optSelection]').change(function() {
		
		var vendorlength = $(".div-vendorName").text().length;		
		if(vendorlength > 1){
			$("#Reterive").prop( "disabled", false);
		}else{
			$("#Reterive").prop( "disabled", true);
		}
		
	    if (this.value == 'orderno') {
			$(".rangeLabel").html("<b>Order Number Range</b>");
			$(".divTableInvoice, .invstatDiv").hide();
			$(".divTableOrder, .statusDiv").show();
			$(".datelabel").html("<b>Created Date Range</b>");
			$(".dateDiv2").show();
			$('#reportTableOrder, #reportTableInvoice').dataTable().fnClearTable();
			$("#startInput, #endInput").val("");
			$("#input-startDate, #input-endDate, #input-startDate2, #input-endDate2").val("");
			$('#input-endDate, #input-endDate2').val(moment(today).format("DD/MM/YYYY"));
			$("#startInput, #endInput").prop( "disabled", true);
			$('#input-startDate, #input-endDate').prop('disabled', true);
			$('#input-startDate2, #input-endDate2').prop('disabled', true);
			$("#startInput, #endInput").attr('maxlength','10');
			$('input[type=radio][name=filterby]').prop('checked', false);
			
			$("#startInput, #endInput").on("keypress keyup blur",function (event) {    
		        $(this).val($(this).val().replace(/[^\d].+/, ""));
		         if ((event.which < 48 || event.which > 57)) {
		             event.preventDefault();
		         }
		    });

			$('#chkBoxInvStatus').prop('checked', false).removeAttr('checked');
			$('#seinvstat').multiselect("disable");
			$('#Reterive').prop('disabled', false);
			$("#seinvstat").multiselect("clearSelection")
 			$("#seinvstat").multiselect( 'refresh' ); 
	    }
	    else if (this.value == 'invoiceno') {
			$("#startInput, #endInput").removeAttr("maxlength");
			$(".rangeLabel").html("<b>Invoice Number Range</b>");
			$(".divTableInvoice, .invstatDiv").show();
			$(".divTableOrder, .statusDiv").hide();
			$(".datelabel").html("<b>Invoice Date Range</b>");
			$(".dateDiv2").hide();
			$('#reportTableOrder, #reportTableInvoice').dataTable().fnClearTable();
			$("#startInput, #endInput").val("");
			$("#input-startDate, #input-endDate, #input-startDate2, #input-endDate2").val("");
			$('#input-endDate, #input-endDate2').val(moment(today).format("DD/MM/YYYY"));
			$("#startInput, #endInput").prop( "disabled", true);
			$('#input-startDate, #input-endDate').prop('disabled', true);
			$('#input-startDate2, #input-endDate2').prop('disabled', true);
			$('input[type=radio][name=filterby]').prop('checked', false);
			$( "#startInput, #endInput").unbind();
			
			$('#chkBoxStatus').prop('checked', false).removeAttr('checked');
			$('#seStat').multiselect("disable");
			$('#Reterive').prop('disabled', false);
			$("#seStat").multiselect("clearSelection")
 			$("#seStat").multiselect( 'refresh' ); 
	    }
	});
	
	/////////
	$('input[type=radio][name=filterby]').change(function() {
		
		 if (this.value == 'range') {
			$("#startInput, #endInput").prop( "disabled", false);
			$('#input-startDate, #input-endDate, #input-startDate2, #input-endDate2').prop('disabled', true);
			$('#input-startDate, #input-endDate').val('');
			$('#input-endDate, #input-endDate2').val(moment(today).format("DD/MM/YYYY"));
		 }else if (this.value == 'date') {
			$("#startInput, #endInput").prop( "disabled", true);
			$('#input-startDate, #input-endDate').prop('disabled', false);
			$('#input-startDate2, #input-endDate2').prop('disabled', true);
			$("#startInput, #endInput").val("")
		 }else if (this.value == 'orderdate') {
			$("#startInput, #endInput").prop( "disabled", true);
			$('#input-startDate, #input-endDate').prop('disabled', true);
			$('#input-startDate2, #input-endDate2').prop('disabled', false);
			$("#startInput, #endInput").val("")
		 }
	});

	
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

	
	////g) checkbox multiselect inv status
	$("#chkBoxInvStatus").on('change', function () {
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
	/////////////////--- EDITOR ------/////////
	
	
	//////////////////////////////////////////////////////////////////////////
	////////////////////////btn Reterive Click ///////////////////////////////
	//////////////////////////////////////////////////////////////////////////
	$('#Reterive').click(function(){
		
		
		$('#reportTableOrder, #reportTableInvoice').dataTable().fnClearTable();
		
		var currencyFormat = $("#setupCurrency").val();
		
		var vendor = $("#input-vendorCode").val();  
		
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
		
		///ordermode
		var orderMode = $("#ordermode").val(); 
		if(orderMode != ""){
		}else{
			orderMode = 0;
		}
		
		//status
		var status = $("#seStat").val(); 
		if(status != ""){
		}else{
			status = 0;  
		}  
		
		//department
		var department = $("#dept").val(); 
		if(department != ""){
		}else{
			department = 0;
		}
		
		//Invoice Status
		var seinvstat = $("#seinvstat").val(); 
		if(seinvstat != ""){
		}else{
			seinvstat = 0;
		}
		
		//Range
		var filterby = $("input[name='filterby']:checked").val();
		
		if(filterby==undefined){
			filterby = "0";
		}

		
		var valuestart;
		var valueend; 
		var sDate, eDate;
		
		var filterbyLabel = $('input[name="filterby"]:checked').parent('label').text(); 
		

		if(filterby == "range"){
    		valuestart = $('input[name=startInput]').val();
			sDate = " from "+$('input[name=startInput]').val();
			
			valueend = $('input[name=endInput]').val();
			eDate = " until " +$('input[name=endInput]').val(); 
    	}else if(filterby == "date"){
			var startSentDate = moment($("#input-startDate").val(), 'DD/MM/YYYY').format("YYYYMMDD");
			var endSentDate = moment($("#input-endDate").val(), 'DD/MM/YYYY').format("YYYYMMDD");
			
			if(startSentDate=="Invalid date"){
				startSentDate = '';
				sDate = '';
			}else{
				sDate = " from " +$("#input-startDate").val();
			}
			
			if(endSentDate=="Invalid date"){
				startSentDate = '';
				eDate = '';
			}else{
				eDate =  " until " +$("#input-endDate").val();
			}
		
    		valuestart = startSentDate;
			valueend = endSentDate;
    	}else if(filterby == "orderdate"){
			var startSentDate2 = moment($("#input-startDate2").val(), 'DD/MM/YYYY').format("YYYYMMDD");
			var endSentDate2 = moment($("#input-endDate2").val(), 'DD/MM/YYYY').format("YYYYMMDD");
			
			if(startSentDate2=="Invalid date"){
				startSentDate2 = '';
				sDate = '';
			}else{
				sDate = " from " +$("#input-startDate2").val();
			}
			
			if(endSentDate2=="Invalid date"){
				startSentDate2 = '';
				eDate = '';
			}else{
				eDate =  " until " +$("#input-endDate2").val();
			}
		
    		valuestart = startSentDate2;
			valueend = endSentDate2;
    	}else{
    		valuestart = "";
			valueend = "";
    	}


		var optSelection = $("input[name='optSelection']:checked").val();
		
		
		/////////////////--- EDITOR ------/////////		
	
		//ordermode table
		
		if(optSelection == "orderno"){
			
			var tOrder = $('#reportTableOrder').DataTable( {
			dom: 'Bfrtip',
            buttons: [
						{
					  		extend: 'excelHtml5',
					        filename: 'WILMU_SeVendorReport',
					        title: 'Vendor Report ' +vendor +" : " +$(".div-vendorName").text() + ", "+filterbyLabel 
									+sDate +eDate,
					                
					 	},
						{
					    	text: '<i class="fa fa-file-pdf-o"></i> PDF',
							extend: 'pdfHtml5',
							charset: "utf-8",
							bom : "true",
							filename: 'WILMU_SeVendorReport',
							orientation: 'landscape', //portrait
							pageSize: 'A4', //A3 , A5 , A6 , legal , letter
							footer: true,
							exportOptions: {
								search: 'applied',
								order: 'applied'
							},
							customize: function (doc) {
								
								doc.styles.tableHeader.alignment = 'left';
								
						           /*for (i = 0; i < rowCount; i++) {
										doc.content[1].table.body[i][9].alignment = 'right';
						               	doc.content[1].table.body[i][10].alignment = 'right';
						           };*/
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
										doc.content[1].table.body[i][8].alignment = 'right';
										doc.content[1].table.body[i][9].alignment = 'right';
						               	doc.content[1].table.body[i][10].alignment = 'right';
						           };
							 	//doc.content[1].table.widths = [ '8%', '55%', '12%', '12%', '12%']; 
							doc.content.push( {
						                        //margin: [ 0, 0, 0, 12 ],
						                        alignment: 'right',
												bold:true,
								                   text: [
								                         	$('.divtotal').text(), 
								                        ]
	
											 } );
											//Remove the title created by datatTables  
											doc.content.splice(0, 1, {
						                        text: [
						                                   { text: 'Vendor Report ' +vendor +" : " +$(".div-vendorName").text() + ", "+filterbyLabel 
															+sDate +eDate+ '\n',bold:true,fontSize:13,alignment: 'center'}						                        ],
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
		                	filename: 'WILMU_SeVendorReport',
		                	//title: 'Vendor Report By ',
							charset: "utf-8",
							bom : "true",
							customize: function(doc){
										
										var split_csv = doc.split("\n");
										//alert(split_csv)
										$.each(split_csv.slice(1), function (index, csv_row) {
											var csv_cell_array = csv_row.split('","');
											csv_cell_array[1].replace("", (index+1));
										});
										
										return 'Vendor Report ' +vendor +" : " +$(".div-vendorName").text() + ", "+filterbyLabel 
											+sDate +eDate+"\n\n" + doc;
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
		    	url: "ResultSeVendorReport",
		        data : {currencyFormat :  currencyFormat, 
						vendor : vendor, brnch : JSON.stringify(brnch),
						orderMode : JSON.stringify(orderMode),
						status : JSON.stringify(status),
						department : JSON.stringify(department),
						seinvstat : JSON.stringify(seinvstat),
						filterby : filterby, 						
						valuestart : valuestart, valueend : valueend, 
						optSelection : optSelection,
						allbranch : allbranch
						 
						},
		        type: "GET",
		        dataSrc: function (json) {
		            var return_data = new Array();
		            
		            for(var i=0;i< json.length; i++){

		              return_data.push({
		                'No': (i+1),
						'Order No' : json[i].Column1,
						'Order Created Date' : json[i].Column2,
						'Order Date' : json[i].Column3,
						'Invoice No' : json[i].Column4,
						'Order Mode' : json[i].Column5,
						'Order Status' : json[i].Column6,
		                'Control No' : json[i].Column7,
						'PO/refer No' : json[i].Column8,
						'Amount': json[i].Column9,
		            })
		            }					
		            return return_data;
		          },
		     },//This is the end of the AJAX object from the example above
		     	columns    : [
					{'data': 'No'},
					{'data': 'Order No'},
					{'data': 'Order Created Date', type: 'date-uk', targets: 0,},
					{'data': 'Order Date', type: 'date-uk', targets: 0,},
					{'data': 'Invoice No'},
					{'data': 'Order Mode'},
					{'data': 'Order Status'},
					{'data': 'Control No'},
					{'data': 'PO/refer No'},
					{'data': 'Amount', className: "text-right", render: $.fn.dataTable.render.number(',', '.', currencyFormat, '')},	
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
		                .column( 9 )
		                .data()
		                .reduce( function (a, b) {
		                    return intVal(a) + intVal(b);
		                }, 0 );
		 
		 
		            // Update footer
		          /* $( api.column( 9 ).footer() ).html(
		                numFormat(totalLocalPrice)
		            );
		        }
    	});*/


					$("#allTotalAmount").text(" "+numFormat(totalLocalPrice));
					
				/*	var finalQuantity;
					if(totalQuantity > 1){
						finalQuantity = totalQuantity + " copies";
					}else{
						finalQuantity = totalQuantity + " copy";
					}
					
					$("#allTotalQty").html(" "+finalQuantity +" &emsp; "); */
			}
					
		 });
		}else if(optSelection == "invoiceno"){
			
			var tInvoice = $('#reportTableInvoice').DataTable( {
			dom: 'Bfrtip',
            buttons: [
						{
					  		extend: 'excelHtml5',
					        filename: 'WILMU_AcqVendorReport',
					        title: 'Vendor Report ' +vendor +" : " +$(".div-vendorName").text(),
					                
					 	},
						{
					    	text: '<i class="fa fa-file-pdf-o"></i> PDF',
							extend: 'pdfHtml5',
							filename: 'WILMU_AcqVendorReport',
							orientation: 'landscape', //portrait
							pageSize: 'A4', //A3 , A5 , A6 , legal , letter
							footer: true,
							exportOptions: {
								search: 'applied',
								order: 'applied'
							},
							customize: function (doc) {
								
								//doc.styles.tableHeader.alignment = 'left';
								
								 var rowCount = doc.content[1].table.body.length;
						           for (i = 0; i < rowCount; i++) {
										doc.content[1].table.body[i][9].alignment = 'left';
						               	doc.content[1].table.body[i][14].alignment = 'right';
						           };
							
							 	//doc.content[1].table.widths = [ '8%', '55%', '12%', '12%', '12%']; 
							//doc.content[1].table.widths = [ '2%', '6%', '6%', '8%', '7%', '7%', '7%', '6%', '6%', '6%', '10%', '7%', '7%', '10%', '5%'];
							//doc.content[1].table.widths = [ '2%', '4%', '9%', '6%', '6%', '6%', '6%', '9%', '9%', '6%', '9%', '5%', '9%', '9%', '5%'];
							///doc.content[1].table.widths = [ '2.5%', '5%', '8.5%', '6%', '6%', '6%', '6%', '8.5%', '8.5%', '6%', '9%', '4%', '9%', '9%', '6%'];
							//doc.content[1].table.widths = [ '2.5%', '5.3%', '8.2%', '6%', '6%', '6%', '6%', '8.5%', '8.5%', '6%', '9%', '4%', '9%', '9%', '6%'];
							//doc.content[1].table.widths = [ '2.5%', '5.3%', '8.2%', '6.4%', '6%', '6%', '6%', '8.5%', '8.5%', '6%', '9%', '4%', '8.2%', '8.7%', '6.7%'];
							//doc.content[1].table.widths = [ '2.5%', '5.3%', '8.2%', '6.4%', '6%', '6%', '6%', '8.5%', '8.5%', '6%', '8.5%', '4%', '8.2%', '8.7%', '7.2%'];
							doc.content[1].table.widths = [ '2.5%', '5.3%', '8.2%', '6.4%', '6%', '6%', '6%', '8.5%', '8.5%', '6%', '8.5%', '4%', '8.2%', '8.2%', '7.7%'];
											//Remove the title created by datatTables 


											doc.content.push( {
						                        //margin: [ 0, 0, 0, 12 ],
						                        alignment: 'right',
												bold:true,
								                   text: [
								                         	$('.divtotalInvoice').text(), 
								                        ]
	
											 } );


											doc.content.splice(0, 1, {
						                        text: [
						                                   { text: 'Vendor Report ' +vendor +" : " +$(".div-vendorName").text()+ '\n',bold:true,fontSize:13,alignment: 'center'}						                        ],
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
		                	filename: 'WILMU_AcqVendorReport',
		                	//title: 'Vendor Report By ',
							customize: function(doc){
										
										var split_csv = doc.split("\n");
										//alert(split_csv)
										$.each(split_csv.slice(1), function (index, csv_row) {
											var csv_cell_array = csv_row.split('","');
											csv_cell_array[1].replace("", (index+1));
										});
										
										return 'Vendor Report ' +vendor +" : " +$(".div-vendorName").text() +"\n\n" + doc;
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
		    	url: "ResultSeVendorReport",
		        data : {currencyFormat :  currencyFormat, 
						vendor : vendor, brnch : JSON.stringify(brnch),
						orderMode : JSON.stringify(orderMode),
						status : JSON.stringify(status),
						department : JSON.stringify(department),
						seinvstat : JSON.stringify(seinvstat),
						filterby : filterby, 						
						valuestart : valuestart, valueend : valueend, 
						optSelection : optSelection,
						allbranch : allbranch
						},
		        type: "GET",
		        dataSrc: function (json) {
		            var return_data = new Array();
		            
		            for(var i=0;i< json.length; i++){

		              return_data.push({
		                'No': (i+1),
		                'Invoice No' : json[i].Column1,
		                'Invoice Date' : json[i].Column2,
		                'Invoice Status' : json[i].Column3,
		                'Control No' : json[i].Column4,
						'PO/refer No' : json[i].Column5,
						'Order No' : json[i].Column6,
						'Order Created Date' : json[i].Column7,
						'Order Date' : json[i].Column8,
						'Order Mode' : json[i].Column9,
						'Order Status' : json[i].Column10,
						'Pay Ref No': json[i].Column11,
						'Pay Request Date': json[i].Column12,
						'Pay Record Date': json[i].Column13,
						'Amount': json[i].Column14,


		            })
		            }
					
		            return return_data;
		          },
		     },//This is the end of the AJAX object from the example above
		     	columns    : [
					{'data': 'No'},
					{'data': 'Invoice No'},
					{'data': 'Invoice Date', type: 'date-uk', targets: 0,},
					{'data': 'Invoice Status'},
					{'data': 'Control No'},
					{'data': 'PO/refer No'},
					{'data': 'Order No'},
					{'data': 'Order Created Date', type: 'date-uk', targets: 0,},
					{'data': 'Order Date', type: 'date-uk', targets: 0,},
					{'data': 'Order Mode'},
					{'data': 'Order Status'},
					{'data': 'Pay Ref No'},
					{'data': 'Pay Request Date', type: 'date-uk', targets: 0,},
					{'data': 'Pay Record Date', type: 'date-uk', targets: 0,},
					{'data': 'Amount', className: "text-right", render: $.fn.dataTable.render.number(',', '.', currencyFormat, '')},	
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
		                .column( 14 )
		                .data()
		                .reduce( function (a, b) {
		                    return intVal(a) + intVal(b);
		                }, 0 );


		           $("#allTotalAmountInvoice").text(" "+numFormat(totalLocalPrice));
		 
		 
		            // Update footer
		           /*$( api.column( 14 ).footer() ).html(
		                numFormat(totalLocalPrice)
		            );*/
		        }
    	});
			
		}
			
	
		
		
		
		
		
		
		
	});	
		

		/////////////////--- EDITOR ------/////////		
		
		/////result display
		
		
		/*t.on('order.dt search.dt', function () {
			 t.column(0, {search:'applied', order:'applied'}).nodes().each( function (cell, i) {
				cell.innerHTML = i+1;
				t.cell(cell).invalidate('dom');
			 });
	   }).draw();*/
		
    	


	
	
});