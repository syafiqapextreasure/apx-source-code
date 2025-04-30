function messageBox(code, criteria, label){
	var url = "Error_Message?GL79ERRCODE="+code+"" + 
		"&criteria=" + criteria + "&label="+label+"";
	$.ajax({
			url: url,
			success: function(result) {
				//swal('',result, 'info' );
				swal(result);
			}
		});
}

function loader(target_id) {
	$(target_id).load('Loading');
}

	$(".dropdown-menu li a").click(function(){
		$('#searchPatron').removeData();
		//alert("sd");
	  	$(this).parents(".input-group-btn").find('.btn').html('<span class="text-value" data-span="'+$(this).data('value')+'">' + $(this).text() + '</span> <span class="caret"></span>');
	});
	
	
	$(document).on("click", "#btn_submit", function(event){
	 
		var criteria = $('#patronSearchInput').val();
		var searchType =$(".text-value").data('span');
		var cate_id = $('#cate_type').val();
		var action = $(".action2").val();
		var sort = $("#patronSortedBy").val();
		
		
		loader("#display_vendor");
		
		$.get("SearchPatron",{criteria:criteria,search_type:searchType,cate_id:cate_id, action:action, sort:sort, action2:$("#action2").val()},function(data_vendor){			
			$("#display_vendor").html(data_vendor);
			$('#patr_result').DataTable();
		   });
	});
	
	function appendData(patrid, patrname, action){
		
		if(action == 'null'){
			$("#lblPatronID").val(patrid);
			$(".lblName").text(patrname);
			$('#Reterive').prop('disabled', false);
		}

		
	
		/*setTimeout(function(){
	        $('#lblPatronID')[0].focus();
    }, 1000);*/
	
	
		
	}
