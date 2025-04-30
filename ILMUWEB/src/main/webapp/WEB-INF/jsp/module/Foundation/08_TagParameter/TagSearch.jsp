	<!-- Java class-->
	<%@ page import="com.ilmu.global.connection.DBConnection, com.ilmu.foundation.CurrencyCode.CurrencyCriteria, java.util.List"%>
	
	
	<!-- <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script> -->
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/Foundation/Delete.js"></script>
	
	 <!-- DataTables -->	
<%--  <script type="text/javascript" src="<%= request.getContextPath() %>/plugin/datatables/jquery.dataTables.min.js"></script>
 <script type="text/javascript" src="<%= request.getContextPath() %>/plugin/datatables/dataTables.bootstrap.min.js"></script>
 <link rel="stylesheet" href="<%= request.getContextPath() %>/plugin/datatables/dataTables.bootstrap.css"> --%>
	
	<script>
	// Code making table row clickable
	// Have to place here otherwise the code won't work
	$(document).ready(function() {
		$('#vendor_result').DataTable();
	
		$('.clickable-row').click(function() {
				$('#vendorModal').modal('hide');
				
				document.getElementById('currency').value = $(this).attr('data-value');
				document.getElementById('description').value = $(this).attr('data-name');
				document.getElementById('publisher').value = $(this).attr('data-pub');
				document.getElementById('bank').value = $(this).attr('data-bank');
				document.getElementById('currency').focus();
 		
		});
	});
	</script>
	
	<script>
    var $table = $('#table');

    $(function () {
        $('#modalTable').on('shown.bs.modal', function () {
            $table.DataTable('resetView');
        });
    });
</script>
	
	<table class="table table-hover"  data-toggle="table" id="vendor_result" data-click-to-select="true" >
		<thead>
			<tr>
				<th data-sortable="true">Currency Code</th>
				<th data-sortable="true">Description</th>
				<th data-sortable="true">Publisher's Rate</th>
				<th data-sortable="true">Bank's Rate</th>
				<th>Action</th>
			</tr>
		</thead>
		<tbody>
		<% 
		String searchType = request.getParameter("searchType");
		String criteria = request.getParameter("criteria");//.toUpperCase();
		String cate_type = request.getParameter("cate_type");
		String cate_id = request.getParameter("cate_id");
		
		List<CurrencyCriteria> vendorResult = null;
		//List<Vendor> searchVendorByName = null;

		if(criteria == null)
		{
			out.println();
		}
		if(searchType.equals("GL24FOREX")){
			vendorResult = CurrencyCriteria.searchCurrencyByCode(criteria);
		}else if (searchType.equals("GL24DESC")) {
			vendorResult = CurrencyCriteria.searchCurrencyByDesc(criteria);
		}
		%>
		<%
			for(CurrencyCriteria i: vendorResult){
		%>
	 
			<tr class="clickable-row" data-dismiss="modal" data-value="<%= i.getcurrency()%>" data-name="<%= i.getdesc()%>" data-pub="<%= i.getpubrate()%>" data-bank="<%= i.getbank()%>">
				<td><%= i.getcurrency() %></td>
				<td><%= i.getdesc() %></td>
				<td><%= i.getpubrate() %></td>
				<td><%= i.getbank() %></td>
				<td class=" last">
					<input type="hidden" value="A" name="fndcategories"/>
							
							<button class="btn btn-primary btn-xs" data-toggle='modal' data-target='#viewModal' href='Modal_ViewCurrency?GL24FOREX=<%=i.getcurrency()%>'><span class="glyphicon glyphicon-eye-open" title="View Record" ></span></button>
							<button class="btn btn-info btn-xs" data-toggle='modal' data-target='#editModal' href='Modal_EditCurrency?GL24FOREX=<%=i.getcurrency()%>'><span class="glyphicon glyphicon-pencil" title="Edit Record" ></span></button>
   							<button class="btn btn-dull btn-xs" onclick="deleteCurrency(this.id)" id="<%=i.getcurrency()%>"><span class="glyphicon glyphicon-trash" title="Delete Record" ></span></button>
   				</td>
   				
			</tr>
		<%
			}
		
		%>
		</tbody>
	</table>
	


	
	