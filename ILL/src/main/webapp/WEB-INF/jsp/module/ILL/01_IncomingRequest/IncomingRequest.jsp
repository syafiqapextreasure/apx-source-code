<%@ page contentType="text/html; charset=UTF-8" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

	<script type="text/javascript" src="${pageContext.request.contextPath}/js/ILL/IncomingRequest.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/ILL/validateIncomingRequest.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath() %>/plugin/moment.min.js"></script>
	<link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">
	
	<script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/1.10.16/js/jquery.dataTables.js"></script> 
	<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.16/css/jquery.dataTables.css">
   <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/responsive/2.2.0/css/responsive.dataTables.min.css">
    <script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/responsive/2.2.0/js/dataTables.responsive.min.js"></script>
    <script src="//cdnjs.cloudflare.com/ajax/libs/numeral.js/1.4.5/numeral.min.js"></script>
	<script src="//cdnjs.cloudflare.com/ajax/libs/jquery-form-validator/2.3.26/jquery.form-validator.min.js"></script>

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
	    
	    #modalincomReq {
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
						<button class="btn btn-primary pull-right" data-toggle='modal' data-target='#searchInRequest'><i class="glyphicon glyphicon-search" title="Search" data-searchfor="InRequesr"></i></button>
        				
        				<button type="button" class="btn btn-primary pull-right" id="add" data-toggle='modal' data-target="#modalincomReq" data-whatever="Add Incoming Request"> 
        				<i class="glyphicon glyphicon-plus" title="Add Incoming Request"></i></button>
        				
						<div class="clearfix"></div>
					</div>
					
					<div class="panel-body" id="display_Monograph">
						<table id="incomReq_table" class="table table-bordered table-striped">
						 <thead>
						 	<tr>
						 		<th>No</th>
								<th>Request No</th>
								<th>Control No</th>
								<th>Accession No</th>
								<th>Title</th>
								<th>Requestor Name</th>
						 		<th>Request Date</th>
						 		<th>Status</th>
						 		<th>Action</th>
						 	</tr>
						 </thead>
						</table>
					</div>
				</div>
			</div>
		<!-- END MAIN CONTENT -->
		
		<!-- START MODAL ADD, EDIT, VIEW -->
		<div class="modal fade" id="modalincomReq" tabindex="-1" role="dialog" aria-labelledby="ModalAdd" aria-hidden="true" data-keyboard="false" data-backdrop="static">
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
						<form role="form" class="form-horizontal" id="formdataIncomReq" method="post">
							<br>
							
							<div class="form-group">
								<!-- <div class="col-sm-1"></div> -->
								<div class="col-sm-2"><label>Request Number</label></div>
									<div class="col-sm-2 lblRequestNo"></div>
								<div class="col-sm-1"></div>
								<div class="col-sm-2"><label></label></div>
									<div class="col-sm-2 lblStatus"></div>
							</div>

							<fieldset class="scheduler-border">
							<legend class="scheduler-border">Request Details:</legend>
								<div class="row">
									<div class="form-group">
										<!-- <div class="col-sm-1"></div> -->
										<div class="col-sm-2">
												<label>Control No.</label>  
											</div>
											<div class="col-sm-3">
												<div class="input-group">
													<input type="text" class="form-control ctrlno" id="txtMaterial" name="txtMaterial">
														<a href="RetrieveTitleModal?action=20" id="searchMaterial" class="input-group-addon btn btn-primary" data-toggle="modal" data-target="#titleSearch">
														<span class="glyphicon glyphicon-option-horizontal"></span></a>
												</div>
											</div>
									</div>
									
									<div class="form-group">
										<div class="col-sm-2"><label>Title</label></div>
										<div class="col-sm-6">
											<textarea class="form-control" rows="2" id="lblTitle" name="lblTitle" disabled></textarea>
										</div>
									</div>
									
									<div class="form-group">
								<!-- <div class="col-sm-1"></div> -->
								<div class="col-sm-2"><label>Accession No.</label></div>
								<div class="col-sm-3">
									<!-- <input type="text" class="form-control" id="txtAccession" name="txtAccession"> -->
									<select class="form-control" id="txtAccession">
									 	<!-- <option></option> -->
										<!-- <option></option> -->
									</select>
								</div>
								<div class="col-sm-1"></div>
								<div class="col-sm-2"><label>Service Charges</label></div>
								<div class="col-sm-2">
									<input type="text" class="form-control right" id="txtService" name="txtService" >
									<!-- data-validation="number" data-validation-allowing="float"
                   					data-sanitize="numberFormat"  data-sanitize-number-format="0,0.00"> -->
								</div>
							</div>
							
							<div class="form-group">
								<div class="col-sm-2"><label>Date Requested</label></div>
								<div class='col-sm-3'>
			                      	<div class="input-group date" id="txtRequestedDate">
			  						 	<input type="text" class="form-control" id="txtRequestedDate" name="txtRequestedDate">
			  						 	<span class="input-group-addon">
			  							<i class="glyphicon glyphicon-calendar"></i></span>
									</div>
			        			</div>
								<div class="col-sm-1"></div>
								<div class="col-sm-2"><label>Handling Charges</label></div>
								<div class="col-sm-2">
									<input type="text" class="form-control right" id="txtHandling" name="txtHandling">
								</div>
							</div>
							
							<div class="form-group">
								<div class="col-sm-2"><label>Date Sent</label></div>
								<div class='col-sm-3'>
			                      	<div class="input-group date" id="txtSentDate">
			  						 	<input type="text" class="form-control" id="txtSentDate" name="txtSentDate">
			  						 	<span class="input-group-addon">
			  							<i class="glyphicon glyphicon-calendar"></i></span>
									</div>
			        			</div>
								<div class="col-sm-1"></div>
								<div class="col-sm-2"><label>Discount (%)</label></div>
								<div class="col-sm-2">
									<input type="text" class="form-control right" id="txtDiscount" name="txtDiscount">
								</div>
							</div>
							
							<div class="form-group">
								<div class="col-sm-6"></div>
								<div class="col-sm-2"><label>Total Amount</label></div>
								<div class="col-sm-2 right lblAmount"></div>
								<!-- <div class="col-sm-2">
									<input type="text" class="form-control right" id="lblAmount" name="lblAmount" disabled>
								</div> -->
							</div>
									
									

								</div>
						</fieldset>
						
						<fieldset class="scheduler-border">
							<legend class="scheduler-border">Requestor Details :</legend>
								<div class="row">
									<div class="form-group">
										<!-- <div class="col-sm-1"></div> -->
											<div class="col-sm-2">
												<label>Requestor ID</label>
											</div>
											<div class="col-sm-3">
												<div class="input-group">
													<input type="text" class="form-control idOfficer" id="txtPatron" name="txtPatron">
														<a href="Modal_PatrSearch" id="searchPatron" class="input-group-addon btn btn-primary searchpatr" data-toggle="modal" data-target="#modal_searchPatronID">
														<span class="glyphicon glyphicon-option-horizontal"></span></a>
												</div>
											</div>
											<!-- <div class="col-sm-6 div-officername"></div> -->		
									</div>
									
									
									<div class="form-group">
										<div class="col-sm-2"><label>Requestor Name</label></div>
										<div class="col-sm-6 lblName"></div>
									</div>
									
									<div class="form-group">
										<div class="col-sm-2"><label></label></div>
										<div class="col-sm-6 lblAdd1"></div>
									</div>
									
									<div class="form-group">
										<div class="col-sm-2"><label></label></div>
										<div class="col-sm-6 lblAdd2"></div>
									</div>
									
									<div class="form-group">
										<div class="col-sm-2"><label></label></div>
										<div class="col-sm-6 lblAdd3"></div>
									</div>
									
									<div class="form-group">
										<div class="col-sm-2"><label>Postcode</label></div>
										<div class="col-sm-3 lblPostCode"></div>
										<div class="col-sm-1"></div>
										<div class="col-sm-2"><label>Telephone No</label></div>
										<div class="col-sm-2 lblTelNo"></div>
									</div>
							
									<div class="form-group">
										<div class="col-sm-2"><label>Contact Person</label></div>
										<div class='col-sm-5'>
											<input type="text" class="form-control" id="txtContact" name="txtContact">
					        			</div>
									</div>
								</div>
						</fieldset>
							
							<!-- END TAB CONTENT -->
							<div class="modal-footer">
							
								<div class="form-group">
									<div class="col-sm-2"><label>Date</label></div>
										<div class='col-sm-1 daterec'>
										</div>
									<div class="col-sm-3"></div>
									<div class="col-sm-2"><label>Officer</label></div>
										<div class='col-sm-1 recby'>
										</div>
								</div>
							
								<button type="button" class="btn btn-primary" id="previewbtn" data-placement="bottom" title="Preview" data-original-title="Preview"><span class="glyphicon glyphicon-eye-open" style="color:white"></span></button>
							    <button type="submit" id="save" class="btn btn-primary save">Save</button>
							    <button type="button" id="cancel" class="btn btn-secondary" data-dismiss="modal">Cancel</button>
							    <button type="button" id="close" class="btn btn-default" data-dismiss="modal">Close</button>
						        
							</div>	
						</form>
						</div>
					<!-- END Modal content-->
				</div>
			</div>
		</div>
		<!-- END START MODAL ADD, EDIT, VIEW -->
		
		
		<!-- Modal for search -->
		<div class="modal fade" id="searchInRequest" tabindex="-1" role="dialog" aria-labelledby="searchInRequest" data-keyboard="false" data-backdrop="static">
			<div class="modal-dialog" role="document" style="width:55%;overflow:auto">
				<div class="modal-content">
					<!-- Modal content-->
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-label="Close" id="closesearch"><span aria-hidden="true">&times;</span></button>
						<h4 class="modal-title" id="myModalLabel">Search</h4>
					</div>
					
					<div class="modal-body">
						<div class="panel panel-default" id="form_parent">
							<div class="panel-group">
								<div class="panel panel-default">
									<div class="panel-heading">
										<h4 class="panel-title">
											<a data-toggle="collapse" data-parent="#form_parent"></a>
										</h4>
									</div>
									<div id="search-monograph" class="panel-collapse collapse in">
										<div class="panel-body">
											<div class="form-horizontal">
											
												<div class="form-group">
													<div class="col-sm-3">
														<label class="radio-inline"><input type="radio" id="search-type" name="searchSelection" value="ctrlNo" checked>Control No</label>
													</div>
												
													<div class="col-sm-3">
														<label class="radio-inline"><input type="radio" id="search-type" name="searchSelection" value="reqID">Requestor ID</label>
													</div>
												</div>
												
												
													
												<div class="form-group">
													<div class="col-sm-12 col-md-12">
														<input type="text" class="form-control" name="input-criteria" id="input-criteria" autocomplete="off">
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
												
											</div>
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
		
		<!-- MODAL WHEN CLICK Search CONTROL NUMBER -->
		<div class="modal fade" id="titleSearch" tabindex="-1" role="dialog" aria-labelledby="titleSearch" data-keyboard="false" data-backdrop="static">
			<div class="modal-dialog" role="document" style="width:60%;overflow:auto">
				<div class="modal-content">
				<!-- Remote content load here -->
				</div>
			</div>
		</div>
		<!-- Modal END modal search CONTROL NUMBER-->
		
		<!-- MODAL WHEN CLICK Search officer -->
		<div class="modal fade" id="modal_searchPatronID" tabindex="-1" role="dialog" aria-labelledby="modal_searchPatronID" data-keyboard="false" data-backdrop="static"> 
		<div class="modal-dialog" style="width:55%;">
			<div class="modal-content" id="Modal_PatrSearchContent">
				<!-- Remote content load here  -->
			</div>
		</div>
		</div>
		<!-- Modal END modal search officer-->
		


</body>
</html>