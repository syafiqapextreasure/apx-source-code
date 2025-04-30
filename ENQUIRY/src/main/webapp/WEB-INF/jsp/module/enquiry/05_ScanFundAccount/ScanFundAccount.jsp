<%@ page contentType="text/html; charset=UTF-8" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

	<script type="text/javascript" src="${pageContext.request.contextPath}/js/enquiry/scanfund.js"></script>
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
						<!-- <button class="btn btn-primary pull-right" data-toggle='modal' data-target='#searchWeed'><i class="glyphicon glyphicon-search" title="Search" data-searchfor="Weeding"></i></button> -->
						<div class="clearfix"></div>
					</div>
					
					<div class="panel-body" id="display_Fund">
						<form class="form-horizontal" name="scanFund" id="scanFund">
							<div class="form-group">
								<div class="col-sm-1"></div>
								<div class="col-sm-2">
									<label>Fund Account : </label>
								</div>
								<div class='col-sm-4'>
									<select class="form-control" id="Fund" name="Fund">
										<jsp:include page="../Select_Fund.jsp"></jsp:include> 
									</select>
								</div>
								
								<div class='col-sm-2 btn-group pull-right'>
									<div class="col-md-1"><button type="button" id="fundReterive" class="btn btn-primary" title="Retrieve"><span class="glyphicon glyphicon-log-in" style="color:white"></span></button></div>
								</div>
							</div>

							<fieldset class="scheduler-border">
							<legend class="scheduler-border"></legend>
								<div class="row">
									
									<table class="table table-condensed fundtable">
										<thead>
									    	<tr>
									      	</tr>
									    </thead>
   	 									<tbody>
									    	<tr>
									        	<td></td>
									        	<td><button type="button" class="btn btn-default Allocation" data-toggle="modal" data-target="#modalFund" data-rowid="1" data-whatever="Allocation Transactions for " >Allocation&ensp;&ensp;</button></td>
									        	<td class="right AllocationVal"></td>
									      	</tr>
										   	<tr>
										    	<td class="right">+</td>
										        <td><button type="button" class="btn btn-default Increase" data-toggle="modal" data-target="#modalFund" data-rowid="2" data-whatever="Increase Allocation Transactions for ">Increase&ensp;&ensp;&ensp;</button></td>
										        <td class="right IncreaseVal"></td>
										   	</tr>
									      	<tr>
									        	<td class="right">-</td>
									        	<td><button type="button" class="btn btn-default Decrease" data-toggle="modal" data-target="#modalFund" data-rowid="3" data-whatever="Decrease Allocation Transactions for ">Decrease&ensp;&ensp;</button></td>
									        	<td class="right DecreaseVal"></td>
									        	<td class="right cTotalAlloc"></td>
									      	</tr>
									      	<tr style="vertical-align:top">
									        	<td class="right"></td>
									        	<td></td>
									        	<td class="right">____________</td>
									      	</tr>
      										<tr>
										        <td class="right">-</td>
										        <td><button type="button" class="btn btn-default Commitment" data-toggle="modal" data-target="#modalFund" data-rowid="4" data-whatever="Commitment Transactions for ">Commitment</button></td>
										        <td class="right"></td>
										        <td class="right CommitmentVal"></td>
      										</tr>
									       	<tr>
									        	<td class="right">-</td>
									        	<td><button type="button" class="btn btn-default Payment" data-toggle="modal" data-target="#modalFund" data-rowid="7" data-whatever="Payment Transactions for ">Payment&ensp;&ensp;&ensp;</button></td>
									        	<td class="right"></td>
									        	<td class="right PaymentVal"></td>
									      	</tr>
										    <tr>
										    	<td class="right"></td>
										        <td></td>
										        <td></td>
										        <td class="right">____________</td>
										    </tr>
										 	<tr>
										  		<td class="right"></td>
										        <td></td>
										        <td></td>
										        <td class="right cBalAlloc"></td>
										  	</tr>
										  	<tr>
										        <td class="right"></td>
										        <td></td>
										        <td></td>
										        <td class="right">____________</td>
										   	</tr>
    									</tbody>
  									</table>
								</div>
							</fieldset>
						</form>
					</div>
				</div>
			</div>
		<!-- END MAIN CONTENT -->
		
		
		<div class="modal fade" id="modalFund" tabindex="-1" role="dialog" aria-labelledby="modalFund" aria-hidden="true" data-keyboard="false" data-backdrop="static">
			<div class="modal-dialog" role="document" style="width:75%">
				<div class="modal-content">
					<!-- Modal content-->
						<div class="modal-header">
							<h5 class="modal-title" id="modalName" align="center">form</h5>
					    	<button type="button" id="closeModalFund" class="close" data-dismiss="modal" aria-label="Close">
					        	<span aria-hidden="true">&times;</span>
					        </button>
						</div>
						
						<div class="modal-body">
							<table id="Fund_table" class="table table-bordered table-striped">
							 	<thead>
							 		<tr>
							 			<th>Trx#</th>
										<th>Date</th>
										<th>Trx Code</th>
										<th>Increase</th>
										<th>Decreae</th>
							 		</tr>
							 	</thead>
							 	<tfoot>
									<tr>
								    	<th colspan="3"></th>
								    	<th></th>
								    	<th></th>
								    </tr>
								 </tfoot>
							</table>
							
							<div class="modal-footer">
								<button type="button" id="close" class="btn btn-default" data-dismiss="modal">Close</button>
							</div>
							
							
						</div>
					<!-- END Modal content-->
				</div>
			</div>
		</div>


</body>
</html>