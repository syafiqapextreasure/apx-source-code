	<!-- Java class-->
	<%@ page import="com.ilmu.global.connection.DBConnection, com.ilmu.foundation.PatronDetails.PatCriteria, com.ilmu.foundation.PatronDetails.PatronSearch, java.util.List"%>
	
	
	<!-- <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script> -->
	
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
				
				//$('#vendorModal').modal('hide');
				
				/* document.getElementById('patronId').value = $(this).attr('data-value');
				document.getElementById('patronName').value = $(this).attr('data-id'); */
				//document.getElementById('patronId').focus();
				var patronId = $(this).attr("data-value");
				var patronName = $(this).attr("data-id");
				var dataid = $(this).attr("data-action");
				//alert(patronId);
				/* $("#patronId").val(patronId);
				$("#patronName").val(patronName); */
				
				if(dataid == 'headlib'){
					$("#patronIdheadlib").val(patronId);
					$("#patronNameheadlib").val(patronName);
				}else if(dataid == 'PROHEAD'){
					$("#patronIdPROHEAD").val(patronId);
					$("#patronNamePROHEAD").val(patronName);
				}else if(dataid == 'ACQHEAD'){
					$("#patronIdACQHEAD").val(patronId);
					$("#patronNameACQHEAD").val(patronName);
				}else if(dataid == 'CATHEAD'){
					$("#patronIdCATHEAD").val(patronId);
					$("#patronNameCATHEAD").val(patronName);
				}else if(dataid == 'SERHEAD'){
					$("#patronIdSERHEAD").val(patronId);
					$("#patronNameSERHEAD").val(patronName);
				}else if(dataid == 'IRSHEAD'){
					$("#patronIdIRSHEAD").val(patronId);
					$("#patronNameIRSHEAD").val(patronName);
				}else if(dataid == 'FINHEAD'){
					$("#patronIdFINHEAD").val(patronId);
					$("#patronNameFINHEAD").val(patronName);
				}else{
					$("#patronId").val(patronId);
					$("#patronName").val(patronName);
				}
				
				//$(".close").click();
				
 		
		});
		
		$('body').on('hidden.bs.modal', '.modal', function () {
		     $(this).removeData('bs.modal');
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
			</tr>
		</thead>
		<tbody>
		<% 
		String searchType = request.getParameter("search_type");
		String criteria = request.getParameter("criteria");
		String cate_type = request.getParameter("cate_type");
		String cate_id = request.getParameter("cate_id");
		String action = request.getParameter("action");
		
		List<PatCriteria> vendorResult = null;
		//List<Vendor> searchVendorByName = null;

		if(criteria == null)
		{
			out.println();
		}else
		{
			/* if(searchType.equals("patronId"))
				vendorResult = PatronSearch.searchPatronById(criteria);
			else if (searchType.equals("patronName")) */
				//vendorResult = PatCriteria.searchPatronById(criteria);
				vendorResult = PatCriteria.searchPatronById(searchType, criteria, cate_type, cate_id);
			
		%>
		<%
			for(PatCriteria i: vendorResult){
				System.out.println(i.getId() + " i.getId()");
		%>

			<tr class="clickable-row" data-dismiss="modal" data-value="<%= i.getId()%>" data-id="<%= i.getName()%>" data-action="<%=action%>">
				<td><%= i.getId() %></td>
				<td><%= i.getName() %></td>
			</tr>
		<%
			}
		}
		%>
		</tbody>
	</table>
	


	
	