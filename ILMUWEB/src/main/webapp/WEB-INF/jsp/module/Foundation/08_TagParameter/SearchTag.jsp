<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.util.*"%>
<%@ page import="java.sql.*" %>
<%@page import="com.ilmu.foundation.global.GlobalSQLStatement"%>
<%@page import="com.ilmu.foundation.global.Foundation"%>
<%@ page import="com.ilmu.global.connection.DBConnection, com.ilmu.foundation.CurrencyCode.CurrencyCriteria, java.util.List"%>

	
	<script>
	
	
	// Update placeholder for vendor after changes in select
    function updatePlaceholder() {
    	//document.getElementById('criteria').placeholder = document.getElementById('search-type').text;
    	$("#criteria").attr("placeholder", $("#search-type option:selected").text());
    	//document.getElementById('criteria'). = "";
    	$("#criteria").val("");
    }
	
	function getValue(cate_type){
		
		var str = cate_type.options[cate_type.selectedIndex].getAttribute('value');
		var e = document.getElementById("cate_type");
		var strUser = e.options[e.selectedIndex].value;
		document.getElementById("cate-id").value=strUser;
		
		
 	}
	function resetForm() {
	    document.getElementById("current_form").reset();
	}
	
	// Send vendor info
	function send_vendor_info() {	
		// Hide the search form
		$('#search_vendor').collapse("hide");
		// Show the result form
		$('#result_vendor').collapse("show").height("auto");
		return false;
	}
	
	// Search vendor result from SearchVendor.jsp
	$(document).ready(function(){
		//Div collapse
		$("#search").click(function(){
			$("#search_vendor").collapse("show");
			$("#result_vendor").collapse("hide");
		});
		
		$("#result").click(function(){
			$("#search_vendor").collapse("hide");
			$("#result_vendor").collapse("show");
		});
		
		$('#btn_submit').click(function(){
			//alert("test");
			var vendor_details = $('#criteria').val();
			var cate_id = $('#cate-id').val();
			
			if (document.getElementById('currency').checked) {
				var searchType = "GL24FOREX";
			}else if (document.getElementById('description').checked) {
				var searchType = "GL24DESC";
			}
			
			
			//alert(vendor_details);
			//alert(searchType);
			$.get("CurrencySearch",{criteria:vendor_details,searchType:searchType,cate_id:cate_id},function(data_vendor){
				//alert("TEST 1");

				$("#display_vendor").html(data_vendor);
			   });
		})
	});
	</script>

                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span></button>
                        <h4 class="modal-title" align="center">Search Criteria</h4>
                    </div>

	<div class="modal-body">
		<div class="panel panel-default" id="form_parent">
			<div class="panel-group">
			
				<div class="panel panel-default">
					<div class="panel-heading">
						<h4 class="panel-title">
							<a data-toggle="collapse" data-parent="#form_parent" href="#search_vendor" id="search">Search</a>
						</h4>
					</div>
					<div id="search_vendor" class="panel-collapse collapse in">
						<div class="panel-body">
							<form role="form" class="form-horizontal" id="current_form" name="current_form" onsubmit="return send_vendor_info()">
							
							<div class="form-group">
								<label class="col-sm-2 "><input type="checkbox" class="minimal" name="check" id="check" value="Y"> <strong>MARC</strong></label>
								
								<div class="col-sm-2 col-md-2">
		                  		<select class="form-control" id="GL17MARC" name="GL17MARC" onchange="document.getElementById('GL16DESC').selectedIndex
		                            						= document.getElementById('GL17MARC').selectedIndex">
		                      		<!-- <option value="0">Select</option> -->
		                      		<%
		                      			GlobalSQLStatement marccode = new GlobalSQLStatement();
										List<Foundation> marc = marccode.getMARC();
										for (Foundation list : marc) {
							 		%>
							  		<option value="<%=list.getGL16MARC()%>"><%=list.getGL16MARC()%></option>
							  		<%
										}
							  		%>
								</select>
		                		</div>
		               
		                  		<div class="col-sm-6 col-md-6">
		                  		<select class="form-control" id="GL16DESC" name="GL16DESC" onchange="document.getElementById('GL17MARC').selectedIndex
		                            						= document.getElementById('GL16DESC').selectedIndex">
		                      		<!-- <option value="0">Please Select</option> -->
		                      		<%
										GlobalSQLStatement marcdesc = new GlobalSQLStatement();
										List<Foundation> marclist = marcdesc.getMARC();
										for (Foundation list : marclist) {
							  		%>
							  		<option value="<%=list.getGL16MARC()%>"><%=list.getGL16DESC()%></option>
							  		
							  		<%
										}
							  		%>
								</select>
		                		</div>
								
							</div>
							
							<div class="form-group">
								<!--Radio group-->
								<label class="col-sm-3">
								<div class="form-check">
								    <input class="form-check-input" name="group100" type="radio" id="Tag" checked>Tag
								    <label class="form-check-label" for="radio100">Tag</label>
								</div>
								
								<div class="form-check">
								    <input class="form-check-input" name="group100" type="radio" id="Description">
								    <label class="form-check-label" for="radio101">Description</label>
								</div>
								</label>
								<label class="col-sm-3">
								</label>
							</div>
            				<!-- <div class="form-group">
            				<div class="radio">
            					<div class="col-sm-5 col-md-5">
  								<label>
    							<input type="radio" class="minimal" name="choice" id="currency"  checked>
    					 		Currency Code
  								</label>
  								</div>
  								<div class="col-sm-5 col-md-5">
  								<label>
    							<input type="radio" class="minimal" name="choice" id="description" >
    					 		Description
  								</label>
					  			</div>
					  			<div class="clearfix"></div>
					  			
					  			</div>
					  		</div>
					  		
					  		<div class="form-group">
            					<div class="col-sm-5 col-md-5">
            					<input type="text" class="form-control" name="criteria" id="criteria">
            						<textarea rows="5" cols="90" id="criteria" name="criteria"></textarea>
								</div>
							</div> -->
                            
							<div class="form-group">
								<div class="col-sm-4 col-md-3"></div>
								<div class="col-sm-8 col-md-8">
								<button type="button" class="btn btn-info" id="btn_submit" onclick="send()" data-dismiss="modal">
										<span class="glyphicon glyphicon-search"></span> Find
								</button>
								<input type="button" class="btn btn-default" style="width: 70px" value="Reset" onclick="resetForm()" id="btn-resetForm">
								<input type="button" name="cancel" value="Cancel" class="btn btn-default" data-dismiss="modal"/>
							</div>
							</div>
							</form>
						</div>
					</div>
				</div>
			
				
				
			</div>
		</div>
	</div>
	
	