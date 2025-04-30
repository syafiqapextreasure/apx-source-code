<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="ISO-8859-1" import="java.util.*"%>
<%@ page import="java.sql.*"%>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/general/vendorSearch.js"></script>
<style>
#modal_vendorSearch {
	z-index: 1080 !important;
}
</style>

<%
	String action = request.getParameter("action");
%>

<div class="modal-header">
	<button type="button" id="closeModalVendor" class="close"
		data-dismiss="modal" aria-label="Close">
		<span aria-hidden="true">&times;</span>
	</button>
	<h4 class="modal-title" id="myModalLabel">Search Vendor</h4>
</div>

<input type="hidden" id="vendAction" value="<%=action%>" disabled>

<div class="modal-body">
	<div class="panel-group">
		<div class="panel panel-default">
			<div class="panel-heading">
				<h4 class="panel-title">
					<a data-toggle="collapse" data-parent="#form_parent"
						href="#search_vendor">Search</a>
				</h4>
			</div>
			<div id="search_vendor" class="panel-collapse collapse in">
				<div class="form-horizontal" onsubmit="return searchVendor()">
					<br />
					<div class="form-group">
						<div class="col-sm-offset-1 col-sm-3 col-md-offset-1 col-md-3">
							<label>Search By</label>
						</div>
						<div class="col-sm-4 col-md-4">
							<select class="form-control" id="dropdown-searchCriteria"
								name="search-type" onchange="updatePlaceholder()">
								<option value="vendorCode">Vendor Code</option>
								<option value="vendorName">Vendor Name</option>
							</select>
						</div>
					</div>
					<div class="clearfix"></div>
					<div class="form-group">
						<div class="col-sm-offset-1 col-sm-3 col-md-offset-1 col-md-3">
							<label>Search Text</label>
						</div>
						<div class="col-sm-4 col-md-4">
							<input type="text" class="form-control" name="criteria"
								id="input-criteria" placeholder="Vendor Code">
						</div>
					</div>

					<div class="form-group">
						<div class=" col-md-4"></div>

						<div class="col-md-4">
							<button type="button" name="searchVendor" id="searchVendor"
								class="btn btn-info searchVendor">
								<span class="glyphicon glyphicon-search"></span> Search
							</button>
							<button type="button" name="cancel" id="cancel"
								class="btn btn-info" data-dismiss="modal">Cancel</button>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<div class="panel panel-default">
		<div class="panel-heading">
			<h4 class="panel-title">
				<a data-toggle="collapse" data-parent="#form_parent"
					href="#result_vendor">Result</a>
			</h4>
		</div>
		<div id="result_vendor" class="panel-collapse collapse">
			<div class="panel-body">
				<div id="display_vendor">
					<table class="table table-hover" id="table-vendor"
						data-toggle="table">
						<thead>
							<tr>
								<th data-sortable="true">Vendor Code</th>
								<th data-sortable="true">Vendor Name</th>
							</tr>
						</thead>
						<tbody>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
</div>