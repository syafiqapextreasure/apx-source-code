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
		
		var criteriaInput = $('input[type=radio][name=choice]:checked').attr('id')
		
		$('input[name=choice]').change(function(){
			$('#criteria').val('');
			var criteriaInput = $('input[type=radio][name=choice]:checked').attr('id');
			
			if(criteriaInput == 'id'){
				$('#criteria').attr('maxlength','12');
			}else{
				$('#criteria').attr('maxlength','100');
			} 
		});
	
		
		if(document.getElementById('id').checked){
			$('#criteria').attr('maxlength','12');
		} 
		
		$('#btn_submit').click(function(){
			//alert("test");
			var vendor_details = $('#criteria').val();
			var cate_id = $('#cate-id').val();
			var loginId = $('#liferayLogin').val();
			
			if (document.getElementById('id').checked) {
				var searchType = "GL14PATR";
			}else if (document.getElementById('memberdate').checked) {
				var searchType = "GL14MEMDATE";
			}else if (document.getElementById('name').checked) {
				var searchType = "GL14NAME";
			}else if (document.getElementById('expirydate').checked) {
				var searchType = "GL14EXPDATE";
			}else if (document.getElementById('oldic').checked) {
				var searchType = "GL14OLDIC";
			}else if (document.getElementById('corpid').checked) {
				var searchType = "GL14RELID";
			}else if (document.getElementById('newic').checked) {
				var searchType = "GL14NEWIC";
			}
			
			$(".close").click(function(){
				$("#criteria").val("");
				$("#id").prop("checked", true );
			});
			
			function loader(target_id) {
				$(target_id).load('Loading');
			}
			
			loader("#example1");
			
			
			/* alert(vendor_details);
			alert(searchType); */
			$.get("CriteriaSearch",{criteria:vendor_details,searchType:searchType,cate_id:cate_id,loginId:loginId},function(data_vendor){
				//alert("TEST 1");
				
				$("#display_vendor").html(data_vendor);
				///////////////
				$("#criteria").val("");
				$("#id").prop( "checked", true );
			   });
		})
	});
	</script>

                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span></button>
                        <h4 class="modal-title">Search Criteria</h4>
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
							<!-- <form role="form" class="form-horizontal" id="current_form" name="current_form" onsubmit="return send_vendor_info()"> -->
							
	            				<div class="radio">
	            					<div class="col-sm-5 col-md-5">
	  									<label><input type="radio" class="minimal" name="choice" id="id"  checked>Patron ID</label>
	  								</div>
	  								<div class="col-sm-5 col-md-5">
	  								<label>
	    							<input type="radio" class="minimal" name="choice" id="memberdate" >
	    					 		Membership Date
	  								</label>
						  			</div>
						  			<div class="clearfix"></div>
						  			<div class="col-sm-5 col-md-5">
	  								<label>
	    							<input type="radio" class="minimal" name="choice" id="name" >
	    					 		Name
	  								</label>
						  			</div>
						  			<div class="col-sm-5 col-md-5">
	  								<label>
	    							<input type="radio" class="minimal" name="choice" id="expirydate" >
	    					 		Membership Expiry Date
	  								</label>
						  			</div>
						  			<div class="col-sm-5 col-md-5">
	  								<label>
	    							<input type="radio" class="minimal" name="choice" id="oldic" >
	    					 		Old IC
	  								</label>
						  			</div>
						  			<div class="col-sm-5 col-md-5">
	  								<label>
	    							<input type="radio" class="minimal" name="choice" id="corpid" >
	    					 		Corporate ID
	  								</label>
						  			</div>
						  			<div class="col-sm-5 col-md-7">
	  								<label>
	    							<input type="radio" class="minimal" name="choice" id="newic" >
	    					 		New IC
	  								</label>
						  			</div>
						  			</div>
            				
            			<!-- 	<div class="clearfix"></div> -->
            				
            				<br>
            				<!-- <div class="form-group">
                				<label class="col-sm-2">Sort By</label>
                				<div class="col-sm-3 col-form-label">
                				<select class="form-control" id="search-type" name="search-type" onchange="updatePlaceholder()">
  								<option value="patronId">Patron Id</option>
  								<option value="patronName">Patron Name</option>
								</select>
                				</div>
            				</div> -->
            				
            				<br><br>
            				<div>
            					<div class="col-sm-5">
            					<input type="text" class="form-control" name="criteria" id="criteria">
            						<!-- <textarea rows="5" cols="90" id="criteria" name="criteria"></textarea> -->
								</div>
							</div>
                            <br><br>
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
							<!-- </form> -->
						</div>
					</div>
				</div>
			
				
				
			</div>
		</div>
	</div>
	
	