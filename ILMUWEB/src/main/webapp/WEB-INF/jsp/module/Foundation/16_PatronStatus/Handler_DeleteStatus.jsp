<%@page import="com.ilmu.foundation.PatronStatus.SQLStatement"%>

	<%
	try {
		
		System.out.println("testing 123");
		String GL08STAT = request.getParameter("GL08STAT");
		System.out.println(GL08STAT);
		
		if(SQLStatement.CheckIfExist(GL08STAT))
		{
			System.out.println("rr");
			SQLStatement.deleteStat(GL08STAT);

		out.println("ok"); 
		}
	} catch (Exception e) {
		out.println("error");
	}
	%>
