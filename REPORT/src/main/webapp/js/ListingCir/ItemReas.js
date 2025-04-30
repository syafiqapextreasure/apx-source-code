$(document).ready(function() {
	
	//////////////////////////////////////////////////////////////////////////
	//////////////////////////setup page//////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////
	//$('#Reterive').prop('disabled', true);
	
	//$(".patriddisplay").html("<b>Reassigned by</b>");
	//$("label[for='labelPatrId']").text('Reassigned by');

	
	$('#oriloca, #reassignloca').multiselect({
    	allSelectedText: 'All',
   		maxHeight: 200,
   		includeSelectAllOption: true
	});
	
	//S$("#oriloca, #reassignloca").multiselect("disable");
	
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
		
	
	
	
	//////table setup
	$('#reportTable').DataTable( {
		destroy: true,
		searching: false,
		bLengthChange: false,
		autoWidth: false,
		responsive: true,
	});
	
	
	
	/*$('input[type=checkbox][name=chkBoxSearchCateria]').on('change', function() {
		alert($(this).val());
		switch ($(this).val()) {
			case 'date':
				if($(this).is(":checked")) {
		          	$('#Reterive').prop('disabled', false);
		        }else{
					$('#Reterive').prop('disabled', true);	
				}
		      break;
			case 'oriLoca':
				$('#oriloca').multiselect("enable");
				//$("#smd, #icat, #loca").multiselect("disable");
				$('#Reterive').prop('disabled', true);
					$('#oriloca').change(function() {
						if($("#patronCate").val() != ""){
							$('#Reterive').prop('disabled', false);
						}else{
							$('#Reterive').prop('disabled', true);
						}
					 });
					if($(this).is(":checked")) {
			          	$('#oriloca').multiselect("enable");
			        }else{
						$('#oriloca').multiselect("disable");	
					}
		      break;
		}
	});
	*/


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
		
		var oriloca = $("#oriloca").val(); 
		if(oriloca != ""){
		}else{
			oriloca = 0;
		}
		
		var reassignloca = $("#reassignloca").val(); 
		if(reassignloca != ""){
		}else{
			reassignloca = 0;
		}
		
		var patrID = $('#lblPatronID').val();
		
		
		/////result display
		var t = $('#reportTable').DataTable( {
			dom: 'Bfrtip',
            buttons: [
                /*'excel', 'pdf', 'print'*/
						{
					  		extend: 'excelHtml5',
					        filename: 'WILMU_ItemReas',
					        title: "Item Reassignment Listing " +sDate + " until " +eDate,

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
							filename: 'WILMU_ItemReas',
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
						  ///////////////////// doc.content[1].table.widths = [ '3%', '10%', '10%', '10%', '7%','7%','7%','7%','7%','10%','8%'];  
							 //doc.content[1].table.body[0][5].alignment = 'right';
						
						


											//Remove the title created by datatTables
											doc.content.splice(0, 1, {
						                        text: [
						                                   { text: 'Item Reassignment Listing '+sDate+ ' until '+eDate+ ' \n',bold:true,fontSize:13,alignment: 'center'},
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
		                	filename: 'WILMU_ItemReas',
		                	title: 'Item Reassignment Listing ',
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
										
										return "Item Reassignment Listing " +sDate + " until "+ eDate +"\n\n" + doc;
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
		    	url: "ResultItemReas",
		        data : {startSentDate : startSentDate, endSentDate : endSentDate,  oriloca : JSON.stringify(oriloca),
						reassignloca : JSON.stringify(reassignloca), patrID : patrID
						},
		        type: "GET",
		        dataSrc: function (json) {
		            var return_data = new Array();
		            
		            for(var i=0;i< json.length; i++){

					var callno = json[i].Column2;
					
					if(callno!=undefined){
						if(callno.charAt(0)=='*'){
							callno = callno.slice(1);
							callno = "<i>"+callno+"</i>";
						}	
					}else{
						callno = "";
					}


					var title = json[i].Column3;
					
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
		                'Reassigned For' : json[i].Column4,
						'Ori Location/Branch' : json[i].Column6,
						'Temp Location/Branch' : json[i].Column5,
						'Start Date' : json[i].Column7,
						'End Date' : json[i].Column8,
						'Processed Date': json[i].Column9,
						'Reassigned By' : json[i].Column10,
						'Status' : json[i].Column11,
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
					{'data': 'Reassigned For'},
					{'data': 'Ori Location/Branch'},
					{'data': 'Temp Location/Branch'},
					{'data': 'Start Date'},
					{'data': 'End Date'},
					{'data': 'Processed Date'},
					{'data': 'Reassigned By'},
					{'data': 'Status'},
					
										
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