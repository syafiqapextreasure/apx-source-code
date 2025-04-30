<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/Receipting/Listing/AAEL0350.js"></script>
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
							<label>Patron ID
							</label>
						</div>
						<div class="row">
							<jsp:include page="../../Listing/AAEL0250/searchPatronId.jsp"></jsp:include>
						</div>
					</div>

					<div class="form-group">
						<div class="col-sm-2 col-md-2">
							<label> <input type="checkbox" id="dateRange"
								name="chkBoxSearchCateria" value="dateRange"> Date Range
								from
							</label>
						</div>
						<div class="col-sm-4">
							<div class="input-daterange input-group">
								<input type="text" class="input-sm form-control"
									name="startDate" id="input-startDate" autocomplete="off" /> <span
									class="input-group-addon">to</span> <input type="text"
									class="input-sm form-control" name="endDate" id="input-endDate"
									autocomplete="off" />
							</div>
						</div>
					</div>
					<div class="btn-group pull-right">
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
							<th class="removeHeaderForTable">Items</th>
							<th style="text-align: right" class="removeHeaderForTable">Value</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<!-- <th>1</th> -->
							<th>Patron Id</th>
							<td style="text-align: left" id="patronId"></td>
						</tr>
						<tr>
							<!-- <th>2</th> -->
							<th>Patron Name</th>
							<td style="text-align: left" id="patronName"></td>
						</tr>
						<tr>
							<!-- <th>3</th> -->
							<th>Balance</th>
							<td style="text-align: left" id="balance"></td>
						</tr>
					</tbody>
				</table>
				<br>
			</div>
		</div>
	</div>
	<!-- END MAIN CONTENT -->
</body>
</html>