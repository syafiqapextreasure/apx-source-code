$(document).ready(function() {
	
	//////////////////////////////////////////////////////////////////////////
	//////////////////////////setup page//////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////
	
	$(".datelabel").html("<b>Loan Date Range</b>");
	
	$('#Reterive').prop('disabled', true);
	
	$('#patronCate, #smd, #branch, #icat, #loca, #dept').multiselect({
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
		
		if(code == 13) {
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
			sDate = $("#input-startDate").val();
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
		
		var dept = $("#dept").val(); 
		if(dept != ""){
		}else{
			dept = 0;
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
			columnDefs: [{
				  "defaultContent": "",
				  "targets": "_all"
				}],
            buttons: [
                /*'excel', 'pdf', 'print'*/
						{
					  		extend: 'excelHtml5',
					        filename: 'WILMU_LostItemNotification',
					        title: 'Item On Loan Report By '+titleHeader +" from " +sDate + " until " +eDate,

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
							filename: 'WILMU_LostItemNotification',
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

						   doc.content[1].table.widths = [ '3%', '10%', '20%', '6%', '6%', '6%','6%','6%','6%','6%','9%','8%','8%'];  

											//Remove the title created by datatTables
											doc.content.splice(0, 1, {
						                        text: [
						                                   { text: 'Item On Loan Report By '+titleHeader+' from '+sDate+ ' until '+eDate+ ' \n',bold:true,fontSize:13,alignment: 'center'},
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
		                	filename: 'WILMU_LostItemNotification',
		                	title: 'Lost Item Notification Report',
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
										
										return "Item On Loan Report By "+titleHeader+ " from " +sDate + " until "+ eDate +"\n\n" + doc;
										//var split_csv = doc.split("\n");
										 
									 }
             			},
             			
            ],
            searching: false,
			destroy: true,
			bLengthChange: false,
			autoWidth: false,
			responsive: true,
			processing: true,
            select: {
                style: 'multi'
            },
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
		    	url: "ResultLostItem",
		        data : {startSentDate : startSentDate, endSentDate : endSentDate,  brnch : JSON.stringify(brnch),
						chkBoxSearchCateria : chkBoxSearchCateria, searchCateriaval : JSON.stringify(searchCateriaval),
						type : type, dept : JSON.stringify(dept)
						},
		        type: "GET",
		        dataSrc: function (json) {
		        	
		        	console.log("json: "+JSON.stringify(json));
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
		                'Accession No' : json[i].accNo,
		                'Patron ID/Name' : json[i].patron,
		                'Title' : json[i].title,
		                'Call No' : json[i].callNo,
		                'Patron ID/Name' : json[i].patron,
						'Due Date' : json[i].dueDate,
						'Late By' : json[i].lateBy,
						'Late Fines' : 'RM '+json[i].lateFines,
						'Lost Fines' : 'RM '+json[i].lostFines,
						'Process Fee' : 'RM '+json[i].processFee,
		            })
		            }
					
		            return return_data;
		          },
		     },//This is the end of the AJAX object from the example above
		     	columns    : [
					{'data': 'No'},
					{'data': 'Accession No'},
					{'data': 'Patron ID/Name'},
					{'data': 'Title'},
					{'data': 'Call No'},		
					{'data': 'Due Date'},
					{'data': 'Late By'},
					{'data': 'Late Fines'},
					{'data': 'Lost Fines'},
					{'data': 'Process Fee'},
			
				],

    } );
    	
    });
	
    $(document).on('click dblclick', '#reportTable > tbody >tr', function() {

        $('#previewBtn').prop('disabled', false);
        $('#printBtn').prop('disabled', false);
        $('#emailBtn').prop('disabled', false);
    	
    });
    
    $('#previewBtn').click(function() {
        console.log('in preview overdue');

            var table = $('#reportTable').DataTable();
            var tableData = table.rows({
                selected: true
            }).data().toArray();

            console.log("tableData: " + tableData);

            var dataJSON = JSON.stringify(tableData);
            console.log("data in json: " + dataJSON);

            $.ajax({
                url: "GeneratePreviewDocument",
                dataType: 'json',
                data: {
                    json: JSON.stringify(tableData),
                    reminderType: $("input[name='reminderRadiosName']:checked").val()
                },
	            beforeSend: function() {
	            	swal({
	            	     title: 'Retrieving data from Database.'
	            	});
	            	swal.showLoading();
	            },
                success: function(data) {
                    if (data) {
                    	swal.close();
                        console.log('return preview document data: ' + data)
                        console.log('return preview document data: ' + JSON.stringify(data))
                        var winOpen = window.open("previewNotification");
                        winOpen.document.write(data);

                    } else if (!data) {
                        console.log('no data: ' + data)
                    }
                }
            });
    });

    $('#print_Btn').click(function() {

        	console.log('in print overdue notification');
	        var table = $('#reportTable').DataTable();
	        var tableData = table.rows({
	            selected: true
	        }).data().toArray();
	
	        console.log("tableData: " + tableData);
	
	        var dataJSON = JSON.stringify(tableData);
	        console.log("data in json: " + dataJSON);
	
	        $.ajax({
	            url: "PrintDocument",
	            dataType: 'json',
	            data: {
	                json: JSON.stringify(tableData),
	                liferayLogin: liferayLogin,
	                reminderType: $("input[name='reminderRadiosName']:checked").val(),
	                overdueType: "notification"
	            },
	            beforeSend: function() {
	            	swal({
	            	     title: 'Retrieving data from Database.'
	            	});
	            	swal.showLoading();
	            },
	            success: function(data) {
	                if (data) {
	                	
	                    var w = window.open();
	                    swal.close();
	                    console.log('line 893 data accssion no:' + data.insertedRow);
	                    console.log('line 893 data html letter:' + data.htmlLetter);
	                    //		console.log('line 893 data.stringify(data.html): '+ JSON.stringify(data.htmlLetter).replaceAll('"',''));
	
	                    w.document.write(data.htmlLetter);
	                    w.print();
	
	                    for (var i = 0; i < data.insertedRow.length; i++) {
	                        var accNo = data.insertedRow[i];
	                        console.log('line 846 data.insertedRow.length:' + data.insertedRow.length);
	                        console.log('line 846 accNo:' + accNo);
	                        table.rows().nodes().each(function(a, b) {
	                            if ($(a).children().eq(0).text() == accNo) {
	                                table.rows(a).remove();
	
	                            }
	
	                            return false;
	                        });
	                        table.rows().invalidate();
	                        table.draw();
	                    }
	
	                } else if (!data) {
	                    console.log('no data: ' + data)
	                }
	            }
	        });
    });
    
    
    $('#emailBtn').click(function() {

        console.log('in sent overdue to mailbrowser');

	        var table = $('#reportTable').DataTable();
	        var tableData = table.rows({
	            selected: true
	        }).data().toArray();
	
	        console.log("tableData: " + tableData);
	
	        var dataJSON = JSON.stringify(tableData);
	        console.log("line 878 data in json: " + dataJSON);
	        console.log("line 878 data in json length: " + tableData.length);
	
	        swal({
	            text: "Are you sure to send notification?",
	            showCancelButton: true,
	            confirmButtonColor: '#3085d6',
	            cancelButtonColor: '#d33',
	            confirmButtonText: 'Yes',
	            cancelButtonText: 'No',
	            confirmButtonClass: 'confirm-class',
	            cancelButtonClass: 'cancel-class',
	            closeOnConfirm: false,
	            closeOnCancel: false
	        }).then(
	            function() {
	                $.ajax({
	                    url: "SentOverdueToMailBrowser",
	                    dataType: 'json',
	                    data: {
	                        json: JSON.stringify(tableData),
	                        sender: liferayLogin,
	                        reminderType: $("input[name='reminderRadiosName']:checked").val()
	                    },
	                    success: function(data) {
	
	                        for (var i = 0; i < data.insertedRow.length; i++) {
	                            var accNo = data.insertedRow[i];
	                            console.log('line 846 data.insertedRow.length:' + data.insertedRow.length);
	                            console.log('line 846 accNo:' + accNo);
	                            table.rows().nodes().each(function(a, b) {
	                                if ($(a).children().eq(0).text() == accNo) {
	                                    table.rows(a).remove();
	
	                                }
	
	                                return false;
	                            });
	                            table.rows().invalidate();
	                            table.draw();
	                        }
	                        swal("Overdue Notification has sent to Mail Browser successfully.");
	                    }
	                });
	            });	
		});
});