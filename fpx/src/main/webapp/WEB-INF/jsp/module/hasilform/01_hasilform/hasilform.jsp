<%@ page contentType="text/html; charset=UTF-8" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

	<script type="text/javascript" src="${pageContext.request.contextPath}/js/date.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/hasilform.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/plugin/moment-with-locales.js"></script>
    

	<style type="text/css">	
		.center {
			text-align: center;
		}	
		
		.right {
			text-align: right;
		}	
		
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
	    
		
		tr{
		    border-top: hidden;
		    align: right;
		}
		
		.branch_info {
			border-style: solid;
		    padding-top: 20px;
		    /*margin: 10px 0 20px 0;*/
		    background-color: rgba(214, 224, 226, 0.2);
		    border-top-width: 0;
		    border-bottom-width: 2px;
		    -webkit-border-radius: 3px;
		    -moz-border-radius: 3px;
		    border-radius: 3px;
		    -webkit-box-shadow: none;
		    -moz-box-shadow: none;
		    box-shadow: none;
		    -webkit-box-sizing: border-box;
		    -moz-box-sizing: border-box;
		    box-sizing: border-box;
		}
		
		tr.group,
			tr.group:hover {
			    background-color: #ddd !important;
		}
		
		.printer table{
		    counter-reset: rowNumber;
		}
		 
		.printer tr {
		    counter-increment: rowNumber;
		}
		 
		.printer tr td:nth-child(2)::before {
		    content: counter(rowNumber);
		    min-width: 1em;
		    margin-right: 0.5em;
		}
	</style>
	
</head>

<body>
	<!-- START MAIN CONTENT -->
			<div class="box box-default">
				<div class="panel panel-default">
					<div class="panel-heading">
						<h3 class="panel-title pull-left"></h3>

						<div class="clearfix"></div>
					</div>
					
					<div class="panel-body" id="display_Fund">
						<form class="form-horizontal" name="hasilform" id="hasilform">
						
						<input type="hidden" class="getLength" disabled>

							<fieldset class="scheduler-border">
							<legend class="scheduler-border"></legend>
							
							<!-- <div class="form-check">
								<label class="form-check-label">
									<input class="form-check-input" type="radio" name="trantype" id="all" value="withAll">
										Print item with all transaction types
								</label>
							</div> -->
							
							<!-- <div class="form-check">
								<label class="form-check-label">
									<input class="form-check-input" type="radio" name="trantype" id="wtihTran" value="withTranTypeVal" checked>
										Print item with transaction types
								</label>
							</div> -->
									
							<div class="modal-body row" style="width:100%";>
							
								<div class="col-md-6">
						  			<fieldset class="scheduler-border">
						  				<legend class="scheduler-border"></legend>
						  				
						  				<div class="form-group">
											<div class="col-sm-3 col-md-3"><label>Tarikh Transaksi</label></div>
												<div class="col-sm-4">
													<div class="input-daterange input-group">
														<input type="text" class="input-sm form-control"  name="tarikhtran"
															id="tarikhtran" autocomplete="off"/>
													</div>
												</div>
										</div>
										<div class="form-group branchDiv">
											<div class="col-sm-3 col-md-3"><label>Cawangan</label></div>
												<div class='col-sm-7'>
													<div class="form-check">
														<select id="branch" name="branch" class="form-control">
															<jsp:include page="../../../include/shared/Selection/Select_Branch.jsp"></jsp:include> 
														</select>
													</div>
												</div>	
										</div>
										<div class="form-group branchDiv">
											<div class="col-sm-3 col-md-3"><label for="chkPaymentMode" >Pay Mode</label></div>
												<div class='col-sm-7'>
													<div class="form-check">
														<select id="paymode" multiple="multiple" name="paymode" class="form-control">
															<jsp:include page="../../../include/shared/Selection/Select_Paymode.jsp"></jsp:include> 
														</select>
													</div>
												</div>	
										</div>
										<br>
										
										
										<%-- <div class="form-check">
								  			<input class="form-check-input" type="checkbox" value="patrCate" id="chkPatronCategory">
								  			<label class="form-check-label" for="chkPatronCategory">Patron Category</label>
										</div>
										<div class="form-check">
											<select id="patronCate" multiple="multiple" name="patronCate">
												<jsp:include page="../../Select_PatronCategory.jsp"></jsp:include> 
											</select>
										</div>
										
										<br>
										<div class="form-group">
											<div class="col-sm-3 col-md-3">
												<div class="form-check">
								  					<input class="form-check-input" type="checkbox" value="officer" id="chkOfficer">
								  					<label class="form-check-label" for="chkOfficer">Officer ID</label>
												</div>
											</div>
											<div class="col-sm-7">
												<div class="input-group">
										  			<input type="text" class="form-control" id="lblPatronID" name="lblPatronID">
										  			<span class="input-group-btn">
										   				<button class="btn btn-primary searchheadLib" type="button" data-toggle="modal" data-target="#modal_searchPatronID">...</button>
										  			</span>
												</div>
											</div>
										</div>										
										
										<div class="form-group">
											<div class="col-sm-3 col-md-3"></div>
											<div class="col-sm-7 lblName"></div>
										</div> --%>

										
										
										
						  			</fieldset>
						  			
						  			
						    		<!-- Your second column here -->
						  		</div>
							
						  		<div class="col-md-6" style="width:50%" >
						  		
						  		
									
									<%-- <div class="form-check">
										<label class="form-check-label">
											<input class="form-check-input" type="radio" name="trancode" id="chkPayment" value="payment">
												Payment
										</label>
										<div class="form-check">
											<select id="payData" multiple="multiple" name="payData">
												<jsp:include page="../Select_Payment.jsp"></jsp:include> 
											</select>
										</div>
									</div> --%>
									
									

									<div class="col-md-8 col-lg-8 branch_info" style="border:1px solid #ddd; width:100%;">
									
										<table align="right" class='tablesumkod3 tablesumkod4 ' style="width:100%">
											<thead>
												<tr>
											 		<th></th>
													<th></th>
													<th></th>
													
												</tr>
											</thead>
											
											<!-- <tfoot align="right" >
												 	<tr>
														<th></th>
														<th>Jumlah</th>
														<th></th>
														
												 	</tr>
							 				</tfoot> -->
										</table>
										
					               	<br/>
						                <!-- <div class="row">
							               	<div class="form-group">
							               	    <div class="col-md-7 col-lg-7 tol text-right">
							               			<label>Jumlah : &nbsp</label><label id="allTotal"> 0</label>
							                	</div>		
							                </div>
						                </div> -->
						             </div>
						  		</div>
						  		
						  		<div id="testing1">
						  		</div>
						  		
						  		
							</div>
							
							<div class="btn-group pull-right ">	
								<div class="col-md-1"><button type="button" id="Reterive" class="btn btn-primary" title="Retrieve"> Retrieve</button></div>
							</div>
							
							
							</fieldset>
							</form>	
							
							<br/>	

							<table id="hasilformTable" class="table table-bordered table-striped display compact" style="width:99.9%">
							 <thead>
							 	<tr>
							 		<th>Kod kewangan</th> 
							 		<th>No</th>
							 		<th>Kod</th>
							 		<th>Kod Kewangan</th>
									<th>Tajuk Kod Kewangan</th>
									<th>No Resit</th>
									<th>No ID</th>
									<th>Nama Ahli</th>
									<th>Patron Category</th>
									<th>Pay Mode</th>
									<th>Bayaran (RM)</th>
							 	</tr>
							 </thead>
							 <tfoot align="right">
							 	<tr>
							 		<th></th> 
							 		<th></th> 
							 		<th></th>
							 		<th></th>
									<th></th>
									<th></th>
									<th></th>
									<th></th>
									<th></th>
									<th>Jumlah (RM)</th>
									<th></th>
							 	</tr>
							 </tfoot>
							</table>
															
						
						
					</div>
				</div>
			</div>
			<div class="col-md-8 col-lg-8 branch_info" style="border:1px solid #ddd; width:100%; visibility:hidden;">
				<table align="right" class='tablesumkod' style="height:1px; visibility:hidden;">
											<thead>
												<tr>
											 		<th></th>
													<th></th>
													
												</tr>
											</thead>
										</table>
										
										<table align="right" class='tablesumkod2' style="height:1px; visibility:hidden;">
											<thead>
												<tr>
											 		<th></th>
													<th></th>
													
												</tr>
											</thead>
										</table>
										<table align="right" class='teststu' style="height:1px; visibility:hidden;">
											<thead>
												<tr>
											 		<th></th>
													<th></th>
													
												</tr>
											</thead>
										</table>
			</div>
		<!-- END MAIN CONTENT -->
		
		
		<!-- MODAL WHEN CLICK HEAD LIB -->
		<div class="modal fade" id="modal_searchPatronID" tabindex="-1" role="dialog" aria-labelledby="modal_searchPatronID" data-keyboard="false" data-backdrop="static"> 
			<div class="modal-dialog" style="width:55%;">
				<div class="modal-content" id="Modal_PatrSearchContent">

					<!-- Remote content load here  -->
				</div>
			</div>
		</div>
		<!-- Modal END modal search HEAD LIB -->
		
		


</body>
</html>