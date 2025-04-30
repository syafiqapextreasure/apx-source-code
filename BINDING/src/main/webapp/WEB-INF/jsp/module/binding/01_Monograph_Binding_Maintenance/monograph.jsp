<%@ page contentType="text/html; charset=UTF-8" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

	<script type="text/javascript" src="${pageContext.request.contextPath}/js/binding/monograph.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/binding/validateMonograph.js"></script>
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
	    
	    #modalMonograph {
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
						<button class="btn btn-primary pull-right" data-toggle='modal' data-target='#searchMonograph'><i class="glyphicon glyphicon-search" title="Search" data-searchfor="Monograph"></i></button>
        				<button type="button" class="btn btn-primary pull-right" id="addMonograph" data-toggle='modal' data-target="#modalMonograph" data-whatever="Add New Binding Record"> 
        				<i class="glyphicon glyphicon-plus" title="Add New Binding Record"></i></button>
        				
						<div class="clearfix"></div>
					</div>
					
					<div class="panel-body" id="display_Monograph">
						<table id="Monograph_table" class="table table-bordered table-striped">
						 <thead>
						 	<tr>
						 		<th>No</th>
								<th>Reference No</th>
								<th>Title</th>
								<th>Date Sent</th>
								<th>Date Expected</th>
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
		<div class="modal fade" id="modalMonograph" tabindex="-1" role="dialog" aria-labelledby="ModalAdd" aria-hidden="true" data-keyboard="false" data-backdrop="static">
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
						<form role="form" class="form-horizontal" id="formdataMonograph" method="post">
							<br>
							
							<div class="form-group">
								<!-- <div class="col-sm-1"></div> -->
								<div class="col-sm-2"><label>Binding No</label></div>
									<div class="col-sm-2 bindingNo"></div>
								<div class="col-sm-1"></div>
								<div class="col-sm-2"><label>Reference No</label></div>
									<div class="col-sm-2 refrenceNo"></div>
								<div class="col-sm-1"></div>
									<div class="col-sm-2 stausView"></div>
							</div>
							
							<div class="form-group">
								<!-- <div class="col-sm-1"></div> -->
								<div class="col-sm-2">
										<label>Accession No<span id="red">*</span></label>  
									</div>
									<div class="col-sm-3">
										<div class="input-group">
											<input type="text" class="form-control" id="accessionNo" name="accessionNo">
												<a href="RetrieveTitleModal?action=15" id="searchAccsion" class="input-group-addon btn btn-primary searchpatr" data-toggle="modal" data-target="#titleSearch">
												<span class="glyphicon glyphicon-option-horizontal"></span></a>
										</div>
									</div>
							</div>
							
							<div class="form-group">
								<!-- <div class="col-sm-1"></div> -->
								<div class="col-sm-2"><label>Control Number</label></div>
								<div class="col-sm-3">
									<input type="text" class="form-control" id="controlNo" name="controlNo" disabled>
								</div>
								<!-- <div class="col-sm-6"><textarea class="form-control" rows="2" id="editissuevolDes" name="editissuevolDes" disabled></textarea>
								</div> -->
							</div>
							
							<div class="form-group">
								<div class="col-sm-2"><label>Title</label></div>
								<div class="col-sm-6">
									<textarea class="form-control" rows="2" id="editissuevolDes" name="editissuevolDes" disabled></textarea>
								</div>
								<!-- <div class="col-sm-6"><textarea class="form-control" rows="2" id="editissuevolDes" name="editissuevolDes" disabled></textarea>
								</div> -->
							</div>
							
							<div class="form-group">
								<!-- <div class="col-sm-1"></div> -->
									<div class="col-sm-2">
										<label><input type="checkbox" class="form-check-input" id="checkboxofficer" name="Retain" value="input-officer">&nbsp <strong>Officer<span id="red">*</span></strong></label>
										<!-- <label><strong>Officer<span id="red">*</span></strong></label> -->  
									</div>
									<div class="col-sm-3">
										<div class="input-group">
											<input type="text" class="form-control idOfficer" id="officer" name="officer">
												<a href="Modal_PatrSearch" id="searchOffi" class="input-group-addon btn btn-primary searchpatr" data-toggle="modal" data-target="#modal_patrSearchMono">
												<span class="glyphicon glyphicon-option-horizontal"></span></a>
										</div>
									</div>
									<div class="col-sm-6 div-officername"></div>
									
							</div>
							
							<div class="form-group">
								<!-- <div class="col-sm-1"></div> -->
									<div class="col-sm-2">
										<label><input type="checkbox" class="form-check-input" id="checkboxbinder" name="Retain" value="input-binder">&nbsp <strong>Binder<span id="red">*</span></strong></label>
										<!-- <label><strong>Binder<span id="red">*</span></strong></label>  --> 
									</div>
									<div class="col-sm-3">
										<div class="input-group">
											<input type="text" class="form-control" id="binder" name="binder">
												<a href="Modal_Vendor" id="searchBinder" class="input-group-addon btn btn-primary searchpatr" data-toggle="modal" data-target="#modal_vendorSearch">
												<span class="glyphicon glyphicon-option-horizontal"></span></a>
										</div>
									</div>
									<div class="col-sm-6 div-bindername"></div>
									
							</div>
							
							<div class="form-group">
								<!-- <div class="col-sm-1"></div> -->
									<div class="col-sm-2"><label><input type="checkbox" class="form-check-input" id="checkboxcurrency" name="Retain" value="input-currency">&nbsp <strong>Currency<span id="red">*</span></strong></label></div>
									<!-- <div class="col-sm-2"><label>Currency<span id="red">*</span></label></div> -->
										<div class='col-sm-4'>
											<select class="form-control" id="currency" name="currency">
												<jsp:include page="../Select_Currency.jsp"></jsp:include> 
											</select>
										</div>
							</div>
							
							<div class="form-group">
								<!-- <div class="col-sm-1"></div> -->
								<div class="col-sm-2"><label>Exchange Rate</label></div>
								<div class="col-sm-3">
									<input type="text" class="form-control" id="erate" name="erate">
								</div>
								<div class="col-sm-1"></div>
								<div class="col-sm-2"><label>Year</label></div>
								<div class="col-sm-2">
									<input type="text" class="form-control" id="year" name="year">
								</div>
							</div>
							
							
		
							<div class="form-group">
								<!-- <div class="col-sm-1"></div> -->
								<div class="col-sm-2"><label>Foreign Cost</label></div>
								<div class="col-sm-3">
									<input type="text" class="form-control" id="fcost" name="fcost">
								</div>
								<div class="col-sm-1"></div>
								<div class="col-sm-2"><label>Local Cost</label></div>
								<div class="col-sm-2">
									<input type="text" class="form-control" id="lcost" name="lcost">
								</div>
							</div>
							
							<div class="form-group">
								<!-- <div class="col-sm-1"></div> -->
								<div class="col-sm-2"><label><input type="checkbox" class="form-check-input" id="checkboxdatesent" name="Retain" value="input-dateSent">&nbsp <strong>Date Sent<span id="red">*</span></strong></label></div>
								<!-- <div class="col-sm-2"><label>Date Sent<span id="red">*</span></label></div> -->
								<div class='col-sm-3'>
			                      	<div class="input-group date" id="dateSent">
			  						 	<input type="text" class="form-control" id="dsent" name="dsent">
			  						 	<span class="input-group-addon">
			  							<i class="glyphicon glyphicon-calendar"></i></span>
									</div>
			        			</div>
								<!-- <div class="col-sm-2">
									<input type="text" class="form-control" id="dsent" name="dsent">
								</div> -->
								<div class="col-sm-1"></div>
								<!-- <div class="col-sm-2"><label>Date Expected<span id="red">*</span></label></div> -->
								<div class="col-sm-2"><label><input type="checkbox" class="form-check-input" id="checkboxdateExpected" name="Retain" value="input-dateExpected">&nbsp <strong>Date Expected<span id="red">*</span></strong></label></div>
								<div class='col-sm-3'>
			                      	<div class="input-group date" id="dateExpected">
			  						 	<input type="text" class="form-control" id="dexpect" name="dexpect">
			  						 	<span class="input-group-addon">
			  							<i class="glyphicon glyphicon-calendar"></i></span>
									</div>
			        			</div>
								<!-- <div class="col-sm-2">
									<input type="text" class="form-control" id="dexpect" name="dexpect">
								</div> -->
							</div>
							
							<div class="form-group">
								<!-- <div class="col-sm-1"></div> -->
								<div class="col-sm-2"><label><input type="checkbox" class="form-check-input" id="checkboxRemarks" name="Retain" value="input-Remarks">&nbsp Remarks</label></div>
									<!-- <div class="col-sm-2"><label>Remarks</label></div> -->
										<div class='col-sm-9'>
											<textarea class="form-control" rows="1" id="remarks" name="remarks"></textarea>
										</div>
							</div>
							
							<div class="form-group vhideStat">
								<!-- <div class="col-sm-1"></div> -->
								<div class="col-sm-2"><label>Status</label></div>
								<div class="col-sm-2">
									<label><input type="radio" name="statusRadio" value="S" checked>&nbsp Sent</label>
								</div>
								<div class="col-sm-2">
									<label><input type="radio" name="statusRadio" value="P">&nbsp Pending</label>
								</div>
								<div class="col-sm-2">
									<label><input type="radio" name="statusRadio" value="R" disabled>&nbsp Received</label>
								</div>
							</div>
							
							<fieldset class="scheduler-border">
							<legend class="scheduler-border">Binding Instruction</legend>
								<div class="row">
									<div class="form-group">
										<div class="col-sm-2"><label>ISBN</label></div>
										<div class="col-sm-2 isbn"></div>
								
										<div class="col-sm-3"><label>Type of Binding</label></div>
										<div class='col-sm-5'>
											<select class="form-control" id="typeBind" name="typeBind">
												<jsp:include page="../Select_TypeBind.jsp"></jsp:include>
											</select>
										</div>
									</div>
									
									<div class="form-group">
										<div class="col-sm-2"><label>Spine Title</label></div>
										<div class="col-sm-9">
											<input type="text" class="form-control" id="spineTitle" name="spineTitle">
										</div>
									</div>
									
									<div class="form-group">
										<div class="col-sm-2"><label>Call Number</label></div>
										<div class="col-sm-3 callNumber"></div>
									</div>
									
									<div class="form-group">
										<div class="col-sm-2"><label>Cover Color</label></div>
										<div class="col-sm-3">
											<input type="text" class="form-control" id="cColor" name="cColor">
										</div>
										<div class="col-sm-1"></div> 
										<div class="col-sm-2">
											<label class="checkbox-inline">
										      <input type="checkbox" class="form-check-input" id="oriCover" value='Y'>Original Cover
										    </label>
										</div>
										<div class="col-sm-2">
											<label class="checkbox-inline">
										      <input type="checkbox" class="form-check-input" id="advert" value='Y'>Advertisements
										    </label>
										</div>
										<div class="col-sm-2">
											<label class="checkbox-inline">
										      <input type="checkbox" class="form-check-input" id="titlePage" value='Y'>Title Page
										    </label>
										</div>
									</div>


									<div class="form-group">
										<div class="col-sm-2"><label>Lettering Color</label></div>
										<div class="col-sm-3">
											<input type="text" class="form-control" id="lColor" name="lColor">
										</div>
										<div class="col-sm-1"></div> 
										<div class="col-sm-2">
											<label class="checkbox-inline">
										      <input type="checkbox" class="form-check-input" id="supp" value='Y'>Supplements
										    </label>
										</div>
										<div class="col-sm-2">
											<label class="checkbox-inline">
										      <input type="checkbox" class="form-check-input" id="indexInc" value='Y'>Index Included
										    </label>
										</div>
										<div class="col-sm-2">
											<label class="checkbox-inline">
										      <input type="checkbox" class="form-check-input" id="contentsPage" value='Y'>Contents PageTitle Page
										    </label>
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
		<div class="modal fade" id="searchMonograph" tabindex="-1" role="dialog" aria-labelledby="searchMonograph" data-keyboard="false" data-backdrop="static">
			<div class="modal-dialog" role="document" style="width:55%;overflow:auto">
				<div class="modal-content">
					<!-- Modal content-->
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-label="Close" id="closesearchMonograph"><span aria-hidden="true">&times;</span></button>
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
														<label class="radio-inline"><input type="radio" id="search-type" name="searchSelectionMono" value="ctrlNo" checked>Control No</label>
													</div>
												
													<div class="col-sm-3">
														<label class="radio-inline"><input type="radio" id="search-type" name="searchSelectionMono" value="officcerCode">Officer Code</label>
													</div>
												</div>
												
												<div class="form-group">
													<div class="col-sm-3">
														<label class="radio-inline"><input type="radio" id="search-type" name="searchSelectionMono" value="binderCode">Binder Code</label>
													</div>
												
													<div class="col-sm-3">
														<label class="radio-inline"><input type="radio" id="search-type" name="searchSelectionMono" value="sentDate">Sent Date</label>
													</div>
												</div>
													
												<div class="form-group">
													<div class="col-sm-12 col-md-12">
														<input type="text" class="form-control" name="input-criteriaMono" id="input-criteriaMono" autocomplete="off">
													</div>
												</div>
												
												<div class="form-group">
													<div class="col-sm-6 col-md-6">
														<!-- Sent Date -->
														<!-- <div class="input-daterange input-group" id="datepickerSentDate">
															<input type="text" class="form-control"
																name="sDate" id="input-sentDate" /> 
														</div> -->
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
		
		<!-- MODAL WHEN CLICK Search ACCESSION NUMBER -->
		<div class="modal fade" id="titleSearch" tabindex="-1" role="dialog" aria-labelledby="titleSearch" data-keyboard="false" data-backdrop="static">
			<div class="modal-dialog" role="document" style="width:60%;overflow:auto">
				<div class="modal-content">
				<!-- Remote content load here -->
				</div>
			</div>
		</div>
		<!-- Modal END modal search  ACCESSION number-->
		
		<!-- MODAL WHEN CLICK Search officer -->
		<div class="modal fade" id="modal_patrSearchMono" tabindex="-1" role="dialog" aria-labelledby="modal_patrSearchMono" data-keyboard="false" data-backdrop="static"> 
			<div class="modal-dialog" style="width:55%;">
				    <div class="modal-content" id="modal_patrSearchMonoContent">
					  <!-- Remote content load here  -->
				  	</div>
			</div>
		</div>
		<!-- Modal END modal search officer-->
		
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