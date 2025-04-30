<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/Binding/Report/AABR0250.js"></script>
</head>
<body>
	<!-- START MAIN CONTENT -->
	<div class="box box-default">
		<div class="panel panel-default">
			<div class="panel-heading"></div>
			<div class="panel-body">
				<form class="form-horizontal">
					<div class="form-group">
						<div class="col-sm-2 col-md-2">
							<label>Type of Date </label>
						</div>
						<div class="col-sm-4">
							<input type="radio" id="sent-date" name="date-type" value="sent">
							<label for="sent-date">Date Sent</label> <input type="radio"
								id="return-date" name="date-type" value="return"> <label
								for="return-date">Date Return</label>
						</div>
					</div>
					<div class="form-group">
						<div class="col-sm-2 col-md-2">
							<input type="radio" id="yearly" name="selectOption"
								value="yearly"> <label for="yearly">Yearly </label>
						</div>
						<div class="col-sm-4">
							<input type="text" id="yearly-text" name="yearly-text">
						</div>
					</div>

					<div class="form-group">
						<div class="col-sm-2 col-md-2">
							<input type="radio" id="monthly" name="selectOption"
								value="monthly"> <label for="monthly"> Monthly </label>
						</div>
						<div class="col-sm-4">
							<select name="month" id="monthly-text">
								<option value="00">JANUARY</option>
								<option value="01">FEBRUARY</option>
								<option value="02">MARCH</option>
								<option value="03">APRIL</option>
								<option value="04">MAY</option>
								<option value="05">JUNE</option>
								<option value="06">JULY</option>
								<option value="07">AUGUST</option>
								<option value="08">SEPTEMBER</option>
								<option value="09">OCTOBER</option>
								<option value="10">NOVEMBER</option>
								<option value="11">DECEMBER</option>
							</select>
						</div>
					</div>

					<div class="form-group">
						<div class="col-sm-2 col-md-2">
							<input type="radio" id="dateRange" name="selectOption"
								value="dateRange"> <label for="dateRange">Date
								Range </label>
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

					<div class="form-group">
						<div class="col-sm-2 col-md-2">
							<label for="binding-status">Binding Status </label>
						</div>
						<div class="col-sm-4">
							<select name="binding-status" multiple="multiple"
								id="binding-status">
								<option value="P">Pending</option>
								<option value="S">Sent</option>
								<option value="R">Returned</option>
								<option value="C">Cancelled</option>
							</select>
						</div>
					</div>

					<div class="form-group">
						<div class="col-sm-2 col-md-2">
							<label> <input type="checkbox" id="officerId"
								name="chkBoxSearchCateria" value="officerId"> Officer Id
							</label>
						</div>

						<div class="col-sm-3">
							<div class="input-group">
								<input type="text" class="form-control" id="lblPatronID"
									name="lblPatronID"> <a
									class="input-group-addon btn btn-primary inputOfficerId"
									data-toggle="modal" data-target="#modal_officerIdSearch"> <span
									class="glyphicon glyphicon-th-list"></span></a>
							</div>
						</div>
					</div>

					<!-- MODAL WHEN CLICK Search button-officerIdSearch-->
					<div class="modal fade" id="modal_officerIdSearch" tabindex="-1"
						role="dialog" aria-labelledby="modal_officerIdSearch"
						data-keyboard="false" data-backdrop="static">
						<div class="modal-dialog" style="width: 55%; height: 100vh;">
							<div class="modal-content" id="modal_officerIdSearchContent">
								<!-- Remote content load here  -->
							</div>
						</div>
					</div>
					<!-- Modal END modal search -->

					<div class="form-group">
						<div class="col-sm-2 col-md-2">
							<label>Binding Item </label>
						</div>
						<div class="col-sm-4">
							<input type="radio" id="serials" name="bindingItem" value="S">
							<label for="serials">Serials</label> <input type="radio"
								id="monograph" name="bindingItem" value="M"> <label
								for="monograph">Monograph</label>
						</div>
					</div>
					<div class="btn-group pull-right">
						<div class="col-md-1">
							<button type="button" id="Reterive" class="btn btn-primary"
								title="Retrieve">Retrieve</button>
						</div>
					</div>
				</form>
				<br> <br>
				<div class="col-md-6 col-lg-3 pull-right divtotal divTotalSum"
					style="text-align: right;"></div>
				<br> <br>
				<table id="reportTable" class="table table-bordered table-striped">
					<thead>
						<tr>
							<th>No</th>
							<th>Title</th>
							<th>Call No</th>
							<th>Control No</th>
							<th>Accession No</th>
							<th>Volume</th>
							<th>Binding Type</th>
							<th>Officer Id</th>
							<th>Date Sent</th>
							<th>Date Expected</th>
							<th>Date Return</th>
							<th>Status</th>
							<th>Cost</th>
							<th>Binder</th>
						</tr>
					</thead>
				</table>
			</div>
		</div>
	</div>
	<!-- END MAIN CONTENT -->
</body>
</html>