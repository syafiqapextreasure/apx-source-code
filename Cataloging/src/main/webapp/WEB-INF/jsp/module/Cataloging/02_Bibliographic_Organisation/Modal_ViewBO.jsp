<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="com.ilmu.global.connection.DBConnection, 
					com.ilmu.cataloging.Bibliography.*, 
					java.util.List, 
					com.ilmu.global.*,java.text.*,java.util.*"%>
<link rel="stylesheet" href="${pageContext.request.contextPath}/themes/style.css"/><%-- 
<script type="text/javascript" src="${pageContext.request.contextPath}/js/general/global.js"></script> --%>

<div class="modal-body">
<%
/* String[] tag = request.getParameterValues("tag[]");
String[] indi1 = request.getParameterValues("indi1[]");
String[] indi2 = request.getParameterValues("indi2[]");
String[] raw = request.getParameterValues("rawdata[]"); */
//int total = Integer.parseInt(request.getParameter("total"));
	
%>

<div class="displayBO" style="min-height:10%;max-height:80%;display:block;overflow-y:scroll">
<table id="viewBO">
	<tr>
		<td>Created By: <span class='Cuser'></span></td>
	    <td>Created Date: <span class='Cdate'></span></td>
	    <td>Created Time: <span class='Ctime'></span></td>
	    <td>&nbsp;</td>
	</tr>
	<tr>
	 	<td>Modified By: <span class='Muser'></span></td>
		<td>Modified Date: <span class='Mdate'></span></td>
	    <td>Modified Time: <span class='Mtime'></span></td> 
	</tr>
	<tr>
	 <td class="orimatno">Control No: <span class='Cmatno'></span></td>
	<%--  <td class="actionstatus">Status: <span class='Cstatus'><%=CTMATM.getCT02STATUS()%></span></td>
	 <td class="type">Type:<span class='Ctype'><%=CTMATM.getCT02TYPE() %></span></td> --%>
	</tr>
</table>
<table class="table table-hover table-condensed table-striped table-fixed BibRcrd" data-url="data1.json" data-height="299" data-sort-name="name" data-sort-order="desc" style="border-collapse:collapse;color:black">
<col width='5%'><col width='5%'><col width='5%'>
<tbody>
	<%-- <%
	
	for (int i=0;i<=total;i++){
	
			%>
				<tr>
					<td class='tag'><%=tag[i] %></td>
					<td class='indi1'><%=indi1[i] %></td>
					<td class='indi2'><%=indi2[i] %></td>
					<td class='raw'><%=raw[i] %></td>
				</tr>
			
			<%
		}
			%> --%>
</tbody>
</table> 
</div>
</div>
<div class="modal-footer">

<button class='btn btn-sm' id='cancel' data-dismiss="modal">Close</button>
</div>


	