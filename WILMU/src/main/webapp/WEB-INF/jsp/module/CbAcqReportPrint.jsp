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
        		output += "<div class='form-check'>";
        		output += "<input class='form-check-input' type='checkbox' value='acqprint' name = 'chkBoxAcqPrint' id='chkBoxAcqPrint'> ";
        		output += "<label class='form-check-label'> " +i.getDesc();
        		output += "</label>";
        		output += "</div>";

        	}
        		
        	out.println(output);

    %>