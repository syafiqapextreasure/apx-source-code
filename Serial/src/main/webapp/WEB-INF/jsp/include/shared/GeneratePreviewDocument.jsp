<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- Perform Search -->
<%@ page
	import="java.util.List, java.util.ArrayList, java.util.HashMap,
				com.ilmu.cataloging.PDF.*"%>
<%

	String text = "";

	String order = request.getParameter("orderno");
	String refno = request.getParameter("referenceNo");
	String ordermode = request.getParameter("ordermode");
	String action = request.getParameter("action");
	String liferayLogin = request.getParameter("liferayLogin");
	String vendor = request.getParameter("vendor");
	
	System.out.println("vendor2222222222222222" +vendor);
	
	String letterId = "";
	
	Library library = Library.getContactsOnly();
	Library sender = Library.loginDetail(liferayLogin); 
	Library vendorInfo = Library.getVendorDetails2(order);
	Library hod = Library.hodDetail();
	Library orderSpan = Library.ReadOrderSpan();
	//Library staffDetil = Library.staffDetail(order);
	Library batchrequstor = Library.batchrequestor(order);
	Library orderTotal = Library.allTotal(order); 
	
	
	////ORDERING GET LETTER ID
	if(ordermode.equals("20")){
		letterId = "ES04";
	}else if(ordermode.equals("40")){
		letterId = "ES01";
	}else if(ordermode.equals("44")){
		letterId = "ES05";
	}else if(ordermode.equals("60")){
		letterId = "ES03";
	} 
	
	Library letterTitile = Library.lettersubject(letterId);
	
	List<AssignOrderItems> assignOrderItems = null;
	assignOrderItems = AssignOrderItems.orderItems2(order);
	
	List<AssignOrderItems> assignRequestorDetail = null;
	assignRequestorDetail = AssignOrderItems.AssignRequestorDetail(order);
	
	if(action.equals("orderLetter")){
		text = Document.PreviewLetter(library, vendorInfo, assignOrderItems, sender, refno, letterTitile, 
				hod, orderSpan, batchrequstor, orderTotal, assignRequestorDetail, letterId);
	}
	
	
	if (text!=null) {
		if(action.equals("orderLetter")){
			if(refno.equals("(PREVIEW)")){
				System.out.println("Textss" + text);
				out.print(text);
			}else{
				int mailno = saveMail.Get98VALUE("MAILNO");
				System.out.println(mailno + "mailno"); 
					
				int newmailno = new Integer(mailno + 1);
				saveMail.updatingmailno(newmailno);
				
				saveMail.SAVEMAIL(newmailno, liferayLogin, vendor, text.replace("'", "''"), letterId);
				//saveMail.RecordTransaction(liferayLogin, refno);
				
				out.print(text);
			}
		}else if(action.equals("notification")){
			
		}else if(action.equals("orderList")){
			int mailno = saveMail.Get98VALUE("MAILNO");
			System.out.println(mailno + "mailno"); 
				
			int newmailno = new Integer(mailno + 1);
			saveMail.updatingmailno(newmailno);
			
			saveMail.SAVEMAIL(newmailno, liferayLogin, vendor, text.replace("'", "''"), letterId); 
			//saveMail.RecordTransaction(liferayLogin, refno);
			
			out.print(text);
		}
		
		/* System.out.println("Textss" + text);
		out.print(text); */
	} 
	
	/* if (text!=null) {
		System.out.println("Textss" + text);
		out.print(text);
	}  */

%>