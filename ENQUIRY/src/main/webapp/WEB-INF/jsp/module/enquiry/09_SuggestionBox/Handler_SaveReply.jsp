<%@page import="java.util.List, com.ilmu.enquiry.suggBox.suggBox "%>
<%@ page import="java.util.*, com.ilmu.utilities.query.*, com.ilmu.global.DateTime, com.ilmu.global.DateFormatter" %>

<%
	try {
		
		String lblDate = request.getParameter("lblDate");
		System.out.println("lblDate >  "+lblDate);
		
		String lblTime = request.getParameter("lblTime");
		System.out.println("lblTime >  "+lblTime);
		
		String lblPatronID = request.getParameter("lblPatronID");
		System.out.println("lblPatronID >  "+lblPatronID);

		String txtReply = request.getParameter("txtReply");
		System.out.println("txtReply >  "+txtReply);

	
		suggBox.updatingSugg(lblPatronID, lblDate, lblTime, txtReply);
		out.println("ok"); 
		//boolean bSuccessful;
		/* if(action.equals("ADD")){
			//////BINDER NO//////////////////
			int bidno = Monograph.Get98VALUE("BINDNO");
			System.out.println(bidno + "bidno"); 
				
			int newbidno= new Integer(bidno + 1);
			Monograph.updatingbidno(newbidno);
			
			String bindnoConvert = String.format("%010d", newbidno);
			System.out.println("bindnoConvert" + bindnoConvert);
			
			//////BINDER REFERENCE//////////////////
			int bindRef = Monograph.Get98VALUE("BIREFNO");
			System.out.println(bindRef + "bindRef"); 
							
			int newbindRef= new Integer(bindRef + 1);
			Monograph.updatingbindRef(newbindRef);
			
			String bindRefConvert = String.format("%010d", newbindRef);
			System.out.println("bindRefConvert" + bindRefConvert);
			
			Monograph.CreateMonograph(accno, ctrlno, officer, binder, currency, erate, 
					year, fcost, lcost, dsent, dexpect, remarks, isbn, typeBind, status, spineTitle, 
					callno, cColor, lColor, daterec, recby, oriCover, advert, titlePage, supp, 
					indexInc, contentsPage, bindnoConvert, bindRefConvert);
			out.println(bindnoConvert); 
		} */
		
	}catch (Exception e) {
		out.println("error");
	}

%>