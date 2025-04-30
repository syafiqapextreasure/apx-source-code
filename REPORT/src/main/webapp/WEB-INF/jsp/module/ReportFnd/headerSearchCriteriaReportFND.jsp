
	<br>
		<div class="form-group memdate">
			<div class="col-sm-2 col-md-2"><label>Membership Date</label></div>
				<div class="col-sm-4">
					<div class="input-daterange input-group">
						<input type="text" class="input-sm form-control"  name="startDate"
							id="input-startDate" autocomplete="off"/> <span class="input-group-addon">to</span>
						<input type="text" class="input-sm form-control" name="endDate" id="input-endDate" autocomplete="off"/>
					</div>
				</div>
		</div>
		
		<div class="form-group branch">
			<div class="col-sm-2 col-md-2"><label>Branch</label></div>
				<div class='col-sm-6'>
					<div class="form-check">
						<select id="branch" multiple="multiple" name="branch">
							<jsp:include page="../../module/Select_Brnc.jsp"></jsp:include> 
						</select>
					</div>
				</div>	
		</div>
		
		
		
		<div class="form-group patrStatnCate">
			<div class="col-sm-2">
				<div class="form-check">
					<input class="form-check-input" type="checkbox" value="patrStat" id="chkPatrStat">
						<label class="form-check-label" for="chkPatrStat">Patron Status</label>
				</div>
			</div>
			<div class='col-sm-4'>
				<div class="form-check">
					<select id="patronStat" multiple="multiple" name="patronStat">
						<jsp:include page="../../module/Select_PatrStat.jsp"></jsp:include> 
					</select>
				</div>
			</div>
			
			<div class="col-sm-2">
				<div class="form-check">
					<input class="form-check-input" type="checkbox" value="patrCate" id="chkPatrCate">
						<label class="form-check-label" for="chkPatrCate">Patron Category</label>
				</div>
			</div>
			<div class='col-sm-4'>
				<div class="form-check">
					<select id="patronCate" multiple="multiple" name="patronCate">
						<jsp:include page="../../module/Select_PatCate.jsp"></jsp:include> 
					</select>
				</div>
			</div>
					
								
		</div>
		

		
		<div class="form-group">
			
				
		</div>

		<div class="btn-group pull-right ">	
			<div class="col-md-1"><button type="button" id="Reterive" class="btn btn-primary" title="Retrieve"> Retrieve</button></div>
  				<!-- <div class="col-md-1"></div>
  				<div class="col-md-1"><button type="button" id="print" class="btn btn-primary" title="Print"><i class="glyphicon glyphicon-print" aria-hidden="true"></i></button></div> -->
		</div>
