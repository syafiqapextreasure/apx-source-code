<%@ page contentType="text/html; charset=UTF-8" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

	<script type="text/javascript" src="${pageContext.request.contextPath}/js/ReportFnd/VendorStatistics.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath() %>/plugin/moment.min.js"></script>
	<link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">
	
	<script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/1.10.16/js/jquery.dataTables.js"></script> 
	<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.16/css/jquery.dataTables.css">
    <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/responsive/2.2.0/css/responsive.dataTables.min.css"> 
    <script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/responsive/2.2.0/js/dataTables.responsive.min.js"></script>
    
    
   <!--  <script type="text/javascript" src="https://cdn.datatables.net/1.10.24/js/jquery.dataTables.min.js"></script>
    <script type="text/javascript" src="https://cdn.datatables.net/rowgroup/1.1.2/js/dataTables.rowGroup.min.js"></script> -->
    
    <script type="text/javascript" src=" //cdn.datatables.net/plug-ins/1.10.24/api/sum().js"></script>
    <script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/buttons/1.6.1/js/dataTables.buttons.min.js"></script>
	<script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/buttons/1.6.1/js/buttons.flash.min.js"></script>
	<script type="text/javascript" charset="utf8" src="https://cdnjs.cloudflare.com/ajax/libs/jszip/3.1.3/jszip.min.js"></script>
	<script type="text/javascript" charset="utf8" src="https://cdnjs.cloudflare.com/ajax/libs/pdfmake/0.1.53/pdfmake.min.js"></script>
	<script type="text/javascript" charset="utf8" src="https://cdnjs.cloudflare.com/ajax/libs/pdfmake/0.1.53/vfs_fonts.js"></script>
	<script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/buttons/1.6.1/js/buttons.html5.min.js"></script>
	<script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/buttons/1.6.1/js/buttons.print.min.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath() %>/plugin/buttons.print.js"></script>
	


	<!-- <style type="text/css">	
	
		@media print
		{
			html, body {  border: 1px solid white;
            height: 99%;
            page-break-after: avoid;
            page-break-before: avoid;}
			.dt-print-table, .dt-print-table thead, .dt-print-table th, .dt-print-table tr 
			{border: 0 none !important;}
			
		}
		
		.center {
			text-align: center;
		}	
		
		.right {
			text-align: right;
		}	
		
		#red {
			color: red;
		}
		
		

	</style> -->
</head>

<body>
	<!-- START MAIN CONTENT -->
			<div class="box box-default">
				<div class="panel panel-default">
					<div class="panel-heading">
						
						<!-- <div class="clearfix"></div> -->
					</div>

					<div class="panel-body" id="display_cirAct">
						<form class="form-horizontal" name="cirActForm" id="cirActForm">
							<div class="form-group memdate">
								<div class="col-sm-2 col-md-2"><label>Date Range</label></div>
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
										<input class="form-check-input" type="radio" name="chkBoxModule" id="chkBoxAcq" value="AC" checked>
										Acquisition
									</label>
								</div>
								<div class="col-sm-2 col-md-2">
									<label class="form-check-label">
										<input class="form-check-input" type="radio" name="chkBoxModule" id="chkBoxSert" value="SE">
										Serial
									</label>
								</div>
							</div>
							
							<input type="hidden" class="vendorSelection" value = "c">
							<jsp:include page="../../Vendor.jsp"></jsp:include> 
							
							<!-- <div class="form-group">
								<div class="col-sm-2">
									<label><input type="checkbox" class="form-check-input" id="checkboxVendor" name="Retain" value="input-vendorCode">&nbsp Vendor</label>
								</div>
								<div class="col-sm-4">
									<div class="input-group">
										<input type="text" class="form-control" id="input-vendorCode" name="inputVendorCode">
											<a href="Modal_Vendor" id="searchvendor" class="input-group-addon btn btn-primary" data-toggle="modal" data-target="#modal_vendorSearch">
											<span class="glyphicon glyphicon-option-horizontal"></span></a>
									</div>
								</div>
							</div>
										
							<div class="form-group">
								<div class="col-sm-2"><label></label></div>
									<div class='col-sm-8'>
										<div id="div-vendorName"></div>
									</div>
							</div> -->
							
							<div class="btn-group pull-right ">	
								<div class="col-md-1"><button type="button" id="Reterive" class="btn btn-primary" title="Retrieve"> Retrieve</button></div>
					  				<!-- <div class="col-md-1"></div>
					  				<div class="col-md-1"><button type="button" id="print" class="btn btn-primary" title="Print"><i class="glyphicon glyphicon-print" aria-hidden="true"></i></button></div> -->
							</div>
						</form>
						
						<br><br>
						<table id="reportTable" class="table table-bordered table-striped">
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