<%@page import="com.ilmu.global.viewNote, com.ilmu.foundation.global.CheckIfExist"%>

	<%
	try {
		
		System.out.println("gettt");
		String id = request.getParameter("idpatr");
		System.out.println("idddddd :" +id);
		
		int total = viewNote.CheckIfExist(id);
		out.println(total); 
		
	} catch (Exception e) {
		out.println("error");
	}

%>
