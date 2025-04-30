<%@ page contentType="text/html; charset=UTF-8" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

	<script type="text/javascript" src="${pageContext.request.contextPath}/js/binding/checkinBinding.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath() %>/plugin/moment.min.js"></script>
	<link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">

	<script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/1.10.16/js/jquery.dataTables.js"></script> 
	<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.16/css/jquery.dataTables.css">
    <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/responsive/2.2.0/css/responsive.dataTables.min.css">
    <script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/responsive/2.2.0/js/dataTables.responsive.min.js"></script>

	<style type="text/css">	
		.center {
			text-align: center;
		}	
		
		#red {
			color: red;
		}	
	</style>
</head>

<body>
	<!-- START MAIN CONTENT -->
	<div class="container">
		<div class="panel-group" id="accordion" role="tablist"
			aria-multiselectable="true">
			<div class="panel panel-default">
				<div class="panel-heading" role="tab" id="headingOne">
					<h4 class="panel-title">
						<a role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseSearch" aria-expanded="true"
							aria-controls="collapseSearch"> Search </a>
					</h4>
				</div>
				<div id="collapseSearch" class="panel-collapse collapse in"
					role="tabpanel" aria-labelledby="headingOne">
					<div class="panel-body">
						<!-- Search Form -->
						<form class="form-horizontal" name="searchForm" id="searchForm">
							
							<div class="form-group">
									<!-- <div class="col-sm-1"></div> -->
									<div class="col-sm-3"><label>Search</label></div>
									<div class="col-sm-6">
										<input type="text" class="form-control criteria ctrlno" name="criteria" id="criteria">					
										<!--Date -->
										<div class="input-daterange input-group" id="datepickerCheckin">
											<input type="text" class="form-control"
												name="start" id="input-startDate" /> <span
												class="input-group-addon">to</span> <input type="text"
												class="form-control" name="end" id="input-endDate" />
										</div>
										<!-- END Date -->
									</div>
							</div>
							
							<div class="form-group">
									<div class="col-sm-4">
										<label><input type="radio" name="radioOption" value="issn">&nbsp ISSN</label>
									</div>
									<div class="col-sm-3">
										<label><input type="radio" name="radioOption" value="binder">&nbsp Binder</label>
									</div>
									<div class="col-sm-4">
										<label><input type="radio" name="radioOption" value="accno">&nbsp Accession No</label>
									</div>
							</div>
							
							<div class="form-group">
									<div class="col-sm-4">
										<label><input type="radio" name="radioOption" value="refno" checked>&nbsp Reference No</label>
									</div>
									<div class="col-sm-3">
										<label><input type="radio" name="radioOption" value="year">&nbsp Year</label>
									</div>
									<div class="col-sm-4">
										<label><input type="radio" name="radioOption" value="datesent">&nbsp Enter Date Sent</label>
									</div>
									<!-- <div class="col-sm-4">
										<label><input type="radio" name="radioOption" value="ctrlno">&nbsp Title With Control No</label>
									</div> -->
							</div>
							
							<div class="form-group">
									<div class="col-sm-4">
										<label><input type="radio" name="radioOption" value="ctrlno">&nbsp Title With Control No 
											<!-- <a href="RetrieveTitleModal?action=20" id="searchISSN" class="input-group-addon btn btn-primary searchpatr" data-toggle="modal" data-target="#titleSearch">
												<span class="glyphicon glyphicon-option-horizontal"></span></a> -->
											<button type="button" id="checkinCtrlno" class="btn btn-primary"  href="RetrieveTitleModal?action=20" data-toggle="modal" data-target="#titleSearch">
											<span class="glyphicon glyphicon-option-horizontal" style="color:white"></span>
											</button>
										</label> 
									</div><!-- 
									<div class="col-sm-2">
										<div class="input-group">
											<input type="hidden" class="form-control" id="accessionNo" name="accessionNo">
												<a href="RetrieveTitleModal?action=15" id="searchAccsion" class="input-group-addon btn btn-primary searchpatr" data-toggle="modal" data-target="#titleSearch">
												<span class="glyphicon glyphicon-option-horizontal"></span></a>
										</div>
									</div> -->
							</div>
  	
							<div class="btn-group pull-right ">	
									<div class="col-md-1"><button type="button" id="bindCheckInReterive" class="btn btn-primary" title="Retrieve"><span class="glyphicon glyphicon-log-in" style="color:white"></span></button></div>
  									<div class="col-md-1"></div>
  									<div class="col-md-1"><button type="button" id="checkIn" class="btn btn-primary" title="Check In" data-original-title="checkIn"><i class="fa fa-check-circle fa-lg" aria-hidden="true"></i></button></div>
							</div>

						</form>
					</div>
				</div>
			</div>
			<div class="panel panel-default">
				<div class="panel-heading">
					<h4 class="panel-title">
						<a data-toggle="collapse" data-parent="#form_parent"
							href="#panel-result">Result</a>
					</h4>
				</div>
				<div id="panel-result" class="panel-collapse collapse">
					<div class="panel-body">
						<input type="hidden" id="input-parameters">
						<!-- Search Result -->
						<div id="search-results">
							<table class="table table-hover" id="table-BindCheckIn">
								<thead>
									<tr>
										<th><!-- <input name="select_all" value="1" id="example-select-all" type="radio" /> --></th>
										<th>Binding No</th>
										<th>Title</th>
										<th>Volume/Issue, Copy</th>
										<th>Binder</th>
										<th>Officer</th>
										<th>Date Sent</th>
										<th>Accession No</th>
										<th>Type</th>
										<th>Control No</th>
										<th>Vol From</th>
										<th>Vol To</th>
										<th>Copy</th> 
									</tr>
								</thead>
								<tbody>
								</tbody>
							</table>
						</div>
					</div>
				</div>
		</div>
		</div>
	</div>		
	<!-- END MAIN CONTENT -->

		
		<!-- MODAL WHEN CLICK Search button-search binder -->
	    <div class="modal fade" id="modal_vendorSearch" tabindex="-1" role="dialog" aria-labelledby="modal_vendorSearch" aria-hidden="true" data-keyboard="false" data-backdrop="static"> 
			<div class="modal-dialog" style="width:50%;">
				    <div class="modal-content" id="modal_vendorSearchContent">
					  <!-- Remote content load here  -->
				  	</div>
			</div>
		</div>
		<!-- Modal END modal search  Binder-->

		<!-- MODAL WHEN CLICK CheckIn Detail For Acession -->
		<div class="modal fade" id="modalAcessionDetail" tabindex="-1" role="dialog" aria-labelledby="modalEditIssue" aria-hidden="true" data-keyboard="false" data-backdrop="static">
		<div class="modal-dialog" role="document" style="width:85%;overflow:auto">
			<div class="modal-content">
				<!-- Modal content-->	
					<div class="modal-header">
						<h5 class="modal-title" id="modalName" align="center">Accession Detail</h5>
					    	<button type="button" id="closeModalAccDetail" class="close" data-dismiss="modal" aria-label="Close">
					          <span aria-hidden="true">&times;</span>
					        </button>
					</div>
					<div class="modal-body">

					<form role="form" class="form-horizontal" id="formdataAcessionDetail" method="post"> 	
						
						<div class="form-group">
							<div class="col-sm-2"><label>Control No</label></div>
							<div class="col-sm-2">
								<input type="text" class="form-control" id="checkCtrlno" name="checkCtrlno" disabled>
							</div>
							<div class="col-sm-2"></div>
							<div class="col-sm-2"><label>Accession</label></div>
							<div class="col-sm-2">
								<input type="text" class="form-control" id="checkacc" name="checkacc">
							</div>
							<div class="col-sm-1">
								<!-- <button id="checkgenerate" class="btn btn-primary" style=""><span class="fa fa-credit-card" style="color:white" title="Generate Accession No"></span></button> -->
								<a class="btn btn-primary" title="Generate Accession No"><span id="checkgenerate" class="glyphicon glyphicon-credit-card"></span></a>
							</div>
						</div>					
						
						<div class="form-group">
							<div class="col-sm-2"><label>SMD</label></div>
								<div class="col-sm-3">
									<select class="form-control" id="checkSMD" name="checkSMD">
										<jsp:include page="../Select_SMD.jsp"></jsp:include>
									</select>
								</div>
							<div class="col-sm-1"></div>
							<div class="col-sm-2"><label>Item Category</label></div>
								<div class="col-sm-3">
									<select class="form-control" id="checkicat" name="checkicat">
										<jsp:include page="../Select_ItemCatgory.jsp"></jsp:include>
									</select>
								</div>
						</div>
						
						<div class="form-group">
							<div class="col-sm-2"><label>Location</label></div>
								<div class="col-sm-3">
									<select class="form-control" id="checkloca" name="checkloca">
										<jsp:include page="../Select_Loca.jsp"></jsp:include>
									</select>
								</div>
							<div class="col-sm-1"></div>
							<div class="col-sm-2"><label>Condition</label></div>
								<div class="col-sm-3">
									<select class="form-control" id="checkcon" name="checkcon">
										<jsp:include page="../Select_Con.jsp"></jsp:include>
									</select>
								</div>
						</div>
						
						<div class="form-group">
							<div class="col-sm-2"><label>Currency</label></div>
								<div class="col-sm-3">
									<select class="form-control" id="checkcurrent" name="checkcurrent">
										<jsp:include page="../Select_Currency.jsp"></jsp:include>
									</select>
								</div> 
							<div class="col-sm-1"></div>
							<div class="col-sm-2"><label>Exchange Rate</label></div>
								<div class="col-sm-3">
									<input type="text" class="form-control" id="checkerate" name="checkerate">
								</div> 
						</div>
						
						<div class="form-group">
							<div class="col-sm-2"><label>Foreign Price</label></div>
							<div class="col-sm-2">
								<input type="text" class="form-control" id="checkfprice" name="checkfprice">
							</div>
							<div class="col-sm-2"></div>
							<div class="col-sm-2"><label>Local Price</label></div>
							<div class="col-sm-2">
								<input type="text" class="form-control" id="checklprice" name="checklprice">
							</div>
						</div>	
						
						<div class="form-group">
							<div class="col-sm-2"><label>Volume</label></div>
							<div class="col-sm-3">
								<input type="text" class="form-control" id="checkvol" name="checkvol">
							</div>
							<div class="col-sm-1"></div>
							<div class="col-sm-2"><label>Copy No</label></div>
							<div class="col-sm-2">
								<input type="text" class="form-control" id="checkcopy" name="checkcopy">
							</div>
						</div>	
						
						<div class="form-group">
							<div class="col-sm-2"><label>Spine</label></div>
								<div class="col-sm-7">
									<input type="text" class="form-control" id="checkspine" name="checkspine">
								</div>
						</div>
						
								
						<div class="form-group modal-footer">
							<button type="submit" id="saveEditIssue" class="btn btn-primary saveCheckin">Ok</button>
							<button type="button" id="close" class="btn btn-secondary" data-dismiss="modal">Cancel</button>
						</div> 
					</form>
						 	 
					</div>
				<!-- End Modal content -->
				</div>
			</div>
		</div>
		<!-- MODAL END CheckIn Detail For Acession -->
		
		<!-- MODAL WHEN CLICK Search CONTROL NUMBER -->
		<div class="modal fade" id="titleSearch" tabindex="-1" role="dialog" aria-labelledby="titleSearch" data-keyboard="false" data-backdrop="static">
			<div class="modal-dialog" role="document" style="width:60%;overflow:auto">
				<div class="modal-content">
				<!-- Remote content load here -->
				</div>
			</div>
		</div>
		<!-- Modal END modal search CONTROL NUMBER-->
</body>
</html>