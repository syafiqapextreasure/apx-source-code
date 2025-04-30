
<script type="text/javascript" src="${pageContext.request.contextPath}/js/acquisition/crddbtNote.js"></script>

<!-- <script>
	$(document).ready(function(){
		$('#search').click(function(){
			
			//loadCreditDebitRecord();
			
			var input_criteria = $('#input-criteria').val();
			//replaceLoader("#display_CDNote");
			var search_type = $("input[name='searchSelection']:checked").val();

			url = "resultSearchCrdDbt?criteria="+input_criteria+"&searchType="+search_type;  //resultSearchCrdDbt CreditDebitMaint
			///$('#cdNote').DataTable().clear().draw();
			//$("#cdNote").empty();
			//$('#cdNote').html (""); 
			$.get(url,function(data){
				/////$('#cdNote').append ('Your html content');
				//$("#cdNote").load();
				//$('#cdNote').DataTable().ajax.reload();
				$("#display_CDNote").html(data);
				//alert(data);
				//$("#cdNote").html(data);
			});	
			$("#closeFindMode").click();
		});
	
	});

</script> -->

<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal" aria-label="Close" id="closeFindMode"><span aria-hidden="true">&times;</span></button>
		<h4 class="modal-title" id="myModalLabel">Find Mode</h4>
	</div>
	
	<div class="modal-body">
		<div class="panel panel-default" id="form_parent">
			<div class="panel-group">
				<div class="panel panel-default">
					<div class="panel-heading">
						<h4 class="panel-title">
							<a data-toggle="collapse" data-parent="#form_parent" href="#search-creditdebit"></a>
						</h4>
					</div>
					<div id="search-creditdebit" class="panel-collapse collapse in">
						<div class="panel-body">
							<form role="form" class="form-horizontal">
							
								<div class="form-group">
									<div class="col-sm-1"><input type="radio" id="search-type" name="searchSelection" value=vendorCode checked></div>
									<div class="col-sm-3"><label>Vendor Code</label></div>
									<div class="col-sm-1"><input type="radio" id="search-type" name="searchSelection" value="receivedDate"></div>
									<div class="col-sm-3"><label>Received Date</label></div>
								</div>
								
								<div class="form-group">
									<div class="col-sm-1"><input type="radio" id="search-type" name="searchSelection" value="noteNo"></div>
									<div class="col-sm-3"><label>Note No</label></div>
									<div class="col-sm-1"><input type="radio" id="search-type" name="searchSelection" value="usedDate"></div>
									<div class="col-sm-3"><label>Used Date</label></div>
								</div>
									
								<div class="form-group">
									<div class="col-sm-12 col-md-12">
										<input type="text" class="form-control" name="input-criteria" id="input-criteria">
									</div>
								</div>
				
								<div class="form-group">
									<div class="col-sm-8 col-md-8">
										<button type="button" class="btn btn-info" id="search">
											<span class="glyphicon glyphicon-search"></span> Search
										</button>
										<input type="button" name="cancel" value="Cancel" class="btn btn-info" data-dismiss="modal"/>
									</div>
								</div>
								
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	
	<div class="modal-footer"></div>