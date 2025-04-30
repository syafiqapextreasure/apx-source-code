<%@page import="java.net.*,java.io.*, com.wilmu.utilities.*"%>

<%
String action = request.getParameter("action");
if(action.equals("ReviewListNo")){
String function = request.getParameter("functions");
GetFndGlnumb glnumb = GetFndGlnumb.getFndGlNumbVal(function);

out.println(Handler.concatMatno(String.valueOf(glnumb.getGL98VALUE())));
}
%>