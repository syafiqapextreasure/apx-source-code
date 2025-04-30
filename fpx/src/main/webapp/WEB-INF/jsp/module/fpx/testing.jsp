
<%@ page import="com.wilmu.fpx.CSRF" %>

<html>
<head><title>First JSP</title></head>

	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script> 
	<script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.29.4/moment.min.js"></script> 
	
	
<body>

<script>

$(document).ready(function(){
	
	var today = new Date();

	alert($( "input[name='csrfToken']" ).val());
	
	var json = {"payload": {
		"subsysId": "PPK",
		"password": "pPk@2022WLm",
		"orderNo": "PPK0000000228",
		"description": "PPK0000000228",
		"txnTime": txnTime,
		"feeCode": "",
		"amount": "1.00"
					}};
					
	var jsonString = JSON.stringify(json);
	
	var urlUpdateRating = "https://fpxuat.ppj.gov.my/pay-api/pay/access";
	
	 var settings = {
				//'cache': false,
				//'dataType': "jsonp",
				"async": true,
				withCredentials: true,
				"crossDomain": true,
				"url": urlUpdateRating,
				"method": "POST",
				contentType: "application/json",
				 headers: {
				        'X-CSRF-TOKEN': $( "input[name='csrfToken']" ).val(),
				        'Access-Control-Allow-Origin': '*',
						'Access-Control-Allow-Methods': 'GET, POST,OPTIONS',
						'Access-Control-Allow-Credentials': 'true',
						'Access-Control-Max-Age': '3600',
						'Access-Control-Allow-Headers': 'Content-Type'
				    }
			}
	 
	 alert(settings)
			
		
			
			$.ajax(settings).done(function (response) {
				alert(response);
			});
	
});

</script>

 <%
// generate a random CSRF token
String csrfToken = CSRF.getToken();

// place the CSRF token in a cookie
javax.servlet.http.Cookie cookie = new javax.servlet.http.Cookie("csrf", csrfToken);
response.addCookie(cookie);
%>

<form action="/action" method="POST">
  <input type="text" name="csrfToken" value="<%= csrfToken %>"/>
</form>
</body>
</html>