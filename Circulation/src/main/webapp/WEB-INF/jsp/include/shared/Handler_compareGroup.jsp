<%@page import="java.net.*,java.io.*, com.ilmu.global.viewNote"%>

<%

	String login = request.getParameter("login");
	String getauthor = request.getParameter("getauthor");
	
	String logingroup = viewNote.GetGroup(login);
	System.out.println(logingroup + "logingroup");
	
	String authorgroup = viewNote.GetGroup(getauthor);
	System.out.println(authorgroup + "authorgroup");
	
	if(logingroup.equals(authorgroup)){
		out.println("same");
	}else{
		out.println("diffrence");
	}

%>