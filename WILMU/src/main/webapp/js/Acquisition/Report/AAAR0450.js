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
	
	
	
	/////new add
	function ReplaceNumberWithCommas(yourNumber) {
	    //Seperates the components of the number
	    var components = yourNumber.toString().split(".");
	    //Comma-fies the first part
	    components [0] = components [0].replace(/\B(?=(\d{3})+(?!\d))/g, ",");
	    //Combines the two sections
	    return components.join(".");
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

	/////////////////--- EDITOR ------/////////
	
	////1) Hide selection criteria
	/////List all div   
	////.rangeDiv, .vendorSearch, .patronSearch, .orderModeDiv, .statusDiv, .acctDiv,
	////.deptDiv, .invstatDiv, .itemstatDiv, .recordselectionDiv, .claimstatDiv, .printerStat
	
	$(".patronSearch, .statusDiv, .acctDiv, .itemstatDiv, .claimstatDiv").hide();
	$(".recordselectionDiv, .printerStat").hide();

	
	////2) Change Label
	///Date Label
	$(".datelabel").html("<b>Order Date Range</b>");
	
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
		e.preventDefault();
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
		

		/////////////////--- EDITOR ------/////////		
		
		/////result display
		var t = $('#reportTable').DataTable( {
			dom: 'Bfrtip',
            buttons: [
						{
					  		extend: 'excelHtml5',
					        filename: 'WILMU_AcqOrdersReport',
					        title: 'Orders Report ' +sDate +eDate,
					                
					 	},
						{
					    	text: '<i class="fa fa-file-pdf-o"></i> PDF',
							extend: 'pdfHtml5',
							filename: 'WILMU_AcqOrdersReport',
							charset: "utf-8",
							bom : "true",
							orientation: 'landscape', //portrait
							pageSize: 'A4', //A3 , A5 , A6 , legal , letter
							footer: true,
							exportOptions: {
								search: 'applied',
								order: 'applied'
							},
							//messageBottom: '\n\n this is text messgae for footer area',
							/*messageBottom: {
			                 // lineHeight: 5,
			                  text: '\n\n '+ $(".test").text(),
			                  customize: function ( doc ) {
			                    doc.content.splice( {
			                      margin: [ 0, 0, 0, 12 ],
			                      alignment: 'center',
			                      image: `data:image/png;base64,.............`
			                    });
			                  },
			                  alignment: 'right',
			                  margin: [ 0, 0, 0, 12 ],
			                },*/
							/* messageBottom: function () {
			                    alignment: 'center'
			                },*/
							customize: function (doc) {
								//doc.defaultStyle.font = 'Arial';
								//doc.styles.tableHeader.alignment = 'left';
								
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
						                                   { text: 'Orders Report '+sDate +eDate+ '\n',bold:true,fontSize:13,alignment: 'center'}						                        ],
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
		                	filename: 'WILMU_AcqOrdersReport',
		                	//title: 'Orders Report By ',
							charset: "utf-8",
							bom : "true",
							customize: function(doc){
										
										var split_csv = doc.split("\n");
										//alert(split_csv)
										$.each(split_csv.slice(1), function (index, csv_row) {
											var csv_cell_array = csv_row.split('","');
											csv_cell_array[1].replace("", (index+1));
										});
										
										return "Orders Report "+sDate + eDate +"\n\n" + doc;
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
		    	url: "ResultAcqOrdersReport",
		        data : {currencyFormat :  currencyFormat, 
						startSentDate : startSentDate, endSentDate : endSentDate, brnch : JSON.stringify(brnch),
						//// Editer/////
						rangeFirstVal : rangeFirstVal, rangeLastVal : rangeLastVal,
						vendor : vendor, patronID : patronID,
						orderMode : JSON.stringify(orderMode),
						department : JSON.stringify(department),
						acqinvstat : JSON.stringify(acqinvstat),
						//// Editer///// 
						allbranch : allbranch
						},
		        type: "GET",
				/*start_time: new Date().getTime(),
				    complete: function(data) {
				        alert('This request took '+(new Date().getTime() - this.start_time)+' ms');
				},*/
		        dataSrc: function (json) {
		            var return_data = new Array();
		            
		            for(var i=0;i< json.length; i++){

		              return_data.push({
		                'No': (i+1),
		                'Order No' : json[i].Column1,
		                'Order Date' : json[i].Column2,
		                'Vendor' : json[i].Column3,
		                'Title' : json[i].Column4,
						'ISBN' : json[i].Column5,
						'Author' : json[i].Column6,
						'Quantity' : json[i].Column7,
						'Unit Price' : json[i].Column11 + ReplaceNumberWithCommas(json[i].Column8),
						'Total Price' : json[i].Column11 + ReplaceNumberWithCommas(json[i].Column9),
						'Local Price' :json[i].Column10,
		            })
		            }
					
		            return return_data;
		          },
		     },//This is the end of the AJAX object from the example above
		     	columns    : [
					{'data': 'No'},
					{'data': 'Order No'},
					{'data': 'Order Date', type: 'date-uk', targets: 0,},
					{'data': 'Vendor'},
					{'data': 'Title'},
					{'data': 'ISBN'},
					{'data': 'Author'},
					{'data': 'Quantity'},
					{'data': 'Unit Price', className: "text-right"},
					{'data': 'Total Price', className: "text-right",},
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
		                .column( 10 )
		                .data()
		                .reduce( function (a, b) {
		                    return intVal(a) + intVal(b);
		                }, 0 );

		            totalQuantity = api
		                .column( 7 )
		                .data()
		                .reduce( function (a, b) {
		                    return intVal2(a) + intVal2(b);
		                }, 0 );
		 
		 			/////////////////////////////////////////////////////////
		            // Update footer
					/*$(".totalQuantity").html("Total Amount : "+
		                numFormat(totalLocalPrice)
		            );


					var finalQuantity;
					if(totalQuantity > 1){
						finalQuantity = totalQuantity + " copies";
					}else{
						finalQuantity = totalQuantity + " copy";
					}

		           $(".totalAmount").html("Total Quantity : "+
		                (finalQuantity)
		            );*/

					$("#allTotalAmount").text(" "+numFormat(totalLocalPrice));
					
					var finalQuantity;
					if(totalQuantity > 1){
						finalQuantity = totalQuantity + " copies";
					}else{
						finalQuantity = totalQuantity + " copy";
					}
					
					$("#allTotalQty").html(" "+finalQuantity +" &emsp; "); 

					//////////////////
		           /*$( api.column( 10 ).footer() ).html(
		                numFormat(totalLocalPrice)
		            );

					var finalQuantity;
					if(totalQuantity > 1){
						finalQuantity = totalQuantity + " copies";
					}else{
						finalQuantity = totalQuantity + " copy";
					}

		           $( api.column( 7 ).footer() ).html(
		                (finalQuantity)
		            );*/
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
