<%@ page import="com.ilmu.global.serial.Issues"%>
<%
System.out.println("SQL" + request.getParameter("startDate") +  request.getParameter("freq"));
if(request.getParameter("noOfIssues") != "")
	out.println(Issues.SE04_computeStopDate(request.getParameter("startDate"), request.getParameter("freq"), Integer.parseInt(request.getParameter("noOfIssues"))));
%>