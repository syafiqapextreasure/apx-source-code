<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/IRS/Listing/AAIL0450.js"></script>
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
							<input type="radio" id="allPatron" name="selectOption"
								value="all"> <label for="allPatron">Print all patrons</label>
						</div>
					</div>

					<div class="form-group">
						<div class="col-sm-2 col-md-2">
							<input type="radio" id="rangePatron" name="selectOption"
								value="range"> <label for="rangePatron">Patron ID from </label>
						</div>
						<div class="col-sm-4">
							<div class="input-daterange input-group">
								<input type="text" class="input-sm form-control"
									name="startDate" id="input-patron1" autocomplete="off" /> <span
									class="input-group-addon">to</span> <input type="text"
									class="input-sm form-control" name="endDate" id="input-patron2"
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
							<th>Patron ID</th>
							<th>Name</th>
							<th>Specialization</th>
							<th>Descriptor</th>
							<th>Hits</th>
						</tr>
					</thead>
				</table>
			</div>
		</div>
	</div>
	<!-- END MAIN CONTENT -->
</body>
</html>