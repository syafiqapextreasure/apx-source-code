<%@ page import="com.ilmu.receipting.Receipting.ReceiptingService, java.util.List, com.ilmu.global.*" %>
<%
Glnumb CTRLNO = Glnumb.getGL98VALUE("RECEIPTNO");
String receiptno = Handler.concatMatno(String.valueOf(CTRLNO.getGL98VALUE()));
out.println(receiptno);
%>