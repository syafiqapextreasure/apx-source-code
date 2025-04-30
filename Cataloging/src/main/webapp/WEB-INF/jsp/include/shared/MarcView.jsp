<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="com.ilmu.global.connection.DBConnection, 
					com.ilmu.cataloging.Bibliography.*, 
					com.ilmu.cataloging.Template_Maint.*,
					java.util.List, 
					com.ilmu.global.*,java.text.*,java.util.*"%>
					

<script type="text/javascript" src="${pageContext.request.contextPath}/js/Cataloging/BO.js"></script>	
<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
		<h4 class="modal-title" id="myModalLabel">
			Marc View
		</h4>
	</div>
<%
	String[] tag = request.getParameterValues("tag[]");
	String[] indi1 = request.getParameterValues("indi1[]");
	String[] indi2 = request.getParameterValues("indi2[]");
	String[] raw = request.getParameterValues("data[]");
	int total = Integer.parseInt(request.getParameter("total"));
%>
<div class="modal-body">
	<table class='table table-condensed table-fixed table-bordered marctable' style="table-layout:fixed">
	<col width="20%">
	<col width="10%">
	<col width="10%">
	<col width="60%">
		<thead>
			<tr>
				<th>Tag</th>
				<th>Indi 1</th>
				<th>Indi 2</th>
				<th>Data</th>
			</tr>
		</thead>
		<tbody>
			<%
				for(int i=0;i<total;i++){
			%>
			<tr>
				<td><%=tag[i] %></td>
				<td><%=indi1[i] %></td>
				<td><%=indi2[i] %></td>
				<td style="word-wrap:break-word;"><%=raw[i] %></td>
			</tr>
			<%
				}
			%>
		</tbody>
	</table>

</div>
<div class="modal-footer">
		<input type="button" name="cancel" value="Cancel" class="btn btn-info btn-cancel" data-dismiss="modal"/>
	</div>