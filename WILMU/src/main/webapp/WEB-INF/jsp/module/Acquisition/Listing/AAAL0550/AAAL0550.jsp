<%@ page contentType="text/html; charset=UTF-8" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head> 

	<script type="text/javascript" src="${pageContext.request.contextPath}/js/Acquisition/globalAcqReportAndListing.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/Acquisition/Listing/AAAL0550.js"></script>

	
	
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
						
							<jsp:include page="../../headerSearchCriteriaAcqReportAndListing1.jsp"></jsp:include> 
							
							<input type="hidden" class="vendorSelection" value = "c">
							<jsp:include page="../../../../module/Vendor.jsp"></jsp:include> 
							
							<input type="hidden" class="patrSelection" value = "c">
							<jsp:include page="../../../../module/PatronID.jsp"></jsp:include> 
							
							
							<div class="form-group div1">
								<!-- <div class="col-sm-2 orderModeDiv">
									<div class="form-check">
										<input class="form-check-input" type="checkbox" value="ordMode" id="chkBoxOrderMode">
											<label class="form-check-label">Order Mode</label>
									</div>
								</div>
								<div class='col-sm-4 orderModeDiv'>
									<div class="form-check">
										<select id="ordermode" multiple="multiple" name="ordermode">
											<jsp:include page="../../../../module/Select_OrderMode.jsp"></jsp:include> 
										</select>
									</div>
								</div> -->
								
								<div class="col-sm-3 statusDiv">
									<div class="form-check">
										<input class="form-check-input" type="checkbox" value="status" id="chkBoxStatus">
											<label class="form-check-label statusLabel">Status</label>
									</div>
								</div>
								
								<!--(Request - R, Order - O, Invoice - I, Gift - G) -->
								<div class='col-sm-4 statusDiv'>
									<div class="form-check">
										<select id="acqStat" multiple="multiple" name="acqStat">
											<jsp:include page="../../../../module/Select_AcqStatus.jsp">
												<jsp:param name="setAcqStat" value="R" />
											</jsp:include> 
										</select>
									</div>
								</div>
										
							</div>
							
							<jsp:include page="../../headerSearchCriteriaAcqReportAndListing2.jsp"></jsp:include> 
							
						</form>
						
						<br><br>
						<table id="reportTable" class="table table-bordered table-striped display compact" width="100%">
						 <thead>
						 	<tr>
						 		<th>No</th>
						 		<th>Request No</th>
								<th>Request Date</th>
								<th>Requestor</th>
								<th>Department</th>
								<th>Title</th>
								<th>Publisher</th>
								<th>Vendor</th>
								<th>Price</th>								
								<th>Quantity</th> 
						 	</tr>
						 </thead>
						</table>
					</div>
				</div>
			</div>
		<!-- END MAIN CONTENT -->
		
		
</body>
</html>