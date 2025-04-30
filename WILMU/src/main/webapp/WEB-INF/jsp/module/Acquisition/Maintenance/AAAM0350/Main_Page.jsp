<%@ page contentType="text/html; charset=UTF-8" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/Acquisition/Maintenance/AAAM0350/AAAM0350.js"></script>

</head>

<body>
	<div class="panel-group">
	  <div class="panel panel-default">
	    <div class="panel-heading">
	      	<div class="form-check form-check-inline">
				<label class="form-check-label">
				    <input class="form-check-input" type="radio" name="actiontype" id="inlineRadionewreq" value="newreq" checked>New Request &nbsp;
				 </label>  
				  <label class="form-check-label">
				    <input class="form-check-input" type="radio" name="actiontype" id="inlineRadioreviewList" value="reviewList"> Review List
				  </label>
			</div>
	        <a class="accordion-toggle" data-toggle="collapse" href="#statusMaint">
	          <i class="indicator glyphicon glyphicon-chevron-down  pull-right"></i>
	        </a>
	    </div>
	    <div id="viewnewreq" class="panel-collapse collapse in">
	      	<div class="panel-body" id="statusCombine">
	      		<div class="row">
	       			<div class="col-md-12 col-lg-12">
	       				<div class="panel-body">
						<form role="form" class="form-horizontal" id="serachpurathour">
							<!-- <fieldset class="col-md-6">    	
								<legend>Search</legend> -->
								<div class="panel panel-default">
									<div class="panel-body">
										<div class="form-group">
										<div class="col-sm-3">
											<label style="padding-left:60px">
											<input type="checkbox" id="checkbox-vendorCode" name="searchMethod" value="vendor">&nbsp;Vendor </label>  
										</div>
										<div class="col-sm-4">
											<div class="input-group">
													<input type="text" class="form-control" id="input-vendorCode" name="inputVendorCode">
													<a href="Global?type=Modal&name=Modal_Vendor" id="searchvendor" class="input-group-addon btn btn-primary" data-toggle="modal" data-target="#modal_vendorSearch">
											<span class="glyphicon glyphicon-th-list"></span></a>
												</div>
												<div class='col-sm-8'><div id="div-vendorName"></div></div>
												</div>
										</div>
										
										
										<div class="form-group">
										<div class="col-sm-3">
											<label style="padding-left:60px">
											<input type="checkbox" name="searchMethod" id="checkbox-reqNo" value="orderNo">&nbsp;Request No.</label>  
										</div>
											<div class="col-sm-4">
												<input type="text" class="form-control" id="input-reqNo" name="inputreqno">
											</div>
										</div>
										
										<div class="form-group">
											<div class="col-sm-3">
											<label style="padding-left:60px">
											<input type="checkbox" name="searchMethod" id="checkbox-date" value="orderDate">&nbsp;Request Date  </label>  
										</div>
											<div class="col-sm-4">
												<div class="input-daterange input-group" id="datepicker">
													<input type="text" class="input-sm form-control"
														name="start" id="input-startDate" /> <span
														class="input-group-addon">to</span> <input type="text"
														class="input-sm form-control" name="end"
														id="input-endDate" />
												</div>
											</div>
										</div>
										
										<div class="btn-group" style="margin-left:55%">	
											<!-- <button type="button" id="button-retrievenewreq" class="btn btn-primary" title="Retrieve"><span class="glyphicon glyphicon-log-in" style="color:white"></span></button> -->
											<button type="button" id="button-retrievenewreq" class="btn btn-primary" title="Retrieve">Retrieve</button>
										</div>
										</div>
									</div>					
								
							<!-- </fieldset>		 -->
						</form>
					</div>
						<div class="panel panel-default">
							<div class="panel-heading">
								<div>	
									<button type="button" class="btn btn-primary" id="preview" data-placement="bottom" title="Preview"><span class="glyphicon glyphicon-eye-open" style="color:white"></span></button>
									<button type="button" class="btn btn-primary" id="reviewlist" data-toggle='modal' data-target="#ReviewListNumber"> 
						        		<i class="glyphicon glyphicon-hourglass" data-placement="bottom" title="Print Review List"></i>
						        	</button>
									<!-- <button type="button" class="btn btn-primary" id="reviewlist" data-placement="bottom" title="Print Review List"><span class="glyphicon glyphicon-sort-by-attributes" style="color:white"></span></button> -->
									<!-- <button class="btn btn-primary" data-toggle='modal' id="reviewlist" data-target='#ReviewListNumber' href='Modal_ReviewListNumber'><i class="glyphicon glyphicon-sort-by-attributes" title="Print Review List" data-searchfor="printpreviewlist"></i></button> -->
								</div>
							</div>
				 	<div id="search" class="panel-collapse collapse in">
						<div class="panel-body" id="cancel_orderList">
							<table id="newreqtable" class="table table-bordered table-striped" style="width:100%">
								<thead>
									<tr>
										<th><input type = "checkbox" class="checkAll" id="checkAll"></th>
										<th>Request</th>
										<th>Author/Title</th>
										<th>Quantity</th>
										<th>Requestor</th>
										<th>Request Date</th>
										<th>Estimated Price</th>
										<th>Local Price</th>
									</tr>
								 </thead>           
							</table>
						</div>
					</div>
				</div>
			</div>
	       </div>   		
	       </div>
	      	</div>
	    </div>
	    
	    <div id="viewreviewlist" class="panel-collapse collapse in">
	    	<div class="panel-body" id="statusCombine2">
	      		<div class="row">
	       			<div class="col-md-12 col-lg-12">
	       				<div class="form-group row">
							<div class="panel-body">
								<div class="panel panel-default">
									<div class="panel-body">
										<div class="form-group">
											<div class="col-sm-1"></div>
											<div class="col-sm-2">
												<label>Review List No</label>
											</div>
											<div class="col-sm-3">
												<div class="input-group">
													<input type="text" class="form-control" id="reviewListNo" name="reviewListNo">
													<a href="Acquisition?type=Maintenance&module=AAAM0350&name=Modal_SearchReviewList" id="searchreviewlistno" class="input-group-addon btn btn-primary" data-toggle="modal" data-target="#modal_reviewlistno">
													<span class="glyphicon glyphicon-th-list"></span></a>
												</div>
											</div>
											<div class="col-sm-2">
											<div class="btn-group" style="margin-left:50%;">	
											<button type="button" id="button-retrievereviewlistno" class="btn btn-primary" title="Retrieve">Retrieve</button>
										</div>
											</div>
										</div>
										
									
										
										</div>
									</div>
							</div>
						</div>
						<div class="panel panel-default">
							<div class="panel-heading">
								<div>	
									<button type="button" class="btn btn-primary" id=Reject data-placement="bottom" title="Reject"><i class="glyphicon glyphicon-scissors"></i></button>
									<button type="button" class="btn btn-primary" id="Approve" data-placement="bottom" title="Approve"><i class="glyphicon glyphicon-list-alt"></i></button>
									<button type="button" class="btn btn-primary" id="Merge" data-placement="bottom" title="Merge Approval"><i class="glyphicon glyphicon-level-up"></i></button>
									<!-- <button type="button" class="btn btn-primary" id="reviewlist" data-toggle='modal' data-target="#ReviewListNumber"> 
						        		<i class="glyphicon glyphicon-sort-by-attributes" data-placement="bottom" title="Print Review List"></i>
						        	</button> -->
						        	<i class="far fa-clipboard-check"></i>
								</div>
							</div>
				 			<div id="search" class="panel-collapse collapse in">
								<div class="panel-body" id="reviewLISTMAIN">
									<table id="review" class="table table-bordered table-striped" style="width:100%">
										<thead>
											<tr>
												<th><input type ="checkbox" class="checkAll2" id="checkAll2"></th>
												<th>Request</th>
												<th>Author/Title</th>
												<th>Quantity</th>
												<th>Requestor</th>
												<th>Request Date</th>
												<th>Estimated Price</th>
												<th>Local Price</th>
												<th>Review List No</th>
											</tr>
										 </thead>           
									</table>
								</div>
							</div>
						</div>
	       			</div>   		
	     		</div>
	   		</div>
  		</div>
	</div>

	<!-- MODAL WHEN CLICK Search button-vendorSearch -->
	<div class="modal fade" id="modal_vendorSearch" tabindex="-1" role="dialog" aria-labelledby="modal_vendorSearch" data-keyboard="false" data-backdrop="static"> 
		<div class="modal-dialog" style="width:50%;">
			<div class="modal-content" id="modal_vendorSearchContent">
				<!-- Remote content load here  -->
			</div>
		</div>
	</div>
	<!-- Modal END modal search -->

  	<!-- MODAL FOR REVIEW LIST NUMBER -->
  	<div class="modal fade" id="ReviewListNumber" tabindex="-1" role="dialog" aria-labelledby="ReviewListNumber" data-keyboard="false" data-backdrop="static">
		<div class="modal-dialog" role="document" style="width:45%;overflow:auto">
			<div class="modal-content" id="processContent">
				<!-- Remote content load here -->
				</div>
		</div>
	</div>
	<!-- END MODAL FOR REVIEW LIST NUMBER  -->
	
	<!-- MODAL WHEN CLICK Search button review list no -->
	<div class="modal fade" id="modal_reviewlistno" tabindex="-1" role="dialog" aria-labelledby="modal_reviewlistno" data-keyboard="false" data-backdrop="static"> 
		<div class="modal-dialog" style="width:75%;">
			<div class="modal-content" id="modal_reviewlistnoSearchContent">
				<!-- Remote content load here  -->
			</div>
		</div>
	</div>
	<!-- Modal END modal search button review list no -->

  

</body>
</html>