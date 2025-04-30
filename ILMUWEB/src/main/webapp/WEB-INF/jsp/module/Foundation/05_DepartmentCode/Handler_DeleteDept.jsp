<%@page import="com.ilmu.foundation.DepartmentCode.SQLStatement"%>

	<%
	try {
		
		System.out.println("testing 123");
		String GL11DEPT = request.getParameter("GL11DEPT");
		System.out.println(GL11DEPT);
		
		SQLStatement.deleteDept(GL11DEPT);
		out.println("ok"); 
		
	} catch (Exception e) {
		out.println("error");
	}
	%>
