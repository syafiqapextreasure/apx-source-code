
<%@page import="com.wilmu.acquisition.ordermaint.*, java.util.*"%>

<%
/* String username = (String)session.getAttribute("username");
System.out.println(username + " username");
boolean accLvl = AccessLvl.executeAccessLvl(username,"AAAM1650" );
//boolean accLvl = true;
if(accLvl==true){ */
%>
<!doctype html>
<html>
	<head>
	<style>	
	#modalOdrMaint {
		    overflow-y: scroll;
		}
	</style>

		<!-- <meta charset="UTF-8"> -->

		<script type="text/javascript" src="${pageContext.request.contextPath}/js/Acquisition/Maintenance/AAAM0250/AAAM0250.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/Acquisition/Maintenance/AAAM0250/Validate_AAAM0250.js"></script>
		<script type="text/javascript" src="<%= request.getContextPath() %>/plugin/moment.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/general/searchOrdrMaint.js"></script>
		<link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">
		
		<script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/1.10.16/js/jquery.dataTables.js"></script> 
		<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.16/css/jquery.dataTables.css">
   		<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/responsive/2.2.0/css/responsive.dataTables.min.css">
    	<script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/responsive/2.2.0/js/dataTables.responsive.min.js"></script>
	</head>
	<body>
		<!-- START MAIN CONTENT -->
			<div class="box box-default">
				<div class="panel panel-default">
					<div class="panel-heading">
						<h3 class="panel-title pull-left"></h3>
						<button class="btn btn-primary pull-right" data-toggle='modal' data-target='#searchOdrMaint' href='Acquisition?type=Maintenance&module=AAAM0250&name=Modal_SearchOrderMaint'><i class="glyphicon glyphicon-search" title="Search" data-searchfor="orderMaint"></i></button>
        				<button type="button" class="btn btn-primary pull-right" id="addNewOdrMaint" data-toggle='modal' data-target="#modalOdrMaint" data-mode="1"> 
        				<i class="glyphicon glyphicon-plus" title="Add New Order Maint"></i></button>
						<div class="clearfix"></div>
					</div>
					
					<div class="panel-body" id="display_OdrMaint">
						<table id="OdrMaint2" class="table table-bordered table-striped">
						 <thead>
						 	<tr>
						 		<th>Order No.</th>
								<th>Control No.</th>
								<th>Title</th>
								<th>Vendor</th>
								<th>Status</th>
								<th>Reference</th>
								<th>Order Date</th>
								<th>Amount</th>
						 		<th>Action</th>
						 	</tr>
						 </thead>
						 <!-- <tbody id="test">
						 </tbody> -->
						</table>
					</div>
					<!-- <div id="test"></div> -->
				</div>
			</div>
		<!-- END MAIN CONTENT -->
		
		<!-- START MODAL ADD, EDIT, VIEW -->
		<div class="modal fade" id="modalOdrMaint" tabindex="-1" role="dialog" aria-labelledby="ModalAdd" aria-hidden="true" data-keyboard="false" data-backdrop="static">
			<div class="modal-dialog" role="document" style="width:70%">
				<jsp:include page="Modal_OrderMaint.jsp"></jsp:include> 
					<!-- END Modal content-->
			</div>
		</div>
		<!-- END MODAL ADD, EDIT, VIEW -->
		
		<!-- Modal for search -->
		<div class="modal fade" id="searchOdrMaint" tabindex="-1" role="dialog" aria-labelledby="searchOdrMaint" data-keyboard="false" data-backdrop="static">
			<div class="modal-dialog" role="document" style="width:55%;overflow:auto">
				<div class="modal-content">
				<!-- Remote content load here -->
				</div>
			</div>
		</div>	
		
		<!-- MODAL WHEN CLICK Search button-vendorSearch -->
	    <div class="modal fade" id="modal_vendorSearch" tabindex="-1" role="dialog" aria-labelledby="modal_vendorSearch" data-keyboard="false" data-backdrop="static"> 
			<div class="modal-dialog" style="width:50%;">
				    <div class="modal-content" id="modal_vendorSearchContent">
					  <!-- Remote content load here  -->
				  	</div>
			</div>
		</div>
		<!-- Modal END modal search -->
		
		<!-- MODAL WHEN CLICK Search CONTORL NUMBER -->
		<div class="modal fade" id="titleSearch" tabindex="-1" role="dialog" aria-labelledby="titleSearch" data-keyboard="false" data-backdrop="static">
			<div class="modal-dialog" role="document" style="width:55%;overflow:auto">
				<div class="modal-content">
				<!-- Remote content load here -->
				</div>
			</div>
		</div>
		<!-- Modal END modal search  control number-->
		
		<!-- MODAL WHEN CLICK Search patronid -->
		<div class="modal fade" id="modal_patrSearch4order" tabindex="-1" role="dialog" aria-labelledby="modal_patrSearch4order" data-keyboard="false" data-backdrop="static"> 
			<div class="modal-dialog" style="width:50%;">
				    <div class="modal-content" id="modal_patrSearch4orderContent">
					  <!-- Remote content load here  -->
				  	</div>
			</div>
		</div>
		<!-- Modal END modal search patronid-->
		
		
		<!-- MODAL WHEN CLICK NOTE-->
		<div class="modal fade" id="modalViewNote" tabindex="-1" role="dialog" aria-labelledby="modalViewNote" aria-hidden="true" data-keyboard="false" data-backdrop="static"> 
			<div class="modal-dialog" role="document" style="width:70%;overflow:auto">
				    <div class="modal-content" id="modal_vendorViewNoteContent">
					  <!-- Remote content load here  -->
				  	</div>
			</div>
		</div>
				<!-- MODAL WHEN CLICK Search patronid -->
		<div class="modal fade" id="ordermodal" tabindex="-1" role="dialog" aria-labelledby="ordermodal" data-keyboard="false" data-backdrop="static"> 
			<div class="modal-dialog" style="width:100%;">
				    <div class="modal-content" id="ordermodalContent">
					  <!-- Remote content load here  -->
				  	</div>
			</div>
		</div>
		
		<div class="modal fade" id="bibmodal" tabindex="-1" role="dialog" aria-labelledby="bibmodal" data-keyboard="false" data-backdrop="static"> 
			<div class="modal-dialog" style="width:100%;">
				    <div class="modal-content" id="orderbibmodalContent">
					  <!-- Remote content load here  -->
				  	</div>
			</div>
		</div>
	
		<!-- Modal END modal search patronid-->

	</body>
</html>