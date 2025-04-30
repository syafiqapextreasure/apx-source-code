<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="com.ilmu.foundation.global.*, java.util.List" %>
    
<%	
	/* List<SelectStatement> selectgroupid = SelectStatement.selectGroupID(); */

	GlobalSQLStatement group = new GlobalSQLStatement();
	
	String username = "admin";
	
	List<Foundation> grouplist = group.getGroupId(username);
	
	String selected = "-";
	String output = "";

	/* for(SelectStatement i: selectgroupid)
	{
		output += "<option value='" + i.getCode() + "' ";

		
		output += ">" + i.getCode() + " - " + i.getDesc() + "</option>";
	} */
	
	out.println(output);
	
	
%>