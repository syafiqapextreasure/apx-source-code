<%@page import="com.ilmu.foundation.GroupID.GroupID "%>

	<%
	try {
		String code = request.getParameter("code");
		System.out.println("DELETE Group ID");

		if(GroupID.CheckIfExist(code)){
			System.out.println("rr");
			GroupID.deleteGroupID(code);
			out.println("ok");
		}
	} catch (Exception e) {
		out.println("error");
	}

	%>
