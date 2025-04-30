$(document).ready(function() {
	
	//////////////////////////////////////////////////////////////////////////
	//////////////////////////setup page//////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////
	
	$(".datelabel").html("<b>Charged Date Range</b>");
	
	$('#Reterive').prop('disabled', true);
	
	$('#patronCate, #smd, #branch, #icat, #loca').multiselect({
    	allSelectedText: 'All',
   		maxHeight: 200,
   		includeSelectAllOption: true
	});
	
	$("#patronCate, #smd, #icat, #loca").multiselect("disable");
	
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
	
	///////////////////patrSelection
/*	var patrSelection = $(".patrSelection").val();
	
	switch(patrSelection) {
		  case 'm':
			$("<label />", {
			  insertAfter: ".patriddisplay", // (or use appendTo: to insert into it)
			  append: ['Patron ID'] // include our $input and also some text description
			});
		    break;	
		  case 'c':
			var $input = $('<input />', {
			  class : "form-check-input",
			  type : "checkbox",
			  name : "chkBoxSearchCateria",
			  id : "chkBoxSearchCateria",
			  value : "patrID"
			});
			
			$("<label />", {
			  insertAfter: ".patriddisplay", // (or use appendTo: to insert into it)
			  append: [$input, ' Patron ID'] // include our $input and also some text description
			});
		    break;
		  case 'r':
			var $input = $('<input />', {
			  class : "form-check-input",
			  type : "radio",
			  name : "chkBoxSearchCateria",
			  id : "chkBoxSearchCateria",
			  value : "patrID"
			});
			
			$("<label />", {
			  insertAfter: ".patriddisplay", // (or use appendTo: to insert into it)
			  append: [$input, ' Patron ID'] // include our $input and also some text description
			});
		    break;
		}  */
	///////////////////
	
	
	
	//////////////radio button on change
	$('input[type=radio][name=chkBoxSearchCateria]').on('change', function() {

		//alert($("input[name='chkBoxSearchCateria']:checked").val()+"fff");
		switch ($(this).val()) {
			 /*case 'patrID':
				$('#Reterive').prop('disabled', false);
		      break;*/
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

	///on click searc patron 
	$('.patronid').on('click',function(){
		var url = "Modal_PatrSearch";

		$.get(url,function(data){
			$("#Modal_PatrSearchContent").html(data);
		});
	});


	
	
	
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
		var sDate, eDate;
		
		var startSentDate = $("#input-startDate").val();
		if(startSentDate == ''){
			startSentDate = '';
			sDate = "";
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
			eDate =  $("#input-endDate").val();
		}
		
		var brnch = $("#branch").val(); 
		if(brnch != ""){
		}else{
			brnch = 0;
		}
		
		/*var head_item = $('#reportTable').DataTable().columns(1).header();*/
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
		
		switch(collectionType) {
		  case 'mono':
			type = "M";
		    break;
		  case 'irs':
			type = "I";
		    break;
		}
		
		/*alert(chkBoxSearchCateria +"chkBoxSearchCateria");
		alert(JSON.stringify(searchCateriaval) +"searchCateriaval");*/
		
		// Setup - add a text input to each footer cell
/*    $('#reportTable thead tr:eq(1) th').each( function () {
        var title = $(this).text();
        $(this).html( '<input type="text" placeholder="Search '+title+'" class="column_search" />' );
    } );
*/
		/////result display
		var t = $('#reportTable').DataTable( {
			dom: 'Bfrtip',
            buttons: [
                /*'excel', 'pdf', 'print'*/
						{
					  		extend: 'excelHtml5',
					        filename: 'WILMU_OnLoanItemListing',
					        title: 'On Loan Item Listing '+titleHeader +sDate + " until " +eDate,

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
							filename: 'WILMU_OnLoanItemListing',
							charset: "utf-8",
							bom : "true",
							exportData : {decodeEntities:true},
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
							    //doc.content[1].table.widths = [30,50,50,50,50,50,50,50,50,50,50,50];
							//doc.content[1].table.body[0][3].alignment = 'right';
							//doc.content[1].table.widths = [ '8%', '55%', '12%', '12%', '12%'];
							 //doc.content[1].table.widths = [ '8%', '55%', '12%', '12%', '12%'];
						   //doc.content[1].table.widths = [ '3%', '10%', '20%', '7%', '7%','7%','7%','7%','7%','9%','8%','8%'];  
							 //doc.content[1].table.body[0][5].alignment = 'right';
						
						


											//Remove the title created by datatTables
											doc.content.splice(0, 1, {
						                        text: [
						                                   { text: 'On Loan Item Listing '+titleHeader +sDate+ ' until '+eDate+ ' \n',bold:true,fontSize:13,alignment: 'center'},
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
		                	filename: 'WILMU_OnLoanItemListing',
		                	title: 'On Loan Item Listing ',
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
										
										return "On Loan Item Listing "+titleHeader +sDate + " until "+ eDate +"\n\n" + doc;
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
		    	url: "ResultOnLoanItemListing",
		        data : {startSentDate : startSentDate, endSentDate : endSentDate,  brnch : JSON.stringify(brnch),
						chkBoxSearchCateria : chkBoxSearchCateria, searchCateriaval : JSON.stringify(searchCateriaval),
						type : type
						},
		        type: "GET",
		        dataSrc: function (json) {
		            var return_data = new Array();
		            
		            for(var i=0;i< json.length; i++){

					var callno = json[i].Column5;
					
					if(callno!=undefined){
						if(callno.charAt(0)=='*'){
							callno = callno.slice(1);
							callno = "<i>"+callno+"</i>";
						}	
					}else{
						callno = "";
					}


					var title = json[i].Column4;
					
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
						'Borrow Date/Time' : json[i].Column2,
						'Due Date/Time' : json[i].Column3,
		                'Title' : title,
		                'Call No' : callno,
		                'Patron ID/Name' : json[i].Column6,
						'Loan Type' : json[i].Column7,
						'Overdue Notice' : json[i].Column8,
						'Branch' : json[i].Column9,
						'Location' : json[i].Column10,
		            })
		            }
					
		            return return_data;
		          },
		     },//This is the end of the AJAX object from the example above
		     	columns    : [
					{'data': 'No'},
					{'data': 'Accession No'},
					{'data': 'Borrow Date/Time'},
					{'data': 'Due Date/Time'},
					{'data': 'Title'},
					{'data': 'Call No'},
					{'data': 'Patron ID/Name'},
					{'data': 'Loan Type'},
					{'data': 'Overdue Notice'},
					{'data': 'Branch'},
					{'data': 'Location'},
					
					
										
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