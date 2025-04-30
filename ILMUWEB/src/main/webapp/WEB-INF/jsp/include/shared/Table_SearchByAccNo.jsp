<%@ page import="com.ilmu.cataloging.Acc_Maint.*, com.ilmu.foundation.*, java.util.List" %>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/Cataloging/Accession_Maintenance.js"></script>					
	
	<table class="table table-hover" data-toggle="table" data-pagination="true" data-search="true" data-show-columns="true" data-pagination="true" data-click-to-select="true" data-toolbar="#toolbar" id="table-creditdebit">
		<thead>
			<tr>
				<th data-sortable="true">No.</th>
				<th data-sortable="true">Accession No.</th>
				<th data-sortable="true">Title</th>
				<th data-sortable="true">Item Category</th>
				<th data-sortable="true">SMD</th>
				<th data-sortable="true">Branch</th>
				<th data-sortable="true">Location</th>
				<th data-sortable="true">Status</th>
				<th data-sortable="true">Volume</th>
				<th data-sortable="true">Copy No.</th>
				<th class="col-md-2">Action&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</th>
			</tr>
		</thead>
		<tbody>
			<% 
			String controlNoInput = request.getParameter("txtMatNo");
			String status = request.getParameter("status");
			List<CTRetrieve> searchResult = null;
			List<CTRetrieve> searchResult1 = null;
			
			searchResult = CTRetrieve.CT_SearchByMatno(controlNoInput);
			searchResult1 = CTRetrieve.CT_getTitle(controlNoInput);
			
				int id= 0;
				for(CTRetrieve i: searchResult)
				{
					for(CTRetrieve j: searchResult1)
					{
						id++;

					String deleteFunc = "deleteRecord(&quot;" +controlNoInput + "&quot;)";
					out.println("<tr>");
					out.println("<td>" + id + "</td>");
					out.println("<td>" + controlNoInput + "</td>");
					out.println("<td>" + j.getCT05SRAW() + "</td>");
					out.println("<td>" + i.getGL10DESC() + "</td>");
					out.println("<td>" + i.getGL47DESC() + "</td>");
					out.println("<td>" + i.getGL71DESC() + "</td>");
					out.println("<td>" + i.getGL05DESC() + "</td>");
					if((i.getCT03VOLUME()).equals("A")){
					out.println("<td>Available</td>");
					}
					if((i.getCT03VOLUME()).equals("F")){
						out.println("<td>Final Processing</td>");
						}
					if(i.getCT03STAT()==null || i.getCT03STAT()==" "){
					out.println("<td>" + " " +"</td>");
					}
					else{
						out.println("<td> </td>");
					}
					out.println("<td>" + i.getCT03COPYNO() + "</td>");
					out.println("<td><a type='button' title='View' class='btn btn-success' data-toggle='modal' data-target='#viewAccMaint' href='Modal_ViewAccMaint?accNo="+controlNoInput+"&controlNo="+i.getCT03MATNO()+"'><span class='glyphicon glyphicon-open-file'></span></a>");
					out.println("<a type='button' title='Update' class='btn btn-info' data-toggle='modal' data-target='#editAccMaint' href='Modal_EditAccMaint?controlNo="+i.getCT03MATNO()+"'><span class='glyphicon glyphicon-edit'></span></a>");
					out.println("<a type='button' title='Delete' class='btn btn-danger' data-toggle='modal' data-target='#delete' href='jsp/include/sharedV1/Delete_Modal.jsp?controlNo="+controlNoInput+"'><span class='glyphicon glyphicon-trash'></span></a>");
	
					}
				}
				
			%>
		</tbody>
	</table>
	
		<div class="modal fade" id="delete" role="dialog" >
				    <div class="modal-dialog" style="width:900px;">
						  <div class="modal-content">
						  <!-- Remote content load here -->
						  </div>
					</div>
				</div>
	
	
	