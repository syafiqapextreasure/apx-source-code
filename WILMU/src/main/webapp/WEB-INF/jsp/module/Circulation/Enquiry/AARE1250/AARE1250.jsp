<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/Circulation/Enquiry/AARE1250.js"></script>
</head>
<body>
	<!-- START MAIN CONTENT -->
	<div class="box box-default">
		<div class="panel panel-default">
			<div class="panel-heading"></div>

			<div class="panel-body" id="display_cirAct">
				<form class="form-horizontal" name="cirActForm" id="cirActForm">
					<jsp:include page="../../headerSearchCriteriaListingCir.jsp"></jsp:include>
				</form>
				<br> <br>
				<table id="ttttttTable"
					class="table table-bordered table-striped display compact"
					width="100%">
					<thead>
						<tr>
							<th>Main</th>
							<th>Description</th>
							<th>Details</th>
							<th>Code</th>
							<th>Branch</th>
							<th>Value</th>
						</tr>
					</thead>
				</table>
			</div>
		</div>
	</div>
	<!-- END MAIN CONTENT -->
</body>
</html>