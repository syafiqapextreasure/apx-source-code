<%@ page contentType="text/html; charset=UTF-8" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

	<script type="text/javascript" src="${pageContext.request.contextPath}/js/enquiry/IncomeDetails2.js"></script>
 	<script type="text/javascript" src="<%= request.getContextPath() %>/plugin/moment.min.js"></script>
	<link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">
	
	<script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/1.10.16/js/jquery.dataTables.js"></script> 
	<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.16/css/jquery.dataTables.css">
    <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/responsive/2.2.0/css/responsive.dataTables.min.css"> 
    <script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/responsive/2.2.0/js/dataTables.responsive.min.js"></script>
    
    
   <!--  <script type="text/javascript" src="https://cdn.datatables.net/1.10.24/js/jquery.dataTables.min.js"></script>
    <script type="text/javascript" src="https://cdn.datatables.net/rowgroup/1.1.2/js/dataTables.rowGroup.min.js"></script> -->
    
    <script type="text/javascript" src=" //cdn.datatables.net/plug-ins/1.10.24/api/sum().js"></script>
    <script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/buttons/1.6.1/js/dataTables.buttons.min.js"></script>
	<script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/buttons/1.6.1/js/buttons.flash.min.js"></script>
	<script type="text/javascript" charset="utf8" src="https://cdnjs.cloudflare.com/ajax/libs/jszip/3.1.3/jszip.min.js"></script>
	<script type="text/javascript" charset="utf8" src="https://cdnjs.cloudflare.com/ajax/libs/pdfmake/0.1.53/pdfmake.min.js"></script>
	<script type="text/javascript" charset="utf8" src="https://cdnjs.cloudflare.com/ajax/libs/pdfmake/0.1.53/vfs_fonts.js"></script>
	<script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/buttons/1.6.1/js/buttons.html5.min.js"></script>
	<script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/buttons/1.6.1/js/buttons.print.min.js"></script>
<%-- 	<script type="text/javascript" src="<%= request.getContextPath() %>/plugin/buttons.print.js"></script> --%>
   
    

	<style type="text/css">	
		.num{
			position:absolute; 
			bottom:5; 
			right:5;
			width:200px;
			}
		.num2{
			width:auto;
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
	    
	    #modalWeed {
		    overflow-y: scroll;
		}
		
		.fundtable {
		   margin: auto;
		   width: 50% !important; 
		}
		
		tr{
		    border-top: hidden;
		}
		
		.branch_info {
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
						<form class="form-horizontal" name="incomeDetails" id="incomeDetails">

							<fieldset class="scheduler-border">
							<legend class="scheduler-border">Print Item With Transaction Types</legend>

									
							<div class="modal-body row">
							
								<div class="col-md-6 sQ1">
						  			<fieldset class="scheduler-border">
						  				<legend class="scheduler-border">Selection</legend>
						  				
						  				<div class="form-group">
											<div class="col-sm-3 col-md-3"><label>Transaction date</label></div>
												<div class="col-sm-7">
													<div class="input-daterange input-group">
														<input type="text" class="input-sm form-control"  name="txtFrom"
														id="txtFrom" autocomplete="off"/> <span class="input-group-addon">to</span>
														<input type="text" class="input-sm form-control" name="txtTo" id="txtTo" autocomplete="off"/>
													</div>
												</div>
										</div>
										
										<div class="form-check">
								  			<input class="form-check-input" type="checkbox" value="patrCate" id="chkPatronCategory">
								  			<label class="form-check-label" for="chkPatronCategory">Patron Category</label>
										</div>
										<%-- <div class="form-group">
											<div class='col-sm-7'>
													<select class="form-control" id="patronCate" name="patronCate">
														<jsp:include page="../Select_PatCate.jsp"></jsp:include> 
													</select>
											</div>
										</div> --%>
										<div class="form-check">
											<select id="patronCate" multiple="multiple"" name="patronCate">
												<jsp:include page="../Select_PatCate.jsp"></jsp:include> 
											</select>
										</div> 
										
										<br>
										<!-- <div class="form-group">
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
												</div> -->
												
												<!-- <div class="input-group">
													<input type="text" class="form-control" id="lblPatronID" name="lblPatronID">
													<button type="button" class="btn btn-info">
											          <span class="glyphicon glyphicon-search"></span> Search
											        </button> -->
													<!-- <a href="Modal_PatrSearch" id="searchPatronID" class="input-group-addon btn btn-primary searchheadLib" data-toggle="modal" data-target="#modal_searchPatronID">
														<span class="glyphicon glyphicon-option-horizontal"></span></a> -->
												<!-- </div> -->
											<!-- </div>
										</div>	 -->									
										
										<div class="form-group">
											<div class="col-sm-3 col-md-3"></div>
											<div class="col-sm-7 lblName"></div>
										</div>

										<br>
										<%-- <div class="form-check">
								  			<input class="form-check-input" type="checkbox" value="branch" id="chkBranch">
								  			<label class="form-check-label" for="chkBranch">Branch</label>
										</div>
										<div class="form-check">
											<select id="brnchData" multiple="multiple"" name="brnchData">
												<jsp:include page="../Select_Brnc.jsp"></jsp:include> 
											</select>
										</div> --%>
										
										<br>
										<div class="form-check">
								  			<input class="form-check-input" type="checkbox" value="paymode" id="chkPaymentMode">
								  			<label class="form-check-label" for="chkPaymentMode">Payment Mode *(Applicable for payment option only)</label>
										</div>
										<div class="form-check">
											<select id="paymodeData" multiple="multiple"  name="paymodeData">
												<jsp:include page="../Select_Paymode.jsp"></jsp:include> 
											</select>
										</div>
										
						  			</fieldset>
						  			<!-- <fieldset class="scheduler-border">
						  				<legend class="scheduler-border">Branch Category</legend>
						  				<div class="form-check" style="width:100%">
								  			<input class="form-check-input" type="radio" name="brancode" id="brancode" value="officerB">
								  			<label class="form-check-label" for="chkCatBranch" style="width:25%">Officer Branch</label>
								  			
								  			<input class="form-check-input" type="radio" name="brancode" id="brancode" value="patronB">
								  			<label class="form-check-label" for="chkCatBranch" style="width:25%">Patron Branch</label>
								  			
								  			<input class="form-check-input" type="radio" name="brancode" id="brancode" value="itemB">
								  			<label class="form-check-label" for="chkCatBranch">Item Branch</label>
										</div>
						  				
										
						  			</fieldset> -->
						  			
						  			
						    		<!-- Your second column here -->
						  		</div>
							
						  		<div class="col-md-3 sQ2">
						  		
						  			<%-- <div class="form-check">
							  			<input class="form-check-input" type="checkbox" value="charge" id="chkCharge">
							  			<label class="form-check-label" for="chkCharge">Charge</label>
									</div>
									<div class="form-check">
										<select id="chargeData" multiple="multiple" name="chargeData">
											<jsp:include page="../Select_Charge.jsp"></jsp:include> 
										</select>
									</div> --%>
									
									<div class="form-check">
										<label class="form-check-label">
											<input class="form-check-input" type="radio" name="trancode" id="chkCharge" value="charge">
												Charge
										</label>
										<div class="form-check">
											<select id="chargeData" multiple="multiple" name="chargeData">
												<jsp:include page="../Select_Charge.jsp"></jsp:include> 
											</select>
										</div>
									</div>
									
									<br>
									<%-- <div class="form-check">
							  			<input class="form-check-input" type="checkbox" value="payment" id="chkPayment">
							  			<label class="form-check-label" for="chkPayment">Payment</label>
									</div>
									<div class="form-check">
										<select id="payData" multiple="multiple"" name="payData">
											<jsp:include page="../Select_Payment.jsp"></jsp:include> 
										</select>
									</div> --%>
									
									<div class="form-check">
										<label class="form-check-label">
											<input class="form-check-input" type="radio" name="trancode" id="chkPayment" value="payment">
												Payment
										</label>
										<div class="form-check">
											<select id="payData" multiple="multiple"" name="payData">
												<jsp:include page="../Select_Payment.jsp"></jsp:include> 
											</select>
										</div>
									</div>
									
									<br>
									<%-- <div class="form-check">
							  			<input class="form-check-input" type="checkbox" value="override" id="chkOverride">
							  			<label class="form-check-label" for="chkOverride">Override</label>
									</div>
									<div class="form-check">
										<select id="overrideData" multiple="multiple"" name="overrideData">
											<jsp:include page="../Select_Override.jsp"></jsp:include> 
										</select>
									</div> --%>
									
									<div class="form-check">
										<label class="form-check-label">
											<input class="form-check-input" type="radio" name="trancode" id="chkOverride" value="override">
												Override
										</label>
										<div class="form-check">
											<select id="overrideData" multiple="multiple"" name="overrideData">
												<jsp:include page="../Select_Override.jsp"></jsp:include> 
											</select>
										</div>
									</div>
									
									<br>
									<%-- <div class="form-check">
							  			<input class="form-check-input" type="checkbox" value="other" id="chkOthers">
							  			<label class="form-check-label" for="chkOthers">Other</label>
									</div>
									<div class="form-check">
										<select id="otherData" multiple="multiple"" name="otherData">
											<jsp:include page="../Select_Other.jsp"></jsp:include> 
										</select>
									</div> --%>
									
									<div class="form-check">
										<label class="form-check-label">
											<input class="form-check-input" type="radio" name="trancode" id="chkOthers" value="other">
												Other
										</label>
										<div class="form-check">
											<select id="otherData" multiple="multiple"" name="otherData">
												<jsp:include page="../Select_Other.jsp"></jsp:include> 
											</select>
										</div>
									</div>
									
									<br>
									<!-- <div class="col-md-10 col-lg-10 branch_info" style="border:1px solid #ddd;text-align: right;width: 220%;height:10%;">
						                <div class="row">
							               	<div class="form-group num">
							               	    <div class="col-md-10 col-lg-10 tol">
							               			<label>Total : &nbsp</label><label id="allTotal">RM 0</label>
							                	</div>		
							                	<div class="col-md-4 col-lg-4">
							                		<label>:</label>
							                		<label id="allTotal">0</label>
							                	</div>
							                </div>
						                </div>
						             </div> -->
						  		</div>
						  		
						  		
							</div>
							
							<div class="btn-group pull-right ">	
									<div class="col-md-1"><button type="button" id="incomeReterive" class="btn btn-primary" title="Retrieve">Retrieve</span></button></div>
							</div>
							
							
							</fieldset>
							</form>		
							
							<br>
							
							<table class="table table-bordered table-striped" id="table-IncomeDetails">
								<thead>
									<tr>
										<!-- <th>Branch</th> -->
										<th>No</th>
										<!-- <th>Transaction No</th> -->
										<th>Transaction Type</th>
										<th>Transaction Date</th>
										<th>Membership ID/Name</th>
										 <th>Receipt No</th>
										<th>Officer ID/Name</th>
										<!-- <th>Accession No</th> -->
										<th>Payment Mode</th>
										<th>Amount</th>
										<!-- <th>Branch Name</th>   -->
										
									</tr>
								</thead>
								<!-- <tbody>
								</tbody> -->
								 <tfoot align="right">
								<!--	<tr> <th></th><th></th><th></th><th></th><th></th><th></th><th></th><th>Total : </th><th></th><th></th></tr> -->
								</tfoot>
							</table>
							
							<br>
							<div class="col-md-12 col-lg-12 total_info" style="border:1px solid #ddd;text-align: right;">     
						   		<div class="row">
							    	<div class="form-group divtotal">
							        	<div class="col-md-12 col-lg-12 tolQty">		
							             	<label>Total Amount: &nbsp</label><span id="allTotalAmount"> 0</span>
							             </div>	
							             </div>
							                
							              <div class="form-group">
							              	<div class="col-md-12 col-lg-12 tolAmount"></div>	
							                </div>
						         </div>
						     </div>
															
						
						
					</div>
				</div>
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