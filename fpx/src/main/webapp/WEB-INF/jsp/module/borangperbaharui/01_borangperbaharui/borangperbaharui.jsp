<%@ page contentType="text/html; charset=UTF-8" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

	<script type="text/javascript" src="${pageContext.request.contextPath}/js/borangperbaharui.js"></script>
	
	  <style>	
		.image--cover {
		  width: 120px;
		  height: 120px;
		  border-radius: 50%;
		  object-fit: cover;
		  object-position: center right;
		}
		
		input {
			text-align: left;
		}
		
		.status-rectangle {
            width: 150px; /* Set the width of the rectangle */
            height: 40px; /* Set the height of the rectangle */
            background-color: #da3131; /* Set the background color */
            color: white; /* Set the text color */
            text-align: center; /* Center-align text */
            line-height: 40px; /* Vertically center-align text */
            font-weight: bold; /* Make the text bold */
        }
        /* Define the styles for the anchor link */
        .status-link {
            color: white; /* Set the link text color */
            text-decoration: none; /* Remove underline from the link text */
            display: block; /* Make it a block-level element to fill the entire rectangle */
            height: 100%; /* Ensure the link takes the full height of the rectangle */
        }
        
     
	  </style> 
	
</head>

<body>
	<!-- START MAIN CONTENT -->
	<div class="box box-default">
    	<div class="panel panel-default">
    		<div class="panel-heading">
    			<p align="right">
    			<!-- <button type="button" id="renewMembershipBtn" class="btn btn-primary"> Renew Membership</button> -->
    			<button id="renewMembershipBtn" class="btn btn-primary" data-toggle="modal" data-target="#modalrenewmember">Perbaharui Keahlian</button>
    			</p>
    		</div>
    		
    		<div class="panel-body" id="display">
    			<form role="form" class="form-horizontal" id="formdatapatrEnquiry" method="post">
    				<div class="panel-body" id="patrDet">
    				
    					<input type="hidden" class="renewmonth">
               							
		        		<div style="float:left">
							<img src="${pageContext.request.contextPath}/resources/image/user_default.png" id ="imgtest3" class="image--cover">
				  		</div>
				  			  		
						<div class="row">    
							
		            		<div class=" col-md-9 col-lg-10 "> 
		            			<%-- <input type="hidden" class="patrSelection" value = "mm">
								<jsp:include page="../../../module/PatronID.jsp"></jsp:include>  --%>
								<div class="form-group">
										<div class="col-sm-2"><label>ID Pengguna</label></div>
										<div class="col-sm-3">
											<input type="text" class="form-control" id="lblPatronID" name="lblPatronID" disabled>
										</div>
										<div class="col-sm-5"></div>
										 <div class="col-sm-3 status-rectangle">
									        <a class="status-link" href="https://wilmudev.ppj.gov.my/fpx/PaymentProcess?bil=y%7C%7Decu%7Bxyw" target="_blank">Bayar</a>
									    </div>
								</div>
								
								
								
								<div class="form-group">
										<div class="col-sm-2"><label>Alamat</label></div>
										<div class="col-sm-8">
											<textarea class="form-control" rows="4" id="txtAddress" name="txtAddress" disabled></textarea>
										</div>
								</div>
								
								<div class="form-group">
					            	<label class="col-sm-2">Tarikh Didaftarkan</label>
				                     	<div class="col-sm-3 col-md-3 lblDateEnrolled">
				                        </div>
				                      			
				                    <label class="col-sm-2">Tarikh Tamat Keahlian</label>
               							<div class="col-sm-3 col-md-3 lblExpiryDate">
               							</div>
				                </div>
								
				  				<div class="form-group">
					            	<label class="col-sm-2">No Telefon</label>
				                     	<div class="col-sm-3 col-md-3 lblContactNo">
				                        </div>
				                      			
				                    <label class="col-sm-2">Emel</label>
               							<div class="col-sm-4 col-md-4 lblIPAddress">
               							</div>
				                </div>
				                
				                
				               <!--  <div class="btn-group pull-right ">	
									<div class="col-md-1"><button type="button" id="renewMembershipBtn" class="btn btn-primary" title="Renew Membership"> Renew Membership</button></div>
								</div> 	 -->
								
		             		</div>
						</div>
		      		</div>
    			
    			
    				<!-- START CUSTOM TABS -->
    				
			  		
    				<div class="row">
    					<div class="col-md-12">
    						<!-- Custom Tabs -->
    						<div class="nav-tabs-custom">
    							<ul class="nav nav-tabs">
                  					<li class="active"><a href="#tab_1" data-toggle="tab"><strong>Maklumat lain</strong></a></li>
				                  	<li><a href="#tab_2" data-toggle="tab"><strong>Maklumat peribadi</strong></a></li>
                				</ul>
                				
                				<div class="tab-content">
                					<div class="tab-pane active" id="tab_1">
                						<div class="box-body">
                							<div class="row">
                								
								                <div class="form-group">
									            	<label class="col-sm-2">Bilangan Pinjaman</label>
								                     	<div class="col-sm-3 col-md-3 lblItemsChargedTD">
								                        </div>
								                      			
								                    <label class="col-sm-2">Tarikh Pinjaman Terakhir</label>
								                     	<div class="col-sm-3 col-md-3 lblLastChargeDate">
								                        </div>
								                </div>

								                <div class="form-group">
									            	<label class="col-sm-2">Bahan Lewat Pulang</label>
								                     	<div class="col-sm-3 col-md-3 lblLateReturnsTD">
								                        </div>
								                      			
								                    <label class="col-sm-2">Tarikh Pemulangan Terakhir</label>
				               							<div class="col-sm-3 col-md-3 lblLastReturnDate">
				               							</div>
								                </div>


								                <div class="form-group">
									            	<label class="col-sm-2">Bilangan bahan Hilang</label>
								                     	<div class="col-sm-3 col-md-3 lblNumLostItems">
								                        </div>
								                      			
								                    <label class="col-sm-2">Tarikh Pembaharuan Terakhir</label>
				               							<div class="col-sm-3 col-md-3 lblLastRenewDate">
				               							</div>
								                </div>

								                <div class="form-group">
									            	<label class="col-sm-2">Bilangan Penggantungan</label>
				               							<div class="col-sm-3 col-md-3 lblNumSuspension">
				               							</div>
								                      			
								                    <label class="col-sm-2">Jumlah Tertunggak</label>
								                     	<div class="col-sm-3 col-md-3 lblFinesOutstanding">
								                        </div>
								                </div>

								                <div class="form-group">
									            	<label class="col-sm-2">Bahan Pinjaman Tahun Ini</label>
				               							<div class="col-sm-3 col-md-3 lblItemsChargedYTD">
				               							</div> 
								                      			
								                    <label class="col-sm-2">Jumlah Bayaran</label>
				               							<div class="col-sm-3 col-md-3 lblFinesPaid">
				               							</div>
								                </div>

								                <div class="form-group">
									            	<label class="col-sm-2">Bahan Lewat Pulang Tahun Ini</label>
				               							<div class="col-sm-3 col-md-3 lblLateReturnsYTD">
				               							</div>
								                    
								                </div>
								             
												
                							</div><!-- /.row -->
                						</div><!-- /.box -->
                					</div><!-- /.tab-pane 1 -->
                					
                					<div class="tab-pane" id="tab_2">
                						 <div class="box-body">
                						 
                						 	<div class="form-group">
								            	<label class="col-sm-2">Id Kumpulan</label>
							                     	<div class="col-sm-3 col-md-3 lblGroupID">
							                        </div>
							                      			
							                    <label class="col-sm-2">Jabatan</label>
			               							<div class="col-sm-3 col-md-3 lblDepartment">
			               							</div>
							                </div>
							                
							                <div class="form-group">
								            	<label class="col-sm-2">Status</label>
							                     	<div class="col-sm-3 col-md-3 lblStatus">
							                        </div>
							                      			
							                    <label class="col-sm-2">Kategori</label>
			               							<div class="col-sm-3 col-md-3 lblCategory">
			               							</div>
							                </div>
							                
							                <div class="form-group">
								            	<label class="col-sm-2">Tarikh Lahir</label>
							                     	<div class="col-sm-3 col-md-3 lblDOB">
							                        </div>
							                      			
							                    <label class="col-sm-2">Jantina</label>
			               							<div class="col-sm-3 col-md-3 lblGender">
			               							</div>
							                </div>
                						 	
											
										
                						 </div>
                					</div><!-- /.tab-pane 2 -->
                					
                					
                				</div><!-- nav-tabs-custom -->
    						</div><!-- /. -->
    					</div> <!-- /.col -->
    				</div><!-- /.row -->
    				
    				<!-- END TAB CONTENT -->
			
    				<!-- END CUSTOM TABS -->
    			</form>
    		</div>
		</div>
	</div> 

	<!-- END MAIN CONTENT -->	
	
	
	
	<!-- modal tuk renewal membership -->
	<div class="modal fade" id="modalrenewmember" tabindex="-1" role="dialog" aria-labelledby="ModalAdd" aria-hidden="true" data-keyboard="false" data-backdrop="static">
			<div class="modal-dialog" style="width: 70%;">
				<div class="modal-content">
					<jsp:include page="Modal_RenewMembership.jsp"></jsp:include> 
				</div>
			</div>
	</div>

</body>
</html>