<script type="text/javascript" src="${pageContext.request.contextPath}/js/Serial/Order.js"></script>					

<div class="modal-body">		
		<div class="panel-group" id="accordion" role="tablist"
			aria-multiselectable="true">
			<div class="panel panel-default">
				<div class="panel-heading">
					<h4 class="panel-title">
						<a data-toggle="collapse" data-parent="#form_parent"
							href="#search"><%=request.getParameter("mode") %> Number</a>
					</h4>
				</div>
				<div id="search" class="panel-collapse collapse in">
					<div class="panel-body">
						<form role="form" class="form-horizontal" id="current_form"
							onsubmit="searchOrder()">

							<!-- Vendor Code -->
							<div class="form-group">
								<div class="col-sm-12 col-md-12 col-xs-12">
									Enter a Reference number for the selected orders or click the Reference number
									button for system generated number. This request number will be assigned as 
									the reference for selected orders
								</div>
							</div>

							<!-- Cancellation Date -->
							<div class="form-group">
								<div class="col-sm-8">
									<input type="text" class="form-control" id="reference">
								</div>
								<div class="col-sm-2">
									<button type="button" class="btn btn-default selectPopup" onclick="generate()">...</button>
								</div>
							</div>
							<div class="form-group">
								<div class="col-sm-3">
									<label for="checkbox-orderDate">
									<input type="checkbox" id="checkExist" name="processCheck" value="checkExist">Check If Exist </label>
								</div>
								<div class="col-sm-3">
									<label for="checkbox-orderDate">
									<input type="checkbox" id="orderLetter" name="processCheck" value="orderLetter">Skip Printing
									</label>
								</div>
							</div>

						</form>
					</div>
				</div>
			</div>

	
		</div>
		<!-- Hidden Search parameters -->
		<input type="hidden" id="input-searchParams">

	<script type="text/javascript" src="js/09.js"></script>
	<script type="text/javascript" src="js/shared.js"></script>
</div>
<div class="modal-footer">
  	<button type="submit" id="save" class="btn btn-primary" onClick="process()" disabled>OK</button>
	<button type="button" id="cancel" class="btn btn-secondary" data-dismiss="modal">Cancel</button>
</div>
