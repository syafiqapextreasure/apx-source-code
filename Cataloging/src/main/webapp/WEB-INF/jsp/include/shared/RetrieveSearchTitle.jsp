<%@ page contentType="text/html; charset=UTF-8" %>

<%@ page import="com.ilmu.global.connection.DBConnection, 
					com.ilmu.cataloging.Bibliography.*, 
					java.util.List, com.ilmu.cataloging.Template_Maint.*,
					com.ilmu.global.*,java.text.*,java.util.*"%>


<script type="text/javascript" src="${pageContext.request.contextPath}/js/general/global.js"></script>	
<style>
.selected {
    background-color: #B0BED9;
}
</style>
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
			String type = request.getParameter("type");
			System.out.println(type + "Type");
			String module = request.getParameter("module");
			
			if(searchType.equals("ctrlNo")){
		%>
			<tr>
				<th data-sortable="true">Title</th>
				<th data-sortable="true">Control Number</th>
			</tr>
			
		<%}else if(searchType.equals("Accno")){
		%>
		<tr>
				<th data-sortable="true">Title</th>
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

				if (searchType.equals("bufferNo")){
					bufferResult =Bibliography.getByBufferNo(criteria, type, Handler.tblName(module));
				}else if (searchType.equals("tag")){
					bufferResult = Bibliography.getByBufferTag(tag, criteria, type, Handler.tblName(module));
				}else if (searchType.equals("officerID")){
						bufferResult = Bibliography.getOfficerID_Buffer(patrid, criteria, startDate, stopDate, type);
				}else if (searchType.equals("MatNo")){
						bufferResult = Bibliography.getByBufferAccNo(criteria, type, Handler.tblName(module));
				}else if (searchType.equals("Accno")){
					bufferResult = Bibliography.getBufferByAccNo(criteria, type, Handler.tblName(module));
			}
			%>
			<%
			for(Bibliography i: bufferResult){
				count++;
				
				/* if(searchType.equals("bufferNo")){
					System.out.println("Matno" +i.getBufferno());
					System.out.println("Title" + i.getTitle()); */
			%>
				<%-- <tr  style="cursor:pointer" class="clickable-row" data-action="<%=action %>" data-bufferno="<%= i.getBufferno() %>">
					<td class="pointer" data-value="<%= i.getBufferno() %>">
						<%=criteria%>
					</td>
					<td class="title" data-value="<%= i.getTitle() %>">
						<%= i.getTitle() %>
					</td>
				</tr> --%>
			<%
					//}else{
			%>
				<tr  style="cursor:pointer" class="clickable-row" data-action="<%=action %>" data-bufferno="<%if((i.getRating())!=null){ %><%= i.getRating() %><%}else{%><%= criteria %><%}%>" data-type="<%=i.getPatrName()%>" data-dismiss='modal'>
					<td class="pointer" data-value="<%if((i.getRating())!=null){ %><%= i.getRating() %><%}else{%><%= criteria %><%}%>">
						<%= i.getRating() %>
					</td>
					<td class="title" data-value="<%= i.getPatrID() %>" >
						<%= i.getPatrID() %>
					</td>
				</tr>
		<% 			//}
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
				System.out.println("cHECK" + criteria);
				if(criteria == null)
				{
					out.println();
				}else
				{	
					System.out.println("T" + criteria);

					if(searchType.equals("title"))
						titleResult = Bibliography.searchByTitle(criteria, module, type);
					else if (searchType.equals("name"))
						titleResult = Bibliography.searchByName(criteria, module, type);
					else if (searchType.equals("subject"))
						titleResult = Bibliography.searchBySubject(criteria, module, type);
					else if (searchType.equals("publisher"))
						titleResult = Bibliography.searchByPubl(criteria, module, type);
					else if (searchType.equals("placeOfPublication"))
						titleResult = Bibliography.searchByPubl(criteria, module, type);
					else if (searchType.equals("yearOfPublication"))
						titleResult = Bibliography.searchByPubl(criteria, module, type);
					else if (searchType.equals("series"))
						titleResult = Bibliography.searchBySeries(criteria, module, type);
					else if (searchType.equals("callNo"))
						titleResult = Bibliography.searchByCallNo(criteria, module, type);
					else if (searchType.equals("isbn"))
						titleResult = Bibliography.searchByIndx(criteria, "020", type);
					else if (searchType.equals("issn"))
						titleResult = Bibliography.searchByIndx(criteria, "022", type);
					else if (searchType.equals("notesArea"))
						titleResult = Bibliography.searchByIndx(criteria, "5%", type);
					else if (searchType.equals("ctrlNo"))
						titleResult = Bibliography.CT_SearchByAccno(criteria, type);
					else if (searchType.equals("tag"))
						titleResult = Bibliography.getInTag(tag, criteria, module, type);
					else if (searchType.equals("officerID"))
						titleResult = Bibliography.getOfficerID_Index(patrid, criteria, startDate, stopDate, type);
					else if (searchType.equals("MatNo"))
						titleResult = Bibliography.CT_SearchByAccessionNo(criteria, type);
					else if (searchType.equals("Accno"))
						titleResult = Bibliography.CT_SearchByAccessionNo(criteria, type);
				
				/* 	else if (searchType.equals("officerID")&& type.equals("creator"))
						titleResult = Bibliography.getInTag(patrid, startDate, endDate); */
					/* else if (searchType.equals("officerID")&& searchType.equals("modifier"))
						titleResult = Bibliography.getInTag(tag, criteria); */
				%>
				<%
					for(Bibliography i: titleResult){
						count++;
						System.out.println(searchType+"titleSubfield" +  i.getBufferno() );
						if(searchType.equals("ctrlNo")){
				%>
				<tr  style="cursor:pointer" class="clickable-row" data-action="<%=action %>" data-matno="<%= criteria %>" data-dismiss="modal" data-type="<%=i.getTitle()%>">
					<td style="display:none" class="pointer" data-value="<%= criteria %>">
						<%= i.getMatno() %>
					</td>
					<td class="title" data-value="<%= i.getBufferno() %>" >
						<%= (i.getBufferno()).replace("<","&#60").replace(">","&#62") %>
					</td>
					<td class="hits" data-value="<%= criteria %>" >
						<%= criteria %>
					</td>
				</tr>
			<%}else if(searchType.equals("MatNo") ){
				%>
				<tr  style="cursor:pointer" class="clickable-row" data-action="<%=action %>" data-matno="<%=i.getPatrName() %>" 
						data-dismiss="modal" data-type="<%=i.getTitle()%>" data-search="matno" data-accno="<%= criteria %>">
					<td style="display:none" class="pointer" data-value="<%= criteria %>">
						<%= criteria %>
					</td>
					<td class="title" data-value="<%= i.getRating() %>" >
						<%= i.getRating() %>
					</td>
					<td class="hits" data-value="<%= criteria %>" >
						<%= criteria %>
					</td>
				</tr>
				
				<%}else if(searchType.equals("Accno") ){
				%>
				<tr  style="cursor:pointer" class="clickable-row" data-action="<%=action %>" data-matno="<%=i.getPatrName() %>" 
						data-dismiss="modal" data-type="<%=i.getTitle()%>" data-search="matno" data-accno="<%= criteria %>">
					<td style="display:none" class="pointer" data-value="<%= criteria %>">
						<%= criteria %>
					</td>
					<td class="title" data-value="<%= i.getRating() %>" >
						<%= i.getRating() %>
					</td>
					<td class="hits" data-value="<%= criteria %>" >
						<%= criteria %>
					</td>
				</tr>
			<%
					}else{
						System.out.println("heeeeeeeee"+i.getTitle());
			%>
			<tr  style="cursor:pointer" class="clickable-row" data-action="<%=action %>" data-value="<%if((i.getPointer())!=0){ %><%= i.getPointer() %><%}else{%><%= criteria %><%}%>">
				<%-- <td style="display:none" class="pointer" data-value="<%if((i.getPointer())!=0){ %><%= i.getPointer() %><%}else{%><%= criteria %><%}%>">
					<%= i.getMatno() %>
				</td> --%>
				<td class="title" data-value="<%= i.getTitle().replace("<","&#60").replace(">","&#62")  %>">
			
					<%= i.getTitle().replace("<","&#60").replace(">","&#62")  %>
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
	