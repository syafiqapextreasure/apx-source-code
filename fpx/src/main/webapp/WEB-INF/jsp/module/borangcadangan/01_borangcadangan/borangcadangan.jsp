<%@ page contentType="text/html; charset=UTF-8" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

	<%-- <script type="text/javascript" src="${pageContext.request.contextPath}/js/borangkeahlian.js"></script> --%>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/borangcadangan.js"></script>
	
	 <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/responsive/2.2.0/css/responsive.dataTables.min.css"> 
    <script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/responsive/2.2.0/js/dataTables.responsive.min.js"></script>

	
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
	
			<div class="box box-default centered" style="width:90%">
				<div class="panel panel-default">
				
					<div class="panel-heading">
						<div class="clearfix">
						<center><h3><b>BORANG CADANGAN PEROLEHAN BAHAN</b></h3></center></div>
					</div>
						
					<div class="panel-body" id="borangcadangan">
					
					<div class="pull-right">
						<a class="btn btn-primary btn-sm misc" data-toggle="modal" data-target="#detailcdgn" title="">
						<i class="glyphicon glyphicon-plus"></i></a>
					</div>
					
					<br><br>
					
					<table id="tblCdgnBahan" class="table table-bordered table-striped display compact" width="100%">
						 <thead>
						 	<tr>
						 		<th>No</th>
						 		<th>Judul</th>
								<th>Subjek</th>
								<th>ISBN</th>
								<th>Jenis Bahan</th>
								<th>Pengarang</th>
								<th>Penerbit</th>
								<th>Edisi</th>
								<th>Bil Salinan</th>
								<th>Bil Set</th>
								<th>Catatan</th>
								<th>Tindakan</th>
						 	</tr>
						 </thead>
					</table>
					
					<br>
					
					<div class="pull-right">
						<button type="button" id="hntrcdgn" class="btn btn-primary" title="Hantar"> Hantar</button>	
					</div>
					
					
						<!-- <table id="tblCdgn" class="table table-bordered table-striped display compact" width="100%">
						 <thead>
						 	<tr>
						 		<th>No</th>
						 		<th>Tarikh Permintaan</th>
								<th>Judul</th>
								<th>Jenis Bahan</th>
								<th>Bil Salinan</th>
								<th>Status</th>
								<th>Tindakan</th>
						 	</tr>
						 </thead>
						</table> -->
					
						<br>
						
						
						<div class="modal fade" id="detailcdgn" tabindex="-1" role="dialog" aria-labelledby="ModalAdd" aria-hidden="true" data-keyboard="false" data-backdrop="static">
							<div class="modal-dialog" style="width: 70%;">
							
							<div class="modal-content">
									<div class="modal-header">
										<h5 class="modal-title" id="modalName" align="center">Cadangan Bahan</h5>
										<button type="button" id="closeModalAdd" class="close" data-dismiss="modal" aria-label="Close">
											<span aria-hidden="true">&times;</span>
										</button>
									</div>
									
									<br>
									<form role="form" class="form-horizontal" id="formdataborangcadangan" method="post">
									
							<input type="hidden" class="form-control" id="rownos" name="rownos">
							
							<div class="form-group">
								<div class="col-sm-3"><label>Judul  <span id="red">*</span></label></div>
									<div class='col-sm-8'>
										<input type="text" class="form-control" id="judul" name="judul">
								</div>
							</div>
							
							
							<div class="form-group">
								<div class="col-sm-3"><label>Subjek</label></div>
									<div class='col-sm-3'>
										<!-- <input type="text" class="form-control" id="subjek" name="subjek"> -->
										<select class="form-control" id="subjek" name="subjek">
											<jsp:include page="../../../include/shared/Selection/Select_Subj.jsp"></jsp:include>
										</select>
									</div>
								<div class="col-sm-2"><label>ISBN</label></div>
									<div class='col-sm-3'>
										<input type="text" class="form-control" id="isbn" name="isbn">
								</div>
							</div>
							
							
							<div class="form-group">
								<div class="col-sm-3"><label>Jenis Bahan <span id="red">*</span></label></div>
									<div class="col-sm-3">
										<select class="form-control" id="jenisbahan" name="jenisbahan">
											<jsp:include page="../../../include/shared/Selection/Select_SMD.jsp"></jsp:include>
										</select>
									</div>
							</div> 
							
							<div class="form-group">
								<div class="col-sm-3"><label>Pengarang</label></div>
									<div class='col-sm-5'>
										<input type="text" class="form-control" id="pengarang" name="pengarang">			
									</div>
							</div>
							
							<div class="form-group">
								<div class="col-sm-3"><label>Penerbit</label></div>
									<div class='col-sm-5'>
										<input type="text" class="form-control" id="penerbit" name="penerbit">			
									</div>
							</div>
							
							<div class="form-group">
								<div class="col-sm-3"><label>Edisi</label></div>
									<div class='col-sm-3'>
										<input type="text" class="form-control" id="edisi" name="edisi">
									</div>
								<!-- <div class="col-sm-2"><label>No. LC</label></div>
									<div class='col-sm-3'>
										<input type="text" class="form-control" id="noLC" name="noLC">
								</div> -->
							</div>
							
							
							
							<div class="form-group">
								<div class="col-sm-3"><label>Bil Salinan  <span id="red">*</span></label></div>
									<div class='col-sm-3'>
										<input type="text" class="form-control" id="copy" name="copy">
								</div>
								<div class="col-sm-2"><label>Bil Set</label></div>
									<div class='col-sm-3'>
										<input type="text" class="form-control" id="set" name="set">
									</div>
							</div>
							
							<div class="form-group">
									<div class="col-sm-3"><label>Catatan</label></div>
										<div class='col-sm-8'>
											<textarea class="form-control" rows="3" id="remarks" name="remarks"></textarea>
										</div>
							</div>
							
	
							
							<br><br>
							
							<div class="modal-footer">
								<button type="submit" id="Tambah" class="btn btn-primary" title="Tambah"> Tambah</button>
								<button type="button" id="EditCdgn" class="btn btn-primary" title="Kemaskini"> Kemaskini</button>
								<button type="button" id="cancel" class="btn btn-secondary" title="Tutup" data-dismiss="modal"> Tutup</button>					        
						</div>	

						
							
							
						
						</form>
							</div>
				
							</div>
						</div>
		
					
						<form role="form" class="form-horizontal" id="detaiusercdgn">
								
								<div><h3><b>Maklumat Pencadang</b></h3></div>
								
								<div class="form-group">
										<div class="col-sm-3"><label>Id Pengguna</label></div>
											<div class='col-sm-3'>
												<input type="text" class="form-control" id="idcadang" name="idcadang" disabled>
										</div>
								</div>
								
								<div class="form-group">
										<div class="col-sm-3"><label>Nama</label></div>
											<div class='col-sm-6'>
												<input type="text" class="form-control" id="namacadang" name="namacadang" disabled>
										</div>
								</div>
								
								<div class="form-group">
									<div class="col-sm-3"><label>No Telefon</label></div>
										<div class='col-sm-3'>
											<input type="text" class="form-control" id="phone" name="phone" disabled>
										</div>
									<div class="col-sm-2"><label>Email</label></div>
										<div class='col-sm-3'>
											<input type="text" class="form-control" id="email" name="email" disabled>
									</div>
								</div>

									
								<div class="form-group">
									<div class="col-sm-3"><label>Tarikh</label></div>
									 <div class='col-sm-3'>
				                      	<div class="input-group date">
				  						 	<input type="text" class="form-control inputtarikh" id="inputtarikh" name="inputtarikh" disabled>
				  						 	<span class="input-group-addon"><i class="glyphicon glyphicon-calendar"></i></span>
										</div>
				        			</div>
								</div>
						
							
							
						
						</form>
					</div>
				</div>
			</div>
		<!-- END MAIN CONTENT -->	

</body>
</html>