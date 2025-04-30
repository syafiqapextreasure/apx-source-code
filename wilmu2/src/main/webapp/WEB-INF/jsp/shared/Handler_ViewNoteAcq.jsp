<%@page import="java.util.List, com.kmlink.ilmu.shared.global.Glnumb, com.kmlink.ilmu.shared.global.viewNote"%>
<%@ page import="java.util.*, com.kmlink.ilmu.shared.utilities.query.*, com.kmlink.ilmu.shared.global.DateTime, com.kmlink.ilmu.shared.global.DateFormatter" %>

<%
	try {
		String passval = request.getParameter("passval");
		
		String notetitle = request.getParameter("notetitle");
		
		String note = request.getParameter("note");

		String viewprm = request.getParameter("viewprm");

		String editprm = request.getParameter("editprm");
		
		String login = request.getParameter("login");
		
		String vendor = request.getParameter("vendor");
		
		viewNote.saveNote("AC01", passval, notetitle, note, viewprm, editprm, login, vendor);
		out.println("ok");

	}catch (Exception e) {
		out.println("error");
	}

%>