<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>

<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">
	
<script type="text/javascript" src="${pageContext.request.contextPath}/plugins/moment.min.js"></script>
	
<script type="text/javascript" src="${pageContext.request.contextPath}/js/Routing/Maintenance/AATM0250/AATM0250.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/Routing/Maintenance/AATM0250/Validate_AATM0250.js"></script>

	
	<style>
	
	#rsvCollectionMaster_table {
		table-layout: fixed;

	}

	
	#createModal {
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
					data-toggle="modal" data-target="#modalroutingmaster" data-action="add" data-mode="1"><span class="glyphicon glyphicon-plus pull-right" title="Add Template"></span></button>
							 <a class="btn btn-primary"  class="btn btn-info" role="button"
					data-toggle="modal" data-target="#searchRoutingMaster" data-action="add"><span class="glyphicon glyphicon-search pull-right" style="color:white"></span></a>

							   </h4>
						</div>
							<div id="result" class="panel-collapse collapse in">
									<div class="panel-body" id="display_routing">
	
			<table id="routingTable"
				class="table table-bordered table-striped" style="overflow-x: auto">
				<thead>
					<tr>
						<th>No.</th>
						<th>Title</th>
						<th>Patron Name</th>
						<th>Copy No.</th>
						<th>Priority</th>
						<th>Action</th>
					</tr>
				</thead>
			</table>
		</div>
	
								</div>
					</div>
					</div></div>
	<div class="modal fade" id="modalroutingmaster" tabindex="-1" role="dialog" aria-labelledby="createModal" data-keyboard="false" data-backdrop="static">
		<div class="modal-dialog" style="width: 65%;">
				<jsp:include page="Modal_RoutingMaster.jsp"></jsp:include> 
		</div>
	</div>

	
	<div class="modal fade" id="searchRoutingMaster" tabindex="-1" role="dialog" aria-labelledby="findModal" data-keyboard="false" data-backdrop="static">
		<div class="modal-dialog" style="width: 65%;">
				<jsp:include page="Modal_SearchRoutingMaster.jsp"></jsp:include> 
		</div>
	</div>
	
	<!-- MODAL WHEN CLICK Search ACCESSION NUMBER -->
		<div class="modal fade" id="titleSearch" tabindex="-1" role="dialog" aria-labelledby="titleSearch" data-keyboard="false" data-backdrop="static">
			<div class="modal-dialog" role="document" style="width:80%;overflow:auto">
				<div class="modal-content">
				<!-- Remote content load here -->
				</div>
			</div>
		</div>
		<!-- Modal END modal search  ACCESSION number-->
		
			
				<!-- MODAL WHEN CLICK Search officer -->
		<div class="modal fade" id="modal_patrSearchMono" tabindex="-1" role="dialog" aria-labelledby="modal_patrSearchMono" data-keyboard="false" data-backdrop="static"> 
			<div class="modal-dialog" style="width:80%;">
				    <div class="modal-content" id="modal_patrSearchMonoContent">
					  <!-- Remote content load here  -->
				  	</div>
			</div>
		</div>
		<!-- Modal END modal search officer-->
	
</body>

</html>