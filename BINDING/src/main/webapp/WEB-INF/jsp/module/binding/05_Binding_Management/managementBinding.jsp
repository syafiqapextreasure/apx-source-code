<%@page import="java.util.List,com.ilmu.binding.bindManagement.bindManegement"%>
<%@ page import="java.util.*, com.ilmu.utilities.query.*, com.ilmu.global.DateTime, com.ilmu.global.DateFormatter" %>

<%
	try {
		
		String liferayLogin = request.getParameter("liferayLogin");
		System.out.println("liferayLogin >  "+liferayLogin);
		
		String bindno = request.getParameter("bindno");
		System.out.println("bindno >  "+bindno);
		
		String accno = request.getParameter("accno");
		System.out.println("accno >  "+accno);
		
		String type = request.getParameter("type");
		System.out.println("type >  "+type);
		
		int total = Integer.parseInt(request.getParameter("total"));
		System.out.println("total >  "+total);
		
		String order = request.getParameter("order");
		System.out.println("order >  "+order);
		

		bindManegement.managementBind(liferayLogin, bindno, accno, type, total, order);
		out.println("ok");

		
		
	}catch (Exception e) {
		out.println("error");
	}

%>