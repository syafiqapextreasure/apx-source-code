<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.util.*"%>
<%@ page import="java.sql.*" %>
<%@page import="com.ilmu.foundation.global.GlobalSQLStatement"%>
<%@page import="com.ilmu.foundation.global.Foundation"%>
<%@ page import="com.ilmu.global.connection.DBConnection, com.ilmu.foundation.PatronDetails.PatronSearch, java.util.List"%>

	
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
		$('#result_vendor').collapse("show");//.height("auto");
		//return false;
	}
	
	// Search vendor result from SearchVendor.jsp
	$(document).ready(function(){
		//Div collapse
		$("#search").click(function(){
			$("#search_vendor").collapse("show");
			$("#result_vendor").collapse("hide");
		});
		
		$("#result").click(function(){
			alert("result");
			$("#search_vendor").collapse("hide");
			$("#result_vendor").collapse("show");
		}); 
		
		$('#btn_submit').click(function(){
			
			var vendor_details = $('#criteria').val();
			var searchType = $('#search-type').val();
			//alert(searchType + " searchType");
			var cate_id = $('#cate-id').val();
			var action = $('#action').val();
			//alert(cate_id + " cate_id");
			//alert(action);
			$.get("SearchPatron",{criteria:vendor_details,search_type:searchType,cate_id:cate_id,action:action},function(data_vendor){
				
				$("#display_vendor").html(data_vendor);

			});
		});
	});
	</script>
	
	<%
		String action = request.getParameter("action");
		System.out.println(action + " klm");
	%>

                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span></button>
                        <h4 class="modal-title" align="center">Patron Search</h4>
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
                				<label class="col-sm-3 control-label">Search By</label>
                				<div class="col-sm-5 col-md-5">
                				<select class="form-control" id="search-type" name="search-type" onchange="updatePlaceholder()">
  								<option value="select">Please Select</option>
 								<option value="patronId">Patron Id</option>
  								<option value="patronName">Name</option>
  								<option value="patrnic">New IC</option>
  								<option value="patonic">Old IC</option>
								</select>
                				</div>
            				</div>
            				
            				<div class="clearfix"></div>
            				
            				<div class="form-group">
                				<label class="col-sm-3 control-label">Search Text</label>
                				<div class="col-sm-5 col-md-5">
                					<input type="hidden" name="action" id="action" value="<%=action%>">
                    				<input type="text" class="form-control" name="criteria" id="criteria" placeholder="Please Select">
                				</div>
            				</div>
            				
            				<div class="form-group">
                                <label class="col-sm-3 control-label">Filtered By</label>
                                <div class="col-sm-5 col-md-5">
                                     <select class="form-control" id="cate_type" name="cate_type" onchange="getValue(this)">
                                     <option value="">Please Select</option>
                                     <%
											GlobalSQLStatement cat = new GlobalSQLStatement();
											List<Foundation> tpllist = cat.getCategory();
											for (Foundation e : tpllist) {
										%>
										<option value="<%=e.getGL07CATE()%>"><%=e.getGL07DESC()%></option>
										<%
									}
								%>
									 </select>
                                </div>
                                <div class="col-sm-5 col-md-2">
                                      <input type="text" class="form-control" id="cate-id" name="cate-id"  readonly>
                                </div>
                                
                            </div>
                            
							<div class="form-group">
								<div class="col-sm-4 col-md-3"></div>
								<div class="col-sm-8 col-md-8">
								<button type="button" class="btn btn-info" id="btn_submit">
										<span class="glyphicon glyphicon-search"></span> Find
								</button>
								<input type="button" class="btn btn-default" style="width: 70px" value="Reset" onclick="resetForm()" id="btn-resetForm">
								<input type="button" name="cancel" value="Cancel"  class="btn btn-default" data-dismiss="modal"/>
							</div>
							</div>
							</form>
						</div>
					</div>
				</div>
			
				<div class="panel panel-default">
					<div class="panel-heading">
						<h4 class="panel-title">
							<a data-toggle="collapse" data-parent="#form_parent" href="#result_vendor" id="result">Result</a>
						</h4>
					</div>
					<div id="result_vendor" class="panel-collapse collapse in">
						<div class="panel-body">
							<div id="display_vendor"></div>
						</div>
					</div>
				</div>
				
			</div>
		</div>
	</div>
	
	