<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/Foundation/Report/KLGS0250.js"></script>
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
							<label>Print Statistics report from</label>
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
							<label> <input type="checkbox" id="statusCheck"
								name="chkBoxSearchCateria" value="statusCheck"> Status
							</label>
						</div>
						<div class="col-sm-4">
							<div class="form-check">
								<select multiple="multiple" name="byStatus-select"
									id="byStatus-select">
									<jsp:include page="../../../../module/Select_PatronStatus.jsp"></jsp:include>
								</select>
							</div>
						</div>
					</div>
					<div class="form-group">
						<div class="col-sm-2 col-md-2">
							<label> <input type="checkbox" id="branchCheck"
								name="chkBoxSearchCateria" value="branchCheck"> Branch
							</label>
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
					<div class="btn-group pull-right ">
						<div class="col-md-1">
							<button type="button" id="Reterive" class="btn btn-primary"
								title="Retrieve">Retrieve</button>
						</div>
					</div>
				</form>
				<br> <br>
				<div class="col-md-6 col-lg-3 pull-right" style="text-align: right;"></div>
				<br> <br>
				<table id="reportTable" class="table table-bordered table-striped">
					<thead>
						<tr>
							<th></th>
							<th>Patron Category</th>
							<th>Total of Patron</th>
							<th>Expired within date range</th>
							<th>Expired before date range</th>
							<th>Active</th>
						</tr>
					</thead>
					<tfoot>
						<tr>
							<th id="toDeleteTd"></th>
							<th id="toColspanTd" style="text-align: center">Total</th>
							<th></th>
							<th></th>
							<th></th>
							<th></th>
						</tr>
					</tfoot>
				</table>
			</div>
		</div>
	</div>
	<!-- END MAIN CONTENT -->
</body>
</html>