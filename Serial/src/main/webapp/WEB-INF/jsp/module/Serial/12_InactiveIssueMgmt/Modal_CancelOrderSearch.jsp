<script type="text/javascript" src="${pageContext.request.contextPath}/js/Acquisition/Order_Cancellation.js"></script>	
<link id="stylesheet" href="<%= request.getContextPath() %>/plugin/bootstrap-datepicker/css/bootstrap-datepicker.min.css" rel="stylesheet">
<script type="text/javascript" src="${pageContext.request.contextPath}/plugin/bootstrap-datepicker/js/bootstrap-datepicker.min.js"></script>	
<%@page import="com.ilmu.global.*"%>
<%@page import="java.util.List"%>
<%@ page import="java.util.*" %>		

<script type="text/javascript">
$(function () {
	$("#input-cancellationStartDate").datepicker({
		format: 'dd/mm/yyyy'
	});
	
	$("#input-cancellationEndDate").datepicker({
		format: 'dd/mm/yyyy'
	});
	
	$("#input-startDate").datepicker({
		format: 'dd/mm/yyyy'
	});
	$("#input-endDate").datepicker({
		format: 'dd/mm/yyyy'
	});
});
</script>				

<div class="modal-body">		
		<div class="panel-group" id="accordion" role="tablist"
			aria-multiselectable="true">
			<div class="panel panel-default">
				<div class="panel-heading">
					<h4 class="panel-title">
						<a data-toggle="collapse" data-parent="#form_parent"
							href="#search">Search</a>
					</h4>
				</div>
				<div id="search" class="panel-collapse collapse in">
					<div class="panel-body">
						<form role="form" class="form-horizontal" id="current_form"
							onsubmit="searchOrder()">

							<!-- Vendor Code -->
							<div class="form-group">
								<div class="col-sm-1 col-md-1 col-xs-1">
									<input type="checkbox" id="checkbox-vendorCode"
										name="searchMethod" onclick="toggleSearchField()" value="vendor">
								</div>
								<div class="col-sm-2 col-md-2 col-xs-4">
									<label for="checkbox-vendorCode">Vendor:</label>
								</div>
								<div class="col-sm-2 col-md-2 col-xs-10">
									<input type="text" class="form-control text CT03VEND" id="input-vendorCode"
										onblur="updateVendorName()" onkeyup="updateVendorName()">
								</div>
								<div class="col-sm-1 col-md-1 col-xs-2">
									<button type="button" class="btn btn-default selectPopup" data-toggle="modal" data-target="#vendorSearch" href="Modal_Vendor" >...</button>
								</div>
								<div class="col-sm-4 col-md-4">
									<div class="div-vendorName" id="div-vendorName"></div>
								</div>
							</div>

							<!-- Cancellation Date -->
							<div class="form-group">
								<div class="col-sm-1">
									<input type="checkbox" id="checkbox-cancellationDate" name="searchMethod" value="ordercanceldate">
								</div>
								<div class="col-sm-2">
									<label for="checkbox-cancellationDate">Cancellation Date From: </label>
								</div>
								<div class="col-sm-4">
									<div class="input-daterange input-group" id="datepicker">
										<input type="text" class="input-sm form-control"
											readonly="readonly" name="start" id="input-cancellationStartDate" /> <span
											class="input-group-addon">to</span> <input type="text"
											class="input-sm form-control" name="end" readonly="readonly"
											id="input-cancellationEndDate" />
									</div>
								</div>
							</div>

							<!-- Order Date -->
							<div class="form-group">
								<div class="col-sm-1">
									<input type="checkbox" id="checkbox-orderDate" name="searchMethod" value="orderdate">
								</div>
								<div class="col-sm-2">
									<label for="checkbox-orderDate">Order Date: </label>
								</div>
								<div class="col-sm-4">
									<div class="input-daterange input-group" id="datepicker">
										<input type="text" class="input-sm form-control"
											readonly="readonly" name="start" id="input-startDate" /> <span
											class="input-group-addon">to</span> <input type="text"
											class="input-sm form-control" name="end" readonly="readonly"
											id="input-endDate" />
									</div>
								</div>
							</div>

							<!-- Order Number -->
							<div class="form-group">
								<div class="col-sm-1 col-md-1">
									<input type="checkbox" id="checkbox-feedbackType" name="searchMethod" value="feedbacktype">
								</div>
								<div class="col-sm-2 col-md-2">
									<label for="checkbox-feedbackType">Feedback Type.:</label>
								</div>
								<div class="col-sm-4 col-md-4">
									<select id="select-feedbackType" class="form-control">
										<option value="">Please Select</option>
										<option value="0">All Type</option>
										<%
											List<Vendor> list = Vendor.searchVendorFeed();
											for (Vendor i: list) {
										%>
										<option value="<%=i.getCode()%>"><%=i.getName()%></option>
										<%
											}
										%>
									</select>
								</div>
							</div>
							
							<!-- Order Number -->
							<div class="form-group">
								<div class="col-sm-1 col-md-1">
									<input type="checkbox" id="checkbox-orderNo" name="searchMethod"
										onclick="toggleSearchField()" value="orderno">
								</div>
								<div class="col-sm-2 col-md-2">
									<label for="checkbox-orderNo">Order No.:</label>
								</div>
								<div class="col-sm-4 col-md-4">
									<input type="text" class="form-control" id="input-orderNo"
										maxlength="10" onblur="prependZeroes(this, 10)">
								</div>
							</div>

							<!-- Reference Number -->
							<div class="form-group">
								<div class="col-sm-1 col-md-1">
									<input type="checkbox" id="checkbox-referenceNo" name="searchMethod"
										onclick="toggleSearchField()" value="referenceno">
								</div>
								<div class="col-sm-2 col-md-2">
									<label for="checkbox-referenceNo">Reference No.:</label>
								</div>
								<div class="col-sm-4 col-md-4">
									<input type="text" class="form-control" id="input-referenceNo"
										maxlength="10" onblur="prependZeroes(this, 10)">
								</div>
							</div>

							<!-- Buttons -->
							<div class="form-group">
									<div class="col-sm-4 col-md-4"></div>
									<div class="col-sm-8 col-md-8">
										<button type="button" class="btn btn-info vendor_submit" onclick="searchAcq()" disabled>
											<span class="glyphicon glyphicon-search"></span> Search
										</button>
										<button type="button" id="cancel" class="btn btn-secondary" data-dismiss="modal">Cancel</button>
									</div>
								</div>
						</form>
					</div>
				</div>
			</div>

			<!-- Search Result -->
		<!-- 	<div class="panel panel-default" id="panel-result">
				<div class="panel-heading">
					<h4 class="panel-title">
						<a data-toggle="collapse" data-parent="#form_parent"
							href="#result">Result</a>
					</h4>
				</div>
				<div id="result" class="panel-collapse collapse">
					<div class="panel-body" id="search-result"
						style="overflow: scroll;">
						<table id="table-order" class="table table-hover"
							data-toggle="table" data-search="true" data-pagination="true"
							data-show-columns="true" data-click-to-select="true">
							<thead>
								<tr>
									<th data-sortable="true">Order No</th>
									<th data-sortable="true">Ref. No</th>
									<th data-sortable="true">Vendor</th>
									<th data-sortable="true">Order Date</th>
									<th data-sortable="true">Title</th>
									<th data-sortable="true">Order Type</th>
									<th data-sortable="true">Order Status</th>
									<th data-sortable="true">Invoice Status</th>
									<th data-sortable="true">Copies Sets</th>
									<th data-sortable="true">Received</th>
								</tr>
							</thead>
							<tbody></tbody>
						</table>
					</div>
				</div>
			</div> -->
		</div>
		<!-- Hidden Search parameters -->
		<input type="hidden" id="input-searchParams">

	<script type="text/javascript" src="js/09.js"></script>
	<script type="text/javascript" src="js/shared.js"></script>
</div>
