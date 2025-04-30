	<!-- Java class-->
	<%@ page import="com.ilmu.global.connection.DBConnection, com.ilmu.cataloging.Bibliography.Bibliography, java.util.List"%>
	
	<!-- Bootstrap table -->
	<!-- <script src="lib/bootstrap-table/bootstrap-table.js" type="text/javascript"></script> -->
	<link rel="stylesheet" href="lib/bootstrap-table/bootstrap-table.css" type="text/css"></link>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
<script>
$(document).ready(function(){
	$('.clickable-row').click(function() {
		// Hide the modal
	/* 
		$('#titleSearch').modal('hide');
		$('#vendorModal').modal('hide'); */
	
		var matno = $(this).find(".matno").text();
		var title = $(this).find(".title").text();
		// Replace value
		if(!(document.getElementById('matno') === null))
		{
			document.getElementById('matno').value = matno;
		}
		
		if(!(document.getElementById('CT03MATNO') === null)){
			document.getElementById('CT03MATNO').value=matno;
		}
		
		if(!(document.getElementById('CT05SRAW') === null)){
			document.getElementById('CT05SRAW').innerHTML = title;
		}
		if(!(document.getElementById('title') === null))
		{
			document.getElementById('title').innerHTML = title;
		}
		if(!(document.getElementById('controlNo') === null))
		{
			document.getElementById('controlNo').value = matno;
		}
		// Set focus
		
		//setTimeout(function() {
			//document.getElementById('matno').focus()
			//document.getElementById('title').focus()
			document.getElementById('controlNo').focus()
		//}, 600);
		
	});
});
</script>
	<!-- <script>
	// Code making table row clickable
	// Have to place here otherwise the code won't work
	$(document).ready(function() {

		$('#title_result').bootstrapTable();
		
		$('.clickable-row').click(function() {
			// Hide the modal
			alert("1");
			$('#titleSearch').modal('hide');
			$('#vendorModal').modal('hide');
			
			var matno = $(this).find(".matno").text();
			var title = $(this).find(".title").text();

			// Replace value
			if(!(document.getElementById('matno') === null))
			{
				document.getElementById('matno').value = matno;
			}
			if(!(document.getElementById('title') === null))
			{
				document.getElementById('title').innerHTML = title;
			}
			if(!(document.getElementById('controlNo') === null))
			{
				document.getElementById('controlNo').value = matno;
			}
			// Set focus
			
			//setTimeout(function() {
				//document.getElementById('matno').focus()
				//document.getElementById('title').focus()
				document.getElementById('controlNo').focus()
			//}, 600);
			
		});
	});
	</script> -->
	
	<table class="table table-hover" data-toggle="table" id="title_result" data-click-to-select="true">
		<thead>
			<tr>
				<!-- <th data-sortable="true">Control No.</th> -->
				<th data-sortable="true">Title</th>
			</tr>
		</thead>
		<tbody>
		<% 
		String searchType = request.getParameter("search_type");
		String criteria = request.getParameter("criteria");
		
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
			/* else if (searchType.equals("isbn"))
				titleResult = Bibliography.searchByIndx(criteria);
			else if (searchType.equals("issn"))
				titleResult = Bibliography.searchByIndx(criteria);
			else if (searchType.equals("notesArea"))
				titleResult = Bibliography.searchByIndx(criteria);
			else if (searchType.equals("bufferno")) */
				titleResult =Bibliography.getByBufferNo(criteria);
		%>
		<%
			for(Bibliography i: titleResult){
		%>
			<tr id="matNoRow" class="clickable-row">
			<td class="matno" data-value="<%= i.getMatno() %>"><%= i.getMatno() %></td>
				<td class="title" data-value="<%= i.getTitle() %>"><%= i.getTitle() %></td>
			</tr>
		<% 
				}
		}
		%>
		</tbody>
	</table>