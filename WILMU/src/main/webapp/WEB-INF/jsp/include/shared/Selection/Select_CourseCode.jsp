<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="ISO-8859-1"%>
<%@ page import="com.wilmu.foundation.course.data.*, java.util.List" %>
    
    
    <select class="form-control course" id="course" name="course">
<%	
	List<GetFndCourse> courselist = GetFndCourse.GetFndCourseCodeandDesc();
	
	String selected = "-";
	String output = "";
	output += "<option value='0'>Please select</option>";
	for(GetFndCourse i: courselist)
	{
		output += "<option value='" + i.getCode() + "' ";

		
		output += ">" + i.getCode() + " - " + i.getDesc() + "</option>";
	}
	
	out.println(output);
	
	
%>
</select>