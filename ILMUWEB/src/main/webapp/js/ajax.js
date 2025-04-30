$(document).ready(function() {
	$('#txtDetails1').blur(function(event) {
		//alert("test");
		var id = $('#txtDetails1').val();
		//alert("test 2");
		$.get('MD5', {
			txtDetails1 : password
		}, function(responseText) {
			//alert("test 3");
			$('#ajaxResponse').text(responseText);
		});
	});
});


$(document).ready(function() {
	$('#txtDetails').blur(function(event) {
		alert("test");
		var id = $('#txtDetails').val();
		//alert("test 2");
		$.get('JqueryServlet', {
			txtDetails : id
		}, function(responseText) {
			//alert("test 3");
			$('#ajaxResponse').text(responseText);
		});
	});
});

$(document).ready(function() {
	$('#txtDetails2').blur(function(event) {
		
		var name = $('#txtDetails2').val();
		
		$.get('AlphabetCheck', {
			txtDetails2 : name
		}, function(responseText) {
			//alert("test 3");
			$('#nameResponse').text(responseText);
		});
	});
});


$(document).ready(function() {
	$('#txtDetails9').blur(function(event) {
		//alert("test");
		var ic = $('#txtDetails9').val();
		//alert("test 2");
		$.get('NumericalCheck', {
			txtDetails9 : ic
		}, function(responseText) {
			//alert("test 3");
			$('#icResponse').text(responseText);
		});
	});
});


$(document).ready(function() {
	$('#txtDetails20').blur(function(event) {
		//alert("test");
		var date1 = $('#txtDetails19').val();
		//alert(date1);
		var date2 = $('#txtDetails20').val();
		//alert(date2);
		
		if(date2<date1){
			alert("The Date Enrolled is AFTER the Expiry Date.");
		}
		else{
			alert("Successful.");
		}
		/*$.get('../../../../DateCompare', {
			txtDetails19 : date1, txtDetails20 : date2
		}, function(responseText) {
			alert("test 3");
			$('#dateResponse').text(responseText);
		});*/
		
		
	});
});






