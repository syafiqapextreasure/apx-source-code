<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.sql.*" %>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head> 

	<script type="text/javascript" src="${pageContext.request.contextPath}/js/RenewalBook.js"></script>

	<style>
	     fieldset.scheduler-border {
		    border: 1px groove #ddd !important;
		    padding: 0 1.4em 1.4em 1.4em !important;
		    margin: 0 0 1.5em 0 !important;
		    -webkit-box-shadow:  0px 0px 0px 0px #000;
		            box-shadow:  0px 0px 0px 0px #000;
		}

	    legend.scheduler-border {
	        font-size: 1.2em !important;
	        font-weight: bold !important;
	        text-align: left !important;
	        width:auto;
	        padding:0 10px;
	        border-bottom:none;
	    }
     </style>
	
	
</head>

<body>
	<!-- START MAIN CONTENT -->
			<div class="box box-default">
				<div class="panel panel-default">
					<div class="panel-heading">
						<!-- <div class="clearfix"></div> -->
					</div>

					<div class="panel-body" id="display_patronReview">
					
						<form class="form-horizontal" name="bahanRenewal" id="bahanRenewal">
						
						
							<fieldset class="scheduler-border">
								<legend class="scheduler-border">Patron Details</legend>
									<div class="row">
									
										
				            			<div class="form-group">
											<label class="col-sm-2">Patron ID</label>
											<div class="col-sm-3 col-md-3 lblpatronID"></div>
										</div> 
										
										<div class="form-group">
											<div class="col-sm-2">
												<label class="form-check-label">Name</label>
											</div>
						
											<div class="col-sm-7 col-md-7 lblname"></div>
										</div>
										
										<div class="form-group">
											<div class="col-sm-2">
												<label class="form-check-label">Category</label>
											</div>
						
											<div class="col-sm-3 col-md-3 lblcate"></div>
										</div>	 
									
									</div>
							</fieldset>
						

							
							<!-- <div class="form-group">
								<div class="col-sm-2">
									<label class="form-check-label">Patron ID</label>
								</div>
			
								<div class="col-sm-3 col-md-3 lblpatronID">fffffffffff</div>
							</div>
							
							
							<div class="form-group">
								<div class="col-sm-2">
									<label class="form-check-label">Name</label>
								</div>
			
								<div class="col-sm-7 col-md-7 lblname">namaaaaaaaaaaaa</div>
							</div>
							
							<div class="form-group">
								<div class="col-sm-2">
									<label class="form-check-label">Category</label>
								</div>
			
								<div class="col-sm-3 col-md-3 lblcate">Category</div>
							</div> -->
	
							
							<div class="btn-group pull-right ">	
								<div class="col-md-1"><button type="button" id="renewbtn" class="btn btn-primary" title="Renew"> Renew</button></div>
							</div>
							
						</form>
						
						<br><br>
						<table id="renewalTable" class="table table-bordered table-striped display compact" width="100%">
						 <thead>
						 	<tr>
						 		<th width="5%">No</th>
						 		<th width="10%">Accession No</th>
						 		<th width="35%">Title</th>
						 		<th width="10%">Borrow Date</th>
						 		<th width="10%">Due Date</th>
								<th width="10%">New Date</th>
								<th width="10%">Remarks</th>
								<th width="10%">Action <!-- <input name="select_all" value="1" id="example-select-all" type="checkbox"  />--></th>
						 	</tr>
						 </thead>
						</table>
						

					</div>
				</div>
			</div>
		<!-- END MAIN CONTENT -->
		
		
		
		
	<%-- 	<div class="modal fade" id="modalreviewitem" tabindex="-1" role="dialog" aria-labelledby="ModalAdd" aria-hidden="true" data-keyboard="false" data-backdrop="static">
			<div class="modal-dialog" style="width: 70%;">
				<div class="modal-content">
					<jsp:include page="Modal_ReviewBahanRosak.jsp"></jsp:include> 
				</div>
			</div>
		</div> --%>
		

		
		
</body>
</html>