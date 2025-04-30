$(document).ready(function() {
	$('#itemType').multiselect({
    	allSelectedText: 'All',
   		maxHeight: 200,
   		includeSelectAllOption: false
	});
	
	///////////////////// input-startDate set Current Date  //////////////////
	var today = new Date();  
	$('#input-startDate').datepicker({
		format: "dd/mm/yyyy",
		todayBtn: true,
		autoclose: true,
		todayHighlight: true,
	});
	
	///////////////////// input-endDate set Current Date  ////////////////////
	$('#input-endDate').datepicker({
		format: "dd/mm/yyyy",
		todayBtn: true,
		autoclose: true,
		todayHighlight: true,
	});
	
	$("#Reterive").attr('disabled', 'disabled');	
	
	function checkConditionPartForInputFormatting(){
		if($("#itemType").val().length > 0 && ($("#input-startDate").val().length > 0 || $("#input-endDate").val().length > 0) && $("#input-vendorCode").val().length > 0){ // patronId HAS INPUT ONLY && date CHECKED && date has one of input
			$('#Reterive').removeAttr('disabled');
		}
		else{
			$("#Reterive").attr('disabled', 'disabled');
		}
	}
	
	$("#input-startDate, #input-endDate").on('change', function() {
		checkConditionPartForInputFormatting()
	})
	
	$(".div1vend").remove()
	
	////Vendor
	$('.vendorCode').on('click',function(){
		var url = "Modal_Vendor?action=bind";

		$.get(url,function(data){
			$("#modal_vendorSearchContent").html(data);
		});
	});
	
	$("#input-vendorCode").attr('maxlength',4);
	
	$("#input-vendorCode").on("keypress keyup blur",function (e) {
		$(this).val($(this).val().toUpperCase());
		var id = $("#input-vendorCode").val();
		
		////display vendor name
		$(".div-vendorName").empty();
		
		$.get('ResultVendorName', {
        	id : id,
			action : "bind"
		 	}, function(responseJson) {
			if(responseJson != null){
				$.each(responseJson, function(key,value) {
					$(".div-vendorName").text(value['vendorName']);
					//$('#Reterive').prop('disabled', false);
					checkConditionPartForInputFormatting()
				});
			}
		});
	});
	
	$("#input-vendorCode").on("input",function() {
		checkConditionPartForInputFormatting()
	});
	
	//clear vendor keydown backspace
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
	 

	////// table setup
	$('#reportTable').DataTable( {
		destroy: true,
		searching: false,
		bLengthChange: false,
		autoWidth: false,
		responsive: true,
	});
	
	
	//////////////////////////////////////////////////////////////////////////
	////////////////////////btn Reterive Click ///////////////////////////////
	//////////////////////////////////////////////////////////////////////////
	$('#Reterive').click(function(){
		var startSentDate = moment($("#input-startDate").val(), 'DD/MM/YYYY').format("YYYYMMDD");
		var endSentDate = moment($("#input-endDate").val(), 'DD/MM/YYYY').format("YYYYMMDD");
		var sDate, eDate;
		
		var startSentDate = $("#input-startDate").val();
		if(startSentDate == ''){
			startSentDate = '';
			sDate = "";
		}else{
			startSentDate = moment($("#input-startDate").val(), 'DD/MM/YYYY').format("YYYYMMDD");
			sDate = $("#input-startDate").val();
		}
		
		var endSentDate = $("#input-endDate").val();
		if(endSentDate == ''){
			endSentDate = '';
		}else{
			endSentDate = moment($("#input-endDate").val(), 'DD/MM/YYYY').format("YYYYMMDD");
			eDate =  $("#input-endDate").val();
		}
		
		var itemType = $("#itemType").val()
		var binder = $("#input-vendorCode").val()
		
		/////result display
		var t = $('#reportTable').DataTable({
			dom: 'Bfrtip',
			info:     false,
			buttons: [
						{
					  		extend: 'excelHtml5',
					        filename: 'WILMU_In-Bindery Listing',
					        title: 'In-Bindery Listing from ' + sDate +" until "+ eDate,
					 	},
						{
					    	text: '<i class="fa fa-file-pdf-o"></i> PDF',
							extend: 'pdfHtml5',
							filename: 'WILMU_In-Bindery Listing',
							charset: "utf-8",
							bom : "true",
							orientation: 'landscape', //portrait
							pageSize: 'A4', //A3 , A5 , A6 , legal , letter
							footer: true,
							exportOptions: {
								search: 'applied',
								order: 'applied'
							},
							customize: function (doc) {
								var rowCount = doc.content[1].table.body.length;
						           for (i = 0; i < rowCount; i++) {
										doc.content[1].table.body[i][0].alignment = 'left';
										doc.content[1].table.body[i][1].alignment = 'left';
										doc.content[1].table.body[i][2].alignment = 'left';
										doc.content[1].table.body[i][3].alignment = 'left';
										doc.content[1].table.body[i][4].alignment = 'left';
										doc.content[1].table.body[i][5].alignment = 'left';
										doc.content[1].table.body[i][6].alignment = 'right';
						           };
							doc.content[1].table.widths = [ '5%', '10%', '40%', '10%', '10%', '10%', '15%'];
							
							doc.content.push( {
						                        alignment: 'right',
												bold:true,
								                   text: [
								                         	$('.divtotal').text(), 
								                        ]
											 });

											//Remove the title created by datatTables  
											doc.content.splice(0, 1, {
						                        text: [
						                                   { text: 'In-Bindery Listing from '+sDate + " until " +eDate+ '\n',bold:true,fontSize:13,alignment: 'center'}						                        ],
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
		                	filename: 'WILMU_In-Bindery Listing',
							charset: "utf-8",
							bom : "true",
							customize: function(doc){
										var split_csv = doc.split("\n");
										$.each(split_csv.slice(1), function (index, csv_row) {
											var csv_cell_array = csv_row.split('","');
											csv_cell_array[1].replace("", (index+1));
										});
										return "In-Bindery Listing from "+sDate + " until " + eDate +"\n\n" + doc;
									 }
             			},
            ],
			destroy: true,
			searching: true,
			bLengthChange: false,
			autoWidth: false,
			responsive: true,
			processing: true,
			paging: true,
			language: {
             loadingRecords : "Please wait - loading...",
             processing :     "Please wait formatting in progress..."
        	},
			deferRender: true,
			footer: true,
			ajax: {
		    	url: "getInBinderyList",
		        data : {startSentDate : startSentDate, endSentDate : endSentDate, itemType : itemType, binder : binder},
		        type: "GET",
		        dataSrc: function (json) {
		            var return_data = new Array();
					var i = 0
					$.each( json, function( key, value ) {
						return_data.push({
			                'No': (i+1),
			                'Call No' : value.callNo,
							'Title' : value.title,
							'Accession No' : value.accessionNo,
			                'Officer' : value.officer,
			                'Date' : value.dateSent,
			                'ISBN/ ISSN' : value.isbNs
			            })
					i++
					});
		            return return_data;
		          },
		     },
			columns: [
				{ "data": "No" },
				{ "data": "Call No" },
				{ "data": "Title" },
				{ "data": "Accession No" },
			    { "data": "Officer" },
				{ "data": "Date" },
			    { "data": "ISBN/ ISSN" }
			  ],
    		});
	})
});