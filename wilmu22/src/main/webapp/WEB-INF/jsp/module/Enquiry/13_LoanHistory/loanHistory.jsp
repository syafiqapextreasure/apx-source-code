<%@ page contentType="text/html; charset=UTF-8" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

	<script type="text/javascript" src="${pageContext.request.contextPath}/js/Enquiry/loanHistory.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath() %>/plugin/moment.min.js"></script>
<!-- 	<link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet"> -->
	
	<script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/1.10.16/js/jquery.dataTables.js"></script> 
	<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.16/css/jquery.dataTables.css">
    <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/responsive/2.2.0/css/responsive.dataTables.min.css"> 
    <script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/responsive/2.2.0/js/dataTables.responsive.min.js"></script>
    
    
   <!--  <script type="text/javascript" src="https://cdn.datatables.net/1.10.24/js/jquery.dataTables.min.js"></script>
    <script type="text/javascript" src="https://cdn.datatables.net/rowgroup/1.1.2/js/dataTables.rowGroup.min.js"></script> -->
    
    <script type="text/javascript" src=" //cdn.datatables.net/plug-ins/1.10.24/api/sum().js"></script>
    <script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/buttons/1.6.1/js/dataTables.buttons.min.js"></script>
	<script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/buttons/1.6.1/js/buttons.flash.min.js"></script>
	<script type="text/javascript" charset="utf8" src="https://cdnjs.cloudflare.com/ajax/libs/jszip/3.1.3/jszip.min.js"></script>
	<script type="text/javascript" charset="utf8" src="https://cdnjs.cloudflare.com/ajax/libs/pdfmake/0.1.53/pdfmake.min.js"></script>
	<script type="text/javascript" charset="utf8" src="https://cdnjs.cloudflare.com/ajax/libs/pdfmake/0.1.53/vfs_fonts.js"></script>
	<script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/buttons/1.6.1/js/buttons.html5.min.js"></script>
	<script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/buttons/1.6.1/js/buttons.print.min.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath() %>/plugin/buttons.print.js"></script>
	
<script type="text/javascript" src="${pageContext.request.contextPath}/js/general/global.js"></script>	

	<!-- <style type="text/css">	
	
		@media print
		{
			html, body {  border: 1px solid white;
            height: 99%;
            page-break-after: avoid;
            page-break-before: avoid;}
			.dt-print-table, .dt-print-table thead, .dt-print-table th, .dt-print-table tr 
			{border: 0 none !important;}
			
		}
		
		.center {
			text-align: center;
		}	
		
		.right {
			text-align: right;
		}	
		
		#red {
			color: red;
		}
		
		

	</style> -->
</head>

<body>
	<!-- START MAIN CONTENT -->
			<div class="box box-default">
				<div class="panel panel-default">
					<div class="panel-heading">
						
						<!-- <div class="clearfix"></div> -->
					</div>

					<div class="panel-body" id="display_cirAct">
						<form class="form-horizontal" name="loanHistoryForm" id="loanHistoryForm">
							
							<br>
							<div class="modal-body row">
								<div class="col-md-5">
									<div class="form-group">
										<div class="col-sm-4 col-md-4">
										<label id="testingLabel"></label>
											<label class="form-check-label">
												<input class="form-check-input" type="radio" name="radioSearchCateria" id="radioSearchCateria" value="ctrlNo">
												Control Number
											</label>
										</div>
 										<div class="col-sm-6">
											<div class="input-group">
												<input type="text" class="form-control" id="txtControl" name="txtControl">
                        							 <span class="input-group-btn">
                        							 	<a  href='RetrieveTitleModal?action=8' class="btn btn-primary" data-toggle="modal" data-target="#searchWeed" title="Search"><span class="glyphicon glyphicon-search" style="width: 14px; height: 20px;"></span></a>
													</span>
											</div>
										</div>
<!-- 										<div class="col-sm-6">
											<div class="input-group">
												<input type="text" class="form-control" id="txtControl" name="txtControl">
													<span class="input-group-btn">
										   				<button class="btn btn-primary searchCtrlNo" type="button" data-toggle="modal" data-target="#Modal_PatrSearch">...</button>
										  			</span>
											</div>
										</div> -->
									</div>
									
									<div class="form-group">
										<div class="col-sm-4 col-md-4">
											<label class="form-check-label">
												<input class="form-check-input" type="radio" name="radioSearchCateria" id="radioSearchCateria" value="accno">
												Accession Number
											</label>
										</div>
										<div class="col-sm-6">
												<div class="input-group">
													<input type="text" class="form-control" id="txtAccessionNo" name="txtAccessionNo">
												</div>
										</div>
									</div>
							
								</div>
								<div class="col-md-5">
									<div class="form-group">
										<div class="col-sm-10">
											<textarea class="form-control" rows="4" id="txtTitle" name="txtTitle" disabled></textarea>
										</div>
									</div>
								</div>
							</div>

							
						</form>
						
						<br><br>
						<table id="loanHistoryTable" class="table table-bordered table-striped">
						 <thead>
						 	<tr>
						 		<th>No</th>
						 		<th>Accession No</th>
								<th>Patron ID</th>
								<th>Borrow Date</th>
								<th>Due Date</th>
								<th>Return Date</th>
								<th>Charge Officer</th>
								<th>Discharge Officer</th>
						 	</tr>
						 </thead>
						</table>
					</div>
				</div>
			</div>
		<!-- END MAIN CONTENT -->
	<!-- MODAL WHEN CLICK Circulation Modal-->
<!--     <div class="modal fade" id="Modal_PatrSearch" tabindex="-1" role="dialog" aria-labelledby="Modal_PatrSearchModal" aria-hidden="true" data-backdrop="static"> 
		<div class="modal-dialog" style="width:70%;">
			    <div class="modal-content" id="Modal_PatrSearchContent">
				  Remote content load here
			  	</div>
		</div>
	</div> -->
	
	<!-- open modal -->
<!-- <div class="modal fade" id="modal_searchPatronID" tabindex="-1" role="dialog" aria-labelledby="modal_searchPatronID" data-keyboard="false" data-backdrop="static"> 
            <div class="modal-dialog" style="width:55%;">
                  <div class="modal-content" id="Modal_PatrSearchContent">
                        Remote content load here 
                  </div>
            </div>
</div>
 -->
 
 	<!-- MODAL WHEN CLICK Circulation Modal-->
    <div class="modal fade" id="searchWeed" tabindex="-1" role="dialog" aria-labelledby="circulationModal" aria-hidden="true" data-backdrop="static"> 
		<div class="modal-dialog" style="width:70%;">
			    <div class="modal-content" id="searchWeedContent">
				  <!-- Remote content load here -->
			  	</div>
		</div>
	</div>
		
</body>
</html>