<%@ page contentType="text/html; charset=UTF-8" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

	<script type="text/javascript" src="${pageContext.request.contextPath}/js/borangbahanrosak.js"></script>
	

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
			<div class="box box-default centered" style="width:65%">
				<div class="panel panel-default">
				
					<div class="panel-heading">
						<div class="clearfix">
						<center><h3><b>LAPORAN BAHAN ROSAK/HILANG</b></h3></center></div>
					</div>
						
					<div class="panel-body" id="laporanBhan">
						<br><h3>Bahan dalam Pinjaman</h3>
						<table id="tblPinjaman" class="table table-bordered table-striped display compact tblPinjaman" width="100%">
						 <thead>
						 	<tr>
						 		<th>No</th>
						 		<th>No Perolehan</th>
								<th>Judul</th>
								<th>Tarikh Pinjaman</th>
								<th>Tarikh Pulangan</th>
								<th>Tindakan</th>
						 	</tr>
						 </thead>
						</table>
						
						
						<br><h3>Laporan Bahan Hilang</h3>
						<table id="tblBahanHilang" class="table table-bordered table-striped display compact tblBahanHilang" width="100%">
						 <thead>
						 	<tr>
						 		<th>No</th>
						 		<th>No Perolehan</th>
								<th>Judul</th>
								<th>Status</th>
						 	</tr>
						 </thead>
						</table>
						
						<br><h3>Laporan Bahan Rosak</h3>
						<table id="tblBahanRosak" class="table table-bordered table-striped display compact tblBahanRosak" width="100%">
						 <thead>
						 	<tr>
						 		<th>No</th>
						 		<th>No Perolehan</th>
								<th>Judul</th>
								<th>Status</th>
						 	</tr>
						 </thead>
						</table>
					</div>
				</div>
			</div>
		<!-- END MAIN CONTENT -->	
		
		<div class="modal fade" id="modalbhnrosak" tabindex="-1" role="dialog" aria-labelledby="ModalAdd" aria-hidden="true" data-keyboard="false" data-backdrop="static">
			<div class="modal-dialog" style="width: 70%;">
				<div class="modal-content">
					<jsp:include page="Modal_LaporanBahanRosak.jsp"></jsp:include> 
				</div>
			</div>
		</div>
		
		<div class="modal fade" id="modalbhnhilang" tabindex="-1" role="dialog" aria-labelledby="ModalAdd" aria-hidden="true" data-keyboard="false" data-backdrop="static">
			<div class="modal-dialog" style="width: 70%;">
				<div class="modal-content">
					<jsp:include page="Modal_LaporanBahanHilang.jsp"></jsp:include> 
				</div>
			</div>
		</div>
		
		
		<!-- START MODAL ADD, EDIT, VIEW -->
		<!-- <div class="modal fade" id="modalbhnrosak" tabindex="-1" role="dialog" aria-labelledby="ModalAdd" aria-hidden="true" data-keyboard="false" data-backdrop="static">
			<div class="modal-dialog" role="document" style="width:70%">
				<div class="modal-content">
					Modal content
						
				</div>
			</div>
		</div> -->
		<!-- END START MODAL ADD, EDIT, VIEW -->

</body>
</html>