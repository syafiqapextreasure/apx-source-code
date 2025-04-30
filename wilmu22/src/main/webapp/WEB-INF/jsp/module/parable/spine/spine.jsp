<%-- <%@ include file="/WEB-INF/jsp/module/parable/book/book_spine_controller.jsp" %>
<%@ include file="/WEB-INF/jsp/module/parable/book/book_spineScript.jsp" %> --%>
 <%@ include file="/WEB-INF/jsp/module/parable/spine/spine_controller.jsp" %>
<%@ include file="/WEB-INF/jsp/module/parable/spine/spineScript.jsp" %>
<%@page import="com.kmlink.ilmu.parable.parable_beta.Config_Print, com.kmlink.ilmu.parable.parable_beta.CurrentUser"%>

<%--  <script type="text/javascript" src="<%= request.getContextPath() %>/plugin/datatables/jquery.dataTables.min.js"></script>
 <script type="text/javascript" src="<%= request.getContextPath() %>/plugin/datatables/dataTables.bootstrap.min.js"></script>
 <link rel="stylesheet" href="<%= request.getContextPath() %>/plugin/datatables/dataTables.bootstrap.css"> --%>
 
 <link rel="stylesheet" href="${pageContext.request.contextPath}/plugins/datatables/dataTables.bootstrap.css"/>
 
  <script type="text/javascript" src="<%= request.getContextPath() %>/plugins/datatables/jquery.dataTables.min.js"></script>
 <script type="text/javascript" src="<%= request.getContextPath() %>/plugins/datatables/dataTables.bootstrap.min.js"></script>
 
	<style>
input[type=date]{
 max-width:184px;
}

.row{
margin-bottom:20px;
}

.table{
	max-width:50px !important;
	white-space: nowrap;
 	overflow: hidden;
  	text-overflow: ellipsis;
}
.option-setting{
	height:136px;
}
.form-control[readonly]{
	background-color:white !important;
}
</style>
<script>
$(document).ready(function(){
	$("[title]").tooltip();
	
	 $('.spineTable').DataTable({
		    responsive: true
		});
    //alert("dsdsd");
});
var openFile = function (event) {
    var input = event.target;
    var reader = new FileReader();
    reader.onload = function () {
        var text = reader.result;
        $("#output").html(text);
    };
    reader.readAsText(input.files[0]);
};

$(function(){
	$("#search_by").change(function(){
		if($(this).val().trim() == "catalog_date".trim()){
			$("#search_catalog_date").show();
			$("#search_accession").hide();
		}else if($(this).val().trim() == "accession_no".trim()){
			$("#search_accession").show();
			$("#search_catalog_date").hide();
		}
		
		$("input[type='text']").val("");
		
		$("#index_date1").val("");
		$("#index_date2").val("");
		$("#index_date12").val("");
		$("#index_date22").val("");
		$("#index_date13").val("");
		$("#index_date23").val("");
		$("#index_date14").val("");
		$("#index_date24").val("");
		$("#catalog_date1").val("");
		$("#catalog_date2").val("");
		$("#catalog_date12").val("");
		$("#catalog_date22").val("");
		$("#catalog_date13").val("");
		$("#catalog_date23").val("");
		$("#catalog_date14").val("");
		$("#catalog_date24").val("");
	})

})
/* 
$(function(){
	
    if($("#search_by").val() == "Catalog Date")
    {
        alert("Name Field is missing");
     //   return false;
    }
})

 */
$(function () {
	$dropdown_Branch = $('#langCurrent');
	$dropdown_Branch[0].selectedIndex = -1;
	
	$dropdown_Location = $('#langLast');
	$dropdown_Location[0].selectedIndex = -1;
});


$(function () {
    $('#rdoCurrent').change(function () {
    	$dropdown_Location = $('#langLast');
    	$dropdown_Location[0].selectedIndex = -1;
        $('#langLast').prop('disabled', true)
        var id = this.id.replace('rdo', '');
        $('#lang' + id).prop('disabled', !this.checked);
    });
});

$(function () {
    $('#rdoLast').change(function () {
    	$dropdown_Branch = $('#langCurrent');
    	$dropdown_Branch[0].selectedIndex = -1;
        $('#langCurrent').prop('disabled', true)
        var id = this.id.replace('rdo', '');
        $('#lang' + id).prop('disabled', !this.checked);
    });
});

</script>
<html>
<%-- 
<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>

<link rel="stylesheet" href="<%= request.getContextPath() %>/css/sweetalert2.min.css" rel="stylesheet">

<link rel="stylesheet" href="${pageContext.request.contextPath}/plugins/bootstrap-datepicker/css/bootstrap-datepicker.css"/>

<script src="${pageContext.request.contextPath}/plugins/sweetalert2/js/sweetalert2.min.js" ></script>

<!-- Bootstrap Date-Picker Plugin -->
<script type="text/javascript" src="<%= request.getContextPath() %>/plugins/bootstrap-datepicker/js/bootstrap-datepicker.min.js"></script>

 --%>

<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/plugins/sweetalert2.min.js" ></script>
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/sweetalert2.min.css" rel="stylesheet">
<!-- Bootstrap Date-Picker Plugin -->
<script type="text/javascript" src="<%= request.getContextPath() %>/plugins/bootstrap-datepicker/js/bootstrap-datepicker.min.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap-datepicker.css"/>


 
<div style= "padding:10px 10%">
	<div class="panel panel-default">
		<div class="panel-heading">
			<h3 class="panel-title" style="display:inline">Spine Label </h3>
			<span style="display:inline;font-size:8pt">(v2021.0.0.0)</span>
		</div>
		<div class="panel-body">
		<form class="form-horizontal" id= "form-bookLabel">	
			<div class = "row">
				<div class = "col-md-12" style = "border-bottom:1px solid #78b7ef"><h4>Filters</h4></div>
			</div>
			<div class="form-group">
		    	<label for="search_by" class="control-label col-sm-2">Search By :</label>
		    	<div class="col-sm-3">
				<select name = "search_by" id="search_by" class="form-control">
      				<option value="accession_no">Accession No Range</option>
      				<option value="accession_no_range">Accession No</option>
      				<option value="call_no">Call Number</option>
      				<option value="catalog_date">Catalog Date</option>
      				<option value ="index_date">Index Date</option>
      				<option value="control_no">Control Number</option>
      				<!-- <option value="branch">Location</option> -->
      				<option value="item_cat">Item Category</option>
      				<option value="smd">SMD</option>
      				<option value="title">Title</option>
      			</select>
				</div>
      		</div>
			<div id="queryDetail">
	 			<div class="form-group" id = "catalog_date"> 
			    	<label for="catalog_date1" class="control-label col-sm-2">Catalog Date :</label>
			    	<script>
			    	
			    	$(function(){
			    		var FromEndDate;
			    		var ToEndDate;
			    		var startDate;
			    		$('#catalog_date1').datepicker({
			    			format: "dd/mm/yyyy",
			    		    startDate: '',
			    		    endDate: FromEndDate, 
			    		    autoclose: true
			    		}).on('changeDate', function(selected){
			    		        startDate = new Date(selected.date.valueOf());
			    		        startDate.setDate(startDate.getDate(new Date(selected.date.valueOf())));
			    		        $('#catalog_date2').datepicker('setStartDate', startDate);
			    		    }); 
			    		$('#catalog_date2').datepicker({
		    			 	format: "dd/mm/yyyy",
	    		        	startDate: startDate,
		    		        endDate: ToEndDate,
		    		        autoclose: true
		    		    }).on('changeDate', function(selected){
		    		        FromEndDate = new Date(selected.date.valueOf());
		    		        FromEndDate.setDate(FromEndDate.getDate(new Date(selected.date.valueOf())));
		    		        $('#catalog_date1').datepicker('setEndDate', FromEndDate);
		    		    });
			    	})
			    	</script>
					<div class="col-sm-2">
			    	<input name="catalog_date1" id = "catalog_date1" class="form-control" type="text" required/>
					</div>
					<div class="col-sm-1">
			    	<label for="catalog_date2" style = "margin:0 10"> to </label>
					</div>
					<div class="col-sm-2">
			    	<input name="catalog_date2" id = "catalog_date2"  class="form-control" type="text" required/>
					</div>
		  		</div>
		  		<div class = "form-horizontal" id = "branch">
				<div class="form-group">
					<label for="branch" class="control-label col-sm-2">Branch :</label>
					<div class="col-sm-3">
			    	<select name="branch" class="form-control">
			    		<%
			    		for (branch branch : picklistBranch) {
			    		%>
			    		<option value ="<%=branch.getBranch()%>"><%=branch.getBranchDesc()%></option>
			    		<%
			    		}
			    		%>
			    	</select>
					</div>
					</div>
			    	<div class="form-group">
			    		<label for="catalog_date1"  class="control-label col-sm-2"><input type="radio" name="location_radio" id="location_radio_1" checked>
						Catalog Date :</label>
				    	<script>
				    	$(function(){
				    		var FromEndDate;
				    		var ToEndDate;
				    		var startDate;
				    		$('#catalog_date14').datepicker({
				    			format: "dd/mm/yyyy",
				    		    startDate: '',
				    		    endDate: FromEndDate, 
				    		    autoclose: true
				    		}).on('changeDate', function(selected){
				    		        startDate = new Date(selected.date.valueOf());
				    		        startDate.setDate(startDate.getDate(new Date(selected.date.valueOf())));
				    		        $('#catalog_date24').datepicker('setStartDate', startDate);
				    		    }); 
				    		$('#catalog_date24').datepicker({
			    			 	format: "dd/mm/yyyy",
		    		        	startDate: startDate,
			    		        endDate: ToEndDate,
			    		        autoclose: true
			    		    }).on('changeDate', function(selected){
			    		        FromEndDate = new Date(selected.date.valueOf());
			    		        FromEndDate.setDate(FromEndDate.getDate(new Date(selected.date.valueOf())));
			    		        $('#catalog_date14').datepicker('setEndDate', FromEndDate);
			    		    });
				    	})
				    	</script>
						<div class="col-sm-2">
				    	<input name="catalog_date1" id ="catalog_date14" class="form-control" type="text" readonly/>
						</div>
						<div class="col-sm-1">
				    	<label for="catalog_date2" style = "margin:0 10"> to </label>
				    	</div>
						<div class="col-sm-2">
						<input name="catalog_date2" id ="catalog_date24" class="form-control" type="text" readonly/>
						</div>
		  			</div>
		  			<div class="form-group">
			    		<label class="control-label col-sm-2" for="index_date1"><input type="radio" name="location_radio" id="location_radio_2">
						Index Date :</label>
				    	<script>
				    	$(function(){
				    		var FromEndDate;
				    		var ToEndDate;
				    		var startDate;
				    		$('#index_date14').datepicker({
				    			format: "dd/mm/yyyy",
				    		    startDate: '',
				    		    endDate: FromEndDate, 
				    		    autoclose: true
				    		}).on('changeDate', function(selected){
				    		        startDate = new Date(selected.date.valueOf());
				    		        startDate.setDate(startDate.getDate(new Date(selected.date.valueOf())));
				    		        $('#index_date24').datepicker('setStartDate', startDate);
				    		    }); 
				    		$('#index_date24').datepicker({
			    			 	format: "dd/mm/yyyy",
		    		        	startDate: startDate,
			    		        endDate: ToEndDate,
			    		        autoclose: true
			    		    }).on('changeDate', function(selected){
			    		        FromEndDate = new Date(selected.date.valueOf());
			    		        FromEndDate.setDate(FromEndDate.getDate(new Date(selected.date.valueOf())));
			    		        $('#index_date14').datepicker('setEndDate', FromEndDate);
			    		    });
				    	})
				    	</script>
						<div class="col-sm-2">
				    	<input name="index_date1" id = "index_date14" class="form-control" type="text" disabled/>
						</div>
						<div class="col-sm-1">
				    	<label for="index_date2" style = "margin:0 10"> to </label>
						</div>
						<div class="col-sm-2">
				    	<input name="index_date2" id = "index_date24"  class="form-control" type="text" disabled/>
						</div>
		  			</div>
		  		</div>
	 			<div class="form-group" id = "index_date">
			    	<label for="index_date1" class="control-label col-sm-2">Index Date :</label>
			    	<script>
			    	$(function(){
			    		var FromEndDate;
			    		var ToEndDate;
			    		var startDate;
			    		$('#index_date1').datepicker({
			    			format: "dd/mm/yyyy",
			    		    startDate: '',
			    		    endDate: FromEndDate, 
			    		    autoclose: true
			    		}).on('changeDate', function(selected){
			    		        startDate = new Date(selected.date.valueOf());
			    		        startDate.setDate(startDate.getDate(new Date(selected.date.valueOf())));
			    		        $('#index_date2').datepicker('setStartDate', startDate);
			    		    }); 
			    		$('#index_date2').datepicker({
		    			 	format: "dd/mm/yyyy",
	    		        	startDate: startDate,
		    		        endDate: ToEndDate,
		    		        autoclose: true
		    		    }).on('changeDate', function(selected){
		    		        FromEndDate = new Date(selected.date.valueOf());
		    		        FromEndDate.setDate(FromEndDate.getDate(new Date(selected.date.valueOf())));
		    		        $('#index_date1').datepicker('setEndDate', FromEndDate);
		    		    });
			    	})
			    	</script>
					<div class="col-sm-2">
			    	<input name="index_date1" id ="index_date1" class="form-control" type="text" readonly/>
					</div>
					<div class="col-sm-1">
			    	<label for="index_date2" style = "margin:0 10"> to </label>
					</div>
					<div class="col-sm-2">
			    	<input name="index_date2" id ="index_date2" class="form-control" type="text" readonly/>
					</div>
		  		</div>
	 			<div class="form-group" id = "accession_no">
			    	<label for="first_number" class="control-label col-sm-2">Range :</label>
					<div class="col-sm-2">
			    	<input name="first_number" class="form-control" type="text" maxlength="10" required/>
					</div>
					<div class="col-sm-1">
			    	<label for="second_number" style = "margin:0 10"> to </label>
					</div>
					<div class="col-sm-2">
			    	<input name="second_number" class="form-control" type="text" maxlength="10" required/>
					</div>
		  		</div>
		  		<div class="form-group" id = "accession_no_range">
			    	<label class="control-label col-sm-2" for="first_number">Range :</label>
			    	<div class="col-sm-2">
			    	<input name="first_number" class="form-control" type="text" maxlength="10" required/>
			    	</div>
			    	<div class="col-sm-1">
			    	<label for="second_number" style = "margin:0 10"> to </label>
			    	</div>
			    	<div class="col-sm-2">
			    	<input name="second_number" class="form-control" type="text" maxlength="10" disabled/>
		  			</div>
		  		</div>
		  		<div class = "form-group" id = "call_no">		
			    	<label for="call_number" class="control-label col-sm-2">Call No. :</label>
					<div class="col-sm-2">
			    	<input name="call_number1" id= "call_number1" class="form-control" type="text"/>
					</div>
					<div class="col-sm-1">
			    	<label for="call_number1" style = "margin:0 10"> to </label>
					</div>
					<div class="col-sm-2">
			    	<input name="call_number2" id = "call_number2" class="form-control" type="text"/>
					</div>
		  		</div>
		  		<div class = "form-group" id = "control_no">		
			    	<label for="control_number" class="control-label col-sm-2">Control No. :</label>
					<div class="col-sm-2">
			    	<input name="control_number1" class="form-control" type="text" maxlength="10"/>
					</div>
					<div class="col-sm-1">
			    	<label for="control_number2" style = "margin:0 10"> to </label>
					</div>
					<div class="col-sm-2">
			    	<input name="control_number2" class="form-control" type="text" maxlength="10"/>
					</div>
		  		</div>
		  		<div class = "form-horizontal" id = "item_cat">		
				<div class="form-group"> 
			    	<label for="item_category" class="control-label col-sm-2">Item Category :</label>
					<div class="col-sm-3">
			    	<select name="item_category" class="form-control">
			    		<%
			    		for (item_category itemCategories : itemCategory) {
			    		%>
			    		<option value ="<%=itemCategories.getItemCategory()%>"><%=itemCategories.getDescription() %></option>
			    		<%
			    		}
			    		%>
			    	</select>
					</div>
			    	</div>
   		 			<div class="form-group">   		 			
			    		<label for="catalog_date1" class="control-label col-sm-2"><input type="radio" name="category_radio" id="category_radio_1" checked>
						Catalog Date :</label>
				    	<script>
				    	$(function(){
				    		var FromEndDate;
				    		var ToEndDate;
				    		var startDate;
				    		$('#catalog_date12').datepicker({
				    			format: "dd/mm/yyyy",
				    		    startDate: '',
				    		    endDate: FromEndDate, 
				    		    autoclose: true
				    		}).on('changeDate', function(selected){
				    		        startDate = new Date(selected.date.valueOf());
				    		        startDate.setDate(startDate.getDate(new Date(selected.date.valueOf())));
				    		        $('#catalog_date22').datepicker('setStartDate', startDate);
				    		    }); 
				    		$('#catalog_date22').datepicker({
			    			 	format: "dd/mm/yyyy",
		    		        	startDate: startDate,
			    		        endDate: ToEndDate,
			    		        autoclose: true
			    		    }).on('changeDate', function(selected){
			    		        FromEndDate = new Date(selected.date.valueOf());
			    		        FromEndDate.setDate(FromEndDate.getDate(new Date(selected.date.valueOf())));
			    		        $('#catalog_date12').datepicker('setEndDate', FromEndDate);
			    		    });
				    	})
				    	</script>
						<div class="col-sm-2">
				    	<input name="catalog_date1" id ="catalog_date12" class="form-control" type="text" readonly/>
						</div>
						<div class="col-sm-1">
				    	<label for="catalog_date2" style = "margin:0 10"> to </label>
						</div>
						<div class="col-sm-2">
				    	<input name="catalog_date2" id ="catalog_date22" class="form-control" type="text" readonly/>
						</div>
		  			</div>
		  			<div class="form-group">
			    		<label for="index_date1"class="control-label col-sm-2"><input type="radio" name="category_radio" id="category_radio_2">
				    	Index Date :</label>
				    	<script>
				    	$(function(){
				    		var FromEndDate;
				    		var ToEndDate;
				    		var startDate;
				    		$('#index_date12').datepicker({
				    			format: "dd/mm/yyyy",
				    		    startDate: '',
				    		    endDate: FromEndDate, 
				    		    autoclose: true
				    		}).on('changeDate', function(selected){
				    		        startDate = new Date(selected.date.valueOf());
				    		        startDate.setDate(startDate.getDate(new Date(selected.date.valueOf())));
				    		        $('#index_date22').datepicker('setStartDate', startDate);
				    		    }); 
				    		$('#index_date22').datepicker({
			    			 	format: "dd/mm/yyyy",
		    		        	startDate: startDate,
			    		        endDate: ToEndDate,
			    		        autoclose: true
			    		    }).on('changeDate', function(selected){
			    		        FromEndDate = new Date(selected.date.valueOf());
			    		        FromEndDate.setDate(FromEndDate.getDate(new Date(selected.date.valueOf())));
			    		        $('#index_date12').datepicker('setEndDate', FromEndDate);
			    		    });
				    	})
				    	</script>
						<div class="col-sm-2">
				    	<input name="index_date1" id = "index_date12" class="form-control" type="text" disabled/>
						</div>
						<div class="col-sm-1">
				    	<label for="index_date2" style = "margin:0 10"> to </label>
						</div>
						<div class="col-sm-2">
				    	<input name="index_date2" id = "index_date22"  class="form-control" type="text" disabled/>
						</div>
		  			</div>
		  		</div>
		  		<div  class = "form-horizontal" id = "smd">	
				<div class="form-group">					
			    	<label for="smd" class="control-label col-sm-2">SMD :</label>
					<div class="col-sm-2">
			    	<select name="smdVal" class="form-control">
			    		<%
			    		for (smd smds : picklistSMD) {
			    		%>
			    		<option value ="<%=smds.getSMD()%>"><%=smds.getDescription()%></option>
			    		<%
			    		}
			    		%>
			    	</select>
			    	</div>
			    	</div>
			    	<div class="form-group">
			    		<label for="catalog_date1" class="control-label col-sm-2"><input type="radio" name="smd_radio" id="smd_radio_1" checked>
				    	Catalog Date :</label>
				    	<script>
				    	$(function(){
				    		var FromEndDate;
				    		var ToEndDate;
				    		var startDate;
				    		$('#catalog_date13').datepicker({
				    			format: "dd/mm/yyyy",
				    		    startDate: '',
				    		    endDate: FromEndDate, 
				    		    autoclose: true
				    		}).on('changeDate', function(selected){
				    		        startDate = new Date(selected.date.valueOf());
				    		        startDate.setDate(startDate.getDate(new Date(selected.date.valueOf())));
				    		        $('#catalog_date23').datepicker('setStartDate', startDate);
				    		    }); 
				    		$('#catalog_date23').datepicker({
			    			 	format: "dd/mm/yyyy",
		    		        	startDate: startDate,
			    		        endDate: ToEndDate,
			    		        autoclose: true
			    		    }).on('changeDate', function(selected){
			    		        FromEndDate = new Date(selected.date.valueOf());
			    		        FromEndDate.setDate(FromEndDate.getDate(new Date(selected.date.valueOf())));
			    		        $('#catalog_date13').datepicker('setEndDate', FromEndDate);
			    		    });
				    	})
				    	</script>
						<div class="col-sm-2">
				    	<input name="catalog_date1" id = "catalog_date13" class="form-control" type="text" readonly/>
						</div>
						<div class="col-sm-1">
				    	<label for="catalog_date2" style = "margin:0 10"> to </label>
						</div>
						<div class="col-sm-2">
				    	<input name="catalog_date2" id = "catalog_date23" class="form-control" type="text" readonly/>
						</div>
		  			</div>
		  			<div class="form-group">
			    			<label for="index_date1" class="control-label col-sm-2"><input type="radio" name="smd_radio" id="smd_radio_2">
							Index Date :</label>
				    	<script>
				    	$(function(){
				    		var FromEndDate;
				    		var ToEndDate;
				    		var startDate;
				    		$('#index_date13').datepicker({
				    			format: "dd/mm/yyyy",
				    		    startDate: '',
				    		    endDate: FromEndDate, 
				    		    autoclose: true
				    		}).on('changeDate', function(selected){
				    		        startDate = new Date(selected.date.valueOf());
				    		        startDate.setDate(startDate.getDate(new Date(selected.date.valueOf())));
				    		        $('#index_date23').datepicker('setStartDate', startDate);
				    		    }); 
				    		$('#index_date23').datepicker({
			    			 	format: "dd/mm/yyyy",
		    		        	startDate: startDate,
			    		        endDate: ToEndDate,
			    		        autoclose: true
			    		    }).on('changeDate', function(selected){
			    		        FromEndDate = new Date(selected.date.valueOf());
			    		        FromEndDate.setDate(FromEndDate.getDate(new Date(selected.date.valueOf())));
			    		        $('#index_date13').datepicker('setEndDate', FromEndDate);
			    		    });
				    	})
				    	</script>
						<div class="col-sm-2">
				    	<input name="index_date1" id = "index_date13"  class="form-control" type="text" disabled/>
						</div>
						<div class="col-sm-1">
				    	<label for="index_date2" style = "margin:0 10"> to </label>
						</div>
						<div class="col-sm-2">
				    	<input name="index_date2"  id = "index_date23"  class="form-control" type="text" disabled/>
						</div>
		  			</div>
		  		</div>
		  		<div class = "form-group" id = "title">		
			    	<label for="title" class="control-label col-sm-2">Title :</label>
					<div class="col-sm-2">
			    	<input name="title" class="form-control" type="text"/>
					</div>
		  		</div>
		  	</div>
			<div class="form-group">
		  		<label class="control-label col-sm-2"><input type="radio" name="rdoSelect" id="rdoCurrent" checked>Branch: </label>	
			    	<div class="col-sm-3">
			    		<select name="dropdown_branch" id = "langCurrent" class="form-control">
			    		<%
			    		for (branch branch : picklistBranch) {
			    		%>
			    		<option value ="<%=branch.getBranchDesc()%>"><%=branch.getBranchDesc()%></option>
			    		<%
			    		}
			    		%>
			    		</select>
			    	</div>
			 </div>
			 <div class="form-group">
		  		<label class="control-label col-sm-2" ><input type="radio" name="rdoSelect" id="rdoLast">Location: </label>	
		    		<div class="col-sm-3">
						<%
						List<String> location = labelLocation.getLabelLocation();
						%>
					
							<select name="dropdown_location "id = "langLast" class="form-control" disabled>
							<%
								for(int i=0;  i<location.size();  i++){

									System.out.println(labelLocation.getLabelLocation().get(i));
							%>
								<option value="<%=labelLocation.getLabelLocation().get(i)%>" data-type="<%=labelLocation.getLabelLocation().get(i)%>"><%=labelLocation.getLabelLocation().get(i) %></option> 
		  
							<%
								}
							%>
							</select>
							<br>
					
      				</div>
			 </div>
			<div class = "row">
				<div class = "col-md-12" style = "border-bottom:1px solid #78b7ef"><h4>Preferences</h4></div>
			</div>		
		<!-- 	<div class="panel panel-default col-md-3 option-setting">
				<div class="panel-body">
					<div class = "col-md-12">
						<h4>Print Format</h4>
					</div>
					<div class ="col-md-9" style = "margin-bottom:3px;">
						<div class = "radio">
							<input type='file' accept='.ini' onchange='openFile(event)'>
						</div>
						<br>
					</div>
				</div>
			</div> -->
 					<div class="form-group">
						<label for="title" class="control-label col-sm-4">Template :
					</label>
					<%
					String branch = CurrentUser.getBrncCode("SYSADMIN");
					List<Config_Print> tpl = Config_Print.retrieveTpl("N", branch);
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
		<div class="panel-heading clearfix">
				<div class="text-right">
					<span class="message"></span>
					<button type ="button" class = "btn btn-primary" id ="print" title="Print"><span class="glyphicon glyphicon-print"></span></button>
				</div> 
			</div>
		<div class="panel-body">
			<form method="post" id = "selectedValue" action ="printSpine_Details"  target="printForm">
				<input type = "hidden" id = "printStyle" name = "printStyle">
				<input type = "hidden" id = "noData" name = "noData">
				<input type = "hidden" id = "splitStyle" name = "splitStyle">
				<input type = "hidden" id = "styletype" name = "styletype">
				<textarea id = "output" name = "customStyle" style = "display:none;" rows="4" cols="50">
				</textarea>
				<div style = "max-height:500px;overflow: auto;">
					<table class="table table-bordered spineTable" id = "queryResult" style = "max-width:100%;">
						<thead style="background:rgba(52, 73, 94, 0.94);color:#ecf0f1">
							<tr>
								<th><input type = "checkbox" class="checkAll" id = "checkAll"></th>
							<!-- 	<th>Accession No</th>
								<th>Call Number</th>
								<th>Title</th> -->
								<!-- <th>Location</th>
								<th>SMD</th>
								<th>Author</th>
								<th>Volume</th>
								<th>Copy Number</th> -->
								<!-- <th>Location Desc.</th> -->
								<!-- <th>Branch</th> -->
								<!-- <th>Branch Desc.</th>
								<th>Accession Date</th>
								<th>Catalog Date</th> -->
							<!-- 	<th>Last Index Date</th>
								<th>Local Cost</th>
								<th>Currency</th>
								<th>Foreign Cost</th>
								<th>Spine Item Category</th>
								<th>Spine SMD</th>
								<th>Part No</th>
								<th>User Define Spine</th> -->
								<!-- <th>Item Category Desc.</th>
								<th>SMD Desc.</th> -->
								<!-- <th>Item Category</th>
								<th>Publication Year</th> -->
								<th>Accession No</th>
								<th>Copy No</th>
								<th>Call No</th>
							<!-- 	<th>Location</th>
								<th>SMD</th>
								<th>Author</th>
								<th>Volume</th>
								<th>Copy Number</th> -->
								<th>Volume</th>
								<!-- <th>Branch</th>-->
								<th>Title</th>
								<th>Author</th> 
								<th>Item Category</th>
								<th>SMD</th>
								<th>Location</th>
								<th>Control No</th>
								<th>Created Date</th>
								<th>Index Date</th>
								<th>Accession Date</th>
								<th>Local Cost</th>
								<th>Foreign Cost</th>
								<th>Currency</th>
								<th>Spine</th>
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