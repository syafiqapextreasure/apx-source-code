	<!-- Java class-->
	<%@ page import="com.ilmu.global.connection.DBConnection, com.ilmu.foundation.GeneralSubject.SubjectCriteria, java.util.List"%>
	
	
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
				
				document.getElementById('start').value = $(this).attr('data-value');
				document.getElementById('end').value = $(this).attr('data-end');
				document.getElementById('desc').value = $(this).attr('data-desc');
				document.getElementById('join').value = $(this).attr('data-join');
				document.getElementById('start').focus();
 		
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
				<th data-sortable="true">Start Code</th>
				<th data-sortable="true">End Code</th>
				<th data-sortable="true">Join</th>
				<th data-sortable="true">Description</th>
				<th>Action</th>
			</tr>
		</thead>
		<tbody>
		<% 
		String searchType = request.getParameter("searchType");
		String criteria = request.getParameter("criteria");
		String cate_type = request.getParameter("cate_type");
		String cate_id = request.getParameter("cate_id");
		
		List<SubjectCriteria> vendorResult = null;
		//List<Vendor> searchVendorByName = null;

		if(criteria == null)
		{
			out.println();
		}
		if(searchType.equals("GL54SUBJSTA")){
			vendorResult = SubjectCriteria.searchSubjByStart(criteria);
		}else if (searchType.equals("GL54SUBJEND")) {
			vendorResult = SubjectCriteria.searchSubjByEnd(criteria);
		}else if (searchType.equals("GL54DESC")) {
			vendorResult = SubjectCriteria.searchSubjByDesc(criteria);
		}
		%>
		<%
			for(SubjectCriteria i: vendorResult){
				
				String codeA = i.getstart();
				String codeD = i.getend();
				//System.out.println(code+"code");
				
				String newConvertCodeA = codeA.replace(" ", "+");
				String newConvertCodeD = codeD.replace(" ", "+");
		%>
	 
			<tr class="clickable-row" data-dismiss="modal" data-value="<%= i.getstart()%>" data-end="<%= i.getend()%>" data-join="<%= i.getjoin()%>" data-desc="<%= i.getdesc()%>">
				<td><%= i.getstart() %></td>
				<td><%= i.getend() %></td>
				<td><%= i.getjoin() %></td>
				<td><%= i.getdesc() %></td>
				<td class=" last">
					<input type="hidden" value="A" name="fndcategories"/>
							
							<button class="btn btn-primary btn-xs" data-toggle='modal' data-target='#viewModal' href='Modal_ViewSubject?GL54SUBJSTA=<%=newConvertCodeA%>&&GL54SUBJEND=<%=newConvertCodeD%>'><span class="glyphicon glyphicon-eye-open" title="View Record" ></span></button>
							<button class="btn btn-info btn-xs" data-toggle='modal' data-target='#editModal' href='Modal_EditSubject?GL54SUBJSTA=<%=newConvertCodeA%>&&GL54SUBJEND=<%=newConvertCodeD%>'><span class="glyphicon glyphicon-pencil" title="Edit Record" ></span></button>
   							<button class="btn btn-dull btn-xs" onclick="deleteSubj(this.id)" id="<%=i.getstart()%>"><span class="glyphicon glyphicon-trash" title="Delete Record" ></span></button>
   				</td>
   				
			</tr>
		<%
			}
		
		%>
		</tbody>
	</table>
	


	
	