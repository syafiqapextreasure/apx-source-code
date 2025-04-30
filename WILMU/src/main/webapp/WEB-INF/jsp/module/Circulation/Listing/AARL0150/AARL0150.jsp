<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/Circulation/Listing/AARL0150.js"></script>
</head>
<body>
	<!-- START MAIN CONTENT -->
	<div class="box box-default">
		<div class="panel panel-default">
			<div class="panel-heading">
			</div>
			<div class="panel-body" id="display_cirAct">
				<form class="form-horizontal" name="cirActForm" id="cirActForm">
					<jsp:include page="../AARL0150/headerSearchCriteriaListingCir2.jsp"></jsp:include>
				</form>
				<br> <br>
				<table id="reportTable" class="table table-bordered table-striped">
					<thead>
						<tr>
							<th>No</th>
							<th>Accession No</th>
							<th>Charge Date</th>
							<th>Due Date</th>
							<th>Title</th>
							<th>Call No</th>
							<th>Patron ID/Name</th>
							<th>Item Category</th>
							<th>Late By</th>
							<th>Branch</th>
							<th>Location</th>
						</tr>
					</thead>
				</table>
			</div>
		</div>
	</div>
	<!-- END MAIN CONTENT -->
</body>
</html>