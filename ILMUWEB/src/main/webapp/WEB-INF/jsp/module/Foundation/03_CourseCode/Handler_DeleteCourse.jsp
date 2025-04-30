<%@page import="com.ilmu.foundation.CourseCode.SQLStatement"%>

	<%
	try {
		
		System.out.println("testing 123");
		String GL12COURSE = request.getParameter("GL12COURSE");
		System.out.println(GL12COURSE);
		
		System.out.println("kkk"+SQLStatement.CheckIfExist(GL12COURSE));
		if(SQLStatement.CheckIfExist(GL12COURSE))
		{
			System.out.println("rr");
			SQLStatement.deleteCourse(GL12COURSE);

		out.println("ok"); 
		}
	} catch (Exception e) {
		out.println("error");
	}
	%>
