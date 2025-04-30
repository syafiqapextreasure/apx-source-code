<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/Binding/Listing/AABL0250.js"></script>
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
							<label> Item type</label>
						</div>
						<div class='col-sm-4'>
							<div class="form-check">
								<input type="radio" id="itemMonograph" name="itemType" value="M">
								<label for="itemMonograph">Monograph</label> &nbsp; <input
									type="radio" id="itemSerial" name="itemType" value="S">
								<label for="itemSerial">Serials</label>
							</div>
						</div>
					</div>

					<div class="form-group">
						<div class="col-sm-2 col-md-2">
							<label> <input type="checkbox" id="binder"
								name="chkBoxSearchCateria" value="binder"> Binder
							</label>
						</div>
						<div class="col-sm-3">
							<div class="input-group">
								<input type="text" class="form-control" id="input-vendorCode"
									name="input-vendorCode"> <a
									class="input-group-addon btn btn-primary vendorCode"
									data-toggle="modal" data-target="#modal_vendorSearch"> <span
									class="glyphicon glyphicon-th-list"></span></a>
							</div>
						</div>
					</div>
					<div class="form-group">
						<div class="col-sm-2 col-md-2"></div>
						<div class="col-sm-3 div-vendorName"></div>
					</div>
					<br>

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

					<div class="form-group">
						<div class="col-sm-2 col-md-2">
							<label> <input type="checkbox" id="controlNo"
								name="chkBoxSearchCateria" value="controlNo"> Control
								No.
							</label>
						</div>
						<div class="col-sm-3">
							<div class="input-group">
								<input type="text" class="form-control"
									id="input-contorlNo"> <a
									class="input-group-addon btn btn-primary controlNoSearch" data-toggle="modal"
									data-target="#modal_controlNoSearch"> <span
									class="glyphicon glyphicon-th-list"></span></a>
							</div>
						</div>
					</div>
					
					<!-- MODAL WHEN CLICK Search button-controlNoSearch -->
					<div class="modal fade" id="modal_controlNoSearch" tabindex="-1"
						role="dialog" aria-labelledby="modal_controlNoSearch"
						data-keyboard="false" data-backdrop="static">
						<div class="modal-dialog"
							style="width: 65%; height: 100vh; overflow-y: hidden;">
							<div class="modal-content" id="modal_controlNoSearchContent">
								<!-- Remote content load here  -->
							</div>
						</div>
					</div>
					<!-- Modal END modal search -->

					<div class="form-group">
						<div class="col-sm-2 col-md-2">
							<label> <input type="checkbox" id="dateRange"
								name="chkBoxSearchCateria" value="dateRange"> Date Sent
								from
							</label>
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
						<div class="col-sm-2 col-md-2">
							<label> <input type="checkbox" id="bindingInstructionPrintCheck"
								name="chkBoxPrintCriteria" value="BI"> Print Binding
								Instructions
							</label>
						</div>
					</div>

					<div class="form-group">
						<div class="col-sm-2 col-md-2">
							<label> <input type="checkbox" id="bindingSlipPrintCheck"
								name="chkBoxPrintCriteria" value="BS"> Print Binding
								Slip
							</label>
						</div>
					</div>

					<br>
					<div class="btn-group pull-right">
						<div class="col-md-1">
							<button type="button" id="Reterive" class="btn btn-primary"
								title="Retrieve">Retrieve</button>
						</div>
					</div>
				</form>
				<br> <br>
				<table id="reportTable1" class="table table-bordered table-striped itemChange">
					<thead>
						<tr>
							<th>Control No</th>
							<th>Title</th>
							<th>Vol. From</th>
							<th>Vol. To</th>
							<th>Iss. From</th>
							<th>Iss. To</th>
							<th>ISSN</th>
							<th>Year</th>
							<th>Publisher</th>
							<th>Call No.</th>
							<th>Binding No.</th>
							<th>Pages</th>
							<th>BinderName</th>
						</tr>
					</thead>
				</table>
				<table id="reportTable2" class="table table-bordered table-striped">
					<thead>
						<tr>
							<th>Control No</th>
							<th>Title</th>
							<th>Accession No</th>
							<th>Author</th>
							<th>ISBN</th>
							<th>Copy No</th>
							<th>Year</th>
							<th>Publisher</th>
							<th>Call No.</th>
							<th>Binding No.</th>
							<th>BinderName</th>
						</tr>
					</thead>
				</table>
			</div>
		</div>
	</div>
	<!-- END MAIN CONTENT -->
</body>
</html>