<%--
  - template1.jsp
  -
  - ---------------------------------------------------------------------------
  -  Version    Date        By     Description
  - ---------------------------------------------------------------------------
  - ---------------------------------------------------------------------------
  -
  - Paradigm Systems Sdn Bhd
  --%>

<%-- JSP Page Directives { --%>
<%@ page import="com.paradigm.boca.bl.*" %>
<%@ page import="com.paradigm.boca.bm.*" %>
<%@ page import="com.paradigm.boca.ui.jsp.*" %>
<%@ page import="com.paradigm.util.*" %>
<%-- JSP Page Directives } --%>

<%-- JSP Declarations {} --%>

<%-- HTML & JSP Code { --%>
<%

    String module = request.getParameter("MODULE");
    if (module == null || module.equals("")) {
        module = "ROOT";
    }
    String action = request.getParameter("ACTION");
    if (action == null || action.equals("")) {
        action = "home1.jsp";
    }
    String content = "/WEB-INF/jsp/module/" + module + "/" + action;
    
    System.out.println("sss" + content);
%>

<html>
  <head>
    <title>NeuSync Z39.50 Web Client</title>
    <meta http-equiv="Pragma" content="no-cache">
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <script language="JavaScript" src="${pageContext.request.contextPath}/js/util.js"></script>
    <link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/style.css">
  </head>
  <body leftmargin="0" topmargin="0">
    <table border="0" cellpadding="0" cellspacing="0" width="749">
      <tr>
        <td width="100%">
          <jsp:include page='/WEB-INF/jsp/include/header.jsp' />
        </td>
      </tr>
      <tr>
        <td width="100%">
          <br />
          <jsp:include page="<%= content %>" />
        </td>
      </tr>
      <tr>
        <td width="100%">
          <jsp:include page='/WEB-INF/jsp/include/footer.jsp' />
        </td>
      </tr>
    </table>
  </body>
</html>
<%-- HTML & JSP Code } --%>
