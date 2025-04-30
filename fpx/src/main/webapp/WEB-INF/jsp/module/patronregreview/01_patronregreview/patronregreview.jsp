<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.sql.*" %>
<%-- <%@page import="com.ilmu.foundation.global.GlobalSQLStatement"%>
<%@page import="com.ilmu.foundation.global.Foundation"%> --%>
<%-- <%@page import="com.ilmu.foundation.PatronDetails.RetrievePatron"%> --%>
<%-- <%@ page import=" java.util.List, com.ilmu.foundation.PatronDetails.PatCriteria" %> --%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head> 



	<link href="https://cdnjs.cloudflare.com/ajax/libs/sweetalert/1.1.3/sweetalert.css" rel="stylesheet"/>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/sweetalert/1.1.3/sweetalert-dev.min.js"></script>
	
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/patronregreview.js"></script>

</head>


<style>
.modal-body {
    max-height: 100%;
    overflow-y: auto;
}

#red {
	color: red;
}

</style>

<body>
	<!-- START MAIN CONTENT -->
			<div class="box box-default">
				<div class="panel panel-default">
					<div class="panel-heading">
						<!-- <div class="clearfix"></div> -->
					</div>

					<div class="panel-body" id="display_patronReview">
					
						<form class="form-horizontal" name="PatrReview" id="PatrReview">
						

							
							<div class="form-group">
								<div class="col-sm-2">
									<label class="form-check-label">Choose Status</label>
								</div>
			
								<jsp:include page="../../../include/shared/Selection/Radio_StatusPending.jsp"></jsp:include>	
							</div>
							
							
							<div class="form-group">
								<div class="col-sm-2"><label>Choose Branch</label></div>
									<div class='col-sm-5'>
										<select class="form-control" id="branch" name="branch">
											<jsp:include page="../../../include/shared/Selection/Select_Branch.jsp"></jsp:include>
											
										</select>
								</div>
							</div> 
							
							
							<div class="btn-group pull-right ">	
								<div class="col-md-1"><button type="button" id="Reterive" class="btn btn-primary" title="Retrieve"> Retrieve</button></div>
							</div>
							
						</form>
						
						<br><br>
						<table id="reviewTable" class="table table-bordered table-striped display compact" width="100%">
						 <thead>
						 	<tr>
						 		<th>No</th>
						 		<th>Patron Id</th>
								<th>Patron Name</th>
								<th>Branch</th>
								<th>Action</th>
						 	</tr>
						 </thead>
						</table>
						

					</div>
				</div>
			</div>
		<!-- END MAIN CONTENT -->
		
		
		<!-- Edit Modal-->
	<div class="modal fade" id="editModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-keyboard="false" data-backdrop="static">
            <div class="modal-dialog" style="width:80%;overflow:auto;">
                <div class="modal-content">

		 		 <!-- Remote content load here -->
		  		</div>
			</div>
	</div>
		
		
		<!-- START MODAL ADD, EDIT, VIEW -->
		<div class="modal fade" id="modalpatronpreview" tabindex="-1" role="dialog" aria-labelledby="ModalAdd" aria-hidden="true" data-keyboard="false" data-backdrop="static">
			<div class="modal-dialog" role="document" style="width:85%">
				
				<div class="modal-content">
					<!-- Modal content-->
						<div class="modal-header">
							<h5 class="modal-title" id="modalName" align="center">form</h5>
					    	<button type="button" id="closeModalAdd" class="close" data-dismiss="modal" aria-label="Close">
					        	<span aria-hidden="true">&times;</span>
					        </button>
						</div>
						<form role="form" id="patronForm" class="form-horizontal" action="${pageContext.request.contextPath}/UpdatePatronDetails" enctype="multipart/form-data" method="post">
						<div class="modal-body" style="height:70%;overflow:auto">
							<div class="panel-body">
								
										<div class="row">
						<div class="col-md-13">
						<!-- Custom Tabs -->
							<div class="nav-tabs-custom">
								<ul class="nav nav-tabs">
									<li class="active"><a href="#1" data-toggle="tab" aria-expanded="false"><strong>Patron Details</strong></a></li>
									<li><a href="#2" data-toggle="tab"><strong>Personal</strong></a></li>
					             	<li><a href="#4" data-toggle="tab"><strong>Residence</strong></a></li>
					             	<li><a href="#6" data-toggle="tab"><strong>Office</strong></a></li>
					             	<li><a href="#8" data-toggle="tab"><strong>Prove</strong></a></li>
								</ul>
								
							<!-- TAB CONTENT -->
							<div class="tab-content">
							
							<!-- TAB ONE -->
								<div class="tab-pane active" id="1">
								<div class="box-body">
								<div class="row">
								
								<div class="col-sm-2 col-md-3">			
										
					          			
					          			<div class="fileinput fileinput-new" data-provides="fileinput">
					  						<div class="fileinput-new thumbnail" style="width: 150px; height: 150px;">
					  						 	<%-- <img src="${pageContext.request.contextPath}/resources/image/avatar.jpg" style="width:150px"> --%>
					  						 	<!-- <img src="${pageContext.servletContext.contextPath }/PatronReviewPhoto?patrid=?patronid" style="width:150px" class="picdmge"/> -->
					  						 	<img src="${pageContext.request.contextPath}/resources/image/user_default.png" id ="imgtest3" class="image--cover">
					  						 	
					    					</div>
					  						<!-- <div class="fileinput-preview fileinput-exists thumbnail" style="width:auto"></div>
					  						<div>
					    						<span class="btn btn-default btn-file"><span class="fileinput-new">
					    						<span class="glyphicon glyphicon-folder-open" aria-hidden="true"></span>   Browse image</span>
					    						<span class="fileinput-exists">Change</span>
					    						<input type="file" name="photo"></span>
					    						<a href="#" class="btn btn-default fileinput-exists" data-dismiss="fileinput">Remove</a>
					  						</div> -->
										</div>
    			
								</div>
										
								<div class="col-md-9">	
									<div class="form-group">
					                  	
					                	<label class="col-sm-2 " id="lblPatron(0)"><b>Patron ID</b></label>
										<div class="col-md-4 GL14PATR"></div>

					                </div>
					                 
					                 

					                
					          		<div class="form-group">
					                	<label class="col-sm-2" id="lblPatron(3)"><strong>Name</strong></label>
					                    <div class="col-sm-3 col-md-8 GL14NAME"></div>
					                      
					                 
					               </div>
					             		<!-- End Name Title -->	
					             		
					             		<div class="form-group">
					                      <label class="col-sm-2 control-label"></label>
					                      <div class="col-sm-5 col-md-4">
					                       <div id="nameResponse" style="color:red"></div>
					                      </div>
					                    </div>
					                    
					                  	<!-- Group Id -->
					                  <div class="form-group">
					                  <label class="col-sm-2 " id="lblPatron(5)"><strong>Group ID<span id="red">*</span></strong></label>
					                  <div class="col-sm-5 col-md-5">
					                  	
					                  	<select class="form-control" id="GL14GRID" name="GL14GRID">
											<jsp:include page="../../../include/shared/Selection/Select_GroupId.jsp"></jsp:include> 
										</select>
					                </div>
					                          
					             </div>
					             <!-- End Group Id -->
					             
					             <!-- Category -->
					             <div class="form-group">
					                  <label class="col-sm-2 " id="lblPatron(6)"><strong>Category<span id="red">*</span></strong></label>
					                  <div class="col-sm-5 col-md-5">
					                  
					                  		<select class="form-control" id="GL14CATE" name="GL14CATE">
												<jsp:include page="../../../include/shared/Selection/Select_PatronCategory.jsp"></jsp:include> 
											</select>
										
					                </div>
					             </div>
					             <!-- End Category -->
					             
					             
					              <div class="form-group">
					                      <label class="col-sm-2 ">Register Fee (RM)</label>
					                      <div class='col-sm-5 patrfee'></div>
					        	  </div>
					             
					             <!-- Patron Status -->
					                  <div class="form-group">
					                  <label class="col-sm-2 " id="lblPatron(7)"><strong>Patron Status<span id="red">*</span></strong></label>
					                  <div class="col-sm-5 col-md-5">
					                  
					                  	<select class="form-control" id="GL14STAT" name="GL14STAT">
												<jsp:include page="../../../include/shared/Selection/Select_PatronStatus.jsp"></jsp:include> 
										</select>

					                </div>
					                             
					             </div>
					             <!-- End Patron Status -->
					             
					            <!-- Registered Branch -->
					                  	<div class="form-group">
					                  	<label class="col-sm-2 " id="lblPatron(8)"><strong>Registered Branch<span id="red">*</span></strong></label>
					                  	<div class="col-sm-6 col-md-6">
					                  		
					                  		<select class="form-control" id="GL14BRNC" name="GL14BRNC">
													<jsp:include page="../../../include/shared/Selection/Select_Branch.jsp"></jsp:include> 
											</select>

					                	</div>
      
					             		</div>
					             		<!-- End Register Branch -->
					             
					             		
					                    
					             		<div class="form-group">
					                      <label class="col-sm-2 " id="lblPatron(11)"><b>Date Enrolled<span id="red">*</span></b></label>
					                      <div class='col-sm-4'>
					                      	<div class="input-group date">
												<input type="text" data-date-format="dd/mm/yyyy" class="input-sm form-control" name="GL14MEMDATE"  
													id="GL14MEMDATE" autocomplete="off"/><span class="input-group-addon">
					  							<i class="glyphicon glyphicon-calendar"></i></span>
											</div>

					        			  </div>
					        			  

					                      
					                      <label class="col-sm-2 control-label lblhide" id="lblPatron(12)"><b>Expiry Date<span id="red">*</span></b></label>
					                      
					                      <div class='col-sm-4 lblhide'>
					                      	<div class="input-group date">
					                      		<input type="text" class="form-control" id="GL14EXPDATE2" name="GL14EXPDATE2" >
					  						 	<span class="input-group-addon">
					  							<i class="glyphicon glyphicon-calendar"></i></span>
											</div>
					        			  </div>
					        
					                      
					              </div>
												
								</div><!-- /.col -->
					            </div><!-- /.row -->
							    </div><!-- /.box body -->
								</div><!-- /.End Tab 1 -->
								
								
								<!-- TAB TWO -->	 
							   <div class="tab-pane" id="2">
							   <div class="box-body">
					           <div class="row">
					                  	
					           <div class="col-sm-1 col-md-6">
					                  	
					
					             	<div class="form-group">
						             	<label class="col-sm-3">IC</label>
						                	<div class="col-sm-2 col-md-6 GL14NEWIC"></div>
					                </div>
					                
					                 <div class="form-group">
					                      <label class="col-sm-3" id="lblPatron(25)">Gender</label>
					                      
					                      <div class="col-sm-3 col-md-3"><input class="form-check-input" type="radio" name="GL14SEX" id="optGender(0)" value="M"> Male</div>
					                      <div class="col-sm-3 col-md-3"><input class="form-check-input" type="radio" name="GL14SEX" id="optGender(1)" value="F"> Female</label></div>
					                      	
								  	 	</div>
			                      
			                      <div class="form-group">
					                      <label class="col-sm-3 ">Date of Birth</label>
					                      <div class='col-sm-5 dob'></div>
					        	  </div>
					             
					             </div><!-- col -->
					             
					             <div class="col-sm-1 col-md-6">
					
					             		<div class="row">
					             		   <div class="form-group">
					                         <label class="col-sm-3 " id="emailAdd">E-Mail Address</label>
					                         <div class="col-sm-2 col-md-7 GL14IPADD"></div>
					                      </div>
					                    </div>
					             		   
   
					             
					             </div><!-- col -->
					             </div><!-- row -->
					             </div><!-- box body -->
										
								</div><!-- /.End Tab 2 -->
									
									
							
							
							<!-- TAB FOUR -->
							<div class="tab-pane" id="4">
							<div class="box-body">
				
									
								  <div class="form-group">
					                	<label class="col-sm-2"><strong>Address</strong></label>
					                    <div class="col-sm-3 col-md-8 GL14ADD1"></div>
					              </div>
					              
					              <div class="form-group">
					                	<label class="col-sm-2"><strong></strong></label>
					                    <div class="col-sm-3 col-md-8 GL14ADD2"></div>
					              </div>
					              
					              <div class="form-group">
					                	<label class="col-sm-2"><strong></strong></label>
					                    <div class="col-sm-3 col-md-8 GL14ADD3"></div>
					              </div>
					              
					              
					              <div class="form-group">
					                	<label class="col-sm-2"><strong>Postcode</strong></label>
					                    <div class="col-sm-3 col-md-3 GL14CODE"></div>
					                    
					                    <label class="col-sm-2"><strong>State/Town</strong></label>
					                    <div class="col-sm-3 col-md-3">
					                    	<select  class="form-control" id="GL14TOWN" name="GL14TOWN">
													<jsp:include page="../../../include/shared/Selection/Select_Fndcode.jsp">
														<jsp:param name="fcode" value="M"  />
													</jsp:include> 
											</select>
					                    </div>
					              </div>
					              
					              
					              <div class="form-group">
					                	<label class="col-sm-2"><strong>Home Tel. No.</strong></label>
					                    <div class="col-sm-3 col-md-3 GL14HTEL"></div>
					                    
					                    
					                    <label class="col-sm-2"><strong>Mobile No.</strong></label>
					                    <div class="col-sm-3 col-md-3 GL14MTEL"></div>
					              </div>
					              
					              
	
					                 
					           </div><!-- /.End Box Body-->
					       	   </div><!-- /.tab-pane -->
							<!-- /.End Tab 4 -->

							<!-- TAB SIX-->
							<div class="tab-pane" id="6">
					       	<div class="box-body">
					       
					       
					       			<div class="form-group">
					                	<label class="col-sm-2"><strong>Address</strong></label>
					                    <div class="col-sm-3 col-md-8 OFFADD1"></div>
					              </div>
					              
					              <div class="form-group">
					                	<label class="col-sm-2"><strong></strong></label>
					                    <div class="col-sm-3 col-md-8 OFFADD2"></div>
					              </div>
					              
					              <div class="form-group">
					                	<label class="col-sm-2"><strong></strong></label>
					                    <div class="col-sm-3 col-md-8 OFFADD3"></div>
					              </div>
					              
					              
					              <div class="form-group">
					                	<label class="col-sm-2"><strong>Postcode</strong></label>
					                    <div class="col-sm-3 col-md-3 GL14OFFCODE"></div>
					                    
					                    <label class="col-sm-2"><strong>State/Town</strong></label>
					                    <div class="col-sm-3 col-md-3">
					                    	<select  class="form-control" id="GL14OFFTOWN" name="GL14OFFTOWN">
													<jsp:include page="../../../include/shared/Selection/Select_Fndcode.jsp">
														<jsp:param name="fcode" value="M"  />
													</jsp:include> 
											</select>
					                    </div>
					              </div>
					              
					              
					              <div class="form-group">
					                	<label class="col-sm-2"><strong>Office Tel. No.</strong></label>
					                    <div class="col-sm-3 col-md-3 GL14OTEL"></div>
					                    
					                    
					                    <label class="col-sm-2"><strong>Office Fax</strong></label>
					                    <div class="col-sm-3 col-md-3 GL14FAX"></div>
					              </div>
					              
					              
					             
					       </div><!-- /.box body -->
					       </div><!-- /.tab pane -->
					       <!-- /.End Tab 6 -->
					       
					       <!-- TAB EIGHT-->
					       <div class="tab-pane" id="8">
					       	<div class="box-body">
	

					       		<br>
					       		
					       		<table id="proven" class="table table-bordered table-striped">
									<thead>
										<tr>
											<th  style="width:100%" class="filenamedesc"></th>
										</tr>
									</thead>
									<tbody>
										    <tr>
										      <td scope="row" class="fileimage">
										      	
										      	<div class="fileinput fileinput-new" data-provides="fileinput">
								  					<div class="fileinput-new thumbnail" style="width: 450px; height: 450px;">
								  						<img src="${pageContext.servletContext.contextPath }/PatronFileImage?patrid=" style="width:250px" class="picdmge2" onclick="enlargeImg(this)" />
								    				</div>
							  						<div class="fileinput-preview fileinput-exists thumbnail" style="width:auto"></div>
												</div>
										      
										      </td>
										    </tr>
									</tbody>
								</table>
					       	</div>
					       </div>
					       <!-- TAB EIGHT-->
							
							</div><!-- /.END TAB CONTENT -->
							
							</div><!-- /.END CUSTOM TAB -->
							
							</div><!-- /.END COLUMN 13 -->
							 
						 </div><!-- /.END ROW -->
						
						</div><!-- /.END PANEL BODY -->
						</div><!-- /.END MODAL BODY -->
						
						<div class="modal-footer">
							<!-- <a href="viewNote" id="viewNote" class="btn btn-primary" data-toggle="modal" data-target="#modalViewNote">Patron Note</a> -->
							<button type="button" id="approveApplication" class="btn btn-primary sendButton" >Approve</button>
					        <button type="button" id="rejectApplication" class="btn btn-danger reject" >Reject</button>
					        <button type="button" style="width: 80px" class="btn btn-default antoclose" data-dismiss="modal">Close</button>
						</div>			
						</form>
					<!-- END Modal content-->
				</div>
			</div>
		</div>
		<!-- END START MODAL ADD, EDIT, VIEW -->
		
		
</body>
</html>