<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/mrbs/Report/AABR0150.js"></script>
</head>
<body>
	<!-- START MAIN CONTENT -->
	<div class="box box-default">
		<div class="panel panel-default">
			<div class="panel-heading"></div>
			<div class="panel-body">
				<form class="form-horizontal">
					<div class="form-group">
						<div class="col-sm-2">
							<label>Booking Date From</label>
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
					<div class="btn-group pull-right ">
						<div class="col-md-1">
							<button type="button" id="Reterive" class="btn btn-primary"
								title="Retrieve">Retrieve</button>
						</div>
					</div>
				</form>

				<br> <br>
				<table id="listingreportTable"
					class="table table-bordered table-striped display compact"
					style="width: 100%;">
					<thead>
						<tr>
							<th>No</th>
							<th>Room ID</th>
							<th>Room Title</th>
							<th>Room Description</th>
							<th>Total Booking</th>
						</tr>
					</thead>
				</table>
			</div>
		</div>
	</div>
	<!-- END MAIN CONTENT -->
</body>
</html>