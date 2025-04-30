
	<script>
	$(document).ready(function() {

		//Div collapse
		$("#search-title").click(function(){
			$("#search_title").collapse("show");
			$("#result_title").collapse("hide");
		});
		
		$("#result-title").click(function(){
			$("#search_title").collapse("hide");
			$("#result_title").collapse("show");
		});
		
	});
	
	// Update placeholder for vendor after changes in select
    function updatePlaceholder() {
    	$("#criteria").attr("placeholder", $("#search_type option:selected").text());
    	$("#criteria").val("");
    }

	// Send title info
	function send_title_info() {	
		// Hide the search form
		$('#search_title').collapse("hide");
		// Show the result form
		$('#result_title').collapse("show").height("auto");
		return false;
	}	
	
	// Search title result from SearchTitle.jsp
	$(document).ready(function(){
		$('#btn-submit-title').click(function(){
			
			// Display loading bar
			//replaceLoader("#display_title");
			var details = $('#criteria1').val();
			var searchType = $('#search_type').val();
	
				$.get("SearchTitle",{criteria:details,search_type:searchType},function(data_title){
					$("#display_title").html(data_title);
					$('#result_title').collapse("show").height("auto");
					
			   });			
		})
	});
	</script>

	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
		<h4 class="modal-title" id="myModalLabel">Search</h4>
	</div>
	<div class="modal-body">
		<div class="panel panel-default" id="form_parent">
			<div class="panel-group">
				<div class="panel panel-default">
					<div class="panel-heading">
						<h4 class="panel-title">
							<a data-toggle="collapse" data-parent="#form_parent" href="#search_title" id="search-title">Search</a>
						</h4>
					</div>
					<div id="search_title" class="panel-collapse collapse in">
						<div class="panel-body">
							<form role="form" class="form-horizontal">
															<div class="form-group">
									<div class="col-sm-4 col-md-4">
										<label>Search By</label> 
									</div>
									<div class="col-sm-4 col-md-4">
										<select class="form-control" id="search_type" name="search_type" onchange="updatePlaceholder()">
											<option value="title">Title</option>
											<option value="name">Name</option>
											<option value="subject">Subject</option>
											<option value="publisher">Publisher</option>
											<option value="placeOfPublication">Place of Publication</option>
											<option value="yearOfPublication">Year of Publication</option>
											<option value="series">Series</option>
											<option value="callNo">Call-No.</option>
											<option value="isbn">ISBN</option>
											<option value="issn">ISSN</option>
											<option value="notesArea">Notes Area</option>
										</select>
									</div>
								</div>
								
								<div class="clearfix"></div>
								
								<div class="form-group">
									<div class="col-sm-4 col-md-4">
										<label>Search Text</label>
									</div>
									<div class="col-sm-4 col-md-4">
										<input type="text" class="form-control" name="criteria1" id="criteria1" placeholder="Title">
								</div>
								</div>
								<div class="form-group">
									<div class="col-sm-4 col-md-4"></div>
									<div class="col-sm-8 col-md-8">
										<button type="button" class="btn btn-info" id="btn-submit-title">
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
						<a data-toggle="collapse" data-parent="#form_parent" href="#result_title" id="result-title">Result</a>
					</h4>
				</div>
				<div id="result_title" class="panel-collapse collapse">
					<div class="panel-body">
						<div id="display_title"></div>
					</div>
				</div>
			</div>
			
			</div>
		</div>
	</div>
	
	<div class="modal-footer"></div>