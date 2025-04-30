<%@page import="com.ilmu.foundation.ModuleAccessLevel.ModuleAccessLevel"%>

	<%
	try {
		String d1 = request.getParameter("d1");
		String d2 = request.getParameter("d2");
		System.out.println("DELETE AccessLevel");
		//System.out.println(controlNo);

			ModuleAccessLevel.deleteAccessLevel(d1, d2);
			out.println("ok");

	} catch (Exception e) {
		out.println("error");
	}
	/* try {
		
		System.out.println("DELETE FundAccountChart");
		String fundcode = request.getParameter("fund");
		System.out.println(fundcode);
		
		if(FundAccountChart.CheckIfExist(fundcode)){
			System.out.println("rr");
			///SQLStatement.deleteSMD(GL47SMD);

		out.println("ok"); 
		}
	} catch (Exception e) {
		out.println("error");
	} */
	%>
