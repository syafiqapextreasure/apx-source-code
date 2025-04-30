	<!-- Java class-->
	<%@ page import="com.ilmu.global.connection.DBConnection, com.ilmu.foundation.LocationCode.LocationCriteria, java.util.List"%>
	
	
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
				
				document.getElementById('locacode').value = $(this).attr('data-value');
				document.getElementById('desc').value = $(this).attr('data-desc');
				document.getElementById('branch').value = $(this).attr('data-branch');
				document.getElementById('special').value = $(this).attr('data-special');
				document.getElementById('collection').value = $(this).attr('data-col');
				document.getElementById('locacode').focus();
 		
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
				<th data-sortable="true">Branch Code</th>
				<th data-sortable="true">Location Name</th>
				<th data-sortable="true">Description</th>
				<th data-sortable="true">Specialization</th>
				<th data-sortable="true">Collection</th>
				<th>Action</th>
			</tr>
		</thead>
		<tbody>
		<% 
		String searchType = request.getParameter("searchType");
		String criteria = request.getParameter("criteria").toUpperCase();
		String cate_type = request.getParameter("cate_type");
		String cate_id = request.getParameter("cate_id");
		
		List<LocationCriteria> vendorResult = null;
		//List<Vendor> searchVendorByName = null;

		if(criteria == null)
		{
			out.println();
		}
		if(searchType.equals("GL05LOCA")){
			vendorResult = LocationCriteria.searchLocaByCode(criteria);
		}else if (searchType.equals("GL05DESC")) {
			vendorResult = LocationCriteria.searchLocaByDesc(criteria);
		}else if (searchType.equals("GL05BRNC")) {
			vendorResult = LocationCriteria.searchLocaByBranch(criteria);
		}
		%>
		<%
			for(LocationCriteria i: vendorResult){
				
				String code = i.getlocacode();
				//System.out.println(code+"code");
				
				String newConvertCode = code.replace(" ", "+");
		%>
	 
			<tr class="clickable-row" data-dismiss="modal" data-value="<%= i.getlocacode()%>" data-desc="<%= i.getdesc()%>" data-branch="<%= i.getbranch()%>" data-special="<%= i.getspecial()%>" data-col="<%= i.getcollection()%>">
				<td><%= i.getbranch() %></td>
				<td><%= i.getlocacode() %></td>
				<td><%= i.getdesc() %></td>
				<td><%= i.getspecial() %></td>
				<td><%= i.getcollection() %></td>
				<td class=" last">
					<input type="hidden" value="A" name="fndcategories"/>
							
							<button class="btn btn-primary btn-xs" data-toggle='modal' data-target='#viewModal' href='Modal_ViewLocation?GL05LOCA=<%=newConvertCode%>'><span class="glyphicon glyphicon-eye-open" title="View Record" ></span></button>
							<button class="btn btn-info btn-xs" data-toggle='modal' data-target='#editModal' href='Modal_EditLocation?GL05LOCA=<%=newConvertCode%>'><span class="glyphicon glyphicon-pencil" title="Edit Record" ></span></button>
   							<button class="btn btn-dull btn-xs" onclick="deleteLoca(this.id)" id="<%=i.getlocacode()%>"><span class="glyphicon glyphicon-trash" title="Delete Record" ></span></button>
   				</td>
   				
			</tr>
		<%
			}
		
		%>
		</tbody>
	</table>
	


	
	