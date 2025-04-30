<%@ page contentType="text/html; charset=UTF-8" %>
<head>
<style>
.form-group{
margin-bottom : 5px;
}
</style>
</head>
				<div class="modal-content">
					<!-- Modal content-->
					<div class="modal-header">
						<h5 class="modal-title" id="modalName" align="center">form</h5>
					    	<button type="button" id="closeModalAdd" class="close" data-dismiss="modal" aria-label="Close">
					        	<span aria-hidden="true">&times;</span>
					        </button>
					</div>
					<form role="form" class="form-horizontal" id="formdataOrderMain" method="post">
					
					<!-- for ORDERNO VALUE -->
					<br>
					<div class="form-group" id="orderhideorView">
						<div class="col-sm-1"></div>
						<div class="col-sm-3">									
							 &nbsp;<label for="orderno">Order No</label>
						</div>
							<div class="col-sm-2">
								<input type="text" class="form-control" id="orderno" name="orderno">
							</div>
							
					</div>
						<div class="form-group" id="details">
						<div class="col-sm-1"></div>
	
							<label for="created" class="col-sm-6"></label>
							
							<!-- <div class="col-sm-2 Creatdon " style="text-align:right;">
								Created on 
							</div>
							<div class="col-sm-3 Creatdon " style="text-align:right;">
								By 
							</div> -->
					</div>
					
					
					<div class="modal-body" id="detail">
						<ul class="nav nav-tabs" role="tablist">
							<li id="Particulars" class="active"><a href="#particular-tab"
								aria-controls="particular-tab" role="tab" data-toggle="tab">Particulars </a></li>
							<li id="StatusTab"><a href="#status-tab" aria-controls="status-tab" role="tab"
								data-toggle="tab">Status</a></li>
							<li id="RequestorTab"><a href="#requestor-tab" aria-controls="requestor-tab" role="tab"
								data-toggle="tab">Requestor</a></li>
							<li id="FeedbackTab"><a href="#feedback-tab" aria-controls="feedback-tab" role="tab"
								data-toggle="tab">Feedback</a></li>
							<li id="ErrorTab"><a href="#error-in-supply-tab"
								aria-controls="error-in-supply-tab" role="tab" data-toggle="tab">Error in Supply</a></li>
						</ul>
						
						<!-- TAB CONTENT -->
						<div class="tab-content">
							<!-- TAB PARTICULAR -->
							<div role="tabpanel" class="tab-pane pwidth-95 active" id="particular-tab">

								<div>
									<br>
									<!-- <form role="form" class="form-horizontal" id="formdata" method="post"> -->
									
										<div class="form-group">
											<div class="col-sm-1"></div>
											<div class="col-sm-2">
												<label for="isbn">ISBN</label>
											</div>
											<div class="col-sm-3">
												<input type="text" class="form-control" id="isbn" name="isbn">
											</div>
											<div class="col-sm-3">(To verify duplicate Orders)</div>
										</div>
										
										<div class="form-group">
											<div class="col-sm-1"></div>
											<div class="col-sm-2">
												<label for="isbn">Control No. <span style="color:red">*</span></label>
											</div>
											<div class="col-sm-3">
										    	<div class="input-group">
											    	<input type="text" class="form-control CT03MATNO" id="input-contorlNo" name="inputControlNo" onkeydown="matnoKeydown(event,this)" onfocusout="matnoFocusOut(this)">
												    <a href="Global?type=Modal&name=Modal_BibSearch&action=8&module=Cataloging" id="searchCtrlNo" class="input-group-addon btn btn-primary" data-toggle="modal" data-target="#titleSearch">
												    <span class="glyphicon glyphicon-th-list"></span></a>
										    	</div>
											</div>
											<div class='col-sm-5'>
												<div class="title" id="title"> </div>
											</div>	
										</div>
										<div class="form-group">
											<div class="col-sm-3">
												<label style="padding-left:58px"><input type="checkbox" class="form-check-input" id="checkboxVendor" name="Retain" value="input-vendorCode">&nbsp; Vendor<span style="color:red">*</span></label>
											</div>
											<div class="col-sm-3">
										    	<div class="input-group">
											    	<input type="text" class="form-control" id="input-vendorCode" name="inputVendorCode">
												    <a href="Modal_Vendor" id="searchvendor" class="input-group-addon btn btn-primary" data-toggle="modal" data-target="#modal_vendorSearch">
												    <span class="glyphicon glyphicon-th-list"></span></a>
										    	</div>
											</div>
											<div class='col-sm-6'>
												<div id="div-vendorName"></div>
											</div>
										</div>
										
										<div class="form-group">
											<div class="col-sm-3">
												<label style="padding-left:58px"><input type="checkbox" class="form-check-input" id="checkboxOrderMode" name="Retain" value="ordermode">&nbsp; Order Mode <span style="color:red">*</span></label>
											</div>
											<div class="col-sm-4">
													<jsp:include page="../../../../include/shared/Selection/Select_OrderMode.jsp"></jsp:include>
											</div>
										</div>
										
										<div class="form-group">
											<div class="col-sm-3">
												<label style="padding-left:58px"><input type="checkbox" class="form-check-input" id="checkboxSource" name="Retain" value="source">&nbsp; Source <span style="color:red">*</span></label>
											</div>
											<div class="col-sm-4">
													<jsp:include page="../../../../include/shared/Selection/Select_Source.jsp"></jsp:include>
											</div>
										</div>
										
										<div class="form-group">
											<div class="col-sm-3">
												<label style="padding-left:58px">
												<input type="checkbox" class="form-check-input" id="checkboxCurrency" name="Retain" value="currency">&nbsp; Currency <span style="color:red">*</span></label>
											</div>
											<div class="col-sm-4">
												<jsp:include page="../../../../include/shared/Selection/Select_CurrencyList.jsp"></jsp:include>
											</div>
										</div>
										
										<div class="form-group">
											<div class="col-sm-3">
												<label style="padding-left:58px"><input type="checkbox" class="form-check-input" id="checkboxSubj" name="Retain" value="subj">&nbsp; General Subject</label>
											</div>
											<div class="col-sm-4">
													<jsp:include page="../../../../include/shared/Selection/Select_GeneralSubj.jsp"></jsp:include>
											</div>
										</div>
										
										<div class="form-group">
											<div class="col-sm-1"></div>
											<div class="col-sm-2">
												<label for="exchangeRate">Exchange Rate <span style="color:red">*</span></label>
											</div>
											<div class="col-sm-3">
												<input type="number" class="form-control" step="any" id="div-exchange-Rate" name="exchangeRate" maxlength="16" minlength="4">
											</div>
										</div>
										
										<div class="form-group">
											<div class="col-sm-1"></div>
											<div class="col-sm-2">
												<label for="foreign">Foreign Price <span style="color:red">*</span></label>
											</div>
											<div class="col-sm-3">
												<input type="number" class="form-control" step="any" id="foreign-amount" name="foreignAmount" maxlength="16" minlength="4">
											</div>
										</div>
										
										<div class="form-group">
											<div class="col-sm-1"></div>
											<div class="col-sm-2">
												<label for="local">Local Price <span style="color:red">*</span></label>
											</div>
											<div class="col-sm-3">
												<input type="number" class="form-control" step="any" id="local-amount" name="localAmount" maxlength="16" minlength="4">	
											</div>
										</div>
										
										<div class="form-group">
											<div class="col-sm-1"></div>
											<div class="col-sm-2"><label>Quantity</label></div>
											<div class="col-sm-7">
												<div class="form-group">
														
														<!-- <div class="col-sm-1"><input type="radio" name="quantity" value="set"></div>
														<div class="col-sm-2"><label>Order : </label></div> -->
														<div class="col-sm-3">
															<label><input type="radio" name="quantity" value="set">&nbsp; Order :</label>
														</div>
														<div class="col-sm-3">
															<input type="text" class="form-control" id="set" name="set" readonly>						
														</div>	
														<div class="col-sm-1">sets(</div>  
														<div class="col-sm-3">
															<input type="text" class="form-control" id="copiesSet" name="copiesSet" readonly>						
														</div>
														<div class="col-sm-2">copies/sets) </div>
													</div>
													<div class="form-group">
														<!-- <div class="col-sm-1"><input type="radio" name="quantity" value="copies" checked></div>
														<div class="col-sm-2"><label>Order : </label></div> -->
														<div class="col-sm-3">
															<label><input type="radio" name="quantity" value="copies">&nbsp; Order :</label>
														</div>
														<div class="col-sm-3">
															<input type="text" class="form-control" id="copies" name="copies">						
														</div>
														<div class="col-sm-2"> copies </div>
													</div>
											</div>
										</div>
									<!-- </form> -->
								</div>
							</div>
							<!-- END TAB PARTICULAR -->
							
							<!-- TAB STATUS -->
							<div role="tabpanel" class="tab-pane pwidth-95" id="status-tab">
								<div class="margin-btm-15"></div>
								<div class="row">
									<div class="col-sm-6">
										<div class="panel panel-default">
											<div class="panel-heading">Order</div>
											<div class="panel-body">
												<div class="col-xs-12 form-horizontal">
													<div class="form-group">
														<label for="Code" class="col-sm-3 control-label">Status </label>
														<div class="col-sm-9" id="div-status">
															<!-- <input class="form-control" type="text" id="div-status" disabled> -->
														</div>
													</div>

													<div class="form-group">
														<label for="Code" class="col-sm-3 control-label">Reference Number </label>
														<div class="col-sm-9">
															<input class="form-control" type="text" id="div-refNo" disabled>
														</div>
													</div>

													<div class="form-group">
														<label for="Code" class="col-sm-3 control-label">Order Date</label>
														<div class="col-sm-9">
															<input class="form-control" type="text" id="orderdate" disabled>
														</div>
													</div>

													<div class="form-group">
														<label for="Code" class="col-sm-3 control-label">Expected Date </label>
														<div class="col-sm-9">
															<input class="form-control" type="text" id="expectedDate" disabled>
														</div>
													</div>
												</div>
											</div>
										</div>
									</div>
									<div class="col-sm-6">
										<div class="panel panel-default">
											<div class="panel-heading">Claims</div>
											<div class="panel-body">
												<div class="col-xs-12 form-horizontal">
													<div class="form-group">
														<label for="Code" class="col-sm-3 control-label">First Claim on </label>
														<div class="col-sm-9">
															<input class="form-control" type="text" id="claims1" disabled>
														</div>
													</div>

													<div class="form-group">
														<label for="Code" class="col-sm-3 control-label">Second Claim on </label>
														<div class="col-sm-9">
															<input class="form-control" type="text" id="claims2" disabled>
														</div> 
													</div>

													<div class="form-group">
														<label for="Code" class="col-sm-3 control-label">Third Claim on </label>
														<div class="col-sm-9">
															<input class="form-control" type="text" id="claims3" disabled>
														</div>
													</div>
												</div>
											</div>
										</div>
								
									</div>
									
								</div>
								<div class="row">
									<div class="col-xs-12">
										<div class="panel panel-default">
											<div class="panel-heading"></div>
											<div class="panel-body">
												<div id="receivingListingContainer">
													<table id="status" class="table table-bordered table-striped" style="width:100%">
														<thead>
														 	<tr>
														 		<th>Accession Number</th>
																<th>Branch</th>
																<th>Location</th>
																<th>Item Category</th>
																<th>Condition</th>
																<th>SMD</th>
														 	</tr>
													 	</thead>
													</table>
												</div>
											</div>
										</div>
									</div>
									<!-- </form> -->
								</div>
							</div>
							
							<!-- END TAB STATUS -->
							
							
							<!-- TAB REQUESTOR -->
							<div role="tabpanel" class="tab-pane pwidth-95" id="requestor-tab">
								<div class="margin-btm-15"></div>
									<div id="requestorContainer">
										<div class="row" id="mainRequest">
											<div class="col-xs-12">
												<div class="panel panel-default">
													<div class="panel-heading"></div>
													<div class="panel-body">
														<div class="form-group">
															<!-- <div class="col-sm-1"></div> -->
															<div class="col-sm-1"><label for="requestor">Requestor</label></div>
															<div class="col-sm-4">
																<div class="input-group">
																	<input type="text" class="form-control reqId" id="requestorId" name="requestorId">
																	<a href="Modal_PatrSearch" id="searchpatr" class="input-group-addon btn btn-primary searchpatr" data-toggle="modal" data-target="#modal_patrSearch4order">
																	<span class="glyphicon glyphicon-option-horizontal"></span></a>
																</div>
															</div>
															<button class="btn btn-primary addrow" type="button">Add Requestor</button>
														</div>
														
														<div class="form-group">
															<div class="col-sm-3">
																<label><input type="checkbox" class="form-check-input" id="checkboxRequestor" name="Retain" value="input-requstor">&nbsp; Retain Requestor list</label>
															</div>
														</div>
														
														<div id="viewReqestor">
															<table id="requestor" class="table table-bordered table-striped requestor" style="width:100%">
																<thead>
																 	<tr>
																 		<th>Request No</th>
																		<th>Requestor</th>
																		<th>Name</th>
																		<th>Department</th>
																		<th>Date Requested</th>
																		<th>Action</th>
																 	</tr>
															 	</thead>
															</table>
														</div>
														
														
														<!-- <div id="addRequestor">
															<table id="addRequestorTable" class="table table-bordered table-striped">
															    <thead>
															        <tr>
															        	<th>Request No</th>
															            <th>Requestor</th>
																		<th>Name</th>
																		<th>Department</th>
																		<th>Date Requested</th>
																		<th></th>
															        </tr>
															    </thead>
														    	<tbody class="editHide">
															        <tr>
															        	<td class="col-sm-4"><div class="reqNo"></div></td>
															            <td class="col-sm-4">
															            <div class="input-group">
																	    	<input type="text" class="form-control reqId" id="requestorId" name="requestorId">
																		      <a href="Modal_PatrSearch?action=charge" id="searchpatr" class="input-group-addon btn btn-primary searchpatr" data-toggle="modal" data-target="#modal_patrSearch4order">
												    							<span class="glyphicon glyphicon-option-horizontal"></span></a>
																    	</div>
															            </td>
															            <td class="col-sm-4">
															                <div class="name" id="namePatr"></div>
															            </td>
															            <td class="col-sm-3">
															                <div class="dept" id="dept"></div>
															            </td>
															            <td>
															            	<div class="datereq" id="datereq"></div>
															            </td>
															            <td class="col-sm-2"><a class="deleteRow"></a>
																			<input type="button" class="ibtnDel btn btn-md btn-danger" value="Delete">
															            </td>
															        </tr>
														    	</tbody>
														    	<tfoot>
															        <tr>
															            <td colspan="6" style="text-align: left;">
															                <input type="button" class="btn btn-lg btn-block " id="addRequestorRow" value="Add Requestor" />
															                <button id="addRequestor" type="button" class="btn btn-primary">Add Requestor</button>
															            </td>
															        </tr>
														    	</tfoot>
															</table>
														</div> -->
													</div>
												</div>
											</div>
										</div>
									</div>
							</div>
							<!-- END TAB REQUESTOR -->
							
							<!-- TAB FEEDBACK -->
							<div role="tabpanel" class="tab-pane pwidth-95" id="feedback-tab">
								<div class="margin-btm-15"></div>
									<div id="feedbackContainer">
									<div class="row" id="mainRequest">
											<div class="col-xs-12">
											<table id="feedback" class="table table-bordered table-striped feedback"  style="width:100%">
												<thead>
												 	<tr>
												 		<th>Date</th>
														<th>Feedback</th>
														<th>Officer</th>
												 	</tr>
											 	</thead>
											</table>
											</div>
											</div>
									</div>
							</div>
							<!-- END TAB FEEDBACK -->
							
							<!-- TAB ERROR IN SUPPLY -->
							<div role="tabpanel" class="tab-pane pwidth-95" id="error-in-supply-tab">
								<div class="margin-btm-15"></div>
									<div id="errorInSupplyListingContainer">
											<table id="errorInSupply" class="table table-bordered table-striped" style="width:100%">
												<thead>
												 	<tr>
												 		<th>Date</th>
														<th>Feedback</th>
														<th>Officer</th>
												 	</tr>
											 	</thead>
											</table>
									</div>
							</div>
							<!-- END TAB ERROR IN SUPPLY -->
							
						</div>
						
						
						<!-- END TAB CONTENT -->
						<div class="form-group modal-footer">
								<!-- <button type="button" id="viewNote" class="btn btn-primary" id="viewNote" data-toggle='modal' data-target="#modalViewNote">Note</button> -->
						      	<a href="viewNote" id="viewNote" class="btn btn-primary" data-toggle="modal" data-target="#modalViewNote">Note</a>
						      	<button type="submit" id="save" class="btn btn-primary">Save</button>
						        <button type="button" id="close" class="btn btn-default" data-dismiss="modal">Close</button>
					        
						</div>	
					</div>
					
					</form>
					<!-- END Modal content-->
				</div>
				
			