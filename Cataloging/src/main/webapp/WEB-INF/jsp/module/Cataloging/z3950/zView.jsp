<%--
  - zView.jsp
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
<%@ page import="com.paradigm.boca.bl.*" %>
<%@ page import="com.paradigm.util.*" %>
<%@ page import="com.paradigm.boca.ui.jsp.*" %>
<%@ page import="com.paradigm.boca.ui.*" %>
<%@ page import="com.ilmu.cataloguing.util.*" %>
<%@ page import="com.ilmu.cataloguing.bm.*" %>
<%@ page import="java.util.*" %>
<%@ page import="com.elmu.z3950.util.*" %>
<%@ page import="com.elmu.z3950.*" %>
<%
    String szAction = request.getParameter("ACTION");
    String szRequestPage = request.getParameter("page") == null ? "" : request.getParameter("page");

    TagDisplayHandler tagDisplayHandler = TagDisplayHandler.getInstance("elmu-z3950");
    String szZSearchResult = "";
    String szTitle = "";
    Record record = null;
    VariableField[] aVariableFields = null;
    if (szRequestPage.equals("zList.jsp")) {
        int iSelectedServer = 0;
        int iSelectedMARC = 0;
        try {
            iSelectedServer = Integer.parseInt(request.getParameter("hdSelectedServer"));
            iSelectedMARC = Integer.parseInt(request.getParameter("hdSelectedMARC"));
        } catch (NumberFormatException e) {}
        Vector vZClientThreads = (Vector) session.getAttribute("zclientthreads");
        if (vZClientThreads != null) {
            ZClientThread zclientThread = (ZClientThread) vZClientThreads.get(iSelectedServer);
            if (zclientThread.getStatus() == ZClientThread.RECORDS_FOUND) {
                int intCount = zclientThread.getZClient().getRecordCount();
                String[] aszMarcRecords = zclientThread.getMarcRecords();
                record = Record.fromString(aszMarcRecords[iSelectedMARC]);
                if (record != null) {
                    DataField[] aTitleFields = record.getDataFields("245");
                    if (aTitleFields != null) {
                        szTitle = aTitleFields[0].getFormattedData(new DefaultDataFieldDataFormatter());
                    }
                    aVariableFields = record.getVariableFields();
                }
            }
        }
    }
%>
<script language="JavaScript">

function download() {
    var form = document.frmZView;
    submitForm2(form, 'z3950','zDownloadMarc.jsp');
}

</script>
<div class="text-display"><span class="text-label">Title : </span><%= szTitle %></div>
<br />
<table border="0" cellpadding="3" cellspacing="2" width="100%">
  <tr class="theadrow1">
    <td>Tag</td>
    <td>Ind1</td>
    <td>Ind2</td>
    <td>Detail</td>
  </tr>
<%
    boolean blRow = true;
    String szRowClass = "";
    for (int i = 0; i < ArrayLengthCounter.countLength(aVariableFields); i++) {
        if (blRow){
            szRowClass = "tdatarow1";
        } else {
            szRowClass = "tdatarow2";
        }
        blRow = ! blRow;
        if (tagDisplayHandler.isTagVisible(aVariableFields[i].getTag())) {
%>
  <tr class="<%= szRowClass %>">
<%
            String szValue = null;
            if (aVariableFields[i].isControlField()) {
                szValue = aVariableFields[i].getPrintableData();
            } else {
                szValue = ((DataField) aVariableFields[i]).getFormattedData(new DefaultDataFieldDataFormatter());
            }
%>
    <td><%= aVariableFields[i].getTag() %></td>
    <td align="center"><%= aVariableFields[i].getIndicator(1) == null ? "" : aVariableFields[i].getIndicator(1).toString() %></td>
    <td align="center"><%= aVariableFields[i].getIndicator(2) == null ? "" : aVariableFields[i].getIndicator(2).toString() %></td>
    <td><%= szValue %></td>
<%
%>
  </tr>
<%
        }
    }
%>
  <tr>
    <td colspan="4" align="center">
      <br />
      <input type="button" onclick="download();" value="Download Marc">
      <input type="button" onclick="history.back();" value="Back">
    </td>
  </tr>
</table>
<form name="frmZView" method="POST">
  <input type="hidden" name="page" value="<%= szAction %>">
  <input type="hidden" name="hdSelectedMARC" value="<%= request.getParameter("hdSelectedMARC") %>">
  <input type="hidden" name="hdSelectedServer" value="<%= request.getParameter("hdSelectedServer") %>">
</form>

