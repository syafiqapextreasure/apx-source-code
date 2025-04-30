$(document).ready(function() {
	
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
	
	//$('#acqitemstat').val("A").hide();//children('option[value="A"]').hide();
	//$("[id*=acqitemstat] option[value='A']").remove();
	

	$("#lblPatronID").prop( "disabled", false);	
	$('.patronid').prop('disabled', false);
	$('#Reterive').prop('disabled', true);
	
	$('#chkBoxItemStatus').prop( "disabled", true);
	$('#chkBoxItemStatus').prop('checked', true);
	
	
	var input = $('input[value="A"]');
		input.prop('disabled', true);
		var input2 = input.parent('label').parent('a').parent('li');
		input2.removeClass('active');
		input2.addClass('disabled');
		
		$("#acqitemstat option[value=A]").remove();
		$('#acqitemstat').multiselect('refresh');
		
	/*$('#acqitemstat').change(function() {
		var patrleng = $(".lblName").text().length();
			
		if($("#acqitemstat").val() != "" && (patrleng > 1)){
			$('#Reterive').prop('disabled', false);
		}else{
			$('#Reterive').prop('disabled', true);
		}
	});*/
	
	$('#acqitemstat').multiselect("enable");
	
	/////////////////--- EDITOR ------/////////
	
	////1) Hide selection criteria
	/////List all div   
	////.rangeDiv, .vendorSearch, .patronSearch, .orderModeDiv, .statusDiv, .acctDiv,
	////.deptDiv, .invstatDiv, .itemstatDiv, .recordselectionDiv, .claimstatDiv, .printerStat
	
	$(".rangeDiv, .orderModeDiv, .acctDiv, .invstatDiv, .statusDiv").hide();
	$(".recordselectionDiv, .claimstatDiv, .printerStat").hide();

	
	////2) Change Label
	///Date Label
	$(".datelabel").html("<b>Requested Date Range</b>");
	
	///Range Label
	//$(".rangeLabel").html("<b>Order Number Range</b>");
	
	///Status Label (Request/Order /Invoice /Gift)
	$(".statusLabel").html("<b>Item Status</b>")
	
	
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
					
					///////XX
					/*var patron = $(".lblName").text();
						if(patron.length > 1){
							$('#Reterive').prop('disabled', false);
						}else{
							$('#Reterive').prop('disabled', true);
						}*/
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
				if(responseJson==''){
					$('#Reterive').prop('disabled', true);
				}
				$.each(responseJson, function(key,value) {
					$(".lblName").text(value['Name']);


					if($("#acqitemstat").val() != ""  && value['Name'].length > 1){
						$('#Reterive').prop('disabled', false);
					}else{
						$('#Reterive').prop('disabled', true);
					}
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
	});
	
	
	///4) Checkbox tick or not
	
	////e)  checkbox multiselect department
	$("#chkBoxDepartment").on('change', function () {
	    var self = $(this);
	    if (self.is(":checked")) {
			$('#dept').multiselect("enable");
			$('#Reterive').prop('disabled', true);
			$('#dept').change(function() {
				
				///////XX
				var patron = $(".lblName").text();
						
				if($("#acqitemstat").val() != "" && patron.length > 1){
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
		
		var input = $('input[value="A"]');
		input.prop('disabled', true);
		var input2 = input.parent('label').parent('a').parent('li');
		input2.removeClass('active');
		input2.addClass('disabled');
		
			$('#acqitemstat').multiselect("enable");
			$('#Reterive').prop('disabled', true);
			$('#acqitemstat').change(function() {
				///////XX
				var patron = $(".lblName").text();
				
				if($("#acqitemstat").val() != ""  && patron.length > 1){
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
	
	$('#acqitemstat').change(function() {

		var patrleng = $(".lblName").text();
			
		if($("#acqitemstat").val() != "" && (patrleng.length > 1)){
			$('#Reterive').prop('disabled', false);
		}else{
			$('#Reterive').prop('disabled', true);
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
		//2 Vendor AND Patron Id
		var chkBoxSearchCateria = [];
		$.each($("input[name='chkBoxSearchCateria']:checked"), function(){            
                chkBoxSearchCateria.push($(this).val());
            });
		chkBoxSearchCateria = chkBoxSearchCateria.join(", ");
		
		var vendor;
		
		var patronID = $("#lblPatronID").val();
		//alert(chkBoxSearchCateria+"chkBoxSearchCateria");
		
		switch(chkBoxSearchCateria) {
		
			 case 'vendCode':
					vendor = $("#input-vendorCode").val();  
		    break;	
			case '':
				vendor = 0;
		    break;	
			
		}
		
		//6 department
		var department = $("#dept").val(); 
		if(department != ""){
		}else{
			department = 0;
		}
		
		//8 Item Status
		var acqitemstat = $("#acqitemstat").val(); 
		if(acqitemstat != ""){
		}else{
			acqitemstat = 0;
		}	
		
		

		

		/////////////////--- EDITOR ------/////////		
		
		/////result display
		var t = $('#reportTable').DataTable( {
			dom: 'Bfrtip',
            buttons: [
						{
					  		extend: 'excelHtml5',
					        filename: 'WILMU_AcqRequestedItemStatus',
					        title: 'Requested Item Status ' +sDate +eDate,
					                
					 	},
						{
					    	text: '<i class="fa fa-file-pdf-o"></i> PDF',
					    	className : 'clickpdf',
							extend: 'pdfHtml5',
							filename: 'WILMU_AcqRequestedItemStatus',
							orientation: 'landscape', //portrait
							charset: "utf-8",
							bom : "true",
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
										doc.content[1].table.body[i][0].alignment = 'left';
						               	doc.content[1].table.body[i][1].alignment = 'left';
										doc.content[1].table.body[i][2].alignment = 'left';
										doc.content[1].table.body[i][3].alignment = 'left';
						               	doc.content[1].table.body[i][4].alignment = 'left';
										doc.content[1].table.body[i][5].alignment = 'left';
										doc.content[1].table.body[i][6].alignment = 'left';
										doc.content[1].table.body[i][7].alignment = 'left';
						           };
								
								 
							
							 	//doc.content[1].table.widths = [ '8%', '55%', '12%', '12%', '12%']; 

											//Remove the title created by datatTables  
											doc.content.splice(0, 1, {
						                        text: [
						                                   { text: 'Requested Item Status  '+sDate +eDate+ '\n',bold:true,fontSize:13,alignment: 'center'}						                        ],
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
		                	filename: 'WILMU_AcqRequestedItemStatus',
		                	//title: 'Requested Item Status By ',
							charset: "utf-8",
							bom : "true",
							customize: function(doc){
										
										var split_csv = doc.split("\n");
										//alert(split_csv)
										$.each(split_csv.slice(1), function (index, csv_row) {
											var csv_cell_array = csv_row.split('","');
											csv_cell_array[1].replace("", (index+1));
										});
										
										return "Requested Item Status "+sDate + eDate +"\n\n" + doc;
									 }
             			},
						{
					    	//text: '<i class="fa fa-file-pdf-o"></i> PDF', extend: "pdf", className: "fred"
							//extend: 'pdfHtml5', className: "RequestedItemStatus",
							//filename: 'WILMU_AcqRequestedItemStatus',
							text: "Notice to requestor",
							className : 'notice',
				                action: function ( e, dt, node, config ) {
				                   
									$('#modalNotice').modal('show');

									$('#modalNotice').on('hide.bs.modal show.bs.modal', function(event) {
										$(this).off('hide.bs.modal');
										var $activeElement = $(document.activeElement);
  
										  if ($activeElement.is('[data-toggle], [data-dismiss]')) {
										    if($activeElement[0].id == "Oknotice"){
												
												var chkBoxAcqAttachPrint; 
												if($('#attachlist').not(':checked').length){
													chkBoxAcqAttachPrint = 0;
											    }else{
												 	chkBoxAcqAttachPrint = "Y";
												 	//alert("dss")
												 	$(".buttons-pdf").click();
											    }
											
										    	var printNotice = $("input[name='printNotice']:checked").val();
												
												$.post("Global?type=Handler&name=GeneratePreviewDocumentAcqReportListing", {
													letterId : "EQ34",
													loginid: $("#liferayLogin").val(),
													patronid : patronID,
													actionPrint : "RequestNotice",
													printNotice : printNotice,}, 

													function(result){
										
														 var w = window.open('about:blank');
														 	w.document.open();
														 	w.document.write(result);
														 	w.document.close();
														 	w.print();
												});



										    }

										  }
									});
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
		    	url: "ResultAcqRequestedItemStatus",
		        data : {currencyFormat :  currencyFormat, 
						startSentDate : startSentDate, endSentDate : endSentDate, brnch : JSON.stringify(brnch),
						//// Editer/////
						vendor : vendor, patronID : patronID,
						department : JSON.stringify(department),
						acqitemstat : JSON.stringify(acqitemstat),
						//// Editer///// 
						allbranch : allbranch
						},
		        type: "GET",
		        dataSrc: function (json) {
		            var return_data = new Array();
		            
		            for(var i=0;i< json.length; i++){
			
					var column2 = json[i].column2;
					//alert(column2+"column2");
						if(column2 == "" || column2 == undefined){
							column2 = "-";
					}
			
					var column5 = json[i].Column5;
					if(column5 == ""){
						column5 = "-";
					}
					
					var column6 = json[i].Column6;
					
					if(column6 == ""){
						column6 = "-";
					}

		              return_data.push({
		                'No': (i+1),
		                'Title' : json[i].Column1,
		                'Call No' : column2,
		                'Location' : json[i].Column3,
		                'Branch' : json[i].Column4,
						'Accession No' : column5,
						'Item Category' : column6,
						'Requestor' : json[i].Column7,
		            })
		            }
					
		            return return_data;
		          },
		     },//This is the end of the AJAX object from the example above
		     	columns    : [
					{'data': 'No'},
					{'data': 'Title'},
					{'data': 'Call No'},
					{'data': 'Location'},
					{'data': 'Branch'},
					{'data': 'Accession No'},
					{'data': 'Item Category'},
					{'data': 'Requestor'},	
				],
				
    	});
		
		/*t.on('order.dt search.dt', function () {
			 t.column(0, {search:'applied', order:'applied'}).nodes().each( function (cell, i) {
				cell.innerHTML = i+1;
				t.cell(cell).invalidate('dom');
			 });
	   }).draw();*/

	   	$('#modalNotice').on('hidden.bs.modal', function () {
			$(this).find('form').trigger('reset');
		});
		
		if(acqitemstat.includes('01') || acqitemstat.includes('20')){
			//$(".notice").hide();
			t.buttons( '.notice' ).remove();
		}else {
			//$(".notice").show();
			t.buttons( '.notice' ).show();
		}
		
    	

    });


	
});