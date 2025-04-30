<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.util.*"%>
<%@ page import="java.sql.*" %>
<%@page import="com.ilmu.foundation.global.*"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/general/vendor.js"></script>	
<style>
#modal_vendorSearch {
	z-index: 1080 !important;
}
</style>

<!-- <script>
	// Update placeholder for vendor after changes in select
	function updatePlaceholder() {
		//document.getElementById('criteria').placeholder = document.getElementById('search-type').text;
		$("#input-criteria").attr("placeholder",
				$("#dropdown-searchCriteria option:selected").text());
		//document.getElementById('criteria'). = "";
		$("#input-criteria").val("");
	}

	// AJAX Vendor Search
	/* AJAX Processing */

	function searchVendor() {
		var request;
		/* Get the selected value */
		var criteria = document.getElementById('input-criteria').value; // Default using vendor code
		var searchCriteria = document.getElementById('dropdown-searchCriteria').value; // Optional values

		/* Generate URL */
		var url = "include/shared/SearchVendor.jsp?";

		// Append url with criteria
		url += searchCriteria + "=" + criteria;

		AJAXUpdate(url, '#display_vendor');

		// Hide the search form
		$('#search_vendor').collapse("hide");
		// Show the result form
		$('#result_vendor').collapse("show");
		return false;
	}
</script> -->

  <div class="modal-header">
				<button type="button" id="closeModalVendor" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title" id="myModalLabel">Search Binder</h4>
			</div>
     
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
							<div class="form-horizontal"
								onsubmit="return searchVendor()">
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
									<div class=" col-md-3"></div>
									<div
										class="col-xs-offset-1 col-xs-3 col-sm-offset-1 col-sm-2 col-md-offset-1 col-md-2">
										<button type="submit" name="search" value="search" id="searchVendor"
											class="btn btn-info">
											<span class="glyphicon glyphicon-search"></span> Search
										</button>
									</div>
									<div class="col-xs-3 col-sm-2 col-md-1">
										<input type="button" name="cancel" value="Cancel"
											style="width: 80px" class="btn btn-info" data-dismiss="modal" />
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
								<table class="table table-hover" id="table-vendor" data-toggle="table">
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