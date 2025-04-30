<%@ page import="com.ilmu.global.*, java.util.List"%>
<script>

</script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/general/global.js"></script>	
<script type="text/javascript" src="${pageContext.request.contextPath}/js/general/Search_Modal.js"></script>	
<%
	String action = request.getParameter("action");
%>
	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
		<h4 class="modal-title" id="myModalLabel">Search Vendor</h4>
	</div>
	
	<div class="modal-body">
		<div class="panel panel-default" id="form_parent">
			<div class="panel-group">
			
				<div class="panel panel-default">
					<div class="panel-heading">
						<h4 class="panel-title">
							<a data-toggle="collapse" data-parent="#form_parent" href="#search_vendor" id="search">Search</a>
						</h4>
					</div>
					<div id="search_vendor" class="panel-collapse collapse in">
						<div class="panel-body">
							<form role="form" class="form-horizontal">
								<div class="form-group">
									<div class="col-sm-4 col-md-4">
										<label>Search By</label> 
									</div>
									<div class="col-sm-4 col-md-4">
									<select class="form-control" id="search-type" name="search-type" onchange="updateVenPlaceholder()">
										<option value="vendorCode">Vendor Code</option>
										<option value="vendorName">Vendor Name</option>
									</select>
									</div>
								</div>
								<div class="clearfix"></div>
								<div class="form-group">
									<div class="col-sm-4 col-md-4">
										<label>Search Text</label>
									</div>
									<div class="col-sm-4 col-md-4">
										<input type="text" class="form-control" name="criteria" id="criteria1" placeholder="Vendor Code">
										<input type="hidden" class="type" value="<%=action %>">
									</div>
								</div>
								<div class="form-group">
									<div class="col-sm-4 col-md-4"></div>
									<div class="col-sm-8 col-md-8">
										<button type="button" class="btn btn-info vendor_submit" id="vendor_submit">
											<span class="glyphicon glyphicon-search"></span> Search
										</button>
										<input type="button" name="cancel" value="Cancel" class="btn btn-info" data-dismiss="modal"/>
									</div>
								</div>
							</form>
						</div>
					</div>
				</div>
			
				<div class="panel panel-default">
					<div class="panel-heading">
						<h4 class="panel-title">
							<a data-toggle="collapse" data-parent="#form_parent" href="#result_vendor" id="result">Result</a>
						</h4>
					</div>
					<div id="result_vendor" class="panel-collapse collapse">
						<div class="panel-body">
							<div id="display_vendor"></div>
						</div>
					</div>
				</div>
				
			</div>
		</div>
	</div>
	
	<div class="modal-footer"></div>