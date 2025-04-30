<br>
<div class="form-group" id="optionDisplay">
	<div class="col-sm-2 col-md-2">
		<label><b>Due Date Range</b></label>
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
		<label>Branch</label>
	</div>
	<div class='col-sm-6'>
		<div class="form-check">
			<select id="branch" multiple="multiple" name="branch">
				<jsp:include page="../../module/Select_Branch.jsp"></jsp:include>
			</select>
		</div>
	</div>
</div>

<div class="form-group">
	<div class="col-sm-2 col-md-2">
		<label class="form-check-label"> <input
			class="form-check-input" type="radio" name="chkBoxSearchCateria"
			id="chkBoxSearchCateria" value="itemcate"> Item Category
		</label>
	</div>
	<div class='col-sm-4'>
		<div class="form-check">
			<select id="icat" multiple="multiple" name="icat">
				<jsp:include page="../../module/Select_ItemCate.jsp"></jsp:include>
			</select>
		</div>
	</div>

	<div class="col-sm-2 col-md-2">
		<label class="form-check-label"> <input
			class="form-check-input" type="radio" name="chkBoxSearchCateria"
			id="chkBoxSearchCateria" value="loca"> Location
		</label>
	</div>
	<div class='col-sm-4'>
		<div class="form-check">
			<select id="loca" multiple="multiple" name="loca">
				<jsp:include page="../../module/Select_Loca.jsp"></jsp:include>
			</select>
		</div>
	</div>
</div>

<div class="form-group">
	<div class="col-sm-2 col-md-2 smd">
		<label class="form-check-label"> <input
			class="form-check-input" type="radio" name="chkBoxSearchCateria"
			id="chkBoxSearchCateria" value="smd"> SMD
		</label>
	</div>
	<div class='col-sm-4 smd'>
		<div class="form-check">
			<select id="smd" multiple="multiple" name="smd">
				<jsp:include page="../../module/Select_SMD.jsp"></jsp:include>
			</select>
		</div>
	</div>

	<div class="col-sm-2 col-md-2 patronCategory">
		<label class="form-check-label"> <input
			class="form-check-input" type="radio" name="chkBoxSearchCateria"
			id="chkBoxSearchCateria" value="pateCate"> Patron Category
		</label>
	</div>
	<div class='col-sm-4 patronCategory'>
		<div class="form-check">
			<select id="patronCate" multiple="multiple" name="patronCate">
				<jsp:include page="../../module/Select_PatronCategory.jsp"></jsp:include>
			</select>
		</div>
	</div>
</div>

<div class="form-group collType">
	<div class="col-sm-2 col-md-2">
		<label class="form-check-label">Collection Type</label>
	</div>
	<div class='col-sm-7'>
		<label class="radio-inline"> <input type="radio"
			name="collectionType" value="mono" checked>Monograph
		</label> <label class="radio-inline"> <input type="radio"
			name="collectionType" value="irs">IRS
		</label>
	</div>
</div>

<div class="form-group unReturn">
	<div class="col-sm-2 col-md-2">
		<label> <input type="checkbox" id="unReturn" name="unReturn"
			value="Y"> Unreturn
		</label>
	</div>
</div>

<div class="btn-group pull-right">
	<div class="col-md-1">
		<button type="button" id="Reterive" class="btn btn-primary"
			title="Retrieve">Retrieve</button>
	</div>
</div>