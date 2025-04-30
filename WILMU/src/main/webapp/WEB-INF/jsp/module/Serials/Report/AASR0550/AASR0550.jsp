<%@ page contentType="text/html; charset=UTF-8" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head> 

	<script type="text/javascript" src="${pageContext.request.contextPath}/js/Serials/globalSeReportAndListing.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/Serials/Report/AASR0550.js"></script>

	
	
</head>

<body>
	<!-- START MAIN CONTENT -->
			<div class="box box-default">
				<div class="panel panel-default">
					<div class="panel-heading">
						<!-- <div class="clearfix"></div> -->
					</div>

					<div class="panel-body" id="display_seReportnListing">
					
					<input type="hidden" id='setupCurrency' disabled> 
					
						<form class="form-horizontal" name="ReportnListing" id="ReportnListing">

						<div class="form-group">
							<div class="col-sm-2 col-md-2">
								<label class="form-check-label dateselectioinlabel">
									<input class="form-check-input" type="radio" name="dateSelection" id="dateSelection" value="orderdate" checked>
									Order Date Range
								</label>
							</div>
							<div class="col-sm-3 col-md-3">
								<label class="form-check-label">
									<input class="form-check-input" type="radio" name="dateSelection" id="dateSelection" value="receiveddate">
									Received Date Range
								</label>
							</div>
						</div>
						
							<jsp:include page="../../headerSearchCriteriaSeReportAndListing1.jsp"></jsp:include> 
							
							<input type="hidden" class="vendorSelection" value = "c">
							<jsp:include page="../../../../module/Vendor.jsp"></jsp:include> 
							
							<input type="hidden" class="patrSelection" value = "c">
							<jsp:include page="../../../../module/PatronID.jsp"></jsp:include> 
							
							
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
									</div>
								</div>
										
							</div>
							
							<jsp:include page="../../headerSearchCriteriaSeReportAndListing2.jsp"></jsp:include> 
							
						</form>
						
						<br><br>
						<table id="reportTable" class="table table-bordered table-striped display compact" width="100%">
						 <thead>
						 	<tr>
						 		<th>No</th>
						 		<th>Order No</th>
								<th>Vendor</th>
								<th>ISSN</th>
								<th>Title</th>
								<th>Author</th>
								<th>Frequency</th>
								<th>Order Date</th>
								<th>Expected Date</th>
								<th>Received Date</th>
								<th>Copies Ordered</th>
								<th>Copies Received</th>
								<th>Local Price</th>
						 	</tr>
						 </thead>
						 <tfoot align="right">
							<!-- <tr><th></th>
						 		<th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th>Total: </th><th></th><th></th><th></th><th></th></tr> -->
						</tfoot>
						</table>

						<br>
							<div class="col-md-12 col-lg-12 total_info" style="border:1px solid #ddd;text-align: right;">     
						   		<div class="row">
							    	<div class="form-group divtotal">
							        	<div class="col-md-12 col-lg-12 tolQty">
							            	<label>Total Copies Ordered: &nbsp;</label><span id="allTotalCopyOrder"> 0 &emsp;</span>
							               	<label>Total Copies Received: &nbsp;</label><span id="allTotalCopyReceived"> 0 &emsp;</span>
							             	<label>Total Local Price: &nbsp;</label><span id="allTotalAmount"> 0</span>
							             </div>	
							             </div>
							                
							              <div class="form-group">
							              	<div class="col-md-12 col-lg-12 tolAmount"></div>	
							                </div>
						         </div>
						     </div>
					</div>
				</div>
			</div>
		<!-- END MAIN CONTENT -->
		
		
</body>
</html>