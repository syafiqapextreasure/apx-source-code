<%@ page contentType="text/html; charset=UTF-8" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

	<script type="text/javascript" src="${pageContext.request.contextPath}/js/enquiry/PatronHistory.js"></script>
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
		
		.right {
			text-align: right;
		}
		
		#red {
			color: red;
		}	
		
		fieldset.scheduler-border {
		    border: 1px groove #ddd !important;
		    padding: 0 1.4em 1.4em 1.4em !important;
		    margin: 0 0 1.5em 0 !important;
		    -webkit-box-shadow:  0px 0px 0px 0px #000;
		            box-shadow:  0px 0px 0px 0px #000;
		}

	    legend.scheduler-border {
	        font-size: 1.2em !important;
	        font-weight: bold !important;
	        text-align: left !important;
	        width:auto;
	        padding:0 10px;
	        border-bottom:none;
	    }	
	    
	    #modalWeed {
		    overflow-y: scroll;
		}
		
		.fundtable {
		   margin: auto;
		   width: 50% !important; 
		}
		
		tr{
		    border-top: hidden;
		}
	</style>
</head>

<body>
	<!-- START MAIN CONTENT -->
			<div class="box box-default">
				<div class="panel panel-default">
					<div class="panel-heading">
						<h3 class="panel-title pull-left"></h3>

						<div class="clearfix"></div>
					</div>
					
					<div class="panel-body" id="display_Fund">
						<form class="form-horizontal" name="scanFund" id="scanFund">

							<fieldset class="scheduler-border">
							<legend class="scheduler-border">Patron Details</legend>
								<div class="row">
								
									
			            			<div class="form-group">
										<label class="col-sm-2">Patron ID</label>
											<div class="col-sm-3">
												<div class="input-group">
													<input type="text" class="form-control" id="lblPatronID" name="lblPatronID">
														<a href="Modal_PatrSearch" id="searchPatronID" class="input-group-addon btn btn-primary searchheadLib" data-toggle="modal" data-target="#modal_searchPatronID">
															<span class="glyphicon glyphicon-option-horizontal"></span></a>
												</div>
											</div>
									</div> 
									
									<div class="form-group">
						            	<label class="col-sm-2">Name</label>
					                     	<div class="col-sm-3 col-md-3 lblName">
					                        </div>
					                </div>
									
									<div class="form-group">
						            	<label class="col-sm-2">Membership Date</label>
					                     	<div class="col-sm-3 col-md-3 lblMemberDate">
					                        </div>
					                      			
					                    <label class="col-sm-2">Expiry Date</label>
	               							<div class="col-sm-3 col-md-3 lblExpiry">
	               							</div>
					                </div>
								
					  				<div class="form-group">
						            	<label class="col-sm-2">Membership Category</label>
					                     	<div class="col-sm-3 col-md-3 lblCategory">
					                        </div>
					                      			
					                    <label class="col-sm-2">Status</label>
	               							<div class="col-sm-4 col-md-4 lblStatus">
	               							</div>
					                </div>	 
								
								</div>
							</fieldset>
							</form>						
						
						<br>
						<button id="refresh" class="btn btn-primary"><span class="fa fa-refresh" style="color:white" title="Refresh"></span></button>
						
							<table  class="table table-bordered table-striped" id="table-PatronHistory">
								<thead>
									<tr>
										<th>Accession No</th>
										<th>Borrow Date/Time</th>
										<th>Due Date/Time</th>
										<th>Return Date/time</th>
										<th>Status</th>
										<th>Item Status</th>
										<th>No of Renewal</th>
										<th>Item Category</th>
										<th>Charged Officer</th>
										<th>Discharged Officer</th>
										<th>Title</th>
										<th>Renew Date/Time</th>
										<th>Renew Officer</th>
									</tr>
								</thead>
								<tbody>
								</tbody>
							</table>
					</div>
				</div>
			</div>
		<!-- END MAIN CONTENT -->
		
		
		<!-- MODAL WHEN CLICK HEAD LIB -->
		<div class="modal fade" id="modal_searchPatronID" tabindex="-1" role="dialog" aria-labelledby="modal_searchPatronID" data-keyboard="false" data-backdrop="static"> 
			<div class="modal-dialog" style="width:55%;">
				<div class="modal-content" id="Modal_PatrSearchContent">
					<!-- Remote content load here  -->
				</div>
			</div>
		</div>
		<!-- Modal END modal search HEAD LIB -->
		
		


</body>
</html>