/**
 * 
 */

$(document).ready(function() {
	$('.collapse').on('shown.bs.collapse', function(){
		$(this).parent().find(".glyphicon-chevron-up").removeClass("glyphicon-chevron-up").addClass("glyphicon-chevron-down");
		}).on('hidden.bs.collapse', function(){
		$(this).parent().find(".glyphicon-chevron-down").removeClass("glyphicon-chevron-down").addClass("glyphicon-chevron-up");
		});


});

function loader(target_id) {
	$(target_id).load('Loading');
}

function getURL(identifier){
	var searchType = $("input[name='searchSelection']:checked").val();
	var action = $(identifier).attr("data-action");
	var title = $('.criteria').val();
	 if(searchType=="selection"){
			var title = $('#criteria').val();
			var searchType = $('#search_type').val();
			loader("#div-result");
			$.get("RetrieveSearchTitle1",{criteria:title,search_type:searchType,action:action},function(data_title){
				$("#search_title").collapse('show');
				$("#result_title").collapse('show');
				$("#display_title").html(data_title);
			   });	
			}
	else if(searchType == "MatNo"){
		alert(title);
		$.get("Table_SearchByAccNo",{txtMatNo : title},function(data){
			$("#result").collapse('show');
			$('#titleSearch').modal('hide');
			$("#div-result").html(data);
		
			/*$("#div-result").html(data);
			$('#titleSearch').modal('hide');
		
			$("#txt-searchAll").val("no");*/
		});
}
	else if(searchType == "ctrlNo"){
			var searchType = "ctrlNo";
		
		url = "RetrieveSearchTitle1?criteria="+title+"&search_type="+searchType+"&action="+action;
		
		$.get(url,function(data_title){
			$("#search_title").collapse('show');
			$("#result_title").collapse('show');
			$("#display_title").html(data_title);
		});	
}
	
	else if(searchType == "tag"){

		$('#tags').change(function(){
	alert("fff");
		 var data = $(this).children('option:selected').data('id');
		 var value = $(this).val();
		});	
		url = "RetrieveSearchTitle1?criteria="+title+"&search_type="+searchType + "&values="+value+"&condition="+data;
		alert(url);
		$.get(url,function(data_title){
			$("#display_title").html(data_title);
		
	});

}
	document.getElementById("current_form").reset();

}

$('#btn-submit-retrievemodal').click(function(){
	
	// Display loading bar
	//replaceLoader("#display_title");
	var newSearch = $("input[name='searchSelection']:checked").val();
	 var checkedValue  = $('input[name=searchin]:checked', '#retrieveBO').val(); 
	 if(checkedValue=="index"){
		 if(newSearch=="selection"){
			var title = $('#criteria').val();
			var searchType = $('#search_type').val();
			$.get("RetrieveSearchTitle",{criteria:title,search_type:searchType},function(data_title){
				$("#display_title").html(data_title);
			   });	
			}
	else if(newSearch=="bufferNo"){
		var title = $('#criteria').val();
		
		$.get("jsp/modules/Cataloging/BO_Record/RetrieveBufferNo.jsp",{criteria:title},function(data_title){
			$("#display_title").html(data_title);
		   });	
		}
	else if(newSearch=="ctrlNo"){
		var title = $('#criteria').val();
		
		$.get("jsp/modules/Cataloging/BO_Record/RetrieveCtrlNo.jsp",{criteria:title},function(data_title){
			$("#display_title").html(data_title);
		   });	
		}
	   }
	   else{
		   if(newSearch=="bufferNo"){
				var title = $('#criteria').val();
				var searchType = "bufferno";
				$.get("RetrieveSearchTitle",{criteria:title,search_type:searchType},function(data_title){
					$("#display_title").html(data_title);
				   });	
				}
	   }
}) 

$('.clickable-row').click(function() {
		var pointer = $(this).attr("data-value");
		var title = $(this).find(".title").text();
		var matno = $(this).data("matno");
		var action = $(this).attr("data-action");
		if(pointer!=null){
		$.get("ViewTitles1",{pointer : pointer, action:action},function(data){
			$("#result_title").collapse('show');
			$("#display_title").html(data);
		});	
			document.getElementById('controlNo').focus();
		}	
		
		
		else if(matno!=null){
		if(action=="8"){
			document.getElementById("title").innerHTML = title;
			document.getElementById("CT03MATNO").value = matno;
		}else if(action=="5"){
			alert("ddd");
			$.get("Table_CTResultMaster",{controlNoInput : matno},function(data){
				$("#result").collapse('show');
				$('#titleSearch').modal('hide');
				$("#div-result").html(data);
			});
		}
		else{
		url = "Table_UnindexBO?matno="+matno+"&action=IndexUnindex";
				
				$.get(url,function(data_title){
					$('#titleSearch').modal('hide');
					$('.collapse').show();
					$("#tags").html(data_title);
					$("#actionstatus").html("Status: Record Retrieved");
				});	
		}
			}
		});

/*$('#tags').change(function(){
	 var data = $(this).children('option:selected').data('id');
	 var value = $(this).val();
	});*/

function messageBox1(code, criteria, label){
	var url = "Error_Message?GL79ERRCODE="+code+"" + 
		"&criteria=" + criteria + "&label="+label+"";
	$.ajax({
			url: url,
			success: function(result) {
				swal('Info!',result, 'warning' );
			}
		});
}

function messageBox(message, info, code, criteria, label){
	var url = "Error_Message?GL79ERRCODE="+code+"" + 
		"&criteria=" + criteria + "&label="+label+"";
	$.ajax({
			url: url,
			success: function(result) {
				swal(message,result, info );
			}
		});
}

function saveMessage(code, criteria, label){
	var url = "Error_Message?GL79ERRCODE="+code+"" + 
		"&criteria=" + criteria + "&label="+label+"";
	$.ajax({
			url: url,
			success: function(result) {
				swal('Cancelled!',result, 'error' );
			}
		});
}

var unsaved = false;

$(":input").change(function(){ //trigers change in all input fields including text type
    unsaved = true;
});

function unloadPage(){ 
    if(unsaved){
        return "You have unsaved changes on this page. Do you want to leave this page and discard your changes or stay on this page?";
    }
}

window.onbeforeunload = unloadPage;
