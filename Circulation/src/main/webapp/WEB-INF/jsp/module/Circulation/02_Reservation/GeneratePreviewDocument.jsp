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
		String action = request.getParameter("action");
	System.out.println("Action" + action);
		if(action.equals("acknowledgement")){
			System.out.println("A");
	%>
	<script>
	$(document).ready(function() {
		var params = window.location.href.substr(window.location.href.indexOf('?'));
		
		// replace the "&" with "--" so that the params will not be taken
		/* params = params.replace(/&/g, '{}'); */
		params = params.replace(/&/g, '%26');
		//params = params.replace(/&/g, '%26');
		var url =  "<%= request.getContextPath() %>"
					+ "/print?url="
					+ window.location.protocol + "//" 
					+ window.location.host
					+ "<%= request.getContextPath() %>" 
					+ "/Print_Acknowledgement"
					+ params; 
	
		$.ajax({
			// Title
			url: url,
			success: function(result) {
				// TRIM any new line that exists in front or behind
			
				result = result.replace(/^\s+|\s+$/g, '');
				$('#test').html(result);
			}
		});
		
		document.location.href = url;
	});
</script>
<%
		}else if(action.equals("reserved")){
			System.out.println("A45");
	%>
	<script>
	$(document).ready(function() {
		var params = window.location.href.substr(window.location.href.indexOf('?'));
		
		// replace the "&" with "--" so that the params will not be taken
		/* params = params.replace(/&/g, '{}'); */
		params = params.replace(/&/g, '%26');
		//params = params.replace(/&/g, '%26');
		var url =  "<%= request.getContextPath() %>"
					+ "/print?url="
					+ window.location.protocol + "//" 
					+ window.location.host
					+ "<%= request.getContextPath() %>" 
					+ "/Print_CancellationLetter"
					+ params; 

		$.ajax({
			// Title
			url: url,
			success: function(result) {
				// TRIM any new line that exists in front or behind
			
				result = result.replace(/^\s+|\s+$/g, '');
				$('#test').html(result);
			}
		});
		
		document.location.href = url;
	});
</script>

	<%
			
		}else{
			System.out.println("A1");
	%>
<script>
	$(document).ready(function() {
		var params = window.location.href.substr(window.location.href.indexOf('?'));
		
		// replace the "&" with "--" so that the params will not be taken
		/* params = params.replace(/&/g, '{}'); */
		params = params.replace(/&/g, '%26');
		//params = params.replace(/&/g, '%26');
		var url =  "<%= request.getContextPath() %>"
					+ "/print?url="
					+ window.location.protocol + "//" 
					+ window.location.host
					+ "<%= request.getContextPath() %>" 
					+ "/GenerateDoc"
					+ params; 

		$.ajax({
			// Title
			url: url,
			success: function(result) {
				// TRIM any new line that exists in front or behind
			
				result = result.replace(/^\s+|\s+$/g, '');
				$('#test').html(result);
			}
		});
		
		document.location.href = url;
	});
</script>
<%
		}
%>
</body>
</html>

