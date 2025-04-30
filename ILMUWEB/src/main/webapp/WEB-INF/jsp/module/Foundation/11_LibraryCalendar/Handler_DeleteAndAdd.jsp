<%@page import="com.ilmu.foundation.LibraryCalendar.SQLStatement"%>

<%
	try {
		/* String date = request.getParameter("holDate");
		String branch = request.getParameter("holBranch");
		String description = request.getParameter("holDesc");
		String type = request.getParameter("holType");

		SQLStatement.deleteHoliday(date, branch, description, type);
		out.println("ok"); */
		String oldDate = request.getParameter("oldDate");
		String branch = request.getParameter("branch");
		String type = request.getParameter("type");
		String title = request.getParameter("title");
		String allBranch = request.getParameter("allBranch");
		String newDate = request.getParameter("newDate");
		
		if(type.equals("event")){
			//SQLStatement.deleteEvent(oldDate, branch, type, title, allBranch);
			SQLStatement.deleteEvent(oldDate, branch, title);
			//SQLStatement.addEvent(newDate, branch, type, title, allBranch);
			SQLStatement.addEvent(newDate, branch, title);
			out.println("ok");
		}else{
			SQLStatement.delete(oldDate, branch, type, title, allBranch);
			SQLStatement.add(newDate, branch, type, title, allBranch);
			out.println("ok");
		}
		
		

	} catch (Exception e) {
		out.println("error");
	}
%>