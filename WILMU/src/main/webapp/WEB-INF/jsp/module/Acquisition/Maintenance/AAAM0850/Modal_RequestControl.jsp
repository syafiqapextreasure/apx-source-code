<head>
<style>
.form-group{
margin-bottom : 5px;
}
/* form {
width:100%;
height:10px;
margin:;
position:relative;
}
 input {
width:100%;
height: 25px;
 } */
</style>
</head>
<!-- this div is just to check if the UI form tally with liferay UI -->
<div style="width: 100%;">
<div style="width: 220px; height: 100px; float: left; background: green;">
</div>
<div class="modal-content" style="margin-left: 16.8%;">
					<!-- Modal content-->
						<div class="modal-header">
							<h5 class="modal-title" id="modalName" align="center">form</h5>
					    	<button type="button" id="closeModalAdd" class="close" data-dismiss="modal" aria-label="Close">
					        	<span aria-hidden="true">&times;</span>
					        </button>
						</div>
						<!-- <div class="container" style="width:100%"> -->
						  <div class="panel panel-default">
						    <div class="panel-heading">General Information</div>
						    <div class="panel-body">
						<form role="form" class="form-horizontal" id="formdatareqCtrl" method="post" style="">
						<br>
							<div class="form-group">
									<div class="col-sm-1"></div>
									<div class="col-sm-2" style="font-size:14px"><label for="reqno">Request Number</label></div>
									<div class="col-sm-1"><input type="text" size="12" value="" class="reqno form-control" readonly style="height:20px; width:120px"></div>
									<div class="col-sm-1"></div>
							<!-- </div> -->
							<!-- <div class="form-group"> -->
									<div class="col-sm-1"></div>
									<div class="col-sm-2" style="font-size:14px"><label for="reqdate">Date Requested</label></div>
									<div class="col-sm-1"><input type="text" value="" class="reqdate form-control" readonly style="height:20px; width:auto"></div>
									<div class="col-sm-1"></div>
							</div>
				
								<div class="form-group">
									<div class="col-sm-3">
										<label style="padding-left:90px; font-size:14px ">
										<input type="checkbox" class="form-check-input" id="checkboxreqID" name="checkboxreqID" value="reqIDCheck" >&nbsp;Requestor ID<span class="mandatory">*</span></label>  
									</div>
									<div class="col-sm-3">
										<div class="input-group">
											<input type="text" class="form-control lblPatronID" id="requestorId" name="requestorId" style="height:20px; width:270px">
												<a href="Modal_PatrSearch" id="searchpatr" class="input-group-addon btn btn-primary searchpatr" data-toggle="modal" data-target="#modal_patrSearch4order">
												<span class="glyphicon glyphicon-th-list" style="height:2px"></span></a>
										</div>
									</div>
									<div class='col-sm-6' >
											<div id="div-reqname"></div>
										</div>
							</div>

							
							<div class="form-group">
								<div class="col-sm-1"></div>
									<div class="col-sm-2"><label style="font-size:14px">Department</label></div>
										<div class='col-sm-7'>
											<div>
												<input type="text" id="div-reqdept" class="form-control" disabled=disabled style="height:20px">
											</div>
										</div>
							</div>
							
							<div class="form-group">
									<div class="col-sm-3">
										<label style="padding-left:90px; font-size:14px">
										<input type="checkbox" class="form-check-input" id="checkboxMode" name="checkboxMode" value="modeCheck" >
										Acq Mode</label>
									</div>
									<!--  -->
									<div class="col-sm-4">
										<jsp:include page="../../../../include/shared/Selection/Select_OrderMode.jsp"></jsp:include>
									</div>
							</div>
							
							<div class="form-group">
								<div class="col-sm-1"></div>
									<div class="col-sm-2"><label style="font-size:14px">ISBN</label></div>
										<div class='col-sm-4'>
											<input type="text" class="form-control" id="input-ISBN" name="inputISBN" maxlength="13" minlength="9" style="height:20px; width:auto">
										</div>
							</div>
							
							<div class="form-group">
								<div class="col-sm-1"></div>
									<div class="col-sm-2"><label style="font-size:14px">Title<span class="mandatory">*</span></label></div>
										<div class='col-sm-6'>
											<textarea class="form-control" id="input-title" name="inputtitle" maxlength="255" minlength="5" style="height:20px">
											</textarea>
										</div>
							</div>
							
							<div class="form-group">
								<div class="col-sm-1"></div>
									<div class="col-sm-2"><label style="font-size:14px">Author</label></div>
										<div class='col-sm-6'>
											<input type="text" class="form-control" id="input-author" name="inputauthor" style="height:20px">
										</div>
							</div>
							
							<div class="form-group">
								<div class="col-sm-1"></div>
									<div class="col-sm-2"><label style="font-size:14px">Publication</label></div>
										<div class='col-sm-6'>
											<input type="text" class="form-control" id="input-pub" name="inputpub" style="height:20px">
										</div>
							</div>
							
							<div class="form-group">
								<div class="col-sm-1"></div>
									<div class="col-sm-2"><label style="font-size:14px">Edition</label></div>
										<div class='col-sm-4'>
											<input type="text" class="form-control" id="input-edition" name="inputedition" minlength="1" maxlength="50" style="height:20px">
										</div>
									<div class="col-sm-1"></div>
							</div>
							
							<div class="form-group">
								<div class="col-sm-1"></div>
									<div class="col-sm-2"><label style="font-size:14px">LC No </label></div>
										<div class='col-sm-3'>
											<input type="text" class="form-control" id="input-lcNo" name="inputlcNo" style="height:20px">
										</div>
									<!-- <div class="col-sm-2"><label>No. of copies</label></div>
										<div class='col-sm-2'>
											<input type="text" class="form-control" id="input-copies" name="inputcopies">
										</div> -->
							</div>
							<div class="form-group">
								<div class="col-sm-1"></div>
											<div class="col-sm-2"><label style="font-size:14px">No. of sets</label></div>
										<div class='col-sm-2'>
											<input size="3" type="number" class="form-control" id="input-set" name="inputset" maxlength="1" minlength="1" style="height:20px">
										</div>
									<div class="col-sm-1"></div>
							</div>
							<div class="form-group">
								<div class="col-sm-1"></div>
									<div class="col-sm-2"><label style="font-size:14px">No. of copies<span class="mandatory">*</span></label></div> 
										<div class='col-sm-2'>
											<input type="number" size="3" class="form-control" id="input-copies" name="inputcopies" style="height:20px">
										</div>
							</div>
							
							<div class="form-group">
								<div class="col-sm-1"></div>
									<div class="col-sm-2"><label style="font-size:14px">User Remarks</label></div>
										<div class='col-sm-7'>
											<textarea class="form-control" rows="3" id="remarks" name="remarks" maxlength="255" minlength="5" style="height:20px"></textarea>
										</div>
							</div>

							<div class="form-group">
								<div class="col-sm-1"></div>
									<div class="col-sm-2"><label style="font-size:14px">Control No</label></div>
										<div class='col-sm-4'>
											<input type="text" class="form-control" id="input-ctrlno" name="inputctrlno" maxlength="10" minlength="10" style="height:20px">
										</div>
										<div class="col-sm-1"></div>
							</div>
							<div class="form-group">
								<div class="col-sm-1"></div>
									<div class="col-sm-2"><label style="font-size:14px">Request Status</label></div>
										<div class='col-sm-5' id="reqStatus">
										<input type="text" value="" class="reqStatus form-control" disabled="disabled" style="height:20px">
										</div>
										<div class="col-sm-1"></div>
							</div> 
							<div class="form-group">
								<div class="col-sm-1"></div>
									<div class="col-sm-2"><label style="font-size:14px">Currency</label></div>
										<div class='col-sm-3'>
											<jsp:include page="../../../../include/shared/Selection/Select_CurrencyList.jsp"></jsp:include>
										</div>
							</div>
							
							<div class="form-group">
								<div class="col-sm-1"></div>
									<div class="col-sm-2"><label style="font-size:14px">Exchange Rate</label></div>
										<div class='col-sm-2'>
											<input type="number" step="any" class="form-control" id="input-excRate" name="inputexcRate" style="height:20px">
										</div>
							</div>
							
							<div class="form-group">
								<div class="col-sm-1"></div>
									<div class="col-sm-2"><label style="font-size:14px">Foreign Price</label></div>
										<div class='col-sm-2'>
											<input type="number" step="any" class="form-control" id="input-estima" name="inputestima" maxlength="16" minlength="4" style="height:20px">
										</div>
										<div class="col-sm-1"></div>
							</div>
							<div class="form-group">
								<div class="col-sm-1"></div>
									<div class="col-sm-2"><label style="font-size:14px">Local Price</label></div>
										<div class='col-sm-2'>
											<input type="number" step="any" class="form-control" id="input-lprice" name="inputlprice" maxlength="16" minlength="4" style="height:20px">
										</div>
										<div class="col-sm-1"></div>
							</div>
							
							<div class="form-group">
								<div class="col-sm-1"></div>
									<div class="col-sm-2"><label style="font-size:14px">Vendor Code</label></div>
									<div class="col-sm-3">
										<div class="input-group">
											<input type="text" class="form-control" id="input-vendorCode" name="inputVendorCode" style="height:20px">
											<a href="Global?type=Modal&name=Modal_Vendor" id="searchvendor" class="input-group-addon btn btn-primary" data-toggle="modal" data-target="#modal_vendorSearch">
											<span class="glyphicon glyphicon-th-list"></span></a>
										</div>
									</div>
									<div class='col-sm-6'>
										<div id="div-vendorName"></div>
									</div>
							</div>
										
							
							<div class="form-group">
								<div class="col-sm-1"></div>
									<div class="col-sm-2"><label style="font-size:14px">Remarks</label></div>
										<div class='col-sm-7'>
											<textarea class="form-control" rows="3" id="remarksofficeuse" name="remarksofficeuse" style="height:20px"></textarea>
										</div>
							</div>
							<!-- END TAB CONTENT -->
							<div class="modal-footer">
							      	<button type="submit" id="save" class="btn btn-primary save">Save</button>
							        <button type="button" id="cancel" class="btn btn-secondary" data-dismiss="modal">Cancel</button>
							        <button type="button" id="close" class="btn btn-default" data-dismiss="modal">Close</button>
						        
							</div>	
						</form>
						</div></div>
						<!-- </div> -->
					<!-- END Modal content-->
				</div>
				</div>
	
		