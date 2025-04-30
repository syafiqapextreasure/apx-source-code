	<%@ page import="com.ilmu.global.connection.*, com.ilmu.global.*, java.util.List"%>
	
	<script>
	
	$(document).on("click", "#btn_submit", function(event){

		var publ = $('#criteria').val();
		var searchType =$(".text-value").data('span');
		loader("#displayReq");
		$("#searchReq").collapse('hide');
		$("#resultReq").collapse('show');
		$.get("SearchRequestor",{criteria:publ,search_type:searchType,action:"modal"},function(data){
			$("#displayReq").html(data);
		   });		
	});
	// Update placeholder for vendor after changes in select
    function updatePlaceholder() {
    	$("#criteria").attr("placeholder", $("#search-type option:selected").text());
    	$("#criteria").val("");
    }
	
	// Send publisher info
	function sendReqInfo() {	
		// Hide the search form
		$('#searchReq').collapse("hide");
		// Show the result form
		$('#resultReq').collapse("show").height("auto");
		return false;
	}
	$(".dropdown-menu li a").click(function(){
		  $(this).parents(".input-group-btn").find('.btn').html('<span class="text-value" data-span="'+$(this).data('value')+'">' + $(this).text() + '</span> <span class="caret"></span>');
	});
	// Search publisher result from SearchPublisher.jsp
	/* $(document).ready(function(){
		$('#btn_submit').click(function(){
			
			// Display loading bar
			loader("#displayReq");
			var publ = $('#criteria').val();
			var searchType = $('#search-type').val();
			
			$.get("SearchRequestor",{criteria:publ,search_type:searchType,action:"modal"},function(data){
				$("#displayReq").html(data);
			   });			
		})
	}); */
	</script>

	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
		<h4 class="modal-title" id="myModalLabel">Search Requestor</h4>
	</div>
	
	<div class="modal-body">
		<div class="panel panel-default" id="form_parent">
			<div class="panel-group">
			
				<div class="panel panel-default">
					<div class="panel-heading">
						<h4 class="panel-title">
							<a data-toggle="collapse" data-parent="#form_parent" href="#searchReq">Search</a>
						</h4>
					</div>
					<div id="searchReq" class="panel-collapse collapse in">
						<div class="panel-body">
							<form role="form" class="form-horizontal" onsubmit="return sendReqInfo()">
								<div class="form-group">
									<label for="patronSearchMode" class="col-sm-2 control-label">Search</label>
									<div class="col-xs-10 col-sm-8 col-md-8 col-lg-8">
										<div class="input-group" id="search-type">
											<div class="input-group-btn">
												<button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
													<span class="text-value" data-span="reqID">ID</span> <span class="caret"></span>
												</button>
												<ul class="dropdown-menu">
													<li value="reqID" text="ID" class="pointer"><a data-value="reqID" >ID</a></li>
			
													<li value="reqName" text="Name" class="pointer"><a data-value="reqName" >Name</a></li>
													
													<li value="newIC" text="New IC" class="pointer"><a data-value="newIC" >New IC</a></li>
													
													<li value="oldIC" text="Old IC" class="pointer"><a data-value="oldIC" >Old IC</a></li>
			
												</ul>
											</div>
											<input field-input="" class="form-control" id="criteria" option="reqID" old-value="reqID" type="text">
											<span class="input-group-btn">
												<button class="btn btn-default" type="button" action="search" id="btn_submit">
													Search</button>
											</span>
										</div>
									</div>
									</div>
								<!-- </div>
								<div class="form-group">
									<div class="col-sm-4">
										<label>Search By</label> 
									</div>
									<div class="col-sm-4">
									<select class="form-control" id="search-type" name="search-type" onchange="updatePlaceholder()">
										<option value="reqID">ID</option>
										<option value="reqName">Name</option>
										<option value="newIC">New IC</option>
										<option value="oldIC">Old IC</option>
									</select>
									</div>
								</div>
								<div class="clearfix"></div>
								<div class="form-group">
									<div class="col-sm-4">
										<label>Search Text</label>
									</div>
									<div class="col-sm-4">
										<input type="text" class="form-control" name="criteria" id="criteria" placeholder="ID">
									</div>
								</div>
								<div class="clearfix"></div> -->
								<!--  
								<div class="form-group">
									<div class="col-sm-4">
										<label>Filtered By</label> 
									</div>
									<div class="col-sm-6">
									<select class="form-control">
										<option value="ILL">INTER LIBRARY LOAN</option>
										<option value="KA">KAKITANGAN AKADEMIK</option>
										<option value="KBA">KAKITANGAN BUKAN AKADEMIK</option>
										<option value="KIP">KAKITANGAN IBU PEJABAT</option>
										<option value="KP">KAKITANGAN PERPUSTAKAAN</option>
										<option value="KPP">KUMPULAN PENGURUSAN & PROFESSIONAL</option>
										<option value="KPT">KUMPULAN PENGURUSAN TERTINGGI</option>
										<option value="KS">KUMPULAN SOKONGAN</option>
										<option value="PEL">PELAJAR</option>
										<option value="PLT">PELATIH</option>
									</select>
									</div>
								</div>
								-->
								<!-- <div class="form-group">
									<div class="col-sm-4"></div>
									<div class="col-sm-8">
										<button type="submit" class="btn btn-info" id="btn_submit">
											<span class="glyphicon glyphicon-search"></span> Search
										</button>
										<input type="button" name="cancel" value="Cancel" class="btn btn-info" data-dismiss="modal"/>
									</div>
								</div> -->
							</form>
						</div>
					</div>
				</div>
			
				<div class="panel panel-default">
					<div class="panel-heading">
						<h4 class="panel-title">
							<a data-toggle="collapse" data-parent="#form_parent" href="#resultReq">Result</a>
						</h4>
					</div>
					<div id="resultReq" class="panel-collapse collapse">
						<div class="panel-body">
							<div id="displayReq"></div>
						</div>
					</div>
				</div>
				
			</div>
		</div>
	</div>
	
	<div class="modal-footer"></div>