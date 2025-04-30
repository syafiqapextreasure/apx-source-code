<%@ page contentType="text/html; charset=UTF-8" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

	<script type="text/javascript" src="${pageContext.request.contextPath}/js/borangkeahlian.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/eRegistration/validatedaftarAhli.js"></script>
	

	<style type="text/css">			
		.right {
			text-align: right;
		}
		.centered{
    		margin: 0 auto;
		}	
		#red {
			color: red;
		}
	</style>
</head>

<body>
	<!-- START MAIN CONTENT -->
	
	<%
     	if(null != request.getAttribute("msg")){
     		String msg = (String) request.getAttribute("msg");
     	
     %>
     <input type="text" id="msgAdd" value="<%=msg%>">
     
     <%
     	}
     %>
	
			<div class="box box-default centered" style="width:70%">
				<div class="panel panel-default">
				
					<div class="panel-heading">
						<div class="clearfix">
							<%-- <img  src="${pageContext.request.contextPath}/resources/image/ban.PNG"
								width="810" height="200"> --%>
						<center><h3><b>BORANG DAFTAR KEAHLIAN PPK</b></h3></center></div>
					</div>
						
					<div class="panel-body" id="eRegister">
						<form role="form" class="form-horizontal" id="formdataregister" enctype="multipart/form-data">
							<div class="form-group"><center>Maklumat mandatori yang perlu dimasukkan<span id="red">*</span></center></div>
							
							
							<div class="form-group">
								<div class="col-sm-12">
								<!-- <table border="1">
									<thead>
										<tr>
											<b>Terma & Syarat untuk menjadi ahli PPKP</b>
											 <p> 1. Anda mesti berdaftar dengan Portal PPJ. Jika belum sila daftar di <a href="https://user.ppj.gov.my/auth/register">sini</a>.
											 <p> 2. Anda mestilah individu warganegara Malaysia.
											 <p> 3. Pastikan maklumat anda di Portal PPJ sepertu nama, alamat, email, No K/P, no telefon adalah betul dan tepat.
										</tr>
									</thead>
								</table> -->
								
								<table border="1">
									<!-- <tr>
									<th>Terma & Syarat untuk menjadi ahli PPKP</th>
									</tr> -->
									<tr>
									<td style="width: 100%">
										<b>Terma & Syarat untuk menjadi ahli PPK</b>
										 <p> 1. Anda mestilah individu warganegara Malaysia.
										 <p> 2. Pastikan maklumat anda di Portal PPJ seperti nama, alamat, email, No K/P, no telefon adalah betul dan tepat.
										 <p> 3. Anda akan di kenakan caj kad keahlian sebanyak RM 1.00 kecuali untuk kakitangan PPJ, anak kakitangan PPJ, pasangan kakitangan PPJ, warga emas dan OKU  </p>
									</td>
										 
									</tr>
								</table>
								
								 
								</div>
							</div> 

							
							<div class="form-group">
								<div class="col-sm-4"><label>Id Pengguna Portal PPJ <span id="red">*</span></label></div>
									<div class='col-sm-5'>
										<input type="email" class="form-control" id="emailAhli" name="emailAhli">			
									</div>
							</div>
							
							<div class="form-group row">
							    <div class="col-sm-4"><label>Warganegara <span id="red">*</span></label></div>
							    <div class="col-sm-2">
							      <div class="form-check">
							      	<label><input class="form-check-input" type="radio" id="warganegara" name="warganegaraRadio" value="Y"> Ya</label>
							      </div>
							    </div>
							    <div class="col-sm-2">
							      <div class="form-check">
							        <label><input class="form-check-input" type="radio" id="warganegara" name="warganegaraRadio" value="N"> Tidak</label>
							      </div>
							    </div>
						   </div>
							
							<input type="text" class="form-control" id="jantina" name="jantina" disabled>	
							
							<div class="form-group">
								<div class="col-sm-4"><label>Lokasi Mengambil Kad Ahli<span id="red">*</span></label></div>
									<div class='col-sm-5'>
										<select class="form-control" id="branch" name="branch">
											<jsp:include page="../../../include/shared/Selection/Select_Branch.jsp"></jsp:include>
											
										</select>
								</div>
							</div> 
							
							<%-- <div class="form-group">
								<div class="col-sm-4"><label>Kategori Ahli<span id="red">*</span></label></div>
									<div class='col-sm-5'>
										<select class="form-control" id="cate" name="cate">
											<jsp:include page="../../../include/shared/Selection/Select_PatronCategory.jsp"></jsp:include> 
										</select>
								</div>
							</div>  --%>
							
							<div class="form-group">
								<div class="col-sm-4"><label>Nama</label></div>
									<div class='col-sm-7'>
										<input style="text-transform: uppercase;" type="text" class="form-control" id="namaAhli" name="namaAhli">
								</div>
							</div>
							
							<div class="form-group">
								<div class="col-sm-4"><label>No. K/P</label></div>
									<div class='col-sm-4'>
										<input type="text" class="form-control" id="ic" name="ic">
								</div>
							</div>
							
							<!-- <div class="form-group">
								<div class="col-sm-2">
									<label class="radio-inline"><input type="radio" name="icOrPass" value="ic"><b>No KP</b></label>
								</div>
								<div class="col-sm-2">
									<label class="radio-inline"><input type="radio" name="icOrPass" value="passport"><b>Passport</b></label>
								</div>
							</div>
							
							<div class="form-group">
								<div class="col-sm-4"></div>
									<div class='col-sm-3'>
										<input type="text" class="form-control" id="ic" name="ic">
										<input type="text" class="form-control" id="passport" name="passport">
									</div>
							</div> -->
							
							
							<input type="hidden" class="form-control" id="inputtarikh" name="inputtarikh" disabled>
							<input type="hidden" class="form-control" id="umur" name=umur disabled>
							<input type="hidden" value="<jsp:include page="../../../../classes/tokenAPI.jsp"></jsp:include>" id="tokenval" name="tokenval" disabled>
							
							
							<div class="form-group">
							
								<div class="col-sm-4"><label>Tarikh Lahir<span id="red">*</span></label></div>
								 <div class='col-sm-4'>
			                      	<div class="input-group date" id="dob">
			  						 	<input type="text" class="form-control inputDOB" id="inputDOB" name="inputDOB">
			  						 	<span class="input-group-addon"><i class="glyphicon glyphicon-calendar"></i></span>
									</div>
			        			</div>
							</div>
							
							
							
							<div class="form-group">
								<div class="col-sm-4"><label>Alamat/Address <span id="red">*</span></label></div>
									<div class='col-sm-8'>
										<input style="text-transform: uppercase;" type="text" class="form-control" id="alamat" name="alamat"> 
									</div>
							</div>
							<div class="form-group">
								<div class="col-sm-4"></div>
									<div class='col-sm-8'>
										<input style="text-transform: uppercase;" type="text" class="form-control" id="alamat2" name="alamat2"> 
									</div>
							</div>
							<div class="form-group">
								<div class="col-sm-4"></div>
									<div class='col-sm-8'>
										<input style="text-transform: uppercase;" type="text" class="form-control" id="alamat3" name="alamat3"> 
									</div>
							</div>
							
							<div class="form-group">
								<div class="col-sm-4"><label>Poskod <span id="red">*</span></label></div>
									<div class='col-sm-3'>
										<input type="text" class="form-control" id="poskod" name="poskod"> 
									</div>
							</div>
							
							<div class="form-group">
								<div class="col-sm-4"><label>Negeri<span id="red">*</span></label></div>
									<div class='col-sm-4'>
										<select class="form-control" id="negeri" name="negeri">
											<jsp:include page="../../../include/shared/Selection/Select_Fndcode.jsp">
												<jsp:param name="fcode" value="M" /> 
											</jsp:include> 
										</select>
									</div>
							</div>
							
							
							
							<div class="form-group">
								<div class="col-sm-4"><label>Alamat Pejabat / Sekolah/ Fakulti</label></div>
									<div class='col-sm-8'>
										<input style="text-transform: uppercase;" type="text" class="form-control" id="alamatSurat" name="alamatSurat"> 
									</div>
							</div>
							<div class="form-group">
								<div class="col-sm-4"></div>
									<div class='col-sm-8'>
										<input style="text-transform: uppercase;" type="text" class="form-control" id="alamatSurat2" name="alamatSurat2"> 
									</div>
							</div>
							<div class="form-group">
								<div class="col-sm-4"></div>
									<div class='col-sm-8'>
										<input style="text-transform: uppercase;" type="text" class="form-control" id="alamatSurat3" name="alamatSurat3"> 
									</div>
							</div>
							
							<div class="form-group">
								<div class="col-sm-4"><label>Poskod</label></div>
									<div class='col-sm-3'>
										<input type="text" class="form-control" id="poskodSurat" name="poskodSurat"> 
									</div>
							</div>
							
							<div class="form-group">
								<div class="col-sm-4"><label>Negeri/Bandar</label></div>
									<div class='col-sm-4'>
										<select class="form-control" id="negeriSurat" name="negeriSurat">
											<jsp:include page="../../../include/shared/Selection/Select_Fndcode.jsp">
												<jsp:param name="fcode" value="M" /> 
											</jsp:include>
										</select>
									</div>
							</div>
							

							<div class="form-group">
								<div class="col-sm-4"><label>No. Telefon/ HP</label></div>
									<div class='col-sm-3'>
										<input type="text" class="form-control" id="phone" name="phone">
									</div>
							</div>
							
							
							<div class="form-group">
								<div class="col-sm-4"><label>Bangsa</label></div>
									<div class="col-sm-3">
										<select class="form-control" id="race" name="race">
											<jsp:include page="../../../include/shared/Selection/Select_Fndcode.jsp">
												<jsp:param name="fcode" value="J" /> 
											</jsp:include>
										</select>
								   </div>
							</div>

							
							<!-- <div class="form-group">
								<div class="col-sm-4"><label>Warganegara</label></div>
									<div class='col-sm-3'>
										<input type="text" class="form-control" id="warganegara" name="warganegara">
									</div>
							</div> -->
							
							
							<div class="form-group">
								<div class="col-sm-4"><label>Gambar</label></div>
									<div class='col-sm-5'>
										<input type="file" class="form-control picImage" id="picImage" name="picImage" accept=".jpg, .png"/>  																	
									</div>
							</div>
							
							<div class="form-group row labelquesstaffppj">
							    <div class="col-sm-4"><label>Adakah anda kakitangan PPJ? <span id="red">*</span></label></div>
							    <div class="col-sm-2">
							      <div class="form-check">
							      	<label><input class="form-check-input" type="radio" id="staffppjYes" name="ppjRadio" value="Y"> Ya</label>
							      </div>
							    </div>
							    <div class="col-sm-2">
							      <div class="form-check">
							        <label><input class="form-check-input" type="radio" id="staffppjNo" name="ppjRadio" value="N"> Tidak</label>
							      </div>
							    </div>
						   </div>

						   <div class="form-group picpasspekerja">
								<div class="col-sm-4"><label>Sila muat naik salinan kad kakitangan anda</label></div>
									<div class='col-sm-5'>
										<input type="file" class="form-control paspekerjakakitgn" id="paspekerjakakitgn" name="paspekerjakakitgn" accept=".jpg, .png, .pdf"/>  																	
									</div>
							</div>


						   <div class="form-group row labelquespsgnppj">
							    <div class="col-sm-4"><label>Adakah Suami/Isteri anda kakitangan PPJ? <span id="red">*</span></label></div>
							    <div class="col-sm-2">
							      <div class="form-check">
							      	<label><input class="form-check-input" type="radio" id="psngnppjYes" name="ppjpsgnRadio" value="Y"> Ya</label>
							      </div>
							    </div>
							    <div class="col-sm-2">
							      <div class="form-check">
							        <label><input class="form-check-input" type="radio" id="psngnppjNo" name="ppjpsgnRadio" value="N"> Tidak</label>
							      </div>
							    </div>
						   </div>

						   <div class="form-group picpasspekerjapsgn">
								<div class="col-sm-4"><label>Sila muat naik salinan kad kakitangan Suami/Isteri anda</label></div>
									<div class='col-sm-5'>
										<input type="file" class="form-control paspekerjapsgn" id="paspekerjapsgn" name="paspekerjapsgn" accept=".jpg, .png, .pdf"/>  																	
									</div>
							</div>
							
							<div class="form-group getidpsgn">
								<div class="col-sm-4"><label>Sila masukkan No. K/P kakitangan Suami/Isteri anda</label></div>
									<div class='col-sm-3'>
										<input type="text" class="form-control" id="idPsgn" name="idPsgn">  																	
									</div>
							</div>


						   <div class="form-group row labelquestparent">
							    <div class="col-sm-4"><label>Adakah Ibu/Bapa/Penjaga anda kakitangan PPJ?<span id="red">*</span></label></div>
							    <div class="col-sm-2">
							      <div class="form-check">
							      	<label><input class="form-check-input" type="radio" id="parentStaffYes" name="parentStaffRadio" value="Y"> Ya</label>
							      </div>
							    </div>
							    <div class="col-sm-2">
							      <div class="form-check">
							        <label><input class="form-check-input" type="radio" id="parentStaffNo" name="parentStaffRadio" value="N"> Tidak</label>
							      </div>
							    </div>
						   </div>


						   <div class="form-group picpasspekerjaparent">
								<div class="col-sm-4"><label>Sila muat naik salinan kad kakitangan Ibu/Bapa/Penjaga anda</label></div>
									<div class='col-sm-5'>
										<input type="file" class="form-control paspekerjaparent" id="paspekerjaparent" name="paspekerjaparent" accept=".jpg, .png, .pdf"/>  																	
									</div>
							</div>


						   
						   <div class="form-group getidparentstaff">
								<div class="col-sm-4"><label>Sila masukkan No. K/P kakitangan Ibu/Bapa/Penjaga anda</label></div>
									<div class='col-sm-3'>
										<input type="text" class="form-control" id="idParentStaff" name="idParentStaff">  																	
									</div>
							</div>


							<div class="form-group row labelquestoku">
							    <div class="col-sm-4"><label>Adakah anda berstatus OKU? <span id="red">*</span></label></div>
							    <div class="col-sm-2">
							      <div class="form-check">
							      	<label><input class="form-check-input" type="radio" id="okuYes" name="okuRadio" value="Y"> Ya</label>
							      </div>
							    </div>
							    <div class="col-sm-2">
							      <div class="form-check">
							        <label><input class="form-check-input" type="radio" id="okuNo" name="okuRadio" value="N"> Tidak</label>
							      </div>
							    </div>
						   </div>
							  
							  
							  <div class="form-group pickadoku">
								<div class="col-sm-4"><label>Sila muat naik salinan kad OKU anda</label></div>
									<div class='col-sm-5'>
										<input type="file" class="form-control kadOKU" id="kadOKU" name="kadOKU" accept=".jpg, .png, .pdf"/>  																	
									</div>
							</div> 
							
							
							
							
							
							<fieldset class="scheduler-border parentDetail">
								<legend class="scheduler-border"></legend>

									<div class="form-group parentbukanstaff">
										<div class="col-sm-12">
											<label>*Bagi Pendaftaran Umur 5 - 12 Tahun, Makluman Ibu/Bapa/Penjaga Perlu Lengkapkan Maklumat Dibawah*</label>
										</div>
									</div>
									
									
									<div class="form-group parentstaff">
										<div class="col-sm-12">
											<label>*Maklumat Ibu/Bapa/Penjaga*</label>
										</div>
									</div>
									
									<div class="form-group divemailpatent">
										<div class="col-sm-4"><label>Email Ibu/Bapa/Penjaga<span id="red">*</span></label></div>
											<div class='col-sm-5'>
												<input type="email" class="form-control" id="emailParent" name="emailParent">			
											</div>
									</div>
									
									
									<div class="form-group textIdparent">
										<div class="col-sm-4"><label class="labeltextIdparent">No. K/P Ibu/Bapa/Penjaga</label></div>
											<div class='col-sm-3'>
												<input type="text" class="form-control" id="idParent" name="idparent">
											</div>
									</div>
											
								
							
									<div class="form-group">
										<div class="col-sm-4"><label class="labelnamaPenjaga">Nama Ibu/Bapa/Penjaga</label></div>
											<div class='col-sm-7'>
												<input style="text-transform: uppercase;" type="text" class="form-control" id="namaPenjaga" name="namaPenjaga">
										</div>
									</div>
									
									
									<div class="form-group">
										<div class="col-sm-4"><label>Alamat Pejabat</label></div>
											<div class='col-sm-8'>
												<input style="text-transform: uppercase;" type="text" class="form-control" id="alamatSuratParent" name="alamatSuratParent"> 
											</div>
									</div>
									<div class="form-group">
										<div class="col-sm-4"></div>
											<div class='col-sm-8'>
												<input style="text-transform: uppercase;" type="text" class="form-control" id="alamatSuratParent2" name="alamatSuratParent2"> 
											</div>
									</div>
									<div class="form-group">
										<div class="col-sm-4"></div>
											<div class='col-sm-8'>
												<input style="text-transform: uppercase;" type="text" class="form-control" id="alamatSuratParent3" name="alamatSuratParent3"> 
											</div>
									</div>
									
									<div class="form-group">
										<div class="col-sm-4"><label>Poskod</label></div>
											<div class='col-sm-3'>
												<input type="text" class="form-control" id="poskodSuratParent" name="poskodSuratParent"> 
											</div>
									</div>
									
									<div class="form-group">
										<div class="col-sm-4"><label>Negeri/Bandar</label></div>
											<div class='col-sm-4'>
												<select class="form-control" id="negeriSuratParent" name="negeriSuratParent">
													<jsp:include page="../../../include/shared/Selection/Select_Fndcode.jsp">
														<jsp:param name="fcode" value="M" /> 
													</jsp:include> 
												</select>
											</div>
									</div>
									
									<div class="form-group">
										<div class="col-sm-4"><label>No Telefon/ HP</label></div>
											<div class='col-sm-3'>
												<input type="text" class="form-control" id="phoneParent" name="phoneParent">
											</div>
									</div>
									
								</fieldset>
									
									<div class="modal-footer">							
									    <button type="submit" id="Register" class="btn btn-primary Register">Daftar</button>
									    <button type="button" id="cancel" class="btn btn-secondary">Batal</button>  
									</div>
										
							
							
						
						</form>
					</div>
				</div>
			</div>
		<!-- END MAIN CONTENT -->	

</body>
</html>