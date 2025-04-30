<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="com.ilmu.cataloging.Acc_Maint.*, com.ilmu.foundation.global.*, java.util.List" %>
<% 
	String accNo = request.getParameter("accNo");
	System.out.println("accNo" + request.getParameter("accNo"));

	CTRetrieve updateAccMaint = CTRetrieve.CT_getDetailsBy(accNo); 
	
	System.out.println("ControlNoView" + request.getParameter("controlNo"));
%>
<% List<CTRetrieve> searchResult = CTRetrieve.DisplayAll(request.getParameter("controlNo")); %>

<script type="text/javascript">	

	 function clear()
	  	    {
	  	        $('#viewAccMaint').find('input:text, input:password, select, textarea').val('');
	  	        $('#viewAccMaint').find('input:radio, input:checkbox').prop('checked', false);
	  	}
</script> 

<style>
.modal-body {
    max-height: 100%;
    overflow-y: auto;
}

</style>
<!-- Modal content-->	  
<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
		<h4 class="modal-title" id="myModalLabel">View Accession Maintenance</h4>
	</div>
	<div class="modal-body" style="height:500px;overflow:auto">
		<div class="panel-body">
			<form role="form" class="form-horizontal" id="viewAccMaint">
					<div class="row">
	<div class="col-md-12">
	<!-- Custom Tabs -->
		<div class="nav-tabs-custom">
			<ul class="nav nav-tabs">
				<li class="active"><a href="#tabs_1" data-toggle="tab" aria-expanded="false">Details</a></li>
				<li><a href="#tabs_2" data-toggle="tab">Acquisition Details</a></li>
				<li class=""><a href="#tabs_3" data-toggle="tab" aria-expanded="true">Document Details</a></li>
             	<li><a href="#tabs_4" data-toggle="tab">Related Copies</a></li>
				 <li class="pull-right"><a href="#" class="text-muted"><i class="fa fa-gear"></i></a></li>
			</ul>
		<div class="tab-content">
			<div class="tab-pane active " id="tabs_1">
				<div class="box-body">
					<div class="row">
			            <div class="col-md-12">		
			             <div class="form-group">
			             <div class="col-sm-3 col-md-3">
                      <label>Accession No.:</label>
                      </div>
                      <div class="col-sm-3">
                       <input type="text" class="form-control" id="button-generateAccession" value="<%= accNo %>" disabled>
                      </div>
					   <div class="col-sm-2">
						<input type="button" class="btn btn-info" id="button-generateAccession" value="Generate" disabled>
					   </div>
                     </div>
					</div>
					</div>
						<div class="row">
			            <div class="col-md-12">		
					 <div class="form-group">
					   <div class="col-sm-3 col-md-3">
					   <label>
                      <input type="checkbox" class="fastRelease" id="fastRelease" value="" disabled>Control Number:
					  </label>
					  </div>
                      <div class="col-sm-3">
                      <input type="text" class="form-control" name="CtrlNo" id="CtrlNo" value="<%= updateAccMaint.getCT03MATNO() %>" disabled>
                      </div>
					   <div class="col-sm-1">
						<button type="button" class="btn btn-default selectPopup" data-toggle="modal" data-target="#vendorModal" href="jsp/include/sharedV1/SearchTitleModal.jsp" disabled>...</button>
					   </div>
					     <div class="col-sm-3">
						 		<div id="title"><%= updateAccMaint.getCT05SRAW() %></div>
						  </div>
					<!--   <div class="col-sm-4"><div id="div-vendorName"><%=updateAccMaint.getGL39NAME()%></div></div> -->
                    </div>
                    </div>
                    </div>
                  	 
					  <div class="row">
			            <div class="col-md-12">		
							<div class="form-group">
								<div class="col-sm-3 col-md-3">
									<label><input type="checkbox" class="fastRelease" id="fastRelease" value="" disabled>Location:</label>
								</div>
								<div class="col-sm-5 col-md-5">
									<select class="form-control" id="tpl" name="tpl" onchange="getTpl()" disabled>
										<option value="0"><%= updateAccMaint.getCT03LOCA() %>-<%=updateAccMaint.getGL05DESC() %></option>
															<%
																SQLStatement loca = new SQLStatement();
																List<Foundation> location = loca.getLocation();
																for (Foundation e : location) {
															%>
										<option value="<%=e.getGL05LOCA()%>"><%=e.getGL05LOCA()%>-<%=e.getGL05DESC()%></option>
															<%
																}
															%>
								</select>
							</div>
							<%-- <div class="col-sm-5 col-md-5">
								<select class="form-control" id="tpl" name="tpl" onchange="getTpl()" disabled>
									<option value="0"><%= updateAccMaint.getGL05DESC() %></option>
															<%
																SQLStatement loca1 = new SQLStatement();
																List<Foundation> desc = loca1.getLocation();
																for (Foundation e : location) {
															%>
															<option value="<%=e.getGL05DESC()%>"><%=e.getGL05DESC()%></option>
															<%
																}
															%>
								</select>
							</div> --%>
						</div>
					</div>
				</div>
				<div class="row">
			            <div class="col-md-12">		
							<div class="form-group">
								<div class="col-sm-3 col-md-3">
									<label><input type="checkbox" class="fastRelease" id="fastRelease" value="" disabled>Item Category:</label>
								</div>
								<div class="col-sm-5 col-md-5">
									<select class="form-control" id="tpl" name="tpl" onchange="getTpl()" disabled>
										<option value="0"><%= updateAccMaint.getCT03ICAT() %>-<%= updateAccMaint.getGL10DESC() %></option>
															<%
																SQLStatement itemcat = new SQLStatement();
																List<Foundation> items = itemcat.getItemCat();
																for (Foundation e : items) {
															%>
										<option value="<%=e.getGL10ICAT()%>"><%=e.getGL10ICAT()%>-<%=e.getGL10DESC()%></option>
															<%
																}
															%>
									</select>
								</div>
								<%-- <div class="col-sm-5 col-md-5">
									<select class="form-control" id="CT03ICAT1" name="CT03ICAT1" onchange="equalCategory1()" disabled>
										<option value="0"><%= updateAccMaint.getGL10DESC() %></option>
											<%
												SQLStatement itemcat1 = new SQLStatement();
												List<Foundation> desc1 = itemcat1.getItemCat();
												for (Foundation e : desc1) {
											%>
											<option value="<%=e.getGL10ICAT()%>"><%=e.getGL10DESC()%></option>
											<%
												}
											%>
								</select>
								</div> --%>
							</div>
						</div>
					</div>
					<div class="row">
			            <div class="col-md-12">		
							<div class="form-group">
								<div class="col-sm-3 col-md-3">
									<label><input type="checkbox" class="fastRelease" id="fastRelease" value="" disabled>Condition:</label>
								</div>
								<div class="col-sm-5 col-md-5">
									<select class="form-control" id="tpl" name="tpl" onchange="getTpl()" disabled>
															<option value="0"><%= updateAccMaint.getCT03COND() %>-<%= updateAccMaint.getGL06DESC() %></option>
															 <%
																SQLStatement itemcat2 = new SQLStatement();
																List<Foundation> items2 = itemcat2.getLocation();
																for (Foundation e : location) {
															%>
															<option value="<%=e.getGL06COND()%>"><%=e.getGL06COND()%>-<%=e.getGL06DESC()%></option>
															<%
																}
															%> 
									</select>
								</div>
								<%-- <div class="col-sm-5 col-md-5">
									<select class="form-control" id="CT03COND1" name="CT03COND1" onchange="equalCondition1()" disabled>
										<option value="0"><%= updateAccMaint.getGL06DESC() %></option>
											<%
												SQLStatement cond1 = new SQLStatement();
												List<Foundation> desc2 = cond1.getCondition();
												for (Foundation e : desc2) {
											%>
											<option value="<%=e.getGL06COND()%>"><%=e.getGL06DESC()%></option>
											<%
												}
											%>
								</select>
								</div> --%>
							</div>
						</div>
					</div>
					 <div class="row">
			            <div class="col-md-12">		
							<div class="form-group">
								<div class="col-sm-3 col-md-3">
									<label><input type="checkbox" class="fastRelease" id="fastRelease" value="" disabled>SMD:</label>
								</div>
								<div class="col-sm-5 col-md-5">
									<select class="form-control" id="tpl" name="tpl" onchange="getTpl()" disabled>
															<option value="0"><%= updateAccMaint.getCT03SMD() %>-<%= updateAccMaint.getGL47DESC() %></option>
															<%
																SQLStatement smd = new SQLStatement();
																List<Foundation> smds = smd.getSMD();
																for (Foundation e : smds) {
															%>
															<option value="<%=e.getGL47SMD()%>"><%=e.getGL47SMD()%>-<%=e.getGL47DESC()%></option>
															<%
																}
															%>
														</select>
								</div>
								<%-- <div class="col-sm-5 col-md-5">
									<select class="form-control" id="CT03SMD1" name="CT03SMD1" onchange="equalSMD1()" disabled>
										<option value="0"><%= updateAccMaint.getGL47DESC() %></option>
											<%
												SQLStatement smd1 = new SQLStatement();
												List<Foundation> desc3 = smd1.getSMD();
												for (Foundation e : desc3) {
											%>
											<option value="<%=e.getGL47SMD()%>"><%=e.getGL47DESC()%></option>
											<%
												}
											%>
								</select>
								</div> --%>
							</div>
						</div>
					</div>
					 <div class="row">
			            <div class="col-md-12">		
							<div class="form-group">
								<div class="col-sm-3 col-md-3">
									<label><input type="checkbox" class="fastRelease" id="fastRelease" value="" disabled>Currency Code:</label>
								</div>
								<div class="col-sm-5 col-md-5">
									<select class="form-control" id="" onchange="updateCurrency()" name="currency" disabled>
									
															<option value="0"><%= updateAccMaint.getCT03FOREX() %>-<%= updateAccMaint.getGL24DESC() %></option>
															<%
																SQLStatement currCode = new SQLStatement();
															List<Foundation> currCodes = currCode.getCurrencyCode();
																for (Foundation e : currCodes) {
															%>
															<option value="<%=e.getGL24FOREX()%>" data-id="<%=e.getGL24BRATE()%>"><%=e.getGL24FOREX()%>-<%=e.getGL24DESC()%></option>
															<%
																}
															%>
														</select>
								</div>
								<%-- <div class="col-sm-5 col-md-5">
									<select class="form-control" id="" name="currency1" onchange="equalForex1()" disabled>
										<option value="0"><%= updateAccMaint.getGL24DESC() %></option>
											<%
												SQLStatement currCode1 = new SQLStatement();
												List<Foundation> desc4 = currCode1.getCurrencyCode();
												for (Foundation e : desc4) {
											%>
											<option value="<%=e.getGL24FOREX()%>" data-id="<%=e.getGL24BRATE()%>"><%=e.getGL24DESC()%></option>
											<%
												}
											%>
								</select>
								</div> --%>
								
							</div>
						</div>
					</div>
					 <div class="row">
			            <div class="col-md-12">		
							<div class="form-group">
								<div class="col-sm-3 col-md-3">
									<label><input type="checkbox" class="fastRelease" id="fastRelease" value="" disabled>Volume:</label>
								</div>
								<div class="col-sm-5 col-md-5">
									<input type="text" class="form-control" name="CtrlNo" id="CtrlNo" style="width:100%" value="<%= updateAccMaint.getCT03VOLUME() %>" disabled>
								</div>
							</div>
						</div>
					</div>
					<div class="row">
			            <div class="col-md-12">		
							<div class="form-group">
								<div class="col-sm-3 col-md-3">
									<label><input type="checkbox" class="fastRelease" id="fastRelease" value="" disabled>Publisher's Rate:</label>
								</div>
								<div class="col-sm-5 col-md-5">
									<input type="text" class="form-control" name="CtrlNo" id="CtrlNo" value="<%= updateAccMaint.getCT03RATE() %>" disabled>
								</div>
							</div>
						</div>
					</div>
					<div class="row">
						 	<div class="col-md-12">		
							<div class="form-group">	
								<div class="col-sm-3 col-md-3">
									<label><input type="checkbox" name="addAccessionMaint" value="CT03COPYNO" class="fastRelease" id="fastRelease" value="" disabled>&nbsp;Copies:</label>
								</div>
								<div class="col-sm-3 col-md-3">
									<input type="text" class="form-control" name="CT03COPYNO" id="" value="<%= updateAccMaint.getCT03COPYNO() %>" disabled> 
								</div>
							</div>
							</div>
						</div>
					<div class="row">
			            <div class="col-md-9">		
							<div class="form-group">
								<div class="col-sm-3 col-md-3">
									<label>Copies : </label>
								</div>
							<%
							if (updateAccMaint.getCT03ORG()==null){
							%>
								<div class="col-sm-3 col-md-3">
									<input name="copyTypes" id="copyTypes" value="Y" type="radio"> 
									Original
								</div>
								<div class="col-sm-3 col-md-3">
									<input name="copyTypes" id="copyTypes" value="N" type="radio"> 
									Duplicate Copy 
								</div>	
							<%
							}else{
							%>
								<div class="col-sm-3 col-md-3">
									<input name="copyTypes" id="copyTypes" value="Y" type="radio" <% if(updateAccMaint.getCT03ORG().equals("Y")){out.println("checked");} %>> 
									Original
								</div>
								<div class="col-sm-3 col-md-3">
									<input name="copyTypes" id="copyTypes" value="N" type="radio" <% if(updateAccMaint.getCT03ORG().equals("N")){out.println("checked");} %>> 
									Duplicate Copy 
								</div>	
								</div>
							<%
							}
							%>
								<div class="col-sm-3 col-md-3">
								</div>
							</div>
						</div>
						<div class="row">
			            <div class="col-md-10">		
							<div class="form-group">
								<div class="col-sm-10 col-md-10">
									<label>
									<%
										if((updateAccMaint.getCT03ONTHEFLY()).equals("Y")){
									%>
										<input type="checkbox" class="onthefly" id="onthefly" value="onthefly" checked disabled>
									<%
										}else {
									%>
										<input type="checkbox" class="onthefly" id="onthefly" value="onthefly" disabled>
									<%
										}
									%>
										On the fly[Alert prompt in Discharging]
									</label>
								</div>
							</div>
						</div>
					</div>
					 <div class="row">
			            <div class="col-md-12">		
							<div class="form-group">
								<div class="col-sm-3 col-md-3">
														<label><input type="checkbox" class="fastRelease" id="fastRelease" value="" disabled>Foreign Cost:</label>
								</div>
								<div class="col-sm-3 col-md-3">
														<label><input type="checkbox" class="fastRelease" id="fastRelease" value="" disabled>Local Cost:</label>
								</div>
								<div class="col-sm-3 col-md-3">
														<label><input type="checkbox" class="fastRelease" id="fastRelease" value="" disabled>Service Cost:</label>
								</div>
								<div class="col-sm-3 col-md-3">
														<label><input type="checkbox" class="fastRelease" id="fastRelease" value="" disabled>Handling Cost:</label>
								</div>
							</div>
						</div>
					</div>
					<div class="row">
			            <div class="col-md-12">		
							<div class="form-group">
								<div class="col-sm-3 col-md-3">
									<input class="form-control cost validate-double" id="foreignCost" type="text" value="<%= updateAccMaint.getCT03FCOST() %>" onkeyup="updateLocalCost()" required="" name="foreignCost" disabled>
								</div>
								<div class="col-sm-3 col-md-3">
									<input class="form-control cost validate-double" id="localCost" type="text" value="<%= updateAccMaint.getCT03LCOST() %>" onkeyup="updateForeignCost()" required="" name="localCost" disabled>
								</div>
								<div class="col-sm-3 col-md-3">
									<input type="text" class="form-control" name="CtrlNo" id="CtrlNo" value="<%= updateAccMaint.getCT03SCHAR() %>" style="width:100%" disabled>
								</div>
								<div class="col-sm-3 col-md-3">
									<input type="text" class="form-control" name="CtrlNo" id="CtrlNo" value="<%= updateAccMaint.getCT03HCHAR() %>" style="width:100%" disabled>
								</div>
							</div>
						</div>
					</div>
			
					</div>
		
					</div>
			 	<div class="tab-pane" id="tabs_2">
					 <div class="form-group">
						<div class="col-sm-2 col-md-2">
				            <label><input type="checkbox" name="addAccessionMaint" value="vendor" class="fastRelease" id="fastRelease" value="" disabled>&nbsp;Vendor:</label>
				        </div>
						<div class="col-sm-3  col-md-3">
				            <input type="text" class="form-control" class="CT03VEND" value="<%=updateAccMaint.getCT03VEND()%>" id=vendor name="CT03VEND" disabled>
				        </div>
				        <div class="col-sm-1">
							<button type="button" class="btn btn-default selectPopup" data-toggle="modal" data-target="#vendorSearch" href="Modal_Vendor" disabled>...</button>
					   	</div>
					   	 <div class="col-sm-3  col-md-3">
				        	 <div id="div-vendorName"><%=updateAccMaint.getGL39NAME()%></div>
				        </div>
					</div>
					<div class="form-group">
						<div class="col-sm-2 col-md-2">
							<label><input type="checkbox" name="addAccessionMaint" value="CT03INVOICE" class="fastRelease" id="fastRelease" value="" >&nbsp;Invoice No.:</label>
						</div>
						<div class="col-sm-3 col-md-3">
							<input type="text" class="form-control" name="CT03INVOICE" id="CT03INVOICE" value="<%= updateAccMaint.getCT03INVOICE() %>"  disabled>
						</div>
					</div>
					<div class="form-group">
						<div class="col-sm-2 col-md-2">
							<label><input type="checkbox" name="addAccessionMaint" value="CT03INVDATE" class="fastRelease" id="fastRelease" value="" >&nbsp;Invoice Date:</label>
						</div>
						<div class="col-sm-3 col-md-3">
			          	<div class="input-group date">
  						 	<input type="text" class="form-control" name="CT03INVDATE" id="viewCT03INVDATE" value="<%= updateAccMaint.getCT03INVDATE() %>" disabled>
  						 	<span class="input-group-addon">
  							<i class="glyphicon glyphicon-calendar"></i></span>
						</div>
						</div>
					</div>
				 <div class="form-group">
					<div class="col-sm-3 col-md-3">
						<label>Order Number : </label>
					</div>
					<div class="col-sm-7 col-md-7">
						<%= updateAccMaint.getCT03ORDER() %>
					</div>
				</div>
				<div class="form-group">
					<div class="col-sm-3 col-md-3">
						<label>Date Ordered : </label>
					</div>
					<div class="col-sm-7 col-md-7">
						<%= updateAccMaint.getAC03OrderDate() %>
					</div>
				</div>
				<div class="form-group">
					<div class="col-sm-3 col-md-3">
						<label>Reference Number : </label>
					</div>
					<div class="col-sm-7 col-md-7">
						<%= updateAccMaint.getAC03REFNO() %>
					</div>
				</div>
				</div>
			  <div class="tab-pane" id="tabs_3">
				  <div class="form-group"></div>
							               <div class="form-group">
					<div class="col-sm-4 col-md-4">
						<label>Item Status :</label>
						<% if ((updateAccMaint.getCT03STAT()).equals("A")){%>
							Available
							<%} %>
					</div>
					<div class="col-sm-4 col-md-4">
						<label>Borrower ID : </label><%= updateAccMaint.getCT03PATR() %>
					</div>
					<div class="col-sm-4 col-md-4">
						<label>Circulated Hits: </label><%= updateAccMaint.getCT03CIRHITS() %>
					</div>
				</div>
				<div class="form-group">
					<div class="col-sm-4 col-md-4">
						<label>Last Patron ID : </label><%= updateAccMaint.getCT03LASTID() %>
					</div>
					<div class="col-sm-4 col-md-4">
						<label>Date Borrowed : </label><%= updateAccMaint.getCT03BDATE() %>
					</div>
					<div class="col-sm-4 col-md-4">
						<label>Claim Hits : </label><%= updateAccMaint.getCT03CLMHITS() %>
				</div>
				</div>
				<div class="form-group">
					<div class="col-sm-4 col-md-4">
						<label>Last Activity Date : </label><%= updateAccMaint.getCT03LASTACT() %>
					</div>
					<div class="col-sm-4 col-md-4">
						<label>Date Due : </label><%= updateAccMaint.getCT03DDATE() %>
					</div>
				</div>
						
			  </div>
				<!-- /.tab-pane -->
				<div class="tab-pane" id="tabs_4">
					<div class="form-group"></div>
					<div class="form-group">
						<div class="col-sm-4 col-md-4">
							<label>Accession No. [Status] : </label>
						</div>
						<div class="col-sm-4 col-md-4">
							 <textarea rows="4" cols="50"  id="accTitl" name="accTitl" disabled><%for(CTRetrieve e: searchResult){if(!(e.getTotalDocNo()).equals(updateAccMaint.getCT03DOCNO())){if(( e.getTotal())>0){%><%= e.getTotalDocNo() %><%= e.getTotalStat() %><%}}}%></textarea>
							<%-- <textarea rows="4" cols="50"  id="accTitl" name="accTitl" ><%for(CTRetrieve e: searchResult){%><%= e.getTotalDocNo() %><%= e.getTotalStat() %><%}%></textarea> --%>
						</div>
					</div>
				</div>
				<!-- /.tab-pane -->
			
				 <!-- /.tab-pane -->
			</div>
		<!-- /.tab-content -->
		 </div>
		<!-- nav-tabs-custom -->
		</div>
		 <!-- /.col -->
	 </div>
			</form>
		</div>
	</div>
	
	<div class="modal-footer">
	<a type="button" class="btn btn-info" data-toggle='modal' data-target='.marcviews' href='Modal_BORcrd?ctrlno=<%=updateAccMaint.getCT03MATNO() %>'>Bib Details</a>
	<input type="button" name="cancel" value="Close" class="btn btn-info" data-dismiss="modal"/>
	</div>			
	
	
	<div class="modal fade marcviews marcview" style="z-index:1050" tabindex="-1" role="dialog" aria-labelledby="isbdview" data-backdrop="static">
	    <div class="modal-dialog" style="width:80%" role="document">
			  <div class="modal-content" style="padding:10px">
			  </div>
		</div>
	</div>
