$(document).ready(function() {
    $('.tableVendor').DataTable({
		"bDestroy": true,
         "paging": true,
         "lengthChange": false,
         "searching": false,
         "ordering": true,
         "info": true,
         "autoWidth": true
    }); 
	
	//////////////////////function loader //////////////////////////////////////
	function loader(target_id) {
		$(target_id).load('Loading');
	}
	
	//$(".btnSelectVend").css({ width: '90px', 'padding-top': '1px', 'padding-bottom': '1px' });
	
	/////////////////////  CLICK button-vendorSearch ///////////////////////////
	$(".searchVendor").click(function(e){
		e.preventDefault();
		e.stopImmediatePropagation();
		
		$('.tableVendor').dataTable().fnClearTable();

		var searchCriteria =  $( "#dropdown-searchCriteria option:selected" ).val();  //select
		var criteria = $("#input-criteria").val();  /// search value
		var encoded = encodeURIComponent(criteria);
		var vendAction  = $("#vendAction").val(); 
		
		loader("#table-vendor");
		$.get('SearchVendor?searchCriteria='+searchCriteria + '&criteria=' + encoded
				+ '&action=' + vendAction,function(data){
			// Hide the search form
			$('#search_vendor').collapse("hide");
			// Show the result form
			$('#result_vendor').collapse("show");
			$("#table-vendor").html(data);
		});
	});
	
	$('#table-vendor tbody').on('click', '.btnSelectVend', function () {
		 var getVendorCode = $(this).attr("data-value");
		 var getVendorName = $(this).attr("data-value2");
		 $("#closeModalVendor").click();
		 $("#input-vendorCode").val(getVendorCode);
		 $("#input-vendorCode").focus();
		 $(".div-vendorname").text(getVendorName);
	});
	
	// modal_vendorSearch
	$('#modal_vendorSearch').on('hidden.bs.modal', function () {
		$('#search_vendor').collapse("show");
		$('#result_vendor').collapse("hide");
		$("#input-criteria").val("");			
		$('#dropdown-searchCriteria option[value=vendorCode]').attr('selected','selected');
		$('.tableVendor').dataTable().fnClearTable();
	});
});