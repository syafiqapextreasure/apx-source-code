<%@ page contentType="text/html; charset=UTF-8" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
	<script type="text/javascript" src="${pageContext.request.contextPath}/plugin/bootstrap-multiselect/js/bootstrap-multiselect.js"></script>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/plugin/bootstrap-multiselect/css/bootstrap-multiselect.css" type="text/css"/>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/acquisition/invoiceEntry.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath() %>/plugin/moment.min.js"></script>
	<link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">
	
	<!-- <script src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.min.js"></script>
	<script src="https://cdn.datatables.net/select/1.2.7/js/dataTables.select.min.js"></script> -->
	
</head>
	<style>
		/* #modalInvoice {
		    overflow-y: scroll;
		} */
	</style>


<body>	
	<div class="container">
		<div class="panel-group" id="accordion" role="tablist"
			aria-multiselectable="true">
			<div class="panel panel-default">
				<div class="panel-heading" role="tab" id="headingOne">
					<h4 class="panel-title">
						<a role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseSearch" aria-expanded="true"
							aria-controls="collapseSearch"> Search </a>
					</h4>
				</div>
				<div id="collapseSearch" class="panel-collapse collapse in"
					role="tabpanel" aria-labelledby="headingOne">
					<div class="panel-body">
						<!-- Search Form -->
						<form class="form-horizontal" name="searchForm" id="searchForm">
							<!-- Vendor -->
							<div class="form-group">
								<div class="col-sm-1 col-md-1">
									<input type="radio" id="radio-vendorCode" name="searchMethod" value="radio-vendorCode">
								</div>
								<div class="col-sm-2 col-md-2">
									<label for="radio-vendorCode">Vendor: </label>
								</div>
								<div class="col-sm-4">
									<div class="input-group">
										<input type="text" class="form-control" id="input-vendorCode" name="inputVendorCode" autocomplete="off">
										<a href="Modal_Vendor" id="searchvendor" class="input-group-addon btn btn-primary" data-toggle="modal" data-target="#modal_vendorSearch">
										<span class="glyphicon glyphicon-option-horizontal"></span></a>
									</div>
								</div>
								<!-- <div class="col-sm-4 col-md-4">
									<div id="div-vendorName"></div>
								</div> -->
							</div>
							
							<div class="form-group">
								<div class="col-sm-3 col-md-3"></div>
								<div class="col-sm-4 col-md-4">
									<div id="div-vendorName"></div>
								</div>
							</div>
							
							<!-- Order Date -->
							<div class="form-group">
								<div class="col-sm-offset-1 col-sm-2">
									<label for="checkbox-date">Order Date: </label>
								</div>
								<div class="col-sm-4">
									<div class="input-daterange input-group" id="datepicker">
										<input type="text" class="input-sm form-control"  placeholder="Starting Date" name="start"
											id="input-startDate" autocomplete="off"/> <span class="input-group-addon">to</span>
										<input type="text" class="input-sm form-control" name="end" id="input-endDate" autocomplete="off"/>
									</div>
								</div>
							</div>

							<!-- Invoice Number -->
							<div class="form-group">
								<div class="col-sm-1 col-md-1">
									<input type="radio" id="radio-referenceNo" name="searchMethod" value="radio-referenceNo">
								</div>
								<div class="col-sm-2 col-md-2">
									<label for="radio-referenceNo">Reference No:</label>
								</div>
								<div class="col-sm-4 col-md-4">
									<input type="text" class="form-control" id="input-referenceNo">
								</div>
							</div>

							<!-- Order Number -->
							<div class="form-group">
								<div class="col-sm-1 col-md-1">
									<input type="radio" id="radio-orderNo" name="searchMethod" value="radio-orderNo">
								</div>
								<div class="col-sm-2 col-md-2">
									<label for="radio-orderNo">Order No:</label>
								</div>
								<div class="col-sm-4 col-md-4">
									<input type="text" class="form-control" id="input-orderNo" maxlength="10">
								</div>
							</div>

							<!-- Title -->
							<div class="form-group">
								<div class="col-sm-1 col-md-1">
									<input type="radio" id="radio-title" name="searchMethod" value="radio-title">
								</div>
								<div class="col-sm-2 col-md-2">
									<label for="radio-title">Title:</label>
								</div>
								<div class="col-sm-4 col-md-4">
									<!-- <input type="text" class="form-control" id="input-title"> -->
									<div class="input-group">
										<input type="text" class="form-control" id="input-title" name="input-title">
										<a href="RetrieveTitleModal?action=5" id="searchtitle" class="input-group-addon btn btn-primary" data-toggle="modal" data-target="#titleSearch">
										<span class="glyphicon glyphicon-option-horizontal"></span></a>
									</div>
								</div>
								<input type="hidden" class="form-control" id="input-titleCtrlno" name="input-titleCtrlno">
							</div>
							
							<div class="btn-group pull-right ">	
								<!-- <button type="button" id="invoiceRetrive" class="btn btn-primary" title="Retrieve"><span class="glyphicon glyphicon-log-in" style="color:white"></span></button>
								<button type="button" id="process" class="btn btn-primary" title="Process" data-original-title="Process" data-toggle='modal' data-target="#modalInvoice"><span class="glyphicon glyphicon-hourglass" style="color:white"></span></button> -->
									<div class="col-md-1"><button type="button" id="invoiceRetrive" class="btn btn-primary" title="Retrieve"><span class="glyphicon glyphicon-log-in" style="color:white"></span></button></div>
  									<div class="col-md-1"></div>
  									<div class="col-md-1"><button type="button" id="process" class="btn btn-primary" title="Process" data-original-title="Process" data-toggle='modal' data-target="#modalInvoice"><span class="glyphicon glyphicon-hourglass" style="color:white"></span></button></div>
							</div>

							<!-- <div class="btn-group pull-right ">	
								<button type="button" id="invoiceRetrive" class="btn btn-primary" title="Retrieve"><span class="glyphicon glyphicon-log-in" style="color:white"></span></button>
							</div>
							
							<div class="form-group">
								<div class="col-xs-1"></div>
									<button type="button" id="invoiceRetrive" class="btn btn-primary"> Retrieve </button>
									<button type="button" id="process" class="btn btn-primary" title="Process" data-original-title="Process" data-toggle='modal' data-target="#modalInvoice"><span class="glyphicon glyphicon-hourglass" style="color:white"></span></button>
									<button type="button" id="process" class="btn btn-primary" data-original-title="Process"  data-toggle='modal' data-target="#modalInvoice"> Process </button>
							</div> -->
						</form>
					</div>
				</div>
			</div>
			<div class="panel panel-default">
				<div class="panel-heading">
					<h4 class="panel-title">
						<a data-toggle="collapse" data-parent="#form_parent"
							href="#panel-result">Result</a>
					</h4>
				</div>
				<div id="panel-result" class="panel-collapse collapse">
					<div class="panel-body">
						<input type="hidden" id="input-parameters">
						<!-- Search Result -->
						<div id="search-results">
							<table class="table table-hover" id="table-invoice">
								<thead>
									<tr>
										<th><input name="select_all" value="1" id="example-select-all" type="checkbox" /></th>
										<th>Order No</th>
										<th>Reference No</th>
										<th>Title</th>
										<th>Order Date</th>
										<th>Status</th>
										<th>Vendor</th>
										<th>Qty Received</th>
										<th>Qty Paid</th>
									</tr>
								</thead>
								<tbody>
								</tbody>
							</table>
						</div>
					</div>
				</div>
		</div>
		</div>
	</div>
	
		<!-- INVOICE FOR PROCESS  -->
		<div class="modal fade" id="modalInvoice" tabindex="-1" role="dialog" aria-labelledby="ModalInvoice" aria-hidden="true" data-keyboard="false" data-backdrop="static">
			<div class="modal-dialog" role="document" style="width:90%">
				<div class="modal-content">
					<!-- Modal content-->
						<div class="modal-header">
							<h5 class="modal-title" id="modalName" align="center">Invoice</h5>
					    	<button type="button" id="closeModalInvoice" class="close" data-dismiss="modal" aria-label="Close">
					        	<span aria-hidden="true">&times;</span>
					        </button>
						</div>
						
						<div class="modal-body">
							<form role="form" class="form-horizontal" id="formdataInvoiceProcess" method="post">
								
								<div class="form-group">
									<div class="col-sm-1"></div>
									<div class="col-sm-2">
										<label>Invoice No</label>
									</div>
									<div class="col-sm-4">
										<input type="text" class="form-control" id="invoiceno" name="invoiceno">
									</div>
								</div>
								<input type="hidden" class="form-control" id="vendcode" name="vendcode">
								<!-- <input type="hidden" class="form-control" id="refno" name="refno"> -->
								<input type="hidden" class="form-control" id="excrate" name="excrate">
								<!-- <input type="text" class="form-control" id="NoteNo" name="NoteNo">
								<input type="text" class="form-control" id="NoteType" name="NoteType"> -->
								
								<div class="form-group">
									<div class="col-sm-1"></div>
									<div class="col-sm-2"><label>Invoice Date</label></div>
									 <div class='col-sm-3'>
				                      	<div class="input-group date" id="invoiceDate">
				  						 	<input type="text" class="form-control" id="invoice_date" name="invoice_date">
				  						 	<span class="input-group-addon">
				  							<i class="glyphicon glyphicon-calendar"></i></span>
										</div>
				        			</div>
								</div>
								
								<%-- <div class="form-group">
									<div class="col-sm-1"></div>
									<div class="col-sm-2"><label>Currency</label></div>
										<div class="col-sm-5">
											<select class="form-control" id="currency" name="currency">
												<jsp:include page="../Select_CurrencyList.jsp"></jsp:include>
											</select>
										</div>
								</div> --%>
								
								<div class="form-group">
									<table id="processInvoice" class="table table-bordered table-striped" width="100%">
										<thead>
											<tr>
												<th>Order</th>
												<th>Title</th>
												<th>Qty To Pay</th>
												<th>Currency</th>
												<th>Unit Price</th>
												<th>Total Foreign Price</th>
												<th>Exch/Rate</th>
												<th>Total Local Price</th>
												<th>Max Qty To Pay</th>
												<th>refno</th>
												<th>Action</th>
											</tr>
										</thead>
										 <!-- <tfoot>
								            <tr>
								                <th colspan="4" style="text-align:right" class="testtotal">Total:</th>
								                <th></th>
								            </tr>
								        </tfoot> -->
									</table>
								</div>
								
								<br>
								
								<div class="form-group">
									<table id="tableInvoiceProcess" class="table table-borderless" align="right" style="width:80%">
									  <thead>
									    <tr>
									      <td></td>
									      <td></td>
									      <td></td>
									      <td align="right">
									      	<div  id="totalForeign" class='col-sm-7'></div>
									      </td>
									      <td id="forex">RM</td>
									      <td align="right">
									      	<div id="total" class='col-sm-7'></div>
									      </td>
									    </tr>
									  </thead>
									  <tbody>
									    <tr>
									      	<th align="right">Discount</th>
									      	<th>
									      		<div class='col-sm-4'>
									      			<input type="text" class="form-control" id="discount" name="discount"> 
									      		</div> % of total order
									      	</th>
									      <td class="forexForeignUnit"></td>
									      <td>
									      	<div class='col-sm-8'>
									      		<input type="text" class="form-control" id="discountForign" name="discountForign" style="text-align:right;">
									      	</div>
									      </td>
									      <td>RM</td>
									      <td>
									      	<div class='col-sm-8'>
									      		<input type="text" class="form-control" id="discount2" name="discount2" style="text-align:right;">
									      	</div>
									      </td>
									    </tr>
									    <tr>
									      <td></td>
									      <th>Handling Charges</th>
									      <td class="forexForeignUnit"></td>
									      <td>
									      	<div class='col-sm-8'>
									      		<input type="text" class="form-control" id="handlingForign" name="handlingForign" style="text-align:right;">
									      	</div>
									      </td>
									      <td>RM</td>
									      <td>
									      	<div class='col-sm-8'>
									      	<input type="text" class="form-control" id="handling" name="handling" style="text-align:right;">
									      	</div>
									      </td>
									    </tr>
									    <tr>
									      <td></td>
									      <th>Service Charges</th>
									      <td class="forexForeignUnit"></td>
									      <td>
									      	<div class='col-sm-8'>
									      		<input type="text" class="form-control" id="servicesForign" name="serviceForign" style="text-align:right;">
									      	</div>
									      </td>
									      <td>RM</td>
									      <td>
									      	<div class='col-sm-8'>
									      		<input type="text" class="form-control" id="services" name="services" style="text-align:right;">
									      	</div>
									      </td>
									    </tr>
									    <tr>
									    	<td></td>
									    	<td></td>
									    	<td></td>
									    	<td></td>
									    	<!-- <td class="forexForeignUnit"></td>
									    	<td align="right">
									    		<div id="finaltotalForeign" class='col-sm-7'></div>
									    	</td> -->
									    	<td>RM</td>
									    	<td align="right">
									    		<div id="finaltotal" class='col-sm-7'></div>
									    	</td>
									    </tr>
									  </tbody>
									</table>
								</div>
								
							
								<!-- <div class="row">
    								<div class="col-sm-3 col-md-6 col-lg-4">
										Order No
    								</div>
								    <div class="col-sm-9 col-md-6 col-lg-8">
					
								    </div>

								</div> -->
								<div class="form-group modal-footer">
							      	<button type="submit" id="saveInvoice" class="btn btn-primary">Ok</button>
							        <button type="button" id="cancel" class="btn btn-secondary" data-dismiss="modal">Cancel</button>
					      		</div>
							</form>
						</div>
						
					<!-- END MODAL CONTENT -->
				</div>
			</div>
		</div>
		
		<!-- INVOICE FOR UPDATE ACCESSION RECORDS  -->
		<div class="modal fade" id="modalUpdateACC" tabindex="-1" role="dialog" aria-labelledby="ModalUpdateACC" aria-hidden="true" data-keyboard="false" data-backdrop="static">
			<div class="modal-dialog" role="document" style="width:50%">
				<div class="modal-content">
					<!-- Modal content-->
					<div class="modal-header">
						<h5 class="modal-title" id="modalName" align="center">Update Accession Records</h5>
					    	<button type="button" id="closeModalInvoice" class="close" data-dismiss="modal" aria-label="Close">
					        	<span aria-hidden="true">&times;</span>
					        </button>
					</div>
					<div class="modal-body">
						
						<form role="form" class="form-horizontal" id="formdataUpdAcc" method="post">
							<p><h4>Please select the accession number bellow which will update for the <span class="updAccInvno"></span></h4></p>
							
							<br>
								
								<table id="updAccStatus" class="table table-bordered table-striped" width="100%">
									<thead>
										<tr>
											<th>No</th>
											<th>Order No</th>
											<th>Accession No</th>
											<th>Update</th>
										</tr>
									</thead>
								</table>
								
								<div class="form-group">
									<div class="col-sm-2">
										<label>
											<input type="checkbox" name="selectAllAcc" id="selectAllAcc" value="selectAllAcc">
											Select All
										</label>
									</div>
								</div>
								
								<div class="form-group">
									<div class="col-sm-3"><b>
										Records Selected : <span class="totalrecord">0</span></b>
									</div>
								</div>
								
								<div class="form-group modal-footer">
							      	<button type="button" id="saveUpdAcc" class="btn btn-primary" data-dismiss="modal">Ok</button>
							        <!-- <button type="button" id="cancel" class="btn btn-secondary" data-dismiss="modal">Cancel</button> -->
					      		</div>
							
						</form>
					</div>
					<!--END Modal content-->
				</div>
			</div>
		</div>
		<!-- END FOR UPDATE ACCESSION RECORDS -->
	
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
		
		<!-- MODAL WHEN CLICK Search fundDistribution -->
		<div class="modal fade" id="fundDistribution" role="dialog"  data-backdrop="static">
			<div class="modal-dialog modal-lg">
				<div class="modal-content" id="fundContent">

					<!-- Remote content load here -->
				</div>
			</div>
		</div>
		<!-- MODAL  END  fundDistribution -->
		
		<!-- MODAL CREADIT DEBIT  -->
		<div class="modal fade" id="modalisShowCDNote" tabindex="-1" role="dialog" aria-labelledby="ModalisShowCDNote" aria-hidden="true" data-keyboard="false" data-backdrop="static">
			<div class="modal-dialog" role="document" style="width:65%">
				<div class="modal-content">
					<!-- Modal content-->
					<div class="modal-header">
						<h5 class="modal-title" id="modalName" align="center">View Credit/Debit Notes</h5>
					    	<button type="button" id="closeisShowCDNote" class="close" data-dismiss="modal" aria-label="Close">
					        	<span aria-hidden="true">&times;</span>
					        </button>
					</div>
					<div class="modal-body">
						<form role="form" class="form-horizontal" id="formdataisShowCDNote" method="post">
							<br>
								<table id="isShowCDNote" class="table table-bordered table-striped" width="100%">
									<thead>
										<tr>
											<th></th>
											<th>Note Type</th>
											<th>Remarks</th>
											<th>Note No</th> 
											<th>Received Date</th>
											<th>Foreign</th>
											<th>Foreign Amount</th>
											<th>Local Amount</th>
										</tr>
									</thead>
								</table>
								
								<div class="form-group modal-footer">
							      	<button type="button" id="saveisShowCDNote" class="btn btn-primary" data-dismiss="modal">Use</button>
							        <button type="button" id="cancelisShowCDNote" class="btn btn-secondary" data-dismiss="modal">Cancel</button>
					      		</div>
						</form>
					</div>
					<!-- END Modal content-->
				</div>
			</div>
		</div>
		<!-- END MODAL CREADIT DEBIT  -->
		
		<!-- Modal Edit Invoice-->
		<div class="modal fade" id="editInvoice" role="dialog">
		    <div class="modal-dialog modal-lg" style="width:60%;">
		      <div class="modal-content">
		        <div class="modal-header">
		          <button type="button" class="close" data-dismiss="modal">&times;</button>
		          <h4 class="modal-title">Edit</h4>
		        </div>
		        <div class="modal-body">
		       		<form role="form" class="form-horizontal" id="formdataedit" method="post">
		       		
		       			<span id="editInvoiceOrderno"></span>
		       			<!-- <span id="editInvoiceqty"></span> -->
		       			<span id="editInvoicemaxqty"></span>
		       			
		       			<div class="form-group">
							<div class="col-sm-1"></div>
								<div class="col-sm-2"><label for="editqtytopay">Qty To Pay</label></div>
									<div class='col-sm-2'>
										<input type="text" class="form-control" id="editqtytopay" name="editqtytopay">
									</div>
						</div>
		       		
		       			<div class="form-group">
							<div class="col-sm-1"></div>
							<div class="col-sm-2"><label>Currency</label></div>
							<div class="col-sm-4">
								<select class="form-control" id="editInvoicecurrency" name="editInvoicecurrency">
								<%-- 	<jsp:include page="../Select_CurrencyList.jsp"></jsp:include> --%>
								</select>
							</div>
						</div>
						
						<div class="form-group">
							<div class="col-sm-1"></div>
								<div class="col-sm-2"><label>Unit Price</label></div>
									<div class='col-sm-2'>
										<input type="text" class="form-control" id="editInvoiceUnitprice" name="editInvoiceUnitprice">
									</div>
						</div>
						
						<div class="form-group">
							<div class="col-sm-1"></div>
								<div class="col-sm-2"><label>Exch/Rate</label></div>
									<div class='col-sm-2'>
										<input type="text" class="form-control" id="editInvoiceExchRate" name="editInvoiceExchRate">
									</div>
						</div>

  
						<div class="modal-footer">
							<button type="button" id="saveEditInvoice" class="btn btn-primary" data-dismiss="modal">Ok</button>
							<button type="button" id="cancelEditInvoice" class="btn btn-secondary" data-dismiss="modal">Cancel</button>
					    </div>
					</form>
				</div>
		      </div>
		    </div>
		  </div>
		<!-- END Modal Edit Invoice-->
</body>

</html>