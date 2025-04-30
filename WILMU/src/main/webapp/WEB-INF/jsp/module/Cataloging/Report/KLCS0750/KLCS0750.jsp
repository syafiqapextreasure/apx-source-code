<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/Cataloging/Report/KLCS0750.js"></script>
</head>
<body>
	<!-- START MAIN CONTENT -->
	<div class="box box-default">
		<div class="panel panel-default">
			<div class="panel-heading"></div>
			<div class="panel-body" id="display_cirAct">
				<form class="form-horizontal" name="cirActForm" id="cirActForm">
					<div class="form-group">
						<div class="col-sm-2 col-md-2">
							<label>Created Date from</label>
						</div>
						<div class="col-sm-3">
							<div class="input-daterange input-group">
								<input type="text" class="input-sm form-control" name="fromDate"
									id="input-fromDate" autocomplete="off" /> <span
									class="input-group-addon">to</span> <input type="text"
									class="input-sm form-control" name="toDate" id="input-toDate"
									autocomplete="off" />
							</div>
						</div>
					</div>
					<div class="form-group">
						<div class="col-sm-2 col-md-2">
							<input type="radio" id="byBranch" name="selectOption"
								value="byBranch"> <label for="byBranch">by
								Branch</label>
						</div>
						<div class="col-sm-4">
							<div class="form-check">
								<select multiple="multiple" name="byBranch-select"
									id="byBranch-select">
									<jsp:include page="../../../../module/Select_Branch.jsp"></jsp:include>
								</select>
							</div>
						</div>
					</div>
					<div class="form-group">
						<div class="col-sm-2 col-md-2">
							<input type="radio" id="bySMD" name="selectOption" value="bySMD">
							<label for="bySMD">by SMD</label>
						</div>
						<div class="col-sm-4">
							<div class="form-check">
								<select multiple="multiple" name="bySMD-select"
									id="bySMD-select">
									<jsp:include page="../../../../module/Select_SMD.jsp"></jsp:include>
								</select>
							</div>
						</div>
					</div>
					<div class="form-group">
						<div class="col-sm-2 col-md-2">
							<input type="radio" id="byItemCategory" name="selectOption"
								value="byItemCategory"> <label for="byItemCategory">by
								Item Category</label>
						</div>
						<div class="col-sm-4">
							<div class="form-check">
								<select multiple="multiple" name="byItemCategory-select"
									id="byItemCategory-select">
									<jsp:include page="../../../../module/Select_ItemCate.jsp"></jsp:include>
								</select>
							</div>
						</div>
					</div>
					<div class="form-group">
						<div class="col-sm-2 col-md-2">
							<input type="checkbox" id="byVendorCode" name="selectVendorCode"
								value="byVendorCode"> <label for="byVendorCode">by
								Vendor Code</label>
						</div>
						<div class="col-sm-4">
							<div class="form-check">
								<select class="input-sm" style="width:17vw;" name="vendorCode-text" id="vendorCode-text">
									<jsp:include page="../../../../module/Select_Vendor.jsp"></jsp:include>
								</select>
							</div>
						</div>
					</div>
					<div class="btn-group pull-right ">
						<div class="col-md-1">
							<button type="button" id="Reterive" class="btn btn-primary"
								title="Retrieve">Retrieve</button>
						</div>
					</div>
				</form>
				<br> <br>
				<table id="reportTable"
					class="table table-bordered table-striped">
					<thead>
						<tr>
							<th>Accession No</th>
							<th>Control No</th>
							<th>SMD</th>
							<th>Item Category</th>
							<th>Branch</th>
							<th>Vendor</th>
							<th>Date Created</th>
							<th>Date Modified</th>
							<th>Date Released</th>
							<th>Processing Days</th>
							<th>Title</th>
						</tr>
					</thead>
				</table>
			</div>
		</div>
	</div>
	<!-- END MAIN CONTENT -->
</body>
</html>