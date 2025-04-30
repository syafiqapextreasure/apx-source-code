<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/Receipting/Listing/AAEL0150.js"></script>
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
							<label>Transaction Date From</label>
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
					<br>
					<div class="form-group">
						<div class="col-sm-2">
							<label>Patron Category</label>
						</div>
						<div class="col-sm-3 patronCategory">
							<div class="form-check">
								<select id="patronCate" multiple="multiple" name="patronCate">
									<jsp:include
										page="../../../../module/Select_PatronCategory.jsp"></jsp:include>
								</select>
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
					class="table table-bordered table-striped display compact" style="width:100%;">
					<thead>
						<tr>
							<th>No</th>
							<th>Patron ID</th>
							<th>Patron Name</th>
							<th>Patron Category</th>
							<th>Course</th>
							<th>Department</th>
							<th>Amount</th>
						</tr>
					</thead>
				</table>
				<br>
				<div class="col-md-12 col-lg-12"
					style="border: 1px solid #ddd; text-align: right;">
					<div class="row">
						<div class="form-group divtotal">
							<div class="col-md-12 col-lg-12"><label>Total Amount: &nbsp;</label><span id="allTotalAmount"> 0</span>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- END MAIN CONTENT -->
</body>
</html>