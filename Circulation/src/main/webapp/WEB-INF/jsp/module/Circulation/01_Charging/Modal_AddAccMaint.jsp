<%-- <%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="com.ilmu.cataloging.Acc_Maint.*, com.ilmu.foundation.global.*, java.util.List, com.ilmu.global.*" %>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/Cataloging/Accession_Maintenance.js"></script>					
<!-- Include Required Prerequisites -->
<style>
.modal-body {
    max-height: 100%;
    overflow-y: auto;
}

label {
    font-weight: normal !important;
}

label.mandatory {
    font-weight: bold !important;
}

</style>
<script>
$('#CT03INVDATE').datepicker({
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

<!-- Modal content-->	  
<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
		<h4 class="modal-title" id="myModalLabel">Add Accession Maintenance</h4>
	</div>
	<div class="modal-body" style="height:800px;overflow:auto">
		<div class="panel-body">
			<form role="form" class="form-horizontal">
					<div class="row">
	<div class="col-md-12">
	<!-- Custom Tabs -->
		<div class="nav-tabs-custom">
			<ul class="nav nav-tabs">
				<li class="active"><a href="#tab1" data-toggle="tab" aria-expanded="false">Details</a></li>
				<li><a href="#tab2" data-toggle="tab">Acquisition Details</a></li>
				<li class=""><a href="#tab3" data-toggle="tab" aria-expanded="true">Document Details</a></li>
             	<li><a href="#tab4" data-toggle="tab">Related Copies</a></li>
    
				 <li class="pull-right"><a href="#" class="text-muted"><i class="fa fa-gear"></i></a></li>
			</ul>
		<div class="tab-content">
			<div class="tab-pane active" id="tab1">
				<div class="box-body">
					<div class="row">
			            <div class="col-md-12">		
							<div class="form-group">
								<div class="col-sm-3 col-md-3">
									<label class="mandatory">Accession No.:</label>
								</div>
								  <div class="col-sm-3">
								   <input type="text" class="form-control CT03DOCNO"  value=""  id="CT03DOCNO" name="checkedValue" maxlength="10">
								   <%
								   	int max = GlobalValidation.getAccessionMaxLength();
									int min = GlobalValidation.getAccessionMinLength();
								   %>
								   <input type= "hidden" id="maxlength" value="<%=max%>">
								   <input type= "hidden" id="minlength" value="<%=min%>">
								  </div>
								   <div class="col-sm-2">
									<input type="button" class="btn btn-info" id="button-generateAccession" onclick="generateAcc()" value="Generate" >
								   </div>
     <!--                
								  <div class="col-sm-5">
									<textarea cols="50" rows="5" id="title" id="CT05SRAW"></textarea>
								  </div> -->
							</div>
						</div>
					</div>
					<div class="row">
			            <div class="col-md-12">		
							 <div class="form-group">
							   <div class="col-sm-3 col-md-3">
							   <label class="mandatory">
							  <input type="checkbox" value="CT03MATNO" class="fastRelease" id="fastRelease" value="" name="checkedValue">&nbsp;Control Number:
							  </label>
							  </div>
							  <div class="col-sm-3">
							  <input type="text" class="form-control CT03MATNO" id="CT03MATNO" value="" maxlength="10" onkeydown="matnoKeydown(event,this)" onfocusout="matnoFocusOut(this)" required >
							  </div>
							   <div class="col-sm-1 col-md-1"><button type="button" class="btn btn-default selectPopup"  data-toggle="modal" data-target="#titleSearch" href="RetrieveTitleModal?action=8">...</button></div>
							    <div class="col-sm-5">
							  		<div class="title"></div>
							  </div>
							</div>
						</div>
                    </div>
                  	 
					  <div class="row">
			            <div class="col-md-12">		
							<div class="form-group">
								<div class="col-sm-3 col-md-3">
									<label class="mandatory"><input type="checkbox" value="CT03LOCA" name="checkedValue" class="fastRelease" value="CT03LOCA" >&nbsp;Location:</label>
								</div>
								<div class="col-sm-5 col-md-5">
									<select class="form-control drpdown CT03LOCA" id="CT03LOCA" name="CT03LOCA" onchange="equalLocation()">
										<option value="0">Please Select</option>
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
							<div class="col-sm-5 col-md-5">
								<select class="form-control CT03LOCA1" name="GL03LOCA1" id="GL03LOCA1" onchange="equalLocation1()">
									<option value="0">Please Select</option>
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
							</div>
							</div>
						</div>
					</div>
					<div class="row">
			            <div class="col-md-12">		
							<div class="form-group">
								<div class="col-sm-3 col-md-3">
									<label class="mandatory"><input type="checkbox" value="CT03ICAT" name="checkedValue" class="fastRelease" id="fastRelease" value="" >&nbsp;Item Category:</label>
								</div>
								<div class="col-sm-5 col-md-5">
									<select class="form-control CT03ICAT" id="CT03ICAT" name="CT03ICAT" id="CT03ICAT" onchange="equalCategory()">
										<option value="0">Please Select</option>
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
								<div class="col-sm-5 col-md-5">
									<select class="form-control CT03ICAT1" name="CT03ICAT1" id="CT03ICAT1" onchange="equalCategory1()">
										<option value="0">Please Select</option>
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
								</div>
					
							</div>
						</div>
					</div>
					<div class="row">
			            <div class="col-md-12">		
							<div class="form-group">
								<div class="col-sm-3 col-md-3">
									<label class="mandatory"><input type="checkbox" value="CT03COND" name="checkedValue" class="fastRelease" id="fastRelease" value="" >&nbsp;Condition:</label>
								</div>
								<div class="col-sm-5 col-md-5">
									<select class="form-control CT03COND" id="CT03COND" name="CT03COND" id="CT03COND" onchange="equalCondition()">
										<option value="0">Please Select</option>
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
								<div class="col-sm-5 col-md-5">
									<select class="form-control CT03COND1" name="CT03COND1" id="CT03COND1" onchange="equalCondition1()">
										<option value="0">Please Select</option>
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
								</div>
							</div>
						</div>
					</div>
					 <div class="row">
			            <div class="col-md-12">		
							<div class="form-group">
								<div class="col-sm-3 col-md-3">
									<label class="mandatory"><input type="checkbox" value="CT03SMD" name="checkedValue" class="fastRelease" id="fastRelease" value="" >&nbsp;SMD:</label>
								</div>
								<div class="col-sm-5 col-md-5">
									<select class="form-control CT03SMD" id="CT03SMD" name="CT03SMD" onchange="equalSMD()">
										<option value="0">Please Select</option>
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
								<div class="col-sm-5 col-md-5">
									<select class="form-control CT03SMD1" name="CT03SMD1" id="CT03SMD1" onchange="equalSMD1()">
										<option value="0">Please Select</option>
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
								</div>
							</div>
						</div>
					</div>
					 <div class="row">
			            <div class="col-md-12">		
							<div class="form-group">
								<div class="col-sm-3 col-md-3">
									<label><input type="checkbox" name="checkedValue" value="currency" class="fastRelease" id="fastRelease" value="" >&nbsp;Currency Code:</label>
								</div>
								<div class="col-sm-5 col-md-5">
									<select class="form-control currency"  onchange="equalForex(this)" name="currency" id="currency">
															<option value="0">Please Select</option>
													<%
																SQLStatement currCode = new SQLStatement();
															List<Foundation> currCodes = currCode.getCurrencyCode();
																for (Foundation e : currCodes) {
															%>
															<option  value="<%=e.getGL24FOREX()%>" data-id="<%=e.getGL24BRATE()%>"><%=e.getGL24FOREX()%>-<%=e.getGL24DESC()%></option>
															<%
																}
															%>
														</select>
														<input class="form-control cost4 validate-double" type="hidden" onkeyup="updateBothCost()" required name="exchangeRate">
								</div>
								<div class="col-sm-5 col-md-5">
									<select class="form-control currency1" name="currency1" onchange="equalForex1(this)" id="currency1">
										<option value="0">Please Select</option>
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
								</div>
							</div>
						</div>
					</div>
					 <div class="row">
			            <div class="col-md-12">		
							<div class="form-group">
								<div class="col-sm-3 col-md-3">
									<label><input type="checkbox" name="checkedValue" value="CT03VOL" class="fastRelease" id="fastRelease" value="" >&nbsp;Volume:</label>
								</div>
								<div class="col-sm-5 col-md-5">
									<input type="text" class="form-control CT03VOL" id="CT03VOL" name="CT03VOL" style="width:100%" value="" >
								</div>
							</div>
						</div>
					</div>
					<div class="row">
			            <div class="col-md-12">		
							<div class="form-group">
								<div class="col-sm-3 col-md-3">
									<label><input type="checkbox" name="checkedValue" value="CT03RATE" class="fastRelease" value="" >&nbsp;Publisher's Rate:</label>
								</div>
								<div class="col-sm-7 col-md-7">
									<input type="text" class="form-control CT03RATE" name="CT03RATE" id="CT03RATE" style="width:50%" value="" >
								</div>
							</div>
						</div>
					</div>
						<div class="row">
						 	<div class="col-md-12">		
							<div class="form-group">	
								<div class="col-sm-3 col-md-3">
									<label><input type="checkbox" name="checkedValue" value="CT03COPYNO" class="fastRelease" id="fastRelease" value="" >&nbsp;Copies:</label>
								</div>
								<div class="col-sm-3 col-md-3">
									<input type="text" class="form-control CT03COPYNO" name="CT03COPYNO" id="CT03COPYNO" value="" > 
								</div>
							</div>
							</div>
						</div>
					<div class="row">
			            <div class="col-md-12">		
							<div class="form-group">
								<div class="col-sm-3 col-md-3">
									<label>Copies : </label>
								</div>

								<div class="col-sm-3 col-md-3">
								<label>
									<input name="copyType" class="copyType" value="Y" type="radio" checked > 
									Original
									</label>
								</div>
								<div class="col-sm-3 col-md-3">
									<label>
									<input name="copyType" class="copyType" value="N" type="radio"> 
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
										<input type="checkbox" name="checkedValue" class="onthefly" id="onthefly" value="Y">
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
									<label><input type="checkbox" name="checkedValue" value="foreignCost" class="fastRelease"  value="" >&nbsp;Foreign Cost:</label>
								</div>
								<div class="col-sm-3 col-md-3">
									<label><input type="checkbox" name="checkedValue" value="localCost" class="fastRelease"  value="" >&nbsp;Local Cost:</label>
								</div>
								<div class="col-sm-3 col-md-3">
									<label><input type="checkbox" name="checkedValue" value="sCost" class="fastRelease"  value="" >&nbsp;Service Cost:</label>
								</div>
								<div class="col-sm-3 col-md-3">
									<label><input type="checkbox" name="checkedValue" value="hCost" class="fastRelease"  value="" >&nbsp;Handling Cost:</label>
								</div>
							</div>
						</div>
					</div>
					<div class="row">
			            <div class="col-md-12">		
							<div class="form-group">
								<div class="col-sm-3 col-md-3">
									<input class="form-control cost validate-double foreignCost"  id="foreignCost" type="text"  onkeyup="updateForeignCost(this)" name="foreignCost" >
								</div>
								<div class="col-sm-3 col-md-3">
									<input class="form-control cost validate-double localCost" id="localCost" type="text" onkeyup="updateLocalCost(this)" name="localCost" >
								</div>
								<div class="col-sm-3 col-md-3">
									<input type="text" class="form-control cost validate-double sCost"  name="sCost"  style="width:100%" >
								</div>
								<div class="col-sm-3 col-md-3">
									<input type="text" class="form-control cost validate-double hCost" name="hCost"   value="" style="width:100%" >
								</div>
							</div>
						</div>
					</div>
			
					</div>
			 </div>
			 	<div class="tab-pane" id="tab2">
					<div class="form-group">
						<div class="col-sm-2 col-md-2">
				            <label><input type="checkbox" name="checkedValue" value="CT03VEND" class="fastRelease" value="" >&nbsp;Vendor:</label>
				        </div>
						<div class="col-sm-3  col-md-3">
				            <input type="text" class="form-control CT03VEND" name="CT03VEND" id="CT03VEND" onkeydown="vendorKeydown(event, this)" onfocusout="vendorFocusout(this)">
				        </div>
				        <div class="col-sm-1">
							<button type="button" class="btn btn-default selectPopup" data-toggle="modal" data-target="#vendorSearch" href="Modal_Vendor" >...</button>
					   	</div>
					   	 <div class="col-sm-3  col-md-3">
				        	 <div class="div-vendorName" id="div-vendorName" ></div>
				        </div>
					</div>
					<div class="form-group">
						<div class="col-sm-2 col-md-2">
							<label><input type="checkbox" name="checkedValue" value="CT03INVOICE" class="fastRelease"  value="" >&nbsp;Invoice No.:</label>
						</div>
						<div class="col-sm-3 col-md-3">
							<input type="text" class="form-control CT03INVOICE" name="CT03INVOICE" value="" >
						</div>
					</div>
					<div class="form-group">
						<div class="col-sm-2 col-md-2">
							<label><input type="checkbox" name="checkedValue" value="CT03INVDATE" class="fastRelease" value="" >&nbsp;Invoice Date:</label>
						</div>
						<div class="col-sm-3 col-md-3">
			          	<div class="input-group date">
  						 	<input type="text" class="form-control CT03INVDATE"  name="CT03INVDATE" id="CT03INVDATE" value="" >
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
						
						</div>
					</div>
					<div class="form-group">
						<div class="col-sm-3 col-md-3">
							<label>Date Ordered : </label>
						</div>
						<div class="col-sm-7 col-md-7">
							
						</div>
					</div>
					<div class="form-group">
						<div class="col-sm-3 col-md-3">
							<label>Reference Number : </label>
						</div>
						<div class="col-sm-7 col-md-7">
						
						</div>
					</div>
				</div>
			  <div class="tab-pane" id="tab3">
				<div class="form-group"></div>
							               <div class="form-group">
					<div class="col-sm-4 col-md-4">
						<label>Item Status :</label>
					</div>
					<div class="col-sm-4 col-md-4">
						<label>Borrower ID : </label>
					</div>
					<div class="col-sm-4 col-md-4">
						<label>Circulated Hits: </label>
					</div>
				</div>
				<div class="form-group">
					<div class="col-sm-4 col-md-4">
						<label>Last Patron ID : </label>
					</div>
					<div class="col-sm-4 col-md-4">
						<label>Date Borrowed : </label>
					</div>
					<div class="col-sm-4 col-md-4">
						<label>Claim Hits : </label>
				</div>
				</div>
				<div class="form-group">
					<div class="col-sm-4 col-md-4">
						<label>Last Activity Date : </label>
					</div>
					<div class="col-sm-4 col-md-4">
						<label>Date Due : </label>
					</div>
				</div>
			  </div>
				<!-- /.tab-pane -->
				<div class="tab-pane" id="tab4">
					<div class="form-group"></div>
					<div class="form-group">
						<div class="col-sm-4 col-md-4">
							<label>Accession No. [Status] : </label>
						</div>
						<div class="col-sm-4 col-md-4">
							<textarea rows="4" cols="50"  name="accTitl" ></textarea>
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
		<a type="button" class="btn btn-info marcbtn" data-toggle='modal' data-target='.marcAview'>Bib Details</a>
		<button type="button" class="btn btn-info" id="add-submit" onclick="addRecord(this)">
			<span class="glyphicon glyphicon-save"></span> Save
		</button>
		<input type="button" name="cancel" value="Close" class="btn btn-info" data-dismiss="modal"/>
	</div>			

	 <script type="text/javascript">
  $('.input-group.date').datepicker({
	    format: "dd/mm/yyyy",
	    todayBtn: true,
	    autoclose: true,
	    todayHighlight: true
	});
  </script>  
  
  	<div class="modal fade marcview marcAview" style="z-index:1050" tabindex="-1" role="dialog" aria-labelledby="isbdview" data-backdrop="static">
	    <div class="modal-dialog" style="width:80%" role="document">
			  <div class="modal-content" id='marccontents'  style="padding:10px">
			  </div>
		</div>
	</div>
	 --%>