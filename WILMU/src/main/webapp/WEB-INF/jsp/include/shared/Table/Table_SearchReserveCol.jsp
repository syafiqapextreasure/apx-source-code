<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="com.wilmu.reservecollection.data.SearchReserveDetails,
				 java.util.List.*,
				 java.util.*"%>
	

		<table id="rsvCollectionMaster_table"
				class="table table-bordered table-striped" style="overflow-x: auto">
				<thead>
					<tr>
						<th>No.</th>
						<th>Course</th>
						<th>Semester</th>
						<th>Subject</th>
						<th>Instructor</th>
						<th>Title</th>
						<th>Action</th>
					</tr>
				</thead>
			<tbody>
				<% 
					String getChecked = request.getParameter("getChecked");
					System.out.println("getChecked : " + getChecked);
					
					String getCriteria = request.getParameter("getCriteria");
					System.out.println("getCriteria : " + getCriteria);
	
					List<SearchReserveDetails> search2 = null;
					
					search2 = SearchReserveDetails.getSearchDetails(getChecked, getCriteria);
					int count = 1;
					for(SearchReserveDetails getSearchDetails: search2){ 
				%>
				<tr>
					<td><%=count++%></td>
					<td><%=getSearchDetails.getGL12DESC()%></td>
					<td><%=getSearchDetails.getGL60DESC()%></td>
					<td><%=getSearchDetails.getGL54DESC()%></td>
					<%-- <td><%=getSearchDetails.getstatus()%></td> --%>
					<td><%=getSearchDetails.getGL14NAME()%></td>
					<td><%=getSearchDetails.getRE01TITLE()%></td> 
					<td class="last">
						<button id="Viewform" class="btn btn-primary btn-sm" data-toggle='modal' data-target="#modalreservecollection" data-mode="3" title="View Reserve" data-rowid="<%=getSearchDetails.getRC01RESERVENO()%>"><span class="glyphicon glyphicon-eye-open"></span></button>
						<button id="Editform" class="btn btn-info btn-sm Edit" data-toggle='modal' data-target="#modalreservecollection" data-mode="2" title="Edit Order" data-rowid="<%=getSearchDetails.getRC01RESERVENO()%>"><span class="glyphicon glyphicon-pencil"></span></button>
		   				<button id="Deleteform" class="btn btn-dull btn-sm" data-reserve="<%=getSearchDetails.getRC01RESERVENO()%>" onclick="deleteReserve(this)"<%-- data-Status="<%=getSearch.getStatus()%>" --%>><span class="glyphicon glyphicon-trash" title="Delete Oder" ></span></button>
					</td>
				</tr>
				<%
					}
					System.out.println("ENDDDD"); 
				%>
			</tbody>
		</table>
			


