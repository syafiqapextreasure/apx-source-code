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
	
	/////////////////--- EDITOR ------/////////
	
	////1) Hide selection criteria
	/////List all div   
	////.rangeDiv, .vendorSearch, .patronSearch, .orderModeDiv, .statusDiv, .acctDiv,
	////.deptDiv, .invstatDiv, .itemstatDiv, .recordselectionDiv, .claimstatDiv, .printerStat
	
	$(".patronSearch, .orderModeDiv, .statusDiv, .acctDiv, .itemstatDiv, .claimstatDiv").hide();
	$(".deptDiv, .recordselectionDiv, .printerStat").hide();
	$(".datelabel").hide();

	
	////2) Change Label
	///Date Label
	// $(".datelabel").html("<b>Order Date Range</b>");
	
	///Range Label
	$(".rangeLabel").html("<b>Order Number Range</b>");
	
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
		var startInput = $("#startInput").val();
		var endInput = $("#endInput").val();
		if(startInput.length >=1 || endInput.length >=1){
			$('#Reterive').prop('disabled', false);
		}else{
			$('#Reterive').prop('disabled', true);
		}
	});
	

	////f) checkbox multiselect inv status
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
		$('#reportTable').dataTable().fnClearTable();
		
		var currencyFormat = $("#setupCurrency").val();
		
		var dateSelection = $('input[name="dateSelection"]:checked').val();
		
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
		
		//7 Invoice Status
		var seinvstat = $("#seinvstat").val(); 
		if(seinvstat != ""){
		}else{
			seinvstat = 0;
		}
		

		/////////////////--- EDITOR ------/////////		
		
		/////result display
		var t = $('#reportTable').DataTable( {
			dom: 'Bfrtip',
            buttons: [
						{
					  		extend: 'excelHtml5',
					        filename: 'WILMU_SeCheckInItemsReport',
					        title: 's Report ' +sDate +eDate,
					                
					 	},
						{
					    	text: '<i class="fa fa-file-pdf-o"></i> PDF',
							extend: 'pdfHtml5',
							filename: 'WILMU_SeCheckInItemsReport',
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
										doc.content[1].table.body[i][10].alignment = 'right';
						               	doc.content[1].table.body[i][11].alignment = 'right';
						               	doc.content[1].table.body[i][12].alignment = 'right';
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
						                                   { text: 'Check-In Items Report '+sDate +eDate+ '\n',bold:true,fontSize:13,alignment: 'center'}						                        ],
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
		                	filename: 'WILMU_SeCheckInItemsReport',
		                	//title: 'Check-In Items Report By ',
							customize: function(doc){
										
										var split_csv = doc.split("\n");
										//alert(split_csv)
										$.each(split_csv.slice(1), function (index, csv_row) {
											var csv_cell_array = csv_row.split('","');
											csv_cell_array[1].replace("", (index+1));
										});
										
										return "Check-In Items Report "+sDate + eDate +"\n\n" + doc;
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
		    	url: "ResultSeCheckInItemsReport",
		        data : {dateSelection : dateSelection, currencyFormat :  currencyFormat, 
						startSentDate : startSentDate, endSentDate : endSentDate, 
						brnch : JSON.stringify(brnch), allbranch : allbranch,
						//// Editer/////
						rangeFirstVal : rangeFirstVal, rangeLastVal : rangeLastVal,
						vendor : vendor, //patronID : patronID,
						seinvstat : JSON.stringify(seinvstat),
						//// Editer///// 
						},
		        type: "GET",
				rowsGroup: [1, 2],
		        dataSrc: function (json) {
		            var return_data = new Array();
		            
		            for(var i=0;i< json.length; i++){

		              return_data.push({
		                'No': (i+1),
		                'Order No' : json[i].Column1,
		                'Vendor' : json[i].Column2,
		                'ISSN' : json[i].Column3,
		                'Title' : json[i].Column4,
						'Author' : json[i].Column5,
						'Frequency' : json[i].Column6,
						'Order Date' : json[i].Column7,
						'Expected Date' : json[i].Column8,
						'Received Date' : json[i].Column9,
						'Copies Ordered' :json[i].Column10,
						'Copies Received' :json[i].Column11,
						'Local Price' :json[i].Column12,
		            })
		            }
					
		            return return_data;
		          },
		     },//This is the end of the AJAX object from the example above
		     	columns    : [
					{'data': 'No'},
					{'data': 'Order No', visible: false},
					{'data': 'Vendor', },
					{'data': 'ISSN',},
					{'data': 'Title',},
					{'data': 'Author',},
					{'data': 'Frequency',},
					{'data': 'Order Date', type: 'date-uk', targets: 0,},
					{'data': 'Expected Date', type: 'date-uk', targets: 0,},
					{'data': 'Received Date', type: 'date-uk', targets: 0,},
					{'data': 'Copies Ordered',  className: "text-right"},
					{'data': 'Copies Received', className: "text-right"},
					{'data': 'Local Price', className: "text-right", render: $.fn.dataTable.render.number(',', '.', currencyFormat, '')},
				],
				drawCallback: function ( settings ) {
					
					var api = this.api();
					var rows = api.rows( {page:'current'} ).nodes();
					var last=null;
					
					var iCpyOrder = parseInt(0);
					var iCpyReceived = parseInt(0);
					var iLocalPrice = parseFloat(0);
					
					api.column(1, {page:'current'} ).data().each( function ( group, i ) {
					 	
					     if (last !== group) {
						
					         var rowData = api.row(i).data();

							iCpyOrder += parseInt(rowData['Copies Ordered']);
							iCpyReceived += parseInt(rowData['Copies Received']);
							iLocalPrice += parseFloat(rowData['Local Price']);
					 
					         $(rows).eq(i).before(
					         ///'<tr class="group"><td colspan="11"> Order No : ' + group + " - " + rowData['Vendor'] + '</td></tr>'
						    '<tr class="group"><td colspan="12"><b> Order No : ' + group +'</b></td></tr>'
							/*+'<tr class="group"><td colspan="11"> Vendor : ' + rowData['Vendor'] +'</td></tr>'
							+'<tr class="group"><td colspan="11"> ISSN : ' + rowData['ISSN'] +'</td></tr>'
							+'<tr class="group"><td colspan="11"> Title : ' + rowData['Title'] +'</td></tr>'
							+'<tr class="group"><td colspan="11"> Author : ' + rowData['Author'] +'</td></tr>'
							+'<tr class="group"><td colspan="11"> Frequency : ' + rowData['Frequency'] +'</td></tr>'*/
							//+'<tr class="group"><td colspan="11"> Copies Ordered : ' + rowData['Copies Ordered'] +'</td></tr>'
							//+'<tr class="group"><td colspan="11" class="copyreceived"> Copies Received : ' + rowData['Copies Received'] +'</td></tr>'
							//+'<tr class="group"><td colspan="11" class="lprice"> Local Price : ' + rowData['Local Price'] +'</td></tr>'
					     );
					         last = group;
					     }

						/*$("#allTotalCopyOrder").text(iCpyOrder);
						$("#allTotalCopyReceived").text(iCpyReceived);
						$("#allTotalAmount").text(iLocalPrice.toFixed(currencyFormat) );*/
					    //alert(testing+"sdfsfs");
					});
					
				},footerCallback: function ( row, data, start, end, display ) {
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
		            totalCopiesOrdered = api
		                .column( 10 )
		                .data()
		                .reduce( function (a, b) {
		                    return intVal(a) + intVal(b);
		                }, 0 );

					
					$("#allTotalCopyOrder").html(totalCopiesOrdered +" &emsp; ");
					
					
					 totalCopyReceived = api
		                .column( 11 )
		                .data()
		                .reduce( function (a, b) {
		                    return intVal(a) + intVal(b);
		                }, 0 );

					
					$("#allTotalCopyReceived").html(" "+totalCopyReceived +" &emsp; ");
					
					totalAmount = api
		                .column( 12 )
		                .data()
		                .reduce( function (a, b) {
		                    return intVal(a) + intVal(b);
		                }, 0 );

					
					$("#allTotalAmount").html(" "+numFormat(totalAmount));
					
					
		          /*  totalQuantity = api
		                .column( 7 )
		                .data()
		                .reduce( function (a, b) {
		                    return intVal2(a) + intVal2(b);
		                }, 0 );*/
		 
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

					/*$("#allTotalAmount").text(" "+numFormat(totalLocalPrice));
					
					var finalQuantity;
					if(totalQuantity > 1){
						finalQuantity = totalQuantity + " copies";
					}else{
						finalQuantity = totalQuantity + " copy";
					}
					
					$("#allTotalQty").html(" "+finalQuantity +" &emsp; "); */

				
		        }
    	});


		

		
		/*t.on('order.dt search.dt', function () {
			 t.column(0, {search:'applied', order:'applied'}).nodes().each( function (cell, i) {
				cell.innerHTML = i+1;
				t.cell(cell).invalidate('dom');
			 });
	   }).draw();*/
		
    	//alert( 'Rows '+t.rows( '.copyreceived' ).count()+' are copyreceived' );

    });

	

});


