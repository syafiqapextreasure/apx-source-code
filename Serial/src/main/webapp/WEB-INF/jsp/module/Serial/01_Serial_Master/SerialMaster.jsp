<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Serial Master</title>
<style>
.modal {
  overflow-y:auto;
}
</style>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/general/global.js"></script>					
<script type="text/javascript" src="<%= request.getContextPath() %>/plugin/datatables/jquery.dataTables.min.js"></script>
 <script type="text/javascript" src="<%= request.getContextPath() %>/plugin/datatables/dataTables.bootstrap.min.js"></script>
 <link rel="stylesheet" href="<%= request.getContextPath() %>/plugin/datatables/dataTables.bootstrap.css">
 <script type="text/javascript" src="${pageContext.request.contextPath}/js/Serial/Serial_Master.js"></script>		
<script type="text/javascript">	


	
	// Search title result from SearchTitle.jsp
	function loadRecord(){
		// Display loading bar
		replaceLoader("#display_title");
		var title = $('#criteria').val();
		var searchType = $('#search_type').val();
		
		url = "${pageContext.request.contextPath}/jsp/include/sharedV1/Table_ControlNumber.jsp?criteria="+title+"&search_type="+searchType;
		
		$.get(url,function(data_title){
			$("#display_title").html(data_title);
		});	
	}
	
	function reloadRecord(){
		// Display loading bar
		replaceLoader("#display_title");
		
		$.get(url,function(data_title){
			$("#display_title").html(data_title);
		});	
	}

	
	/* function deleteRecord(controlNo){
		var url = "Handler_DeletePeriodicalsMaster.jsp?controlNo=" + controlNo;
		
		var result = confirm("You are about to delete one record?");
		if (result) {
			$.ajax({
	  			url: url,
	  			success: function(result) {
	  				var deleteRecord = clearSpace(result);
	  				if(deleteRecord == "ok"){
	  					showPopupMsg("Delete successful.","success");
	  					
	  					if($("#txt-searchAll").val() === "yes")
	  						reloadRecord();
	  					else
	  						window.location = window.location.href;
	  				}else{
	  					showPopupMsg("Delete unsuccessful.","warning");
	  				}
	  			}
	  		});
		}
	} */
	</script>
</head>

<body>

		<div class="panel panel-default" id="form_parent">
		<div class="panel-heading">
			<div class="form-group">
			<button type="button" alt="Show All Records" class="btn btn-primary" id="btn_showAll" title="Show All">
				<span class="glyphicon glyphicon-th-list" style="color:white"></span>
				</button>
						<!-- <button type="button" class="btn btn-primary" id="btn_search" data-toggle="modal" 
							data-target="#titleSearch" href="Modal_ControlNumber.jsp">
							<span class="glyphicon glyphicon-search"></span></button> -->
							<a class="btn btn-primary" id='retrieveLink' data-toggle='modal' data-target='#titleSearch' href='RetrieveTitleModal?action=5' title="Catalog Search"><span class="glyphicon glyphicon-search" style="color:white"></span></a>
						<!--  <a class="btn btn-primary" id='retrieveLink' data-toggle='modal' data-target='#titleSearch' href='Modal_SerialMstr?action=serialMstr' title="Search"><span class="glyphicon glyphicon-search" style="color:white"></span></a>
						 --><button type="button" class="btn btn-primary" id="btn_add" data-toggle="modal" 
							data-target="#addNewSerialMaster" href="Modal_AddNewSerialMaster" title="Add"><span class="glyphicon glyphicon-plus" style="color:white"></span></button>
				</div>
		</div>
			<div class="panel-body" id="panel_serial">
						<table class="table table-bordered" id="tableSerial" style="font-size:10pt">
						<thead>
								<tr>
									<th>Control No.</th>
									<th>Title</th>
									<th>Action</th>
								</tr>
							</thead>
							<tbody id="serialdata">
							</tbody>
					</table>
<!-- 					<table class="table-hover" id="tableSerial" style="width:100%">
						<thead style="height:10%">
							<tr>
								<th data-sortable="true" class="col-md-2">Control No.</th>
								<th data-sortable="true">Title</th>
								<th class="col-md-2">Action</th>
							</tr>
						</thead>
						<tbody>
						</tbody>
					</table> -->
            </div>
		</div>

	<input type="hidden" id="txt-searchAll"/>
	<!-- Bootstrap modal for add new periodicals master -->
	<div class="modal fade" id="addNewSerialMaster" tabindex="-1" role="dialog" aria-labelledby="addNewPeriodicalsMaster">
	    <div class="modal-dialog" role="document" style="width:900px;">
			  <div class="modal-content">
			  <!-- Remote content load here -->
			  </div>
		</div>
	</div>
    <!-- Modal END -->
	
	<!-- Bootstrap modal for periodicals master edit -->
	<div class="modal fade" id="editPeriodicalsMaster" tabindex="-1" role="dialog" aria-labelledby="editPeriodicalsMaster">
		<div class="modal-dialog" role="document" style="width:900px;">
			<div class="modal-content">
			<!-- Remote content load here -->
			</div>
		</div>
	</div>	
	<!-- Modal END -->
	
	<!-- Bootstrap modal for periodicals master view -->
	<div class="modal fade" id="viewPeriodicalsMaster" tabindex="-1" role="dialog" aria-labelledby="viewPeriodicalsMaster">
		<div class="modal-dialog" role="document" style="width:900px;">
			<div class="modal-content">
			<!-- Remote content load here -->
			</div>
		</div>
	</div>	
	<!-- Modal END -->
	
	<!-- Bootstrap modal for control no search -->
	<div class="modal fade" id="titleSearch" tabindex="-1" role="dialog" aria-labelledby="titleSearch">
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

	
	<!-- Bootstrap modal for requestor search -->
	<div class="modal fade" id="requestorSearch" tabindex="-1" role="dialog" aria-labelledby="requestorSearch">
	    <div class="modal-dialog" role="document">
			  <div class="modal-content">
			  <!-- Remote content load here -->
			  </div>
		</div>
	</div>

</body>
</html>