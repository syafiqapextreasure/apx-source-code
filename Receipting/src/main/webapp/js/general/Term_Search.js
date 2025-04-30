/**
 * 
 */

	
	//Submit term search form to get the list of search
	$('#btn-submit-title').click(function(){
			
			var title = $("#criteria").val();
			$("#search_termsearch").collapse("hide");
			$("#result_termsearch").collapse("show");
			var tag = $("#tag").val();
			loader("#display_termsearch");
			$.get("Table_TermSearch",{criteria:title,tag:tag},function(data_title){
				$("#display_termsearch").html(data_title);
			   });	
		});
	
	$('#criteria').keypress(function(e) {
	    if(e.which == 13) {
		var title = $(this).val();
		$("#search_termsearch").collapse("hide");
		$("#result_termsearch").collapse("show");
		var tag = $("#tag").val();
		loader("#display_termsearch");
		$.get("Table_TermSearch",{criteria:title,tag:tag},function(data_title){
			$("#display_termsearch").html(data_title);
		   });	
		return false;
	    }

	});
	
	
		//Close term search modal only
		$(".termSearch").click(function(){
			$("#termSearch").modal("hide");
		});
		
		
		
		//Click term search row to get particular value of the table row
		$('.clickable-row1').click(function() {
			var tag = $('#tag').val();
			var title = $(this).attr("data-value");
			var name = $(".concatData").attr('name');
	
			   $.get("Handler_Subfields",{title:title,tag:tag, name:name},function(html){
					$("tr.primarySubf").empty();
					$("tr.headingTr").after(html);
					$('#termSearch').modal('hide');
			   });
			
		});
	