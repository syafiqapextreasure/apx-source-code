<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/Receipting/Listing/AAEL0250.js"></script>
</head>
<body>
	<!-- START MAIN CONTENT -->
	<div class="box box-default">
		<div class="panel panel-default">
			<div class="panel-heading"></div>
			<div class="panel-body">
				<form class="form-horizontal">
					<jsp:include page="../../headerSearchCriteriaListingRe.jsp"></jsp:include>
				</form>
				<br> <br>
				<table id="reportTable" class="table table-bordered table-striped">
					<thead>
						<tr>
							<th>No</th>
							<th>Trx No.</th>
							<th>Date</th>
							<th>Code</th>
							<th>Desc</th>
							<th>Patron ID</th>
							<th>Reference</th>
							<th>Amount</th>
							<th>Paid Amount</th>
							<th>Override</th>
							<th>Balance</th>
						</tr>
					</thead>
				</table>
				<br>
				<div class="col-md-12 col-lg-12"
					style="border: 1px solid #ddd; text-align: right;">
					<div class="row divtotal">
								<label>Total Amount: &nbsp;</label><span id="allTotalAmount">
									0</span>
								<label>Total Paid Amount: &nbsp;</label><span id="allTotalPaidAmount">
									0</span>
								<label>Total Override: &nbsp;</label><span id="allTotalOverrideAmount">
									0</span>
								<label>Total Balance: &nbsp;</label><span id="allTotalBalanceAmount">
									0</span>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- END MAIN CONTENT -->
</body>
</html>