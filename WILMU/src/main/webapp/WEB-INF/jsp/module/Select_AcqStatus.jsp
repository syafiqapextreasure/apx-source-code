<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="ISO-8859-1"%>
<%@ page import="com.wilmu.foundation.acqstatus.data.GetFndAcqstatus,java.util.List" %>
<%
	String setAcqStat = request.getParameter("setAcqStat");
	System.out.println("setAcqStat"+setAcqStat);
    List<GetFndAcqstatus> setACQSTAT = GetFndAcqstatus.GetFndAcqstatusCodeandDesc(setAcqStat);
        String selected = "-";
        String output = "";

        for(GetFndAcqstatus i: setACQSTAT)
        {
        	output += "<option value='" + i.getCode() + "' ";
        	output += ">" + i.getCode() + " - " + i.getDesc() + "</option>";
        }
        out.println(output);
%>