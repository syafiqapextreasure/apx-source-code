<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- Perform Search -->
<%@ page
	import="java.util.List, java.util.ArrayList, java.util.HashMap,
				com.ilmu.circulation.PDF.*, 
				com.ilmu.cataloging.template.*,
				com.ilmu.circulation.resv.*"%>

<%
System.out.println("in");
	// Store execution result
	String text = "";

	String accMatNo = request.getParameter("docno");
	String matno = request.getParameter("matno");

	List<Cataloging> cataloging = new ArrayList<Cataloging>();
	String updateString = "";

	// Attributes
	HashMap<String, String> attributes = new HashMap<String, String>();
	if (accMatNo != null) {
		attributes.put("accMatNo", accMatNo);
	}
	

	 	Reservation_Doc title = Reservation_Doc.getResvTitle(accMatNo,matno);
		Reservation_Doc callno = Reservation_Doc.getResvCallNo(accMatNo,matno); 
		Reservation_Doc isbn = Reservation_Doc.getResvISBN(accMatNo,matno); 

		Library library = Library.getContactsOnly();
		Library receiver = Library.Receiver(accMatNo,matno);
		text = Document.resvAcknowledgment(cataloging,library, title, callno,isbn,receiver, accMatNo);
		

	// Redirecting to the target page
	if (text!=null) {
		out.print(text);
	}
	
%>

