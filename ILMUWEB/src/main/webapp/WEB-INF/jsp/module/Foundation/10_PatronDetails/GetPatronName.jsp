<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.ilmu.foundation.PatronDetails.PatronSearch, com.ilmu.foundation.global.DataFormatter" %>

<%
	// Get parameters to perform search
	String patronId = request.getParameter("patronId");

	if(patronId == null) {
		out.print("Inappropriate parameter is provided");
	} else {
		out.print(DataFormatter.replaceEmptyForNull(PatronSearch.getPatronNameById(patronId)));
	}
%>