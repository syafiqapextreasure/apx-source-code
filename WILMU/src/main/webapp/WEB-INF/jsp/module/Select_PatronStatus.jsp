<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="ISO-8859-1"%>
<%@ page import="com.wilmu.foundation.patronstatus.data.GetFndPatronStatus,java.util.List" %>
    
<%
    List<GetFndPatronStatus> selectpatrstat = GetFndPatronStatus.getFndPatronStatusCodeandDesc();
        	
        	String selected = "-";
        	String output = "";

        	for(GetFndPatronStatus i: selectpatrstat)
        	{
        		output += "<option value='" + i.getCode() + "' ";

        		
        		output += ">" + i.getCode() + " - " + i.getDesc() + "</option>";
        	}
        	
        	out.println(output);
    %>