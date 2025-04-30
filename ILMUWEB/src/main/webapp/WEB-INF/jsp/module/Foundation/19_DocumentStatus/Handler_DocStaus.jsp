<%@page import="com.ilmu.foundation.DocumentStatus.SQLStatement"%>

		<%
	try {
		
		System.out.println("testing 123");
		String deleteDoc = request.getParameter("deleteDoc");
		System.out.println(deleteDoc + " deleteDoc");
		
		if(SQLStatement.CheckIfExist(deleteDoc)){
			SQLStatement.deleteCodeTable(deleteDoc);

		out.println("ok"); 
		}
	} catch (Exception e) {
		out.println("error");
	}
	%>