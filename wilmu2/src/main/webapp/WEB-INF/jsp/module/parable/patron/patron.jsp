<meta http-equiv="Content-type" value="text/html; charset=UTF-8" />
<%-- <%@ include file="/include/header.jsp" %> --%>
<%@ include file="/WEB-INF/jsp/module/parable/patron/patronController.jsp" %>
<%@ include file="patronScript.jsp" %>
<%@page import="com.kmlink.ilmu.parable.parable_beta.Config_Print, com.kmlink.ilmu.parable.parable_beta.CurrentUser"%>
 <script type="text/javascript" src="<%= request.getContextPath() %>/plugins/datatables/jquery.dataTables.min.js"></script>
 <script type="text/javascript" src="<%= request.getContextPath() %>/plugins/datatables/dataTables.bootstrap.min.js"></script>
 <link rel="stylesheet" href="<%= request.getContextPath() %>/plugins/datatables/dataTables.bootstrap.css">


<script>
$(document).ready(function () {
	$('#queryResult').DataTable({
	    responsive: true,
	    "bPaginate": false
	});

});
</script>
<style>
.table{
	max-width:50px !important;
	white-space: nowrap;
 	overflow: hidden;
  	text-overflow: ellipsis;
}
.form-control[readonly]{
	background-color:white !important;
}
</style>
<script>

$(function(){
	$("[title]").tooltip();
	$("#search_by").change(function(){
		if($(this).val().trim() == "catalog_date".trim()){
			$("#search_catalog_date").show();
			$("#search_accession").hide();
		}else if($(this).val().trim() == "accession_no".trim()){
			$("#search_accession").show();
			$("#search_catalog_date").hide();
		}
	})
/* 	
	$("#checkAll").change(function () {
	    $("input:checkbox").prop('checked', $(this).prop("checked"));
	});
	 */
})

</script>
<html>
 <script type="text/javascript" src="${pageContext.request.contextPath}/plugins/jquery-2.1.4.min.js"></script> 
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap-datepicker.css"/>
<link rel="stylesheet" href="<%= request.getContextPath() %>/plugins/sweetalert2/css/sweetalert2.min.css" rel="stylesheet">
<!-- Bootstrap Date-Picker Plugin -->
<script src="${pageContext.request.contextPath}/plugins/sweetalert2/js/sweetalert2.min.js" ></script>
<script type="text/javascript" src="<%= request.getContextPath() %>/plugins/bootstrap-datepicker/js/bootstrap-datepicker.min.js"></script>

 
<%--  <script type="text/javascript" src="<%= request.getContextPath() %>/plugins/bootstrap-datepicker.min.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/plugins/bootstrap-datepicker/css/bootstrap-datepicker.css"> --%>

	<div style= "padding:10px 10%">
		<div class="panel panel-default">
			<div class="panel-heading">
				<h3 class="panel-title" style="display:inline">Patron Label </h3>
				<span style="display:inline;font-size:8pt">(v2021.0.0.0)</span>
			</div>
			<div class="panel-body">
			<form class="form-horizontal">	
				<div class = "row">
					<div class = "col-md-12" style = "border-bottom:1px solid #78b7ef"><h4>Filters</h4></div>
				</div>
				<br>
				<div class="form-group">
			    	<label class="control-label col-sm-2" for="first_number">Search By :</label>
			    	<div class="col-sm-3">
			    	<select name = "search_by" id="search_by" class = "form-control">
	      				<option value="patron_id">Patron ID</option>
	      				<option value="name">Name</option>
	      				<option value="patron_cat">Patron Category</option>
	      				<option value ="department">Department</option>
	      				<option value ="course">Course</option>
	      				<option value="date_range">Membership Date</option>
	      			</select>
	      			</div>
	      		</div>
				<div id="queryDetail">
		 			<div class="form-group" id = "patron_id">
				    	<label class="control-label col-sm-2" for="patron_id">Range : </label>
				    	<div class="col-sm-2">
				    	<input name="patron_id1" class="form-control" type="text"/>
				    	</div>
				    	<div class="col-sm-1">
				    	<label for="patron_id">to </label>
				    	</div>
				    	<div class="col-sm-2">
				    	<input name="patron_id2" class="form-control" type="text"/>
				    	</div>
			  		</div>
		 			<div class="form-group" id = "name">
				    	<label class="control-label col-sm-2" for="name">Name :</label>
				    	<div class="col-sm-2">
				    	<input name="name" class="form-control" type="text"/>
				    	</div>
			  		</div>
			  		<div class="form-group" id = "patron_cat">
				    	<label class="control-label col-sm-2" for="patron_cat">Patron Category :</label>
				    	<div class="col-sm-4">
				    	<select name="item_category" class ="form-control">
				    	<%
				    		for (patron_category patron_category : picklistCategory) {
			    		%>
				    		<option value ="<%=patron_category.getPatronCategory()%>"><%=patron_category.getDescription() %></option>
			    		<%
				    		}
			    		%>
				    	</select>
				    	</div>
			  		</div>
			  		<div class = "form-group" id = "department">		
				    	<label class="control-label col-sm-2" for="department">Department :</label>
				    	<div class="col-sm-4">
				    	<select name="department" class = "form-control">
				    	<%
				    		for (department tempDepartment : picklistDepartment) {
			    		%>
				    		<option value ="<%=tempDepartment.getDepartment()%>"><%=tempDepartment.getDescription()%></option>
			    		<%
				    		}
			    		%>
				    	</select>
				    	</div>
			  		</div>
			  		<div class = "form_group" id = "course">		
				    	<label class="control-label col-sm-2" for="course">Course :</label>
				    	<div class="col-sm-4">
				    	<select name="course" class ="form-control">
				    		<%
				    		for (course tempCourse : picklistCourse) {
				    		%>
				    		<option value ="<%=tempCourse.getCourse()%>"><%=tempCourse.getDescription()%></option>
				    		<%
				    		}
				    		%>
				    	</select>
				    	</div>
			  		</div>
		 			<div class="form-group" id = "date_range"> 
				    	<label class="control-label col-sm-2" for="catalog_date1">Membership Date :</label>
				    	<script>
 				    	$(function(){
				    		var FromEndDate;
				    		var ToEndDate;
				    		var startDate;
				    		$('#date_range1').datepicker({
				    			format: "dd/mm/yyyy",
				    		    startDate: '',
				    		    endDate: FromEndDate, 
				    		    autoclose: true
				    		}).on('changeDate', function(selected){
				    		        startDate = new Date(selected.date.valueOf());
				    		        startDate.setDate(startDate.getDate(new Date(selected.date.valueOf())));
				    		        $('#date_range2').datepicker('setStartDate', startDate);
				    		    }); 
				    		$('#date_range2').datepicker({
			    			 	format: "dd/mm/yyyy",
		    		        	startDate: startDate,
			    		        endDate: ToEndDate,
			    		        autoclose: true
			    		    }).on('changeDate', function(selected){
			    		        FromEndDate = new Date(selected.date.valueOf());
			    		        FromEndDate.setDate(FromEndDate.getDate(new Date(selected.date.valueOf())));
			    		        $('#date_range1').datepicker('setEndDate', FromEndDate);
			    		    });
				    	})
				    	</script>
				    	<div class="col-sm-2">
				    	<input name="date_range1" id = "date_range1" class="form-control" type="text" readonly="readonly"/>
				    	</div>
				    	<div class="col-sm-1">
				    	<label for="catalog_date2" style = "margin:0 10"> to </label>
				    	</div>
				    	<div class="col-sm-2">
				    	<input name="date_range2" id = "date_range2"  class="form-control" type="text" readonly="readonly"/>
			  			</div>
			  		</div>
			  	</div>
 			  	<div class="form-group">
		    	<label class="control-label col-sm-2" for="search_by">Branch :</label>
		    	<div class="col-sm-3">
						<%
						List<String> brch = labelBranch.getLabelBranch();
						%>
					
							<select id = "branch" class="form-control">
								<option value="All">All</option>
							<%
								for(int i=0;  i<brch.size();  i++){

							%>
						 		 <option value="<%=labelBranch.getLabelBranch().get(i)%>" data-type="<%=labelBranch.getLabelBranch().get(i)%>"><%=labelBranch.getLabelBranch().get(i) %></option> 
		  
							<%
								}
							%>
							</select>
							<br>
					
	      			</div>
	      		</div>
				<div class = "row">
					<div class = "col-md-12" style = "border-bottom:1px solid #78b7ef"><h4>Print Option</h4></div>
				</div>
				<!-- <form id = "barcodeType">
					<div class = "radio">
						<label>
							<input type="radio" name="barcodeType" id="optionsRadios1" value="barcode" checked>
							<span style = "margin-top:40px">Barcode</span>
						</label>
					</div>
					<div class="radio">
						<label>
							<input type="radio" name="barcodeType" id="optionsRadios2" value="qrcode"> 
							QR Code
						</label>
					</div>
				</form> -->
				<div class="form-group">
						<label for="title" class="control-label col-sm-4">Template :
					</label>
					<%
					String branch = CurrentUser.getBrncCode("SYSADMIN");
					List<Config_Print> tpl = Config_Print.retrieveTpl("T", branch);
					%>
					<div class ="col-md-5" style = "margin-bottom:3px;">
						<select id = "splitMethod" class="form-control">
						<%
							for (Config_Print i : tpl) {
						%>
							<option value="<%=i.getLB01TPLNAME()%>" data-type="<%=i.getLB01TplGrp()%>"><%=i.getLB01NOTES() %></option>
						<%
							}
						%>
						</select>
						<br>
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-sm-4">Start printing from position :</label> 
					<div class ="col-md-2" style = "margin-bottom:3px;">
						<input class="form-control" type= "number" id="number" min="1" style="width: 50%;">
						<br>
					</div>
					<div class = "col-md-3">
						<label>
							<input type="checkbox" id='unprint' class='unprint' name='unprint'>
							Unprinted records only
						</label>
					</div>
			</div>	
				 <div class ="col-md-offset-10 col-md-2 text-right" style = "display:inline"><img src="/image/preloader.gif" id = "loading" style = "width:40px;margin-top:-3px;display:none"><button type="button" class = "btn btn-primary submit" id ="accession_no" title="Search"><span class="glyphicon glyphicon-search" style="color:white"></span></button></div>
		   		</form>
	  		</div>
		</div>
		<div class = "panel panel-default">
			<!-- <div class="panel-heading">
				<h3 class="panel-title">Queries Result</h3>
				<div class="text-right">
						<span class="message"></span>
						<button type ="button" class = "btn btn-primary" id ="print" title="Print"><span class="glyphicon glyphicon-print"></span></button>
				</div> 
			</div> -->
			<div class="panel-heading clearfix">
				<div class="text-right">
					<span class="message"></span>
					<button type ="button" class = "btn btn-primary" id ="print" title="Print"><span class="glyphicon glyphicon-print"></span></button>
				</div> 
			</div>
			<div class="panel-body">
				<form method="post" id = "selectedValue"  action ="patron_print" target="printForm">
					<input type = "hidden" id = "printStyle" name = "printStyle">
					<input type = "hidden" id = "splitStyle" name = "splitStyle">
					<input type = "hidden" id = "noData" name = "noData">
					<div style = "max-height:500px;overflow: auto;">
						<table class="table table-bordered" id = "queryResult" style = "width:100%">
							<thead style="background:rgba(52, 73, 94, 0.94);color:#ecf0f1">
								<tr>
									<th><input type = "checkbox" class="checkAll" id = "checkAll"></th>
									<th data-sortable="true" style="text-align: center;">Patron ID</th>
									<th>Name</th>
									<th>Patron Category Code</th>
									<th>Department Code</th>
									<th>Course Code</th>
									<th>Membership Date</th>
								</tr>
							</thead>
							<tbody>
							<tr></tr>
							</tbody>
						</table>
						<div id="loader"></div>
					</div>
				</form>

			</div>
		</div>
		<div class="modal fade bs-example-modal-sm" id ="errorModal" tabindex="-1" role="dialog">
		  <div class="modal-dialog" role="document"  style = "width:400px">
		    <div class="modal-content">
		      <div class="modal-header">
		        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
		        <h4 class="modal-title">Search Result</h4>
		      </div>
		      <div class="modal-body">
		        <p>No record found.</p>
		      </div>
		      <div class="modal-footer">
		        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>

		      </div>
		    </div><!-- /.modal-content -->
		  </div><!-- /.modal-dialog -->
	</div><!-- /.modal -->
	</div>
</html>