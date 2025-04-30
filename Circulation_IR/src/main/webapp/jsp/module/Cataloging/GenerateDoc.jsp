<%-- <%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- Perform Search -->
<%@ page
	import="java.util.List, java.util.ArrayList, java.util.HashMap,
				com.ilmu.cataloging.PDF.*, 
				com.ilmu.cataloging.template.*,
				com.ilmu.cataloging.Release_Circulation.*"%>
<script>
$(document).ready(function() {
	alert("dsdsd");
});
</script>
<%
System.out.println("in");
	// Store execution result
	String text = "";

	/* Get the parameters to perform search */
	// Mandatory Field
	String accMatNo = request.getParameter("accMatNo");
	
	// Optional Fields
	String accTitl = request.getParameter("accTitl");
	String accCallNo = request.getParameter("accCallNo");

	//boolean printForeignAmount = Boolean.parseBoolean(printForeignAmount_string);

	/* Variables needed for processing */
	List<Cataloging> cataloging = new ArrayList<Cataloging>();
	String updateString = "";

	// Attributes
	HashMap<String, String> attributes = new HashMap<String, String>();

	/* // Prepare dates
	if (startDate != null) {
		attributes.put("startDate", startDate);
	}

	if (endDate != null) {
		attributes.put("endDate", endDate);
	} */

	/* Perform search */
	
	if (accMatNo != null) {
		attributes.put("accMatNo", accMatNo);
	}
	// Optional field: invoiceNo, orderNo, referenceNo
	else if (accTitl != null) {
		attributes.put("accTitl", accTitl);
	} else if (accCallNo != null) {
		attributes.put("accCallNo", accCallNo);
	} 

	// Execute statement: only when at least vendor code is present
	//SQLStatement eb = new SQLStatement();
	//Cataloging cat = eb.getDoc(accMatNo);
	//cataloging = Invoice.SE07_searchListByAttributes(attributes);
	//Vendor vendor = Vendor.searchByVendorCode(invoiceList.get(0).getVendorCode());
	CatalogingDOC catDOC = CatalogingDOC.searchAccMatNo(accMatNo);

	Library library = Library.getContactsOnly();
	Library patrDetail = Library.getPatrDetails();
	text = Document.createReleaseForCIDoc(cataloging,library, catDOC,patrDetail, accMatNo, accTitl, accCallNo);
	// Redirecting to the target page
	if (text != null) {
		out.print(text);
	}
%>

 --%>