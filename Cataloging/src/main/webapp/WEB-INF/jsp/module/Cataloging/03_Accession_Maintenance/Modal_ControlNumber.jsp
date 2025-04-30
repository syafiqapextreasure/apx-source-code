	<script>
	$(document).ready(function() {
		//Div collapse
		$("#search-title").click(function(){
			$("#search_controlNum").collapse("show");
			$("#result_controlNum").collapse("hide");
		});
		
		$("#result-title").click(function(){
			$("#search_controlNum").collapse("hide");
			$("#result_controlNum").collapse("show");
		});
		
		$('#btn-submit-title').click(function(){	
			loadRecord();
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
		$('#search_controlNum').collapse("hide");
		// Show the result form
		$('#result_controlNum').collapse("show").height("auto");
		return false;
	}	
	</script>

	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
		<h4 class="modal-title" id="myModalLabel">Search Title</h4>
	</div>
	<div class="modal-body">
		<div class="panel panel-default" id="form_parent">
			<div class="panel-group">
				<div class="panel panel-default">
					<div class="panel-heading">
						<h4 class="panel-title">
							<a data-toggle="collapse" data-parent="#form_parent" href="#search_controlNum" id="search-title">Search</a>
						</h4>
					</div>
					<div id="search_controlNum" class="panel-collapse collapse in">
						<div class="panel-body">
							<form role="form" class="form-horizontal" onsubmit="return send_title_info()">
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
										<input type="text" class="form-control" name="criteria" id="criteria" placeholder="Title">
									</div>
								</div>
								
								<div class="form-group">
									<div class="col-sm-4 col-md-4"></div>
									<div class="col-sm-8 col-md-8">
										<button type="submit" class="btn btn-info" id="btn-submit-title">
											<span class="glyphicon glyphicon-search"></span> Search
										</button>
										<input type="button" name="cancel" value="Close" class="btn btn-info" data-dismiss="modal"/>
									</div>
								</div>
							</form>
						</div>
					</div>
			</div>
			
			<div class="panel panel-default">
				<div class="panel-heading">
					<h4 class="panel-title">
						<a data-toggle="collapse" data-parent="#form_parent" href="#result_controlNum" id="result-title">Result</a>
					</h4>
				</div>
				<div id="result_controlNum" class="panel-collapse collapse">
					<div class="panel-body">
						<div id="display_title"></div>
					</div>
				</div>
			</div>
			
			</div>
		</div>
	</div>
	
	<div class="modal-footer"></div>