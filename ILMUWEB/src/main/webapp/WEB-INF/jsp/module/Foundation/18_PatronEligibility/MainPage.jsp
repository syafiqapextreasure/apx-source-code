<%@page import="org.springframework.util.CollectionUtils"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="com.ilmu.foundation.PatronEligibility.SQLStatement"%>
<%@ page
	import="com.ilmu.foundation.PatronEligibility.PatronEligibility"%>
<%@ page import="java.util.List"%>
<%@ page import="com.ilmu.foundation.global.GlobalSQLStatement"%>
<%@ page import="com.ilmu.foundation.PatronEligibility.SearchElig, com.ilmu.global.*"%>

<!DOCTYPE html>
<html lang="en">
<head>
<title>MainPage Patron Eligibility</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/resources/css/patron-elig.css">
<!-- DataTable -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/plugin/datatables/dataTables.bootstrap.css">
<link
	href="https://ajax.googleapis.com/ajax/libs/jqueryui/1.8/themes/base/jquery-ui.css"
	rel="stylesheet" type="text/css" />
</head>

<body>
	<script>
		$(function() {
			$('#eligTable').DataTable({
				pagination : true,
				lengthChange : false,
				searching : false,
				ordering : true,
				info : true,
				autoWidth : true
			});
		});
	</script>
</head>
<body>
	<!-- <section>
		<h1>
			<i class="glyphicon glyphicon-list"></i> Patron Eligibility
		</h1>
	</section> -->
	<%
		String view = request.getParameter("result");
	System.out.println("view =" +view);
	%>
	<section>
		<div class="box box-default">
			<div class="pull-right">
				<div class="panel-heading">
					<!-- START add patron -->
					<a id="newPatronBtn" data-toggle="modal" data-target="#addModal"
						href="AddWizard?result=<%=view%>" class="btn btn-primary btn-sm">
						<span class="glyphicon glyphicon-plus" title="Add New Patron"></span>
					</a>
					<!-- END add patron -->

					<!-- START find patron -->
					<a id="findPatronBtn" data-toggle="modal"
						data-target="#searchModal" href="Modal_SearchElig"
						class="btn btn-primary btn-sm"> <span
						class="glyphicon glyphicon-search" title="Find Patron"></span>
					</a>
					<!-- END find patron -->
				</div>
			</div>
			<br> <br>
			<div class="panel-body" id="display_vendor">
				<table id="eligTable" class="table table-striped table-bordered">
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
							SQLStatement sql = new SQLStatement();
							List<PatronEligibility> pe = sql.getPatronEligibities(view);

							for (PatronEligibility p : pe) {
								
								/* String getHourOrDay = "";
								if(p.getLoanPeriodType() == "H"){
									getHourOrDay = "hour(s)";
								}else if(p.getLoanPeriodType() == "D"){
									getHourOrDay = "day(s)";
								} */
						%>
						<tr>
							<td><%=p.getPatronCategory()%> - <%=p.getPatronCategoryDesc()%></td>
							<td><%=p.getItemCategory()%> - <%=p.getItemCategoryDesc()%></td>
							<td><%=p.getSmdCode()%> - <%=p.getSmdCodeDesc()%></td>
							<td><%=p.getBranch()%> - <%=p.getBranchDesc()%></td>
							<td><%=p.getLoanPeriod()%> <%=p.getLoanPeriodType().equals("D") ? "day(s)" : "hour(s)"%>
							</td>
							<td class="form-inline">
								<!-- START view patron --> <a id="viewPatronBtn"
								data-toggle="modal" data-target="#viewModal"
								href="Modal_ViewElig?GL27CATE=<%=p.getPatronCategory()%>&GL27ICAT=<%=p.getItemCategory()%>&GL27SMD=<%=p.getSmdCode()%>&GL27BRNC=<%=p.getBranch()%>"
								class="btn btn-success btn-xs"> <span
									class="glyphicon glyphicon-eye-open" title="View Patron"></span>
							</a> <!-- END view patron --> <!-- START edit patron --> <a
								id="editPatronBtn" data-toggle="modal" data-target="#editModal"
								href="Modal_EditElig?GL27CATE=<%=p.getPatronCategory()%>&GL27ICAT=<%=p.getItemCategory()%>&GL27SMD=<%=p.getSmdCode()%>&GL27BRNC=<%=p.getBranch()%>"
								class="btn btn-info btn-xs"> <span
									class="glyphicon glyphicon-pencil" title="Edit Patron"></span>
							</a> <!-- END edit patron --> <!-- START delete patron -->
								<button class="btn btn-dull btn-xs" id="deletePatronBtn"
									onclick="deleteEligibility('<%=p.getPatronCategory()%>','<%=p.getItemCategory()%>','<%=p.getSmdCode()%>','<%=p.getBranch()%>')">
									<span class="glyphicon glyphicon-trash" title="Delete Patron"></span>
								</button>
							</td>
							<!-- END delete patron -->
						</tr>
						<%
							}
						%>
					</tbody>
				</table>
			</div>
		</div>
	</section>
	
<%-- 	<div id="eligModal" class="modal fade" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">

				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel2">ILMU</h4>
				</div>
				<div class="modal-body">
					<div id="testmodal2" style="padding: 5px 20px;">
						<%
							/* SQLStatement sql = new SQLStatement(); */
							/* Y if retrieve all record, N if only top 100 */
							final List<PatronEligibility> records = sql.getPatronEligibities("Y");
						%>
						<p>
							There are
							<%=records.size()%>
							records existed.
						</p>
						<p>
							Click <strong>YES</strong> to retrieve all or click <strong>NO</strong>
							to retrieve first 100 records
						</p>
					</div>
				</div>
				<div class="modal-footer">
		    	<button data-id="Y" class="btn btn-default choose">Yes</button>
				<button data-id="N" class="btn btn-default choose">No</button> 
					<!-- <a
						href="template?MODULE=Foundation/18_PatronEligibility&ACTION=MainPage.jsp&result=Y"
						class="btn btn-default choose">Yes</a> <a
						href="template?MODULE=Foundation/18_PatronEligibility&ACTION=MainPage.jsp&result=N"
						class="btn btn-default choose">No</a>  -->
				</div>
			</div>
		</div>
	</div> --%>

	<div id="elig_modal" data-toggle="modal" data-target="#eligModal"></div>
	
	<!-- Modal -->
	<div class="modal fade" id="viewModal" role="dialog" data-keyboard="false" data-backdrop="static">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<!-- Remote content load here -->
			</div>
		</div>
	</div>

	<!-- Modal -->
	<div class="modal fade" id="editModal" role="dialog" data-keyboard="false" data-backdrop="static">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<!-- Remote content load here -->
			</div>
		</div>
	</div>

	<!-- Modal -->
	<div class="modal fade" id="addModal" role="dialog" data-keyboard="false" data-backdrop="static">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<!-- Remote content load here -->
			</div>
		</div>
	</div>

	<!-- Modal -->
	<div class="modal fade" id="searchModal" role="dialog" data-keyboard="false" data-backdrop="static">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<!-- Remote content load here -->
			</div>
		</div>
	</div>

	<script type="text/javascript"
		src="<%=request.getContextPath()%>/resources/js/patron-elig.js"></script>
	<%-- <script type="text/javascript"
		src="<%=request.getContextPath()%>/resources/js/search-elig.js"></script> --%>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/js/Foundation/Delete.js"></script>
	<!-- DataTable -->
	<script type="text/javascript"
		src="<%=request.getContextPath()%>/plugin/datatables/jquery.dataTables.min.js"></script>
	<script type="text/javascript"
		src="<%=request.getContextPath()%>/plugin/datatables/dataTables.bootstrap.min.js"></script>
</body>

</html>