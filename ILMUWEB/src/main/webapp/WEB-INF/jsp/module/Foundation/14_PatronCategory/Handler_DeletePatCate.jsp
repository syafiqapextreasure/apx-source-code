<%@page import="com.ilmu.foundation.PatronCategory.SQLStatement"%>

	<%
	try {
		
		System.out.println("testing 123");
		String GL07CATE = request.getParameter("GL07CATE");
		System.out.println(GL07CATE);
		
		if(SQLStatement.CheckIfExist(GL07CATE))
		{
			System.out.println("rr");
			SQLStatement.deletePatCat(GL07CATE);

		out.println("ok"); 
		}
	} catch (Exception e) {
		out.println("error");
	}
	%>
