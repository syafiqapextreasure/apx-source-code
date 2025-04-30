<%@ page import="com.ilmu.global.connection.DBConnection, 
					com.ilmu.cataloging.Bibliography.*, 
					java.util.List, com.ilmu.cataloging.Template_Maint.*,
					com.ilmu.global.*,java.text.*,java.util.*"%>

	<script type="text/javascript" src="${pageContext.request.contextPath}/js/BO.js"></script>
	<table class="table table-hover" data-toggle="table" id="title_result" data-click-to-select="true">
		<thead>
			<tr>
				<th data-sortable="true">Title</th>
				<th data-sortable="true">Call Number</th>
				<th data-sortable="true">Year</th>
				<th data-sortable="true">Edition</th>
			</tr>
		</thead>
		<tbody>
		<% 
		String pointer = request.getParameter("pointer");
		
		
		List<Bibliography> titleResult = null;


			titleResult = Bibliography.getAllRaw(pointer);
		
		%>
		<%
			for(Bibliography i: titleResult){
		%>
			<tr class="clickable-row" id="searchBtn1" data-toggle="modal" data-matno="<%= i.getMatno() %>">
			
				<td class="title" data-value="<%= i.getTitle() %>"><%= i.getTitle() %></td>
				<td class="title" data-value="<%= i.getCallNo() %>"><%= i.getCallNo() %></td>
				<td class="title" data-value="<%= i.getYear() %>"><%= i.getYear() %></td>
				<td class="title" data-value="<%= i.getEdition() %>"><%= i.getEdition() %></td>

			</tr>
		<% 
				}

		%>
		</tbody>
	</table>
	
	
	