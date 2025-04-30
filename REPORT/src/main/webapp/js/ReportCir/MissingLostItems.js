$(document).ready(function() {
	
	//////////////////////////////////////////////////////////////////////////
	//////////////////////////setup page//////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////

	//$(".datelabel").html("<b>Date Range</b>");
	$('#Reterive').prop('disabled', true);
	
	$('#smd, #branch, #icat, #loca').multiselect({
    	allSelectedText: 'All',
   		maxHeight: 200,
   		includeSelectAllOption: true
	});
	
	$("#smd, #icat, #loca").multiselect("disable");
	
	$("#input-startAcc , #input-endAcc").prop("disabled", true);
	$("input[name=chkBoxSearchCateria2][value=date]").prop('checked', true);
	
	$('input[type=radio][name=chkBoxSearchCateria2]').on('change', function() {
		switch ($(this).val()) {
			
			case 'date':
					$("#input-startAcc , #input-endAcc").prop("disabled", true);
					$("input[name=chkBoxSearchCateria2][value=date]").prop('checked', true);
					
					$("#input-startDate , #input-endDate").prop("disabled", false);
					
				break;
				
			case 'accno':
					$("#input-startDate , #input-endDate").prop("disabled", true);
					$("input[name=chkBoxSearchCateria2][value=accno]").prop('checked', true);
					
					$("#input-startAcc , #input-endAcc").prop("disabled", false);
				break;
		}
	});
	 
	
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
	
	//////////////radio button on change
	$('input[type=radio][name=chkBoxSearchCateria]').on('change', function() {
		switch ($(this).val()) {
		    case 'pateCate':
				$('#patronCate').multiselect("enable");
			  	$("#smd, #icat, #loca").multiselect("disable");
				$('#Reterive').prop('disabled', true);
					$('#patronCate').change(function() {
						if($("#patronCate").val() != ""){
							$('#Reterive').prop('disabled', false);
						}else{
							$('#Reterive').prop('disabled', true);
						}
					 });
		      break;
		    case 'itemcate':
				$('#icat').multiselect("enable");
			  	$("#smd, #patronCate, #loca").multiselect("disable");
				$('#Reterive').prop('disabled', true);
					$('#icat').change(function() {
						if($("#icat").val() != ""){
							$('#Reterive').prop('disabled', false);
						}else{
							$('#Reterive').prop('disabled', true);
						}
					});
		      break;
			case 'smd':
				$('#smd').multiselect("enable");
			  	$("#patronCate, #icat, #loca").multiselect("disable");
				$('#Reterive').prop('disabled', true);
				$('#smd').change(function() {
					if($("#smd").val() != ""){
						$('#Reterive').prop('disabled', false);
					}else{
						$('#Reterive').prop('disabled', true);
					}
				});
		      break;
			case 'loca':
				$('#loca').multiselect("enable");
			  	$("#patronCate, #icat, #smd").multiselect("disable");
				$('#Reterive').prop('disabled', true);
				$('#loca').change(function() {
					if($("#loca").val() != ""){
						$('#Reterive').prop('disabled', false);
					}else{
						$('#Reterive').prop('disabled', true);
					}
				});
		      break;	
		  }
	});
	
	////////Branch mandatary
	/*$('#branch').change(function() {
		if($("#branch").val() != ""){
			$('#Reterive').prop('disabled', false);
		}else{
			$('#Reterive').prop('disabled', true);
		}
	 });*/


	
	
	
	//////table setup
	$('#reportTable').DataTable( {
		destroy: true,
		searching: false,
		bLengthChange: false,
		autoWidth: false,
		responsive: true,
	});
	


	//////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////
	
	//////////////////////////////////////////////////////////////////////////
	////////////////////////btn Reterive Click ///////////////////////////////
	//////////////////////////////////////////////////////////////////////////
	$('#Reterive').click(function(){
		$('#reportTable').dataTable().fnClearTable();
		
		var startSentDate = moment($("#input-startDate").val(), 'DD/MM/YYYY').format("YYYYMMDD");
		var endSentDate = moment($("#input-endDate").val(), 'DD/MM/YYYY').format("YYYYMMDD");
		//var sDate, eDate;
		var sVal, eVal;
		var firstValue, endValue;
		
		var chkBoxSearchCateria2 = $("input[name='chkBoxSearchCateria2']:checked").val();
		switch(chkBoxSearchCateria2) {
				case 'date':
					var startSentDate = $("#input-startDate").val();
					if(startSentDate == ''){
						firstValue = '';
						sVal = '';
					}else{
						firstValue = moment($("#input-startDate").val(), 'DD/MM/YYYY').format("YYYYMMDD");
						sVal ="from " + $("#input-startDate").val();
						
					}
					
					var endSentDate = $("#input-endDate").val();
					if(endSentDate == ''){
						endValue = '';
						eVal = '';
					}else{
						endValue = moment($("#input-endDate").val(), 'DD/MM/YYYY').format("YYYYMMDD");
						eVal =  $("#input-endDate").val();
					}
					break;
				case 'accno':
					firstValue = $("#input-startAcc").val();
					endValue = $("#input-endAcc").val();
					break;
		}
		
		
		
		var brnch = $("#branch").val(); 
		if(brnch != ""){
		}else{
			brnch = 0;
		}
		
		/*var head_item = $('#reportTable').DataTable().columns(1).header();
		var titleHeader;*/
		
		
		var titleHeader;
		
		
		var chkBoxSearchCateria = $("input[name='chkBoxSearchCateria']:checked").val();
		var searchCateriaval;

		switch(chkBoxSearchCateria) {
		  case 'patrID':
			searchCateriaval = $('#lblPatronID').val();
			titleHeader = "Patron Id";
			/*$(head_item ).html(titleHeader);*/
		    // code block
		    break;	
		  case 'pateCate':
			searchCateriaval = $('#patronCate').val();
			titleHeader = "Patron Category";
			/*$(head_item ).html(titleHeader);*/
		    // code block
		    break;
		  case 'smd':
			searchCateriaval = $('#smd').val();
			titleHeader = "SMD";
			/*$(head_item ).html(titleHeader);*/
			//$(head_item ).html('SMD header');
		    // code block
		    break;
		  case 'itemcate':
			searchCateriaval = $('#icat').val();
			titleHeader = "Item Category";
			/*$(head_item ).html(titleHeader);*/
		    // code block
		    break;
		  case 'loca':
			searchCateriaval = $('#loca').val();
			titleHeader = "Item Location";
			/*$(head_item ).html(titleHeader);*/
		    // code block
		    break;
		 case undefined:
			searchCateriaval = "";
		    // code block
		    break;
		    // code block
		} 
		
		var collectionType = $("input[name='collectionType']:checked").val();
		var type;
		
		//alert("collectionType"+collectionType);
		
		switch(collectionType) {
		  case 'mono':
			type = "M";
		    break;
		  case 'irs':
			type = "I";
		    break;
		} 
		
		//alert("type"+type);
		
		var chkBoxMissOrLost = $("input[name='chkBoxMissOrLost']:checked").val();
		/*var chkBoxMissOrLost = [];
		$.each($("input[name='chkBoxMissOrLost']:checked"), function(){            
                chkBoxMissOrLost.push($(this).val());
            });
		chkBoxMissOrLost = chkBoxMissOrLost.join("', '");*/
		///alert("chkBoxMissOrLost"+chkBoxMissOrLost);S
		
		/////result display
		var t = $('#reportTable').DataTable( {
			dom: 'Bfrtip',
            buttons: [
                /*'excel', 'pdf', 'print'*/
						{
					  		extend: 'excelHtml5',
					        filename: 'WILMU_MissingLostItems',
					        title: 'Missing/Lost Items Report '+titleHeader +sVal + " until " +eVal,

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
							filename: 'WILMU_MissingLostItems',
							orientation: 'landscape', //portrait
							pageSize: 'A4', //A3 , A5 , A6 , legal , letter
							footer: true,
							//messageTop: 'Income Details by Transaction Type' ,
							//messageBottom: "lllllll",
							exportOptions: {
								//columns: ':visible',
								search: 'applied',
								order: 'applied'
							},
							customize: function (doc) {
								
								 /*var rowCount = doc.content[1].table.body.length;
						           for (i = 0; i < rowCount; i++) {
										doc.content[1].table.body[i][2].alignment = 'right';
						               	doc.content[1].table.body[i][3].alignment = 'right';
										doc.content[1].table.body[i][4].alignment = 'right';
						           };*/
								
								//doc.content[1].table.widths = Array(doc.content[1].table.body[0].length + 1).join('*').split('');
							//	doc.content[1].table.widths = [30,100,50,50,50];
							//doc.content[1].table.body[0][3].alignment = 'right';
							 //doc.content[1].table.widths = [ '8%', '55%', '12%', '12%', '12%']; 
							 //doc.content[1].table.body[0][5].alignment = 'right';
						
						


											//Remove the title created by datatTables
											doc.content.splice(0, 1, {
						                        text: [
						                                   { text: 'Missing/Lost Items Report '+titleHeader +sVal+ ' until '+eVal+' \n',bold:true,fontSize:13,alignment: 'center'},
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
		                	filename: 'WILMU_MissingLostItems',
		                	title: 'Missing/Lost Items Report ',
							charset: "utf-8",
							bom : "true",
							customize: function(doc){
										
										var split_csv = doc.split("\n");
										//alert(split_csv)
										$.each(split_csv.slice(1), function (index, csv_row) {
											var csv_cell_array = csv_row.split('","');
											csv_cell_array[1].replace("", (index+1));
										});
										///alert(csv_cell_array[1]);
										//alert(csv_cell_array[1]);
										
										return "Missing/Lost Items Report "+titleHeader +sVal + " until "+ eVal +"\n\n" + doc;
										//var split_csv = doc.split("\n");
										 
									 }
             			},
            ],
			destroy: true,
			searching: true,
			bLengthChange: false,
			autoWidth: false,
			responsive: true,
			processing: true,
			/*language: {
		        "processing": "Loading. Please wait..."
		    },*/
			language: {
             loadingRecords : "Please wait - loading...",
             processing :     "Please wait formatting in progress"
        	},
			/*language: {
                    processing: '<i class="fa fa-spinner fa-spin" style="font-size:24px;color:rgb(75, 183, 245);"></i>'
            },*/
			//serverSide: true,
			deferRender: true,
			footer: true,
		    ajax: {
		    	url: "ResultMissingLostItems",
		        data : {startSentDate : startSentDate, endSentDate : endSentDate, brnch : JSON.stringify(brnch), 
						chkBoxSearchCateria : chkBoxSearchCateria, searchCateriaval : JSON.stringify(searchCateriaval),
						type : type, chkBoxMissOrLost : chkBoxMissOrLost, chkBoxSearchCateria2 : chkBoxSearchCateria2,
						firstValue : firstValue, endValue : endValue
						},
		        type: "GET",
		        dataSrc: function (json) {
		            var return_data = new Array();
		            
		            for(var i=0;i< json.length; i++){

					var callno = json[i].Column3;
					
					if(callno!=undefined){
						if(callno.charAt(0)=='*'){
							callno = callno.slice(1);
							callno = "<i>"+callno+"</i>";
						}	
					}else{
						callno = "";
					}
				
					
					var title = json[i].Column2;
					
					if(title!=undefined){
						if(title.charAt(0)=='*'){
							title = title.slice(1);
							title = "<i>"+title+"</i>";
						}	
					}else{
						title = "";
					}
					
		              return_data.push({
		                'No': (i+1),
		                'Accession No' : json[i].Column1,
		                'Title' : title,
		                'Call No' : callno,
		                'Last Patron' : json[i].Column4,
						'Item Category' : json[i].Column5,
						'SMD' : json[i].Column6,
						'Location' : json[i].Column7,
						'Vendor' : json[i].Column8,
						'Date' : json[i].Column9,
						'Price' : json[i].Column10,
		            })
		            }
					
		            return return_data;
		          },
		     },//This is the end of the AJAX object from the example above
		     	columns    : [
					{'data': 'No'},
					{'data': 'Accession No'},
					{'data': 'Title'},
					{'data': 'Call No'},
					{'data': 'Last Patron'},
					{'data': 'Item Category'},
					{'data': 'SMD'},
					{'data': 'Location'},
					{'data': 'Vendor'},
					{'data': 'Date'},
					{'data': 'Price', className: "text-right", render: $.fn.dataTable.render.number(',', '.', 4, '')},
					
										
				],

    } );
		
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