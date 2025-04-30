<%@ page contentType="text/html; charset=UTF-8" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

	<script type="text/javascript" src="${pageContext.request.contextPath}/js/enquiry/borrower.js"></script>
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

					<div class="panel-body" id="display_borrower">
						<form class="form-horizontal" name="borrowerForm" id="borrowerForm">
							<br>
							<input type="hidden" class="form-control" id="libname">
							<div class="form-group">
								<div class="col-sm-2 col-md-2"><label>Activity Date</label></div>
									<div class="col-sm-4">
										<div class="input-daterange input-group">
											<input type="text" class="input-sm form-control"  name="startDate"
												id="input-startDate" autocomplete="off"/> <span class="input-group-addon">to</span>
											<input type="text" class="input-sm form-control" name="endDate" id="input-endDate" autocomplete="off"/>
										</div>
									</div>
							</div>
						
							<div class="form-group">
									<!-- <div class="col-sm-1"></div> -->
										<div class="col-sm-2">
											<label><input type="checkbox" class="form-check-input" id="checkboxpatcat" name="checkboxpatcat" value="input-patcat">&nbsp <strong>Patron Category</strong></label>
											<!-- <label>Currency<span id="red">*</span></label> -->
										</div>
											<div class='col-sm-4'>
												<select class="form-control" id="patronCate" name="patronCate">
													<jsp:include page="../Select_PatCate.jsp"></jsp:include> 
												</select>
											</div>
							</div>
							
							<div class="form-group">
									<!-- <div class="col-sm-1"></div> -->
										<div class="col-sm-2">
											<label><input type="checkbox" class="form-check-input" id="checkboxbrnc" name="checkboxbrnc" value="input-brnc">&nbsp <strong>Branch</strong></label>
										</div>
											<div class='col-sm-4'>
												<select class="form-control" id="branch" name="branch">
													<jsp:include page="../Select_Brnc.jsp"></jsp:include> 
												</select>
											</div>
							</div>
							
							<div class="form-group">
								<div class="col-sm-2"><label>Minimum Borrowing</label></div>
								<div class="col-sm-2">
									<input type="text" class="form-control" id="minBorrow" name="minBorrow">
								</div>
							</div>
							
							<div class="btn-group pull-right ">	
									<div class="col-md-1"><button type="button" id="borrowerReterive" class="btn btn-primary" title="Retrieve"> Retrieve</button></div>
  									<!-- <div class="col-md-1"></div>
  									<div class="col-md-1"><button type="button" id="print" class="btn btn-primary" title="Print"><i class="glyphicon glyphicon-print" aria-hidden="true"></i></button></div> -->
							</div>
						</form>
						<br><br>
						<table id="borrower" class="table table-bordered table-striped">
						 <thead>
						 	<tr>
						 		<th>Patron ID</th>
								<th>Name</th>
								<th>Department</th>
								<th>Designation</th>
								<th>Borrowing Transaction</th>
								<th>No of Lost Item</th>
						 	</tr>
						 </thead>
						</table>
					</div>
				</div>
			</div>
		<!-- END MAIN CONTENT -->
		
		
</body>
</html>