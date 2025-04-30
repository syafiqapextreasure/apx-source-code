<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/Accounting/Listing/AAFL0250.js"></script>
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
							<input type="radio" id="byLocation" name="selectOption"
								value="byLocation"> <label for="byLocation">by
								Location</label>
						</div>
						<div class="col-sm-4">
							<div class="form-check">
								<select multiple="multiple" name="byLocation-select"
									id="byLocation-select">
									<jsp:include page="../../../../module/Select_Loca.jsp"></jsp:include>
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
					<div class="btn-group pull-right ">
						<div class="col-md-1">
							<button type="button" id="Reterive" class="btn btn-primary"
								title="Retrieve">Retrieve</button>
						</div>
					</div>
				</form>
				<br> <br>
				<div class="col-md-6 col-lg-3 pull-right divtotal divTotalSum"
					style="text-align: right;"></div>
				<br> <br>
				<table id="reportTableNotBranch"
					class="table table-bordered table-striped">
					<thead>
						<tr>
							<th>Code</th>
							<th>Description</th>
							<th>Titles</th>
							<th>Copies</th>
							<th>Amount</th>
						</tr>
					</thead>
					<tfoot>
						<tr>
							<th style="border-right:none;padding:8px 10px;">Total</th>
							<th></th>
							<th id="totalTitle" style="padding:8px 10px;"></th>
							<th id="totalCopy" style="padding:8px 10px;"></th>
							<th id="totalAmount" style="padding:8px 10px;"></th>
						</tr>
					</tfoot>
				</table>
 				<table id="reportTableBranch"
					class="table table-bordered table-striped">
					<thead>
						<tr>
							<th>Branch</th>
							<th>Code</th>
							<th>Description</th>
							<th>Titles</th>
							<th>Copies</th>
							<th>Amount</th>
						</tr>
					</thead>
				</table>
				<!-- <div id="reportTableBranch" class="table table-bordered table-striped"></div> -->
			</div>
		</div>
	</div>
	<!-- END MAIN CONTENT -->
</body>
</html>