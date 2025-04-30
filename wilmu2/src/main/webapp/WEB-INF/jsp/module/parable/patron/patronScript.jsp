<script>
function loader(target_id) {
	$(target_id).load('../../include/Loading.jsp');
}
function getValue(){
	$(".message").html($('.patrChks:checked').length + " enteries selected ");
}

$(function(){
	
	$("#number").keydown(function(event) {
		if (this.value.length == 0 && event.which == 48 ){
			return false;
		  }
		
	});
	
	$(".checkAll").change(function () {
	    $(".patrChks:checkbox").prop('checked', $(this).prop("checked"));
	    $(".message").html($('.patrChks:checked').length + " enteries selected ");
	});
	
	function hideSearch(name) {
	    $("#queryDetail").children().hide();
	    $("#"+ name).show();
	    $(".submit").attr('id',name);
	};
	
	hideSearch("patron_id");
	
	$("#search_by").change(function() {
		var query = $("#search_by").val();
		hideSearch(query);
	})
	
	$('.submit').click(function(){
		$(".message").html();
		$("#loader").show();
		$("#queryResult").hide();
		var obj = $(this);
		var data = null;
		var inputID = obj.attr("id"); 
		var selectedBranch = $('#branch').val(); 
		var unprint = null;
		
		if( $("#"+inputID).find("input").val() != null){
			data = $("#"+inputID).find("input").serialize();
		}else if( $("#"+inputID).find("select").val()){
			data = inputID+"="+ $("#"+inputID).find("select").val();
		}
	
		$('#checkAll').prop('checked', false);
		if($('input[name="unprint"]:checked').length > 0){
			unprint = 0;
		}else{
			unprint = 1;
		}
		loader('#loader');
		$.ajax({ // Send the credential values to another checker.php using Ajax in POST menthod
		type : 'POST',
		data : data+"&action="+inputID+"&unprint=" + unprint + "&selectedBranch="+ selectedBranch,
		url  : 'patronProcess',
		dataType : "json",
		success: function(responseText){
			$("#loader").hide();
			$("#queryResult").show();
			$('.submit').prop("disabled", false);// Get the result and asign to each case
			$("#queryResult > tbody").html("");
			$("#recordCount").html(" " + responseText.length);
			if(responseText.length > 0){
				
				var patronId = "";
				for(var i =0;i < responseText.length;i++)
				{	
					var patron = responseText[i];
					var newRowContent = null;
						newRowContent ="<tr><td><input onchange='getValue()' class='patrChks' value="+ patron.patronId +" type='checkbox' name = 'selectedValue[]'/></td>"+"<td>"+ patron.patronId  + "</td>"+"<td>"+ patron.name + "</td>"+"<td>"+ patron.patronCategoryCode  + "</td>"+"<td>"+ patron.departmentCode + "</td>"+"<td>"+ patron.courseCode +"</td>"+"<td>"+ patron.membershipDate + "</td></tr>";	
					 $("#queryResult tbody").append(newRowContent);
				}
			}else{
				swal( '','No record found','');  
			}
		}
	});
	return false;
});
	$("#print").click(function(){
		//alert("click print");
		var number = $("#number").val();
		
		//alert("print number"+number+"number");
		
		if(number!=null&&number!=""){
			$("#noData").val(number);
		}else if (number == '') {
			number = "1";
			$("#noData").val(number);
	    }else{
			number = "1";
			$("#noData").val(number);
		}
		
		var splitVal = $("#splitMethod option:selected").val();

		$("#splitStyle").val(splitVal);

		window.open("","printForm","width=500,height=300,toolbar=0");
		$("#selectedValue").submit();
	})

})
</script>
