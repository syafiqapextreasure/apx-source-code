<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<!DOCTYPE html>

<html>
<head>
<meta charset="ISO-8859-1">
<meta name="_csrf" content="4bfd1575-3ad1-4d21-76yh-5ygfuyd9f86721"/>
    <meta name="_csrf_header" content="X-CSRF-TOKEN"/>
    
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script> 
	<script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.29.4/moment.min.js"></script>
	
	 
	
<title>Insert title here</title>

<script>
$(function () {
    var token = $("meta[name='_csrf']").attr("content");
    var header = $("meta[name='_csrf_header']").attr("content");
    $(document).ajaxSend(function(e, xhr, options) {
        xhr.setRequestHeader(header, token);
        
        alert(xhr.setRequestHeader(header, token))
    });
});

</script>
</head>
<body>

</body>
</html>