<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/Receipting/Report/KLRL0350.js"></script>
</head>
<body>
	<!-- START MAIN CONTENT -->
	<div class="box box-default">
		<div class="panel panel-default">
			<div class="panel-heading"></div>
			<div class="panel-body">
				<form class="form-horizontal">
					<div class="form-group">
						<div class="col-sm-2 col-md-2">
							<label>Print all items with transaction date from</label>
						</div>
						<div class="col-sm-3">
							<div class="input-daterange input-group">
								<input type="text" class="input-sm form-control"
									name="startDate" id="input-startDate" autocomplete="off" /> <span
									class="input-group-addon">to</span> <input type="text"
									class="input-sm form-control" name="endDate" id="input-endDate"
									autocomplete="off" />
							</div>
						</div>
					</div>
					<br>
					<div class="form-group">
						<div class="col-sm-2 col-md-2">
							<label> <input type="checkbox" id="branchSelect"
								name="chkBoxBranchCateria" value="Y"> Branch
							</label>
						</div>
						<div class="col-sm-2 patronCategory">
							<div class="form-check">
								<select id="branch" multiple="multiple" name="branch">
									<jsp:include page="../../../../module/Select_Branch.jsp"></jsp:include>
								</select>
							</div>
						</div>
					</div>
					<div class="btn-group pull-right">
						<div class="col-md-1">
							<button type="button" id="Reterive" class="btn btn-primary"
								title="Retrieve">Retrieve</button>
						</div>
					</div>
					<br>
				</form>
				<br>
				<div class="col-md-6 col-lg-3 pull-right divtotal divTotalSum" style="text-align: right;">
				</div>

				<br> <br>
				<table id="reportTableBranch"
					class="table table-bordered table-striped display compact"
					style="width: 100%;">
					<thead>
						<tr>
							<th>Branch</th>
							<th>Transaction Type</th>
							<th>Collection (RM)</th>
						</tr>
					</thead>
				</table>
				<br>

			</div>
		</div>
	</div>
	<!-- END MAIN CONTENT -->
</body>
</html>