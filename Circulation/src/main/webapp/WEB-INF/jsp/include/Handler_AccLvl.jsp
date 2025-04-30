<%@page import="com.ilmu.circulation.Charging.*,java.util.*,com.ilmu.global.*,
				java.util.*"%>
<%
//String username = (String)session.getAttribute("username");
String username = request.getParameter("username");
String progId = request.getParameter("progID");
System.out.println(username + progId);
boolean accLvl = AccessLvl.executeAccessLvl(username,progId );
out.println(accLvl);
%>