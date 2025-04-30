<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- Perform Search -->
<%@ page
	import="java.util.List, java.util.ArrayList, java.util.HashMap,
				com.kmlink.ilmu.circulation.PDF.*"%>
<%

String text = "";
String action = request.getParameter("action");
String patron = request.getParameter("GL14PATR");
String accession = request.getParameter("CT03DOCNO");
System.out.println("accession >  "+accession);
String senderpatr = request.getParameter("sender");
//String CT03DOCNO = request.getParameter("CT03DOCNO");
//String[] accession = request.getParameterValues("accession[]");
Library library = Library.getContactsOnly();
Library patronData = Library.ReceiverCancel(patron);
Library sender = Library.SenderPatron((String) session.getAttribute("username"));

//05112019
Library hod = Library.hodDetail();
String id = request.getParameter("id");

//ER01
String docno = request.getParameter("docno");
reservationletter patronDetail = reservationletter.GetPatronDetail(docno);
reservationletter resvDetail = reservationletter.GetReservstionDetail(docno);
reservationletter collectDays = reservationletter.GetCOLLECTIONDAYS();
//Library sender2 = Library.loginDetail(id);

///111119
///for renewal n Discharging
String GL14PATR = request.getParameter("GL14PATR");

renewalLetter GetPatronInfo = renewalLetter.GetPatronInfo(GL14PATR);

//131119
String rsvdate = request.getParameter("rsvdate");


if(action.equals("Charging")){
	List<Document> charge = null;
	charge = Document.charges(accession);
	text = Document.charge(library, charge, patronData, sender);
}else if(action.equals("reserved")){
	Library letterTitile = Library.lettersubject("ER01");
	text = Document.ER01(library, letterTitile, patronDetail, resvDetail, collectDays, sender, hod);
}else if(action.equals("Renewal")){
	String CT03DOCNO = request.getParameter("CT03DOCNO");
	System.out.println("CT03DOCNO >  "+CT03DOCNO);
	int lengthCT03DOCNO = CT03DOCNO.split(",").length;
	System.out.println("lengthCT03DOCNO >  "+lengthCT03DOCNO);	
	String[] dischargeDetails = CT03DOCNO.split("\\|");
	text = Document.Renewal(library, sender, GetPatronInfo, CT03DOCNO, lengthCT03DOCNO);
}else if(action.equals("Discharging")){
	String CT03DOCNO = request.getParameter("CT03DOCNO");
	System.out.println("CT03DOCNO >  "+CT03DOCNO);
	int lengthCT03DOCNO = CT03DOCNO.split(",").length;
	System.out.println("lengthCT03DOCNO >  "+lengthCT03DOCNO);	
	String[] dischargeDetails = CT03DOCNO.split("\\|");
	text = Document.Discharging(library, sender, GetPatronInfo, CT03DOCNO, lengthCT03DOCNO);
}else if(action.equals("acknowledgement")){
	Library letterTitile = Library.lettersubject("ER03");
	text = Document.ER03(library, letterTitile, patronDetail, resvDetail, hod);
}else if(action.equals("cancellation")){
	Library letterTitile = Library.lettersubject("ER11");
	text = Document.ER11(library, letterTitile, patronDetail, resvDetail, sender, hod, rsvdate);
}


if (text!=null) {
	/* System.out.println("Textss" + text);
	out.print(text); */
	int mailno = saveMail.Get98VALUE("MAILNO");
		
	int newmailno = new Integer(mailno + 1);
	saveMail.updatingmailno(newmailno);
	
	String receiver = "", subject = "";
	if(action.equals("Charging")){
		receiver = patronData.getPatrId();
		subject = "Charging Slip";
	}else if(action.equals("reserved")){
		receiver = patronDetail.getPATRONID();
		Library letterTitile = Library.lettersubject("ER01");
		subject = letterTitile.getName();
	}else if(action.equals("Renewal")){
		receiver = GL14PATR;
		subject = "RENEWAL SLIP";
	}else if(action.equals("Discharging")){
		receiver = GL14PATR;
		subject = "DISCHARGING SLIP";
	}else if(action.equals("acknowledgement")){
		receiver = patronDetail.getPATRONID();
		Library letterTitile = Library.lettersubject("ER03");
		subject = letterTitile.getName();
	}else if(action.equals("cancellation")){
		receiver = patronDetail.getPATRONID();
		Library letterTitile = Library.lettersubject("ER11");
		subject = letterTitile.getName();
	}
	
	saveMail.SAVEMAIL(newmailno, sender, receiver, text, subject);
	
	out.print(text);
}
%>