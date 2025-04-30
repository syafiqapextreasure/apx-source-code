	<!-- Java class-->
	<%@ page import="com.ilmu.global.connection.DBConnection, com.ilmu.foundation.VendorCode.VendorCriteria, java.util.List"%>
	
	
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
				
				document.getElementById('vendorcode').value = $(this).attr('data-value');
				document.getElementById('vendorname').value = $(this).attr('data-name');
				document.getElementById('binder').value = $(this).attr('data-binder');
				document.getElementById('vendor').value = $(this).attr('data-vendor');
				document.getElementById('publisher').value = $(this).attr('data-pub');
				document.getElementById('vendorcode').focus();
 		
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
				<th data-sortable="true">Vendor Code</th>
				<th data-sortable="true">Vendor Name</th>
				<th data-sortable="true">Binder</th>
				<th data-sortable="true">Vendor</th>
				<th data-sortable="true">Publisher</th>
				<th>Action</th>
			</tr>
		</thead>
		<tbody>
		<% 
		String searchType = request.getParameter("searchType");
		String criteria = request.getParameter("criteria");
		String cate_type = request.getParameter("cate_type");
		String cate_id = request.getParameter("cate_id");
		
		List<VendorCriteria> vendorResult = null;
		//List<Vendor> searchVendorByName = null;

		if(criteria == null)
		{
			out.println();
		}
		if(searchType.equals("GL39CODE")){
			vendorResult = VendorCriteria.searchVendorByCode(criteria);
		}else if (searchType.equals("GL39NAME")) {
			vendorResult = VendorCriteria.searchVendorByName(criteria);
		}else if (searchType.equals("GL39PERINC")) {
			vendorResult = VendorCriteria.searchVendorByPic(criteria);
		}else if (searchType.equals("GL39PCODE")) {
			vendorResult = VendorCriteria.searchVendorByPoscode(criteria);
		}
		%>
		<%
			for(VendorCriteria i: vendorResult){
				
				String code = i.getvendorCode();
				//System.out.println(code+"code");
				
				String newConvertCode = code.replace(" ", "+");
		%>
	 
			<tr class="clickable-row" data-dismiss="modal" data-value="<%= i.getvendorCode()%>" data-name="<%= i.getvendorName()%>" data-binder="<%= i.getbinder()%>" data-vendor="<%= i.getvendor()%>" data-pub="<%= i.getpublisher()%>">
				<td><%= i.getvendorCode() %></td>
				<td><%= i.getvendorName() %></td>
				<td><%= i.getbinder() %></td>
				<td><%= i.getvendor() %></td>
				<td><%= i.getpublisher() %></td>
				<td class=" last">
					<input type="hidden" value="A" name="fndcategories"/>
							
							<button class="btn btn-primary btn-xs" data-toggle='modal' data-target='#viewModal' href='Modal_ViewVendor?GL39CODE=<%=newConvertCode%>'><span class="glyphicon glyphicon-eye-open" title="View Record" ></span></button>
							<button class="btn btn-info btn-xs" data-toggle='modal' data-target='#editModal' href='Modal_EditVendor?GL39CODE=<%=newConvertCode%>'><span class="glyphicon glyphicon-pencil" title="Edit Record" ></span></button>
   							<button class="btn btn-dull btn-xs" onclick="deleteVendor(this.id)" id="<%=i.getvendorCode()%>"><span class="glyphicon glyphicon-trash" title="Delete Record" ></span></button>
   				</td>
   				
			</tr>
		<%
			}
		
		%>
		</tbody>
	</table>
	


	
	