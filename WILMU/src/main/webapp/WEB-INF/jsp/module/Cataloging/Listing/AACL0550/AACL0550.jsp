<%@ page contentType="text/html; charset=UTF-8" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head> 

	<script type="text/javascript" src="${pageContext.request.contextPath}/js/Cataloging/globalCatReportAndListing.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/Cataloging/Listing/AACL0550.js"></script>

</head>

<body>
	<!-- START MAIN CONTENT -->
			<div class="box box-default">
				<div class="panel panel-default">
					<div class="panel-heading">
						<!-- <div class="clearfix"></div> -->
					</div>

					<div class="panel-body" id="display_fndReportnListing">
					 
					
						<form class="form-horizontal" name="catReportnListing" id="catReportnListing">
						
								
							<div class="form-group titleDiv">
								<div class="col-sm-2 col-md-2">
									<label>Query by</label>
								</div>
								<div class='col-sm-4 querybyDiv'>
										<select id="queryby" name="queryby" class="form-control">
											<jsp:include page="../../../../include/shared/Selection/Select_Queryby.jsp"></jsp:include>
										</select>
								</div>
							</div>

							
							<div class="form-group titleDiv">
								<div class="col-sm-2 col-md-2">
									<label class="titleLabel">Description</label>
								</div>
									<div class="col-sm-7">
										<input type="text" class="input-sm form-control"  name="inputDesc" id="inputDesc">
									</div>
							</div>
							
							<div class="btn-group pull-right ">	
									<div class="col-md-1"><button type="button" id="Reterive" class="btn btn-primary" title="Retrieve"> Retrieve</button></div>
							</div>
							
							
						</form>
						
						<br><br>
						<table id="catreportTable" class="table table-bordered table-striped display compact" width="100%">
						 <thead>
						 	<tr>
						 		<!-- <th><input type = "checkbox" class="checkAll" id="checkAll"></th> -->
						 		<th>Authority Status</th>
								<th>Hits</th>
								<th>Description</th>
						 	</tr>
						 </thead>
						</table>
					</div>
				</div>
			</div>
		<!-- END MAIN CONTENT -->
		
		
</body>
</html>