$(document).ready(function() {
	////// table setup
	$('#reportTable').DataTable( {
		destroy: true,
		searching: false,
		bLengthChange: false,
		autoWidth: false,
		responsive: true,
	});
	
	$('#sent-date, #dateRange, #monograph').prop('checked', true);
	
	$('#binding-status').multiselect({
    	allSelectedText: 'All',
   		maxHeight: 200,
   		includeSelectAllOption: true
	});
	
	$('#yearly-text').attr('maxLength', '4')
	$("#yearly-text").on("keypress keyup blur", function(event) {
		$(this).val($(this).val().replace(/[^\d].+/, ""));
		if ((event.which < 48 || event.which > 57)) {
			event.preventDefault();
		}
	});
	
	$('#yearly-text').val(moment().year());
	$('#yearly-text, #monthly-text, #Reterive, #lblPatronID').attr('disabled', 'disabled');
	$('.inputOfficerId').prop('disabled', true);
	
	///////////////////// input-startDate set Current Date  //////////////////
	$('#input-startDate').val(moment().startOf('month').format('DD/MM/YYYY'));
	$('#input-startDate').datepicker({
		format: "dd/mm/yyyy",
		todayBtn: true,
		autoclose: true,
		todayHighlight: true,
	});
	
	var monthlyValue = ''
	$("#monthly-text").on('change', function() {
		monthlyValue = $('#monthly-text').val();
		$('#input-startDate').val(moment().month(monthlyValue).startOf('month').format('DD/MM/YYYY'));
		$('#input-endDate').val(moment().month(monthlyValue).endOf('month').format('DD/MM/YYYY'));
	})
	
	var year = ''
	$("#yearly-text").on('input change', function() {
		year = $('#yearly-text').val();
		$('#input-startDate').val('01/01/'+year);
		$('#input-endDate').val('31/12/'+year);
	})
	
	///////////////////// input-endDate set Current Date  ////////////////////
	$('#input-endDate').val(moment().endOf('month').format('DD/MM/YYYY'));
	$('#input-endDate').datepicker({
		format: "dd/mm/yyyy",
		todayBtn: true,
		autoclose: true,
		todayHighlight: true,
	});

	////////////// radio button on click  ////////////////////
	$('input[type=radio][name=selectOption]').click(function() {
		var chkBoxSearchCateria = $("input[name='selectOption']:checked").val();
		switch(chkBoxSearchCateria) {
		  case 'yearly':
			$('#yearly-text').removeAttr('disabled');
			$('#input-startDate').val(moment().startOf('year').format('DD/MM/YYYY'));
			$('#input-endDate').val(moment().endOf('year').format('DD/MM/YYYY'));
			if($('#yearly-text').val() != moment().year()){
				$('#input-startDate').val('01/01/'+year);
				$('#input-endDate').val('31/12/'+year);
			}
			$('#monthly-text, #input-startDate, #input-endDate').attr('disabled', 'disabled');
		    break;
		  case 'monthly':
			$('#monthly-text').removeAttr('disabled');
			monthlyValue = $('#monthly-text').val();
			$('#input-startDate').val(moment().month(monthlyValue).startOf('month').format('DD/MM/YYYY'));
			$('#input-endDate').val(moment().month(monthlyValue).endOf('month').format('DD/MM/YYYY'));
			$('#yearly-text, #input-startDate, #input-endDate').attr('disabled', 'disabled');
		    break;
		  case 'dateRange':
			$('#input-startDate, #input-endDate').removeAttr('disabled');
			$('#yearly-text, #monthly-text').attr('disabled', 'disabled');
		    break;
		 case undefined:
			//searchCateriaval = "";
			//chkBoxSearchCateria = "";
		    break;
		} 
	});
	
	////////////// checkbox button on click  ////////////////////
	$('input[type=checkbox][name=chkBoxSearchCateria]').click(function() {
		if($("#officerId").prop("checked") == true){
			$('#lblPatronID').removeAttr('disabled');
			$('.inputOfficerId').prop('disabled', false);
		}
		if($("#officerId").prop("checked") == false){
			$('#lblPatronID').attr('disabled', 'disabled');
			$('.inputOfficerId').prop('disabled', true);
		}
		$('#lblPatronID').val('');
		checkFormat()
	});
	
	$('#yearly-text, #input-startDate, #input-endDate, #lblPatronID').on('input change', function() {
		checkFormat()
	})
	
	$('#binding-status, #monthly-text').on('change', function() {
		checkFormat()
	})
	
	$('#modal_officerIdSearch').on('hidden.bs.modal', function () {
		checkFormat()
	});
	
	// Officer Id
	$('.inputOfficerId').on('click',function(){
		var url = "Modal_PatrSearch";
		
		$.get(url,function(data){
			$("#modal_officerIdSearchContent").html(data);
		});
	});
	
	function checkFormat(){
		// FOR ALL TRUE ONLY
		if($("#yearly").prop("checked") == true && $("#yearly-text").val().length > 0 && $('#binding-status').val().length > 0 && $("#officerId").prop("checked") == false){
			$("#Reterive").removeAttr('disabled');
		}
		if($("#yearly").prop("checked") == true && $("#yearly-text").val().length > 0 && $('#binding-status').val().length > 0 && $("#officerId").prop("checked") == true && $('#lblPatronID').val().length > 0){
			$("#Reterive").removeAttr('disabled');
		}
		if($("#monthly").prop("checked") == true && $('#binding-status').val().length > 0 && $("#officerId").prop("checked") == false){
			$("#Reterive").removeAttr('disabled');
		}
		if($("#monthly").prop("checked") == true && $('#binding-status').val().length > 0 && $("#officerId").prop("checked") == true && $('#lblPatronID').val().length > 0){
			$("#Reterive").removeAttr('disabled');
		}
		if($("#dateRange").prop("checked") == true && ($('#input-startDate').val().length > 0 || $('#input-endDate').val().length > 0) && $('#binding-status').val().length > 0 && $("#officerId").prop("checked") == true && $('#lblPatronID').val().length > 0){
			$("#Reterive").removeAttr('disabled');
		}
		if($("#dateRange").prop("checked") == true && ($('#input-startDate').val().length > 0 || $('#input-endDate').val().length > 0) && $('#binding-status').val().length > 0 && $("#officerId").prop("checked") == false){
			$("#Reterive").removeAttr('disabled');
		}
		
		// FOR ALL FALSE YEARLY RADIO BUTTON ONLY
		if($("#yearly").prop("checked") == true && $("#yearly-text").val().length == 0 && $('#binding-status').val().length > 0 && $("#officerId").prop("checked") == true && $('#lblPatronID').val().length > 0){
			$("#Reterive").attr('disabled', 'disabled');
		}
		if($("#yearly").prop("checked") == true && $("#yearly-text").val().length > 0 && $('#binding-status').val().length == 0 && $("#officerId").prop("checked") == true && $('#lblPatronID').val().length > 0){
			$("#Reterive").attr('disabled', 'disabled');
		}
		if($("#yearly").prop("checked") == true && $("#yearly-text").val().length > 0 && $('#binding-status').val().length > 0 && $("#officerId").prop("checked") == true && $('#lblPatronID').val().length == 0){
			$("#Reterive").attr('disabled', 'disabled');
		}
		if($("#yearly").prop("checked") == true && $("#yearly-text").val().length == 0 && $('#binding-status').val().length > 0 && $("#officerId").prop("checked") == false){
			$("#Reterive").attr('disabled', 'disabled');
		}
		if($("#yearly").prop("checked") == true && $("#yearly-text").val().length > 0 && $('#binding-status').val().length == 0 && $("#officerId").prop("checked") == false){
			$("#Reterive").attr('disabled', 'disabled');
		}
		
		// FOR ALL FALSE MONTHLY RADIO BUTTON ONLY
		if($("#monthly").prop("checked") == true && $('#binding-status').val().length == 0 && $("#officerId").prop("checked") == true && $('#lblPatronID').val().length > 0){
			$("#Reterive").attr('disabled', 'disabled');
		}
		if($("#monthly").prop("checked") == true && $('#binding-status').val().length == 0 && $("#officerId").prop("checked") == true && $('#lblPatronID').val().length == 0){
			$("#Reterive").attr('disabled', 'disabled');
		}
		if($("#monthly").prop("checked") == true && $('#binding-status').val().length > 0 && $("#officerId").prop("checked") == true && $('#lblPatronID').val().length == 0){
			$("#Reterive").attr('disabled', 'disabled');
		}
		if($("#monthly").prop("checked") == true && $('#binding-status').val().length == 0 && $("#officerId").prop("checked") == false){
			$("#Reterive").attr('disabled', 'disabled');
		}
		
		// FOR ALL FALSE FOR DATERANGE RADIO BUTTON ONLY
		if($("#dateRange").prop("checked") == true && $('#input-startDate').val().length == 0 && $('#input-endDate').val().length == 0 && $('#binding-status').val().length > 0 && $("#officerId").prop("checked") == false){
			$("#Reterive").attr('disabled', 'disabled');
		}
		if($("#dateRange").prop("checked") == true && $('#input-startDate').val().length == 0 && $('#input-endDate').val().length == 0 && $('#binding-status').val().length == 0 && $("#officerId").prop("checked") == false){
			$("#Reterive").attr('disabled', 'disabled');
		}
		if($("#dateRange").prop("checked") == true && $('#input-startDate').val().length > 0 && $('#input-endDate').val().length > 0 && $('#binding-status').val().length == 0 && $("#officerId").prop("checked") == false){
			$("#Reterive").attr('disabled', 'disabled');
		}
		if($("#dateRange").prop("checked") == true && $('#input-startDate').val().length == 0 && $('#input-endDate').val().length > 0 && $('#binding-status').val().length == 0 && $("#officerId").prop("checked") == false){
			$("#Reterive").attr('disabled', 'disabled');
		}
			if($("#dateRange").prop("checked") == true && $('#input-startDate').val().length > 0 && $('#input-endDate').val().length == 0 && $('#binding-status').val().length == 0 && $("#officerId").prop("checked") == false){
			$("#Reterive").attr('disabled', 'disabled');
		}
		
		if($("#dateRange").prop("checked") == true && $('#input-startDate').val().length == 0 && $('#input-endDate').val().length > 0 && $('#binding-status').val().length > 0 && $("#officerId").prop("checked") == true && $('#lblPatronID').val().length == 0){
			$("#Reterive").attr('disabled', 'disabled');
		}
		if($("#dateRange").prop("checked") == true && $('#input-startDate').val().length > 0 && $('#input-endDate').val().length == 0 && $('#binding-status').val().length > 0 && $("#officerId").prop("checked") == true && $('#lblPatronID').val().length == 0){
			$("#Reterive").attr('disabled', 'disabled');
		}
		if($("#dateRange").prop("checked") == true && $('#input-startDate').val().length > 0 && $('#input-endDate').val().length > 0 && $('#binding-status').val().length > 0 && $("#officerId").prop("checked") == true && $('#lblPatronID').val().length == 0){
			$("#Reterive").attr('disabled', 'disabled');
		}
		if($("#dateRange").prop("checked") == true && $('#input-startDate').val().length > 0 && $('#input-endDate').val().length > 0 && $('#binding-status').val().length == 0 && $("#officerId").prop("checked") == true && $('#lblPatronID').val().length > 0){
			$("#Reterive").attr('disabled', 'disabled');
		}
		if($("#dateRange").prop("checked") == true && $('#input-startDate').val().length == 0 && $('#input-endDate').val().length == 0 && $('#binding-status').val().length > 0 && $("#officerId").prop("checked") == true && $('#lblPatronID').val().length > 0){
			$("#Reterive").attr('disabled', 'disabled');
		}
		if($("#dateRange").prop("checked") == true && $('#input-startDate').val().length == 0 && $('#input-endDate').val().length > 0 && $('#binding-status').val().length == 0 && $("#officerId").prop("checked") == true && $('#lblPatronID').val().length > 0){
			$("#Reterive").attr('disabled', 'disabled');
		}
		if($("#dateRange").prop("checked") == true && $('#input-startDate').val().length > 0 && $('#input-endDate').val().length == 0 && $('#binding-status').val().length == 0 && $("#officerId").prop("checked") == true && $('#lblPatronID').val().length > 0){
			$("#Reterive").attr('disabled', 'disabled');
		}
	}
	
	//////////////////////////////////////////////////////////////////////////
	////////////////////////btn Reterive Click ///////////////////////////////
	//////////////////////////////////////////////////////////////////////////
	$('#Reterive').click(function(){
		var dateType = $('input[type=radio][name=date-type]:checked').val();
		var selectOption = $('input[type=radio][name=selectOption]:checked').val();
		var bindingItem = $('input[type=radio][name=bindingItem]:checked').val();
		var bindingStatus = JSON.stringify($('#binding-status').val());
		var arraySumPrice = new Array();
		var offIdCheck = ''
		var officerId = ''
		
		if($("#officerId").prop("checked") == true){
			offIdCheck = $('#officerId').val();
			officerId  = $('#lblPatronID').val();
		}else{
			offIdCheck = 'N'
		}
		
		var startSentDate = moment($("#input-startDate").val(), 'DD/MM/YYYY').format("YYYYMMDD");
		var endSentDate = moment($("#input-endDate").val(), 'DD/MM/YYYY').format("YYYYMMDD");
		
		var sDate = '';
		var eDate = '';
		
		var startSentDate = $("#input-startDate").val();
		if(startSentDate == ''){
			startSentDate = '';
			sDate = ''
		}else{
			startSentDate = moment($("#input-startDate").val(), 'DD/MM/YYYY').format("YYYYMMDD");
			sDate = $("#input-startDate").val();
		}
		
		var endSentDate = $("#input-endDate").val();
		if(endSentDate == ''){
			endSentDate = '';
			eDate = ''
		}else{
			endSentDate = moment($("#input-endDate").val(), 'DD/MM/YYYY').format("YYYYMMDD");
			eDate = $("#input-endDate").val();
		}
		
		// result display
		$('#reportTable').DataTable({
			dom: 'Bfrtip',
			info:     false,
			buttons: [
						{
					  		extend: 'excelHtml5',
					        filename: 'WILMU_Binding Report',
					        title: 'WILMU Binding Report '+ sDate +" until "+ eDate,
					 	},
						{
					    	text: '<i class="fa fa-file-pdf-o"></i> PDF',
							extend: 'pdfHtml5',
							filename: 'WILMU_Binding Report',
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
											//Remove the title created by datatTables  
											doc.content.splice(0, 1, {
						                        text: [
						                                   { text: 'WILMU Binding Report from '+sDate + " until " +eDate+ '\n',bold:true,fontSize:13,alignment: 'center'}			                        ],
						                        margin: [0, 0, 0, 20],
						                    });
											doc.pageMargins = [ 20, 20, 20, 20 ]; //left, top, right, bottom
							doc.content.push( {
						                        alignment: 'right',
												bold:true,
								                   text: [
								                         	$('.divtotal').text(), 
								                        ]
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
		                	filename: 'WILMU_Binding Report',
							charset: "utf-8",
							bom : "true",
							customize: function(doc){
										var split_csv = doc.split("\n");
										$.each(split_csv.slice(1), function (index, csv_row) {
											var csv_cell_array = csv_row.split('","');
											csv_cell_array[1].replace("", (index+1));
										});
										return "WILMU Binding Reportfrom "+sDate + " until " + eDate +"\n\n" + doc;
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
		    	url: "getBindReport",
		        data : {dateType: dateType,
						selectOption: selectOption,
						bindingItem: bindingItem,
						bindingStatus: bindingStatus,
						startSentDate: startSentDate,
						endSentDate: endSentDate,
						offIdCheck: offIdCheck,
						officerId: officerId
						},
		        type: "GET",
		        dataSrc: function (json) {
		            var return_data = new Array();
					var i = 1
					$.each( json, function( key, value ) {
						return_data.push({
							'No' : i,
							'Title' : value.title,
							'Call No' : value.callNo,
							'Control No' : value.controlNo,
							'Accession No' : value.accessionNo,
							'Volume' : value.volume,
							'Binding Type' : value.bindingType,
							'Officer Id' : value.officerId,
							'Date Sent' : value.dateSent,
							'Date Expected' : value.dateExpected,
							'Date Return' : value.dateReturn,
							'Status' : value.status,
			                'Cost' : value.cost,
							'Binder' : value.binder
			            })
					i++
					});
		            return return_data;
		          },
		     },
			columns: [
				{ "data": "No" },
				{ "data": "Title" },
				{ "data": "Call No" },
				{ "data": "Control No" },
				{ "data": "Accession No" },
			    { "data": "Volume" },
				{ "data": "Binding Type" },
				{ "data": "Officer Id" },
				{ "data": "Date Sent" },
				{ "data": "Date Expected" },
			    { "data": "Date Return" },
				{ "data": "Status" },
				{ "data": "Cost" },
				{ "data": "Binder" },
			  ],
			footerCallback: function ( row, data, start, end, display ) {
		            var api = this.api()
					$(".divTotalSum").empty();
					
		            // Total Quantity over all pages
		            var totalQuantity = api.column(12).data().reduce(function (a, b){
		                    return Number(a) + Number(b);
		                }, 0 );

					var rowTableCount = Number(api.rows({ page: 'all' }).count())
					
					$(".divTotalSum").append('<div><label>TOTAL COST : '+totalQuantity.toFixed(2)+' &nbsp;</label></div>\n');
					$(".divTotalSum").append('<div><label>TOTAL RECORDS : '+rowTableCount+' &nbsp;</label></div>\n');
					$(".divTotalSum").css({"border": "1px solid #ddd"});
		        }
    		});
	})
});