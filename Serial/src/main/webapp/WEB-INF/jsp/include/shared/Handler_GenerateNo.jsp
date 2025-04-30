<%@page import="java.net.*,java.io.*, com.ilmu.global.*"%>

<%
String function = request.getParameter("functions");
Glnumb glnumb = Glnumb.getGL98VALUE(function);

out.println(Handler.concatMatno(String.valueOf(glnumb.getGL98VALUE())));
%>