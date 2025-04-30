<%@ page contentType="text/html; charset=UTF-8" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Request Control</title>
<%String pathWebcontent=request.getContextPath();%> 

  <script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.18.1/moment.min.js"></script>
<script type="text/javascript" src="<%= request.getContextPath() %>/js/Acquisition/Maintenance/AAAM0850.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/general/delete.js"></script>
<style>
.mandatory{
		color:red;
	}
	/*.modal { overflow: auto !important; }*/
</style>
</head>
<body>
	<!-- START MAIN CONTENT -->
			<div class="box box-default">
				<div class="panel panel-default">
					<div class="panel-heading">
						<h3 class="panel-title pull-left"></h3>
						<button class="btn btn-primary pull-right" data-toggle='modal' data-target='#searchreqCtrl' href='Global?type=Modal&name=Modal_SearchReqCtrl'><i class="glyphicon glyphicon-search" title="Search" data-searchfor="reqCtrl"></i></button>
        				<button type="button" class="btn btn-primary pull-right" id="addreqCtrl" data-toggle='modal' data-target="#modalreqCtrl" data-mode="1"> 
        				<i class="glyphicon glyphicon-plus" title="Add New Request Control"></i></button>
						<div class="clearfix"></div>
					</div>
					
					<div class="panel-body" id="display_reqCntrl">
						<table id="reqCtrl" class="table table-bordered table-striped" style="width:100%">
						<col width="10%">
						<col width="15%">
						<col width="10%">
						<col width="20%">
						<col width="10%">
						<col width="5%">
						<col width="15%">
						 <thead>
						 	<tr>
						 		<th>Request No</th>
								<th>Requestor</th>
								<th>ISBN</th>
								<th>Title</th>
								<th>Date Request</th>
								<th>Status</th>
						 		<th>Action</th>
						 	</tr>
						 </thead>
						</table>
					</div>
				</div>
			</div>
		<!-- END MAIN CONTENT -->
		
		<!-- START MODAL ADD, EDIT, VIEW -->
		<div class="modal fade" id="modalreqCtrl" tabindex="-1" role="dialog" aria-labelledby="ModalAdd" aria-hidden="true" data-keyboard="false" data-backdrop="static">
			<div class="modal-dialog" role="document" style="width:100%">
				<jsp:include page="Modal_RequestControl.jsp"></jsp:include> 
			</div>
		</div>
		<!-- END START MODAL ADD, EDIT, VIEW -->
		
			<!-- MODAL WHEN CLICK Search button-vendorSearch -->
	    <div class="modal fade" id="modal_vendorSearch" tabindex="-1" role="dialog" aria-labelledby="modal_vendorSearch" data-keyboard="false" data-backdrop="static"> 
			<div class="modal-dialog" style="width:50%;">
				    <div class="modal-content" id="modal_vendorSearchContent">
					  <!-- Remote content load here  -->
				  	</div>
			</div>
		</div>
		<!-- Modal END modal search -->
		
		<!-- Modal for search -->
		<div class="modal fade" id="searchreqCtrl" tabindex="-1" role="dialog" aria-labelledby="searchreqCtrl" data-keyboard="false" data-backdrop="static">
			<div class="modal-dialog" role="document" style="width:55%;overflow:auto">
				<div class="modal-content">
				<!-- Remote content load here -->
				</div>
			</div>
		</div>
		<!-- END modal for search -->

	<!-- MODAL WHEN CLICK Search patronid -->
		<div class="modal fade" id="modal_patrSearch4order" tabindex="-1" role="dialog" aria-labelledby="modal_patrSearch4order" data-keyboard="false" data-backdrop="static"> 
			<div class="modal-dialog" style="width:50%;">
				    <div class="modal-content" id="modal_patrSearch4orderContent">
					  <!-- Remote content load here  -->
				  	</div>
			</div>
		</div>
		<!-- Modal END modal search patronid-->
</body>
</html>