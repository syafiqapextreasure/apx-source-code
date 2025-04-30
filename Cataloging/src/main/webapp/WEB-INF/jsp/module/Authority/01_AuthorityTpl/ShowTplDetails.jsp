<%@page import="com.ilmu.cataloging.Template_Maint.*"%>
<%@page import="java.util.List"%>
<%@ page import="java.util.*,com.ilmu.global.Handler" %>


<script type="text/javascript" src="${pageContext.request.contextPath}/js/Cataloging/Template_Maintenance.js"></script>					
	<script>
	$('#showtpldetails').DataTable({
	    responsive: true,
	    "order": [[ 2, "asc" ]]

	});
	</script>
<style>
/* .table-fixed thead {
  width: 100%;
}
.table-fixed tbody {
  height: 230px;
  overflow-y: auto;
  width: 100%;
}
 */

</style>

<% 
	String tplName = request.getParameter("tplName");
	String module = request.getParameter("module");	

	int seqNo = Integer.parseInt(request.getParameter("seqNo"));
	List<Template_Maintenance> searchResult = Template_Maintenance.DisplayByTplID(seqNo); 
		
%>
	<div style="text-align: center;">
		<h2><%= request.getParameter("tplName") %></h2>
		<input type="hidden" class="tplname" value="<%= request.getParameter("tplName") %>">
	</div>
	<table class="table table-fixed table-bordered table-condensed" id="showtpldetails">
	
									<thead>
										<tr>
											<td colspan="6" style="text-align:center;font-size:18pt">
												<input type="hidden" name="tplName" value="<%= request.getParameter("tplName") %>">
											</td>
										</tr>
										<tr>
											<th style="display:none"></th>
											<th style="display:none"></th>
											<th data-sortable="true">Tags</th>
											<th data-sortable="true">Description</th>
											<th data-sortable="true">Indicator 1</th>
											<th data-sortable="true">Indicator 2</th>
											<th data-sortable="true">Subfields</th>
											<th>Actions</th>
											</tr>
									</thead>
									<tbody>
								 <%
										
							for(Template_Maintenance e: searchResult)
							{
								 %>
										<tr>
																					
											<!--Template ID-->
											<td style="display:none"><%=e.getCTTPLID()%></td>
												
											<!--End of template ID-->
													
											<!--Template name-->
													
											<td style="display:none"><%=e.getCT15TNAME()%></td>
													
											<!--End of template name-->
													
											<!--Tags-->
											<%
												System.out.println("ShowTemplate" + e.getCTTPLTAG());
											%>
											<td class="GL17TAG"><%=e.getCTTPLTAG()%></td>
													
											<!--End of tags-->
													
											<!--Tag name-->
													
											<td><%=e.getGL17DESC()%></td>
													
											<!--End of tag name-->
													
											<!--Indicator 1-->
													
									      <td><%=e.getCTTPLINDI1()%></td>
													
											<!--End of indicator 1-->
													
											<!--Indicator 1-->
													
											<td><%=e.getCTTPLINDI2()%></td>  
													
											<!--End of indicator 1-->
													
											<!--Subfields-->
													
										<td>
												<table>
												<tr>
													<td>
														<%
														String[] splitSubf = e.getCTTPLSUBF().split("\\|");
														StringBuilder result = new StringBuilder();
														for(int i = 1; i < splitSubf.length; i++)
														{
															String splitData = splitSubf[i].substring(0,1);
															String rawData = splitSubf[i].substring(1);
															Subfield_Handler subfDesc = null;
															subfDesc = Subfield_Handler.getSubfDetails(splitData, e.getCTTPLTAG(), Handler.marcType(module));
														%>
														<input type="hidden" name="subfields" value="<%=splitData%>-<%=subfDesc.getGL23DESC()%>">
														<%=splitData%> - <%=subfDesc.getGL23DESC()%><br>
														<%
														}
														%>
													</td>
												</tr>
											
												</table>
											</td>
													
													<!--End of subfields-->
													
											<%
											if(e.getGL17MANDA().equals("Y")){
											//if((e.getCTTPLTAG()).equals("000")||(e.getCTTPLTAG()).equals("008")||(e.getCTTPLTAG()).equals("245")){
											%>	
											<td>
												<a data-toggle='modal' data-target='#updateModal1' href="Modal_EditTplInfo?updateId=<%=String.valueOf(e.getCTTPLID())%>&seqNo=<%= request.getParameter("seqNo") %>&tag=<%= e.getCTTPLTAG() %>"  class="btn btn-info btn-sm"><span class="glyphicon glyphicon-pencil" title="Update Template"></span></a>
								            	<button onclick="deleteTplPlugin(this.id)" id="<%=String.valueOf(e.getCTTPLID())%>,<%=e.getCTTPLN()%>,<%=e.getCTTPLTAG() %> " class="btn btn-dull btn-sm" disabled><span class="glyphicon glyphicon-trash" title="Delete Template Details"></span></button>
											</td>
											<%
											}
								 			else{
											%>
													
											<td>
								                <a data-toggle='modal' data-target='#updateModal1' href="Modal_EditTplInfo?updateId=<%=String.valueOf(e.getCTTPLID())%>&seqNo=<%= request.getParameter("seqNo") %>&tag=<%= e.getCTTPLTAG() %>"  class="btn btn-info btn-sm"><span class="glyphicon glyphicon-pencil" title="Update Template"></span></a>
								                <a onclick="deleteTplPlugin(this.id)" id="<%=String.valueOf(e.getCTTPLID())%>,<%=Integer.parseInt(request.getParameter("seqNo"))%>,<%=e.getCTTPLTAG() %>" class="btn btn-dull btn-sm"><span class="glyphicon glyphicon-trash" title="Delete Template Details"></span></a>
											 </td>
											<%
								 			}
											%>
											
												</tr>
										
													<%            
													}
												%>


											</tbody>
						</table>