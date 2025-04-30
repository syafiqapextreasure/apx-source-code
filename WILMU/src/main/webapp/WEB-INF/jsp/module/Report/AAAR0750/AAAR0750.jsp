<%@ page contentType="text/html; charset=UTF-8" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head> 

	<script type="text/javascript" src="${pageContext.request.contextPath}/js/Report/AAAR0750.js"></script>

	
	
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
						
							<input type='hidden' class="module">
							
							<input type="hidden" class="vendorSelection" value = "m">
							<jsp:include page="../../Vendor.jsp"></jsp:include> 
						
							<div class="form-group">
								<div class="col-sm-2 col-md-2">
									<label><input type="radio" name="radioOption1" value="doctRange">&nbsp; Document Range</label>
								</div>
							</div>
							
							<div class="form-group">
								<div class="col-sm-2 col-md-2">
								</div>
								<div class="col-sm-3">
									<label><input type="radio" name="radioOption2" value="doctRangeinv">&nbsp; Invoice No</label>
								</div>
								<div class="col-sm-3">
									<label><input type="radio" name="radioOption2" value="doctRangevoc">&nbsp; Voucher No</label>
								</div>
							</div>
							
							
							<div class="form-group">
								<div class="col-sm-2 col-md-2">
								</div>
								<div class="col-sm-3">
									<label><input type="radio" name="radioOption2" value="doctRangecheq">&nbsp; Cheque No</label>
								</div>
								<div class="col-sm-3">
									<label><input type="radio" name="radioOption2" value="doctRangeref">&nbsp; Reference No (Payment)</label>
								</div>
							</div>
							
							<div class="form-group">
								<div class="col-sm-2 col-md-2">
								</div>
								<div class="col-sm-4">
									<div class="input-group">
										<input type="text" class="input-sm form-control"  name="startDoc"
											id="input-startDoc" autocomplete="off"/> <span class="input-group-addon">to</span>
										<input type="text" class="input-sm form-control" name="endDoc" id="input-endDoc" autocomplete="off"/>
									</div>
								</div>
							</div>
			
							
							<div class="form-group">
								<div class="col-sm-2 col-md-2">
									<label><input type="radio" name="radioOption1" value="dateRange">&nbsp; Date Range</label>
								</div>
							</div>
							
							<div class="form-group">
								<div class="col-sm-2 col-md-2">
								</div>
								<div class="col-sm-3">
									<label><input type="radio" name="radioOption3" value="dateRangeinvo">&nbsp; Invoice Created</label>
								</div>
								<div class="col-sm-3">
									<label><input type="radio" name="radioOption3" value="dateRangeRef">&nbsp; Reference (Payment)</label>
								</div>
							</div>
							
							<div class="form-group">
								<div class="col-sm-2 col-md-2">
								</div>
								<div class="col-sm-3">
									<label><input type="radio" name="radioOption3" value="dateRangevoc">&nbsp; Voucher</label>
								</div>
								<div class="col-sm-3">
									<label><input type="radio" name="radioOption3" value="dateRangeproc">&nbsp; Processed</label>
								</div>
							</div>
							
							<div class="form-group">
								<div class="col-sm-2 col-md-2">
								</div>
								<div class="col-sm-3">
									<label><input type="radio" name="radioOption3" value="dateRangecheq">&nbsp; Cheque</label>
								</div>
							</div>
							
							<div class="form-group">
								<div class="col-sm-2 col-md-2">
								</div>
								
								<div class="col-sm-4">
									<div class="input-group input-daterange">
										<input type="text" class="input-sm form-control"  name="startDate"
											id="input-startDate" autocomplete="off"/> <span class="input-group-addon">to</span>
										<input type="text" class="input-sm form-control" name="endDate" id="input-endDate" autocomplete="off"/>
									</div>
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
								 		<th>Invoice No</th>
										<th>Invoice Date</th>
										<th>Order No</th>
										<th>Vendor</th>
										<th>Payment Date</th>
										<th>Amount</th>
										<th>Cheque No</th>
										<th>Cheque Date</th>
										<th>Voucher No</th>
										<th>Voucher Date</th>
										<th>Pay Ref No</th>
										<th>Pay Ref Date</th>
								 	</tr>
								</thead>
								<tfoot align="right">
									<!-- <tr><th></th><th></th><th></th><th></th><th></th><th>Grand Total</th><th></th><th></th><th></th><th></th><th></th><th></th><th></th></tr> -->
								</tfoot>
						</table>
						
							<br>
							<div class="col-md-12 col-lg-12 total_info" style="border:1px solid #ddd;text-align: right;">     
						   		<div class="row">
							    	<div class="form-group divtotal">
							        	<div class="col-md-12 col-lg-12 tolQty">
							            	<!-- <label>Total Quantity: &nbsp;</label><span id="allTotalQty"> 0 &emsp;</span> -->
							               			
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
		<!-- END MAIN CONTENT -->
		
		
</body>
</html>