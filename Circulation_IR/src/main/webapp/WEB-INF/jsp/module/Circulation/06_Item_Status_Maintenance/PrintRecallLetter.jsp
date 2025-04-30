<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- Perform Search -->
<%@ page
	import="java.util.List, java.util.ArrayList, java.util.HashMap,
				com.ilmu.circulation.PDF.*, 
				com.ilmu.cataloging.template.*,
				com.ilmu.circulation.resv.*"%>

<%
	/* System.out.println("in");
	// Store execution result
	String text = "";

	String docno = request.getParameter("docno");
	//String matno = request.getParameter("matno");

	// Attributes
	HashMap<String, String> attributes = new HashMap<String, String>();
	if (docno != null) {
		attributes.put("accMatNo", docno);
	}
	
		System.out.println("title");
	 	Reservation_Doc title = Reservation_Doc.getResvTitle(docno);
	 	System.out.println("callno");
		Reservation_Doc callno = Reservation_Doc.getResvCallNo(docno); 
		System.out.println("isbn");
		Reservation_Doc isbn = Reservation_Doc.getResvISBN(docno); 

		System.out.println("library");
		Library library = Library.getContactsOnly();
		System.out.println("receiver");
		Library receiver = Library.Receiver(docno);
		text = Document.recallLetter(library, title, callno, isbn, receiver, docno);
		

	// Redirecting to the target page
	if (text!=null) {
		out.print(text);
	} */
	
%>

