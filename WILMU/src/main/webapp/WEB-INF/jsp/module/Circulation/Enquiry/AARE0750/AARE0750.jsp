<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/Circulation/Enquiry/AARE0750.js"></script>
</head>
<body>
	<!-- START MAIN CONTENT -->
	<div class="box box-default">
		<div class="panel panel-default">
			<div class="panel-heading"></div>

			<div class="panel-body" id="display_fndReportnListing">
				<form class="form-horizontal" name="cirReportnListing"
					id="fndReportnListing">
					<div class="form-group">
						<div class="col-sm-2">
							<label><input type="radio" name="radioOption"
								id="controlNo" value="controlNo">&nbsp; Control No. <span
								style="color: red">*</span></label>
						</div>
						<div class="col-sm-3">
							<div class="input-group">
								<input type="text" class="form-control test"
									id="input-contorlNo"> 
							</div>
						</div>
						<div class="col-sm-2">
							<label><input type="radio" name="radioOption"
								id="accessionNo" value="accessionNo">&nbsp; Accession No.
								<span style="color: red">*</span></label>
						</div>
						<div class="col-sm-3">
							<div class="input-group">
								<input type="text" class="form-control test"
									id="input-accessionNo">
							</div>
						</div>
					</div>
					<br>
					<div class="form-group">
						<div class="col-sm-2">
							<label>&nbsp; Title</label>
						</div>
						<div class="col-sm-3">
							<div class="input-group">
								<textarea id="input-title">
								</textarea>
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
				<table id="cirreportTable"
					class="table table-bordered table-striped display compact"
					width="100%">
					<thead>
						<tr>
							<th>Accession No</th>
							<th>Patron ID</th>
							<th>Borrow Date</th>
							<th>Due Date</th>
							<th>Return Date</th>
							<th>Charge Officer</th>
							<th>Discharge Officer</th>
						</tr>
					</thead>
				</table>
			</div>
		</div>
	</div>
	<!-- END MAIN CONTENT -->	
</body>
</html>