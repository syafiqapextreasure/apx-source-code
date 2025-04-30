	<!-- Java class-->
	<%@ page import="com.ilmu.global.*,
					java.util.*"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/general/Term_Search.js"></script>	
	<table class="table table-hover" data-toggle="table" id="title_result" data-click-to-select="true">
		<thead>
			<tr>
				<th data-sortable="true">Term</th>
				<th data-sortable="true">Hits</th>
			</tr>
		</thead>
		<tbody>
		<% 
		int count = 0;
		String tag = request.getParameter("tag");
		String criteria = request.getParameter("criteria");
		
		List<SearchRecord> searchResult = null;
		
		if(criteria == null)
		{
			out.println();
		}else
		{	
			searchResult = SearchRecord.getByTermSearch(criteria, tag);
		%>
		<%
			for(SearchRecord i: searchResult){
				count++;
		%>
			<tr class="clickable-row1" style="cursor:pointer" data-value="<%= i.getRaw() %>" data-tag="<%=tag%>">
				<td style="display:none" class="pointer" data-value=""></td>
				<td class="title" data-value="<%= i.getTitle() %>"><%= i.getTitle() %></td>
				<td class="hits" data-value="<%= i.getHits() %>"><%= i.getHits() %></td>
			</tr>
		<% 
				}
		if (count==0){
		%>
		<tr class="clickable-row1">
				<td class="pointer" colspan="2">
					<div class="alert alert-info" align="center">
						<font style="color:black;">No record found</font>
					</div>
				</td>
			</tr>
		<%
		}
		}
		%>
		</tbody>
	</table>
	