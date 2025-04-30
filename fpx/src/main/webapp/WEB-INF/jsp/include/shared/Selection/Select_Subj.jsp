<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="com.wilmu.foundation.generalsubj.data.*, java.util.List" %>
    
    
<%	
	List<GetFndGeneralSubj> sbjctlist = GetFndGeneralSubj.selectGSubj();
	
	String selected = "-";
	String output = "";
	for(GetFndGeneralSubj i: sbjctlist)
	{
		output += "<option value='" + i.getCode() + "' ";

		
		output += ">" + i.getCode() + " - " + i.getDesc() + "</option>";
	}
	
	out.println(output);
	
	
%>
<!-- </select>  previous code-->