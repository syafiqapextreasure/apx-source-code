<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="com.ilmu.cataloging.Acc_Maint.*, com.ilmu.global.*, com.ilmu.foundation.global.*, java.util.List" %>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/Cataloging/Accession_Maintenance.js"></script>					
<% CTRetrieve currControlNo = CTRetrieve.CT_getDetailsBy(request.getParameter("accNo")); %>
<% List<CTRetrieve> searchResult = CTRetrieve.DisplayAll(request.getParameter("controlNo")); 
System.out.println("ControlNoEdit" + request.getParameter("controlNo"));
%>

<style>
.modal-body {
    max-height: 100%;
    overflow-y: auto;
}

</style>
<script>
$('#CT03EINVDATE').datepicker({
	format : "dd/mm/yyyy",
	autoclose : true
});	
/* $(document).ready(function() {
$('.CT03INVDATE').datepicker({
	format : "d/m/yyyy",
	autoclose : true
});	
}); */
</script>
 
<%
String selected = null;
%>
<!-- Modal content-->	  
<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
		<h4 class="modal-title" id="myModalLabel">Edit Accession Maintenance</h4>
	</div>
	<div class="modal-body" style="height:500px;overflow:auto">
		<div class="panel-body">
			<form role="form" class="form-horizontal" id="viewAccMaint">
					<div class="row">
	<div class="col-md-12">
	<!-- Custom Tabs -->
		<div class="nav-tabs-custom">
			<ul class="nav nav-tabs">
				<li class="active"><a href="#tab_1" data-toggle="tab" aria-expanded="false">Details</a></li>
				<li><a href="#tab_2" data-toggle="tab">Acquisition Details</a></li>
				<li class=""><a href="#tab_3" data-toggle="tab" aria-expanded="true">Document Details</a></li>
             	<li><a href="#tab_4" data-toggle="tab">Related Copies</a></li>
				 <li class="pull-right"><a href="#" class="text-muted"><i class="fa fa-gear"></i></a></li>
			</ul>
		<div class="tab-content">
			<div class="tab-pane active" id="tab_1">
				<div class="box-body">
					<div class="row">
			            <div class="col-md-12">		
			             <div class="form-group">
			             <div class="col-sm-3 col-md-3">
                      <label>Accession No.:</label>
                      </div>
                      <div class="col-sm-3">
                      <%
                      	System.out.println("Accession" + currControlNo.getCT03DOCNO());
	                      boolean resvExist = CTRetrieve.CICIRCExist(currControlNo.getCT03DOCNO());
	                      if(resvExist||!(currControlNo.getCT03STAT()).equals("A")&&!(currControlNo.getCT03STAT()).equals("F")){
                      %>
                      	  <input type="text" class="form-control CT03DOCNO mandatory" id="editCT03DOCNO" maxlength="10" name="CT03DOCNO" value="<% if(currControlNo != null){out.println(currControlNo.getCT03DOCNO());} %>" disabled>
                      <%
	                      }else{
                      %>
                        <input type="text" class="form-control CT03DOCNO mandatory" id="editCT03DOCNO" maxlength="10" name="CT03DOCNO" value="<% if(currControlNo != null){out.println(currControlNo.getCT03DOCNO());} %>" >
                        <%
	                      }
                        %>
                         <input type="hidden" class="form-control oriCT03DOCNO mandatory" id="oriCT03DOCNO" maxlength="10" name="CT03DOCNO" value="<% if(currControlNo != null){out.println(currControlNo.getCT03DOCNO());} %>" >
                      </div>
					   <div class="col-sm-2">
					    <%
	                      if(resvExist||!(currControlNo.getCT03STAT()).equals("A")&&!(currControlNo.getCT03STAT()).equals("F")){
                      %>
                      	  <input type="button" class="btn btn-info mandatory" id="button-generateAccession" onclick="generateAcc()" value="Generate" disabled>
                      <%
	                      }else{
                      %>
						<input type="button" class="btn btn-info mandatory" id="button-generateAccession" onclick="generateAcc()" value="Generate" >
						<%
		                      }
						%>
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
                      <%
                      if(!(currControlNo.getCT03STAT()).equals("A")&&!(currControlNo.getCT03STAT()).equals("F")){
                      %>
                        <input type="text" class="form-control mandatory CT03MATNO" name="CT03MATNO" maxlength="10" id="editCT03MATNO" value="<% if(currControlNo != null){out.println(currControlNo.getCT03MATNO());} %>" disabled>
                      <%
                      }else{
                      %>
                     <input type="text" class="form-control mandatory CT03MATNO" name="CT03MATNO" maxlength="10" id="editCT03MATNO" onkeydown="matnoKeydown(event,this)" onfocusout="matnoFocusOut(this)" value="<% if(currControlNo != null){out.println(currControlNo.getCT03MATNO());} %>">
                     <%
                      }
                     %>
                      </div>
					     <div class="col-sm-1 col-md-1"><button type="button" class="btn btn-default selectPopup mandatory"  data-toggle="modal" data-target="#titleSearch" href="RetrieveTitleModal?action=8&module=Cataloging">...</button></div>
					     <div class="col-sm-5">
						 		<div class="title" id="edittitle"><% if(currControlNo != null){out.println(currControlNo.getCT05SRAW());} %></div>
						  </div>

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
									<select class="form-control CT03LOCA mandatory" id="editCT03LOCA" name="CT03LOCA" onchange="equalLocation()"
									 <%
                     					 if(!(currControlNo.getCT03STAT()).equals("A")&&!(currControlNo.getCT03STAT()).equals("F")){
                     				 %>
                     					disabled
                     				 <%
                     					 }
                      				%>>
										<option value="<% if(currControlNo != null){out.println(currControlNo.getCT03LOCA());} %>"><% if(currControlNo != null){out.println(currControlNo.getCT03LOCA() + "-" + currControlNo.getGL05DESC());} %></option>
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
								<select class="form-control CT03LOCA1 mandatory" id="editGL03LOCA1" name="GL03LOCA1" onchange="equalLocation1()" 
								  <% if(!(currControlNo.getCT03STAT()).equals("A")&&!(currControlNo.getCT03STAT()).equals("F")){
				                      %>
				                     	disabled
				                      <%
				                      }
				                      %>>
									<option value="0"><% if(currControlNo != null){out.println(currControlNo.getGL05DESC());} %></option>
															<%
																SQLStatement loca1 = new SQLStatement();
																List<Foundation> desc = loca1.getLocation();
																for (Foundation e : location) {
															%>
															<option value="<%=e.getGL05LOCA()%>"><%=e.getGL05DESC()%></option>
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
								<div class="col-sm-5 col-md-35">
									<select class="form-control CT03ICAT mandatory" id="editCT03ICAT" name="CT03ICAT" onchange="equalCategory()" 
									  <%if(!(currControlNo.getCT03STAT()).equals("A")&&!(currControlNo.getCT03STAT()).equals("F")){
					                      %>
					                     	disabled
					                      <%
					                      }
					                      %>>
											<%-- 	<option value="<% if(currControlNo != null){out.println(currControlNo.getCT03ICAT());} %>"><% if(currControlNo != null){out.println(currControlNo.getCT03ICAT());} %></option> --%>
														<%
												SQLStatement itemcat = new SQLStatement();
												List<Foundation> items = itemcat.getItemCat();
													for (Foundation e : items) {
														if((currControlNo.getCT03ICAT()).equals(e.getGL10ICAT())){
																selected = "selected";
														}else{
															selected = "";
														}
											%>
										<option value="<%=e.getGL10ICAT()%>" <%=selected %>><%=e.getGL10ICAT()%>-<%=e.getGL10DESC()%></option>
											<%
												}
											%>
									</select>
								</div>
								<%-- <div class="col-sm-5 col-md-5">
									<select class="form-control CT03ICAT1 mandatory" id="editCT03ICAT1" name="CT03ICAT1" onchange="equalCategory1()"
									  <% if(!(currControlNo.getCT03STAT()).equals("A")&&!(currControlNo.getCT03STAT()).equals("F")){
				                      %>
				                     	disabled
				                      <%
				                      }
				                      %>>
										<%
												SQLStatement itemcat1 = new SQLStatement();
												List<Foundation> desc1 = itemcat1.getItemCat();
												for (Foundation e : desc1) {
												if((currControlNo.getCT03ICAT()).equals(e.getGL10ICAT())){
														selected = "selected";
												}else{
													selected = "";
												}
											%>
											<option value="<%=e.getGL10ICAT()%>" <%=selected %>><%=e.getGL10DESC()%></option>
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
									<select class="form-control CT03COND mandatory" id="editCT03COND" name="CT03COND" onchange="equalCondition()"
									   <%if(!(currControlNo.getCT03STAT()).equals("A")&&!(currControlNo.getCT03STAT()).equals("F")){
					                      %>
					                     	disabled
					                      <%
					                      }
					                      %>>
															<option value="<% if(currControlNo != null){out.println(currControlNo.getCT03COND());} %>"><% if(currControlNo != null){out.println(currControlNo.getCT03COND() + "-" + currControlNo.getGL06DESC());} %></option>
															 <%
																SQLStatement cond = new SQLStatement();
																List<Foundation> conds = cond.getCondition();
																for (Foundation e : conds) {
															%>
															<option value="<%=e.getGL06COND()%>"><%=e.getGL06COND()%>-<%=e.getGL06DESC()%></option>
															<%
																}
															%> 
									</select>
								</div>
								<%-- <div class="col-sm-5 col-md-5">
									<select class="form-control CT03COND1 mandatory" id="editCT03COND1" name="CT03COND1" onchange="equalCondition1()"
									   <%if(!(currControlNo.getCT03STAT()).equals("A")&&!(currControlNo.getCT03STAT()).equals("F")){
					                      %>
					                     	disabled
					                      <%
					                      }
					                      %>>
									<option value="<% if(currControlNo != null){out.println(currControlNo.getCT03COND());} %>"><% if(currControlNo != null){out.println(currControlNo.getGL06DESC());} %></option>
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
									<select class="form-control CT03SMD mandatory" id="editCT03SMD" name="CT03SMD" onchange="equalSMD()"
									  <% if(!(currControlNo.getCT03STAT()).equals("A")&&!(currControlNo.getCT03STAT()).equals("F")){
				                      %>
				                     	disabled
				                      <%
				                      }
				                      %>>
															<option value="<% if(currControlNo != null){out.println(currControlNo.getCT03SMD());} %>"><% if(currControlNo != null){out.println(currControlNo.getCT03SMD() + "-" + currControlNo.getGL47DESC());} %></option>
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
									<select class="form-control CT03SMD1 mandatory" id="editCT03SMD1" name="CT03SMD1" onchange="equalSMD1()"
									  	<% if(!(currControlNo.getCT03STAT()).equals("A")&&!(currControlNo.getCT03STAT()).equals("F")){
					                      %>
					                     	disabled
					                      <%
					                      }
					                      %>>
										<option value="<% if(currControlNo != null){out.println(currControlNo.getCT03SMD());} %>"><% if(currControlNo != null){out.println(currControlNo.getGL47DESC());} %></option>
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
									<select class="form-control currency" id="editcurrency" onchange="equalForex(this)" name="currency" >
									
											<%System.out.println("Values" + currControlNo.getCT03FOREX());
											if(currControlNo.getCT03FOREX() == null || currControlNo.getCT03FOREX().equals("0")){
											%>
												<option value="0">Please Select</option>
											<%
												}else{
											%>
												<option value="<% if(currControlNo != null){out.println(currControlNo.getCT03FOREX());} %>"><% if(currControlNo != null){out.println(currControlNo.getCT03FOREX() + "-" + currControlNo.getGL24DESC());} %></option>
											<%
												}
											%>
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
									<select class="form-control currency1" id="editcurrency1" name="currency1" onchange="equalForex1(this)">
									<%System.out.println("Values" + currControlNo.getCT03FOREX());
												if(currControlNo.getCT03FOREX().equals("0")){
											%>
												<option value="0">Please Select</option>
											<%
												}else{
											%>
												<option value="<% if(currControlNo != null){out.println(currControlNo.getCT03FOREX());} %>"><% if(currControlNo != null){out.println(currControlNo.getGL24DESC());} %></option>
											<%
												}
											%>
							
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
									<input type="text" class="form-control" name="CT03VOL" id="editCT03VOL" style="width:100%" value="<% if(currControlNo != null){out.println(Handler.ifIsNull(currControlNo.getCT03VOLUME()));} %>" >
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
									<input type="text" class="form-control CT03RATE" name="CT03RATE" id="editCT03RATE" style="width:100%" value="<% if(currControlNo != null){out.println(currControlNo.getCT03RATE());} %>" >
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
									<input type="text" class="form-control" name="CT03COPYNO" id="editCT03COPYNO" value="<% if(currControlNo != null){out.println(currControlNo.getCT03COPYNO());} %>" > 
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
								<div class="col-sm-3 col-md-3">
									<label>
										<input name="copyType1" value="Y" id="editcopyType" type="radio" 
										<% if(currControlNo.getCT03ORG().equals("Y") || currControlNo.getCT03ORG().trim().equals("")){out.println("checked");} %>> 
										Original
									</label>
								</div>
								<div class="col-sm-3 col-md-3">
									<label>
										<input name="copyType1" value="N" id="editcopyType" type="radio"
										<% if(currControlNo.getCT03ORG().equals("N")){out.println("checked");} %>> 
										Duplicate Copy 
									</label> 
								</div>	
								</div>
								<div class="col-sm-3 col-md-3">
								</div>
							</div>
						</div>
						<div class="row">
			            <div class="col-md-10">		
							<div class="form-group">
								<div class="col-sm-10 col-md-10">
									<label>
									
										<input type="checkbox" class="onthefly" id="editonthefly" value="onthefly" 	
										<% if(currControlNo.getCT03ONTHEFLY().equals("Y")){out.println("checked");} %> >
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
									<input class="form-control cost validate-double foreignCost" id="editforeignCost" type="text" value="<% if(currControlNo != null){out.println(currControlNo.getCT03FCOST());} %>" onkeyup="updateForeignCost(this)" required="" name="foreignCost" >
								</div>
								<div class="col-sm-3 col-md-3">
									<input class="form-control cost validate-double localCost" id="editlocalCost" type="text" value="<% if(currControlNo != null){out.println(currControlNo.getCT03LCOST());} %>" onkeyup="updateLocalCost(this)" required="" name="localCost" >
								</div>
								<div class="col-sm-3 col-md-3">
									<input type="text" class="form-control sCost" name="sCost" id="editsCost" value="<% if(currControlNo != null){out.println(currControlNo.getCT03SCHAR());} %>" style="width:100%" >
								</div>
								<div class="col-sm-3 col-md-3">
									<input type="text" class="form-control hCost" name="hCost" id="edithCost" value="<% if(currControlNo != null){out.println(currControlNo.getCT03HCHAR());} %>" style="width:100%" >
								</div>
							</div>
						</div>
					</div>
			
					</div>
		
					</div>
			 	<div class="tab-pane" id="tab_2">
					 <div class="form-group">
						<div class="col-sm-2 col-md-2">
				            <label><input type="checkbox" name="addAccessionMaint" value="vendor" class="fastRelease" id="fastRelease" value="" disabled>&nbsp;Vendor:</label>
				        </div>
						<div class="col-sm-3  col-md-3">
				            <input type="text" class="form-control CT03VEND" id="editCT03VEND" name="CT03VEND" onkeydown="vendorKeydown(event, this)" onfocusout="vendorFocusout(this)" value="<% if(currControlNo != null){out.println(currControlNo.getCT03VEND());} %>" >
				        </div>
				          <div class="col-sm-1">
							<button type="button" class="btn btn-default selectPopup" data-toggle="modal" data-target="#vendorSearch" href="Modal_Vendor" >...</button>
					   	</div>
					   	 <div class="col-sm-3  col-md-3">
				        	 <div class="div-vendorName"><% if(currControlNo != null){out.println(currControlNo.getGL39NAME());} %></div>
				        </div>
					</div>
					<div class="form-group">
						<div class="col-sm-2 col-md-2">
							<label><input type="checkbox" name="addAccessionMaint" value="CT03INVOICE" class="fastRelease" id="fastRelease" value="" >&nbsp;Invoice No.:</label>
						</div>
						<div class="col-sm-3 col-md-3">
							<input type="text" class="form-control" name="CT03INVOICE" id="editCT03INVOICE" value="<% if(currControlNo != null){out.println(currControlNo.getCT03INVOICE());} %>">
						</div>
					</div>
					<div class="form-group">
						<div class="col-sm-2 col-md-2">
							<label><input type="checkbox" name="addAccessionMaint" value="CT03INVDATE" class="fastRelease" id="fastRelease" value="" >&nbsp;Invoice Date:</label>
						</div>
						<div class="col-sm-3 col-md-3">
			          	<div class="input-group date">
  						 <input type="text" class="form-control CT03INVDATE" name="CT03INVDATE" id="CT03EINVDATE" value="<% out.println(currControlNo.getCT03INVDATE());%>" >
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
						<% if(currControlNo != null){out.println(currControlNo.getCT03ORDER());} %>
					</div>
				</div>
				<div class="form-group">
					<div class="col-sm-3 col-md-3">
						<label>Date Ordered : </label>
					</div>
					<div class="col-sm-7 col-md-7">
						<% if(currControlNo != null){out.println(currControlNo.getAC03OrderDate());} %>
					</div>
				</div>
				<div class="form-group">
					<div class="col-sm-3 col-md-3">
						<label>Reference Number : </label>
					</div>
					<div class="col-sm-7 col-md-7">
						<% if(currControlNo != null){out.println(currControlNo.getAC03REFNO());} %>
					</div>
				</div>
				</div>
			  <div class="tab-pane" id="tab_3">
				  <div class="form-group"></div>
							               <div class="form-group">
					<div class="col-sm-4 col-md-4">
						<label>Item Status :</label>
						<% if ((currControlNo.getCT03STAT()).equals("A")){%>
							Available
							<%} %>
					</div>
					<div class="col-sm-4 col-md-4">
						<label>Borrower ID : </label><% if(currControlNo != null){out.println(currControlNo.getCT03PATR());} %>
					</div>
					<div class="col-sm-4 col-md-4">
						<label>Circulated Hits: </label><% if(currControlNo != null){out.println(currControlNo.getCT03CIRHITS());} %>
					</div>
				</div>
				<div class="form-group">
					<div class="col-sm-4 col-md-4">
						<label>Last Patron ID : </label><% if(currControlNo != null){out.println(currControlNo.getCT03LASTID());} %>
					</div>
					<div class="col-sm-4 col-md-4">
						<label>Date Borrowed : </label><% if(currControlNo != null){out.println(currControlNo.getCT03BDATE());} %>
					</div>
					<div class="col-sm-4 col-md-4">
						<label>Claim Hits : </label><% if(currControlNo != null){out.println(currControlNo.getCT03CLMHITS());} %>
				</div>
				</div>
				<div class="form-group">
					<div class="col-sm-4 col-md-4">
						<label>Last Activity Date : </label><% if(currControlNo != null){out.println(currControlNo.getCT03LASTACT());} %>
					</div>
					<div class="col-sm-4 col-md-4">
						<label>Date Due : </label><% if(currControlNo != null){out.println(currControlNo.getCT03DDATE());} %>
					</div>
				</div>
						
			  </div>
				<!-- /.tab-pane -->
				<div class="tab-pane" id="tab_4">
					<div class="form-group"></div>
					<div class="form-group">
						<div class="col-sm-4 col-md-4">
							<label>Accession No. [Status] : </label>
						</div>
						<div class="col-sm-4 col-md-4">
							 <textarea rows="4" cols="50"  id="accTitl" name="accTitl" disabled><%for(CTRetrieve e: searchResult){if(!(e.getTotalDocNo()).equals(currControlNo.getCT03DOCNO())){if((e.getTotal())>0){%><%= e.getTotalDocNo() %><%= e.getTotalStat() %><%}}} %></textarea>
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
	<a type="button" class="btn btn-info" data-toggle='modal' data-target='.marcview' href='Modal_BORcrd?ctrlno=<%=currControlNo.getCT03MATNO() %>'>Bib Details</a>
	<input type="button" name="update" value="Save" class="btn btn-info" onclick="updateAccession()" id="" data-dismiss="modal"/>
	<input type="button" id='viewmarc' name="cancel" value="Cancel" class="btn btn-info" data-dismiss="modal"/>
	</div>			
	<!-- Bootstrap modal for vendor search -->
	

<div class="modal fade marcview" style="z-index:1050" tabindex="-1" role="dialog" aria-labelledby="isbdview" data-backdrop="static">
	    <div class="modal-dialog" style="width:80%" role="document">
			  <div class="modal-content" style="padding:10px">
			  </div>
		</div>
	</div>
	
	