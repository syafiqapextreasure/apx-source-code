/*$(document).ready(function() {
	alert("test");
})*/

function noteCheck(id){
	$.get("GetTolNote",{
		idpatr : id,
		}, 
 	function(data,status){
			var total = data.trim();
			if(total >= 1){
				messageBox("148","","");
			}
		
		//swal("This Patron ID has been attached with note(s) or instruction(s). Click on 'Patron Note' button to achieve the contents.");
 	}).fail(function(data){
		swal("fail");
	})
}

function messageBox(code, criteria, label){
	var url = "Error_Page?GL79ERRCODE="+code+"" +
    "&criteria=" + criteria + "&label="+label+"";
    //alert(url);
    $.ajax({
          url: url,
          success: function(result) {
               swal(result); 
          }
    });
}
	