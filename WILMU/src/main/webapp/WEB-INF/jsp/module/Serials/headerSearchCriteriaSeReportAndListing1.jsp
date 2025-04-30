
		<div class="form-group dateDiv">
			<div class="col-sm-2 col-md-2 dateLabel"><label>Date</label></div>
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
							<jsp:include page="../../module/Select_Branch.jsp"></jsp:include> 
						</select>
					</div>
				</div>	
		</div>
		
		
		<div class="form-group rangeDiv">
			<div class="col-sm-2 col-md-2">
				<input class="form-check-input" type="checkbox" value="range" id="chkBoxRange">
				<label class="rangeLabel">Range</label>
			</div>
				<div class="col-sm-4">
					<div class="input-group">
						<input type="text" class="input-sm form-control"  name="startInput"
							id="startInput" autocomplete="off"/> <span class="input-group-addon">to</span>
						<input type="text" class="input-sm form-control" name="endInput" id="endInput" autocomplete="off"/>
					</div>
				</div>
		</div>
		
		
		
		