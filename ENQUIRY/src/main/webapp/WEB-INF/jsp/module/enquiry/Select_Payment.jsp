<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="com.ilmu.global.SelectStatement, java.util.List" %>
    
<%	
	List<SelectStatement> selectbrnc = SelectStatement.selectpaymentData();
	
	String selected = "-";
	String output = "";

	for(SelectStatement i: selectbrnc)
	{
		output += "<option value='" + i.getCode() + "' ";

		
		output += ">" + i.getCode() + " - " + i.getDesc() + "</option>";
	}
	
	out.println(output);
	
	
%>