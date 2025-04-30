$(document).ready(function() {
	$('#GL71BRNC').blur(function(event) {
		//alert("test");
		var id = $('#GL71BRNC').val();
		//alert("test 2");
		$.get('JqueryServlet', {
			GL71BRNC : id
		}, function(responseText) {
			//alert("test 3");
			$('#ajaxResponse').text(responseText);
		});
	});
});



