<%@ page contentType="text/html; charset=UTF-8" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head> 

	<script type="text/javascript" src="${pageContext.request.contextPath}/js/Serials/globalSeReportAndListing.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/Serials/Report/AASR0850.js"></script>

	
	
</head>

<body>
	<!-- START MAIN CONTENT -->
			<div class="box box-default">
				<div class="panel panel-default">
					<div class="panel-heading">
						<!-- <div class="clearfix"></div> -->
					</div>

					<div class="panel-body" id="display_acqReportnListing">
					
					<input type="hidden" id='setupCurrency' disabled> 
					
						<form class="form-horizontal" name="ReportnListing" id="ReportnListing">
						
						<div class="form-group dateDiv">
							<div class="col-sm-2 col-md-2 dateLabel"><input class="form-check-input" type="radio" value="rangeDate" id="chkBoxRangeDate" name="chkBoxSearchCateria" checked>
							<label>Request Date Range</label></div>
								<div class="col-sm-4">
									<div class="input-daterange input-group">
										<input type="text" class="input-sm form-control"  name="startDate"
											id="input-startDate" autocomplete="off"/> <span class="input-group-addon">to</span>
										<input type="text" class="input-sm form-control" name="endDate" id="input-endDate" autocomplete="off"/>
									</div>
								</div>
						</div>
						
						
							<div class="form-group rangeDiv">
								<div class="col-sm-2 col-md-2">
									<input class="form-check-input" type="radio" value="range" id="chkBoxRange" name="chkBoxSearchCateria">
									<label class="rangeLabel">Range</label>
								</div>
									<div class="col-sm-4">
										<div class="input-group">
											<input type="text" class="input-sm form-control"  name="startInput"
												id="startInput" autocomplete="off"/> <span class="input-group-addon">to</span>
											<input type="text" class="input-sm form-control" name="endInput" id="endInput" autocomplete="off"/>
										</div>
									</div>
							</div>

							<input type="hidden" class="patrSelection" value = "r">
							<jsp:include page="../../../../module/PatronID.jsp"></jsp:include> 
							

							<div class="form-group">
								<div class="col-sm-2">
									<div class="form-check">
										<input class="form-check-input" type="radio" value="controlNo" id="selectionoption" name="chkBoxSearchCateria">
											<label class="form-check-label">Control No</label>
									</div>
								</div>
										
								<div class="col-sm-5">
									<div class="input-group">
										<textarea class="form-control" id="input-contorlNo" name="input-contorlNo" rows="3"></textarea>
											<a href="RetrieveTitleModal?action=5" id="searchCtrlNo" class="input-group-addon btn btn-primary searchCtrlNo" data-toggle="modal" data-target="#titleSearch">
												<span class="glyphicon glyphicon-th-list"></span></a>
									</div>
								</div>
									<!-- <div class='col-sm-5'>
										<div id="div-ctrolNo"> </div>
									</div>	 -->
							</div>
							
							<div class="form-group">
								<div class="col-sm-2">
								</div>
										
								<div class='col-sm-5'>
									<div id="div-ctrolNo"> </div>
								</div>	 
							</div>
							
							
							<div class="form-group titleDiv">
								<div class="col-sm-2 col-md-2">
									<input class="form-check-input" type="radio" value="title" id="chkBoxTitle" name="chkBoxSearchCateria">
									<label>Title Entered By Requestor</label>
								</div>
									<div class="col-sm-7">
										<input type="text" class="input-sm form-control"  name="titleinput" id="titleinput" >
									</div>
							</div>
							
							
							<div class="btn-group pull-right ">	
								<div class="col-md-1"><button type="button" id="Reterive" class="btn btn-primary" title="Retrieve"> Retrieve</button></div>
							</div>
							
							
						</form>
						
						<br><br>
						<table id="reportTable" class="table table-bordered table-striped display compact" width="100%">
						 <thead>
						 	<tr>
						 		<th>No</th>
							 	<th>Request Number</th>
								<th>Control Number</th>
								<th>Date</th>
								<th>Requestor</th>
								<th>Department</th>
								<th>Title</th>
								<th>Publisher</th>
								<th>ISSN</th>
								<th>Kos</th>
								<th>Status</th>
								<th>Apply Status</th>
						 	</tr>
						 </thead>
						</table>
					</div>
				</div>
			</div>
		<!-- END MAIN CONTENT -->
		
		
</body>
</html>