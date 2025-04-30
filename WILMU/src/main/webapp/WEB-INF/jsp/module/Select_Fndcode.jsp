<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="ISO-8859-1"%>
<%@ page import="com.wilmu.foundation.fndcode.data.GetFndCode,java.util.List" %>
    
<%


	String fcode = request.getParameter("fcode");
	System.out.println("fcode"+fcode);


    List<GetFndCode> fcodelist = GetFndCode.getFndFcodeCodeandDesc(fcode);
        	
        	String selected = "-";
        	String output = "";

        	for(GetFndCode i: fcodelist)
        	{
        		output += "<option value='" + i.getCode() + "' ";

        		
        		output += ">" + i.getCode() + " - " + i.getDesc() + "</option>";
        	}
        	
        	out.println(output);
    %>