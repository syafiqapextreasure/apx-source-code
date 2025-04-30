<%@page import="com.ilmu.foundation.SerialFrequency.SQLStatement"%>

		<%
	try {
		
		System.out.println("testing 123");
		String GL49FREQ = request.getParameter("GL49FREQ");
		System.out.println(GL49FREQ);
		
		if(SQLStatement.CheckIfExist(GL49FREQ))
		{
			System.out.println("rr");
			SQLStatement.deleteSerial(GL49FREQ);

		out.println("ok"); 
		}
	} catch (Exception e) {
		out.println("error");
	}
	%>
