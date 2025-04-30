<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/IRS/Report/AAIR0150.js"></script>
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
							<label for="requestDate">Statistics date from </label>
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
					<div class="form-group">
						<div class="col-sm-2 col-md-2">
							<label> Officer ID </label>
						</div>
						<div class="col-sm-2 patronCategory">
							<div class="form-check">
								<jsp:include page="../../../PatronID.jsp"></jsp:include>
							</div>
						</div>
					</div>
					<div class="form-group">
						<div class="col-sm-2 col-md-2">
							<label> <input type="checkbox" id="branchSelect"
								name="chkBoxCategory" value="Y"> Membership category
							</label>
						</div>
						<div class="col-sm-2">
							<div class="form-check">
								<select id="branch" multiple="multiple" name="branch">
									<jsp:include
										page="../../../../module/Select_PatronCategory.jsp"></jsp:include>
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
				</form>
				<br> <br>
				<table id="reportTable" class="table table-bordered table-striped">
					<thead>
						<tr>
							<th>Patron</th>
							<th>Status</th>
							<th>Reserve Date</th>
							<th>Reserve Time</th>
							<th>Priority</th>
							<th>Title</th>
							<th>Pickup Branch</th>
						</tr>
					</thead>
				</table>
			</div>
		</div>
	</div>
	<!-- END MAIN CONTENT -->
</body>
</html>