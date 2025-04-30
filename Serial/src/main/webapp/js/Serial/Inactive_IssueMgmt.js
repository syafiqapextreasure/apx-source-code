/**
 * 
 */


$(document).ready(function() {

	var serial = $('#inactiveList').DataTable({
 	    responsive: true
 	});
	
	$("#refresh").click(function(){
	/*	document.getElementById("inactiveForm").reset();
		
		$("#orderDate2").datepicker({
    		format: 'dd/mm/yyyy'
    	});
    	$( "#orderDate2" ).datepicker( "option", "dateFormat", "dd/mm/yyyy" );
    	   $('#orderDate2').datepicker('setDate', new Date());*/
		location.reload();
	})
	
	$(".checkAll").change(function () {
		 var rows = $('#inactiveList').DataTable().rows({ 'search': 'applied' }).nodes();
	/*	 var rows = $('#orderingList').find('tbody tr');
		  var checked = $(this).prop('checked');
		 $.each(rows, function() {
		      var checkbox = $($(this).find('td').eq(0)).find('input.orderlist').prop('checked', checked);
		      //$(".orderlist").prop('checked', $(this).prop('checked'));
		   });*/
		 
		 if(!this.checked){
	    	   $('input[name="selectedValue[]"]', rows).prop('checked', false);
	    	   $("#purge").attr("disabled", "disabled");
	       }else{
	    	   $('input[name="selectedValue[]"]', rows).prop('checked', this.checked);
	    	   $("#purge").removeAttr("disabled");
	       }
	
		});
	
	$("#orderno1").keyup(function () {
			if($(this).val()!="" && $("#orderno2").val()!=""){
				$("input:checkbox[value='orderno']").attr("checked", true);
			}else{
				$("input:checkbox[value='orderno']").attr("checked", false);
			}
	});
	
	$("#orderno2").keyup(function () {
		if($(this).val()!="" && $("#orderno1").val()!=""){
			$("input:checkbox[value='orderno']").attr("checked", true);
		}else{
			$("input:checkbox[value='orderno']").attr("checked", false);
		}
	});
	
	$("#controlno1").keyup(function () {
		if($(this).val()!="" && $("#controlno2").val()!=""){
			$("input:checkbox[value='controlno']").attr("checked", true);
		}else{
			$("input:checkbox[value='controlno']").attr("checked", false);
		}
	});
	  
	
	$("#controlno2").keyup(function () {
		if($(this).val()!="" && $("#controlno1").val()!=""){
			$("input:checkbox[value='controlno']").attr("checked", true);
		}else{
			$("input:checkbox[value='controlno']").attr("checked", false);
		}
	});
	  
	  
});
function alertMessage(message, info, code, criteria, label){
	var url = "Error_Message?GL79ERRCODE="+code+"" + 
		"&criteria=" + criteria + "&label="+label+"";
	$.ajax({
			url: url,
			success: function(result) {
				//swal(message,result, info );
				//swal(message,result, info );
				swal(result.trim());
			}
		});
}



//Retrieve list of inactive record
function retrieveRecord(){

		var checked = $("input[name='optionsRadios']:checked").val()
		var ordernoFrom = "";
		var ordernoTo = "";
		var ctrlnoFrom = "";
		var ctrlTo = "";
		var date1 =  $("#orderDate1").val();
		var date2 = $("#orderDate2").val();
		var criteria = "";
	
		 if(checked =="orderno"){
			 criteria = "orderno";
			 ordernoFrom = $("#orderno1").val();
			 ordernoTo = $("#orderno2").val();
		 }
		 
		 if(checked =="controlno"){
			 criteria = "controlno";
			 ctrlnoFrom = $("#controlno1").val();
			 ctrlTo = $("#controlno2").val();
		 }
	
		 $("#inactiveList").DataTable().destroy();
		// loader("#inactivedata");
		$.get("Table_InactiveIssueMgtmt",{date1:date1, date2:date2, criteria:criteria,ordernoFrom:ordernoFrom, 
			ordernoTo:ordernoTo,ctrlnoFrom:ctrlnoFrom, ctrlTo:ctrlTo},function(data){

				$("#inactivedata").html(data);
				 serial =  $('#inactiveList').DataTable({
					    responsive: true
					});

		});
}




function getResubmit(){

	 var rows = $('#inactiveList').DataTable().rows({ 'search': 'applied' }).nodes();

	 if($('input[name="selectedValue[]"]:checked', rows).length>0){
		 $("#purge").removeAttr("disabled");
	 }else{
		  $("#purge").attr("disabled", "disabled");
	 }
  	  
}

