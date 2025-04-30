<%@ page contentType="text/html; charset=UTF-8" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head> 

	<script type="text/javascript" src="${pageContext.request.contextPath}/js/Serials/globalSeReportAndListing.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/Serials/Listing/AASL0750.js"></script>

	
	
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
						
							<jsp:include page="../../headerSearchCriteriaSeReportAndListing1.jsp"></jsp:include> 
							
							<div class="form-group ">
								<div class="col-sm-2 col-md-2">
									<input class="form-check-input" type="checkbox" value="range2" id="chkBoxRangeInvoice">
									<label>Invoice No Range</label>
								</div>
									<div class="col-sm-4">
										<div class="input-group">
											<input type="text" class="input-sm form-control"  name="startInput2"
												id="startInput2" autocomplete="off"/> <span class="input-group-addon">to</span>
											<input type="text" class="input-sm form-control" name="endInput2" id="endInput2" autocomplete="off"/>
										</div>
									</div>
							</div>
							
							
							<input type="hidden" class="vendorSelection" value = "c">
							<jsp:include page="../../../../module/Vendor.jsp"></jsp:include> 

							
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
											<jsp:include page="../../../../module/Select_OrderMode.jsp"></jsp:include> 
										</select>
									</div>
								</div> 
								<div class="col-sm-2 statusDiv">
									<div class="form-check">
										<input class="form-check-input" type="checkbox" value="status" id="chkBoxStatus">
											<label class="form-check-label statusLabel">Status</label>
									</div>
								</div>
								
								<!--(Request - R, Order - O, Invoice - I, Gift - G) -->
								<div class='col-sm-4 statusDiv'>
									<div class="form-check">
										<select id="seStat" multiple="multiple" name="seStat">
											<jsp:include page="../../../../module/Select_SeStatus.jsp">
												<jsp:param name="setSeStat" value="O" />
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
						 		<th>Invoice No</th>
								<th>Inv Created Date</th>
								<th>Inv Status</th>								
								<th>Order No</th>
								<th>Order Mode</th>
								<th>Vendor</th>
								<th>Payment Ref No</th>
								<th>Payment Req Date</th>
								<th>Payment Rec Date</th>
								<th>Amount</th>
						 	</tr>
						 </thead>
						 <tfoot align="right">
							<!-- <tr> <th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th>Total : </th><th></th></tr> -->
						</tfoot>
						</table>

							<br>
							<div class="col-md-12 col-lg-12 total_info" style="border:1px solid #ddd;text-align: right;">     
						   		<div class="row">
							    	<div class="form-group divtotal">
							        	<div class="col-md-12 col-lg-12 tolQty">

							               			
							             	<label>Total Amount: &nbsp;</label><span id="allTotalAmount"> 0</span>
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
			</div>
		<!-- END MAIN CONTENT -->
		
		
</body>
</html>