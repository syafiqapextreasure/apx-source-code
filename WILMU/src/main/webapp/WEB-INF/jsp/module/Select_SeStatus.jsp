<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="ISO-8859-1"%>
<%@ page import="com.wilmu.serials.serialstatus.data.GetSerialStatus,java.util.List" %>
    
<%


	String setSetStat = request.getParameter("setSeStat");
	System.out.println("setSetStat"+setSetStat);
	String setSetStat2 = request.getParameter("setSeStat2");
	System.out.println("setSetStat2"+setSetStat2);


    List<GetSerialStatus> setSETAT = GetSerialStatus.GetSerialStatusCodeandDesc(setSetStat, setSetStat2);
        	
        	String selected = "-";
        	String output = "";

        	for(GetSerialStatus i: setSETAT)
        	{
        		output += "<option value='" + i.getCode() + "' ";

        		
        		output += ">" + i.getCode() + " - " + i.getDesc() + "</option>";
        	}
        	
        	out.println(output);
    %>