<%--
  - pageNotAvailable.jsp
  -
  - ---------------------------------------------------------------------------
  -  Version  Date      By    Description
  - ---------------------------------------------------------------------------
  - ---------------------------------------------------------------------------
  -
  - Paradigm Systems Bhd
  --%>

<%-- JSP Page Directives {} --%>

<%-- JSP Declarations {} --%>

<%-- HTML & JSP Code { --%>
<table border="0" width="100%" cellspacing="0" cellpadding="0">
  <tr>
    <td>
      <div class="pageTitle">Page Not Available</div>
      <br />
      <table border="0" width="100%" cellspacing="0" cellpadding="0" align="center">
        <tr>
          <td class="alert-message">
            <br />
            Page (<%= request.getParameter("action") %>) in Module (<%= request.getParameter("module") %>) is not valid.
            <br />
            <br />
            <input type="button" value="Back" onclick="history.back();">
            <br />
            <br />
          </td>
        </tr>
      </table>
      <br />
    </td>
  </tr>
</table>
<%-- HTML & JSP Code } --%>