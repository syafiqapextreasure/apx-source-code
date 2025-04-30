<%@ page contentType="text/html; charset=UTF-8" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head> 

	<script type="text/javascript" src="${pageContext.request.contextPath}/js/PPJM0550.js"></script>


	
</head>

<body>
	<!-- START MAIN CONTENT -->
			<div class="box box-default">
				<div class="panel panel-default">
					<div class="panel-heading">
						<!-- <div class="clearfix"></div> -->
					</div>

					<div class="panel-body" id="display_bilbayatan">
					
						<form class="form-horizontal" name="ppjm0550" id="ppjm0550">
						
						<input type="hidden" class="patrSelection" value = "libPatronId">
						<jsp:include page="../../../module/PatronID.jsp"></jsp:include>
						
						<div class="form-group">
								<div class="col-sm-3 col-md-3">
									 <label><input type="radio" name="chkBoxSearchCateria" value="bookingid"><span class="bookid"> Booking ID</span> </label>
								</div>
									<div class="col-sm-3">
											<input type="text" class="input-sm form-control" name="inputreservid" id="inputreservid" >
									</div>
						</div>
						
						
						<div class="form-group">
								<div class="col-sm-3 col-md-3">
									 <label><input type="radio" name="chkBoxSearchCateria" value="emailbook"><span class="emailbook"> PPJ Portal ID</span> </label>
								</div>
									<div class="col-sm-3">
											<input type="text" class="input-sm form-control" name="inputemailbooking" id="inputemailbooking" >
									</div>
						</div>
								
						</form>
					
						
						
						
						<div class="btn-group pull-right ">	
							<div class="col-md-1"><button type="button" id="Reterive" class="btn btn-primary" title="Retrieve"> Retrieve</button></div>
						</div>

						<br><br>
						<table id="bilBayaranTable" class="table table-bordered table-striped display compact" width="100%">
						 <thead>
						 	<tr>
						 		<th>Nombor Transaksi</th>
						 		<th>Tarikh</th>
						 		<th>Kod</th>   
						 		<th>Rujukan</th>
						 		<th>No Perolehan</th>
						 		<th>Bayaran</th>
								<th>Tindakan</th>
						 	</tr>
						 </thead>
						</table>
						
						<br><br> 
						
						<div id="pagingSelectedTransactionContainer"
									class="panel panel-default">
									<div class="panel-heading"></div>

									<div class="panel-footer">
										<div class="row">
											<div class="col-lg-12" payment-summary>

												<div class="panel panel-default">
													<div class="panel-body">
													<form class="form-horizontal">
													<div class="form-group">
														    <label class="control-label col-sm-2" >Jumlah Bayaran</label>
														    <div class="col-sm-3">
														      <input id="Amount" name="Amount"
																			placeholder="Received Amount"
																			class="form-control numeric" type="text" value="0.00" disabled/>
														    </div>
														  </div>
														  <div class="form-group"> 
														   <div class="col-sm-2"> 
														   </div>
														  <div class="col-sm-3"> 
														    <button	id="PayBtn" type="button" onclick="accept()" class="btn btn-primary">Teruskan Buat Bayaran</button>
														  </div>
														  </div>
														</form>
													</div>
												</div>
											</div>
										</div>
									</div>

								</div>
					</div>
				</div>
			</div>
		<!-- END MAIN CONTENT -->
		
</body>
</html>