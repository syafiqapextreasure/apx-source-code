<%@page import="com.ilmu.foundation.LibraryCalendar.SQLStatement"%>

<%
	try {
		String day = request.getParameter("holDay");
		String branch = request.getParameter("holBranch");
		String desc = request.getParameter("holDesc");
		String type = request.getParameter("holType");
		String allBranch = request.getParameter("allBranch");
		
		System.out.println(day + " day");
		System.out.println(branch + " branch");
		System.out.println(desc + " desc");
		System.out.println(type + " type");
		System.out.println(allBranch + " allBranch");
		
		if (allBranch.equals("true")) {
			SQLStatement.addWeekendAllBranch(day, desc, type);
			out.println("ok true");
		}else if(allBranch.equals("false")){
			SQLStatement.addWeekend(day, branch, desc, type);
			out.println("ok false");
		}

		//SQLStatement.addWeekend(day, branch, desc, type);
		//out.println("ok");

	} catch (Exception e) {
		out.println("error");
	}
%>
