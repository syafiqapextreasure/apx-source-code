<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="ISO-8859-1"%>
<%@ page import="com.wilmu.foundation.icat.data.*, java.util.List" %>
    
    
    <select class="form-control icat" id="icat" name="icat">
<%	
	List<GetFndIcat> icatlist = GetFndIcat.getIcatCodeNDesc();
	
	String selected = "-";
	String output = "";
	output += "<option value='0'>Please select</option>";
	for(GetFndIcat i: icatlist)
	{
		output += "<option value='" + i.getCode() + "' ";

		
		output += ">" + i.getCode() + " - " + i.getDesc() + "</option>";
	}
	
	out.println(output);
	
	
%>
</select>