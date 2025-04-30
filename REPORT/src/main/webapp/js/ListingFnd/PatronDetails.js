$(document).ready(function() {
	
	//////////////////////////////////////////////////////////////////////////
	//////////////////////////setup page//////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////
	
	//$('#Reterive').prop('disabled', true);
	
	$('#patronCate, #branch').multiselect({
    	allSelectedText: 'All',
   		maxHeight: 200,
   		includeSelectAllOption: true
	});
	
	$("#patronCate, #branch").multiselect("disable");
	//$('#lblPatronID, .patronid').prop('disabled', true);
	//$('input[name="chkDate"]').prop('disabled', true);
	
	
	$("input[name=chkDate][value=M]").prop('checked', true);
	$("input[type=checkbox][value=dateChecked]").prop("checked",true);
	
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
			$('input[name="chkDate"]').prop('disabled', false);
			$('#Reterive').prop('disabled', false);
	    } else {
			$('input[name="chkDate"]').prop('disabled', true);
			$('#Reterive').prop('disabled', true);
	    }
	});
	
	
	/*$("input[name=chkBoxSearchCateria]").on('change', function () {
	    var self = $(this);
		alert(self);
	    if (self.is(":checked")) {
			$('#patronCate').multiselect("enable");
			$('#Reterive').prop('disabled', true);
	    } else {
			$('#patronCate').multiselect("disable");
			$('#Reterive').prop('disabled', false);
	    }
	});*/
	//////////////radio button on change
/*	$('input[type=checkbox][name=chkBoxSearchCateria]').on('change', function() {
		alert($(this).val());
		switch ($(this).val()) {
			 case 'patrID':
				$('#Reterive').prop('disabled', false);
		      break;
		    case 'pateCate':
				$('#patronCate').multiselect("enable");
				$('#Reterive').prop('disabled', true);
					$('#patronCate').change(function() {
						if($("#patronCate").val() != ""){
							$('#Reterive').prop('disabled', false);
						}else{
							$('#Reterive').prop('disabled', true);
						}
					 });
		      break;
		    case 'branch':
				$('#branch').multiselect("enable");
				$('#Reterive').prop('disabled', true);
					$('#icat').change(function() {
						if($("#icat").val() != ""){
							$('#Reterive').prop('disabled', false);
						}else{
							$('#Reterive').prop('disabled', true);
						}
					});
		      break;
			case 'dateChecked':
				$('input[name="chkDate"]').prop('disabled', false);
				$('#Reterive').prop('disabled', true);
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
*/	
	////////Branch mandatary
	/*$('#branch').change(function() {
		if($("#branch").val() != ""){
			$('#Reterive').prop('disabled', false);
		}else{
			$('#Reterive').prop('disabled', true);
		}
	 });*/

	///on click searc patron 
	/*$('.patronid').on('click',function(){
		var url = "Modal_PatrSearch";
		
		alert(url);
		$.get(url,function(data){
			$("#Modal_PatrSearchContent").html(data);
		});
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
		
		var dateSelection = $('input[name="chkDate"]:checked').val();
		
		//alert(dateSelection +"dateSelection");
		
		var dateSelection2
		
		
		if(dateSelection == 'M'){
			dateSelection2 = 'Membership Date '
		}else if(dateSelection == 'E'){
			dateSelection2 = 'Expiry Date '
		}
		
		//alert(dateSelection2 +"dateSelection2dateSelection2");
		
		
		
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
		
		var patron = $("#lblPatronID").val();
		
		////buat balik patrcode n branchhhh
		
		//var chkBoxSearchCateria2 = $("input[name='chkBoxSearchCateria2']:checked").val();
		
		//alert(chkBoxSearchCateria2+"chkBoxSearchCateriachkBoxSearchCateria2");
		
		var chkBoxSearchCateria2 = [];
		$.each($("input[name='chkBoxSearchCateria2']:checked"), function(){            
                chkBoxSearchCateria2.push($(this).val());
            });
		chkBoxSearchCateria2 = chkBoxSearchCateria2.join(", ");
		
		var patronCate;
		var branch;
			//alert(chkBoxSearchCateria2+"chkBoxSearchCateria2");
		switch(chkBoxSearchCateria2) {
		
			 case 'pateCate':
					patronCate = $("#patronCate").val();
					branch = 0;
		
				/*var patronCate = $("#patronCate").val(); 
					if(patronCate != ""){
					}else{
						patronCate = 0;
					}*/
		    break;	
			 case 'branchChecked':
				branch = $("#branch").val();
				patronCate = 0;
				
				/*var branch = $("#branch").val(); 
					if(branch != ""){
					}else{
						branch = 0;
					}*/
		    break;	
 			case 'pateCate, branchChecked':
				patronCate = $("#patronCate").val();
				branch = $("#branch").val();
				
				/*var branch = $("#branch").val(); 
					if(branch != ""){
					}else{
						branch = 0;
					}*/
		    break;	
			case '':
				patronCate = 0;
				branch = 0;
				
				/*var branch = $("#branch").val(); 
					if(branch != ""){
					}else{
						branch = 0;
					}*/
		    break;	
			
		}
		
		

		
		
		if(dateSelection != undefined){
		}else{
			dateSelection = 0;
		}
		
		
		
		var condition;
		if(dateSelection != 0 || patronCate != 0 || patron.length > 1){
			condition = "YES";
		}else{
			condition = "NO";
		}


		/////result display
		var t = $('#reportTable').DataTable( {
			dom: 'Bfrtip',
            buttons: [
                /*'excel', 'pdf', 'print'*/
						{
					  		extend: 'excelHtml5',
					        filename: 'WILMU_PatronDetailsListing',
					        //title: 'Patron Details Listing',
							title: 'Patron Details Listing '+dateSelection2 +sDate + " until " +eDate,

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
							filename: 'WILMU_PatronDetailsListing',
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
						    doc.content[1].table.widths = [ '5.55%', '5.55%', '5.55%', '5.55%', '5.55%', '5.55%', '5.55%', '5.55%', '5.55%', '5.55%', '5.55%', '5.55%', '5.55%', '5.55%', '5.55%', '5.55%', '5.55%', '5.55%']; 
							 //doc.content[1].table.body[0][5].alignment = 'right';
						
						


											//Remove the title created by datatTables
											doc.content.splice(0, 1, {
						                        text: [
						                                   { text: 'Patron Details Listing '+dateSelection2 +sDate + ' until '+eDate +' \n',bold:true,fontSize:13,alignment: 'center'},
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
		                	filename: 'WILMU_PatronDetailsListing',
		                	title: 'Patron Details Listing',
							customize: function(doc){
										
										var split_csv = doc.split("\n");
										//alert(split_csv)
										$.each(split_csv.slice(1), function (index, csv_row) {
											var csv_cell_array = csv_row.split('","');
											csv_cell_array[1].replace("", (index+1));
										});
										///alert(csv_cell_array[1]);
										//alert(csv_cell_array[1]);
										
										return 'Patron Details Listing '+dateSelection2 +sDate + " until " +eDate +"\n\n" + doc;
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
		    	url: "ResultPatronDetailsListing",
		        data : {dateSelection : dateSelection, startSentDate : startSentDate, endSentDate : endSentDate,
						 patron : patron, condition : condition,
						patronCate : JSON.stringify(patronCate), branch : JSON.stringify(branch) 
						},
		        type: "GET",
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
					{'data': 'Deposit', className: "text-right", render: $.fn.dataTable.render.number(',', '.', 4, '')},
															
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