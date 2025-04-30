$(document).ready(function() {
	
	//////////////////////////////////////////////////////////////////////////
	//////////////////////////setup page//////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////
	$.urlParam = function(name){
		var results = new RegExp('[\?&]' + name + '=([^&#]*)').exec(window.location.href);
		return results[1] || 0;
	}
	
	$('.module').val(decodeURIComponent($.urlParam('value')));
	
	//$("#vendor").focus();
	$('#paymentReterive').prop('disabled', true);
	$('input[name=radioOption1]').attr("disabled",true);
	$('input[name=radioOption2]').attr("disabled",true);
	$('input[name=radioOption3]').attr("disabled",true);
	$('#input-startDoc, #input-endDoc').prop( "disabled", true );
	$('#input-startDate, #input-endDate').prop( "disabled", true );
	
	
	///////////////////// input-startDate set Current Date  //////////////////
	var today = new Date();  
	$('#input-startDate').datepicker({
		//startDate : today,
		format: "dd/mm/yyyy",
		todayBtn: true,
		autoclose: true,
		todayHighlight: true,
	}).on('changeDate', function(e) {
        // Revalidate the date field
		$('#paymentReterive').prop('disabled', false);
    });
	
	///////////////////// input-endDate set Current Date  ////////////////////
	
	$('#input-endDate').val(moment(today).format("DD/MM/YYYY"));
	$('#input-endDate').datepicker({
		format: "dd/mm/yyyy",
		todayBtn: true,
		autoclose: true,
		todayHighlight: true,
	}).on('changeDate', function(e) {
        // Revalidate the date field
		$('#paymentReterive').prop('disabled', false);
    });
	
	//////table setup
	$('#table-PayHistory').DataTable( {
		destroy: true,
		searching: false,
		bLengthChange: false,
		autoWidth: false,
		responsive: true,
	});
	
	///////////////////////////////ON CHANGE TYPE//////////////////////////////
	$('input[name=radioOption1]').change(function(){
		var value = $( 'input[name=radioOption1]:checked' ).val();
		
		if(value == "doctRange"){
			
			$('input[name=radioOption2]').attr("disabled",false);
			$('input[name=radioOption3]').attr("disabled",true);
			$('#input-startDate').datepicker('setDate', null);
			$('#input-endDate').val(moment(today).format("DD/MM/YYYY"));
			$('#input-endDate').datepicker('setDate', moment(today).format("DD/MM/YYYY"));
			$('#input-startDate').val("");
			$('input[name=radioOption3]').prop('checked', false);
			$("#paymentReterive").attr('disabled', 'disabled');
			
			$("input[name='radioOption2']").click(function () {
		    	if ($("input[name='radioOption2']").is(":checked")) {
		    		
		    		$('#input-startDoc, #input-endDoc').prop( "disabled", false );
					$('#input-startDate, #input-endDate').prop( "disabled", true );
					$("#input-startDoc").focus();
					
					$("#input-startDoc").blur(function() {
					    if(($("#input-startDoc").val().length >= 1) && $("#input-endDoc").val().length >= 1){
					    	$('#paymentReterive').prop('disabled', false);
					    } else {
					    	$('#paymentReterive').prop('disabled', true);
					    }
					});
					
					$("#input-endDoc").blur(function() {
					    if(($("#input-startDoc").val().length >= 1) && $("#input-endDoc").val().length >= 1){
					    	$('#paymentReterive').prop('disabled', false);
					    } else {
					    	$('#paymentReterive').prop('disabled', true);
					    }
					});
					
		    	}
		    });
			
		}else if(value=="dateRange"){
			$('input[name=radioOption2]').attr("disabled",true);
			$('input[name=radioOption3]').attr("disabled",false);
			/*$('#input-startDoc, #input-endDoc').prop( "disabled", true );*/
			$('#input-startDoc, #input-endDoc').val("");
			/*$('#input-startDate, #input-endDate').prop( "disabled", false );*/
			$('input[name=radioOption2]').prop('checked', false);
			$("#paymentReterive").attr('disabled', 'disabled');
			
			$("input[name='radioOption3']").click(function () {
		    	if ($("input[name='radioOption3']").is(":checked")) {
		    		
		    		$('#input-startDate, #input-endDate').prop( "disabled", false );
					$('#input-startDoc, #input-endDoc').prop( "disabled", true );
					$("#input-startDate").focus();
					
					$("#input-startDate").blur(function() {
					    if((($("#input-startDate").val().length >= 1) && $("#input-endDate").val().length >= 1) || $("#input-startDate").val().length >= 1){
					    	$('#paymentReterive').prop('disabled', false);
					    } else {
					    	$('#paymentReterive').prop('disabled', true);
					    }
					});

					$("#input-endDate").blur(function() {
					    if(($("#input-startDate").val().length >= 1) && $("#input-endDate").val().length >= 1){
					    	$('#paymentReterive').prop('disabled', false);
					    } else {
					    	$('#paymentReterive').prop('disabled', true);
					    }
					});
					
		    	}
		    });
		}
	});
	
	//////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////
	
	//////////////////////////////////////////////////////////////////////////
	//////////////////////////vendor /////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////
	
	//blur
	$("#vendor").blur(function(e){
		var vendor = $("#vendor").val();
	
		$(".div-vendorname").empty();
		////display vendor name
		$.get('GetVendorName', {
        	code : vendor
		 	}, function(responseJson) {
		 		
		 	if(responseJson == ""){
		 		$("#vendor").val("");
		 		$("#vendor").css("border", "solid red");
		 		$('input[name=radioOption1]').attr("disabled",true);
		 		$("#paymentReterive").attr('disabled', 'disabled');
		 	}
			if(responseJson != null){
				$.each(responseJson, function(key,value) {
					$(".div-vendorname").text(value['vendorName']);
					$("#vendor").css("border", "");
					$("#paymentReterive").attr('disabled', false);
					$('input[name=radioOption1]').attr("disabled",false);
				});
			}
		});
	});
	
	//backspace
	$("#vendor").keydown(function(e){ 
		var code = e.keyCode || e.which;
		if(code == '8' ||code == '46'){
			$(".div-vendorname").empty();
			$("#vendor").css("border", "");
			$("#paymentReterive").attr('disabled', false);
			$('#table-PayHistory').dataTable().fnClearTable();
		}
	});
	
	//////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////
	
	//////////////////////////////////////////////////////////////////////////
	////////////////////////btn Payment Reterive//////////////////////////////
	//////////////////////////////////////////////////////////////////////////
	$('#paymentReterive').click(function(){
		$('#table-PayHistory').dataTable().fnClearTable();
		var module = $(".module").val();
		var vendor = $("#vendor").val();
		var radioOption1 = $( 'input[name=radioOption1]:checked' ).val();
		var radioOption2 = $( 'input[name=radioOption2]:checked' ).val();
		var radioOption3 = $( 'input[name=radioOption3]:checked' ).val();
		var inputstartDoc = $("#input-startDoc").val();
		var inputendDoc = $("#input-endDoc").val();
		var startDate = moment($("#input-startDate").val(), 'DD/MM/YYYY').format("YYYYMMDD");
		var endDate = moment($("#input-endDate").val(), 'DD/MM/YYYY').format("YYYYMMDD"); 
		
		$('#panel-result').collapse('show');
		
		$('#table-PayHistory').DataTable( {
			destroy: true,
			searching: false,
			bLengthChange: false,
			autoWidth: false,
			responsive: true,
			processing: true,
			//info: false,
		    ajax: {
		    	url: "resultPayHistory",
		        data : {module : module, vendor : vendor, radioOption1 : radioOption1, radioOption2 : radioOption2,
		        	radioOption3 : radioOption3, inputstartDoc : inputstartDoc, inputendDoc : inputendDoc,
		        	startDate : startDate, endDate : endDate},
		        type: "GET",
		        dataSrc: function (json) {
		            var return_data = new Array();

		            for(var i=0;i< json.length; i++){

		              return_data.push({
		                'Invoice No': json[i].InvoiceNo,
		                'Invoice Date' : json[i].InvoiceDate, 
		                'Order No' : json[i].OrderNo,
		                'Cheque No' : json[i].ChequeNo,
		                'Cheque Date' : json[i].ChequeDate,
		                'Voucher No' : json[i].VoucherNo,
		                'Voucher Date' : json[i].VoucherDate,
		                'Ref No' : json[i].RefNo,
		                'Ref Date' : json[i].RefDate,
		                'Amount' : json[i].Amount,
		                'Vendor' : json[i].Vendor
		            })
		            }
					
		            return return_data;
		          },
		     },//This is the end of the AJAX object from the example above
		     	columns    : [
					{'data': 'Invoice No'},
					{'data': 'Invoice Date', className: "text-center"},
					{'data': 'Order No', className: "text-center"},
					{'data': 'Cheque No', className: "text-center"},
					{'data': 'Cheque Date', className: "text-center"},
					{'data': 'Voucher No', className: "text-center" },
					{'data': 'Voucher Date', className: "text-center"},
					{'data': 'Ref No', className: "text-center"},
					{'data': 'Ref Date', className: "text-center"},
					{'data': 'Amount', className: "text-center", render: $.fn.dataTable.render.number(',', '.', 2, '')},
					{'data': 'Vendor'}
				],
		});
		
	});
	//////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////
	
	//////////////////////////////////////////////////////////////////////////
	////////////////////////btn REFERESH//////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////
	$('#refresh').click(function(){
		$('#table-PayHistory').dataTable().fnClearTable();
		$('#panel-result').collapse('hide');
		$("#searchForm")[0].reset();
		$(".div-vendorname").empty();
		$('#input-endDate').val(moment(today).format("DD/MM/YYYY"));
		$('#input-endDate').datepicker('setDate', moment(today).format("DD/MM/YYYY"));
		//$(this).find('form').trigger('reset');
	});
	
	//////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////
	
});