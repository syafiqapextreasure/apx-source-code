<%@ page import="com.ilmu.global.connection.DBConnection, 
					com.ilmu.cataloging.Bibliography.*, 
					java.util.List, com.ilmu.cataloging.Template_Maint.*,
					com.ilmu.global.*,java.text.*,java.util.*"%>
					

<script type="text/javascript" src="${pageContext.request.contextPath}/js/general/global.js"></script>	
<script type="text/javascript" src="${pageContext.request.contextPath}/js/general/Search_Modal.js"></script>	

<style>
#officerSearch{
	z-index: 1080 !important;
}

body {
  padding-top: 50px;
}
legend a {
  color: inherit;
}
legend.legendStyle {
padding-left: 5px;
padding-right: 5px;
}
fieldset.fsStyle {
font-weight: normal;
border: 1px solid #999999;
padding: 4px 0 4px 0;
margin: 5px 0 5px 0;
}
legend.legendStyle {
font-size: 14px;
}

legend {
width: auto;
border-bottom: 0px;
}


</style>

<%
String action = request.getParameter("action");
System.out.println(action);
%>
	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
		<h4 class="modal-title" id="myModalLabel">Catalog Search</h4>
	</div>
	<div class="modal-body">
	
		<div class="panel-group" id="form_parent">
			<div class="panel-group">
				<div class="panel panel-default">
					<div class="panel-heading">
						<h4 class="panel-title">
							<a data-toggle="collapse" data-parent="#form_parent" href="#search_title" id="search-title">
								Search
								<i class="indicator glyphicon glyphicon-chevron-down  pull-right"></i>
							</a>
						</h4>
					</div>
					<div id="search_title" class="panel-collapse collapse in search_title">
						<div class="panel-body">
							<form role="form" class="form-horizontal" id="retrieveBO">
							<div class="form-group">
									<div class="col-sm-3 col-md-3">
										<label>Search For</label>
									</div>
									<div class="col-sm-6 col-md-6">
										<input type="text" class="form-control criteria" name="criteria" id="criteria" placeholder="Enter Search Term">
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
								if(!action.equals("5")&!action.equals("8")&!action.equals("15")){
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
										<select class="form-control title" id="search_type" name="search_type">
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
									<select class="form-control tags" id="select_tags" name="tags" disabled>
										<option value="0">Please select</option>
										<%
										for(Tag_Handler i: TagInfo){
										%>
										<option value="<%=i.getGL17TAG()%>" data-id="<%=i.getGL17GRID()%>"><%=i.getGL17TAG()%>-<%=i.getGL17DESC()%></option>

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
									<div class="cold-md-12">
											<fieldset class="fsStyle">													
												<legend class="legendStyle">
									                  <input name="searchSelection" id="OfficerID" value="officerID" type="radio" onclick="radio()">
													  <label>as Officer ID</label>
												</legend>
												<div class="row collapse in" id="demo">
													<div class="form-group">
														<label class="col-sm-3 control-label">&nbsp;</label>
														<div class="col-sm-2 col-md-2">
															<select class="form-control officerID" id="officerValue" name="search_type" onchange="updatePlaceholder()" disabled>
																<option value="creator">Creator</option>
																<option value="modifier">Modifier</option>
															</select>
														</div>
													</div>
													<div class="form-group">
														<label class="col-sm-3 control-label">Date Range:</label>
														<div class="col-sm-6">
															<div class="input-daterange input-group date" id="orderDate">
																<input type="text" class="form-control" readonly id="startDateInput"/>
																<span class="input-group-addon">to</span>
																<input type="text" class="form-control" readonly id="stopDateInput"/>
															</div>
														</div>
													</div>
									          	</div>
									  		</fieldset>
									</div>
								<!-- 	<div class="form-group">
										<div class="col-sm-12 col-md-12">
										<fieldset style="border: 1px solid #999999;padding:4px 0 4px 0;">
											<legend class="legendStyle" style="font-size: 90%;">
						                      <input name="searchSelection" id="OfficerID" value="OfficerID" type="radio" onclick="radio()">
						                      as Officer ID
											</legend>
											<div class="form-group">
												<div class="col-sm-2 col-md-2">
													<select class="form-control officerID" id="search_type" name="search_type" onchange="updatePlaceholder()">
														<option value="creator">Creator</option>
														<option value="modifier">Modifier</option>
													</select>
												</div>
											</div>
											<div class="form-group">
												<label class="col-sm-3 control-label">Date Range:</label>
												<div class="col-sm-6">
													<div class="input-daterange input-group date" id="orderDate">
														<input type="text" class="form-control" readonly id="startDateInput"/>
														<span class="input-group-addon">to</span>
														<input type="text" class="form-control" readonly id="stopDateInput"/>
													</div>
												</div>
											</div>
											<div class="col-sm-2 col-md-2">
												<button type="button" class="btn btn-success officerID" id="btn_add" data-toggle="modal" 
												data-target="#officerSearch" href="Modal_OfficerID">...</button>
											</div>
											</fieldset>
										</div>
									</div>
 -->
								<div class="form-group">
									<div class="col-sm-4 col-md-4"></div>
									<div class="col-sm-8 col-md-8">
								
									<%
									if(action.equals("5")||action.equals("8")||action.equals("15")){
									%>
										<button type="button" class="btn btn-info" id="btn-submit-retrievemodal1" data-action="<%=action %>" onclick="getURL(this);return send_title_info()">
											<span class="glyphicon glyphicon-search"></span> Search
										</button>
									<%
										}else{
									%>
										<button type="button" class="btn btn-info" id="btn-submit-retrievemodal" data-action="<%=action %>" onclick="getDetails(this);return send_title_info()">
											<span class="glyphicon glyphicon-search"></span> Search
										</button>
									<%
										}
									%>
									<!-- 	<input type="button" name="cancel" value="Cancel" class="btn btn-info" data-dismiss="modal"/> -->
									 <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
									</div>
								</div>
							</form>
						</div>
					</div>
			</div>
			
			<div class="panel panel-default">
				<div class="panel-heading">
					<h4 class="panel-title">
						<a data-toggle="collapse" data-parent="#form_parent" href="#result_title" id="result-title">
							Result
							<i class="indicator glyphicon glyphicon-chevron-up pull-right"></i>
						</a>
					</h4>
				</div>
				<div id="result_title" class="panel-collapse collapse result_title">
					<div class="panel-body"  style="height:50%;overflow-y:scroll">
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
			  <div class="modal-content" id='officerResult'>
			  <!-- Remote content load here -->
			  </div>
		</div>
	</div>