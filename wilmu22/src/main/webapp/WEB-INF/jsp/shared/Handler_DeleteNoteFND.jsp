<%@page import="com.kmlink.ilmu.shared.global.viewNote "%>

	<%
	try {
		String id = request.getParameter("id");
		
		viewNote.deleteNote(id);
		out.println("ok");
	} catch (Exception e) {
		out.println("error");
	}
	%>
