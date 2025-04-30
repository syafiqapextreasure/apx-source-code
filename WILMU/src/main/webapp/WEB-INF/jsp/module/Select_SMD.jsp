<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="ISO-8859-1"%>
<%@ page import="com.wilmu.foundation.smd.data.*, java.util.List" %>
<%	
	List<GetFndSmd> smdlist = GetFndSmd.getFndSmdCodeandDesc();
	String selected = "-";
	String output = "";
	for(GetFndSmd i: smdlist)
	{
		output += "<option value='" + i.getCode() + "' ";
		output += ">" + i.getCode() + " - " + i.getDesc() + "</option>";
	}
	out.println(output);
%>