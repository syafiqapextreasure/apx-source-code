<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="com.ilmu.global.connection.*, 
				com.ilmu.cataloging.Bibliography.*, 
				com.ilmu.cataloging.Template_Maint.*,
				java.util.List, com.ilmu.global.*"%>
<%
String tag =request.getParameter("tag");
String data =request.getParameter("data");
String module = request.getParameter("module");
String marc = Handler.marcType(module);

System.out.println(data+tag);
Tag_Handler GL17STOP = Tag_Handler.GL17STOP(tag, marc);

if(GL17STOP.getGL17STOP().equals("Y")){
	boolean nonfilling = Tag_Handler.stopword(data);
	if(nonfilling){
		int length = data.length() + 1;
		out.println(length);
	}
}
%>