/**
 * 
 */
$(document).ready(function () {
	
	
	$("#cancel").click(function(){
		location.reload();
		localStorage.clear();
	})
$("#retrieveto, #retrievefrom").click(function(){
	$("#retrieval").modal("show");
	var action = $(this).data("action");
	var term = localStorage.getItem("fromTerm");
	var tag = localStorage.getItem("fromTag");

	$.get("Modal_Retrieval",{action:action},function(data){
		$("#retrievalcontent").html(data);
		if(term.trim()!=""){
			$("#select_tags").val(tag);
			$("#criteria").val(term);
			$.get("TermRetrieval",{criteria:term,tag:tag},function(data_title){
				$("#searchcontent").html(data_title);
				$("#changercrd").val("");
				
				if(action.trim()=="fromTerm"){
					localStorage.setItem("fromTerm", term);
					localStorage.setItem("fromTag", tag);
				}
			 });
		}
	});
});

$("#merge").click(function(){
	var pointer = [];
	var tag = [];
	var topointer = $("#topointer").val();
	var totag = $("#totag").val();
	
	$('#listable > tbody  > tr').each(function() {
		if($(this).find(".slctdpointer").val() == topointer){
			alertMessage("", "info", "134", "", '');
			pointer = [];
			tag = [];
		}else{
			pointer.push($(this).find(".slctdpointer").val());
			tag.push($(this).find(".slctdtag").val());
		}
	});
	
	$.get("Handler_Merge",{pointer:pointer, tag:tag,topointer:topointer, totag:totag, total:tag.length},function(data){
		alert(data);
		if(data.trim()=="success"){
			swal("", "Successfully merged", "");
			   setTimeout(function(){ location.reload(); }, 1000);			   
			//swal("", "Successfully merged", "");
			//location.reload();
			//localStorage.clear();

			   
			//swal("", "Successfully merged", "");
			//location.reload();
			//localStorage.clear();
		}else if(data.trim()=="Fail to delete invalid term"){
			alertMessage("", "info", "134", "", '');
		}
	});
	
/*	var topointer = $("#topointer").val();
	var totag = $("#totag").val();
	
	$.get("Handler_Merge",{pointer:pointer, tag:tag,topointer:topointer, totag:totag, total:tag.length},function(data){
		if(data.trim()=="success"){
			swal("", "Successfully merged", "");
			location.reload();
			localStorage.clear();
		}else if(data.trim()=="Fail to update pointer"){
			alertMessage("", "info", "134", "", '');
		}
	});*/
	
/*	$('#listable > tbody  > tr').each(function() {
		var pointer = $(this).find(".slctdpointer").val();
		var topointer = $("#topointer").val();
		var totag = $("#totag").val();
		var tag = $(this).find(".slctdtag").val();
	
		if(pointer == topointer){
			alertMessage("", "info", "134", "", '');
		}else{
			$.get("Handler_Merge",{pointer:pointer, tag:tag,topointer:topointer, totag:totag},function(data){
				if(data.trim()=="success"){
					swal("", "Successfully merged", "");
					location.reload();
					localStorage.clear();
				}else if(data.trim()=="Fail to update pointer"){
					alertMessage("", "info", "134", "", '');
				}
			});
		}
	});*/
});

$('table').on('click', '#remove', function(e){
	   $(this).closest('tr').remove()
	})


});