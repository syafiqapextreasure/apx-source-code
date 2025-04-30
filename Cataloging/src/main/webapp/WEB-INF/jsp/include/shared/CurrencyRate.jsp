<%@ page import="com.ilmu.global.Currency, java.text.DecimalFormat" %>

<%
/*
String vendorCode = request.getParameter("vendorCode");

if(Currency.isCodeExist(vendorCode))
{
	out.println(new DecimalFormat("#.#####").format(Currency.getPubRate(vendorCode)));
}*/

String currency = request.getParameter("currency");
String rate = request.getParameter("rate");
System.out.println(currency);
if(Currency.isCodeExist(currency))
{
	out.println(new DecimalFormat("#.#####").format(Currency.getPubRate(currency)));
}
%>