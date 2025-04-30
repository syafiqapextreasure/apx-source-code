<%@page import="java.util.List"%>
<%@ page import="java.util.*" %>
<!DOCTYPE html>
<html>
<body>

	  <div class="panel-group" id="testing" style=" position: relative;overflow:hidden;">
        <div class="panel panel-default">
            <div class="panel-heading clearfix">
				<div class="btn-group pull-right ">
					<a class="btn btn-primary" id='retrieveLink' data-toggle='modal' data-target='#searchCancelOrder' data-placement="bottom" href='Acquisition_Maintenance?module=Cancel_Order&action=Modal_CancelOrderSearch' title="Search"><span class="glyphicon glyphicon-search" style="color:white"></span></a>
					&nbsp;			
					<button type="button" class="btn btn-primary" id="resubmit" data-toggle="modal" data-placement="bottom" title="Resubmit" onclick="resubmit()" ><span class="glyphicon glyphicon-retweet" style="color:white"></span></button>
					<button type="button" class="btn btn-primary" id="btn_add" href="" data-placement="bottom" title="Purge" onclick="deleteRecord()" ><span class="glyphicon glyphicon-trash" style="color:white"></span></button>
				</div>
            </div>
              <div id="search" class="panel-collapse collapse in">
			<div class="panel-body" id="cancel_orderList">
				<table class="table table-bordered" id="cancelOrderList" style="font-size:10pt">
					<thead>
						<tr>
							<th><input type = "checkbox" class="checkAll" id = "checkAll"></th>
							<th data-sortable="true" style="text-align: center;">Order No.</th>
							<th data-sortable="true" style="text-align: center;">Vendor</th>
							<th data-sortable="true" style="text-align: center;">Order Date</th>
							<th data-sortable="true" style="text-align: center;">Cancellation Date</th>
							<th data-sortable="true" style="text-align: center;">Title</th>
							<th data-sortable="true" style="text-align: center;">Copies</th>
							<th data-sortable="true" style="text-align: center;">Received</th>
							<th data-sortable="true" style="text-align: center;">Status</th>
							<th data-sortable="true" style="text-align: center;">Order Type</th>
							<th data-sortable="true" style="text-align: center;">Feedback</th>
						</tr>
					</thead>
					 <tbody>
		
													
					</tbody>
				</table>
			</div>
		</div>
        </div>
    </div>


			<div id="searchCancelOrder" class="modal fade" role="dialog" data-backdrop="static">
				    <div class="modal-dialog"  style="width:900px;">
					  <div class="modal-content">
						  <!-- Remote content load here -->
						  </div>
					</div>
				</div>
				
				
				<div id="resubmitVendor" class="modal fade" role="dialog" data-backdrop="static">
				    <div class="modal-dialog"  style="width:900px;">
					  <div class="modal-content">
					  	<div class="modal-body">		
							<div class="panel-group" id="accordion" role="tablist"
								aria-multiselectable="true">
								<div class="panel panel-default">
									<div class="panel-heading">
										<h4 class="panel-title">
											<a data-toggle="collapse" data-parent="#form_parent"
												href="#search">Resubmit</a>
										</h4>
									</div>
									<div id="search" class="panel-collapse collapse in">
										<div class="panel-body">
										  	<form role="form" class="form-horizontal" id="current_form"
												onsubmit="searchOrder()">
												<!-- Vendor Code -->
												<div class="form-group">
													<div class="col-sm-2 col-md-2 col-xs-4">
														<label for="checkbox-vendorCode">Vendor:</label>
													</div>
													<div class="col-sm-2 col-md-2 col-xs-10">
														<input type="text" class="form-control text CT03VEND" id="vendorCode"
															onblur="updateVendorName()" onkeyup="updateVendorName()">
													</div>
												 		<div class="col-sm-1 col-md-1 col-xs-2">
															<button type="button" class="btn btn-default selectPopup" data-toggle="modal" data-target="#vendorSearch" href="Modal_Vendor?action=vendor" >...</button>
														</div>
													<div class="col-sm-4 col-md-4">
														<div class="div-vendorName" id="divvendorName"></div>
													</div>
												</div>
											</form>
										</div>
									</div>
								  </div>
							</div>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-primary" onclick="updateVendor()" data-dismiss="modal">Update</button>
							<button type="button" id="cancel" class="btn btn-primary" data-dismiss="modal">Cancel</button>
						</div>
					</div>
				</div>
			</div>
			
			<div id="vendorSearch" class="modal fade" role="dialog" data-backdrop="static">
				    <div class="modal-dialog"  style="width:900px;">
					  <div class="modal-content">
						  <!-- Remote content load here -->
						  </div>
					</div>
				</div>
				
					<!-- MODAL WHEN CLICK Circulation Modal-->
    <div class="modal fade" id="displayBO" tabindex="-1" role="dialog" aria-labelledby="circulationModal" aria-hidden="true" data-backdrop="static"> 
		<div class="modal-dialog" style="width:70%;">
			    <div class="modal-content" id="BORecord">
				  <!-- Remote content load here -->
			  	</div>
		</div>
	</div>
</body>
    
</html>
            