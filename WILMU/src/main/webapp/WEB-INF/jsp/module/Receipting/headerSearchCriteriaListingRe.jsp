<br>
<div class="form-group">
	<div class="col-sm-2 col-md-2">
		<label> <input type="checkbox" id="patronId"
			name="chkBoxSearchCateria" value="patronId"> Patron ID :
		</label>
	</div>
	<div class="row">
		<jsp:include page="Listing/AAEL0250/searchPatronId.jsp"></jsp:include>
	</div>
</div>

<div class="form-group">
	<div class="col-sm-2 col-md-2">
		<label> <input type="checkbox" id="dateRange"
			name="chkBoxSearchCateria" value="dateRange"> Date Range from
			:
		</label>
	</div>
	<div class="col-sm-4">
		<div class="input-daterange input-group">
			<input type="text" class="input-sm form-control" name="startDate"
				id="input-startDate" autocomplete="off" /> <span
				class="input-group-addon">to</span> <input type="text"
				class="input-sm form-control" name="endDate" id="input-endDate"
				autocomplete="off" />
		</div>
	</div>
</div>

<div class="form-group">
	<div class="col-sm-2 col-md-2">
		<label> <input type="checkbox" id="trxcCode"
			name="chkBoxSearchCateria" value="trxcCode"> Transaction Code
			:
		</label>
	</div>
	<div class='col-sm-4'>
		<div class="form-check">
			<select class="form-control" style="overflow: scroll;"
				id="transactCode" name="transactCode">
				<jsp:include page="../../module/Select_TransactionCode.jsp"></jsp:include>
			</select>
		</div>
	</div>
</div>

<div class="form-group">
	<div class="col-sm-2 col-md-2">
		<label> <input type="checkbox" id="paidTransaction"
			name="chkBoxSearchCateria" value="paidTransaction"> Includes
			paid transaction
		</label>
	</div>
</div>


<div class="btn-group pull-right">
	<div class="col-md-1">
		<button type="button" id="Reterive" class="btn btn-primary"
			title="Retrieve">Retrieve</button>
	</div>
</div>