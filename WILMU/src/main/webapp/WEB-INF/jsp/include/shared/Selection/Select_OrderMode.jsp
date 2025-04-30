<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="ISO-8859-1"%>
<%@ page import="com.wilmu.foundation.acqmode.data.GetFndAcqmode,java.util.List" %>
    <select class="form-control" id="acqMode" name="acqMode">
<%
    List<GetFndAcqmode> selectordermode = GetFndAcqmode.GetFndAcqmodeCodeandDesc();
        	
        	String selected = "-";
        	String output = "";
        	
        	output += "<option value='Please select'>Please select</option>";

        	for(GetFndAcqmode i: selectordermode)
        	{
        		output += "<option value='" + i.getCode() + "' ";

        		
        		output += ">" + i.getCode() + " - " + i.getDesc() + "</option>";
        	}
        	
        	out.println(output);
    %>
    </select>