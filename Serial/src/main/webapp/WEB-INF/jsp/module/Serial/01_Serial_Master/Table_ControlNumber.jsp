	<!-- Java class-->
	<%@ page import="icms.general.DBConnect, icms.periodicals.PeriodicalsMaster, java.util.List"%>
	
	<!-- Bootstrap table -->
	<script src="../../../../lib/bootstrap-table/bootstrap-table.js" type="text/javascript"></script>
	<link rel="stylesheet" href="../../../../lib/bootstrap-table/bootstrap-table.css" type="text/css"></link>
	
	<script>
	// Code making table row clickable
	// Have to place here otherwise the code won't work
	$(document).ready(function() {
		$('#title_result').bootstrapTable();
		
		$('.clickable-row').click(function() {
			var controlNoInput = $(this).find(".controlNoInput").text();
			// Hide the modal
			$('#titleSearch').modal('hide');
			replaceLoader("#div-result");
			$.get("Table_PeriodicalsMaster.jsp",{controlNoInput : controlNoInput},function(data){
				$("#div-result").html(data);
				$("#txt-searchAll").val("no");
			});	
		});
	});
	</script>
	
	<table class="table table-hover" data-toggle="table" id="title_result" data-click-to-select="true">
		<thead>
			<tr>
				<th data-sortable="true">Control No.</th>
				<th data-sortable="true">Title</th>
			</tr>
		</thead>
		<tbody>
		<% 
		String searchType = request.getParameter("search_type");
		String criteria = request.getParameter("criteria");
		
		List<PeriodicalsMaster> titleResult = null;
		
		if(criteria == null)
		{
			out.println();
		}else
		{	
			if(searchType.equals("title"))
				titleResult = PeriodicalsMaster.searchByTitle(criteria);
			else if (searchType.equals("name"))
				titleResult = PeriodicalsMaster.searchByName(criteria);
			else if (searchType.equals("subject"))
				titleResult = PeriodicalsMaster.searchBySubject(criteria);
			else if (searchType.equals("publisher"))
				titleResult = PeriodicalsMaster.searchByPubl(criteria);
			else if (searchType.equals("placeOfPublication"))
				titleResult = PeriodicalsMaster.searchByPlaceOfPubl(criteria);
			else if (searchType.equals("yearOfPublication"))
				titleResult = PeriodicalsMaster.searchByYearOfPubl(criteria);
			else if (searchType.equals("series"))
				titleResult = PeriodicalsMaster.searchBySeries(criteria);
			else if (searchType.equals("callNo"))
				titleResult = PeriodicalsMaster.searchByCallNo(criteria);
			else if (searchType.equals("isbn"))
				titleResult = PeriodicalsMaster.searchByISBN(criteria);
			else if (searchType.equals("issn"))
				titleResult = PeriodicalsMaster.searchByISSN(criteria);
			else if (searchType.equals("notesArea"))
				titleResult = PeriodicalsMaster.searchByNoteArea(criteria);
		%>
		<%
			for(PeriodicalsMaster i: titleResult){
		%>
			<tr class="clickable-row">
				<td class="controlNoInput" data-value="<%= i.getControlNo() %>"><%= i.getControlNo() %></td>
				<td class="title" data-value="<%= i.getTitle() %>"><%= i.getTitle() %></td>
			</tr>
		<% 
				}
		}
		%>
		</tbody>
	</table>