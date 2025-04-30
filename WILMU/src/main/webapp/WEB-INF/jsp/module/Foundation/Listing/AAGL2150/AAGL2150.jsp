<%@ page contentType="text/html; charset=UTF-8" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head> 

	<script type="text/javascript" src="${pageContext.request.contextPath}/js/Foundation/globalFndReportAndListing.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/Foundation/Listing/AAGL2150.js"></script>

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
						 		<th>Marc type</th>
								<th>Indicator 1</th>
								<th>Tag</th>
								<th>Indicator 2</th>
								<th>Subfields</th>
								<th>Description</th>
								<th>Repeatable</th>
								<th>Mandatory</th>
						 	</tr>
						 </thead>
						</table>
					</div>
				</div>
			</div>
		<!-- END MAIN CONTENT -->
		
		
</body>
</html>