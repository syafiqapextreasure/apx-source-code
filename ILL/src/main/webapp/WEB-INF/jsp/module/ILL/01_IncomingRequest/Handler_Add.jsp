<%@page import="java.util.List, com.ilmu.global.AN_Number, com.ilmu.ill.IncomingRequest.IncomingRequest"%>
<%@ page import="java.util.*, com.ilmu.utilities.query.*, com.ilmu.global.DateTime, com.ilmu.global.DateFormatter" %>

<%
	try {
		
		String sRequestNo = request.getParameter("sRequestNo");
		System.out.println("sRequestNo >  "+sRequestNo);
		
		String sControlNo = request.getParameter("sControlNo");
		System.out.println("sControlNo >  "+sControlNo);
		
		String sAccession = request.getParameter("sAccession");
		System.out.println("sAccession >  "+sAccession);

		String sRequestor = request.getParameter("sRequestor");
		System.out.println("sRequestor >  "+sRequestor);
		
		String contact = request.getParameter("contact");
		System.out.println("contact >  "+contact);
		
		String requestedDate = request.getParameter("requestedDate");
		System.out.println("requestedDate >  "+requestedDate);
		
		String sentDate = request.getParameter("sentDate");
		System.out.println("sentDate >  "+sentDate);

		String cService = request.getParameter("cService");
		System.out.println("cService >  "+cService);
	
		String cHandling = request.getParameter("cHandling");
		System.out.println("cHandling >  "+cHandling);
	
		String cDiscount = request.getParameter("cDiscount");
		System.out.println("cDiscount >  "+cDiscount);

		String cAmount = request.getParameter("cAmount");
		System.out.println("cAmount >  "+cAmount);

		String gsUserId = request.getParameter("gsUserId");
		System.out.println("gsUserId >  "+gsUserId);

		
		
		
		//boolean bSuccessful;
		int illno = AN_Number.Get98VALUE("INREQUESTNO");
		System.out.println(illno + "illno"); 
				
		int newillno= new Integer(illno + 1);
		AN_Number.updatingbidno(newillno, "INREQUESTNO");
		
		String noConvert = String.format("%010d", newillno);
		System.out.println("noConvert" + noConvert);
		
		
		boolean add = IncomingRequest.AddNewRecord(sRequestNo, sControlNo, sAccession, sRequestor, contact, 
				requestedDate, sentDate, cService, cHandling, cDiscount, cAmount, gsUserId, noConvert);
		
		if(add == true){
			System.out.println("yessss");
			out.println(noConvert);
		}else{
			System.out.println("false");
			out.println("error");
		}
		
		//out.println(noConvert);

	}catch (Exception e) {
		out.println("error");
	}

%>