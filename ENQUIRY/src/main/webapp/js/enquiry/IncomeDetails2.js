 $(document).ready(function() {
	
	//$('#example-getting-started').multiselect();
	$('#chargeData, #payData, #overrideData, #otherData, #brnchData, #paymodeData, #patronCate, #brnchCat').multiselect({
    	allSelectedText: 'All',
   		maxHeight: 200,
   		includeSelectAllOption: true
	});
	
	
	
	$("#chargeData, #payData, #overrideData, #otherData, #brnchData, #paymodeData, #patronCate, #brnchCat").multiselect("disable");
	///$('#patronCate').prop('disabled', 'disabled');
	$('#lblPatronID').prop('readonly', true);
	$('.searchheadLib').prop('disabled', true);
	$('#incomeReterive').prop('disabled', 'disabled')
	
	///upon realod
	///////////////////// input-startDate set Current Date  //////////////////
	var today = new Date();  
	$('#txtFrom').datepicker({
		//startDate : today,
		format: "dd/mm/yyyy",
		todayBtn: true,
		autoclose: true,
		todayHighlight: true,
	});
	
	///////////////////// input-endDate set Current Date  ////////////////////
	$('#txtTo').val(moment(today).format("DD/MM/YYYY"));
	$('#txtTo').datepicker({
		format: "dd/mm/yyyy",
		todayBtn: true,
		autoclose: true,
		todayHighlight: true,
	});
	
	///
	//////table setup
	$('#table-IncomeDetails').DataTable( {
		destroy: true,
		searching: false,
		bLengthChange: false,
		autoWidth: false,
		responsive: true,
		columnDefs:[
            {visible:false,targets:0},
  			/*{visible:false,targets:11}*/
        ],
	});
	
	/*$('.sQ1').on('change', function(){
		 ////Retrieve button control
		if(($('#chkPatronCategory:checked').val() != undefined||$('#chkOfficer:checked').val() != undefined||$('#chkBranch:checked').val() != undefined||$('#chkPaymentMode:checked').val() != undefined)&&($('#chkCharge:checked').val() != undefined||$('#chkPayment:checked').val() != undefined||$('#chkOverride:checked').val() != undefined||$('#chkOthers:checked').val() != undefined)){
			$('#incomeReterive').prop('disabled', false)
		}else{
			$('#incomeReterive').prop('disabled', 'disabled')
		}
		});
	$('.sQ2').on('change', function(){
		  ////Retrieve button control
		if(($('#chkPatronCategory:checked').val() != undefined||$('#chkOfficer:checked').val() != undefined||$('#chkBranch:checked').val() != undefined||$('#chkPaymentMode:checked').val() != undefined)&&($('#chkCharge:checked').val() != undefined||$('#chkPayment:checked').val() != undefined||$('#chkOverride:checked').val() != undefined||$('#chkOthers:checked').val() != undefined)){
			$('#incomeReterive').prop('disabled', false)
		}else{
			$('#incomeReterive').prop('disabled', 'disabled')
		}
		});
	$('.sQ3').on('change', function(){
		  ////Retrieve button control
		if(($('#chkPatronCategory:checked').val() != undefined||$('#chkOfficer:checked').val() != undefined||$('#chkBranch:checked').val() != undefined||$('#chkPaymentMode:checked').val() != undefined)&&($('#chkCharge:checked').val() != undefined||$('#chkPayment:checked').val() != undefined||$('#chkOverride:checked').val() != undefined||$('#chkOthers:checked').val() != undefined)){
			$('#incomeReterive').prop('disabled', false)
		}else{
			$('#incomeReterive').prop('disabled', 'disabled')
		}
		});*/
		
	
		
		
		
	
	///open modal
	$('.searchheadLib').on('click',function(){
		//if (event.keyCode === 10 || event.keyCode === 13) {
	       
			var url = "Modal_PatrSearch";
			$.get(url,function(data){
				$("#Modal_PatrSearchContent").html(data);
			});
			
		//}
	});
	
	$("#chkPatronCategory").on('change', function () {
	    var self = $(this);
	    if (self.is(":checked")) {
			$('#patronCate').multiselect("enable");
			$('#incomeReterive').prop('disabled', true);
			$('#patronCate').change(function() {
				if($("#patronCate").val() != ""){
					$('#incomeReterive').prop('disabled', false);
				}else{
					$('#incomeReterive').prop('disabled', true);
				}
			});	
	    } else {
			$('#patronCate').multiselect("disable");
			$('#incomeReterive').prop('disabled', false);
			$("#patronCate").multiselect("clearSelection");
 			$("#patronCate").multiselect( 'refresh' ); 
	    }
	});
	

	
	//////////////radio bitton for code
	$('input[type=radio][name=trancode]').on('change', function() {
		$('#incomeReterive').prop('disabled', true);
		switch ($(this).val()) {
		    case 'charge':
				$('#chargeData').multiselect("enable");
			  	$("#payData, #overrideData, #otherData").multiselect("disable");
			  	$("#payData, #overrideData, #otherData").multiselect("clearSelection");
			  	$('#chargeData').change(function() {
					if($("#chargeData").val() != ""){
						$('#incomeReterive').prop('disabled', false);
					}else{
						$('#incomeReterive').prop('disabled', true);
					}
				});
		      break;
		    case 'payment':
				$('#payData').multiselect("enable");
			  	$("#chargeData, #overrideData, #otherData").multiselect("disable");
			  	$("#chargeData, #overrideData, #otherData").multiselect("clearSelection");
			  	$('#payData').change(function() {
					if($("#payData").val() != ""){
						$('#incomeReterive').prop('disabled', false);
					}else{
						$('#incomeReterive').prop('disabled', true);
					}
				});
		      break;
			case 'override':
				$('#overrideData').multiselect("enable");
			  	$("#chargeData, #payData, #otherData").multiselect("disable");
			  	$("#chargeData, #payData, #otherData").multiselect("clearSelection");
			  	$('#overrideData').change(function() {
					if($("#overrideData").val() != ""){
						$('#incomeReterive').prop('disabled', false);
					}else{
						$('#incomeReterive').prop('disabled', true);
					}
				});
		      break;
			case 'other':
				$('#otherData').multiselect("enable");
			  	$("#chargeData, #payData, #overrideData").multiselect("disable");
			  	$("#chargeData, #payData, #overrideData").multiselect("clearSelection");
			  	$('#otherData').change(function() {
					if($("#otherData").val() != ""){
						$('#incomeReterive').prop('disabled', false);
					}else{
						$('#incomeReterive').prop('disabled', true);
					}
				});
		      break;
		  }
	});
	
	
	
	//////////////radio button for Branch Cat
	////$('input[name=brancode][value=officerB]').attr('checked', true); 
	
	
	/*$('input[type=radio][name=brancode]').on('change', function() {
		switch ($(this).val()) {
		    case 'officerB':
		      break;
		    case 'patronB':
		      break;
			case 'itemB':
		      break;
		  }
	});*/
	
	//////Officer ID
	$("#chkOfficer").on('change', function () {
	    var self = $(this);
	    if (self.is(":checked")) {
			$('#lblPatronID').prop('readonly', false);
			$('.searchheadLib').prop('disabled', false);
	    } else {
			$('#lblPatronID').prop('readonly', true);
			$('.searchheadLib').prop('disabled', true);
	    }
	});
	
	//////Branch
	/*$("#chkBranch").on('change', function () {
	    var self = $(this);
	    if (self.is(":checked")) {
			$('#brnchData').multiselect("enable");
	    } else {
			$('#brnchData').multiselect("disable");
	    }
	});
	
	//////Branch
	$("#chkCatBranch").on('change', function () {
	    var self = $(this);
	    if (self.is(":checked")) {
			$('#brnchCat').multiselect("enable");
	    } else {
			$('#brnchCat').multiselect("disable");
	    }
	});*/
	
	//////Payment Mode
	$("#chkPaymentMode").on('change', function () {
	    var self = $(this);
	    if (self.is(":checked")) {
			$('#paymodeData').multiselect("enable");
			$('#incomeReterive').prop('disabled', true);
			$('#paymodeData').change(function() {
				if($("#paymodeData").val() != ""){
					$('#incomeReterive').prop('disabled', false);
				}else{
					$('#incomeReterive').prop('disabled', true);
				}
			});	
	    } else {
			$('#paymodeData').multiselect("disable");
			$('#incomeReterive').prop('disabled', false);
			$("#paymodeData").multiselect("clearSelection")
 			$("#paymodeData").multiselect( 'refresh' ); 
	    }
	});
	
	/*$("#chkPaymentMode").on('change', function () {
	    var self = $(this);
	    if (self.is(":checked")) {
			$('#paymodeData').multiselect("enable");
	    } else {
			$('#paymodeData').multiselect("disable");
	    }
	});*/
	
	$("#lblPatronID").keydown(function(e){ 
		var PatronID = $("#lblPatronID").val();
		
		var code = e.keyCode || e.which;
		if(code == '9' ||code == '13' ){
			$.get('getLoadPatronDetail', {
	        	id : PatronID
			 	}, function(responseJson) {
				if(responseJson != null){
					if(responseJson==''){
						messageBox("036","",""); 
						$(".lblName").empty();
						$("#lblPatronID").val("");
						setTimeout(function(){
					        $('#lblPatronID')[0].focus();
					    }, 3000);
					}else{
						$.each(responseJson, function(key,value) {
							$(".lblName").text(value['Name']);
						});
					}
				}
			});			
			event.preventDefault();	
		}else if(code == '8'){
			 $(".lblName").empty();
		}
	});
	
	
	//////////////////////////////////////////////////////////////////////////
	////////////////////////btn Reterive//////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////
	$('#incomeReterive').click(function(){
		
		if( $('input[name=trancode]').is(':checked') ){
		   	var txtFrom = $("#txtFrom").val();
			if(txtFrom == ''){
				txtFrom = '';
			}else{
				txtFrom = moment($("#txtFrom").val(), 'DD/MM/YYYY').format("YYYYMMDD");
			}
			
			var txtTo = $("#txtTo").val();
			if(txtTo == ''){
				txtTo = '';
			}else{
				txtTo = moment($("#txtTo").val(), 'DD/MM/YYYY').format("YYYYMMDD");
			}
			
			var patrCateValue;
			var patrCate = $('#chkPatronCategory:checked').val();
	    	if(patrCate == "patrCate"){
	    		patrCate = "Y";
				patrCateValue = $("#patronCate").val();
	    	}else{
	    		patrCate = "N";
				patrCateValue = '';
	    	}
	    	
	    	var officer = $("#lblPatronID").val();
			var chkOfficer  = $('#chkOfficer:checked').val();
			if(chkOfficer == "officer"){
	    		chkOfficer = "Y";
				//officer = $("#lblPatronID").val();
	    	}else{
	    		chkOfficer = "N";
				//officer = '';
	    	}
	    	
	    	var paymodeVal;
			var chkPaymentMode = $('#chkPaymentMode:checked').val();
			if(chkPaymentMode == "paymode"){
	    		chkPaymentMode = "Y";
				 paymodeVal = $('#paymodeData').val();
	    	}else{
	    		chkPaymentMode = "N";
				paymodeVal = '';
	    	}
	    	
	    	var chargeVal;
			var chkCharge = $('#chkCharge:checked').val();
			if(chkCharge == "charge"){
	    		chkCharge = "Y";
				chargeVal = $('#chargeData').val();
				//combinecode = chargeVal;
	    	}else{
	    		chkCharge = "N";
				chargeVal = '';
	    	}
	    	
	    	var payVal;
			var chkPayment = $('#chkPayment:checked').val();
			if(chkPayment == "payment"){
	    		chkPayment = "Y";
				 payVal = $('#payData').val();
				//combinecode	+= "," +payVal;
	    	}else{
	    		chkPayment = "N";
				payVal = '';
	    	}
	    	
	    	var overrideVal;
			var chkOverride = $('#chkOverride:checked').val();
			if(chkOverride == "override"){
	    		chkOverride = "Y";
				 overrideVal = $('#overrideData').val();
				//combinecode	+= "," + overrideVal;
	    	}else{
	    		chkOverride = "N";
				overrideVal = '';
	    	}
	    	
	    	var otherVal;
			var chkOthers = $('#chkOthers:checked').val();
			if(chkOthers == "other"){
	    		chkOthers = "Y";
				otherVal = $('#otherData').val();
				//combinecode	+= "," + otherVal;
	    	}else{
	    		chkOthers = "N";
				otherVal = '';
	    	}
	    	
	    	
	    	var t = $('#table-IncomeDetails').DataTable( {
					dom: 'Bfrtip',
						buttons: [
							 	/*{
					                extend: 'excelHtml5',
					                filename: 'Income_Detail',
					                title: 'Income Details by Transaction Type By',
					                //messageTop: 'Minimum Borrowing = ' ,
					               customize: function( xlsx ) {
						                var sheet = xlsx.xl.worksheets['sheet1.xml'];
						               // $('row:first c', sheet).attr( 's', '42' );
 										$('c[r=B3] t', sheet).text( 'Custom text' );
						            }
					                
					             },*/
								{
					            	 	text: '<i class="fa fa-file-pdf-o"></i> PDF',
										extend: 'pdfHtml5',
										filename: 'Income_Detail',
										orientation: 'landscape', //portrait
										pageSize: 'A4', //A3 , A5 , A6 , legal , letter
										//messageTop: 'Income Details by Transaction Type' ,
										//messageBottom: "lllllll",
										exportOptions: {
											//columns: ':visible',
											search: 'applied',
											order: 'applied'
										},
										customize: function (doc) {
											
											  
											doc.styles.tableHeader.alignment = 'left';
											
											var rowCount = doc.content[1].table.body.length;
											doc.content[1].table.widths = ['14%', '7%','9%', '20%', '18%', '10%', '15%','9%',];
											//var cols = [];
   											

											//alert(rowCount +"rowCount1");
											for (i = 1; i < rowCount; i++) {
												doc.content[1].table.body[i][0].text = i
												doc.content[1].table.body[i][7].alignment = 'right';
												//alert(i +"rowCount2");
											   //doc.content[1].table.body[i][2].alignment = 'left';
												//doc.content[1].table.body[i][11].alignment = 'right';
											};
											
											
											//$(doc).append("<div class='right'>"+ $('.branch_info').html()+"</div>");
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
						                                   { text: 'Income Details by Transaction Type \n',bold:true,fontSize:13,alignment: 'center'},
						                                   /////{ text: $("#libname").val() +'\n',bold:true,fontSize:13,alignment: 'center'},
						                        ],
						                        margin: [0, 0, 0, 12],
						                    });
						                    
						                    
						                    doc.content.push({pageBreak: 'before',
														  table: {
														    headerRows: 1,
														    widths: [ "*", "*" ],
															heights: [20, 70],
															//pageBreak : true,
															//	dontBreakRows: true,
															
														    body: [
														      [
														        { text: "DISEDIAKAN OLEH", bold: true, alignment: 'center'},
														        //{ text: "DISEMAK OLEH", bold: true, alignment: 'center'},
														        { text: "DISAHKAN OLEH", bold: true, alignment: 'center'},
														        //{ text: "DITERIMA OLEH ", bold: true, alignment: 'center'}
														      ],
														      [ { text: " ", bold: true, alignment: 'left',border: [true, false, false, false]},
													 			//{ text: " ", bold: true, alignment: 'left',border: [true, false, false, false]}, 
																{ text: " ", bold: true, alignment: 'left',border: [true, false, true, false]},
												 				//{ text: " ", bold: true, alignment: 'left',border: [true, false, true, false]} 
															  ],
														      [ { text: "NAMA & TANDATANGAN", bold: true, alignment: 'center',border: [true, false, false, false]},
													 			///{ text: "NAMA & TANDATANGAN", bold: true, alignment: 'center',border: [true, false, false, false]}, 
																{ text: "NAMA & TANDATANGAN", bold: true, alignment: 'center',border: [true, false, true, false]},
												 				///{ text: "NAMA & TANDATANGAN", bold: true, alignment: 'center',border: [true, false, true, false]} 
															  ],
															  [ { text: "TARIKH :", bold: true, alignment: 'left'},
													 			//{ text: "TARIKH :", bold: true, alignment: 'left'}, 
																{ text: "TARIKH :", bold: true, alignment: 'left'},
												 				//{ text: "TARIKH :", bold: true, alignment: 'left'} 
															  ],
													
														    ]
														  }
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
					            /*{
					                extend: 'print',
									//autoPrint: false,
									///orientation: 'landscape',
                    				pageSize: 'A4',
                					text: '<i class="glyphicon glyphicon-print" aria-hidden="true"></i> Print',
									title: '',
									//messageTop: 'Income Details by Transaction Type By : '+ $('#brnchData').val() +'<br><b>Total : </b>' + $("#allTotal").text(),
					                customize: function ( win ) {
						
											var css = '@page { size: landscape; }',
							                    head = win.document.head || win.document.getElementsByTagName('head')[0],
							                    style = win.document.createElement('style');
							 
							                style.type = 'text/css';
							                style.media = 'print';
							 
							                if (style.styleSheet)
							                {
							                  style.styleSheet.cssText = css;
							                }
							                else
							                {
							                  style.appendChild(win.document.createTextNode(css));
							                }
							 
							                head.appendChild(style);

 											$(win.document.body).find('table').addClass('printer');
					 						$(win.document.body).prepend( "<div class='center'><b>Income Details by Transaction Type</b></div>");
										    $(win.document.body).append("<div class='right'>"+ $('.branch_info').html()+"</div>");
					                    	$(win.document.body).find( 'table' ).addClass( 'compact' ).css( 'font-size', 'inherit' );
											$(win.document.body).prepend( "<div style='float: left'></div><div style='float: right'>"
											+moment(today).format("DD/MM/YYYY") +'<br>' 
				                    		+moment(today).format("hh:mm:ss A")+"</div><div style='margin: 0 auto; width: 100px;'></div>");
					                },
									exportOptions: {
										//columns: [ 0, 1, 2, 3, 4, 5, 6 ]
						           },
					            },*/
								/*{
									text: '<i class="fa fa-file-text-o"></i> CSV',
						            extend: 'csvHtml5',
						            /*text: 'Copy all data',
						            exportOptions: {
						                modifier: {
						                    search: 'none'
						                }
						            }*/
									//columns: [ 1, ':visible' ],
									/*** filename: 'Income_Detail',
									title: 'Income Detail by Transaction Type',
                					text: 'CSV',
									customize: function(doc){
										
										var split_csv = doc.split("\n");
										//alert(split_csv)
										$.each(split_csv.slice(1), function (index, csv_row) {
											var csv_cell_array = csv_row.split('","');
											//alert(csv_cell_array[1].replace('', (index+1)))
											csv_cell_array[1].replace("", (index+1));
											///alert(split_csv.slice(1));
										});
										///alert(csv_cell_array[1]);
										//alert(csv_cell_array[1]);
										
										return "Income Detail by Transaction Type\n\n" + doc;
										//var split_csv = doc.split("\n");
										 
									 }*/
									/*customize: function (csv) {
										var split_csv = csv.split("\n");
										 $.each(split_csv.slice(1), function (index, csv_row) {
											//alert("csv_row"+index);
											 var csv_cell_array = csv_row.split('","');
										csv_cell_array[1] = (index+1);
										//alert(csv_cell_array[1].replace('', (index+1)))
										});
									}*/
        						/////////////}
					        ],
							destroy: true,
							searching: true,
							bLengthChange: false,
							autoWidth: false,
							responsive: true,
							processing: true,
							//bSort: false,
							//buttons: ['pdf', 'print'],
							//lengthMenu: [[10, 25, 50, -1], [10, 25, 50, "All"]],
							//ordering: false,
							///order: [[10, 'asc']],
							//order: [[ 0, 'asc' ], [ 1, 'asc' ]],
							//info: false,
							
						    ajax: {
						    	url: "resultIncomeDetailMat",
						    	data : {txtFrom : txtFrom,
									txtTo : txtTo,
									patrCate : patrCate,
									patrCateValue : JSON.stringify(patrCateValue),
									chkOfficer : chkOfficer,
									officer : officer,
									chkBranch : "N",
									branchVal : "",
									///chkBranch : chkBranch,
									///branchVal : JSON.stringify(branchVal),
									chkPaymentMode : chkPaymentMode,
									paymodeVal : JSON.stringify(paymodeVal),
									chkCharge : chkCharge,
									chargeVal : JSON.stringify(chargeVal),
									chkPayment : chkPayment,
									payVal : JSON.stringify(payVal),
									chkOverride : chkOverride,
									overrideVal : JSON.stringify(overrideVal),
									chkOthers : chkOthers ,
									otherVal : JSON.stringify(otherVal),
									///brancodeValue : brancodeValue
									brancodeValue : 'T4.GL14BRNC'
									},
								//dataType: 'json',
						        type: "GET",
						        dataSrc: function (json) {
						            var return_data = new Array();
						            for(var i=0;i< json.length; i++){
						            	//alert(json[i].trantypeDesc +"fdgd");
						              return_data.push({
										//'Branch' : json[i].branch,
						            	'No': '',
						                //'Transaction No' : json[i].trxNo, 
										'Transaction Type' : json[i].trantypeDesc, 
						                'Transaction Date' : json[i].txnDate,
						                'Membership ID/Name' : json[i].memberID +" - "+json[i].memberName,
						                'Receipt No' : json[i].ref,
						                'Officer ID/NAME' : json[i].officer +" - "+json[i].officerName,
										//'Accession No' : json[i].accno,
										'Payment Mode' : json[i].paymode,
										'Amount (RM)' : json[i].amt,
										/*'Branch Name' : json[i].branchdesc*/
										
						              })
						            }
						            return return_data;
						          },
						     },//This is the end of the AJAX object from the example above 
						     	columns    : [
									//{'data': 'Branch', visible: false},	
						     	    {'data': 'No'},
									//{'data': 'Transaction No'},
									{'data': 'Transaction Type'},
									{'data': 'Transaction Date', className: "text-right",type: 'date-uk', targets: 0,},
									{'data': 'Membership ID/Name'},
									{'data': 'Receipt No', className: "text-right"},
									{'data': 'Officer ID/NAME'},
									//{'data': 'Accession No'},
									{'data': 'Payment Mode'},
									{'data': 'Amount (RM)', className: "text-right", render: $.fn.dataTable.render.number(',', '.', 2, '')},
									/*{'data': 'Branch Name', visible: false},	*/
									
								],
								//order: [[ 0, 'asc' ]],
        						//displayLength: 25,
							  footerCallback: function ( row, data, start, end, display ) {
					            var api = this.api(), data;
			
								var numFormat = $.fn.dataTable.render.number( '\,', '.', '2', '' ).display
					 
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
					                .column( 7 )
					                .data()
					                .reduce( function (a, b) {
					                    return intVal(a) + intVal(b);
					                }, 0 );
					 
					 
					
					            // Update footer
								$("#allTotalAmount").text(" "+numFormat(totalLocalPrice));
					          /* $( api.column( 8 ).footer() ).html(
					                numFormat(totalLocalPrice)
					            );*/
					        },

							
			}); 
						
						
				/*t.on( 'order.dt search.dt', function () {
					t.column(1, {search:'applied', order:'applied'}).nodes().each( function (cell, i) {
					  	cell.innerHTML = i+1;
					} );
				} ).draw();*/
				
				t.on('order.dt search.dt', function () {
				     t.column(0, {search:'applied', order:'applied'}).nodes().each( function (cell, i) {
				           cell.innerHTML = i+1;
				           t.cell(cell).invalidate('dom');
				     });
				}).draw();
    	
		}
		else{
		    swal("Please Tick Either Charge, Payment, Override, Other");
		}
		


		///var getTrantype = $('input[name="trantype"]:checked').val();
		
    	/*var brancodeValue;
    	var url;
		var brancode = $('#brancode:checked').val();
    	if(brancode == "officerB"){
			brancodeValue = 'T4.GL14BRNC';
			url = "resultIncomeDetail";
    	}else if(brancode == "patronB"){
			brancodeValue = 'T3.GL14BRNC';
			url = "resultIncomeDetail";
    	}else if(brancode == "itemB"){
			brancodeValue = 'CT03LOCA';
			url = "resultIncomeDetailItemCat";
		}else{
			alert("Please Pick Branch Category");
		}*/

		
	//alert("patrCateValue"+patrCateValue);
		/*alert("patrCate"+patrCate);
		alert("patrCateValue"+patrCateValue);
		alert(" JSON.stringify(patrCateValue)"+ JSON.stringify(patrCateValue));*/
		

		
		/*alert("chkOfficer"+chkOfficer);
		alert("officer"+officer);*/

		/*var branchVal;
		var chkBranch = $('#chkBranch:checked').val();
		if(chkBranch == "branch"){
    		chkBranch = "Y";
			branchVal = $('#brnchData').val();
    	}else{
    		chkBranch = "N";
			branchVal = "";
    	}
		*/


	

		/*alert("chkPaymentMode"+chkPaymentMode);
		alert("paymodeVal"+paymodeVal);
		alert(" JSON.stringify(paymodeVal)"+ JSON.stringify(paymodeVal));*/

		
		/////choose
		
		/*alert("chkCharge"+chkCharge);
		alert("chargeVal"+chargeVal);
		alert(" JSON.stringify(chargeVal)"+ JSON.stringify(chargeVal));*/

		
		
		/*alert("chkPayment"+chkPayment);
		alert("payVal"+payVal);
		alert(" JSON.stringify(payVal)"+ JSON.stringify(payVal));*/



		
		/*alert("chkOverride"+chkOverride);
		alert("overrideVal"+overrideVal);
		alert(" JSON.stringify(overrideVal)"+ JSON.stringify(overrideVal));*/


		

		/*alert("chkOthers"+chkOthers);
		alert("otherVal"+otherVal);
		alert(" JSON.stringify(otherVal)"+ JSON.stringify(otherVal));*/


	

		
		///for table
		
		

	});
	
	//////////////////////////////////////////////////////////////////////////
	////////////////////////btn Print//////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////
	/*$('#print').click(function(){
	});	*/

	

});	
