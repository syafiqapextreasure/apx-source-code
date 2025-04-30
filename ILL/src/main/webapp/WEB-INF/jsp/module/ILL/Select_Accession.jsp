<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="com.ilmu.ill.IncomingRequest.IncomingRequest, java.util.List" %>
    
<%	
	String vsControl = request.getParameter("code");
	List<IncomingRequest> sqlAccession= IncomingRequest.LoadAccession(vsControl); 
	
	String selected = "-";
	String output = "";

	for(IncomingRequest i: sqlAccession)
	{
		String stat = i.getStatus();
		
		switch(stat){
        case "A":
        	stat = "Available";
	  		break;
        case "G":
        	stat = "ILL Request";
	  		break;
	    }
		output += "<option value='" + i.getAccession() + "' ";

		
		output += ">" + i.getAccession() + " - " + stat + "</option>";
	}
	
	out.println(output);
	
	
%>