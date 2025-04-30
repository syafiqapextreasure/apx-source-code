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
	String patron = request.getParameter("patron");	
	String matno = request.getParameter("matno");
	String date = request.getParameter("rsvdate");
	/* Get the parameters to perform search */
	// Mandatory Field
/* 	String accMatNo = request.getParameter("accMatNo");
	String accTitl = request.getParameter("accTitl");
	String accCallNo = request.getParameter("accCallNo");
	String matno = request.getParameter("matno");
	
	System.out.println("erere" + matno); */
	//boolean printForeignAmount = Boolean.parseBoolean(printForeignAmount_string);

	/* Variables needed for processing */
	List<Cataloging> cataloging = new ArrayList<Cataloging>();
	String updateString = "";

	// Attributes
	HashMap<String, String> attributes = new HashMap<String, String>();
	if (accMatNo != null) {
		attributes.put("accMatNo", accMatNo);
	}
	
/* 	if (accMatNo != null) {
		attributes.put("accMatNo", accMatNo);
	}
	// Optional field: invoiceNo, orderNo, referenceNo
	else if (accTitl != null) {
		attributes.put("accTitl", accTitl);
	} else if (accCallNo != null) {
		attributes.put("accCallNo", accCallNo);
	}  else if (matno != null) {
		attributes.put("matno", matno);
	}  */
System.out.println("MANTO" + matno);
	 	Reservation_Doc title = Reservation_Doc.getResvTitleCancel(accMatNo,matno);
		Reservation_Doc callno = Reservation_Doc.getResvCallNoCancel(accMatNo,matno); 

		Reservation_Doc isbn = Reservation_Doc.getResvISBN(accMatNo, matno); 

		Library library = Library.getContactsOnly();
		Library receiver = Library.ReceiverCancel(patron);
		Library sender = Library.SenderCancel(patron);
		text = Document.createReleaseForCIDoc(date, cataloging,library, title, callno,isbn,receiver, sender, accMatNo);
		/* Library receiver = Library.Receiver(matno);
		Library sender = Library.Sender(matno);
		text = Document.createReleaseForCIDoc(cataloging,library, catDOC, ISBN,receiver, sender, accMatNo, accTitl, accCallNo, matno);
		Mail.CT_CreateReleaseForCI(cataloging,library, catDOC,receiver, sender, accMatNo, accTitl, accCallNo); */
		

	// Redirecting to the target page
	if (text!=null) {
		out.print(text);
	}
	
%>

