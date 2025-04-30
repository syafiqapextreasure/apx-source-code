<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List, java.util.ArrayList, com.ilmu.acquisition.orderManagement.errorSupply" %>

<script>
     // $(function () {
       $('#tableErrorInSupply').DataTable({
          "paging": true,
          "lengthChange": false,
          "searching": false,
          "ordering": true,
          "info": true,
          "autoWidth": true
        }); 
        

      //});
	</script>

	<%
		// get the orderNo
		String orderNo = request.getParameter("orderNo");
		
		// Retrieve the list
		List<errorSupply> eisList = errorSupply.viewAllSupplyInError();
	%>
	
	<!-- Print the tables -->
	<table class="table table-hover"  id="tableErrorInSupply">
		<thead>
			<tr>
				<th data-sortable="true">Order No</th>
				<th data-sortable="true">Invoice No</th>
				<th data-sortable="true">Date</th>
				<th data-sortable="true">Reason</th>
			</tr>
		</thead>
	<%
		for(errorSupply eis : eisList) {
	%>
		<tr>
			<td><%= eis.getorderNo() %></td>
			<td><%= eis.getinvoiceNo() %></td>
			<td><%= eis.getdate() %></td>
			<td><%= eis.getreason() %></td>
		</tr>
	<%
		}
	%>
	</table>