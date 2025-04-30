<%@ page contentType="text/html; charset=UTF-8" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

	<script type="text/javascript" src="${pageContext.request.contextPath}/js/ListingFnd/ErrorInSupply.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath() %>/plugin/moment.min.js"></script>
	<link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">
	
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

					<div class="panel-body" id="display_foundationListing">
						<form class="form-horizontal" name="foundationListingForm" id="foundationListingForm">
							<jsp:include page="../headerSearchCriteriaListingFND.jsp"></jsp:include> 
						</form>
						
						<br><br>
						<table id="foundationTable" class="table table-bordered table-striped">
						 <thead>
						 	<tr>
						 		<th>No</th>
						 		<th>Error in Supply Code</th>
								<th>Brief Description</th>
								<th>Full Description</th>
						 	</tr>
						 </thead>
						</table>
					</div>
				</div>
			</div>
		<!-- END MAIN CONTENT -->
		
		
</body>
</html>