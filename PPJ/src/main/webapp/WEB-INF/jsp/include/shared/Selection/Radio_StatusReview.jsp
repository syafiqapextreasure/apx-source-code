<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="com.wilmu.global.GetFndDocumentstatusIn,java.util.List" %>
   
    
<%


    List<GetFndDocumentstatusIn> statuslist = GetFndDocumentstatusIn.GetFndDocumentstatusInCodeandDesc();
        	
        	String output = "";

        	for(GetFndDocumentstatusIn i: statuslist)
        	{
        		output += "<div class='col-sm-3 col-md-3'>";
        		output += "<label class='form-check-label'>";
        		output += "<input class='form-check-input' type='radio' name='radiostat' id='radiostat' value='" + i.getCode() + "'> ";
        		output += i.getDesc();
        		output += "</label>";
        		output += "</div>";

        	}
        		
        	out.println(output);
    %>