<%@page import="java.util.List, com.ilmu.global.viewNote"%>
<%@ page import="java.util.*, com.ilmu.utilities.query.*, com.ilmu.global.DateTime, com.ilmu.global.DateFormatter" %>

<%
	try {
		
		String idnote = request.getParameter("idnote");
		System.out.println("idnote >  "+idnote);
		
		String notetitle = request.getParameter("notetitle");
		System.out.println("notetitle >  "+notetitle);
		
		String note = request.getParameter("note");
		System.out.println("note >  "+note);

		String viewprm = request.getParameter("viewprm");
		System.out.println("viewprm >  "+viewprm);

		String editprm = request.getParameter("editprm");
		System.out.println("editprm >  "+editprm);
		
		String vendor = request.getParameter("vendor");
		System.out.println("vendor >  "+vendor);
		
		
		viewNote.updatingnote(idnote, notetitle, note, viewprm, editprm, vendor);
		out.println("ok");

	}catch (Exception e) {
		out.println("error");
	}

%>