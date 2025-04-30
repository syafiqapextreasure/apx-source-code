<%@page import="java.util.List,com.ilmu.ill.returnIll.ClickReturn"%>
<%@ page import="java.util.*, com.ilmu.utilities.query.*, com.ilmu.global.DateTime, com.ilmu.global.DateFormatter" %>

<%
	try {
		
		String sReferenceNo = request.getParameter("sReferenceNo");
		System.out.println("sReferenceNo >  "+sReferenceNo);
		
		String sControlNo = request.getParameter("sControlNo");
		System.out.println("sControlNo >  "+sControlNo);
		
		String iCopies = request.getParameter("iCopies");
		System.out.println("iCopies >  "+iCopies); 
		
		/* int iCopies = Integer.parseInt(request.getParameter("iCopies"));
		System.out.println("iCopies >  "+iCopies); */
		
		int total = Integer.parseInt(request.getParameter("total"));
		System.out.println("total >  "+total);
		
		String liferayLogin = request.getParameter("liferayLogin");
		
		boolean bValid = false;
		
		ClickReturn value = new ClickReturn();
		bValid = value.cmdOK_Click(sReferenceNo, sControlNo, iCopies, total, liferayLogin);
		bValid = true;
		out.println(bValid); 
		System.out.println("dfsfsfsdfsdfds22233" +bValid +"");
		/* String output=value.getErrMessage();
		//output = patrvalid.getErrMessage();
		System.out.println("dfsfsfsdfsdfds22233" +output +"");
		out.println(output);   */
		
		/* ClickReturn.cmdOK_Click(sReferenceNo, sControlNo, iCopies);
		out.println("ok"); */ 


		
		
	}catch (Exception e) {
		out.println("error");
	}

%>