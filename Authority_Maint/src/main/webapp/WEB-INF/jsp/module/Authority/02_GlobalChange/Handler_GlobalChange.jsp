
<%@ page import="com.ilmu.global.connection.DBConnection, 
					com.ilmu.Authority.AuthorityGlobal.*,
					java.util.List, com.ilmu.cataloging.Template_Maint.*,
					com.ilmu.global.*,java.text.*,java.util.*"%>
<%
int pointer = Integer.parseInt(request.getParameter("pointer"));
String term = request.getParameter("term");
String tag = request.getParameter("tag");
String[] ctrlno = request.getParameterValues("ctrlno[]");
int total = Integer.parseInt(request.getParameter("total"));

 boolean existCT05SRAW = Global_Change.CT05SRAW_Exist(term, tag);

if(existCT05SRAW)
{
	out.println("fail");
}else{
	boolean updateTerm = Global_Change.updateTerm(term, pointer, tag);
//	String matno = Global_Change.getMatno(pointer);

	for (int i = 0; i < total; i++) {
		boolean updateModifyBy = Global_Change.updateIdxBy(ctrlno[i]);
	}

	if(updateTerm){
		out.println("success");
	}
} 
%>