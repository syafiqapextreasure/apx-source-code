	<%@ page import="com.ilmu.foundation.CodeTable.*, com.ilmu.foundation.*, java.util.List" %>
	
	<%
	
	
	 System.out.println("Masuk Handler Add Code Table");
	 
	 String FCODE=request.getParameter("FCODE");
	 String FNAME=request.getParameter("FNAME");
	 String CODE=request.getParameter("CODE");
	 String DESCRIPTION=request.getParameter("DESCRIPTION");
	
	 SQLStatement.AddCodeTable(FCODE, FNAME, CODE, DESCRIPTION);
	 out.println("ok");
	%>