<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="ISO-8859-1"%>
<%@ page import="com.wilmu.foundation.branch.data.GetFndBranch,java.util.List" %>
    <select class="form-control" id="searchbranch" name="searchbranch">
<%
    List<GetFndBranch> selectbrnc = GetFndBranch.getFndBranchCodeandDesc();
        	
        	String selected = "-";
        	String output = "";
			output+="<option value='0'> Please select </option>";
        	for(GetFndBranch i: selectbrnc)
        	{
        		output += "<option value='" + i.getCode() + "' ";

        		
        		output += ">" + i.getCode() + " - " + i.getDesc() + "</option>";
        	}
        	
        	out.println(output);
    %>
    </select>