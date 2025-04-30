<%@ page contentType="text/html; charset=UTF-8"%>

<!-- <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"> -->
<!DOCTYPE html>
<html lang="en">

<head>

<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.16/css/jquery.dataTables.css">
<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/responsive/2.2.0/css/responsive.dataTables.min.css">
<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/select/1.3.1/css/select.dataTables.min.css">

<script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/1.10.16/js/jquery.dataTables.js"></script>
<script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/responsive/2.2.0/js/dataTables.responsive.min.js"></script>


<script type="text/javascript" src="${pageContext.request.contextPath}/js/membershipRenewal/validateMembershipRenewal.js"></script>

<style>
.button {
	display: block;
	width: 115px;
	height: 25px;
	background: #4E9CAF;
	padding: 10px;
	text-align: center;
	border-radius: 5px;
	color: white;
	font-weight: bold;
}

div.editable {
    width: 300px;
    height: 200px;
    border: 1px solid #ccc;
    padding: 5px;
}

textarea {
  resize: none;
}

#searchPatronID {
  display: inline;
  width: 30px;
  text-align: right;
} 

#divNewExpDate{
	display:none;
}
	
</style>

</head>

<body>

<div class="container">
				<div class="col-md-12">
					<div class="col-md-2">
						<div style="margin-top: 10px;"><label>Patron Id:</label></div>
					</div>
					<div class="col-md-8" style=" margin-top: 8px;">
					<input type="text" maxlength="12" name="patronId" id="patronId" class="form-control" style=" width: 30%; display:inline;">
						<button type="button" class="btn btn-primary btn-sm search" id="btn_add" data-toggle="modal" 
							data-target="#searchPatron" title="Search Patron" ><span class="glyphicon glyphicon-search" style="color:white"></span></button>
<!-- 						<a href="Modal_MemberPatrSearch" id="searchPatronID" class="input-group-addon btn btn-primary searchheadLib" data-toggle="modal" data-target="#modal_searchPatronID" style="width: 55px;">
                        	<span class="glyphicon glyphicon-search"></span></a> -->
					</div>	
				</div>	
				<div class="col-md-12" style="margin-bottom: 10px;top: 5px;">
					<div class="col-md-2">
						<div style="margin-top: 10px;"><label>Names:</label></div>
					</div>
					<div class="col-md-8" >
						<div style="margin-top: 8px;"><span id="patronName" ></span></div>
					</div>
				</div>
				<div class="col-md-12">
					<div class="col-md-2">
						<div style="margin-top: 10px;"><label>Address:</label></div>
					</div>
					<div class="col-md-6" style=" margin-top: 12px;">						
						<textarea name="patronAddr" id="patronAddr"  cols="40" rows="5" class="form-control" style="width:auto" readonly></textarea>
					</div>	
					<div class="col-md-4">
							<button type="button" class="btn btn-primary" id="renewMembershipBtn" disabled="true"><label>Renew Membership</label></button>
					</div>	
				</div>
				<div class="col-md-12" style="top: 10px;margin-bottom: 10px;">
					<div class="form-group row">
				    	<label  class="col-sm-3 col-form-label">Membership Date:</label>
				    <div class="col-sm-3">
				      	<span id="membershipDate"></span>
				    </div>
				    <label  class="col-sm-3 col-form-label">Expired Date:</label>
				    	<div class="col-sm-3">
				      		<span id="expiredDate"></span>
				    	</div>
				  	</div>
				</div>	
				<div class="col-md-12" style="margin-bottom: 10px;">
					<div class="form-group row">
				    	<label class="col-sm-3 col-form-label">Recorded By:</label>
				    <div class="col-sm-3">
				      	<span id="recordedBy"></span>
				    </div>
				    <label id="divNewExpDate" class="col-sm-3 col-form-label">New Expiry Date:</label>
				    	<div class="col-sm-3">
				      		<span id="newExpDate"></span>
				    	</div>
				  	</div>
				</div>	
					
</div>		

				
    <div id="statusCombineDetail" class="panel-collapse collapse in">
      	<div class="panel-body" id="statusCombine">
      		<div class="row">
       			<div class="col-md-12 col-lg-12">			
					<div class="nav-tabs-custom">
						<ul id="NMInfo" class="nav nav-tabs" role="tablist">
							<li role="presentation" class="a active"><a href="#item" aria-controls="item" data-toggle="tab" aria-expanded="false"><strong>Other Information</strong></a></li>
			             	<li role="presentation" class="b"><a href="#personalInfo" aria-controls="personalInfo" data-toggle="tab"><strong>Personal Information</strong></a></li>       
						</ul>
			
						<!-- TAB CONTENT -->
						<div class="tab-content">
		
						<!-- TAB 1 -->
						<div class="tab-pane active" id="item">
							<div class="box-body">
								<form>
								  	<div class="form-group row">
								    	<label for="Status" class="col-sm-3 col-form-label">Item Charged To-Date</label>
								    <div class="col-sm-3">
								      	<span id="itemChargedToDate"></span>
								    </div>
								    <label for="Currency" class="col-sm-3 col-form-label">Last Charge Date</label>
								    	<div class="col-sm-3">
								      		<span id="lastChargeDate"></span>
								    	</div>
								  	</div>
								  	<div class="form-group row" id="forConly">
								    	<label for="borrowedByC" class="col-sm-3 col-form-label">Late Returns To-Date</label>
										    <div class="col-sm-3">
										      	<span id="lateReturnToDate"></span>
										    </div>
								    	<label for="dueDate" class="col-sm-3 col-form-label">Last Return Date</label>
									    	<div class="col-sm-3">
									      		<span id="lastReturnDate"></span>
									    	</div>
								  	</div>
								  	<div class="form-group row">
								    	<label for="ItemCategory" class="col-sm-3 col-form-label">No. of Lost Items</label>
								    <div class="col-sm-3">
								      	<span id="noOfItemLost"></span>
								    </div>
								    <label for="ForeignCost" class="col-sm-3 col-form-label">Last Renew Date</label>
								    	<div class="col-sm-3">
								      		<span id="lastRenewDate"></span>
								    	</div>
								  	</div>
								  	<div class="form-group row">
								    	<label for="SMD" class="col-sm-3 col-form-label">No. of Suspension</label>
								    <div class="col-sm-3">
								      	<span id="noOfSuspend"></span>
								    </div>
								    <label for="LocalCost" class="col-sm-3 col-form-label">Amount Outstanding</label>
								    	<div class="col-sm-3">
								      		<span id="amountOutstanding"></span>
								    	</div>
								  	</div>
								  	<div class="form-group row">
								    	<label for="Condition" class="col-sm-3 col-form-label">Item Charged YTD</label>
								    <div class="col-sm-3">
								      	<span id="itemChargedYTD"></span>
								    </div>
								    <label for="CirculatedHits" class="col-sm-3 col-form-label">Amount Paid</label>
								    	<div class="col-sm-3">
								      		<span id="amountPaid"></span>
								    	</div>
								  	</div>
								  	<div class="form-group row">
								    	<label for="CopyNo" class="col-sm-3 col-form-label">Late Returns YTD</label>
								    <div class="col-sm-3">
								      	<span id="lateReturnsYTD"></span>
								    </div>
								   </div>
								</form>
						</div>
								<!-- <div class="row">
            					</div>End row item -->
					    	</div><!--/End box body item -->
<!-- 						</div>/.End Tab item -->
			
						<!-- TAB 4 -->	 
					   	<div class="tab-pane" id="personalInfo">
					   		<div class="box-body">
							  	<div class="form-group row">
								    	<label for="Status" class="col-sm-2 col-form-label">Group ID</label>
								    <div class="col-sm-4">
								      	<span id="groupId"></span>
								    </div>
								  	</div>
								  	<div class="form-group row" id="forConly">
								    	<label for="borrowedByC" class="col-sm-2 col-form-label">Status</label>
										    <div class="col-sm-4">
										      	<span id="status"></span>
										    </div>
								  	</div>
								  	<div class="form-group row">
								    	<label for="DOB" class="col-sm-2 col-form-label">Date of Birth</label>
								    <div class="col-sm-4">
								      	<span id="DOB"></span>
								    </div>
								  	</div>
								  	<div class="form-group row">
								    	<label for="SMD" class="col-sm-2 col-form-label">Department</label>
								    <div class="col-sm-4">
								      	<span id="dept"></span>
								    </div>
								  	</div>
								  	<div class="form-group row">
								    	<label for="Condition" class="col-sm-2 col-form-label">Category</label>
								    <div class="col-sm-4">
								      	<span id="category"></span>
								    </div>
								  	</div>
								  	<div class="form-group row">
								    	<label for="CopyNo" class="col-sm-2 col-form-label">Gender</label>
								    <div class="col-sm-4">
								      	<span id="gender"></span>
								    </div>
									</div>
             				</div><!-- END box body personalInfo-->
						</div><!-- /.End Tab personalInfo -->
						</div>
						</div><!-- /.END TAB CONTENT -->
		
					</div><!-- /.END CUSTOM TAB -->
       			</div>   		
     		</div>
      	</div>
	
	<!-- Renew Membership Modal -->
	<div id="renewMembershipModal" class="modal fade" role="dialog"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog" role="document" style="width: 55%">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLabel">
						<label>Renew Membership</label>
					</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">×</span>
					</button>
				</div>
					<div class="col-md-12">
						<div class="form-group">
							<div class="col-md-3">
								<div><label style="margin-top: 10px;margin-bottom: 10px;">New Expire Date</label><br></div>
								<div><label style="margin-top: 15px;margin-bottom: 13px;">Category</label><br></div>
								<div><label style="margin-top: 15px;margin-bottom: 13px;">Fee</label><br></div>
							</div>
							<div class="col-md-5">
								<input type="text" style="margin-top: 10px;margin-bottom: 10px;" name="newExpDate" id="newExpDate_modal" disabled="true"><br>
						  		<input type="text" style="margin-top: 10px;margin-bottom: 10px;" name="category" id="category_modal" disabled="true"><br>
						  		<input type="text" style="margin-top: 10px;margin-bottom: 10px;" name="fee" id="fee_modal" disabled="true"><br>
							</div>
						</div>
					</div>
				
				<div class="modal-footer">
					<button type="button" class="btn btn-primary" id="updateMembershipBtn" data-dismiss="modal">OK</button>
					<button type="button" class="btn btn-secondary"
						data-dismiss="modal" id="getPatronIdBtn">Close</button>
				</div>
			</div>
		</div>
	</div>
	<div id="confirm" class="modal hide fade">
	  <div class="modal-body">
	    Are you sure?
	  </div>
	  <div class="modal-footer">
	    <button type="button" data-dismiss="modal" class="btn btn-primary" id="delete">Delete</button>
	    <button type="button" data-dismiss="modal" class="btn">Cancel</button>
	  </div>
</div>

<!-- open modal -->
<div class="modal fade" id="modal_searchPatronID" tabindex="-1" role="dialog" aria-labelledby="modal_searchPatronID" data-keyboard="false" data-backdrop="static"> 
            <div class="modal-dialog" style="width:55%;">
                  <div class="modal-content" id="Modal_PatrSearchContent">
                        Remote content load here 
                  </div>
            </div>
</div>
		<div class="modal fade" id="searchPatron" tabindex="-1" role="dialog" aria-labelledby="searchPatron"  aria-hidden="true" data-backdrop="static"> 
	<div class="modal-dialog" style="width:80%;">
		    <div class="modal-content" id="searchPatrContent">
			  <!-- Remote content load here -->
		  </div>
	</div>
</div>	
</body>

</html>
