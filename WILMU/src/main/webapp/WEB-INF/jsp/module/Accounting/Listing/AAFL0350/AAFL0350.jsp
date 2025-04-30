<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/Accounting/Listing/AAFL0350.js"></script>
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
							<label>Print Fund Code from</label>
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

					<div class="btn-group pull-right ">
						<div class="col-md-1">
							<button type="button" id="Reterive" class="btn btn-primary"
								title="Retrieve">Retrieve</button>
						</div>
					</div>
				</form>
				<br> <br>
				<table id="reportTable" class="table table-bordered table-striped">
					<thead>
						<tr>
							<th>Fund Code</th>
							<th>Description</th>
							<th>Allocation</th>
							<th>Increment</th>
							<th>Decrement</th>
							<th>Encumbrance</th>
							<th>Commitment</th>
							<th>Liability</th>
							<th>Payment</th>
						</tr>
					</thead>
					<tfoot>
						<tr>
							<th style="border-right:none;padding:8px 10px;">Total</th>
							<th></th>
							<th id="totalAllocation" style="padding:8px 10px;"></th>
							<th id="totalIncrement" style="padding:8px 10px;"></th>
							<th id="totalDecrement" style="padding:8px 10px;"></th>
							<th id="totalEncumbrance" style="padding:8px 10px;"></th>
							<th id="totalCommitment" style="padding:8px 10px;"></th>
							<th id="totalLiability" style="padding:8px 10px;"></th>
							<th id="totalPayment" style="padding:8px 10px;"></th>
						</tr>
					</tfoot>
				</table>
			</div>
		</div>
	</div>
	<!-- END MAIN CONTENT -->
</body>
</html>