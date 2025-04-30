<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page
	import="java.util.List, java.util.ArrayList, java.util.HashMap,
				com.kmlink.ilmu.shared.pdf.*,
				com.kmlink.ilmu.shared.circulation.*"%>

<%
	String text = "";
	String action = request.getParameter("action");
	String vendor = request.getParameter("vendor");

	String reqno = request.getParameter("reqno");
	String orderno = request.getParameter("orderno");
	String msOrderMode = request.getParameter("msOrderMode");
	String issn = request.getParameter("issn");
	String dstart = request.getParameter("dstart");
	String dstop = request.getParameter("dstop");
	String issno = request.getParameter("issno");
	String eiscode = request.getParameter("eiscode");
	String reviewNo = request.getParameter("reviewNo");

	try {

		String mailNo = request.getParameter("mailNo");
		String accessionNo = request.getParameter("accessionNo");
		String patronId = request.getParameter("patronId");
		String title = request.getParameter("title");
		String callNo = request.getParameter("callNo");
		String dueDate = request.getParameter("dueDate");
		String lateBy = request.getParameter("lateBy");
		String fines = request.getParameter("fines");

		String liferayLogin = request.getParameter("sender");

		System.out.println("InsertGLMAILDocument mailNo: " + mailNo);
		System.out.println("InsertGLMAILDocument accessionNo: " + accessionNo);
		System.out.println("InsertGLMAILDocument patronId: " + patronId);
		System.out.println("InsertGLMAILDocument title: " + title);
		System.out.println("InsertGLMAILDocument callNo: " + callNo);
		System.out.println("InsertGLMAILDocument dueDate: " + dueDate);
		System.out.println("InsertGLMAILDocument lateBy: " + lateBy);
		System.out.println("InsertGLMAILDocument fines: " + fines);

		String filteredPatronId = patronId.substring(0, patronId.indexOf(','));
		String filteredPatronName = patronId.substring(patronId.lastIndexOf(",") + 1);

		System.out.println("InsertGLMAILDocument filteredPatronId: " + filteredPatronId);
		System.out.println("InsertGLMAILDocument filteredPatronName: " + filteredPatronName);

		saveMail.updateCICIRC(accessionNo, filteredPatronId);

		int mailno = saveMail.Get98VALUE("MAILNO");
		System.out.println(mailno + " Andy mailno");

		int newmailno = new Integer(mailno + 1);
		saveMail.updatingmailno(newmailno);

		text = Document.ER06(mailNo, accessionNo, filteredPatronId, filteredPatronName, title, callNo, dueDate,
				lateBy, fines);

		saveMail.SAVEMAIL(newmailno, liferayLogin, filteredPatronId, text, "ER06");

		out.println("ok");

	} catch (Exception e) {
		out.println("error");
	}
%>