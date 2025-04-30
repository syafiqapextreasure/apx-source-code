<%@ page contentType="text/html; charset=UTF-8" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

	<script type="text/javascript" src="${pageContext.request.contextPath}/js/Foundation/groupId.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/Foundation/validateGroupId.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath() %>/plugin/moment.min.js"></script>
	<link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">

	<style type="text/css">			
		.right {
			text-align: right;
		}
		#red {
			color: red;
		}
		/* .center {
			text-align: center;
		} */	
	</style>
</head>

<body>
	<!-- START MAIN CONTENT -->
			<div class="box box-default">
				<div class="panel panel-default">
					<div class="panel-heading">
						<h3 class="panel-title pull-left"></h3>
						 <button class="btn btn-primary pull-right" data-toggle='modal' data-target='#searchgroupId'><i class="glyphicon glyphicon-search" title="Search" data-searchfor="sgroupId"></i></button>
        				<button type="button" class="btn btn-primary pull-right" id="addgroupId" data-toggle='modal' data-target="#modalgroupId" data-whatever="Add"> 
        				<i class="glyphicon glyphicon-plus" title="Add Group Id"></i></button>
        				
						<div class="clearfix"></div>
					</div>
							
					<div class="panel-body" id="display_groupId">
						<table id="groupIdTable" class="table table-bordered table-striped">
						 <thead>
						 	<tr>
						 		<th>Group Id</th>
								<th>Group Name</th>
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
		<div class="modal fade" id="modalgroupId" tabindex="-1" role="dialog" aria-labelledby="ModalAdd" aria-hidden="true" data-keyboard="false" data-backdrop="static">
			<div class="modal-dialog" role="document" style="width:65%">
				<div class="modal-content">
					<!-- Modal content-->
						<div class="modal-header">
							<h5 class="modal-title" id="modalName" align="center">form</h5>
					    	<button type="button" id="closeModalAdd" class="close" data-dismiss="modal" aria-label="Close">
					        	<span aria-hidden="true">&times;</span>
					        </button>
						</div>
						<form role="form" class="form-horizontal" id="formdatagroupId" method="post">
							<br>
							
							<div class="form-group">
								<div class="col-sm-1"></div>
									<div class="col-sm-3"><label>Group Id <span id="red">*</span></label></div>
										<div class='col-sm-2'>
											<input type="text" class="form-control" id="inputgroupId" name="inputgroupId">
										</div>
							</div>
							
							<div class="form-group">
								<div class="col-sm-1"></div>
									<div class="col-sm-3"><label>Group Name</label></div>
										<div class='col-sm-6'>
											<input type="text" class="form-control" id="inputGroupName" name="inputGroupName">
										</div>
							</div>
							
							<div class="form-group">
								<div class="col-sm-1"></div>
									<div class="col-sm-3"><label>Access Level <span id="red">*</span></label></div>
										<div class='col-sm-2'>
											<input type="text" class="form-control" id="inputAccessLevel" name="inputAccessLevel">
										</div>
							</div>
							
							<!-- END TAB CONTENT -->
							<div class="modal-footer">
							
								<!-- <div class="form-group">
									<div class="col-sm-2"><label>Date</label></div>
										<div class='col-sm-1 daterec'>
										</div>
									<div class="col-sm-3"></div>
									<div class="col-sm-2"><label>Officer</label></div>
										<div class='col-sm-1 recby'>
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
		<div class="modal fade" id="searchgroupId" tabindex="-1" role="dialog" aria-labelledby="searchgroupId" data-keyboard="false" data-backdrop="static">
			<div class="modal-dialog" role="document" style="width:50%;overflow:auto">
				<div class="modal-content">
					<!-- Modal content-->
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-label="Close" id="closesearchgroupId"><span aria-hidden="true">&times;</span></button>
						<h4 class="modal-title" id="myModalLabel">Search</h4>
					</div>
					
					<div class="modal-body">
						<div class="panel panel-default" id="form_parent">
							<div class="panel-group">
								<div class="panel panel-default">
									<div class="panel-heading">
										<h4 class="panel-title">
											<a data-toggle="collapse" data-parent="#form_parent" href="#search-groupId"></a>
										</h4>
									</div>
									<div id="search-groupId" class="panel-collapse collapse in">
										<div class="panel-body">
											<form role="form" class="form-horizontal">
											
												<div class="form-group">
													<div class="col-sm-3">
														<label class="radio-inline"><input type="radio" id="search-type" name="searchSelection" value="GroupId" checked>Group Id</label>
													</div>
												
													<div class="col-sm-3">
														<label class="radio-inline"><input type="radio" id="search-type" name="searchSelection" value="GroupName">Group Name</label>
													</div>
													
													<div class="col-sm-3">
														<label class="radio-inline"><input type="radio" id="search-type" name="searchSelection" value="AccessLevel">Access Level</label>
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