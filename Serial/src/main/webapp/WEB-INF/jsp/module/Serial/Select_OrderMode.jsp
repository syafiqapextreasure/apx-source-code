<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="com.ilmu.global.orderMode, java.util.List" %>
    <option value="">Please Select</option>
<%	
	List<orderMode> OrderModeList = orderMode.getOrderMode();
	
	String selected = "-";
	String output = "";

	for(orderMode i: OrderModeList)
	{
		output += "<option value='" + i.getACQMODE() + "' ";

		
		output += ">" + i.getACQMODE() + " - " + i.getDESC() + "</option>";
	}
	
	out.println(output);
	
	
%>