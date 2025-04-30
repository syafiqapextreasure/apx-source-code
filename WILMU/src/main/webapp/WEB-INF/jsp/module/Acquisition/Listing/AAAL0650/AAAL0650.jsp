<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/Acquisition/globalAcqReportAndListing.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/Acquisition/Listing/AAAL0650.js"></script>
</head>
<body>
	<!-- START MAIN CONTENT -->
	<div class="box box-default">
		<div class="panel panel-default">
			<div class="panel-heading">
			</div>
			<div class="panel-body" id="display_acqReportnListing">
				<input type="hidden" id='setupCurrency' disabled> <input
					type="hidden" class='libname' disabled>
				<form class="form-horizontal" name="ReportnListing"
					id="ReportnListing">
					<div class="form-group rangeDiv">
						<div class="col-sm-3 col-md-3">
							<input class="form-check-input" type="radio" value="range"
								id="selectionoption" name="selectionoption" checked> <label
								class="rangeLabel">Range</label>
						</div>
						<div class="col-sm-5">
							<textarea class="form-control" id="rangeInput" name="rangeInput"
								rows="3"></textarea>
						</div>
					</div>
					<div class="form-group">
						<div class="col-sm-3">
							<div class="form-check">
								<input class="form-check-input" type="radio" value="controlNo"
									id="selectionoption" name="selectionoption"> <label
									class="form-check-label">Control No</label>
							</div>
						</div>
						<div class="col-sm-5">
							<div class="input-group">
								<textarea class="form-control" id="input-contorlNo"
									name="input-contorlNo" rows="3"></textarea>
								<a href="RetrieveTitleModal?action=5" id="searchCtrlNo"
									class="input-group-addon btn btn-primary searchCtrlNo"
									data-toggle="modal" data-target="#titleSearch"> <span
									class="glyphicon glyphicon-th-list"></span></a>
							</div>
						</div>
					</div>

					<div class="form-group printerStat">
						<div class="col-sm-3">
							<jsp:include page="../../../../module/CbAcqReportPrint.jsp">
								<jsp:param name="claimStatVal" value="V" />
							</jsp:include>
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
				<table id="reportTable"
					class="table table-bordered table-striped display compact"
					width="100%">
					<thead>
						<tr>
							<th>No</th>
							<th>Order No</th>
							<th>Control No</th>
							<th>Accession No</th>
							<th>Title</th>
							<th>Author</th>
							<th>Publisher</th>
							<th>Location</th>
							<th>Branch</th>
							<th>Item Category</th>
							<th>Condition</th>
							<th>SMD</th>
							<th>Volume</th>
							<th>Price</th>
							<th>Status</th>
						</tr>
					</thead>
				</table>
			</div>
		</div>
	</div>
	<!-- END MAIN CONTENT -->

	<!-- MODAL WHEN CLICK Search CONTORL NUMBER -->
	<div class="modal fade" id="titleSearch" tabindex="-1" role="dialog"
		aria-labelledby="titleSearch" data-keyboard="false"
		data-backdrop="static">
		<div class="modal-dialog" role="document"
			style="width: 55%; overflow: auto">
			<div class="modal-content">
				<!-- Remote content load here -->
			</div>
		</div>
	</div>
	<!-- Modal END modal search  control number-->
</body>
</html>