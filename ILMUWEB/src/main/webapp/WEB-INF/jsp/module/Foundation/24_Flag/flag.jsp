<%@ page contentType="text/html; charset=UTF-8" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

	<script type="text/javascript" src="${pageContext.request.contextPath}/js/Foundation/flag.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/Foundation/validateflag.js"></script>
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
						<button class="btn btn-primary pull-right" data-toggle='modal' data-target='#searchFlag'><i class="glyphicon glyphicon-search" title="Search" data-searchfor="sVendorFlag"></i></button>
        				<button type="button" class="btn btn-primary pull-right" id="addFlag" data-toggle='modal' data-target="#modalFlag" data-whatever="Add"> 
        				<i class="glyphicon glyphicon-plus" title="Add Flag"></i></button>
        				
						<div class="clearfix"></div>
					</div>
							
					<div class="panel-body" id="display_Flag">
						<table id="flagTable" class="table table-bordered table-striped">
						 <thead>
						 	<tr>
						 		<th>Function</th>
								<th>Description</th>
								<th>Value</th>
						 		<th>Action</th>
						 	</tr>
						 </thead>
						</table>
					</div>
				</div>
			</div>
		<!-- END MAIN CONTENT -->
		
		
		<!-- START MODAL ADD, EDIT, VIEW -->
		<div class="modal fade" id="modalFlag" tabindex="-1" role="dialog" aria-labelledby="ModalAdd" aria-hidden="true" data-keyboard="false" data-backdrop="static">
			<div class="modal-dialog" role="document" style="width:65%">
				<div class="modal-content">
					<!-- Modal content-->
						<div class="modal-header">
							<h5 class="modal-title" id="modalName" align="center">form</h5>
					    	<button type="button" id="closeModalAdd" class="close" data-dismiss="modal" aria-label="Close">
					        	<span aria-hidden="true">&times;</span>
					        </button>
						</div>
						<form role="form" class="form-horizontal" id="formdataFlag" method="post">
							<br>
							
							<div class="form-group">
								<div class="col-sm-1"></div>
									<div class="col-sm-3"><label>Function <span id="red">*</span></label></div>
										<div class='col-sm-4'>
											<input type="text" class="form-control" id="inputFunction" name="inputFunction">
										</div>
							</div>
							
							<div class="form-group">
								<div class="col-sm-1"></div>
									<div class="col-sm-3"><label>Description <span id="red">*</span></label></div>
										<div class='col-sm-6'>
											<input type="text" class="form-control" id="inputDescription" name="inputDescription">
										</div>
							</div>
							
							<div class="form-group">
								<div class="col-sm-1"></div>
									<div class="col-sm-3"><label>Value <span id="red">*</span></label></div>
										<div class='col-sm-6'>
											<input type="text" class="form-control" id="inputValue" name="inputValue">
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
		<div class="modal fade" id="searchFlag" tabindex="-1" role="dialog" aria-labelledby="searchFlag" data-keyboard="false" data-backdrop="static">
			<div class="modal-dialog" role="document" style="width:50%;overflow:auto">
				<div class="modal-content">
					<!-- Modal content-->
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-label="Close" id="closesearchFlag"><span aria-hidden="true">&times;</span></button>
						<h4 class="modal-title" id="myModalLabel">Search</h4>
					</div>
					
					<div class="modal-body">
						<div class="panel panel-default" id="form_parent">
							<div class="panel-group">
								<div class="panel panel-default">
									<div class="panel-heading">
										<h4 class="panel-title">
											<a data-toggle="collapse" data-parent="#form_parent" href="#search-Flag"></a>
										</h4>
									</div>
									<div id="search-groupId" class="panel-collapse collapse in">
										<div class="panel-body">
											<form role="form" class="form-horizontal">
											
												<div class="form-group">
													<div class="col-sm-3">
														<label class="radio-inline"><input type="radio" id="search-type" name="searchSelection" value="Function" checked>Function</label>
													</div>
												
													<div class="col-sm-3">
														<label class="radio-inline"><input type="radio" id="search-type" name="searchSelection" value="Description">Description</label>
													</div>
													
													<div class="col-sm-3">
														<label class="radio-inline"><input type="radio" id="search-type" name="searchSelection" value="Value">Value</label>
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