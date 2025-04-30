<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- Perform Search -->
<%@ page
	import="java.util.List, java.util.ArrayList, java.util.HashMap,
				com.ilmu.ill.pdf.*, com.ilmu.global.globalFunc"%>
<%

	String text = "";

	String ControlNo = request.getParameter("sControlNo");
	String sRequestor = request.getParameter("sRequestor");
	String liferayLogin = request.getParameter("liferayLogin");
	String action = request.getParameter("action");
	String requestno = request.getParameter("requestno");
	String RequestedDate = request.getParameter("RequestedDate");
	String sAccessionNo = request.getParameter("sAccessionNo");
	
	String refno = request.getParameter("refno");
	//String lendingLib = request.getParameter("lendingLib");
	String action2 = request.getParameter("action2");
	String reqDate = request.getParameter("reqDate");
	
	String duedate = request.getParameter("duedate");
	String sContactPerson = request.getParameter("sContactPerson");
	String doctype = request.getParameter("doctype");
	
	String sVolume = request.getParameter("sVolume");
	String sIssue = request.getParameter("sIssue");
	String sPageNumber = request.getParameter("sPageNumber");
 	
	
	
	System.out.println("ControlNoControlNo" +ControlNo);
	
	///officer n staff
	Library library = Library.getContactsOnly();
	/* Library loginid = Library.loginDetail(liferayLogin);
	Library hod = Library.hodDetail(); */
	
	///detail patron
	Library patron = Library.GetPatronDetails(sRequestor);
	
	/////letter info
	Library letterTitile = Library.lettersubject(action);
	globalFunc reqDetail = globalFunc.GetRequestDetails(refno);
	
	if(action.equals("EL01")){
		if(sAccessionNo != ""){
			
		}
		text = Document.EL01(library, requestno, RequestedDate, patron, liferayLogin,
				sAccessionNo, ControlNo, sRequestor);
	}else if(action.equals("EL05")){
		text = Document.EL05(letterTitile, library, liferayLogin, patron, sRequestor, reqDetail, refno, reqDate,
				ControlNo);
	}else if(action.equals("EL03")){
		text = Document.EL03(letterTitile, library, liferayLogin, patron, sRequestor, reqDetail, refno, reqDate,
				ControlNo, duedate);
	}else if(action.equals("EL02")){
		text = Document.EL02(letterTitile, library, liferayLogin, patron, sRequestor, refno,
				ControlNo, sContactPerson, reqDate, action2, doctype, sVolume, sIssue, sPageNumber);
	}else if(action.equals("EL06")){
		text = Document.EL06(letterTitile, library, liferayLogin, patron, sRequestor, refno,
				ControlNo, sContactPerson, reqDate, doctype, sVolume, sIssue, sPageNumber, duedate);
	}
	
	
	
	if (text!=null) {
		
		if(action.equals("EL01")){
			if(action2.equals("print")){
				int mailno = saveMail.Get98VALUE("MAILNO");
				System.out.println(mailno + "mailno"); 
					
				int newmailno = new Integer(mailno + 1);
				saveMail.updatingmailno(newmailno);
				
				saveMail.SAVEMAIL(newmailno, liferayLogin, sRequestor, text.replace("'", "''"), "EL01"); 			
				out.print(text);
			}else{ 
				out.print(text);
			}
		}else if(action.equals("EL05")){
			if(action2.equals("print")){
				int mailno = saveMail.Get98VALUE("MAILNO");
				System.out.println(mailno + "mailno"); 
					
				int newmailno = new Integer(mailno + 1);
				saveMail.updatingmailno(newmailno);
				
				saveMail.SAVEMAIL(newmailno, liferayLogin, sRequestor, text.replace("'", "''"), "EL05"); 			
				out.print(text);
			}else{
				out.print(text);
			}
		}else if(action.equals("EL03")){
			if(action2.equals("print")){
				int mailno = saveMail.Get98VALUE("MAILNO");
				System.out.println(mailno + "mailno"); 
					
				int newmailno = new Integer(mailno + 1);
				saveMail.updatingmailno(newmailno);
				
				saveMail.SAVEMAIL(newmailno, liferayLogin, sRequestor, text.replace("'", "''"), "EL03"); 			
				out.print(text);
			}else{ 
				out.print(text);
			}
		}else if(action.equals("EL02")){
			if(action2.equals("print")){
				int mailno = saveMail.Get98VALUE("MAILNO");
				System.out.println(mailno + "mailno"); 
					
				int newmailno = new Integer(mailno + 1);
				saveMail.updatingmailno(newmailno);
				
				saveMail.SAVEMAIL(newmailno, liferayLogin, sRequestor, text.replace("'", "''"), "EL02");  			
				out.print(text);
			}else{ 
				out.print(text);
			}
		}else if(action.equals("EL06")){
			int mailno = saveMail.Get98VALUE("MAILNO");
			System.out.println(mailno + "mailno"); 
				
			int newmailno = new Integer(mailno + 1);
			saveMail.updatingmailno(newmailno);
			
			saveMail.SAVEMAIL(newmailno, liferayLogin, sRequestor, text.replace("'", "''"), "EL06");  		
			out.print(text);
		}
		
		
	}
%>