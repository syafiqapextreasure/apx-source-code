<%@page import="com.kmlink.ilmu.parable.parable_beta.QueryCatalogDate"%>
<%-- <%@ page import="com.kmlink.ilmu.parable.parable_beta.DBConnect"%> --%>
<%@ page import="com.kmlink.ilmu.parable.parable_beta.QueryAcession"%>

<%

String accession = request.getParameter("accession");
//QueryAcession queryAcession= new QueryAcession();
String array1[]= accession.split(",");
for (String temp: array1){
	boolean update = QueryAcession.updateCT03SPINESTATUS(temp);
}
out.println("Done");
%>