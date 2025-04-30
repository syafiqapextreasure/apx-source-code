$(document).ready(function() {
	  $.get("template",{MODULE:"Circulation/01_Charging",ACTION:"Charging.jsp"},function(data_title){
	   });
	  
	   $('#table-charging').DataTable({
	       responsive: true
	   });
	   $("#button-generateAccession").click(
			   function(){
			$("td.GL14NAME").html("test");
			$("td.GL14CATE").html("test");
			$("td.GL14STAT").html("test");
			$("td.GL14MEMDATE").html("test");
			$("td.GL14EXPDATE").html("test");
	   });
	  
});