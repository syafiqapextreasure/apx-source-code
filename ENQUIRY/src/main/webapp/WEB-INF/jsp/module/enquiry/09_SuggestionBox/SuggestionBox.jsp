<%@ page contentType="text/html; charset=UTF-8" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

	<script type="text/javascript" src="${pageContext.request.contextPath}/js/enquiry/SuggestionBox.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/enquiry/validateSuggestionBox.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath() %>/plugin/moment.min.js"></script>
	<link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">
	
    <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/responsive/2.2.0/css/responsive.dataTables.min.css">
    <script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/responsive/2.2.0/js/dataTables.responsive.min.js"></script>

	<style type="text/css">	
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
						
						<!-- <div class="clearfix"></div> -->
					</div>
					<br>
					<div class="btn-group pull-right ">	
					</div>
					
					<div class="panel-body" id="display_SB">
					
						<div class="form-group">
							<div class="col-sm-2 col-md-2"><label>Date</label></div>
								<div class="col-sm-4">
									<div class="input-daterange input-group">
										<input type="text" class="input-sm form-control"  name="startDate"
												id="input-startDate" autocomplete="off"/> <span class="input-group-addon">to</span>
										<input type="text" class="input-sm form-control" name="endDate" id="input-endDate" autocomplete="off"/>
									</div>
								</div>
						</div>
						
						<div class="btn-group pull-right ">	
									<div class="col-md-1"><button type="button" id="suggBoxReterive" class="btn btn-primary" title="Retrieve"><span class="glyphicon glyphicon-log-in" style="color:white"></span></button></div>
						</div>
							
						<br><br>
						<table id="suggBox" class="table table-bordered table-striped">
						 <thead>
						 	<tr>
						 		<th>No</th>
						 		<th>Patron ID</th>
								<th>Patron</th>
								<th>Date</th>
								<th>Time</th>
								<th>Suggestion</th>
								<th>Reply Status</th>
								<th>Action</th>
						 	</tr>
						 </thead>
						</table>
					</div>
				</div>
			</div>
		<!-- END MAIN CONTENT -->
		
		
		<div class="modal fade" id="modalReply" tabindex="-1" role="dialog" aria-labelledby="ModalAdd" aria-hidden="true" data-keyboard="false" data-backdrop="static">
			<div class="modal-dialog" role="document" style="width:75%">
				<div class="modal-content">
					<!-- Modal content-->
						<div class="modal-header">
							<h5 class="modal-title" id="modalName" align="center">form</h5>
					    	<button type="button" id="closeModalAdd" class="close" data-dismiss="modal" aria-label="Close">
					        	<span aria-hidden="true">&times;</span>
					        </button>
						</div>
						
						<div class="modal-body">
						<form role="form" class="form-horizontal" id="formdataSugg" method="post">
							<br>
									<div class="form-group">
										
										<div class="col-sm-2"><label>Date : </label></div>
										<div class="col-sm-2 lblDate"></div>
									</div>
									
									<div class="form-group">
										<div class="col-sm-2"><label>Time : </label></div>
										<div class="col-sm-2 lblTime"></div>
									</div>
									
									<div class="form-group">
										<div class="col-sm-2"><label>Patron ID : </label></div>
										<div class="col-sm-2 lblPatronID"></div>
									</div>
									
									<div class="form-group">
										<div class="col-sm-2"><label>Name: </label></div>
										<div class="col-sm-2 lblPatronName"></div>
									</div>
									
									<div class="form-group">
										<div class="col-sm-2"><label>Suggestion</label></div>
										<div class="col-sm-8">
											<textarea class="form-control" rows="7" id="txtSuggest" name="txtSuggest" disabled></textarea>
										</div>
									</div>
									
									<div class="form-group">
										<div class="col-sm-2"><label>Formal Reply</label></div>
										<div class="col-sm-8">
											<textarea class="form-control" rows="7" id="txtReply" name="txtReply"></textarea>
										</div>
									</div>
			
							
							
							<!-- END TAB CONTENT -->
							<div class="modal-footer">
								<button type="submit" id="save" class="btn btn-primary save">Save</button>
							    <button type="button" id="close" class="btn btn-default" data-dismiss="modal">Close</button>
							</div>	
						</form>
						</div>
					<!-- END Modal content-->
				</div>
			</div>
		</div>
		
		
</body>
</html>