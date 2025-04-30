<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="ISO-8859-1"%>
<%
	String[] indicatorvalue = {" ","#","0","1","2","3","4","5","6","7","8","9"};
	for(String val : indicatorvalue){
		out.println("<option value = '"  + val + "'>" + val +"</option>");
	}
%>