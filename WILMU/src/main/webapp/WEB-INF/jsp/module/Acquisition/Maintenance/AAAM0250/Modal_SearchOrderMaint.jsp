<%@ page contentType="text/html; charset=UTF-8" %>
<script type="text/javascript" src="<%= request.getContextPath() %>/plugin/moment.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/Acquisition/Maintenance/AAAM0250/Modal_SearchOrderMaint.js"></script>

<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal" aria-label="Close" id="closeSearch"><span aria-hidden="true">&times;</span></button>
		<h4 class="modal-title" id="myModalLabel">Search</h4>
	</div>
	
	<div class="modal-body">
		<div class="panel panel-default" id="form_parent">
			<div class="panel-group">
				<div class="panel panel-default">
					<div class="panel-heading">
						<h4 class="panel-title">
							<a data-toggle="collapse" data-parent="#form_parent" href="#search-ordMaint"></a>
						</h4>
					</div>
					<div id="search-ordMaint" class="panel-collapse collapse in">
						<div class="panel-body">
							<form role="form" class="form-horizontal" id="searchOrder">
								
								<div class="form-group">
									<!-- <div class="col-sm-1"></div> -->
									<div class="col-sm-3"><label>Search</label></div>
									<div class="col-sm-6">
										<input type="text" class="form-control criteria" name="criteria" id="criteria">					
										<!-- Order Date -->
										<div class="input-daterange input-group" id="datepicker">
											<input type="text" class="form-control"
												name="start" id="input-startDate" /> <span
												class="input-group-addon">to</span> <input type="text"
												class="form-control" name="end" id="input-endDate" />
										</div>
										<!-- END Order Date -->
									</div>
								</div>
								<div class="form-group">
									<div class="col-sm-5">
										<label><input type="radio" name="radioOption" value="controlNo">&nbsp; Control No</label>
									</div>
								</div>
								<div class="form-group">
									<div class="col-sm-3">
										<label><input type="radio" name="radioOption" value="selection">&nbsp; Catalog</label>
									</div>
									<div class="col-sm-4">
										<select class="form-control title" id="search_type" name="search_type">
											<option value="0">Please Select</option>
											<option value="title">Title</option>
											<option value="name">Name</option>
											<option value="subject">Subject</option>
											<option value="isbn">ISBN</option>
											<option value="issn">ISSN</option>
											<option value="publisher">Publisher</option>
										</select>						
									</div>
								</div>
								<div class="form-group">
									<div class="col-sm-3">
										<label>
											<input type="radio" name="radioOption" value="order">&nbsp; Acquisitions
										</label>
									</div>
									<div class="col-sm-4">
										<select class="form-control vendor" id="search_order" name="search_order">
											<option value="0">Please Select</option>
											<option value="vendor">Vendor Code</option>
											<option value="reference">Reference No</option>
											<!-- <option value="review">Review List No</option> -->
											<option value="orderno">Order No</option>
											<option value="orderdate">Order Date</option>
											<option value="invoiceno">Invoice No</option>
											<option value="reviewlistno">Review List No</option>
										</select>						
									</div>
								</div>		
								<div class="form-group">
									<div class="col-sm-5">
										<label><input type="radio" name="radioOption" value="accno">&nbsp; Accession No</label>
								</div>
								</div>
								<div class="form-group" align="center">
						      		<button type="button" id="btn-submit-retrievemodal" class="btn btn-info"><span class="glyphicon glyphicon-search"></span>Search</button>
						        	<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
					      		</div>
					      		<div id="test"></div>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	
	<div class="modal-footer"></div>