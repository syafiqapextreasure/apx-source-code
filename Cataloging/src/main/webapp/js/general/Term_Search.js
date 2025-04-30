/**
 * 
 */

/*$('#criteria').keypress(function (e) {
 var key = e.which|| event.keyCode;
 if(key == 13)  // the enter key code
  {
	 // e.preventDefault();
	aletr("test");
   // return false;  
  }
}); */  

/*$('#termSearch').on('hidden.bs.modal', function (e) {        
	alert("1");
	$(this).removeData()
})*/


function getData(e){
	var code = e.keyCode || e.which;
	if(code == '9'||code=='13'){
		 e.preventDefault();
	 getResult();
	}
	
}

function getResult(){
	var title = $("#criteria").val();
	var module = $("#module").val();

	$("#search_termsearch").collapse("hide");
	$("#result_termsearch").collapse("show");
	var tag = $("#termtag").val();
	loader("#display_termsearch");
	$.get("Table_TermSearch",{criteria:title,tag:tag, module:module},function(data_title){
		$("#display_termsearch").html(data_title);
	   });	
}
	
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
	
	/*$('#criteria').keypress(function(e) {
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

	});*/
	
	
		//Close term search modal only
	function closeTermModal(){
			$("#termSearch").modal("hide");
		}
		
		//Click term search row to get particular value of the table row
		$('.clickable-row1').click(function() {
			var action = $("#action").val();
			var tag = $('#termtag').val();
			var title = $(this).attr("data-value");
			var name = $("#tagValue").attr("name");
			var tagid = $("#tagid").val();
			var module = $("#module").val();
			
			   $.get("Handler_Subfields",{title:title,tag:tag, name:name, action:action, tagid:tagid,module:module},function(html){
				   if(action=="modal_termsearch"){
					   $("tr.primarySubf").remove();
					//$("tr.primarySubf").empty();
					$("tr.headingTr").after(html);
				   }else{
						//$('#BO_Record tbody tr').eq(tagid).find('.data').val(html);
					   $('#BO_Record tbody tr.' + tagid).find('.data').val(html);
				   }
					$('#termSearch').modal('hide');
					$('#termSearchs').modal('hide');
			   });
			
		});
	