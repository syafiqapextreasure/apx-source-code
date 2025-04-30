$(document).ready(function() {
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
	
	$("#input-contorlNo").attr('maxlength',10);
	$('.vendorCode, .controlNoSearch').prop('disabled', true);
	$("#Reterive, #input-startDate, #input-endDate, #input-vendorCode, #input-contorlNo").attr('disabled', 'disabled');
	$("#bindingInstructionPrintCheck, #bindingSlipPrintCheck").attr('disabled', 'disabled');
	$("#itemSerial").prop("checked", true);
	
	
	$('input[type=checkbox][name=chkBoxSearchCateria]').click(function() {
		checkConditionPartForDisableInput()
		$("#bindingInstructionPrintCheck, #bindingSlipPrintCheck").prop('disabled', true);
		$("#bindingInstructionPrintCheck, #bindingSlipPrintCheck").prop("checked", false);
	})
	
	$('#input-vendorCode, #input-contorlNo').on('input',function(e){
		checkConditionPartForDisableInput()
		$("#bindingInstructionPrintCheck, #bindingSlipPrintCheck").prop('disabled', true);
		$("#bindingInstructionPrintCheck, #bindingSlipPrintCheck").prop("checked", false);
	});
	
	$("#input-startDate, #input-endDate").on('change', function() {
		checkConditionPartForDisableInput()
		$("#bindingInstructionPrintCheck, #bindingSlipPrintCheck").prop('disabled', true);
		$("#bindingInstructionPrintCheck, #bindingSlipPrintCheck").prop("checked", false);
	})
	
	$('.div-vendorName').on('DOMSubtreeModified', function(){
	  checkConditionPartForDisableInput()
	  $("#bindingInstructionPrintCheck, #bindingSlipPrintCheck").prop('disabled', true);
	  $("#bindingInstructionPrintCheck, #bindingSlipPrintCheck").prop("checked", false);
	});
	
	// modal_controlNoSearch
	$('#modal_controlNoSearch').on('hidden.bs.modal', function () {
		checkConditionPartForDisableInput()
		$("#bindingInstructionPrintCheck, #bindingSlipPrintCheck").prop('disabled', true);
	 	$("#bindingInstructionPrintCheck, #bindingSlipPrintCheck").prop("checked", false);
	});
	
	function checkConditionPartForDisableInput(){
		// dateRange only checked
		if($("#dateRange").prop("checked") == true && $("#controlNo").prop("checked") != true && $("#binder").prop("checked") != true){
			$("#input-startDate, #input-endDate").removeAttr('disabled');
			$("#Reterive").attr('disabled', 'disabled');
			if($("#input-startDate").val().length > 0){
				$("#Reterive").removeAttr('disabled');
			}
		}
		// binder only checked
		if($("#binder").prop("checked") == true && $("#controlNo").prop("checked") != true && $("#dateRange").prop("checked") != true){
			$("#input-vendorCode").removeAttr('disabled');
			$('.vendorCode').prop('disabled', false);
			$("#Reterive").attr('disabled', 'disabled');
			if($("#input-vendorCode").val().length > 0){
				$("#Reterive").removeAttr('disabled');
			}
		}
		// controlNo only checked
		if($("#controlNo").prop("checked") == true && $("#binder").prop("checked") != true && $("#dateRange").prop("checked") != true){
			$("#input-contorlNo").removeAttr('disabled');
			$(".controlNoSearch").prop('disabled', false);
			$("#Reterive").attr('disabled', 'disabled');
			if($("#input-contorlNo").val().length > 0){
				$("#Reterive").removeAttr('disabled');
			}
		}
		// binder && controlNo only checked
		if($("#binder").prop("checked") == true && $("#controlNo").prop("checked") == true && $("#dateRange").prop("checked") != true){
			$("#input-vendorCode, #input-contorlNo").removeAttr('disabled');
			$('.vendorCode').prop('disabled', false);
			$(".controlNoSearch").prop('disabled', false);
			$("#Reterive").attr('disabled', 'disabled');
			if($("#input-vendorCode").val().length > 0 && $("#input-contorlNo").val().length > 0){
				$("#Reterive").removeAttr('disabled');
			}
		}
		// binder && dateRange only checked
		if($("#binder").prop("checked") == true && $("#dateRange").prop("checked") == true && $("#controlNo").prop("checked") != true){
			$("#input-vendorCode, #input-startDate, #input-endDate").removeAttr('disabled');
			$('.vendorCode').prop('disabled', false);
			$("#Reterive").attr('disabled', 'disabled');
			if($("#input-vendorCode").val().length > 0 && $("#input-startDate").val().length > 0){
				$("#Reterive").removeAttr('disabled');
			}
		}
		// dateRange && controlNo only checked
		if($("#dateRange").prop("checked") == true && $("#controlNo").prop("checked") == true && $("#binder").prop("checked") != true){
			$("#input-startDate, #input-endDate, #input-contorlNo").removeAttr('disabled');
			$(".controlNoSearch").prop('disabled', false);
			$("#Reterive").attr('disabled', 'disabled');
			if($("#input-startDate").val().length > 0 && $("#input-contorlNo").val().length > 0){
				$("#Reterive").removeAttr('disabled');
			}
		}
		// dateRange not checked
		if($("#dateRange").prop("checked") != true){
			$("#input-startDate, #input-endDate").attr('disabled', 'disabled').val("");
		}
		// binder not checked
		if($("#binder").prop("checked") != true){
			$(".div-vendorName").text('');
			$("#input-vendorCode").attr('disabled', 'disabled').val("");
			$('.vendorCode').prop('disabled', true);
		}
		// controlNo not checked
		if($("#controlNo").prop("checked")!= true){
			$(".controlNoSearch").prop('disabled', true);
			$("#input-contorlNo").attr('disabled', 'disabled').val("");
		}
		// all checkbox not checked
		if($("#controlNo").prop("checked")!= true && 
			$("#binder").prop("checked")!= true &&
			$("#dateRange").prop("checked")!= true){
			$("#Reterive").attr('disabled', 'disabled');
			$("#bindingInstructionPrintCheck, #bindingSlipPrintCheck").prop('disabled', true);
		}
		// all checkbox checked
		if($("#binder").prop("checked")== true &&
			$("#controlNo").prop("checked")== true && 
			$("#dateRange").prop("checked")== true){
				$("#input-vendorCode, #input-contorlNo, #input-startDate, #input-endDate").removeAttr('disabled');
				$("#Reterive").attr('disabled', 'disabled');
				if($("#input-vendorCode").val().length > 0 && $("#input-contorlNo").val().length > 0 && $("#input-startDate").val().length > 0){
					$("#Reterive").removeAttr('disabled');
				}
		}
	}

	// Vendor
	$('.vendorCode').on('click',function(){
		var url = "Modal_Vendor?action=All";

		$.get(url,function(data){
			$("#modal_vendorSearchContent").html(data);
		});
	});
	
	// Control Number
	$('.controlNoSearch').on('click',function(){
		var url = "Modal_BibSearch?action=8";

		$.get(url,function(data){
			$("#modal_controlNoSearchContent").html(data);
		});
	});
	
	$("#input-vendorCode").attr('maxlength',4);
	
	$("#input-vendorCode").on("keypress keyup blur",function (e) {
		$(this).val($(this).val().toUpperCase());
		var id = $("#input-vendorCode").val();
		
		// display vendor name
		$(".div-vendorName").empty();
		
		$.get('ResultVendorName', {
        	id : id,
			action : "All"
		 	}, function(responseJson) {
			if(responseJson != null){
				$.each(responseJson, function(key,value) {
					$(".div-vendorName").text(value['vendorName']);
					//$('#Reterive').prop('disabled', false);
					checkConditionPartForDisableInput()
				});
			}
		});
	});
	
	// clear vendor keydown backspace
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
	
	// table reportTable1 setup Serial
	var l = $('#reportTable1').DataTable( {
		destroy: true,
		searching: false,
		bLengthChange: false,
		autoWidth: false,
		responsive: true,
	});
	l.columns(12).visible( false );
	
	// table reportTable2 setup Monograph
 	var p = $('#reportTable2').DataTable( {
		destroy: true,
		searching: false,
		bLengthChange: false,
		autoWidth: false,
		responsive: true,
	});
	
	p.columns(10).visible( false );
	
	$('#reportTable2_wrapper').css({"display":"none"})
	
	function disablePrintSelect(){
		$("#bindingInstructionPrintCheck, #bindingSlipPrintCheck").removeAttr('disabled');
	}
	
	$('input[type="radio"]').click(function() {
		if($('input[name="itemType"]:checked').val() === 'M'){
			$('#reportTable1_wrapper').css({"display":"none"})
			$('#reportTable2_wrapper').css({"display":"block"})
		}
		if($('input[name="itemType"]:checked').val() === 'S'){
			$('#reportTable2_wrapper').css({"display":"none"})
			$('#reportTable1_wrapper').css({"display":"block"})
		}
	});
	
	
	//////////////////////////////////////////////////////////////////////////
	////////////////////////btn Reterive Click ///////////////////////////////
	//////////////////////////////////////////////////////////////////////////
	$('#Reterive').click(function(){
		var startSentDate = moment($("#input-startDate").val(), 'DD/MM/YYYY').format("YYYYMMDD");
		var endSentDate = moment($("#input-endDate").val(), 'DD/MM/YYYY').format("YYYYMMDD");
		
		var startSentDate = $("#input-startDate").val();
		if(startSentDate == ''){
			startSentDate = '';
		}else{
			startSentDate = moment($("#input-startDate").val(), 'DD/MM/YYYY').format("YYYYMMDD");
		}
		
		var endSentDate = $("#input-endDate").val();
		if(endSentDate == ''){
			endSentDate = '';
		}else{
			endSentDate = moment($("#input-endDate").val(), 'DD/MM/YYYY').format("YYYYMMDD");
		}
		
		if($('input[type=radio][name=itemType]:checked').val() === 'M'){
			
		var title = ''
		var year = ''
		var callNo = ''
		var publisher = ''
		var authorName = ''
		var accessionNumber = ''
		var isbn = ''
		var issn = ''
		
		var volFrom = ''
		var volTo = ''
		var issFrom = ''
		var issTo = ''
		var pages = ''
		var bindNo = ''
		var binderCode = ''
		var copyNo = ''
			
		// result display for Monograph
		$('#reportTable2').DataTable({
			dom: 'Bfrtip',
			info:     false,
			buttons: [
						{	text: '<i class="fa fa-file-pdf-o"></i> PDF',
				            action: function ( e, dt, node, config ) {
								var tableTest = $('#reportTable2').DataTable();
								var $rows = tableTest.$('tr.selected');
								if($rows.length){
									if($("#bindingInstructionPrintCheck").prop("checked") == true && 
									$("#bindingSlipPrintCheck").prop("checked") == false){ // type : Serial && Binding Instruction Checked
										$.post("Global?type=Handler&name=GeneratePreviewDocumentBindingListing", {
											itemType: $('input[type=radio][name=itemType]:checked').val(), // done
											bindingType: 'BI',
											title: title,
											authorName: authorName,
											accessionNumber: accessionNumber,
											callNo: callNo,
											bindingNo: bindNo,
											isbn: isbn,
											binderCode: binderCode,
											copyNo: copyNo
										}).then(function(result){
												var w = window.open("_blank");
												w.document.write(result);
												w.print();	 
											});
									}
									if($("#bindingSlipPrintCheck").prop("checked") == true && 
									$("#bindingInstructionPrintCheck").prop("checked") == false){ // type : Monograph && Binding Slip Checked
										$.post("Global?type=Handler&name=GeneratePreviewDocumentBindingListing", {
											itemType: $('input[type=radio][name=itemType]:checked').val(),
											bindingType: 'BS',
											title: title,
											authorName: authorName,
											accessionNumber: accessionNumber,
											year: year,
											callNo: callNo,
											isbn: isbn,
											publisher: publisher
										}).then(function(result){
												var w = window.open("_blank");
												w.document.write(result);
												w.print();	 
											});
										}
									if($("#bindingInstructionPrintCheck").prop("checked") == true && 
									$("#bindingSlipPrintCheck").prop("checked") == true){
										$.post("Global?type=Handler&name=GeneratePreviewDocumentBindingListing", {
											itemType: $('input[type=radio][name=itemType]:checked').val(), // done
											bindingType: 'BI',
											title: title,
											authorName: authorName,
											accessionNumber: accessionNumber,
											callNo: callNo,
											bindingNo: bindNo,
											isbn: isbn,
											binderCode: binderCode,
											copyNo: copyNo
										}).then(function(result){
												var w = window.open("_blank");
												w.document.write(result);
												w.print();	 
										});
										$.post("Global?type=Handler&name=GeneratePreviewDocumentBindingListing", {
											itemType: $('input[type=radio][name=itemType]:checked').val(),
											bindingType: 'BS',
											title: title,
											authorName: authorName,
											accessionNumber: accessionNumber,
											year: year,
											callNo: callNo,
											isbn: isbn,
											publisher: publisher
										}).then(function(result){
												var w = window.open("_blank");
												w.document.write(result);
												w.print();	 
										});
									}
								}
								if($("#bindingInstructionPrintCheck").prop("checked") == true && $("#bindingSlipPrintCheck").prop("checked") == false && $rows.length == 0){
									swal("", "Please select reocrd to preview.", "");
								}
								if($("#bindingSlipPrintCheck").prop("checked") == true && $("#bindingInstructionPrintCheck").prop("checked") == false && $rows.length == 0){
									swal("", "Please select reocrd to preview.", "");
								}
								if($("#bindingInstructionPrintCheck").prop("checked") == true && 
								$("#bindingSlipPrintCheck").prop("checked") == true && $rows.length == 0){
									swal("", "Please select reocrd to preview.", "");
								}
								if($("#bindingInstructionPrintCheck").prop("checked") == false && 
								$("#bindingSlipPrintCheck").prop("checked") == false){
									swal("", "Please select either Binding Slip or Binding Instruction to be previewed.", "");
								}
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
		    	url: "getSlipInfoList",
		        data : {dateCheck : $("#dateRange").prop("checked") == true ? 'Y':'N',
						startSentDate : startSentDate, 
						endSentDate : endSentDate, 
						itemType : $('input[type=radio][name=itemType]:checked').val(),
						binderCheck : $("#binder").prop("checked") == true ? 'Y':'N',
						binderValue : $("#input-vendorCode").val(),
						controlCheck : $("#controlNo").prop("checked") == true ? 'Y':'N',
						controlValue : $("#input-contorlNo").val()
						},
		        type: "GET",
		        dataSrc: function (json) {
		            var return_data = new Array();
					$.each( json, function( key, value ) {
						return_data.push({
			                'Control No' : value.controlNo,
							'Title' : value.title,
							'Accession_No' : value.accessionNo,
							'Author' : value.author,
							'ISBN' : value.isbNs,
							'Copy_No' : value.copyNo,
							'Year' : value.year,
							'Publisher' : value.publisher,
							'Call_No' : value.callNo,
			                'Binding_No' : value.bindingNo,
							'BinderName' : value.binderCode
			            })
					});
		            return return_data;
		          },
		     },
			columns: [
				{ "data": "Control No" },
				{ "data": "Title" },
				{ "data": "Accession_No" },
			    { "data": "Author" },
				{ "data": "ISBN" },
				{ "data": "Copy_No" },
				{ "data": "Year" },
				{ "data": "Publisher" },
			    { "data": "Call_No" },
				{ "data": "Binding_No" },
				{ "data": "BinderName" },
			  ],
			select: 'single',
    		});
			disablePrintSelect()
			
			var table = $('#reportTable2').DataTable();
			
			table.columns(10).visible( false );
 
			$('#reportTable2 tbody').on( 'click', 'tr', function () {
				title = table.row( this ).data().Title
				authorName = table.row( this ).data().Author
				accessionNumber = table.row( this ).data().Accession_No
				year = table.row( this ).data().Year
				callNo = table.row( this ).data().Call_No
				isbn = table.row( this ).data().ISBN
				publisher = table.row( this ).data().Publisher
				bindNo = table.row( this ).data().Binding_No
				binderCode = table.row( this ).data().BinderName
				copyNo = table.row( this ).data().Copy_No
			});
		}
		if($('input[type=radio][name=itemType]:checked').val() === 'S'){
			
		var title = ''
		var year = ''
		var callNo = ''
		var publisher = ''
		var authorName = ''
		var accessionNumber = ''
		var isbn = ''
		var issn = ''
		
		var volFrom = ''
		var volTo = ''
		var issFrom = ''
		var issTo = ''
		var pages = ''
		var bindNo = ''
		var binderCode = ''
		var copyNo = ''
			
		// result display for Serial
		$('#reportTable1').DataTable({
			dom: 'Bfrtip',
			info:     false,
			buttons: [
						{	text: '<i class="fa fa-file-pdf-o"></i> PDF',
				            action: function ( e, dt, node, config ) {
								var tableTest = $('#reportTable1').DataTable();
								var $rows = tableTest.$('tr.selected');
								if($rows.length){
									if($("#bindingInstructionPrintCheck").prop("checked") == true && 
									$("#bindingSlipPrintCheck").prop("checked") == false){ // type : Serial && Binding Instruction Checked Only
										$.post("Global?type=Handler&name=GeneratePreviewDocumentBindingListing", {
											itemType: $('input[type=radio][name=itemType]:checked').val(),
											bindingType: 'BI',
											bindingNo: bindNo,
											title: title,
											year: year,
											callNo: callNo,
											issn: issn,
											publisher: publisher,
											volFrom: volFrom,
											volTo: volTo,
											issFrom: issFrom,
											issTo: issTo,
											pages: pages,
											binderCode : binderCode
										}).then(function(result) {
												var w = window.open("_blank");
												w.document.write(result);
												w.print();	 
											});
									}
									if($("#bindingSlipPrintCheck").prop("checked") == true && 
									$("#bindingInstructionPrintCheck").prop("checked") == false){ // type : Monograph && Binding Slip Checked Only
										$.post("Global?type=Handler&name=GeneratePreviewDocumentBindingListing", {
											itemType: $('input[type=radio][name=itemType]:checked').val(),
											bindingType: 'BS',
											title: title,
											year: year,
											callNo: callNo,
											issn: issn,
											publisher: publisher,
											volFrom: volFrom,
											volTo: volTo,
											issFrom: issFrom,
											issTo: issTo,
											pages: pages
										}).then(function(result){
												var w = window.open("_blank");
												w.document.write(result);
												w.print();	 
											});
										}
									if($("#bindingInstructionPrintCheck").prop("checked") == true && 
									$("#bindingSlipPrintCheck").prop("checked") == true){
										// FOR BINDING INSTRUCTION PRINT FIRST
										$.post("Global?type=Handler&name=GeneratePreviewDocumentBindingListing", {
											itemType: $('input[type=radio][name=itemType]:checked').val(),
											bindingType: 'BI',
											bindingNo: bindNo,
											title: title,
											year: year,
											callNo: callNo,
											issn: issn,
											publisher: publisher,
											volFrom: volFrom,
											volTo: volTo,
											issFrom: issFrom,
											issTo: issTo,
											pages: pages,
											binderCode : binderCode
										}).then(function(result) {
												var w = window.open("_blank");
												w.document.write(result);
												w.print();	 
										});
										
										// FOR BINDING SLIP PRINT SECOND
										$.post("Global?type=Handler&name=GeneratePreviewDocumentBindingListing", {
											itemType: $('input[type=radio][name=itemType]:checked').val(),
											bindingType: 'BS',
											title: title,
											year: year,
											callNo: callNo,
											issn: issn,
											publisher: publisher,
											volFrom: volFrom,
											volTo: volTo,
											issFrom: issFrom,
											issTo: issTo,
											pages: pages
										}).then(function(result){
												var w = window.open("_blank");
												w.document.write(result);
												w.print();	 
										});
									}
								}
								if($("#bindingInstructionPrintCheck").prop("checked") == true && $("#bindingSlipPrintCheck").prop("checked") == false && $rows.length == 0){
									swal("", "Please select reocrd to preview.", "");
								}
								if($("#bindingSlipPrintCheck").prop("checked") == true && $("#bindingInstructionPrintCheck").prop("checked") == false && $rows.length == 0){
									swal("", "Please select reocrd to preview.", "");
								}
								if($("#bindingInstructionPrintCheck").prop("checked") == true && 
								$("#bindingSlipPrintCheck").prop("checked") == true && $rows.length == 0){
									swal("", "Please select reocrd to preview.", "");
								}
								if($("#bindingInstructionPrintCheck").prop("checked") == false && 
								$("#bindingSlipPrintCheck").prop("checked") == false){
									swal("", "Please select either Binding Slip or Binding Instruction to be previewed.", "");
								}
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
		    	url: "getSlipInfoList",
		        data : {dateCheck : $("#dateRange").prop("checked") == true ? 'Y':'N',
						startSentDate : startSentDate, 
						endSentDate : endSentDate, 
						itemType : $('input[type=radio][name=itemType]:checked').val(),
						binderCheck : $("#binder").prop("checked") == true ? 'Y':'N',
						binderValue : $("#input-vendorCode").val(),
						controlCheck : $("#controlNo").prop("checked") == true ? 'Y':'N',
						controlValue : $("#input-contorlNo").val()
						},
		        type: "GET",
		        dataSrc: function (json) {
		            var return_data = new Array();
					$.each( json, function( key, value ) {
						return_data.push({
			                'Control No' : value.controlNo,
							'Title' : value.title,
							'Vol_From' : value.volFrom,
							'Vol_To' : value.volTo,
							'Iss_From' : value.issFrom,
							'Iss_To' : value.issTo,
							'ISSN' : value.isbNs,
							'Year' : value.year,
							'Publisher' : value.publisher,
							'Call_No' : value.callNo,
			                'Binding_No' : value.bindingNo,
			                'Pages' : value.pages,
							'BinderName' : value.binderCode
			            })
					});
		            return return_data;
		          },
		     },
			columns: [
				{ "data": "Control No" },
				{ "data": "Title" },
				{ "data": "Vol_From" },
			    { "data": "Vol_To" },
				{ "data": "Iss_From" },
			    { "data": "Iss_To" },
				{ "data": "ISSN" },
				{ "data": "Year" },
				{ "data": "Publisher" },
			    { "data": "Call_No" },
				{ "data": "Binding_No" },
			    { "data": "Pages" },
				{ "data": "BinderName" }
			  ],
			select: 'single',
    		});
			disablePrintSelect()
			
			var table = $('#reportTable1').DataTable();
			
			table.columns(12).visible( false );
 
			$('#reportTable1 tbody').on( 'click', 'tr', function () {
				title = table.row( this ).data().Title
				year = table.row( this ).data().Year
				callNo = table.row( this ).data().Call_No
				issn = table.row( this ).data().ISSN
				publisher = table.row( this ).data().Publisher
				volFrom = table.row( this ).data().Vol_From
				volTo = table.row( this ).data().Vol_To
				issFrom = table.row( this ).data().Iss_From
				issTo = table.row( this ).data().Iss_To
				pages = table.row( this ).data().Pages
				bindNo = table.row( this ).data().Binding_No
				binderCode = table.row( this ).data().BinderName
			});
		}
	})
});