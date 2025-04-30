<%@page import="com.ilmu.cataloging.Template_Maint.*,com.ilmu.cataloging.Bibliography.*,
				com.ilmu.global.Glnumb"%>
<%@page import="java.util.List"%>
<%@ page import="java.util.*" %>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/Cataloging/Template_Maintenance.js"></script>					
<style>
.modal-body {
    max-height: 100%;
    overflow-y: auto;
}

</style>
<!-- Modal content-->	  
<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
		<h4 class="modal-title" id="myModalLabel">Edit Template Maintenance</h4>
	</div>
	<div class="modal-body">
			  <%
								int seqNo = Integer.parseInt(request.getParameter("seqNo"));
			  					String module = request.getParameter("module");
								Template_Maintenance i = Template_Maintenance.getTplByID(seqNo, module);
						   %>
									
						<form role="form" class="form-horizontal" id="current_form" method="post">
							<div class="form-group">

								<div class="col-sm-2 col-md-2">
									<label>Template Name:</label>
								</div>
								<div class="col-sm-5 col-md-5">
									<input type="hidden" name="tplid" id="CT15SEQNO" value="<%=i.getCT15SEQNO()%>"/>
									<input type="hidden" name="tpl" id="hiddenTpl" value="<%=i.getCT15TNAME()%>"/>
									<input type="text" name="tplName" id="CT15TNAME" class="form-control" value="<%=i.getCT15TNAME()%>"/>
								</div>

							</div>

							<div class="clearfix"></div>
							<div class="form-group">
								<div class="col-sm-2">
									<label>Type:</label>
								</div>
								<div class="col-sm-5 col-md-5">
									<select class="form-control tags" id="type" name="type">
									<%
										int seqNos = Integer.parseInt(request.getParameter("seqNo"));
										String selected =  BO_Record.getType(seqNos);
										List<Tag_Handler> type = Tag_Handler.getBibType(module);
										for(Tag_Handler j: type){
											
											System.out.println("Code" + j.getCODE());
									%>
										<option value="<%=j.getCODE()%>" id="type" <%if(selected.equals(j.getCODE())){%>selected<%} %>><%=j.getDESC()%></option>
			
									<%
										}
									%>
									</select>
								</div>
							</div>
							<div class="form-group">
								<div class="col-sm-2">
									<label>Status :</label>
								</div>
								<div class="col-sm-2">
									<label class="radio-inline"> 
										<input type="radio" class="active" id="CT15STAT" name="status" value="A" <% if(i.getCT15STAT().equals("A")){out.println("checked");} %>>Active
									</label>
								</div>
								<div class="col-sm-2">
									<label class="radio-inline"> 
										<input type="radio" id="CT15STAT" class="inactive" name="status" value="I" <% if(i.getCT15STAT().equals("I")){out.println("checked");} %>>Inactive
									</label>
								</div>
								
								
							</div>
							<div class="col-sm-12"></div>
							<div class="clearfix"></div>
					
						</form>
			
		</div>
	
	
	<div class="modal-footer">
		<input type="button" value="Save" class="btn btn-info" id="btn-update" data-dismiss="modal"/>
		<input type="button" name="cancel" value="Cancel" id="btn-cancel" class="btn btn-info" data-dismiss="modal"/>
	</div>	
	
	