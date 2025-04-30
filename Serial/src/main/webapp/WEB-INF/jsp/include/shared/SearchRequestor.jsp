<%@ page import="com.ilmu.global.connection.*, com.ilmu.global.*, java.util.List"%>
	
	<%
		String action = request.getParameter("action");
		List<Requestor> reqResult = null;
		String criteria = request.getParameter("criteria");
		System.out.println("Action" + action);
	if(action.equals("keyup")){
		reqResult = Requestor.searchByRequestorID(criteria);
		for(Requestor i: reqResult){
			out.println(i.getName());
		}
	}else if(action.equals("blur")){
		reqResult = Requestor.searchByRequestorIDBlur(criteria);
		for(Requestor i: reqResult){
			out.println(i.getName());
		}
	}else{
	%>
	<script>
	// Code making table row clickable
	// Have to place here otherwise the code won't work
	$(document).ready(function() {
		$('#reqResult').DataTable({
		    responsive: true
		});
		$('.clickable-row').click(function() {
			// Hide the modal
			$('#requestorSearch').modal('hide');
			// Replace value
			document.getElementById('requestor').value = $(this).data('value');
			// Set focus
			//setTimeout(function() {
				document.getElementById('requestor').focus()
			//}, 600);
		});
	});
	</script>
	
	<table class="table table-hover" data-toggle="table" id="reqResult" data-click-to-select="true">
		<thead>
			<tr>
				<th data-sortable="true">ID</th>
				<th data-sortable="true">Name</th>
				<th data-sortable="true">Category</th>
			</tr>
		</thead>
		<tbody>
		<% 
		String searchType = request.getParameter("search_type");
		

		if(criteria == null)
		{
			out.println();
		}else
		{
			if(searchType.equals("reqID"))
				reqResult = Requestor.searchByRequestorID(criteria);
			else if (searchType.equals("reqName"))
				reqResult = Requestor.searchByRequestorName(criteria);
			else if (searchType.equals("newIC"))
				reqResult = Requestor.searchByRequestorNewIC(criteria);
			else if (searchType.equals("oldIC"))
				reqResult = Requestor.searchByRequestorOldIC(criteria);
		%>
		<%
			for(Requestor i: reqResult){
		%>
			<tr class="clickable-row" data-value="<%= i.getCode() %>">
				<td><%= i.getCode() %></td>
				<td><%= i.getName() %></td>
				<td><%= i.getCategory() %></td>
			</tr>
		<%
			}
		}
		%>
		</tbody>
	</table>
	<%
	}
	%>