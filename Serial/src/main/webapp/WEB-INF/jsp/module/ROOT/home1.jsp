<%--
/-
 - home.jsp
 -
 - ----------------------------------------------------------------------------
 - Version  Date      By    Description
 - ----------------------------------------------------------------------------
 -  1.0.0   20041221  BKL   Releasing the first version.
 - ----------------------------------------------------------------------------
 -
 - Paradigm Systems Bhd
 -/
--%>
<%@ page language="java" %>
<%@ page import="com.paradigm.boca.ui.jsp.*" %>
<%
    throw new SendRedirectException("/index.jsp?module=z3950&action=zClient.jsp");
%>