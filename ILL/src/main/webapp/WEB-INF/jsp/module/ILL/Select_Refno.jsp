<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="com.ilmu.global.globalmethod, java.util.List" %>
    
<%	
	List<globalmethod> selectiillist = globalmethod.LoadReferenceNo();
	
	String selected = "-";
	String output = "";

	for(globalmethod i: selectiillist)
	{
		output += "<option value='" + i.getCode() + "' ";

		
		output += ">" + i.getCode() + "</option>";
	}
	
	out.println(output);
	
	
%>