
<%@page import="java.util.List, com.ilmu.foundation.ModuleAccessLevel.ModuleAccessLevel"%>
<%@ page import="java.util.*, com.ilmu.utilities.query.*, com.ilmu.global.DateTime" %>

<%
	try {
		
		String GroupID = request.getParameter("GroupID");
		System.out.println("GroupID >  "+GroupID);
		
		String ModuleName = request.getParameter("ModuleName");
		System.out.println("ModuleName >  "+ModuleName);
		
		int AccessLevel = Integer.parseInt(request.getParameter("AccessLevel"));
		System.out.println("AccessLevel >  "+AccessLevel);
		
		/* String date = request.getParameter("date");
		System.out.println("date >  "+date);
		
		String userID = request.getParameter("userID");
		System.out.println("userID >  "+userID); */
		
		String action = request.getParameter("action");
		System.out.println("action >  "+action);
		
		boolean bSuccessful = false;
		
		if(action.equals("ADD")){
			bSuccessful = ModuleAccessLevel.CreateFundAccountChart(GroupID, ModuleName, AccessLevel);
			System.out.println("rr" +bSuccessful);
			
			if(bSuccessful){
				out.println("ok");
			}else{
				out.println("fail");
			}
		}else if(action.equals("EDIT")){
			bSuccessful = ModuleAccessLevel.updating(GroupID, ModuleName, AccessLevel);
			if(bSuccessful){
				out.println("ok");
			}else{
				out.println("fail");
			}
		}
		//out.println("ok");
	} catch (Exception e) {
		out.println("error");
	}
	
%>