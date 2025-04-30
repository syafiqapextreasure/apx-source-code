<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="ISO-8859-1"%>
<%@ page import="com.wilmu.cataloging.global.GetOfficerEditList,java.util.List" %>
    
<%
    List<GetOfficerEditList> selectoffic = GetOfficerEditList.getCatOfficerEdit();
        	
        	String selected = "-";
        	String output = "";

        	for(GetOfficerEditList i: selectoffic)
        	{
        		output += "<option value='" + i.getCode() + "' ";

        		
        		output += ">" + i.getCode() + " - " + i.getDesc() + "</option>";
        	}
        	
        	out.println(output);
    %>