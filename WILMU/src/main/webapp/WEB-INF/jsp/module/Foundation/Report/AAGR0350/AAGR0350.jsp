<%@ page contentType="text/html; charset=UTF-8" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head> 

	<script type="text/javascript" src="${pageContext.request.contextPath}/js/Foundation/globalFndReportAndListing.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/Foundation/Report/AAGR0350.js"></script>

</head>

<body>
	<!-- START MAIN CONTENT -->
			<div class="box box-default">
				<div class="panel panel-default">
					<div class="panel-heading">
						<!-- <div class="clearfix"></div> -->
					</div>

					<div class="panel-body" id="display_fndReportnListing">
					 
					
						<form class="form-horizontal" name="fndReportnListing" id="fndReportnListing">
						
							<div class="form-group dateDiv">
								<div class="col-sm-2 col-md-2 dateLabel"><label>Date</label></div>
									<div class="col-sm-4">
										<div class="input-daterange input-group">
											<input type="text" class="input-sm form-control"  name="startDate"
												id="input-startDate" autocomplete="off"/> <span class="input-group-addon">to</span>
											<input type="text" class="input-sm form-control" name="endDate" id="input-endDate" autocomplete="off"/>
										</div>
									</div>
							</div>
							
							<div class="form-group">
								<div class="col-sm-2 col-md-2"><label>Module</label></div>
								
								<div class="col-sm-2 col-md-2">
									<label class="form-check-label">
										<input class="form-check-input" type="radio" name="chkBoxModule" id="chkBoxAcq" value="AC" data-name="Acquisition" checked>
										Acquisition
									</label>
								</div>
								<div class="col-sm-2 col-md-2">
									<label class="form-check-label">
										<input class="form-check-input" type="radio" name="chkBoxModule" id="chkBoxSert" value="SE" data-name="Serial">
										Serial
									</label>
								</div>
							</div>
							
							<input type="hidden" class="vendorSelection" value = "c">
							<jsp:include page="../../../../module/Vendor.jsp"></jsp:include> 
						
							<jsp:include page="../../headerSearchCriteriaFndReportAndListing.jsp"></jsp:include> 

						</form>
						
						<br><br>
						<table id="reportTableFnd" class="table table-bordered table-striped display compact" width="100%">
						 <thead>
						 	<tr>
						 		<!-- <th>No</th> -->
						 		<th>Statistic Item</th>
						 		<th style="text-align:right">Value</th>
						 	</tr>
						 </thead>
						 <tbody>
						 	<tr class="ACQ">
						 		<!-- <th>1</th> -->
						 		<th>Number Of Claims Sent</th>
						 		<td style="text-align:right" class="NumberOfClaimsSent"></td> 
						 	</tr>
						 	<tr class="SER">
						 		<!-- <th>2</th> -->
						 		<th>Number Of Claims Sent (Order)</th>
						 		<td style="text-align:right" class="NumberOfClaimsSentOrder"></td>
						 	</tr>
						 	<tr class="SER">
						 		<!-- <th>3</th> -->
						 		<th>Number Of Claims Sent (Issue)</th>
						 		<td style="text-align:right" class="NumberOfClaimsSentIssue"></td>   
						 	</tr>
						 	<tr>
						 		<!-- <th>4</th> -->
						 		<th>Number Of Copies Cancelled</th>
						 		<td style="text-align:right" class="NumberOfCopiesCancelled"></td>
						 	</tr>
						 	<tr>
						 		<!-- <th>5</th> -->
						 		<th>Number Of Copies Claimed</th>
						 		<td style="text-align:right" class="NumberOfCopiesClaimed"></td>
						 	</tr>
						 	<tr>
						 		<!-- <th>6</th> -->
						 		<th>Number Of Copies Ordered</th>
						 		<td style="text-align:right" class="NumberOfCopiesOrdered"></td>
						 	</tr>
						 	<tr>
						 		<!-- <th>7</th> -->
						 		<th>Number Of Copies Received</th>
						 		<td style="text-align:right" class="NumberOfCopiesReceived"></td>
						 	</tr>
						 	<tr>
						 		<!-- <th>8</th> -->
						 		<th>Number Of Title Ordered</th>
						 		<td style="text-align:right" class="NumberOfTitleOrdered"></td>
						 	</tr>
						 	<tr>
						 		<!-- <th>9</th> -->
						 		<th>Number Of Title Received</th>
						 		<td style="text-align:right" class="NumberOfTitleReceived"></td>
						 	</tr>
						 	<tr>
						 		<!-- <th>10</th> -->
						 		<th>Amount Ordered (RM)</th>
						 		<td style="text-align:right" class="AmountOrdered"></td>
						 	</tr>
						 	<tr>
						 		<!-- <th>11</th> -->
						 		<th>Amount Invoiced (RM)</th>
						 		<td style="text-align:right" class="AmountInvoiced"></td>
						 	</tr>
						 </tbody>
						</table>
					</div>
				</div>
			</div>
		<!-- END MAIN CONTENT -->
		
		
</body>
</html>