<%@page import="java.util.List, com.ilmu.foundation.VendorFeedback.VendorFeedback "%>
<%@ page import="java.util.*, com.ilmu.utilities.query.*, com.ilmu.global.DateTime, com.ilmu.global.DateFormatter" %>

<%
	try {
		String codeVendor = request.getParameter("codeVendor");
		System.out.println("codeVendor >  "+codeVendor);
		
		String Description = request.getParameter("Description");
		System.out.println("Description >  "+Description);
		
		String Notify = request.getParameter("Notify");
		System.out.println("Notify >  "+Notify);
		
		String Display = request.getParameter("Display");
		System.out.println("Display >  "+Display);
		
		/* String date = request.getParameter("date");
		System.out.println("date >  "+date);
		
		String userID = request.getParameter("userID");
		System.out.println("userID >  "+userID); */
		
		String action = request.getParameter("action");
		System.out.println("action >  "+action);
		
		boolean bSuccessful;
		if(action.equals("ADD")){
			 bSuccessful = VendorFeedback.CreateVendorFeedback(codeVendor, Description, Notify, Display);
			 out.println("ok");
		}else if(action.equals("EDIT")){
				bSuccessful = VendorFeedback.updating(codeVendor, Description, Notify, Display);
				out.println("ok");
		}
		
	}catch (Exception e) {
		out.println("error");
	}
%>