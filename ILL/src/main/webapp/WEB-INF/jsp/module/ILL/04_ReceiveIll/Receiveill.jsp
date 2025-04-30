<%@ page contentType="text/html; charset=UTF-8" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

	<script type="text/javascript" src="${pageContext.request.contextPath}/js/ILL/receiveILL.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath() %>/plugin/moment.min.js"></script>
	<link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">
	
	<!-- <script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/1.10.16/js/jquery.dataTables.min.js"></script>  -->
   <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/responsive/2.2.0/css/responsive.dataTables.min.css">
    <script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/responsive/2.2.0/js/dataTables.responsive.min.js"></script>

	<script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/buttons/1.6.1/js/dataTables.buttons.min.js"></script>
	<script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/buttons/1.6.1/js/buttons.flash.min.js"></script>
	<script type="text/javascript" charset="utf8" src="https://cdnjs.cloudflare.com/ajax/libs/jszip/3.1.3/jszip.min.js"></script>
	<script type="text/javascript" charset="utf8" src="https://cdnjs.cloudflare.com/ajax/libs/pdfmake/0.1.53/pdfmake.min.js"></script>
	<script type="text/javascript" charset="utf8" src="https://cdnjs.cloudflare.com/ajax/libs/pdfmake/0.1.53/vfs_fonts.js"></script>
	<script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/buttons/1.6.1/js/buttons.html5.min.js"></script>
	<script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/buttons/1.6.1/js/buttons.print.min.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath() %>/plugin/buttons.print.js"></script>


	<style type="text/css">	
	
		@media print
		{
			html, body {  border: 1px solid white;
            height: 99%;
            page-break-after: avoid;
            page-break-before: avoid;}
			.dt-print-table, .dt-print-table thead, .dt-print-table th, .dt-print-table tr 
			{border: 0 none !important;}
			
		}
		
		.center {
			text-align: center;
		}	
		
		.right {
			text-align: right;
		}	
		
		#red {
			color: red;
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

					<div class="panel-body" id="display_illreturn">
						<form class="form-horizontal" name="illreceiveForm" id="illreceiveForm">
							<br>
							<div class="col-sm-3 acclength"></div>
							<div class="form-group">
									<!-- <div class="col-sm-1"></div> -->
										<div class="col-sm-2">
											<label><strong>ILL Reference No</strong> <span id="red">*</span></label>
										</div>
										<div class='col-sm-2'>
											<select class="form-control" id="cboReferenceNo" name="cboReferenceNo">
												<jsp:include page="../Select_Refno.jsp"></jsp:include> 
											</select>
										</div>
										
										<div class="col-md-1"><button type="button" id="detail" class="btn btn-primary" title="Ref No" data-toggle="modal" data-target="#detailRefNo"><span class="glyphicon glyphicon-option-horizontal" style="color:white"></span></button></div>
										
							</div>
							
							<div class="form-group">
								<div class="col-sm-2"><label>Call Number</label></div>
								<div class="col-sm-6 lblCallNo"></div>
							</div>
							
							<div class="form-group">
								<div class="col-sm-2"><label>Control Number</label></div>
								<div class="col-sm-6 lblControlNo"></div>
							</div>
							
							<div class="form-group">
								<div class="col-sm-2"><label></label></div>
								<div class="col-sm-7 lblTitle"></div>
							</div>
							
							<div class="form-group">
								<div class="col-sm-2">Requestor</div>
								<div class="col-sm-6 lblRequestor"></div>
								<div class="col-sm-2 lblRequestorId"></div>
							</div>
							
							<div class="form-group">
								<div class="col-sm-2">Lending Library</div>
								<div class="col-sm-6 lblLendingLib"></div>
							</div>
							
							<div class="form-group">
								<div class="col-sm-2">Requested Date</div>
								<div class="col-sm-2 lblRequestedDate"></div>
								<div class="col-sm-1"></div>
								<div class="col-sm-2">Expected Date</div>
								<div class="col-sm-2 lblExpectedDate"></div>
							</div>
							
							<div class="form-group">
								<div class="col-sm-2 col-md-2"><label>Received Date</label> <span id="red">*</span></div>
									<div class="col-sm-2">
										<div class="input-daterange input-group">
											<input type="text" class="input-sm form-control"  name="txtReceivedDate"
												id="txtReceivedDate" autocomplete="off"/> 
										</div>
									</div>
								<div class="col-sm-1"></div>
								<div class="col-sm-2 col-md-2"><label>Due Date</label></div>
									<div class="col-sm-2">
										<div class="input-daterange input-group">
											<input type="text" class="input-sm form-control"  name="txtDueDate"
												id="txtDueDate" autocomplete="off"/> 
										</div>
									</div>
							</div>
							
							<div class="form-group">
								<div class="col-sm-2"><label>Copies Received</label> <span id="red">*</span></div>
								<div class="col-sm-1">
									<input type="text" class="form-control" id="txtCopies" name="txtCopies">
								</div>
							</div>
					
							
							<!-- <div class="btn-group pull-right ">	
									<div class="col-md-1"><button type="button" id="returnReterive" class="btn btn-primary" title="Retrieve"><span class="glyphicon glyphicon-log-in" style="color:white"></span></button></div>
  									<div class="col-md-1"></div>
  									<div class="col-md-1"><button type="button" id="print" class="btn btn-primary" title="Print"><i class="glyphicon glyphicon-print" aria-hidden="true"></i></button></div>
							</div> -->
						</form>
						<br><br>
						<div class="panel panel-default">
							<div class="panel-heading">
								<!-- <div>
									<h3>Item Details</h3>
								</div> -->
								<div class="row">
									<div class="col-md-4"><button type="button" class="btn btn-primary" id="preview" data-placement="bottom" title="Preview"><span class="glyphicon glyphicon-eye-open" style="color:white"></span></button>
									<button  id='generate' class="btn btn-primary"><span class="fa fa-credit-card" style="color:white" title="Generate Accession No"></span></button>
									<button type="button" class="btn btn-primary" id="save" data-placement="bottom" title="Save"><span class="glyphicon glyphicon-save" style="color:white"></span></button></div>
								</div>
								
							</div>
				 	<div id="search" class="panel-collapse collapse in">
						<div class="panel-body"> <!-- id="cancel_orderList"> -->
							<table id="receiveIll" class="table table-bordered table-striped">
							 <thead>
							 	<tr>
							 		<th>No</th>
							 		<th>Accession No</th>
									<th>Location</th>
									<th>Item Category</th>
									<th>Condition</th>
									<th>SMD</th>
							 	</tr>
							 </thead>
							 <tbody>
	           					<tr>
	           							<td id="NOCOPY"> 1</td>
	           							<td><input type="text" class="form-control accno" id="Accession" size="10"></td>
	           							<td>
	           								<select class="form-control Loca" id="Loca">
	           									<jsp:include page="../Select_Loca.jsp"></jsp:include>
	           								</select> 
	           							</td>
	           							<td>
	           								<select class="form-control ItemCatgory" id="ItemCatgory" name="ItemCatgory">
	           									<jsp:include page="../Select_ItemCatgory.jsp"></jsp:include>
	           								</select> 	
	           							</td>
	           							<td>
	           								<select class="form-control Con" id="Con">
	           									<jsp:include page="../Select_Con.jsp"></jsp:include>
	           								</select>
	           							</td>
	           							<td>
	           								<select class="form-control SMD" id="SMD" name="SMD">
												<jsp:include page="../Select_Smd.jsp"></jsp:include>
											</select>
					      				</td>
	           						</tr> 
								</tbody>
							</table>
						</div>
					</div>
				</div>
					
					</div>
				</div>
			</div>
		<!-- END MAIN CONTENT -->
		
		
		<div class="modal fade" id="detailRefNo" tabindex="-1" role="dialog" aria-labelledby="detailRefNo" aria-hidden="true">
		  <div class="modal-dialog" role="document" style="width:65%">
		    <div class="modal-content">
		      <div class="modal-header">
		        <h5 class="modal-title" id="exampleModalLabel">Select Reference Number</h5>
		        <button type="button"  id="closerefno" class="close" data-dismiss="modal" aria-label="Close">
		          <span aria-hidden="true">&times;</span>
		        </button>
		      </div>
		      <div class="modal-body">
		    		<table id="refno" class="table table-bordered table-striped">
						<thead>
							<tr>
								<th></th>
								<th>Reference Number</th>
							 	<th>Title</th>
							 	</tr>
						</thead>
					</table>
		      </div>
		      <div class="modal-footer">
		        	 <button type="button" id="refnoOK" class="btn btn-primary">Ok</button>
		        	 <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancel</button>
		      </div>
		    </div>
		  </div>
		</div>
		
</body>
</html>