<%@page import="com.ilmu.cataloging.Template_Maint.*,
				com.ilmu.global.Glnumb, com.ilmu.global.Handler"%>
<%@page import="java.util.List"%>
<%@ page import="java.util.*" %>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/Cataloging/BO.js"></script>	
<script type="text/javascript" src="${pageContext.request.contextPath}/js/Cataloging/Template_Maintenance.js"></script>						
<script type="text/javascript" src="${pageContext.request.contextPath}/js/general/global.js"></script>	
<style>
#tagTable td:nth-child(1) { display: none;}
</style>

<!-- Modal content-->	  
<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
		<h4 class="modal-title" id="myModalLabel">
				Add Tag
		</h4>
	</div>
	<div class="modal-body">		
								    <form role="form" class="form-horizontal" id="current_form" name="AddTplInfo" action="AddTplInfo?tplName=<%= request.getParameter("tplName") %>&seqNo=<%= request.getParameter("seqNo") %>" method="post">
				
								<div class="form-group">
									<div class="col-sm-2">
														<input type="hidden" id="status" name="status" value= "A">
													</div>
												</div>
										
										<div class="clearfix"></div>
										<div class="form-group">
											<div class="col-sm-1"></div>
											<div class="col-sm-2">
												<label>Tag:</label>
											</div>
											<div class="col-sm-6 col-md-6">
											<%			
			
											List<Tag_Handler> TagInfo = null;
											String module = request.getParameter("module");
											TagInfo = Tag_Handler.getTags(Handler.marcType(module));
											%>
												<select class="form-control tags" id="tag" name="tags" onchange="getIndicators()">
													<option value="0">Please select</option>
													<%
													for(Tag_Handler i: TagInfo){
														
													%>
													<option value="<%=i.getGL17TAG()%>" data-desc= "<%=i.getGL17DESC()%>" data-repeat="<%=i.getGL17REPEAT()%>"><%=i.getGL17TAG()%>-<%=i.getGL17DESC()%></option>
			
													<%										
														}
													%>
												</select>
										</div>
										</div>
										<div class="clearfix"></div>
										
										<div id="indicator"></div>
			
										
										<div class="clearfix"></div>
										<div id="subfList"></div>
										<div id="uiIDs">
												</div>

			
										<div class="col-sm-12 tagDiv">
										<div class="clearfix"></div>
										</div>
									</form>
			</div>

	<div class="modal-footer">
		<button type="button" class="btn btn-info" onclick="addTag('BibRcrd', this)">
			<span class="glyphicon glyphicon-save"></span> Save
		</button>
		<input type="button" name="cancel" value="Cancel" class="btn btn-info btn-cancel" data-dismiss="modal"/>
	</div>			
	
	