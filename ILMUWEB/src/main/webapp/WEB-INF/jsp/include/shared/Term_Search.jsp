<%@page import="com.ilmu.cataloging.Template_Maint.SQLStatement, 
				com.ilmu.cataloging.Template_Maint.Cataloging, 
				java.util.*"%>

	
	<script>
	$(document).ready(function(){
		$('#btn-submit-title').click(function(){
			
			
			var title = $("#criteria").val();
			
			var tag = $("#tag").val();
			$.get("Table_TermSearch",{criteria:title,tag:tag},function(data_title){
				$("#display_title").html(data_title);
			   });	
			
		});
		});
	</script>
	
	<%
	String tag = request.getParameter("tag");

	%>
	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
		<h4 class="modal-title" id="myModalLabel">Search</h4>
	</div>
	<div class="modal-body">
	
		<div class="panel-group" id="form_parent">
			<div class="panel-group">
				<div class="panel panel-default">
					<div class="panel-heading">
						<h4 class="panel-title">
							<a data-toggle="collapse" data-parent="#form_parent" href="#search_title" id="search-title">Search</a>
						</h4>
					</div>
					<div id="search_title" class="panel-collapse collapse in">
						<div class="panel-body">
							<form role="form" class="form-horizontal" id="retrieveBO">
							<div class="form-group">
									<div class="col-sm-2 col-md-2">
										<label>Search For</label>
									</div>
									<div class="col-sm-6 col-md-6">
										
										<input type="hidden" id="tag" value="<%=tag%>">
										<input type="text" class="form-control" name="criteria" id="criteria" placeholder="Title">
									</div>
								</div>
								<div class="clearfix"></div>

								<div class="form-group">
									<div class="col-sm-4 col-md-4"></div>
									<div class="col-sm-8 col-md-8">
										<button type="button" class="btn btn-info" id="btn-submit-title" onclick="return send_title_info()">
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
						<a data-toggle="collapse" data-parent="#form_parent" href="#result_title" id="result-title">Result</a>
					</h4>
				</div>
				<div id="result_title" class="panel-collapse collapse">
					<div class="panel-body">
						<div id="display_title"></div>
					</div>
				</div>
			</div>
			
			</div>
		</div>
	</div>
	
	<div class="modal-footer"></div>
	
	<div class="modal fade" id="officerSearch" tabindex="-1" role="dialog" aria-labelledby="officerSearch">
	    <div class="modal-dialog" role="document">
			  <div class="modal-content">
			  <!-- Remote content load here -->
			  </div>
		</div>
	</div>