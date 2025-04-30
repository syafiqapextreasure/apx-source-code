<%@ page contentType="text/html; charset=UTF-8" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

	<script type="text/javascript" src="${pageContext.request.contextPath}/js/Foundation/ModuleAccessLevel.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/Foundation/validateModuleAccessLevel.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath() %>/plugin/moment.min.js"></script>
	<link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">

	<style type="text/css">			
		.right {
			text-align: right;
		}
		.center {
			text-align: center;
		}	
	</style>
</head>

<body>
	<!-- START MAIN CONTENT -->
			<div class="box box-default">
				<div class="panel panel-default">
					<div class="panel-heading">
						<h3 class="panel-title pull-left"></h3>
						<button class="btn btn-primary pull-right" data-toggle='modal' data-target='#searchModuleAccessLevel'><i class="glyphicon glyphicon-search" title="Search" data-searchfor="sModuleAccessLevel"></i></button>
        				<button type="button" class="btn btn-primary pull-right" id="addModuleAccessLevel" data-toggle='modal' data-target="#modalModuleAccessLevel" data-whatever="Add New"> 
        				<i class="glyphicon glyphicon-plus" title="Add New"></i></button>
        				
						<div class="clearfix"></div>
					</div>
					
					<input type="hidden" id="comfirmChoose">
						
					<div class="panel-body" id="display_ModuleAccessLevel">
						<table id="TableModuleAccessLevel" class="table table-bordered table-striped">
						 <thead>
						 	<tr>
						 		<th>No</th>
								<th>Group ID</th>
								<th>Module Code</th>
								<th>Module Name</th>
								<th>Access Level</th>
						 		<th>Action</th>
						 	</tr>
						 </thead>
						</table>
					</div>
				</div>
			</div>
		<!-- END MAIN CONTENT -->
		
		<!-- START MODAL ADD, EDIT, VIEW -->
		<div class="modal fade" id="modalModuleAccessLevel" tabindex="-1" role="dialog" aria-labelledby="ModalAdd" aria-hidden="true" data-keyboard="false" data-backdrop="static">
			<div class="modal-dialog" role="document" style="width:65%">
				<div class="modal-content">
					<!-- Modal content-->
						<div class="modal-header">
							<h5 class="modal-title" id="modalName" align="center">form</h5>
					    	<button type="button" id="closeModalAdd" class="close" data-dismiss="modal" aria-label="Close">
					        	<span aria-hidden="true">&times;</span>
					        </button>
						</div>
						<form role="form" class="form-horizontal" id="formdataModuleAccessLevel" method="post">
							<br>
							
							<div class="form-group">
								<div class="col-sm-1"></div>
									<div class="col-sm-3"><label><input type="checkbox" class="form-check-input" id="checkGroupID" name="Retain" value="inputGroupID">&nbsp Group ID</label><!-- <label>Group ID</label> --></div>
										<div class="col-sm-7">
											<select class="form-control" id="inputGroupID" name="inputGroupID">
												<jsp:include page="../Select_GroupID.jsp"></jsp:include>
											</select>
										</div>
							</div>
							
							<div class="form-group">
								<div class="col-sm-1"></div>
									<div class="col-sm-3"><label><input type="checkbox" class="form-check-input" id="checkModuleName" name="Retain" value="inputModuleName">&nbsp Module Name</label><!-- <label>Module Name</label> --></div>
										<div class="col-sm-7">
											<select class="form-control" id="inputModuleName" name="inputModuleName">
												<option value="GL">GL - FOUNDATION</option>
												<option value="CI">CI - CIRCULATION</option>
												<option value="CT">CT - CATALOGING</option>
												<option value="AU">AU - AUTHORITY</option>
												<option value="OP">OP - INFOTRACK</option>
												<option value="AC">AC - ACQUISITIONS</option>
												<option value="FA">FA - FUND ACCOUNTING</option>
												<option value="LO">LO - OUTGOING REQUEST</option>
												<option value="IR">IR - IRS</option>
												<option value="RE">RE - RECEIPTING</option>
												<option value="SE">SE - SERIALS</option>
												<option value="PS">PS - PARASORD</option>
												<option value="BI">BI - BINDING</option>
												<option value="LI">LI - INCOMING REQUEST</option>
												<option value="ST">ST - STOCK TAKE</option>
												<option value="RT">TR - ROUTING</option>
												<option value="RC">RC - RESERVE COLLECTION</option>
												<option value="DD">DD- DOCUMENT DELIVERY</option>
												<option value="MM">MM - MEMBERSHIP</option>
												<option value="LL">LL - INTER LIBRARY LOAN</option>
											</select>
										</div>
							</div>
							
							<div class="form-group">
								<div class="col-sm-1"></div>
									<div class="col-sm-3"><label><input type="checkbox" class="form-check-input" id="checkAccessLevel" name="Retain" value="inputAccessLevel">&nbsp Access Level</label><!-- <label>Access Level</label> --></div>
										<div class='col-sm-2'>
											<input type="text" class="form-control" id="inputAccessLevel" name="inputAccessLevel">
										</div>
							</div>
							
							<!-- END TAB CONTENT -->
							<div class="modal-footer">
							
								<!-- <div class="form-group">
									<div class="col-sm-2"><label>Date</label></div>
										<div class='col-sm-1 daterecModuleAccessLevel'>
										</div>
									<div class="col-sm-3"></div>
									<div class="col-sm-2"><label>Officer</label></div>
										<div class='col-sm-1 recbyModuleAccessLevel'>
										</div>
								</div> -->
							
							    <button type="submit" id="save" class="btn btn-primary save">Save</button>
							    <button type="button" id="cancel" class="btn btn-secondary" data-dismiss="modal">Cancel</button>
							    <button type="button" id="close" class="btn btn-default" data-dismiss="modal">Close</button>
						        
							</div>	
						</form>
					<!-- END Modal content-->
				</div>
			</div>
		</div>
		<!-- END START MODAL ADD, EDIT, VIEW -->
		
		<!-- Modal for search -->
		<div class="modal fade" id="searchModuleAccessLevel" tabindex="-1" role="dialog" aria-labelledby="searchModuleAccessLevel" data-keyboard="false" data-backdrop="static">
			<div class="modal-dialog" role="document" style="width:55%;overflow:auto">
				<div class="modal-content">
					<!-- Modal content-->
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-label="Close" id="closesearchModuleAccessLevel"><span aria-hidden="true">&times;</span></button>
						<h4 class="modal-title" id="myModalLabel">Search</h4>
					</div>
					
					<div class="modal-body">
						<div class="panel panel-default" id="form_parent">
							<div class="panel-group">
								<div class="panel panel-default">
									<div class="panel-heading">
										<h4 class="panel-title">
											<a data-toggle="collapse" data-parent="#form_parent" href="#search-ModuleAccessLevel"></a>
										</h4>
									</div>
									<div id="search-creditdebit" class="panel-collapse collapse in">
										<div class="panel-body">
											<form role="form" class="form-horizontal">
												
												<div class="form-group">
													<div class="col-sm-3">
														<label class="radio-inline"><input type="radio" id="search-type" name="searchSelection" value="GroupID" checked>Group ID</label>
													</div>
												
													<div class="col-sm-3">
														<label class="radio-inline"><input type="radio" id="search-type" name="searchSelection" value="ModuleName">Module Name</label>
													</div>
												</div>
													
												<div class="form-group">
													<div class="col-sm-12 col-md-12">
														<input type="text" class="form-control" name="input-criteria" id="input-criteria" autocomplete="off">
													</div>
												</div>
								
												<div class="form-group">
													<div class="col-sm-8 col-md-8">
														<button type="button" class="btn btn-info" id="search">
															<span class="glyphicon glyphicon-search"></span> Search
														</button>
														<input type="button" name="cancel" value="Cancel" class="btn btn-info" data-dismiss="modal"/>
													</div>
												</div>
												
											</form>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
					
					<div class="modal-footer"></div>
					<!-- Modal content-->
				</div>
			</div>
		</div>
		<!-- END modal for search -->
</body>
</html>