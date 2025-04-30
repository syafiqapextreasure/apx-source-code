<%@ page contentType="text/html; charset=UTF-8" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

	<script type="text/javascript" src="${pageContext.request.contextPath}/js/binding/claimBinding.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath() %>/plugin/moment.min.js"></script>
	<link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">

	<script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/1.10.16/js/jquery.dataTables.js"></script> 
	<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.16/css/jquery.dataTables.css">
    <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/responsive/2.2.0/css/responsive.dataTables.min.css">
    <script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/responsive/2.2.0/js/dataTables.responsive.min.js"></script>

	<style type="text/css">	
		.center {
			text-align: center;
		}	
		
		#red {
			color: red;
		}	
	</style>
</head>

<body>
	<!-- START MAIN CONTENT -->
	<div class="container">
		<div class="panel-group" id="accordion" role="tablist"
			aria-multiselectable="true">
			<div class="panel panel-default">
				<div class="panel-heading" role="tab" id="headingOne">
					<h4 class="panel-title">
						<a role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseSearch" aria-expanded="true"
							aria-controls="collapseSearch"> Search </a>
					</h4>
				</div>
				<div id="collapseSearch" class="panel-collapse collapse in"
					role="tabpanel" aria-labelledby="headingOne">
					<div class="panel-body">
						<!-- Search Form -->
						<form class="form-horizontal" name="searchForm" id="searchForm">
							
							<div class="form-group">
									<!-- <div class="col-sm-1"></div> -->
									<div class="col-sm-3"><label>Search</label></div>
									<div class="col-sm-6">
										<input type="text" class="form-control criteria" name="criteria" id="criteria">					
										<!--Date -->
										<div class="input-daterange input-group" id="datepicker">
											<input type="text" class="form-control"
												name="start" id="input-startDate" /> <span
												class="input-group-addon">to</span> <input type="text"
												class="form-control" name="end" id="input-endDate" />
										</div>
										<!-- END Date -->
									</div>
							</div>
							
							<div class="form-group">
								<div class="col-sm-3"></div>
									<div class="col-sm-3">
										<label><input type="radio" name="radioOption" value="bindercode" checked>&nbsp Binder Code</label>
									</div>
									<div class="col-sm-4">
										<label><input type="radio" name="radioOption" value="dateClaims">&nbsp Expected date range</label>
									</div>
							</div>

							<div class="btn-group pull-right ">	
									<div class="col-md-1"><button type="button" id="claimsReterive" class="btn btn-primary" title="Retrieve"><span class="glyphicon glyphicon-log-in" style="color:white"></span></button></div>
  									<div class="col-md-1"></div>
  									<div class="col-md-1"><button type="button" id="Claims" class="btn btn-primary" title="Claims" data-original-title="Claims"><!-- <i class="fa fa-ad" aria-hidden="true"></i> -->Claims</button></div>
							</div>

						</form>
					</div>
				</div>
			</div>
			<div class="panel panel-default">
				<div class="panel-heading">
					<h4 class="panel-title">
						<a data-toggle="collapse" data-parent="#form_parent"
							href="#panel-result">Result</a>
					</h4>
				</div>
				<div id="panel-result" class="panel-collapse collapse">
					<div class="panel-body">
						<input type="hidden" id="input-parameters">
						<!-- Search Result -->
						<div id="search-results">
						<!-- <table class="display nowrap" width="100%" id="table-BindClaim"> -->
							<table class="table table-hover" id="table-BindClaim">
								<thead>
									<tr>
										<th><input name="select_all" value="1" id="example-select-all" type="checkbox" /></th>
										<th>Binding No</th>
										<th>Reference No</th>
										<th>Title</th>
										<th>Binder</th>
										<th>Date Sent</th>
										<th>Date Expected</th>
									</tr>
								</thead>
								<tbody>
								</tbody>
							</table> 
						</div>
					</div>
				</div>
		</div>
		</div>
	</div>		
	<!-- END MAIN CONTENT -->
		

		

		
		
		<!-- MODAL WHEN CLICK Search button-search binder -->
	    <div class="modal fade" id="modal_vendorSearch" tabindex="-1" role="dialog" aria-labelledby="modal_vendorSearch" aria-hidden="true" data-keyboard="false" data-backdrop="static"> 
			<div class="modal-dialog" style="width:50%;">
				    <div class="modal-content" id="modal_vendorSearchContent">
					  <!-- Remote content load here  -->
				  	</div>
			</div>
		</div>
		<!-- Modal END modal search  Binder-->

</body>
</html>