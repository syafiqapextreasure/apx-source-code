<%-- <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import="java.util.List, java.util.ArrayList, java.util.HashMap,
		com.ilmu.cataloging.template.*, 
		com.ilmu.cataloging.Release_Circulation.*,
		com.ilmu.cataloging.PDF.*" %>

<%

	/**
	Update payment reference number and generate new mail
	Return: new mail number if success, -1 if failure
	**/
	
	/* Prepare required variables */
	// Vendor Code is mandatory
		String accMatNo = request.getParameter("accMatNo");
	
	// Optional Fields
	String accTitl = request.getParameter("accTitl");
	String accCallNo = request.getParameter("accCallNo");
	
	
	// Variables needed
		List<Cataloging> cataloging = new ArrayList<Cataloging>();
	HashMap<String, String> attributes = new HashMap<String, String>();

		if (accMatNo == null||accMatNo == "") {
			accMatNo="";	
		}else if(accTitl == null||accTitl == ""){
			accTitl="";
		}else if(accCallNo == null||accCallNo == ""){
			accCallNo="";
		}

	
		attributes.put("accMatNo", accMatNo);
		attributes.put("accTitl", accTitl);
		attributes.put("accCallNo", accCallNo);


		CatalogingDOC catDOC = CatalogingDOC.searchAccMatNo(accMatNo);
	//Vendor vendor = Vendor.searchByVendorCode(invoiceList.get(0).getVendorCode());
	
	//boolean result = .AC06_updateInvoiceByAttributes(paymentReferenceNo, attributes);
	
	// Print the result of update
		Library receiver = Library.Receiver(accMatNo);
		Library sender = Library.Sender(accMatNo);
		int output= Mail.CT_CreateReleaseForCI("SYSADMIN", cataloging, catDOC, accMatNo, accTitl, accCallNo, "SYSADMIN");	
		
		// Provide the new mail number
		out.print(output);
		
		System.out.println("output" + output);
		
%> --%>