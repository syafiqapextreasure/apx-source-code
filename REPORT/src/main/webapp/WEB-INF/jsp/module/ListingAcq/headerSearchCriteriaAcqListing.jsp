
	<br>
		<div class="form-group">
			<div class="col-sm-2 col-md-2"><label>Order Date</label></div>
				<div class="col-sm-4">
					<div class="input-daterange input-group">
						<input type="text" class="input-sm form-control"  name="startDate"
							id="input-startDate" autocomplete="off"/> <span class="input-group-addon">to</span>
						<input type="text" class="input-sm form-control" name="endDate" id="input-endDate" autocomplete="off"/>
					</div>
				</div>
		</div>
		
		<div class="form-group">
			<div class="col-sm-2 col-md-2"><label>Branch</label></div>
				<div class='col-sm-6'>
					<div class="form-check">
						<select id="branch" multiple="multiple" name="branch">
							<jsp:include page="../../module/Select_Brnc.jsp"></jsp:include> 
						</select>
					</div>
				</div>	
		</div>
			
		<input type="hidden" class="vendorSelection" value = "c">
		<jsp:include page="../Vendor.jsp"></jsp:include> 

		<div class="form-group">
			<div class="col-sm-2 col-md-2">
				<input class="form-check-input" type="checkbox" name="chkBoxSearchCateria" id="chkBoxRange" value="orderNumberRange">
					<label>Order Number Range</label></div>
						<div class="col-sm-4">
							<div class="input-daterange input-group">
								<input type="text" class="input-sm form-control"  name="first_number" id="first_number" maxlength="10" disabled/> 
									<span class="input-group-addon">to</span>
								<input type="text" class="input-sm form-control" name="second_number" id="second_number" maxlength="10" disabled/>
							</div>
						</div>
		</div>
		
		<div class="form-group">
 			<div class="col-sm-2 col-md-2">
				<label class="form-check-label">
					<input class="form-check-input" type="checkbox" name="chkBoxSearchCateria" id="chkBoxOrderMode" value="orderMode">
					Order Mode
				</label>
			</div>
			<div class='col-sm-4'>
				<div class="form-check">
					<select id="orderMode" multiple="multiple" name="orderMode" disabled>
						<jsp:include page="../../module/Select_OrderMode.jsp"></jsp:include> 
					</select>
				</div>
			</div>	
		
			<div class="col-sm-2 col-md-2 patronCategory">
				<label class="form-check-label">
					<input class="form-check-input" type="checkbox" name="chkBoxSearchCateria" id="chkBoxOrderStatus_Request" value="orderStatusRequest">
					Order Status/ Request
				</label>
			</div>
			<div class='col-sm-4 patronCategory'>
				<div class="form-check">
					<select id="orderStatusRequest" multiple="multiple" name="orderStatusRequest" disabled>
						<jsp:include page="../../module/Select_OrderStatusRequest.jsp"></jsp:include>
					</select>
				</div>
			</div>

		</div>
		
		<div class="form-group">
			<div class="col-sm-2 col-md-2">
				<label class="form-check-label">
					<input class="form-check-input" type="checkbox" name="chkBoxSearchCateria" id="chkBoxAccount" value="account">
					Account
				</label>
			</div>
			<div class='col-sm-4'>
				<div class="form-check">
					<select id="account" multiple="multiple" name="account" disabled>
						<jsp:include page="../../module/Select_Account.jsp"></jsp:include> 
					</select>
				</div>
			</div>	
			
		<div class="form-group">
			<div class="col-sm-2 col-md-2">
				<label class="form-check-label">
					<input class="form-check-input" type="checkbox" name="chkBoxSearchCateria" id="chkBoxDepartment" value="department">
					Department
				</label>
			</div>
				<div class='col-sm-4'>
					<div class="form-check">
						<select id="dept" multiple="multiple" name="dept" disabled>
							<jsp:include page="../../module/Select_Dept.jsp"></jsp:include> 
						</select>
					</div>
				</div>	
		</div>

		</div>
		

		<div class="btn-group pull-right ">	
  					<div class="col-md-1"><button type="button" id="Reterive" class="btn btn-primary" title="Retrieve"> Retrieve</button></div>
				<!-- <div class="col-md-1"><button type="button" id="Reterive" class="btn btn-primary" title="Retrieve"> Retrieve</button></div> -->
		</div>
