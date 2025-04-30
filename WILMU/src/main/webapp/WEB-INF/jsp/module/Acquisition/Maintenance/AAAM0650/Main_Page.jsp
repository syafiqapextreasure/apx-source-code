<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="ISO-8859-1"%>
<%@page import="java.util.List"%>
<%@ page import="java.util.*" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>

<link rel="stylesheet" href="<%= request.getContextPath() %>/css/global.css">
</head>

<body>
 <!-- Main content -->
 	<section class="content">
		<div class="container">

		<!-- Content Start -->
		<div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">
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

							<!-- Vendor Code -->
							<div class="form-group">
								<div class="col-sm-1 col-md-1 col-xs-1">
									<input type="checkbox" name="checkbox" id="checkbox-vendorCode"
										name="searchMethod" onclick="toggleSearchField()" value="vendor">
								</div>
								<div class="col-sm-2 col-md-2 col-xs-4">
									<label for="checkbox-vendorCode">Vendor:</label>
								</div>
								<div class="col-sm-2 col-md-2 col-xs-10">
									<input type="text" class="form-control" id="input-vendorCode"
										onblur="updateVendorName()" onkeyup="updateVendorName()">
								</div>
								<div class="col-sm-1 col-md-1 col-xs-2">
									<button type="button" class="btn btn-default"
										data-toggle="modal" id="button-vendorSearch"
										data-target="#modal_vendorSearch">...</button>
								</div>
								<div class="col-sm-4 col-md-5">
									<div id="div-vendorName"></div>
								</div>
							</div>

							<!-- Order Date -->
							<div class="form-group">
								<div class="col-sm-1">
									<input type="checkbox" name="checkbox" id="checkbox-date" value="orderDate">
								</div>
								<div class="col-sm-2">
									<label for="checkbox-date">Order Date: </label>
								</div>
								<div class="col-sm-4">
									<div class="input-daterange input-group" id="datepicker">
										<input type="text" class="input-sm form-control"
											name="start" id="input-startDate" /> <span
											class="input-group-addon">to</span> <input type="text"
											class="input-sm form-control" name="end"
											id="input-endDate" />
									</div>
									<!-- <div class="input-group date">
							    		<input type="text" class="form-control" id="input-startDate">
									    	<span class="input-group-addon">
	  											<i class="glyphicon glyphicon-calendar"></i>
	  										</span> to<input type="text" class="form-control" id="endDate">
									    	<span class="input-group-addon">
	  											<i class="glyphicon glyphicon-calendar"></i>
	  										</span>
						    		</div> -->
								</div>
							</div>

							<!-- Order Number -->
							<div class="form-group">
								<div class="col-sm-1 col-md-1">
									<input type="checkbox" name="checkbox" id="checkbox-orderNo"
										onclick="toggleSearchField()" value="orderNo">
								</div>
								<div class="col-sm-2 col-md-2">
									<label for="checkbox-orderNo">Order No.:</label>
								</div>
								<div class="col-sm-4 col-md-4">
									<input type="text" class="form-control" id="input-orderNo"
										maxlength="10" onblur="prependZeroes(this, 10)">
								</div>
							</div>

							<!-- Reference Number -->
							<div class="form-group">
								<div class="col-sm-1 col-md-1">
									<input type="checkbox" name="checkbox" id="checkbox-referenceNo"
										onclick="toggleSearchField()" value="referenceNo">
								</div>
								<div class="col-sm-2 col-md-2">
									<label for="checkbox-referenceNo">Reference No.:</label>
								</div>
								<div class="col-sm-4 col-md-4">
									<input type="text" class="form-control" id="input-referenceNo"
										maxlength="10" onblur="prependZeroes(this, 10)">
								</div>
							</div>

							<!-- Buttons -->
							<!-- <div class="form-group">
								<div class="col-sm-offset-1 col-sm-2 col-md-offset-1 col-md-2">
									<input type="button" class="btn btn-warning" value="Reset"
										onclick="resetForm()">
								</div>
							</div> -->
						</form>
					</div>
				</div>
			</div>

			<!-- Search Action -->
			<div class="panel panel-default">
				<div class="panel-heading">
					<h4 class="panel-title">
						<a data-toggle="collapse" data-parent="#form_parent"
							href="#actions">Actions</a>
					</h4>
				</div>
				<div id="actions" class="panel-collapse collapse in">
					<div class="panel-body">
						<!-- Content: Actions -->
						<button type="button" class="btn btn-primary" id="button-claim1"
							onclick="searchOrder(this.id)">1st Claim</button>
						<button type="button" class="btn btn-primary" id="button-claim2"
							onclick="searchOrder(this.id)">2nd Claim</button>
						<button type="button" class="btn btn-primary" id="button-claim3"
							onclick="searchOrder(this.id)">3rd Claim</button>
						<button type="button" class="btn btn-primary"
							id="button-unclaimed" onclick="searchOrder(this.id)">Unclaimed</button>
						<button type="button" class="btn btn-primary"
							id="button-retrieveAll" onclick="searchOrder(this.id)">Retrieve
							All</button>
						<button type="button" class="btn btn-primary" title="Refresh"
							id="button-refresh"><span class="glyphicon glyphicon-refresh" style="color:white"></span>
						</button>
					<!-- 	<button type="button" class="btn btn-default" disabled="disabled"
							id="button-cancellation">Cancellation</button>
						<button type="button" class="btn btn-default" disabled="disabled"
							id="button-claim">Claim</button> -->
					</div>
				</div>
			</div>

			<!-- Search Result -->
			<div class="panel panel-default" id="panel-result">
				<div class="panel-heading">
					<h4 class="panel-title">
						<a data-toggle="collapse" data-parent="#form_parent"
							href="#result">Result</a>
					</h4>
				</div>
				<div id="result" class="panel-collapse collapse">
					<div class="panel-body" id="search-result"
						style="overflow: scroll;">
				
					<table id="table-order" class="table table-hover"
							data-toggle="table" data-search="true" data-pagination="true"
							data-show-columns="true" data-click-to-select="true">
							<thead>
								<tr>
									<th>Order No</th>
									<th>Vendor Feedback</th>
									<th>Ref. No</th>
									<th>Vendor</th>
									<th>Order Date</th>
									<th>Title</th>
									<th>Order Type</th>
									<th>Order Status</th>
									<th>Invoice Status</th>
									<th>Copies/Sets</th>
									<th>Received</th>
								</tr>
							</thead>
							<tbody></tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
		<!-- Hidden Search parameters -->
		<input type="hidden" id="input-searchParams">
		<!-- Footer -->
		
	</div>
	  </section>
	<div> &nbsp; </div>	

	<!-- MODAL WHEN CLICK Search button-vendorSearch -->
    <div class="modal fade" id="modal_vendorSearch" tabindex="-1" role="dialog" aria-labelledby="modal_vendorSearch" aria-hidden="true" data-keyboard="false" data-backdrop="static"> 
		<div class="modal-dialog" style="width:50%;">
			    <div class="modal-content" id="modal_vendorSearchContent">
				  <!-- Remote content load here  -->
			  	</div>
		</div>
	</div>
	
	

	
</body>
</html>
