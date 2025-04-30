<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="com.ilmu.global.globalmethod, java.util.List" %>
    
<%	
	List<globalmethod> getCondition = globalmethod.getCondition();
	
	String selected = "-";
	String output = "";

	for(globalmethod i: getCondition)
	{
		output += "<option value='" + i.getCode() + "' ";

		
		output += ">" + i.getCode() + " - " + i.getDesc() + "</option>";
	}
	
	out.println(output);
	
	
%>
