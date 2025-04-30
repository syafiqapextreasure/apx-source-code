
<%@ page
	import="com.ilmu.foundation.PatronEligibility.*, com.ilmu.foundation.*, java.util.List"%>

<%
	String GL24FOREX = request.getParameter("GL24FOREX1");
	String GL27CATE = request.getParameter("GL27CATE1");
	String GL27ICAT = request.getParameter("GL27ICAT1");
	String GL27SMD = request.getParameter("GL27SMD1");
	String GL27BRNC = request.getParameter("GL27BRNC1");
	String GL27PLOAN = request.getParameter("GL27PLOAN1");
	String GL27ELIG = request.getParameter("GL27ELIG1");
	String GL27RENEW = request.getParameter("GL27RENEW1");
	String GL27GRACE1 = request.getParameter("GL27GRACE11");
	String GL27GRACE2 = request.getParameter("GL27GRACE21");
	String GL27GRACE3 = request.getParameter("GL27GRACE31");
	String GL27TIMESCOST = request.getParameter("GL27TIMESCOST1");
	String GL27RSTR = request.getParameter("GL27RSTR1");
	String GL27PRCFEES = request.getParameter("GL27PRCFEES1");
	String GL27FGRACE1 = request.getParameter("GL27FGRACE11");
	String GL27FGRACE2 = request.getParameter("GL27FGRACE21");
	String GL27FGRACE3 = request.getParameter("GL27FGRACE31");
	String GL27BKMULTP = request.getParameter("GL27BKMULTP");
	String GL27BKGRACE = request.getParameter("GL27BKGRACE");
	String GL27ALLOWOVD = request.getParameter("GL27ALLOWOVD");
	String GL27ALLOWRSV = request.getParameter("GL27ALLOWRSV");
	String GL27PTYPE = request.getParameter("GL27PTYPE");
	String GL27INCFINE = request.getParameter("GL27INCFINE");

	// 	SQLStatement.AddEligibility(GL27CATE, GL27ICAT, GL27SMD, GL27BRNC, GL27PLOAN, GL27ELIG, GL27RENEW,
	// 			GL27GRACE1, GL27GRACE2, GL27GRACE3, GL27TIMESCOST, GL27RSTR, GL27PRCFEES, GL27FGRACE1, GL27FGRACE2,
	// 			GL27FGRACE3, GL27BKMULTP, GL27BKGRACE, GL27ALLOWOVD, GL27ALLOWRSV, GL27PTYPE, GL27INCFINE);
%>