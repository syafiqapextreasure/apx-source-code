<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/Cataloging/globalCatReportAndListing.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/Cataloging/Report/KLCS0250.js"></script>
</head>
<body>
	<!-- START MAIN CONTENT -->
	<div class="box box-default">
		<div class="panel panel-default">
			<div class="panel-heading">
				<!-- <div class="clearfix"></div> -->
			</div>
			<div class="panel-body" id="display_fndReportnListing">
				<form class="form-horizontal" name="catReportnListing"
					id="catReportnListing">
					<div class="form-group">
						<div class="col-sm-2 col-md-2">
							<label class="form-check-label"> Date Range </label>
						</div>

						<div class="form-group">
							<div class="col-sm-2 col-md-2">
								<label class="form-check-label"> <input
									class="form-check-input" type="radio" name="dateSelection"
									id="dateSelection" value="02CRDATE"
									data-name="Catalog Date Range" checked> Catalog Date
									Range
								</label>
							</div>
							<div class="col-sm-2 col-md-2">
								<label class="form-check-label dateselectioinlabel"> <input
									class="form-check-input" type="radio" name="dateSelection"
									id="dateSelection" value="02IDXDATE"
									data-name="Index Date Range"> Index Date Range
								</label>
							</div>
						</div>
					</div>

					<div class="form-group dateDiv">
						<div class="col-sm-2 col-md-2 dateLabel"></div>
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

					<div class="form-group branchDiv">
						<div class="col-sm-2 col-md-2">
							<label><input class="form-check-input" type="checkbox"
								name="chkBoxBranch" id="chkBoxBranch" value="branchCheckbox">
								Branch</label>
						</div>
						<div class='col-sm-6'>
							<div class="form-check">
								<select id="branch" multiple="multiple" name="branch">
									<jsp:include page="../../../../module/Select_Branch.jsp"></jsp:include>
								</select>
							</div>
						</div>
					</div>


					<div class="form-group locaDiv">
						<div class="col-sm-2 col-md-2">
							<label><input class="form-check-input" type="checkbox"
								name="chkBoxLoca" id="chkBoxLoca" value="location">
								Location</label>
						</div>
						<div class='col-sm-6'>
							<div class="form-check">
								<select id="loca" multiple="multiple" name="loca">
									<jsp:include page="../../../../module/Select_Loca.jsp"></jsp:include>
								</select>
							</div>
						</div>
					</div>

					<div class="form-group cattypeDiv">
						<div class="col-sm-2 col-md-2">
							<label><input class="form-check-input" type="checkbox"
								name="chkBoxCattype" id="chkBoxCattype" value="catelogingtype">
								Catalog Type</label>
						</div>
						<div class='col-sm-6'>
							<div class="form-check">
								<select id="cattype" multiple="multiple" name="cattype">
									<jsp:include page="../../../../module/Select_Fndcode.jsp">
										<jsp:param name="fcode" value="AB" />
									</jsp:include>
								</select>
							</div>
						</div>
					</div>

					<!-- <div class="form-group locaDiv">
								<div class="col-sm-2 col-md-2"><label>
									<input class="form-check-input" type="checkbox" name="chkBoxdisplay" id="chkBoxdisplay" value="displayAll">
									Display All Class No</label></div>
								<div class="col-sm-2 col-md-2"><label>
									<input class="form-check-input" type="checkbox" name="chkBoxincunindex" id="chkBoxincunindex" value="incunindex">
									Include unindex record</label></div>				
							</div> -->

					<div class="btn-group pull-right ">
						<div class="col-md-1">
							<button type="button" id="Reterive" class="btn btn-primary"
								title="Retrieve">Retrieve</button>
						</div>
					</div>
				</form>

				<br>
				<br>
				<table id="catreportTable"
					class="table table-bordered table-striped display compact"
					width="100%">
					<thead>
						<tr>
							<th>No</th>
							<th>Class No Range</th>
							<th>Description</th>
							<th>Title</th>
							<th>Copies</th>
						</tr>
					</thead>
				</table>
			</div>
		</div>
	</div>
	<!-- END MAIN CONTENT -->
</body>
</html>