<script type="text/javascript" src="${pageContext.request.contextPath}/js/general/patrid.js"></script>
<div class="form-group patronSearch">
	<div class="div1patrid col-sm-3 col-md-3">
		<div class="patriddisplay"></div>
		<!-- Patron ID -->
	</div>
	<div class="div2patrid col-sm-3">
		<div class="input-group">
			<input type="text" class="form-control" id="lblPatronID"
				name="lblPatronID"> <a href=""
				class="input-group-addon btn btn-primary patronid"
				data-toggle="modal" data-target="#modal_searchPatronID"> <span
				class="glyphicon glyphicon-th-list"></span></a>
		</div>
	</div>
</div>

<div class="form-group">
	<div class="col-sm-3 col-md-3 div1patrid"></div>
	<div class="col-sm-7 lblName"></div>
</div>

<!-- MODAL WHEN CLICK PATRON ID -->
<div class="modal fade" id="modal_searchPatronID" tabindex="-1"
	role="dialog" aria-labelledby="modal_searchPatronID"
	data-keyboard="false" data-backdrop="static">
	<div class="modal-dialog" style="width: 55%;">
		<div class="modal-content" id="Modal_PatrSearchContent">
			<!-- Remote content load here  -->
			lalalala
		</div>
	</div>
</div>
<!-- Modal END modal search PATRON ID -->