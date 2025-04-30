
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/Acquisition/Maintenance/AAAM0350/Modal_ReviewListNo.js"></script>

<%
	String reqno = request.getParameter("reqno");
%>					

<div class="modal-body">		
		<div class="panel-group" id="accordion" role="tablist"
			aria-multiselectable="true">
			<div class="panel panel-default">
				<div class="panel-heading">
					<h4 class="panel-title" align="center">Review List Number</h4>
				</div>
				<div id="search" class="panel-collapse collapse in">
					<div class="panel-body">
						<form role="form" class="form-horizontal" id="reviewList">				
							<div class="form-group">
								<div class="col-sm-6">
										<textarea class="form-control" rows="3" id="reviewNopurAuthor"  style="display: none;" name="reviewNopurAuthor"><%=reqno%></textarea>
									<%-- <input type="text" class="form-control" id="reviewNo" name="reviewNo" value="<%=request.getParameter("reqno")%>"> --%>
								</div>
							</div>
							
							<div class="form-group">
								<div class="col-sm-12 col-md-12 col-xs-12">
									Enter a review list number for the selected request or click
									review list number button for system generated number.
									This review list number will be assigned to the selected request.
								</div>
							</div>

							<div class="form-group">
								<div class="col-sm-6">
									<input type="text" class="form-control" id="reviewNo" name="reviewNo">
								</div>
								<div class="col-sm-2">
									<button type="button" class="btn btn-default generateReviewNo">...</button>
								</div>
							</div>
							<div class="form-group">
								<div class="col-sm-3">
									<label><input type="checkbox" class="form-check-input" id="skip" name="skip" value="skip">Skip Print-out</label>
								</div>
							</div>
							<div class="form-group pull-right">
							      <button type="submit" id="save" class="btn btn-primary">OK</button>
								  <button type="button" id="cancel" class="btn btn-secondary" data-dismiss="modal">Cancel</button>
							</div>
						</form>
					</div>
				</div>
			</div>

	
		</div>

</div>
<!-- <div class="modal-footer">
  	<button type="submit" id="save" class="btn btn-primary">OK</button>
	<button type="button" id="cancel" class="btn btn-secondary" data-dismiss="modal">Cancel</button>
</div> -->
