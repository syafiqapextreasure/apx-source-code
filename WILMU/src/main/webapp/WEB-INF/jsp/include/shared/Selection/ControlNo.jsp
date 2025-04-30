<script type="text/javascript" src="${pageContext.request.contextPath}/js/general/controlno.js"></script>


		<div class="form-group controlnosearch">
			<div class="div1control col-sm-3 col-md-3">
				<div class="controlnodisplay"></div>
			</div>
			<div class="col-sm-3">
				<div class="input-group">
					<input type="text" class="form-control CT03MATNO" id="input-contorlNo" name="inputControlNo" onkeydown="matnoKeydown(event,this)" onfocusout="matnoFocusOut(this)">
												    <a href="Global?type=Modal&name=Modal_BibSearch&action=8&module=Cataloging" id="searchCtrlNo" class="input-group-addon btn btn-primary" data-toggle="modal" data-target="#titleSearch">
												    <span class="glyphicon glyphicon-th-list"></span></a>
				</div>
			</div>
		</div>	
		
		
		
		<!-- <div class="form-group">
			<div class="div-vendorName1 col-sm-3 col-md-3"></div>
			<div class="col-sm-7 div-ctrolNo"></div>
		</div> -->
		
		
		<!-- MODAL WHEN CLICK Search CONTORL NUMBER -->
		<div class="modal fade" id="titleSearch" tabindex="-1" role="dialog" aria-labelledby="titleSearch" data-keyboard="false" data-backdrop="static">
			<div class="modal-dialog" role="document" style="width:55%;overflow:auto">
				<div class="modal-content">
				<!-- Remote content load here -->
				</div>
			</div>
		</div>
		<!-- Modal END modal search  control number-->