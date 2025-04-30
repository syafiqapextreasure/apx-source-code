<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/Report/AARL0950.js"></script>
</head>
<body>
	<!-- START MAIN CONTENT -->
	<div class="box box-default">
		<div class="panel panel-default">
			<div class="panel-heading">
			</div>

			<div class="panel-body" id="display_ReportnListing">
				<form class="form-horizontal" name="auditTrailForm"
					id="auditTrailForm">
					<%
					String moduleProg = request.getParameter("value");
					%>

					<input type='hidden' class="modprog" value="<%=moduleProg%>">

					<div class="form-group dateDiv">
						<div class="col-sm-2 col-md-2 dateLabel">
							<label>Activity Date Range</label>
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
							<label>Activity Code</label>
						</div>
						<div class='col-sm-6'>
							<div class="form-check">
								<select id="actCode" multiple="multiple" name="actCode">
									<jsp:include page="../../../module/Select_AuditTrail.jsp">
										<jsp:param name="auditTrailBy" value="<%=moduleProg%>" />
									</jsp:include>
								</select>
							</div>
						</div>
					</div>

					<input type="hidden" class="patrSelection" value="cOffiId">
					<jsp:include page="../../../module/PatronID.jsp"></jsp:include>

					<div class="btn-group pull-right ">
						<div class="col-md-1">
							<button type="button" id="Reterive" class="btn btn-primary"
								title="Retrieve">Retrieve</button>
						</div>
					</div>
				</form>
				<br>
				<br>
				<table id="auditTrailTable"
					class="table table-bordered table-striped display compact"
					width="100%">
					<thead>
						<tr>
							<th>No</th>
							<th>Date</th>
							<th>Time</th>
							<th>Activity Code</th>
							<th>Reference</th>
							<th>Patron ID</th>
							<th>Patron Name</th>
							<th>Terminal ID</th>
						</tr>
					</thead>
				</table>
			</div>
		</div>
	</div>
	<!-- END MAIN CONTENT -->
</body>
</html>