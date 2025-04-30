<%@page import="com.ilmu.foundation.Flag.Flag "%>

	<%
	try {
		String code = request.getParameter("code");
		System.out.println("DELETE Flag");

		//if(GroupID.CheckIfExist(code)){
			Flag.deleteFlag(code);
			out.println("ok");
		//}
	} catch (Exception e) {
		out.println("error");
	}

	%>
