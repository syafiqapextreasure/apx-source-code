<%@page import="java.util.List, com.ilmu.foundation.GroupID.GroupID "%>
<%@ page import="java.util.*, com.ilmu.utilities.query.*, com.ilmu.global.DateTime, com.ilmu.global.DateFormatter" %>

<%
	try {
		String GroupId = request.getParameter("GroupId");
		System.out.println("GroupId >  "+GroupId);
		
		String GroupName = request.getParameter("GroupName");
		System.out.println("GroupName >  "+GroupName);
		
		int AccessLevel = Integer.parseInt(request.getParameter("AccessLevel"));
		System.out.println("AccessLevel >  "+AccessLevel);

		/* String date = request.getParameter("date");
		System.out.println("date >  "+date);
		
		String userID = request.getParameter("userID");
		System.out.println("userID >  "+userID); */
		
		String action = request.getParameter("action");
		System.out.println("action >  "+action);
		
		boolean bSuccessful;
		if(action.equals("ADD")){
			 bSuccessful = GroupID.CreateGroupID(GroupId, GroupName, AccessLevel);
			 out.println("ok");
		}else if(action.equals("EDIT")){
				bSuccessful = GroupID.updating(GroupId, GroupName, AccessLevel);
				out.println("ok");
		}
		
	}catch (Exception e) {
		out.println("error");
	}
%>