<script type="text/javascript" src="${pageContext.request.contextPath}/js/general/patrid.js"></script>


		<div class="form-group">
			<div class="col-sm-2 col-md-2">
				<div class="patriddisplay"></div>
					<!-- <input class="form-check-input" type="radio" name="chkBoxSearchCateria" id="chkBoxSearchCateria" value="patrID"> -->
					<!-- Patron ID -->
			</div>
			<div class="col-sm-3">
				<div class="input-group">
					<input type="text" class="form-control" id="lblPatronID" name="lblPatronID">
					<span class="input-group-btn">
						<button class="btn btn-primary patronid" type="button" data-toggle="modal" data-target="#modal_searchPatronID">...</button>
					</span>
				</div>
			</div>
		</div>	
		
		<div class="form-group">
			<div class="col-sm-2 col-md-2"></div>
			<div class="col-sm-7 lblName"></div>
		</div>
		
		
<!-- MODAL WHEN CLICK PATRON ID -->
		<div class="modal fade" id="modal_searchPatronID" tabindex="-1" role="dialog" aria-labelledby="modal_searchPatronID" data-keyboard="false" data-backdrop="static"> 
			<div class="modal-dialog" style="width:55%;">
				<div class="modal-content" id="Modal_PatrSearchContent">

					<!-- Remote content load here  -->
				</div>
			</div>
		</div>
		<!-- Modal END modal search PATRON ID -->