<%@ page contentType="text/html; charset=UTF-8" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/ILL/reqAuthorisation.js"></script>
		<script type="text/javascript" src="<%= request.getContextPath() %>/plugin/moment.min.js"></script>
		<link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">
	
   		<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/responsive/2.2.0/css/responsive.dataTables.min.css">
    	<script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/responsive/2.2.0/js/dataTables.responsive.min.js"></script>

</head>

<style>
	fieldset 
	{
		border: 1px solid #ddd !important;
		margin-left: 3%;
		xmin-width: 0;
		padding: 10px;      
		position: relative;
		border-radius:4px;
		background-color:#f5f5f5;
		padding-left:10px!important;
	}	
	
		legend
		{
			font-size:14px;
			font-weight:bold;
			margin-bottom: 0px; 
			width: 35%; 
			border: 1px solid #ddd;
			border-radius: 4px; 
			padding: 5px 5px 5px 10px; 
			background-color: #ffffff;
		}
</style>
<body>
	<div class="panel-group">
	  <div class="panel panel-default">
	    <div class="panel-heading">
	    </div>
	    <div id="viewnewreq" class="panel-collapse collapse in">
	      	<div class="panel-body" id="statusCombine">
	      		<div class="row">
	       			<div class="col-md-12 col-lg-12">
	       				<div class="panel-body">
						<form role="form" class="form-horizontal" id="serachpurathour">
							<!-- <fieldset class="col-md-6">    	
								<legend>Search</legend> -->
								<div class="panel panel-default">
									<div class="panel-body">
										<div class="form-group">
											<div class="col-sm-1">
												<input type="checkbox" id="checkbox-reqid" name="searchMethod" value="reqid">
											</div>
											<div class="col-sm-2">
												<label for="checkbox-reqid">Requestor ID</label>
											</div>
											<div class="col-sm-3">
												<div class="input-group">
													<input type="text" class="form-control" id="txtRequestor" name="txtRequestor">
													<a href="Modal_Vendor" id="searchvendor" class="input-group-addon btn btn-primary" data-toggle="modal" data-target="#modal_vendorSearch">
													<span class="glyphicon glyphicon-option-horizontal"></span></a>
												</div>
											</div>
										</div>
										
										<div class="form-group">
											<div class="col-sm-1"></div>
											<div class="col-sm-2"><label></label></div>
												<div class='col-sm-8 lblRequestorName'></div>
										</div>
										
										<div class="form-group">
											<div class="col-sm-1">
												<input type="checkbox" name="searchMethod" id="checkbox-LibraryId" value="LibraryId">
											</div>
											<div class="col-sm-2">
												<label for="checkbox-LibraryId">Lending Library ID</label>
											</div>
											<div class="col-sm-3">
												<div class="input-group">
													<input type="text" class="form-control" id="txtLibraryId" name="txtLibraryId">
													<a href="Modal_Vendor" id="searchvendor" class="input-group-addon btn btn-primary" data-toggle="modal" data-target="#modal_vendorSearch">
													<span class="glyphicon glyphicon-option-horizontal"></span></a>
												</div>
											</div>
										</div>
										
										<div class="form-group">
											<div class="col-sm-1"></div>
											<div class="col-sm-2"><label></label></div>
												<div class='col-sm-8 lblLibraryName'></div>
										</div>
										
										<div class="form-group">
											<div class="col-sm-1">
												<input type="checkbox" name="searchMethod" id="checkbox-date" value="dateReq">
											</div>
											<div class="col-sm-2">
												<label for="checkbox-date">Date Requested</label>
											</div>
											<div class="col-sm-4">
												<div class="input-daterange input-group" id="datepicker">
													<input type="text" class="input-sm form-control"
														name="start" id="input-startDate" /> <span
														class="input-group-addon">to</span> <input type="text"
														class="input-sm form-control" name="end"
														id="input-endDate" />
												</div>
											</div>
										</div>
										
										<div class="btn-group pull-right ">	
											<button type="button" id="button-retrievereq" class="btn btn-primary" title="Retrieve"><span class="glyphicon glyphicon-log-in" style="color:white"></span></button>
										</div>
										</div>
									</div>					
								
							<!-- </fieldset>		 -->
						</form>
					</div>
						<div class="panel panel-default">
							<div class="panel-heading">
								<div>	
									<button type="button" class="btn btn-primary" id=Reject data-placement="bottom" title="Reject"><i class="glyphicon glyphicon-scissors"></i></button>
									<button type="button" class="btn btn-primary" id="Approve" data-placement="bottom" title="Approve"><i class="glyphicon glyphicon-list-alt"></i></button>
								</div>
							</div>
				 	<div id="search" class="panel-collapse collapse in">
						<div class="panel-body" id="cancel_orderList">
							<table id="reqtable" class="table table-bordered table-striped">
								<thead>
									<tr>
										<th><input type = "checkbox" class="checkAll" id="checkAll"></th>
										<th>Request No</th>
										<th>Author/Title</th>
										<th>ISBN/ISSN</th>
										<th>Requestor</th>
										<th>Request Date</th>
									</tr>
								 </thead>           
							</table>
						</div>
					</div>
				</div>
			</div>
	       </div>   		
	       </div>
	      	</div>
	    </div>
	</div>

	<!-- MODAL WHEN CLICK Search button-vendorSearch -->
	<div class="modal fade" id="modal_vendorSearch" tabindex="-1" role="dialog" aria-labelledby="modal_vendorSearch" data-keyboard="false" data-backdrop="static"> 
		<div class="modal-dialog" style="width:50%;">
			<div class="modal-content" id="modal_vendorSearchContent">
				<!-- Remote content load here  -->
			</div>
		</div>
	</div>
	<!-- Modal END modal search -->

  	<!-- MODAL FOR REVIEW LIST NUMBER -->
  	<div class="modal fade" id="ReviewListNumber" tabindex="-1" role="dialog" aria-labelledby="ReviewListNumber" data-keyboard="false" data-backdrop="static">
		<div class="modal-dialog" role="document" style="width:45%;overflow:auto">
			<div class="modal-content" id="processContent">
				<!-- Remote content load here -->
				</div>
		</div>
	</div>
	<!-- END MODAL FOR REVIEW LIST NUMBER  -->
	
	<!-- MODAL WHEN CLICK Search button review list no -->
	<div class="modal fade" id="modal_reviewlistno" tabindex="-1" role="dialog" aria-labelledby="modal_reviewlistno" data-keyboard="false" data-backdrop="static"> 
		<div class="modal-dialog" style="width:75%;">
			<div class="modal-content" id="modal_reviewlistnoSearchContent">
				<!-- Remote content load here  -->
			</div>
		</div>
	</div>
	<!-- Modal END modal search button review list no -->

  

</body>
</html>