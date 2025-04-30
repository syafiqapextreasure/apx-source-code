<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="ISO-8859-1"%>
<%@ page import="com.wilmu.cataloging.listing.cateditlisting.CatOrderTypeListing,java.util.List" %>
<%
    List<CatOrderTypeListing> catOrderType = CatOrderTypeListing.getCatOrderTypeListing();
        String selected = "-";
        String output = "";
        boolean checkTrue = false;

        for(CatOrderTypeListing i: catOrderType)
        {
        	if(i.getCatOrderCode().toLowerCase().equals("c")){
        		output += "<option selected='selected' value='" + i.getCatOrderCode() + "' ";
            	output += ">" + i.getCatOrderCode() + " - " + i.getCatOrderDesc() + "</option>";
        	}
        	if(!i.getCatOrderCode().toLowerCase().equals("c")){
        		output += "<option value='" + i.getCatOrderCode() + "' ";
            	output += ">" + i.getCatOrderCode() + " - " + i.getCatOrderDesc() + "</option>";
        	}
        }
        out.println(output);
%>