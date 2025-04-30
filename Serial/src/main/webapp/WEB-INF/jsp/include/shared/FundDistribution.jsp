<%
if((FundDistribution.fundIntegration()).equals("Y")){
%>

<%@page import="com.ilmu.fundacc.FundAcc.*"%>
<%@page import="com.ilmu.global.*"%>
<%@page import="java.util.List"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/Serial/RecordForPayment.js"></script>	
<script type="text/javascript" src="${pageContext.request.contextPath}/js/general/FundDistribution.js"></script>	


<style>

.tablePanel{
height:200px;
overflow:auto;
}

#orderList thead th{
	background-color:rgba(52, 73, 94, 0.94);
	color:white;
}

</style>


<%
String module = request.getParameter("module");
String vendor = request.getParameter("vendor");
String desc = request.getParameter("desc");
String ref = request.getParameter("ref");
String totalOrder = request.getParameter("totalOrder");

%>
<div class="modal-body">	
	<input type="hidden" id= "vendor" value="<%=vendor%>">
	<input type="hidden" id= "desc" value="<%=desc%>">
	<input type="hidden" id= "ref" value="<%=ref%>">
	<input type="hidden" id= "module" value="<%=module%>">
	<div class="panel panel-default">
		<div class="panel-body">
		<div class="panel with-nav-tabs panel-default">
	                <div class="panel-heading">
	              <!--   <button type="button" class="btn btn-primary" id="btn_addrow" onclick="addRow()" disabled><span class="glyphicon glyphicon-plus" title="Add"></span></button>
	                         --><ul class="nav nav-tabs">
	                            <li class="active"><a href="#tab1default" data-toggle="tab">Order</a></li>
	                            <%
	                        		if(module.equals("AAAM0450") || module.equals("AAAM1150")){
	                       		 %>
	                            <li class="disabled"><a href="#tab2default">Discount and charges</a></li>
	                            <%
	                        		}else{
	                            %>
	                            <li><a href="#tab2default" data-toggle="tab">Discount and charges</a></li>
	                            <%
	                        		}
	                            %>
	                        </ul>
	                </div>
	                <div class="panel-body">
	                    <div class="tab-content">
	                        <div class="tab-pane fade in active" id="tab1default">
	                        	<div class="tablePanel" >
	                        	<strong>Order : <span id="totalOrder"><%=totalOrder %></span></strong>
	                           <button type="button" class="btn btn-primary pull-right" id="btn_addrow" onclick="addRow()" disabled><span class="glyphicon glyphicon-plus" title="Add"></span></button>
	                        	<table id="orderList" class="table table-bordered table-striped table-fixed">
									<thead>
									 	<tr>
											<th width="30%">Fund</th>
											<th width="30%">Description</th>
											<th width="15%">Committed</th>
											<th width="15%">Actual</th>
											<th width="10%"></th>
									 	</tr>
									</thead>
									<tbody>
										<tr class="row_1">
											<td width="30%">
												<select class="form-control funds" onchange="getfund(this)">
												<%
													List<FundDistribution> fund = FundDistribution.getListOfAccounting();
												%>
													<option>Please select</option>
												<%
													for(FundDistribution i : fund){
												%>
													<option value="<%=i.getFA01DESC() %>"><%=i.getFA01FUND() %></option>
												<% 
													}
												%>
												</select>
											</td>
											<td width="30%"></td>
											<td width="15%"></td>
											<td width="15%"><input type="number" class="form-control amount" onblur="checkbalance(this)" disabled></td>
											<td width="10%">
												<a class='btn btn-dull btn-sm' onclick="removeOrder(this)">
													<span class="glyphicon glyphicon-trash"></span>
												</a>
											</td>
										</tr>
									</tbody>
								</table>
								</div>
								<div style="float:right"><strong>Total : <span id="orderAmt"></span></strong></div>
	                        </div>
	   
	                        <div class="tab-pane fade" id="tab2default">
	                        	 <div class="panel-body">
	                        	 <!--  <p>Discounts : 0.000</p> -->
	                        	 <strong>Discounts : <span id="totalDisount"></span></strong>
	                          	 <button type="button" class="btn btn-primary pull-right" id="btn_addrow" onclick="addRow()" disabled><span class="glyphicon glyphicon-plus" title="Add"></span></button>
		                        	 <table id="discountList" class="table table-bordered table-striped">
										<thead>
										 	<tr>
												<th>Fund</th>
												<th>Description</th>
												<th>Committed</th>
												<th>Actual</th>
										 	</tr>
										</thead>
										<tbody>
											<tr class="row_1">
												<td>
													<select class="form-control funds" onchange="getfund(this)">
														<option>Please select</option>
													<%
														for(FundDistribution i : fund){
													%>
														<option value="<%=i.getFA01DESC() %>"><%=i.getFA01FUND() %></option>
													<% 
														}
													%>
													</select>
												</td>
												<td></td>
												<td></td>
												<td><input type="text" class="form-control amount" onblur="checkbalance(this)"></td>
												<td>
													<a class='btn btn-dull btn-sm removeRow'>
														<span class="glyphicon glyphicon-trash"></span>
													</a>
												</td>
											</tr>
										</tbody>
									</table>
	                        	 </div>
	                        	 <div class="panel-body">
	                        	 	<!-- <p>Service Charges : 0.000</p> -->
	                        	 	 <strong>Service Charges : <span id="totalService"></span></strong>
	                          	 	<button type="button" class="btn btn-primary pull-right" id="btn_addrow" onclick="addRow()" disabled><span class="glyphicon glyphicon-plus" title="Add"></span></button>
		                        	 <table id="serviceList" class="table table-bordered table-striped">
										<thead>
										 	<tr>
												<th>Fund</th>
												<th>Description</th>
												<th>Committed</th>
												<th>Actual</th>
										 	</tr>
										</thead>
										<tbody>
											<tr class="row_1">
												<td>
													<select class="form-control funds" onchange="getfund(this)">
														<option>Please select</option>
													<%
														for(FundDistribution i : fund){
													%>
														<option value="<%=i.getFA01DESC() %>"><%=i.getFA01FUND() %></option>
													<% 
														}
													%>
													</select>
												</td>
												<td></td>
												<td></td>
												<td><input type="text" class="form-control amount" onblur="checkbalance(this)"></td>
												<td>
													<a class='btn btn-dull btn-sm removeRow' >
														<span class="glyphicon glyphicon-trash"></span>
													</a>
												</td>
											</tr>
										</tbody>
									</table>
	                        	 </div>
	                        	 <div class="panel-body">
	                        		<!-- <p>Handling Charges : 0.000</p> -->
	                        		 <strong>Handling Charges : <span id="totalHandling"></span></strong>
	                          	 	<button type="button" class="btn btn-primary pull-right" id="btn_addrow" onclick="addRow()" disabled><span class="glyphicon glyphicon-plus" title="Add"></span></button>
		                        	 
		                        	 <table id="handlingList" class="table table-bordered table-striped">
										<thead>
										 	<tr>
												<th>Fund</th>
												<th>Description</th>
												<th>Committed</th>
												<th>Actual</th>
										 	</tr>
										</thead>
										<tbody>
											<tr class="row_1">
												<td>
													<select class="form-control funds" onchange="getfund(this)">
														<option>Please select</option>
													<%
														for(FundDistribution i : fund){
													%>
														<option value="<%=i.getFA01DESC() %>"><%=i.getFA01FUND() %></option>
													<% 
														}
													%>
													</select>
												</td>
												<td></td>
												<td></td>
												<td><input type="text" class="form-control amount" onblur="checkbalance(this)"></td>
												<td>
													<a class='btn btn-dull btn-sm removeRow'>
														<span class="glyphicon glyphicon-trash"></span>
													</a>
												</td>
											</tr>
										</tbody>
									</table>
	                        	 </div>
	                        </div>
	                    </div>
	                </div>
	            </div>
		</div>
	</div>
</div>
<div class="modal-footer">
<button type="button" id="saveFund" class="btn btn-primary" onClick="proceed()" disabled>OK</button>
<button type="button" id="cancel" class="btn btn-secondary" data-dismiss="modal">Cancel</button>
</div>
<%
}else{
out.println("no_funddistribution");
}
%>
