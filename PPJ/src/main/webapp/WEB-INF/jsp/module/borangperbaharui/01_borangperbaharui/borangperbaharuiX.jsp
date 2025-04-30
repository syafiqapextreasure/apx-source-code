<%@ page contentType="text/html; charset=UTF-8" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

	<script type="text/javascript" src="${pageContext.request.contextPath}/js/borangperbaharui.js"></script>
	

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
	
			<div class="box box-default centered" style="width:850px">
				<div class="panel panel-default">
				
					<div class="panel-heading">
						<div class="clearfix">
						<center><h3><b>BORANG PERBAHARUI KEAHLIAN</b></h3></center></div>
					</div>
						
					<div class="panel-body" id="perbaharuikeahlian">
						<form role="form" class="form-horizontal" id="formdataperbaharuikeahlian" method="post">
						
							<div class="form-group">
								<div class="col-sm-4"><label>Id </label></div>
									<div class='col-sm-7'>
										<input type="text" class="form-control" id="idAhli" name="idAhli">
								</div>
							</div>

							<div class="form-group">
								<div class="col-sm-4"><label>Nama </label></div>
									<div class='col-sm-7'>
										<input type="text" class="form-control" id="namaAhli" name="namaAhli" disabled>
								</div>
							</div>
							
							<div class="form-group">
								<div class="col-sm-4"><label>Email</label></div>
									<div class='col-sm-5'>
										<input type="email" class="form-control" id="emailAhli" name="emailAhli" disabled>			
									</div>
							</div>
							
							
							<input type="hidden" class="form-control" id="spantime" name="spantime">
							
							<div class="form-group">
								<div class="col-sm-4"><label>No Kad Pengenalan</label></div>
									<div class='col-sm-3'>
										<input type="text" class="form-control" id="ic" name="ic" disabled>
								</div>
							</div> 
							
							<div class="form-group">
								<div class="col-sm-4"><label>Tarikh Lahir</label></div>
								 <div class='col-sm-4'>
			                      	<div class="input-group date" id="dob">
			  						 	<input type="text" class="form-control inputDOB" id="inputDOB" name="inputDOB" disabled>
			  						 	<span class="input-group-addon"><i class="glyphicon glyphicon-calendar"></i></span>
									</div>
			        			</div>
							</div>
							
							<div class="form-group">
								<div class="col-sm-4"><label>Jantina</label></div>
			                      	<div class="col-sm-3">
										<label class="radio-inline"><input type="radio" name="jantina" value="F" disabled>Perempuan</label>
									</div>
									<div class="col-sm-3">
										<label class="radio-inline"><input type="radio" name="jantina" value="M" disabled>Lelaki</label>
									</div>
							</div>
							
							
							
							<div class="form-group">
								<div class="col-sm-4"><label>Alamat </label></div>
									<div class='col-sm-8'>
										<input type="text" class="form-control" id="alamat" name="alamat" disabled> 
									</div>
							</div>
							<div class="form-group">
								<div class="col-sm-4"></div>
									<div class='col-sm-8'>
										<input type="text" class="form-control" id="alamat2" name="alamat2" disabled> 
									</div>
							</div>
							<div class="form-group">
								<div class="col-sm-4"></div>
									<div class='col-sm-8'>
										<input type="text" class="form-control" id="alamat3" name="alamat3" disabled> 
									</div>
							</div>
							
							<div class="form-group">
								<div class="col-sm-4"><label>Poskod </label></div>
									<div class='col-sm-3'>
										<input type="text" class="form-control" id="poskod" name="poskod" disabled> 
									</div>
							</div>
							
							<div class="form-group">
								<div class="col-sm-4"><label>Negeri </label></div>
									<div class='col-sm-3'>
										<input type="text" class="form-control" id="negeri" name="bandar" disabled> 
									</div>
							</div>
							
							
							<div class="form-group">
								<div class="col-sm-4"><label>Warganegara </label></div>
									<div class='col-sm-3'>
										<input type="text" class="form-control" id="warganegara" name="warganegara" disabled> 
									</div>
							</div>
							
							<div class="form-group">
								<div class="col-sm-4"><label>No Telefon Bimbit</label></div>
									<div class='col-sm-3'>
										<input type="text" class="form-control" id="phone" name="phone" disabled>
									</div>
							</div>
							
							<div class="form-group">
								<div class="col-sm-4"><label>No Telefon Rumah</label></div>
									<div class='col-sm-3'>
										<input type="text" class="form-control" id="telrumah" name="telrumah" disabled>
									</div>
							</div>
							
							<div class="form-group">
								<div class="col-sm-4"><label>Tarikh Tamant Tempoh</label></div>
								 <div class='col-sm-4'>
			                      	<div class="input-group date" id="dob">
			  						 	<input type="text" class="form-control inputExpDate" id="inputExpDate" name="inputExpDate" disabled>
			  						 	<span class="input-group-addon"><i class="glyphicon glyphicon-calendar"></i></span>
									</div>
			        			</div>
							</div>
							
							
														
									<div class="modal-footer">							
									    <button type="submit" id="Renew" class="btn btn-primary renew">Pembaharui Keahlian</button>
									    <button type="button" id="cancel" class="btn btn-secondary">Batalkan</button>  
									</div>
							
						
						</form>
					</div>
				</div>
			</div>
		<!-- END MAIN CONTENT -->	

</body>
</html>