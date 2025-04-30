<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@ page language="java" import="java.sql.*, com.ilmu.global.*, com.ilmu.receipting.TransactionType.*,java.util.*"
%>
<head>
	<title>Accession Maintenance</title>
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/Receipting/Transaction_Type.js"></script>	
		 <script type="text/javascript" src="<%= request.getContextPath() %>/plugin/datatables/jquery.dataTables.min.js"></script>
 <script type="text/javascript" src="<%= request.getContextPath() %>/plugin/datatables/dataTables.bootstrap.min.js"></script>
  <link rel="stylesheet" href="<%= request.getContextPath() %>/plugin/datatables/dataTables.bootstrap.css">
	
	<script>
	$(document).ready(function() {
	$('#searchbyaccno').DataTable({
	    responsive: true
	});
	});
	</script>
</head>

	<body>
		<div class="panel panel-default" id="form_parent">
				<div class="panel-group">
					<div class="panel panel-default">
						<div class="panel-heading clearfix">
							<h4 class="panel-title">
							  	<button type="button" class="btn btn-primary" id="btn_add" data-toggle="modal" 
											data-target="#addAccMaint" href="Modal_AddAccMaint"><span class="glyphicon glyphicon-plus" title="Add Template"></span></button>
							 <a class="btn btn-primary" id='retrieveLink' data-toggle='modal' data-target='#titleSearch' href='RetrieveTitleModal?action=5'><span class="glyphicon glyphicon-search" style="color:white"></span></a>
														<a data-toggle="collapse" data-parent="#form_parent" href="#search">
									<i class="indicator glyphicon glyphicon-chevron-down  pull-right"></i>
								</a>
							   </h4>
						</div>
							<div id="result" class="panel-collapse collapse in">
								<form method="post" action="AddNewTpl" onsubmit="return check()">
									<div class="panel-body">
										<div id="display_result"></div>
										  <div class="row">
										    <div>
												<div style="height:90%" class="accession_list">
												
													<table class="table table-bordered" id="searchbyaccno" style="font-size:11pt">
														<thead>
															<tr>
																<!-- <th data-sortable="true">No.</th> -->
																<th data-sortable="true">Code</th>
																<th data-sortable="true">Description</th>
																<th data-sortable="true">Mode</th>
																<th data-sortable="true">Type</th>
																<th data-sortable="true">Action</th>
															</tr>
														</thead>
														<tbody>
															<%
															List <TransactionTypeMaint> searchResult = TransactionTypeMaint.ListTranscType();
															for(TransactionTypeMaint i: searchResult)
															{
															%>
															<tr>
																<td><%=i.getGL38TXCD() %></td>
																<td><%=i.getGL38DESC() %></td>
																<%
																String type =i.getGL38TYPE();
																
																if(type.equals("R")){
																	type = "Receipt";
																}else if(type.equals("D")){
																	type = "Due";
																}else if(type.equals("J")){
																	type = "Journal";
																}
																
																String mode = i.getGL38MODE();
																
																if(mode.equals("C")){
																	mode = "Credit";
																}else if(mode.equals("D")){
																	mode = "Debit";
																}
																%>
																<td><%=mode %></td>
																<td><%=type %></td>
																<td>
																	<a type='button' title='View' class='btn btn-primary' data-toggle='modal' data-target='#viewTransType' href='Modal_View?code=<%=i.getGL38TXCD()%>'><span class='glyphicon glyphicon-eye-open'></span></a>
																</td>
															</tr>
															<%
															}
															%>
														</tbody>
														</table>
														
												</div>
											</div>	
										</div>
										</div>
								   </form>
								</div>
					</div>

					<!-- <div class="panel panel-default">
						<div class="panel-heading">
							<h4 class="panel-title">
								<a data-toggle="collapse" data-parent="#form_parent"
									href="#result">Result</a>
							</h4>
							
						</div>
						<div id="result" class="panel-collapse collapse">
						<form method="post" action="AddNewTpl" onsubmit="return check()">
							<div class="panel-body">
							<div id="display_result" style="overflow:scroll;width:100%"></div>
			  <div class="row">
			    <div class="col-xs-12">
					<div id="div-result" style="overflow:scroll;width:100%;height:50%">
				
					</div>
					</div>
					
				</div>

							</div>
						   </form>
						</div>
					</div> -->

				</div>
			</div>
	
	<div class="modal lg fade" id="accModal" role= "dialog">
	<div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
          <h4 class="modal-title">Selection List</h4>
        </div><div class="container"></div>
        <div class="modal-body" id="accSlctList" >
    		
        </div>
        <div class="modal-footer">
          <a href="#" data-dismiss="modal" class="btn">Close</a>
          <a href="#" id="subfSave" class="btn btn-primary">Save changes</a>
        </div>
      </div>
    </div>
</div>


 </section>

	<!-- Bootstrap modal for selection search -->
	<div class="modal fade" id="viewTransType" tabindex="-1" role="dialog" aria-labelledby="selectionSearch"  data-backdrop="static">
	    <div class="modal-dialog" role="document">
			  <div class="modal-content">
			  <!-- Remote content load here -->
			  </div>
		</div>
	</div>
    <!-- Modal END -->
    
    <!-- Bootstrap modal for control no search -->
	<div class="modal fade" id="ctrlSearch" tabindex="-1" role="dialog" aria-labelledby="ctrlSearch"  data-backdrop="static">
	    <div class="modal-dialog" role="document">
			  <div class="modal-content">
			  <!-- Remote content load here -->
			  </div>
		</div>
	</div>
    <!-- Modal END -->
    
 
	<!-- Bootstrap modal for control no search -->
	<div class="modal fade" id="tagSearch" tabindex="-1" role="dialog" aria-labelledby="tagSearch"  data-backdrop="static">
	    <div class="modal-dialog" role="document">
			  <div class="modal-content">
			  <!-- Remote content load here -->
			  </div>
		</div>
	</div>
    <!-- Modal END -->
    

      
      	<div class="modal fade" id="MatNoSearch" tabindex="-1" role="dialog" aria-labelledby="MatNoSearch"  data-backdrop="static">
	    <div class="modal-dialog" role="document">
			  <div class="modal-content">
			  <!-- Remote content load here -->
			  </div>
		</div>
	</div>
	
	
	<!-- Modal HTML -->  
				<div class="modal fade" id="updateModal" role="dialog"  data-backdrop="static">
				    <div class="modal-dialog" style="height:360px;">
						  <div class="modal-content">
						  <!-- Remote content load here -->
						  </div>
					</div>
				</div>
			    <!-- Modal END -->
			    
			 <!--    		Modal HTML View Accession Maint  
				<div class="modal fade" id="viewAccMaintenance" role="dialog"  data-backdrop="static">
				    <div class="modal-dialog" style="width:1000px;height:900px;">
						  <div class="modal-content">
						  Remote content load here
						  </div>
					</div>
				</div>
			    Modal END
			    
				<div class="modal fade" id="editAccMaintenance"  data-backdrop="static">
		<div class="modal-dialog" role="document" style="width:1000px;">
			<div class="modal-content">
			Remote content load here
			</div>
		</div>
	</div>	      
			    --> 		<!-- Modal HTML Add Accession Maint-->  
				<div class="modal fade" id="addAccMaint" role="dialog"  data-backdrop="static">
				    <div class="modal-dialog modal-lg" style="height:360px;">
						  <div class="modal-content">
						  <!-- Remote content load here -->
						  </div>
					</div>
				</div>
			    <!-- Modal END -->
				<!-- Modal HTML Add Accession Maint-->
				
					<!-- Bootstrap modal for vendor search -->
	<div class="modal fade" id="vendorSearch"  data-backdrop="static">
	    <div class="modal-dialog" role="document">
			  <div class="modal-content">
			  <!-- Remote content load here -->
			  </div>
		</div>
	</div>
    <!-- Modal END -->

	    <!-- Modal HTML for vendor search-->  
				<div class="modal fade" id="vendorModal" role="dialog"  data-backdrop="static">
				    <div class="modal-dialog">
						  <div class="modal-content">
						  <!-- Remote content load here -->
						  </div>
					</div>
				</div>
				
	<div class="modal fade" id="titleSearch" tabindex="-1" role="dialog" aria-labelledby="titleSearch"  data-backdrop="static">
		<div class="modal-dialog modal-lg" role="document">
			<div class="modal-content">
			<!-- Remote content load here -->
			</div>
		</div>
	</div>	
			
		<div class="modal fade" id="delete" role="dialog" >
				    <div class="modal-dialog" style="width:900px;">
						  <div class="modal-content">
						  <!-- Remote content load here -->
						  </div>
					</div>
				</div>
				
							<!-- Modal HTML View Accession Maint-->  
				<div class="modal fade" id="viewAccMaintenance" role="dialog"  data-backdrop="static">
				    <div class="modal-dialog modal-lg" style="height:900px;">
						  <div class="modal-content">
						  <!-- Remote content load here -->
						  </div>
					</div>
				</div>
			    <!-- Modal END -->
			    
			    	<!-- Modal HTML View Accession Maint-->  
				<div class="modal fade" id="editAccMaintenance" role="dialog"  data-backdrop="static">
				    <div class="modal-dialog modal-lg" style="height:900px;">
						  <div class="modal-content">
						  <!-- Remote content load here -->
						  </div>
					</div>
				</div>
</body>
</html>