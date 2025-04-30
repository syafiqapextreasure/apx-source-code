<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- Perform Search -->
<%@ page
	import="java.util.List, java.util.ArrayList, java.util.HashMap,
				com.ilmu.circulation.PDF.*, 
				com.ilmu.cataloging.template.*"%>
<%

	String text = "";

	String docno = request.getParameter("docno");
	String id = request.getParameter("id");
	
	Library library = Library.getContactsOnly();
	
	// Attributes
	HashMap<String, String> attributes = new HashMap<String, String>();
	if (docno != null) {
		attributes.put("accMatNo", docno);
	}
		
	/* System.out.println("title");
	Reservation_Doc title = Reservation_Doc.getResvTitle(docno);
	System.out.println("callno");
	Reservation_Doc callno = Reservation_Doc.getResvCallNo(docno); 
	System.out.println("isbn");
	Reservation_Doc isbn = Reservation_Doc.getResvISBN(docno);  */

	System.out.println("fffffffffffff" +id);
	Library receiver = Library.Receiver(docno);
	
	Library letterTitile = Library.lettersubject("ER02");
	Library sender = Library.SenderPatron(id);
	recallLetter patronDetail = recallLetter.GetPatronDetail(docno);
	recallLetter cirDetail = recallLetter.GetBookDetail(docno);
	
	text = Document.ER02(library, letterTitile, patronDetail, cirDetail, sender);
	
	/* if (text!=null) {
		System.out.println("Textss" + text);
		out.print(text);
	}  */
	if (text!=null) {
		int mailno = saveMail.Get98VALUE("MAILNO");
		System.out.println(mailno + "mailno"); 
			
		int newmailno = new Integer(mailno + 1);
		saveMail.updatingmailno(newmailno);
		
		saveMail.SAVEMAIL(newmailno, id, patronDetail.getPATRONID(), text.replace("'", "''"), "ER02"); 
		
		out.print(text);
	}


%>