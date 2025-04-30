<%@page import="com.ilmu.foundation.LibraryCalendar.SQLStatement"%>

<%
	try {
		String date = request.getParameter("evntDate");
		String branch = request.getParameter("evntBranch");
		String desc = request.getParameter("evntDesc");

		SQLStatement.deleteEvent(date, branch, desc);
		out.println("ok");

	} catch (Exception e) {
		out.println("error");
	}
%>