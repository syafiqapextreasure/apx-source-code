<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>

<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">
	
<script type="text/javascript" src="${pageContext.request.contextPath}/plugins/moment.min.js"></script>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/Reserve_Collection/init.js"></script>
	
<script type="text/javascript" src="${pageContext.request.contextPath}/js/Reserve_Collection/Maintenance/AAVM0150/AAVM0150.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/Reserve_Collection/Maintenance/AAVM0150/Validate_AAVM0150.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/Reserve_Collection/ColReorderWithResize.js"></script>


<!-- 	<style type="text/css">	


	</style> -->
	
	<style>
	
	#rsvCollectionMaster_table {
		table-layout: fixed;

	}

	#modalreservecollection {
		overflow-y: scroll;
		}
	
	
	</style>

</head>

<body>
<div class="panel panel-default" id="form_parent">
				<div class="panel-group">
					<div class="panel panel-default">
						<div class="panel-heading clearfix">
							<h4 class="panel-title">
							  	<button type="button" class="btn btn-primary"
					data-toggle="modal" data-target="#modalreservecollection" data-action="add" data-mode="1"><span class="glyphicon glyphicon-plus pull-right" title="Add Template"></span></button>
							 <a class="btn btn-primary" href="Global?type=Modal&name=Modal_SearchReserveCol" class="btn btn-info" role="button"
					data-toggle="modal" data-target="#searchModal" data-action="add"><span class="glyphicon glyphicon-search pull-right" style="color:white"></span></a>

							   </h4>
						</div>
							<div id="result" class="panel-collapse collapse in">
									<div class="panel-body" id="display_reservecollection">
	
			<table id="rsvCollectionMaster_table"
				class="table table-bordered table-striped" style="overflow-x: auto">
				<thead>
					<tr>
						<th class="rowNumber">No.</th>
						<th>Course</th>
						<th>Semester</th>
						<th>Subject</th>
						<th>Instructor</th>
						<th>Title</th>
						<th>Action</th>
					</tr>
				</thead>
			</table>
		</div>
	
								</div>
					</div>
					</div></div>
	<div class="modal fade" id="modalreservecollection" tabindex="-1" role="dialog" aria-labelledby="createModal" data-keyboard="false" data-backdrop="static">
		<div class="modal-dialog" style="width: 65%;">
				<jsp:include page="Modal_ReserveCollection.jsp"></jsp:include> 
		</div>
	</div>

	<div class="modal fade" id="editModal" tabindex="-1" role="dialog" aria-labelledby="editModal" data-keyboard="false" data-backdrop="static">
		<div class="modal-dialog" style="width: 65%;">
			<div class="modal-content" id="editModalContent">
				Remote content load here
			</div>
		</div>
	</div>
	
	<div class="modal fade" id="deleteModal" tabindex="-1" role="dialog" aria-labelledby="deleteModal" data-keyboard="false" data-backdrop="static">
		<div class="modal-dialog" style="width: 65%;">
			<div class="modal-content" id="deleteModalContent">
				Remote content load here
			</div>
		</div>
	</div>
	
	<div class="modal fade" id="viewModal" tabindex="-1" role="dialog" aria-labelledby="viewModal" data-keyboard="false" data-backdrop="static">
		<div class="modal-dialog" style="width: 65%;">
			<div class="modal-content" id="viewModalContent">
				Remote content load here
			</div>
		</div>
	</div>
	
	<div class="modal fade" id="searchModal" tabindex="-1" role="dialog" aria-labelledby="findModal" data-keyboard="false" data-backdrop="static">
		<div class="modal-dialog" style="width: 65%;">
			<div class="modal-content" id="findModalContent">
				Remote content load here
			</div>
		</div>
	</div>
			<!-- MODAL WHEN CLICK Search officer -->
		<!-- <div class="modal fade" id="modal_patrSearchMono" tabindex="-1" role="dialog" aria-labelledby="modal_patrSearchMono" data-keyboard="false" data-backdrop="static"> 
			<div class="modal-dialog" style="width:55%;">
				    <div class="modal-content" id="modal_patrSearchMonoContent">
					  Remote content load here 
				  	</div>
			</div>
		</div> -->
		<!-- Modal END modal search officer-->
	
		<!-- MODAL WHEN CLICK Search CONTROL NUMBER -->
		<!-- <div class="modal fade" id="titleSearch" tabindex="-1" role="dialog" aria-labelledby="titleSearch" data-keyboard="false" data-backdrop="static">
			<div class="modal-dialog" role="document" style="width:80%;overflow:auto">
				<div class="modal-content">
				Remote content load here
				</div>
			</div>
		</div> -->
		<!-- Modal END modal search CONTROL NUMBER-->
	
<!-- 	<div class="modal fade" id="titleSearchAccMain" tabindex="-1"
		role="dialog" aria-labelledby="titleSearchAccMain"
		data-keyboard="false" data-backdrop="static">
		<div class="modal-dialog" style="width: 65%;">
			<div class="modal-content" id="Modal_TitleSearchContent">
				Remote content load here</div>
		</div>
	</div>
	
	open modal search patron
	<div class="modal fade" id="modal_searchPatronID" tabindex="-1"
		role="dialog" aria-labelledby="modal_searchPatronID"
		data-keyboard="false" data-backdrop="static">
		<div class="modal-dialog" style="width: 65%;">
			<div class="modal-content" id="Modal_PatrSearchContent">
				Remote content load here 
			</div>
		</div>
	</div> -->
	
</body>

</html>