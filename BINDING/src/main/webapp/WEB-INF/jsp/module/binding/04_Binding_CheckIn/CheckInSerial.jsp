<%@page import="java.util.List, com.ilmu.binding.checkin.bindingCheckin"%>
<%@ page import="java.util.*, com.ilmu.utilities.query.*, com.ilmu.global.DateTime, com.ilmu.global.DateFormatter" %>

<%
	try {
		
		String liferayLogin = request.getParameter("liferayLogin");
		System.out.println("liferayLogin >  "+liferayLogin);
		
		String bindno = request.getParameter("bindno");
		System.out.println("bindno >  "+bindno);
		
		String checkCtrlno = request.getParameter("checkCtrlno");
		System.out.println("checkCtrlno >  "+checkCtrlno);
		
		String checkacc = request.getParameter("checkacc");
		System.out.println("checkacc >  "+checkacc);
		
		String checkSMD = request.getParameter("checkSMD");
		System.out.println("checkSMD >  "+checkSMD);
		
		String checkicat = request.getParameter("checkicat");
		System.out.println("checkicat >  "+checkicat);
		
		String checkloca = request.getParameter("checkloca");
		System.out.println("checkloca >  "+checkloca);
		
		String checkcon = request.getParameter("checkcon");
		System.out.println("checkcon >  "+checkcon);
		
		String checkfprice = request.getParameter("checkfprice");
		System.out.println("checkfprice >  "+checkfprice);
		
		String checklprice = request.getParameter("checklprice");
		System.out.println("checklprice >  "+checklprice);
		
		String checkvol = request.getParameter("checkvol");
		System.out.println("checkvol >  "+checkvol);

		String checkcopy = request.getParameter("checkcopy");
		System.out.println("checkcopy >  "+checkcopy);
		
		String checkspine = request.getParameter("checkspine");
		System.out.println("checkspine >  "+checkspine);  		
		
		String createAcc = request.getParameter("createAcc");
		System.out.println("createAcc >  "+createAcc);
		
		
		//if(createAcc.equals("NO")){
			bindingCheckin.checkinSerial(bindno, liferayLogin, createAcc, checkCtrlno, checkacc,
					checkSMD, checkicat, checkloca, checkcon, checkfprice, checklprice, checkvol,
					checkcopy, checkspine);
			out.println("ok");
		//}
		//boolean bSuccessful;
		
		
	}catch (Exception e) {
		out.println("error");
	}

%>