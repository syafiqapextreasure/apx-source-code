<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="ISO-8859-1" import="java.util.*"%>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/Acquisition/Maintenance/AAAM0350/Modal_SearchReviewList.js"></script>	
<!-- <style>
#modal_vendorSearch {
	z-index: 1080 !important;
}
</style> -->


  <div class="modal-header">
				<button type="button" id="closeModalseacehreview" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title" id="myModalLabel">Search Review List No</h4>
			</div>
     
	<div class="modal-body">
				<div class="panel-group">
					<div class="panel panel-default">
						<div class="panel-heading">
							<h4 class="panel-title">
								<a data-toggle="collapse" data-parent="#form_parent"
									href="#search_reviewlist">Search</a>
							</h4>
						</div>
						<div id="search_reviewlist" class="panel-collapse collapse in">
							<div class="form-horizontal">
								<br />
								<div class="form-group">
									<div class="col-sm-3"></div>
									<div class="col-sm-2">
										<label><input type="radio" name="radioOptionseachreviewlist" value="rListNo">&nbsp; Review List No</label>
									</div>
									<div class="col-sm-2">
										<label><input type="radio" name="radioOptionseachreviewlist" value="vCode">&nbsp; Vendor Code</label>
									</div>
									<div class="col-sm-2">
										<label><input type="radio" name="radioOptionseachreviewlist" value="oDate">&nbsp; Request Date</label>
									</div>
								</div>
								<div class="form-group">
									<div class="col-sm-1"></div>
									<div class="col-sm-2">
										<label>Search</label>
									</div>
									<div class="col-sm-6">
										<input type="text" class="form-control criteriaseachreviewlist" name="criteriaseachreviewlist" id="criteriaseachreviewlist">					
										<!-- Order Date -->
										<div class="input-daterange input-group" id="datepickerseachreviewlist">
											<input type="text" class="form-control"
												name="start" id="input-startDateseachreviewlist" /> <span
												class="input-group-addon">to</span> <input type="text"
												class="form-control" name="end" id="input-endDateseachreviewlist" />
										</div>
										<!-- END Order Date -->
									</div>
								</div>
								<div class="clearfix"></div>

								<div class="form-group" align="center">
						      		<button type="button" id="btn-submit-searchreview" class="btn btn-info"><span class="glyphicon glyphicon-search"></span>Search</button>
						        	<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
					      		</div>
							</div>
						</div>
					</div>
				</div>

				<div class="panel panel-default">
					<div class="panel-heading">
						<h4 class="panel-title">
							<a data-toggle="collapse" data-parent="#form_parent"
								href="#result_reviewlist">Result</a>
						</h4>
					</div>
					<div id="result_reviewlist" class="panel-collapse collapse">
						<div class="panel-body">
							<div id="display_reviewList">
								<table class="table table-hover" id="table-reviewList" data-toggle="table" style="width:100%">
									<thead>
										<tr>
											<th data-sortable="true">Review List No</th>
											<th data-sortable="true">Action</th>
										</tr>
									</thead>
									<tbody>
									</tbody>
								</table>
							</div>
						</div>
					</div>
				</div>
			</div>