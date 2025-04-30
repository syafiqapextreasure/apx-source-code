
<%@ page import="com.wilmu.fpx.CSRF" %>





<html>
<head><title>Payment</title></head>

	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script> 
	<script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.29.4/moment.min.js"></script> 
	
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/Pay/PaymentProcess.js"></script>
	
	
<body>

<%--  <%
// generate a random CSRF token
String csrfToken = CSRF.getToken();

// place the CSRF token in a cookie
javax.servlet.http.Cookie cookie = new javax.servlet.http.Cookie("csrf", csrfToken);
response.addCookie(cookie);
%> --%>



<form id="confirm" name="confirm" method="post" action="https://fpxuat.ppj.gov.my/pay/pay" >
	 <%-- <input type="hidden" name="csrfToken" value="<%= csrfToken %>"/> --%>
	 
		<table>
		<tr> <td> 
		 <label style="display:none"></label>
		 <input type="hidden"  name="token" value=""/> 
		 </td>
		</tr>
		</table>
		
		<button style="display:none" type="submit" id="submitBtn">Submit Form</button>
	</form>
</body>
</html>