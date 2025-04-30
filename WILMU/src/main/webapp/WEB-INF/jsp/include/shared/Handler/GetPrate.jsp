<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="ISO-8859-1"%>
    <%@page import="com.wilmu.foundation.currency.data.*,java.util.*"%>
    
<%
String currency = request.getParameter("currency");
List<GetFndCurrency> prate = GetFndCurrency.getCurrencyPDRATE(currency);
for(GetFndCurrency i: prate){
	
out.println(i.getrate());
}
%>