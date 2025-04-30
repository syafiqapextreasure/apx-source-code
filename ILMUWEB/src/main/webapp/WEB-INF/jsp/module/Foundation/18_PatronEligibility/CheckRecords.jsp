<%@ page
	import="com.ilmu.foundation.PatronEligibility.PatronEligibility"%>
<%@ page import="java.util.List"%>
<%@ page import="com.ilmu.foundation.PatronEligibility.SQLStatement"%>

<%
SQLStatement sql = new SQLStatement();
int rowNum = sql.CountTotalRcrd();
out.println(rowNum);
/* 	SQLStatement sql = new SQLStatement();
	final List<PatronEligibility> records = sql.getPatronEligibities("");

	if (records.size() <= 100) {
		System.out.println("none");
		out.println("none");
	} else if (records.size() > 100) {
		System.out.println("need");
		out.println("need");
	} */
%>