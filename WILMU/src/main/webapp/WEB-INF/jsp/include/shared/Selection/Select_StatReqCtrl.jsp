<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="ISO-8859-1"%>
<%@ page import="com.wilmu.foundation.acqstatus.data.GetFndAcqstatus, java.util.List" %>

    <select class="form-control" id="searchStatus" name="searchStatus">
<%	

	String setAcqStat = "A";
	System.out.println("setAcqStat"+setAcqStat);


List<GetFndAcqstatus> getsearchstatus = GetFndAcqstatus.GetFndAcqstatusCodeandDesc(setAcqStat);
	
	String selected = "-";
	String output = "";
	output+="<option value='0'>Please select</option>";
	for(GetFndAcqstatus i: getsearchstatus)
	{
		output += "<option value='" + i.getCode() + "' ";

		
		output += ">" + i.getCode() + " - " + i.getDesc() + "</option>";
	}
	
	out.println(output);
	
	
%>
	</select>