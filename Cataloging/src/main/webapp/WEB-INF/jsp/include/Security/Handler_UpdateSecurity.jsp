<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="com.ilmu.global.connection.DBConnection, 
					com.ilmu.cataloging.Bibliography.*, com.ilmu.utilities.query.*,
					java.util.List, 
					com.ilmu.global.*,java.text.*,java.util.*"%>

<%
String ctrlno = request.getParameter("ctrlno");
String status = request.getParameter("status");

boolean update = Bibliography.updateStatus(ctrlno, status);

if(update){
	out.println("successful");
}else{
	out.println("fail");
}
%>

	
	