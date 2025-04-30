<%@page import="com.ilmu.global.viewNote "%>

	<%
	try {
		String id = request.getParameter("id");
		
		viewNote.deleteNote(id);
		out.println("ok");
	} catch (Exception e) {
		out.println("error");
	}
	%>
