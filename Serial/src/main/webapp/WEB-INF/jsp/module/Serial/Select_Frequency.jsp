<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="com.ilmu.global.*, java.util.List" %>
    
<%	
	List<Frequency> FrequencyList = Frequency.getFrequency();
	
	String selected = "-";
	String output = "";

	for(Frequency i: FrequencyList)
	{
		output += "<option value='" + i.getCODE() + "' ";

		
		output += ">" + i.getCODE() + " - " + i.getDESCRIPTION() + "</option>";
	}
	
	out.println(output);
	
	
%>