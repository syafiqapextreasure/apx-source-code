<%@page import="com.ilmu.foundation.BranchCode.SQLStatement"%>

	<%
	try {
		
		System.out.println("testing 123");
		String GL71BRNC = request.getParameter("GL71BRNC");
		System.out.println(GL71BRNC);
		
		if(SQLStatement.CheckIfExist(GL71BRNC))
		{
			System.out.println("rr");
			SQLStatement.deleteBranch(GL71BRNC);

		out.println("ok"); 
		}
	} catch (Exception e) {
		out.println("error");
	}
	%>
