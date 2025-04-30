<%@page import="com.ilmu.foundation.LibraryCalendar.SQLStatement"%>

<%
	try {
		String date = request.getParameter("holDate");
		String branch = request.getParameter("holBranch");
		String description = request.getParameter("holDesc");
		String type = request.getParameter("holType");

		SQLStatement.updateHoliday(date, branch, description, type);
		out.println("ok");

	} catch (Exception e) {
		out.println("error");
	}
%>