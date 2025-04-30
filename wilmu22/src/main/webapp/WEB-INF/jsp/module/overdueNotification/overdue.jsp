<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">


<!-- <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css"rel="stylesheet"> -->

<!-- <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.16/css/jquery.dataTables.css"> -->

<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/select/1.1.0/css/select.dataTables.min.css">

<link rel="stylesheet" href="${pageContext.request.contextPath}/plugins/sweetalert2/css/sweetalert2.min.css">

<script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/1.10.16/js/jquery.dataTables.js"></script>

 <script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/select/1.1.0/js/dataTables.select.min.js"></script>

  <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/responsive/2.2.0/css/responsive.dataTables.min.css"> 
    <script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/responsive/2.2.0/js/dataTables.responsive.min.js"></script>

<%-- <script src="${pageContext.request.contextPath}/plugins/sweetalert2/js/sweetalert2.min.js"></script> --%>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/overdueNotification/validateOverdueNotification.js"></script>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/overdueNotification/ColReorderWithResize.js"></script>

<%-- <script type="text/javascript" src="${pageContext.request.contextPath}/js/general/cirActivity.js"></script> --%>

 <script type="text/javascript" src="https://cdn.datatables.net/plug-ins/1.10.24/api/sum().js"></script>
    <script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/buttons/2.0.1/js/dataTables.buttons.min.js"></script>
	<script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/buttons/1.6.1/js/buttons.flash.min.js"></script>
	<script type="text/javascript" charset="utf8" src="https://cdnjs.cloudflare.com/ajax/libs/jszip/3.1.3/jszip.min.js"></script>
	<script type="text/javascript" charset="utf8" src="https://cdnjs.cloudflare.com/ajax/libs/pdfmake/0.1.53/pdfmake.min.js"></script>
	<script type="text/javascript" charset="utf8" src="https://cdnjs.cloudflare.com/ajax/libs/pdfmake/0.1.53/vfs_fonts.js"></script>
	<script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/buttons/1.6.1/js/buttons.html5.min.js"></script>
	<script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/buttons/1.6.1/js/buttons.print.min.js"></script>
	
<style>
/* 
#overdue_table {
	table-layout: fixed;
}

#overdue_table tr td {
	white-space: nowrap;
	overflow: hidden;
	text-overflow: ellipsis;
	color: black;
}

th {
	white-space: nowrap;
	overflow: hidden;
	text-overflow: ellipsis;
	color: black;

}

#overdueRmd_table {
	table-layout: fixed;
}

#overdueRmd_table tr td {
	white-space: nowrap;
	overflow: hidden;
	text-overflow: ellipsis;
	color: black;
}
 */
/*  
button {
  background-color: #4CAF50;
  }
   */
<script type="text/javascript">
	$(document).on({
	    ajaxStart: function(){
	    	$('.overlay').fadeIn();
	    },
	    ajaxStop: function(){ 
	    	$('.overlay').fadeOut();
	    }    
	});
</script>

</style>

</head>
<body>

<!-- 	<form id="submitButtonRedirectPage" action="redirectPage"  target="_blank">
	    <input type="submit">
	</form> -->
<!-- 		<form method="post" id="submitButtonRedirectPage" action="redirectPage">
	    <input type="submit">
 	</form> -->
<!--	
	<form id="invisible_form" action="redirectPage" method="post" target="_blank">
	  <input id="new_window_parameter_1" name="parameter" type="submit" value="default">
	</form>
 -->
	<div class="OnContainer" style="margin-left: 20px;" id="OnContainer">
		<form role="form" class="form-horizontal" id="form-radioDate">
		<input type='hidden' class="module">
			<div class="col-md-12">
				<div class="col-md-3">
					<div class="form-group ">
						<div class="radio">
							<label><input type="radio" name="retrieveRadios"
								id="optradio" value="unprint" checked="checked"><b>Retrieve
								Unprinted Notices </b></label>
						</div>
						<div class="radio">
							<label><input type="radio" name="retrieveRadios"
								id="optradio" value="reprint"><b>Retrieve Notices for
								Reprint</b></label>
						</div>
						<div class="radio">
							<label><input type="radio" name="retrieveRadios"
								id="optradio" value="patron"><b>Retrieve Selected Patron </b></label>
						</div>
					</div>
				</div>
				<div class="col-md-3" style="padding-right: 0px;padding-left: 0px;">
					<div id="unprintedDate">
						<input
							style="height: 25px; border-bottom-width: 2px; border-top-width: 2px; margin-bottom: 3px; margin-top: 3px; type =; padding-bottom: 0px;"
							type="text" class="unprint" name="forUnprint" id="forUnprint"
							disabled="true">
					</div>
					<div id="reprintDate">
						<input
							style="height: 25px; border-bottom-width: 2px; border-top-width: 2px; margin-bottom: 3px; margin-top: 3px; type =; padding-bottom: 0px;"
							type="text" class="reprint" name="forReprint" id="forReprint"
							disabled="true">&nbsp
						<button type="button" class="btn btn-info btn-xs"
							data-toggle="modal" data-target="#exampleModal" id="btnOpenReprintModal"
							disabled="true">...</button>
					</div>
					<div class="input-group date" id="patronId">
						<input
							style="height: 25px; border-bottom-width: 2px; border-top-width: 2px; margin-bottom: 3px; margin-top: 3px; type =; padding-bottom: 0px;"
							type="text" class="patron" name="forPatron" id="forPatron"
							disabled="true">
					</div>
				</div>
				<div class="col-md-6">
					<div class="col-md-3">
						<div class="form-group ">
							<div style="margin-top: 10px;"><b>Patron Category:</b></div>
							<div style="margin-top: 12px;"><b>Branch:</b></div>
						</div>
					</div>
					<div class="col-md-2">
						<div class="form-group ">
							<div class="input-group select">
								<select
									style="padding-top: 2px; padding-bottom: 2px; top: 3px; bottom: 2px; height: 30px; left: 0px;"
									data-id="1" class="labelBlack form-control patronCategoryId"
									id="patronCategoryId1"></select>
							</div>
							<div class="input-group select">
								<select
									style="padding-top: 2px; padding-bottom: 2px; top: 8px; bottom: 2px; height: 30px;"
									data-id="2" class="labelBlack form-control branchId"
									id="branchId2"></select>
							</div>
						</div>
					</div>
					<div class="col-md-3">
						<div class="form-group ">
							<div class="input-group select">
								<select
									style="padding-top: 2px; padding-bottom: 2px; top: 3px; bottom: 2px; height: 30px; left: 8px; width: 250px;"
									data-id="1" class="labelBlack form-control patronCategoryDesc"
									id="patronCategoryDesc1" required
									class="form-control nearSelcttxt"></select>
							</div>
							<div class="input-group select">
								<select
									style="padding-top: 2px; padding-bottom: 2px; top: 8px; bottom: 2px; height: 30px; left: 8px; width: 250px;"
									data-id="2" class="labelBlack form-control branchDesc"
									id="branchDesc2"></select>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="col-md-12">
				<div class="col-md-3">
					<div class="form-group ">
						<div class="input-group" style="width: 100%; float: left;">
							<input name="officerCB" type="checkbox" class="form-check-input"
								id="officerCB"> &nbsp <b>Officer ID:</b>
						</div>
					</div> 
				</div>
				<div class="col-md-3" style="padding-left: 0px;padding-right: 0px;">
					<input type="text" maxlength="12" name="officerId" id="officerId"
						class="checkbox1" disabled="true"> 
				</div>
				<div class="col-md-3">
					<div class="form-group ">
						<div class="radio">
							<label><input type="radio" name="reminderRadiosName"
								id="reminderRadios" value="first"><b>First Reminder</b></label>
						</div>
						<div class="radio">
							<label><input type="radio" name="reminderRadiosName"
								id="reminderRadios" value="second"><b>Second Reminder</b></label>
						</div>
						<div class="radio">
							<label><input type="radio" name="reminderRadiosName"
								id="reminderRadios" value="third" checked="checked"><b>Third
								Reminder</b></label>
						</div>
					</div>


				</div>
				<div class="col-md-3">
					<div class="form-group ">
						<div class="radio">
							<label><input type="radio" name="addressRadiosName"
								id="addressRadios" value="department" checked="checked"><b>Print
								Department Address</b></label>
						</div>
						<div class="radio">
							<label><input type="radio" name="addressRadiosName"
								id="addressRadios" value="office"><b>Print Office Address</b></label>
						</div>
						<div class="radio">
							<label><input type="radio" name="addressRadiosName"
								id="addressRadios" value="home"><b>Print Home Address</b></label>
						</div>
					</div>
				</div>
			</div>
			<div class="col-md-12">
				<div class="col-md-3" style="top: 10px;">
					<div class="form-group">
						<div class="radio">
							<label><input type="radio" name="patronStatusRadios"
								id="unchange" value="unchange" checked="checked"><b>Unchange</b></label>
						</div>
					</div>
				</div>
			</div>
			<div class="col-md-12">
				<div class="col-md-1">
					<div class="form-group">
						<div class="radio">
							<label><input type="radio" name="patronStatusRadios"
								id="change" value="change"><b>Change to</b></label>
						</div>
					</div>
				</div>
			
				<div class="col-md-1" style="padding-left: 50px;">
					<div class="form-group">
						<div class="input-group select">
							<select
								style="padding-top: 0px; padding-bottom: 0px; padding-right: 0px; padding-left: 0px; border-left-width: 2px; border-right-width: 2px; border-bottom-width: 2px; border-top-width: 2px; height: 28px; width: 48px; top: 8px;"
								data-id="3" class="labelBlack form-control patronStatusId"
								id="patronStatusId3" disabled="true"></select>
						</div>
					</div>
				</div>
				<div class="col-md-3">
					<div class="input-group select">
						<select
							style="padding-top: 0px; padding-left: 0px; padding-bottom: 0px; padding-right: 0px; border-left-width: 2px; border-right-width: 2px; border-bottom-width: 2px; border-top-width: 2px; top: 8px; bottom: 8px; height: 28px; width: 204px;"
							data-id="3" class="labelBlack form-control patronStatusDesc"
							id="patronStatusDesc3" disabled="true"></select>
					</div>
				</div>
				<div class="col-md-3">
					<span class="message"></span>
				</div>
			</div>
			<div class="overlay" style="display:none;">
			    <div class="spinner-border" role="status">
			  		<span class="sr-only">Loading...</span>
				</div>
			</div>
		</form>
	</div>

	<div class="OrContainer" style="margin-left: 20px;" id="OrContainer">
		<form class="form-horizontal" id="overReminderForm">
		<input type='hidden' class="module">
			<div class="col-md-12">
				<div class="form-group" style="margin-top: 6px;">
					<div class="col-md-2" style="top: 6px;"><b>Print All Overdue from:</b></div>
					<div class='col-md-3'>
						<div class="input-group date" id="startDate" style="left: -15;">
							<input type="text" class="form-control" name="dateFrom"
								id="dateFrom"> <span class="input-group-addon"> <i
								class="glyphicon glyphicon-calendar"></i>
							</span>
						</div>
					</div>
					<div class="input-group"
						style="width: 5%; float: left; left: -8; top: 5px;">
						&nbsp;&nbsp;&nbsp; <span> <b>to</b> </span>
					</div>
					<div class='col-md-3'>
						<div class="input-group date" id="endDate">
							<input type="text" class="form-control" name="dateTo" id="dateTo">
							<span class="input-group-addon"> <i
								class="glyphicon glyphicon-calendar"></i></span>
						</div>
					</div>
				</div>
			</div>
			<div class="col-md-12">

				<div class="col-md-9">
					<form class="form-horizontal" name="cirActForm" id="cirActForm">
						<jsp:include page="/WEB-INF/jsp/shared/headerSearchCateria.jsp"></jsp:include> 
					</form>
				</div>
				<div class="col-md-3">
					<div class="form-group ">
						<div class="radio">
							<label><input type="radio" name="addressRadiosName"
								id="addressRadios" value="department" checked="checked"><b>Print
								Department Address</b></label>
						</div>
						<div class="radio">
							<label><input type="radio" name="addressRadiosName"
								id="addressRadios" value="office"><b>Print Office Address</b></label>
						</div>
						<div class="radio">
							<label><input type="radio" name="addressRadiosName"
								id="addressRadios" value="home"><b>Print Home Address</b></label>
						</div>
					</div>
				</div>
			</div>
		</form>
	</div>

	<div class="col-md-12">
		<div class="form-group">
			<div class="overNotifyTable">
				<div class="table-responsive" style="overflow-x: auto;">
					<table id="overdue_table" class="table table-bordered table-striped"
						style="overflow-x: auto">
						<thead>
							<tr>
							<!-- 	<th><input type = "checkbox" class="checkAll" id = "checkAll"></th> -->
								<th>Accession No</th>
								<th>Patron</th>
								<th>Title</th>
								<th>Call No.</th>
								<th>Due Date</th>
								<th>Reminder Type</th>
								<th>Late By</th>
								<th>Fines</th>
								<th>Email</th>
								<th>Branch</th>
								<th>Location</th>
							</tr>
						</thead>
					</table>
				</div>
			</div>
			<div class="overReminderTable">
				<div class="table-responsive" style="overflow-x: auto;">
					<table id="overdueRmd_table" class="table table-bordered table-striped"
						style="overflow-x: auto">
						<thead>
							<tr>
							<!-- 	<th><input type = "checkbox" class="checkAll" id = "checkAll"></th> -->
								<th>Accession No</th>
								<th>Patron</th>
								<th>Title</th>
								<th>Call No.</th>
								<th>Due Date</th>
								<th>Reminder Type</th>
								<th>Email</th>
								<th>Branch</th>
								<th>Location</th>
							</tr>
						</thead>
					</table>
				</div>
			</div>
		</div>
	</div>
	<div class="container">
		<div class="row">
			<div class="col-md-6">
				<button type="button" id="reloadPageBtn"
					class="btn btn-primary mr-auto" style="background-color: #1ab394">New</button>
			</div>
			<div class="col-md-6">
				<span class="pull-right">
					<button type="button" id="retrieveBtn" form="form-overdue"
						class="btn btn-primary" style="background-color: #1ab394" >Retrieve</button>
					<button type="button" id="previewBtn"
						class="btn btn-primary" disabled="true" style="background-color: #1ab394">Preview</button>
					<button type="button" id="printOverdue"
						class="btn btn-primary" style="background-color: #1ab394" disabled="true" >Print</button>
					<button type="button" id="emailBtn" class="btn btn-primary" style="background-color: #1ab394" disabled="true" 
						>Email</button>
				</span>
			</div>
		</div>
	</div>

	<!-- Button trigger modal -->


	<!-- Modal -->
	<div class="modal" id="exampleModal" tabindex="-1" role="dialog"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLabel">Printed Notices</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">×</span>
					</button>
				</div>
				<div class="modal-body">
					<div class="col-md-12">
						<div class="form-group">

							<div class="list-group email"></div>

							<div class="table-responsive" id="display_email"
								style="overflow-x: auto;">
  								<table id="model_table_a"
									class="table table-bordered table-striped"
									style="overflow-x: auto">
									<thead>
										<tr>
											<th>Printed Date</th>
										</tr>
									</thead>
								</table>  
							</div>
						</div>
					</div>

					<div class="col-md-12">
						<div class="form-group">
							<div class="list-group email"></div>
							<div class="table-responsive" id="display_email"
								style="overflow-x: auto;">
								<table id="model_table_b"
									class="table table-bordered table-striped"
									style="overflow-x: auto">
									<thead>
										<tr>
											<th>Accession No</th>
											<th>Title</th>
										</tr>
									</thead>
								</table>
							</div>
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-primary" id=buttonDelete data-dismiss="modal">Cancel</button>
					<button type="button" class="btn btn-secondary"
						data-dismiss="modal" id="getDateBtn">OK</button>
				</div>
			</div>
		</div>
	</div>

	<!-- 	<script>
/* 		document.getElementById("forPatron1").innerHTML = "My First JavaScript"; */
document.getElementById("forReprint").value = "Johnny Bravo";
	</script> -->
    <script type="text/javascript">
        function goToInfo(){

        }

        function goToOther(){
        	alert('redirect page');
        	window.location = '/redirectPage.jsp';
        }

        </script>

</body>
</html>