<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="com.ilmu.global.connection.DBConnection, 
					com.ilmu.cataloging.Bibliography.*, 
					java.util.List, 
					com.ilmu.global.*,java.text.*,java.util.*, 
					com.ilmu.cataloging.Template_Maint.*"%>
<script>
$('#BibRcrd').find("textarea, button, select, a, .rawtag,.indi1, .indi2").removeAttr('disabled');
</script>
<%
		String cretime = DateTime.getTodayTime();
		String credate = DateTime.getTodayDate();
		String userName = (String) session.getAttribute("username");
		String type=request.getParameter("type");
		
		String module = request.getParameter("module");
		String ctype = Tag_Handler.getMsBibType(type, Handler.bibType(module));
		String prematno = request.getParameter("matno");
	/* 	String precreateby = request.getParameter("createby");
		String precreatime = request.getParameter("creatime");
		String precreatedate = request.getParameter("createdate");
		String premodiby = request.getParameter("modiby");
		String premodidate = request.getParameter("modidate");
		String premoditime = request.getParameter("moditime"); */
		String action = request.getParameter("action");
	/* 	System.out.println("ssd" + cretime + credate + userName + type + ctype+ prematno + precreateby + precreatime + precreatedate + premodiby+ 
				premodidate + premoditime + action); */
%>

<%
	if(action.equals("Duplicate Copy")){
%>
<table id='rcrddetails' class="table-hover table-condensed table-fixed duplicatecpy" data-url="data1.json" data-height="299" data-sort-name="name" data-sort-order="desc" style="border-collapse:collapse;color:black;width:100%">
	<tr>
		<td class="creby">Created By: <span class="Cuser"><strong><%=userName %></strong></span></td>
	    <td class="credate">Created Date: <span class="Cdate"><strong><%=credate %></strong></span></td>
	    <td class="cretime">Created Time: <span class="Ctime"><strong><%=cretime %></strong></span></td>
	</tr>
	<tr>
	 	<td>Modified By:<span class="Muser"><%=userName  %></span><input type='hidden' id='newUser' value='<%=(String)session.getAttribute("username")%> '></td>
		    <td>Modified Date: <span class="Mdate"><%=credate %></span>
		    <td>Modified Time: <span class="Mtime"><%=cretime %></span></td>
	</tr>
	<tr>
	 <td class="orimatno">Buffer No:  <span class='Cmatno'></span></td>
	<td class="actionstatus">Record Status:<span class='Cstatus'>New Record</span></td>
	<td>Type:<span class='Ctype'><%=ctype %></span><input type="hidden" class="typecode" value="<%=type%>"></td>
	</tr>
</table>

<%-- <table id='rcrddetails' class="table-hover table-condensed table-fixed cancelcpy" data-url="data1.json" data-height="299"
 data-sort-name="name" data-sort-order="desc" style="border-collapse:collapse;color:black;width:100%;display:none">
	<tr>
		<td class="creby">Created By: <span class="Cuser"><strong><%=precreateby %></strong></span></td>
	    <td class="credate">Created Date: <span class="Cdate"><strong><%=precreatedate %></strong></span></td>
	    <td class="cretime">Created Time: <span class="Ctime"><strong><%=precreatime %></strong></span></td>
	</tr>
	<tr>
	 	<td>Modified By:<span class="Muser"><%=premodiby  %></span><input type='hidden' id='newUser' value='<%=(String)session.getAttribute("username")%> '></td>
		    <td>Modified Date: <span class="Mdate"><%=premodidate %></span>
		    <td>Modified Time: <span class="Mtime"><%=premoditime %></span></td>
	</tr>
	<tr>
	 <td class="orimatno">Buffer No:  <span class='Cmatno'><%=prematno%></span></td>
	<td class="actionstatus">Record Status:<span class='Cstatus'>Index Record</span></td>
	<td>Type:<span class='Ctype'><%=ctype %></span><input type="hidden" class="typecode" value="<%=type%>"></td>
	</tr>
</table> --%>
<%
	}
%>

