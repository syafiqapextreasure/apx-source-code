<div class="form-group div2">
			<div class="col-sm-3 acctDiv">
				<div class="form-check">
					<input class="form-check-input" type="checkbox" value="account" id="chkBoxAcct">
						<label class="form-check-label">Account</label>
				</div>
			</div>
			
			<div class='col-sm-4 acctDiv'>
				<div class="form-check">
					<select id="acct" multiple="multiple" name="acct">
						<jsp:include page="../../module/Select_Account.jsp">
							<jsp:param name="setAcct" value="AC" />  
						</jsp:include> 
					</select>
				</div>
			</div>
			
			<div class="col-sm-3 deptDiv">
				<div class="form-check">
					<input class="form-check-input" type="checkbox" value="department" id="chkBoxDepartment">
						<label class="form-check-label deptLabel">Department</label>
				</div>
			</div>
			<div class='col-sm-4 deptDiv'>
				<div class="form-check">
					<select id="dept" multiple="multiple" name="dept">
						<jsp:include page="../../module/Select_Department.jsp"></jsp:include> 
					</select>
				</div>
			</div>
					
		</div>
		
		
		<div class="form-group div3">
			<div class="col-sm-3 invstatDiv">
				<div class="form-check">
					<input class="form-check-input" type="checkbox" value="invstatus" id="chkBoxInvStatus">
						<label class="form-check-label">Invoice Status</label>
				</div>
			</div>
			<div class='col-sm-4 invstatDiv'>
				<div class="form-check">
					<select id="acqinvstat" multiple="multiple" name="acqinvstat" class="acqinvstat">
						<jsp:include page="../../module/Select_Fndcode.jsp">
							<jsp:param name="fcode" value="Q" /> 
						</jsp:include> 
					</select>
				</div>
			</div>
			
			<div class="col-sm-3 itemstatDiv">
				<div class="form-check">
					<input class="form-check-input" type="checkbox" value="itemstatus" id="chkBoxItemStatus">
						<label class="form-check-label statusLabel">Item Status</label>
				</div>
			</div>
			<div class='col-sm-4 itemstatDiv'>
				<div class="form-check">
					<select id="acqitemstat" multiple="multiple" name="acqitemstat">
						<jsp:include page="../../module/Select_Fndcode.jsp">
							<jsp:param name="fcode" value="R" /> 
						</jsp:include> 
					</select>
				</div>
			</div>
					
		</div>

	
		<div class="form-group recordselectionDiv">
			<div class="col-sm-3">
				<div class="form-check">
					<input class="form-check-input" type="radio" name="rbRrcdSelection" value="rcdselection" id="rbRrcdSelection">
						<label class="form-check-label">Record Selection</label>
				</div>
			</div>
			
			<jsp:include page="../../module/Radio_RecordSelection.jsp">
				<jsp:param name="recordselec" value="T" /> 
			</jsp:include> 		
		</div>
		
		
		<div class="form-group claimstatDiv">
			<div class="col-sm-3">
				<div class="form-check">
					<input class="form-check-input rbClaimStat" type="radio" name="rbClaimStat" value="claimstat" id="rbClaimStat">
						<label class="form-check-label">Claim Status</label>
				</div>
			</div>
			
			<jsp:include page="../../module/Radio_ClaimStatus.jsp">
				<jsp:param name="claimStatVal" value="U" /> 
			</jsp:include> 		
		</div>
		
		
		<div class="form-group printerStat">
			<div class="col-sm-3">
				<jsp:include page="../../module/CbAcqReportPrint.jsp">
					<jsp:param name="claimStatVal" value="V" /> 
				</jsp:include> 
			</div>
		</div>
		
	

		<div class="btn-group pull-right ">	
			<div class="col-md-1"><button type="button" id="Reterive" class="btn btn-primary" title="Retrieve"> Retrieve</button></div>
		</div>
