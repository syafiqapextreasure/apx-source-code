	<%@ page import="com.ilmu.foundation.CourseCode.*, com.ilmu.foundation.*, java.util.List" %>
	
	<%
	
	
	System.out.println("Masuk Handler Add Currency");
	
	 String GL12COURSE = request.getParameter("GL12COURSE");
   	 String GL12DESC = request.getParameter("GL12DESC");
	
   	SQLStatement.AddCourse(GL12COURSE, GL12DESC);
	
	%>