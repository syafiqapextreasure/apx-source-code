<%@ page import="com.ilmu.global.connection.DBConnection, 
					com.ilmu.cataloging.Bibliography.*, 
					java.util.List, com.ilmu.cataloging.Template_Maint.*,
					com.ilmu.global.*,java.text.*,java.util.*"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/general/global.js"></script>	
<%
String action = request.getParameter("action");
System.out.println(action);
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
									<div class="col-sm-3 col-md-3">
										<label>Search For</label>
									</div>
									<div class="col-sm-6 col-md-6">
										<input type="text" class="form-control criteria" name="criteria" id="criteria" placeholder="Title">
									</div>
								</div>
								<%
								if(action.equals("5")){
								%>
								<div class="form-group">
									<div class="col-sm-3 col-md-3">
											<input name="searchSelection" id="MatNo" value="MatNo" type="radio" onclick="radio()"> 
											<label>Accession Number</label> 
										</div>
								</div>
								<%
								}else{
								%>
								<div class="clearfix"></div>
								<%
								}
								%>
								<div class="clearfix"></div>
								<%
								if(!action.equals("5")&!action.equals("8")){
								%>
								<div class="form-group">
									<div class="col-sm-3 col-md-3">
										<label>Search In</label> 
									</div>
									<div class="col-sm-2 col-md-2">
										 <label><input type="radio" name="searchin" value="index">Index</label>
									</div>
									<div class="col-sm-2 col-md-2">
										  <label><input type="radio" name="searchin" value="buffer">Buffer</label>
									</div>
								</div>
								<%
								}
								%>

								<div class="form-group">
									<div class="col-sm-3 col-md-3">
										<input name="searchSelection" id="selection" value="selection" type="radio" onclick="radio()"> 
										<label>Selection</label> 
									</div>
									<div class="col-sm-4 col-md-4">
										<select class="form-control title" id="search_type" name="search_type" onchange="updatePlaceholder()">
											<option value="title">Title</option>
											<option value="name">Name</option>
											<option value="subject">Subject</option>
											<option value="publisher">Publisher</option>
											<option value="placeOfPublication">Place of Publication</option>
											<option value="yearOfPublication">Year of Publication</option>
											<option value="series">Series</option>
											<option value="callNo">Call-No.</option>
											<option value="isbn">ISBN</option>
											<option value="issn">ISSN</option>
											<option value="notesArea">Notes Area</option>
										</select>
									</div>
								</div>
								<div class="clearfix"></div>
								<div class="form-group">		
									<div class="col-sm-3 col-md-3">
										<input name="searchSelection" id="tag" value="tag" type="radio" onclick="radio()"> 
										<label>In Tag</label>
									</div>
									<div class="col-sm-4 col-md-4">
										<%								
								List<Tag_Handler> TagInfo = null;
								TagInfo = Tag_Handler.getBibTags();
								%>
									<select class="form-control" id="tags" name="tags" onchange="getIndicators()">
										<option value="0">Please select</option>
										<%
										for(Tag_Handler i: TagInfo){
										%>
										<option value="<%=i.getGL17TABNAME()%>" data-id="<%=i.getGL17GRID()%>"><%=i.getGL17TAG()%>-<%=i.getGL17DESC()%></option>

										<%										
											}
										%>
										</select>
									</div>
								</div>
								<div class="clearfix"></div>
								<div id="index">
								<div class="form-group">		
									<div class="col-sm-4 col-md-4">
										<input name="searchSelection" id="ctrlNo" value="ctrlNo" type="radio" onclick="radio()">
										<label> Control Number </label>
									</div>
								</div>
								</div>
								<div id="buffer" style="display:none">
									<div class="form-group">		
									<div class="col-sm-4 col-md-4">
										<input name="searchSelection" id="bufferNo" value="bufferNo" type="radio" onclick="radio()">
										<label> Buffer Number: </label>
									</div>
								</div>
								</div>
								<div class="clearfix"></div>
								<div class="form-group">
									<div class="col-sm-3 col-md-3">
										<input name="searchSelection" id="OfficerID" value="OfficerID" type="radio" onclick="radio()">
										<label>as Officer ID</label>
									</div>
									<div class="col-sm-2 col-md-2">
										<select class="form-control officerID" id="search_type" name="search_type" onchange="updatePlaceholder()">
											<option value="creator">Creator</option>
											<option value="modifier">Modifier</option>
										</select>
									</div>
									<div class="col-sm-2 col-md-2">
										<button type="button" class="btn btn-success officerID" id="btn_add" data-toggle="modal" 
										data-target="#officerSearch" href="../../ICMS/jsp/include/sharedV1/Modal_OfficerID.jsp">...</button>
									</div>
								</div>

								<div class="form-group">
									<div class="col-sm-4 col-md-4"></div>
									<div class="col-sm-8 col-md-8">
								
									<%
									if(action.equals("5")||action.equals("8")){
									%>
										<button type="button" class="btn btn-info" id="btn-submit-retrievemodal1" data-action="<%=action %>" onclick="getURL(this);return send_title_info()">
											<span class="glyphicon glyphicon-search"></span> Search
										</button>
									<%
										}else{
									%>
										<button type="button" class="btn btn-info" id="btn-submit-retrievemodal" onclick="return send_title_info()">
											<span class="glyphicon glyphicon-search"></span> Search
										</button>
									<%
										}
									%>
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