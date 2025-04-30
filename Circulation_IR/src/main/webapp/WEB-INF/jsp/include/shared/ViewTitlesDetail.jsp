<%@ page import="com.ilmu.global.connection.DBConnection, 
					com.ilmu.cataloging.Bibliography.*, 
					java.util.List, com.ilmu.cataloging.Template_Maint.*,
					com.ilmu.global.*,java.text.*,java.util.*"%>

<meta charset="ISO-8859-1">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/general/global.js"></script>	
<script type="text/javascript" src="<%= request.getContextPath() %>/plugin/datatables/jquery.dataTables.min.js"></script>
 <script type="text/javascript" src="<%= request.getContextPath() %>/plugin/datatables/dataTables.bootstrap.min.js"></script>
 <link rel="stylesheet" href="<%= request.getContextPath() %>/plugin/datatables/dataTables.bootstrap.css">
		
	<table class="table table-hover" id="view-resultDet" data-toggle="table" data-search="true" 
				data-pagination="true" data-show-columns="true" data-click-to-select="true">
		<thead>
			<tr>
				<th data-sortable="true">Accession No</th>
				<th data-sortable="true">Item Category</th>
				<th data-sortable="true">Status</th>
			</tr>
		</thead>
		<tbody>
		<% 
		String pointer = request.getParameter("pointer");
		String action = request.getParameter ("action");
		String patrid = request.getParameter("patrid");
		String search_type = request.getParameter("search_type");
		
		List<Bibliography> titleResult = null;


			titleResult = Bibliography.getAllRaw(pointer);
		
		%>
		<%
			for(Bibliography i: titleResult){
		%>
			<tr  style="cursor:pointer" class="clickable-row" id="searchBtn1Det" data-action="<%=action%>" data-toggle="modal" data-matno="<%= i.getMatno()%>">
			
				<td class="title" data-value="<%= i.getDOCNO() %>"><%= i.getDOCNO() %></td>
				<td class="title" data-value="<%= i.getICATDESC() %>"><%= i.getICATDESC() %></td>
				<td class="title" data-value="<%= i.getSTATDESC() %>"><%= i.getSTATDESC() %></td>
			</tr>
		<% 
				}

		%>
		</tbody>
	</table>
	
	
	