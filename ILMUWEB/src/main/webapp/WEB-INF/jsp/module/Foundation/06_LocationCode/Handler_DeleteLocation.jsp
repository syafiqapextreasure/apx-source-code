<%@page import="com.ilmu.foundation.LocationCode.SQLStatement"%>

	<%
	try {
		
		System.out.println("testing 123");
		String GL05LOCA = request.getParameter("GL05LOCA");
		System.out.println(GL05LOCA);
		
		SQLStatement.deleteLoca(GL05LOCA);
		out.println("ok"); 
		
	} catch (Exception e) {
		out.println("error");
	}
	%>
