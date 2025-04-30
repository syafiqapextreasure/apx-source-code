<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="com.ilmu.global.Source, java.util.List" %>
       <option value="">Please Select</option> 
<%	
	List<Source> SourceList = Source.getBibSource();
	
	String selected = "-";
	String output = "";

	for(Source i: SourceList)
	{
		output += "<option value='" + i.getCODE() + "' ";

		
		output += ">" + i.getCODE() + " - " + i.getDESCRIPTION() + "</option>";
	}
	
	out.println(output);
	
	
%>