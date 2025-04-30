<%@ page contentType="text/html; charset=UTF-8" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

	<script type="text/javascript" src="${pageContext.request.contextPath}/js/enquiry/weeding.js"></script>
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
	</style>
</head>

<body>
	<!-- START MAIN CONTENT -->
			<div class="box box-default">
				<div class="panel panel-default">
					<div class="panel-heading">
						<h3 class="panel-title pull-left"></h3>
						<button class="btn btn-primary pull-right" data-toggle='modal' data-target='#searchWeed'><i class="glyphicon glyphicon-search" title="Search" data-searchfor="Weeding"></i></button>
						<div class="clearfix"></div>
					</div>
					
					<div class="panel-body" id="display_Weedig">
						<table id="Weeding_table" class="table table-bordered table-striped">
						 <thead>
						 	<tr>
						 		<th>Trxno</th>
								<th>No</th>
								<th>Accession No</th>
								<th>Title</th>
								<th>Weed Date</th>
						 		<th>Action</th>
						 	</tr>
						 </thead>
						</table>
					</div>
				</div>
			</div>
		<!-- END MAIN CONTENT -->
		
	
		
		<!-- Modal for search -->
		<div class="modal fade" id="searchWeed" tabindex="-1" role="dialog" aria-labelledby="searchWeed" data-keyboard="false" data-backdrop="static">
			<div class="modal-dialog" role="document" style="width:55%;overflow:auto">
				<div class="modal-content">
					<!-- Modal content-->
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-label="Close" id="closesearchWeed"><span aria-hidden="true">&times;</span></button>
						<h4 class="modal-title" id="myModalLabel">Search</h4>
					</div>
					
					<div class="modal-body">
						<div class="panel panel-default" id="form_parent">
							<div class="panel-group">
								<div class="panel panel-default">
									<div class="panel-heading">
										<h4 class="panel-title">
											<a data-toggle="collapse" data-parent="#form_parent" href="#search-monograph"></a>
										</h4>
									</div>
									<div id="search-monograph" class="panel-collapse collapse in">
										<div class="panel-body">
											<form role="form" class="form-horizontal">
											
												<div class="form-group">
													<div class="col-sm-3">
														<label class="radio-inline"><input type="radio" id="search-type" name="searchSelectionWeed" value="accNo" checked>Accession No</label>
													</div>
												
													<div class="col-sm-3">
														<label class="radio-inline"><input type="radio" id="search-type" name="searchSelectionWeed" value="weedDate">Weed Date</label>
													</div>
													
													<div class="col-sm-3">
														<label class="radio-inline"><input type="radio" id="search-type" name="searchSelectionWeed" value="title">Title</label>
													</div>
												</div>
												
												<div class="form-group">
													<div class="col-sm-12 col-md-12">
														<input type="text" class="form-control" name="input-criteriaWeed" id="input-criteriaWeed" autocomplete="off">
													</div>
												</div>
												
												<div class="form-group">
													<div class="col-sm-6 col-md-6">
														<!-- Sent Date -->
														<div class="input-daterange input-group" id="datepickerSentDate">
															<input type="text" class="form-control"
																name="sDate" id="input-sentDate" /> <span
																class="input-group-addon">to</span> <input type="text"
																class="form-control" name="eDate" id="endSentDate" />
														</div>
														<!-- END Sent Date -->
													</div>
												</div>
								
												<div class="form-group">
													<div class="col-sm-8 col-md-8">
														<button type="button" class="btn btn-info" id="search">
															<span class="glyphicon glyphicon-search"></span> Search
														</button>
														<input type="button" name="cancel" value="Cancel" class="btn btn-info" data-dismiss="modal"/>
													</div>
												</div>
												
											</form>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
					
					<div class="modal-footer"></div>
					<!-- Modal content-->
				</div>
			</div>
		</div>
		<!-- END modal for search -->
		
		
		<div class="modal fade" id="modalWeed" tabindex="-1" role="dialog" aria-labelledby="ModalAdd" aria-hidden="true" data-keyboard="false" data-backdrop="static">
			<div class="modal-dialog" role="document" style="width:75%">
				<div class="modal-content">
					<!-- Modal content-->
						<div class="modal-header">
							<h5 class="modal-title" id="modalName" align="center">form</h5>
					    	<button type="button" id="closeModalAdd" class="close" data-dismiss="modal" aria-label="Close">
					        	<span aria-hidden="true">&times;</span>
					        </button>
						</div>
						
						<div class="modal-body">
						<form role="form" class="form-horizontal" id="formdataWeed" method="post">
							<br>
							
							<fieldset class="scheduler-border">
							<legend class="scheduler-border"></legend>
								<div class="row">
								
									<div class="form-group">
										<div class="col-sm-2"><label>Accession No</label></div>
										<div class="col-sm-2 lblAccession"></div>
									</div>
									
									<div class="form-group">
										<div class="col-sm-2"><label>Title</label></div>
										<div class="col-sm-8">
											<textarea class="form-control" rows="3" id="txtTitle" name="txtTitle" disabled></textarea>
										</div>
									</div>
									
									<div class="form-group">
										<div class="col-sm-2"><label>Reason</label></div>
										<div class="col-sm-2 lblReason"></div>
									</div>
									
									<div class="form-group">
										<div class="col-sm-2"><label>Weed Date</label></div>
										<div class="col-sm-2 LblWeedDate"></div>
									</div>
								</div>
							</fieldset>
							
							<fieldset class="scheduler-border">
							<legend class="scheduler-border">Accession Details</legend>
								<div class="row">
									<div class="form-group">
										<div class="col-sm-2"><label>Status</label></div>
										<div class="col-sm-3 lblStatus"></div>
										
										<div class="col-sm-1"></div>
										<div class="col-sm-2"><label>Circulated Hits</label></div>
										<div class="col-sm-3 lblCircHit"></div>
									</div>
									
									<div class="form-group">
										<div class="col-sm-2"><label>Item Category</label></div>
										<div class="col-sm-3 lblItemCat"></div>
										
										<div class="col-sm-1"></div>
										<div class="col-sm-2"><label>Claim Hit </label></div>
										<div class="col-sm-3 lblClaimHits"></div>
									</div>
									
									<div class="form-group">
										<div class="col-sm-2"><label>Condition</label></div>
										<div class="col-sm-3 lblCondition"></div>
										
										<div class="col-sm-1"></div>
										<div class="col-sm-2"><label>Last Activity Date</label></div>
										<div class="col-sm-3 lblLastActDate"></div>
									</div>
									
									<div class="form-group">
										<div class="col-sm-2"><label>Location</label></div>
										<div class="col-sm-3 lblLocation"></div>
										
										<div class="col-sm-1"></div>
										<div class="col-sm-2"><label>Borrower ID</label></div>
										<div class="col-sm-3 lblBorrowerID"></div>
									</div>
									
									<div class="form-group">
										<div class="col-sm-2"><label>Control No</label></div>
										<div class="col-sm-3 lblMaterialNo"></div>
										
										<div class="col-sm-1"></div>
										<div class="col-sm-2"><label>Date Borrowed</label></div>
										<div class="col-sm-3 lblDateBorrowed"></div>
									</div>
									
									<div class="form-group">
										<div class="col-sm-2"><label>Currency Code</label></div>
										<div class="col-sm-3 lblCurrencyCode"></div>
										
										<div class="col-sm-1"></div>
										<div class="col-sm-2"><label>Time Borrowed </label></div>
										<div class="col-sm-3 lblTimeBorrowed"></div>
									</div>
									
									<div class="form-group">
										<div class="col-sm-2"><label>Foreign Cost</label></div>
										<div class="col-sm-3 lblForeignCost"></div>
										
										<div class="col-sm-1"></div>
										<div class="col-sm-2"><label>Date Due</label></div>
										<div class="col-sm-3 lblDateDue"></div>
									</div>
									
									<div class="form-group">
										<div class="col-sm-2"><label>Local Cost</label></div>
										<div class="col-sm-3 lblLocalCost"></div>
										
										<div class="col-sm-1"></div>
										<div class="col-sm-2"><label>Time Due</label></div>
										<div class="col-sm-3 lblTImeDue"></div>
									</div>
									
								</div>
							</fieldset>

							<fieldset class="scheduler-border">
							<legend class="scheduler-border">Biblographic</legend>
								<div class="row">
									<div class="form-group">
										<div class="col-sm-2"><label>Author</label></div>
										<div class="col-sm-5 lblAuthor"></div>
									</div>
									
									<div class="form-group">
										<div class="col-sm-2"><label>Call No</label></div>
										<div class="col-sm-5 lblCallNo"></div>
									</div>
									
									<div class="form-group">
										<div class="col-sm-2"><label>Publisher</label></div>
										<div class="col-sm-5 lblPublisher"></div>
									</div>
									
									<div class="form-group">
										<div class="col-sm-2"><label>Edition</label></div>
										<div class="col-sm-5 lblEdition"></div>
									</div>
									
									<div class="form-group">
										<div class="col-sm-2"><label>Collation</label></div>
										<div class="col-sm-5 lblCollation"></div>
									</div>
								</div>
						</fieldset>
							
							<!-- END TAB CONTENT -->
							<div class="modal-footer">
							
								<!-- <div class="form-group">
									<div class="col-sm-2"><label>Date</label></div>
										<div class='col-sm-1 daterec'>
										</div>
									<div class="col-sm-3"></div>
									<div class="col-sm-2"><label>Officer</label></div>
										<div class='col-sm-1 recby'>
										</div>
								</div> -->
							
							    <button type="button" id="close" class="btn btn-default" data-dismiss="modal">Close</button>
						        
							</div>	
						</form>
						</div>
					<!-- END Modal content-->
				</div>
			</div>
		</div>


</body>
</html>