<%@ page contentType="text/html; charset=UTF-8" %>

<%@ page import="com.ilmu.global.connection.DBConnection, 
					com.ilmu.serial.serial_master.*, 
					java.util.List, com.ilmu.cataloging.Template_Maint.*,
					com.ilmu.global.*,java.text.*,java.util.*"%>


<script type="text/javascript" src="${pageContext.request.contextPath}/js/general/Search_Modal.js"></script>	

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
				List<RetrieveSerialList> titleResult = null;

				if(criteria == null)
				{
					out.println();
				}else
				{	
					if(searchType.equals("title"))
						titleResult = RetrieveSerialList.searchByTitle(criteria);
					else if (searchType.equals("name"))
						titleResult = RetrieveSerialList.searchByName(criteria);
					else if (searchType.equals("subject"))
						titleResult = RetrieveSerialList.searchBySubject(criteria);
					else if (searchType.equals("publisher"))
						titleResult = RetrieveSerialList.searchByPubl(criteria);
					else if (searchType.equals("placeOfPublication"))
						titleResult = RetrieveSerialList.searchByPubl(criteria);
					else if (searchType.equals("yearOfPublication"))
						titleResult = RetrieveSerialList.searchByPubl(criteria);
					else if (searchType.equals("series"))
						titleResult = RetrieveSerialList.searchBySeries(criteria);
					else if (searchType.equals("callNo"))
						titleResult = RetrieveSerialList.searchByCallNo(criteria);
					else if (searchType.equals("isbn"))
						titleResult = RetrieveSerialList.searchByIndx(criteria, "020");
					else if (searchType.equals("issn"))
						titleResult = RetrieveSerialList.searchByIndx(criteria, "022");
					else if (searchType.equals("notesArea"))
						titleResult = RetrieveSerialList.searchByIndx(criteria, "5%");
					else if (searchType.equals("ctrlNo"))
						titleResult = RetrieveSerialList.CT_SearchByAccno(criteria);
					else if (searchType.equals("tag"))
						titleResult = RetrieveSerialList.getInTag(tag, criteria);
					else if (searchType.equals("officerID"))
						titleResult = RetrieveSerialList.getOfficerID_Index(patrid, criteria, startDate, stopDate);
				
				/* 	else if (searchType.equals("officerID")&& type.equals("creator"))
						titleResult = RetrieveSerialList.getInTag(patrid, startDate, endDate); */
					/* else if (searchType.equals("officerID")&& searchType.equals("modifier"))
						titleResult = RetrieveSerialList.getInTag(tag, criteria); */
				%>
				<%
					for(RetrieveSerialList i: titleResult){
						count++;
						if(searchType.equals("ctrlNo")){
				%>
				<tr  style="cursor:pointer" class="clickable-row" data-action="<%=action %>" data-matno="<%= criteria %>">
					<td style="display:none" class="pointer" data-value="<%= criteria %>">
						<%= i.getMatno() %>
					</td>
					<td class="title" data-value="<%= i.getTitle() %>">
						<%= i.getTitle() %>
					</td>
					<td class="hits" data-value="<%= criteria %>">
						<%= criteria %>
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
		%>
		</tbody>
	</table>
	