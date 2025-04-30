<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="ISO-8859-1"%>
<%@ page import="com.wilmu.foundation.acdemicsubj.data.*, java.util.List" %>
    <select class='form-control' id='subj' name='subj'>
<%	
	List<GetFndAcdemicSubj> getFund= GetFndAcdemicSubj.selectGSubj();
	
	String selected = "-";
	String output = "";
	output += "<option value='0'>Please select</option>";
	for(GetFndAcdemicSubj i: getFund)
	{
		output += "<option value='" + i.getCode() + "' ";

		
		output += ">" + i.getCode() + " - " + i.getDesc() + "</option>";
	}
	out.println(output);
	
	
%>

</select>