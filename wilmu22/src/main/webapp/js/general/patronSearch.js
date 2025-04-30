$(document).ready(function() {
	$('#btn_submit').click(function() {
		var criteria = $('#patronSearchInput').val();
		var searchType = $(".text-value").data('span');
	//	var searchType= $('#patronsearch').val();
		var cate_id = $('#cate_type').val();
		var action = $("#action").val();
		var sort = $("#patronSortedBy").val();
		
		$.get("SearchPatron",{criteria:criteria,search_type:searchType,cate_id:cate_id, action:action, sort:sort},function(data_vendor){
		/*	$("#result_vendor").collapse('show');
			$("#search_vendor").collapse('hide');*/
			$("#display_vendor").html(data_vendor);
		   });
	});
});