<%@ page contentType="text/html; charset=UTF-8" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

	<script type="text/javascript" src="${pageContext.request.contextPath}/js/ILL/returnILL.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath() %>/plugin/moment.min.js"></script>
	<link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">
	
	<!-- <script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/1.10.16/js/jquery.dataTables.min.js"></script>  -->
   <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/responsive/2.2.0/css/responsive.dataTables.min.css">
    <script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/responsive/2.2.0/js/dataTables.responsive.min.js"></script>

	<script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/buttons/1.6.1/js/dataTables.buttons.min.js"></script>
	<script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/buttons/1.6.1/js/buttons.flash.min.js"></script>
	<script type="text/javascript" charset="utf8" src="https://cdnjs.cloudflare.com/ajax/libs/jszip/3.1.3/jszip.min.js"></script>
	<script type="text/javascript" charset="utf8" src="https://cdnjs.cloudflare.com/ajax/libs/pdfmake/0.1.53/pdfmake.min.js"></script>
	<script type="text/javascript" charset="utf8" src="https://cdnjs.cloudflare.com/ajax/libs/pdfmake/0.1.53/vfs_fonts.js"></script>
	<script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/buttons/1.6.1/js/buttons.html5.min.js"></script>
	<script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/buttons/1.6.1/js/buttons.print.min.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath() %>/plugin/buttons.print.js"></script>


	<style type="text/css">	
	
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
		
		

	</style>
</head>

<body>
	<!-- START MAIN CONTENT -->
			<div class="box box-default">
				<div class="panel panel-default">
					<div class="panel-heading">
						
						<!-- <div class="clearfix"></div> -->
					</div>

					<div class="panel-body" id="display_illreturn">
						<form class="form-horizontal" name="illreturnForm" id="illreturnForm">
							<br>
							
							<div class="form-group">
									<!-- <div class="col-sm-1"></div> -->
										<div class="col-sm-2">
											<label><strong>Lending Library</strong></label>
										</div>
											<div class='col-sm-4'>
												<select class="form-control" id="plkupLendingLib" name="plkupLendingLib">
													<jsp:include page="../SelectIllPatr.jsp"></jsp:include> 
												</select>
											</div>
							</div>
							
							<div class="form-group">
								<div class="col-sm-2"><label>Library Name</label></div>
								<div class="col-sm-6 lblName"></div>
							</div>
							
							<div class="form-group">
								<div class="col-sm-2"><label>Address</label></div>
								<div class="col-sm-6 lblLibAdd1"></div>
							</div>
							
							<div class="form-group">
								<div class="col-sm-2"></div>
								<div class="col-sm-6 lblLibAdd2"></div>
							</div>
							
							<div class="form-group">
								<div class="col-sm-2"></div>
								<div class="col-sm-6 lblLibAdd3"></div>
							</div>
							
							<div class="form-group">
								<div class="col-sm-2 col-md-2"><label>Date Returned</label></div>
									<div class="col-sm-2">
										<div class="input-daterange input-group">
											<input type="text" class="input-sm form-control"  name="txtDateReturned"
												id="txtDateReturned" autocomplete="off"/> 
										</div>
									</div>
							</div>
					
							
							<div class="btn-group pull-right ">	
									<div class="col-md-1"><button type="button" id="returnReterive" class="btn btn-primary" title="Retrieve"><span class="glyphicon glyphicon-log-in" style="color:white"></span></button></div>
  									<!-- <div class="col-md-1"></div>
  									<div class="col-md-1"><button type="button" id="print" class="btn btn-primary" title="Print"><i class="glyphicon glyphicon-print" aria-hidden="true"></i></button></div> -->
							</div>
						</form>
						<br><br>
						<div class="panel panel-default">
							<div class="panel-heading">
								<!-- <div>
									<h3>Item Details</h3>
								</div> -->
								<div class="row">
									<div class="col-md-4"><button type="button" class="btn btn-primary" id="preview" data-placement="bottom" title="Preview"><span class="glyphicon glyphicon-eye-open" style="color:white"></span></button>
									<button type="button" class="btn btn-primary" id="return" data-placement="bottom" title="Return"><span class="glyphicon glyphicon-export" style="color:white"></span></button></div>
									<div class="col-md-4"></div>
									<div class="col-md-2">
										<div class="form-check">
											<input type="checkbox" class="form-check-input" id="print" name="print" value="print" checked>
											<label class="form-check-label" for="print">Print Notification Letter</label>
										</div>
									</div>
								</div>
								
							</div>
				 	<div id="search" class="panel-collapse collapse in">
						<div class="panel-body"> <!-- id="cancel_orderList"> -->
							<table id="returnIll" class="table table-bordered table-striped">
							 <thead>
							 	<tr>
							 		<th><input name="select_all" value="1" id="example-select-all" type="checkbox" /></th>
							 		<th>Reference No</th>
									<th>Control No</th>
									<th>Title</th>
									<th>Copies</th>
									<th>Requested Date</th>
									<th>Expected Date</th>
									<th>Received Date</th>
							 	</tr>
							 </thead>
							</table>
						</div>
					</div>
				</div>
					
					</div>
				</div>
			</div>
		<!-- END MAIN CONTENT -->
		
		
</body>
</html>