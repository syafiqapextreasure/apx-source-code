<%@page import="com.ilmu.foundation.GeneralSubject.SQLStatement"%>

	<%
	try {
		
		System.out.println("testing 123");
		String GL54SUBJSTA = request.getParameter("GL54SUBJSTA");
		System.out.println(GL54SUBJSTA);
		
		SQLStatement.deleteSubj(GL54SUBJSTA);
		out.println("ok"); 
		
	} catch (Exception e) {
		out.println("error");
	}
	%>
