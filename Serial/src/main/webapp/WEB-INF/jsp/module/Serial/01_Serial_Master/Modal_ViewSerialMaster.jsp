	<%@ page contentType="text/html; charset=UTF-8" %>
	<%@ page import="java.util.List, com.ilmu.global.*, com.ilmu.serial.serial_master.*" %>
	<% System.out.println("Serial" + request.getParameter("controlNo"));Serial_Master currControlNo = Serial_Master.SE01_getDetailsByControlNo(request.getParameter("controlNo")); %>
	<style>
	.combine-row {
    	height:60px;
	}	
	</style>
	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
		<h4 class="modal-title" id="myModalLabel">View Serial Master</h4>
	</div>
	<div class="modal-body">
		<div class="panel-body">
			<form role="form" class="form-horizontal">
				
				<div class="form-group">
					<div class="col-sm-2">
						<label>Control No<span style="color:red">*</span></label>
					</div>
					<div class="col-sm-3">
						<input type="text" class="form-control" readonly 
							value="<% if(currControlNo != null){out.println(currControlNo.getControlNo());} %>">
						<div class="clearfix"></div>
						<!-- <button type="button" class="btn btn-default" disabled>Bibliography</button> -->
					</div>
					<div class="col-sm-1">
						<button type="button" class="btn btn-default" disabled>...</button>
					</div>
					<div class="col-sm-6">
						<div><% if(currControlNo != null){out.println(currControlNo.getTitle());} %></div>
					</div>
				</div>
				
				<div class="form-group">
					<div class="col-sm-2">
						<label>SMD</label>
					</div>
					<div class="col-sm-4">
						<select class="form-control" disabled>
						<%
						 if(currControlNo.getSMD() != null){
						%>
							<option><% if(currControlNo != null){out.println(currControlNo.getSMD());} %></option>
						<%
						 }else{
						%>
						<option>Please Select</option>
						<%
						 }
						%>
						</select>
					</div>
					
					<div class="col-sm-2">
						<label>Currency<span style="color:red">*</span></label>
					</div>
					<div class="col-sm-4">
						<select class="form-control" disabled>
						<%
						 if(currControlNo.getCurrency() != null){
						%>
							<option><% out.println(currControlNo.getCurrency());out.println(" - " + Currency.getCurrencyDesc(currControlNo.getCurrency()));%></option>
						<%
						 }else{
						%>
						<option>Please Select</option>
						<%
						 }
						%>
						</select>
					</div>
				</div>
				
				<div class="form-group">
					<div class="col-sm-2">
						<label>Language</label>
					</div>
					<div class="col-sm-4">
						<select class="form-control" disabled>
							<option><% if(currControlNo.getLanguage() == null){out.println("");}else{out.println(currControlNo.getLanguage());} %></option>
						</select>
					</div>
					
					<div class="col-sm-2">
						<label>Publisher Rate</label>
					</div>
					<div class="col-sm-2">
						<input type="text" class="form-control" readonly 
							value="<% if(currControlNo != null){out.println(currControlNo.getExchRate());} %>">
					</div>
				</div>
				
				<div class="form-group">
					<div class="col-sm-2">
						<label>Department</label>
					</div>
					<div class="col-sm-4">
						<select class="form-control" disabled>
							<option><% if(currControlNo.getDepartment() == null){out.println("");}else{out.println(currControlNo.getDepartment());} %></option>
						</select>
					</div>
					
					<div class="col-sm-2">
						<label>Foreign Price</label>
					</div>
					<div class="col-sm-2">
						<input type="text" class="form-control decimal" readonly
							value="<% if(currControlNo != null){out.println(Handler.format2Decimal(currControlNo.getFPrice()));} %>">
					</div>
				</div>
				
				<div class="form-group">
					<div class="col-sm-2">
						<label>Frequency</label>
					</div>
					<div class="col-sm-4">
						<select class="form-control" disabled>
							<option><% if(currControlNo.getFrequency() == null){out.println("");}else{out.println(currControlNo.getFrequency());} %></option>
						</select>
					</div>
					
					<div class="col-sm-2">
						<label>Local Price<span style="color:red">*</span></label>
					</div>
					<div class="col-sm-2">
						<input type="text" class="form-control decimal" readonly
							value="<% if(currControlNo != null){out.println(Handler.format2Decimal(currControlNo.getLPrice()));} %>">
					</div>
				</div>
				
				<div class="form-group">
					<div class="col-sm-2">
						<label>Vendor<span style="color:red">*</span></label>
					</div>
					<div class="col-sm-2">
						<input type="text" class="form-control" readonly
							value="<% if(currControlNo != null){out.println(currControlNo.getVendorCode());} %>">					
					</div>			  
					<div class="col-sm-1">
						<button type="button" class="btn btn-default" disabled>...</button>
					</div>
					<div class="col-sm-4">
						<div><% if(currControlNo != null){out.println(currControlNo.getVendorName());} %></div>
					</div>
				</div>
				
				<div class="form-group">
					<div class="col-sm-2">
						<label>Binder</label>
					</div>
					<div class="col-sm-2">
						<input type="text" class="form-control" readonly
							value="<% if(currControlNo.getBinder() == null){out.println("");}else{out.println(currControlNo.getBinder());} %>">				
					</div>			  
					<div class="col-sm-1">
						<button type="button" class="btn btn-default" disabled>...</button>
					</div>
					<div class="col-sm-4">
						<div><% if(currControlNo.getBinderName() == null){out.println("");}else{out.println(currControlNo.getBinderName());} %></div>
					</div>
				</div>
				
				<div class="form-group">
					<div class="col-sm-2">
						<label>Publisher</label>
					</div>
					<div class="col-sm-2">
						<input type="text" class="form-control" readonly
							value="<% if(currControlNo.getPublisher() == null){out.println("");}else{out.println(currControlNo.getPublisher());}%>">						
					</div>			  
					<div class="col-sm-1">
						<button type="button" class="btn btn-default" disabled>...</button>
					</div>
					<div class="col-sm-4">
						<div><% if(currControlNo.getPublisherName() == null){out.println("");}else{out.println(currControlNo.getPublisherName());} %></div>
					</div>
				</div>
				
				<div class="form-group">
					<div class="col-sm-2">
						<label>Bibliographic Source</label>
					</div>
					<div class="col-sm-4">
						<select class="form-control" disabled>
							<option><% if(currControlNo.getBibliographic() == null){out.println("");}else{out.println(currControlNo.getBibliographic());} %></option>
						</select>
					</div>
				</div>
				
				<div class="form-group">
					<div class="col-sm-2">
						<label>Renewal Alert</label>
					</div>
					<div class="col-sm-2">
						<input type="text" class="form-control" readonly
							value="<% if(currControlNo.getRenewal() == null){out.println("");}else{out.println(currControlNo.getRenewal());} %>">
					</div>
					<div class="col-sm-2"></div>
					<div class="col-sm-6">
						<div class="combine-row">
							<fieldset class="amount-border">
								<div class="checkbox">
									<label style="width:150px;"><input type="checkbox" disabled <% if(currControlNo.getCumIndx().equals("Y")){out.println("checked");} %>>Cumulative Index</label>
									<label><input type="checkbox" disabled <% if(currControlNo.getContPg().equals("Y")){out.println("checked");} %>>Contents Page</label>
								</div>
								<div class="checkbox">
								  	<label style="width:150px;"><input type="checkbox" disabled <% if(currControlNo.getStdOrd().equals("Y")){out.println("checked");} %>>Standing Order</label>
								  	<label><input type="checkbox" disabled <% if(currControlNo.getBindtr().equals("Y")){out.println("checked");} %>>Binding Treatment</label>
								</div>
								<div class="checkbox">
								  	<label style="width:150px;"><input type="checkbox" disabled <% if(currControlNo.getSubjHead().equals("Y")){out.println("checked");} %>>Subject Heading</label>
								  	<label><input type="checkbox" disabled <% if(currControlNo.getIrsIdx().equals("Y")){out.println("checked");} %>>IRS Indexing</label>
								</div>
								<div class="checkbox">
								  	<label style="width:150px;"><input type="checkbox" disabled <% if(currControlNo.getTitlPg().equals("Y")){out.println("checked");} %>>Title Page</label>
								  	<label><input type="checkbox" disabled <% if(currControlNo.getRoute().equals("Y")){out.println("checked");} %>>Routing</label>
								</div>
							</fieldset>
						</div>
					</div>
				</div>
				
				<div class="form-group">
					<div class="col-sm-2">
						<label>Serial Mode</label>
					</div>
					<div class="col-sm-4">
						<select class="form-control" disabled>
							<option><% if(currControlNo.getSerialMode() == null){out.println("");}else{out.println(currControlNo.getSerialMode());} %></option>
						</select>
					</div>
				</div>
				
				<div class="form-group">
					<div class="col-sm-2">
						<label>Reference No</label>
					</div>
					<div class="col-sm-3">
						<input type="text" class="form-control" readonly
							value="<% if(currControlNo.getRefNo() == null){out.println("");}else{out.println(currControlNo.getRefNo());} %>">
					</div>
				</div>
				
				<div class="form-group">
					<div class="col-sm-2">
						<label>Requestor</label>
					</div>
					<div class="col-sm-2">
						<input type="text" class="form-control" readonly
							value="<% if(currControlNo.getRequestor() == null){out.println("");}else{out.println(currControlNo.getRequestor());} %>">					
					</div>			  
					<div class="col-sm-1">
						<button type="button" class="btn btn-default" disabled>...</button>
					</div>
					<div class="col-sm-4">
						<div><% if(currControlNo.getRequestorName() == null){out.println("");}else{out.println(currControlNo.getRequestorName());} %></div>
					</div>
				</div>
			</form>
		</div>
	</div>
	
	<div class="modal-footer">
		<input type="button" name="cancel" value="Cancel" class="btn btn-info" data-dismiss="modal"/>
	</div>