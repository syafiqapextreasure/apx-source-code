<%--
  - zDownloadMarc.jsp
  -
  - ----------------------------------------------------------------------------
  - Version Date      By    Description
  - ----------------------------------------------------------------------------
  -  1.1    20050118  BKL   Releasing the first version.
  -  1.2    20050327  BKL   Modified to search multiple servers.
  - ----------------------------------------------------------------------------
  -
  - Paradigm Systems Berhad
 --%>
<%@ page language="java" %>
<%@ page import="java.io.*" %>
<%@ page import="java.util.*" %>
<%@ page import="com.paradigm.util.*" %>
<%@ page import="com.elmu.z3950.util.*" %>
<%@ page import="com.elmu.z3950.*" %>
<%
    String szRequestPage = request.getParameter("page") == null ? "" : request.getParameter("page");
    try {

        String szZSearchResult = null;
        int iSelectedServer = 0;
        int iSelectedMARC = 0;
        try {
            iSelectedServer = Integer.parseInt(request.getParameter("hdSelectedServer"));
            iSelectedMARC = Integer.parseInt(request.getParameter("hdSelectedMARC"));
        } catch (NumberFormatException e) {}
        Vector vZClientThreads = (Vector) session.getAttribute("zclientthreads");
        if (vZClientThreads != null) {
            ZClientThread zclientThread = (ZClientThread) vZClientThreads.get(iSelectedServer);
            if ((zclientThread != null) && (zclientThread.getStatus() == ZClientThread.RECORDS_FOUND)) {
                String[] aszMarcRecords = zclientThread.getMarcRecords();
                szZSearchResult = aszMarcRecords[iSelectedMARC];
            }
        }
        if (szZSearchResult != null) {
            byte[] buffer = null;
            ServletOutputStream out2 = null;
            String szContentType = "text/plain; charset=UTF-8";
            out.clear();
            buffer = szZSearchResult.getBytes("UTF-8");
            response.setContentType(szContentType);
            out2 = response.getOutputStream();
            out2.write(buffer);
            out2.flush();
            out2.close();
            out.close();
        } else {
            throw new Exception("Constructing Marc failed.");
        }
    } catch (NumberFormatException e) {
        String szMessage = e.getMessage() == null ? "" : e.getMessage();
        out.println("<h2>The Marc file cannot be generated. Please try again.</h2>");
        out.println("<pre>");
        out.println(szMessage);
        out.println("</pre>");
        out.println("<script language='JavaScript'>");
        out.println("if (confirm('Do you want to close this window?')) {window.close();}");
        out.println("</script>");
    } catch (Exception e) {
        e.printStackTrace(System.err);
    } catch (Error er) {
    }
%>