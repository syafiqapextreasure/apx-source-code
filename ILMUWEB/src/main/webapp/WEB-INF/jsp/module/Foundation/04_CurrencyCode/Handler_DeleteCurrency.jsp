<%@page import="com.ilmu.foundation.CurrencyCode.SQLStatement"%>

		<%
	try {
		
		System.out.println("testing 123");
		String GL24FOREX = request.getParameter("GL24FOREX");
		System.out.println(GL24FOREX);
		
		if(SQLStatement.CheckIfExist(GL24FOREX))
		{
			System.out.println("rr");
			SQLStatement.deleteCurrency(GL24FOREX);

		out.println("ok"); 
		}
	} catch (Exception e) {
		out.println("error");
	}
	%>
