<%@ page contentType="text/html; charset=UTF-8" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head> 

	<script type="text/javascript" src="${pageContext.request.contextPath}/js/Foundation/globalFndReportAndListing.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/Foundation/Listing/AAGL1850.js"></script>

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
						
								
						
								<div class="form-group">
									<div class="col-sm-2 col-md-2 patronCategory">
									<label class="form-check-label">
										<input class="form-check-input" type="checkbox" name="chkBoxSearchCateria" id="chkBoxSearchCateria" value="dateChecked">
										Date Range
									</label>
								</div>
								
								<div class="form-group">
									<div class="col-sm-3 col-md-3">
										<label class="form-check-label">
											<input class="form-check-input" type="radio" name="dateSelection" id="dateSelection" value="MEMDATE" data-name="Membership Date Range" checked>
											Membership Date  Range
										</label>
									</div>
									<div class="col-sm-3 col-md-3">
										<label class="form-check-label dateselectioinlabel">
											<input class="form-check-input" type="radio" name="dateSelection" id="dateSelection" value="EXPDATE" data-name="Expiry Date Range">
											Expiry Date Range
										</label>
									</div>
								</div>
							
								<!-- <div class="col-sm-2 col-md-2">
									<label class="form-check-label">
											<input class="form-check-input" type="radio" name="chkDate" id="chkBoxLoan" value="M">
											Membership 
										</label>
									</div>
									<div class="col-sm-2 col-md-2">
										<label class="form-check-label">
											<input class="form-check-input" type="radio" name="chkDate" id="chkBoxReturn" value="E">
											Expiry 
										</label>
									</div>-->
								</div> 
								
								
						
							<div class="form-group dateDiv">
								<div class="col-sm-2 col-md-2 dateLabel"></div>
									<div class="col-sm-4">
										<div class="input-daterange input-group">
											<input type="text" class="input-sm form-control"  name="startDate"
												id="input-startDate" autocomplete="off"/> <span class="input-group-addon">to</span>
											<input type="text" class="input-sm form-control" name="endDate" id="input-endDate" autocomplete="off"/>
										</div>
									</div>
							</div>
							
							<input type="hidden" class="patrSelection" value = "m">
							<jsp:include page="../../../../module/PatronID.jsp"></jsp:include> 

							<div class="form-group branchDiv">
								<div class="col-sm-2 col-md-2"><label><input class="form-check-input" type="checkbox" name="chkBoxSearchCateria2" id="chkBoxSearchCateria2" value="branchChecked">
									Branch</label></div>
									<div class='col-sm-6'>
										<div class="form-check">
											<select id="branch" multiple="multiple" name="branch">
												<jsp:include page="../../../../module/Select_Branch.jsp"></jsp:include> 
											</select>
										</div>
									</div>	
							</div>
							
							<div class="form-group patroncateDiv">
								<div class="col-sm-2 col-md-2"><label class="form-check-label">
									<input class="form-check-input" type="checkbox" name="chkBoxSearchCateria2" id="chkBoxSearchCateria2" value="pateCate">
									Patron Category
								</label></div>
									<div class='col-sm-6'>
										<div class="form-check">
											<select id="patronCate" multiple="multiple" name="patronCate">
												<jsp:include page="../../../../module/Select_PatronCategory.jsp"></jsp:include> 
											</select>
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
						 		<th>Patron Id</th>
								<th>Name</th>
								<th>Abbrevation</th>
								<th>Category</th>
								<th>Related Id</th>
								<th>Home Address</th>
								<th>Home Postcode</th>								
								<th>Department</th>
								<th>Course Code</th>
								<th>IC/Passport No</th>
								<th>Designation</th>
								<th>Branch</th>
								<th>Expiry Date</th>
								<th>User Status</th>
								<th>Office Tel No</th>
								<th>Membership Date</th>								
								<th>Deposit</th>
						 	</tr>
						 </thead>
						</table>
					</div>
				</div>
			</div>
		<!-- END MAIN CONTENT -->
		
		
</body>
</html>