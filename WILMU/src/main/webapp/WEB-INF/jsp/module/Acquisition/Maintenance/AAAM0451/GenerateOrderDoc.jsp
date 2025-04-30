<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- Perform Search -->
<%@ page
	import="java.util.List, java.util.ArrayList, java.util.HashMap,
				com.wilmu.utilities.*,com.wilmu.acquisition.global.*,
				com.wilmu.acquisition.ordering.data.*"%>

<%
System.out.println("in");
	// Store execution result
	String text = "";

	/* Get the parameters to perform search */
	// Mandatory Field
	String order = request.getParameter("orderno");
	String refno = request.getParameter("referenceNo");
	String action = request.getParameter("action");
	String ordermode = request.getParameter("ordermode");
	System.out.println("Order" + order);
	List<Ordering> ordering= null;
		//ordering =  Ordering.getOrderingDoc(order);
		if(ordermode!=null){
			if(ordermode.equals("44")){
				ordering =  Ordering.getPerformaOrder(order);
			}else{
				ordering =  Ordering.getOrderingDoc(order);
			}
		}else{
			ordering =  Ordering.getOrderingDoc(order);
		}
		

	Library library = Library.getContactsOnly();
	Library vendorInfo = Library.getVendorDetails2(order);
	
	/* if(action.equals("orderLetter")){
		text = Document.if(action.equals("orderLetter")){
		text = Document.orderingDoc(ordering, library, refno, ordermode, vendorInfo);
	}(ordering, library, refno, ordermode, vendorInfo);
	}	if(action.equals("notification")){
		text = Document.reqNotification(ordering, library, refno, action);
	} */



	// Redirecting to the target page
	System.out.println("Text11" + text);
	if (text!=null) {
		System.out.println("Textss" + text);
		out.print(text);
	}
	
%>

