<%@page import="java.util.List,com.ilmu.binding.claim.Claims"%>
<%@ page import="java.util.*, com.ilmu.utilities.query.*, com.ilmu.global.DateTime, com.ilmu.global.DateFormatter" %>

<%
	try {
		
		String liferayLogin = request.getParameter("liferayLogin");
		System.out.println("liferayLogin >  "+liferayLogin);
		
		String bindno = request.getParameter("bindno");
		System.out.println("bindno >  "+bindno);
		
		int total = Integer.parseInt(request.getParameter("total"));
		System.out.println("total >  "+total);
		
		

		Claims.claimBind(liferayLogin, bindno, total);
		out.println("ok");

		
		
	}catch (Exception e) {
		out.println("error");
	}

%>