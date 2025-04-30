<%@ page contentType="text/html; charset=UTF-8" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

	<script type="text/javascript" src="${pageContext.request.contextPath}/js/ILL/outgoing.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/ILL/validateoutgoing.js"></script>
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
	    
	    #modalOutReq {
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
						<button class="btn btn-primary pull-right searchoutgoingill" data-toggle='modal' data-target='#searchOutgoing'><i class="glyphicon glyphicon-search" title="Search" data-searchfor="Outgoing"></i></button>
        				<button type="button" class="btn btn-primary pull-right" id="addOutReq" data-toggle='modal' data-target="#modalOutReq" data-whatever="Add Outgoing Request"> 
        				<i class="glyphicon glyphicon-plus" title="Add Outgoing Request"></i></button>
        				
						<div class="clearfix"></div>
					</div>
					
					<div class="panel-body" id="display_Outgoing">
						<table id="Outgoing_table" class="table table-bordered table-striped">
						 <thead>
						 	<tr>
						 		<th>No</th>
								<th>ILL Ref No</th>
								<th>Control No</th>
								<th>Title</th>
								<th>Lending Library</th>
								<th>Requestor Name</th>
								<th>Request Date</th>
								<th>msg</th>
						 		<th>Action</th>
						 	</tr>
						 </thead>
						</table>
					</div>
				</div>
			</div>
		<!-- END MAIN CONTENT -->
		
		<!-- START MODAL ADD, EDIT, VIEW -->
		<div class="modal fade" id="modalOutReq" tabindex="-1" role="dialog" aria-labelledby="ModalAdd" aria-hidden="true" data-keyboard="false" data-backdrop="static">
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
						<form role="form" class="form-horizontal" id="formdataOutReq" method="post">
							<br>
							
							<div class="form-group">
								<!-- <div class="col-sm-1"></div> -->
								<div class="col-sm-2"><label>ILL Reference No</label></div>
									<div class="col-sm-2 lblReferenceNumber"></div>
							</div>
							
							<fieldset class="scheduler-border">
							<legend class="scheduler-border"></legend>
								<div class="row">
								
									<div class="form-group">
										<!-- <div class="col-sm-1"></div> -->
											<div class="col-sm-2">
												<label>Control No</label><span id="red">*</span>
											</div>
											<div class="col-sm-3">
												<div class="input-group">
													<input type="text" class="form-control ctrlno" id="txtMaterialNo" name="txtMaterialNo">
														<a href="RetrieveTitleModal?action=20" id="searchMaterial" class="input-group-addon btn btn-primary" data-toggle="modal" data-target="#titleSearch">
														<span class="glyphicon glyphicon-option-horizontal"></span></a>
												</div>
											</div>
											<div class="col-sm-6 lblTitle"></div>	
									</div>
									
									<div class="form-group">
										<div class="col-sm-2"><label>Accession No.</label></div>
										<div class="col-sm-3">
											<!-- <input type="text" class="form-control" id="txtAccession" name="txtAccession"> -->
											<select class="form-control" id="txtAccession" name="txtAccession" disabled>
											 	<option></option>
												<!-- <option></option> -->
											</select>
										</div>
									</div>
									
									<div class="form-group">
											<div class="col-sm-2"><label>Document Type<span id="red">*</span></label></div>
												<div class='col-sm-3'>
													<select class="form-control" id="cboDocumentType" name="cboDocumentType">
														<option value="01">Book</option>
														<option value="02">Book Chapter</option>
														<option value="03">Journal</option>
														<option value="04">Journal Articles</option>
													</select>
												</div>
									</div>
									
									<div class="form-group">
										<div class="col-sm-2"><label>Volume</label></div>
										<div class="col-sm-3">
											<input type="text" class="form-control" id="txtVolume" name="txtVolume">
										</div>
										<div class="col-sm-2"><label>Issue</label></div>
										<div class="col-sm-3">
											<input type="text" class="form-control" id="txtIssue" name="txtIssue">
										</div>
									</div>
									
									<div class="form-group">
										<div class="col-sm-2"><label>Page Number</label></div>
										<div class="col-sm-3">
											<input type="text" class="form-control" id="txtPageNumber" name="txtPageNumber">
										</div>
									</div>
									
									<div class="form-group">
										    <div class="col-sm-2"><label>Date Requested</label><span id="red">*</span></div>
											<div class='col-sm-3'>
						                      	<div class="input-group date" id="dateExpected">
						  						 	<input type="text" class="form-control" id="txtRequestedDate" name="txtRequestedDate">
						  						 	<span class="input-group-addon">
						  							<i class="glyphicon glyphicon-calendar"></i></span>
												</div>
					        				</div>
					        				<div class="col-sm-2"><label>Date Expected</label></div>
											<div class='col-sm-3'>
						                      	<div class="input-group date" id="dateExpected">
						  						 	<input type="text" class="form-control" id="txtExpectedDate" name="txtExpectedDate">
						  						 	<span class="input-group-addon">
						  							<i class="glyphicon glyphicon-calendar"></i></span>
												</div>
					        				</div>
									</div>
									
									
								</div>
						</fieldset>
						
						<fieldset class="scheduler-border">
							<legend class="scheduler-border">Lending Library Details</legend>
								<div class="row">
								
									<div class="form-group">
										<!-- <div class="col-sm-1"></div> -->
											<div class="col-sm-2">
												<label>Library ID</label><span id="red">*</span>
											</div>
											<div class="col-sm-3">
												<div class="input-group">
													<input type="text" class="form-control" id="txtLibraryID" name="txtLibraryID">
														<a href="Modal_PatrSearch?textid=libid2" id="searchLibID" class="input-group-addon btn btn-primary searchpatr" data-toggle="modal" data-target="#modal_searchLibID">
														<span class="glyphicon glyphicon-option-horizontal"></span></a>
												</div>
											</div>
											
											<div class="col-sm-1"></div>
											<div class="col-sm-2"><label>Date Due</label></div>
											<div class='col-sm-3'>
						                      	<div class="input-group date" id="dateExpected">
						  						 	<input type="text" class="form-control" id="txtDueDate" name="txtDueDate">
						  						 	<span class="input-group-addon">
						  							<i class="glyphicon glyphicon-calendar"></i></span>
												</div>
					        				</div>
											<!-- <div class="col-sm-6 div-officername"></div> -->		
									</div>
									
									
									<div class="form-group">
										<div class="col-sm-2"><label>Library Name</label></div>
										<div class="col-sm-6 lblLibName"></div>
									</div>
									
									<div class="form-group">
										<div class="col-sm-2"><label></label></div>
										<div class="col-sm-6 lblLibAdd1"></div>
									</div>
									
									<div class="form-group">
										<div class="col-sm-2"><label></label></div>
										<div class="col-sm-6 lblLibAdd2"></div>
									</div>
									
									<div class="form-group">
										<div class="col-sm-2"><label></label></div>
										<div class="col-sm-6 lblLibAdd3"></div>
									</div>
									
									<div class="form-group">
										<div class="col-sm-2"><label>Telephone No</label></div>
										<div class="col-sm-2 lblLibTelNo"></div>
									</div>
							
									<div class="form-group">
										<div class="col-sm-2"><label>Contact Person</label></div>
										<div class='col-sm-5'>
											<input type="text" class="form-control" id="txtContactPerson" name="txtContactPerson">
					        			</div>
									</div>
									
								</div>
						</fieldset>
							
							
		
							<fieldset class="scheduler-border">
							<legend class="scheduler-border">Requestor Details</legend>
								<div class="row">
								
									<div class="form-group">
										<!-- <div class="col-sm-1"></div> -->
											<div class="col-sm-2">
												<label>Requestor ID</label><span id="red">*</span>
											</div>
											<div class="col-sm-3">
												<div class="input-group">
													<input type="text" class="form-control" id="txtPatron" name="txtPatron">
														<a href="Modal_PatrSearch?textid=reqid" id="searchReqID" class="input-group-addon btn btn-primary searchpatr" data-toggle="modal" data-target="#modal_searchreqid">
														<span class="glyphicon glyphicon-option-horizontal"></span></a>
												</div>
											</div>
											<!-- <div class="col-sm-6 div-officername"></div> -->		
									</div>
									
									
									<div class="form-group">
										<div class="col-sm-2"><label>Requestor Name</label></div>
										<div class="col-sm-6 lblRequestorName"></div>
									</div>
									
									<div class="form-group">
										<div class="col-sm-2"><label>Address</label></div>
										<div class="col-sm-6 lblReqAdd1"></div>
									</div>
									
									<div class="form-group">
										<div class="col-sm-2"><label></label></div>
										<div class="col-sm-6 lblReqAdd2"></div>
									</div>
									
									<div class="form-group">
										<div class="col-sm-2"><label></label></div>
										<div class="col-sm-6 lblReqAdd3"></div>
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
		<div class="modal fade" id="searchOutgoing" tabindex="-1" role="dialog" aria-labelledby="searchOutgoing" data-keyboard="false" data-backdrop="static">
			<div class="modal-dialog" role="document" style="width:55%;overflow:auto">
				<div class="modal-content">
					<!-- Modal content-->
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-label="Close" id="closesearchOutgoing"><span aria-hidden="true">&times;</span></button>
						<h4 class="modal-title" id="myModalLabel">Search</h4>
					</div>
					
					<div class="modal-body">
						<div class="panel panel-default" id="form_parent">
							<div class="panel-group">
								<div class="panel panel-default">
									<div class="panel-heading">
										<h4 class="panel-title">
											<a data-toggle="collapse" data-parent="#form_parent" href="#search-Outgoing"></a>
										</h4>
									</div>
									<div id="search-Outgoing" class="panel-collapse collapse in">
										<div class="panel-body">
											<form role="form" class="form-horizontal">
											
												<div class="form-group">
													<div class="col-sm-3">
														<label class="radio-inline"><input type="radio" id="search-type" name="searchSelection" value="lendlib" checked>Lending Library ID</label>
													</div>
												</div>
												
												<div class="form-group">
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
														<input type="button" name="cancel" value="Cancel" class="btn btn-info searcoutcancel" data-dismiss="modal"/>
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
		
		<!-- MODAL WHEN CLICK Search CONTROL NUMBER -->
		<div class="modal fade" id="titleSearch" tabindex="-1" role="dialog" aria-labelledby="titleSearch" data-keyboard="false" data-backdrop="static">
			<div class="modal-dialog" role="document" style="width:60%;overflow:auto">
				<div class="modal-content">
				<!-- Remote content load here -->
				</div>
			</div>
		</div>
		<!-- Modal END modal search CONTROL NUMBER-->
		
		<div class="modal fade" id="modal_searchLibID" tabindex="-1" role="dialog" aria-labelledby="modal_searchLibID" data-keyboard="false" data-backdrop="static">
			<div class="modal-dialog" role="document" style="width:60%;overflow:auto">
				<div class="modal-content">
				<!-- Remote content load here -->
				</div>
			</div>
		</div>
		
		<div class="modal fade" id="modal_searchreqid" tabindex="-1" role="dialog" aria-labelledby="modal_searchreqid" data-keyboard="false" data-backdrop="static">
			<div class="modal-dialog" role="document" style="width:60%;overflow:auto">
				<div class="modal-content">
				<!-- Remote content load here -->
				</div>
			</div>
		</div>

</body>
</html>