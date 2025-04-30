/**
 * 
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
	$("#searchVendor").click(function(){
		
		var searchCriteria =  $( "#dropdown-searchCriteria option:selected" ).val();  //select

		var criteria = $("#input-criteria").val();  /// search value
		
		loader("#table-vendor");
		$.get('SearchVendor?searchCriteria='+searchCriteria + '&criteria=' + criteria,function(data){
			// Hide the search form
			$('#search_vendor').collapse("hide");
			// Show the result form
			$('#result_vendor').collapse("show");
			
			$("#table-vendor").html(data);
		});
	});
	
	$('#table-vendor tbody').on('click', '#btnSelect', function () {

		 var getVendorCode = $(this).attr("data-value");
		 var getVendorName = $(this).attr("data-value2");
		 //$(".close").click();
		 $("#closeModalVendor").click();
		 $("#vendor").val(getVendorCode);
		 $("#vendor").focus();
		 $(".div-vendorname").text(getVendorName);
	});
	
});

