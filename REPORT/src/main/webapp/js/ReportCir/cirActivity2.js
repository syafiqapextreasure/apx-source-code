$(document).ready(function() {
	
	//////////////////////////////////////////////////////////////////////////
	//////////////////////////setup page//////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////
	
	$('#Reterive').prop('disabled', true);
	
	$('#patronCate, #smd, #branch, #icat, #loca').multiselect({
    	allSelectedText: 'All',
   		maxHeight: 200,
   		includeSelectAllOption: true
	});
	
	$("#patronCate, #smd, #icat, #loca").multiselect("disable");
	
	/////$(".branchrepcir").hide();
	
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
	
	
	
	///on click searc patron 
	$('.patronid').on('click',function(){
		var url = "Modal_PatrSearch";
		
		$.get(url,function(data){
			$("#Modal_PatrSearchContent").html(data);
		});
	});
	
	///keyup
	$("#lblPatronID").keyup(function(e){
		var id = $("#lblPatronID").val();

		$(".lblName").empty();
		////display vendor name
		$.get('GetPatronName', {
        	id : id
		 	}, function(responseJson) {
			if(responseJson != null){
				$.each(responseJson, function(key,value) {
					$(".lblName").text(value['Name']);
				});
			}
		});
	});
	
	
	//clear title when paron id keydown backspace
	$("#lblPatronID").keydown(function(e){ 
		var code = e.keyCode || e.which;
		if(code == '8' ||code == '46'){
			$("#.lblName").empty();
		}
		if (code === 13) { 
			e.preventDefault();
		    return false;
		}
	});
	
	//////////////radio button on change
	$('input[type=radio][name=chkBoxSearchCateria]').on('change', function() {
		
		$("#smd, #icat, #loca,#patronCate").multiselect("clearSelection");
		
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
			eDate =  $("#input-endDate").val();
		}
		
		/*var brnch = $("#branch").val(); 
		if(brnch != ""){
		}else{
			brnch = 0;
		}*/
		
		var staffid = $("#lblPatronID").val();
		
		var head_item = $('#reportTable').DataTable().columns(1).header();
		var titleHeader;
		
		
		var chkBoxSearchCateria = $("input[name='chkBoxSearchCateria']:checked").val();
		var searchCateriaval;
		switch(chkBoxSearchCateria) {
		  case 'pateCate':
			searchCateriaval = $('#patronCate').val();
			titleHeader = "Membership Category";
			$(head_item ).html(titleHeader);
		    // code block
		    break;
		  case 'smd':
			searchCateriaval = $('#smd').val();
			titleHeader = "SMD";
			$(head_item ).html(titleHeader);
			//$(head_item ).html('SMD header');
		    // code block
		    break;
		  case 'itemcate':
			searchCateriaval = $('#icat').val();
			titleHeader = "Item Category";
			$(head_item ).html(titleHeader);
		    // code block
		    break;
		  case 'loca':
			searchCateriaval = $('#loca').val();
			titleHeader = "Item Location";
			$(head_item ).html(titleHeader);
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
		
		var unReturn = $("input[name='unReturn']:checked").val();
		
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
					        filename: 'WILMU_CirActivity',
					        title: 'Circulation Activity By '+titleHeader +sDate + " until " +eDate,

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
							filename: 'WILMU_CirActivity',
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
								
								 var rowCount = doc.content[1].table.body.length;
						           for (i = 0; i < rowCount; i++) {
										doc.content[1].table.body[i][2].alignment = 'right';
						               	doc.content[1].table.body[i][3].alignment = 'right';
										doc.content[1].table.body[i][4].alignment = 'right';
						           };
								
								//doc.content[1].table.widths = Array(doc.content[1].table.body[0].length + 1).join('*').split('');
							//	doc.content[1].table.widths = [30,100,50,50,50];
							//doc.content[1].table.body[0][3].alignment = 'right';
							 doc.content[1].table.widths = [ '8%', '55%', '12%', '12%', '12%']; 
							 //doc.content[1].table.body[0][5].alignment = 'right';
						
						


											//Remove the title created by datatTables  
											doc.content.splice(0, 1, {
						                        text: [
						                                   { text: 'Circulation Activity By '+titleHeader +sDate+ ' until '+eDate+ '\n',bold:true,fontSize:13,alignment: 'center'},
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
		                	filename: 'WILMU_CirActivity',
		                	title: 'Circulation Activity By ',
							customize: function(doc){
										
										var split_csv = doc.split("\n");
										//alert(split_csv)
										$.each(split_csv.slice(1), function (index, csv_row) {
											var csv_cell_array = csv_row.split('","');
											csv_cell_array[1].replace("", (index+1));
										});
										///alert(csv_cell_array[1]);
										//alert(csv_cell_array[1]);  +" from " +startSentDate + " until" +endSentDate
										
										return "Circulation Activity By "+titleHeader +sDate + " until "+ eDate +"\n\n" + doc;
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
             processing :     "Please wait formatting in progress..."
        	},
			/*language: {
                    processing: '<i class="fa fa-spinner fa-spin" style="font-size:24px;color:rgb(75, 183, 245);"></i>'
            },*/
			//serverSide: true,
			deferRender: true,
			footer: true,
		    ajax: {
		    	url: "ResultCirAct2",
		        data : {startSentDate : startSentDate, endSentDate : endSentDate, staffid: staffid,//brnch : JSON.stringify(brnch), 
						chkBoxSearchCateria : chkBoxSearchCateria, searchCateriaval : JSON.stringify(searchCateriaval),
						type : type, unReturn : unReturn
						},
		        type: "GET",
		        dataSrc: function (json) {
		            var return_data = new Array();
		            
		            for(var i=0;i< json.length; i++){

		              return_data.push({
		                'No': (i+1),
		                'Description' : json[i].Id + " - " +json[i].Desc,
		                'Charge' : json[i].Column1,
		                'Discharge' : json[i].Column2,
		                'Renewal' : json[i].Column3,
		            })
		            }
					
		            return return_data;
		          },
		     },//This is the end of the AJAX object from the example above
		     	columns    : [
					{'data': 'No'},
					{'data': 'Description'},
					{'data': 'Charge', className: "text-right"},
					{'data': 'Discharge', className: "text-right"},
					{'data': 'Renewal', className: "text-right"},
					
					
				],
				footerCallback: function ( row, data, start, end, display ) {
		            var api = this.api(), data;
		 
		            // Remove the formatting to get integer data for summation
		            var intVal = function ( i ) {
		                return typeof i === 'string' ?
		                    i.replace(/[\$,]/g, '')*1 :
		                    typeof i === 'number' ?
		                        i : 0;
		            };
		 
					///charge
		            // Total over all pages
		            totalCharge = api
		                .column( 2 )
		                .data()
		                .reduce( function (a, b) {
		                    return intVal(a) + intVal(b);
		                }, 0 );
		 
		            // Total over this page
		            /*pageTotal = api
		                .column( 3, { page: 'current'} )
		                .data()
		                .reduce( function (a, b) {
		                    return intVal(a) + intVal(b);
		                }, 0 );*/
		 
		            // Update footer
		           $( api.column( 2 ).footer() ).html(
		                totalCharge
		            );

					///discharge
		            // Total over all pages
		            totalCharge = api
		                .column( 3 )
		                .data()
		                .reduce( function (a, b) {
		                    return intVal(a) + intVal(b);
		                }, 0 );
		 
		            // Total over this page
		            /*pageTotal = api
		                .column( 3, { page: 'current'} )
		                .data()
		                .reduce( function (a, b) {
		                    return intVal(a) + intVal(b);
		                }, 0 );*/
		 
		            // Update footer
		           $( api.column( 3 ).footer() ).html(
		                totalCharge
		            );

					///renewal
		            // Total over all pages
		            totalCharge = api
		                .column( 4 )
		                .data()
		                .reduce( function (a, b) {
		                    return intVal(a) + intVal(b);
		                }, 0 );
		 
		            // Total over this page
		            /*pageTotal = api
		                .column( 3, { page: 'current'} )
		                .data()
		                .reduce( function (a, b) {
		                    return intVal(a) + intVal(b);
		                }, 0 );*/
		 
		            // Update footer
		           $( api.column( 4 ).footer() ).html(
		                totalCharge
		            );
		        }
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