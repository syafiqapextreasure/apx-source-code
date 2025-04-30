/**
 * 
 */

$(document).ready(function() {
	  $.get("template",{MODULE:"Circulation/03_Discharging",ACTION:"Discharging.jsp"},function(data_title){
	   });
	  
	   $('#table-charging').DataTable({
	       responsive: true
	   });
	   $("#GL14PATR").focus(function(){
			$("td.GL14NAME").html("");
			$("td.GL14CATE").html("");
			$("td.GL14STAT").html("");
			$("td.GL14MEMDATE").html("");
			$("td.GL14EXPDATE").html("");
	   });
	  $("#CT03DOCNO").focusout(function(){
			  var CT03DOCNO = this.value;
				var url = "Handler_Charging?CT03DOCNO=" + CT03DOCNO;
				$.get(url,function(data){
					$("#table-charging").html(data);
					
				/*$("#indicator").html(data);*/
				});	
			});
	  $("#GL14PATR").focusout(function(){
		  var GL14PATR = this.value;
			var url = "Handler_PatronDetails?GL14PATR=" + GL14PATR;
			$.get(url,function(data){
				var details = data.trim().split(",");
				$("td.GL14NAME").append(' <span>'+details[1]+'</span>');
				$("td.GL14CATE").append(' <span>'+details[2]+'</span>');
				$("td.GL14STAT").append(' <span>'+details[5]+'</span>');
				$("td.GL14MEMDATE").append(' <span>'+details[3]+'</span>');
				$("td.GL14EXPDATE").append(' <span>'+details[4]+'</span>');
				
			/*$("#indicator").html(data);*/
			});	
		});
});