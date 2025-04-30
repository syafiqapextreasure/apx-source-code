
<%@ page import="com.ilmu.global.connection.DBConnection, 
					com.ilmu.Authority.AuthorityGlobal.*,
					java.util.List, com.ilmu.cataloging.Template_Maint.*,
					com.ilmu.global.*,java.text.*,java.util.*"%>
<%
int pointer = Integer.parseInt(request.getParameter("pointer"));
String term = request.getParameter("term");
String tag = request.getParameter("tag");


boolean deleteRecord = Global_Change.deleteTerm(pointer, tag);

if(deleteRecord)
{
	out.println("success");
}else{
		out.println("fail");

}
%>