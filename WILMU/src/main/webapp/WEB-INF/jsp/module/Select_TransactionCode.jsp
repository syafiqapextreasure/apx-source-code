<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="ISO-8859-1"%>
<%@ page import="com.wilmu.foundation.transactioncode.data.*, java.util.List" %>
   
<%	
	List<TransactionCode> transactionCode = TransactionCode.getTransactionCodeandDesc();

	String selected = "-";
	String output = "";

	for(TransactionCode i: transactionCode)
	{
		output += "<option value='" + i.getCode() + "' ";
		output += ">" + i.getCode() + " - " + i.getDesc() + "</option>";
	}
	out.println(output);
%>