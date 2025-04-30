<script type="text/javascript" src="${pageContext.request.contextPath}/js/general/vendor.js"></script>
<div class="form-group vendorSearch">
	<div class="div1vend col-sm-3 col-md-3">
		<div class="vendordisplay"></div>
		<!-- <input class="form-check-input" type="radio" name="chkBoxSearchCateria" id="chkBoxSearchCateria" value="patrID"> -->
		<!-- Patron ID -->
	</div>
	<div class="col-sm-3">
		<div class="input-group">
			<input type="text" class="form-control" id="input-vendorCode"
				name="input-vendorCode"> <a href=""
				class="input-group-addon btn btn-primary vendorCode"
				data-toggle="modal" data-target="#modal_vendorSearch"> <span
				class="glyphicon glyphicon-th-list"></span></a>
			<!-- <span class="input-group-addon btn btn-primary">
						<button class="vendorCode" type="button" data-toggle="modal" data-target="#modal_vendorSearch"><span class="glyphicon glyphicon-th-list"></span></button>
					</span> -->
		</div>
	</div>
</div>

<div class="form-group">
	<div class="div-vendorName1 col-sm-3 col-md-3"></div>
	<div class="col-sm-7 div-vendorName"></div>
</div>

<!-- MODAL WHEN CLICK Search button-vendorSearch -->
<div class="modal fade" id="modal_vendorSearch" tabindex="-1"
	role="dialog" aria-labelledby="modal_vendorSearch"
	data-keyboard="false" data-backdrop="static">
	<div class="modal-dialog"
		style="width: 65%; height: 100vh; overflow-y: hidden;">
		<div class="modal-content" id="modal_vendorSearchContent">
			<!-- Remote content load here  -->
		</div>
	</div>
</div>
<!-- Modal END modal search -->