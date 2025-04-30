<%@ page contentType="text/html; charset=UTF-8" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head> 

	<script type="text/javascript" src="${pageContext.request.contextPath}/js/Foundation/Maintenance/AAIFN1500M60.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/Foundation/globalFndMaint.js"></script>
	
</head>

<body>
	<!-- START MAIN CONTENT -->
			<div class="box box-default">
				<div class="panel panel-default">
					<div class="panel-heading">
					<h3 class="panel-title pull-left"></h3>
				        <h3 class="panel-title pull-left"></h3>
						<button class="btn btn-primary pull-right" data-toggle='modal' data-target='#searchcoursecode'><i class="glyphicon glyphicon-search" title="Search" data-searchfor="coursecode"></i></button>
        				<button type="button" class="btn btn-primary pull-right" id="addCoursecode" data-toggle='modal' data-target="#modalcorsecode" data-mode="1"> 
        				<i class="glyphicon glyphicon-plus" title="Add Course Code"></i></button>
						<div class="clearfix"></div> 
					</div>

					<div class="panel-body" id="display_fndMaint">
					 
						
						<br><br>
						<table id="fndMaintTable" class="table table-bordered table-striped display compact" width="100%">
						 <thead>
						 	<tr>
						 		<th>No</th>
						 		<th>Course Code</th>
		                        <th>Description</th>
		                        <th>Tutor</th>
		                        <th>Action</th>
						 	</tr>
						 </thead>
						</table>
					</div>
				</div>
			</div>
		<!-- END MAIN CONTENT -->
		
		
		
		
		<!-- START MODAL ADD, EDIT, VIEW -->
		<div class="modal fade" id="modalcorsecode" tabindex="-1" role="dialog" aria-labelledby="ModalAdd" aria-hidden="true" data-keyboard="false" data-backdrop="static">
			<div class="modal-dialog" role="document" style="width:80%">
				<div class="modal-content">
					<!-- Modal content-->
						<div class="modal-header">
							<h5 class="modal-title" id="modalName" align="center">form</h5>
					    	<button type="button" id="closeModalAdd" class="close" data-dismiss="modal" aria-label="Close">
					        	<span aria-hidden="true">&times;</span>
					        </button>
						</div>
						<form role="form" class="form-horizontal" id="formdatacoursecode" method="post">
							<br>
							
							<div class="form-group row">
									<div class="col-sm-3"><label>Course Code<span id="red">*</span></label></div>
										<div class='col-sm-2'>
											<input type="text" class="form-control" id="inputcoursecode" name="inputcoursecode">
										</div>
							</div>
							
							<div class="form-group row">
									<div class="col-sm-3"><label>Description</label></div>
										<div class='col-sm-6'>
											<input type="text" class="form-control" id="inputcoursedescription" name="inputcoursedescription">
										</div>
							</div>
							
							
							<input type="hidden" class="patrSelection" value = "mTutor">
							<jsp:include page="../../../../module/PatronID.jsp"></jsp:include> 
							
							<%-- <div class="form-group">
								<div class="col-sm-1"></div>
									<div class="col-sm-3"><label>Tutor</label></div>
										<div class='col-sm-4'>
											<input type="hidden" class="patrSelection" value = "m">
											<jsp:include page="../../../../module/PatronID.jsp"></jsp:include> 
										</div>
							</div> --%>
							
							<!-- END TAB CONTENT -->
							<div class="modal-footer">
							
								<div class="form-group">
									<div class="col-sm-2"><label>Date</label></div>
										<div class='col-sm-1 daterec'>
										</div>
									<div class="col-sm-3"></div>
									<div class="col-sm-2"><label>Officer</label></div>
										<div class='col-sm-1 recby'>
										</div>
								</div>
							
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
		
		
</body>
</html>