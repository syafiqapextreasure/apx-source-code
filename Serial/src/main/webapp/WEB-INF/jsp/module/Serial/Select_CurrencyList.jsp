<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="com.ilmu.global.Currency, java.util.List" %>
        <option value="">Please Select</option>
<%	
	List<Currency> currencyList = Currency.getForexNameNDesc();
	
	String selected = "-";
	String output = "";

	for(Currency i: currencyList)
	{
		output += "<option value='" + i.getforexCode() + "' ";

		
		output += ">" + i.getforexCode() + " - " + i.getforexDesc() + "</option>";
	}
	
	out.println(output);
	
	
%>