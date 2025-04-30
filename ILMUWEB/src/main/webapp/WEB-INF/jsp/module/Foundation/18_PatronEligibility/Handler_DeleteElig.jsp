<%@page import="com.ilmu.foundation.PatronEligibility.SQLStatement"%>

<%
	try {
		String GL27CATE = request.getParameter("GL27CATE1");
		String GL27ICAT = request.getParameter("GL27ICAT1");
		String GL27SMD = request.getParameter("GL27SMD1");
		String GL27BRNC = request.getParameter("GL27BRNC1");

		String result = SQLStatement.checkEligibility(GL27CATE, GL27ICAT, GL27SMD, GL27BRNC);
		if (result == "continue") {
			SQLStatement.deleteEligibility(GL27CATE, GL27ICAT, GL27SMD, GL27BRNC);
			out.println("ok");
		} else {
			out.println("error");
		}

	} catch (Exception e) {
		out.println("error");
	}
%>