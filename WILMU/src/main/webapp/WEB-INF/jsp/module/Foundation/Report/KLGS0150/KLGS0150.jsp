<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/Foundation/globalFndReportAndListing.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/Foundation/Report/KLGS0150.js"></script>
</head>
<body>
	<!-- START MAIN CONTENT -->
	<div class="box box-default">
		<div class="panel panel-default">
			<div class="panel-heading">
				<!-- <div class="clearfix"></div> -->
			</div>

			<div class="panel-body" id="display_fndReportnListing">
				<form class="form-horizontal" name="fndReportnListing"
					id="fndReportnListing">
					<div class="form-group dateDiv">
						<div class="col-sm-2 col-md-2 dateLabel">
							<label>Date</label>
						</div>
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
							<label>Branch</label>
						</div>
						<div class='col-sm-6'>
							<div class="form-check">
								<select id="branch" multiple="multiple" name="branch">
									<jsp:include page="../../../../module/Select_Branch.jsp"></jsp:include>
								</select>
							</div>
						</div>
					</div>

					<div class="form-group patrStatnCate">
						<div class="col-sm-2">
							<div class="form-check">
								<input class="form-check-input" type="checkbox" value="patrStat"
									id="chkPatrStat"> <label class="form-check-label"
									for="chkPatrStat">Patron Status</label>
							</div>
						</div>
						<div class='col-sm-4'>
							<div class="form-check">
								<select id="patronStat" multiple="multiple" name="patronStat">
									<jsp:include page="../../../../module/Select_PatronStatus.jsp"></jsp:include>
								</select>
							</div>
						</div>

						<div class="col-sm-2">
							<div class="form-check">
								<input class="form-check-input" type="checkbox" value="patrCate"
									id="chkPatrCate"> <label class="form-check-label"
									for="chkPatrCate">Patron Category</label>
							</div>
						</div>
						<div class='col-sm-4'>
							<div class="form-check">
								<select id="patronCate" multiple="multiple" name="patronCate">
									<jsp:include
										page="../../../../module/Select_PatronCategory.jsp"></jsp:include>
								</select>
							</div>
						</div>
					</div>
					<div class="form-group">
						<div class="col-sm-3">
							<div class="form-check">
								<input class="form-check-input" type="checkbox" value="expiredMember"
									id="chkExpiredMember"> <label class="form-check-label"
									for="chkExpiredMember">Include Expired Membership</label>
							</div>
						</div>
					</div>
					<jsp:include
						page="../../headerSearchCriteriaFndReportAndListing.jsp"></jsp:include>
				</form>
				<br> <br>
				<table id="fndreportTable"
					class="table table-bordered table-striped display compact"
					width="100%">
					<thead>
						<tr>
							<th>No</th>
							<th>Patron Category</th>
							<th>New Membership</th>
							<th>Registration Fee (RM)</th>
							<th>Deposit (RM)</th>
						</tr>
					</thead>
					<tfoot align="right">
						<tr>
							<th></th>
							<th>Total</th>
							<th></th>
							<th></th>
							<th></th>
						</tr>
					</tfoot>
				</table>
			</div>
		</div>
	</div>
	<!-- END MAIN CONTENT -->
</body>
</html>