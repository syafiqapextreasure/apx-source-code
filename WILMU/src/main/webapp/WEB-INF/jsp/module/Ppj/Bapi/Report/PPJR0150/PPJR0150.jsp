<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/Ppj/Bapi/Report/PPJR0150.js"></script>
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
							<label>Search by Date :</label>
						</div>
						<div class="col-sm-2">
							<div class="input-daterange input-group">
								<input type="text" class="form-control"
									name="valueDate" id="input-valueDate" autocomplete="off" />
							</div>
						</div>
					</div>
					<div class="form-group">
						<div class="col-sm-2 col-md-2">
							<label> <input type="checkbox" id="chckBranch"
								name="chkBoxSearchCateria" value="chckBranch"> Search by
								Branch
							</label>
						</div>
						<div class="col-sm-4">
							<div class="form-check">
								<select class="form-control"  id="branchSelect" name="branch">
									<jsp:include page="../../../../../module/Select_Branch.jsp"></jsp:include>
								</select>
							</div>
						</div>
					</div>
					<div class="form-group">
						<div class="col-sm-2 col-md-2">
							<label> <input type="checkbox" id="chckFilename"
								name="chkBoxSearchCateria" value="chckFilename"> Search
								by Filename
							</label>
						</div>
						<div class="col-sm-4">
							<div class="input-group">
								<input type="text" class="form-control" id="fileNameInput"
									name="fileNameInput">
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
				
				<button id="exportfile">Export</button>
				
				<table id="reportTable" class="table table-bordered table-striped">
					<thead>
						<tr>
							<th>File name</th>
							<th>Time</th>
							<th>Line 1</th>
							<th>Line 2</th>
							<th>Line 3</th>
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