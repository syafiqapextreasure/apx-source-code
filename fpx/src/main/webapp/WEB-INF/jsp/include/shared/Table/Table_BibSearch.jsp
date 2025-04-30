<%@ page contentType="text/html; charset=UTF-8" %>

<%@ page import="java.text.*,java.util.*, com.wilmu.cataloging.global.*"%>
<%-- <script type="text/javascript" src="${pageContext.request.contextPath}/js/general/Modal_BibSearch.js"></script>	 --%>
<%
String actiontype = request.getParameter("actiontype");
if(actiontype.equals("keyupCtrlNo")){
	String criteria = request.getParameter("criteria");
	List<GetCatSearchBib> titleResult = GetCatSearchBib.getSearchByMatno(criteria, "");
	// System.out.println(titleResult.length); 
	if (titleResult.isEmpty()){
		out.println("Error");
	}else{
		for(GetCatSearchBib i: titleResult){
			System.out.println("Title" + i.getBufferno());
			out.println(i.getBufferno());
		}
	}
}else if(actiontype.equals("keyupAccNo")){
	String criteria = request.getParameter("criteria");
	List<GetCatSearchBib> titleResult = GetCatSearchBib.CT_SearchByAccessionNo(criteria, "");
	// System.out.println(titleResult.length); 
	if (titleResult.isEmpty()){
		out.println("Error");
	}else{
		for(GetCatSearchBib i: titleResult){
			System.out.println("Title" + i.getRating());
			out.println(i.getRating());
		}
	}

}else{

%>
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
			String bibtype = request.getParameter("bibtype");
			if(searchType.equals("ctrlNo")){
		%>
			<tr>
				<th data-sortable="true">Title</th>
				<th data-sortable="true">Control Number</th>
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
			List<GetCatSearchBuffer> bufferResult = null;

			if(criteria == null)
			{
				out.println();
			}else
			{	
				
				if (searchType.equals("bufferNo")){
					bufferResult =GetCatSearchBuffer.getSearchByBufferno(criteria, bibtype);
				}else if (searchType.equals("tag")){
					bufferResult = GetCatSearchBuffer.getSearchByTag(tag, criteria, bibtype);
				}else if (searchType.equals("officerID")){
						bufferResult = GetCatSearchBuffer.getSearchOfficer(patrid, criteria, startDate, stopDate, bibtype);
				}else if (searchType.equals("MatNo")){
						bufferResult = GetCatSearchBuffer.getSearchByAccNo(criteria, bibtype);
				}
			%>
			<%
			for(GetCatSearchBuffer i: bufferResult){
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
				<tr  style="cursor:pointer" class="clickable-row" data-action="<%=action %>" data-bufferno="<%if((i.getMatno())!=null){ %><%= i.getMatno() %><%}else{%><%= criteria %><%}%>" data-type="<%=i.getMarc()%>" data-dismiss='modal'>
					<td class="pointer" data-value="<%if((i.getMatno())!=null){ %><%= i.getMatno() %><%}else{%><%= criteria %><%}%>">
						<%= i.getMatno() %>
					</td>
					<td class="title" data-value="<%= i.getTitle() %>" >
						<%= i.getTitle() %>
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
				List<GetCatSearchBib> titleResult = null;
				System.out.println(searchType + criteria);
				if(criteria == null)
				{
					out.println();
				}else
				{	
					if(searchType.equals("title"))
						titleResult = GetCatSearchBib.searchByTitle(criteria, bibtype);
					else if (searchType.equals("name"))
						titleResult = GetCatSearchBib.searchByName(criteria, bibtype);
					else if (searchType.equals("subject"))
						titleResult = GetCatSearchBib.searchBySubject(criteria, bibtype);
					else if (searchType.equals("publisher"))
						titleResult = GetCatSearchBib.searchByPubl(criteria, bibtype);
					else if (searchType.equals("placeOfPublication"))
						titleResult = GetCatSearchBib.searchByPubl(criteria, bibtype);
					else if (searchType.equals("yearOfPublication"))
						titleResult = GetCatSearchBib.searchByPubl(criteria, bibtype);
					else if (searchType.equals("series"))
						titleResult = GetCatSearchBib.searchBySeries(criteria, bibtype);
					else if (searchType.equals("callNo"))
						titleResult = GetCatSearchBib.searchByCallNo(criteria, bibtype);
					else if (searchType.equals("isbn"))
						titleResult = GetCatSearchBib.searchByIndx(criteria, "020", bibtype);
					else if (searchType.equals("issn"))
						titleResult = GetCatSearchBib.searchByIndx(criteria, "022", bibtype);
					else if (searchType.equals("notesArea"))
						titleResult = GetCatSearchBib.searchByIndx(criteria, "5%", bibtype);
					else if (searchType.equals("ctrlNo"))
						titleResult = GetCatSearchBib.getSearchByMatno(criteria, bibtype);
					else if (searchType.equals("tag"))
						titleResult = GetCatSearchBib.getSearchByTag(tag, criteria, bibtype);
					else if (searchType.equals("officerID"))
						titleResult = GetCatSearchBib.getSearchByOfficer(patrid, criteria, startDate, stopDate, bibtype);
					else if (searchType.equals("MatNo"))
						titleResult = GetCatSearchBib.CT_SearchByAccessionNo(criteria, bibtype);
				
	
				%>
				<%
					for(GetCatSearchBib i: titleResult){
						count++;
						if(searchType.equals("ctrlNo")){
				%>
				<tr  style="cursor:pointer" class="clickable-row" data-action="<%=action %>" data-matno="<%= criteria %>" data-dismiss="modal" data-type="<%=i.getTitle()%>">
					<td style="display:none" class="pointer" data-value="<%= criteria %>">
						<%= i.getMatno() %>
					</td>
					<td class="title" data-value="<%= i.getBufferno() %>" >
						<%= i.getBufferno() %>
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
					<td class="title" data-value="<%= i.getMatno() %>" >
						<%= i.getMatno() %>
					</td>
					<td class="hits" data-value="<%= criteria %>" >
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
		}
		%>
		</tbody>
	</table>
	<%
}
	%>
	
	
	