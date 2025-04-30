<%@page import="com.ilmu.Authority.AuthorityMaint.*"%>
<%@page import="com.ilmu.global.*"%>
<%@page import="java.util.List"%>
<%@ page import="java.util.*" %>
<!DOCTYPE html>
<html>
<head>


<script type="text/javascript" src="${pageContext.request.contextPath}/js/Authority/Template_Maintenance.js"></script>		 <!-- DataTables -->	
 <script type="text/javascript" src="<%= request.getContextPath() %>/plugin/datatables/jquery.dataTables.min.js"></script>
 <script type="text/javascript" src="<%= request.getContextPath() %>/plugin/datatables/dataTables.bootstrap.min.js"></script>
 <link rel="stylesheet" href="<%= request.getContextPath() %>/plugin/datatables/dataTables.bootstrap.css">
			
	<script>
  	$(document).ready(function () {
  		$("[title]").tooltip();
	$('#tplTable').DataTable({
	    responsive: true
	});
	
	 $("#tplTxt").click(function(){
	        $("#result_tags").removeClass("in");
	    });
	 
	 $("#tplInfoTxt").click(function(){
	        $("#search").removeClass("in");
	    });
});
	</script>
<style>

	.disabled {
   pointer-events: none;
   cursor: default;
}
.flipIn {
            -webkit-backface-visibility: visible !important;
            -webkit-animation-name: flipInY;
            backface-visibility: visible !important;
            animation-name: flipInY;
         }
.modal {
  padding: 5px;
    -webkit-border-radius: 8px;
    -moz-border-radius: 8px;
    border-radius: 8px;
}


</style>

</head>
<body>

 <!--  	<div class="panel-heading">
		<b>List Of Templates and Details</b>
	</div> -->
	
	<%
		String username =(String)session.getAttribute("screenname");
	%>
	  <div class="panel-group" id="testing" style=" position: relative;overflow:hidden;">
        <div class="panel panel-default">
            <div class="panel-heading clearfix">
            	  	<h4 class="panel-title pull-left" style="padding-top: 7.5px;"><a data-toggle="collapse" data-parent="#testing" href="#search" id="tplTxt">Templates</a></h4>
				<div class="btn-group pull-right">
					<button type="button" class="btn btn-primary" id="btn_add" data-toggle="modal" 
											data-target="#addRcrd" href="AddNewTplInfo?action=1" data-placement="bottom" title="Add Template"><span class="glyphicon glyphicon-plus"></span></button>
				</div>
            </div>
              <div id="search" class="panel-collapse collapse in">
			<div class="panel-body">
				<table class="table table-bordered" id="tplTable">
				<col width="70%">
				<col width="20%">
				<col width="10%">
					<thead>
						<tr>
							<th data-sortable="true" style="text-align: center;">Template</th>
							<th data-sortable="true" style="text-align: center;">Status</th>
							<th style="text-align: center;">Actions</th>
						</tr>
					</thead>
					 <tbody>
						<%
							List<Template_Maintenance> allTpl = null;
							List<Template_Maintenance> chkExist = null;
							List<Template_Maintenance> notchkExist = null;
							allTpl = Template_Maintenance.getAllTpl();
							chkExist = Template_Maintenance.chkExist();
							notchkExist = Template_Maintenance.notExist();
						
							for (Template_Maintenance i: allTpl) {
						%>
						<tr>
							<td><%=i.getCT15TNAME()%></td>
							<%
								if(i.getCT15STAT().equals("A")){
							%>
							<td style="text-align: center;">
								<a title="Update Template" data-toggle='modal' data-target='#updateModal' href="Modal_EditTpl?seqNo=<%=i.getCT15SEQNO()%>">
									<span class="label label-success">Active</span>
								</a>
							</td>
							<%
								}
								else{
							%>
							<td style="text-align: center;">
								<a title="Update Template" data-toggle='modal' data-target='#updateModal' href="Modal_EditTpl?seqNo=<%=i.getCT15SEQNO()%>">
									<span class="label label-warning">Inactive</span>
								</a>
							</td>
							<%
								}
							%>
							
							<%
								if(i.getCT15STAT().equals("A")){
							%>
							<td style="text-align: center;">
							<button type="button" id="<%=i.getCT15SEQNO()%>,<%=i.getCT15TNAME()%>" onclick="myFunction(this.id)" class="btn btn-primary btn-sm"><span class="glyphicon glyphicon-eye-open" title="View Template"></span></button>
								<%-- <a href="AddNewTplInfo?tplName=<%=i.getCT15TNAME()%>&seqNo=<%=i.getCT15SEQNO()%>"data-toggle='modal' data-target='#addRcrd' class="btn btn-primary btn-sm submit"><span class="glyphicon glyphicon-plus" title="Add Template"></span></a> --%>
							<%-- 	<a data-toggle='modal' data-target='#updateModal' href="Modal_EditTpl?seqNo=<%=i.getCT15SEQNO()%>" class="btn btn-info btn-sm"><span class="glyphicon glyphicon-pencil" title="Update Template"></span></a>
								<a onclick="deletePlugin(this.id)" id="<%=i.getCT15SEQNO()%>,<%=i.getCT15TNAME()%>" class="btn btn-dull btn-sm"><span class="glyphicon glyphicon-trash" title="Delete Template"></span></a> --%>
							</td> 
							<%
								}
								else{
							%>
							<td style="text-align: center;">
								<a href="AddTplInfo?tplName=<%=i.getCT15TNAME()%>&seqNo=<%=i.getCT15SEQNO()%>" data-backdrop="static" class="btn btn-primary btn-sm submit disabled"><span class="glyphicon glyphicon-eye-open" title="Update Template"></span></a>
								<%-- <a href="AddNewTplInfo?tplName=<%=i.getCT15TNAME()%>&seqNo=<%=i.getCT15SEQNO()%>"data-toggle='modal' data-target='#addRcrd' class="btn btn-primary btn-sm submit"><span class="glyphicon glyphicon-plus" title="Add Template"></span></a> --%>
							<%-- 	<a data-toggle='modal' data-target='#updateModal' href="Modal_EditTpl?seqNo=<%=i.getCT15SEQNO()%>" class="btn btn-info btn-sm"><span class="glyphicon glyphicon-pencil" title="Update Template"></span></a>
								<% 
								out.println("<a type='button' title='Delete' class='btn btn-dull btn-sm disabled' data-toggle='modal' data-target='#delete' href='jsp/include/sharedV1/Delete_Modal?tplID="+i.getCT15SEQNO()+"&errorID=008'><span class='glyphicon glyphicon-trash'></span></a>");
								%> --%>
								
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
			</div>
		</div>
        </div>
        <div class="panel panel-default">
            <div class="panel-heading clearfix">
                <h4 class="panel-title ">
                 <a data-toggle="collapse" data-parent="#testing" href="#result_tags" id="tplInfoTxt">Template Details</a>
                </h4>
                 <div class="btn-group pull-right">
					<button type="button" class="btn btn-primary" id="btn_addTplInfo" data-toggle="modal" 
					data-target="#addRcrd" data-placement="bottom" title="Add Template Details "><span class="glyphicon glyphicon-plus" ></span></button>
				</div>
            </div>
              <div id="result_tags" class="panel-collapse collapse">
					<div class="panel-body fixed-body">
						<div id="display_title"></div>
					</div>
			</div>
        </div>
    </div>


	<div id="addTpl" class="modal fade" role="dialog" data-backdrop="static">
				    <div class="modal-dialog modal-lg" style="height:900px;padding-top:10%;">
					  <div class="modal-content">
						  <!-- Remote content load here -->
						  </div>
					</div>
				</div>
					<div class="modal fade" id="delete" role="dialog" data-backdrop="static">
				    <div class="modal-dialog modal-lg" >
						  <div class="modal-content">
						  <!-- Remote content load here -->
						  </div>
					</div>
				</div>
				
			
					<!-- Modal HTML -->  
				<div class="modal fade" id="updateModal" role="dialog" data-backdrop="static">
				    <div class="modal-dialog modal-lg">
						  <div class="modal-content">
						  <!-- Remote content load here -->
						  </div>
					</div>
				</div>
			    <!-- Modal END -->
			    
			    <div class="modal fade" id="viewRcrd" role="dialog" data-backdrop="static">
				    <div class="modal-dialog modal-lg" style="height:900px;">
						  <div class="modal-content">
						  <!-- Remote content load here -->
						  </div>
					</div>
				</div>
				
						<div class="modal fade" id="updateModal1" role="dialog" data-backdrop="static">
				    <div class="modal-dialog modal-lg">
						  <div class="modal-content">
						  <!-- Remote content load here -->
						  </div>
					</div>
				</div>
				
				<div class="modal fade" id="addRcrd" role="dialog" data-backdrop="static">
				    <div class="modal-dialog modal-lg">
						  <div class="modal-content">
						  <!-- Remote content load here -->
						  </div>
					</div>
				</div>
				
	
</body>
    
</html>
            