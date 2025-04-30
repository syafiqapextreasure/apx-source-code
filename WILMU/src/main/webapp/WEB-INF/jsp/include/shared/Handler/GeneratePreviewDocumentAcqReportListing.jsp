<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- Perform Search -->
<%@ page
	import="com.wilmu.acquisition.global.print.AcqPrintRequestedItemStatus"%>
<%
	String text = "";
	String letterId = request.getParameter("letterId");
	String loginid = request.getParameter("loginid");
	String patronid = request.getParameter("patronid");
	String actionPrint = request.getParameter("actionPrint");
	String printNotice = request.getParameter("printNotice");
	
	String currencyFormat = request.getParameter("currencyFormat");
	String optionSelect = request.getParameter("optionSelect");
	String searchval = request.getParameter("searchval");
	String chkBoxAcqPrint = request.getParameter("chkBoxAcqPrint");

	if(actionPrint.equals("RequestNotice")){
		text = AcqPrintRequestedItemStatus.GenerateNotice(letterId,loginid,patronid,actionPrint,printNotice);
	}
	if(actionPrint.equals("ReceivingSlip")){
		text = AcqPrintRequestedItemStatus.generateReceivingSlip(currencyFormat,optionSelect,searchval,chkBoxAcqPrint);
	}
	
	if(text!=null) {
		if(actionPrint.equals("RequestNotice")){
			out.print(text);
		}
		if(actionPrint.equals("ReceivingSlip")){
			out.print(text);
		}
	} 
%>