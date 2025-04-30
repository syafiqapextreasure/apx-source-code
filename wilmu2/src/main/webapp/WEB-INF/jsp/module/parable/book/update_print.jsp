<%@page import="com.kmlink.ilmu.parable.parable_beta.QueryCatalogDate"%>
<%-- <%@ page import="com.kmlink.ilmu.parable.parable_beta.DBConnect"%> --%>
<%@ page import="com.kmlink.ilmu.parable.parable_beta.QueryAcession"%>

<%
System.out.print("line 6 update_print.jsp");
String accession = request.getParameter("accession");
//QueryAcession queryAcession= new QueryAcession();
String array1[]= accession.split(",");
for (String temp: array1){
	boolean update = QueryAcession.updateCT03BLESTATUS(temp);
	System.out.print("line 12 update_print.jsp update: "+ update);
}
out.println("Done");
%>