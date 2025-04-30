<%@page import="com.ilmu.cataloging.Template_Maint.*"%>
<%@page import="java.util.List"%>
<%@ page import="java.util.*" %>

	<%
	try {
		String tplID = request.getParameter("CTTPLID");
		System.out.println("s" + tplID);

		Template_Maintenance.deleteTplDetails(tplID);

		out.println("ok");
		
	} catch (Exception e) {
		out.println("error");
	}
	%>
