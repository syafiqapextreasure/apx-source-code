<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- Perform Search -->
<%@ page import="com.wilmu.binding.listing.AABL0250.BindPrintRequestItem" %>
<%
	String itemType = request.getParameter("itemType");
	String bindingType = request.getParameter("bindingType");

	String title = request.getParameter("title");
	String authorName = request.getParameter("authorName");
	String accessionNumber = request.getParameter("accessionNumber");
	String year = request.getParameter("year");
	String callNo = request.getParameter("callNo");
	String isbn = request.getParameter("isbn");
	String publisher = request.getParameter("publisher");
	
	String issn = request.getParameter("issn");
	String volFrom = request.getParameter("volFrom");
	String volTo = request.getParameter("volTo");
	String issFrom = request.getParameter("issFrom");
	String issTo = request.getParameter("issTo");
	String pages = request.getParameter("pages");
	String bindingNo = request.getParameter("bindingNo");
	String binderCode = request.getParameter("binderCode");
	String copyNo = request.getParameter("copyNo");
	
	String text = "";
	
	if(itemType.equals("M")){
		if(bindingType.equals("BS")){// Binding Slip Checked
			text = BindPrintRequestItem.generateBindSlipMonograph(title,authorName,accessionNumber,year,callNo,isbn,publisher);
		}
		if(bindingType.equals("BI")){// Binding Instruction Checked
			text = BindPrintRequestItem.generateBindInstructionMonograph(title,isbn,accessionNumber,authorName,bindingNo,callNo,binderCode,copyNo);
		}
	}
	if(itemType.equals("S")){
		if(bindingType.equals("BS")){// Binding Slip Checked
			text = BindPrintRequestItem.generateBindSlipSerial(title,year,callNo,issn,publisher,pages,volFrom,volTo,issFrom,issTo);
		}
		if(bindingType.equals("BI")){// Binding Instruction Checked
			text = BindPrintRequestItem.generateBindInstructionSerial(binderCode,bindingNo,title,year,callNo,issn,publisher,pages,volFrom,volTo,issFrom,issTo);
		}
	}
	out.print(text);
%>