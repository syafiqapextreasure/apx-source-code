<%@ page contentType="text/html; charset=UTF-8" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

	<script type="text/javascript" src="${pageContext.request.contextPath}/js/enquiry/fundDetails.js"></script>
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
							<button type="button" class="btn btn-primary pull-right" id="viewAll">View All</button>
						<!-- <button class="btn btn-primary pull-right" data-toggle='modal' data-target='#searchWeed'><i class="glyphicon glyphicon-search" title="Search" data-searchfor="Weeding"></i></button> -->
						<div class="clearfix"></div>
					</div>
					
					<div class="panel-body" id="display_Fund">
						<form class="form-horizontal" name="scanFund" id="scanFund">
							
							<fieldset class="scheduler-border">
							<legend class="scheduler-border">Note</legend>
								<div class="row">
									
									<h4>This program will list all Fund Master records. You can enquire for funds that are running short by amount or by percentage by click on option button. Enter value desired. Click on view button.</h4>
								</div>
							</fieldset>
							
							<fieldset class="scheduler-border">
							<legend class="scheduler-border">Option</legend>
								<div class="row">
									<div class="form-group">
										<div class="col-sm-2">
											<label><input type="radio" name="radioOption" value="amt">&nbsp Amount</label>
										</div>
										<div class="col-sm-2">
											<label><input type="radio" name="radioOption" value="percen">&nbsp Percentage</label>
										</div>
									</div>
									
									<div class="form-group">
										<div class="col-sm-2">
											<label>Enter Value :</label>
										</div>
										<div class='col-sm-2'>
											<input type="text" class="form-control" id="txtEntry" name="txtEntry">
										</div>
										
										<div class='col-sm-4 btn-group pull-right'>
											<div class="col-md-6">
												<button type="button" class="btn btn-primary" id="view">View</button>
												<!-- <button type="button" id="fundReterive" class="btn btn-primary" title="Retrieve"><span class="glyphicon glyphicon-log-in" style="color:white"></span>
												</button> -->
											</div>
										</div>
									</div>
								</div>
							</fieldset>
							</form>						
						
						<br>
							<table  class="table table-bordered table-striped" id="table-FundDetails">
								<thead>
									<tr>
										<th>Fund</th>
										<th>Description</th>
										<th>Allocation</th>
										<th>+Increment</th>
										<th>-Decrement</th>
										<th>-Commitment</th>
										<th>-Payment</th>
										<th>Balance</th>
									</tr>
								</thead>
								<tbody>
								</tbody>
							</table>
					</div>
				</div>
			</div>
		<!-- END MAIN CONTENT -->
		
		
		


</body>
</html>