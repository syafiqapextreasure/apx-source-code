<%@page import="com.ilmu.cataloging.Template_Maint.SQLStatement, 
				com.ilmu.cataloging.Template_Maint.Cataloging, 
				java.util.*"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/general/Term_Search.js"></script>	
	
	<%
	String tag = request.getParameter("tag");
	String action = request.getParameter("action");
	String tagid= request.getParameter("tagid");

	%>
	<div class="modal-header">

		<h4 class="modal-title" id="myModalLabel">Search</h4>
	</div>
	<div class="modal-body">
	
		<div class="panel-group" id="form_parent">
			<div class="panel-group">
				<div class="panel panel-default">
					<div class="panel-heading">
						<h4 class="panel-title">
							<a data-toggle="collapse" data-parent="#form_parent" href="#search_termsearch" id="search-termsearch">Search</a>
						</h4>
					</div>
					<div id="search_termsearch" class="panel-collapse collapse in">
						<div class="panel-body">
							<form role="form" class="form-horizontal" id="termSearchModal">
							<div class="form-group">
									<div class="col-sm-2 col-md-2">
										<label>Search For</label>
									</div>
									<div class="col-sm-6 col-md-6">
										<input type="hidden" id="tagid" value="<%=tagid %>">
										<input type="hidden" id="action" value="<%=action %>">
										<input type="hidden" id="termtag" value="<%=tag%>">
										<input type="text" class="form-control" name="criteria" id="criteria"onkeydown="getData(event)">
									</div>
								</div>
								<div class="clearfix"></div>

								<div class="form-group">
									<div class="col-sm-4 col-md-4"></div>
									<div class="col-sm-8 col-md-8">
										<button type="button" class="btn btn-info" id="btn-submit-titles" onclick="getResult()">
											<span class="glyphicon glyphicon-search"></span> Search
										</button>
										<input type="button" name="cancel" value="Cancel" class="btn btn-info termSearch" onclick="closeTermModal();"/>
									</div>
								</div>
							</form>
						</div>
					</div>
			</div>
			
			<div class="panel panel-default">
				<div class="panel-heading">
					<h4 class="panel-title">
						<a data-toggle="collapse" data-parent="#form_parent" href="#result_termsearch" id="result-termsearch">Result</a>
					</h4>
				</div>
				<div id="result_termsearch" class="panel-collapse collapse">
					<div class="panel-body">
						<div id="display_termsearch" style="height:50%;overflow:scroll"></div>
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