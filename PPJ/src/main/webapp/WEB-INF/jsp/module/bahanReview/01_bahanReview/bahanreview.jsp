<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.sql.*" %>
<%-- <%@page import="com.ilmu.foundation.global.GlobalSQLStatement"%>
<%@page import="com.ilmu.foundation.global.Foundation"%> --%>
<%-- <%@page import="com.ilmu.foundation.PatronDetails.RetrievePatron"%> --%>
<%-- <%@ page import=" java.util.List, com.ilmu.foundation.PatronDetails.PatCriteria" %> --%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head> 

	<script type="text/javascript" src="${pageContext.request.contextPath}/js/bahanreview.js"></script>

<style>
	     #modalreviewitem {
	
		    overflow-y: scroll;
		 }
     </style>
	
	
</head>

<body>
	<!-- START MAIN CONTENT -->
			<div class="box box-default">
				<div class="panel panel-default">
					<div class="panel-heading">
						<!-- <div class="clearfix"></div> -->
					</div>

					<div class="panel-body" id="display_patronReview">
					
						<form class="form-horizontal" name="bahanReview" id="PatrReview">
						

							
							<%-- <div class="form-group">
								<div class="col-sm-2">
									<label class="form-check-label">Choose Status</label>
								</div>
			
								<jsp:include page="../../../include/shared/Selection/Radio_StatusReview.jsp"></jsp:include>	
							</div>--%>
							
							
							<div class="form-group">
								<div class="col-sm-2"><label>Choose Branch</label></div>
									<div class='col-sm-5'>
										<select class="form-control" id="branch" name="branch">
											<jsp:include page="../../../include/shared/Selection/Select_Branch.jsp"></jsp:include>
											
										</select>
								</div>
							</div>  
							
							
							<div class="btn-group pull-right ">	
								<div class="col-md-1"><button type="button" id="Reterive" class="btn btn-primary" title="Retrieve"> Retrieve</button></div>
							</div>
							
						</form>
						
						<br><br>
						<table id="reviewAccnoTable" class="table table-bordered table-striped display compact" width="100%">
						 <thead>
						 	<tr>
						 		<th width="5%">No</th>
						 		<th width="10%">User Report No</th>
						 		<th width="10%">Acc No</th>
						 		<th width="25%">Title</th>
						 		<th width="10%">Patron Id</th>
								<th width="10%">Patron Name</th>
								<th width="10%">Date Report</th>
								<th width="10%">Time Report</th>
								<!-- <th>Status</th> -->
								<th width="10%">Action</th>
						 	</tr>
						 </thead>
						</table>

					</div>
				</div>
			</div>
		<!-- END MAIN CONTENT -->
		
		
		
		
		<div class="modal fade" id="modalreviewitem" tabindex="-1" role="dialog" aria-labelledby="ModalAdd" aria-hidden="true" data-keyboard="false" data-backdrop="static">
			<div class="modal-dialog" style="width: 70%;">
				<div class="modal-content">
					<jsp:include page="Modal_ReviewBahanRosak.jsp"></jsp:include> 
				</div>
			</div>
		</div>
		

		
		
</body>
</html>