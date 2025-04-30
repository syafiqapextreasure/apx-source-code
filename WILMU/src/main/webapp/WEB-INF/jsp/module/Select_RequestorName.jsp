<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="ISO-8859-1"%>
<%@ page import="com.wilmu.ill_incoming_request.report.AALR0150.*" %>
<%@ page import="com.wilmu.ill_outgoing_request.report.AALR0250.*" %>
<%@ page import="java.util.List" %>
<%
String requestorCode = request.getParameter("requestorCode");
System.out.println("requestorCode is "+requestorCode);
String selected = "-";
String output = "";
	if(requestorCode.equals("INCOMING")){
	    List<RequestorInfo> requestorInfo = GetIncomingRequest.getIncomingRequestorName();
		for(RequestorInfo i: requestorInfo)
		{
			output += "<option value='" + i.getCode() + "' ";
			output += ">" + i.getCode() + " - " + i.getDesc() + "</option>";
		}
	}
	if(requestorCode.equals("OUTGOING")){
	    List<RequestorInfo> requestorInfo = GetOutcomingRequest.getOutcomingRequestorName();
		for(RequestorInfo i: requestorInfo)
		{
			output += "<option value='" + i.getCode() + "' ";
			output += ">" + i.getCode() + " - " + i.getDesc() + "</option>";
		}
	}
	out.println(output);
%>