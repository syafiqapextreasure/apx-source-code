<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- Perform Search -->
<%@ page
	import="java.util.List, java.util.ArrayList, java.util.HashMap,
				com.ilmu.receipting.pdf.*, com.ilmu.receipting.Receipting.*"%>
<%

	String text = "";
	String action = request.getParameter("action");
	String receiptno = request.getParameter("receiptno");
	String patronid = request.getParameter("patronid");
	String total = request.getParameter("total");
	String received = request.getParameter("received");
	String change = request.getParameter("change");
	String paymentMode = request.getParameter("PayMode");
	System.out.println("paymentmode" +paymentMode);
	System.out.println("ggggggggggggggg" +session.getAttribute("username"));

	Library library = Library.getContactsOnly();
	List <ReceiptingService> receipt = ReceiptingService.ListReceiptData(receiptno);
	Library officer = Library.SenderPatron((String) session.getAttribute("username"));

	Library letterTitile = Library.lettersubject("receipt");
	text = Document.RECEIPT(library, letterTitile, officer, patronid, receiptno,
			total, received, change, paymentMode, receipt);



if (text!=null) {
	System.out.println("Textss" + text);
	out.print(text);
}
%>