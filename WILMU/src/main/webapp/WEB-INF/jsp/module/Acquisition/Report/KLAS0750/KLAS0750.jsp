<%@ page contentType="text/html; charset=UTF-8" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head> 

	<script type="text/javascript" src="${pageContext.request.contextPath}/js/Acquisition/globalAcqReportAndListing.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/Acquisition/Report/KLAS0750.js"></script>

	
	
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
							
							
							
							<jsp:include page="../../headerSearchCriteriaAcqReportAndListing2.jsp"></jsp:include> 
							
						</form>
						
						<br><br>
						<div class = "divreportTableFull">
							<table id="reportTableFull" class="table table-bordered table-striped display compact" width="100%">
							 <thead>
							 	<tr>
							 		<th>No</th>
							 		<th>Vendor</th>
							 		<th>Order No</th>
									<th>Order Date</th>
									<th>Date Received</th>
									<th>Order Price </th>
									<th>Title</th>
									<th>Invoice No</th>
									<th>Qty Received</th>
									<th>Status</th>
									<!-- <th>Total</th>  -->
							 	</tr>
							 </thead>
							</table>
							
							<br>
									<div class="col-md-12 col-lg-12 total_info" style="border:1px solid #ddd;text-align: right;">
					               
					                
						                <div class="row">
							               	<div class="form-group divtotal">
							               	    <div class="col-md-12 col-lg-12 tolQty">
							               			<label>Grand Total Received Items : &nbsp;</label><span id="allTotalQty"> 0 &emsp;</span>
							                	</div>	
							                </div>
							                
							                <div class="form-group">
							               	    <div class="col-md-12 col-lg-12 tolAmount">
							               			
							                	</div>	
							                </div>
						                </div>
						             </div>
						</div>
						
						<div class = "divreportTableSummary">
							<table id="reportTableSummary" class="table table-bordered table-striped display compact" width="100%">
							 <thead>
							 	<tr>
							 		<th>Vendor</th>
							 		<th>Total Received Item (Orders)</th>
									<th>Total Not Received Item (Orders)</th>
									<th>Total Local Payment (Invoices)</th>
									<th>Total Unpaid Local Amount (Invoice)</th>
							 	</tr>
							 </thead>
							</table>
						</div>
					</div>
				</div>
			</div>
		<!-- END MAIN CONTENT -->
		
		
</body>
</html>