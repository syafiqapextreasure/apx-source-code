<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="ISO-8859-1"%>
<%@ page import="com.wilmu.foundation.generalsubj.data.GetFndGeneralSubj, java.util.List" %>
    
<%	
	List<GetFndGeneralSubj> getSubj= GetFndGeneralSubj.selectGSubj();
	
	String selected = "-";
	String output = "";

	for(GetFndGeneralSubj i: getSubj)
	{
		output += "<option value='" + i.getCode() + "' ";

		
		output += ">" + i.getCode() + " - " + i.getDesc() + "</option>";
	}
	
	out.println(output);
	
	
%>