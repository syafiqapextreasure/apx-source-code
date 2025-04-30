<%@ page contentType="text/html; charset=UTF-8" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head> 

	<script type="text/javascript" src="${pageContext.request.contextPath}/js/Foundation/globalFndReportAndListing.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/Foundation/Listing/AAGL1150.js"></script>

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
						
							<jsp:include page="../../headerSearchCriteriaFndReportAndListing.jsp"></jsp:include> 

						</form>
						
						<br><br>
						<table id="fndreportTable" class="table table-bordered table-striped display compact" width="100%">
						 <thead>
						 	<tr>
						 		<th>No</th>
						 		<th>Patron Category</th>
								<th>Description</th>
								<th>Max Loan</th>
								<th>Max Fines</th>
								<th>Fines Limit</th>
						 	</tr>
						 </thead>
						</table>
					</div>
				</div>
			</div>
		<!-- END MAIN CONTENT -->
		
		
</body>
</html>