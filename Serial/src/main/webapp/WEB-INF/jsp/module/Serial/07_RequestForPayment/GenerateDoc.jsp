<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- Perform Search -->
<%@ page
	import="java.util.List, java.util.ArrayList, java.util.HashMap,
				com.ilmu.cataloging.PDF.*, 
				com.ilmu.cataloging.template.*,
				com.ilmu.serial.RequestForPayment.*,
				com.ilmu.serial.RecordForPayment.*"%>

<%
System.out.println("in");
	// Store execution result
	String text = "";

	/* Get the parameters to perform search */
	// Mandatory Field
/* 	String criteria = request.getParameter("criteria");
	String textValue = request.getParameter("textValue");
	String vendor = request.getParameter("vendor");
	String startDate = request.getParameter("startDate");
	String stopDate = request.getParameter("stopDate");
	String ref = request.getParameter("ref");
	String groupList = request.getParameter("groupList");
	String letterID = request.getParameter("sLetterID");
	String voucherdate = request.getParameter("voucherdate");
	String voucherno = request.getParameter("voucherno");
	String chequedate = request.getParameter("chequedate");
	String chequeno = request.getParameter("chequeno"); */
	
	System.out.println("Text1");
	
	String vendor = request.getParameter("vendor");
	String orderno = request.getParameter("order");
	String letterID = request.getParameter("sLetterID");
	String groupList = request.getParameter("groupList");
	
	String ref = request.getParameter("ref");
	String textValue = request.getParameter("textValue");
	String criteria = request.getParameter("criteria");
	
	String voucherdate = request.getParameter("voucherdate");
	String voucherno = request.getParameter("voucherno");
	String chequedate = request.getParameter("chequedate");
	String chequeno = request.getParameter("chequeno");
	
	List<RequestForPayment> requestpayment = null;
	List<RecordForPayment> recordpayment = null;
	System.out.println("Text1" + orderno + ref + letterID);
	if(letterID.equals("ES25")|| letterID.equals("ES18")){
		String action = request.getParameter("action");
		requestpayment = RequestForPayment.LoadTransactionsDoc(criteria,textValue,groupList, action);
		System.out.println("Text21");
	}else if(letterID.equals("ES19")){
		String action = request.getParameter("action");
		
		if(action.equals("accept")){

			recordpayment = RecordForPayment.ProcessOrderNo(orderno, vendor);
		}else if(action.equals("preview")){
			recordpayment = RecordForPayment.PreviewOrderNo(orderno, vendor);
		}
	}
	Library library = Library.getContactsOnly();
	
	if(letterID.equals("ES25")){
		text = Document.GrpforpaymentDoc(requestpayment, library, ref);
	}else if(letterID.equals("ES18")){
		text = Document.requestforpaymentDoc(requestpayment, library, ref);
	}else if(letterID.equals("ES19")){
		text = Document.recordforpaymentDoc(recordpayment, library, voucherno, voucherdate,chequedate,chequeno);
	} 

	System.out.println("Text" + text);
	// Redirecting to the target page
	System.out.println("Text11" + text);
	if (text!=null) {
		System.out.println("Textss" + text);
		out.print(text);
	}
	
%>

