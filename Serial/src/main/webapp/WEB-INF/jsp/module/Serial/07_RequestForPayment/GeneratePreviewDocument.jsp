<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->

<title>Release for Circulation</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
</head>
<body>
	<div class="container">

		<div class="container">
			<h1>Creating PDF</h1>
			<p>Generating Letter... Please wait...</p>
		</div>
		
		<div style="margin-top: 80px;">
		</div>
	</div>
	<%
	%>
<script>
	$(document).ready(function() {
		var params = window.location.href.substr(window.location.href.indexOf('?'));
		params = params.replace(/&/g, '%26');
		
		var url =  "<%= request.getContextPath() %>"
					+ "/print?url="
					+ window.location.protocol + "//" 
					+ window.location.host
					+ "<%= request.getContextPath() %>" 
					+ "/GenerateDoc"
					+ params;
	alert(url);
		$.ajax({
			url: url,
			success: function(result) {
				result = result.replace(/^\s+|\s+$/g, '');
				$('#test').html(result);
			}
		});
		
		document.location.href = url;
	});
</script>
</body>
</html>

