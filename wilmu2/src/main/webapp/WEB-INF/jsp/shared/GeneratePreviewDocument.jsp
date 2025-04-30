<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page
	import="java.util.List, java.util.ArrayList, java.util.HashMap,
				com.kmlink.ilmu.shared.pdf.*"%>



<%
	String senderUsername = (String) session.getAttribute("username");
	String text = "";
	String action = request.getParameter("action");
	String vendor = request.getParameter("vendor");
	String liferayLogin = request.getParameter("id");
	String reqno = request.getParameter("reqno");
	String orderno = request.getParameter("orderno");
	String msOrderMode = request.getParameter("msOrderMode");
//	String title = request.getParameter("title");
	String issn = request.getParameter("issn");
	String dstart = request.getParameter("dstart");
	String dstop = request.getParameter("dstop");
	String issno = request.getParameter("issno");
	String eiscode = request.getParameter("eiscode");
	String reviewNo = request.getParameter("reviewNo");

	
/* 	Library library = Library.getContactsOnly();
	Library2 hod = Library2.hodDetail();
	Library2 loginDetail = Library2.loginDetail(liferayLogin);
	Library getVendDetail = Library.getVendorDetailsBYCODE(vendor); */
	
/* 	List<odermanagement> claimncancel = odermanagement.detailletter(reqno);
	IssueManagement ceasedPublicationOrder = IssueManagement.cporderdetail2(orderno);
	List<IssueManagement> ceasedPublicationIssue = IssueManagement.cpissuedetail(orderno,issno, action);
	List<PurchaseAuthorisation> purautho = PurchaseAuthorisation.previewreqrecord(reqno);
	
	IssueManagement reason = IssueManagement.getEISREASON(eiscode); */
/*
	if(action.equals("1st Claim") || action.equals("2nd Claim") || action.equals("3rd Claim")){
		System.out.println("CLAIM");
		Library letterTitile = Library.lettersubject("ES11");
		text = Document.ES11(library, letterTitile, getVendDetail, hod, loginDetail, claimncancel, action);
	}else if(action.equals("cancel")){
		System.out.println("CANCEL");
		Library letterTitile = Library.lettersubject("ES12");
		text = Document.ES12(library, letterTitile, getVendDetail, hod, loginDetail, claimncancel, action);
	}else if(action.equals("previewMissingIssueClaims") || action.equals("saveMissingIssueClaims")){
		System.out.println("previewMissingIssueClaims");
 		if(msOrderMode.equals("60") || msOrderMode.equals("61") || msOrderMode.equals("62") || msOrderMode.equals("71") || msOrderMode.equals("72")){
			//ES16  add jer dulu
			Library letterTitile = Library.lettersubject("ES16");
			text = Document.ES16(letterTitile, library, getVendDetail, hod, loginDetail, action, orderno, title, 
					issn, dstart, dstop, ceasedPublicationOrder, ceasedPublicationIssue);
			//text =  Document.missingIssueClaimsES16(ceasedPublicationOrder, ceasedPublicationIssue, getVendDetail, purauthogetOfficeName, action, library, status);
		}else{
			//ES13
			Library letterTitile = Library.lettersubject("ES13");
			text =  Document.ES13(letterTitile, library, getVendDetail, hod, loginDetail, action, orderno, title, 
					issn, dstart, dstop, ceasedPublicationOrder, ceasedPublicationIssue);
		}
	}else if(action.equals("previewCeasedPublication") || action.equals("saveCeasedPublication")){ 
		System.out.println("previewCeasedPublication");
		Library letterTitile = Library.lettersubject("ES14");
		text = Document.ES14(letterTitile, library, getVendDetail, hod, loginDetail, action, orderno, title, 
				issn, dstart, dstop, ceasedPublicationOrder, ceasedPublicationIssue);
	}else if(action.equals("EIS")){
		System.out.println("EIS");
		Library letterTitile = Library.lettersubject("ES15");
		text = Document.ES15(letterTitile, library, getVendDetail, hod, loginDetail, action, orderno, title, issn, dstart, dstop, 
				ceasedPublicationOrder, ceasedPublicationIssue, reason);
	}else if(action.equals("checkin")){
		Library letterTitile = Library.lettersubject("ES17");
		text =  Document.ES17(letterTitile, library, getVendDetail, hod, loginDetail, action, orderno, title, 
				issn, dstart, dstop, ceasedPublicationOrder, ceasedPublicationIssue);
	}else if(action.equals("previewreviewListNo") || action.equals("printreviewListNo")){
		Library letterTitile = Library.lettersubject("ES26");
		text = Document.ES26(library, reviewNo, letterTitile, hod, loginDetail, purautho);
	} */
	
	
	
	/////FRO DISPLAY AND SAVE 
/* 	if (text!=null) {
		if(action.equals("1st Claim") || action.equals("2nd Claim") || action.equals("3rd Claim")){ */
			
			
	try{
		
		
 			String mailNo = request.getParameter("mailNo");
			String accessionNo = request.getParameter("accessionNo");
			String patronId = request.getParameter("patronId");
 			String title = request.getParameter("title");
			String callNo = request.getParameter("callNo");
			String dueDate = request.getParameter("dueDate");
 			String lateBy = request.getParameter("lateBy");
			String fines = request.getParameter("fines");
			
			String officetype = request.getParameter("officetype");

			
			String filteredPatronId = patronId.substring(0, patronId.indexOf(','));
			String filteredPatronName = patronId.substring(patronId.lastIndexOf(",") + 1);


			text = Document.ER06(mailNo, accessionNo, filteredPatronId, filteredPatronName, 
					title, callNo, dueDate, lateBy, fines, officetype ); 
		
				out.print(text);

			} catch (Exception e) {
				out.println("error");
			}

%>