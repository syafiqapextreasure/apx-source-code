<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.util.*"%>
<%@ page import="java.sql.*" %>
<%@page import="com.ilmu.foundation.global.GlobalSQLStatement"%>
<%@page import="com.ilmu.foundation.global.Foundation"%>


<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/Foundation/libInformation.js"></script>
    <script type="text/javascript" src="<%= request.getContextPath() %>/plugin/moment.min.js"></script>

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
    			<form role="form" class="form-horizontal" id="formdatalibInfo" method="post">
    				<!-- START CUSTOM TABS -->
    				<div class="row">
    					<div class="col-md-12">
    						<!-- Custom Tabs -->
    						<div class="nav-tabs-custom">
    							<ul class="nav nav-tabs">
                  					<li class="active"><a href="#tab_1" data-toggle="tab"><strong>Library Information</strong></a></li>
				                  	<li><a href="#tab_2" data-toggle="tab"><strong>Acquisition</strong></a></li>
				                  	<li><a href="#tab_3" data-toggle="tab"><strong>Cataloging</strong></a></li>
				                  	<li><a href="#tab_4" data-toggle="tab"><strong>Circulation</strong></a></li>
				                  	<li><a href="#tab_5" data-toggle="tab"><strong>Infotrack</strong></a></li>
				                  	<li><a href="#tab_6" data-toggle="tab"><strong>Serial</strong></a></li>
				                  	<li><a href="#tab_7" data-toggle="tab"><strong>IRS</strong></a></li>
				                  	<li><a href="#tab_8" data-toggle="tab"><strong>Fund Accounting</strong></a></li>
									<li class="pull-right"><a href="#" class="text-muted"><i class="fa fa-gear"></i></a></li>
                				</ul>
                				
                				<div class="tab-content">
                					<div class="tab-pane active" id="tab_1">
                						<div class="box-body">
                							<div class="row">
                								<div class="col-sm-2 col-md-2">
				                					<div class="fileinput fileinput-new" data-provides="fileinput">
								  						<div class="fileinput-new thumbnail" style="width: 150px; height: 150px;">
								    						<img src="${pageContext.request.contextPath}/resources/image/avatar.jpg" style="width:150px">
								  						</div>
								  						<div class="fileinput-preview fileinput-exists thumbnail" style="width:auto"></div>
								  						<div>
								    						<span class="btn btn-default btn-file"><span class="fileinput-new">
								    						<span class="glyphicon glyphicon-folder-open" aria-hidden="true" ></span>   Browse image</span>
								    						<span class="fileinput-exists">Change</span>
								    						<input type="file" name="photo" id="logoLib"></span>
								    						<a href="#" class="btn btn-default fileinput-exists" data-dismiss="fileinput">Remove</a>
								  						</div>
													</div>
				                				</div>
				                				
				                				 <input type="hidden" class="form-control" id="action">
				                				 
				                				<div class="col-md-9">
						  							<div class="form-group">
				                      					<label class="col-sm-2">Library Name</label>
									                    <div class="col-sm-3 col-md-7">
									                        <input type="text" class="form-control" id="libName" name="libName">
									                    </div>
				                    				</div>	
				                    				<div class="form-group">
				                      					<label class="col-sm-2">Organization Name</label>
				                      						<div class="col-sm-3 col-md-7">
				                        						<input type="text" class="form-control" id="orgName" name="orgName">
				                      						</div>
				                    				</div> 
				                    				<div class="form-group">
				                      					<label class="col-sm-2"><b>Library Symbol</b></label>
				                      						<div class="col-sm-3 col-md-2">
				                        						<input type="text" class="form-control" id="libSymbol" name="libSymbol">
				                      						</div>
				                    				</div>
				                    				<div class="form-group">
				                  						<label class="col-sm-2">Branch Code</label>
				                  						<div class="col-sm-5 col-md-5">
				                  							<select class="form-control" id="brncCode" name="brncCode">
																<jsp:include page="../Select_BranchCode.jsp"></jsp:include>
															</select>
				                						</div>              
				             					    </div>
				             					    <div class="form-group">
				                 				   		<label class="col-sm-2">Address</label>
				                 						<div class="col-sm-3 col-md-7">
				                    						<input type="text" class="form-control" id="add1" name="add1">
				             	 						</div>
				             					   	</div>
				             					   	<div class="form-group">
				                 						<label class="col-sm-2"></label>
				                 						<div class="col-sm-3 col-md-7">
				                    						<input type="text" class="form-control" id="add2" name="add2">
				             	 						</div>
				             					   	</div>
				             					   	<div class="form-group">
				                 						<label class="col-sm-2"></label>
				                 						<div class="col-sm-3 col-md-7">
				                    						<input type="text" class="form-control" id="add3" name="add3">
				             	 						</div>
				             					  	</div>
				             					  	<div class="form-group">
				                      					<label class="col-sm-2">Postcode</label>
				                      					<div class="col-sm-2 col-md-2">
				                        					<input type="text" class="form-control" id="postcode" name="postcode">
				                      					</div>
				                      
				                      					<label class="col-sm-1">Town</label>
				                      					<div class="col-sm-4 col-md-4">
				                        					<select class="form-control" id="town" name="town">
																<jsp:include page="../Select_Town.jsp"></jsp:include>
															</select>
				                      					</div>
				                     			  	</div>
				                     			  	<div class="form-group">
				                      					<label class="col-sm-2">Telephone</label>
				                      					<div class="col-sm-3 col-md-3">
				                        					<input type="text" class="form-control" id="tel" name="tel">
				                      					</div>
				                      
				                      					<label class="col-sm-1">Fax</label>
				                      					<div class="col-sm-3 col-md-3">
				                        					<input type="text" class="form-control" id="fax" name="fax">
				                      					</div>
				                     			  	</div>
				                     			  	<div class="form-group">
												  		<label class="col-sm-2">Head Librarian</label>
														<div class="col-sm-3">
															<div class="input-group">
																<input type="text" class="form-control" id="headLib" name="headLib">
																	<a href="Modal_PatrSearch?textid=headlib" id="searchheadLib" class="input-group-addon btn btn-primary searchheadLib" data-toggle="modal" data-target="#modal_searchheadLib">
																	<span class="glyphicon glyphicon-option-horizontal"></span></a>
															</div>
														</div>
														<div class="col-sm-4 div-headLibname"></div>
													
													 	<label class="col-sm-1 control-label">Tel. Ext</label>
									                		<div class="col-sm-2 col-md-2">
									                    		<input type="text" class="form-control" id="headLibExt" name="headLibExt" >
									             	 		</div>
													</div>
													<div class="form-group">
													  	<label class="col-sm-2">PR Officer</label>
														<div class="col-sm-3">
															<div class="input-group">
																<input type="text" class="form-control" id="profficer" name="profficer">
																	<a href="Modal_PatrSearch?textid=profficer" id="searchprofficer" class="input-group-addon btn btn-primary searchprofficer" data-toggle="modal" data-target="#modal_searchprofficer">
																	<span class="glyphicon glyphicon-option-horizontal"></span></a>
															</div>
														</div>
														<div class="col-sm-4 div-profficername"></div>
														
														 <label class="col-sm-1 control-label">Tel. Ext</label>
										                 <div class="col-sm-2 col-md-2">
										                    <input type="text" class="form-control" id="profficerExt" name="profficerExt" >
										             	 </div>
													</div> 
													
													<!-- <div class="form-group">
									                 	<label class="col-sm-2">Library Logo</label>
									                 	<div class="col-sm-3 col-md-2">
									                    	<input type="text" class="form-control" id="logo" name="logo">
									             	 	</div>
									             	</div> -->
									             	
						  						</div>
                							</div><!-- /.row -->
                						</div><!-- /.box -->
                					</div><!-- /.tab-pane 1 -->
                					
                					<div class="tab-pane" id="tab_2">
                						 <div class="box-body">
                						 	<div class="form-group">
											  	<label class="col-sm-2">Acquisition Head</label>
												<div class="col-sm-3">
													<div class="input-group">
														<input type="text" class="form-control" id="acqhead" name="acqhead">
															<a href="Modal_PatrSearch?textid=acqhead" id="searchacqhead" class="input-group-addon btn btn-primary searchacqhead" data-toggle="modal" data-target="#modal_searchacqhead">
															<span class="glyphicon glyphicon-option-horizontal"></span></a>
													</div>
												</div>
												<div class="col-sm-3 div-acqheadname"></div>
												
												 <label class="col-sm-1 control-label">Tel. Ext</label>
								                 <div class="col-sm-2 col-md-2">
								                    <input type="text" class="form-control" id="acqext" name="acqext" >
								             	 </div>
											</div> 
											
											<div class="form-group">
						                    	<label class="col-sm-1">1st Grace Period</label>
						                      	<div class="col-sm-1 col-md-1">
						                        	<input type="text" class="form-control" id="gp1" name="gp1">
						                      	</div>
						                      
						                      	<label class="col-sm-1">2nd Grace Period</label>
						                     	<div class="col-sm-1 col-md-1">
						                        	<input type="text" class="form-control" id="gp2" name="gp2">
						                      	</div>
						                      	
						                      	<label class="col-sm-1">3rd Grace Period</label>
							                  	<div class="col-sm-1 col-md-1">
							                    	<input type="text" class="form-control" id="gp3" name="gp3">
							                   	</div>
							                   	
							                   	<label class="col-sm-1">Order Span</label>
							                      	<div class="input-group col-sm-2 col-md-2">
							                        	<input type="text" class="form-control" id="orderspan" name="orderspan">
							                      		<span class="input-group-addon">Day</span>
							                      	</div>
						                     	</div> 
						                     	
						                     	<div class="form-group">
							                    	<label class="col-sm-2">Batch Order Printing</label>
							                      		<div class="col-sm-1 col-md-10">
							                        		<input type="checkbox" class="minimal" name="acqprint" id="acqprint" >
							                      		</div>
							                    </div>
                						 </div>
                					</div><!-- /.tab-pane 2 -->
                					
                					<div class="tab-pane" id="tab_3">
                						<div class="box-body">
                							<div class="form-group">
											  	<label class="col-sm-2">Catalog Head</label>
												<div class="col-sm-3">
													<div class="input-group">
														<input type="text" class="form-control" id="cathead" name="cathead">
															<a href="Modal_PatrSearch?textid=cathead" id="catheaad" class="input-group-addon btn btn-primary searchcathead" data-toggle="modal" data-target="#modal_searchcathead">
															<span class="glyphicon glyphicon-option-horizontal"></span></a>
													</div>
												</div>
												<div class="col-sm-3 div-catheadname"></div>
												
												 <label class="col-sm-1 control-label">Tel. Ext</label>
								                 <div class="col-sm-2 col-md-2">
								                    <input type="text" class="form-control" id="catext" name="catext" >
								             	 </div>
											</div> 
											<div class="form-group">
					                    	<label class="col-sm-2">Base MARC Type</label>
				                      			<div class="col-sm-3 col-md-3">
				                        			<select class="form-control" id="marctype" name="marctype">
														<jsp:include page="../Select_marctype.jsp"></jsp:include>
													</select>
				                      			</div>
				                      			
				                      		<label class="col-sm-2">Indexing Threshold Time</label>
               									<div class="col-sm-2 col-md-2">
                        							<input type="text" class="form-control" id="itt" name="itt">
               									</div>
				                     		</div>
                						</div>
                						
                					</div><!-- /.tab-pane 3 -->
                					
                					<div class="tab-pane" id="tab_4">
                						<div class="box-body">
                							<div class="form-group">
											  	<label class="col-sm-2">Circ. Head</label>
												<div class="col-sm-3">
													<div class="input-group">
														<input type="text" class="form-control" id="cirhead" name="cirhead">
															<a href="Modal_PatrSearch?textid=cirhead" id="cirhead" class="input-group-addon btn btn-primary searchcirhead" data-toggle="modal" data-target="#modal_searchcirhead">
															<span class="glyphicon glyphicon-option-horizontal"></span></a>
													</div>
												</div>
												<div class="col-sm-3 div-cirheadname"></div>
												
												 <label class="col-sm-1 control-label">Tel. Ext</label>
								                 <div class="col-sm-2 col-md-2">
								                    <input type="text" class="form-control" id="cirext" name="cirext" >
								             	 </div>
											</div>
											<div class="form-group">
											    <label class="col-sm-2">Library Weekends</label>
											    <div class="col-sm-3">
												    <select class="form-control" id="libweekend">
												      	<option value="Sunday">Sunday</option>
								  						<option value="Monday">Monday</option>
								  						<option value="Tuesday">Tuesday</option>
								  						<option value="Wednesday">Wednesday</option>
								  						<option value="Thursday">Thursday</option>
								  						<option value="Friday">Friday</option>
								  						<option value="Saturday">Saturday</option>
												    </select>
											    </div>
											    <label class="col-sm-3">Reservation Time Frame</label>
                      								<div class="input-group col-sm-2 col-md-2">
                        								<input type="text" class="form-control" id="resvtime" name="resvtime">
                      									<span class="input-group-addon">Day(s)</span>
                      								</div>
											</div>
											
											<div class="row">
												<label class="col-sm-3">Default Replacement Cost for Lost Book :</label>
											    <div class="form-group col-sm-2">
											        <div class="input-group"> 
											        	<span class="input-group-addon">RM</span>
														<input type="text" class="form-control" id="rcost" name="rcost">
											        </div>
											    </div>
											    <label class="col-sm-1"></label>
											    
											    <div class="form-group col-sm-5">
											        <div class="input-group"> 
											            <input type="text" class="form-control" id="timecost" name="timecost">
											            <span class="input-group-addon">times Cost of Lost Book (as Default)</span>
											        </div>
											    </div>
											</div>
											

								            
								            <div class="form-group">
								            	<label class="col-sm-2 ">Due Date Calculation</label>
						                      	<div class="radio"> 
						                      		<div class="col-md-3"> 
							  							<label>
							    							<input type="radio" class="minimal" name="circmode" id="circmode" value="I">
							    					 		Inclusive of Holidays
							  							</label>
						  							</div> 
						  							<div class="col-md-3"> 
						  								<label>
						    								<input type="radio" class="minimal" name="circmode" id="circmode" value="E">
						    					 			Exclusive of Holidays
						  								</label>
											  		</div>
											  	</div>
								            </div>
								            
                						</div>
                					</div><!-- /.tab-pane 4 -->
                					
                					<div class="tab-pane" id="tab_5">
                						<div class="box-body">
                							<div class="row">
                								<div class="col-sm-5 col-md-5">
                									<div class="panel-group">
								    				<div class="panel panel-default">
								      				<div class="panel-heading">
													<h3 class="panel-title">OPAC Retrieval Limit</h3>
													</div>
													<div class="panel-body">
												
								      					<div class="form-group">
								                      		<label class="col-sm-5">OPAC Retrieval Limit</label>
								                      			<div class="col-sm-5 col-md-3">
								                        			<input type="text" class="form-control" id="opaclimit" name="opaclimit">
								                      			</div>
								                    	</div>
								                    	
								                    	<div class="form-group">
								                    		<div class="radio"> 
								                      			<div class="col-md-7"> 
								  								<label>
								    								<input type="radio" class="minimal" name="loadAll" id="loadAll" value="Y">
								    					 				Allow user to load all the searched records
								  								</label>
								  								</div> 
								  							</div>
								  							<div class="radio"> 
								  								<div class="col-md-7"> 
								  								<label>
								    								<input type="radio" class="minimal" name="loadAll" id="loadAll" value="N">
								    					 				Load until OPAC Retrieval Limit
								  								</label>
													  			</div>
															</div>
											  	 		</div>
											  	 
								    				</div>
								    				</div>
												</div>
                								</div>
                								
                								<div class="col-sm-5 col-md-5">
                									<div class="panel-group">
								    				<div class="panel panel-default">
								      					<div class="panel-heading">
															<h3 class="panel-title">New Arrivals</h3>
														</div>
														
														<div class="panel-body">
												
								                    		<div class="form-group">
								                    			<div class="checkbox"> 
								                      				<div class="col-md-5"> 
								  										<label>
								    										<input type="checkbox" class="minimal" name="newEnable" id="newEnable">
								    					 						Enable user defines date
								  										</label>
								  									</div> 
								  								</div>
								  						
								  								<div class="checkbox"> 
								  									<div class="col-md-7"> 
								  										<label>
								    										<input type="checkbox" class="minimal" name="newInc" id="newInc">
								    					 						Include only first released material
								  										</label>
													  				</div>
																</div>
											  	 			</div>
											  	 
											  	 			<div class="col-md-1">
								                  				<button type="button" class="btn btn-info selectPopup" id="btn-search" data-toggle="modal" data-target="#newArrival">New Arrivals Policy</button>
								                  			</div>
								    					</div>
								    				</div>
											  </div>
                								</div>
                						
                						</div>
                						
                						<div class="form-group">
                							<label class="col-sm-2">OPAC Message Time-out : (max. 60 second)</label>
                      							<div class="input-group col-sm-3 col-md-3">
                        							<input type="text" class="form-control" id="opacmsgtime" name="opacmsgtime">
                      									<span class="input-group-addon">second(s)</span>
                      							</div>
	                    				</div>
	                    				
	                    				<div class="form-group">
                							<label class="col-sm-2">Search History</label>
                      							<div class="input-group col-sm-3 col-md-3">
                        							<input type="text" class="form-control" id="sercHistory" name="sercHistory">
                      									<span class="input-group-addon">resultset(s)</span>
                      							</div>
	                    				</div>
	                    				
	                    				<input type="hidden" class="form-control" id="DateConfiguration" name="DateConfiguration">
	                    				<input type="hidden" class="form-control" id="Locationlist" name="Locationlist">
	                    				<input type="hidden" class="form-control" id="ItemCategorylist" name="ItemCategorylist">
	                    				<input type="hidden" class="form-control" id="Conditionlist" name="Conditionlist">
	                    				<input type="hidden" class="form-control" id="SMDlist" name="SMDlist">
	                    				<input type="hidden" class="form-control" id="Statuslist" name="Statuslist">
                						</div>
                					</div><!-- /.tab-pane 5 -->
                					
                					<div class="tab-pane" id="tab_6">
                						<div class="box-body">
                							<div class="form-group">
											  	<label class="col-sm-2">Serials. Head</label>
												<div class="col-sm-3">
													<div class="input-group">
														<input type="text" class="form-control" id="serHead" name="serHead">
															<a href="Modal_PatrSearch?textid=serHead" id="serHead" class="input-group-addon btn btn-primary searchserHead" data-toggle="modal" data-target="#modal_searchserHead">
															<span class="glyphicon glyphicon-option-horizontal"></span></a>
													</div>
												</div>
												<div class="col-sm-3 div-serHeadname"></div>
												
												 <label class="col-sm-1 control-label">Tel. Ext</label>
								                 <div class="col-sm-2 col-md-2">
								                    <input type="text" class="form-control" id="serext" name="serext" >
								             	 </div>
											</div>
											<div class="form-group">
						                      <label class="col-sm-2">Batch Order Printing</label>
						                      <div class="col-sm-1 col-md-10">
						                        <input type="checkbox" class="minimal" name="serbprint" id="serbprint">
						                      </div>
						                    </div>
                						</div>
                					</div><!-- /.tab-pane 6 -->
                					
                					<div class="tab-pane" id="tab_7">
                						<div class="box-body">
                							<div class="form-group">
											  	<label class="col-sm-2">IRS Head</label>
												<div class="col-sm-3">
													<div class="input-group">
														<input type="text" class="form-control" id="irsHead" name="irsHead">
															<a href="Modal_PatrSearch?textid=irsHead" id="irsHead" class="input-group-addon btn btn-primary searchirsHead" data-toggle="modal" data-target="#modal_searchirsHead">
															<span class="glyphicon glyphicon-option-horizontal"></span></a>
													</div>
												</div>
												<div class="col-sm-3 div-irsHeadname"></div>
												
												 <label class="col-sm-1 control-label">Tel. Ext</label>
								                 <div class="col-sm-2 col-md-2">
								                    <input type="text" class="form-control" id="irsext" name="irsext">
								             	 </div>
											</div>
											<div class="form-group">
					                    	<label class="col-sm-2">IRS MARC Type</label>
				                      			<div class="col-sm-3 col-md-3">
				                        			<select class="form-control" id="irstype" name="irstype">
														<jsp:include page="../Select_marctype.jsp"></jsp:include>
													</select>
				                      			</div>
				                     		</div>
				                     		<div class="form-group">
								            	<label class="col-sm-2 ">System Generated Number</label>
						                      	<div class="radio"> 
						                      		<div class="col-md-1"> 
							  							<label>
							    							<input type="radio" class="minimal" name="irsflag" id="irsflag" value="Y">
							    					 		Yes
							  							</label>
						  							</div> 
						  							<div class="col-md-1"> 
						  								<label>
						    								<input type="radio" class="minimal" name="irsflag" id="irsflag" value="N">
						    					 			No
						  								</label>
											  		</div>
											  	</div>
								            </div>
                						</div>
                					</div><!-- /.tab-pane 7 -->
                					
                					<div class="tab-pane" id="tab_8">
                						<div class="box-body">
                							<div class="form-group">
											  	<label class="col-sm-2">Finance Controller</label>
												<div class="col-sm-3">
													<div class="input-group">
														<input type="text" class="form-control" id="finHead" name="finHead">
															<a href="Modal_PatrSearch?textid=finHead" id="finHead" class="input-group-addon btn btn-primary searchfinHead" data-toggle="modal" data-target="#modal_searchfinHead">
															<span class="glyphicon glyphicon-option-horizontal"></span></a>
													</div>
												</div>
												<div class="col-sm-3 div-finHeadname"></div>
												
												 <label class="col-sm-1 control-label">Tel. Ext</label>
								                 <div class="col-sm-2 col-md-2">
								                    <input type="text" class="form-control" id="finext" name="finext">
								             	 </div>
											</div>
											<div class="form-group">
							              		<label class="col-sm-2">Fund Code</label>
							                	<div class="col-sm-1 col-md-2">
							                    	<input type="text" class="form-control" id="fund" name="fund">
							             		</div>
							       			</div>
                						</div>
                					</div><!-- /.tab-pane 8 -->
                					
                				</div><!-- nav-tabs-custom -->
    						</div><!-- /. -->
    					</div> <!-- /.col -->
    				</div><!-- /.row -->
    				
    				<!-- END TAB CONTENT -->
							<div class="modal-footer">
							
							    <button type="submit" id="save" class="btn btn-primary save">Save</button>
							    <button type="button" id="cancel" class="btn btn-secondary" data-dismiss="modal">Cancel</button>
						        
							</div>	
    				<!-- END CUSTOM TABS -->
    			</form>
    		</div>
		</div>
	</div>   
	<!-- ./END MAIN CONTENT -->		
	
	<!-- MODAL WHEN CLICK HEAD LIB -->
	<div class="modal fade" id="modal_searchheadLib" tabindex="-1" role="dialog" aria-labelledby="modal_searchheadLib" data-keyboard="false" data-backdrop="static"> 
		<div class="modal-dialog" style="width:55%;">
			<div class="modal-content" id="modal_searchheadLibContent">
				<!-- Remote content load here  -->
			</div>
		</div>
	</div>
	<!-- Modal END modal search HEAD LIB -->
	
	<!-- MODAL WHEN CLICK PR OFFICER -->
	<div class="modal fade" id="modal_searchprofficer" tabindex="-1" role="dialog" aria-labelledby="modal_searchprofficer" data-keyboard="false" data-backdrop="static"> 
		<div class="modal-dialog" style="width:55%;">
			<div class="modal-content" id="modal_modal_searchprofficerContent">
				<!-- Remote content load here  -->
			</div>
		</div>
	</div>
	<!-- Modal END modal search PR OFFICER  -->
	
	<!-- MODAL WHEN CLICK ACQ HEAD -->
	<div class="modal fade" id="modal_searchacqhead" tabindex="-1" role="dialog" aria-labelledby="modal_searchacqhead" data-keyboard="false" data-backdrop="static"> 
		<div class="modal-dialog" style="width:55%;">
			<div class="modal-content" id="modal_searchacqheadContent">
				<!-- Remote content load here  -->
			</div>
		</div>
	</div>
	<!-- Modal END modal search ACQ HEAD -->
	
	<!-- MODAL WHEN CLICK CAT HEAD -->
	<div class="modal fade" id="modal_searchcathead" tabindex="-1" role="dialog" aria-labelledby="modal_searchcathead" data-keyboard="false" data-backdrop="static"> 
		<div class="modal-dialog" style="width:55%;">
			<div class="modal-content" id="modal_searchcatheadContent">
				<!-- Remote content load here  -->
			</div>
		</div>
	</div>
	<!-- Modal END modal search CAT HEAD -->
	
	<!-- MODAL WHEN CLICK CIR HEAD -->
	<div class="modal fade" id="modal_searchcirhead" tabindex="-1" role="dialog" aria-labelledby="modal_searchcirhead" data-keyboard="false" data-backdrop="static"> 
		<div class="modal-dialog" style="width:55%;">
			<div class="modal-content" id="modal_searchcirheadContent">
				<!-- Remote content load here  -->
			</div>
		</div>
	</div>
	<!-- Modal END modal search CIR HEAD -->
	
	<!-- MODAL WHEN CLICK SER HEAD -->
	<div class="modal fade" id="modal_searchserHead" tabindex="-1" role="dialog" aria-labelledby="modal_searchserHead" data-keyboard="false" data-backdrop="static"> 
		<div class="modal-dialog" style="width:55%;">
			<div class="modal-content" id="modal_searchcirheadContent">
				<!-- Remote content load here  -->
			</div>
		</div>
	</div>
	<!-- Modal END modal search SER HEAD -->
	
	<!-- MODAL WHEN CLICK SER HEAD -->
	<div class="modal fade" id="modal_searchirsHead" tabindex="-1" role="dialog" aria-labelledby="modal_searchirsHead" data-keyboard="false" data-backdrop="static"> 
		<div class="modal-dialog" style="width:55%;">
			<div class="modal-content" id="modal_searchirsHeadContent">
				<!-- Remote content load here  -->
			</div>
		</div>
	</div>
	<!-- Modal END modal search SER HEAD -->
	
	
	<!-- MODAL WHEN CLICK FUND HEAD -->
	<div class="modal fade" id="modal_searchfinHead" tabindex="-1" role="dialog" aria-labelledby="modal_searchfinHead" data-keyboard="false" data-backdrop="static"> 
		<div class="modal-dialog" style="width:55%;">
			<div class="modal-content" id="modal_searchfinHeadContent">
				<!-- Remote content load here  -->
			</div>
		</div>
	</div>
	<!-- Modal END modal search FUND HEAD -->
	
	<!-- MODAL WHEN CLICK NEW ARRIVAL POLICY -->
	<div class="modal fade" id="newArrival" tabindex="-1" role="dialog" aria-labelledby="ModalAdd" aria-hidden="true" data-keyboard="false" data-backdrop="static">
			<div class="modal-dialog" role="document" style="width:70%">
				<div class="modal-content">
					<!-- Modal content-->
					<div class="modal-header">
						<h5 class="modal-title" id="modalName" align="center">form</h5>
					    	<button type="button" id="closeModalAdd" class="close" data-dismiss="modal" aria-label="Close">
					        	<span aria-hidden="true">&times;</span>
					        </button>
					</div>
					<form role="form" class="form-horizontal" id="formdataArr" method="post">
					&emsp;
					<div class="form-group">
                		<label class="col-sm-2">Date Configuration</label>
                      		<div class="col-sm-1 col-md-1">
								<input type="text" class="form-control" id="daydate" name="daydate">
							</div>
                      		<div class="input-group col-sm-3 col-md-3">
                        		<select class="form-control" id="dwm">
												      	<option value="D">Day</option>
								  						<option value="W">Week</option>
								  						<option value="M">Month</option>
												    </select>
                      				<span class="input-group-addon currentdate"></span>
                      		</div>
	               </div>
					
					
					<div class="modal-body" id="detail">
						<ul class="nav nav-tabs" role="tablist">
							<li id="Location" class="active"><a href="#loca"
								aria-controls="loca" role="tab" data-toggle="tab">Location </a></li>
							<li id="Icate"><a href="#icate" aria-controls="icate" role="tab"
								data-toggle="tab">Item Category</a></li>
							<li id="Condi"><a href="#condi" aria-controls="condi" role="tab"
								data-toggle="tab">Condition</a></li>
							<li id="Smd"><a href="#smd" aria-controls="smd" role="tab"
								data-toggle="tab">SMD</a></li>
							<li id="Status"><a href="#status"
								aria-controls="status" role="tab" data-toggle="tab">Status</a></li>
						</ul>
						
						<!-- TAB CONTENT -->
						<div class="tab-content">
							<!-- TAB LOCATION -->
							<div role="tabpanel" class="tab-pane pwidth-95 active" id="loca">
								<div class="row">
									<div class="col-xs-12">
										<div class="panel panel-default">
											<div class="panel-heading"></div>
											<div class="panel-body">
												<div id="locaListingContainer">
													<table id="locaInfo" class="table table-bordered table-striped">
														<thead>
														 	<tr>
														 		<th>Location Code</th>
																<th>Description</th>
																<th>Branch Code</th>
														 	</tr>
													 	</thead>
													</table>
												</div>
											</div>
										</div>
									</div>
								</div>	
							</div>
							<!-- END TAB LOCATION -->
							
							<!-- TAB ITEM CATEGORY -->
							<div role="tabpanel" class="tab-pane pwidth-95" id="icate">
								<div class="margin-btm-15"></div>
								<div id="listiCat">
											<table id="icat" class="table table-bordered table-striped">
												<thead>
												 	<tr>
														<th>Code</th>
														<th>Description</th>
												 	</tr>
											 	</thead>
											</table>
									</div>
								
							</div>
							
							<!-- END TAB ITEM CATEGORY -->
							
							
							<!-- TAB CONDITION -->
							<div role="tabpanel" class="tab-pane pwidth-95" id="condi">
								<div class="margin-btm-15"></div>
										<div id="listCond">
											<table id="cond" class="table table-bordered table-striped">
												<thead>
												 	<tr>
														<th>Code</th>
														<th>Description</th>
												 	</tr>
											 	</thead>
											</table>
									</div>
							</div>
							<!-- END TAB CONDITION -->
							
							<!-- TAB SMD -->
							<div role="tabpanel" class="tab-pane pwidth-95" id="smd">
								<div class="margin-btm-15"></div>
									<div id="listSMD">
											<table id="SMD" class="table table-bordered table-striped">
												<thead>
												 	<tr>
														<th>Code</th>
														<th>Description</th>
												 	</tr>
											 	</thead>
											</table>
									</div>
							</div>
							<!-- END TAB SMD -->
							
							<!-- TAB STATUS -->
							<div role="tabpanel" class="tab-pane pwidth-95" id="status">
								<div class="margin-btm-15"></div>
									<div id="listStatus">
											<table id="stat" class="table table-bordered table-striped">
												<thead>
												 	<tr>
												 		<th>Code</th>
														<th>Description</th>
												 	</tr>
											 	</thead>
											</table>
									</div>
							</div>
							<!-- END TAB STATUS -->
							
						</div>
						
						
						<!-- END TAB CONTENT -->
						<div class="form-group modal-footer">
						      	<button type="button" id="savearr" class="btn btn-primary">Save</button>
						        <button type="button" id="cancelarr" class="btn btn-secondary" data-dismiss="modal">Cancel</button>
					        
						</div>	
					</div>
					
					</form>
					<!-- END Modal content-->
				</div>
			</div>
		</div>
	<!-- MODAL WHEN CLICK NEW ARRIVAL POLICY -->

				    
 	</body>
</html>

