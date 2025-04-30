<%@page import="com.ilmu.cataloging.Template_Maint.*,com.ilmu.cataloging.Bibliography.*,
				com.ilmu.global.Glnumb, com.ilmu.global.Handler"%>
<%@page import="java.util.List"%>
<%@ page import="java.util.*" %>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/Cataloging/Template_Maintenance.js"></script>					
<script type="text/javascript" src="${pageContext.request.contextPath}/js/general/global.js"></script>	


<style>
#tagTable td:nth-child(1) { display: none;}
.panel-group .panel {
  overflow: visible;
}
</style>

<%
String action = request.getParameter("action");
String module = request.getParameter("module");
System.out.println("Action" + module + action);
String marc = Handler.marcType(module);
System.out.println("Action" + marc);

%>
<!-- Modal content-->	  
<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
		<h4 class="modal-title" id="myModalLabel">
			<%
				if(action.equals("1")||action.equals("2")){
			%>
				Add Template And Information Maintenance
			<%
				}else{
			%>
				Add Tag
			<%
				}
			%>
		</h4>
	</div>
	<div class="modal-body">		
		<div class="panel-group">
					<div class="panel panel-default">
						<div class="panel-heading" style="height:5%">
							<h4 class="panel-title">
								<a data-toggle="collapse" data-parent="#form_parent"
									href="#searchForm"><i class="indicator glyphicon glyphicon-chevron-down  pull-right"></i>
								</a>
							</h4>
						</div>
						<div id="searchForm" class="panel-collapse collapse in">
							<div class="panel-body"  style="height:50%;overflow-y:visible;">
									
								    <form role="form" class="form-horizontal" id="current_form" name="AddTplInfo" action="AddTplInfo?tplName=<%= request.getParameter("tplName") %>&seqNo=<%= request.getParameter("seqNo") %>" method="post">
										<%
											int seqno = 0;
											if(action.equals("1")){
										%>
										<input type="hidden" class="action" value="addAction">
											<div class="form-group">
										<div class="col-sm-1"></div>
									<div class="col-sm-2 col-md-2">
										<label>Template Name:</label>
									</div>
									<div class="col-sm-6 col-md-6">
										<%
											Glnumb i = Glnumb.getGL98VALUE("CTMSTRSEQNO");
											seqno = i.getGL98VALUE();
										%>
										<input type="hidden" id="tplId" value="<%=i.getGL98VALUE()%>">
								
										<input type="text" class="form-control" name="tplName" id="tplname">
									</div>
								</div>
								<%
											}
								%>
										<%
											if(action.equals("2")){
												System.out.println("ssss" + request.getParameter("tplName") + Integer.parseInt(request.getParameter("seqNo")));
												String tplName = request.getParameter("tplName");
												int seqNo = Integer.parseInt(request.getParameter("seqNo"));
												
										
										%>
										<input type="hidden" class="action" value="upAction">
											<div class="form-group">
												<div class="col-sm-1"></div>
												<div class="col-sm-2 col-md-2">
													<label>Template Name:</label>
												</div>
												<div class="col-sm-6 col-md-6 tplName">
													<%=tplName %>
													<input type="hidden" id="tplId" value="<%=seqNo%>">
														<input type="hidden" class="form-control" name="tplName" id="tplname" value="<%=tplName %>">
													
												</div>
											</div>
											<%
														}if(!action.equals("templateTag")){
											%>
									<div class="form-group">
									<div class="col-sm-2">
														<input type="hidden" id="status" name="status" value= "A">
													</div>
												</div>
											<div class="form-group">
											<div class="col-sm-1"></div>
											<div class="col-sm-2">
												<label>Type:</label>
											</div>
											<div class="col-sm-6 col-md-6">
											<input type="hidden" id="marc" value="<%=marc%>">
											<select class="form-control type" id="type" name="type">
											<%
												List<Tag_Handler> type = Tag_Handler.getBibType(module);
												for(Tag_Handler i: type){	
											%>
												<option value="<%=i.getCODE()%>" ><%=i.getDESC()%></option>
			
											<%
												}
											%>
											</select>
											
										</div>
										</div>
										<%
														}
										%>
										<div class="clearfix"></div>
										<div class="form-group">
											<div class="col-sm-1"></div>
											<div class="col-sm-2">
												<label>Tag:</label>
											</div>
											<div class="col-sm-6 col-md-6">
											<%
											if(action.equals("templateTag")||action.equals("2")){
												String GL17TAG = request.getParameter("GL17TAG");
												String[] splitTag = GL17TAG.split(",");
												
												List<Tag_Handler> TagInfo = null;
												TagInfo = Tag_Handler.getTags(marc);
											%>
												<select class="form-control tags" id="tag" name="tags" onchange="getIndicators()">
													<option value="0">Please select</option>
													<%
													List<String> DefaultTag =Template_Maintenance.getDefault(marc);
													for(Tag_Handler i: TagInfo){
														
														String defaultRecord = "";
														if(DefaultTag!=null){
															System.out.println("DefaultTag" + DefaultTag);
															if(DefaultTag.contains(i.getGL17TAG())){
																defaultRecord = Template_Maintenance.getDefaultData(i.getGL17TAG());
																
																if(defaultRecord==null && defaultRecord.contains("NULL")){
																	defaultRecord = "";																	
																}
															/* 	String data = "";
																if(!defaultRecord.trim().isEmpty()&& !defaultRecord.trim().contains("NULL") && defaultRecord.trim().contains("|")){
																	data = defaultRecord.substring(defaultRecord.indexOf("|"));
																}else{
																	data
																} */
															}
														}
													/* 	String defaultRecord = Template_Maintenance.getDefaultData(i.getGL17TAG());
														
														System.out.println("123" + defaultRecord);
														
														if (defaultRecord==null){
															defaultRecord = "";
														} */
													/* 	if(!defaultRecord.trim().isEmpty()&& !defaultRecord.trim().contains("NULL") && defaultRecord.trim().contains("|")){
															defaultRecord = defaultRecord;
														}else{
															defaultRecord = "";
														} */
														
													%>
													<option 
													<%
													if ((i.getGL17REPEAT()).equals("N")){
													
														for(int j = 0; j < splitTag.length; j++)
														{
															//String splitData = splitTag[j].substring(0,1);
															//System.out.println(splitData);
															if((i.getGL17TAG()).equals(splitTag[j])){
																out.println("disabled=disabled ");
															}
														} 
													}
													%>
													value="<%=i.getGL17TAG()%>" id="<%=i.getGL17TAG()%>" data-desc= "<%=i.getGL17DESC()%>" 
													data-auth="<%=i.getGL17AUTH()%>" data-repeat="<%=i.getGL17REPEAT()%>" 
													data-manda= "<%=i.getGL17MANDA()%>" data-default="<%=defaultRecord%>"><%=i.getGL17TAG()%>-<%=i.getGL17DESC()%></option>
			
													<%										
														}
													%>
												</select>
											
											<%
											}else{			
			
											List<Tag_Handler> TagInfo = null;
											TagInfo = Tag_Handler.getTags(marc);
											%>
												<select class="form-control tags" id="tag" name="tags" onchange="getIndicators()">
													<option value="0">Please select</option>
													<%
													for(Tag_Handler i: TagInfo){
														
													%>
													<option value="<%=i.getGL17TAG()%>" id="<%=i.getGL17TAG()%>" data-desc= "<%=i.getGL17DESC()%>" data-repeat="<%=i.getGL17REPEAT()%>"><%=i.getGL17TAG()%>-<%=i.getGL17DESC()%></option>
			
													<%										
														}
													%>
												</select>
											<%
											}
											%>
										</div>
										</div>
										<div class="clearfix"></div>
										
										<div id="indicator"></div>
			
										
										<div class="clearfix"></div>
										<div id="subfList"></div>
										<div id="uiIDs">
												</div>
											<div class="form-group">
											<div class="col-sm-1"></div>
											<div class="col-sm-2"></div>
											<div class="col-sm-6">
										<div id="subfmessage">	
											<%
											if(action.equals("1")||action.equals("2")){
										%>
										<button type="button" class="btn btn-primary appendTag" disabled>
											<span class="glyphicon glyphicon-plus" title="Add New Tag"></span>
										</button>
										<%}%>
									</div>
																		
												</div>
												</div>
			
										<div class="col-sm-12 tagDiv">
										<div class="clearfix"></div>
										</div>
									</form>
							</div>
						</div>
					</div>
					 <%if(action.equals("1")||action.equals("2")){%>
					<div class="panel panel-default">
					    <div class="panel-heading">
					      <h4 class="panel-title">
					        <a class="accordion-toggle" data-toggle="collapse" data-parent="#accordion" href="#collapseOne">
								List of Template Details 
								<i class="indicator glyphicon glyphicon-chevron-up  pull-right"></i>
					        </a>
					      </h4>
					    </div>
					   
					    <div id="collapseOne" class="panel-collapse collapse in">
					      <div class="panel-body" style="overflow-y: scroll; height:300px;">
					      	<table id="tagTable" class="table table-fixed tagTable">
								<thead>
									<tr id="tableRow">
										<th style="display:none"></th>
										<th>Tag</th>
										<th>Indicator 1</th>
										<th>Indicator 2</th>
										<th>Subfields</th>
										<th>Actions</th>
									</tr>
								</thead>
								<tbody  id="tablebody">
								
								<%
									if(action.equals("1")){ 
										List<Template_Maintenance> taglist = null;
										taglist = Template_Maintenance.getMandaTagDetails(marc);
										for(Template_Maintenance i: taglist)
										{System.out.println("Default" + i.getGL17DEFAULT());
											if((!i.getGL17DEFAULT().trim().isEmpty())){
											String defaults = i.getGL17DEFAULT();
											
											String indi1 = defaults.substring(0,1);
											String indi2 = defaults.substring(1,2);
											String subf = defaults.substring(2);
											System.out.println("Default" + defaults);
								%>
									<tr>
										<td style="display:none"><%=seqno%></td>
										<td class='tag'><%=i.getGL17TAG()%></td>
										<td class='indi1'><%=indi1 %></td>
										<td class='indi2'><%=indi2 %></td>
										<td class='subfields'><%=subf %></td>
										<td><button class="btn btn-dull" onclick="deleteRow(this)"><span class="glyphicon glyphicon-trash" id="delPOIbutton"></span></button></td>
										<%-- <%
											if((i.getGL17MANDA()).equals("Y")){
										%>
											<td><button class="btn btn-dull" title="Delete" onclick="deleteRow(this)" disabled><span class="glyphicon glyphicon-trash" id="delPOIbutton"></span></button></td>
										<%
											}else{
										%>
										<td><button class="btn btn-dull" title="Delete" onclick="deleteRow(this)"><span class="glyphicon glyphicon-trash" id="delPOIbutton"></span></button></td>
										<%
											}
										%> --%>
									</tr>
									
								<%}}} %>
								<%
									if(action.equals("2")){ 
										System.out.println("NEW");
										String tplName = request.getParameter("tplName");
										int seqNo = Integer.parseInt(request.getParameter("seqNo"));
										List<BO_Record> BORecord = null;
										BORecord = BO_Record.getBOList(seqNo, marc);
										for(BO_Record i: BORecord)
										{
								%>
									<tr>
										<td style="display:none"><%=seqNo %></td>
										<td class='tag'><%=i.getTAG()%></td>
										<td class='indi1'><%=i.getINDI1()%></td>
										<td class='indi2'><%=i.getINDI2()%></td>
										<td class='subfields'><%=i.getCTTPLSUBF()%> </td>
										<%
											if((i.getGL17MANDA()).equals("Y")){
										%>
											<td><button class="btn btn-dull" title="Delete" onclick="deleteRow(this)" disabled><span class="glyphicon glyphicon-trash" id="delPOIbutton"></span></button></td>
										<%
											}else{
										%>
										<td><button class="btn btn-dull" title="Delete" onclick="deleteRow(this)"><span class="glyphicon glyphicon-trash" id="delPOIbutton"></span></button></td>
										<%
											}
										%>
									</tr>
									
								<%}} %>
								</tbody>
								</table>
						</div>
					</div>
			</div>
			</div>
			</div>

	<div class="modal-footer">
	<%
		if(action.equals("2")){ 
	%>
		<button type="button" class="btn btn-info addTplInfo" id="addTplInfo" disabled>
			Save
		</button>
	<%
		}else{
	%>
		<button type="button" class="btn btn-info addTplInfo" id="addTplInfo" disabled>
			Save
		</button>
	<%
		}
	%>
		<input type="button" name="cancel" value="Cancel" id="btn-cancel" class="btn btn-info btn-cancel cancels" data-dismiss="modal"/>
	</div>		
	<%}else{ %>
	<div class="modal-footer">
		<button type="button" class="btn btn-info" onclick="addTag('BibRcrd', '<%=request.getParameter("ctmstr")%>')">
			Save
		</button>
		<input type="button" name="cancel" value="Cancel" class="btn btn-info btn-cancel" data-dismiss="modal"/>
	</div>
	<%} %>
	
	