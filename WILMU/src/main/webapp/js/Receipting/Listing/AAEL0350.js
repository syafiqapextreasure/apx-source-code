$(document).ready(function() {
	///////////////////// input-startDate set Current Date  //////////////////
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
	
	$(".removeHeaderForTable").empty()
	
	$("#lblPatronID").attr('maxLength', 12);	
	$("#input-startDate, #input-endDate, #Reterive").attr('disabled', 'disabled');	
	//$('.patronid').prop('disabled', true);   FOR THE BUTTON MODAL TO LOOK FOR USER
	
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
				});
			}
		});
	});
	
	$("#lblPatronID").on('input', function() {
		checkConditionPartForInputFormatting()
	})
	
	$('.lblName').on('DOMSubtreeModified', function(){
	  checkConditionPartForInputFormatting()
	});
	
	$("#input-startDate, #input-endDate").on('change', function() {
		checkConditionPartForInputFormatting()
	})
	
	$('input[type=checkbox][name=chkBoxSearchCateria]').click(function() {
		checkConditionPartForDisableInput()
		checkConditionPartForInputFormatting()
	})
	
	function checkConditionPartForInputFormatting(){
		if($("#lblPatronID").val().length > 0 && $("#dateRange").prop("checked") != true){ // patronId HAS INPUT ONLY && date NOT CHECKED
			$('#Reterive').removeAttr('disabled');
		}
		if($("#lblPatronID").val().length == 0 && $("#dateRange").prop("checked") != true){ // patronId HAS NO INPUT ONLY && date NOT CHECKED
			$('#Reterive').attr('disabled', 'disabled');
		}
		if($("#lblPatronID").val().length > 0 && $("#dateRange").prop("checked") == true && ($("#input-startDate").val().length > 0 || $("#input-endDate").val().length > 0)){ // patronId HAS INPUT ONLY && date CHECKED && date has one of input
			$('#Reterive').removeAttr('disabled');
		}
		if($("#lblPatronID").val().length > 0 && $("#dateRange").prop("checked") == true && $("#input-startDate").val().length == 0 && $("#input-endDate").val().length == 0){ // patronId HAS INPUT ONLY && date CHECKED && date has no input
			$('#Reterive').attr('disabled', 'disabled');
		}
	}

	function checkConditionPartForDisableInput(){
		if($("#dateRange").prop("checked") == true){
			$("#input-startDate, #input-endDate").removeAttr('disabled');
		}
		if($("#dateRange").prop("checked") != true){
			$("#input-startDate, #input-endDate").attr('disabled', 'disabled');
		}
	}
 
	$(".patronid").removeAttr('href'); 
	/// on click searc patron 	
	$('.patronid').on('click',function(){
		var url = "Modal_PatrSearch";
		$.get(url,function(data){
			$("#Modal_PatrSearchContent").html(data);
		});
	});

	////// table setup
	$('#reportTable').DataTable( {
		destroy: true,
		searching: false,
		bLengthChange: false,
		autoWidth: false,
		responsive: true,
		paging: false,
		footer: false,
		bSort : false
	});
	
	// when reterive
	$('#Reterive').click(function(){
		var sDate, eDate;
		var startSentDate = $("#input-startDate").val();
		if(startSentDate == ''){
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
		
		var patronId = $("#lblPatronID").val();
		var dateRangeCheck = '';
		
		if($("#dateRange").prop("checked") == true){
			dateRangeCheck= 'Y'
		} else {
			dateRangeCheck = 'N'
		}
		
		$.get('getOutstandingOverdueFineList', {patronId : patronId, dateRangeCheck : dateRangeCheck, startSentDate : startSentDate, endSentDate : endSentDate}, 
		function(responseJson) {
			if(responseJson != null){
				$("#patronId").text(responseJson.patronId)
				$("#patronName").text(responseJson.patronName)
				$("#balance").text(responseJson.patronBalance)

				var t = $('#reportTable').DataTable({
							dom: 'Bfrtip',
				            buttons: [
				                /*'excel', 'pdf', 'print'*/
										{
									  		extend: 'excelHtml5',
									        filename: 'WILMU_OutstandingOverdueFines',
									        title: 'Outstanding Overdue Fines from ' + sDate +" until "+ eDate,
											exportOptions: {
												search: 'applied',
												order: 'applied',
												rows: ':visible'
											},    
									 	},
										{
											text: '<i class="fa fa-file-pdf-o"></i> PDF',
											extend: 'pdfHtml5',
											filename: 'WILMU_OutstandingOverdueFines',
											charset: "utf-8",
											bom : "true",
											orientation: 'portrait', //portrait
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
										           };
											doc.content[1].table.body[0][0].text = ''
											doc.content[1].table.body[0][1].text = ''
											doc.content[1].table.widths = [ '40%', '60%'];
															//Remove the title created by datatTables
															doc.content.splice(0, 1, {
										                        text: [
										                                   { text: 'Outstanding Overdue Fines from '+sDate + " until " +eDate+ '\n',bold:true,fontSize:13,alignment: 'center'},
										                        ],
										                        margin: [0, 0, 0, 12],
										                    });
									
															doc['footer']=(function(page, pages) {
																return {
																	columns: [
																		{
																			alignment: 'left',
																			text: ['\t\t\t\t  ', moment(new Date()).format("DD/MM/YYYY") +'\t' +moment(new Date()).format("hh:mm:ss A")]
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
						                	filename: 'WILMU_OutstandingOverdueFines',
						                	title: 'Outstanding Overdue Fines',
											charset: "utf-8",
											bom : "true",
											exportOptions: {
												//columns: ':visible',
												search: 'applied',
												order: 'applied',
												rows: ':visible'
											},
											customize: function(doc){
														var split_csv = doc.split("\n");
														$.each(split_csv.slice(1), function (index, csv_row) {
															var csv_cell_array = csv_row.split('","');
															csv_cell_array[1].replace("", (index+1));
														});
														return "Outstanding Overdue Fines from "+sDate + " until " + eDate +"\n\n" + doc;
													 }
				             			},
				            ],
							destroy: true,
							searching: true,
							bLengthChange: false,
							autoWidth: false,
							responsive: true,
							processing: false,
							paging:   false,
							bInfo : false,
							bSort : false,
							language: {
				             loadingRecords : "Please wait - loading...",
				             processing :     "Please wait formatting in progress..."
				        	},
							deferRender: true,
				});
			}
		})
    });
});