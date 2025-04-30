<%@ page contentType="text/html; charset=UTF-8" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head> 

	<script type="text/javascript" src="${pageContext.request.contextPath}/js/Serials/globalSeReportAndListing.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/Serials/Listing/AASL0450.js"></script>

	
	
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
						
							<jsp:include page="../../headerSearchCriteriaSeReportAndListing1.jsp"></jsp:include> 
							
							<input type="hidden" class="vendorSelection" value = "c">
							<jsp:include page="../../../../module/Vendor.jsp"></jsp:include> 
							
							<%-- <input type="hidden" class="patrSelection" value = "c">
							<jsp:include page="../../../../module/PatronID.jsp"></jsp:include>  --%>
							
							
							<div class="form-group div1">
								<div class="col-sm-2 orderModeDiv">
									<div class="form-check">
										<input class="form-check-input" type="checkbox" value="ordMode" id="chkBoxOrderMode">
											<label class="form-check-label">Order Mode</label>
									</div>
								</div>
								<div class='col-sm-4 orderModeDiv'>
									<div class="form-check">
										<select id="ordermode" multiple="multiple" name="ordermode">
											<jsp:include page="../../../../module/Select_SeOrderMode.jsp"></jsp:include> 
										</select>
									</div>
								</div>
								<%-- 
								<div class="col-sm-2 statusDiv">
									<div class="form-check">
										<input class="form-check-input" type="checkbox" value="status" id="chkBoxStatus">
											<label class="form-check-label statusLabel">Status</label>
									</div>
								</div>
								
								<!--(issue(hufurf shj, order(no shj))) -->
								<div class='col-sm-4 statusDiv'>
									<div class="form-check">
										<select id="seStat" multiple="multiple" name="seStat">
											<jsp:include page="../../../../module/Select_SeStatus.jsp">
												<jsp:param name="setSeStat" value="issue" />
											</jsp:include> 
										</select>
									</div> --%>
								</div>
										
							</div>
							
							
							<div class="form-group selection">
								<jsp:include page="../../../../module/Checkbox_endyearseial.jsp">
									<jsp:param name="selection" value="X" /> 
								</jsp:include>	
							</div>
							
							
							
							<jsp:include page="../../headerSearchCriteriaSeReportAndListing2.jsp"></jsp:include> 
							<br>
						</form>
						
						<br>
						<table id="reportTable" class="table table-bordered table-striped display compact" width="100%">
						 <thead>
						 	<tr>
						 		<th>No</th>
						 		<th>Title</th>
								<th>Volume</th>
								<th>Issue</th>
								<th>Start Date</th>
								<th>Stop Date</th>
								<th>Frequency</th>
								<th>Status</th>
								<th>Issue No</th>
								<th>Copy</th>
								<th>Branch</th>
								<th>Location</th>
								<th>Item Category</th>
								<th>Date</th>
								<th>Status</th>
								<th>Vendor</th>
						 	</tr>
						 </thead>
						 </table>
						
					</div>
				</div>
			</div>
		<!-- END MAIN CONTENT -->
		
		
</body>
</html>