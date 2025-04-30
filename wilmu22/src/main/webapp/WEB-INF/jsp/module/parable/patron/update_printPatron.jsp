<%@page import="com.kmlink.ilmu.parable.parable_beta.QueryCatalogDate"%>
<%-- <%@ page import="com.kmlink.ilmu.parable.parable_beta.DBConnect"%> --%>
<%@ page import="com.kmlink.ilmu.parable.parable_beta.patron.QueryPatron"%>

<%

String patron = request.getParameter("patron");

//QueryPatron queryPatron = new QueryPatron();
String array1[]= patron.split(",");
for (String temp: array1){
	boolean update = QueryPatron.updatGL14BSTATUS(patron);
}
out.println("Done");
%>