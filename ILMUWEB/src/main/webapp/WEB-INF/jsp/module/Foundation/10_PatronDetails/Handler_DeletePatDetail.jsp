<%@page import="com.ilmu.foundation.PatronDetails.SQLStatement"%>

	<%
	try {
		
		System.out.println("testing 123");
		String GL14PATR = request.getParameter("GL14PATR");
		System.out.println(GL14PATR);
		
		if(SQLStatement.CheckIfExist(GL14PATR))
		{
			System.out.println("rr");
			SQLStatement.deletePatDetail(GL14PATR);

		out.println("ok"); 
		}
	} catch (Exception e) {
		out.println("error");
	}
	%>
