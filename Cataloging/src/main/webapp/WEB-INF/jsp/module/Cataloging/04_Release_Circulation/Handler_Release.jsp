<%@page import="com.ilmu.cataloging.Release_Circulation.*"%>
<%@page import="com.ilmu.cataloging.Template_Maint.Cataloging"%>
<%@page import="java.util.List"%>
<%@ page import="java.util.*" %>
<%
String CT03DOCNO = request.getParameter("CT03DOCNO");
boolean CT03DOCNOExist = ReleaseForCirculation.CT03DOCNOExist(CT03DOCNO);

if(CT03DOCNOExist){
	out.println("ok");
}else{
	out.println("Accession No. does not exist");
}
%>