<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
  <%@page import="java.util.*,com.ilmu.global.*,
				java.util.*"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>W-ILMU / Receipting / Transaction</title>

 <script type="text/javascript" src="<%= request.getContextPath() %>/js/Receipting/Receipting.js"></script>
<script>
$(document).ready(function() {
  var sPageURL = decodeURIComponent(window.parent.location);
  		var sURLVariables;
  		if(sPageURL.indexOf('?') != -1){
	 sURLVariables = sPageURL.split('?');
		var sURLVariables1 = sURLVariables[1];
		 var varArray = sURLVariables1.split('='); //eg. index.html?msg=1
		if((varArray[1]!=null)&& varArray[1]!=''){
		document.getElementById("PatronId").value = varArray[1];
		$("#PatronId").focus();
	}
		}
		
});
</script>
</head>
<body>
	<div class="container">
		<!-- <ol class="breadcrumb">
			<li><a href="<spring:url value="/" />">W-ILMU</a></li>
			<li><a href="<spring:url value="/receipting" />">Receipting</a></li>
			<li><a href="<spring:url value="/receipting/receipting" />">Receipting</a></li>
			<li class="active">Transaction</li>
		</ol>-->

		<div class="panel panel-default" id="PatronSearchPanel"
			action-url="<spring:url value="/receipting/search-patron/submit-ajax" />">
			<div class="panel-heading">
				<div class="row">
					<div class="col-xs-8">
						<%
							int receiptno = Glnumb.GetBuffPoint("RECEIPTNO")-1;
						%>
						<label>Last Receipt No. : <%=Handler.concatMatno(String.valueOf(receiptno)) %></label>
						<input type="hidden" value = "<%=(String)session.getAttribute("screenname")%>" id="screenname">
					</div>
					<div class="col-xs-4">
						<div class="pull-right">
							<a class="btn btn-sm btn-primary refresh" target="_self" title="New"
								action="New"><i class="glyphicon glyphicon-refresh"></i></a>
						</div>
					</div>
				</div>
			</div>
			<div class="panel-body">
				<div class="row">
					<div class="col-xs-12 col-sm-10 col-md-6 col-lg-4">
						<div class="inline-block va-top">
							<div class="margin-btm-5"></div>
							<div class="input-group">
								<div class="input-group-addon">Patron ID</div>
								<input type="text" class="form-control PatronId" id="PatronId"
									name="PatronId" placeholder="Patron ID" /> 
								<span class="input-group-btn">
									<button  class="btn btn-primary" id="btn_add" data-toggle="modal" action="advanceSearch"
												data-target="#searchPatron" href="Modal_PatrSearch?action=addResv" title="Search">
										<i class="glyphicon glyphicon-search"></i>&nbsp;
									</button>
								</span>
							</div>
						</div>
						<!-- <div class="inline-block va-top">
							<div class="margin-btm-5"></div>
							<button class="btn btn-default" type="button"
								action-url="<spring:url value="/receipting/advance-search-patron" />"
								action="advanceSearch">...</button>
						</div>-->
					</div>

					<div class="col-xs-12 col-sm-10 col-md-6 col-lg-4 hide" patron-name id="patron-name">
						<div class="margin-btm-5"></div>
						<div class="input-group">
							<span class="input-group-addon"><i
								class="glyphicon glyphicon-user"></i></span> <input type="text"
								class="form-control" id="PatronName" disabled>
						</div>
					</div>
				</div>
			</div>
		</div>

		<div class="panel panel-default" id="TransactionPanel">

			<div class="panel-heading">Transactions</div>

			<div class="panel-body">

				<div>
					<ul id="receiptingTabs" class="nav nav-tabs" role="tablist">
						<li role="presentation" class="active"><a
							href="#transaction-due" aria-controls="transaction-due"
							role="tab" data-toggle="tab">Due</a></li>
						<li role="presentation"><a href="#transaction-paid"
							aria-controls="transaction-paid" role="tab" data-toggle="tab">Paid</a></li>
						<li role="presentation"><a href="#transaction-receipt"
							aria-controls="transaction-receipt" role="tab" data-toggle="tab">Receipt</a></li>
					</ul>



					<div class="tab-content">
						<!--  Transaction Due -->
						<div role="tabpanel" class="tab-pane active" id="transaction-due">

							<div id="pagingContainerDue"
								action-url="<spring:url value="/receipting/receipting/submit-ajax" />">

								<div class="margin-btm-15"></div>

								<div paging-pagination="pagingContainerDue" class="paging"></div>

								<div class="margin-btm-15"></div>


								<div class="panel panel-default">
									<div class="panel-heading">
										<div class="row">
											<div class="col-xs-8">Transactions</div>
											<div class="col-xs-4">
												<div class="pull-right">
													<%-- <a class="btn btn-sm btn-primary hide" target="_self"
														title="Miscellaneous" action="Miscellaneous"
														action-url="<spring:url value="/receipting/miscellaneous" />"><i
														class="glyphicon glyphicon-plus"></i></a> --%>
														<a class="btn btn-primary hide btn-sm misc"  data-toggle="modal" data-target="#miscModal" title=""><i class="glyphicon glyphicon-plus"></i></a>
												</div>
											</div>
										</div>
									</div>
									<div class="panel-body" id="pendingtable">

									<%-- 	<table class="table table-bordered" id="pendingtable" style="font-size:11pt">
											<thead style="background-color:rgba(52, 73, 94, 0.94);color:#ecf0f1">
												<tr>
													<th>Trx #</th>
													<th>Date</th>
													<th>Code</th>
													<th>Officer</th>
													<th>Reference</th>
													<th>Accession No.</th>
													<th class="ta-right">Due</th>
													<th class="ta-right">Action</th>
													<th class="ta-right"></th>
												</tr>
											</thead>
											<tbody id="pendingtable">
												<tr class="data-template hide">
													<td>#number#</td>
													<td toFormat="Date">#date#</td>
													<td calculate-gst="#calculateGst#">#code#</td>
													<td>#officerId#</td>
													<td>#reference#</td>
													<td book-title="#title#">#accessionNumber#</td>
													<td class="ta-right" toFixed="2">#actualAmount#</td>
													<td class="ta-right"><a
														class="btn btn-success btn-sm hide"
														can-override="#canOverride#" title="Override"
														action="override"
														action-url="<spring:url value="/receipting/override/" />#number#"
														action-value="#number#"><i
															class="glyphicon glyphicon-copy"></i></a></td>
													<td class="ta-right"><input select-transaction
														transaction-number="#number#" transaction-code="#code#"
														transaction-due="#actualAmount#"
														transaction-accession-number="#accessionNumber#"
														transaction-calculate-gst="#calculateGst#" type="checkbox" /></td>
												<tr>
												<tr class="no-data-template hide">
													<td colspan="8">
														<div class="padding-5">
															<i class="glyphicon glyphicon-exclamation-sign"></i> No
															data found.
														</div>
													</td>
												<tr>
												<tr class="loading-template hide">
													<td colspan="8">
														<div class="padding-5">
															<i class="glyphicon glyphicon-hourglass"></i> Loading.
														</div>
													</td>
												<tr>
											</tbody>
										</table>
 --%>
										<div paging-pagination-entry="pagingContainerDue"
											class="paging"></div>

										<div paging-pagination="pagingContainerDue" class="paging"></div>
									</div>
								</div>


								<div class="margin-btm-15"></div>

								<div id="pagingSelectedTransactionContainer"
									gst-rate="${gstRate}" class="panel panel-default">
									<div class="panel-heading">Selected Transactions</div>
									<div class="panel-body">

										<div class="margin-btm-15"></div>

										<table paging-table="pagingSelectedTransactionContainer"
											class="table table-bordered table-striped table-hover table-condensed transaction" id="transaction">
											<thead style="background-color:rgba(52, 73, 94, 0.94);color:#ecf0f1">
												<tr>
													<th>Trx #</th>
													<th>Code</th>
													<th class="ta-right">Due</th>
													<th class="ta-right">Distribution</th>
													<!-- <th class="ta-right">GST</th> -->
													<th class="ta-right">Action</th>
												</tr>
											</thead>
											<tbody id="selectedTransaction">
											
											</tbody>
											<tfoot>
												<tr class="bold">
													<td colspan="2">Total</td>
													<td class="ta-right due" footer="due">0.00</td>
													<td id= "distribution" class="ta-right distribution" footer="distribution">0.00</td>
													<!-- <td class="ta-right" footer="gst">0.00</td> -->
													<td></td>
												</tr>
											</tfoot>
										</table>

									</div>
									<div class="panel-footer">
										<div class="row">
											<div class="col-lg-12" payment-summary>


												<div class="panel panel-default">
													<div class="panel-body">
												<form class="form-horizontal">
												<div class="form-group">
														    <label class="control-label col-sm-2" for="email">Payment</label>
														    <div class="col-sm-3">
														    <span id="Payment">0.00</span>
														    </div>
														  </div>
														<div class="form-group">
														    <label class="control-label col-sm-2" for="email">Rounding</label>
														    <div class="col-sm-3">
														    <span id="Rounding">0.00</span>
														    </div>
														  </div>
														  <div class="form-group">
														    <label class="control-label col-sm-2" for="email">Total</label>
														    <div class="col-sm-3">
														   	<span id="Rounding">0.00</span>
														    </div>
														  </div>  
												
														</form>
													</div>
												</div>

												<div class="panel panel-default">
													<div class="panel-body">
													<form class="form-horizontal">
													<div class="form-group">
														    <label class="control-label col-sm-2" for="email">Amount</label>
														    <div class="col-sm-3">
														      <input id="Amount" name="Amount"
																			placeholder="Received Amount"
																			class="form-control numeric" type="text" value="0.00" />
														    </div>
														  </div>
														  <div class="form-group">
														    <label class="control-label col-sm-2" for="pwd">Change</label>
														    <div class="col-sm-3"> 
																	<div class="col-xs-6 col-sm-6 col-md-7 col-lg-7">
																		<span id="Change">0.00</span>
																	</div>
														    </div>
														  </div>
														  <div class="form-group">
														    <label class="control-label col-sm-2" for="pwd">Mode</label>
														    <div class="col-sm-3"> 
<!-- 																<select id="PayMode" class="form-control"
																			init-value="Cash">
																			<option value="Cash">Cash</option>
																			<option value="Credit Card">Credit Card</option>
																			<option value="Credit Card">Debit Card</option>
																</select> -->
																<select id="PayMode" class="form-control">
																	<option value='0'>Please Select</option>
																	<%
																		List<PaymentMode> paymentMode = PaymentMode.getPaymentCode();
																		for (PaymentMode i: paymentMode) {
																		
																	%>
																	 <option value="<%=i.getCode()%>"><%=i.getDescription()%></option>
													
																	<%
																		}
																	%>
																</select>
														    </div>
														  </div>
														  <div class="form-group"> 
														   <div class="col-sm-2"> 
														   </div>
														  <div class="col-sm-3"> 
														    <button	id="PayBtn" type="button" onclick="accept()" class="btn btn-primary">Accept</button>
														  </div>
														  </div>
														</form>
													</div>
												</div>
											</div>
										</div>
									</div>

								</div>
							</div>
						</div>

						<!--  Transaction Paid -->
						<div role="tabpanel" class="tab-pane" id="transaction-paid">

							<div id="pagingContainerPaid"
								action-url="<spring:url value="/receipting/receipting/submit-ajax" />">

								<div class="margin-btm-15"></div>

								<div paging-pagination="pagingContainerPaid" class="paging"></div>

								<div class="margin-btm-15"></div>

								<table paging-table="pagingContainerPaid" paging-table
									class="table table-striped table-bordered table-hover table-condensed">
									<thead style="background-color:rgba(52, 73, 94, 0.94);color:#ecf0f1">
										<tr>
											<th>Trx #</th>
											<th>Date</th>
											<th>Code</th>
											<th>Reference</th>
											<th>Accession No.</th>
											<th class="ta-right">Due</th>
											<th class="ta-right">Override</th>
											<th class="ta-right">Paid Amount</th>
											<th class="ta-right">Balance</th>
										</tr>
									</thead>
									<tbody>
										<!-- <tr class="data-template hide">
											<td>#number#</td>
											<td toFormat="Date">#date#</td>
											<td>#code#</td>
											<td>#reference#</td>
											<td book-title="#title#">#accessionNumber#</td>
											<td class="ta-right" toFixed="2">#amount#</td>
											<td class="ta-right" toFixed="2">#overrideAmount#</td>
											<td class="ta-right" toFixed="2">#receiptAmountWithoutGst#</td>
											<td class="ta-right" toFixed="2">#actualAmount#</td>
										<tr>
										<tr class="no-data-template hide">
											<td colspan="12">
												<div class="padding-5">
													<i class="glyphicon glyphicon-exclamation-sign"></i> No
													data found.
												</div>
											</td>
										<tr>
										<tr class="loading-template hide">
											<td colspan="12">
												<div class="padding-5">
													<i class="glyphicon glyphicon-hourglass"></i> Loading.
												</div>
											</td>
										<tr> -->
									</tbody>
								</table>

								<div paging-pagination-entry="pagingContainerPaid"
									class="paging"></div>

								<div paging-pagination="pagingContainerPaid" class="paging"></div>

							</div>

						</div>

						<!--  Transaction Receipt -->
						<div role="tabpanel" class="tab-pane" id="transaction-receipt">

							<div class="row hide" id="receiptSearchPanel">
								<div class="col-xs-12 col-sm-10 col-md-6 col-lg-4">
									<div class="margin-btm-15"></div>
									<div class="input-group">
										<div class="input-group-btn" id="receiptSearchOption">
											<button type="button" class="btn btn-default dropdown-toggle"
												data-toggle="dropdown" aria-haspopup="true"
												aria-expanded="false">
												<span class="text-value">Receipt No</span> <span
													class="caret"></span>
											</button>
											<ul class="dropdown-menu">
												<li value="receiptNo" text="Receipt No."
													ph-value="Receipt No." class="pointer"><a>Receipt
														No.</a></li>
												<li value="date" text="Date" ph-value="dd/mm/yyyy"
													class="pointer"><a>Date</a></li>
											</ul>
										</div>
										<input type="text" class="form-control" maxlength="10"
											id="receiptSearchInput" option="receiptNo"
											old-value="receiptNo" placeholder="Receipt No." /> <span
											class="input-group-btn">
											<button class="btn btn-default" type="button" action="search">
												<i class="glyphicon glyphicon-search"></i>&nbsp;
											</button>
										</span>
									</div>
									<div class="margin-btm-5"></div>
								</div>
							</div>

							<div id="pagingContainerReceipt">


								<div class="margin-btm-15"></div>

								<div paging-pagination="pagingContainerReceipt" class="paging"></div>

								<div class="margin-btm-15"></div>

								<table paging-table="pagingContainerReceipt" paging-table
									class="table table-striped table-hover table-bordered table-condensed">
									<thead style="background-color:rgba(52, 73, 94, 0.94);color:#ecf0f1">
										<tr>
											<th>ReceiptNo</th>
											<th>Date</th>
											<!-- <th>Time</th> -->
											<th>Officer</th>
											<th class="ta-right">Payment</th>
											<!-- <th class="ta-right">Rounding</th>
											<th class="ta-right">GST</th>
											<th class="ta-right">Rounded</th> -->
											<th class="ta-right">Action</th>
										</tr>
									</thead>
									<tbody>
										<tr class="data-template hide">
											<td>#receiptNumber#</td>
											<td toFormat="Date">#receiptDate#</td>
											<!-- <td></td> -->
											<td>#officerId#</td>
											<td class="ta-right" toFixed="2">#totalPayment#</td>
											<!-- <td class="ta-right" toFixed="2">#rounding#</td>
											<td class="ta-right" toFixed="2">#totalGst#</td>
											<td class="ta-right" toFixed="2">#totalPaymentRounded#</td> -->
											<td class="ta-right"><button action="Print"
													action-value="#receiptNumber#" title="Print"
													class="btn btn-primary">
													<i class="glyphicon glyphicon-print"></i>
												</button></td>
										<tr>
										<tr class="no-data-template hide">
											<td colspan="9">
												<div class="padding-5">
													<i class="glyphicon glyphicon-exclamation-sign"></i> No
													data found.
												</div>
											</td>
										<tr>
										<tr class="loading-template hide">
											<td colspan="9">
												<div class="padding-5">
													<i class="glyphicon glyphicon-hourglass"></i> Loading.
												</div>
											</td>
										<tr>
									</tbody>
								</table>

								<div paging-pagination-entry="pagingContainerReceipt"
									class="paging"></div>

								<div paging-pagination="pagingContainerReceipt" class="paging"></div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<div class="modal fade" id="receiptingModal" role="dialog" data-backdrop="static">
		<div class="modal-dialog modal-lg">
			<div class="modal-content ">
				<div class="loading-template">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<h4 class="modal-title">Loading</h4>
					</div>
					<div class="modal-body">
						<i class="glyphicon glyphicon-flash"></i> Loading content..
					</div>
				</div>
			</div>
		</div>
	</div>
	
	<div class="modal fade" id="searchPatron" tabindex="-1" role="dialog" aria-labelledby="searchPatron"  aria-hidden="true"> 
		<div class="modal-dialog" style="min-width:60%;">
		  <div class="modal-content">
			  <!-- Remote content load here -->
		  </div>
	</div>
</div>

	<div class="modal fade" id="mosal" role="dialog" >
				    <div class="modal-dialog" style="width:900px;height:400px">
						  <div class="modal-content" id="data">
						 
						  </div>
					</div>
				</div>
				
				<div class="modal fade" id="miscModal" role="dialog" >
				    <div class="modal-dialog" style="width:900px;">
						  <div class="modal-content modal_contentmisc">
						  <!-- Remote content load here -->
						  </div>
					</div>
				</div>
</body>
</html>

