	<%@ page contentType="text/html; charset=UTF-8" %>
	<%@ page import="com.ilmu.circulation.Charging.*, com.ilmu.circulation.Weeding.*, java.util.List"%>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/Weeding.js"></script>	
<script>

 $(document).ready(function() {
	
	 $('#weeding_enquiry').DataTable({
	       responsive: true
	   });
	 
}); 
</script>
		<table class="table table-responsive" id="weeding_enquiry">	
           		<thead>
           			<tr> 
           				<th data-sortable="true">No.</th>
           				<th data-sortable="true">Accession No.</th>
           				<th data-sortable="true">Title</th>
           				<th data-sortable="true">Weed Date</th>
           				<th data-sortable="true">Action</th>
           			</tr>
           		</thead>
	
		<tbody>
		<% 
		String searchType = request.getParameter("search");
		String searchType1 = request.getParameter("search1");
		String criteria = request.getParameter("criteria");
		
		List<Weeding> weedingenqury = null;
		//List<Vendor> searchVendorByName = null;

		if(criteria == null)
		{
		%>
			<tr>
				<td>No result</td>
			</tr>
		<%
		}else
		{
			weedingenqury = Weeding.retrieveWeedingList(criteria, searchType, searchType1);
		
		%>
	
		<%
				int count = 1;
				for(Weeding i: weedingenqury){
		%>
				<tr>
					<td><%= count++ %></td>
					<td><%= i.getWE03DOCNO() %></td>
					<td><%= i.getWE03TITL() %></td>
					<td><%= i.getWE03WDATE() %></td>
					<td>
						<a type='button' title='View' class='btn btn-primary' data-toggle='modal' data-target="#viewRecord" href="Modal_ViewWeeding?accNo=<%= i.getWE03DOCNO() %>"><span class='glyphicon glyphicon-eye-open'></span></a>
					</td>
				</tr>
		<%
				}
		}
			
		%>
		</tbody>
	</table>