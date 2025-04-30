<%@ page import="com.ilmu.global.*, java.util.List"%>
	
	<script>
	// Update placeholder for vendor after changes in select
    function updatePlaceholder() {
    	//document.getElementById('criteria').placeholder = document.getElementById('search-type').text;
    	$("#criteria").attr("placeholder", $("#search-type option:selected").text());
    	//document.getElementById('criteria'). = "";
    	$("#criteria").val("");
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
			var vendor_details = $('#criteria1').val();
			var searchType = $('#search-type').val();
			$.get("SearchVendor",{criteria:vendor_details,search_type:searchType},function(data_vendor){
				$("#display_vendor").html(data_vendor);
			   });			
		})
	});
	</script>

	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
		<h4 class="modal-title" id="myModalLabel">Search Vendor</h4>
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
							<form role="form" class="form-horizontal">
								<div class="form-group">
									<div class="col-sm-4 col-md-4">
										<label>Search By</label> 
									</div>
									<div class="col-sm-4 col-md-4">
									<select class="form-control" id="search-type" name="search-type" onchange="updatePlaceholder()">
										<option value="vendorCode">Vendor Code</option>
										<option value="vendorName">Vendor Name</option>
									</select>
									</div>
								</div>
								<div class="clearfix"></div>
								<div class="form-group">
									<div class="col-sm-4 col-md-4">
										<label>Search Text</label>
									</div>
									<div class="col-sm-4 col-md-4">
										<input type="text" class="form-control" name="criteria" id="criteria1" placeholder="Vendor Code">
									</div>
								</div>
								<div class="form-group">
									<div class="col-sm-4 col-md-4"></div>
									<div class="col-sm-8 col-md-8">
										<button type="button" class="btn btn-info" id="btn_submit">
											<span class="glyphicon glyphicon-search"></span> Search
										</button>
										<input type="button" name="cancel" value="Cancel" class="btn btn-info" data-dismiss="modal"/>
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
					<div id="result_vendor" class="panel-collapse collapse">
						<div class="panel-body">
							<div id="display_vendor"></div>
						</div>
					</div>
				</div>
				
			</div>
		</div>
	</div>
	
	<div class="modal-footer"></div>