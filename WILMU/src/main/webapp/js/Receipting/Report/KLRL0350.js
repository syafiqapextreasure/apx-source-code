$(document).ready(function() {	
	$('#branch').multiselect({
    	allSelectedText: 'All',
   		maxHeight: 200,
   		includeSelectAllOption: true
	}); 
	$('#branch').multiselect("disable");
	
	///////////////////// input-startDate set Current Date  //////////////////
	$('#input-startDate').datepicker({
		format: "dd/mm/yyyy",
		todayBtn: true,
		autoclose: true,
		todayHighlight: true,
	});
	
	////// table setup //////
	$('#reportTableBranch').DataTable({
		destroy: true,
		searching: false,
		bLengthChange: false,
		autoWidth: false,
		responsive: true,
		paging: false
	});
	
	$('#branch, #branchSelect, #input-startDate, #input-endDate').on('change', function(){
		if($('#branchSelect').prop("checked") == true){ // checkbox is CHECKED
			$('#branch').multiselect("enable");
			if($("#branchSelect").prop("checked") == true && $('#branch').val().length > 0 && ($("#input-startDate").val().length > 0 || $("#input-endDate").val().length > 0)){
				$("#Reterive").removeAttr('disabled');
			}else {
				$("#Reterive").attr('disabled', 'disabled');
			}
		} else if($('#branchSelect').prop("checked") != true){ // checkbox is not CHECKED 
			$('#branch').multiselect("disable");
			if($("#input-startDate").val().length > 0 || $("#input-endDate").val().length > 0){
				$('#Reterive').removeAttr('disabled');
			}else {
				$('#Reterive').attr('disabled', 'disabled');
			}
		}
	})
	
	$('#branch, #branchSelect, #input-startDate, #input-endDate').on('input', function(){
		if($('#branchSelect').prop("checked") == true){ // checkbox is CHECKED
			$('#branch').multiselect("enable");
			if($("#branchSelect").prop("checked") == true && $('#branch').val().length > 0 && ($("#input-startDate").val().length > 0 || $("#input-endDate").val().length > 0)){
				$("#Reterive").removeAttr('disabled');
			}else {
				$("#Reterive").attr('disabled', 'disabled');
			}
		} else if($('#branchSelect').prop("checked") != true){ // checkbox is not CHECKED 
			$('#branch').multiselect("disable");
			if($("#input-startDate").val().length > 0 || $("#input-endDate").val().length > 0){
				$('#Reterive').removeAttr('disabled');
			}else {
				$('#Reterive').attr('disabled', 'disabled');
			}
		}
	})
	
	$('input[type=checkbox][name=chkBoxBranchCateria]').click(function() {
		if($('#branchSelect').prop("checked") == true){ // checkbox is CHECKED
			$('#branch').multiselect("enable");
			if($("#branchSelect").prop("checked") == true && $('#branch').val().length > 0 && ($("#input-startDate").val().length > 0 || $("#input-endDate").val().length > 0)){
				$("#Reterive").removeAttr('disabled');
			}else {
				$("#Reterive").attr('disabled', 'disabled');
			}
		} else if($('#branchSelect').prop("checked") != true){ // checkbox is not CHECKED 
			$('#branch').multiselect("disable");
			if($("#input-startDate").val().length > 0 || $("#input-endDate").val().length > 0){
				$('#Reterive').removeAttr('disabled');
			}else {
				$('#Reterive').attr('disabled', 'disabled');
			}
		}
	})
	
	///////////////////// input-endDate set Current Date  ////////////////////
	var today = new Date();  
	$('#input-endDate').val(moment(today).format("DD/MM/YYYY"));
	$('#input-endDate').datepicker({
		format: "dd/mm/yyyy",
		todayBtn: true,
		autoclose: true,
		todayHighlight: true,
	});
	

	
	//////////////////////////////////////////////////////////////////////////
	////////////////////////btn Reterive Click ///////////////////////////////
	//////////////////////////////////////////////////////////////////////////
	$('#Reterive').click(function(){
		$('#reportTableBranch').dataTable().fnClearTable();
		
		var tt = 0
		var arraySumPrice = new Array()
		
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
		
		var branchCheck = ''
		var branchSelect = ''
		if($('#branchSelect').prop("checked") == true){
			branchCheck = 'Y'
			branchSelect = JSON.stringify($('#branch').val())
		}else{
			branchCheck = 'N'
			branchSelect = ''
		}
		
		if($("#branchSelect").prop("checked") == true){ // when branch is selected
		/////result display
		var t = $('#reportTableBranch').DataTable({
			dom: 'Bfrtip',
			info:     false,
			buttons: [
						{
					  		extend: 'excelHtml5',
					        filename: 'WILMU_Income Report',
					        title: 'Income Report from ' + sDate +" until "+ eDate,
					 	},
						{
					    	text: '<i class="fa fa-file-pdf-o"></i> PDF',
							extend: 'pdfHtml5',
							filename: 'WILMU_Income Report',
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
										doc.content[1].table.body[i][2].alignment = 'right';
						           };
							doc.content[1].table.widths = [ '30%', '30%', '40%',];
							
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
						                                   { text: 'Income Report from '+sDate + " until " +eDate+ '\n',bold:true,fontSize:13,alignment: 'center'}						                        ],
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
		                	filename: 'WILMU_Income Report',
							charset: "utf-8",
							bom : "true",
							customize: function(doc){
										var split_csv = doc.split("\n");
										$.each(split_csv.slice(1), function (index, csv_row) {
											var csv_cell_array = csv_row.split('","');
											csv_cell_array[1].replace("", (index+1));
										});
										return "Income Report from "+sDate + " until " + eDate +"\n\n" + doc;
									 }
             			},
            ],
			destroy: true,
			searching: true,
			ordering: false,
			bLengthChange: false,
			autoWidth: false,
			responsive: true,
			processing: true,
			paging: false,
			language: {
             loadingRecords : "Please wait - loading...",
             processing :     "Please wait formatting in progress..."
        	},
			deferRender: true,
			footer: true,
			ajax: {
		    	url: "getIncomeReport",
		        data : {startSentDate : startSentDate, endSentDate : endSentDate, branchCheck : branchCheck, branchSelect : branchSelect},
		        type: "GET",
		        dataSrc: function (json) {
		            var return_data = new Array();
					var i = 0
					$.each( json, function( key, value ) {
						return_data.push({
			                'Branch' : value.branch,
							'Transaction Type' : value.desc,
							'Collection' : value.totalIncome.replace(/\B(?=(\d{3})+(?!\d))/g, ',')
			            })
					i++
					});
		            return return_data;
		          },
		     },
			columns: [
				{ "data": "Branch"},
				{ "data": "Transaction Type" },
				{ "data": "Collection" }
			  ],
			columnDefs: [{
			            targets: [ 0 ],
			            visible: false
			        }],
			drawCallback: function( settings ) {
		        var api = this.api();

				var last1 = null;
				var rows1 = api.rows({ page: 'current' }).nodes();
				
				api.column(0, { page: 'current' }).data().each(function (group, i) {
                    if (last1 !== group) {
                        $(rows1).eq(i).before('<tr class="group"><td colspan="3"> For Branch : ' + group + '</td></tr>');
                        last1 = group;
                    }
                });
		    },
			footerCallback: function ( row, data, start, end, display ) {
		            var api = this.api()
					$(".divTotalSum").empty();
					
		            // Total Quantity over all pages
		            var totalQuantity = api.column(2).data().reduce(function (a, b){
		                    return Number(a.toString().replace(',', '')) + Number(b.toString().replace(',', ''));
		                }, 0 );
					
					var last2 = null;
					var rowTableCount = Number(api.rows({ page: 'all' }).count()) - 1 
					
					api.column(0, { page: 'all' }).data().each(function (group, i) {
		                    if (last2 !== group) {
								arraySumPrice.push(Number(tt).toFixed(2).replace(/\B(?=(\d{3})+(?!\d))/g, ','))
								tt = 0 
		                        last2 = group;
		                    }
							if(i == rowTableCount){
								tt = Number(tt) + Number(api.column(2).data()[i].replace(/\B(?=(\d{3})+(?!\d))/g, ''))
								arraySumPrice.push(Number(tt).toFixed(2).replace(/\B(?=(\d{3})+(?!\d))/g, ','))
							}  
							tt = Number(tt) + Number(api.column(2).data()[i].replace(',', ''))
	                });

					arraySumPrice.shift()
					var arrayBranch = JSON.stringify($('#branch').val())
					arrayBranch = arrayBranch.replace(/'/g, '"') //replacing all ' with "
					arrayBranch = JSON.parse(arrayBranch)
					$(".divTotalSum").append('<div><label>Grand Total : &nbsp; <span>'+totalQuantity.toFixed(2).replace(/\B(?=(\d{3})+(?!\d))/g, ',')+'</span></label></div>\n');
					$.each( arrayBranch, function( key, value ) {
						$(".divTotalSum").append('<div><label>Total for '+arrayBranch[key]+' : &nbsp; <span>'+arraySumPrice[key]+'</span></label></div>\n');
					});
					$(".divTotalSum").css({"border": "1px solid #ddd"});
		        }
    		});
		}
		
		
		if($("#branchSelect").prop("checked") != true){ // when branch is not selected
		/////result display
		var t = $('#reportTableBranch').DataTable({
			dom: 'Bfrtip',
			info:     false,
			buttons: [
						{
					  		extend: 'excelHtml5',
					        filename: 'WILMU_Income Report',
					        title: 'Income Report from ' + sDate +" until "+ eDate,
							exportOptions: {
								columns: [ 1, 2 ]
							}
					 	},
						{
					    	text: '<i class="fa fa-file-pdf-o"></i> PDF',
							extend: 'pdfHtml5',
							filename: 'WILMU_Income Report',
							charset: "utf-8",
							bom : "true",
							orientation: 'landscape', //portrait
							pageSize: 'A4', //A3 , A5 , A6 , legal , letter
							footer: true,
							exportOptions: {
								search: 'applied',
								order: 'applied',
								columns: [ 1, 2 ]
							},
							customize: function (doc) {
								var rowCount = doc.content[1].table.body.length;
						           /*for (i = 0; i < rowCount; i++) {
										doc.content[1].table.body[i][0].alignment = 'left';
										doc.content[1].table.body[i][1].alignment = 'left';
						           };*/
							//doc.content[1].table.widths = [ '30%', '30%'];
							
							doc.content.push( {
						                        alignment: 'left',
												bold:true,
								                   text: [
								                         	$('.divtotal').text(), 
								                        ]
											 });

											//Remove the title created by datatTables  
											doc.content.splice(0, 1, {
						                        text: [
						                                   { text: 'Income Report from '+sDate + " until " +eDate+ '\n',bold:true,fontSize:13,alignment: 'center'}						                        ],
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
		                	filename: 'WILMU_Income Report',
							charset: "utf-8",
							bom : "true",
							exportOptions: {
								columns: [ 1, 2 ]
							},
							customize: function(doc){
										var split_csv = doc.split("\n");
										$.each(split_csv.slice(1), function (index, csv_row) {
											var csv_cell_array = csv_row.split('","');
											csv_cell_array[1].replace("", (index+1));
										});
										return "Income Report from "+sDate + " until " + eDate +"\n\n" + doc;
									 }
             			},
            ],
			destroy: true,
			searching: true,
			ordering: false,
			bLengthChange: false,
			autoWidth: false,
			responsive: true,
			processing: true,
			paging: false,
			language: {
             loadingRecords : "Please wait - loading...",
             processing :     "Please wait formatting in progress..."
        	},
			deferRender: true,
			footer: true,
			ajax: {
		    	url: "getIncomeReport",
		        data : {startSentDate : startSentDate, endSentDate : endSentDate, branchCheck : branchCheck, branchSelect : branchSelect},
		        type: "GET",
		        dataSrc: function (json) {
		            var return_data = new Array();
					var i = 0
					$.each( json, function( key, value ) {
						return_data.push({
							'Branch' : " ",
							'Transaction Type' : value.desc,
							'Collection (RM)' : value.totalIncome.replace(/\B(?=(\d{3})+(?!\d))/g, ',')
			            })
					i++
					});
		            return return_data;
		          },
		     },
			columns: [
				{ "data": "Branch" },
				{ "data": "Transaction Type" },
				{ "data": "Collection (RM)" }
			  ],
			columnDefs: [{
				            targets: [ 0 ],
				            visible: false
				        }],
			footerCallback: function ( row, data, start, end, display ) {
		            var api = this.api()
					$(".divTotalSum").empty();

		            // Total Quantity over all pages
		            var totalQuantity = api.column(2).data().reduce(function (a, b){
		                    return Number(a.toString().replace(',', '')) + Number(b.toString().replace(',', ''));
		                }, 0 );
					$(".divTotalSum").append('<div><label>Grand Total : &nbsp; <span>'+totalQuantity.toFixed(2).replace(/\B(?=(\d{3})+(?!\d))/g, ',')+'</span></label></div>\n');
					$(".divTotalSum").css({"border": "1px solid #ddd"});
				}
    		});
		}
	})
});