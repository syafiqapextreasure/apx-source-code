$(document).ready(function() {
	
	/*function messageBox(code, criteria, label){
			$.get("Global?type=Handler&name=Error_Page",{GL79ERRCODE : code,
				criteria : criteria,
			 	label : label},function(result){
			 		//alert(result);
					//swal('',result, 'info' );
					swal(result);
				
			});
	}*/ 
	
	//////////////////////////////////////////////////////////////////////////
	//////////////////////////setup page//////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////
	$("#startInput, #endInput").attr('maxlength','10');
	
	//table
	//////table setup
	$('#acqEnquiryTable').DataTable( {
		destroy: true,
		searching: false,
		bLengthChange: false,
		autoWidth: false,
		responsive: true,
	});
	
	jQuery.extend( jQuery.fn.dataTableExt.oSort, {
	    "date-uk-pre": function ( a ) {
	        if (a == null || a == "") {
	            return 0;
	        }
	        var ukDatea = a.split('/');
	        return (ukDatea[2] + ukDatea[1] + ukDatea[0]) * 1;
	    },
	 
	    "date-uk-asc": function ( a, b ) {
	        return ((a < b) ? -1 : ((a > b) ? 1 : 0));
	    },
	 
	    "date-uk-desc": function ( a, b ) {
	        return ((a < b) ? 1 : ((a > b) ? -1 : 0));
	    }
	});
	
	///////////////////// input-startDate set Current Date  //////////////////
	var today = new Date();  
	$('#input-startDate').datepicker({
		//startDate : today,
		//dateFormat : "dd/mm/yyyy",
		format: "dd/mm/yyyy",
		todayBtn: true,
		autoclose: true,
		todayHighlight: true,	
		endDate: new Date(),
		}).on('changeDate', function (selected) {
                    var minDate = new Date(selected.date);
                    minDate.setDate(minDate.getDate() + 1);
                    $('#input-endDate').datepicker('setStartDate', minDate);
	});
	
	///////////////////// input-endDate set Current Date  ////////////////////
	$('#input-endDate').val(moment(today).format("DD/MM/YYYY"));
	$('#input-endDate').datepicker({
		format: "dd/mm/yyyy",
		todayBtn: true,
		autoclose: true,
		todayHighlight: true,
     	endDate: new Date(),
		 }).on('changeDate', function (selected) {
                    var minDate = new Date(selected.date);
                    minDate.setDate(minDate.getDate() - 1);
                    $('#input-startDate').datepicker('setEndDate', minDate);
    });
	
	$('#acqStat, #dept').multiselect({
    	allSelectedText: 'All',
   		maxHeight: 200,
   		includeSelectAllOption: true,
		numberDisplayed: 1,
	});
	
	$("#startInput, #endInput").prop( "disabled", true);
	$("#acqStat, #dept").multiselect("disable");
	$("#input-vendorCode").prop( "disabled", true);
	$('.vendorCode').prop('disabled', true);
	$(".vendorCode").removeAttr('href');  
	
	$.get('ResultGlflag2', {
		 	id : "CURRENCYFORMAT",
		 	}, function(responseJson) {
			$("#setupCurrency").val(responseJson);
	});
	
	$("#chkBoxRange").on('change', function () {
		var self = $(this);
	    if (self.is(":checked")) {
			$("#startInput, #endInput").prop( "disabled", false);
			$('#Reterive').prop('disabled', true);
			
			
	    } else {
			$("#startInput, #endInput").prop( "disabled", true);
			$('#Reterive').prop('disabled', false);
			$("#startInput, #endInput").val("");
	    }
	});
	
	/*$("#startInput, #endInput").keyup(function(e){
		var startInput = $("#startInput").val();
		var endInput = $("#endInput").val();
		if(startInput.length >=1 || endInput.length >=1){
			$('#Reterive').prop('disabled', false);
		}else{
			$('#Reterive').prop('disabled', true);
		}
	});*/
	
	
	$("#startInput, #endInput").focusout(function(){
		
		e.preventDefault();
		e.stopImmediatePropagation();

	    if(parseFloat($("#startInput").val()) > parseFloat($("#endInput").val())){
			$("#startInput, #endInput").css("border", "solid red");
	        $("#Reterive").prop('disabled',true);
	
			messageBox("172","","");
			$("#chkBoxRange").focus();
	    } else {
	        $(".error").css("display","none");
			$("#startInput, #endInput").css("border", "");
	        $("#Reterive").prop('disabled',false);        
	    }
	    
	});
	

	//////
	$("#chkBoxStatus").on('change', function () {
	    var self = $(this);
	    if (self.is(":checked")) {
			$('#acqStat').multiselect("enable");
			$('#Reterive').prop('disabled', true);
			$('#acqStat').change(function() {
				if($("#acqStat").val() != ""){
					$('#Reterive').prop('disabled', false);
				}else{
					$('#Reterive').prop('disabled', true);
				}
			});	
	    } else {
			$('#acqStat').multiselect("disable");
			$('#Reterive').prop('disabled', false);
			$("#acqStat").multiselect("clearSelection")
 			$("#acqStat").multiselect( 'refresh' ); 
	    }
	});
	
	$("#chkBoxDepartment").on('change', function () {
	    var self = $(this);
	    if (self.is(":checked")) {
			$('#dept').multiselect("enable");
			$('#Reterive').prop('disabled', true);
			$('#dept').change(function() {
				if($("#dept").val() != ""){
					$('#Reterive').prop('disabled', false);
				}else{
					$('#Reterive').prop('disabled', true);
				}
			});
	    } else {
			$('#dept').multiselect("disable");
			$('#Reterive').prop('disabled', false);
			$("#dept").multiselect("clearSelection")
 			$("#dept").multiselect( 'refresh' ); 
	    }
	});
	
	$('.vendorCode').on('click',function(){
		var url = "Modal_Vendor?action=All";

		$.get(url,function(data){
			$("#modal_vendorSearchContent").html(data);
		});
	});
	
	$("#input-vendorCode").on("keypress keyup blur",function (e) {
		var id = $("#input-vendorCode").val();

		$(".div-vendorName").empty();
		////display vendor name
		$.get('ResultVendorName', {
        	id : id,
			action : "All"
		 	}, function(responseJson) {
			if(responseJson != null){
				$.each(responseJson, function(key,value) {
					$(".div-vendorName").text(value['vendorName']);
					$('#Reterive').prop('disabled', false);
				});
			}
		});
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
	
});