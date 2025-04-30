<div class="col-sm-4">
	<div class="input-group">
		<input type="text" class="form-control" id="lblPatronID"
			name="lblPatronID"> <a id="patronIdButtonClick"
			class="input-group-addon btn btn-primary patronid"
			data-toggle="modal" data-target="#modal_searchPatronID"> <span
			class="glyphicon glyphicon-th-list"></span></a>
	</div>
</div>
<div class="col-sm-4 lblName"></div>

<!-- MODAL WHEN CLICK PATRON ID -->
<div class="modal fade" id="modal_searchPatronID" tabindex="-1"
	role="dialog" aria-labelledby="modal_searchPatronID"
	data-keyboard="false" data-backdrop="static">
	<div class="modal-dialog" style="width: 55%;">
		<div class="modal-content" id="Modal_PatrSearchContent">
			<!-- Remote content load here  -->
		</div>
	</div>
</div>
<!-- Modal END modal search PATRON ID -->