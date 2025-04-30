<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/Accounting/Listing/AAFL0150.js"></script>
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
							<label>Transaction Code from</label>
						</div>
						<div class="col-sm-3">
							<div class="input-daterange input-group">
								<input type="text" class="input-sm form-control" name="fromCode"
									id="input-fromCode" autocomplete="off" /> <span
									class="input-group-addon">to</span> <input type="text"
									class="input-sm form-control" name="toCode" id="input-toCode"
									autocomplete="off" />
							</div>
						</div>
					</div>
					<div class="form-group">
						<div class="col-sm-2 col-md-2">
							<label>Transaction Type</label>
						</div>
						<div class="col-sm-4">
							<input type="radio" id="transaction-no" name="transaction-type" value="number">
							<label for="transaction-no">Transaction No.</label> <input type="radio"
								id="transaction-date" name="transaction-type" value="date"> <label
								for="transaction-date">Transaction Date</label>
						</div>
					</div>
					<div class="form-group">
						<div class="col-sm-2 col-md-2 transaction-change">
							<label>Transaction No. from</label>
						</div>
						<div class="col-sm-3">
							<div class="input-daterange input-group">
								<input type="text" class="input-sm form-control" name="fromInput"
									id="input-fromInput" autocomplete="off" /> <span
									class="input-group-addon">to</span> <input type="text"
									class="input-sm form-control" name="toInput" id="input-toInput"
									autocomplete="off" />
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
				<br><br>
				<table id="reportTable" class="table table-bordered table-striped">
					<thead>
						<tr>
							<th>Trx Date</th>
							<th>Trx No</th>
							<th>Fund Code</th>
							<th>Fund Desc</th>
							<th>Trx Code</th>
							<th>Trx Code Desc</th>
							<th>Description</th>
							<th>Amount</th>
							<th>Reference</th>
						</tr>
					</thead>
				</table>
			</div>
		</div>
	</div>
	<!-- END MAIN CONTENT -->
</body>
</html>