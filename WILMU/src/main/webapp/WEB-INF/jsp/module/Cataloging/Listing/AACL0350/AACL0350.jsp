<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/Cataloging/globalCatReportAndListing.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/Cataloging/Listing/AACL0350.js"></script>
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
							<div class="col-sm-3 col-md-3">
								<label class="form-check-label"> <input
									class="form-check-input" type="radio" name="dateSelection"
									id="dateSelection" value="02CRDATE"
									data-name="Catalog Date  Range" checked> Catalog Date
									Range
								</label>
							</div>
							<div class="col-sm-3 col-md-3">
								<label class="form-check-label dateselectioinlabel"> <input
									class="form-check-input" type="radio" name="dateSelection"
									id="dateSelection" value="02IDXDATE"
									data-name="Index Date Range"> Index Date Range
								</label>
							</div>
						</div>
					</div>

					<div class="form-group">
						<div class="col-sm-2 col-md-2">
							<label class="form-check-label"> </label>
						</div>

						<div class="form-group">
							<div class="col-sm-3 col-md-3">
								<label class="form-check-label"> <input
									class="form-check-input" type="radio" name="dateSelection"
									id="dateSelection" value="03CRDATE"
									data-name="Accession Date  Range"> Accession Date Range
								</label>
							</div>
							<div class="col-sm-3 col-md-3">
								<label class="form-check-label dateselectioinlabel"> <input
									class="form-check-input" type="radio" name="dateSelection"
									id="dateSelection" value="03RFCDATE"
									data-name="Release Date Range"> Release Date Range
								</label>
							</div>
						</div>
					</div>


					<div class="form-group">
						<div class="col-sm-2 col-md-2">
							<label class="form-check-label"> </label>
						</div>

						<div class="form-group">
							<div class="col-sm-3 col-md-3">
								<label class="form-check-label dateselectioinlabel"> <input
									class="form-check-input" type="radio" name="dateSelection"
									id="dateSelection" value="03LASTACT"
									data-name="Last Date Range"> Last Date Range
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

					<div class="form-group branchDiv">
						<div class="col-sm-2 col-md-2">
							<label><input class="form-check-input" type="checkbox"
								name="chkBoxBranch" id="chkBoxBranch" value="branchChecked">
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

					<div class="form-group rangeAccDiv">
						<div class="col-sm-2 col-md-2">
							<input class="form-check-input" type="checkbox" value="rangeAcc"
								id="chkBoxRangeAcc"> <label class="rangeLabel">Accession
								No Range</label>
						</div>
						<div class="col-sm-4">
							<div class="input-group">
								<input type="text" class="input-sm form-control startInputAcc"
									name="startInputAcc" id="startInputAcc" autocomplete="off" />
								<span class="input-group-addon">to</span> <input type="text"
									class="input-sm form-control endInputAcc" name="endInputAcc"
									id="endInputAcc" autocomplete="off" />
							</div>
						</div>
					</div>

					<div class="form-group rangeCallnoDiv">
						<div class="col-sm-2 col-md-2">
							<input class="form-check-input" type="checkbox"
								value="rangeCallno" id="chkBoxRangeCallno"> <label
								class="rangeLabel">Call No Range</label>
						</div>
						<div class="col-sm-4">
							<div class="input-group">
								<input type="text"
									class="input-sm form-control startInputCallno"
									name="startInputCallno" id="startInputCallno"
									autocomplete="off" /> <span class="input-group-addon">to</span>
								<input type="text" class="input-sm form-control endInputCallno"
									name="endInputCallno" id="endInputCallno" autocomplete="off" />
							</div>
						</div>
					</div>

					<div class="form-group titleDiv">
						<div class="col-sm-2 col-md-2">
							<input class="form-check-input" type="checkbox" value="iTilte"
								id="chkBoxTitle"> <label class="titleLabel">Title</label>
						</div>
						<div class="col-sm-7">
							<input type="text" class="input-sm form-control"
								name="inputtitle" id="inputtitle">
						</div>
					</div>


					<div class="form-group div3">
						<div class="col-sm-2 smdDiv">
							<div class="form-check">
								<input class="form-check-input" type="checkbox"
									value="invstatus" id="chkBoxsmd"> <label
									class="form-check-label">SMD</label>
							</div>
						</div>
						<div class='col-sm-4 smdDiv'>
							<div class="form-check">
								<select id="smd" multiple="multiple" name="smd">
									<jsp:include page="../../../Select_SMD.jsp"></jsp:include>
								</select>
							</div>
						</div>

						<div class="col-sm-2 itemstatDiv">
							<div class="form-check">
								<input class="form-check-input" type="checkbox"
									value="itemstatus" id="chkBoxIcat"> <label
									class="form-check-label statusLabel">Item Category</label>
							</div>
						</div>
						<div class='col-sm-4 itemstatDiv'>
							<div class="form-check">
								<select id="icat" multiple="multiple" name="icat">
									<jsp:include page="../../../Select_ItemCate.jsp"></jsp:include>
								</select>
							</div>
						</div>
					</div>

					<div class="form-group div3">
						<div class="col-sm-2 smdDiv">
							<div class="form-check">
								<input class="form-check-input" type="checkbox" value="location"
									id="chkBoxlocation"> <label class="form-check-label">Location</label>
							</div>
						</div>
						<div class='col-sm-4 smdDiv'>
							<div class="form-check">
								<select id="loca" multiple="multiple" name="loca">
									<jsp:include page="../../../Select_Loca.jsp"></jsp:include>
								</select>
							</div>
						</div>

						<div class="col-sm-2 itemstatDiv">
							<div class="form-check">
								<input class="form-check-input" type="checkbox" value="doctatus"
									id="chkBoxdocStatus"> <label
									class="form-check-label statusLabel">Status</label>
							</div>
						</div>
						<div class='col-sm-4 itemstatDiv'>
							<div class="form-check">
								<select id="doctatus" multiple="multiple" name="doctatus">
									<jsp:include
										page="../../../../include/shared/Selection/Select_DocStatus.jsp"></jsp:include>
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
				<table id="catreportTable"
					class="table table-bordered table-striped display compact"
					width="100%">
					<thead>
						<tr>
							<th>No</th>
							<th>Control No</th>
							<th>Acc No</th>
							<th>Title</th>
							<th>Call No</th>
							<th>Author</th>
							<th>Edition</th>
							<th>Publisher</th>
							<th>ISBN</th>
							<th>Volume</th>
							<th>SMD</th>
							<th>ICAT</th>
							<th>Location</th>
							<th>Status</th>
							<th>Local Price</th>
							<th>Vendor</th>
							<th>Officer</th>
							<th>Branch</th>
						</tr>
					</thead>
				</table>
			</div>
		</div>
	</div>
	<!-- END MAIN CONTENT -->
</body>
</html>