<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/ILL_Outgoing_Request/Report/AALR0250.js"></script>
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
							<input type="radio" id="requestName" name="selectOption"
								value="requestName"> <label for="requestName">Requestor
								Name </label>
						</div>
						<div class="col-sm-4">
							<div class="input-group">
								<select class="input-sm" style="width:17vw;" name="requestorName-text" id="requestorName-text">
									<jsp:include
										page="../../../../module/Select_RequestorName.jsp">
										<jsp:param name="requestorCode" value="OUTGOING" /></jsp:include>
								</select>
							</div>
						</div>
					</div>

					<div class="form-group">
						<div class="col-sm-2 col-md-2">
							<input type="radio" id="requestDate" name="selectOption"
								value="requestDate"> <label for="requestDate">Request
								Date from </label>
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
				<div class="col-md-6 col-lg-3 pull-right" style="text-align: right;"></div>
				<br> <br>
				<table id="reportTable" class="table table-bordered table-striped">
					<thead>
						<tr>
							<th>No</th>
							<th>Request No</th>
							<th>Requestor</th>
							<th>Title</th>
							<th>Control No</th>
							<th>Accession No</th>
							<th>Dt Request</th>
							<th>Dt Expected</th>
							<th>Dt Received</th>
							<th>Dt Returned</th>
							<th>Lending Library</th>
							<th>Contact Person</th>
						</tr>
					</thead>
				</table>
			</div>
		</div>
	</div>
	<!-- END MAIN CONTENT -->
</body>
</html>