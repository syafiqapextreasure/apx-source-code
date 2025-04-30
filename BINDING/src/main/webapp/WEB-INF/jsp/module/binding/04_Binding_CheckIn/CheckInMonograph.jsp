<%@page import="java.util.List, com.ilmu.binding.checkin.bindingCheckin"%>
<%@ page import="java.util.*, com.ilmu.utilities.query.*, com.ilmu.global.DateTime, com.ilmu.global.DateFormatter" %>

<%
	try {
		
		String liferayLogin = request.getParameter("liferayLogin");
		System.out.println("liferayLogin >  "+liferayLogin);
		
		String bindno = request.getParameter("bindno");
		System.out.println("bindno >  "+bindno);
		
		String accno = request.getParameter("accno");
		System.out.println("accno >  "+accno);
		
		//boolean bSuccessful;
		bindingCheckin.checkinMonograph(bindno, accno, liferayLogin);
		out.println("ok");
		
	}catch (Exception e) {
		out.println("error");
	}

%>