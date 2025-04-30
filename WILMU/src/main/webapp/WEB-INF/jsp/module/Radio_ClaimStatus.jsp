<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="ISO-8859-1"%>
<%@ page import="com.wilmu.foundation.fndcode.data.GetFndCode,java.util.List" %>
<%
	String fcode = request.getParameter("claimStatVal");
	System.out.println("fcode"+fcode);
    List<GetFndCode> fcodelist = GetFndCode.getFndFcodeCodeandDesc(fcode);
        	String output = "";
        	for(GetFndCode i: fcodelist)
        	{
        		output += "<div class='col-sm-2 col-md-2'>";
        		output += "<label class='form-check-label'>";
        		output += "<input class='form-check-input' type='radio' name='radioclaimstat' id='radioclaimstat' value='" + i.getCode() + "'> ";
        		output += i.getDesc();
        		output += "</label>";
        		output += "</div>";
        	}
        	out.println(output);
    %>