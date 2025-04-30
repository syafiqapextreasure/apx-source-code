<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="ISO-8859-1"%>
<%@ page import="com.wilmu.foundation.documentstatus.data.GetFndDocumentstatus,java.util.List" %>
    
<%
    List<GetFndDocumentstatus> selectdocstatus = GetFndDocumentstatus.GetFndDocumentstatusCodeandDesc();
        	
        	String selected = "-";
        	String output = "";

        	for(GetFndDocumentstatus i: selectdocstatus)
        	{
        		output += "<option value='" + i.getCode() + "' ";

        		
        		output += ">" + i.getCode() + " - " + i.getDesc() + "</option>";
        	}
        	
        	out.println(output);
    %>