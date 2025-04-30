<%@ page contentType="text/html; charset=UTF-8" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head> 

	<script type="text/javascript" src="${pageContext.request.contextPath}/js/Serials/globalSeReportAndListing.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/Serials/Listing/AASL0550.js"></script>

	
	
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
						
						
						<div class="form-group">
									<div class="col-sm-2 col-md-2">
									<label class="form-check-label">
										Date Range
									</label>
								</div>
								
								<div class="form-group">
									<div class="col-sm-2 col-md-2">
										<label class="form-check-label">
											<input class="form-check-input" type="radio" name="dateSelection" id="dateSelection" value="02CRDATE" data-name="Catalog Date Range" checked>
											Catalog Date  Range
										</label>
									</div>
									<div class="col-sm-2 col-md-2">
										<label class="form-check-label dateselectioinlabel">
											<input class="form-check-input" type="radio" name="dateSelection" id="dateSelection" value="02IDXDATE" data-name="Index Date Range">
											Index Date Range
										</label>
									</div>
								</div>

						</div> 
						
						
						<div class="form-group">
								<div class="col-sm-2 col-md-2 dateLabel"></div>
								<div class="col-sm-4">
									<div class="input-daterange input-group">
										<input type="text" data-date-format="dd/mm/yyyy" class="input-sm form-control"  name="startDate"  
											id="input-startDate" autocomplete="off"/> <span class="input-group-addon">to</span>
										<input type="text" class="input-sm form-control" name="endDate" id="input-endDate" autocomplete="off"/>
									</div>
								</div>
						</div>
						 
							
							<input type="hidden" class="patrSelection" value = "cOffiId">
							<jsp:include page="../../../../module/PatronID.jsp"></jsp:include> 
							
						<div class="form-group">
							<div class="col-sm-2 col-md-2"><label>Format</label></div>
							<div class="col-sm-2 col-md-2">
								<label class="form-check-label formatlabel">
									<input class="form-check-input" type="radio" name="formatlabel" id="formatlabel" value="marc" checked>
									MARC
								</label>
							</div>
							<div class="col-sm-2 col-md-2">
								<label class="form-check-label">
									<input class="form-check-input" type="radio" name="formatlabel" id="formatlabel" value="linear">
									Linear
								</label>
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
						 		<!-- <th>No</th> -->
						 		<th>Control No</th>
								<th>Tag</th>
								<th>Description</th>								
								<th>Indi 1</th>
								<th>Indi 2</th>
								<th>Data</th>
								<th>Accession No</th>
								<th>Branch</th>
								<th>Location</th>								
								<th>Item Category</th>
								<th>Status</th>
								<th>Matno</th>
						 	</tr>
						 </thead>
						 <tfoot align="right">
							<!-- <tr><th></th>
						 		<th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th>Total :</th><th></th><th></th><th></th></tr> -->
						</tfoot>
						</table>
						
						
					</div>
				</div>
			</div>
		<!-- END MAIN CONTENT -->
		
		
</body>
</html>