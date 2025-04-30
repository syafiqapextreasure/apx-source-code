/**
 * 
 */

$("#accept").click(function(){
	var pointer = $("#pointer").val();
	var term = $("#term").val();
	$("#modaltag").modal("show");
	$.get("Modal_TagChange",{pointer:pointer, term:term},function(data){
		$("#tagcontent").html(data);
	});
})


$("#acceptchg").click(function(){
	var pointer = $("#pointer").val();
	var term = $("#changercrd").val();
	//var oriterm = $("#term").val();
	var tag = $('.tags :selected').val();
	var ctrlno = [];
	
	$("#tagtbl tbody tr td:nth-child(3)").each(function() {
		 ctrlno.push($(this).text());
	});
	
	alert(ctrlno.toString());
	alert(ctrlno.length);
	$.get("Handler_GlobalChange",{pointer:pointer, term:term,tag:tag, ctrlno:ctrlno,total:ctrlno.length},function(data){
		$("#modaltag").modal("hide");
		if(data.trim()=="fail"){
			swal("", "Term already exist. Please perform global merge");
		}else{
			swal("", "Successfully Updated");
			var title = $("#criteria").val();
			var tag = $('.tags :selected').val()
			loader("#searchcontent");
			$.get("TermRetrieval",{criteria:title,tag:tag},function(data_title){
				$("#searchcontent").html(data_title);
			 });	
		}
		//$("#tagcontent").html(data);
	});
})


$("#delete").click(function(){
    $("#changercrd").val("");
})

function refresh(){
	document.location.reload(true)
}