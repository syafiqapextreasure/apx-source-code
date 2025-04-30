<%@ page contentType="text/html; charset=UTF-8" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head> 

	<script type="text/javascript" src="${pageContext.request.contextPath}/js/Acquisition/globalAcqEnquiry.js"></script> 
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/Acquisition/Enquiry/AAAE0250.js"></script>


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
					
						<form class="form-horizontal" name="acqEnquiry" id="acqEnquiry">
						
							<jsp:include page="../headerSearchCriteriaAcqEnquiry.jsp"></jsp:include> 
							
							
						</form>
						
						<br><br>
						<table id="acqEnquiryTable" class="table table-bordered table-striped display compact" width="100%">
						 <thead>
						 	<tr>
						 		<th>No</th>	
								<th>Branch</th>	
						 		<th>Order No</th>
								<th>Order Date</th>
								<th>Expected Date</th>
								<th>Title</th>
								<th>Status</th>
								<th>Vendor</th>
								<th>Copies/Sets</th>
								<th>Received</th>
								<th>Currency</th>
								<th>Foreign Cost</th>
								<th>Local Cost</th>
								<th>Invoice Status</th>		
						 	</tr>
						 </thead>
						</table>
					</div>
				</div>
			</div>
		<!-- END MAIN CONTENT -->
		
		
</body>
</html>