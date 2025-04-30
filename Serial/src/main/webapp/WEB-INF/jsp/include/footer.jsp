<%--
/*
 * footer.jsp
 *
 * ----------------------------------------------------------------------------
 *  Version    Date        By     Description
 * ----------------------------------------------------------------------------
 * ----------------------------------------------------------------------------
 *
 * Paradigm Systems Bhd
 */
--%>
<%@ page language="java" %>
<%
    com.ilmu.util.PropertiesHandler handler = com.ilmu.util.PropertiesHandler.getInstance();
    String szOrganizationName = handler.getProperty("OrganizationName");
    com.paradigm.util.CalendarDateTime now = new com.paradigm.util.CalendarDateTime();
    if (szOrganizationName == null) {
        szOrganizationName = "";
    }
%>
<br />
<table border="0" width="750" cellspacing="0" cellpadding="3">
  <tr>
    <td width="100%" class="footer" align="center" valign="middle">
      ©<%= now.getYear() %> <%= szOrganizationName %>. All rights reserved.
    </td>
  </tr>
</table>