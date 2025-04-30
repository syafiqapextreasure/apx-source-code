<%@ page import="com.ilmu.global.connection.DBConnection, 
					com.ilmu.cataloging.Bibliography.*, 
					java.util.List, com.ilmu.cataloging.Template_Maint.*,
					com.ilmu.global.*,java.text.*,java.util.*"%>
					
<script type="text/javascript" src="${pageContext.request.contextPath}/js/general/global.js"></script>
	
	<table class="table table-hover" data-toggle="table" id="title_result" data-click-to-select="true">
		<thead>
		<%
			String searchType = request.getParameter("search_type");
			String criteria = request.getParameter("criteria");
			String action = request.getParameter("action");
			String condition = request.getParameter("condition");
			String values = request.getParameter("values");
			System.out.println(criteria);
			if(action.equals("5")){
		%>
			<tr>
				<th data-sortable="true">Title</th>
				<th data-sortable="true">Control Number</th>
			</tr>
		<%
			}else{
		%>
			<tr>
				<th data-sortable="true">Term</th>
				<th data-sortable="true">Hits</th>
			</tr>
		<%
			}
		%>
		</thead>
		<tbody>
		<% 
		List<Bibliography> titleResult = null;

		if(criteria == null)
		{
			out.println();
		}else
		{	
			if(searchType.equals("title"))
				titleResult = Bibliography.searchByTitle(criteria);
			else if (searchType.equals("name"))
				titleResult = Bibliography.searchByName(criteria);
			else if (searchType.equals("subject"))
				titleResult = Bibliography.searchBySubject(criteria);
			else if (searchType.equals("publisher"))
				titleResult = Bibliography.searchByPubl(criteria);
			else if (searchType.equals("placeOfPublication"))
				titleResult = Bibliography.searchByPubl(criteria);
			else if (searchType.equals("yearOfPublication"))
				titleResult = Bibliography.searchByPubl(criteria);
			else if (searchType.equals("series"))
				titleResult = Bibliography.searchBySeries(criteria);
			else if (searchType.equals("callNo"))
				titleResult = Bibliography.searchByCallNo(criteria);
			else if (searchType.equals("isbn"))
				titleResult = Bibliography.searchByIndx(criteria);
			else if (searchType.equals("issn"))
				titleResult = Bibliography.searchByIndx(criteria);
			else if (searchType.equals("notesArea"))
				titleResult = Bibliography.searchByIndx(criteria);
			else if (searchType.equals("bufferno"))
				titleResult =Bibliography.getByBufferNo(criteria);
			else if (searchType.equals("ctrlNo"))
				titleResult = Bibliography.CT_SearchByAccno(criteria);
			else if (searchType.equals("tag"))
				titleResult = Bibliography.getAllBy(condition,values,criteria);
		%>
		<%
			for(Bibliography i: titleResult){
				if(searchType.equals("ctrlNo")){
		%>
				<tr  style="cursor:pointer" class="clickable-row" data-action="<%=action %>" data-matno="<%if((criteria)!=null){ %><%= criteria %><%}else{%><%= criteria %><%}%>">
				<td style="display:none" class="pointer" data-value="<%if((i.getPointer())!=0){ %><%= i.getPointer() %><%}else{%><%= criteria %><%}%>"><%= i.getMatno() %></td>
				<td class="title" data-value="<%= i.getTitle() %>"><%= i.getTitle() %></td>
				<td class="hits" data-value="<%if((i.getHits())!=0){ %><%= i.getHits() %><%}else{%><%= criteria %><%}%>"><%if((i.getHits())!=0){ %><%= i.getHits() %><%}else{ %><%= criteria %><%}%></td>
			</tr>
		<%
				}else{
					System.out.println(searchType);
		%>
			<tr  style="cursor:pointer" class="clickable-row" data-action="<%=action %>" data-value="<%if((i.getPointer())!=0){ %><%= i.getPointer() %><%}else{%><%= criteria %><%}%>">
				<td style="display:none" class="pointer" data-value="<%if((i.getPointer())!=0){ %><%= i.getPointer() %><%}else{%><%= criteria %><%}%>"><%= i.getMatno() %></td>
				<td class="title" data-value="<%= i.getTitle() %>"><%= i.getTitle() %></td>
				<td class="hits" data-value="<%if((i.getHits())!=0){ %><%= i.getHits() %><%}else{%><%= criteria %><%}%>"><%if((i.getHits())!=0){ %><%= i.getHits() %><%}else{ %><%= criteria %><%}%></td>
			</tr>
		<% 			}
				}
		}
		%>
		</tbody>
	</table>
	