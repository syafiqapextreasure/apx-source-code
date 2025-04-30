<%@page import="com.ilmu.foundation.SMD.SQLStatement"%>

	<%
	try {
		
		System.out.println("testing 123");
		String GL47SMD = request.getParameter("GL47SMD");
		System.out.println(GL47SMD);
		
		if(SQLStatement.CheckIfExist(GL47SMD))
		{
			System.out.println("rr");
			SQLStatement.deleteSMD(GL47SMD);

		out.println("ok"); 
		}
	} catch (Exception e) {
		out.println("error");
	}
	%>
