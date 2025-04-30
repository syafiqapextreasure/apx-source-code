
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="ISO-8859-1" import="java.util.*"%>
<%@ page import="java.sql.*" %>
<%@page import="com.ilmu.acquisition.orderManagement.errorSupply"%>
<%@page import="java.util.List"%>
<%-- <%@page import="com.ilmu.foundation.global.*"%> --%>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/acquisition/errorSupply.js"></script>	
<style>
#modal_vendorSearch {
	z-index: 1080 !important;
}
</style>

	<%
		String orderNo = request.getParameter("orderNo");
		System.out.println("ORDER VENDOR FEEDBACK " +orderNo);
	
		String removeDoubleordernumber=orderNo.replace("\"", "");
		System.out.println(removeDoubleordernumber + " removeDoubleordernumber");
	%>

			<div class="modal-header">
				<h5 class="modal-title" id="modalName" align="center">Error In Supply</h5>
					<button type="button" id="closeModalEIS" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
			</div>
			<div class="modal-body">
						<form role="form" class="form-horizontal" id="formdataEIS" method="post">
							<div class="form-group">
								<div class="col-sm-1"></div>
								<div class="col-sm-3">
									<label for="orderNoEIS">Order Number</label>
								</div>
								<div class="col-sm-4">
									<span class="orderNoEIS"><%=removeDoubleordernumber%></span>
									<%-- <input type="text" class="form-control" id="orderNoEIS" name="orderNoEIS" value="<%=removeDoubleordernumber%>"> --%>
								</div>
							</div>
							
							<div class="form-group">
								<div class="col-sm-1"></div>
								<div class="col-sm-3"><label>Invoice Number:</label></div>
								<div class="col-sm-4">
									<span class="invoiceNo"></span>
								</div>
							</div>
							
							<div class="form-group">
								<div class="col-sm-1"></div>
								<div class="col-sm-3"><label>Reason</label></div>
								 <div class='col-sm-6'>
								 	<div id="div-errorInSupply" style="overflow-x:hidden;overflow-y:scroll;height:200px">
										<span class="valError"></span>
									</div>
			        			</div>
							</div>
							
							<div class="form-group">
								<div class="col-sm-1"></div>
								<div class="col-sm-3"><label>Date</label></div>
								 <div class='col-sm-3'>
								 	<!-- <input type="text" class="form-control" id="VFDate" name="VFDate"> -->
			                      	<div class="input-group date" id="errorInSupplyDate">
			  						 	<input type="text" class="form-control" id="EISDate" name="EISDate">
			  						 	<span class="input-group-addon">
			  							<i class="glyphicon glyphicon-calendar"></i></span>
									</div>
			        			</div>
							</div>
							
							<div class="panel panel-default" id="panel-result">
						<div class="panel-heading">
							<h4 class="panel-title">
								<a data-toggle="collapse" data-parent="#form_parent"
									href="#viewErrorInSupply">View All Error In Supply</a>
							</h4>
						</div>
						<div id="viewErrorInSupply" class="panel-collapse collapse">
							<div class="panel-body" id="search-viewErrorInSupply">
						
							<table id="table-errorInSupply" class="table table-hover"
									data-toggle="table" data-search="true" data-pagination="true"
									data-show-columns="true" data-click-to-select="true">
									<thead>
										<tr>
											<th data-sortable="true">Order No</th>
											<th data-sortable="true">Invoice No</th>
											<th data-sortable="true">Date</th>
											<th data-sortable="true">Reason</th>
										</tr>
									</thead>
									<tbody></tbody>
								</table>
							</div>
						</div>
					</div>
						 <div class="modal-footer">
							<button type="submit" class="btn btn-primary" id="button-eisSave">Save</button>
							<button type="button" class="btn btn-primary" id="button-eisView"><span class="fa fa-eye" style="color:white" title="View"></span></button>
							<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
						</div>
						</form>
					</div>