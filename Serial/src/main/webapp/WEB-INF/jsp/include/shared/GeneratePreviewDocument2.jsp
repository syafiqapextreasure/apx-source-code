<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- Perform Search -->
<%@ page
	import="java.util.List, java.util.ArrayList, java.util.HashMap,
				com.ilmu.cataloging.PDF.*, 
				com.ilmu.cataloging.template.*,
				com.ilmu.serial.RequestForPayment.*,
				com.ilmu.serial.RecordForPayment.*,
				com.ilmu.cataloging.PDF.*"
%>

<%
	String text = "";
	
	String criteria = request.getParameter("criteria");
	String textValue = request.getParameter("textValue");
	String orderno = request.getParameter("order");
	String action = request.getParameter("action");
	String ref = request.getParameter("ref");
	String letterID = request.getParameter("sLetterID");
	String groupList = request.getParameter("groupList");
	String rate = request.getParameter("rate");
	String chkForeign = request.getParameter("chkForeign");
	String vendor = request.getParameter("vendor");
	Library letterTitile = Library.lettersubject(letterID);
	Library vendorInfo = Library.getVendorDetails3(vendor);
	String currency = request.getParameter("currency");
	String voucherdate = request.getParameter("voucherdate");
	String voucherno = request.getParameter("voucherno");
	String chequedate = request.getParameter("chequedate");
	String chequeno = request.getParameter("chequeno");
	
	List<RequestForPayment> requestpayment = null;
	
	//Library vendorInfo = Library.getVendorDetails2(vendor);
	Library hod = Library.hodDetail();
	Library library = Library.getContactsOnly();
	//RequestForPayment orderTotal =  RequestForPayment.allTotalRequestForPayment(orderno);
	RequestForPayment orderTotal2 =  RequestForPayment.allTotalRequestForPayment2(orderno);
	
	Library sender = Library.loginDetail((String) session.getAttribute("screenname"));	
	
	
	//////////////////////////////
	if(letterID.equals("ES18")|| letterID.equals("ES25")){
		requestpayment = RequestForPayment.LoadTransactionsDoc(criteria,textValue,groupList, action);
		
		if(letterID.equals("ES18")){
			text = Document.requestforpaymentDoc(library, letterTitile, vendorInfo, 
					hod, ref, orderTotal2, rate, requestpayment, vendor, chkForeign);
		}else if(letterID.equals("ES25")){
			text = Document.GrpforpaymentDoc(library, vendorInfo, hod, ref, 
					letterTitile, currency, orderTotal2, requestpayment);
		}
	}else if( letterID.equals("ES19")){
		List<RecordForPayment> ordering = RecordForPayment.GetOrderNo(criteria, textValue);
		text = Document.recordforpaymentDoc(library, letterTitile, vendorInfo, chequeno, chequedate, hod, 
				sender, currency, ordering, voucherno, voucherdate);
	}
	///////////////////////////////////////////////
	
	/* if(letterID.equals("ES25")){
		text = Document.GrpforpaymentDoc(requestpayment, library, ref);
	}else if(letterID.equals("ES18")){
		text = Document.requestforpaymentDoc(requestpayment, library, ref);
	}else if(letterID.equals("ES19")){
		text = Document.recordforpaymentDoc(recordpayment, library, voucherno, voucherdate,chequedate,chequeno);
	}  */
	
	if (text!=null) {
		if(letterID.equals("ES18")|| letterID.equals("ES25")){
			if(action.equals("preview")){
				out.print(text);
			}else if(action.equals("process")){
				int mailno = saveMail.Get98VALUE("MAILNO");
				System.out.println(mailno + "mailno"); 
					
				int newmailno = new Integer(mailno + 1);
				saveMail.updatingmailno(newmailno);
				
				saveMail.SAVEMAIL(newmailno, (String) session.getAttribute("screenname"), vendor, text, letterID);
				out.print(text);
			}
		}else if(letterID.equals("ES19")){
			System.out.println("Textss" + text);
			out.print(text);
			 if(action.equals("accept")){
				int mailno = saveMail.Get98VALUE("MAILNO");
				System.out.println(mailno + "mailno"); 
					
				int newmailno = new Integer(mailno + 1);
				saveMail.updatingmailno(newmailno);
				
				saveMail.SAVEMAIL(newmailno, (String) session.getAttribute("screenname"), vendor, text, letterID);
				out.print(text);
			}else if(action.equals("preview")){
				out.print(text);
			} 
		}
		/*  */
		/* System.out.println("Textss" + text);
		out.print(text); */
	} 
	
	/* System.out.println("Textss" + text);
		out.print(text);  */
	
%>