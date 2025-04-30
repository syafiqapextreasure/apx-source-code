$(document).ready(function() {
	
	//////////////////////////////////////////////////////////////////////////
	//////////////////////////setup page//////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////
	
	///$('#Reterive').prop('disabled', true);
	
	$('#patronCate, #branch, #patronStat').multiselect({
    	allSelectedText: 'All',
   		maxHeight: 200,
   		includeSelectAllOption: true
	});
	
	$("#patronCate, #patronStat").multiselect("disable");
	
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
	
	//////////////check on change
	$("#chkPatrStat").on('change', function () {
	    var self = $(this);
	    if (self.is(":checked")) {
			$('#patronStat').multiselect("enable");
	    } else {
			$('#patronStat').multiselect("disable");
	    }
	});
	
	$("#chkPatrCate").on('change', function () {
	    var self = $(this);
	    if (self.is(":checked")) {
			$('#patronCate').multiselect("enable");
	    } else {
			$('#patronCate').multiselect("disable");
	    }
	});

	
	//////table setup
	$('#membershipTable').DataTable( {
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
		$('#membershipTable').dataTable().fnClearTable();
		
		
		var startSentDate = moment($("#input-startDate").val(), 'DD/MM/YYYY').format("YYYYMMDD");
		var endSentDate = moment($("#input-endDate").val(), 'DD/MM/YYYY').format("YYYYMMDD");
		
		var startSentDate = $("#input-startDate").val();
		if(startSentDate == ''){
			startSentDate = '';
		}else{
			startSentDate = moment($("#input-startDate").val(), 'DD/MM/YYYY').format("YYYYMMDD");
		}
		
		var endSentDate = $("#input-endDate").val();
		if(endSentDate == ''){
			endSentDate = '';
		}else{
			endSentDate = moment($("#input-endDate").val(), 'DD/MM/YYYY').format("YYYYMMDD");
		}

		
		////patron status
		var patrStatValue;
		var patrStatByTest
		var patrStat = $('#chkPatrStat:checked').val();
    	if(patrStat == "patrStat"){
    		patrStat = "Y";
			patrStatValue = $("#patronStat").val();
			patrStatByTest = $( "#patronStat option:selected" ).text();
    	}else{
    		patrStat = "N";
			patrStatValue = '';
			patrStatByTest = '';
    	}
		
		
		////patron cate
		var patrCateValue;
		var patrCate = $('#chkPatrCate:checked').val();
    	if(patrCate == "patrCate"){
    		patrCate = "Y";
			patrCateValue = $("#patronCate").val();
    	}else{
    		patrCate = "N";
			patrCateValue = '';
    	}

		
		var brnch = $("#branch").val(); 
		if(brnch != ""){
		}else{
			brnch = 0;
		}


		/////result display
		var t = $('#membershipTable').DataTable( {
			dom: 'Bfrtip',
			buttons: [
				 /*'excel', 'pdf', 'print'*/
				{
					extend: 'excelHtml5',
					filename: 'WILMU_MembershipRegistration',
					title: 'Statistics Preview from  '+startSentDate + " until "
						+endSentDate +" " +patrStatByTest,

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
							filename: 'WILMU_MembershipRegistration',
							orientation: ' landscape', //portrait
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
								
						
							// doc.content[1].table.widths = [ '8%', '55%', '12%', '12%', '12%']; 
						
						


											//Remove the title created by datatTables
											doc.content.splice(0, 1, {
						                        text: [
						                                   { text: 'Statistics Preview from  '+startSentDate + ' until ' +endSentDate +" " +patrStatByTest+' \n',bold:true,fontSize:13,alignment: 'center'},

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
					     	extend: 'print',
							//autoPrint: false,
							///orientation: 'landscape',
                    		pageSize: 'A4',
                			text: '<i class="glyphicon glyphicon-print" aria-hidden="true"></i> Print',
							footer: true,
							title: '',
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
					 						$(win.document.body).prepend( "<div class='center'><b>Statistics Preview from "+startSentDate
												+" until "+ +endSentDate +" "+ patrStatByTest+ "</b></div>");
					                    	$(win.document.body).find( 'table' ).addClass( 'compact' ).css( 'font-size', 'inherit' );
											$(win.document.body).prepend( "<div style='float: left'></div><div style='float: right'>"
											+moment(today).format("DD/MM/YYYY") +'<br>' 
				                    		+moment(today).format("hh:mm:ss A")+"</div><div style='margin: 0 auto; width: 100px;'></div>");
					                },
									exportOptions: {
										//columns: [ 0, 1, 2, 3, 4, 5, 6 ]
									},
					    },
					
               			{
		                	extend: 'csv',
		                	filename: 'WILMU_MembershipRegistration',
		                	//title: 'Circulation Activity By ',
							customize: function(doc){
										
										var split_csv = doc.split("\n");
										//alert(split_csv)
										$.each(split_csv.slice(1), function (index, csv_row) {
											var csv_cell_array = csv_row.split('","');
											csv_cell_array[1].replace("", (index+1));
										});
										///alert(csv_cell_array[1]); 
										//alert(csv_cell_array[1]);
										
										return "Statistics Preview from  "+startSentDate + " until " +endSentDate +" "+ patrStatByTest +"\n\n" + doc;
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
			deferRender: true,
			language: {
             loadingRecords : "Please wait - loading...",
              processing :     "Please wait formatting in progress..."
        	},
			footer: true,
			/*language: {
        processing: "Loading. Please wait..."
    },*/
		    ajax: {
		    	url: "ResultMembershipReq",
		        data : {startSentDate : startSentDate, endSentDate : endSentDate, 
						patrStatValue : JSON.stringify(patrStatValue), patrStat : patrStat,
						patrCateValue : JSON.stringify(patrCateValue), patrCate : patrCate,
						brnch : JSON.stringify(brnch)
						},
		        type: "GET",
		        dataSrc: function (json) {
		            var return_data = new Array();
		            
		            for(var i=0;i< json.length; i++){
			
					if(json[i].Column2 == " "){
						json[i].Column2 = "0.00";
					}
					
					if(json[i].Column3 == " "){
						json[i].Column3 = "0.00";
					}

		              return_data.push({
		                'No': (i+1),
		                'Patron Category' : json[i].Id + " - " +json[i].Desc,
		                'New Membership' : json[i].Column1,
		                'Registration Fee (RM)' : json[i].Column2,
		                'Deposit (RM)' : json[i].Column3,
		            })
		            }
					
		            return return_data;
		          },
		     },//This is the end of the AJAX object from the example above
		     	columns    : [
					{'data': 'No'},
					{'data': 'Patron Category'},
					{'data': 'New Membership', className: "text-right"},
					{'data': 'Registration Fee (RM)', className: "text-right", render: $.fn.dataTable.render.number(',', '.', 2, '')},
					{'data': 'Deposit (RM)', className: "text-right", render: $.fn.dataTable.render.number(',', '.', 2, '')},
				],
				//order: [[ 1, 'asc' ]],

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
		            total1 = api
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
		                total1
		            );

					///discharge
		            // Total over all pages
		            total2 = api
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
		                total2.toFixed( 2 )
		            );

					///renewal
		            // Total over all pages
		            total3 = api
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
		                total3.toFixed( 2 )
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