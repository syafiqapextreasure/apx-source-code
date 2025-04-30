<%@page import="java.util.List, com.ilmu.foundation.Flag.Flag "%>
<%@ page import="java.util.*, com.ilmu.utilities.query.*, com.ilmu.global.DateTime, com.ilmu.global.DateFormatter" %>

<%
	try {
		String inputFunction = request.getParameter("inputFunction");
		System.out.println("inputFunction >  "+inputFunction);
		
		String description = request.getParameter("description");
		System.out.println("description >  "+description);
		
		String value = request.getParameter("value");
		System.out.println("value >  "+value);
		
		String action = request.getParameter("action");
		System.out.println("action >  "+action);
		
		boolean bSuccessful;
		if(action.equals("ADD")){
			 bSuccessful = Flag.CreateFlag(inputFunction, description, value);
			 out.println("ok");
		}else if(action.equals("EDIT")){
			 bSuccessful = Flag.updating(inputFunction, description, value);
			 out.println("ok"); 
		}
		
	}catch (Exception e) {
		out.println("error");
	}
%>