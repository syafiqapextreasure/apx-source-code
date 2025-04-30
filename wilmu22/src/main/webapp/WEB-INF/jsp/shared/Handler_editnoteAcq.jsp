<%@page import="java.util.List, com.kmlink.ilmu.shared.global.viewNote"%>
<%@ page import="java.util.*, com.kmlink.ilmu.shared.utilities.query.*, com.kmlink.ilmu.shared.global.DateTime, com.kmlink.ilmu.shared.global.DateFormatter" %>

<%
	try {
		String idnote = request.getParameter("idnote");
		
		String notetitle = request.getParameter("notetitle");
		
		String note = request.getParameter("note");

		String viewprm = request.getParameter("viewprm");

		String editprm = request.getParameter("editprm");
		
		String vendor = request.getParameter("vendor");
		
		viewNote.updatingnote(idnote, notetitle, note, viewprm, editprm, vendor);
		out.println("ok");

	}catch (Exception e) {
		out.println("error");
	}

%>