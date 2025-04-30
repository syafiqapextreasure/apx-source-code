<%--
  - zClient.jsp
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
<%@ page import="com.elmu.z3950.bm.*" %>
<%@ page import="com.elmu.z3950.util.*" %>
<%@ page import="com.paradigm.util.*" %>
<%
    String szAction = request.getParameter("ACTION");
    ZClientConfigHandler handler = ZClientConfigHandler.getInstance();
    String szRequestPage = request.getParameter("page") == null ? "" : request.getParameter("page");

    String szServer = request.getParameter("slcServer") == null ? "" : request.getParameter("slcServer");
    String szSearchTerm = request.getParameter("txtTerm") == null ? "" : request.getParameter("txtTerm");
    int iAttribute = -1;
    try {
        iAttribute = Integer.parseInt(request.getParameter("slcUseAttribute"));
    } catch (NumberFormatException nfe) {}
%>

<script language="JavaScript">

function validate() {
    var form = document.form1;
    if(getCheckedValue("slcServer") == "") {
        alert("Please select a Z39.50 Server.");
        return false;
    }
    if (form.txtTerm.value == "") {
        alert("Please fill in the search term.");
        form.txtTerm.focus();
        return false;
    }
    return true;
}

</script>
<!-- Modal content-->	  
	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
		<!-- <h4 class="modal-title" id="myModalLabel">
				Add Tag
		</h4> -->
	</div>
	<div class="modal-body">
<table border="0" width="100%" cellspacing="0" cellpadding="0">
  <form name="form1" method="POST" action="index.jsp?module=z3950&action=zSearch.jsp" onsubmit="return validate()">
  <input type="hidden" name="page" value="<%= szAction %>" />
  <tr>
    <td width="100%" valign="top" align="center">
      <table border="1" width="60%" cellspacing="0" cellpadding="3" bordercolor="#000000">
        <tr>
          <td width="60%" valign="top" class="theadrow1" align="center">Z39.50 CLIENT</td>
        </tr>
        <tr>
          <td width="60%" valign="top">
            <br />
            <table border="0" width="100%" cellspacing="0" cellpadding="3">
              <tr>
                <td colspan="3" height="5" />
              </tr>
              <tr>
                <td valign="top" class="text-label">Use Attribute&nbsp;</td>
                <td valign="top" class="text-label">:</td>
                <td valign="top">
                    <select name="slcUseAttribute">
                    <%
                        /*
                        int[] useAttributeValues = handler.getUseAttributeValues();
                        for (int i = 0; i < ArrayLengthCounter.countLength(useAttributeValues); i++) {
                    %>
                      <option value="<%= useAttributeValues[i] %>"<%= (iAttribute == useAttributeValues[i]) ? " selected" : "" %>><%= handler.getUseAttributeDescription(useAttributeValues[i]) %></option>
                    <%
                        }
                        */
                    %>
                    <%
                        UseAttribute[] useAttributes = handler.getAllUseAttributes();
                        for (int i = 0; i < ArrayLengthCounter.countLength(useAttributes); i++) {
                    %>
                      <option value="<%= useAttributes[i].getId() %>"<%= (iAttribute == Integer.parseInt(useAttributes[i].getId()) ? " selected" : "") %>><%= useAttributes[i].getDescription() %></option>
                    <%
                        }
                        
                    %>
                    </select>
                </td>
              </tr>
              <tr>
                <td colspan="3" height="5" />
              </tr>
              <tr>
                <td valign="top" class="text-label">Search Term&nbsp;</td>
                <td valign="top" class="text-label">:</td>
                <td valign="top">
                  <input type="text" name="txtTerm" size="25" value="<%= szSearchTerm %>">
                </td>
              </tr>
              <tr>
                <td colspan="3" height="10" />
              </tr>
              <tr>
                <td valign="top" class="text-label">Z39.50 Server&nbsp;</td>
                <td valign="top" class="text-label">:</td>
                <td valign="top" class="text-display">
                    <%
                        /*
                        String[] z3950Servers = handler.getZ3950Servers();
                        for (int i = 0; i < ArrayLengthCounter.countLength(z3950Servers); i++) {
                    %>
                      <input type="checkbox" name="slcServer" value="<%= z3950Servers[i] %>" /><%= handler.getZ3950ServerDescription(z3950Servers[i]) %><br />
                    <%
                        }
                        */
                    %>
                    <%
                        
                        Z3950Server[] z3950Servers = handler.getAllZ3950Servers();
                        for (int i = 0; i < ArrayLengthCounter.countLength(z3950Servers); i++) {
                    %>
                      <input type="checkbox" name="slcServer" value="<%= z3950Servers[i].getId() %>" /><%= z3950Servers[i].getDescription() %><br />
                    <%
                        }
                        
                    %>
                </td>
              </tr>
              <tr>
                <td colspan="3" height="5" />
              </tr>
              <tr>
                <td colspan="3">
                  <p align="center"><input type="submit" name="sbmSearch" value="Search">
                </td>
              </tr>
            </table>
          </td>
        </tr>
      </table>
      <br />
      <br />
<%
    if (! szRequestPage.equals("")) {
%>
      <table border="0" width="90%" cellspacing="0" cellpadding="0">
        <tr class="message">
          <td width="10%">&nbsp;</td>
          <td><br/>
<%
        String szEvent = request.getParameter("event") == null ? "" : request.getParameter("event");
        if (szEvent.equals("1")) {
            out.println("Z39.50 Server not specify.");
        } else if (szEvent.equals("2")) {
            out.println("Use Attribute not specify.");
        } else if (szEvent.equals("3")) {
            out.println("Search Term not specify.");
        } else if (szEvent.equals("4")) {
            out.println("No result found.");
        } else if (szEvent.equals("5")) {
            out.println("Z39.50 connection could not establish.");
        }
%>
            <br/><br/>
          </td>
          <td width="10%">&nbsp;</td>
        </tr>
      </table>
<%
    }
%>
    </td>
  </tr>
</form>
</table>
</div>
	<div class="modal-footer">
		<button type="button" class="btn btn-info" id="convert">
			<span class="glyphicon glyphicon-save"></span> Add
		</button>
		<input type="button" name="cancel" value="Cancel" class="btn btn-info btn-cancel" data-dismiss="modal"/>
	</div>	
