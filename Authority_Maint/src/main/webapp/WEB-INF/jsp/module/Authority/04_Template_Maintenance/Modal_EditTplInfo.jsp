<%@page import="com.ilmu.cataloging.Template_Maint.*"%>
<%@page import="java.util.List"%>
<%@ page import="java.util.*" %>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/Cataloging/Template_Maintenance.js"></script>		

<script type="text/javascript" src="${pageContext.request.contextPath}/plugin/bootstrap-multiselect/js/bootstrap-

multiselect.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/plugin/bootstrap-multiselect/css/bootstrap-multiselect.css" type="text/css"/>
	<style>
#tagTable td:nth-child(1) { display: none;}
.panel-group .panel {
  overflow: visible;
}
</style>		
 

<script>
$('.multiselect').multiselect({
	includeSelectAllOption: true,
	 maxHeight: 300  
});


</script>
<!-- Modal content-->	  
<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
		<h4 class="modal-title" id="myModalLabel">Update Template Information Maintenance</h4>
	</div>
	<div class="modal-body">
		<div class="panel panel-default" id="form_parent">
		
		<div class="panel-group" >
					<div class="panel panel-default" >
						<div class="panel-heading">
							<h4 class="panel-title">
								<a data-toggle="collapse" data-parent="#form_parent"
									href="#search">Form</a>
							</h4>
						</div>
						<div id="search" class="panel-collapse collapse in">
						 <%
							int id = Integer.parseInt(request.getParameter("updateId"));
						 System.out.println(id);
						 	Template_Maintenance i = Template_Maintenance.getTplInfo(id);
					  		
					   %>
							
							<div class="panel-body" style="height:50%;overflow-y:visible;">
								
							<div class="form-group">
								<div class="col-sm-1"></div>
								<div class="col-sm-2 col-md-2">
									<label>Template:</label>
								</div>
								<div class="col-sm-2 col-md-2">
									<input type="hidden" name="seqNo" id="seqNo" value="<%= request.getParameter("seqNo") %>">
									<input type="hidden" name="empId" id="empId" value="<%=i.getCTTPLID()%>"/>
									<input type="hidden" name="empName" id="tplName" class="inp-form" value="<%=i.getGL18DESC1()%>"/> 
								<label>
										<%=i.getGL18DESC1()%>
									</label> 
								</div>
							</div>
							<div class="clearfix"></div>
							<div class="form-group"><div class="col-sm-1"></div></div>
							<div class="form-group">
								<div class="col-sm-1"></div>
								<div class="col-sm-2">
									<label>Tag:</label>
								</div>
								<%			
									List<Tag_Handler> TagInfo = null;
									TagInfo = Tag_Handler.getTags();
								%>
								<div class="col-sm-6 col-md-6 tag">
								<input type="hidden" name="empName" id="name" class="inp-form" value="<%=i.getCTTPLTAG()%>"/> 
								<%=i.getCTTPLTAG()%>
								<span id="error" name="error"></span>
									<div id="message"></div>
									<input type="hidden" name="rpt" id="rpt">
									<input type="hidden" name="phone" id="txt0">
									<input class="catalogs" type="hidden" id="txtt0" name="txtt" size="50"/>
								</div>
							</div>
							
							<div class="clearfix"></div>
							<div class="form-group"><div class="col-sm-1"></div></div>
								<div class="form-group" id="indiAll">
								<div class="col-sm-1"></div>
								<div class="col-sm-2">
									<label>Indicators:</label>
								</div>
								<%	
									String GL18TAG = request.getParameter("tag");
									List<Indicator_Handler> IndiInfo1 = null;
									IndiInfo1 = Indicator_Handler.getIndi1(GL18TAG);
								%>
								<div class="col-sm-3 col-md-3">
								<select class="form-control updatefields" id="updateIndi1" name="tags" onchange="loadXMLDoc();addVal()">
								
											<%
												for(Indicator_Handler j: IndiInfo1){
													if((i.getCTTPLINDI1()).equals(j.getGL18INDI())){
											%>
												<option value="<%=j.getGL18INDI()%>" data-indilvl="<%=j.getGL18INDILVL()%>" selected><%=j.getGL18INDI()%>-<%=j.getGL18DESC()%></option>
											<%
													}else{
											%>
										<option value="<%=j.getGL18INDI()%>" data-indilvl="<%=j.getGL18INDILVL()%>"><%=j.getGL18INDI()%>-<%=j.getGL18DESC()%></option>
							
											<%					
													}
					
												}
											%>
								</select>
								</div>
								<div class="col-sm-3 col-md-3">
								<select class="form-control updatefields" id="updateIndi2" name="tags" onchange="loadXMLDoc();addVal()">
									<%	
										List<Indicator_Handler> IndiInfo2 = null;
										IndiInfo2 = Indicator_Handler.getIndi2(GL18TAG);
										%>
											<%
												for(Indicator_Handler j:IndiInfo2){
													if((i.getCTTPLINDI2()).equals(j.getGL18INDI())){
											%>
												<option value="<%=j.getGL18INDI()%>" data-indilvl="<%=j.getGL18INDILVL()%>" selected><%=j.getGL18INDI()%>-<%=j.getGL18DESC()%></option>
											<%
													}else{
											%>
											<option value="<%=j.getGL18INDI()%>" data-indilvl="<%=j.getGL18INDILVL()%>"><%=j.getGL18INDI()%></option>
							
											<%					
													}
					
												}
											%>
								</select>
								</div>
							</div>
							
							
							<div id="indiList"></div>

							
							<div class="clearfix"></div>
							<div class="form-group"><div class="col-sm-1"></div></div>
														
								<div class="form-group" id="subfAll">
								<div class="col-sm-1"></div>
								<div class="col-sm-2">
									<label>Subfields:</label>
								</div>
								<div class="col-sm-3 col-md-3">
								<input type="hidden" value="<%=i.getCTTPLSUBF()%>" id="retrieveSubfields">
								<select class="form-control multiselect updatefields" id="updatesubfields1" name="tags"  multiple='multiple'>
									<%-- <option selected="selected" value="	<

%=i.getCTTPLSUBF()%>">	<%=i.getCTTPLSUBF()%></option> --%>
									<%
										List<Subfield_Handler> subfList = null;
										subfList = Subfield_Handler.getListOfSubf(GL18TAG);
										for(Subfield_Handler j: subfList){
									%>
										<option value='|<%=j.getGL23SUBF

()%>'data-id='<%=j.getGL23REPEAT()%>'><%=j.getGL23SUBF()%>-<%=j.getGL23DESC()%></option>
									<%}%>
								</select>
							<script type="text/javascript">
				
							 		var subfields = document.getElementById ("retrieveSubfields").value;
									var splits = subfields.split("|");
									var strings = subfields;
									var tester = [];
									for(i = 1; i < splits.length; i++){
										strings = splits[i].substring(0,1);
										  

										$('#updatesubfields1').multiselect('select', "|" + strings, true);
										}			
									
							
							</script>
								</div>
							</div>
							
							<div id="subfList"></div>
							<div class="clearfix"></div>
							<div class="col-sm-12"></div>
							<div class="clearfix"></div>
							</div>
						</div>
					</div>


				</div>
	</div>
	</div>
	<div class="modal-footer">
		<button type="button" class="btn btn-info" id="updateTplInfo" data-dismiss="modal">
			Save
		</button>
		<input type="button" name="cancel" value="Cancel" class="btn btn-info btn-cancel" data-dismiss="modal"/>
	</div>			
	
	