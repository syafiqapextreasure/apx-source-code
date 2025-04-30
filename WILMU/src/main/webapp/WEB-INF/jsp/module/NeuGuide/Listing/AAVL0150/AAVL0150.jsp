<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/NeuGuide/Listing/AAVL0150.js"></script>
</head>
<body>
	<!-- START MAIN CONTENT -->
	<div class="box box-default">
		<div class="panel panel-default">
			<div class="panel-heading"></div>
			<div class="panel-body" id="display_cirAct">
				<form class="form-horizontal" name="cirActForm" id="cirActForm">
					<div class="form-group">
						<div class="col-sm-2 col-md-2">
							<label>Stop Date</label>
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
					<div class="form-group">
						<div class="col-sm-2 col-md-2"></div>
						<div class="col-sm-3 div-vendorName"></div>
					</div>

					<!-- MODAL WHEN CLICK Search button-vendorSearch -->
					<div class="modal fade" id="modal_vendorSearch" tabindex="-1"
						role="dialog" aria-labelledby="modal_vendorSearch"
						data-keyboard="false" data-backdrop="static">
						<div class="modal-dialog"
							style="width: 65%; height: 100vh; overflow-y: hidden;">
							<div class="modal-content" id="modal_vendorSearchContent">
								<!-- Remote content load here  -->
							</div>
						</div>
					</div>
					<!-- Modal END modal search -->
					
					<div class="btn-group pull-right ">
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
							<th>No</th>
							<th>Course</th>
							<th>Subject</th>
							<th>Semester</th>
							<th>Instructor</th>
							<th>Control No</th>
							<th>Title</th>
							<th>Start Date</th>
							<th>Stop Date</th>
						</tr>
					</thead>
				</table>
			</div>
		</div>
	</div>
	<!-- END MAIN CONTENT -->
</body>
</html>