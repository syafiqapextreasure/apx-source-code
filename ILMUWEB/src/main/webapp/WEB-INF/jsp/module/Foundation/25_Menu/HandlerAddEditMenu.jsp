<%@page import="java.util.List, com.ilmu.foundation.Menu.Menu"%>
<%@ page import="java.util.*, com.ilmu.utilities.query.*, com.ilmu.global.DateTime, com.ilmu.global.DateFormatter" %>

<%
	try {
		String ModuleName = request.getParameter("ModuleName");
		System.out.println("ModuleName >  "+ModuleName);
		
		String RootName = request.getParameter("RootName");
		System.out.println("RootName >  "+RootName);
		
		String MenuIndex = request.getParameter("MenuIndex");
		System.out.println("MenuIndex >  "+MenuIndex);
		
		String MenuDescription = request.getParameter("MenuDescription");
		System.out.println("MenuDescription >  "+MenuDescription);
		
		String ProgramID = request.getParameter("ProgramID");
		System.out.println("ProgramID >  "+ProgramID);
		
		String AccessLevel = request.getParameter("AccessLevel");
		System.out.println("AccessLevel >  "+AccessLevel);
		
		String userID = request.getParameter("userID");
		System.out.println("userID >  "+userID);
		
		String date = request.getParameter("date");
		System.out.println("date >  "+date);
		
		String action = request.getParameter("action");
		System.out.println("action >  "+action);
		
		boolean bSuccessful;
		if(action.equals("ADD")){
			 bSuccessful = Menu.CreateMenu(ModuleName, RootName, MenuIndex, MenuDescription, ProgramID, AccessLevel);
			 out.println("ok");
		}else if(action.equals("EDIT")){
			
			 String hidemodule = request.getParameter("hidemodule");
			 System.out.println("hidemodule >  "+hidemodule);
			
			 String hiderootname = request.getParameter("hiderootname");
			 System.out.println("hiderootname >  "+hiderootname);
			
			 String hideindex = request.getParameter("hideindex");
			 System.out.println("hideindex >  "+hideindex);
			
			 bSuccessful = Menu.updating(ModuleName, RootName, MenuIndex, MenuDescription, 
					 ProgramID, AccessLevel, hidemodule, hiderootname, hideindex);
			 out.println("ok");  
		}
		
	}catch (Exception e) {
		out.println("error");
	}
%>