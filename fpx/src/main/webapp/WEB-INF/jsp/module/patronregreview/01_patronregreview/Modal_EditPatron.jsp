
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
									<li><a href="#3" data-toggle="tab" aria-expanded="true"><strong>Professional</strong></a></li>
					             	<li><a href="#4" data-toggle="tab"><strong>Residence</strong></a></li>
					             	<li><a href="#5" data-toggle="tab" aria-expanded="true"><strong>Residence (2)</strong></a></li>
					             	<li><a href="#6" data-toggle="tab"><strong>Office</strong></a></li>
					             	<li><a href="#7" data-toggle="tab"><strong>Miscellanous</strong></a></li>
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
					  						 	<img src="${pageContext.request.contextPath}/resources/image/avatar.jpg" style="width:150px">
					    					</div>
					  						<div class="fileinput-preview fileinput-exists thumbnail" style="width:auto"></div>
					  						<div>
					    						<span class="btn btn-default btn-file"><span class="fileinput-new">
					    						<span class="glyphicon glyphicon-folder-open" aria-hidden="true"></span>   Browse image</span>
					    						<span class="fileinput-exists">Change</span>
					    						<input type="file" name="photo"></span>
					    						<a href="#" class="btn btn-default fileinput-exists" data-dismiss="fileinput">Remove</a>
					  						</div>
										</div>
										
									
										
										<div class="fileinput fileinput-new" data-provides="fileinput">
					  						<div class="fileinput-new thumbnail" style="width: auto; height: 150px;">
					  						 	<img src="${ pageContext.servletContext.contextPath }/PhotoServlet?GL14PATR= style="width:auto" />
					    					</div>
					  						<div class="fileinput-preview fileinput-exists thumbnail" style="width:auto"></div>
					  						<div>
					    						<span class="btn btn-default btn-file"><span class="fileinput-new">
					    						<span class="glyphicon glyphicon-folder-open" aria-hidden="true"></span>   Browse image</span>
					    						<span class="fileinput-exists">Change</span>
					    						<input type="file" name="photo"></span>
					    						<a href="#" class="btn btn-default fileinput-exists" data-dismiss="fileinput">Remove</a>
					  						</div>
										</div>
				
					          			
								</div>
										
								<div class="col-md-9">	
									<div class="form-group">
					                  	
					                      <label class="col-sm-2 " id="lblPatron(0)"><b>Patron ID<span id="red">*</span></b></label>
					                      <div class="col-md-4">
					                        <input type="text" class="form-control" id="GL14PATR" name="GL14PATR"  disabled>
					                     </div>
					                     <input type="hidden" id="GL14PATR2" name="GL14PATR2" class="form-control" >">
					
					                      <label class="col-sm-2 control-label" id="lblPatron(2)"><b>Password</b><span id="red">*</span></label>
					                      <div class="col-sm-5 col-md-2">
					                        <input type="password" class="form-control" id="GL14PASW" name="GL14PASW">
					                      </div>

					                    </div>
					                 
					                 <div class="form-group">
					                      <label class="col-sm-2 control-label"></label>
					                      <div class="col-sm-5 col-md-4">
					                       <div id="ajaxResponse" style="color:red"></div>
					                      </div>
					                 </div>
					                 
					                  <input type="hidden" id='loginid' name="loginid">
					                 
					                  <!-- Name Title -->
					             		<div class="form-group">
					                  	<label class="col-sm-2 " id="lblPatron(14)">Name Title</label>
					                  	<div class="col-sm-1 col-md-2">
					                  		<select class="form-control" id="plkupDetails(5)" name="GL14NAMETITLE" onchange="document.getElementById('name').selectedIndex
					                            						= document.getElementById('plkupDetails(5)').selectedIndex">
					        
					     
					                      	
					                     	<option value="">Please select </option>
					                    
					                     	
											</select>
					                	</div> 
					                	
					                		<label class="col-sm-2 " id="lblPatron(1)">Corporate ID/Parent ID</label>
					                	
					                      		<div class="col-sm-3 col-md-3">
					                        		<input type="text" class="form-control" id="txtDetails(22)" name="GL14RELID" >
					                      		</div>
					                      		
					                      		<input type="hidden" id="activateStatusview" name="activateStatusview">
					                      		<div class="form-check" id="hideshowactivate"> <!--  id="hideshowactivate" -->
					                      			<input type="checkbox" class="form-check-input" id="activate" name="activate" value="N">
					  								<input type="hidden" id="activateStatus" name="activateStatus" value="h">
					  								<label class="form-check-label" for="activate">Activate</label>
												</div>
					                	
					                	</div>
					                
					                	<div class="form-group">
					                	<label class="col-sm-2" id="lblPatron(3)"><strong>Name<span id="red">*</span></strong></label>
					                    <div class="col-sm-3 col-md-8">
					                        <input type="text" class="form-control text-uppercase" id="GL14NAME" name="GL14NAME" >
					                    </div>
					                      
					                 
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
					                  <div class="col-sm-2 col-md-2">
					                  <select class="form-control" id="GL14GRID" name="GL14GRID" onchange="document.getElementById('DESCEDIT').selectedIndex
					                            						= document.getElementById('GL14GRID').selectedIndex" >
					                  
					                     <option value=""> </option>
					           
					                      
										 
									</select>
					                </div>
					               
					                  <div class="col-sm-6 col-md-6">
					                  <select class="form-control" id="DESCEDIT" name="DESCEDIT" onchange="document.getElementById('GL14GRID').selectedIndex
					                            						= document.getElementById('DESCEDIT').selectedIndex" >
					                    
									</select>
					                </div>              
					             </div>
					             <!-- End Group Id -->
					             
					             <!-- Category -->
					                  <div class="form-group">
					                  <label class="col-sm-2 " id="lblPatron(6)"><strong>Category<span id="red">*</span></strong></label>
					                  <div class="col-sm-5 col-md-2">
					                  <select class="form-control" id="plkupDetails(1)" name="GL14CATE" onchange="document.getElementById('cateDESCEdit').selectedIndex
					                            						= document.getElementById('plkupDetails(1)').selectedIndex" >
					                     
									</select>
					                </div>
					               
					                <div class="col-sm-5 col-md-6">
					                <select class="form-control" id="cateDESCEdit" name="cateDESCEdit" onchange="document.getElementById('plkupDetails(1)').selectedIndex
					                            						= document.getElementById('cateDESCEdit').selectedIndex" >
					               
					                    
									</select>
					                </div>              
					             </div>
					             <!-- End Category -->
					             
					             <!-- Patron Status -->
					                  <div class="form-group">
					                  <label class="col-sm-2 " id="lblPatron(7)"><strong>Patron Status<span id="red">*</span></strong></label>
					                  <div class="col-sm-5 col-md-2">
					                  <select class="form-control" id="plkupDetails(2)" name="GL14STAT" onchange="document.getElementById('statDESCEdit').selectedIndex
					                            						= document.getElementById('plkupDetails(2)').selectedIndex" >
					                     
									</select>
					                </div>
					               
					                  <div class="col-sm-5 col-md-6">
					                  <select class="form-control" id="statDESCEdit" name="statDESCEdit" onchange="document.getElementById('plkupDetails(2)').selectedIndex
					                            						= document.getElementById('statDESCEdit').selectedIndex" >
					                     
									</select>
					                </div>              
					             </div>
					             <!-- End Patron Status -->
					             
					            <!-- Registered Branch -->
					                  	<div class="form-group">
					                  	<label class="col-sm-2 " id="lblPatron(8)"><strong>Registered Branch<span id="red">*</span></strong></label>
					                  	<div class="col-sm-5 col-md-2">
					                  	<select class="form-control" id="plkupDetails(3)" name="GL14BRNC" onchange="document.getElementById('regDESCEdit').selectedIndex
					                            						= document.getElementById('plkupDetails(3)').selectedIndex" required>
					                    
										  
										</select>
					                	</div>
					               
					                  	<div class="col-sm-5 col-md-6">
					                  	<select class="form-control" id="regDESCEdit" name="regDESCEdit" onchange="document.getElementById('plkupDetails(3)').selectedIndex
					                            						= document.getElementById('regDESCEdit').selectedIndex">
					                   
										  
										</select>
					                	</div>              
					             		</div>
					             		<!-- End Register Branch -->
					             
					             		
					                    
					             		<div class="form-group">
					                      <label class="col-sm-2 " id="lblPatron(11)"><b>Date Enrolled<span id="red">*</span></b></label>
					                      <div class='col-sm-4'>
					                      	<div class="input-group date">
					                      
					  						<span class="input-group-addon">
					  							<i class="glyphicon glyphicon-calendar"></i></span>
											</div>
					        			  </div>
					        			  
					                      <!-- <div class="col-sm-3 col-md-3">
					                        <input type="text" class="form-control" id="txtDetails19" name="GL14MEMDATE" >
					                      </div> -->
					                      
					                      <label class="col-sm-2 control-label" id="lblPatron(12)"><b>Expiry Date<span id="red">*</span></b></label>
					                      
					                      <div class='col-sm-4'>
					                      	<div class="input-group date" id="GL14EXPDATEDIV">
					                      
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
					                  <label class="col-sm-3" id="lblPatron(13)">Race</label>
					                  <div class="col-sm-3 col-md-3">
					                  <select class="form-control" id="plkupDetails(4)" name="GL14RACE" onchange="document.getElementById('raceDescEdit').selectedIndex
					                            						= document.getElementById('plkupDetails(4)').selectedIndex" >
					                     
									</select>
					                </div>
					               
					                  <div class="col-sm-4 col-md-4">
					                  <select class="form-control" id="raceDescEdit" name="raceDescEdit" onchange="document.getElementById('plkupDetails(4)').selectedIndex
					                            						= document.getElementById('raceDescEdit').selectedIndex" >
					                    
									</select>
					                </div>    
					                </div><!-- End Race -->
					                
					                <!-- Religion -->
					                 <div class="form-group">
					                
					                <label class="col-sm-3" id="lblPatron(16)">Religion</label>
					                  <div class="col-sm-3 col-md-3">
					                  <select class="form-control" id="plkupDetails(7)" name="GL14RELIGION" onchange="document.getElementById('religionEdit').selectedIndex
					                            						= document.getElementById('plkupDetails(7)').selectedIndex" >
					                    
									</select>
					                </div>
					               
					                  <div class="col-sm-4 col-md-4">
					                  <select class="form-control" id="religionEdit" name="religionEdit" onchange="document.getElementById('plkupDetails(7)').selectedIndex
					                            						= document.getElementById('religionEdit').selectedIndex" >
					                     
									</select>
					                </div>           
					                 
					             </div>
					             <!-- End Religion -->
					
					             	<div class="form-group">
					             	
					             	<label class="col-sm-3">New IC</label>
					                         <div class="col-sm-2 col-md-6">
					                           <input type="text" class="form-control" id="GL14NEWIC" name="GL14NEWIC"  onblur="IcLength()">
					                      </div>
					                      </div>
					             	
					                <div class="form-group">
					                      <label class="col-sm-3 "></label>
					                      <div class="col-sm-5 col-md-4">
					                       <div id="icResponse" style="color:red"></div>
					                      </div>
					                    </div>
					                      
					                <div class="form-group">
					                       <label class="col-sm-3 " id="lblPatron(24)">Old IC</label>
					                      <div class="col-sm-7 col-md-7">
					                      
					                      </div>
					                </div>
					                
					                <div class="form-group">
					                <label class="col-sm-3 " id="lblPatron(27)">IC Color</label>
					                      <div class="col-sm-5 col-md-5">
					                     
					                      </div>
					                 </div>
					                   
					                      
					             
					             </div><!-- col -->
					             
					             <div class="col-sm-1 col-md-6">
					
					             		   <div class="row">
					             		   <div class="form-group">
					                         <label class="col-sm-3 " id="emailAdd">E-Mail Address</label>
					                         <div class="col-sm-2 col-md-7">
					                           <input type="email" class="form-control" id="GL14IPADD" name="GL14IPADD">
					                         </div>
					                      </div>
					                      </div>
					             		   
					             	   
					                   <div class="row">
					                   <div class="form-group">
					                      <label class="col-sm-3 " id="lblPatron(25)">Gender</label>
					                      <input type="hidden" name="GL14SEX" >
					                      	<label class="radio-inline">
						    					<input type="radio" id="optGender(0)" name="GL14SEX" value="M">
						    					Male
					  						</label>
					  						<label class="radio-inline">
						    					<input type="radio" id="optGender(1)" name="GL14SEX" value="F">
						    					Female
					  						</label>
								  	 	</div>
								  	 	</div>
								  	 	<div class="row">
								  	 	
					                    
					                    <div class="form-group">
					                      <label class="col-sm-3 ">Date of Birth</label>
					                      <div class='col-sm-5'>
					                      
					        			  </div>
					        			  </div>
					                    </div>
					                    
					                    <div class="row">
					                    <div class="form-group">     
					                      <label class="col-sm-3 " id="lblPatron(28)">Age</label>
					                      <div class="col-sm-2 col-md-2">
					                        <input type="text" class="form-control" id="txtDetails(45)" name="GL14AGE">
					                      </div>
					                   </div> 
					                   </div>
					                   
					                   <div class="row"> 
					                    <div class="form-group" id="PassportNo">
					                      <label class="col-sm-3 " id="lblPatron(1)">Passport No</label>
					                     <div class="col-sm-3 col-md-6">
					                        <input type="text" class="form-control" id="txtDetails(22)" name="">
					                      </div>
					                 	</div>
					                 	</div>
					                 	
					                 	<div class="row">
					                 	<div class="form-group">
					                      <label class="col-sm-3" id="lblPatron(23)">Deposit</label>
					                       <div class="col-sm-2 col-md-4">
					                      
					                      </div>
					                    </div>
					                    </div>
					                    
					                    <div class="row">
					                    <div class="form-group">
					                      <label class="col-sm-3" id="lblPatron(20)">Annually Membership Fee</label>
					                      <div class="col-sm-3 col-md-4">
					                    
					                      </div>
					                    </div>
					                    </div>
					                      
					                    
					                    
					                    <div class="row">
					                    <div class="form-group">
					                      <label class="col-sm-3" id="lblPatron(26)">Receipt No.</label>
					                      <div class="col-sm-2 col-md-4">
					                      
					                      </div>
					                    </div>
					                    </div>
					                    
					             
					             </div><!-- col -->
					             </div><!-- row -->
					             </div><!-- box body -->
										
								</div><!-- /.End Tab 2 -->
									
									
							<!-- TAB THREE -->	
								 <div class="tab-pane" id="3">
								 
								 <div class="box-body">
					       
					       				<div class="form-group">
					                      <label class="col-sm-2 " id="lblPatron(29)">Employee No.</label>
					                      <div class="col-sm-1 col-md-2">
					                   
					                      </div>
					                      
					                      
					                      <label class="col-sm-2 " id="lblPatron(30)">Date Employed</label>
					                      <div class="col-sm-1 col-md-2">
					                      
					        			  </div>
					
					                      <label class="col-sm-2 " id="lblPatron(31)">Company No.</label>
					                      <div class="col-sm-1 col-md-2">
					                     
					                      </div>
					                    </div>
					                  
					             <!-- Designation -->
					                  <div class="form-group">
					                  <label class="col-sm-2 " id="lblPatron(32)">Designation</label>
					                  <div class="col-sm-5 col-md-2">
					                   <select class="form-control" id="plkupDetails(8)" name="GL14DESC" onchange="document.getElementById('designationEdit').selectedIndex
					                            						= document.getElementById('plkupDetails(8)').selectedIndex" >
					                 
									</select>
					                </div>
					               
					                  <div class="col-sm-5 col-md-4">
					                  <select class="form-control" id="designationEdit" name="designationEdit" onchange="document.getElementById('plkupDetails(8)').selectedIndex
					                            						= document.getElementById('designationEdit').selectedIndex" >
					                    
									</select>
					                 
					                </div> 
					                
					                <label class="col-sm-2 " id="lblPatron(33)">Supervisor</label>
					                      <div class="col-sm-2 col-md-2">
					                      
										  </div>      
					             </div>
					             <!-- End Designation -->
					             
					             <!-- Department -->
					                  <div class="form-group">
					                  <label class="col-sm-2 " id="lblPatron(34)">Department</label>
					                  <div class="col-sm-5 col-md-2">
					                  <select class="form-control" id="plkupDetails(9)" name="GL14DEPT" onchange="document.getElementById('deptEdit').selectedIndex
					                            						= document.getElementById('plkupDetails(9)').selectedIndex" >
					                    
									</select>
					                </div>
					               
					                  <div class="col-sm-5 col-md-4">
					                  <select class="form-control" id="deptEdit" name="deptEdit" onchange="document.getElementById('plkupDetails(9)').selectedIndex
					                            						= document.getElementById('deptEdit').selectedIndex" >
					                     
									</select>
					                </div> 
					                
					             </div>
					             <!-- End Department -->
					             
					             <!-- Start Course -->
					             <div class="form-group">
					               <label class="col-sm-2" id="lblPatron(15)">Course</label>
					                  <div class="col-sm-1 col-md-2">
					                  <select class="form-control" id="plkupDetails(6)" name="GL14COURSE" onchange="document.getElementById('courseDescEdit').selectedIndex
					                            						= document.getElementById('plkupDetails(6)').selectedIndex" >
					                      
									</select>
					                </div>
					               
					                  <div class="col-sm-2 col-md-4">
					                  <select class="form-control" id="courseDescEdit" name="courseDescEdit" onchange="document.getElementById('plkupDetails(6)').selectedIndex
					                            						= document.getElementById('courseDescEdit').selectedIndex" >
					                    
										 
									</select>
					                </div>   
					                
					                <label class="col-sm-2 " id="lblPatron(35)">Level</label>
					                      <div class="col-sm-2 col-md-2">
					                     
										  </div>        
					                </div>
					                <!-- End Course -->
					             
					           <div class="form-group">
					               <label class="col-sm-2 " id="lblPatron(36)">Remarks</label>
					               <div class="col-sm-2 col-md-10">
					               
										  </div>
					            </div> 
					       
					       		</div><!-- /.BOX BODY -->
									
							</div>
							<!-- /.End Tab 3 -->
							
							<!-- TAB FOUR -->
							<div class="tab-pane" id="4">
							<div class="box-body">
					       				<input type="hidden" id="GL14MAILFLAG" name="GL14MAILFLAG" class="form-control" onblur="getGL14MAILFLAG()">
					       				<div class="form-group">
					                      <label class="col-sm-2 control-label" id="lblPatron(18)">Mailing Address</label>
					                      <div class="col-sm-1 col-md-10">
					                        <input type="checkbox" class="minimal" name="chkMailFlag(0)" id="GL14MAILFLAG" value="R">
					                      </div>
					                    </div>
					       
					       				<div class="form-group">
					                      <label class="col-sm-2 control-label" id="lblPatron(37)">Address</label>
					                      <div class="col-sm-1 col-md-9">
					                     
					                      </div>
					                    </div> 
					                    <div class="form-group">
					                      <label class="col-sm-2 control-label"></label>
					                      <div class="col-sm-1 col-md-9">
					                     
					                       </div>
					                     </div>
					                     <div class="form-group"> 
					                      <label class="col-sm-2 control-label"></label>
					                      <div class="col-sm-1 col-md-9">
					                     
					                      </div>
					                    </div>
					                    
					                    <div class="form-group"> 
					                      <label class="col-sm-2 control-label" id="lblPatron(38)">Postcode</label>
					                      <div class="col-sm-1 col-md-2">
					                      
					                      </div>
					                    
					                  
					             <!-- State/Town -->
					                  <div class="form-group">
					                  <label class="col-sm-1 control-label" id="lblPatron(39)">State/Town</label>
					                  <div class="col-sm-2 col-md-2">
					                  <select class="form-control" id="plkupDetails(10)" name="GL14TOWN" onchange="document.getElementById('townDescEdit').selectedIndex
					                            						= document.getElementById('plkupDetails(10)').selectedIndex" >
					                      
									</select>
					                </div>
					               
					                  <div class="col-sm-5 col-md-4">
					                  <select class="form-control" id="townDescEdit" name="townDescEdit" onchange="document.getElementById('plkupDetails(10)').selectedIndex
					                            						= document.getElementById('townDescEdit').selectedIndex" >
					                  
									</select>
					                </div> 
					                     
					             </div>
					             <!-- End State/Town -->
					             </div>
					             
					             		<div class="form-group"> 
					                      <label class="col-sm-2 control-label" id="lblPatron(40)">Home Tel. No.</label>
					                      <div class="col-sm-1 col-md-2">
					                     
										  </div>
					                    
					                      <label class="col-sm-2 control-label" id="lblPatron(42)">Mobile No.</label>
					                      <div class="col-md-2">
					                    
										  </div>
					                    </div>
					           </div><!-- /.End Box Body-->
					       	   </div><!-- /.tab-pane -->
							<!-- /.End Tab 4 -->
							
							<!-- TAB FIVE -->
							<div class="tab-pane" id="5">
							<div class="box-body">
					       
					       				<div class="form-group">
					                      <label class="col-sm-2 control-label" id="lblPatron(54)">Mailing Address</label>
					                      <div class="col-sm-1 col-md-10">
					                        <input type="checkbox" class="minimal" name="chkMailFlag(2)" id="GL14MAILFLAG" value="S">
					                      </div>
					                    </div>
					       
					       				<div class="form-group">
					                      <label class="col-sm-2 control-label" id="lblPatron(43)">Address</label>
					                      <div class="col-sm-1 col-md-9">
					                     
										  </div>
					                    </div> 
					                    <div class="form-group">
					                      <label class="col-sm-2 control-label"></label>
					                      <div class="col-sm-1 col-md-9">
					                     
										  </div>
					                     </div>
					                     <div class="form-group"> 
					                      <label class="col-sm-2 control-label"></label>
					                      <div class="col-sm-1 col-md-9">
					                      
										  </div>
					                    </div>
					                    
					                    <div class="form-group"> 
					                      <label class="col-sm-2 control-label" id="lblPatron(44)">Postcode</label>
					                      <div class="col-sm-1 col-md-2">
					                    
										  </div>
					                    
					                  
					             <!-- State/Town -->
					                  <div class="form-group">
					                  <label class="col-sm-1 control-label" id="lblPatron(45)">State/Town</label>
					                  <div class="col-sm-2 col-md-2">
					                  <select class="form-control" id="plkupDetails(11)" name="GL14TOWN2" onchange="document.getElementById('town2Edit').selectedIndex
					                            						= document.getElementById('plkupDetails(11)').selectedIndex" >
					                      
									</select>
					                </div>
					               
					                  <div class="col-sm-5 col-md-4">
					                  <select class="form-control" id="town2Edit" name="town2Edit" onchange="document.getElementById('plkupDetails(11)').selectedIndex
					                            						= document.getElementById('town2Edit').selectedIndex" >
					                     
									</select>
					                </div> 
					                     
					             </div>
					             <!-- End State/Town -->
					             </div>
					             
					             		<div class="form-group"> 
					                      <label class="col-sm-2 control-label" id="lblPatron(46)">Home Tel. No.</label>
					                      <div class="col-sm-1 col-md-2">
					                     
										  </div>
					                    </div>
					             
					       	</div><!-- /.box body-->
							</div><!-- /.tab-pane -->
							<!-- /.End Tab 5 -->
							
							
							<!-- TAB SIX-->
							<div class="tab-pane" id="6">
					       	<div class="box-body">
					       
					       				<div class="form-group">
					                      <label class="col-sm-2 control-label" id="lblPatron(55)">Mailing Address</label>
					                      <div class="col-sm-1 col-md-10">
					                        <input type="checkbox" class="minimal" name="chkMailFlag(1)" id="GL14MAILFLAG" value="O">
					                      </div>
					                    </div>
					       
					       				<div class="form-group">
					                      <label class="col-sm-2 control-label" id="lblPatron(47)">Address</label>
					                      <div class="col-sm-1 col-md-9">
					                      
										  </div>
					                    </div> 
					                    <div class="form-group">
					                      <label class="col-sm-2 control-label"></label>
					                      <div class="col-sm-1 col-md-9">
					                      
										  </div>
					                     </div>
					                     <div class="form-group"> 
					                      <label class="col-sm-2 control-label"></label>
					                      <div class="col-sm-1 col-md-9">
					                      
										  </div>
					                    </div>
					                    
					                    <div class="form-group"> 
					                      <label class="col-sm-2 control-label" id="lblPatron(48)">Postcode</label>
					                      <div class="col-sm-1 col-md-2">
					                      
										   </div>
					                    
					                  
					             <!-- State/Town -->
					                  <div class="form-group">
					                  <label class="col-sm-1 control-label" id="lblPatron(49)">State/Town</label>
					                  <div class="col-sm-2 col-md-2">
					                  <select class="form-control" id="plkupDetails(12)" name="GL14OFFTOWN" onchange="document.getElementById('offTownEdit').selectedIndex
					                            						= document.getElementById('plkupDetails(12)').selectedIndex" >
					                   
									</select>
					                </div>
					               
					                  <div class="col-sm-5 col-md-4">
					                  <select class="form-control" id="offTownEdit" name="offTownEdit" onchange="document.getElementById('plkupDetails(12)').selectedIndex
					                            						= document.getElementById('offTownEdit').selectedIndex" >
					                     
									</select>
					                </div> 
					                     
					             </div>
					             <!-- End State/Town -->
					             </div>
					             
					             		<div class="form-group"> 
					                      <label class="col-sm-2 control-label" id="lblPatron(50)">Office Tel. No.</label>
					                      <div class="col-sm-1 col-md-2">
					                      
										  </div>
					             		 
					                      <label class="col-sm-1 control-label" id="lblPatron(51)">Office Fax</label>
					                      <div class="col-md-2">
					                     
										  
										   
										  </div>
					                    </div>
					             
					       </div><!-- /.box body -->
					       </div><!-- /.tab pane -->
					       <!-- /.End Tab 6 -->
					       
					       <!-- TAB SEVEN-->
					       <div class="tab-pane" id="7">
					       	<div class="box-body">
					       		Patron Miscellaneous Attributes
					       		<input type="hidden" class="form-control" id="countrowmiscellaneousedit" name="countrowmiscellaneousedit">
					       		<br>
					       		
					       		<table id="miscellaneousedit" class="table table-bordered table-striped">
									<thead>
										<tr>
											<th>No</th>
											<th>Description</th>
											<th>Value</th>
										</tr>
									</thead>
								</table>
					       	</div>
					       </div>
					       <!-- TAB SEVEN-->
							
							</div><!-- /.END TAB CONTENT -->
							
							</div><!-- /.END CUSTOM TAB -->
							
							</div><!-- /.END COLUMN 13 -->
							 
						 </div><!-- /.END ROW -->
						
						</div><!-- /.END PANEL BODY -->
						</div><!-- /.END MODAL BODY -->
						
						<div class="modal-footer">
							<!-- <a href="viewNote" id="viewNote" class="btn btn-primary" data-toggle="modal" data-target="#modalViewNote">Patron Note</a> -->
							<button type="submit" id="update" class="btn btn-primary sendButton" >Save</button>
							 <a href="template?MODULE=Foundation/10_PatronDetails&ACTION=PatronTable.jsp">
					            <button type="button" style="width: 80px" class="btn btn-default antoclose" data-dismiss="modal">Cancel</button>
					         </a>
						</div>			
						</form>
					<!-- END Modal content-->
