<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- Perform Search -->
<%@ page
	import="java.util.List, java.util.ArrayList, java.util.HashMap,
	com.wilmu.acquisition.purchaseauthorization.data.*,
	com.wilmu.utilities.*,com.wilmu.acquisition.global.*,
	com.wilmu.acquisition.ordering.data.*"%>
<%

	String text = "";

	String reqno = request.getParameter("reqno");
	String refno = request.getParameter("refno");
	String id = request.getParameter("id");//loginid
	String action = request.getParameter("action");
	String letterId = request.getParameter("letterId");
	String orderno = request.getParameter("orderno");
	String CopyReceived = request.getParameter("CopyReceived");
	String vendor = request.getParameter("vendor");
	String date = request.getParameter("date"); 
	String lettercode = null;
	/* String orderNoEISVal = request.getParameter("orderNoEISVal");
	String EISinvno = request.getParameter("EISinvno"); */
	
	////////GLOBAL
	Library library = Library.getContactsOnly();
	Library sender = Library.loginDetail(id);
	Library hod = Library.hodDetail();
	List<PurchaseAuthorisation> purautho = PurchaseAuthorisation.previewreqrecord2(reqno);
	

	if(action.equals("reject")){
		System.out.println("reject");
		Library staffDetil = Library.staffDetail(reqno);
		Library letterTitile = Library.lettersubject("EQ42");
		text = Document.EQ42(library, sender, staffDetil, hod, purautho, action, letterTitile);
	}else if(action.equals("ordering")||action.equals("notification")){
		List<Ordering> ordering= null;
		//ordering =  Ordering.getOrderingDoc(order);
		String order = request.getParameter("orderno");
		String ordermode = request.getParameter("ordermode");
		if(ordermode!=null){
			if(ordermode.equals("44")){
				ordering =  Ordering.getPerformaOrder(order);
			}else
			{
				ordering =  Ordering.getOrderingDoc(order);
			}
		}else{
			ordering =  Ordering.getOrderingDoc(order);
		}
		if(action.equals("orderLetter")){
			Library staffDetil = Library.staffDetail(reqno);
			Library letterTitile = Library.lettersubject("EQ41");
			text = Document.orderingDoc(library, sender, staffDetil, hod, ordering, action, letterTitile, refno);
		}
		if(action.equals("notification")){
			Library staffDetil = Library.staffDetail(refno);
			Library letterTitile = Library.lettersubject("EQ26");
			text = Document_Ordering.EQ26(library, sender, staffDetil, hod, ordering, action, letterTitile, refno);
		} 
	}else{
			Library staffDetil = Library.staffDetail(reqno);
			Library letterTitile = Library.lettersubject("EQ41");
			text = Document.EQ41(library, sender, staffDetil, hod, purautho, action, letterTitile);
	}
		
	
	
	
	if (text!=null) {
		if(action.equals("reject")){
			int mailno = InsertMail.Get98VALUE("MAILNO");
			System.out.println(mailno + "mailno"); 
				
			int newmailno = (mailno + 1);
			InsertMail.updatingmailno(newmailno);
			
			InsertMail.SaveMail(newmailno, id, vendor, text.replace("'", "''"), "EQ42"); 			
			out.print(text);
		}else{
			if(letterId.equals("EQ41")){
				if(action.equals("preview")){
					System.out.println("Textss" + text);
					out.print(text);
				}else if(action.equals("print")){
					System.out.println("1Test");
					int mailno = InsertMail.Get98VALUE("MAILNO");
					System.out.println(mailno + "mailno"); 
						
					//int newmailno = new Integer(mailno + 1);
					int newmailno = (mailno + 1);
					System.out.println("2Test");
					InsertMail.updatingmailno(newmailno);
					System.out.println("3Test");
					InsertMail.SaveMail(newmailno, id, vendor, text.replace("'", "''"), "EQ41"); 	
					System.out.println("4Test");
					out.print(text);
				}
			}
		}
		/* System.out.println("Textss" + text);
		out.print(text); */
	} 


%>