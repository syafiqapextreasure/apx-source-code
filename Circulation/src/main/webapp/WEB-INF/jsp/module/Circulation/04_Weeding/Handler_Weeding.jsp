<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="com.ilmu.global.connection.DBConnection, 
					com.ilmu.circulation.Weeding.*, 
					java.util.List, com.ilmu.global.Glnumb,
					com.ilmu.global.*,java.text.*,java.util.*"%>

<%
String CT03DOCNO = request.getParameter("CT03DOCNO");
String reason = request.getParameter("reason");
System.out.println("Docto" + CT03DOCNO + reason);
boolean insert = Weeding.insertWEDOCM(CT03DOCNO, reason);

if(insert){
	String username = (String)session.getAttribute("screename");
	Weeding.deleteCTDOCM(CT03DOCNO, username);
	System.out.println(CT03DOCNO);
	boolean countRelatedCopy = Weeding.totalCopy(CT03DOCNO);
	if(countRelatedCopy){
		out.println("DeleteBO");
	}else{
		out.println("OK");
	}
}
%>