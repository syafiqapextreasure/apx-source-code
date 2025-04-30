<style>
#patronCate {
width:100%
}

#brch {
    height: 80px;
    overflow-x: fixed;
}

#loca {
    height: 80px;
    overflow-x: fixed;
}


/* .form-check-label{
background-color: yellow;
}
 */
</style>


	<br>
		<div class="form-group">
			<div class="col-sm-2 col-md-2">
				<label class="form-check-label">
				<!-- 	<input class="form-check-input" type="radio" name="chkBoxSearchCateria" id="chkBoxSearchCateria" value="pateCate"> -->
					Patron Category
				</label>
			</div>
			<div class='col-sm-2'>
				<div class="form-check">
					<select id="patronCate" multiple="multiple" name="patronCate">
						<jsp:include page="Select_PatCate.jsp"></jsp:include> 
					</select>
				</div>
			</div>	
 			<div class="col-sm-2 col-md-2">
				<label class="form-check-label">
					<input class="form-check-input" type="radio" name="chkBoxSearchCateria" id="chkBoxSearchCateria" value="branch">
					Branch
				</label>
			</div>
			<div class='col-sm-2' style="left: -50">
				<div class="form-check">
					<select id="brch" multiple="multiple" name="brch">
						<jsp:include page="Select_Branch.jsp"></jsp:include> 
					</select>
				</div>
			</div>
			<div class="col-sm-2 col-md-2">
				<label class="form-check-label">
					<input class="form-check-input" type="radio" name="chkBoxSearchCateria" id="chkBoxSearchCateria" value="loca">
					Location
				</label>
			</div>
			<div class='col-sm-2' style="left: -50">
				<div class="form-check">
					<select id="loca" multiple="multiple" name="loca">
						<jsp:include page="Select_Location.jsp"></jsp:include> 
					</select>
				</div>
			</div>		
		</div>
