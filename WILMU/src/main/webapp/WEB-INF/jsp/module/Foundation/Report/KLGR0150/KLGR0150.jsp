<%@ page contentType="text/html; charset=UTF-8" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head> 

	<script type="text/javascript" src="${pageContext.request.contextPath}/js/Foundation/globalFndReportAndListing.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/Foundation/Report/KLGR0150.js"></script>

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
						
							<jsp:include page="../../headerSearchCriteriaFndReportAndListing.jsp"></jsp:include> 

						</form>
						
						<br><br>
						<table id="fndreportTable" class="table table-bordered table-striped display compact" width="100%">
						 <thead>
						 	<tr>
						 		<th>No</th>
						 		<th>User Name</th>
								<th>Dept/School</th>
								<th>Date Used</th>
								<th>Timex</th>								
								<th>No of Hours</th>
						 	</tr>
						 </thead>
						</table>
					</div>
				</div>
			</div>
		<!-- END MAIN CONTENT -->
		
		
</body>
</html>