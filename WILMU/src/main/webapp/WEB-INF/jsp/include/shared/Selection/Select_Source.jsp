<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="ISO-8859-1"%>
<%@ page import="com.wilmu.foundation.source.data.*, java.util.List" %>
    <select class='form-control' id='source' name='source'>
<%	
	List<GetFndSource> SourceList = GetFndSource.getBibSource();
	
	String selected = "-";
	String output = "";
	output += "<option value='0'>Please select</option>";
	for(GetFndSource i: SourceList)
	{
		output += "<option value='" + i.getCODE() + "' ";

		
		output += ">" + i.getCODE() + " - " + i.getDESCRIPTION() + "</option>";
	}
	out.println(output);
	
	
%>
</select>