<%--
  - zSearch.jsp
  -
  - ----------------------------------------------------------------------------
  - Version Date      By    Description
  - ----------------------------------------------------------------------------
  -  1.1    20050118  BKL   Releasing the first version.
  -  1.2    20050327  BKL   Modified to search multiple servers.
  -  1.3    20050327  BKL   Fixed error.
  - ----------------------------------------------------------------------------
  -
  - Paradigm Systems Berhad
 --%>
<%@ page language="java" %>
<%@ page import="com.elmu.z3950.util.*" %>
<%@ page import="com.elmu.z3950.*" %>
<%@ page import="com.paradigm.util.*" %>
<%@ page import="com.paradigm.boca.bl.*" %>
<%@ page import="com.paradigm.boca.ui.jsp.*" %>
<%@ page import="java.util.*" %>
<%

    String[] aszServer = request.getParameterValues("slcServer");
    String szRequestPage = request.getParameter("page") == null ? "" : request.getParameter("page");
    if (aszServer == null) {
        throw new SendRedirectException("/index.jsp?module=z3950&action=zClient.jsp&page=" + szRequestPage + "&event=1");
    }
    int iAttribute = 0;
    String szSearchTerm = null;
    int iFrom = 1;
    int iTo = 10;
    Vector vZClientThread = null;

    if (szRequestPage.equals("zList.jsp")) {
        try {
            iAttribute = Integer.parseInt((String) session.getAttribute("slcUseAttribute"));
        } catch (NumberFormatException nfe) {
            throw new SendRedirectException("/index.jsp?module=z3950&action=zClient.jsp&page=" + szRequestPage + "&event=2");
        }
        szSearchTerm = session.getAttribute("txtTerm") == null ? "" : (String) session.getAttribute("txtTerm");
        if (szSearchTerm.equals("")) {
            throw new SendRedirectException("/index.jsp?module=z3950&action=zClient.jsp&page=" + szRequestPage + "&event=3");
        }
        try {
            iFrom = Integer.parseInt(request.getParameter("hdZClientFrom"));
            iTo = Integer.parseInt(request.getParameter("hdZClientTo"));
        } catch (Exception e) {
        }
    } else {
        try {
            iAttribute = Integer.parseInt(request.getParameter("slcUseAttribute"));
        } catch (NumberFormatException nfe) {
            throw new SendRedirectException("/index.jsp?module=z3950&action=zClient.jsp&page=" + szRequestPage + "&event=2");
        }
        szSearchTerm = request.getParameter("txtTerm") == null ? "" : request.getParameter("txtTerm");
        if (szSearchTerm.equals("")) {
            throw new SendRedirectException("/index.jsp?module=z3950&action=zClient.jsp&page=" + szRequestPage + "&event=3");
        }
    }

    vZClientThread = new Vector();
    ZClientConfigHandler handler = ZClientConfigHandler.getInstance();
    for (int i = 0; i < ArrayLengthCounter.countLength(aszServer); i++) {
        ZClientThread zclientThread = new ZClientThread(handler.getZ3950Server(aszServer[i]), iAttribute, szSearchTerm, iFrom, iTo);
        zclientThread.start();
        vZClientThread.add(zclientThread);
    }
    session.setAttribute("zclientthreads", vZClientThread);
    session.setAttribute("order", "0");

    session.setAttribute("slcUseAttribute", String.valueOf(iAttribute));
    session.setAttribute("txtTerm", szSearchTerm);
    if (vZClientThread.size() > 0) {
        throw new SendRedirectException("/index.jsp?module=z3950&action=processingDelay.jsp");
    }
%>
