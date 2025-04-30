<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="ISO-8859-1"%>
<%@ page import="com.wilmu.serials.serialordermode.data.GetSerialOrderMode,java.util.List" %>
    
<%
    List<GetSerialOrderMode> selectoserdermode = GetSerialOrderMode.GetSerialOrderModeCodeandDesc();
        	
        	String selected = "-";
        	String output = "";

        	for(GetSerialOrderMode i: selectoserdermode)
        	{
        		output += "<option value='" + i.getCode() + "' ";

        		
        		output += ">" + i.getCode() + " - " + i.getDesc() + "</option>";
        	}
        	
        	out.println(output);
    %>