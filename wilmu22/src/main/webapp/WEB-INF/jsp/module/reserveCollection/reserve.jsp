<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>

<head>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<!-- <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet"> -->

<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.16/css/jquery.dataTables.css">

<script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/1.10.16/js/jquery.dataTables.js"></script>
	
<script type="text/javascript" src="${pageContext.request.contextPath}/plugins/moment.min.js"></script>
 
<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.21/css/jquery.dataTables.min.css"> 

<!-- <link rel="stylesheet" type="text/css"
	href="https://cdn.datatables.net/select/1.3.1/css/select.dataTables.min.css">  -->

 <script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/1.10.21/js/jquery.dataTables.min.js"></script> 

<!--  <script type="text/javascript" charset="utf8"
	src="https://cdn.datatables.net/select/1.3.1/js/dataTables.select.min.js"></script> -->
	
<script type="text/javascript" src="${pageContext.request.contextPath}/js/reserveCollection/js/init.js"></script>
	
<script type="text/javascript" src="${pageContext.request.contextPath}/js/reserveCollection/js/reserve.js"></script>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/reserveCollection/js/ColReorderWithResize.js"></script>


<!-- 	<style type="text/css">	


	</style> -->
	
	<style>
	
	#rsvCollectionMaster_table {
		table-layout: fixed;

	}

	#rsvCollectionMaster_table tr td {
		white-space: nowrap;
		overflow: hidden;
		text-overflow: ellipsis;
		color: black;
	}

	th {
		white-space: nowrap;
		overflow: hidden;
		text-overflow: ellipsis;
		}
	
	#createModal {
		overflow-y: scroll;
		}
	
	
	</style>

</head>

<body>

	<div class="col-md-12" style="margin-top: 10px;">
		<div class="container">
			<table id="rsvCollectionMaster_table"
				class="table table-bordered table-striped" style="overflow-x: auto">
				<thead>
					<tr>
						<th>No.</th>
						<th>Course</th>
						<th>Course Desc</th>
						<th>Semester</th>
						<th>Semester Desc</th>
						<th>Subject</th>
						<th>Subject Desc</th>
						<th>Instructor</th>
						<th>Control No</th>
						<th>Accession No</th>
						<th>Title</th>
						<th>Reserve No</th>
						<th>Action</th>
					</tr>
				</thead>
			</table>
			<div class="modal-footer">
			
				<a href="create-reserve" class="btn btn-info" role="button"
					data-toggle="modal" data-target="#createModal" data-action="add">Add</a> 
			
<!-- 				<a href="edit-reserve" class="btn btn-info" role="button"
					data-toggle="modal" data-target="#editModal" data-action="edit">Edit</a> 
				
				<a href="delete-reserve" class="btn btn-info" role="button"
					data-toggle="modal" data-target="#deleteModal" data-action="delete">Delete</a>

				<a href="view-reserve" class="btn btn-info" role="button"
					data-toggle="modal" data-target="#viewModal" data-action="view">View</a> -->

				<button type="button" class="btn btn-secondary">Refresh</button>

				<a href="find-reserve" class="btn btn-info" role="button"
					data-toggle="modal" data-target="#findModal" data-action="add">Find</a> 

				<button type="button" class="btn btn-secondary" id="getPatronIdBtn">Close</button>
				
			</div>
		</div>
	</div>
	
	<div class="modal fade" id="createModal" tabindex="-1" role="dialog" aria-labelledby="createModal" data-keyboard="false" data-backdrop="static">
		<div class="modal-dialog" style="width: 65%;">
			<div class="modal-content" id="createModalContent123">
				Remote content load here
			</div>
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
	
	<div class="modal fade" id="findModal" tabindex="-1" role="dialog" aria-labelledby="findModal" data-keyboard="false" data-backdrop="static">
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