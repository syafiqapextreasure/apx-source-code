<%--
  - index.jsp
  -
  - ---------------------------------------------------------------------------
  - Version Date      By    Description
  - ---------------------------------------------------------------------------
  -  1.1    20050118  BKL   Releasing the first version.
  -  1.2    20050327  BKL   Modified to search multiple servers.
  - ---------------------------------------------------------------------------
  -
  - Paradigm Systems Bhd
  --%>
<%-- JSP Page Directives { --%>
<%@ page    language="java"
            buffer="24kb"
            errorPage="/index.jsp?module=ROOT&action=errorPage.jsp"
%>
<%@ page import="com.paradigm.boca.bl.*" %>
<%@ page import="com.paradigm.boca.bm.*" %>
<%@ page import="com.paradigm.boca.ui.jsp.*" %>
<%@ page import="com.paradigm.util.*" %>
<%-- JSP Page Directives } --%>

<%-- JSP Declarations {} --%>

<%-- HTML & JSP Code { --%>

<%
System.out.println("ssd");
    String module = request.getParameter("module");
    if (module == null || module.equals("")) {
        module = "ROOT";
    }
    String action = request.getParameter("action");
    if (action == null || action.equals("")) {
        action = "home.jsp";
    }
    
 /*    System.out.println(module + action);

    String pathName = application.getRealPath("/WEB-INF/jsp/module/"+ module + "/" + action);
    
    System.out.println("file" + pathName);
    java.io.File file = new java.io.File(pathName);
    if (! (file.exists() && file.isFile())) {
        module = "ROOT";
        action = "pageNotAvailable.jsp";
    }
 */
    CalendarDateTime now = new CalendarDateTime();

    try {
        if (module.equals("z3950") && action.equals("zDownloadMarc.jsp")) {
%>
<jsp:forward page="/WEB-INF/jsp/module/z3950/zDownloadMarc.jsp" />
<%
        } else if (module.equals("z3950") && action.equals("zDownloadMarc2.jsp")) {
%>
<jsp:forward page="/WEB-INF/jsp/module/z3950/zDownloadMarc2.jsp" />
<%
        } else if (module.equals("z3950") && action.equals("processingDelay2.jsp")) {
%>
<jsp:forward page="/WEB-INF/jsp/module/z3950/processingDelay2.jsp" />
<%
        } else {
            String template = "/WEB-INF/jsp/template/template.jsp";
%>
<jsp:include page="<%= template %>">
    <jsp:param name="MODULE" value="<%= module %>" />
    <jsp:param name="ACTION" value="<%= action %>" />
</jsp:include>
<%
        }
    } catch (Exception se) {
    	
        Throwable t = se.getCause();
        while (t instanceof ServletException) {
            t = ((ServletException) t).getRootCause();
        }
        if (t instanceof JspForwardException) {
            out.clear();
            String location = ((JspForwardException) t).getLocation();
%>
<jsp:forward page="<%= location %>" />
<%
        } else if (t instanceof SendRedirectException) {
            out.clear();
            String location = ((SendRedirectException) t).getLocation();
            response.sendRedirect(request.getContextPath() + location);
        } else {
            throw se;
        }
    }
%><%-- HTML & JSP Code } --%>
