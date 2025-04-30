<%@ page contentType="text/html; charset=UTF-8" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

	<script type="text/javascript" src="${pageContext.request.contextPath}/js/enquiry/paymenthistory.js"></script>
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
		
		/* td {
		 /*  white-space: nowrap; */
		  text-overflow: ellipsis;
		  overflow: hidden;
		} */

		/* #table-PayHistory {
		  table-layout: fixed;
		} */
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
						<form class="form-horizontal" name="payHisForm" id="payHisForm">
							<input type='hidden' class="module">
														
							<div class="form-group">
								<div class="col-sm-2 col-md-2">
									<label>Vendor: </label>
								</div>
								<div class="col-sm-4">
									<div class="input-group">
											<input type="text" class="form-control" id="vendor" name="vendor">
												<a href="Modal_Vendor" id="searchBinder" class="input-group-addon btn btn-primary searchpatr" data-toggle="modal" data-target="#modal_vendorSearch">
												<span class="glyphicon glyphicon-option-horizontal"></span></a>
									</div>
								</div>
								<div class="">
									<div class="col-sm-4 col-md-4 div-vendorname"></div>
								</div>
							</div>
							
							<div class="form-group">
								<div class="col-sm-2 col-md-2">
									<label><input type="radio" name="radioOption1" value="doctRange">&nbsp Document Range</label>
								</div>
							</div>
							
							<div class="form-group">
								<div class="col-sm-2 col-md-2">
								</div>
								<div class="col-sm-3">
									<label><input type="radio" name="radioOption2" value="doctRangeinv">&nbsp Invoice No</label>
								</div>
								<div class="col-sm-3">
									<label><input type="radio" name="radioOption2" value="doctRangevoc">&nbsp Voucher No</label>
								</div>
							</div>
							
							<div class="form-group">
								<div class="col-sm-2 col-md-2">
								</div>
								<div class="col-sm-3">
									<label><input type="radio" name="radioOption2" value="doctRangecheq">&nbsp Cheque No</label>
								</div>
								<div class="col-sm-3">
									<label><input type="radio" name="radioOption2" value="doctRangeref">&nbsp Reference No (Payment)</label>
								</div>
							</div>
							
							<div class="form-group">
								<div class="col-sm-2 col-md-2">
								</div>
								<div class="col-sm-4">
									<div class="input-group">
										<span class="input-group-addon">From</span><input type="text" class="input-sm form-control"  name="startDoc"
											id="input-startDoc" autocomplete="off"/> <span class="input-group-addon">to</span>
										<input type="text" class="input-sm form-control" name="endDoc" id="input-endDoc" autocomplete="off"/>
									</div>
								</div>
							</div>
			
							
							<div class="form-group">
								<div class="col-sm-2 col-md-2">
									<label><input type="radio" name="radioOption1" value="dateRange">&nbsp Date Range</label>
								</div>
							</div>
							
							<div class="form-group">
								<div class="col-sm-2 col-md-2">
								</div>
								<div class="col-sm-3">
									<label><input type="radio" name="radioOption3" value="dateRangeinvo">&nbsp Invoice Created</label>
								</div>
								<div class="col-sm-3">
									<label><input type="radio" name="radioOption3" value="dateRangeRef">&nbsp Reference (Payment)</label>
								</div>
							</div>
							
							<div class="form-group">
								<div class="col-sm-2 col-md-2">
								</div>
								<div class="col-sm-3">
									<label><input type="radio" name="radioOption3" value="dateRangevoc">&nbsp Voucher</label>
								</div>
								<div class="col-sm-3">
									<label><input type="radio" name="radioOption3" value="dateRangeproc">&nbsp Processed</label>
								</div>
							</div>
							
							<div class="form-group">
								<div class="col-sm-2 col-md-2">
								</div>
								<div class="col-sm-3">
									<label><input type="radio" name="radioOption3" value="dateRangecheq">&nbsp Cheque</label>
								</div>
							</div>
							
							<div class="form-group">
								<div class="col-sm-2 col-md-2">
								</div>
								<div class="col-sm-4">
									<div class="input-daterange input-group">
										<span class="input-group-addon">From</span><input type="text" class="input-sm form-control"  name="startDate"
											id="input-startDate" autocomplete="off"/> <span class="input-group-addon">to</span>
										<input type="text" class="input-sm form-control" name="endDate" id="input-endDate" autocomplete="off"/>
									</div>
								</div>
							</div>

						
							<div class="btn-group pull-right ">	
									<div class="col-md-1"><button type="button" id="paymentReterive" class="btn btn-primary" title="Retrieve"><span class="glyphicon glyphicon-log-in" style="color:white"></span></button></div>
  									<div class="col-md-1"></div>
  									<div class="col-md-1"><button type="button" id="refresh" class="btn btn-primary" title="Refresh" data-original-title="Cancellation"><i class="glyphicon glyphicon-refresh" aria-hidden="true"></i></button></div>
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
							<table  class="display nowrap" width="100%" id="table-PayHistory">
								<thead>
									<tr>
										<th>Invoice No</th>
										<th>Invoice Date</th>
										<th>Order No</th>
										<th>Cheque No</th>
										<th>Cheque Date</th>
										<th>Voucher No</th>
										<th>Voucher Date</th>
										<th>Ref No</th>
										<th>Ref Date</th>
										<th>Amount</th>
										<th>Vendor</th>
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