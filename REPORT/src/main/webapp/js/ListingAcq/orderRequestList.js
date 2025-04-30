$(document).ready(function() {
	
	//////////////////////////////////////////////////////////////////////////
	//////////////////////////setup page//////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////
	
	$(".datelabel").html("<b>Loan Date Range</b>");
	
//	$('#Reterive').prop('disabled', true);
	
	$('#patronCate, #smd, #branch, #icat, #loca, #dept, #orderMode, #orderStatusRequest, #account').multiselect({
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
		
	//////////////radio button on change
	$('input[type=checkBox][name=chkBoxSearchCateria]').on('change', function() {

	//	alert($("input[name='chkBoxSearchCateria']:checked").val()+"fff");
		switch ($(this).val()) {
			 /*case 'patrID':
				$('#Reterive').prop('disabled', false);
		      break;*/
		    case 'vendor':
				$('#patronCate').multiselect("enable");
			  	$("#smd, #icat, #loca").multiselect("disable");
/*				$('#Reterive').prop('disabled', true);
					$('#patronCate').change(function() {
						if($("#patronCate").val() != ""){
							$('#Reterive').prop('disabled', false);
						}else{
							$('#Reterive').prop('disabled', true);
						}
					 });*/
		      break;
		    case 'orderNumberRange':
				$('#first_number').prop('disabled', false);
				$("#second_number").prop('disabled', false);

		      break;
			case 'orderMode':
				$('#orderMode').multiselect("enable");

		      break;
			case 'orderStatusRequest':
				$('#orderStatusRequest').multiselect("enable");
			  	$("#patronCate, #icat, #smd").multiselect("disable");

		      break;
			case 'account':
				$('#account').multiselect("enable");
			  	$("#patronCate, #icat, #smd").multiselect("disable");

		      break;	
			case 'department':
				$('#dept').multiselect("enable");
			  	$("#patronCate, #icat, #smd").multiselect("disable");

		      break;	
		  }
	});

	///on click searc patron 
	$('.patronid').on('click',function(){
		var url = "Modal_PatrSearch";

		$.get(url,function(data){
			$("#Modal_PatrSearchContent").html(data);
		});
	});
	
	
	///on click searc patron 
	$('.vendorCode').on('click',function(){
		var url = "Modal_Vendor?action=All"; //bind, supp, pub

		$.get(url,function(data){
			$("#modal_vendorSearchContent").html(data);
		});
	});
	
	///keyup
	$("#input-vendorCode").keyup(function(e){
		var id = $("#input-vendorCode").val();
		/*if(id.length==0){
			$('#Reterive').prop('disabled', true);
		}else{
			$('#Reterive').prop('disabled', false);
		}*/

		$(".div-vendorName").empty();
		////display vendor name
		$.get('GetVendorName', {
        	id : id,
			action : "All"
		 	}, function(responseJson) {
			if(responseJson != null){
				$.each(responseJson, function(key,value) {
					$(".div-vendorName").text(value['vendorName']);
				});
			}
		});
	});
	
	//clear title when paron id keydown backspace
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
		
		var firstNumber = $("#first_number").val();
		var secondNumber = $("#second_number").val();
		
		var orderMode = $("#orderMode").val();
		if(orderMode != ""){
		}else{
			orderMode = 0;
		}
		
		var orderStatusRequest = $("#orderStatusRequest").val();
		if(orderStatusRequest != ""){
		}else{
			orderStatusRequest = 0;
		}
		
		var account = $("#account").val();
		if(account != ""){
		}else{
			account = 0;
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
		    	url: "ResultOrderRequestListing",
		        data : {startSentDate : startSentDate, endSentDate : endSentDate,  brnch : JSON.stringify(brnch),
						chkBoxSearchCateria : chkBoxSearchCateria, searchCateriaval : JSON.stringify(searchCateriaval),
						firstNumber : firstNumber, secondNumber: secondNumber, orderMode: JSON.stringify(orderMode),
						orderStatusRequest: JSON.stringify(orderStatusRequest), account: JSON.stringify(account),
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
		                'Request No' : json[i].requestNo,
		                'ISBN' : json[i].isbn,
		                'Title' : json[i].title,
		                'Author' : json[i].author,
		                'Request By' : json[i].requestedBy,
						'Date Request' : json[i].dateRequested,
						'Copies/Sets' : json[i].copiesSets,
		            })
		            }
					
		            return return_data;
		          },
		     },//This is the end of the AJAX object from the example above
		     	columns    : [
					{'data': 'No'},
					{'data': 'Request No'},
					{'data': 'ISBN'},
					{'data': 'Title'},
					{'data': 'Author'},		
					{'data': 'Request By'},
					{'data': 'Date Request'},
					{'data': 'Copies/Sets'},
			
				],

    } );
    	
    });
 
});