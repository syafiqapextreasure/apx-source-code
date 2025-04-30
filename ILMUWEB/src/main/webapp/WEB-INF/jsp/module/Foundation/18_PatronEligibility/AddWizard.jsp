<%@ page import="com.ilmu.foundation.global.GlobalSQLStatement"%>
<%@ page
	import="com.ilmu.foundation.PatronEligibility.PatronEligibility"%>
<%@ page import="com.ilmu.foundation.global.Foundation"%>
<%@ page import="java.util.List"%>

<!-- Modal content-->
<div class="modal-header">
	<button type="button" class="close" data-dismiss="modal">&times;</button>
	<h4 class="modal-title center">Patron Eligibility Wizard</h4>
	<div class="wizard">
		<div class="wizard-inner">
			<div class="connecting-line"></div>
			<ul class="nav nav-tabs" role="tablist">
				<li role="presentation" class="active"><a href="#step1"
					data-toggle="tab" aria-controls="step1" role="tab" title="Step 1">
						<span class="round-tab"><strong>Step 1</strong> </span>
				</a></li>
				<li role="presentation" class="disabled"><a href="#step2"
					data-toggle="tab" aria-controls="step2" role="tab" title="Step 2">
						<span class="round-tab"><strong>Step 2</strong> </span>
				</a></li>
				<li role="presentation" class="disabled"><a href="#step3"
					data-toggle="tab" aria-controls="step3" role="tab" title="Step 3">
						<span class="round-tab"><strong>Step 3</strong> </span>
				</a></li>
			</ul>
		</div>
	</div>

	<div class="modal-body">
		<form role="form" class="form-horizontal" method="post"
			action="template?MODULE=Foundation/18_PatronEligibility&ACTION=MainPage.jsp&result=<%=request.getParameter("result")%>">
			<div class="tab-content">
				<div class="tab-pane active" role="tabpanel" id="step1">
					<div class="panel-group" id="accordion">
						<div class="panel panel-default">
							<div class="panel-heading">
								<h4 class="panel-title">
									<a data-toggle="collapse" data-parent="#accordion"
										href="#collapse1"> Select Patron Category...</a>
								</h4>
								<input type="checkbox" id="selectAll1"> Select All
							</div>
							<div id="collapse1" class="panel-collapse collapse in">
								<div class="panel-body">
									<table class="table">
										<tbody>
											<%
												GlobalSQLStatement eb = new GlobalSQLStatement();
												List<Foundation> catlist = eb.getCate();

												for (Foundation e : catlist) {
											%>
											<tr>
												<td width="5%"><label><input type="checkbox"
														class="tickCate"
														value="<%=e.getGL07CATE()%>, <%=e.getGL07DESC()%>"></label></td>
												<td><%=e.getGL07CATE()%></td>
												<td><%=e.getGL07DESC()%></td>
											</tr>
											<%
												}
											%>
										</tbody>
									</table>
								</div>
							</div>
						</div>
						<div class="panel panel-default">
							<div class="panel-heading">
								<h4 class="panel-title">
									<a data-toggle="collapse" data-parent="#accordion"
										href="#collapse2"> Select Item Category...</a>
								</h4>
								<input type="checkbox" id="selectAll2"> Select All
							</div>
							<div id="collapse2" class="panel-collapse collapse">
								<div class="panel-body">
									<table class="table">
										<tbody>
											<%
												GlobalSQLStatement ec = new GlobalSQLStatement();
												List<Foundation> icatlist = eb.getICat();

												for (Foundation e : icatlist) {
											%>
											<tr>
												<td width="5%"><input type="checkbox" class="tickItem"
													value="<%=e.getGL10ICAT()%>, <%=e.getGL10DESC()%>"></td>
												<td><%=e.getGL10ICAT()%></td>
												<td><%=e.getGL10DESC()%></td>
											</tr>
											<%
												}
											%>
										</tbody>
									</table>
								</div>
							</div>
						</div>
						<div class="panel panel-default">
							<div class="panel-heading">
								<h4 class="panel-title">
									<a data-toggle="collapse" data-parent="#accordion"
										href="#collapse3"> Select SMD Code...</a>
								</h4>
								<input type="checkbox" id="selectAll3"> Select All
							</div>
							<div id="collapse3" class="panel-collapse collapse">
								<div class="panel-body">
									<table class="table">
										<tbody>
											<%
												GlobalSQLStatement es = new GlobalSQLStatement();
												List<Foundation> smdlist = eb.getSMD();

												for (Foundation e : smdlist) {
											%>
											<tr>
												<td width="5%"><input type="checkbox" class="tickSmd"
													value="<%=e.getGL47SMD()%>, <%=e.getGL47DESC()%>"></td>
												<td><%=e.getGL47SMD()%></td>
												<td><%=e.getGL47DESC()%></td>
											</tr>
											<%
												}
											%>
										</tbody>
									</table>
								</div>
							</div>
						</div>
						<div class="panel panel-default">
							<div class="panel-heading">
								<h4 class="panel-title">
									<a data-toggle="collapse" data-parent="#accordion"
										href="#collapse4"> Select Branch...</a>
								</h4>
								<input type="checkbox" id="selectAll4"> Select All
							</div>
							<div id="collapse4" class="panel-collapse collapse">
								<div class="panel-body">
									<table class="table">
										<tbody>
											<%
												GlobalSQLStatement ebr = new GlobalSQLStatement();
												List<Foundation> brnclist = eb.getBranch();

												for (Foundation e : brnclist) {
											%>
											<tr>
												<td width="5%"><input type="checkbox"
													class="tickBranch"
													value="<%=e.getGL71BRNC()%>, <%=e.getGL71DESC()%>"></td>
												<td><%=e.getGL71BRNC()%></td>
												<td><%=e.getGL71DESC()%></td>
											</tr>
											<%
												}
											%>
										</tbody>
									</table>
								</div>
							</div>
						</div>
					</div>
					<ul class="list-inline pull-right">
						<li><button type="button" class="btn btn-primary next-step1">Next</button></li>
					</ul>
				</div>
				<div class="tab-pane" role="tabpanel" id="step2">
					<table id="indicatorTable" class="table table-striped header-fixed">
						<thead>
							<tr>
								<th class="col10">No</th>
								<th class="col20">Patron Category</th>
								<th class="col20">Item Category</th>
								<th class="col20">SMD Code</th>
								<th class="col20">Branch</th>
								<th class="col10">Ind</th>
							</tr>
						</thead>
					</table>
					<p class="red-inline">Indicator: * means eligibility exist in
						the database</p>
					<div class="panel panel-default">
						<div class="panel-heading">General Information</div>
						<div class="panel-body">
							<div class="col-sm-6">
								<div class="form-group">
									<label class="control-label col-sm-4" for="loanPeriod">Period
										of Loan</label>
									<div class="col-sm-3">
										<input type="number" class="form-control" id="loanPeriod"
											title="Period of loan must greater than one." min="1"
											step="1">
									</div>
									<div class="col-sm-5">
										<label class="radio-inline"><input type="radio"
											name="loanTypeOptions" id="loanTypeD" value="D" checked>
											days</label> <label class="radio-inline"> <input type="radio"
											name="loanTypeOptions" id="loanTypeH" value="H">
											hours
										</label>
									</div>
								</div>
								<div class="form-group">
									<label class="control-label col-sm-4" for="numElig">No.
										of Eligibility</label>
									<div class="col-sm-4">
										<input type="number" class="form-control" id="numElig"
											title="Number of eligibility must greater than one." min="1"
											step="1" max="999">
									</div>
								</div>
								<div class="form-group">
									<label class="control-label col-sm-4" for="numRenewal">No.
										of Renewal</label>
									<div class="col-sm-4">
										<input type="number" class="form-control" id="numRenewal"
											min="0" step="1">
									</div>
								</div>
								<div class="form-group">
									<label class="control-label col-sm-4" for="doOverdue">Allow
										Overdue</label>
									<div class="col-sm-4">
										<label class="radio-inline"><input type="radio"
											name="doOverdueOptions" id="doOverdueY" value="Y">
											Yes </label> <label class="radio-inline"> <input type="radio"
											name="doOverdueOptions" id="doOverdueN" value="N" checked>
											No
										</label>
									</div>
								</div>
							</div>
							<div class="col-sm-6">
								<div class="form-group">
									<label class="control-label col-sm-6" for="overdueGP1">Overdue
										Grace Period 1</label>
									<div class="col-sm-4">
										<input type="number" class="form-control" id="overdueGP1"
											min="0" step="1">
									</div>
									<label class="control-label col-sm-1">day(s)</label>
								</div>
								<div class="form-group">
									<label class="control-label col-sm-6" for="overdueGP2">Overdue
										Grace Period 2</label>
									<div class="col-sm-4">
										<input type="number" class="form-control" id="overdueGP2"
											min="0" step="1">
									</div>
									<label class="control-label col-sm-1">day(s)</label>
								</div>
								<div class="form-group">
									<label class="control-label col-sm-6" for="overdueGP3">Overdue
										Grace Period 3</label>
									<div class="col-sm-4">
										<input type="number" class="form-control" id="overdueGP3"
											min="0" step="1">
									</div>
									<label class="control-label col-sm-1">day(s)</label>
								</div>
								<div class="form-group">
									<label class="control-label col-sm-6">Allow Reserve</label>
									<div class="col-sm-4">
										<label class="radio-inline"><input type="radio"
											name="doReserveOptions" id="doReserveY" value="Y" checked>
											Yes </label> <label class="radio-inline"> <input type="radio"
											name="doReserveOptions" id="doReserveN" value="N"> No
										</label>
									</div>
								</div>
							</div>
						</div>
					</div>
					<ul class="list-inline pull-right">
						<li><button type="button" class="btn btn-default prev-step">Previous</button></li>
						<li><button type="button" class="btn btn-primary next-step2">Next</button></li>
					</ul>
				</div>
				<div class="tab-pane" role="tabpanel" id="step3">
					<table id="finalTable" class="table table-striped header-fixed">
						<thead>
							<tr>
								<th class="col10 col-font">No<br>&nbsp;
								</th>
								<th class="col10 col-font">Patron <br>Category
								</th>
								<th class="col10 col-font">Item <br>Category
								</th>
								<th class="col10 col-font">SMD <br>Code
								</th>
								<th class="col10 col-font">Branch <br>&nbsp;
								</th>
								<th class="col10 col-font">Period <br>of Loan
								</th>
								<th class="col10 col-font">No. of<br>Eligibility
								</th>
								<th class="col10 col-font">No. of<br>Renewal
								</th>
								<th class="col10 col-font">Allow <br>Overdue
								</th>
								<th class="col10 col-font">Allow <br>Reserve
								</th>
							</tr>
						</thead>
					</table>
					<br>
					<div class="panel panel-default">
						<div class="panel-heading">Current Eligibility</div>
						<div class="panel-body">
							<div class="col-sm-6">
								<div class="form-group">
									<label for="showCate" class="control-label col-sm-4">Patron
										Category</label>
									<div class="col-sm-6">
										<input type="text" class="form-control" id="showCate" readonly>
									</div>
								</div>
								<div class="form-group">
									<label for="showItem" class="control-label col-sm-4">SMD
										Code</label>
									<div class="col-sm-6">
										<input type="text" class="form-control" id="showItem" readonly>
									</div>
								</div>
							</div>
							<div class="col-sm-6">
								<div class="form-group">
									<label for="showSmd" class="control-label col-sm-4">Item
										Category</label>
									<div class="col-sm-6">
										<input type="text" class="form-control" id="showSmd" readonly>
									</div>
								</div>
								<div class="form-group">
									<label for="showBranch" class="control-label col-sm-4">Branch</label>
									<div class="col-sm-6">
										<input type="text" class="form-control" id="showBranch"
											readonly>
									</div>
								</div>
							</div>
							<br> <br>
							<div id="infoTab">
								<ul class="tabs nav nav-pills">
									<li><a id="scheduleTab" href="#tab1">Fines Schedule</a></li>
									<li><a id="lostfeeTab" href="#tab2">Lost Fee Setting</a></li>
									<li><a id="fgPeriodTab" href="#tab3">Fines Grace
											Period</a></li>
								</ul>

								<div class="tab-content clearfix">
									<div class="tab-pane active" id="tab1">
										<div class="box-body">
											<div class="panel-body">
												<table id="finesTable"
													class="table table-striped table-bordered table-editable">
													<thead>
														<tr>
															<th>No</th>
															<th>Late From</th>
															<th>Late To</th>
															<th>Rate</th>
															<th class="hidden"></th>
															<th>Fines</th>
														</tr>
													</thead>
													<tbody>
														<tr class="rowId">
															<td class='rowNo'>1</td>
															<td contenteditable="true" id="GL38START1"></td>
															<td contenteditable="true" id="GL38STOP1"></td>
															<td contenteditable="true" id="GL38RATE1"></td>
															<td class="hidden" id="calcFine1"></td>
															<td id="totalFines1"></td>
														</tr>
													</tbody>
												</table>
												<div class="form-inline">
													<div class="pull-left">
														<label class="control-label col-sm-6" for="maxFines">Maximum
															fines for late return</label>
														<div class="col-sm-3">
															<input type="number" class="form-control" value="0.00"
																step="0.01" min="0.00" id="maxFines" />
														</div>
													</div>
													<div class="pull-right">
														<div class="col-sm-2">
															<button type="button"
																class="btn btn-primary btn-info-full clear">Clear
																Schedule</button>
														</div>
													</div>
												</div>
											</div>
										</div>
									</div>
									<div class="tab-pane" id="tab2">
										<div class="box-body">
											<div class="panel-body">
												<div class="form-group">
													<label class="control-label col-sm-6">Include fines
														for lost item?</label>
													<div class="col-sm-4">
														<label class="radio-inline"><input type="radio"
															name="inFinesOptions" id="inFinesY" value="Y" checked>
															Yes</label> <label class="radio-inline"> <input
															type="radio" name="inFinesOptions" id="inFinesN"
															value="N"> No
														</label>
													</div>
												</div>
												<div class="form-group">
													<label class="control-label col-sm-6" for="lostFee">Processing
														Fee for lost item</label>
													<div class="col-sm-4">
														<input type="number" value="0.00" class="form-control" id="lostFee"
															placeholder="0.00" min="0.00" step="0.01">
													</div>
												</div>
												<div class="form-group">
													<label class="control-label col-sm-6" for="timesCost">Number
														to be multiplied by the cost of the lost item</label>
													<div class="col-sm-4">
														<input type="number" class="form-control" id="timesCost"
															min="0" step="1" value="1">
													</div>
												</div>
											</div>
										</div>
									</div>
									<br>
									<div class="tab-pane" id="tab3">
										<div class="box-body">
											<div class="panel-body">
												<div class="form-group">
													<label class="control-label col-sm-3" for="finesGP1">Grace
														Period 1</label>
													<div class="col-sm-3">
														<input type="number" class="form-control" id="finesGP1"
															min="0" step="1">
													</div>
													<label class="control-label col-sm-1">day(s)</label>
												</div>
												<div class="form-group">
													<label class="control-label col-sm-3" for="finesGP2">Grace
														Period 2</label>
													<div class="col-sm-3">
														<input type="number" class="form-control" id="finesGP2"
															min="0" step="1">
													</div>
													<label class="control-label col-sm-1">day(s)</label>
												</div>
												<div class="form-group">
													<label class="control-label col-sm-3" for="finesGP3">Grace
														Period 3</label>
													<div class="col-sm-3">
														<input type="number" class="form-control" id="finesGP3"
															min="0" step="1">
													</div>
													<label class="control-label col-sm-1">day(s)</label>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>

					<ul class="list-inline pull-right">
						<li><button type="button" class="btn btn-default prev-step">Previous</button></li>
						<li><button type="submit"
								class="btn btn-primary btn-info-full finish">Finish</button></li>
						<li><button type="button" data-dismiss="modal"
								class="btn btn-default next-step">Cancel</button></li>
					</ul>
				</div>
				<div class="clearfix"></div>
			</div>
		</form>
	</div>
</div>

<div id="loading">
	<img src="<%=request.getContextPath()%>/resources/image/loading.gif"
		alt="Loading..." /><br> Loading...
</div>

<script type="text/javascript">
	$.getScript("<%=request.getContextPath()%>/resources/js/patron-elig.js");
</script>