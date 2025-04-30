<%@ page contentType="text/html; charset=UTF-8" %>

<%@ page import="com.ilmu.global.connection.DBConnection, 
					com.ilmu.Authority.AuthorityGlobal.*,
					java.util.List, com.ilmu.cataloging.Template_Maint.*,
					com.ilmu.global.*,java.text.*,java.util.*"%>
					<head>

 <script type="text/javascript" src="<%= request.getContextPath() %>/plugin/datatables/jquery.dataTables.min.js"></script>
 <script type="text/javascript" src="<%= request.getContextPath() %>/plugin/datatables/dataTables.bootstrap.min.js"></script>
 <link rel="stylesheet" href="<%= request.getContextPath() %>/plugin/datatables/dataTables.bootstrap.css">
 <script type="text/javascript" src="${pageContext.request.contextPath}/js/Authority/GlobalChange.js"></script>					
  <title>Charging</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/general/global.js"></script>	
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/general/Search_Modal.js"></script>
	<style>
	tr.selected td{background-color:gainsboro;}
	</style>
</head>
<script>
  	$(document).ready(function () {
  		$("[title]").tooltip();
	$('#rglobalchgtbl').DataTable({
	    responsive: true,
	});

});
	</script>
	
	 <table class="table table-bordered" id="rglobalchgtbl" >
				<col width="5%">
				<col width="10%">
				<col width="10%">
					<thead>
						<tr>
							<th data-sortable="true" style="text-align: center;">No.</th>
							<th data-sortable="true" style="text-align: center;">Term Type</th>
							<th style="text-align: center;">Hits</th>
							<th style="text-align: center;">Bibliography Details</th>
						</tr>
					</thead>
					 <tbody>
	<%
			int count = 0;
			String criteria = request.getParameter("criteria");
			String tag = request.getParameter("tag");
		%>
			
				<%
				List<Global_Retrival> titleResult = null;
				String classes = "";
			
				if(criteria == null)
				{
					out.println();
				}else
				{	
					titleResult = Global_Retrival.getInTag(tag, criteria);
				
				%>
				<%
					for(Global_Retrival i: titleResult){
						count++;
				%>
				<tr  style="cursor:pointer" class="clickable-row" data-pointer="<%=i.getPointer()%>">
					<td><%=count %></td>
					<%
						String term = "";
						if( i.getAuthLink().equals("T")){
							term = "Used";
						}else{
							term = "Unused";
						}
					%>
					<td><%= term %></td>
					<td><%= i.getHits() %></td>
					<td><%= i.getTitle() %></td>
				</tr>
		
			
		<% 			}
		if (count==0){
			%>
			<tr class="clickable-row1">
					<td class="pointer" colspan="4">
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