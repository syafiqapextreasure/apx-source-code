<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="ISO-8859-1"%>
<%@ page import="com.wilmu.foundation.location.data.*, java.util.List" %>
    <select class="form-control location" id="location" name="location">
<%	
	List<GetFndLocation> getLoaction = GetFndLocation.getLocaCodeNDesc();
	
	String selected = "-";
	String output = "";
	output += "<option value='0'>Please select</option>";
	for(GetFndLocation i: getLoaction)
	{
		output += "<option value='" + i.getCode() + "' ";

		
		output += ">" + i.getCode() + " - " + i.getDesc() + "</option>";
	}
	
	out.println(output);
	
	
%>
</select>