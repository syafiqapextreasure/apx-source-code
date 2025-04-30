<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.text.*,java.util.*, com.wilmu.cataloging.global.*, com.wilmu.cataloging.bibliographic.data.*"%>

<meta charset="UTF-8">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/general/Modal_BibSearch.js"></script>	
<script type="text/javascript" src="<%= request.getContextPath() %>/plugin/datatables/jquery.dataTables.min.js"></script>
 <script type="text/javascript" src="<%= request.getContextPath() %>/plugin/datatables/dataTables.bootstrap.min.js"></script>
 <link rel="stylesheet" href="<%= request.getContextPath() %>/plugin/datatables/dataTables.bootstrap.css">
		
	<table class="table table-hover" id="view-result12" data-toggle="table" data-search="true" 
				data-pagination="true" data-show-columns="true" data-click-to-select="true">
		<thead>
			<tr>
				<th data-sortable="true">Title</th>
				<th data-sortable="true">Call Number</th>
				<th data-sortable="true">Publication</th>
				<th data-sortable="true">Year</th>
				<th data-sortable="true">Edition</th>
			</tr>
		</thead>
		<tbody>
		<% 
		String pointer = request.getParameter("pointer");
		String action = request.getParameter ("action");
		String patrid = request.getParameter("patrid");
		String search_type = request.getParameter("search_type");
		List<ISBD> callisbd = ISBD.getPunctuation("090");
		List<GetCatBibByPointer> titleResult = null;


			titleResult = GetCatBibByPointer.getCatBibByPointer(pointer);
		
		%>
		<%
			for(GetCatBibByPointer i: titleResult){
		%>
			<tr  style="cursor:pointer" class="clickable-row" id="searchBtn1" data-action="<%=action%>" data-toggle="modal" data-matno="<%= i.getMatno() %>" data-dismiss='modal' data-type = <%=i.getType() %>>
			
				<td class="title" data-value="<%= i.getTitle() %>"><%= i.getTitle() %></td>
				<td class="title" data-value="<%= ISBD.getSubfield(i.getCallNo(), callisbd) %>"><%=ISBD.getSubfield(i.getCallNo(), callisbd) %></td>
				<td class="title" data-value="<%= i.getYear() %>"><%= i.getYear() %></td>
				<td class="title" data-value="<%= i.getPublication() %>"><%= i.getPublication() %></td>
				<td class="title" data-value="<%= i.getEdition() %>"><%= i.getEdition() %></td>

			</tr>
		<% 
				}

		%>
		</tbody>
	</table>
	
	
	