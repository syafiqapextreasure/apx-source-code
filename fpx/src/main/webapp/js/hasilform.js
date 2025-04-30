$(document).ready(function() {

	var today = new Date(); 
	////branch multi select
	/*$('#branch').multiselect({
    	allSelectedText: 'All',
   		maxHeight: 200,
   		includeSelectAllOption: true,
		numberDisplayed: 1,
	});*/
	
	//$('#example-getting-started').multiselect();
	$('#paymode').multiselect({
    	allSelectedText: 'All',
   		maxHeight: 200,
   		includeSelectAllOption: true
	});
	
	//////table setup
	$('#hasilformTable').DataTable( {
		destroy: true,
		searching: false,
		bLengthChange: false,
		bPaginate: false,
		/*autoWidth: false,*/
		responsive: true,
	});
	
	$('.tablesumkod').DataTable( {
		destroy: true,
		searching: false,
		bLengthChange: false,
		/*autoWidth: false,*/
		responsive: true,
		bInfo : false,
		bPaginate: false,
		bFilter: false
	});
	
	$('.tablesumkod2').DataTable( {
		destroy: true,
		searching: false,
		bLengthChange: false,
		/*autoWidth: false,*/
		responsive: true,
		bInfo : false,
		bPaginate: false,
		bFilter: false
	});
	
	$('.tablesumkod3').DataTable( {
		destroy: true,
		searching: false,
		bLengthChange: false,
		/*autoWidth: false,*/
		responsive: true,
		bInfo : false,
		bPaginate: false,
		bFilter: false
	});
	
	$('.tablesumkod4').DataTable( {
		destroy: true,
		searching: false,
		bLengthChange: false,
		/*autoWidth: false,*/
		responsive: true,
		bInfo : false,
		bPaginate: false,
		bFilter: false
	});
	
	$('.teststu').DataTable( {
		destroy: true,
		searching: false,
		bLengthChange: false,
		/*autoWidth: false,*/
		responsive: true,
		bInfo : false,
		bPaginate: false,
		bFilter: false
	});
	
	$("#Reterive").prop( "disabled", true);
	$("#branch").prop("selectedIndex",-1);
	
	//////Payment Mode
	$("#chkPaymentMode").on('change', function () {
	    var self = $(this);
	    if (self.is(":checked")) {
			$('#paymode').multiselect("enable");
	    } else {
			$('#paymode').multiselect("disable");
	    }
	});
	
	
	//onchange branch
	$("#branch").change(function() {

		if($(this).val() == '' || $('#tarikhtran').val() == ''){
			$("#Reterive").prop( "disabled", true);
		}else{
			$("#Reterive").prop( "disabled", false);
		}
	});
	
	//onchange paymode
	$("#paymode").change(function() {

		if( $('#tarikhtran').val() == '' || $('#branch').val() == '' || $('#branch').val() == null){
			$("#Reterive").prop( "disabled", true);
		}else{
			$("#Reterive").prop( "disabled", false);
		}
	});
	
	//on change tarikh
	$("#tarikhtran").change(function() {

		if($(this).val() == '' || $('#branch').val() == '' || $('#branch').val() == null ){
			$("#Reterive").prop( "disabled", true);
		}else{
			$("#Reterive").prop( "disabled", false);
		}
	});
	
	if (window.location.href.indexOf("https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css") > -1) {
		//alert ("test");
}
	//////////////////////////////////////////////////////////////////////////
	////////////////////////btn Reterive Click ///////////////////////////////
	//////////////////////////////////////////////////////////////////////////
	$('#Reterive').click(function(){
		
		$('#hasilformTable').dataTable().fnClearTable();
		
		var trandate = moment($("#tarikhtran").val(), 'DD/MM/YYYY').format("YYYYMMDD");
		var trax = moment(trandate).locale('ms-my').format('dddd').toUpperCase();
		var branch = $('#branch').val();
		var payMode = $('#paymode').val();
		
		
		var numberofcolumn = "1,2,3, 4, 5, 6, 7, 8, 9";
		
		var paymodeVal;
		paymodeVal = $('#paymode').val();
		
		//alert("paymodeVal"+paymodeVal);
		//alert(" JSON.stringify(paymodeVal)"+ JSON.stringify(paymodeVal));
		
		//let lgth = 0;
		
		/////result display
		var t = $('#hasilformTable').DataTable( {
			dom: 'Bfrtip',
			"autoWidth": true,
			autoWidth: true,
			autoHeigth: true,
            buttons: [
						/*{
					  		extend: 'excelHtml5',
					        filename: 'WILMU_LocationListing',
					        title: 'Location Listing',
					                
					 	},*/
						{
					    	text: '<i class="fa fa-file-pdf-o"></i> PDF',
							extend: 'pdfHtml5',
							filename: 'WILMU_LaporanHasil',
							orientation: 'landscape', //portrait
							pageSize: 'A4', //A3 , A5 , A6 , legal , letter
							header: true,
							footer: true,
							exportOptions: {
								 //columns: numberofcolumn,
							 columns: [ 1, 2, 3, 4, 5, 6, 7, 8 , 9, 10],
							},
							customize: function (doc) {
								
								doc.pageMargins = [40,100,40,50];
								
								//console.log(doc + JSON.stringify(doc) +'( THIS IS DOC CONSOLE)')
								doc.content[1].table.headerRows = 1
								
								/*if (doc.content[1].table.body[i][2] =b){
									
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
										doc.content[1].table.body[i][8].alignment = 'left';
										doc.content[1].table.body[i][9].alignment = 'right';
										//doc.content[1].table.body[i][10].alignment = 'right';
						           };
								
							 	//doc.content[1].table.widths = [ '10%', '3%', '3%','4%', '17%', '12%', '12%', '15%','10%', '7%','7%',];
								doc.content[1].table.widths = [ '3%', '3%','4%', '17%', '13%', '13%', '23%','10%', '7%','7%'];
							
							doc.content.push( {
						                        //margin: [ 0, 0, 0, 12 ],
						                        alignment: 'left',
												bold:true,
								                   text: [
								                         	'\n\n'
								                        ]
	
											 } );

								doc.content.push( {
						                        //margin: [ 0, 0, 0, 0 ],
						                        alignment: 'left',
												bold:true,
												
												table:{
													headerRows: 1,
													//dontBreakRows: true,
								                   body: [
															['KOD KEWANGAN', 'TOTAL BAYARAN (RM)'],
															[{ text: $('.tablesumkod tbody').text(), alignment: 'left'}, { text: $('.tablesumkod2 tbody').text(), alignment: 'right'}],
															[{ text: "JUMLAH (RM)", bold: true, alignment: 'right'}, { text: $('.teststu tbody').text(), alignment: 'right'}]
								                        ]
													  }
											 } );
										
										doc.content.push( {
						                        //margin: [ 0, 0, 0, 12 ],
						                        alignment: 'left',
												bold:true,
								                   text: [
								                         	'\n\n\n\n'
														
								                        ]
	
											 } );
								
								doc.content.push({pageBreak: 'before',
														  table: {
														    headerRows: 1,
														    widths: [ "*", "*", "*", "*" ],
															heights: [20, 70, 20, 20],
															//pageBreak : true,
															//	dontBreakRows: true,
															
														    body: [
														      [
														        { text: "DISEDIAKAN OLEH", bold: true, alignment: 'center'},
														        { text: "DISEMAK OLEH", bold: true, alignment: 'center'},
														        { text: "DIHANTAR OLEH", bold: true, alignment: 'center'},
														        { text: "DITERIMA OLEH ", bold: true, alignment: 'center'}
														      ],
														      [ { text: " ", bold: true, alignment: 'left',border: [true, false, false, false]},
													 			{ text: " ", bold: true, alignment: 'left',border: [true, false, false, false]}, 
																{ text: " ", bold: true, alignment: 'left',border: [true, false, false, false]},
												 				{ text: " ", bold: true, alignment: 'left',border: [true, false, true, false]} 
															  ],
														      [ { text: "NAMA & TANDATANGAN", bold: true, alignment: 'center',border: [true, false, false, false]},
													 			{ text: "NAMA & TANDATANGAN", bold: true, alignment: 'center',border: [true, false, false, false]}, 
																{ text: "NAMA & TANDATANGAN", bold: true, alignment: 'center',border: [true, false, false, false]},
												 				{ text: "NAMA & TANDATANGAN", bold: true, alignment: 'center',border: [true, false, true, false]} 
															  ],
															  [ { text: "TARIKH :", bold: true, alignment: 'left'},
													 			{ text: "TARIKH :", bold: true, alignment: 'left'}, 
																{ text: "TARIKH :", bold: true, alignment: 'left'},
												 				{ text: "TARIKH :", bold: true, alignment: 'left'} 
															  ],
													
														    ]
														  }
														});

											//Remove the title created by datatTables  
											doc.content.splice(0, 1, {
													table:{
														headerRows: 1,
														widths: [ "*"],
								                  	 		body: [
																	[
																        { text: ''},
																      ],
								                        		]
													  },
											layout: 'noBorders'
						                    });

											doc['header']=(function(page, pages) {
												
												return {
																	margin: [20, 20, 20, 20],
															        columns: [
															            {
														
														table:{
														headerRows: 1,
														widths: [ "20%", "10%", "60%", "10%" ],
								                  	 		body: [
																	[
																        { text: "*Salinan Kaunter Hasil Jabatan Kewangan, PPJ \n\n**Salinan Fail PPK "+branch+ '', bold: true, alignment: 'left' ,border: [false, false, false, false]},
																        { image : 'data:image/png;base64,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',
																		 					border: [false, false, false, false], width: 40,height: 50, alignment: 'center'  },
																        { text: 'LAPORAN HASIL HARIAN PUSAT PEMBELAJARAN KEJIRANAN \n'+$("#branch option:selected").text()+" \nTARIKH KUTIPAN : " +moment($("#tarikhtran").val(), 'DD/MM/YYYY' ).locale('ms-my').format("DD MMMM YYYY").toUpperCase()+" ("+ trax +")"+ " \nMOD PEMBAYARAN : " +$("#paymode option:selected").text()+ '',bold:true,fontSize:13,alignment: 'center',border: [true, true, true, true]},
																        { text: "", bold: true, alignment: 'center',border: [false, false, false, false] }
																      ],
								                        		]
													  },
														layout: 'noBorders',
														}
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
		                	extend: 'csv',
		                	filename: 'WILMU_LocationListing',
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
										
										return "Location Listing" +"\n\n" + doc;
									 }
             			},*/
            ],
			destroy: true,
			searching: true,
			bLengthChange: false,
			/*autoWidth: false,*/
			responsive: true,
			processing: true,
			language: {
             loadingRecords : "Please wait - loading...",
             processing :     "Please wait formatting in progress..."
        	},
			deferRender: true,
			footer: true,
		    ajax: {
		    	url: "ResultHasilForm",
		        type: "GET",
				data : {trandate : trandate, branch : branch, paymodeVal : JSON.stringify(paymodeVal),},
				/*start_time: new Date().getTime(),
				    complete: function(data) {
				        alert('This request took '+(new Date().getTime() - this.start_time)+' ms');
				},*/
		        dataSrc: function (json) {
		            var return_data = new Array();
					//alert(return_data + " return data apa ni?")
					var lgth = 0;
					var longest;
		            
		            for(var i=0;i< json.length; i++){
			
					var newKod = json[i].Kodkewangan;
					
					//alert(newKod + " NEWKOD apa ni?")
					//console.log( newKod + " NEWKOD apa ni?");
					
					
					if (newKod.length > lgth) {
					    var lgth = newKod.length;
					    longest = newKod;
					  }
			
					//alert(longest +"fff");
					
					$(".getLength").val(longest.length)
					
			
					/*var convertString = [newKod.slice(0, 30), ' : ', newKod.slice(30)].join('');
					alert(convertString)*/

		              return_data.push({
							'Kod kewangan' : 'KOD ' + json[i].Kodkewangan,
		               		'No': (i+1),
			                'Kod' : json[i].Kod,
			                'Kod Kewangan' : json[i].KodKewangan,
			                'Tajuk Kod Kewangan' : json[i].TajukKodKewangan,
			                'No Resit' : json[i].NoResit,
							'No ID' : json[i].NoID,
							'Name Ahli' : json[i].NameAhli,
							'Patron Cat' : json[i].PatronCat,
							'Pay Mode' : json[i].PayMode,
							'Bayaran' : json[i].Bayaran,
							
		            	})
		            }
					//console.log(return_data + "( ///return_data)");
		            return return_data;
		          },
		     },//This is the end of the AJAX object from the example above
		     	columns    : [
					{'data': 'Kod kewangan', visible: false, className: "text-right"},
					{'data': 'No'},
					{'data': 'Kod'},
					{'data': 'Kod Kewangan'},
					{'data': 'Tajuk Kod Kewangan'},
					{'data': 'No Resit'},
					{'data': 'No ID'},
					{'data': 'Name Ahli'},
					{'data': 'Patron Cat'},
					{'data': 'Pay Mode'},
					{'data': 'Bayaran', className: "text-right", render: $.fn.dataTable.render.number(',', '.', 2, '')},
				],
				
				
				
			drawCallback: function ( settings ) {
									$('.branch_info tr').detach();
									/////////// 
										var numFormat = $.fn.dataTable.render.number( '\,', '.', 2, '' ).display
									/////////////
									///////////////////////////////////
								            var api = this.api();
											//console.log(api + " APIIIII");
									//////////////////////////////////
									///////////////////////////////////////////////////////
									///////////////////////////////////////////////////////
									/*const getCircularReplacer = () => {
																	  const seen = new WeakSet();
																	  return (key, value) => {
																	    if (typeof value === 'object' && value !== null) {
																	      if (seen.has(value)) {
																	        return;
																	      }
																	      seen.add(value);
																	    }
																	    return value;
																	  };
																	};*/
									
											//alert(api.rows( {page:'all'} ).nodes() + ' = (a row of data ?)');
								            var rows = api.rows( {page:'all'} ).nodes();
											//const result = JSON.stringify(rows, getCircularReplacer());
											//console.log(result + ' (is this rows?)');
       										//console.log(rows+"rows");
									//////////////////////////////////////////////////////
									//////////////////////////////////////////////////////
								            var last=null;
								     
								            // Remove the formatting to get integer data for summation
									          var intVal = function ( i ) {
										//alert(i + ' = (what is i ?)');
									          	return typeof i === 'string' ?
											          i.replace(/[\$,]/g, '')*1 :
											          typeof i === 'number' ?
												               i : 0;
									          };
								          //console.log(intVal+"( intVal)");
									/////////////////////////////////////////////////////////////
									//////////////////////////////////////////////////////////////////////////
								            total=[];
											
									//alert(api.column(0).data().display + " (FIRST TOTAL)");
								            api.column(0, {page:'all'} ).data().each( function ( group, i ) {
									//console.log(group +"( Hello Group TOP)");
									
								              group_assoc=group.replace(' '," ");
											//alert(JSON.stringify(group_assoc) +('-')+ JSON.stringify(total[group_assoc]) + (' API Column, group!'));
								              //console.log(group_assoc + " (Group Assoc)");
								              if(typeof total[group_assoc] != 'undefined'){
								            total[group_assoc]=total[group_assoc]+intVal(api.column(10).data()[i]);
								        }else{
								            total[group_assoc]=intVal(api.column(10).data()[i]);
								        }
											//console.log(total[group_assoc] +"( Hello Group)");
											
								              if ( last !== group ) {
									//console.log(last+" (LAST)");
								                    $(rows).eq( i ).before(
														'<tr class="group"><td colspan="8" >'+group+'</td><td class="'+group_assoc+'"></td>\n<td></td></tr>'
								                    );
								 
								                    last = group;
								              }
								            } );
////////////////////////////////////////////TOTAL/////////////////////////////////////////////////////////////////////////
										// Total over all pages
						            total2 = api.column( 10 ).data().reduce( function (a, b) {
						                    return intVal(a) + intVal(b);
						                }, 0 );

									//(#test)=numFormat(total12);
									$('.teststu').prepend("<tr><div><td colspan='6'><b>"
											+ numFormat(total2) +"\n</b></td>"
											+"</div></tr>");
											
									

						           /* $( api.column( 9 ).footer() ).html(
						                numFormat(total2)
						            );*/
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

										for(var key in total) {
											//console.log(key +" (KEY!)")
											var getLength = $(".getLength").val();
											
											/*alert(getLength+" getLength");
											alert(key.length+" key.length"+key+" key");*/
											
											/*alert(getLength+" getLength");
											alert(key.length+" key.length");*/
											
											var diffLength =  getLength - (key.length);
											/*alert(diffLength+"diffLength");
											alert(key + "W".repeat(diffLength));
											alert(diffLength+"diffLength");*/
											
											/*alet(getLength+" getLength");
											alert(key.length+" key.length"+key+" key");*/
											
											//alert(key.length)
											//alert(key+ ":" +total[key])
											//$("."+key).html(total[key]);
								        	//$("."+key).html(total[key].toFixed(4));
											//$("."+key).html( numFormat(total[key]));
	
											/*$(".idbranch").text(key);
											$("#totalbybranch").text(total[key].toFixed(4));*/
											
											/*$('.branch_info').prepend("<tr><td class='row branchtotal'></td><td class='form-group'></td><td><b>\n "
											+key+"</b></td><td><b> : RM </b></td><td class='text-right'><b>"
											+numFormat(total[key])
											+"</b></td></tr>");*/
											
											//this where the TOTAL AMOUNT OUTPUT is at.
											$('.tablesumkod3').prepend("<tr><div><td colspan='6'><b>\n"
											+key + /*"&nbsp;".repeat(diffLength)+*/"\t</b></td><td><b>: RM</b></td><td ><b>"
											+numFormat(total[key])
											+"</b></td></div></tr>");
											
											//this where the TOTAL AMOUNT OUTPUT is at.
											$('.tablesumkod').prepend("<tr><div><td colspan='6'><b>"
											+key +"\n\t</b></td><td ><b>"
											+"</b></td></div></tr>");
											
											
											$('.tablesumkod2').prepend("<tr><div><td colspan='6'><b>"
											+ numFormat(total[key]) +"\n</b></td>"
											+"</div></tr>");
											
											
											
											
											//this where the TOTAL AMOUNT OUTPUT is at.
											/*$('.tablesumkod').prepend("<div style='overflow-x: auto;'><table style='width: 565px;'><col style='width: 400px;'/><tr></tr><tr><td>|<b> "+key+ "&nbsp;".repeat(diffLength)+"</b></td><td style = 'text-align:center'><b> : RM</b></td><td><b>"+numFormat(total[key])+" </b>|\t\n</td></tr></table></div>");*/
											
											/*$('.branch_info').prepend("<div class='row branchtotal'><div class='form-group'><div class='col-md-10 col-lg-10'><label class='text-right'>"+key
													+"</label>&nbsp<label class='text-right'> \t\t\t\t: RM </label><label> &nbsp"
													//+$.fn.dataTable.render.number(',','.','2','').display(total[key])
													+numFormat(total[key])
													+"</label> </div></div> ");*/
													
										}
										
								 },
							   footerCallback: function ( row, data, start, end, display ) {
								 var numFormat = $.fn.dataTable.render.number( '\,', '.', 2, '' ).display
						            var api = this.api(), data;
						 
						            // Remove the formatting to get integer data for summation
						            var intVal = function ( i ) {
						                return typeof i === 'string' ?
						                    i.replace(/[\$,]/g, '')*1 :
						                    typeof i === 'number' ?
						                        i : 0;
						            };
						 
						            // Total over all pages
						            total = api
						                .column( 10 )
						                .data()
						                .reduce( function (a, b) {
						                    return intVal(a) + intVal(b);
						                }, 0 );

										//alert(total.toFixed(4)+"total");
									///alert($.fn.dataTable.render.number(',','.','4','').display(total))
						 
						            // Update footer
									/*$("#allTotal").html(" <u>"+numFormat(total)+"</u>");*/
						            $( api.column( 10 ).footer() ).html(
						                numFormat(total)
						            );

									$('.tablesumkod4').prepend("<tr><div><td colspan='5'><b>"
											+"\n</b></td>"
											+"<td><p><b>JUMLAH</b></p></td><td>"+numFormat(total)+"</td>"
											+"</div></tr>");
						        },
    	});



		/*t.on('order.dt search.dt', function () {
				     t.column(1, {search:'applied', order:'applied'}).nodes().each( function (cell, i) {
				           cell.innerHTML = i+1;
				           t.cell(cell).invalidate('dom');
				     });
		}).draw();*/
	});

});