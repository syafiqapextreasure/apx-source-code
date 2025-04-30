<%@ page contentType="text/html; charset=UTF-8" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head> 

	<script type="text/javascript" src="${pageContext.request.contextPath}/js/Acquisition/globalAcqReportAndListing.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/Acquisition/Report/AAAR0950.js"></script>

	
	
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
							
							<input type="hidden" class="patrSelection" value = "mOffiId">
							<jsp:include page="../../../../module/PatronID.jsp"></jsp:include> 
							
							<jsp:include page="../../headerSearchCriteriaAcqReportAndListing2.jsp"></jsp:include>  
							
						</form>
						
						<br><br>
				
							<table id="reportTableFull" class="table table-bordered table-striped display compact" width="100%">
							 <thead>
							 	<tr>
							 		<th>No</th>
							 		<th>Date</th>
							 		<th>Order No</th>
									<th>Reference No</th>
									<th>Control No</th>
									<th>Title</th>
									<th>Status</th>
							 	</tr>
							 </thead>
							</table>
							
							<br>
		
						
					</div>
				</div>
			</div>
		<!-- END MAIN CONTENT -->
		
		
</body>
</html>