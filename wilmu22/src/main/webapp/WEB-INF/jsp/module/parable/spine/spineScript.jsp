
<script>
function getValue(){
	$(".message").html($('.bookChk:checked').length + " enteries selected ");
}


function loader(target_id) {
	$(target_id).load('include/Loading.jsp');
}

$(function(){
	
	$("#number").keydown(function(event) {
		
		if (this.value.length == 0 && event.which == 48 ){
			 return false;
		  }
		
	});
	
	$(".checkAll").change(function () {
	    $("input:checkbox[class=bookChk]").prop('checked', $(this).prop("checked"));
	    $(".message").html($('.bookChk').length + " enteries selected ");
	});
	
	function hideSearch(name) {
	    $("#queryDetail").children().hide();
	    $("#"+ name).show();
	    $(".submit").attr('id',name);
// 	    alert($(".submit").attr('id'));
	};
	
	$('#location_radio_1').on('click', function() {
	   	if ($(this).val() === 'on') {
			$('#catalog_date14').removeAttr("disabled")
			$('#catalog_date24').removeAttr("disabled")
			$('#catalog_date14').prop("readonly", "readonly");
			$('#catalog_date24').prop("readonly", "readonly");
			$('#index_date14').removeAttr("readonly");
			$('#index_date24').removeAttr("readonly");
			$('#index_date14').val("");
			$('#index_date24').val("");
			$('#index_date14').prop("disabled", "disabled");
			$('#index_date24').prop("disabled", "disabled");
	   }
	});
	$('#location_radio_2').on('click', function() {
		if ($(this).val() === 'on') {
			$('#index_date14').removeAttr("disabled")
			$('#index_date24').removeAttr("disabled")
			$('#index_date14').prop("readonly", "readonly");
			$('#index_date24').prop("readonly", "readonly");
			$('#catalog_date14').removeAttr("readonly");
			$('#catalog_date24').removeAttr("readonly");
			$('#catalog_date14').val("");
			$('#catalog_date24').val("");
			$('#catalog_date14').prop("disabled", "disabled");
			$('#catalog_date24').prop("disabled", "disabled");
	  	}
	});
	$('#category_radio_1').on('click', function() {
		if ($(this).val() === 'on') {
			$('#catalog_date12').removeAttr("disabled")
			$('#catalog_date22').removeAttr("disabled")
			$('#catalog_date12').prop("readonly", "readonly");
			$('#catalog_date22').prop("readonly", "readonly");
			$('#index_date12').removeAttr("readonly");
			$('#index_date22').removeAttr("readonly");
			$('#index_date12').val("");
			$('#index_date22').val("");
			$('#index_date12').prop("disabled", "disabled");
			$('#index_date22').prop("disabled", "disabled");
	  	}
	});
	$('#category_radio_2').on('click', function() {
		if ($(this).val() === 'on') {
			$('#index_date12').removeAttr("disabled")
			$('#index_date22').removeAttr("disabled")
			$('#index_date12').prop("readonly", "readonly");
			$('#index_date22').prop("readonly", "readonly");
			$('#catalog_date12').removeAttr("readonly");
			$('#catalog_date22').removeAttr("readonly");
			$('#catalog_date12').val("");
			$('#catalog_date22').val("");
			$('#catalog_date12').prop("disabled", "disabled");
			$('#catalog_date22').prop("disabled", "disabled");
	  	}
	});
	$('#smd_radio_1').on('click', function() {
		if ($(this).val() === 'on') {
			$('#catalog_date13').removeAttr("disabled")
			$('#catalog_date23').removeAttr("disabled")
			$('#catalog_date13').prop("readonly", "readonly");
			$('#catalog_date23').prop("readonly", "readonly");
			$('#index_date13').removeAttr("readonly");
			$('#index_date23').removeAttr("readonly");
			$('#index_date13').val("");
			$('#index_date23').val("");
			$('#index_date13').prop("disabled", "disabled");
			$('#index_date23').prop("disabled", "disabled");
	  	}
	});
	$('#smd_radio_2').on('click', function() {
		if ($(this).val() === 'on') {
			$('#index_date13').removeAttr("disabled")
			$('#index_date23').removeAttr("disabled")
			$('#index_date13').prop("readonly", "readonly");
			$('#index_date23').prop("readonly", "readonly");
			$('#catalog_date13').removeAttr("readonly");
			$('#catalog_date23').removeAttr("readonly");
			$('#catalog_date13').val("");
			$('#catalog_date23').val("");
			$('#catalog_date13').prop("disabled", "disabled");
			$('#catalog_date23').prop("disabled", "disabled");
	  	}
	});
	
	hideSearch("accession_no");
	
	$("#search_by").change(function() {
		var query = $("#search_by").val();
		
		if(query == "accession_no_range"){
			hideSearch(query);			
		}else if(query == "accession_no"){
			hideSearch(query);			
		}else if (query == "call_no"){
			hideSearch(query);
		}else if (query == "catalog_date"){
			hideSearch(query);
		}else if (query == "control_no"){
			hideSearch(query);
		}else if (query == "index_date"){
			hideSearch(query);
		}else if (query == "branch"){
			hideSearch(query);
		}else if (query == "item_cat"){
			hideSearch(query);
		}else if (query == "smd"){
			hideSearch(query);
		}else if (query == "title"){
			hideSearch(query);
		}
	});
	
// 	$(".submit").click(function(){
// 		var obj = $(this);
// 		if(obj.attr("id") == "accession_no"){
// 			var data = $("#accession_no").find("input").serialize();
// 			alert(data);
// 		}
// 	})


$('.submit').click(function(){
		$("#loader").show();
		$("#queryResult").hide();
		$('#checkAll').prop('checked', false);
		
		var unprint = null;
		$('.submit').attr("disabled", "disabled");
		var selectValue = $("#splitMethod").val();
		var obj = $(this);
		var data = null;
		var inputID = obj.attr("id"); 
		if( $("#"+inputID).find("input").val() != null){
			data = $("#"+inputID).find("input").serialize();
			if( $("#"+inputID).find("select").val()){
				var data2  = inputID+"="+ $("#"+inputID).find("select").val();
				data = data+ '&'+ data2;
			}
		}else if( $("#"+inputID).find("select").val()){
			data = inputID+"="+ $("#"+inputID).find("select").val();
		}		
		
		
		if($('input[name="unprint"]:checked').length > 0){
			unprint = 0;
		}else{
			unprint = 1;
		}
		
			loader('#loader');
			$.ajax({ // Send the credential values to another checker.php using Ajax in POST menthod
			type : 'POST',
			data : data+"&action="+obj.attr("id")+"&unprint="+unprint,
			url  : 'spineProcess',
			dataType : "json",
			success: function(responseText){
				$("#loader").hide();
				$("#queryResult").show();
				$('.submit').prop("disabled", false);// Get the result and asign to each case
				if(inputID!="accession_no_range"){
					$("#queryResult > tbody").html("");
				}
				$("#recordCount").html(" " + responseText.length);
				if(responseText.length > 0){
					for(var i =0;i < responseText.length;i++)
					{	
						var accessionNo = "";
						if(responseText[i].accessionNo != null){
							accessionNo = responseText[i].accessionNo;
						}
											var newRowContent = null;
						if(responseText[i].title!=null){
							 newRowContent = "<tr><td><input class='bookChk' onchange='getValue()' value='"+ accessionNo +"' type='checkbox' name = 'selectedValue[]' checked/></td>"+
								"<td>"+ accessionNo + "</td>"+"<td>"+ responseText[i].copyNo + "</td>"+
								"<td>"+ responseText[i].callNo + "</td>"+"<td>"+ responseText[i].volume + "</td>"+
								"<td>"+ responseText[i].title + "</td>"+"<td>"+ responseText[i].author  + "</td>"+
								"<td>"+ responseText[i].itemCategoryDesc + "</td>"+
								"<td>"+ responseText[i].SMDDesc  + "</td>"+ "<td>"+ responseText[i].locationDesc + "</td>"+ 
								"<td>"+ responseText[i].branch + "</td>"+
								"<td>"+ responseText[i].catalognDate + "</td>"+"<td>"+ responseText[i].lastIndexDate + "</td>"+
								"<td>"+ responseText[i].accessionDate + "</td>"+"<td>"+ responseText[i].localCost +
								"<td>"+ responseText[i].foreignCost + "</td>" + "<td>"+ responseText[i].currency + "</td>"+
								"<td>"+ responseText[i].userDefineSpine + "</td>";
								
						}
						$("#queryResult tbody").append(newRowContent);
					}
				}else{
					//$("#errorModal").modal("toggle");		
					swal( '','No record found','');  			
				}
			}
		});
		return false;
	});
	
	$("#print").click(function(){
		var number = $("#number").val();
		if(number!=null&&number!=""){
			$("#noData").val(number);
		}else if (number == '') {
			number = "1";
			$("#noData").val(number);
	    }else{
			number = "1";
			$("#noData").val(number);
		}

		var splitVal = $("#splitMethod").val();
		var type = $("#splitMethod").find(':selected').data('type');

 		$("#splitStyle").val(splitVal);
 		$("#styletype").val(type);
 		
 		var accno = [];
 		$('input.bookChk:checkbox:checked').each(function () {
		    accno.push($(this).val());
		});
 		
 		localStorage.setItem("accno", accno.toString());
 		window.open("","printForm","width=500,height=300,toolbar=0");
		$("#selectedValue").submit();
		
	})
	
})
</script>
