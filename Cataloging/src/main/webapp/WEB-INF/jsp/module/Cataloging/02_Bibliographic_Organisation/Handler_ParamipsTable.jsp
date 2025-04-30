<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="com.ilmu.global.connection.DBConnection, 
					com.ilmu.cataloging.Bibliography.*, 
					java.util.List, com.ilmu.cataloging.Paramips.*,
					com.ilmu.global.*,java.text.*,java.util.*"%>
	
<script type="text/javascript" src="${pageContext.request.contextPath}/js/general/global.js"></script>	
	<table class="table table-hover" data-toggle="table" id="title_result" data-click-to-select="true">
		<thead>
			<tr>
				<th data-sortable="true">Buffer No.</th>
				<th data-sortable="true">Title</th>
			</tr>
		</thead>
		<tbody>
		<% 
	
		String bufferno = request.getParameter("bufferno");
		int total = Integer.parseInt(request.getParameter("total"));
		List<Paramips> CTWORK = null;
		CTWORK =Paramips.retrieveCTWORK(bufferno, total);
		%>
		<%
			for(Paramips i: CTWORK){
		%>
			<tr  style="cursor:pointer" class="clickable-row" data-action="7" data-bufferno="<%= i.getCT04MATNO() %>" data-dismiss="modal">
					<td class="pointer" data-value="<%= i.getCT04MATNO() %>">
						<%=i.getCT04MATNO()%>
					</td>
					<td class="title" data-value="<%= i.getCT04RAW() %>">
						<%= i.getCT04RAW()%>
					</td>
				</tr>
		<% 
		}
		%>
		</tbody>
	</table>
	