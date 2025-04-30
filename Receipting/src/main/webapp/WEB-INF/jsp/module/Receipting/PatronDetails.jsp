<%@page import="com.ilmu.receipting.Receipting.*,
				com.ilmu.global.Glnumb"%>
<%

String id = request.getParameter("patronid");
String name = PatronService.getPatronName(id);

if(name==""){
	out.println("fail");
}else{
	out.println(name);
}
%>