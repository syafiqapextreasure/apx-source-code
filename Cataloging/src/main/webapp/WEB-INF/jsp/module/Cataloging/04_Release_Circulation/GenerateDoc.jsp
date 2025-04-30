<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- Perform Search -->
<%@ page
	import="java.util.List, java.util.ArrayList, java.util.HashMap,
				com.ilmu.cataloging.PDF.*, 
				com.ilmu.cataloging.template.*,
				com.ilmu.cataloging.Release_Circulation.*"%>

<%
System.out.println("in");
	// Store execution result
	String text = "";

	/* Get the parameters to perform search */
	// Mandatory Field
	String accMatNo = request.getParameter("accMatNo");
	String accTitl = request.getParameter("accTitl");
	String accCallNo = request.getParameter("accCallNo");
	String matno = request.getParameter("matno");
	
	System.out.println("erere" + matno);
	//boolean printForeignAmount = Boolean.parseBoolean(printForeignAmount_string);

	/* Variables needed for processing */
	List<Cataloging> cataloging = new ArrayList<Cataloging>();
	String updateString = "";

	// Attributes
	HashMap<String, String> attributes = new HashMap<String, String>();
	
	if (accMatNo != null) {
		attributes.put("accMatNo", accMatNo);
	}
	// Optional field: invoiceNo, orderNo, referenceNo
	else if (accTitl != null) {
		attributes.put("accTitl", accTitl);
	} else if (accCallNo != null) {
		attributes.put("accCallNo", accCallNo);
	}  else if (matno != null) {
		attributes.put("matno", matno);
	} 

		CatalogingDOC catDOC = CatalogingDOC.searchAccMatNo(accMatNo);
		CatalogingDOC ISBN = CatalogingDOC.retrieveISBN(accMatNo);

		Library library = Library.getContactsOnly();
		Library receiver = Library.Receiver(matno);
		Library sender = Library.CatalogSender();
		System.out.println("Sender" + sender.getPatrName());
		text = Document.createReleaseForCIDoc(cataloging,library, catDOC, ISBN,receiver, sender, accMatNo, accTitl, accCallNo, matno);
		System.out.println("Text" + text);
		
		//Temporary blocked
		//Mail.CT_CreateReleaseForCI(cataloging,library, catDOC,receiver, sender, accMatNo, accTitl, accCallNo);
		

	// Redirecting to the target page
	System.out.println("Text11" + text);
	if (text!=null) {
		System.out.println("Textss" + text);
		out.print(text);
	}
	
%>

