<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="com.wilmu.foundation.fndcode.data.GetFndCode,java.util.List" %>
    
<%	
	String code = "P";
    List<GetFndCode> selectpaycode = GetFndCode.getFndFcodeCodeandDesc(code);
        	

        	String selected = "-";
        	String output = "";

        	for(GetFndCode i: selectpaycode)
        	{
        		output += "<option value='" + i.getCode() + "' ";

        		
        		output += ">" + i.getCode() + " - " + i.getDesc() + "</option>";
        	}
        	
        	out.println(output);
    %>