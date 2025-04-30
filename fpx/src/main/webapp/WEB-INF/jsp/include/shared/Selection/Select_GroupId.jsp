<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="com.wilmu.foundation.groupid.data.GetFndGroupId,java.util.List" %>
    
<%




    List<GetFndGroupId> grpid = GetFndGroupId.getFndGroupId();
        	
        	String selected = "-";
        	String output = "";

        	for(GetFndGroupId i: grpid)
        	{
        		output += "<option value='" + i.getCode() + "' ";

        		
        		output += ">" + i.getCode() + " - " + i.getDesc().toUpperCase() + "</option>";
        	}
        	
        	out.println(output);
    %>