<%@page import="com.ilmu.foundation.ItemCategory.SQLStatement"%>

	<%
	try {
		
		System.out.println("testing 123");
		String GL10ICAT = request.getParameter("GL10ICAT");
		System.out.println(GL10ICAT);
		
		if(SQLStatement.CheckIfExist(GL10ICAT))
		{
			System.out.println("rr");
			SQLStatement.deleteItemCat(GL10ICAT);

		out.println("ok"); 
		}
	} catch (Exception e) {
		out.println("error");
	}
	%>
