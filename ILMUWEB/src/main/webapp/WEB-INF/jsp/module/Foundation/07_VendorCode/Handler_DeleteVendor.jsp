<%@page import="com.ilmu.foundation.VendorCode.SQLStatement"%>

		<%
	try {
		
		System.out.println("testing 123");
		String GL39CODE = request.getParameter("GL39CODE");
		System.out.println(GL39CODE);
		
		
		System.out.println("kkk"+SQLStatement.CheckIfExist(GL39CODE));
		if(SQLStatement.CheckIfExist(GL39CODE))
		{
			System.out.println("rr");
			SQLStatement.deleteVendor(GL39CODE);

		out.println("ok"); 
		}
	} catch (Exception e) {
		out.println("error");
	}
	%>
