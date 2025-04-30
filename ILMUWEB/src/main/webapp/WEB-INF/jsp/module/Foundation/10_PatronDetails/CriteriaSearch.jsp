	<!-- Java class-->
	<%@ page import="com.ilmu.global.connection.DBConnection, com.ilmu.foundation.PatronDetails.PatCriteria, 
	java.util.List"%>
	
	
<!-- 	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script> -->
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/Foundation/Delete.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/general/noteAlert.js"></script>
	
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
				
				document.getElementById('patronId').value = $(this).attr('data-value');
				document.getElementById('patronName').value = $(this).attr('data-id');
				document.getElementById('patronId').focus();
 		
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
				<th data-sortable="true">Patron Id</th>
				<th data-sortable="true">Patron Name</th>
				<th>Action</th>
			</tr>
		</thead>
		<tbody>
		<% 
		String searchType = request.getParameter("searchType");
		String criteria = request.getParameter("criteria");
		String cate_type = request.getParameter("cate_type");
		String cate_id = request.getParameter("cate_id");
		String loginId = request.getParameter("loginId");
		
		List<PatCriteria> vendorResult = null;
		//List<Vendor> searchVendorByName = null;

		if(criteria == null)
		{
			out.println();
		}
		if(searchType.equals("GL14PATR")){
			vendorResult = PatCriteria.searchPatronById2(criteria, loginId);
			//vendorResult = PatCriteria.searchPatronById(searchType, criteria, cate_type, cate_id);
					//.searchPatronById(criteria);
		}else if (searchType.equals("GL14NAME")) {
			vendorResult = PatCriteria.searchPatronByName(criteria, loginId);
		}else if (searchType.equals("GL14MEMDATE")) {
			vendorResult = PatCriteria.searchPatronByMemDate(criteria, loginId);
		}else if (searchType.equals("GL14EXPDATE")) {
			vendorResult = PatCriteria.searchPatronByExpDate(criteria, loginId);
		}else if (searchType.equals("GL14OLDIC")) {
			vendorResult = PatCriteria.searchPatronByOldIc(criteria, loginId);
		}else if (searchType.equals("GL14NEWIC")) {
			vendorResult = PatCriteria.searchPatronByNewIc(criteria, loginId);
		}else if (searchType.equals("GL14RELID")) {
			vendorResult = PatCriteria.searchPatronByRelId(criteria, loginId);
		}
		%>
		<%
			for(PatCriteria i: vendorResult){
				
				String id = i.getId();
				
				String newConvertId = id.replace(" ", "+");
		%>
	 
			<tr class="clickable-row" data-dismiss="modal" data-value="<%= i.getId()%>" data-id="<%= i.getName()%>">
				<td><%= i.getId() %></td>
				<td><%= i.getName() %></td>
				<td class=" last">
					<input type="hidden" value="A" name="fndcategories"/>
							<button class="btn btn-primary btn-xs" data-toggle='modal' data-target='#viewModal' href='Modal_ViewPatron?GL14PATR=<%=newConvertId%>'><span class="glyphicon glyphicon-eye-open" title="View Record" onclick="noteCheck('<%=newConvertId%>', 1000)"></span></button>
							<button class="btn btn-info btn-xs" data-toggle='modal' data-target='#editModal' href='Modal_EditPatron?GL14PATR=<%=newConvertId%>'><span class="glyphicon glyphicon-pencil" title="Edit Record" onclick="noteCheck('<%=newConvertId%>', 1000)" ></span></button>
   							<button class="btn btn-dull btn-xs" onclick="deletePatDetail(this.id)" id="<%=i.getId()%>"><span class="glyphicon glyphicon-trash" title="Delete Record" ></span></button>
   				</td>
   				
			</tr>
		<%
			}
		
		%>
		</tbody>
	</table>
	


	
	