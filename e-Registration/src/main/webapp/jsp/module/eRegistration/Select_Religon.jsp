<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="com.ilmu.global.SelectStatement, java.util.List" %>
    
<%	
	List<SelectStatement> selectReligion= SelectStatement.selectReligion();
	
	String selected = "-";
	String output = "";

	for(SelectStatement i: selectReligion)
	{
		output += "<option value='" + i.getCode() + "' ";

		
		output += ">" + i.getCode() + " - " + i.getDesc() + "</option>";
	}
	
	out.println(output);
	
	
%>