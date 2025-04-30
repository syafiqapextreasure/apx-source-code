<%--
  - zSearch.jsp
  -
  - ----------------------------------------------------------------------------
  - Version Date      By    Description
  - ----------------------------------------------------------------------------
  -  1.1    20050327  BKL   Releasing the first version.
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
<%@ page import="java.util.*" %>

<%

    String orderStr = (String) session.getAttribute("order");
System.out.println("Order" + orderStr);
    if (orderStr == null) orderStr = "0";
    int order = Integer.parseInt(orderStr);
    System.out.println("Order1" + order);
    order++;
    session.setAttribute("order", Integer.toString(order));
    ZClientConfigHandler handler = ZClientConfigHandler.getInstance();
    final int INTERVAL = 5;

    boolean isProcessing = false;
    String[][] aaszDisplayString = null;
    
    Vector vZClientThreads = (Vector) session.getAttribute("zclientthreads");
    System.out.println(session.getAttribute("zclientthreads"));
    if (vZClientThreads != null) {
        aaszDisplayString = new String[vZClientThreads.size()][2];
        for (int i = 0; i < vZClientThreads.size(); i++) {
        	System.out.println("Testing Z3950" + (ZClientThread) vZClientThreads.get(i));
            ZClientThread zclientThread = (ZClientThread) vZClientThreads.get(i);
            System.out.println("Testing Z395110" + i + zclientThread.getStatus() + aaszDisplayString[i][0] + aaszDisplayString[i][1]);
            //aaszDisplayString[i][0] = handler.getZ3950ServerDescription(zclientThread.getServer());
            aaszDisplayString[i][0] = zclientThread.getZ3950Server().getDescription();
           
            switch(zclientThread.getStatus()) {
                case ZClientThread.NEW:
                    aaszDisplayString[i][1] = "New";
                    isProcessing = true;
                    break;
                case ZClientThread.ESTABLISHING_CONNECTION:
                    aaszDisplayString[i][1] = "Establishing connection...";
                    isProcessing = true;
                    break;
                case ZClientThread.ESTABLISHING_CONNECTION_FAILED:
                    aaszDisplayString[i][1] = "Establishing connection failed.";
                    break;
                case ZClientThread.SEARCHING:
                    aaszDisplayString[i][1] = "Searching...";
                    isProcessing = true;
                    break;
                case ZClientThread.RETRIEVING_RECORD:
                    aaszDisplayString[i][1] = "Retrieving records...";
                    isProcessing = true;
                    break;
                case ZClientThread.RECORDS_FOUND:
                    aaszDisplayString[i][1] = "Completed.";
                    break;
                case ZClientThread.NO_RECORD_FOUND:
                    aaszDisplayString[i][1] = "No record found.";
                    break;
            }
        }
    }
    System.out.println("Print" + isProcessing);
    if (!isProcessing) {
    	System.out.println("TestZ390");
%>
<script language="JavaScript">
function closeZ3950(){
	$("#Z3950").modal("show");
	$.get("Z3950",function(data){
		$("#content_Z3950").html(data);
	});
}

$(".contents").load('zList');
$("#modalfooter_Z390").html('<button class="btn btn-info" type="button" onclick="closeZ3950();"><span class="glyphicon glyphicon-arrow-left"></span></button>')
/* javascript:top.location = "template?MODULE=Cataloging/05_z3950&ACTION=zList.jsp"; */
</script>
<%
System.out.println("first");
    } else {
%>
<script language="JavaScript">
$(".contents").load('processingDelay2');
</script>
<%
System.out.println("second");
    }
%>

<link rel="stylesheet" type="text/css" href="css/style.css">
<table border="0" cellpadding="0" cellspacing="0" width="100%">
    <tr>
        <td width="100%">
            <table border="0" cellpadding="0" cellspacing="0">
                <tr valign=top>
                    <td width="20">&nbsp;</td>
                    <td width="710">
                    <!-- page - begin -->

      <div align="center">
      <br />
      <table width="100%" border="0" cellpadding="3" cellspacing="0" style="border-collapse: collapse" bordercolor="#111111">
        <tr>
          <td valign="top">
            <font size="2">Your search request is <%= order == 1 ? "currently" : "still" %> being processed.<%= order == 1 ? "" : " Time taken : " + String.valueOf((order - 1) * INTERVAL) + " secs." %>
          </td>
        </tr>
      </table>
      <table width="100%" border="0" cellpadding="3" cellspacing="0" style="border-collapse: collapse" bordercolor="#111111">
<%

    for (int i = 0; i < ArrayLengthCounter.countLength(aaszDisplayString); i++) {
%>
        <tr>
          <td width="45%" align="right"><font size="2"><b><%= aaszDisplayString[i][0] %></b></font></td>
          <td width="5%"><font size="2"><b>&nbsp;:&nbsp;</b></font></td>
          <td width="50%"><font size="2">
<%
        if (aaszDisplayString[i][1].equals("Completed.")) {
        	System.out.println("Completed" + isProcessing);
%>
            <a target="_top" href="index.jsp?module=z3950&action=zList.jsp"><%= aaszDisplayString[i][1] %></a>
<%
        } else {
        	System.out.println("Completed1" + isProcessing);
%>
            <%= aaszDisplayString[i][1] %>
<%
        }
%>
          </font></td>
        </tr>
<%
    }
%>
      </table>
      <br />
      </div>

                    <!-- page - end -->
                    </td>
                    <td width="20">&nbsp;</td>
                </tr>
            </table>
        </td>
    </tr>
</table>

