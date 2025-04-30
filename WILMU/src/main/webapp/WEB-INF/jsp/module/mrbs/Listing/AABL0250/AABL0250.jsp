<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/mrbs/Listing/AABL0250.js"></script>
</head>
<body>
	<!-- START MAIN CONTENT -->
	<div class="box box-default">
		<div class="panel panel-default">
			<div class="panel-heading"></div>
			<div class="panel-body">
				<form class="form-horizontal">
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
							<th>Capacity</th>
							<th>Book By Multiday</th>
							<th>Price Per Day</th>
							<th>Price Half Day</th>
							<th>Price Per Hour</th>
							<th>Admin email</th>
						</tr>
					</thead>
				</table>
			</div>
		</div>
	</div>
	<!-- END MAIN CONTENT -->
</body>
</html>