<%@ page contentType="text/html; charset=UTF-8" %>
<script type="text/javascript" src="<%= request.getContextPath() %>/plugin/moment.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/general/Modal_SearchReqCtrl.js"></script>

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
							<a data-toggle="collapse" data-parent="#form_parent" href="#search-reqCtrl"></a>
						</h4>
					</div>
					<div id="search-ordMaint" class="panel-collapse collapse in">
						<div class="panel-body">
							<form role="form" class="form-horizontal" id="searchreqCtrl">
								
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
									<div class="col-sm-3"></div>
									<div class="col-sm-3">
										<label><input type="radio" name="radioOption" value="reqNo">&nbsp; Request No</label>
									</div>
									<div class="col-sm-2">
										<label><input type="radio" name="radioOption" value="title">&nbsp; Title</label>
									</div>
									<div class="col-sm-2">
										<label><input type="radio" name="radioOption" value="ISBN">&nbsp; ISBN</label>
									</div>
								</div>
								
								<div class="form-group">
									<div class="col-sm-3"></div>
									<div class="col-sm-3">
										<label><input type="radio" name="radioOption" value="reqID">&nbsp; Requestor ID</label>
									</div>
									<div class="col-sm-5">
										<label><input type="radio" name="radioOption" value="datereq">&nbsp; Date Requested</label>
									</div>
								</div>
								
								<br><br> 
								
								<div class="form-group">
									<div class="col-sm-3">
										<label><input type="checkbox" name="checkboxOption" value="status">&nbsp; Status</label>
									</div>
									<div class="col-sm-5">
											<jsp:include page="../Selection/Select_StatReqCtrl.jsp"></jsp:include>
									</div>
								</div>
								
								<div class="form-group">
									<div class="col-sm-3">
										<label><input type="checkbox" name="checkboxOption" value="dept">&nbsp; Department</label>
									</div>
									<div class="col-sm-5">
											<jsp:include page="../Selection/Select_Department.jsp"></jsp:include>
									</div>
								</div>
								
								<div class="form-group">
									<div class="col-sm-3">
										<label><input type="checkbox" name="checkboxOption" value="branch">&nbsp; Branch</label>
									</div>
									<div class="col-sm-5">
											<jsp:include page="../Selection/Select_Branch.jsp"></jsp:include>
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