<%@ page contentType="text/html; charset=UTF-8" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

	<script type="text/javascript" src="${pageContext.request.contextPath}/js/eRegistration/daftarAhli.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/eRegistration/validatedaftarAhli.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath() %>/plugin/moment.min.js"></script>
	<link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">

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
		/* input#namaAhli{text-transform:uppercase};
		input#alamat{text-transform:uppercase};
		input#alamat2{text-transform:uppercase};
		input#alamat3{text-transform:uppercase};
		input#alamatSurat{text-transform:uppercase};
		input#alamatSurat2{text-transform:uppercase};
		input#alamatSurat3{text-transform:uppercase}; */
	</style>
</head>

<body>
	<!-- START MAIN CONTENT -->
	
			<div class="box box-default centered" style="width:850px">
				<div class="panel panel-default">
				
					<div class="panel-heading">
						<div class="clearfix"><center><h3><b>Daftar Ahli/ User Self-Registration</b></h3></center></div>
					</div>
						
					<div class="panel-body" id="eRegister">
						<form role="form" class="form-horizontal" id="formdataregister" method="post">
							<div class="form-group"><center>The fields in <span id="red">*</span> are required fields</center></div>
							<div class="form-group"><center>Sila gunakan huruf BESAR</center></div>
							
							<div class="form-group">
								<div class="col-sm-3"><label>Nama/Name<span id="red">*</span></label></div>
									<div class='col-sm-7'>
										<input style="text-transform: uppercase;" type="text" class="form-control" id="namaAhli" name="namaAhli">
								</div>
							</div>
							
							<%-- <div class="form-group">
								<div class="col-sm-3"><label>Gelaran/Salutation</label></div>
									<div class='col-sm-5'>
										<select class="form-control" id="gelaran" name="gelaran">
											<jsp:include page="../Select_Title.jsp"></jsp:include>
										</select>
								</div>
							</div> --%>
							
							<div class="form-group">
								<div class="col-sm-3"><label>No. Kad Pengenalan/IC <span id="red">*</span></label></div>
									<div class='col-sm-3'>
										<input type="text" class="form-control" id="ic" name="ic">
									</div>
							</div>
							
							<div class="form-group">
								<div class="col-sm-3"><label>Tarikh Lahir/D.O.B <span id="red">*</span></label></div>
								 <div class='col-sm-4'>
			                      	<div class="input-group date" id="dob">
			  						 	<input type="text" class="form-control" id="inputDOB" name="inputDOB">
			  						 	<span class="input-group-addon"><i class="glyphicon glyphicon-calendar"></i></span>
									</div>
			        			</div>
							</div>
							
							<div class="form-group">
								<div class="col-sm-3"><label>Alamat/Address <span id="red">*</span></label></div>
									<div class='col-sm-8'>
										<input style="text-transform: uppercase;" type="text" class="form-control" id="alamat" name="alamat"> 
									</div>
							</div>
							<div class="form-group">
								<div class="col-sm-3"></div>
									<div class='col-sm-8'>
										<input style="text-transform: uppercase;" type="text" class="form-control" id="alamat2" name="alamat2"> 
									</div>
							</div>
							<div class="form-group">
								<div class="col-sm-3"></div>
									<div class='col-sm-8'>
										<input style="text-transform: uppercase;" type="text" class="form-control" id="alamat3" name="alamat3"> 
									</div>
							</div>
							
							<div class="form-group">
								<div class="col-sm-3"><label>Negeri/State <span id="red">*</span></label></div>
									<div class='col-sm-4'>
										<select class="form-control" id="negeri" name="negeri">
											<jsp:include page="../Select_State.jsp"></jsp:include>
										</select>
									</div>
							</div>
							
							<div class="form-group">
								<div class="col-sm-3"><label>Poskod/PostalCode <span id="red">*</span></label></div>
									<div class='col-sm-3'>
										<input type="text" class="form-control" id="poskod" name="poskod"> 
									</div>
							</div>
							
							<div class="form-group">
								<div class="col-sm-3"><label>Alamat Surat-Menyurat <span id="red">*</span></label></div>
									<div class='col-sm-8'>
										<input style="text-transform: uppercase;" type="text" class="form-control" id="alamatSurat" name="alamatSurat"> 
									</div>
							</div>
							<div class="form-group">
								<div class="col-sm-3"></div>
									<div class='col-sm-8'>
										<input style="text-transform: uppercase;" type="text" class="form-control" id="alamatSurat2" name="alamatSurat2"> 
									</div>
							</div>
							<div class="form-group">
								<div class="col-sm-3"></div>
									<div class='col-sm-8'>
										<input style="text-transform: uppercase;" type="text" class="form-control" id="alamatSurat3" name="alamatSurat3"> 
									</div>
							</div>
							
							<div class="form-group">
								<div class="col-sm-3"><label>Negeri Surat-Menyurat <span id="red">*</span></label></div>
									<div class='col-sm-4'>
										<select class="form-control" id="negeriSurat" name="negeriSurat">
											<jsp:include page="../Select_State.jsp"></jsp:include>
										</select>
									</div>
							</div>
							
							<div class="form-group">
								<div class="col-sm-3"><label>Poskod Surat-Menyurat<span id="red">*</span></label></div>
									<div class='col-sm-3'>
										<input type="text" class="form-control" id="poskodSurat" name="poskodSurat"> 
									</div>
							</div>
							
							<div class="form-group">
								<div class="col-sm-3"><label>Warganegara/Nationality<span id="red">*</span></label></div>
									<div class='col-sm-4'>
										<input style="text-transform: uppercase;" type="text" class="form-control" id="warganegara" name="warganegara"> 
									</div>
							</div>
							
							<div class="form-group">
								<div class="col-sm-3"><label>No. H/Phone <span id="red">*</span></label></div>
									<div class='col-sm-3'>
										<input type="text" class="form-control" id="phone" name="phone">
									</div>
							</div>
							
							<div class="form-group">
								<div class="col-sm-3"><label>No. Telefon Rumah</label></div>
									<div class='col-sm-3'>
										<input type="text" class="form-control" id="houseno" name="houseno">									
									</div>
							</div>
							
							<div class="form-group">
								<div class="col-sm-3"><label>Kaum</label></div>
									<div class="col-sm-3">
										<select class="form-control" id="race" name="race">
											<jsp:include page="../Select_Religon.jsp"></jsp:include>
										</select>
								   </div>
							</div>
							
							<!-- <div class="form-group">
									<div class="col-sm-3"><label>Saya ingin guna</label></div>
										<div class="col-sm-2">
											<label class="radio-inline"><input type="radio" id="kad" name="kad" value="M" checked>MyKad</label>
										</div>
										<div class="col-sm-5">
											<label class="radio-inline"><input type="radio" id="kad" name="kad" value="L">Kad ILMU (RM2.00)</label>
										</div>
							</div> -->
							
							<div class="form-group">
								<div class="col-sm-3"><label>Email</label></div>
									<div class='col-sm-5'>
										<input type="email" class="form-control" id="emailAhli" name="emailAhli">			
									</div>
							</div>
							
				
							<br>
							
							<div class="form-group">
								<div class="col-sm-10"></div>
							</div>
							
							<div class="form-group">
								<div class="col-sm-10">
								Pemohon dikehendaki melampirkan dokumen berikut semasa hadir ke mana-mana rangkaian PERPUSTAM :
								<br><br>
								i. Salinan Kad Pengenalan (MyKad/ MyKid) yang disah/ dicop Tadika /Sekolah/ IPTA/IPTS /Majikan<br>
								ii. Bil utiliti (alamat negeri Melaka) sekiranya pemohon bukan warga Melaka yang tidak belajar dan bekerja<br>
								iii. Pemohon golongan kelainan upaya (OKU) perlu melampirkan salinan surat atau kad OKU 
								<br><br>
								Untuk sebarang pertanyaan lanjut sila hubungi kami di:-
								<br>
								<br>
								Telefon : 06-2824859 <br>
								Email : perkhidmatan@perpustam.gov.my / perpustammelaka@gmail.com <br>
								Facebook Message: www.facebook.com/Perpustam
								</div>
							</div>
			
							
							<div class="modal-footer">							
							    <button type="submit" id="Register" class="btn btn-primary Register">Register</button>
							    <button type="button" id="cancel" class="btn btn-secondary">Cancel</button>  
							</div>
						</form>
					</div>
				</div>
			</div>
		<!-- END MAIN CONTENT -->	

</body>
</html>