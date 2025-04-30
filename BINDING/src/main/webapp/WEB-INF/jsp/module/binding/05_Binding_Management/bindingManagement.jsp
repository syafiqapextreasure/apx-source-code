<%@ page contentType="text/html; charset=UTF-8" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

	<script type="text/javascript" src="${pageContext.request.contextPath}/js/binding/bindMngmnt.js"></script>
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
							
							<!-- Binder Type-->
							<div class="form-group">
								<div class="col-sm-2 col-md-2">
									<label for="radio-binder">Binding Item </label>
								</div>
								<div class="col-sm-6">
								
								<div class="col-sm-4">
									<label><input type="radio" name="statusRadio" value="M" checked>Monograph</label>
								</div>
								<div class="col-sm-3">
									<label><input type="radio" name="statusRadio" value="S">&nbsp Serials</label>
								</div>
								</div>
							</div>
							
							<br>
							
							<!-- Binder -->
							<div class="form-group">
								<div class="col-sm-1 col-md-1">
									<input type="checkbox" name="checkbox" id="checkbox-binder" name="searchMethod" value="binderVal">
								</div>
								<div class="col-sm-2 col-md-2">
									<label for="radio-binder">Binder: </label>
								</div>
								<div class="col-sm-4">
									<div class="input-group">
											<input type="text" class="form-control" id="binder" name="binder">
												<a href="Modal_Vendor" id="searchBinder" class="input-group-addon btn btn-primary searchpatr" data-toggle="modal" data-target="#modal_vendorSearch">
												<span class="glyphicon glyphicon-option-horizontal"></span></a>
									</div>
								</div>
								<div class="">
									<div class="col-sm-4 col-md-4 div-bindername"></div>
								</div>
							</div>
							
							<!-- Control No -->
							<div class="form-group">
								<div class="col-sm-1 col-md-1">
									<input type="checkbox" name="checkbox" id="checkbox-controlNo" name="searchMethod" value="controlNoVal">
								</div>
								<div class="col-sm-2 col-md-2">
									<label for="radio-binder">Control No: </label>
								</div>
								<div class="col-sm-4">
									<div class="input-group">
											<input type="text" class="form-control ctrlno" id="ctrlno" name="ctrlNo">
												<a href="RetrieveTitleModal?action=20" id="searchAccsion" class="input-group-addon btn btn-primary searchpatr" data-toggle="modal" data-target="#titleSearch">
												<span class="glyphicon glyphicon-option-horizontal"></span></a>
									</div>
								</div>
								<!-- <div class="">
									<div class="col-sm-4 col-md-4 div-bindername"></div>
								</div> -->
							</div>
			
							<!-- Binding Date -->
							<div class="form-group">
								<div class="col-sm-1 col-md-1">
									<input type="checkbox" name="checkbox" id="checkbox-bindDate" name="searchMethod" value="bindDateVal">
								</div>
								<div class="col-sm-2 col-md-2">
									<label for="checkbox-date">Date Sent from</label>
								</div>
								<div class="col-sm-4">
									<div class="input-daterange input-group" id="datepicker">
										<input type="text" class="input-sm form-control"  placeholder="Starting Date" name="start"
											id="input-startDate" autocomplete="off"/> <span class="input-group-addon">to</span>
										<input type="text" class="input-sm form-control" name="end" id="input-endDate" autocomplete="off"/>
									</div>
								</div>
							</div>

						
							<div class="btn-group pull-right ">	
									<div class="col-md-1"><button type="button" id="bindMngmntReterive" class="btn btn-primary" title="Retrieve"><span class="glyphicon glyphicon-log-in" style="color:white"></span></button></div>
  									<div class="col-md-1"></div>
  									<div class="col-md-1"><button type="button" id="refresh" class="btn btn-primary" title="Refresh" data-original-title="Cancellation"><i class="glyphicon glyphicon-refresh" aria-hidden="true"></i></button></div>
  									<div class="col-md-1"></div>
  									<div class="col-md-1"><button type="button" id="cancellation" class="btn btn-primary" title="Cancellation" data-original-title="Cancellation"><i class="glyphicon glyphicon-remove-sign" aria-hidden="true"></i></button></div>
  									<!-- <button type="button" class="btn btn-primary" id="refresh" href="" data-placement="top" title="" onclick="refresh()" data-original-title="Refresh"><span class="glyphicon glyphicon-refresh" style="color:white"></span></button>
  									<div class="col-md-1"><button type="button" id="cancellation" class="btn btn-primary" title="Cancellation" data-original-title="Cancellation" data-toggle='modal' data-target="#modalInvoice"><span class="glyphicon glyphicon-hourglass" style="color:white"></span>Cancellation</button></div> -->
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
							<table class="table table-hover" id="table-BindingMngmt">
								<thead>
									<tr>
										<th><input name="select_all" value="1" id="example-select-all" type="checkbox" /></th>
										<th>Binding No</th>
										<th>Binder</th>
										<th>Title</th>
										<th>Call No</th>
										<th>Accession</th>
										<th>Status</th>
										<th>Sent Date</th>
										<th>Expected Date</th>
										<th>Order No</th>
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
		
		<!-- MODAL WHEN CLICK Search CONTROL NUMBER -->
		<div class="modal fade" id="titleSearch" tabindex="-1" role="dialog" aria-labelledby="titleSearch" data-keyboard="false" data-backdrop="static">
			<div class="modal-dialog" role="document" style="width:60%;overflow:auto">
				<div class="modal-content">
				<!-- Remote content load here -->
				</div>
			</div>
		</div>
		<!-- Modal END modal search CONTROL NUMBER-->

</body>
</html>