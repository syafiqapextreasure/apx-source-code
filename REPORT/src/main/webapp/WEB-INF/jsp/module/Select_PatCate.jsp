<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="com.wilmu.global.SearchCriteria,java.util.List" %>
    
<%
    List<SearchCriteria> selectloca = SearchCriteria.selectPatCate();
        	
        	String selected = "-";
        	String output = "";

        	for(SearchCriteria i: selectloca)
        	{
        		output += "<option value='" + i.getCode() + "' ";

        		
        		output += ">" + i.getCode() + " - " + i.getDesc() + "</option>";
        	}
        	
        	out.println(output);
    %>