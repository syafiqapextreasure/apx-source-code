/**
 * Javascript for Modal_SearchVendor
 */
$(document).ready(function() {
	

      $('#tableVendor').DataTable({
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
	
	/////////////////////  CLICK button-vendorSearch /////////////////////////////
	$(".searchVendor").click(function(){

		var searchCriteria =  $( "#dropdown-searchCriteria option:selected" ).val();  //select

		var criteria = $("#input-criteria").val();  /// search value
		
		var vendAction  = $("#vendAction").val(); 
		
		loader("#table-vendor");
		$.get('SearchVendor?searchCriteria='+searchCriteria + '&criteria=' + criteria
				+ '&action=' + vendAction,function(data){
			// Hide the search form
			$('#search_vendor').collapse("hide");
			// Show the result form
			$('#result_vendor').collapse("show");
			
			$("#table-vendor").html(data);
		});
	});
	
	/*$('#table-vendor tbody').on('click', '.btnSelectVend', function () {

		 var getVendorCode = $(this).attr("data-value");
		 var getVendorName = $(this).attr("data-value2");
		 //$(".close").click();
		 $("#closeModalVendor").click();
		 $("#input-vendorCode").val(getVendorCode);
		 $("#input-vendorCode").focus();
		 $(".div-vendorname").text(getVendorName);
	});
	
	///modal_vendorSearch
	
	$('#modal_vendorSearch').on('hidden.bs.modal', function () {
		$('#search_vendor').collapse("show");
		$('#result_vendor').collapse("hide");
		$("#input-criteria").val("");			
		$('#dropdown-searchCriteria option[value=vendorCode]').attr('selected','selected');
		$('#tableVendor').dataTable().fnClearTable();
	});*/
	
});

