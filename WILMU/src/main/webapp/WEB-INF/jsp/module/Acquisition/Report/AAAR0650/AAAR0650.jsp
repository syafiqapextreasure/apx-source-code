<%@ page contentType="text/html; charset=UTF-8" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head> 

	<%-- <script type="text/javascript" src="${pageContext.request.contextPath}/js/Acquisition/globalAcqReportAndListing.js"></script> --%>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/Acquisition/Report/AAAR0650.js"></script>

	
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
						
						
						<input type="hidden" class="vendorSelection" value = "m">
						<jsp:include page="../../../../module/Vendor.jsp"></jsp:include>
						
						<div class="form-group branchDiv">
							<div class="col-sm-3 col-md-3"><label>Branch</label></div>
								<div class='col-sm-6'>
									<div class="form-check">
										<select id="branch" multiple="multiple" name="branch">
											<jsp:include page="../../../../module/Select_Branch.jsp"></jsp:include> 
										</select>
									</div>
								</div>	
						</div>
						
						<div class="form-group">
							<div class="col-sm-2 col-md-3">
								<label class="form-check-label dateselectioinlabel">
									<input class="form-check-input" type="radio" name="optSelection" id="optSelection" value="orderno" checked>
									Order No
								</label>
							</div>
							<div class="col-sm-2 col-md-2">
								<label class="form-check-label">
									<input class="form-check-input" type="radio" name="optSelection" id="optSelection" value="invoiceno">
									Invoice No
								</label>
							</div>
						</div>
						
								
							<div class="form-group div1">
								<div class="col-sm-3 orderModeDiv">
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
								<div class='col-sm-3 statusDiv'>
									<div class="form-check">
										<select id="acqStat" multiple="multiple" name="acqStat">
											<jsp:include page="../../../../module/Select_AcqStatus.jsp">
												<jsp:param name="setAcqStat" value="X" />
											</jsp:include> 
										</select>
									</div>
								</div>
										
							</div>
							
							<div class="form-group div2">
								<div class="col-sm-3 deptDiv">
									<div class="form-check">
										<input class="form-check-input" type="checkbox" value="department" id="chkBoxDepartment">
											<label class="form-check-label deptLabel">Department</label>
									</div>
								</div>
								<div class='col-sm-4 deptDiv'>
									<div class="form-check">
										<select id="dept" multiple="multiple" name="dept">
											<jsp:include page="../../../../module/Select_Department.jsp"></jsp:include> 
										</select>
									</div>
								</div>
								
								<div class="col-sm-2 invstatDiv">
									<div class="form-check">
										<input class="form-check-input" type="checkbox" value="invstatus" id="chkBoxInvStatus">
											<label class="form-check-label">Invoice Status</label>
									</div>
								</div>
								<div class='col-sm-3 invstatDiv'>
									<div class="form-check">
										<select id="acqinvstat" multiple="multiple" name="acqinvstat">
											<jsp:include page="../../../../module/Select_Fndcode.jsp">
												<jsp:param name="fcode" value="Q" /> 
											</jsp:include> 
										</select>
									</div>
								</div>
										
							</div>
							
							<%-- <jsp:include page="../../headerSearchCriteriaAcqReportAndListing2.jsp"></jsp:include> --%> 
							
							
							<div class="form-group rangeDiv">
								<div class="col-sm-3 col-md-3">
									<!-- <input class="form-check-input" type="radio" value="range" name="filterby">
									<label class="rangeLabel">Range</label> -->
									 <label><input type="radio" name="filterby" value="range"><span class="rangeLabel"> Range</span> </label>
								</div>
									<div class="col-sm-4">
										<div class="input-group">
											<input type="text" class="input-sm form-control"  name="startInput"
												id="startInput" autocomplete="off"/> <span class="input-group-addon">to</span>
											<input type="text" class="input-sm form-control" name="endInput" id="endInput" autocomplete="off"/>
										</div>
									</div>
							</div>
							
							
							<div class="form-group dateDiv">								
								<div class="col-sm-3 col-md-3">
									<!-- <input class="form-check-input dateLabel" type="radio" value="date" name="filterby">
									<label class="datelabel">Date</label> -->
									<label><input type="radio" name="filterby" value="date"><span class="datelabel"> Date</span> </label>
								</div>
								
									<div class="col-sm-4">
										<div class="input-daterange input-group">
											<input type="text" class="input-sm form-control"  name="startDate"
												id="input-startDate" autocomplete="off"/> <span class="input-group-addon">to</span>
											<input type="text" class="input-sm form-control" name="endDate" id="input-endDate" autocomplete="off"/>
										</div>
									</div>
							</div>
							
							
							<div class="form-group dateDiv2">								
								<div class="col-sm-3 col-md-3">
									<!-- <input class="form-check-input" type="radio" value="orderdate" name="filterby">
									<label>Order Date</label> -->
									<label><input type="radio" name="filterby" value="orderdate"> Order Date</label>
								</div>
								
									<div class="col-sm-4">
										<div class="input-daterange input-group">
											<input type="text" class="input-sm form-control"  name="startDate2"
												id="input-startDate2" autocomplete="off"/> <span class="input-group-addon">to</span>
											<input type="text" class="input-sm form-control" name="endDate2" id="input-endDate2" autocomplete="off"/>
										</div>
									</div>
							</div>
							
							<div class="btn-group pull-right ">	
								<div class="col-md-1"><button type="button" id="Reterive" class="btn btn-primary" title="Retrieve"> Retrieve</button></div>
							</div>
							
						</form>
						
						<br><br>
						
						<div class = "divTableOrder">
							<table id="reportTableOrder" class="table table-bordered table-striped display compact" width="100%">
							 <thead>
							 	<tr>
							 		<th>No</th>
							 		<th>Order No</th>
							 		<th>Order Created Date</th>
									<th>Order Date</th>
									<th>Invoice No</th>
									<th>Order Mode</th>
									<th>Order Status</th>
									<th>Control No</th>
									<th>PO/refer No</th>
							 		<th>Amount</th>
							 	</tr>
							 </thead>
							 <tfoot align="right">
							 	<!-- <tr>
							 		<th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th>Total : </th><th></th>
							 	</tr> -->
							 </tfoot>
							</table>

							<br>
							<div class="col-md-12 col-lg-12 total_info" style="border:1px solid #ddd;text-align: right;">     
						   		<div class="row">
							    	<div class="form-group divtotalOrder">
							        	<div class="col-md-12 col-lg-12 tolQtyOrder">
							            	<!-- <label>Total Quantity: &nbsp;</label><span id="allTotalQty"> 0 &emsp;</span> -->
							               			
							             	<label>Total Amount: &nbsp;</label><span id="allTotalAmountOrder"> 0</span>
							             </div>	
							             </div>
							                
							              <div class="form-group">
							              	<div class="col-md-12 col-lg-12 tolAmountOrder"></div>	
							                </div>
						         </div>
						     </div>
						</div>
						
						<div class="divTableInvoice">
							<table id="reportTableInvoice" class="table table-bordered table-striped display compact" width="100%">
							 <thead>
							 	<tr>
							 		<th>No</th>
							 		<th>Invoice No</th>
									<th>Invoice Date</th>
									<th>Invoice Status</th>
									<th>Control No</th>
									<th>PO/refer No</th>
									<th>Order No</th>
									<th>Order Created Date</th>
									<th>Order Date</th>
									<th>Order Mode</th>
									<th>Order Status</th>
									<th>Pay Ref No</th>
									<th>Pay Request Date</th>
									<th>Pay Record Date</th>
									<th>Amount</th>
							 	</tr>
							 </thead>
							 <tfoot align="right">
								<!-- <tr>
									<th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th>Total : </th><th></th>
								</tr> -->
							</tfoot>
							</table>

							<br>
							<div class="col-md-12 col-lg-12 total_info" style="border:1px solid #ddd;text-align: right;">     
						   		<div class="row">
							    	<div class="form-group divtotalInvoice">
							        	<div class="col-md-12 col-lg-12 tolQtyInvoice">
							            	<!-- <label>Total Quantity: &nbsp;</label><span id="allTotalQty"> 0 &emsp;</span> -->
							               			
							             	<label>Total Amount: &nbsp;</label><span id="allTotalAmountInvoice"> 0</span>
							             </div>	
							             </div>
							                
							              <div class="form-group">
							              	<div class="col-md-12 col-lg-12 tolAmountInvoice"></div>	
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