<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/enquiry/PatronEnquiry.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath() %>/plugin/moment.min.js"></script>
	<link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">
	
    <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/responsive/2.2.0/css/responsive.dataTables.min.css">
    <script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/responsive/2.2.5/js/dataTables.responsive.min.js"></script>
<!--     <script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/colreorder/1.5.2/js/dataTables.colReorder.min.js"></script>
 -->    
    <!-- <script type="text/javascript" charset="utf8" src="https://code.jquery.com/jquery-3.5.1.js"></script>
    <script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/1.10.21/js/jquery.dataTables.min.js"></script>  -->

  </head>
  
  <style>	
	.image--cover {
	  width: 120px;
	  height: 120px;
	  border-radius: 50%;
	  object-fit: cover;
	  object-position: center right;
	}
	
	input {
		text-align: left;
	}
  </style> 
  
 	<body class="hold-transition skin-blue sidebar-mini">
	<!-- MAIN CONTENT -->
    <div class="box box-default">
    	<div class="panel panel-default">
    		<div class="panel-heading">
    		</div>
    		
    		<div class="panel-body" id="display">
    			<form role="form" class="form-horizontal" id="formdatapatrEnquiry" method="post">
    				<div class="panel-body" id="patrDet">
		        		<div style="float:left">
							<img src="${pageContext.request.contextPath}/resources/image/user_default.png" id ="imgtest3" class="image--cover">
				  		</div>
				  			  		
						<div class="row">    
							
		            		<div class=" col-md-9 col-lg-10 "> 
		            			<div class="form-group">
									<label class="col-sm-2">Patron ID</label>
										<div class="col-sm-3">
											<div class="input-group">
												<input type="text" class="form-control" id="lblPatronID" name="lblPatronID">
													<a href="Modal_PatrSearch" id="searchPatronID" class="input-group-addon btn btn-primary searchheadLib" data-toggle="modal" data-target="#modal_searchPatronID">
														<span class="glyphicon glyphicon-option-horizontal"></span></a>
											</div>
										</div>
										<div class="col-sm-3 lblName"></div>
								</div>  
								
								<div class="form-group">
										<div class="col-sm-2"><label>Address</label></div>
										<div class="col-sm-8">
											<textarea class="form-control" rows="4" id="txtAddress" name="txtAddress" disabled></textarea>
										</div>
								</div>
								
								<div class="form-group">
					            	<label class="col-sm-2">Date Enrolled</label>
				                     	<div class="col-sm-3 col-md-3 lblDateEnrolled">
				                        </div>
				                      			
				                    <label class="col-sm-2">Expiry Date</label>
               							<div class="col-sm-3 col-md-3 lblExpiryDate">
               							</div>
				                </div>
								
				  				<div class="form-group">
					            	<label class="col-sm-2">Contact No</label>
				                     	<div class="col-sm-3 col-md-3 lblContactNo">
				                        </div>
				                      			
				                    <label class="col-sm-2">Email Address</label>
               							<div class="col-sm-4 col-md-4 lblIPAddress">
               							</div>
				                </div>	
								
		             		</div>
						</div>
		      		</div>
    			
    			
    				<!-- START CUSTOM TABS -->
    				
			  		
    				<div class="row">
    					<div class="col-md-12">
    						<!-- Custom Tabs -->
    						<div class="nav-tabs-custom">
    							<ul class="nav nav-tabs">
                  					<li class="active"><a href="#tab_1" data-toggle="tab"><strong>Patron Status</strong></a></li>
				                  	<li><a href="#tab_2" data-toggle="tab"><strong>Request Status</strong></a></li>
				                  	<li><a href="#tab_3" data-toggle="tab"><strong>Other Information</strong></a></li>
				                  	<li><a href="#tab_4" data-toggle="tab"><strong>Related Patron</strong></a></li>
				                  	<li><a href="#tab_5" data-toggle="tab"><strong>Miscellaneous</strong></a></li>
                				</ul>
                				
                				<div class="tab-content">
                					<div class="tab-pane active" id="tab_1">
                						<div class="box-body">
                							<div class="row">
                								<div class="form-group">
									            	<label class="col-sm-2">On-loan Items</label>
								                     	<div class="col-sm-1 col-md-1 lblOnloanItems">
								                        </div>
								                        <div class="col-sm-1 col-md-1">
								                        	<button type="button" id="ViewOnloan" class="btn btn-primary" title="View On-loan Items"><span class="glyphicon glyphicon-eye-open" style="color:white"></span></button>
								                        </div>
								                        
								                      			
								                    <label class="col-sm-2">Reservation</label>
				               							<div class="col-sm-1 col-md-1 lblReservedItems">
				               							</div>
				               							<div class="col-sm-1 col-md-1">
				               								<button type="button" id="ViewReservation" class="btn btn-primary" title="View Reservation"><span class="glyphicon glyphicon-eye-open" style="color:white"></span></button>
				               							</div>
								                </div>
								                
								                <div class="form-group">				               						
				               						<label class="col-sm-2">Overdue Items</label>
				               							<div class="col-sm-1 col-md-1 lblOverdueItems">
				               							</div>
				               							<div class="col-sm-1 col-md-1">
				               								<button type="button" id="ViewOverdue" class="btn btn-primary" title="View Overdue Items"><span class="glyphicon glyphicon-eye-open" style="color:white"></span></button>
				               							</div>
								                </div>
								                
								                <br><br>
								                
								                <div class="form-group">				               						
				               						<label for="tblHeader" class="col-sm-10"></label>
								                </div>
												<table id="PatronStatus" class="table table-bordered table-striped">
												 <thead>
												 	<tr>
												 		<th>Accession No</th>
												 		<th>Call No</th>
														<th>Title</th>
														<th>Date Due</th>
														<th>Time Due</th>
														<th>Control No</th>
												 	</tr>
												 </thead>
												</table>
												
												<table id="PatronStatusReserve" class="table table-bordered table-striped">
												 <thead>
												 	<tr>
												 		<th>Control No</th>
												 		<th>Call No</th>
														<th>Title</th>														
														<th>Status</th>
														<th>Date Reserved</th>
												 	</tr>
												 </thead>
												</table>
                							</div><!-- /.row -->
                						</div><!-- /.box -->
                					</div><!-- /.tab-pane 1 -->
                					
                					<div class="tab-pane" id="tab_2">
                						 <div class="box-body">
                						 
                						  <div class="form-group">
						                      	<div class="radio"> 
						                      		<div class="col-md-3"> 
							  							<label>
							    							<input type="radio" class="minimal" name="req" id="req" value="acqreq">
							    					 		Acquisition Request
							  							</label>
						  							</div> 
						  							<div class="col-md-3"> 
						  								<label>
						    								<input type="radio" class="minimal" name="req" id="req" value="serreq">
						    					 			Serials Request
						  								</label>
											  		</div>
											  	</div>
								            </div>
                						 	
											<br>
											<table id="RequestStatus" class="table table-bordered table-striped">
												 <thead>
												 	<tr>
												 		<th>Date Request</th>
												 		<th>Request No</th>
														<th>Tile</th>
														<th>Request Status</th>
												 	</tr>
												 </thead>   
											</table>
										
                						 </div>
                					</div><!-- /.tab-pane 2 -->
                					
                					<div class="tab-pane" id="tab_3">
                						<div class="box-body">
                							<div class="form-group">
								            	<label class="col-sm-2">Items Change To Date</label>
							                     	<div class="col-sm-3 col-md-3 lblItemsChargedTD">
							                        </div>
							                      			
							                    <label class="col-sm-2">Items Change YTD</label>
			               							<div class="col-sm-3 col-md-3 lblItemsChargedYTD">
			               							</div>
							                </div>	
							                
							                <div class="form-group">
								            	<label class="col-sm-2">Late Returns TO Date</label>
							                     	<div class="col-sm-3 col-md-3 lblLateReturnsTD">
							                        </div>
							                      			
							                    <label class="col-sm-2">Late Returnd YTD </label>
			               							<div class="col-sm-3 col-md-3 lblLateReturnsYTD">
			               							</div>
							                </div>
							                
							                <div class="form-group">
								            	<label class="col-sm-2">Last Circ Trx Date</label>
							                     	<div class="col-sm-3 col-md-3 lblLastCircTrxDate">
							                        </div>
							                      			
							                    <label class="col-sm-2">Date Last Returned</label>
			               							<div class="col-sm-3 col-md-3 lblDateLastReturned">
			               							</div>
							                </div>
							                
							                <div class="form-group">
								            	<label class="col-sm-2">No. Of Lost Items</label>
							                     	<div class="col-sm-3 col-md-3 lblNumLostItems">
							                        </div>
							                      			
							                    <label class="col-sm-2">No. Of Suppensions</label>
			               							<div class="col-sm-3 col-md-3 lblNumSuspension">
			               							</div>
							                </div>
							                
							                <div class="form-group">
								            	<label class="col-sm-2">Amount Outstanding</label>
							                     	<div class="col-sm-3 col-md-3 lblFinesOutstanding">
							                        </div>
							                      			
							                    <label class="col-sm-2">Amount Paid</label>
			               							<div class="col-sm-3 col-md-3 lblFinesPaid">
			               							</div>
							                </div>
							                
							                <div class="form-group">
								            	<label class="col-sm-2">Group Id</label>
							                     	<div class="col-sm-3 col-md-3 lblGroupID">
							                        </div>
							                      			
							                    <label class="col-sm-2">Department</label>
			               							<div class="col-sm-3 col-md-3 lblDepartment">
			               							</div>
							                </div>
							                
							                <div class="form-group">
								            	<label class="col-sm-2">Status</label>
							                     	<div class="col-sm-3 col-md-3 lblStatus">
							                        </div>
							                      			
							                    <label class="col-sm-2">Category</label>
			               							<div class="col-sm-3 col-md-3 lblCategory">
			               							</div>
							                </div>
							                
							                <div class="form-group">
								            	<label class="col-sm-2">Date Of Birth</label>
							                     	<div class="col-sm-3 col-md-3 lblDOB">
							                        </div>
							                      			
							                    <label class="col-sm-2">Gender</label>
			               							<div class="col-sm-3 col-md-3 lblGender">
			               							</div>
							                </div>
 
                						</div>
                						
                					</div><!-- /.tab-pane 3 -->
                					
                					<div class="tab-pane" id="tab_4">
                						<div class="box-body">
                						
											<div class="form-group">
								            	<label class="col-sm-2">Patron Id</label>
							                     	<div class="col-sm-2 col-md-2 lblRelatedID">
							                        </div>
							                      			
							                    <label class="col-sm-2">Name</label>
			               							<div class="col-sm-4 col-md-4 lblRelatedName">
			               							</div>
							                </div>	

								            <table id="Related" class="table table-bordered table-striped">
												 <thead>
												 	<tr>
												 		<th>No</th>
												 		<th>Patron ID</th>
														<th>Name</th>
														<th>Status</th>
														<th>Relation</th>
												 	</tr>
												 </thead>   
											</table>
								                      
								            
                						</div>
                					</div><!-- /.tab-pane 4 -->
                					<div class="tab-pane" id="tab_5">
                						<div class="box-body">
                						
											<div class="form-group">
								            	<label class="col-sm-5">Patron Miscellaneous Attributes</label>
							                </div>	

								            <table id="Miscellaneous" class="table table-bordered table-striped">
												 <thead>
												 	<tr>
												 		<th>No</th>
												 		<th>Description</th>
														<th>Value</th>
												 	</tr>
												 </thead>   
											</table>
								                      
								            
                						</div>
                					</div><!-- /.tab-pane 5 -->
                				</div><!-- nav-tabs-custom -->
    						</div><!-- /. -->
    					</div> <!-- /.col -->
    				</div><!-- /.row -->
    				
    				<!-- END TAB CONTENT -->
							<!-- <div class="modal-footer">
							
							    <button type="submit" id="save" class="btn btn-primary save">Save</button>
							    <button type="button" id="cancel" class="btn btn-secondary" data-dismiss="modal">Cancel</button>
						        
							</div>	 -->
    				<!-- END CUSTOM TABS -->
    			</form>
    		</div>
		</div>
	</div>   
	<!-- ./END MAIN CONTENT -->		
	
	<!-- MODAL WHEN CLICK HEAD LIB -->
	<div class="modal fade" id="modal_searchPatronID" tabindex="-1" role="dialog" aria-labelledby="modal_searchPatronID" data-keyboard="false" data-backdrop="static"> 
		<div class="modal-dialog" style="width:55%;">
			<div class="modal-content" id="Modal_PatrSearchContent">
				<!-- Remote content load here  -->
			</div>
		</div>
	</div>
	<!-- Modal END modal search HEAD LIB -->