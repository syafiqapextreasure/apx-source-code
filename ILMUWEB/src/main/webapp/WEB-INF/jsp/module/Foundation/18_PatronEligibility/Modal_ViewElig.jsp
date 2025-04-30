<%@ page import="com.ilmu.foundation.global.GlobalSQLStatement"%>
<%@ page import="com.ilmu.foundation.PatronEligibility.SQLStatement"%>
<%@ page import="com.ilmu.foundation.global.Foundation"%>
<%@ page import="java.text.DecimalFormat"%>
<%@ page import="java.util.List"%>

<%
	GlobalSQLStatement eb = new GlobalSQLStatement();
	Foundation e = eb.getPatronElig(request.getParameter("GL27CATE"), request.getParameter("GL27ICAT"),
			request.getParameter("GL27SMD"), request.getParameter("GL27BRNC"));
%>

<!-- Modal content-->
<div class="modal-header">
	<button type="button" class="close" data-dismiss="modal">&times;</button>
	<h4 class="modal-title center">View Patron Eligibility</h4>
</div>
<div class="modal-body">
	<form role="form" class="form-horizontal" id="viewAccMaint">
		<!-- Custom Tabs -->
		<div class="nav-tabs-custom">
			<ul class="nav nav-tabs">
				<li class="active"><a href="#tab_1" data-toggle="tab"
					aria-expanded="false"><strong>Patron Eligibility</strong></a></li>
				<li><a href="#tab_2" data-toggle="tab"><strong>Fines
							Schedule</strong></a></li>
				<li><a href="#tab_3" data-toggle="tab" aria-expanded="true"><strong>Lost
							Fee Setting</strong></a></li>
				<li><a href="#tab_4" data-toggle="tab"><strong>Fines
							Grace Period</strong></a></li>
			</ul>

			<!-- TAB CONTENT -->
			<div class="tab-content">

				<!-- TAB ONE -->
				<div class="tab-pane active" id="tab_1">
					<div class="box-body">
						<div class="row">
							<!--Patron Category -->
							<div class="form-group">
								<label for="category" class="col-sm-2"><strong>Patron
										Category</strong></label>
								<div class="col-sm-3">
									<select class="form-control" id="category"
										onchange="document.getElementById('categoryDesc').selectedIndex = document.getElementById('category').selectedIndex"
										disabled>
										<option value="<%=e.getGL27CATE()%>"><%=e.getGL27CATE()%></option>
										<%
											GlobalSQLStatement cate = new GlobalSQLStatement();
											List<Foundation> catelist = cate.getCate();
											for (Foundation d : catelist) {
										%>
										<option value="<%=d.getGL07CATE()%>"><%=d.getGL07CATE()%></option>
										<%
											}
										%>
									</select>
								</div>

								<div class="col-sm-6">
									<select class="form-control" id="categoryDesc"
										onchange="document.getElementById('category').selectedIndex = document.getElementById('categoryDesc').selectedIndex"
										disabled>
										<option value="<%=e.getGL07DESC()%>"><%=e.getGL07DESC()%></option>
										<%
											GlobalSQLStatement cateName = new GlobalSQLStatement();
											List<Foundation> cateNamelist = cateName.getCate();
											for (Foundation f : cateNamelist) {
										%>
										<option value="<%=f.getGL07CATE()%>"><%=f.getGL07DESC()%></option>
										<%
											}
										%>
									</select>
								</div>
							</div>
							<!-- End Patron Category -->

							<!--Item Category -->
							<div class="form-group">
								<label class="col-sm-2" for="itemCategory"><strong>Item
										Category</strong></label>
								<div class="col-sm-3">
									<select class="form-control" id="itemCategory"
										onchange="document.getElementById('itemCategoryDesc').selectedIndex = document.getElementById('itemCategory').selectedIndex"
										disabled>

										<option value="<%=e.getGL27ICAT()%>"><%=e.getGL27ICAT()%></option>
										<%
											GlobalSQLStatement icate = new GlobalSQLStatement();
											List<Foundation> icatelist = cate.getICat();
											for (Foundation d : icatelist) {
										%>
										<option value="<%=d.getGL10ICAT()%>"><%=d.getGL10ICAT()%></option>
										<%
											}
										%>
									</select>
								</div>

								<div class="col-sm-6">
									<select class="form-control" id="itemCategoryDesc"
										onchange="document.getElementById('itemCategory').selectedIndex = document.getElementById('itemCategoryDesc').selectedIndex"
										disabled>
										<option value="<%=e.getGL10DESC()%>"><%=e.getGL10DESC()%></option>
										<%
											GlobalSQLStatement icateName = new GlobalSQLStatement();
											List<Foundation> icateNamelist = cateName.getICat();
											for (Foundation f : icateNamelist) {
										%>
										<option value="<%=f.getGL10ICAT()%>"><%=f.getGL10DESC()%></option>
										<%
											}
										%>
									</select>
								</div>
							</div>
							<!-- End Item Category -->

							<!--SMD -->
							<div class="form-group">
								<label class="col-sm-2" for="smdCode"><strong>SMD</strong></label>
								<div class="col-sm-3">
									<select class="form-control" id="smdCode"
										onchange="document.getElementById('smdCodeDesc').selectedIndex = document.getElementById('GL27SMD').selectedIndex"
										disabled>

										<option value="<%=e.getGL27SMD()%>"><%=e.getGL27SMD()%></option>
										<%
											GlobalSQLStatement smd = new GlobalSQLStatement();
											List<Foundation> smdcode = cate.getSMD();
											for (Foundation d : smdcode) {
										%>
										<option value="<%=d.getGL47SMD()%>"><%=d.getGL47SMD()%></option>
										<%
											}
										%>
									</select>
								</div>

								<div class="col-sm-6">
									<select class="form-control" id="smdCodeDesc"
										onchange="document.getElementById('smdCode').selectedIndex = document.getElementById('smdCodeDesc').selectedIndex"
										disabled>
										<option value="<%=e.getGL47DESC()%>"><%=e.getGL47DESC()%></option>
										<%
											GlobalSQLStatement smddesc = new GlobalSQLStatement();
											List<Foundation> smdlist = cateName.getSMD();
											for (Foundation f : smdlist) {
										%>
										<option value="<%=f.getGL47SMD()%>"><%=f.getGL47DESC()%></option>
										<%
											}
										%>
									</select>
								</div>
							</div>
							<!-- End SMD -->

							<!--Branch -->
							<div class="form-group">
								<label class="col-sm-2" for="branch"><strong>Branch</strong></label>
								<div class="col-sm-3">
									<select class="form-control" id="branch"
										onchange="document.getElementById('branchDesc').selectedIndex = document.getElementById('branch').selectedIndex"
										disabled>
										<option value="<%=e.getGL27BRNC()%>"><%=e.getGL27BRNC()%></option>
										<%
											GlobalSQLStatement brnc = new GlobalSQLStatement();
											List<Foundation> brnccode = brnc.getBranch();
											for (Foundation d : brnccode) {
										%>
										<option value="<%=d.getGL71BRNC()%>"><%=d.getGL71BRNC()%></option>
										<%
											}
										%>
									</select>
								</div>

								<div class="col-sm-6">
									<select class="form-control" id="branchDesc"
										onchange="document.getElementById('branch').selectedIndex = document.getElementById('branchDesc').selectedIndex"
										disabled>
										<option value="<%=e.getGL71BRNC()%>"><%=e.getGL71BRNC()%></option>
										<%
											GlobalSQLStatement brncdesc = new GlobalSQLStatement();
											List<Foundation> brnclist = brncdesc.getBranch();
											for (Foundation f : brnclist) {
										%>
										<option value="<%=f.getGL71BRNC()%>"><%=f.getGL71DESC()%></option>
										<%
											}
										%>
									</select>
								</div>
							</div>
							<!-- End Branch -->
							<div class="row">
								<div class="col-sm-6">
									<div class="form-group">
										<label class="col-sm-4" for="loanPeriod">Period of
											Loan</label>
										<div class="col-sm-3">
											<input type="number" class="form-control" id="loanPeriod"
												title="Period of loan must greater than one."
												value="<%=e.getGL27PLOAN() != null ? Integer.parseInt(e.getGL27PLOAN()) : 0%>"
												disabled>
										</div>
										<div class="col-sm-5">
											<%
												if (e.getGL27PTYPE().equals("D")) {
											%>
											<label class="radio-inline"><input type="radio"
												name="loanTypeOptions" id="loanTypeD" value="D" checked
												disabled>days </label> <label class="radio-inline">
												<input type="radio" name="loanTypeOptions" id="loanTypeH"
												value="H" disabled>hours
											</label>
											<%
												} else {
											%><label class="radio-inline"><input type="radio"
												name="loanTypeOptions" id="loanTypeD" value="D" disabled>days
											</label> <label class="radio-inline"> <input type="radio"
												name="loanTypeOptions" id="loanTypeH" value="H" checked
												disabled>hours
											</label>
											<%
												}
											%>
										</div>
									</div>
									<div class="form-group">
										<label class="col-sm-4" for="numElig">No. of
											Eligibility</label>
										<div class="col-sm-4">
											<input type="number" class="form-control" id="numElig"
												title="Number of eligibility must greater than one."
												value="<%=e.getGL27ELIG() != null ? Integer.parseInt(e.getGL27ELIG()) : 0%>"
												disabled>
										</div>
									</div>
									<div class="form-group">
										<label class="col-sm-4" for="numRenewal">No. of
											Renewal</label>
										<div class="col-sm-4">
											<input type="number" class="form-control" id="numRenewal"
												value="<%=e.getGL27RENEW() != null ? Integer.parseInt(e.getGL27RENEW()) : 0%>"
												disabled>
										</div>
									</div>
									<div class="form-group">
										<label class="col-sm-4" for="doOverdue">Allow Overdue</label>
										<div class="col-sm-4">
											<%
												if (e.getGL27ALLOWOVD().equals("Y")) {
											%>
											<label class="radio-inline col-sm-3 "><input
												type="radio" name="allowOverdueOptions" id="allowOverdueY"
												value="Y" checked disabled> Yes </label> <label
												class="radio-inline col-sm-3 "> <input type="radio"
												name="allowOverdueOptions" id="allowOverdueN" value="N"
												disabled> No
											</label>
											<%
												} else {
											%>
											<label class="radio-inline col-sm-3"><input
												type="radio" name="allowOverdueOptions" id="allowOverdueY"
												value="Y" disabled> Yes </label> <label
												class="radio-inline col-sm-3 "> <input type="radio"
												name="allowOverdueOptions" id="allowOverdueN" value="N"
												checked disabled> No
											</label>
											<%
												}
											%>
										</div>
									</div>
								</div>
								<div class="col-sm-6">
									<div class="form-group">
										<label class="col-sm-6" for="overdueGP1">Overdue Grace
											Period 1</label>
										<div class="col-sm-4">
											<input type="number" class="form-control" id="overdueGP1"
												value="<%=e.getGL27GRACE1() != null ? Integer.parseInt(e.getGL27GRACE1()) : 0%>"
												disabled>
										</div>
										<label class="col-sm-1">day(s)</label>
									</div>
									<div class="form-group">
										<label class=" col-sm-6" for="overdueGP2">Overdue
											Grace Period 2</label>
										<div class="col-sm-4">
											<input type="number" class="form-control" id="overdueGP2"
												value="<%=e.getGL27GRACE2() != null ? Integer.parseInt(e.getGL27GRACE2()) : 0%>"
												disabled>
										</div>
										<label class="col-sm-1">day(s)</label>
									</div>
									<div class="form-group">
										<label class="col-sm-6" for="overdueGP3">Overdue Grace
											Period 3</label>
										<div class="col-sm-4">
											<input type="number" class="form-control" id="overdueGP3"
												value="<%=e.getGL27GRACE3() != null ? Integer.parseInt(e.getGL27GRACE3()) : 0%>"
												disabled>
										</div>
										<label class="col-sm-1">day(s)</label>

									</div>
									<div class="form-group">
										<label class="col-sm-6">Allow Reserve</label>
										<div class="col-sm-4">
											<%
												if (e.getGL27ALLOWRSV().equals("Y")) {
											%>
											<label class="radio-inline col-sm-3"><input
												type="radio" name="allowReserveOptions" id="allowReserveY"
												value="Y" checked disabled> Yes </label> <label
												class="radio-inline col-sm-3"> <input type="radio"
												name="allowReserveOptions" id="allowReserveN" value="N"
												disabled> No
											</label>
											<%
												} else {
											%>
											<label class="radio-inline col-sm-3"><input
												type="radio" name="allowReserveOptions" id="allowReserveY"
												value="Y" disabled> Yes </label> <label
												class="radio-inline col-sm-3"> <input type="radio"
												name="allowReserveOptions" id="allowReserveN" value="N"
												checked disabled> No
											</label>
											<%
												}
											%>
										</div>
									</div>
								</div>
							</div>

							<!-- /.row -->
						</div>
						<!-- /.box body -->
					</div>
				</div>
				<!-- /.End Tab 1 -->


				<!-- TAB TWO -->
				<div class="tab-pane" id="tab_2">
					<div class="box-body">
						<div class="row">
							<div class="col-md-12">
								<table id="finesTable"
									class="table table-bordered table-striped">
									<thead>
										<tr>
											<th>From</th>
											<th>To</th>
											<th>Rate</th>
											<th>Fines</th>
										</tr>
									</thead>
									<tbody>
										<%
											SQLStatement ef = new SQLStatement();
											List<Foundation> list = ef.getFines(request.getParameter("GL27CATE"), request.getParameter("GL27ICAT"),
													request.getParameter("GL27SMD"), request.getParameter("GL27BRNC"));

											if (list.size() != 0) {
												for (Foundation i : list) {
										%>

										<tr>
											<td><%=i.getGL38START()%></td>
											<td><%=i.getGL38STOP() != null ? i.getGL38STOP() : ""%></td>
											<td><%=i.getGL38RATE() != null
							? new DecimalFormat("0.00").format(Float.parseFloat(i.getGL38RATE()))
							: ""%></td>
											<td><%=i.getGL38FIRST() != null
							? new DecimalFormat("0.00").format(Float.parseFloat(i.getGL38FIRST()))
							: ""%></td>

										</tr>
										<%
											}
											} else {
										%><tr>
											<td>&nbsp;</td>
											<td>&nbsp;</td>
											<td>&nbsp;</td>
											<td>&nbsp;</td>
										</tr>
										<%
											}
										%>
									</tbody>

								</table>
								<div class="form-group">
									<label for="maxFines" class="col-sm-4">Maximum fines
										for late return</label>
									<div class="col-sm-4 col-md-4">
										<input type="number" class="form-control" id="maxFines"
											value="<%=new DecimalFormat("0.00")
					.format(Float.parseFloat(e.getGL27MAXFINE() != null ? e.getGL27MAXFINE() : "0"))%>"
											disabled />
									</div>
								</div>
							</div>
							<!-- col -->
						</div>
						<!-- row -->
					</div>
					<!-- box body -->
				</div>
				<!-- /.End Tab 2 -->


				<!-- TAB THREE -->
				<div class="tab-pane" id="tab_3">
					<div class="box-body">
						<div class="row">
							<div class="col-md-12">
								<!--Lost Book -->
								<div class="form-group">
									<label class="col-sm-4">Include Fines for Lost Book</label>
									<div class="form-group">
										<%
											if (e.getGL27INCFINE().equals("Y")) {
										%>
										<label class="radio-inline col-sm-1"><input
											type="radio" name="includeFinesOptions" id="includeY"
											value="Y" checked disabled>Yes </label> <label
											class="radio-inline col-sm-1"> <input type="radio"
											name="includeFinesOptions" id="includeN" value="N" disabled>No
										</label>
										<%
											} else {
										%><label class="radio-inline col-sm-1"><input
											type="radio" name="includeFinesOptions" id="includeY"
											value="Y" disabled>Yes </label> <label
											class="radio-inline col-sm-1"> <input type="radio"
											name="includeFinesOptions" id="includeN" value="N" checked
											disabled>No
										</label>
										<%
											}
										%>
									</div>
								</div>
								<!-- End Lost Book -->

								<!--Fee for lost item -->
								<div class="form-group">
									<label class="col-sm-4" for="lostFee">Processing Fee
										for Lost Item</label>
									<div class="col-sm-2 col-md-2">
										<input type="number" class="form-control" id="lostFee"
											value="<%=new DecimalFormat("0.00")
					.format(e.getGL27PRCFEES() != null ? Float.parseFloat(e.getGL27PRCFEES()) : 0)%>"
											disabled>
									</div>
								</div>
								<!-- End Fee For Lost book -->

								<!--SMD -->
								<div class="form-group">
									<label class="col-sm-4" for="timesCost">Number to be
										multiplied by the cost of lost item</label>
									<div class="col-sm-2 col-md-2">
										<input id="timesCost" type="number" class="form-control"
											value="<%=e.getGL27TIMESCOST()%>" disabled>
									</div>
								</div>
								<!-- End SMD -->

							</div>
							<!-- End Col -->
						</div>
						<!-- End Row -->
					</div>
					<!-- /.BOX BODY -->
				</div>
				<!-- /.TAB PANE -->
				<!-- /.End Tab 3 -->

				<!-- TAB FOUR -->
				<div class="tab-pane" id="tab_4">
					<div class="box-body">
						<div class="col-md-12">
							<div class="row">
								<div class="form-group">
									<label for="finesGP1" class="col-sm-2">Grace Period 1</label>
									<div class="col-sm-2">
										<input id="finesGP1" type="number" class="form-control"
											value="<%=e.getGL27FGRACE1() != null ? Integer.parseInt(e.getGL27FGRACE1()) : 0%>"
											disabled>
									</div>
									<span>day(s)</span>
								</div>

								<div class="form-group">
									<label for="finesGP2" class="col-sm-2">Grace Period 2</label>
									<div class="col-sm-2">
										<input id="finesGP2" type="number" class="form-control"
											value="<%=e.getGL27FGRACE2() != null ? Integer.parseInt(e.getGL27FGRACE2()) : 0%>"
											disabled>
									</div>
									<span>day(s)</span>
								</div>

								<div class="form-group">
									<label for="finesGP3" class="col-sm-2">Grace Period 3</label>
									<div class="col-sm-2">
										<input id="finesGP3" type="number" class="form-control"
											value="<%=e.getGL27FGRACE3() != null ? Integer.parseInt(e.getGL27FGRACE3()) : 0%>"
											disabled>
									</div>
									<span>day(s)</span>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<ul class="list-inline pull-right">
			<li><button type="button" data-dismiss="modal"
					class="btn btn-default">Close</button></li>
		</ul>
	</form>
</div>

<script type="text/javascript">
<!-- http://stackoverflow.com/questions/24639335/javascript-console-log-causes-error-synchronous-xmlhttprequest-on-the-main-thr -->
	$.getScript("<%=request.getContextPath()%>/resources/js/search-elig.js");
</script>
