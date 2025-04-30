$(document).ready(function() {
	var today = new Date();  
	$(".div1patrid").removeClass( "col-sm-3 col-md-3" ).addClass( "col-sm-2 col-md-2" );
	
	$('#patronCate').multiselect({
    	allSelectedText: 'All',
   		maxHeight: 200,
   		includeSelectAllOption: true,
		numberDisplayed: 1,
	});
	
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
	
	
	$("#patronCate, #branch").multiselect("disable");
	
	$("input[name=dateSelection][value=MEMDATE]").prop('checked', true);
	$("input[type=checkbox][value=dateChecked]").prop("checked",true);
	
	
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
				$.each(responseJson, function(key,value) {
					$(".lblName").text(value['Name']);
					$('#Reterive').prop('disabled', false);
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
	
	
	$("input:checkbox[value='pateCate']").on('change', function () {
	    var self = $(this);
	    if (self.is(":checked")) {
			$('#patronCate').multiselect("enable");
			$('#patronCate').change(function() {
						if($("#patronCate").val() != ""){
							$('#Reterive').prop('disabled', false);
						}else{
							$('#Reterive').prop('disabled', true);
						}
					 });
	    } else {
			$('#patronCate').multiselect("disable");
	    }
	});
	
	$("input:checkbox[value='branchChecked']").on('change', function () {
	    var self = $(this);
	    if (self.is(":checked")) {
			$('#branch').multiselect("enable");
			$('#branch').change(function() {
						if($("#branch").val() != ""){
							$('#Reterive').prop('disabled', false);
						}else{
							$('#Reterive').prop('disabled', true);
						}
					 });
	    } else {
			$('#branch').multiselect("disable");
	    }
	});
	
	$("input:checkbox[value='dateChecked']").on('change', function () {
	    var self = $(this);
	    if (self.is(":checked")) {
			$('input[name="dateSelection"]').prop('disabled', false);
			$('#Reterive').prop('disabled', false);
	    } else {
			$('input[name="dateSelection"]').prop('disabled', true);
			$('#Reterive').prop('disabled', true);
	    }
	});
	
	//////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////
	
	//////////////////////////////////////////////////////////////////////////
	////////////////////////btn Reterive Click ///////////////////////////////
	//////////////////////////////////////////////////////////////////////////
	$('#Reterive').click(function(){
		$('#fndreportTable').dataTable().fnClearTable();
		
		var dateSelection = $('input[name="dateSelection"]:checked').val();
		var dateSelectionLabel =$("input[name='dateSelection']:checked").data('name')
		
		var startSentDate = moment($("#input-startDate").val(), 'DD/MM/YYYY').format("YYYYMMDD");
		var endSentDate = moment($("#input-endDate").val(), 'DD/MM/YYYY').format("YYYYMMDD");
		var sDate, eDate;
		
		var startSentDate = $("#input-startDate").val();
		if(startSentDate == ''){
			startSentDate = '';
			sDate = dateSelectionLabel + '';
		}else{
			startSentDate = moment($("#input-startDate").val(), 'DD/MM/YYYY').format("YYYYMMDD");
			sDate = dateSelectionLabel +" from " +$("#input-startDate").val();
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
		

		var patronID =  $("#lblPatronID").val();;
		//alert(chkBoxSearchCateria+"chkBoxSearchCateria");
		
		

		var patroncate = $("#patronCate").val(); 
		if(patroncate != ""){
		}else{
			patroncate = 0;
		}
		
		
		if(dateSelection != undefined){
		}else{
			dateSelection = 0;
		}
		
		
		
		var condition;
		if(dateSelection != 0 || patronCate != 0 || brnch != 0 || patron.length > 1){
			condition = "YES";
		}else{
			condition = "NO";
		}
		
		

		/////////////////--- EDITOR ------/////////		
		
		/////result display
		var t = $('#fndreportTable').DataTable( {
			dom: 'Bfrtip',
            buttons: [
						{
					  		extend: 'excelHtml5',
					        filename: 'WILMU_PatronDetailsListing',
					        title: 'Patron Details Listing ' +sDate +eDate,
					                
					 	},
						{
					    	text: '<i class="fa fa-file-pdf-o"></i> PDF',
							extend: 'pdfHtml5',
							filename: 'WILMU_PatronDetailsListing',
							charset: "utf-8",
							bom : "true",
							orientation: 'landscape', //landscape
							pageSize: 'A4', //A3 , A5 , A6 , legal , letter
							footer: true,
							exportOptions: {
								search: 'applied',
								order: 'applied'
							},
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
										doc.content[1].table.body[i][8].alignment = 'left';
										doc.content[1].table.body[i][9].alignment = 'left';
										doc.content[1].table.body[i][10].alignment = 'left';
										doc.content[1].table.body[i][11].alignment = 'left';
										doc.content[1].table.body[i][12].alignment = 'left';
										doc.content[1].table.body[i][13].alignment = 'left';
										doc.content[1].table.body[i][14].alignment = 'left';
										doc.content[1].table.body[i][15].alignment = 'left';
										doc.content[1].table.body[i][16].alignment = 'left';
										doc.content[1].table.body[i][17].alignment = 'right';
						           };
								
							 	//doc.content[1].table.widths = [ '8%', '55%', '12%', '12%', '12%']; 
								//doc.content[1].table.widths = [ '5.55%', '5.55%', '5.55%', '5.55%', '5.55%', '5.55%', '5.55%', '5.55%', '5.55%', '5.55%', '5.55%', '5.55%', '5.55%', '5.55%', '5.55%', '5.55%', '5.55%']; 
							

											//Remove the title created by datatTables  
											doc.content.splice(0, 1, {
						                        text: [
						                                   { text: 'Patron Details Listing '+sDate +eDate+ '\n',bold:true,fontSize:13,alignment: 'center'}						                        ],
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
		                	filename: 'WILMU_PatronDetailsListing',
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
										
										return "Patron Details Listing "+sDate +eDate +"\n\n" + doc;
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
		    	url: "ResultPatronDetailsListing",
		        type: "GET",
 				data : {dateSelection : dateSelection, condition : condition,
						startSentDate : startSentDate, endSentDate : endSentDate, 
						brnch : JSON.stringify(brnch), allbranch : allbranch,
						patronID : patronID,
						patroncate : JSON.stringify(patroncate),
				},
				/*start_time: new Date().getTime(),
				    complete: function(data) {
				        alert('This request took '+(new Date().getTime() - this.start_time)+' ms');
				},*/
		        dataSrc: function (json) {
		            var return_data = new Array();
		            
		            for(var i=0;i< json.length; i++){

		              return_data.push({
		                'No': (i+1),
						'Patron Id' : json[i].Column1,
						'Name' : json[i].Column2,
						'Abbrevation' : json[i].Column3,
		                'Category' : json[i].Column4,
		                'Related Id' : json[i].Column5,
		                'Home Address' : json[i].Column6,
						'Home Postcode' : json[i].Column7,
						'Department' : json[i].Column8,
						'Course Code' : json[i].Column9,
						'IC/Passport No' : json[i].Column10,
						'Designation' : json[i].Column11,
						'Branch' : json[i].Column12,
						'Expiry Date' : json[i].Column13,
						'User Status' : json[i].Column14,
						'Office Tel' : json[i].Column15,
						'Membership Date' : json[i].Column16,
						'Deposit' : json[i].Column17,
		            })
		            }
					
		            return return_data;
		          },
		     },//This is the end of the AJAX object from the example above
		     	columns    : [
					{'data': 'No'},
					{'data': 'Patron Id'},
					{'data': 'Name'},
					{'data': 'Abbrevation'},
					{'data': 'Category'},
					{'data': 'Related Id'},
					{'data': 'Home Address'},
					{'data': 'Home Postcode'},
					{'data': 'Department'},
					{'data': 'Course Code'},
					{'data': 'IC/Passport No'},
					{'data': 'Designation'},
					{'data': 'Branch'},
					{'data': 'Expiry Date', className: "text-right"},
					{'data': 'User Status'},
					{'data': 'Office Tel', className: "text-right"},
					{'data': 'Membership Date', className: "text-right"},
					{'data': 'Deposit', className: "text-right",  render: $.fn.dataTable.render.number(',', '.', 4, '')},
				],
    	});
		
		/*t.on('order.dt search.dt', function () {
			 t.column(0, {search:'applied', order:'applied'}).nodes().each( function (cell, i) {
				cell.innerHTML = i+1;
				t.cell(cell).invalidate('dom');
			 });
	   }).draw();*/
		
    	

    });
	
});