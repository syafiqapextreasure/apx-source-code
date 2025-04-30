<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="com.ilmu.global.connection.DBConnection, 
					com.ilmu.cataloging.Bibliography.*, 
					java.util.List, 
					com.ilmu.global.*,java.text.*,java.util.*"%>
<%
System.out.println("Test Unindex");
String CT02MATNO = request.getParameter("CT02MATNO");
//Check if record exist in CTWORK, 
//if exist delete the record before undindexing
/* if(Unindex.BO_isExist(Handler.replaceWithA(CT02MATNO))){
	Handler_BO.Delete_CTWORK(Handler.replaceWithA(CT02MATNO));
}  */
if(BO_Record.matno_exist(Handler.replaceWithA(CT02MATNO))){
	Handler_BO.Delete_CTWORK(Handler.replaceWithA(CT02MATNO));
}
%>