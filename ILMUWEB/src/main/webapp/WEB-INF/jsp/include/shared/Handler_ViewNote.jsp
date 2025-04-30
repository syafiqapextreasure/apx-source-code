<%@page import="java.util.List, com.ilmu.global.Glnumb, com.ilmu.global.viewNote"%>
<%@ page import="java.util.*, com.ilmu.utilities.query.*, com.ilmu.global.DateTime, com.ilmu.global.DateFormatter" %>

<%
	try {
		
		String passval = request.getParameter("passval");
		System.out.println("passval >  "+passval);
		
		String notetitle = request.getParameter("notetitle");
		System.out.println("notetitle >  "+notetitle);
		
		String note = request.getParameter("note");
		System.out.println("note >  "+note);

		String viewprm = request.getParameter("viewprm");
		System.out.println("viewprm >  "+viewprm);

		String editprm = request.getParameter("editprm");
		System.out.println("editprm >  "+editprm);
		
		String login = request.getParameter("login");
		System.out.println("login >  "+login);
		
		String vendor = request.getParameter("vendor");
		System.out.println("vendor >  "+vendor);
		
		viewNote.saveNote("GL01", passval, notetitle, note, viewprm, editprm, login, vendor);
		out.println("ok");

	}catch (Exception e) {
		out.println("error");
	}

%>