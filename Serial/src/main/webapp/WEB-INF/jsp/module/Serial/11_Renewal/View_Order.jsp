<%-- 
<%@page import="com.ilmu.acquisition.ordermaintenance.Ordermaintenance, java.util.*"%>

<%
/* String username = (String)session.getAttribute("username");
System.out.println(username + " username");
boolean accLvl = AccessLvl.executeAccessLvl(username,"AAAM1650" );
//boolean accLvl = true;
if(accLvl==true){ */
%>
<!doctype html>
<html>
	<head>
		<!-- <meta charset="ISO-8859-1"> -->

		<script type="text/javascript" src="${pageContext.request.contextPath}/js/acquisition/OrderMaintenance.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/acquisition/validateOrderMaintenance.js"></script>
		<script type="text/javascript" src="<%= request.getContextPath() %>/plugin/moment.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/general/searchOrdrMaint.js"></script>
		<link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">
	</head>
	<body>
		<!-- START MAIN CONTENT -->
			<div class="box box-default">
				<div class="panel panel-default">
					<div class="panel-heading">
						<h3 class="panel-title pull-left"></h3>
						<button class="btn btn-primary pull-right" data-toggle='modal' data-target='#searchOdrMaint' href='Modal_SearchOdrMaint'><i class="glyphicon glyphicon-search" title="Search" data-searchfor="orderMaint"></i></button>
        				<button type="button" class="btn btn-primary pull-right" id="addNewOdrMaint" data-toggle='modal' data-target="#modalOdrMaint" data-whatever="Add New Order"> 
        				<i class="glyphicon glyphicon-plus" title="Add New Order Maint"></i></button>
						<div class="clearfix"></div>
					</div>
					
					<div class="panel-body" id="display_OdrMaint">
						<table id="OdrMaint2" class="table table-bordered table-striped">
						 <thead>
						 	<tr>
						 		<th>Order No.</th>
								<th>Control No.</th>
								<th>Title</th>
								<th>Vendor</th>
								<th>Status</th>
								<th>Reference</th>
								<th>Order Date</th>
						 		<th>Action </th>
						 	</tr>
						 </thead>
						 <!-- <tbody id="test">
						 </tbody> -->
						</table>
					</div>
					<!-- <div id="test"></div> -->
				</div>
			</div>
		<!-- END MAIN CONTENT -->
		
		<!-- START MODAL ADD, EDIT, VIEW -->
		<div class="modal fade" id="modalOdrMaint" tabindex="-1" role="dialog" aria-labelledby="ModalAdd" aria-hidden="true" data-keyboard="false" data-backdrop="static">
			<div class="modal-dialog" role="document" style="width:70%">
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
							<!-- <div class="col-sm-1"></div> -->
							<div class="col-sm-2">
									 &nbsp;<label for="orderno">Order No</label>
							</div>
							<div class="col-sm-2">
								<input type="text" class="form-control" id="orderno" name="orderno">
							</div>
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
											<div class="col-sm-3">
												<label for="isbn">ISBN</label>
											</div>
											<div class="col-sm-3">
												<input type="text" class="form-control" id="isbn" name="isbn">
											</div>
											<div class="col-sm-3">(To verify duplicate Orders)</div>
										</div>
										
										<div class="form-group">
											<div class="col-sm-1"></div>
											<div class="col-sm-3"><label>Control Number</label></div>
											<div class="col-sm-4">
										    	<div class="input-group">
											    	<input type="text" class="form-control" id="input-contorlNo" name="inputControlNo">
												    <a href="RetrieveTitleModal?action=5" id="searchCtrlNo" class="input-group-addon btn btn-primary" data-toggle="modal" data-target="#titleSearch">
												    <span class="glyphicon glyphicon-option-horizontal"></span></a>
										    	</div>
											</div>	
										</div>
												
										<div class="form-group">
											<div class="col-sm-1"></div>
											<div class="col-sm-3"><label></label></div>
											<div class='col-sm-8'>
												<div id="div-ctrolNo"> </div>
											</div>
										</div>
										
										<div class="form-group">
											<div class="col-sm-1"></div>
											<div class="col-sm-3">
												<label><input type="checkbox" class="form-check-input" id="checkboxVendor" name="Retain" value="input-vendorCode">&nbsp Vendor</label>
											</div>
											<div class="col-sm-4">
										    	<div class="input-group">
											    	<input type="text" class="form-control" id="input-vendorCode" name="inputVendorCode">
												    <a href="Modal_Vendor" id="searchvendor" class="input-group-addon btn btn-primary" data-toggle="modal" data-target="#modal_vendorSearch">
												    <span class="glyphicon glyphicon-option-horizontal"></span></a>
										    	</div>
											</div>
										</div>
										
										<div class="form-group">
											<div class="col-sm-1"></div>
											<div class="col-sm-3"><label></label></div>
											<div class='col-sm-8'>
												<div id="div-vendorName"></div>
											</div>
										</div>
										
										<div class="form-group">
											<div class="col-sm-1"></div>
											<div class="col-sm-3">
												<label><input type="checkbox" class="form-check-input" id="checkboxOrderMode" name="Retain" value="ordermode">&nbsp; Order Mode</label>
											</div>
											<div class="col-sm-5">
												<select class="form-control" id="ordermode" name="ordermode">
													<jsp:include page="../Select_OrderMode.jsp"></jsp:include>
												</select>
											</div>
										</div>
										
										<div class="form-group">
											<div class="col-sm-1"></div>
											<div class="col-sm-3">
												<label><input type="checkbox" class="form-check-input" id="checkboxSource" name="Retain" value="source">&nbsp Source</label>
											</div>
											<div class="col-sm-5">
												<select class="form-control" id="source" name="source">
													<jsp:include page="../Select_Source.jsp"></jsp:include>
												</select>
											</div>
										</div>
										
										<div class="form-group">
											<div class="col-sm-1"></div>
											<div class="col-sm-3">
												<label><input type="checkbox" class="form-check-input" id="checkboxCurrency" name="Retain" value="currency">&nbsp Currency</label>
											</div>
											<div class="col-sm-4">
												<select class="form-control" id="currency" name="currency">
													<jsp:include page="../Select_CurrencyList.jsp"></jsp:include>
												</select>
											</div>
										</div>
										
										<div class="form-group">
											<div class="col-sm-1"></div>
											<div class="col-sm-3">
												<label for="exchangeRate">Exchange Rate</label>
											</div>
											<div class="col-sm-2">
												<input type="text" class="form-control" id="div-exchange-Rate" name="exchangeRate">
											</div>
										</div>
										
										<div class="form-group">
											<div class="col-sm-1"></div>
											<div class="col-sm-3">
												<label for="foreign">Foreign Price</label>
											</div>
											<div class="col-sm-3">
												<input type="text" class="form-control" id="foreign-amount" name="foreignAmount">
											</div>
										</div>
										
										<div class="form-group">
											<div class="col-sm-1"></div>
											<div class="col-sm-3">
												<label for="local">Local Price</label>
											</div>
											<div class="col-sm-3">
												<input type="text" class="form-control" id="local-amount" name="localAmount">	
											</div>
										</div>
										
										<div class="form-group">
											<div class="col-sm-1"></div>
											<div class="col-sm-3"><label>Quantity</label></div>
											<div class="col-sm-7">
												<div class="form-group">
														
														<!-- <div class="col-sm-1"><input type="radio" name="quantity" value="set"></div>
														<div class="col-sm-2"><label>Order : </label></div> -->
														<div class="col-sm-2">
															<label><input type="radio" name="quantity" value="set">&nbsp Order :</label>
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
														<div class="col-sm-2">
															<label><input type="radio" name="quantity" value="copies">&nbsp Order :</label>
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
													<table id="status" class="table table-bordered table-striped">
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
																	<a href="Modal_PatrSearch?action=charge" id="searchpatr" class="input-group-addon btn btn-primary searchpatr" data-toggle="modal" data-target="#modal_patrSearch4order">
																	<span class="glyphicon glyphicon-option-horizontal"></span></a>
																</div>
															</div>
															<button class="btn btn-primary addrow" type="button">Add Requestor</button>
														</div>
														
														<div id="viewReqestor">
															<table id="requestor" class="table table-bordered table-striped">
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
											<table id="feedback" class="table table-bordered table-striped feedback">
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
							<!-- END TAB FEEDBACK -->
							
							<!-- TAB ERROR IN SUPPLY -->
							<div role="tabpanel" class="tab-pane pwidth-95" id="error-in-supply-tab">
								<div class="margin-btm-15"></div>
									<div id="errorInSupplyListingContainer">
											<table id="errorInSupply" class="table table-bordered table-striped">
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
						      	<button type="submit" id="save" class="btn btn-primary" onClick="testing()">Save</button>
						        <button type="button" id="cancel" class="btn btn-secondary" data-dismiss="modal">Cancel</button>
						        <button type="button" id="close" class="btn btn-default" data-dismiss="modal">Close</button>
					        
						</div>	
					</div>
					
					</form>
					<!-- END Modal content-->
				</div>
			</div>
		</div>
		<!-- END MODAL ADD, EDIT, VIEW -->
		
		<!-- Modal for search -->
		<div class="modal fade" id="searchOdrMaint" tabindex="-1" role="dialog" aria-labelledby="searchOdrMaint" data-keyboard="false" data-backdrop="static">
			<div class="modal-dialog" role="document" style="width:55%;overflow:auto">
				<div class="modal-content">
				<!-- Remote content load here -->
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
		
		<!-- MODAL WHEN CLICK Search CONTORL NUMBER -->
		<div class="modal fade" id="titleSearch" tabindex="-1" role="dialog" aria-labelledby="titleSearch" data-keyboard="false" data-backdrop="static">
			<div class="modal-dialog" role="document" style="width:55%;overflow:auto">
				<div class="modal-content">
				<!-- Remote content load here -->
				</div>
			</div>
		</div>
		<!-- Modal END modal search  control number-->
		
		<!-- MODAL WHEN CLICK Search patronid -->
		<div class="modal fade" id="modal_patrSearch4order" tabindex="-1" role="dialog" aria-labelledby="modal_patrSearch4order" data-keyboard="false" data-backdrop="static"> 
			<div class="modal-dialog" style="width:50%;">
				    <div class="modal-content" id="modal_patrSearch4orderContent">
					  <!-- Remote content load here  -->
				  	</div>
			</div>
		</div>
		<!-- Modal END modal search patronid-->
		
		

	</body>
</html> --%>