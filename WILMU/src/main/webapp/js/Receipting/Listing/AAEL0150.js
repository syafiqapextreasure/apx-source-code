$(document).ready(function() {	
	$('#Reterive').prop('disabled', true);
	$('#patronCate').multiselect({
    	allSelectedText: 'All',
   		maxHeight: 200,
   		includeSelectAllOption: true
	}); 
	
	///////////////////// input-startDate set Current Date  //////////////////
	$('#input-startDate').datepicker({
		format: "dd/mm/yyyy",
		todayBtn: true,
		autoclose: true,
		todayHighlight: true,
	});
	
	////// table setup
	$('#listingreportTable').DataTable({
		destroy: true,
		searching: false,
		bLengthChange: false,
		autoWidth: false,
		responsive: true,
	});
	
	///////////////////// input-endDate set Current Date  ////////////////////
	var today = new Date();  
	$('#input-endDate').val(moment(today).format("DD/MM/YYYY"));
	$('#input-endDate').datepicker({
		format: "dd/mm/yyyy",
		todayBtn: true,
		autoclose: true,
		todayHighlight: true,
	});
	
	$("#input-endDate,#input-startDate,#patronCate").change(function() {
		checkDataFormat($(this).val())
    });

	$("#input-endDate,#input-startDate,#patronCate").on('input',function() {
		checkDataFormat($(this).val())
    });

	function checkDataFormat(searchStatistics){
		if($('#input-endDate').val().length == 0 && $('#input-startDate').val().length == 0 && Array.from($('#patronCate').val()).length == 0){ // all fields empty
			$('#Reterive').prop('disabled', true);
		}else if($('#input-endDate').val().length == 0 && $('#input-startDate').val().length == 0 && Array.from($('#patronCate').val()).length > 0){ // all date empty
			$('#Reterive').prop('disabled', true);
		}else if(($('#input-endDate').val().length > 0 || $('#input-startDate').val().length > 0) && Array.from($('#patronCate').val()).length == 0){
			$('#Reterive').prop('disabled', true);
		}else{
			$('#Reterive').prop('disabled', false);
		}
	}
	
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
		
		/////result display
		var t = $('#listingreportTable').DataTable({
			dom: 'Bfrtip',
			info:     false,
			buttons: [
						{
					  		extend: 'excelHtml5',
					        filename: 'WILMU_Fines Outstanding Listing',
					        title: 'Fines Outstanding Listing from ' + sDate +" until "+ eDate,
					                
					 	},
						{
					    	text: '<i class="fa fa-file-pdf-o"></i> PDF',
							extend: 'pdfHtml5',
							filename: 'WILMU_Fines Outstanding Listing',
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
						                                   { text: 'Fines Outstanding Listing from '+sDate + " until " +eDate+ '\n',bold:true,fontSize:13,alignment: 'center'}						                        ],
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
		                	filename: 'WILMU_Fines Outstanding Listing',
							charset: "utf-8",
							bom : "true",
							customize: function(doc){
										var split_csv = doc.split("\n");
										$.each(split_csv.slice(1), function (index, csv_row) {
											var csv_cell_array = csv_row.split('","');
											csv_cell_array[1].replace("", (index+1));
										});
										return "Fines Outstanding Listing from "+sDate + " until " + eDate +"\n\n" + doc;
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
		    	url: "getFinesOutstandingList",
		        data : {startSentDate : startSentDate, endSentDate : endSentDate, patronCategory : JSON.stringify($('#patronCate').val())},
		        type: "GET",
		        dataSrc: function (json) {
		            var return_data = new Array();
					var i = 0
					$.each( json, function( key, value ) {
						return_data.push({
			                'No': (i+1),
			                'Patron ID' : value.id,
							'Patron Name' : value.name,
							'Patron Category' : value.category,
			                'Course' : value.course,
			                'Department' : value.department,
			                'Amount' : value.amount
			            })
					i++
					});
		            return return_data;
		          },
		     },
			columns: [
				{ "data": "No" },
				{ "data": "Patron ID" },
				{ "data": "Patron Name" },
				{ "data": "Patron Category" },
			    { "data": "Course" },
				{ "data": "Department" },
			    { "data": "Amount" }
			  ],
			columnDefs: [{
			            targets: [ 3 ],
			            visible: false
			        }],
			drawCallback: function( settings ) {
		        var api = this.api();

				var last1 = null;
				var rows1 = api.rows({ page: 'current' }).nodes();
				
				api.column(3, { page: 'current' }).data().each(function (group, i) {
                    if (last1 !== group) {
                        $(rows1).eq(i).before('<tr class="group"><td colspan="6">' + group + '</td></tr>');
                        last1 = group;
                    }
                });
		    },
			footerCallback: function ( row, data, start, end, display ) {
		            var api = this.api()

		            // Total Quantity over all pages
		            totalQuantity = api.column(6).data().reduce(function (a, b){
		                    return Number(a) + Number(b);
		                }, 0 );
					$("#allTotalAmount").text(totalQuantity.toFixed(2));
		        }
    		});
	})
});