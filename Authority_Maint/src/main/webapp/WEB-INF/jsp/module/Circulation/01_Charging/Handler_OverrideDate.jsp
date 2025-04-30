<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="com.ilmu.circulation.Charging.*,
				java.util.*,com.ilmu.global.*,java.text.DateFormat,java.text.SimpleDateFormat"%>

<%
String dueDate = request.getParameter("dueDate");
String patron = request.getParameter("patron");

Charging chr = new Charging();
DateFormat formatter ; 
Date date ; 
formatter = new SimpleDateFormat("dd/MM/yyyy");
 date = (Date)formatter.parse(dueDate); 
Calendar cal=Calendar.getInstance();
cal.setTime(date);
chr.executeOverride(cal, patron); 

String errmessage=chr.getErrMessage();

if((errmessage)!=null  && !(errmessage).isEmpty()){
	
	out.println(errmessage);
}

%>