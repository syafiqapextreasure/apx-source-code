$(document).ready(function() {
	
	 //$('#patr_result').DataTable();
	 $('.patr_result').DataTable({
         "paging": true,
         "lengthChange": false,
         "searching": true,
         "ordering": false,
         "info": true,
         "autoWidth": true
       }); 

	 $("[title]").tooltip();
	
	$(".dropdown-menu li a").click(function(){
		$('#searchPatron').removeData();
	
		  $(this).parents(".input-group-btn").find('.btn').html('<span class="text-value" data-span="'+$(this).data('value')+'">' + $(this).text() + '</span> <span class="caret"></span>');
	});
	
	//////////////////////function loader //////////////////////////////////////
	function loader(target_id) {
		$(target_id).load('Loading');
	}
	
	
	$("#btn_submit").click(function(){
		var criteria = $('#patronSearchInput').val();
		var searchType =$(".text-value").data('span');
		//var searchType =$("#patronSearchInput option:selected").data('value');
	
		var cate_id = $('#cate_type').val();
		var action = $("#action2").val();
		var sort = $("#patronSortedBy").val();
		
		loader("#display_patronSearch");
		$.get("SearchPatron",{criteria:criteria,search_type:searchType,cate_id:cate_id, action:action, sort:sort},function(data_patron){
		/*	$("#result_vendor").collapse('show');
			$("#search_vendor").collapse('hide');*/
			$("#display_patronSearch").html(data_patron);
		});
	});
});	

	function appendData(GL14PATR, name){
		// $("#modal_patrSearch4order").modal("toggle");
		$(".lblPatronID").val(GL14PATR);
	 	$("#lblPatronID").val(GL14PATR);
	 	$("#lblPatronID").focus();
	 	//$('#modal_searchPatronID').modal('hide');
	 	///$(".closemodalpatr").click();
	}
	
	$(".closemodalpatr").click(function () {
        $('#modal_searchPatronID').modal('hide');
    });
	
	$('#modal_patrSearch4order').on('hidden.bs.modal', function () {
		$(this).removeData('bs.modal');
		$('body').addClass('modal-open');
	});