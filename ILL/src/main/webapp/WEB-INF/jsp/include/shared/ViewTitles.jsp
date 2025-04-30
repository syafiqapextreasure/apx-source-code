<%@ page import="com.ilmu.global.connection.DBConnection, 
					com.ilmu.cataloging.Bibliography.*, 
					java.util.List, com.ilmu.cataloging.Template_Maint.*,
					com.ilmu.global.*,java.text.*,java.util.*"%>

<meta charset="ISO-8859-1">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/general/global.js"></script>	
<script type="text/javascript" src="<%= request.getContextPath() %>/plugin/datatables/jquery.dataTables.min.js"></script>
 <script type="text/javascript" src="<%= request.getContextPath() %>/plugin/datatables/dataTables.bootstrap.min.js"></script>
 <link rel="stylesheet" href="<%= request.getContextPath() %>/plugin/datatables/dataTables.bootstrap.css">
 
 <script>
 //$(document).ready(function() {
	 var getAction = $("#action").val();
	 
	 if(getAction == '8'){
		//$('td:nth-child(0),th:nth-child(0)').hide();
		// $('#view-result12 td:nth-child(0)').hide();
		 $('#view-result12 tr').find('td:nth-child(6),th:nth-child(6)').toggle();
		
	 }else if(getAction == '15'){
		 $('td:nth-child(2),th:nth-child(2)').hide();
	 }else if(getAction == '20'){
		
		 $('td:nth-child(6),th:nth-child(6)').hide();
		 $('td:nth-child(7),th:nth-child(7)').hide();
		 $('td:nth-child(8),th:nth-child(8)').hide();
	 }else{
		 $('td:nth-child(6),th:nth-child(6)').hide();
		 $('td:nth-child(7),th:nth-child(7)').hide();
	 }
	 
	//} );
 </script>
<body>
	<%
		String action = request.getParameter ("action");
	%>
	<input type="hidden" id="action" value="<%=action%>">
	<table class="table table-hover" id="view-result12" data-toggle="table" data-search="true" 
				data-pagination="true" data-show-columns="true" data-click-to-select="true">
		<thead>
			<tr>
				<th data-sortable="true">Title</th>
				<th data-sortable="true">Call Number</th>
				<th data-sortable="true">Publication</th>
				<th data-sortable="true">Year</th>
				<th data-sortable="true">Edition</th>
				<th data-sortable="true">Accession No</th>
				<th data-sortable="true">Item Category</th>
				<th data-sortable="true">Status</th>
			</tr>
		</thead>
		<tbody>
		<% 
		String pointer = request.getParameter("pointer");
		
		String patrid = request.getParameter("patrid");
		String search_type = request.getParameter("search_type");
		System.out.println(action + " = ACTION2");
		
		List<Bibliography> titleResult = null;
		
			if(action.equals("15")){
				System.out.println("IS 15");
				titleResult = Bibliography.getAllRaw2(pointer);
				

				for(Bibliography i: titleResult){
					System.out.println(i.getTitle() + " i.getTitle()");
					System.out.println(i.getCallNo() + " i.getCallNo()");
		%>
				<tr  style="cursor:pointer" class="clickable-row" id="searchBtn1" data-action="<%=action%>" data-toggle="modal" data-matno="<%= i.getMatno()%>" data-docno="<%= i.getDOCNO()%>">
			
				<td class="title" data-value="<%= i.getTitle() %>"><%= i.getTitle() %></td>
				<td class="title" data-value="<%= i.getCallNo() %>"><%= i.getCallNo() %></td>
				<td class="title" data-value="<%= i.getYear() %>"><%= i.getYear() %></td>
				<td class="title" data-value="<%= i.getPublication() %>"><%= i.getPublication() %></td>
				<td class="title" data-value="<%= i.getEdition() %>"><%= i.getEdition() %></td>
				<td class="title" data-value="<%= i.getDOCNO() %>"><%= i.getDOCNO() %></td>
				<td class="title" data-value="<%= i.getITEMSTAT() %>"><%= i.getITEMSTAT() %></td>
				<td class="title" data-value="<%= i.getSTATUS() %>"><%= i.getSTATUS() %></td>

			</tr>
		<% 
				}
			}else if(action.equals("8")){
				System.out.println("IS 8");
				titleResult = Bibliography.getAllRaw(pointer);
				for(Bibliography i: titleResult){
		%>
				
				
				<tr  style="cursor:pointer" class="clickable-row" id="searchBtn1" data-action="<%=action%>" data-toggle="modal" data-matno="<%= i.getMatno()%>" data-docno="<%= i.getDOCNO()%>">
			
				<td class="title" data-value="<%= i.getTitle() %>"><%= i.getTitle() %></td>
				<td class="title" data-value="<%= i.getCallNo() %>"><%= i.getCallNo() %></td>
				<td class="title" data-value="<%= i.getYear() %>"><%= i.getYear() %></td>
				<td class="title" data-value="<%= i.getPublication() %>"><%= i.getPublication() %></td>
				<td class="title" data-value="<%= i.getEdition() %>"><%= i.getEdition() %></td>
				<td class="title" data-value="<%= i.getDOCNO() %>"><%= i.getDOCNO() %></td>

				</tr>
		<%
					
				}
			}
			//titleResult = Bibliography.getAllRaw(pointer);
			else if(action.equals("20")){
				System.out.println("IS 20");
				titleResult = Bibliography.getAllRaw(pointer);
				for(Bibliography i: titleResult){
		
		%>
			<tr  style="cursor:pointer" class="clickable-row" id="searchBtn1" data-action="<%=action%>" data-toggle="modal" data-matno="<%= i.getMatno()%>">
			
				<td class="title" data-value="<%= i.getTitle() %>"><%= i.getTitle() %></td>
				<td class="title" data-value="<%= i.getCallNo() %>"><%= i.getCallNo() %></td>
				<td class="title" data-value="<%= i.getYear() %>"><%= i.getYear() %></td>
				<td class="title" data-value="<%= i.getPublication() %>"><%= i.getPublication() %></td>
				</tr>
		<%	
				}
			}
		
		%>
		<%-- <%
			for(Bibliography i: titleResult){
				System.out.println(i.getTitle() + " i.getTitle()");
				System.out.println(i.getCallNo() + " i.getCallNo()");
		%>
			<tr  style="cursor:pointer" class="clickable-row" id="searchBtn1" data-action="<%=action%>" data-toggle="modal" data-matno="<%= i.getMatno()%>" data-docno="<%= i.getDOCNO()%>">
			
				<td class="title" data-value="<%= i.getTitle() %>"><%= i.getTitle() %></td>
				<td class="title" data-value="<%= i.getCallNo() %>"><%= i.getCallNo() %></td>
				<td class="title" data-value="<%= i.getYear() %>"><%= i.getYear() %></td>
				<td class="title" data-value="<%= i.getPublication() %>"><%= i.getPublication() %></td>
				<td class="title" data-value="<%= i.getEdition() %>"><%= i.getEdition() %></td>
				<td class="title" data-value="<%= i.getDOCNO() %>"><%= i.getDOCNO() %></td>

			</tr>
		<% 
				}
		%> --%>
		</tbody>
	</table>
</body>
	
	