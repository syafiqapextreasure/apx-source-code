<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="com.kmlink.ilmu.shared.global.CirActSearchCriteria,java.util.List" %>
    
<%
    List<CirActSearchCriteria> selectloca = CirActSearchCriteria.selectPatCate();
    	
    	String selected = "-";
    	String output = "";

    	for(CirActSearchCriteria i: selectloca)
    	{
    		output += "<option value='" + i.getCode() + "' ";

    		
    		output += ">" + i.getCode() + " - " + i.getDesc() + "</option>";
    	}
    	
    	out.println(output);
    %>