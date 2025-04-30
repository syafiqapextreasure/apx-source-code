<%--
  - zList.jsp
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
<%@ page import="com.elmu.z3950.util.*" %>
<%@ page import="com.elmu.z3950.*" %>
<%@ page import="com.paradigm.util.*" %>
<%@ page import="com.paradigm.boca.bl.*" %>
<%@ page import="com.paradigm.boca.ui.*" %>
<%@ page import="com.ilmu.cataloguing.bm.*" %>
<%@ page import="com.ilmu.cataloguing.util.*" %>
<%@ page import="java.util.*" %>
<%
    String szAction = request.getParameter("ACTION");
    String szRequestPage = request.getParameter("page") == null ? "" : request.getParameter("page");
    TagDisplayHandler tagDisplayHandler = TagDisplayHandler.getInstance("elmu-z3950");
    final String[][] aszTagDisplay = tagDisplayHandler.getBriefDisplayColumns();
    ZClientConfigHandler handler = ZClientConfigHandler.getInstance();

%>
<script language="JavaScript">

function selectMARCRecord(x, y) {
    var form = document.frmZList;
    form.hdSelectedMARC.value = x;
    form.hdSelectedServer.value = y;
    form.submit();
}

function retrieveRecords(iCount, y, server) {
    var form = document.frmZList;
    var elmFrom;
    var elmTo;
    form.hdSelectedServer.value = y;
    form.slcServer.value = server;
    if (form.txtZClientFrom.length > 1) {
        elmFrom = form.txtZClientFrom[y];
        elmTo = form.txtZClientTo[y];
    } else {
        elmFrom = form.txtZClientFrom;
        elmTo = form.txtZClientTo;
    }
    if (elmFrom.value == "") {
        alert("Please key in the starting index.");
        elmFrom.select();
        return;
    }
    if (elmTo.value == "") {
        alert("Please key in the ending index.");
        elmTo.select();
        return;
    }
    if (! integerValid(elmFrom)) {
        alert("The starting index is not a valid number.");
        elmFrom.select();
        return;
    }
    if (! integerValid(elmTo)) {
        alert("The ending index is not a valid number.");
        elmTo.select();
        return;
    }
    var iFrom = parseInt(elmFrom.value);
    var iTo = parseInt(elmTo.value);
    if (iFrom > iTo) {
        alert("The starting index should not larger than the ending index.");
        elmFrom.select();
        return;
    }
    if (iFrom <= 0) {
        alert("The starting index should not less than 1.");
        elmFrom.select();
        return;
    }
    if (iFrom > iCount) {
        alert("The starting index should not larger than the total records found.");
        elmFrom.select();
        return;
    }
    if (iTo > iCount) {
        alert("The ending index should not larger than the total records found.");
        elmTo.select();
        return;
    }
    var count = iTo - iFrom + 1;
    if (count > 20) {
        if (! confirm("Are you sure you want to retrieve " + count + " records in a single batch? The system may need some time to process your request.")) {
            elmTo.select();
            return;
        }
    }
    form.hdZClientFrom.value = elmFrom.value;
    form.hdZClientTo.value = elmTo.value;
    submitForm2(form, 'z3950', 'zSearch.jsp');
}

</script>
<form name="frmZList" method="POST" action="index.jsp?module=z3950&action=zView.jsp">
<input type="hidden" name="page" value="<%= szAction %>" />
<input type="hidden" name="hdSelectedMARC" />
<input type="hidden" name="hdSelectedServer" />
<input type="hidden" name="slcServer" />
<input type="hidden" name="hdZClientFrom" />
<input type="hidden" name="hdZClientTo"  />

<%

    Vector vZClientThreads = (Vector) session.getAttribute("zclientthreads");
    if (vZClientThreads != null) {
        for (int h = 0; h < vZClientThreads.size(); h++) {
            ZClientThread zclientThread = (ZClientThread) vZClientThreads.get(h);
            if (zclientThread.getStatus() == ZClientThread.RECORDS_FOUND) {

                int intCount = zclientThread.getZClient().getRecordCount();
                String[] aszMarcRecords = zclientThread.getMarcRecords();
                int intZClientFrom = zclientThread.getFrom();
                int intZClientTo = zclientThread.getTo();
                if (intCount < intZClientTo) {
                    intZClientTo = intCount;
                }
%>

<table border="0" cellpadding="3" cellspacing="2" width="100%">
  <tr>
    <td align="left" class="text-label">&nbsp;<%= zclientThread.getZ3950Server().getDescription() %></td>
    <td align="right" class="text-label">
      <input type="text" name="txtZClientFrom" size="3" value="<%= intZClientFrom %>" /> to
      <input type="text" name="txtZClientTo" size="3" value="<%= intZClientTo  %>" />
      of <%= intCount %> results
      <input type="button" value="Retrieve" onclick="retrieveRecords(<%= intCount %>, <%= h %>, '<%= zclientThread.getZ3950Server().getId() %>');" />
      </span>
    </td>
  </tr>
</table>
<table border="0" cellpadding="3" cellspacing="2" width="100%">
  <tr class="theadrow1">
    <td align="center">No</td>
<%
                for (int i = 0; i < ArrayLengthCounter.countLength(aszTagDisplay); i++) {
%>
    <td align="center"><%= aszTagDisplay[i][0] %></td>
<%
                }
%>
  </tr>
<%
                boolean blRow = true;
                String szRowClass = "";
                for (int k = 0; k < ArrayLengthCounter.countLength(aszMarcRecords); k++) {
                    if (blRow){
                        szRowClass = "tdatarow1";
                    } else {
                        szRowClass = "tdatarow2";
                    }
                    blRow = ! blRow;
                    String szMarcRecord = aszMarcRecords[k];
                    if (szMarcRecord != null) {
%>
  <tr class="<%= szRowClass %>">
    <td align="center"><%= k + intZClientFrom %></td>
<%
                        Record record = Record.fromString(szMarcRecord);
                        for (int i = 0; i < ArrayLengthCounter.countLength(aszTagDisplay); i++) {
%>
    <td>
      <a href="" onclick="selectMARCRecord(<%= k %>, <%= h %>); return false;">
<%
                            DataField[] aDataFields = record.getDataFields(aszTagDisplay[i][1]);
                            if (aDataFields != null) {
                                if ((aszTagDisplay[i][2] != null) && (aszTagDisplay[i][2].length() > 0)) {
                                    Subfield[] aSubfields = aDataFields[0].getSubfields(aszTagDisplay[i][2].charAt(0));
                                    if ((aSubfields != null) && (aSubfields[0].getDataElement() != null)) {
                                        out.println(aSubfields[0].getDataElement());
                                    }
                                } else {
                                    out.println(aDataFields[0].getFormattedData(new DefaultDataFieldDataFormatter()));
                                }
                            }
%>
      </a>
    </td>
<%
                        }
%>
  </tr>
<%
                    } else {
%>
  <tr class="<%= szRowClass %>">
    <td align="center"><%= k %></td>
<%
                        for (int i = 0; i < ArrayLengthCounter.countLength(aszTagDisplay); i++) {
%>
    <td>&nbsp;</td>
<%
                        }
%>
  </tr>
<%
                    }
                } // for
%>
</table>
<br />
<%
            } else {
%>
<table border="0" width="100%" cellspacing="0" cellpadding="0">
  <tr>
    <td align="left" class="text-label" colspan="3">&nbsp;<%= zclientThread.getZ3950Server().getDescription() %></td>
  </tr>
  <tr class="message">
    <td width="10%">&nbsp;</td>
    <td>
      <br/>
      No result found.
      <input type="hidden" name="txtZClientFrom" />
      <input type="hidden" name="txtZClientTo"  />
      <br/><br/>
    </td>
    <td width="10%">&nbsp;</td>
  </tr>
</table>
<br />
<%
            }
        }
    }
%>
<br />
<div align="center">
  <input type="button" onclick="goPage2('z3950','zClient.jsp');" value="Back">
</div>
</form>
