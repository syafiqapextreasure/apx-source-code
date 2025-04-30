<br>

		<div class="form-group">
			<div class="col-sm-2 col-md-2">
				<label class="form-check-label dateselectioinlabel">
					<input class="form-check-input" type="radio" name="dateSelection" id="dateSelection" value="orderdate" checked>
					Order Date
				</label>
			</div>
			<div class="col-sm-2 col-md-2">
				<label class="form-check-label">
					<input class="form-check-input" type="radio" name="dateSelection" id="dateSelection" value="expecteddate">
					Expected Date
				</label>
			</div>
		</div>
		
		
		<div class="form-group dateDiv">
				<div class="col-sm-4">
					<div class="input-daterange input-group">
						<input type="text" class="input-sm form-control"  name="startDate"
							id="input-startDate" autocomplete="off"/> <span class="input-group-addon">to</span>
						<input type="text" class="input-sm form-control" name="endDate" id="input-endDate" autocomplete="off"/>
					</div>
				</div>
		</div>
		
		<div class="form-group branchDiv">
			
			<div class="col-sm-2 col-md-2"><label>Branch</label></div>
				<div class='col-sm-6'>
					<div class="form-check">
						<select id="branch" multiple="multiple" name="branch">
							<jsp:include page="../../../module/Select_Branch.jsp"></jsp:include> 
						</select>
					</div>
				</div>	
		</div>
		
		<div class="form-group rangeDiv">
			<div class="col-sm-2 col-md-2">
				<input class="form-check-input" type="checkbox" value="range" id="chkBoxRange">
				<label class="rangeLabel">Order No Range</label>
			</div>
				<div class="col-sm-4">
					<div class="input-group">
						<input type="text" class="input-sm form-control"  name="startInput"
							id="startInput" autocomplete="off"/> <span class="input-group-addon">to</span>
						<input type="text" class="input-sm form-control" name="endInput" id="endInput" autocomplete="off"/>
					</div>
				</div>
		</div>
		
		<input type="hidden" class="vendorSelection" value = "c">
		<jsp:include page="../../../module/Vendor.jsp"></jsp:include> 
		
		
		<div class="form-group div1">
								
			<div class="col-sm-2 statusDiv">
				<div class="form-check">
					<input class="form-check-input" type="checkbox" value="status" id="chkBoxStatus">
						<label class="form-check-label statusLabel">Status</label>
				</div>
			</div>
								
			<!--(Request - R, Order - O, Invoice - I, Gift - G) -->
			<div class='col-sm-4 statusDiv'>
				<div class="form-check">
					<select id="acqStat" multiple="multiple" name="acqStat">
						<jsp:include page="../../../module/Select_AcqStatus.jsp">
							<jsp:param name="setAcqStat" value="All" />
						</jsp:include> 
					</select>
				</div>
			</div>
			
			<div class="col-sm-2 deptDiv">
				<div class="form-check">
					<input class="form-check-input" type="checkbox" value="department" id="chkBoxDepartment">
						<label class="form-check-label statusLabel">Department</label>
				</div>
			</div>
			<div class='col-sm-4 deptDiv'>
				<div class="form-check">
					<select id="dept" multiple="multiple" name="dept">
						<jsp:include page="../../../module/Select_Department.jsp"></jsp:include> 
					</select>
				</div>
			</div>
										
		</div>
		

		
		<div class="btn-group pull-right ">	
			<div class="col-md-1"><button type="button" id="Reterive" class="btn btn-primary" title="Retrieve"> Retrieve</button></div>
		</div>
		