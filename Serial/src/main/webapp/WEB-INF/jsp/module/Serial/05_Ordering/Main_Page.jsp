<%@page import="com.ilmu.cataloging.Template_Maint.Template_Maintenance"%>
<%@page import="com.ilmu.cataloging.Template_Maint.Cataloging"%>
<%@page import="com.ilmu.global.*"%>
<%@page import="java.util.List"%>
<%@ page import="java.util.*" %>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.3.0/css/datepicker.min.css" />
<link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.3.0/css/datepicker3.min.css" />

<script src="//cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.3.0/js/bootstrap-datepicker.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/Serial/Order.js"></script>					
<script type="text/javascript" src="<%= request.getContextPath() %>/plugin/datatables/jquery.dataTables.min.js"></script>
 <script type="text/javascript" src="<%= request.getContextPath() %>/plugin/datatables/dataTables.bootstrap.min.js"></script>
 <link rel="stylesheet" href="<%= request.getContextPath() %>/plugin/datatables/dataTables.bootstrap.css">
<script type="text/javascript">
            $(function () {
            	$("#orderDate1").datepicker({
            		format: 'dd/mm/yyyy'
            	});
            	$("#orderDate2").datepicker({
            		format: 'dd/mm/yyyy'
            	});
            });
        </script>
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
</head>
<body>
<div class="panel-group" id="accordion" role="tablist"
			aria-multiselectable="true">
			<div class="panel panel-default">
				<div class="panel-heading">
					<h4 class="panel-title">
						<a data-toggle="collapse" data-parent="#form_parent"
							href="#search">Search</a>
					</h4>
				</div>
				<div id="search" class="panel-collapse collapse in">
					<div class="panel-body">
						<form role="form" class="form-horizontal" id="current_form"
							onsubmit="searchOrder()">
							<fieldset class="col-md-6">    	
								<legend>Selection</legend>
								<div class="panel panel-default">
									<div class="panel-body">
										<div class="form-check">
									      <label class="form-check-label">
									        <input type="radio" class="form-check-input" name="optionsRadios" id="optionsRadios1" value="all" checked>
									       	Retrieve All
									      </label>
									    </div>
										<!-- <div class="form-group row">
										    <label class="col-sm-5">
										        <input type="radio" class="form-check-input" name="optionsRadios" id="optionsRadios1" value="ctrlno">
										      	Control Number
										      </label>	
										      <div class="col-sm-5">
										        <input type="text" class="form-control" id="ctrlNo" maxlength="10" disabled>
										      </div>	    
										</div> -->
									    	<div class="form-group row">
												<label for="Date" class="col-sm-5 col-form-label">
													 <input class="form-check-input" type="radio" name="optionsRadios" id="inlineRadiocharge" value="orderdate" onchange="change()">
													 Retrieve orders created from
												</label>
												<div class="col-sm-7 searchInput">
													<div class="input-group">
													    <input type="text" class="form-control" maxlength="10" id="orderDate1" readonly="readonly"/>
													    	<span class="input-group-addon">to</span>
													    <input type="text" class="form-control" maxlength="10" id="orderDate2" readonly="readonly"/>
													</div>
												</div>
												
											</div>
										 	<!-- <div class="form-group row">
												<label for="Date" class="col-sm-5 col-form-label">
													 <input class="form-check-input" type="checkbox" name="criteria" id="inlineRadiocharge" value="date" onchange="change()" checked>
													Vendor
												</label>
												<div class="col-sm-5">
										        	<input type="text" class="form-control CT03VEND" id="input-vendorCode">
										     	 </div>
										     	 <div class="col-sm-1 col-md-1 col-xs-2">
													<button type="button" class="btn btn-default selectPopup" data-toggle="modal" data-target="#vendorSearch" href="Modal_Vendor" >...</button>
												</div>
											</div> -->
											<div class="form-group row">
											  <div class="col-lg-6">
											    <div class="input-group">
											      <span class="input-group-addon">
											         <input class="form-check-input" type="checkbox" name="criteria" id="inlineRadiocharge" value="date" onchange="change()" checked>
											      </span>
											     	<input type="text" class="form-control CT03VEND" id="input-vendorCode" placeholder="Vendor">
											       <span class="input-group-btn">
											        <button type="button" class="btn btn-default selectPopup" data-toggle="modal" data-target="#vendorSearch" href="Modal_Vendor?action=vendor" >...</button>
											      </span>
											    </div><!-- /input-group -->
											  </div><!-- /.col-lg-6 -->
											</div><!-- /.row -->
										 	<div class="form-group row">
										 	<label for="Date" class="col-sm-5 col-form-label">
													&nbsp;
												</label>
												<div class="col-sm-7 col-md-7">
													<div class="div-vendorName" id="divvendorName"></div>
												</div>
											</div>
											<div class="btn-group pull-right ">	
											<button type="button" id="retrievebtn" class="btn btn-primary" href="" onclick="retrieveRecords()"  data-placement="top" title="Retrieve" disabled><span class="glyphicon glyphicon-log-in" style="color:white"></span></button>
											</div>
										</div>
									</div>					
								
							</fieldset>	
							<fieldset class="col-md-5">    	
								<legend>Order Mode</legend>
								
								<div class="panel panel-default">
									<div class="panel-body">
										<div class="form-group row">
											<label for="Date" class="col-sm-4 col-form-label">
												<input class="form-check-input" type="checkbox" name="criteria" id="inlineRadiocharge" value="date" onchange="change()">
													Generate
											</label>
											<div class="col-sm-5">
										       <select class="form-control" id="ordermode">
											       	<option value="0">Please select</option>
											       	<option value="20">Purchase Order</option>
											       	<option value="40">Proforma Invoice</option>
											       	<option value="44">Proforma Order</option>
											       	<option value="60">Gift Request</option>
										       </select>
										     </div>
										</div>
									</div>
								</div>
								
							</fieldset>	
						</form>
					</div>
				</div>
				
			</div>
		</div>
		
		<div class="panel-group" id="accordion" role="tablist"
			aria-multiselectable="true">
			<div class="panel panel-default">
				<div class="panel-heading">
				<!-- 	<h4 class="panel-title">
						<a data-toggle="collapse" data-parent="#form_parent"
							href="#search">Result</a>
					</h4> -->
					<div>		
								<button type="button" class="btn btn-primary" id="viewOdrMaint" data-toggle='modal' data-target="#modalOdrMaint" data-whatever="View Record Order" title="Details" disabled><span class="glyphicon glyphicon-list-alt" style="color:white"></span></button>
<!-- 								<button type="button" class="btn btn-primary" id="orderbtn" data-placement="bottom" title="Order List" onclick="previewOrder()" title="Order List" disabled><span class="glyphicon glyphicon-sort-by-attributes" style="color:white"></span></button> -->
								<button type="button" class="btn btn-primary" id="previewbtn" data-placement="bottom" title="Preview" onclick="preview()" title="Preview" disabled><span class="glyphicon glyphicon-eye-open" style="color:white"></span></button>
								<a class="btn btn-primary" id='processOrder' data-toggle='modal' data-placement="bottom" title="Process" disabled><span class="glyphicon glyphicon-hourglass" style="color:white"></span></a>
					</div>
				</div>
				 <div id="search" class="panel-collapse collapse in">
			<div class="panel-body" id="cancel_orderList">
				<table class="table table-bordered orderingList" id="orderingList" style="font-size:10pt">
					<thead>
						<tr>
							<th><input type = "checkbox" class="checkAll" id = "checkAll"></th>
							<th data-sortable="true" style="text-align: center;">Order No.</th>
							<th data-sortable="true" style="text-align: center;">Created Date</th>
							<th data-sortable="true" style="text-align: center;">ISSN</th>
							<th data-sortable="true" style="text-align: center;">Author/Title</th>
							<th data-sortable="true" style="text-align: center;">Quantity</th>
							<th data-sortable="true" style="text-align: center;">Unit Price</th>
							<th data-sortable="true" style="text-align: center;">Total Price</th>
							<th data-sortable="true" style="text-align: center;">Local Price</th>
						</tr>
					</thead>
					 <tbody id="OrderList">
		
													
					</tbody>
				</table>
			</div>
		</div>
			</div>
		</div>
		
		<div id="modalProcess" class="modal fade" role="dialog" data-backdrop="static">
				    <div class="modal-dialog"  style="width:900px;">
					  <div class="modal-content" id="processContent">
						  <!-- Remote content load here -->
						  </div>
					</div>
		</div>
		
		
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
					<div class="form-group orderhideorView">
							<div class="col-sm-2">
									 &nbsp;<label for="orderno">Order No</label>
							</div>
							<div class="col-sm-2">
								<input type="text" class="form-control" id="orderno" name="orderno">
							</div>
					</div>
					
					<div class="form-group orderhideorView">
							<div class="col-sm-2"><label>Created on</label>
							</div>
							<div class="col-sm-2" id="createdon">
							</div>
					</div>
					
					
					<div class="modal-body" id="detail">
						<ul class="nav nav-tabs" role="tablist">
							<li id="Particulars" class="active"><a href="#particular-tab"
								aria-controls="particular-tab" role="tab" data-toggle="tab">Particulars </a></li>
							<li id="CardexTab"><a href="#cardex-tab" aria-controls="cardex-tab" role="tab"
								data-toggle="tab">Cardex</a></li>
							<li id="StatusTab"><a href="#status-tab" aria-controls="status-tab" role="tab"
								data-toggle="tab">Status</a></li>
							<li id="RequestorTab"><a href="#requestor-tab" aria-controls="requestor-tab" role="tab"
								data-toggle="tab">Request</a></li>
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
										
										<div class="form-group">
											<div class="col-sm-1"></div>
											<div class="col-sm-3"><label>Control Number</label></div>
											<div class="col-sm-4">
										    	<div class="input-group">
											    	<input type="text" class="form-control" id="input-contorlNo" name="inputControlNo">
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
											    	<input type="text" class="form-control" id="input-vendorCode2" name="inputVendorCode">
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
												<label><input type="checkbox" class="form-check-input" id="checkboxfrequency" name="Retain" value="frequency">&nbsp Frequency</label>
											</div>
											<div class="col-sm-4">
												<select class="form-control" id="frequency" name="frequency">
													<jsp:include page="../Select_Frequency.jsp"></jsp:include>
												</select>
											</div>
										</div>
										
										<div class="form-group">
											<div class="col-sm-1"></div>
											<div class="col-sm-3">
												<label><input type="checkbox" class="form-check-input" id="checkboxSubj" name="Retain" value="subj">&nbsp General Subject</label>
											</div>
											<div class="col-sm-4">
												<select class="form-control" id="subj" name="subj">
													<jsp:include page="../Select_GeneralSubj.jsp"></jsp:include>
												</select>
											</div>
										</div>
										
										<div class="form-group">
											<div class="col-sm-1"></div>
											<div class="col-sm-3">
												<label>First Volume/Issue</label>
											</div>
											<div class="col-sm-4">
												<div class="input-group">
													<input type="text" class="form-control"
														name="volFrom" id="volFrom" /> <span
														class="input-group-addon">/</span> <input type="text"
														class="form-control" name="volisfrom" id="volisfrom" />
												</div>
											</div>
										</div>

										<div class="form-group">
											<div class="col-sm-1"></div>
											<div class="col-sm-3">
												<label for="foreign">Start Date</label>
											</div>
											<div class="col-sm-2">
												<input type="text" class="form-control" id="startDate" name="startDate">
											</div>
											<div class="col-sm-1">
												<label for="foreign">Stop Date</label>
											</div>
											<div class="col-sm-2">
												<input type="text" class="form-control" id="stopDate" name="stopDate">
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
												<label for="foreign">Foreign Cost</label>
											</div>
											<div class="col-sm-3">
												<input type="text" class="form-control" id="foreign-cost" name="foreignCost">
											</div>
										</div>
										
										<div class="form-group">
											<div class="col-sm-1"></div>
											<div class="col-sm-3">
												<label for="local">Local Cost</label>
											</div>
											<div class="col-sm-3">
												<input type="text" class="form-control" id="local-cost" name="localcost">	
											</div>
										</div>
										
										<div class="form-group">
											<div class="col-sm-1"></div>
											<div class="col-sm-3">
												<label>Quantity</label>
											</div>
											<div class="col-sm-4">
												<div class="input-group">
													<input type="text" class="form-control"
														name="qty" id="qty" /> <span
														class="input-group-addon">subscription of</span> <input type="text"
														class="form-control" name="issue" id="issue" /><span
														class="input-group-addon">issue</span>
												</div>
											</div>
										</div>
								</div>
							</div>
							<!-- END TAB PARTICULAR -->
							
							<!-- TAB START CARDEX -->
							<div role="tabpanel" class="tab-pane pwidth-95" id="cardex-tab">
								<div class="margin-btm-15"></div>
									<div id="cardexContainer">
											<table id="cardex" class="table table-bordered table-striped cardex">
												<thead>
												 	<tr>
												 		<th>Copy</th>
														<th>Volume</th>
														<th>Issue</th>
														<th>Expected Date</th>
														<th>Received Date</th>
														<th>First Claim</th>
														<th>Second Claim</th>
														<th>Third Claim</th>
														<!-- <th>Date String</th> -->
												 	</tr>
											 	</thead>
											</table>
									</div>
							</div>
							<!-- TAB END CARDEX -->
							
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
														<label for="Code" class="col-sm-3 control-label">Current Status</label>
														<div class="col-sm-9" id="div-currentstatus">
														</div>
													</div>
													
													<div class="form-group">
														<label for="Code" class="col-sm-3 control-label">Invoice Status</label>
														<div class="col-sm-9" id="div-invoicestatus">
														</div>
													</div>

													<div class="form-group">
														<label for="Code" class="col-sm-3 control-label">Reference Number</label>
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
														<label for="Code" class="col-sm-3 control-label">Expected Date</label>
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
														<label for="Code" class="col-sm-4 control-label">First Claim on</label>
														<div class="col-sm-7">
															<input class="form-control" type="text" id="claims1" disabled>
														</div>
													</div>

													<div class="form-group">
														<label for="Code" class="col-sm-4 control-label">Second Claim on</label>
														<div class="col-sm-7">
															<input class="form-control" type="text" id="claims2" disabled>
														</div> 
													</div>

													<div class="form-group">
														<label for="Code" class="col-sm-4 control-label">Third Claim on</label>
														<div class="col-sm-7">
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
											<div class="panel-heading">Invoice/Payment Details</div>
											<div class="panel-body">
												<div class="form-horizontal">
													<div class="form-group">
														<label class="col-sm-2 control-label">Invoice No</label>
														<div class="col-sm-3" id="invoiceNo">
														</div>
														<label class="col-sm-2 control-label">Currency</label>
														<div class="col-sm-3" id="invCurrency">
														</div>
													</div>
													
													<div class="form-group">
														<label class="col-sm-2 control-label">Local Amount</label>
														<div class="col-sm-3" id="lamount">
														</div>
														<label class="col-sm-2 control-label">Foreign Amount</label>
														<div class="col-sm-3" id="famount">
														</div>
													</div>
													
													<div class="form-group">
														<label class="col-sm-2 control-label">Invoice Date</label>
														<div class="col-sm-3" id="invDate">
														</div>
														<label class="col-sm-2 control-label">Payment Reference</label>
														<div class="col-sm-3" id="paymentRef">
														</div>
													</div>
													
													<div class="form-group">
														<label class="col-sm-2 control-label">Cheque No</label>
														<div class="col-sm-3" id="chequeNo">
														</div>
														<label class="col-sm-2 control-label">Voucher No</label>
														<div class="col-sm-3" id="voucherNo">
														</div>
													</div>
													
													<div class="form-group">
														<label class="col-sm-2 control-label">Cheque Date</label>
														<div class="col-sm-3" id="chequedate">
														</div>
														<label class="col-sm-2 control-label">Voucher Date</label>
														<div class="col-sm-3" id="voucherdate">
														</div>
													</div>
													
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
														
														<div id="viewReqestor">
															<table id="requestor" class="table table-bordered table-striped requestor1">
																<thead>
																 	<tr>
																 		<th>Request No</th>
																		<th>Requestor</th>
																		<th>Name</th>
																		<th>Date Requested</th>
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
		
				<div class="modal fade" id="vendorSearch" role="dialog"  data-backdrop="static">
				    <div class="modal-dialog">
						  <div class="modal-content">
						  <!-- Remote content load here -->
						  </div>
					</div>
				</div>
				
		<div class="modal fade" id="fundDistribution" role="dialog"  data-backdrop="static">
				    <div class="modal-dialog modal-lg">
						  <div class="modal-content" id="fundContent">
						  <!-- Remote content load here -->
						  </div>
					</div>
				</div>
	</body>
    
</html>