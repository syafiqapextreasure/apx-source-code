<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="ISO-8859-1"%>
<%@ page import="com.wilmu.cataloging.report.KLCS0750.*, java.util.List" %>
   
<%   	
   	List<VendorInfo> transactionCode = GetAccessionProcessingTimeframe.getVendorInfo();

   	String selected = "-";
   	String output = "";

   	for(VendorInfo i: transactionCode)
   	{
   		output += "<option value='" + i.getCode() + "' ";
   		output += ">" + i.getCode() + " - " + i.getDesc() + "</option>";
   	}
   	out.println(output);
   %>