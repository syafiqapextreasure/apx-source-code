<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/Cataloging/globalCatReportAndListing.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/Cataloging/Listing/AACL0250.js"></script>
</head>
<body>
	<!-- START MAIN CONTENT -->
	<div class="box box-default">
		<div class="panel panel-default">
			<div class="panel-heading">
			</div>
			<div class="panel-body" id="display_fndReportnListing">
				<form class="form-horizontal" name="catReportnListing"
					id="catReportnListing">
					<div class="form-group">
						<div class="col-sm-2 col-md-2">
							<label class="form-check-label"> Database </label>
						</div>
						<div class='col-sm-6'>
							<div class="form-check">
								<select id="databaseType" multiple="multiple" name="databaseType">
									<jsp:include page="../../../../module/Select_CatOrderType.jsp"></jsp:include>
								</select>
							</div>
						</div>
					</div>
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

					<div class="form-group rangeControlnoDiv">
						<div class="col-sm-2 col-md-2">
							<input class="form-check-input" type="checkbox" value="range"
								id="chkBoxRangeControlNo"> <label class="rangeLabel">Control
								No Range</label>
						</div>
						<div class="col-sm-4">
							<div class="input-group">
								<input type="text"
									class="input-sm form-control startInputCtrlno"
									name="startInputCtrlno" id="startInputCtrlno"
									autocomplete="off" /> <span class="input-group-addon">to</span>
								<input type="text" class="input-sm form-control endInputCtrlno"
									name="endInputCtrlno" id="endInputCtrlno" autocomplete="off" />
							</div>
						</div>
					</div>
					
					<input type="hidden" class="patrSelection" value="cOffiId">
					<jsp:include page="../../../../module/PatronID.jsp"></jsp:include>


					<div class="form-group">
						<div class="col-sm-2 col-md-2">
							<label class="form-check-label"> Format </label>
						</div>
						<div class="form-group">
							<div class="col-sm-2 col-md-2">
								<label class="form-check-label"> <input
									class="form-check-input" type="radio" name="viewFormat"
									id="viewFormat" value="MARC" data-name="MARC" checked>
									MARC
								</label>
							</div>
							<div class="col-sm-2 col-md-2">
								<label class="form-check-label dateselectioinlabel"> <input
									class="form-check-input" type="radio" name="viewFormat"
									id="viewFormat" value="Linear" data-name="Linear">
									Linear
								</label>
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
				<br>
				<br>
				<table id="catreportTable"
					class="table table-bordered table-striped display compact"
					width="100%">
					<thead>
						<tr>
							<th>Branch</th>
							<th>Control No</th>
							<th>Database</th>
							<th>Catalog Date</th>
							<th>Index Date</th>
							<th>Catalog Officer</th>
							<th>Index Officer</th>
							<th>Tag</th>
							<th>Description</th>
							<th>Indicator 1</th>
							<th>Indicator 2</th>
							<th>Data</th>
						</tr>
					</thead>
				</table>
			</div>
		</div>
	</div>
	<!-- END MAIN CONTENT -->
</body>
</html>