<%@ page contentType="text/html; charset=UTF-8" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head> 

	<script type="text/javascript" src="${pageContext.request.contextPath}/js/Acquisition/globalAcqReportAndListing.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/Acquisition/Listing/AAAL0850.js"></script>

	
	
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
							
							
							<input type="hidden" class="patrSelection" value = "m">
							<jsp:include page="../../../../module/PatronID.jsp"></jsp:include> 
							
							<input type="hidden" class="vendorSelection" value = "c">
							<jsp:include page="../../../../module/Vendor.jsp"></jsp:include> 
							
							
							<div class="form-group div1">
								<div class="col-sm-2 orderModeDiv">
									<div class="form-check">
										<input class="form-check-input" type="checkbox" value="ordMode" id="chkBoxOrderMode">
											<label class="form-check-label">Order Mode</label>
									</div>
								</div>
								<div class='col-sm-4 orderModeDiv'>
									<div class="form-check">
										<select id="ordermode" multiple="multiple" name="ordermode">
											<jsp:include page="../../../../module/Select_OrderMode.jsp"></jsp:include> 
										</select>
									</div>
								</div>
								
								<div class="col-sm-2 statusDiv">
									<div class="form-check">
										<input class="form-check-input" type="checkbox" value="status" id="chkBoxStatus">
											<label class="form-check-label statusLabel">Status</label>
									</div>
								</div>
								
								<!--(Request - R, Order - O, Invoice - I, Gift - G) -->
								<div class='col-sm-4 statusDiv'>
									<div class="form-check">
										<select id="acqStat" multiple="multiple" name="acqStat">
											<jsp:include page="../../../../module/Select_AcqStatus.jsp">
												<jsp:param name="setAcqStat" value="R" />
											</jsp:include> 
										</select>
									</div>
								</div>
										
							</div>
							
							<jsp:include page="../../headerSearchCriteriaAcqReportAndListing2.jsp"></jsp:include> 
							
						</form>
						
						<br><br>
						<table id="reportTable" class="table table-bordered table-striped display compact" width="100%">
						 <thead>
						 	<tr>
						 		<th>No</th>
						 		<th>Title</th>
								<th>Call No</th>
								<th>Location</th>
								<th>Branch</th>
								<th>Accession No</th>
								<th>Item Category</th>
								<th>Requestor</th>
						 	</tr>
						 </thead>
						</table>
					</div>
				</div>
			</div>
		<!-- END MAIN CONTENT -->



		<!-- MODAL Notice -->
		<div class="modal fade" id="modalNotice" tabindex="-1" role="dialog" aria-labelledby="ModalNotice" aria-hidden="true" data-keyboard="false" data-backdrop="static">
			<div class="modal-dialog" role="document" style="width:45%">
				<div class="modal-content">
					<!-- Modal content-->
					<div class="modal-header">
						<h5 class="modal-title" id="modalName" align="center">Notice to requestor</h5>
					    	<button type="button" id="closeNotice" class="close" data-dismiss="modal" aria-label="Close">
					        	<span aria-hidden="true">&times;</span>
					        </button>
					</div>
					<div class="modal-body">
						<form role="form" class="form-horizontal" id="formdataNotice" method="post">
							<fieldset class="scheduler-border">
							    <legend class="scheduler-border">Print notice to</legend>
							    	<div class="form-check">
							  
									  <label class="form-check-label">
									  		<input class="form-check-input" type="radio" name="printNotice" id="deptadd" value="dept" checked>
									    Department Address
									  </label>
									</div>
									<div class="form-check">
										<label class="form-check-label">
									  		<input class="form-check-input" type="radio" name="printNotice" id="offadd" value="offi">
									    Officer Address
									  </label>
									</div>
									<div class="form-check">
										 <label class="form-check-label">
									  		<input class="form-check-input" type="radio" name="printNotice" id="homeadd" value="home">
									    	Home Address
									  </label>
									</div>
							</fieldset>
							<div class="form-check">
								<label class="form-check-label">
							  		<input class="form-check-input" type="checkbox" value="attachlistprint" id="attachlist">
							    	Print attachment list
							  </label>
							</div>
						

								
								<div class="form-group modal-footer">
							      	<button type="button" id="Oknotice" class="btn btn-primary" data-dismiss="modal">Ok</button>
							        <button type="button" id="cancelNotice" class="btn btn-secondary" data-dismiss="modal">Cancel</button>
					      		</div>
					      	
						</form>
					</div>
					<!-- END Modal content-->
				</div>
			</div>
		</div>
		<!-- END MODAL Notice -->
		
		
</body>
</html>