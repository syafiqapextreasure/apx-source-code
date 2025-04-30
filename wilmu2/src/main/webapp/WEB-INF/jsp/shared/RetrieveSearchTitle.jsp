<%@ page contentType="text/html; charset=UTF-8" %>

<%-- <%@ page import="com.kmlink.ilmu.shared.global.connection.DBConnection,  --%>
<%@ page import="com.kmlink.ilmu.shared.cataloging.Bibliography.*, 
					java.util.List, com.kmlink.ilmu.shared.cataloging.Template_Maint.*,
					com.kmlink.ilmu.shared.global.*,java.text.*,java.util.*"%>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/general/global.js"></script>	

	<table class="table table-bordered" id="title_result" data-toggle="table" data-search="true" 
				data-pagination="true" data-show-columns="true" data-click-to-select="true" style="height:auto">
				
		<thead>
		<%
			int count = 0;
			String searchType = request.getParameter("search_type");
			String criteria = request.getParameter("criteria");
			String action = request.getParameter("action");
			String tag = request.getParameter("tag");
			String buffer = request.getParameter("buffer");
			String patrid = request.getParameter("patrid");
			String startDate = request.getParameter("startDate");
			String stopDate = request.getParameter("endDate");
System.out.println(patrid+ criteria+ startDate+ stopDate + searchType);
			if(searchType.equals("ctrlNo")){
		%>
			<tr>
				<th data-sortable="true">Title</th>
				<th data-sortable="true">Control Number</th>
				<th data-sortable="true">Accession No</th>
			</tr>
		<%
			}else if(buffer!=null){
		%>
			<tr>
				<th data-sortable="true">Buffer No.</th>
				<th data-sortable="true">Title</th>
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
		
		if(action.equals("7") && buffer!=null){
			List<Bibliography> bufferResult = null;

			if(criteria == null)
			{
				out.println();
			}else
			{	

				if (searchType.equals("bufferNo"))
					bufferResult =Bibliography.getByBufferNo(criteria);
				else if (searchType.equals("tag"))
					bufferResult = Bibliography.getByBufferTag(tag, criteria);
				else if (searchType.equals("officerID"))
					bufferResult = Bibliography.getOfficerID_Buffer(patrid, criteria, startDate, stopDate);
			%>
			<%
			for(Bibliography i: bufferResult){
				count++;
				if(searchType.equals("bufferNo")){
			%>
				<tr  style="cursor:pointer" class="clickable-row" data-action="<%=action %>" data-bufferno="<%= criteria %>">
					<td class="pointer" data-value="<%= criteria %>">
						<%=criteria%>
					</td>
					<td class="title" data-value="<%= i.getTitle() %>">
						<%= i.getTitle() %>
					</td>
				</tr>
			<%
					}else{
			%>
				<tr  style="cursor:pointer" class="clickable-row" data-action="<%=action %>" data-bufferno="<%if((i.getBufferno())!=null){ %><%= i.getBufferno() %><%}else{%><%= criteria %><%}%>">
					<td class="pointer" data-value="<%if((i.getBufferno())!=null){ %><%= i.getBufferno() %><%}else{%><%= criteria %><%}%>">
						<%= i.getBufferno() %>
					</td>
					<td class="title" data-value="<%= i.getTitle() %>">
						<%= i.getTitle() %>
					</td>
				</tr>
		<% 			}
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
				}else{
				List<Bibliography> titleResult = null;
				System.out.println(searchType + criteria);
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
						titleResult = Bibliography.searchByIndx(criteria, "020");
					else if (searchType.equals("issn"))
						titleResult = Bibliography.searchByIndx(criteria, "022");
					else if (searchType.equals("notesArea"))
						titleResult = Bibliography.searchByIndx(criteria, "5%");
					else if (searchType.equals("ctrlNo"))
						titleResult = Bibliography.CT_SearchByAccno(criteria);
					else if (searchType.equals("tag"))
						titleResult = Bibliography.getInTag(tag, criteria);
					else if (searchType.equals("officerID"))
						titleResult = Bibliography.getOfficerID_Index(patrid, criteria, startDate, stopDate);
				
				/* 	else if (searchType.equals("officerID")&& type.equals("creator"))
						titleResult = Bibliography.getInTag(patrid, startDate, endDate); */
					/* else if (searchType.equals("officerID")&& searchType.equals("modifier"))
						titleResult = Bibliography.getInTag(tag, criteria); */
				%>
				<%
					for(Bibliography i: titleResult){
						count++;
						if(searchType.equals("ctrlNo")){
							System.out.println("title = " + i.getTitle());
							System.out.println("criteria = " + criteria);
							System.out.println("getBufferno = " + i.getBufferno());
				%>
				<tr  style="cursor:pointer" class="clickable-row" data-action="<%=action %>" data-matno="<%= criteria %>" data-docno="<%= i.getBufferno()%>">
					<td style="display:none" class="pointer" data-value="<%= criteria %>">
						<%= i.getMatno() %>
					</td>
					<td class="title" data-value="<%= i.getTitle() %>">
						<%= i.getTitle() %>
					</td>
					<td class="hits" data-value="<%= criteria %>">
						<%= criteria %>
					</td>
					<td>
						<%= i.getBufferno() %> <!-- docno  -->
					</td>
				</tr>
			<%
					}else{
			%>
			<tr  style="cursor:pointer" class="clickable-row" data-action="<%=action %>" data-value="<%if((i.getPointer())!=0){ %><%= i.getPointer() %><%}else{%><%= criteria %><%}%>">
				<td style="display:none" class="pointer" data-value="<%if((i.getPointer())!=0){ %><%= i.getPointer() %><%}else{%><%= criteria %><%}%>">
					<%= i.getMatno() %>
				</td>
				<td class="title" data-value="<%= i.getTitle() %>">
					<%= i.getTitle() %>
				</td>
				<td class="hits" data-value="<%if((i.getHits())!=0){ %><%= i.getHits() %><%}else{%><%= criteria %><%}%>">
					<%if((i.getHits())!=0){ %><%= i.getHits() %><%}else{ %><%= criteria %><%}%>
				</td>
			</tr>
		<% 			}
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
		}
		%>
		</tbody>
	</table>
	