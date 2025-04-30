<%@page import="com.ilmu.foundation.CodeTable.SQLStatement"%>

		<%
	try {
		
		System.out.println("testing 123");
		String CODE = request.getParameter("CODE");
		String FCODE = request.getParameter("FCODE");
		System.out.println(CODE);
		System.out.println(FCODE);
		
		if(SQLStatement.CheckIfExist(CODE,FCODE))
		{
			System.out.println("Masukk takkk?");
			System.out.println("CODE 2 =" + CODE);
			System.out.println("FCODE 2 =" + FCODE);
			SQLStatement.deleteCodeTable(CODE,FCODE);

		out.println("ok"); 
		}
	} catch (Exception e) {
		out.println("error");
	}
	%>
