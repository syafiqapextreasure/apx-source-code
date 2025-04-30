<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="ISO-8859-1"%>
<%@ page import="com.wilmu.foundation.currency.data.*, java.util.List" %>
    
    
    <select class="form-control" id="currency" name="currency">
<%	
	List<GetFndCurrency> currencyList = GetFndCurrency.getForexNameNDesc();
	
	String selected = "-";
	String output = "";
	output += "<option value='0'>Please select</option>";
	for(GetFndCurrency i: currencyList)
	{
		output += "<option value='" + i.getforexCode() + "' ";

		
		output += ">" + i.getforexCode() + " - " + i.getforexDesc() + "</option>";
	}
	
	out.println(output);
	
	
%>
</select>