<%@ page contentType="text/html; charset=UTF-8" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/Foundation/PatrMiscellaneous.js"></script>
		<script type="text/javascript" src="<%= request.getContextPath() %>/plugin/moment.min.js"></script>
</head>
<body>
	<!-- START MAIN CONTENT -->
			<div class="box box-default">
				<div class="panel panel-default">
					<div class="panel-heading">
						<h3 class="panel-title pull-left"></h3>
						<button class="btn btn-primary pull-right" data-toggle='modal' id="search" data-target='#searchmiscellaneous' ><!-- href='Modal_Searchmiscellaneous' --><i class="glyphicon glyphicon-search" title="Search" data-searchfor="miscellaneous"></i></button>
        				<button type="button" class="btn btn-primary pull-right" id="addmiscellaneous" data-toggle='modal' data-target="#modalmiscellaneous" data-whatever="Add New"> 
        				<i class="glyphicon glyphicon-plus" title="Add New"></i></button>
						<div class="clearfix"></div>
					</div>
					
					<div class="panel-body" id="display_miscellaneous">
						<table id="miscellaneous" class="table table-bordered table-striped">
						 <thead>
						 	<tr>
						 		<th>Attribute</th>
								<th>Description</th>
						 		<th>Action</th>
						 	</tr>
						 </thead>
						</table>
					</div>
				</div>
			</div>
		<!-- END MAIN CONTENT -->
		
		<!-- START MODAL ADD, EDIT, VIEW -->
		<div class="modal fade" id="modalmiscellaneous" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-keyboard="false" data-backdrop="static">
		<div class="modal-dialog" role="document" style="width:70%">
			<div class="modal-content">
				<!-- Modal content-->	  
				<div class="modal-header">
					<h5 class="modal-title" id="modalName" align="center">form</h5>
					<button type="button" id="closeModalAdd" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
	
				<form role="form" class="form-horizontal" id="formdatamiscellaneous" method="post">
				<div class="modal-body">
					<div class="panel-body">
						<div class="row">
							<div class="col-md-10">
								<div class="form-group">
									<label class="col-sm-3 ">Patron Attribute</label>
									<div class="col-sm-5 col-md-3">
										<input type="text" class="form-control" id="input-patrAttri" name="inputpatrAttri">
									</div>
								</div>

								<div class="form-group">
									<label class="col-sm-3 ">Description</label>
									<div class="col-sm-7">
										<input type="text" class="form-control" id="input-desc" name="inputdesc">
									</div>
								</div>
							
								<div class="form-group">
									<label class="col-sm-3">Date Recorded</label>
									<div class="col-sm-3"><span class="daterec"></span></div>
									
									<label class="col-sm-3">Recorded By</label>
									<div class="col-sm-3"><span class="recby"></span></div>
								</div>
							</div>
						</div><!-- /.END ROW -->
					</div><!-- /.END PANEL BODY -->
				</div><!-- /.END MODAL BODY -->
	
				<div class="modal-footer">
					<button type="submit" id="save" class="btn btn-primary save">Save</button>
					<button type="button" id="cancel" class="btn btn-secondary" data-dismiss="modal">Cancel</button>
					<button type="button" id="close" class="btn btn-default" data-dismiss="modal">Close</button>
				</div>
						
				</form>
			</div>
		</div>
	</div>
	<!-- END START MODAL ADD, EDIT, VIEW -->
		
		
	<!-- Modal for search -->
	<div class="modal fade" id="searchmiscellaneous" tabindex="-1" role="dialog" aria-labelledby="searchmiscellaneous" data-keyboard="false" data-backdrop="static">
		<div class="modal-dialog" role="document" style="width:50%;overflow:auto">
			<div class="modal-content">
				<!-- Remote content load here -->
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close" id="closeFindMode"><span aria-hidden="true">&times;</span></button>
					<h4 class="modal-title" id="myModalLabel">Search Criteria</h4>
				</div>
	
				<div class="modal-body">
					<div class="panel panel-default" id="form_parent">
						<div class="panel-group">
							<div class="panel panel-default">
								<div class="panel-heading">
									<h4 class="panel-title">
										<a data-toggle="collapse" data-parent="#form_parent" href="#search-creditdebit"></a>
									</h4>
								</div>
								
								<div id="search-miscellaneous" class="panel-collapse collapse in">
									<div class="panel-body">
										<form role="form" class="form-horizontal" id="searchmiscellaneous">
							
											<div class="form-group">
												&nbsp;&nbsp;&nbsp;&nbsp;
												
												<label class="radio-inline">
								                    <input id="search-type" name="searchSelection" type="radio" value="attribute" checked>Attribute
								                </label>
								                <label class="radio-inline">
								                    <input id="search-type" name="searchSelection" type="radio" value="description">Description
								                </label>
											</div>
									
											<div class="form-group">
												<div class="col-sm-12 col-md-12">
													<input type="text" class="form-control" name="input-criteria" id="input-criteria">
												</div>
											</div>
				
											<div class="form-group">
												<div class="col-sm-8 col-md-8">
													<button type="button" class="btn btn-info" id="searchVal">
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
		</div>
	</div>
	</div>
	<!-- END modal for search -->

</body>
</html>