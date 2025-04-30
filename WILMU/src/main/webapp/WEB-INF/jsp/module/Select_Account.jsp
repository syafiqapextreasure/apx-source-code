<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="ISO-8859-1"%>
<%@ page import="com.wilmu.accounting.famast.data.GetAcctFamast,java.util.List" %>
    
<%


	String setAcct = request.getParameter("setAcct");
	System.out.println("setAcct"+setAcct);


    List<GetAcctFamast> acctList= GetAcctFamast.getFndAcctCodeandDesc(setAcct);
        	
        	String selected = "-";
        	String output = "";

        	for(GetAcctFamast i: acctList)
        	{
        		output += "<option value='" + i.getCode() + "' ";

        		
        		output += ">" + i.getCode() + " - " + i.getDesc() + "</option>";
        	}
        	
        	out.println(output);
    %>