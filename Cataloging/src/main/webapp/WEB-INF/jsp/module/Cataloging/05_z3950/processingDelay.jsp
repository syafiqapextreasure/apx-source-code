<%--
  - processingDelay.jsp
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
<%@ include file="zSearch.jsp" %>

<div style="align:center">
[ <a href="index.jsp" class="esam">Back to Search Form</a> ]
</div>
<script language="JavaScript">
$(".contents").load('processingDelay2');
/* javascript:top.location = "template?MODULE=Cataloging/05_z3950&ACTION=zList.jsp"; */
</script>
<!-- <iframe class="frame" style="width:100%;height:100%" src="processingDelay2" ></iframe>
 -->

