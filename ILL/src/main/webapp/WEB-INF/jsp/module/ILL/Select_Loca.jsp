<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="com.ilmu.global.globalmethod, java.util.List" %>
    
<%	
	List<globalmethod> getLoaction = globalmethod.getLocation();
	
	String selected = "-";
	String output = "";

	for(globalmethod i: getLoaction)
	{
		output += "<option value='" + i.getCode() + "' ";

		
		output += ">" + i.getCode() + " - " + i.getDesc() + "</option>";
	}
	
	out.println(output);
	
	
%>