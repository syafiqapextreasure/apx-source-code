<!-- Java class-->
<%@ page
	import="com.ilmu.foundation.PatronEligibility.PatronEligibility"%>
<%@ page import="com.ilmu.global.connection.DBConnection"%>
<%@ page import="com.ilmu.foundation.PatronEligibility.SearchElig"%>
<%@ page import=" java.util.List"%>

<link rel="stylesheet"
	href="<%=request.getContextPath()%>/plugin/datatables/dataTables.bootstrap.css">
	
<table class="table table-striped table-bordered" id="eligTable">
	<thead>
		<tr>
			<th id="findPatronCate">Patron Category</th>
			<th id="findItemCate">Item Category</th>
			<th id="findSMD">SMD</th>
			<th id="findBranch">Branch</th>
			<th id="findLoanPeriod">Loan Period</th>
			<th>Action</th>
		</tr>
	</thead>
	<tbody>
		<%
			String type = request.getParameter("searchType");
			String[] criteria = request.getParameterValues("criterierList[]");

			List<PatronEligibility> vendorResult = null;

			if (criteria == null) {
				out.println();
			}
			if (type.equals("cate")) {
				vendorResult = SearchElig.searchEligByCate(criteria);
			} else if (type.equals("icate")) {
				vendorResult = SearchElig.searchEligByIcate(criteria);
			} else if (type.equals("smd")) {
				vendorResult = SearchElig.searchEligBySmd(criteria);
			} else if (type.equals("branch")) {
				vendorResult = SearchElig.searchEligByBranch(criteria);
			}

			for (PatronEligibility p : vendorResult) {
		%>
		<tr>
			<td><%=p.getPatronCategory()%> - <%=p.getPatronCategoryDesc()%></td>
			<td><%=p.getItemCategory()%> - <%=p.getItemCategoryDesc()%></td>
			<td><%=p.getSmdCode()%> - <%=p.getSmdCodeDesc()%></td>
			<td><%=p.getBranch()%> - <%=p.getBranchDesc()%></td>
			<td><%=p.getLoanPeriodString()%></td>

			<td class="form-inline">
				<!-- START view patron --> <a id="viewPatronBtn" data-toggle="modal"
				data-target="#viewModalSearch"
				href="Modal_ViewElig?GL27CATE=<%=p.getPatronCategory()%>&GL27ICAT=<%=p.getItemCategory()%>&GL27SMD=<%=p.getSmdCode()%>&GL27BRNC=<%=p.getBranch()%>"
				class="btn btn-success btn-xs"> <span
					class="glyphicon glyphicon-eye-open" title="View Patron"></span>
			</a> <!-- END view patron --> <!-- START edit patron --> <a
				id="editPatronBtn" data-toggle="modal"
				data-target="#editModalSearch"
				href="Modal_EditElig?GL27CATE=<%=p.getPatronCategory()%>&GL27ICAT=<%=p.getItemCategory()%>&GL27SMD=<%=p.getSmdCode()%>&GL27BRNC=<%=p.getBranch()%>"
				class="btn btn-info btn-xs"> <span
					class="glyphicon glyphicon-pencil" title="Edit Patron"></span>
			</a> <!-- END edit patron --> <!-- START delete patron --> <a
				id="deletePatronBtn"
				onclick="deleteEligibility('<%=p.getPatronCategory()%>','<%=p.getItemCategory()%>','<%=p.getSmdCode()%>','<%=p.getBranch()%>')"
				class="btn btn-dull btn-xs"> <span
					class="glyphicon glyphicon-trash" title="Delete Patron"></span>
			</a> <!-- END delete patron -->
			</td>
		</tr>
		<%
			}
		%>
	</tbody>
</table>

<!-- Modal -->
<div class="modal fade" id="viewModalSearch" role="dialog">
	<div class="modal-dialog modal-lg">
		<div class="modal-content">
			<!-- Remote content load here -->
		</div>
	</div>
</div>

<!-- Modal -->
<div class="modal fade" id="editModalSearch" role="dialog">
	<div class="modal-dialog modal-lg">
		<div class="modal-content">
			<!-- Remote content load here -->
		</div>
	</div>
</div>
<script type="text/javascript">	
$.getScript("${pageContext.request.contextPath}/js/Foundation/Delete.js");

/* DataTables  */
$.getScript("<%=request.getContextPath()%>/plugin/datatables/jquery.dataTables.min.js");
$.getScript("<%=request.getContextPath()%>/plugin/datatables/dataTables.bootstrap.min.js");

	$(function() {
		$('#eligTable').DataTable({
			pagination : true,
			lengthChange : false,
			searching : true,
			ordering : true,
			info : true,
			autoWidth : true
		});
	});
</script>